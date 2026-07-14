/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Locale;
import java.util.Map;
import lightning.product.D_1436_R;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.I_4817_s;
import lightning.product.b_1722_e;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.l_3747_P;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;
import lightning.product.u_530_F;

public class Y_3383_J
implements n_4915_r.n_1700_B {
    private final Map<Integer, b_1722_e> n_1700_B = Maps.newHashMap();
    private final Map<Integer, Float> J_1907_R = Maps.newHashMap();
    private final Map<Integer, Long> R_4764_Y = Maps.newHashMap();

    public void n_1700_B(int eid, b_1722_e pathIn, float distance) {
        this.n_1700_B.put(eid, pathIn);
        this.R_4764_Y.put(eid, j_3341_s.J_1907_R());
        this.J_1907_R.put(eid, Float.valueOf(distance));
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        if (!this.n_1700_B.isEmpty()) {
            long i = j_3341_s.J_1907_R();
            for (Integer integer : this.n_1700_B.keySet()) {
                b_1722_e path = this.n_1700_B.get(integer);
                float f = this.J_1907_R.get(integer).floatValue();
                Y_3383_J.n_1700_B(path, f, true, true, camX, camY, camZ);
            }
            for (Integer integer1 : this.R_4764_Y.keySet().toArray(new Integer[0])) {
                if (i - this.R_4764_Y.get(integer1) <= 5000L) continue;
                this.n_1700_B.remove(integer1);
                this.R_4764_Y.remove(integer1);
            }
        }
    }

    public static void n_1700_B(b_1722_e p_229032_0_, float p_229032_1_, boolean p_229032_2_, boolean p_229032_3_, double p_229032_4_, double p_229032_6_, double p_229032_8_) {
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(0.0f, 1.0f, 0.0f, 0.75f);
        c_4037_x.e_4240_b();
        c_4037_x.G_564_y(6.0f);
        Y_3383_J.J_1907_R(p_229032_0_, p_229032_1_, p_229032_2_, p_229032_3_, p_229032_4_, p_229032_6_, p_229032_8_);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    private static void J_1907_R(b_1722_e p_229034_0_, float p_229034_1_, boolean p_229034_2_, boolean p_229034_3_, double p_229034_4_, double p_229034_6_, double p_229034_8_) {
        Y_3383_J.n_1700_B(p_229034_0_, p_229034_4_, p_229034_6_, p_229034_8_);
        c_1514_x blockpos = p_229034_0_.P_4830_p();
        if (Y_3383_J.n_1700_B(blockpos, p_229034_4_, p_229034_6_, p_229034_8_) <= 80.0f) {
            n_4915_r.n_1700_B(new I_4817_s((float)blockpos.getX() + 0.25f, (float)blockpos.getY() + 0.25f, (double)blockpos.getZ() + 0.25, (float)blockpos.getX() + 0.75f, (float)blockpos.getY() + 0.75f, (float)blockpos.getZ() + 0.75f).offset(-p_229034_4_, -p_229034_6_, -p_229034_8_), 0.0f, 1.0f, 0.0f, 0.5f);
            for (int i = 0; i < p_229034_0_.P_1922_E(); ++i) {
                D_1436_R pathpoint = p_229034_0_.n_1700_B(i);
                if (!(Y_3383_J.n_1700_B(pathpoint.R_4764_Y(), p_229034_4_, p_229034_6_, p_229034_8_) <= 80.0f)) continue;
                float f = i == p_229034_0_.u_1723_Y() ? 1.0f : 0.0f;
                float f1 = i == p_229034_0_.u_1723_Y() ? 0.0f : 1.0f;
                n_4915_r.n_1700_B(new I_4817_s((float)pathpoint.n_1700_B + 0.5f - p_229034_1_, (float)pathpoint.J_1907_R + 0.01f * (float)i, (float)pathpoint.R_4764_Y + 0.5f - p_229034_1_, (float)pathpoint.n_1700_B + 0.5f + p_229034_1_, (float)pathpoint.J_1907_R + 0.25f + 0.01f * (float)i, (float)pathpoint.R_4764_Y + 0.5f + p_229034_1_).offset(-p_229034_4_, -p_229034_6_, -p_229034_8_), f, 0.0f, f1, 0.5f);
            }
        }
        if (p_229034_2_) {
            for (D_1436_R pathpoint2 : p_229034_0_.M_588_G()) {
                if (!(Y_3383_J.n_1700_B(pathpoint2.R_4764_Y(), p_229034_4_, p_229034_6_, p_229034_8_) <= 80.0f)) continue;
                n_4915_r.n_1700_B(new I_4817_s((float)pathpoint2.n_1700_B + 0.5f - p_229034_1_ / 2.0f, (float)pathpoint2.J_1907_R + 0.01f, (float)pathpoint2.R_4764_Y + 0.5f - p_229034_1_ / 2.0f, (float)pathpoint2.n_1700_B + 0.5f + p_229034_1_ / 2.0f, (double)pathpoint2.J_1907_R + 0.1, (float)pathpoint2.R_4764_Y + 0.5f + p_229034_1_ / 2.0f).offset(-p_229034_4_, -p_229034_6_, -p_229034_8_), 1.0f, 0.8f, 0.8f, 0.5f);
            }
            for (D_1436_R pathpoint3 : p_229034_0_.u_2550_I()) {
                if (!(Y_3383_J.n_1700_B(pathpoint3.R_4764_Y(), p_229034_4_, p_229034_6_, p_229034_8_) <= 80.0f)) continue;
                n_4915_r.n_1700_B(new I_4817_s((float)pathpoint3.n_1700_B + 0.5f - p_229034_1_ / 2.0f, (float)pathpoint3.J_1907_R + 0.01f, (float)pathpoint3.R_4764_Y + 0.5f - p_229034_1_ / 2.0f, (float)pathpoint3.n_1700_B + 0.5f + p_229034_1_ / 2.0f, (double)pathpoint3.J_1907_R + 0.1, (float)pathpoint3.R_4764_Y + 0.5f + p_229034_1_ / 2.0f).offset(-p_229034_4_, -p_229034_6_, -p_229034_8_), 0.8f, 1.0f, 1.0f, 0.5f);
            }
        }
        if (p_229034_3_) {
            for (int j = 0; j < p_229034_0_.P_1922_E(); ++j) {
                D_1436_R pathpoint1 = p_229034_0_.n_1700_B(j);
                if (!(Y_3383_J.n_1700_B(pathpoint1.R_4764_Y(), p_229034_4_, p_229034_6_, p_229034_8_) <= 80.0f)) continue;
                n_4915_r.n_1700_B(String.format("%s", new Object[]{pathpoint1.M_588_G}), (double)pathpoint1.n_1700_B + 0.5, (double)pathpoint1.J_1907_R + 0.75, (double)pathpoint1.R_4764_Y + 0.5, -1, 0.02f, true, 0.0f, true);
                n_4915_r.n_1700_B(String.format(Locale.ROOT, "%.2f", Float.valueOf(pathpoint1.u_2550_I)), (double)pathpoint1.n_1700_B + 0.5, (double)pathpoint1.J_1907_R + 0.25, (double)pathpoint1.R_4764_Y + 0.5, -1, 0.02f, true, 0.0f, true);
            }
        }
    }

    public static void n_1700_B(b_1722_e p_229031_0_, double p_229031_1_, double p_229031_3_, double p_229031_5_) {
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(3, E_688_b.Y_601_j);
        for (int i = 0; i < p_229031_0_.P_1922_E(); ++i) {
            D_1436_R pathpoint = p_229031_0_.n_1700_B(i);
            if (Y_3383_J.n_1700_B(pathpoint.R_4764_Y(), p_229031_1_, p_229031_3_, p_229031_5_) > 80.0f) continue;
            float f = (float)i / (float)p_229031_0_.P_1922_E() * 0.33f;
            int j = i == 0 ? 0 : u_530_F.u_1723_Y(f, 0.9f, 0.9f);
            int k = j >> 16 & 0xFF;
            int l = j >> 8 & 0xFF;
            int i1 = j & 0xFF;
            bufferbuilder.pos((double)pathpoint.n_1700_B - p_229031_1_ + 0.5, (double)pathpoint.J_1907_R - p_229031_3_ + 0.5, (double)pathpoint.R_4764_Y - p_229031_5_ + 0.5).color(k, l, i1, 255).endVertex();
        }
        tessellator.J_1907_R();
    }

    private static float n_1700_B(c_1514_x p_229033_0_, double p_229033_1_, double p_229033_3_, double p_229033_5_) {
        return (float)(Math.abs((double)p_229033_0_.getX() - p_229033_1_) + Math.abs((double)p_229033_0_.getY() - p_229033_3_) + Math.abs((double)p_229033_0_.getZ() - p_229033_5_));
    }
}

