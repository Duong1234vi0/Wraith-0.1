/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterators
 *  com.google.common.collect.Lists
 *  com.google.common.util.concurrent.MoreExecutors
 *  com.mojang.datafixers.DSL$TypeReference
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.DataResult
 *  it.unimi.dsi.fastutil.Hash$Strategy
 *  javax.annotation.Nullable
 *  org.apache.commons.io.IOUtils
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.MoreExecutors;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.DataResult;
import it.unimi.dsi.fastutil.Hash;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.F_491_v;
import lightning.product.SharedConstants;
import lightning.product.CharPredicate;
import lightning.product.g_2336_b;
import lightning.product.ReportedException;
import lightning.product.DefaultUncaughtExceptionHandler;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;
import lightning.product.y_3482_a;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class j_3341_s {
    private static final AtomicInteger R_4764_Y = new AtomicInteger(1);
    private static final ExecutorService G_564_y = j_3341_s.n_1700_B("Bootstrap");
    private static final ExecutorService P_1922_E = j_3341_s.n_1700_B("Main");
    private static final ExecutorService u_1723_Y = j_3341_s.Q_4569_t();
    public static LongSupplier n_1700_B = System::nanoTime;
    public static final UUID J_1907_R = new UUID(0L, 0L);
    private static final Logger v_4262_N = LogManager.getLogger();
    private static Exception w_1484_f;
    private static final ExecutorService t_148_a;

    public static <K, V> Collector<Map.Entry<? extends K, ? extends V>, ?, Map<K, V>> n_1700_B() {
        return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue);
    }

    public static <T extends Comparable<T>> String n_1700_B(v_3760_Q<T> property, Object value) {
        return property.n_1700_B((Comparable)value);
    }

    public static String n_1700_B(String type, @Nullable g_2336_b id) {
        return id == null ? type + ".unregistered_sadface" : type + "." + id.R_4764_Y() + "." + id.J_1907_R().replace('/', '.');
    }

    public static long J_1907_R() {
        return j_3341_s.R_4764_Y() / 1000000L;
    }

    public static long R_4764_Y() {
        return n_1700_B.getAsLong();
    }

    public static long G_564_y() {
        return Instant.now().toEpochMilli();
    }

    private static ExecutorService n_1700_B(String serviceName) {
        int i = u_530_F.n_1700_B(Runtime.getRuntime().availableProcessors() - 1, 1, 7);
        Object executorservice = i <= 0 ? MoreExecutors.newDirectExecutorService() : new ForkJoinPool(i, p_lambda$createExecutor$0_1_ -> {
            ForkJoinWorkerThread forkjoinworkerthread = new ForkJoinWorkerThread(p_lambda$createExecutor$0_1_){

                @Override
                protected void onTermination(Throwable p_onTermination_1_) {
                    if (p_onTermination_1_ != null) {
                        v_4262_N.warn("{} died", (Object)this.getName(), (Object)p_onTermination_1_);
                    } else {
                        v_4262_N.debug("{} shutdown", (Object)this.getName());
                    }
                    super.onTermination(p_onTermination_1_);
                }
            };
            forkjoinworkerthread.setName("Worker-" + serviceName + "-" + R_4764_Y.getAndIncrement());
            return forkjoinworkerthread;
        }, j_3341_s::n_1700_B, true);
        return executorservice;
    }

    public static Executor P_1922_E() {
        return G_564_y;
    }

    public static Executor u_1723_Y() {
        return P_1922_E;
    }

    public static Executor v_4262_N() {
        return u_1723_Y;
    }

    public static void w_1484_f() {
        j_3341_s.n_1700_B(P_1922_E);
        j_3341_s.n_1700_B(u_1723_Y);
        j_3341_s.n_1700_B(t_148_a);
    }

    private static void n_1700_B(ExecutorService p_240985_0_) {
        boolean flag;
        p_240985_0_.shutdown();
        try {
            flag = p_240985_0_.awaitTermination(3L, TimeUnit.SECONDS);
        }
        catch (InterruptedException interruptedexception) {
            flag = false;
        }
        if (!flag) {
            p_240985_0_.shutdownNow();
        }
    }

    private static ExecutorService Q_4569_t() {
        return Executors.newCachedThreadPool(p_lambda$createIoExecutor$1_0_ -> {
            Thread thread = new Thread(p_lambda$createIoExecutor$1_0_);
            thread.setName("IO-Worker-" + R_4764_Y.getAndIncrement());
            thread.setUncaughtExceptionHandler(j_3341_s::n_1700_B);
            return thread;
        });
    }

    public static <T> CompletableFuture<T> n_1700_B(Throwable throwableIn) {
        CompletableFuture completablefuture = new CompletableFuture();
        completablefuture.completeExceptionally(throwableIn);
        return completablefuture;
    }

    public static void J_1907_R(Throwable throwableIn) {
        throw throwableIn instanceof RuntimeException ? (RuntimeException)throwableIn : new RuntimeException(throwableIn);
    }

    private static void n_1700_B(Thread thread, Throwable throwable) {
        j_3341_s.R_4764_Y(throwable);
        if (throwable instanceof CompletionException) {
            throwable = throwable.getCause();
        }
        if (throwable instanceof ReportedException) {
            y_3482_a.n_1700_B(((ReportedException)throwable).n_1700_B().G_564_y());
            System.exit(-1);
        }
        v_4262_N.error(String.format("Caught exception in thread %s", thread), throwable);
    }

    @Nullable
    public static Type<?> n_1700_B(DSL.TypeReference type, String choiceName) {
        return !SharedConstants.R_4764_Y ? null : j_3341_s.J_1907_R(type, choiceName);
    }

    @Nullable
    private static Type<?> J_1907_R(DSL.TypeReference typeIn, String choiceName) {
        Type type;
        block2: {
            type = null;
            try {
                type = F_491_v.n_1700_B().getSchema(DataFixUtils.makeKey((int)SharedConstants.n_1700_B().getWorldVersion())).getChoiceType(typeIn, choiceName);
            }
            catch (IllegalArgumentException illegalargumentexception) {
                v_4262_N.debug("No data fixer registered for {}", (Object)choiceName);
                if (!SharedConstants.G_564_y) break block2;
                throw illegalargumentexception;
            }
        }
        return type;
    }

    public static J_1907_R t_148_a() {
        String s = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        if (s.contains("win")) {
            return lightning.product.j_3341_s$J_1907_R.R_4764_Y;
        }
        if (s.contains("mac")) {
            return lightning.product.j_3341_s$J_1907_R.G_564_y;
        }
        if (s.contains("solaris")) {
            return lightning.product.j_3341_s$J_1907_R.J_1907_R;
        }
        if (s.contains("sunos")) {
            return lightning.product.j_3341_s$J_1907_R.J_1907_R;
        }
        if (s.contains("linux")) {
            return lightning.product.j_3341_s$J_1907_R.n_1700_B;
        }
        return s.contains("unix") ? lightning.product.j_3341_s$J_1907_R.n_1700_B : lightning.product.j_3341_s$J_1907_R.P_1922_E;
    }

    public static Stream<String> s_956_w() {
        RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
        return runtimemxbean.getInputArguments().stream().filter(p_lambda$getJvmFlags$2_0_ -> p_lambda$getJvmFlags$2_0_.startsWith("-X"));
    }

    public static <T> T n_1700_B(List<T> listIn) {
        return listIn.get(listIn.size() - 1);
    }

    public static <T> T n_1700_B(Iterable<T> iterable, @Nullable T element) {
        Iterator<T> iterator = iterable.iterator();
        T t = iterator.next();
        if (element != null) {
            T t1 = t;
            while (t1 != element) {
                if (!iterator.hasNext()) continue;
                t1 = iterator.next();
            }
            if (iterator.hasNext()) {
                return iterator.next();
            }
        }
        return t;
    }

    public static <T> T J_1907_R(Iterable<T> iterable, @Nullable T current) {
        Iterator<T> iterator = iterable.iterator();
        T t = null;
        while (iterator.hasNext()) {
            T t1 = iterator.next();
            if (t1 == current) {
                if (t != null) break;
                t = (T)(iterator.hasNext() ? Iterators.getLast(iterator) : current);
                break;
            }
            t = t1;
        }
        return t;
    }

    public static <T> T n_1700_B(Supplier<T> supplier) {
        return supplier.get();
    }

    public static <T> T n_1700_B(T object, Consumer<T> consumer) {
        consumer.accept(object);
        return object;
    }

    public static <K> Hash.Strategy<K> u_2550_I() {
        return lightning.product.j_3341_s$n_1700_B.n_1700_B;
    }

    public static <V> CompletableFuture<List<V>> J_1907_R(List<? extends CompletableFuture<? extends V>> futuresIn) {
        ArrayList list = Lists.newArrayListWithCapacity((int)futuresIn.size());
        CompletableFuture[] completablefuture = new CompletableFuture[futuresIn.size()];
        CompletableFuture completablefuture1 = new CompletableFuture();
        futuresIn.forEach(p_lambda$gather$4_3_ -> {
            int i = list.size();
            list.add(null);
            completablefuture[i] = p_lambda$gather$4_3_.whenComplete((p_lambda$null$3_3_, p_lambda$null$3_4_) -> {
                if (p_lambda$null$3_4_ != null) {
                    completablefuture1.completeExceptionally((Throwable)p_lambda$null$3_4_);
                } else {
                    list.set(i, p_lambda$null$3_3_);
                }
            });
        });
        return CompletableFuture.allOf(completablefuture).applyToEither((CompletionStage)completablefuture1, p_lambda$gather$5_1_ -> list);
    }

    public static <T> Stream<T> n_1700_B(Optional<? extends T> optionalIn) {
        return optionalIn.isPresent() ? Stream.of(optionalIn.get()) : Stream.empty();
    }

    public static Exception M_588_G() {
        return w_1484_f;
    }

    public static void n_1700_B(Exception p_setExceptionOpenUrl_0_) {
        w_1484_f = p_setExceptionOpenUrl_0_;
    }

    public static ExecutorService P_4830_p() {
        return t_148_a;
    }

    public static <T> Optional<T> n_1700_B(Optional<T> opt, Consumer<T> consumer, Runnable orElse) {
        if (opt.isPresent()) {
            consumer.accept(opt.get());
        } else {
            orElse.run();
        }
        return opt;
    }

    public static Runnable n_1700_B(Runnable runnableIn, Supplier<String> supplierIn) {
        return runnableIn;
    }

    public static <T extends Throwable> T R_4764_Y(T throwableIn) {
        if (SharedConstants.G_564_y) {
            v_4262_N.error("Trying to throw a fatal exception, pausing in IDE", throwableIn);
            try {
                while (true) {
                    Thread.sleep(1000L);
                    v_4262_N.error("paused");
                }
            }
            catch (InterruptedException interruptedexception) {
                return throwableIn;
            }
        }
        return throwableIn;
    }

    public static String G_564_y(Throwable throwableIn) {
        if (throwableIn.getCause() != null) {
            return j_3341_s.G_564_y(throwableIn.getCause());
        }
        return throwableIn.getMessage() != null ? throwableIn.getMessage() : throwableIn.toString();
    }

    public static <T> T n_1700_B(T[] selections, Random rand) {
        return selections[rand.nextInt(selections.length)];
    }

    public static int n_1700_B(int[] selections, Random rand) {
        return selections[rand.nextInt(selections.length)];
    }

    private static BooleanSupplier n_1700_B(final Path p_244363_0_, final Path p_244363_1_) {
        return new BooleanSupplier(){

            @Override
            public boolean getAsBoolean() {
                try {
                    Files.move(p_244363_0_, p_244363_1_, new CopyOption[0]);
                    return true;
                }
                catch (IOException ioexception) {
                    v_4262_N.error("Failed to rename", (Throwable)ioexception);
                    return false;
                }
            }

            public String toString() {
                return "rename " + String.valueOf(p_244363_0_) + " to " + String.valueOf(p_244363_1_);
            }
        };
    }

    private static BooleanSupplier n_1700_B(final Path p_244362_0_) {
        return new BooleanSupplier(){

            @Override
            public boolean getAsBoolean() {
                try {
                    Files.deleteIfExists(p_244362_0_);
                    return true;
                }
                catch (IOException ioexception) {
                    v_4262_N.warn("Failed to delete", (Throwable)ioexception);
                    return false;
                }
            }

            public String toString() {
                return "delete old " + String.valueOf(p_244362_0_);
            }
        };
    }

    private static BooleanSupplier J_1907_R(final Path p_244366_0_) {
        return new BooleanSupplier(){

            @Override
            public boolean getAsBoolean() {
                return !Files.exists(p_244366_0_, new LinkOption[0]);
            }

            public String toString() {
                return "verify that " + String.valueOf(p_244366_0_) + " is deleted";
            }
        };
    }

    private static BooleanSupplier R_4764_Y(final Path p_244367_0_) {
        return new BooleanSupplier(){

            @Override
            public boolean getAsBoolean() {
                return Files.isRegularFile(p_244367_0_, new LinkOption[0]);
            }

            public String toString() {
                return "verify that " + String.valueOf(p_244367_0_) + " is present";
            }
        };
    }

    private static boolean n_1700_B(BooleanSupplier ... p_244365_0_) {
        for (BooleanSupplier booleansupplier : p_244365_0_) {
            if (booleansupplier.getAsBoolean()) continue;
            v_4262_N.warn("Failed to execute {}", (Object)booleansupplier);
            return false;
        }
        return true;
    }

    private static boolean n_1700_B(int p_244359_0_, String p_244359_1_, BooleanSupplier ... p_244359_2_) {
        for (int i = 0; i < p_244359_0_; ++i) {
            if (j_3341_s.n_1700_B(p_244359_2_)) {
                return true;
            }
            v_4262_N.error("Failed to {}, retrying {}/{}", (Object)p_244359_1_, (Object)i, (Object)p_244359_0_);
        }
        v_4262_N.error("Failed to {}, aborting, progress might be lost", (Object)p_244359_1_);
        return false;
    }

    public static void n_1700_B(File current, File latest, File oldBackup) {
        j_3341_s.n_1700_B(current.toPath(), latest.toPath(), oldBackup.toPath());
    }

    public static void n_1700_B(Path p_244364_0_, Path p_244364_1_, Path p_244364_2_) {
        int i = 10;
        if ((!Files.exists(p_244364_0_, new LinkOption[0]) || j_3341_s.n_1700_B(10, "create backup " + String.valueOf(p_244364_2_), j_3341_s.n_1700_B(p_244364_2_), j_3341_s.n_1700_B(p_244364_0_, p_244364_2_), j_3341_s.R_4764_Y(p_244364_2_))) && j_3341_s.n_1700_B(10, "remove old " + String.valueOf(p_244364_0_), j_3341_s.n_1700_B(p_244364_0_), j_3341_s.J_1907_R(p_244364_0_)) && !j_3341_s.n_1700_B(10, "replace " + String.valueOf(p_244364_0_) + " with " + String.valueOf(p_244364_1_), j_3341_s.n_1700_B(p_244364_1_, p_244364_0_), j_3341_s.R_4764_Y(p_244364_0_))) {
            j_3341_s.n_1700_B(10, "restore " + String.valueOf(p_244364_0_) + " from " + String.valueOf(p_244364_2_), j_3341_s.n_1700_B(p_244364_2_, p_244364_0_), j_3341_s.R_4764_Y(p_244364_0_));
        }
    }

    public static int n_1700_B(String p_240980_0_, int p_240980_1_, int p_240980_2_) {
        int i = p_240980_0_.length();
        if (p_240980_2_ >= 0) {
            for (int j = 0; p_240980_1_ < i && j < p_240980_2_; ++j) {
                if (!Character.isHighSurrogate(p_240980_0_.charAt(p_240980_1_++)) || p_240980_1_ >= i || !Character.isLowSurrogate(p_240980_0_.charAt(p_240980_1_))) continue;
                ++p_240980_1_;
            }
        } else {
            for (int k = p_240980_2_; p_240980_1_ > 0 && k < 0; ++k) {
                if (!Character.isLowSurrogate(p_240980_0_.charAt(--p_240980_1_)) || p_240980_1_ <= 0 || !Character.isHighSurrogate(p_240980_0_.charAt(p_240980_1_ - 1))) continue;
                --p_240980_1_;
            }
        }
        return p_240980_1_;
    }

    public static Consumer<String> n_1700_B(String prefix, Consumer<String> p_240982_1_) {
        return p_lambda$func_240982_a_$6_2_ -> p_240982_1_.accept(prefix + p_lambda$func_240982_a_$6_2_);
    }

    public static DataResult<int[]> n_1700_B(IntStream stream, int size) {
        int[] aint = stream.limit(size + 1).toArray();
        if (aint.length != size) {
            String s = "Input is not a list of " + size + " ints";
            return aint.length >= size ? DataResult.error((String)s, (Object)Arrays.copyOf(aint, size)) : DataResult.error((String)s);
        }
        return DataResult.success((Object)aint);
    }

    public static void h_1847_R() {
        Thread thread = new Thread("Timer hack thread"){

            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(Integer.MAX_VALUE);
                    }
                }
                catch (InterruptedException interruptedexception) {
                    v_4262_N.warn("Timer hack thread interrupted, that really should not happen");
                    return;
                }
            }
        };
        thread.setDaemon(true);
        thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(v_4262_N));
        thread.start();
    }

    public static void J_1907_R(Path p_240984_0_, Path p_240984_1_, Path p_240984_2_) throws IOException {
        Path path = p_240984_0_.relativize(p_240984_2_);
        Path path1 = p_240984_1_.resolve(path);
        Files.copy(p_240984_2_, path1, new CopyOption[0]);
    }

    public static String n_1700_B(String p_244361_0_, CharPredicate p_244361_1_) {
        return p_244361_0_.toLowerCase(Locale.ROOT).chars().mapToObj(p_lambda$func_244361_a$7_1_ -> p_244361_1_.test((char)p_lambda$func_244361_a$7_1_) ? Character.toString((char)p_lambda$func_244361_a$7_1_) : "_").collect(Collectors.joining());
    }

    static {
        t_148_a = j_3341_s.n_1700_B("cape");
    }

    public static sealed class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        public static final /* enum */ J_1907_R R_4764_Y = new J_1907_R(){

            @Override
            protected String[] J_1907_R(URL url) {
                return new String[]{"rundll32", "url.dll,FileProtocolHandler", url.toString()};
            }
        };
        public static final /* enum */ J_1907_R G_564_y = new J_1907_R(){

            @Override
            protected String[] J_1907_R(URL url) {
                return new String[]{"open", url.toString()};
            }
        };
        public static final /* enum */ J_1907_R P_1922_E = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] u_1723_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])u_1723_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        public void n_1700_B(URL url) {
            try {
                Process process = AccessController.doPrivileged(() -> Runtime.getRuntime().exec(this.J_1907_R(url)));
                for (String s : IOUtils.readLines((InputStream)process.getErrorStream())) {
                    v_4262_N.error(s);
                }
                process.getInputStream().close();
                process.getErrorStream().close();
                process.getOutputStream().close();
            }
            catch (IOException | PrivilegedActionException ioexception) {
                v_4262_N.error("Couldn't open url '{}'", (Object)url, (Object)ioexception);
                w_1484_f = ioexception;
            }
        }

        public void n_1700_B(URI uri) {
            try {
                this.n_1700_B(uri.toURL());
            }
            catch (MalformedURLException malformedurlexception) {
                v_4262_N.error("Couldn't open uri '{}'", (Object)uri, (Object)malformedurlexception);
            }
        }

        public void n_1700_B(File fileIn) {
            try {
                this.n_1700_B(fileIn.toURI().toURL());
            }
            catch (MalformedURLException malformedurlexception) {
                v_4262_N.error("Couldn't open file '{}'", (Object)fileIn, (Object)malformedurlexception);
            }
        }

        protected String[] J_1907_R(URL url) {
            String s = url.toString();
            if ("file".equals(url.getProtocol())) {
                s = s.replace("file:", "file://");
            }
            return new String[]{"xdg-open", s};
        }

        public void n_1700_B(String uri) {
            try {
                this.n_1700_B(new URI(uri).toURL());
            }
            catch (IllegalArgumentException | MalformedURLException | URISyntaxException malformedurlexception) {
                v_4262_N.error("Couldn't open uri '{}'", (Object)uri, (Object)malformedurlexception);
            }
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            u_1723_Y = lightning.product.j_3341_s$J_1907_R.n_1700_B();
        }
    }

    static final class n_1700_B
    extends Enum<n_1700_B>
    implements Hash.Strategy<Object> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] J_1907_R;

        public static n_1700_B[] values() {
            return (n_1700_B[])J_1907_R.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        public int hashCode(Object p_hashCode_1_) {
            return System.identityHashCode(p_hashCode_1_);
        }

        public boolean equals(Object p_equals_1_, Object p_equals_2_) {
            return p_equals_1_ == p_equals_2_;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B};
        }

        static {
            J_1907_R = lightning.product.j_3341_s$n_1700_B.n_1700_B();
        }
    }
}


