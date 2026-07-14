/*
 * Decompiled with CFR 0.152.
 */
package yami.system.media;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.concurrent.atomic.AtomicBoolean;

public final class NativeNowPlayingBridge {
    private static final AtomicBoolean n_1700_B = new AtomicBoolean(false);

    private static void n_1700_B() {
        if (!n_1700_B.compareAndSet(false, true)) {
            return;
        }
        String dllRelPath = "mediaplayerinfo/natives/win/windows_now_playing_bridge.dll";
        Path projectDll = Path.of(dllRelPath, new String[0]);
        try {
            if (Files.exists(projectDll, new LinkOption[0])) {
                System.load(projectDll.toAbsolutePath().toString());
                return;
            }
            String resourcePath = "/" + dllRelPath.replace('\\', '/');
            try (InputStream in = NativeNowPlayingBridge.class.getResourceAsStream(resourcePath);){
                if (in == null) {
                    throw new IOException("Resource not found: " + resourcePath);
                }
                Path tempDir = Files.createTempDirectory("now-playing-", new FileAttribute[0]);
                Path dllFile = tempDir.resolve("windows_now_playing_bridge.dll");
                Files.write(dllFile, in.readAllBytes(), new OpenOption[0]);
                System.load(dllFile.toAbsolutePath().toString());
                dllFile.toFile().deleteOnExit();
                tempDir.toFile().deleteOnExit();
            }
        }
        catch (Throwable e) {
            throw new RuntimeException("Failed to load windows_now_playing_bridge.dll", e);
        }
    }

    public static native String nativePollJson();

    public static native boolean nativeControl(int var0);

    public static native boolean nativeSeekTo(long var0);

    static {
        NativeNowPlayingBridge.n_1700_B();
    }
}

