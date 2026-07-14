package dev.babbaj.pathfinder;

import dev.babbaj.pathfinder.PathSegment;
import org.tukaani.xz.XZInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class NetherPathfinder {
    public static int CACHE_MISS_GENERATE = 0;
    public static int CACHE_MISS_AIR = 1;
    public static int CACHE_MISS_SOLID = 2;
    private static final boolean IS_LOADED;

    public static native long newContext(long var0);

    public static native void freeContext(long var0);

    public static native void insertChunkData(long var0, int var2, int var3, boolean[] var4);

    public static native long getOrCreateChunk(long var0, int var2, int var3);

    public static native long getChunkPointer(long var0, int var2, int var3);

    public static native boolean hasChunkFromJava(long var0, int var2, int var3);

    public static native void cullFarChunks(long var0, int var2, int var3, int var4);

    public static native PathSegment pathFind(long var0, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8, boolean var9, int var10, boolean var11);

    private static native void raytrace0(long var0, int var2, int var3, double[] var4, double[] var5, boolean[] var6, double[] var7);

    public static void raytrace(long l2, int n2, int n3, double[] dArray, double[] dArray2, boolean[] blArray, double[] dArray3) {
        if (dArray.length < n3 * 3 || dArray2.length < n3 * 3 || blArray.length < n3 || dArray3 != null && dArray3.length < n3 * 3) {
            throw new IllegalArgumentException("Bad array lengths idiot");
        }
        NetherPathfinder.raytrace0(l2, n2, n3, dArray, dArray2, blArray, dArray3);
    }

    private static native int isVisibleMulti0(long var0, int var2, int var3, double[] var4, double[] var5, boolean var6);

    public static int isVisibleMulti(long l2, int n2, int n3, double[] dArray, double[] dArray2, boolean bl) {
        if (dArray.length < n3 * 3 || dArray2.length < n3 * 3) {
            throw new IllegalArgumentException("Bad array lengths idiot");
        }
        return NetherPathfinder.isVisibleMulti0(l2, n2, n3, dArray, dArray2, bl);
    }

    public static native boolean isVisible(long var0, int var2, double var3, double var5, double var7, double var9, double var11, double var13);

    public static native boolean cancel(long var0);

    static native long getX2Index();

    public static boolean isThisSystemSupported() {
        return IS_LOADED;
    }

    private static String getNativeLibName() {
        if (Integer.parseInt(System.getProperty("sun.arch.data.model")) != 64) {
            throw new UnsupportedOperationException("Unsupported architecture (64-bit required)");
        }
        String string = System.getProperty("os.name").toLowerCase();
        String string2 = System.getProperty("os.arch").toLowerCase();
        if (string2.contains("arm") || string2.contains("aarch64")) {
            string2 = "aarch64";
        } else if (string2.equals("x86_64") || string2.equals("amd64")) {
            string2 = "x86_64";
        } else {
            throw new UnsupportedOperationException("Unsupported architecture: ".concat(String.valueOf(string2)));
        }
        if (string.contains("linux")) {
            return "libnether_pathfinder-" + string2 + ".so";
        }
        if (string.contains("windows")) {
            return "nether_pathfinder-" + string2 + ".dll";
        }
        if (string.contains("mac")) {
            return "libnether_pathfinder-" + string2 + ".dylib";
        }
        throw new UnsupportedOperationException("Unsupported operating system: ".concat(String.valueOf(string)));
    }

    private static byte[] getNativeLib(String nativeLibName) {
        try (InputStream input = NetherPathfinder.class.getClassLoader().getResourceAsStream("natives.zip.xz")) {
            if (input == null) {
                throw new NullPointerException("Missing natives.zip.xz");
            }
            try (XZInputStream xzInput = new XZInputStream(input); ZipInputStream zipInput = new ZipInputStream(xzInput)) {
                ZipEntry entry;
                while ((entry = zipInput.getNextEntry()) != null) {
                    if (!entry.getName().equals(nativeLibName)) {
                        continue;
                    }
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int read;
                    while ((read = zipInput.read(buffer)) != -1) {
                        output.write(buffer, 0, read);
                    }
                    return output.toByteArray();
                }
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Failed to unpack pathfinder library: " + nativeLibName, exception);
        }
        throw new NullPointerException("Failed to find pathfinder library: " + nativeLibName);
    }

    private static void tryLoadLibrary() throws IOException {
        String nativeLibName = getNativeLibName();
        byte[] nativeLib = getNativeLib(nativeLibName);
        String[] nameParts = nativeLibName.split("\\.");
        Path tempFile = Files.createTempFile(nameParts[0], "." + nameParts[1]);
        System.out.println("[nether-pathfinder] Created temp file at " + tempFile.toAbsolutePath());
        try {
            Files.write(tempFile, nativeLib);
            System.load(tempFile.toAbsolutePath().toString());
        } finally {
            try {
                Files.deleteIfExists(tempFile);
            } catch (IOException exception) {
                System.err.println("[nether-pathfinder] Failed to delete temp file");
                tempFile.toFile().deleteOnExit();
            }
        }
    }

    static {
        boolean bl = false;
        try {
            NetherPathfinder.tryLoadLibrary();
            System.out.println("[nether-pathfinder] Loaded shared library");
            bl = true;
        }
        catch (Throwable throwable) {
            System.err.println("[nether-pathfinder] Failed to load shared library");
            throwable.printStackTrace();
        }
        IS_LOADED = bl;
    }
}

