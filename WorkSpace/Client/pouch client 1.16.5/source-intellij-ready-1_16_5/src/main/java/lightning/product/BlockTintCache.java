/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import java.util.Arrays;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.IntSupplier;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;

public class BlockTintCache {
    private final ThreadLocal<n_1700_B> n_1700_B = ThreadLocal.withInitial(() -> new n_1700_B());
    private final Long2ObjectLinkedOpenHashMap<int[]> J_1907_R = new Long2ObjectLinkedOpenHashMap(256, 0.25f);
    private final ReentrantReadWriteLock R_4764_Y = new ReentrantReadWriteLock();

    public int n_1700_B(c_1514_x blockPosIn, IntSupplier colorSupplier) {
        int k1;
        int i = blockPosIn.getX() >> 4;
        int j = blockPosIn.getZ() >> 4;
        n_1700_B colorcache$entry = this.n_1700_B.get();
        if (colorcache$entry.n_1700_B != i || colorcache$entry.J_1907_R != j) {
            colorcache$entry.n_1700_B = i;
            colorcache$entry.J_1907_R = j;
            colorcache$entry.R_4764_Y = this.J_1907_R(i, j);
        }
        int k = blockPosIn.getX() & 0xF;
        int l = blockPosIn.getZ() & 0xF;
        int i1 = l << 4 | k;
        int j1 = colorcache$entry.R_4764_Y[i1];
        if (j1 != -1) {
            return j1;
        }
        colorcache$entry.R_4764_Y[i1] = k1 = colorSupplier.getAsInt();
        return k1;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(int chunkX, int chunkZ) {
        try {
            this.R_4764_Y.writeLock().lock();
            for (int i = -1; i <= 1; ++i) {
                for (int j = -1; j <= 1; ++j) {
                    long k = Y_1387_d.n_1700_B(chunkX + i, chunkZ + j);
                    this.J_1907_R.remove(k);
                }
            }
        }
        finally {
            this.R_4764_Y.writeLock().unlock();
        }
    }

    public void n_1700_B() {
        try {
            this.R_4764_Y.writeLock().lock();
            this.J_1907_R.clear();
        }
        finally {
            this.R_4764_Y.writeLock().unlock();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private int[] J_1907_R(int chunkX, int chunkZ) {
        int[] aint;
        long i = Y_1387_d.n_1700_B(chunkX, chunkZ);
        this.R_4764_Y.readLock().lock();
        try {
            aint = (int[])this.J_1907_R.get(i);
        }
        finally {
            this.R_4764_Y.readLock().unlock();
        }
        if (aint != null) {
            return aint;
        }
        int[] aint1 = new int[256];
        Arrays.fill(aint1, -1);
        try {
            this.R_4764_Y.writeLock().lock();
            if (this.J_1907_R.size() >= 256) {
                this.J_1907_R.removeFirst();
            }
            this.J_1907_R.put(i, (Object)aint1);
        }
        finally {
            this.R_4764_Y.writeLock().unlock();
        }
        return aint1;
    }

    static class n_1700_B {
        public int n_1700_B = Integer.MIN_VALUE;
        public int J_1907_R = Integer.MIN_VALUE;
        public int[] R_4764_Y;

        private n_1700_B() {
        }
    }
}


