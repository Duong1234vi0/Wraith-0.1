/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import javax.annotation.Nullable;
import lightning.product.ChunkStatus;
import lightning.product.LoggerChunkProgressListener;
import lightning.product.Y_1387_d;
import lightning.product.ChunkProgressListener;

public class StoringChunkProgressListener
implements ChunkProgressListener {
    private final LoggerChunkProgressListener n_1700_B;
    private final Long2ObjectOpenHashMap<ChunkStatus> J_1907_R;
    private Y_1387_d R_4764_Y = new Y_1387_d(0, 0);
    private final int G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private boolean v_4262_N;

    public StoringChunkProgressListener(int radius) {
        this.n_1700_B = new LoggerChunkProgressListener(radius);
        this.G_564_y = radius * 2 + 1;
        this.P_1922_E = radius + ChunkStatus.J_1907_R();
        this.u_1723_Y = this.P_1922_E * 2 + 1;
        this.J_1907_R = new Long2ObjectOpenHashMap();
    }

    @Override
    public void n_1700_B(Y_1387_d center) {
        if (this.v_4262_N) {
            this.n_1700_B.n_1700_B(center);
            this.R_4764_Y = center;
        }
    }

    @Override
    public void n_1700_B(Y_1387_d chunkPosition, @Nullable ChunkStatus newStatus) {
        if (this.v_4262_N) {
            this.n_1700_B.n_1700_B(chunkPosition, newStatus);
            if (newStatus == null) {
                this.J_1907_R.remove(chunkPosition.n_1700_B());
            } else {
                this.J_1907_R.put(chunkPosition.n_1700_B(), (Object)newStatus);
            }
        }
    }

    public void J_1907_R() {
        this.v_4262_N = true;
        this.J_1907_R.clear();
    }

    @Override
    public void n_1700_B() {
        this.v_4262_N = false;
        this.n_1700_B.n_1700_B();
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    public int G_564_y() {
        return this.u_1723_Y;
    }

    public int P_1922_E() {
        return this.n_1700_B.J_1907_R();
    }

    @Nullable
    public ChunkStatus n_1700_B(int x, int z) {
        return (ChunkStatus)this.J_1907_R.get(Y_1387_d.n_1700_B(x + this.R_4764_Y.J_1907_R - this.P_1922_E, z + this.R_4764_Y.R_4764_Y - this.P_1922_E));
    }
}


