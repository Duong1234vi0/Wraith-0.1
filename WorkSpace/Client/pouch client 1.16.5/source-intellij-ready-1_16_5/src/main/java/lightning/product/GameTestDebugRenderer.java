/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import lightning.product.X_933_l;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.n_4915_r;
import lightning.product.o_3091_w;

public class GameTestDebugRenderer
implements n_4915_r.n_1700_B {
    private final Map<c_1514_x, n_1700_B> n_1700_B = Maps.newHashMap();

    public void n_1700_B(c_1514_x p_229022_1_, int p_229022_2_, String p_229022_3_, int p_229022_4_) {
        this.n_1700_B.put(p_229022_1_, new n_1700_B(p_229022_2_, p_229022_3_, j_3341_s.J_1907_R() + (long)p_229022_4_));
    }

    @Override
    public void n_1700_B() {
        this.n_1700_B.clear();
    }

    @Override
    public void n_1700_B(g_221_o matrixStackIn, o_3091_w bufferIn, double camX, double camY, double camZ) {
        long i = j_3341_s.J_1907_R();
        this.n_1700_B.entrySet().removeIf(p_229021_2_ -> i > ((n_1700_B)p_229021_2_.getValue()).R_4764_Y);
        this.n_1700_B.forEach(this::n_1700_B);
    }

    private void n_1700_B(c_1514_x p_229023_1_, n_1700_B p_229023_2_) {
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.h_1847_R);
        c_4037_x.G_564_y(0.0f, 1.0f, 0.0f, 0.75f);
        c_4037_x.e_4240_b();
        n_4915_r.n_1700_B(p_229023_1_, 0.02f, p_229023_2_.n_1700_B(), p_229023_2_.J_1907_R(), p_229023_2_.R_4764_Y(), p_229023_2_.G_564_y());
        if (!p_229023_2_.J_1907_R.isEmpty()) {
            double d0 = (double)p_229023_1_.getX() + 0.5;
            double d1 = (double)p_229023_1_.getY() + 1.2;
            double d2 = (double)p_229023_1_.getZ() + 0.5;
            n_4915_r.n_1700_B(p_229023_2_.J_1907_R, d0, d1, d2, -1, 0.01f, true, 0.0f, true);
        }
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        c_4037_x.d_2461_k();
    }

    static class n_1700_B {
        public int n_1700_B;
        public String J_1907_R;
        public long R_4764_Y;

        public n_1700_B(int p_i226032_1_, String p_i226032_2_, long p_i226032_3_) {
            this.n_1700_B = p_i226032_1_;
            this.J_1907_R = p_i226032_2_;
            this.R_4764_Y = p_i226032_3_;
        }

        public float n_1700_B() {
            return (float)(this.n_1700_B >> 16 & 0xFF) / 255.0f;
        }

        public float J_1907_R() {
            return (float)(this.n_1700_B >> 8 & 0xFF) / 255.0f;
        }

        public float R_4764_Y() {
            return (float)(this.n_1700_B & 0xFF) / 255.0f;
        }

        public float G_564_y() {
            return (float)(this.n_1700_B >> 24 & 0xFF) / 255.0f;
        }
    }
}


