/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_2701_A;
import lightning.product.FormattedText;
import lightning.product.SpectatorMenuItem;
import lightning.product.SpectatorPage;
import lightning.product.X_2140_T;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_372_g;
import lightning.product.j_3341_s;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class SpectatorGui
extends C_2701_A
implements g_372_g {
    private static final g_2336_b J_1907_R = new g_2336_b("textures/gui/widgets.png");
    public static final g_2336_b n_1700_B = new g_2336_b("textures/gui/spectator_widgets.png");
    private final MinecraftClient R_4764_Y;
    private long G_564_y;
    private X_2140_T P_1922_E;

    public SpectatorGui(MinecraftClient mcIn) {
        this.R_4764_Y = mcIn;
    }

    public void n_1700_B(int p_175260_1_) {
        this.G_564_y = j_3341_s.J_1907_R();
        if (this.P_1922_E != null) {
            this.P_1922_E.J_1907_R(p_175260_1_);
        } else {
            this.P_1922_E = new X_2140_T(this);
        }
    }

    private float R_4764_Y() {
        long i = this.G_564_y - j_3341_s.J_1907_R() + 5000L;
        return u_530_F.n_1700_B((float)i / 2000.0f, 0.0f, 1.0f);
    }

    public void n_1700_B(g_221_o p_238528_1_, float p_238528_2_) {
        if (this.P_1922_E != null) {
            float f = this.R_4764_Y();
            if (f <= 0.0f) {
                this.P_1922_E.G_564_y();
            } else {
                int i = this.R_4764_Y.RealmsServerPing().Q_4569_t() / 2;
                int j = this.getBlitOffset();
                this.setBlitOffset(-90);
                int k = u_530_F.G_564_y((float)this.R_4764_Y.RealmsServerPing().M_182_A() - 22.0f * f);
                SpectatorPage spectatordetails = this.P_1922_E.u_1723_Y();
                this.n_1700_B(p_238528_1_, f, i, k, spectatordetails);
                this.setBlitOffset(j);
            }
        }
    }

    protected void n_1700_B(g_221_o p_238529_1_, float p_238529_2_, int p_238529_3_, int p_238529_4_, SpectatorPage p_238529_5_) {
        c_4037_x.n_3318_d();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, p_238529_2_);
        this.R_4764_Y.G_624_v().n_1700_B(J_1907_R);
        this.blit(p_238529_1_, p_238529_3_ - 91, p_238529_4_, 0, 0, 182, 22);
        if (p_238529_5_.n_1700_B() >= 0) {
            this.blit(p_238529_1_, p_238529_3_ - 91 - 1 + p_238529_5_.n_1700_B() * 20, p_238529_4_ - 1, 0, 22, 24, 22);
        }
        for (int i = 0; i < 9; ++i) {
            this.n_1700_B(p_238529_1_, i, this.R_4764_Y.RealmsServerPing().Q_4569_t() / 2 - 90 + i * 20 + 2, p_238529_4_ + 3, p_238529_2_, p_238529_5_.n_1700_B(i));
        }
        c_4037_x.d_2427_y();
        c_4037_x.Y_259_p();
    }

    private void n_1700_B(g_221_o p_238530_1_, int p_238530_2_, int p_238530_3_, float p_238530_4_, float p_238530_5_, SpectatorMenuItem p_238530_6_) {
        this.R_4764_Y.G_624_v().n_1700_B(n_1700_B);
        if (p_238530_6_ != X_2140_T.n_1700_B) {
            int i = (int)(p_238530_5_ * 255.0f);
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y((float)p_238530_3_, p_238530_4_, 0.0f);
            float f = p_238530_6_.G_564_y() ? 1.0f : 0.25f;
            c_4037_x.G_564_y(f, f, f, p_238530_5_);
            p_238530_6_.n_1700_B(p_238530_1_, f, i);
            c_4037_x.d_2461_k();
            if (i > 3 && p_238530_6_.G_564_y()) {
                x_282_a itextcomponent = this.R_4764_Y.P_4830_p.RealmsServerPing[p_238530_2_].u_2550_I();
                this.R_4764_Y.t_148_a.n_1700_B(p_238530_1_, itextcomponent, (float)(p_238530_3_ + 19 - 2 - this.R_4764_Y.t_148_a.n_1700_B((FormattedText)itextcomponent)), p_238530_4_ + 6.0f + 3.0f, 0xFFFFFF + (i << 24));
            }
        }
    }

    public void n_1700_B(g_221_o p_238527_1_) {
        int i = (int)(this.R_4764_Y() * 255.0f);
        if (i > 3 && this.P_1922_E != null) {
            x_282_a itextcomponent;
            SpectatorMenuItem ispectatormenuobject = this.P_1922_E.J_1907_R();
            x_282_a x_282_a2 = itextcomponent = ispectatormenuobject == X_2140_T.n_1700_B ? this.P_1922_E.R_4764_Y().J_1907_R() : ispectatormenuobject.R_4764_Y();
            if (itextcomponent != null) {
                int j = (this.R_4764_Y.RealmsServerPing().Q_4569_t() - this.R_4764_Y.t_148_a.n_1700_B((FormattedText)itextcomponent)) / 2;
                int k = this.R_4764_Y.RealmsServerPing().M_182_A() - 35;
                c_4037_x.v_4276_D();
                c_4037_x.Y_601_j();
                c_4037_x.s_2632_s();
                this.R_4764_Y.t_148_a.n_1700_B(p_238527_1_, itextcomponent, (float)j, (float)k, 0xFFFFFF + (i << 24));
                c_4037_x.Y_259_p();
                c_4037_x.d_2461_k();
            }
        }
    }

    @Override
    public void n_1700_B(X_2140_T menu) {
        this.P_1922_E = null;
        this.G_564_y = 0L;
    }

    public boolean n_1700_B() {
        return this.P_1922_E != null;
    }

    public void n_1700_B(double amount) {
        int i = this.P_1922_E.P_1922_E() + (int)amount;
        while (!(i < 0 || i > 8 || this.P_1922_E.n_1700_B(i) != X_2140_T.n_1700_B && this.P_1922_E.n_1700_B(i).G_564_y())) {
            i = (int)((double)i + amount);
        }
        if (i >= 0 && i <= 8) {
            this.P_1922_E.J_1907_R(i);
            this.G_564_y = j_3341_s.J_1907_R();
        }
    }

    public void J_1907_R() {
        this.G_564_y = j_3341_s.J_1907_R();
        if (this.n_1700_B()) {
            int i = this.P_1922_E.P_1922_E();
            if (i != -1) {
                this.P_1922_E.J_1907_R(i);
            }
        } else {
            this.P_1922_E = new X_2140_T(this);
        }
    }
}



