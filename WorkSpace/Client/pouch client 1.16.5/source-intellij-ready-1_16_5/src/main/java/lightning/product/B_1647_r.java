/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.Y_1387_d;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.o_2576_A;
import lightning.product.u_530_F;
import lightning.product.z_4547_I;
import lightning.product.z_883_p;
import net.optifine.Config;
import net.optifine.render.VboRegion;

public class B_1647_r {
    protected final z_883_p n_1700_B;
    protected final b_4507_u J_1907_R;
    protected int R_4764_Y;
    protected int G_564_y;
    protected int P_1922_E;
    public z_4547_I.n_1700_B[] u_1723_Y;
    private Map<Y_1387_d, VboRegion[]> v_4262_N = new HashMap<Y_1387_d, VboRegion[]>();

    public B_1647_r(z_4547_I renderDispatcherIn, b_4507_u worldIn, int countChunksIn, z_883_p renderGlobalIn) {
        this.n_1700_B = renderGlobalIn;
        this.J_1907_R = worldIn;
        this.n_1700_B(countChunksIn);
        this.n_1700_B(renderDispatcherIn);
    }

    protected void n_1700_B(z_4547_I renderChunkFactory) {
        int i = this.G_564_y * this.R_4764_Y * this.P_1922_E;
        this.u_1723_Y = new z_4547_I.n_1700_B[i];
        for (int j = 0; j < this.G_564_y; ++j) {
            for (int k = 0; k < this.R_4764_Y; ++k) {
                for (int l = 0; l < this.P_1922_E; ++l) {
                    int i1 = this.n_1700_B(j, k, l);
                    this.u_1723_Y[i1] = renderChunkFactory.new z_4547_I.n_1700_B();
                    this.u_1723_Y[i1].n_1700_B(j * 16, k * 16, l * 16);
                    if (!Config.isVbo() || !Config.isRenderRegions()) continue;
                    this.n_1700_B(this.u_1723_Y[i1]);
                }
            }
        }
        for (int j1 = 0; j1 < this.u_1723_Y.length; ++j1) {
            z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender1 = this.u_1723_Y[j1];
            for (int k1 = 0; k1 < b_257_Y.v_4262_N.length; ++k1) {
                b_257_Y direction = b_257_Y.v_4262_N[k1];
                c_1514_x blockpos = chunkrenderdispatcher$chunkrender1.n_1700_B(direction);
                z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = this.n_1700_B(blockpos);
                chunkrenderdispatcher$chunkrender1.n_1700_B(direction, chunkrenderdispatcher$chunkrender);
            }
        }
    }

    public void n_1700_B() {
        for (z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender : this.u_1723_Y) {
            chunkrenderdispatcher$chunkrender.G_564_y();
        }
        this.J_1907_R();
    }

    private int n_1700_B(int x, int y, int z) {
        return (z * this.R_4764_Y + y) * this.G_564_y + x;
    }

    protected void n_1700_B(int renderDistanceChunks) {
        int i;
        this.G_564_y = i = renderDistanceChunks * 2 + 1;
        this.R_4764_Y = 16;
        this.P_1922_E = i;
    }

    public void n_1700_B(double viewEntityX, double viewEntityZ) {
        int i = u_530_F.R_4764_Y(viewEntityX);
        int j = u_530_F.R_4764_Y(viewEntityZ);
        for (int k = 0; k < this.G_564_y; ++k) {
            int l = this.G_564_y * 16;
            int i1 = i - 8 - l / 2;
            int j1 = i1 + Math.floorMod(k * 16 - i1, l);
            for (int k1 = 0; k1 < this.P_1922_E; ++k1) {
                int l1 = this.P_1922_E * 16;
                int i2 = j - 8 - l1 / 2;
                int j2 = i2 + Math.floorMod(k1 * 16 - i2, l1);
                for (int k2 = 0; k2 < this.R_4764_Y; ++k2) {
                    int l2 = k2 * 16;
                    z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = this.u_1723_Y[this.n_1700_B(k, k2, k1)];
                    chunkrenderdispatcher$chunkrender.n_1700_B(j1, l2, j2);
                }
            }
        }
    }

    public void n_1700_B(int sectionX, int sectionY, int sectionZ, boolean rerenderOnMainThread) {
        int i = Math.floorMod(sectionX, this.G_564_y);
        int j = Math.floorMod(sectionY, this.R_4764_Y);
        int k = Math.floorMod(sectionZ, this.P_1922_E);
        z_4547_I.n_1700_B chunkrenderdispatcher$chunkrender = this.u_1723_Y[this.n_1700_B(i, j, k)];
        chunkrenderdispatcher$chunkrender.n_1700_B(rerenderOnMainThread);
    }

    @Nullable
    public z_4547_I.n_1700_B n_1700_B(c_1514_x pos) {
        int i = pos.getX() >> 4;
        int j = pos.getY() >> 4;
        int k = pos.getZ() >> 4;
        if (j >= 0 && j < this.R_4764_Y) {
            i = u_530_F.J_1907_R(i, this.G_564_y);
            k = u_530_F.J_1907_R(k, this.P_1922_E);
            return this.u_1723_Y[this.n_1700_B(i, j, k)];
        }
        return null;
    }

    private void n_1700_B(z_4547_I.n_1700_B p_updateVboRegion_1_) {
        c_1514_x blockpos = p_updateVboRegion_1_.P_1922_E();
        int i = blockpos.getX() >> 8 << 8;
        int j = blockpos.getZ() >> 8 << 8;
        Y_1387_d chunkpos = new Y_1387_d(i, j);
        o_2576_A[] arendertype = o_2576_A.N_2525_X;
        VboRegion[] avboregion = this.v_4262_N.get(chunkpos);
        if (avboregion == null) {
            avboregion = new VboRegion[arendertype.length];
            for (int k = 0; k < arendertype.length; ++k) {
                avboregion[k] = new VboRegion(arendertype[k]);
            }
            this.v_4262_N.put(chunkpos, avboregion);
        }
        for (int l = 0; l < arendertype.length; ++l) {
            o_2576_A rendertype = arendertype[l];
            VboRegion vboregion = avboregion[l];
            if (vboregion == null) continue;
            p_updateVboRegion_1_.n_1700_B(rendertype).n_1700_B(vboregion);
        }
    }

    public void J_1907_R() {
        for (Y_1387_d chunkpos : this.v_4262_N.keySet()) {
            VboRegion[] avboregion = this.v_4262_N.get(chunkpos);
            for (int i = 0; i < avboregion.length; ++i) {
                VboRegion vboregion = avboregion[i];
                if (vboregion != null) {
                    vboregion.deleteGlBuffers();
                }
                avboregion[i] = null;
            }
        }
        this.v_4262_N.clear();
    }
}

