/*
 * Decompiled with CFR 0.152.
 */
package com.electronwill.nightconfig.core.file;

import com.electronwill.nightconfig.core.file.DebouncedRunnable;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public final class FileWatcher {
    private static final AtomicInteger instanceCount = new AtomicInteger(0);
    private static volatile FileWatcher DEFAULT_INSTANCE = null;
    private static final Duration DEFAULT_SERVICE_POLL_TIMEOUT = Duration.ofMillis(200L);
    private static final Duration DEFAULT_DEBOUNCE_TIME = Duration.ofMillis(500L);
    private final ThreadGroup threadGroup;
    private final AtomicInteger threadCount = new AtomicInteger(0);
    private final ConcurrentMap<FileSystem, FsWatcher> watchers = new ConcurrentHashMap<FileSystem, FsWatcher>();
    private final Consumer<Throwable> exceptionHandler;
    private final Duration debounceTime;
    private final long servicePollTimeoutNanos;
    private final int instanceId = instanceCount.getAndIncrement();
    private volatile boolean running = true;

    public static synchronized FileWatcher defaultInstance() {
        if (DEFAULT_INSTANCE == null || !FileWatcher.DEFAULT_INSTANCE.running) {
            DEFAULT_INSTANCE = new FileWatcher();
        }
        return DEFAULT_INSTANCE;
    }

    public FileWatcher() {
        this(DEFAULT_DEBOUNCE_TIME);
    }

    public FileWatcher(Consumer<Exception> exceptionHandler) {
        this(DEFAULT_DEBOUNCE_TIME, t -> {
            if (t instanceof Exception) {
                exceptionHandler.accept((Exception)t);
            } else {
                exceptionHandler.accept(new RuntimeException((Throwable)t));
            }
        });
    }

    public FileWatcher(Duration debounceTime) {
        this(debounceTime, Throwable::printStackTrace);
    }

    public FileWatcher(Duration debounceTime, Consumer<Throwable> exceptionHandler) {
        this(debounceTime, DEFAULT_SERVICE_POLL_TIMEOUT, exceptionHandler);
    }

    FileWatcher(Duration debounceTime, Duration servicePollTimeout, Consumer<Throwable> exceptionHandler) {
        this.debounceTime = debounceTime;
        this.servicePollTimeoutNanos = servicePollTimeout.toNanos();
        this.exceptionHandler = exceptionHandler;
        this.threadGroup = new ThreadGroup("watchers-" + this.instanceId);
    }

    public void addWatch(File file, Runnable changeHandler) {
        this.addWatch(file.toPath(), changeHandler);
    }

    public void addWatch(Path file, Runnable changeHandler) {
        this.addOrPutWatch(file, changeHandler, ControlMessageKind.ADD, null);
    }

    public CompletableFuture<Void> addWatchFuture(Path file, Runnable changeHandler) {
        this.failIfStopped();
        CompletableFuture<Void> future = new CompletableFuture<Void>();
        try {
            this.addOrPutWatch(file, changeHandler, ControlMessageKind.ADD, future);
        }
        catch (Exception ex) {
            future.completeExceptionally(ex);
        }
        return future;
    }

    public void setWatch(File file, Runnable changeHandler) {
        this.setWatch(file.toPath(), changeHandler);
    }

    public void setWatch(Path file, Runnable changeHandler) {
        this.addOrPutWatch(file, changeHandler, ControlMessageKind.PUT, null);
    }

    public CompletableFuture<Void> setWatchFuture(Path file, Runnable changeHandler) {
        this.failIfStopped();
        CompletableFuture<Void> future = new CompletableFuture<Void>();
        try {
            this.addOrPutWatch(file, changeHandler, ControlMessageKind.PUT, future);
        }
        catch (Exception ex) {
            future.completeExceptionally(ex);
        }
        return future;
    }

    private void addOrPutWatch(Path file, Runnable changeHandler, ControlMessageKind kind, CompletableFuture<Void> future) {
        this.failIfStopped();
        try {
            if (Files.exists(file, new LinkOption[0]) && Files.readAttributes(file, BasicFileAttributes.class, new LinkOption[0]).isDirectory()) {
                throw new IllegalArgumentException("FileWatcher is designed to watch files but this path is a directory, not a file: " + String.valueOf(file));
            }
        }
        catch (IOException ex) {
            throw new WatchingException("Failed to get information about path: " + String.valueOf(file), ex);
        }
        CanonicalPath canon = CanonicalPath.from(file);
        FileSystem fs = canon.parentDirectory.getFileSystem();
        try {
            FsWatcher watcher = this.watchers.computeIfAbsent(fs, k -> {
                try {
                    WatchService service = fs.newWatchService();
                    FsWatcher w = new FsWatcher(this.exceptionHandler, this.debounceTime, this.servicePollTimeoutNanos, service);
                    String threadName = "config-file-watcher-" + this.instanceId + "-" + this.threadCount.getAndIncrement();
                    Thread t = new Thread(this.threadGroup, w, threadName);
                    t.setDaemon(true);
                    t.start();
                    return w;
                }
                catch (IOException ex) {
                    throw new WatchingException("Failed to start a new watcher thread for directory " + String.valueOf(canon.parentDirectory), ex);
                }
            });
            watcher.send(ControlMessage.addOrPut(kind, canon, changeHandler, future));
        }
        catch (Exception ex) {
            throw new WatchingException("Failed to watch path '" + String.valueOf(file) + "', canonical path '" + String.valueOf(canon) + "'", ex);
        }
    }

    public void removeWatch(File file) {
        this.removeWatch(file.toPath());
    }

    public void removeWatch(Path file) {
        this.failIfStopped();
        this.removeWatch(file, null);
    }

    public CompletableFuture<Void> removeWatchFuture(Path file) {
        this.failIfStopped();
        CompletableFuture<Void> future = new CompletableFuture<Void>();
        try {
            this.removeWatch(file, future);
        }
        catch (Exception ex) {
            future.completeExceptionally(ex);
        }
        return future;
    }

    private void removeWatch(Path file, CompletableFuture<Void> future) {
        CanonicalPath canon = CanonicalPath.from(file);
        FileSystem fs = canon.parentDirectory.getFileSystem();
        FsWatcher watcher = (FsWatcher)this.watchers.get(fs);
        if (watcher != null) {
            watcher.send(ControlMessage.remove(canon, future));
        }
    }

    public void stop() {
        this.running = false;
        for (FsWatcher watcher : this.watchers.values()) {
            watcher.send(ControlMessage.poison(null));
        }
        this.threadGroup.interrupt();
    }

    public CompletableFuture<Void> stopFuture() {
        this.running = false;
        Collection allWatchers = this.watchers.values();
        if (allWatchers.size() == 0) {
            return CompletableFuture.completedFuture(null);
        }
        CompletableFuture<Void> main = new CompletableFuture<Void>();
        AtomicInteger remainingChildCount = new AtomicInteger(allWatchers.size());
        for (FsWatcher watcher : allWatchers) {
            CompletableFuture<Void> f = new CompletableFuture<Void>();
            f.handle((ok, err) -> {
                int remaining = remainingChildCount.decrementAndGet();
                if (remaining == 0) {
                    if (err == null) {
                        main.complete(null);
                    } else {
                        main.completeExceptionally((Throwable)err);
                    }
                }
                return null;
            });
            watcher.send(ControlMessage.poison(f));
        }
        this.threadGroup.interrupt();
        return main;
    }

    private void failIfStopped() {
        if (!this.running) {
            throw new IllegalStateException("FileWatcher " + this.instanceId + " has been stopped and cannot be used anymore.");
        }
    }

    private static enum ControlMessageKind {
        PUT,
        ADD,
        REMOVE,
        POISON;

    }

    public static class WatchingException
    extends RuntimeException {
        public WatchingException(String message, Throwable cause) {
            super(message, cause);
        }

        public WatchingException(Throwable cause) {
            super(cause);
        }

        public WatchingException(String message) {
            super(message);
        }
    }

    private static class CanonicalPath {
        public final Path parentDirectory;
        public final Path fileName;

        private CanonicalPath(Path parentDirectory, Path fileName) {
            this.parentDirectory = parentDirectory;
            this.fileName = fileName;
        }

        public static CanonicalPath from(Path fullFilePath) {
            try {
                Path fileName;
                Path dir;
                try {
                    Path realFile = fullFilePath.toRealPath(new LinkOption[0]);
                    dir = realFile.getParent();
                    fileName = realFile.getFileName();
                }
                catch (NoSuchFileException e) {
                    dir = fullFilePath.getParent().toRealPath(new LinkOption[0]);
                    fileName = fullFilePath.getFileName();
                }
                return new CanonicalPath(dir, fileName);
            }
            catch (IOException ex) {
                throw new WatchingException("Failed to determine the canonical path of: " + String.valueOf(fullFilePath) + "\nHint: make sure that all parent directories exist.", ex);
            }
        }

        public String toString() {
            return String.valueOf(this.parentDirectory) + "/" + String.valueOf(this.fileName);
        }
    }

    private static final class FsWatcher
    implements Runnable {
        private final Consumer<Throwable> exceptionHandler;
        private final Duration debounceTime;
        private final long servicePollTimeoutNanos;
        private final WatchService watchService;
        private final Map<Path, WatchedDirectory> watchedDirectories = new HashMap<Path, WatchedDirectory>();
        private final ConcurrentLinkedQueue<ControlMessage> controlMessages = new ConcurrentLinkedQueue();

        FsWatcher(Consumer<Throwable> exceptionHandler, Duration debounceTime, long servicePollTimeoutNanos, WatchService watchService) {
            this.exceptionHandler = exceptionHandler;
            this.debounceTime = debounceTime;
            this.servicePollTimeoutNanos = servicePollTimeoutNanos;
            this.watchService = watchService;
        }

        void send(ControlMessage msg) {
            this.controlMessages.add(msg);
        }

        private WatchedDirectory watchDirectory(Path dir, CompletableFuture<Void> future) {
            return this.watchedDirectories.computeIfAbsent(dir, k -> {
                try {
                    WatchKey key = dir.register(this.watchService, StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_CREATE);
                    return new WatchedDirectory(key, new HashMap<Path, DebouncedRunnable>(8));
                }
                catch (Exception ex) {
                    if (future != null) {
                        future.completeExceptionally(ex);
                    } else {
                        this.exceptionHandler.accept(ex);
                    }
                    return null;
                }
            });
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        @Override
        public void run() {
            NamedDaemonThreadFactory threadFactory = new NamedDaemonThreadFactory("FileWatcher-", "-thread-");
            ScheduledExecutorService executor = Executors.newScheduledThreadPool(1, threadFactory);
            CompletableFuture<Void> shutdownFuture = null;
            block12: while (true) {
                Iterator<WatchEvent<?>> fileName4;
                WatchedDirectory w;
                Path dir;
                WatchKey key;
                block21: {
                    block20: {
                        block19: {
                            ControlMessage msg;
                            if ((msg = this.controlMessages.poll()) == null) break block19;
                            CanonicalPath path = msg.path;
                            CompletableFuture<Void> future = msg.future;
                            switch (msg.kind.ordinal()) {
                                case 1: {
                                    Path dir2 = path.parentDirectory;
                                    Path fileName2 = path.fileName;
                                    WatchedDirectory w2 = this.watchDirectory(dir2, future);
                                    if (w2 == null) break;
                                    DebouncedRunnable existingHandler = w2.fileChangeHandlers.get(fileName2);
                                    DebouncedRunnable newHandler = existingHandler != null ? existingHandler.andThen(msg.handler) : new DebouncedRunnable(msg.handler, this.debounceTime);
                                    w2.fileChangeHandlers.put(fileName2, newHandler);
                                    break;
                                }
                                case 0: {
                                    Path dir2 = path.parentDirectory;
                                    Path fileName3 = path.fileName;
                                    WatchedDirectory w2 = this.watchDirectory(dir2, future);
                                    if (w2 == null) break;
                                    DebouncedRunnable newHandler = new DebouncedRunnable(msg.handler, this.debounceTime);
                                    w2.fileChangeHandlers.put(fileName3, newHandler);
                                    break;
                                }
                                case 2: {
                                    Path dir2 = path.parentDirectory;
                                    Path fileName4 = path.fileName;
                                    WatchedDirectory w2 = this.watchedDirectories.get(dir2);
                                    if (w2 == null) break;
                                    w2.fileChangeHandlers.remove(fileName4);
                                    if (!w2.fileChangeHandlers.isEmpty()) break;
                                    w2.key.cancel();
                                    break;
                                }
                                case 3: {
                                    shutdownFuture = future;
                                    break block20;
                                }
                            }
                            if (future == null) continue;
                            future.complete(null);
                            continue;
                        }
                        key = null;
                        try {
                            key = this.watchService.poll(this.servicePollTimeoutNanos, TimeUnit.NANOSECONDS);
                        }
                        catch (InterruptedException e) {
                            continue;
                        }
                        if (key == null) continue;
                        dir = (Path)key.watchable();
                        w = this.watchedDirectories.get(dir);
                        fileName4 = key.pollEvents().iterator();
                        break block21;
                    }
                    try {
                        executor.shutdown();
                        this.watchService.close();
                        this.watchedDirectories.clear();
                    }
                    catch (Exception e) {
                        if (shutdownFuture != null) {
                            shutdownFuture.completeExceptionally(e);
                        }
                        this.exceptionHandler.accept(e);
                    }
                    if (shutdownFuture != null) {
                        shutdownFuture.complete(null);
                    }
                    return;
                }
                while (fileName4.hasNext()) {
                    WatchEvent<?> evt = fileName4.next();
                    WatchEvent.Kind<?> kind = evt.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW) {
                        this.exceptionHandler.accept(new WatchingException("Got watch event OVERFLOW"));
                    } else {
                        Path file = (Path)evt.context();
                        DebouncedRunnable changeHandler = w.fileChangeHandlers.get(file);
                        if (changeHandler != null) {
                            try {
                                changeHandler.run(executor);
                            }
                            catch (Exception ex) {
                                this.exceptionHandler.accept(ex);
                            }
                        }
                    }
                    if (!Thread.interrupted()) continue;
                    continue block12;
                }
                boolean valid = key.reset();
                if (valid) continue;
                this.watchedDirectories.remove(dir);
            }
        }
    }

    private static final class ControlMessage {
        private final ControlMessageKind kind;
        private final CanonicalPath path;
        private final Runnable handler;
        private final CompletableFuture<Void> future;

        private ControlMessage(ControlMessageKind kind, CanonicalPath path, Runnable handler, CompletableFuture<Void> future) {
            this.path = path;
            this.kind = kind;
            this.handler = handler;
            this.future = future;
        }

        static ControlMessage addOrPut(ControlMessageKind kind, CanonicalPath path, Runnable handler, CompletableFuture<Void> future) {
            if (kind != ControlMessageKind.ADD && kind != ControlMessageKind.PUT) {
                throw new IllegalArgumentException("Unexpected message kind " + String.valueOf((Object)kind));
            }
            return new ControlMessage(kind, path, handler, future);
        }

        static ControlMessage remove(CanonicalPath path, CompletableFuture<Void> future) {
            return new ControlMessage(ControlMessageKind.REMOVE, path, null, future);
        }

        static ControlMessage poison(CompletableFuture<Void> future) {
            return new ControlMessage(ControlMessageKind.POISON, null, null, future);
        }

        public String toString() {
            return "ControlMessage[kind=" + String.valueOf((Object)this.kind) + ", path=" + String.valueOf(this.path) + ", handler=" + String.valueOf(this.handler) + ", future=" + String.valueOf(this.future) + "]";
        }
    }

    private static class NamedDaemonThreadFactory
    implements ThreadFactory {
        private static final AtomicInteger FACTORY_NUMBER = new AtomicInteger(1);
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        NamedDaemonThreadFactory(String prefix, String suffix) {
            this.namePrefix = prefix + FACTORY_NUMBER.getAndIncrement() + suffix;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, this.namePrefix + this.threadNumber.getAndIncrement());
            t.setDaemon(true);
            t.setPriority(5);
            return t;
        }
    }

    private static final class WatchedDirectory {
        private final WatchKey key;
        private final Map<Path, DebouncedRunnable> fileChangeHandlers;

        WatchedDirectory(WatchKey key, Map<Path, DebouncedRunnable> fileChangeHandlers) {
            this.key = Objects.requireNonNull(key);
            this.fileChangeHandlers = Objects.requireNonNull(fileChangeHandlers);
        }
    }
}

