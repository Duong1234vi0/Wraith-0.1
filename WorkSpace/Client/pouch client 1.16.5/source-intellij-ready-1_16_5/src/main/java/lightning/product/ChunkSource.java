/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.io.IOException;
import javax.annotation.Nullable;
import lightning.product.ChunkStatus;
import lightning.product.BlockGetter;
import lightning.product.H_1748_a;
import lightning.product.N_4263_v;
import lightning.product.R_1900_x;
import lightning.product.Y_1387_d;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.LightChunkGetter;

public abstract class ChunkSource
implements AutoCloseable,
LightChunkGetter {
    @Nullable
    public H_1748_a n_1700_B(int chunkX, int chunkZ, boolean load) {
        return (H_1748_a)this.J_1907_R(chunkX, chunkZ, ChunkStatus.P_4830_p, load);
    }

    @Nullable
    public H_1748_a R_4764_Y(int chunkX, int chunkZ) {
        return this.n_1700_B(chunkX, chunkZ, false);
    }

    @Override
    @Nullable
    public BlockGetter G_564_y(int chunkX, int chunkZ) {
        return this.J_1907_R(chunkX, chunkZ, ChunkStatus.n_1700_B, false);
    }

    public boolean P_1922_E(int x, int z) {
        return this.J_1907_R(x, z, ChunkStatus.P_4830_p, false) != null;
    }

    @Nullable
    public abstract ChunkAccess J_1907_R(int var1, int var2, ChunkStatus var3, boolean var4);

    public abstract String J_1907_R();

    @Override
    public void close() throws IOException {
    }

    public abstract R_1900_x G_564_y();

    public void n_1700_B(boolean hostile, boolean peaceful) {
    }

    public void n_1700_B(Y_1387_d pos, boolean add) {
    }

    public boolean n_1700_B(N_4263_v entityIn) {
        return true;
    }

    public boolean n_1700_B(Y_1387_d pos) {
        return true;
    }

    public boolean n_1700_B(c_1514_x pos) {
        return true;
    }
}


