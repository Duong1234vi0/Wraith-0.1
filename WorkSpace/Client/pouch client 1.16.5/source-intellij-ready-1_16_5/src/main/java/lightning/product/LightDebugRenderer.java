/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import lightning.product.K_4719_o;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.k_4690_i;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;

public class LightDebugRenderer
implements n_4915_r.n_1700_B {
    private final MinecraftClient n_1700_B;

    public LightDebugRenderer(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        k_4690_i world = this.n_1700_B.Y_601_j;
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_1514_x blockpos = new c_1514_x(camX, camY, camZ);
        LongOpenHashSet longset = new LongOpenHashSet();
        for (c_1514_x blockpos1 : c_1514_x.getAllInBoxMutable(blockpos.add(-10, -10, -10), blockpos.add(10, 10, 10))) {
            int i = world.getLightFor(K_4719_o.n_1700_B, blockpos1);
            float f = (float)(15 - i) / 15.0f * 0.5f + 0.16f;
            int j = u_530_F.u_1723_Y(f, 0.9f, 0.9f);
            long k = SectionPos.P_1922_E(blockpos1.toLong());
            if (longset.add(k)) {
                n_4915_r.n_1700_B(world.q_2307_F().G_564_y().n_1700_B(K_4719_o.n_1700_B, SectionPos.n_1700_B(k)), (double)(SectionPos.J_1907_R(k) * 16 + 8), (double)(SectionPos.R_4764_Y(k) * 16 + 8), (double)(SectionPos.G_564_y(k) * 16 + 8), 0xFF0000, 0.3f);
            }
            if (i == 15) continue;
            n_4915_r.n_1700_B(String.valueOf(i), (double)blockpos1.getX() + 0.5, (double)blockpos1.getY() + 0.25, (double)blockpos1.getZ() + 0.5, j);
        }
        c_4037_x.x_607_J();
        c_4037_x.d_2461_k();
    }
}



