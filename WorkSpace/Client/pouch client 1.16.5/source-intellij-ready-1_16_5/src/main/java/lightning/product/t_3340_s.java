/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.C_3240_x;
import lightning.product.D_1098_v;
import lightning.product.D_4792_h;
import lightning.product.F_3620_e;
import lightning.product.FormattedText;
import lightning.product.J_2020_G;
import lightning.product.M_1336_P;
import lightning.product.T_1114_L;
import lightning.product.Y_4083_F;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.o_2576_A;
import lightning.product.o_3091_w;
import lightning.product.MaterialColor;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class t_3340_s
implements AutoCloseable {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/map/map_icons.png");
    private static final o_2576_A J_1907_R = o_2576_A.M_182_A(n_1700_B);
    private final C_3240_x R_4764_Y;
    private final Map<String, n_1700_B> G_564_y = Maps.newHashMap();

    public t_3340_s(C_3240_x textureManagerIn) {
        this.R_4764_Y = textureManagerIn;
    }

    public void n_1700_B(F_3620_e mapdataIn) {
        this.J_1907_R(mapdataIn).n_1700_B();
    }

    public void n_1700_B(g_221_o p_228086_1_, o_3091_w p_228086_2_, F_3620_e p_228086_3_, boolean p_228086_4_, int p_228086_5_) {
        this.J_1907_R(p_228086_3_).n_1700_B(p_228086_1_, p_228086_2_, p_228086_4_, p_228086_5_);
    }

    private n_1700_B J_1907_R(F_3620_e mapdataIn) {
        n_1700_B mapitemrenderer$instance = this.G_564_y.get(mapdataIn.P_1922_E());
        if (mapitemrenderer$instance == null) {
            mapitemrenderer$instance = new n_1700_B(this, mapdataIn);
            this.G_564_y.put(mapdataIn.P_1922_E(), mapitemrenderer$instance);
        }
        return mapitemrenderer$instance;
    }

    @Nullable
    public n_1700_B n_1700_B(String p_191205_1_) {
        return this.G_564_y.get(p_191205_1_);
    }

    public void n_1700_B() {
        for (n_1700_B mapitemrenderer$instance : this.G_564_y.values()) {
            mapitemrenderer$instance.close();
        }
        this.G_564_y.clear();
    }

    @Nullable
    public F_3620_e n_1700_B(@Nullable n_1700_B p_191207_1_) {
        return p_191207_1_ != null ? p_191207_1_.n_1700_B : null;
    }

    @Override
    public void close() {
        this.n_1700_B();
    }

    class n_1700_B
    implements AutoCloseable {
        private final F_3620_e n_1700_B;
        private final T_1114_L J_1907_R;
        private final o_2576_A R_4764_Y;

        private n_1700_B(t_3340_s this$0, F_3620_e mapdataIn) {
            this.n_1700_B = mapdataIn;
            this.J_1907_R = new T_1114_L(128, 128, true);
            g_2336_b resourcelocation = this$0.R_4764_Y.n_1700_B("map/" + mapdataIn.P_1922_E(), this.J_1907_R);
            this.R_4764_Y = o_2576_A.M_182_A(resourcelocation);
        }

        private void n_1700_B() {
            for (int i = 0; i < 128; ++i) {
                for (int j = 0; j < 128; ++j) {
                    int k = j + i * 128;
                    int l = this.n_1700_B.v_4262_N[k] & 0xFF;
                    if (l / 4 == 0) {
                        this.J_1907_R.J_1907_R().n_1700_B(j, i, 0);
                        continue;
                    }
                    this.J_1907_R.J_1907_R().n_1700_B(j, i, MaterialColor.n_1700_B[l / 4].n_1700_B(l & 3));
                }
            }
            this.J_1907_R.n_1700_B();
        }

        private void n_1700_B(g_221_o p_228089_1_, o_3091_w p_228089_2_, boolean p_228089_3_, int p_228089_4_) {
            boolean i = false;
            boolean j = false;
            float f = 0.0f;
            D_1098_v matrix4f = p_228089_1_.R_4764_Y().n_1700_B();
            D_4792_h ivertexbuilder = p_228089_2_.getBuffer(this.R_4764_Y);
            ivertexbuilder.n_1700_B(matrix4f, 0.0f, 128.0f, -0.01f).color(255, 255, 255, 255).tex(0.0f, 1.0f).J_1907_R(p_228089_4_).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, 128.0f, 128.0f, -0.01f).color(255, 255, 255, 255).tex(1.0f, 1.0f).J_1907_R(p_228089_4_).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, 128.0f, 0.0f, -0.01f).color(255, 255, 255, 255).tex(1.0f, 0.0f).J_1907_R(p_228089_4_).endVertex();
            ivertexbuilder.n_1700_B(matrix4f, 0.0f, 0.0f, -0.01f).color(255, 255, 255, 255).tex(0.0f, 0.0f).J_1907_R(p_228089_4_).endVertex();
            int k = 0;
            for (J_2020_G mapdecoration : this.n_1700_B.s_956_w.values()) {
                if (p_228089_3_ && !mapdecoration.u_1723_Y()) continue;
                p_228089_1_.n_1700_B();
                p_228089_1_.n_1700_B((double)(0.0f + (float)mapdecoration.R_4764_Y() / 2.0f + 64.0f), (double)(0.0f + (float)mapdecoration.G_564_y() / 2.0f + 64.0f), (double)-0.02f);
                p_228089_1_.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)(mapdecoration.P_1922_E() * 360) / 16.0f));
                p_228089_1_.n_1700_B(4.0f, 4.0f, 3.0f);
                p_228089_1_.n_1700_B(-0.125, 0.125, 0.0);
                byte b0 = mapdecoration.n_1700_B();
                float f1 = (float)(b0 % 16 + 0) / 16.0f;
                float f2 = (float)(b0 / 16 + 0) / 16.0f;
                float f3 = (float)(b0 % 16 + 1) / 16.0f;
                float f4 = (float)(b0 / 16 + 1) / 16.0f;
                D_1098_v matrix4f1 = p_228089_1_.R_4764_Y().n_1700_B();
                float f5 = -0.001f;
                D_4792_h ivertexbuilder1 = p_228089_2_.getBuffer(J_1907_R);
                ivertexbuilder1.n_1700_B(matrix4f1, -1.0f, 1.0f, (float)k * -0.001f).color(255, 255, 255, 255).tex(f1, f2).J_1907_R(p_228089_4_).endVertex();
                ivertexbuilder1.n_1700_B(matrix4f1, 1.0f, 1.0f, (float)k * -0.001f).color(255, 255, 255, 255).tex(f3, f2).J_1907_R(p_228089_4_).endVertex();
                ivertexbuilder1.n_1700_B(matrix4f1, 1.0f, -1.0f, (float)k * -0.001f).color(255, 255, 255, 255).tex(f3, f4).J_1907_R(p_228089_4_).endVertex();
                ivertexbuilder1.n_1700_B(matrix4f1, -1.0f, -1.0f, (float)k * -0.001f).color(255, 255, 255, 255).tex(f1, f4).J_1907_R(p_228089_4_).endVertex();
                p_228089_1_.J_1907_R();
                if (mapdecoration.v_4262_N() != null) {
                    Y_4083_F fontrenderer = MinecraftClient.A_4115_X().t_148_a;
                    x_282_a itextcomponent = mapdecoration.v_4262_N();
                    float f6 = fontrenderer.n_1700_B((FormattedText)itextcomponent);
                    float f7 = u_530_F.n_1700_B(25.0f / f6, 0.0f, 0.6666667f);
                    p_228089_1_.n_1700_B();
                    p_228089_1_.n_1700_B((double)(0.0f + (float)mapdecoration.R_4764_Y() / 2.0f + 64.0f - f6 * f7 / 2.0f), (double)(0.0f + (float)mapdecoration.G_564_y() / 2.0f + 64.0f + 4.0f), (double)-0.025f);
                    p_228089_1_.n_1700_B(f7, f7, 1.0f);
                    p_228089_1_.n_1700_B(0.0, 0.0, (double)-0.1f);
                    fontrenderer.n_1700_B(itextcomponent, 0.0f, 0.0f, -1, false, p_228089_1_.R_4764_Y().n_1700_B(), p_228089_2_, false, Integer.MIN_VALUE, p_228089_4_);
                    p_228089_1_.J_1907_R();
                }
                ++k;
            }
        }

        @Override
        public void close() {
            this.J_1907_R.close();
        }
    }
}



