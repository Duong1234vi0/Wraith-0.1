package dev.babbaj.pathfinder;

import dev.babbaj.pathfinder.NetherPathfinder;
import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class Octree {
    private static final Unsafe UNSAFE;
    private static final long X2_INDEX_PTR;
    public static final int SIZEOF_X2 = 1;
    public static final int SIZEOF_X4 = 8;
    public static final int SIZEOF_X8 = 64;
    public static final int SIZEOF_X16 = 512;
    public static final int SIZEOF_CHUNK = 4096;

    public static int x16Index(int n2) {
        return n2 >> 4;
    }

    public static int x8Index(int n2, int n3, int n4) {
        return (n2 & 8) >> 1 | (n3 & 8) >> 2 | (n4 & 8) >> 3;
    }

    public static int x4Index(int n2, int n3, int n4) {
        return n2 & 4 | (n3 & 4) >> 1 | (n4 & 4) >> 2;
    }

    public static int x2Index(int n2, int n3, int n4) {
        return (n2 & 2) << 1 | n3 & 2 | (n4 & 2) >> 1;
    }

    public static int bitIndex(int n2, int n3, int n4) {
        return (n2 & 1) << 2 | (n3 & 1) << 1 | n4 & 1;
    }

    private static long getX2Ptr(long l2, int n2, int n3, int n4) {
        n2 = 1024 * (n2 / 2) + 128 * (n4 / 2) + 2 * (n3 / 2);
        n2 = UNSAFE.getShort(X2_INDEX_PTR + (long)n2);
        return l2 + (long)n2;
    }

    public static void setBlock(long l2, int n2, int n3, int n4, boolean bl) {
        long l3 = Octree.getX2Ptr(l2, n2, n3, n4);
        int n5 = Octree.bitIndex(n2, n3, n4);
        byte by = UNSAFE.getByte(l3);
        by = bl ? (byte)(by | 1 << n5) : (byte)(by & ~(1 << n5));
        UNSAFE.putByte(l3, by);
    }

    public static void initBlock(long l2, int n2, int n3, int n4, boolean bl) {
        long l3 = Octree.getX2Ptr(l2, n2, n3, n4);
        int n5 = Octree.bitIndex(n2, n3, n4);
        byte by = UNSAFE.getByte(l3);
        n2 = bl ? 1 : 0;
        by = (byte)(by | n2 << n5);
        UNSAFE.putByte(l3, by);
    }

    public static boolean getBlock(long l2, int n2, int n3, int n4) {
        long l3 = Octree.getX2Ptr(l2, n2, n3, n4);
        int n5 = Octree.bitIndex(n2, n3, n4);
        return (UNSAFE.getByte(l3) >> n5 & 1) != 0;
    }

    public static void setIsFromJava(long l2) {
        UNSAFE.putByte(l2 + 4096L, (byte)1);
    }

    public static boolean getIsFromJava(long l2) {
        return UNSAFE.getByte(l2 + 4096L) != 0;
    }

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            UNSAFE = (Unsafe)field.get(null);
        }
        catch (ReflectiveOperationException reflectiveOperationException) {
            throw new RuntimeException(reflectiveOperationException);
        }
        X2_INDEX_PTR = NetherPathfinder.getX2Index();
    }
}

