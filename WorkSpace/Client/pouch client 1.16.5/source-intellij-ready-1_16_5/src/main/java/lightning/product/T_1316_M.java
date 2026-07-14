/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.ChunkStatus;
import lightning.product.BlockGetter;
import lightning.product.BiomeManager;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.BlockAndTintGetter;
import lightning.product.Z_3903_F;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.ChunkAccess;
import lightning.product.k_594_Q;
import lightning.product.o_3283_D;
import lightning.product.u_530_F;
import lightning.product.ColorResolver;
import lightning.product.z_2963_s;

public interface T_1316_M
extends BiomeManager.n_1700_B,
BlockAndTintGetter,
o_3283_D {
    @Nullable
    public ChunkAccess n_1700_B(int var1, int var2, ChunkStatus var3, boolean var4);

    @Deprecated
    public boolean R_4764_Y(int var1, int var2);

    public int n_1700_B(z_2963_s.n_1700_B var1, int var2, int var3);

    public int d_2427_y();

    public BiomeManager z_1737_N();

    default public k_594_Q P_1922_E(c_1514_x pos) {
        return this.z_1737_N().n_1700_B(pos);
    }

    default public Stream<K_4074_S> R_4764_Y(I_4817_s aabb) {
        int j1;
        int i = u_530_F.R_4764_Y(aabb.minX);
        int j = u_530_F.R_4764_Y(aabb.maxX);
        int k = u_530_F.R_4764_Y(aabb.minY);
        int l = u_530_F.R_4764_Y(aabb.maxY);
        int i1 = u_530_F.R_4764_Y(aabb.minZ);
        return this.n_1700_B(i, k, i1, j, l, j1 = u_530_F.R_4764_Y(aabb.maxZ)) ? this.n_1700_B(aabb) : Stream.empty();
    }

    @Override
    default public int getBlockColor(c_1514_x blockPosIn, ColorResolver colorResolverIn) {
        return colorResolverIn.getColor(this.P_1922_E(blockPosIn), blockPosIn.getX(), blockPosIn.getZ());
    }

    @Override
    default public k_594_Q G_564_y(int x, int y, int z) {
        ChunkAccess ichunk = this.n_1700_B(x >> 2, z >> 2, ChunkStatus.G_564_y, false);
        return ichunk != null && ichunk.getBiomes() != null ? ichunk.getBiomes().G_564_y(x, y, z) : this.R_4764_Y(x, y, z);
    }

    public k_594_Q R_4764_Y(int var1, int var2, int var3);

    public boolean v_4276_D();

    @Deprecated
    public int d_2461_k();

    public Z_3903_F G_624_v();

    default public c_1514_x n_1700_B(z_2963_s.n_1700_B heightmapType, c_1514_x pos) {
        return new c_1514_x(pos.getX(), this.n_1700_B(heightmapType, pos.getX(), pos.getZ()), pos.getZ());
    }

    default public boolean u_1723_Y(c_1514_x pos) {
        return this.getBlockState(pos).v_4262_N();
    }

    default public boolean v_4262_N(c_1514_x pos) {
        if (pos.getY() >= this.d_2461_k()) {
            return this.canSeeSky(pos);
        }
        c_1514_x blockpos = new c_1514_x(pos.getX(), this.d_2461_k(), pos.getZ());
        if (!this.canSeeSky(blockpos)) {
            return false;
        }
        c_1514_x blockpos1 = blockpos.down();
        while (blockpos1.getY() > pos.getY()) {
            K_4074_S blockstate = this.getBlockState(blockpos1);
            if (blockstate.J_1907_R(this, blockpos1) > 0 && !blockstate.R_4764_Y().n_1700_B()) {
                return false;
            }
            blockpos1 = blockpos1.down();
        }
        return true;
    }

    @Deprecated
    default public float w_1484_f(c_1514_x pos) {
        return this.G_624_v().n_1700_B(this.u_2550_I(pos));
    }

    default public int n_1700_B(c_1514_x pos, b_257_Y direction) {
        return this.getBlockState(pos).R_4764_Y((BlockGetter)this, pos, direction);
    }

    default public ChunkAccess t_148_a(c_1514_x pos) {
        return this.P_1922_E(pos.getX() >> 4, pos.getZ() >> 4);
    }

    default public ChunkAccess P_1922_E(int chunkX, int chunkZ) {
        return this.n_1700_B(chunkX, chunkZ, ChunkStatus.P_4830_p, true);
    }

    default public ChunkAccess n_1700_B(int chunkX, int chunkZ, ChunkStatus requiredStatus) {
        return this.n_1700_B(chunkX, chunkZ, requiredStatus, true);
    }

    @Override
    @Nullable
    default public BlockGetter G_564_y(int chunkX, int chunkZ) {
        return this.n_1700_B(chunkX, chunkZ, ChunkStatus.n_1700_B, false);
    }

    default public boolean s_956_w(c_1514_x pos) {
        return this.getFluidState(pos).n_1700_B(FluidTags.J_1907_R);
    }

    default public boolean G_564_y(I_4817_s bb) {
        int i = u_530_F.R_4764_Y(bb.minX);
        int j = u_530_F.P_1922_E(bb.maxX);
        int k = u_530_F.R_4764_Y(bb.minY);
        int l = u_530_F.P_1922_E(bb.maxY);
        int i1 = u_530_F.R_4764_Y(bb.minZ);
        int j1 = u_530_F.P_1922_E(bb.maxZ);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int k1 = i; k1 < j; ++k1) {
            for (int l1 = k; l1 < l; ++l1) {
                for (int i2 = i1; i2 < j1; ++i2) {
                    K_4074_S blockstate = this.getBlockState(blockpos$mutable.n_1700_B(k1, l1, i2));
                    if (blockstate.P_4830_p().R_4764_Y()) continue;
                    return true;
                }
            }
        }
        return false;
    }

    default public int u_2550_I(c_1514_x pos) {
        return this.J_1907_R(pos, this.d_2427_y());
    }

    default public int J_1907_R(c_1514_x pos, int amount) {
        return pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000 ? this.n_1700_B(pos, amount) : 15;
    }

    @Deprecated
    default public boolean M_588_G(c_1514_x pos) {
        return this.R_4764_Y(pos.getX() >> 4, pos.getZ() >> 4);
    }

    @Deprecated
    default public boolean n_1700_B(c_1514_x from, c_1514_x to) {
        return this.n_1700_B(from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ());
    }

    @Deprecated
    default public boolean n_1700_B(int fromX, int fromY, int fromZ, int toX, int toY, int toZ) {
        if (toY >= 0 && fromY < 256) {
            fromZ >>= 4;
            toX >>= 4;
            toZ >>= 4;
            for (int i = fromX >>= 4; i <= toX; ++i) {
                for (int j = fromZ; j <= toZ; ++j) {
                    if (this.R_4764_Y(i, j)) continue;
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}


