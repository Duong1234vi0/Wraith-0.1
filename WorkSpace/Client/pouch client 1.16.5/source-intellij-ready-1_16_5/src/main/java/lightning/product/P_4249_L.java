/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4115_X;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.N_1972_P;
import lightning.product.T_3334_o;
import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_164_R;
import lightning.product.l_3747_P;
import lightning.product.u_796_y;
import net.optifine.reflect.ReflectorForge;

public class P_4249_L {
    public int n_1700_B;
    public int J_1907_R;
    public int R_4764_Y;
    public int G_564_y;
    public final boolean P_1922_E;
    public int u_1723_Y;
    public int v_4262_N;
    public int w_1484_f;
    public final float[] t_148_a;
    public int s_956_w;
    private boolean u_2550_I = false;

    public P_4249_L(int width, int height, boolean useDepth, boolean isOnMac) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        this.P_1922_E = useDepth;
        this.u_1723_Y = -1;
        this.v_4262_N = -1;
        this.w_1484_f = -1;
        this.t_148_a = new float[4];
        this.t_148_a[0] = 1.0f;
        this.t_148_a[1] = 1.0f;
        this.t_148_a[2] = 1.0f;
        this.t_148_a[3] = 0.0f;
        this.n_1700_B(width, height, isOnMac);
    }

    public void n_1700_B(int p_216491_1_, int p_216491_2_, boolean p_216491_3_) {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> this.G_564_y(p_216491_1_, p_216491_2_, p_216491_3_));
        } else {
            this.G_564_y(p_216491_1_, p_216491_2_, p_216491_3_);
        }
    }

    private void G_564_y(int p_227586_1_, int p_227586_2_, boolean p_227586_3_) {
        if (!g_164_R.v_4262_N()) {
            this.R_4764_Y = p_227586_1_;
            this.G_564_y = p_227586_2_;
        } else {
            c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
            X_933_l.P_4830_p();
            if (this.u_1723_Y >= 0) {
                this.P_1922_E();
            }
            this.J_1907_R(p_227586_1_, p_227586_2_, p_227586_3_);
            X_933_l.w_1484_f(T_3334_o.n_1700_B, 0);
        }
    }

    public void P_1922_E() {
        if (g_164_R.v_4262_N()) {
            c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
            this.w_1484_f();
            this.t_148_a();
            if (this.w_1484_f > -1) {
                N_1972_P.n_1700_B(this.w_1484_f);
                this.w_1484_f = -1;
            }
            if (this.v_4262_N > -1) {
                N_1972_P.n_1700_B(this.v_4262_N);
                this.v_4262_N = -1;
            }
            if (this.u_1723_Y > -1) {
                X_933_l.w_1484_f(T_3334_o.n_1700_B, 0);
                X_933_l.u_2550_I(this.u_1723_Y);
                this.u_1723_Y = -1;
            }
        }
    }

    public void n_1700_B(P_4249_L p_237506_1_) {
        if (g_164_R.v_4262_N()) {
            c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
            if (X_933_l.X_933_l()) {
                X_933_l.w_1484_f(36008, p_237506_1_.u_1723_Y);
                X_933_l.w_1484_f(36009, this.u_1723_Y);
                X_933_l.n_1700_B(0, 0, p_237506_1_.n_1700_B, p_237506_1_.J_1907_R, 0, 0, this.n_1700_B, this.J_1907_R, 256, 9728);
            } else {
                X_933_l.w_1484_f(T_3334_o.n_1700_B, this.u_1723_Y);
                int i = X_933_l.multiplayerClientSuggestionProvider();
                if (i != 0) {
                    int j = X_933_l.Y_601_j();
                    X_933_l.w_1457_N(i);
                    X_933_l.w_1484_f(T_3334_o.n_1700_B, p_237506_1_.u_1723_Y);
                    X_933_l.n_1700_B(3553, 0, 0, 0, 0, 0, Math.min(this.n_1700_B, p_237506_1_.n_1700_B), Math.min(this.J_1907_R, p_237506_1_.J_1907_R));
                    X_933_l.w_1457_N(j);
                }
            }
            X_933_l.w_1484_f(T_3334_o.n_1700_B, 0);
        }
    }

    public void J_1907_R(int p_216492_1_, int p_216492_2_, boolean p_216492_3_) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        this.R_4764_Y = p_216492_1_;
        this.G_564_y = p_216492_2_;
        this.n_1700_B = p_216492_1_;
        this.J_1907_R = p_216492_2_;
        if (!g_164_R.v_4262_N()) {
            this.R_4764_Y(p_216492_3_);
        } else {
            this.u_1723_Y = X_933_l.w_1457_N();
            this.v_4262_N = N_1972_P.n_1700_B();
            if (this.P_1922_E) {
                this.w_1484_f = N_1972_P.n_1700_B();
                X_933_l.w_1457_N(this.w_1484_f);
                X_933_l.J_1907_R(3553, 10241, 9728);
                X_933_l.J_1907_R(3553, 10240, 9728);
                X_933_l.J_1907_R(3553, 10242, 10496);
                X_933_l.J_1907_R(3553, 10243, 10496);
                X_933_l.J_1907_R(3553, 34892, 0);
                if (this.u_2550_I) {
                    X_933_l.n_1700_B(3553, 0, 36013, this.n_1700_B, this.J_1907_R, 0, 34041, 36269, null);
                } else {
                    X_933_l.n_1700_B(3553, 0, 6402, this.n_1700_B, this.J_1907_R, 0, 6402, 5126, null);
                }
            }
            this.n_1700_B(9728);
            X_933_l.w_1457_N(this.v_4262_N);
            X_933_l.n_1700_B(3553, 0, 32856, this.n_1700_B, this.J_1907_R, 0, 6408, 5121, null);
            X_933_l.w_1484_f(T_3334_o.n_1700_B, this.u_1723_Y);
            X_933_l.n_1700_B(T_3334_o.n_1700_B, T_3334_o.R_4764_Y, 3553, this.v_4262_N, 0);
            if (this.P_1922_E) {
                if (this.u_2550_I) {
                    if (ReflectorForge.getForgeUseCombinedDepthStencilAttachment()) {
                        X_933_l.n_1700_B(T_3334_o.n_1700_B, 33306, 3553, this.w_1484_f, 0);
                    } else {
                        X_933_l.n_1700_B(T_3334_o.n_1700_B, 36096, 3553, this.w_1484_f, 0);
                        X_933_l.n_1700_B(T_3334_o.n_1700_B, 36128, 3553, this.w_1484_f, 0);
                    }
                } else {
                    X_933_l.n_1700_B(T_3334_o.n_1700_B, T_3334_o.G_564_y, 3553, this.w_1484_f, 0);
                }
            }
            this.u_1723_Y();
            this.R_4764_Y(p_216492_3_);
            this.w_1484_f();
        }
    }

    public void n_1700_B(int framebufferFilterIn) {
        if (g_164_R.v_4262_N()) {
            c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
            this.s_956_w = framebufferFilterIn;
            X_933_l.w_1457_N(this.v_4262_N);
            X_933_l.J_1907_R(3553, 10241, framebufferFilterIn);
            X_933_l.J_1907_R(3553, 10240, framebufferFilterIn);
            X_933_l.J_1907_R(3553, 10242, 10496);
            X_933_l.J_1907_R(3553, 10243, 10496);
            X_933_l.w_1457_N(0);
        }
    }

    public void u_1723_Y() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        int i = X_933_l.M_588_G(T_3334_o.n_1700_B);
        if (i != T_3334_o.P_1922_E) {
            if (i == T_3334_o.u_1723_Y) {
                throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT");
            }
            if (i == T_3334_o.v_4262_N) {
                throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT");
            }
            if (i == T_3334_o.w_1484_f) {
                throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER");
            }
            if (i == T_3334_o.t_148_a) {
                throw new RuntimeException("GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER");
            }
            throw new RuntimeException("glCheckFramebufferStatus returned unknown status:" + i);
        }
    }

    public void v_4262_N() {
        if (g_164_R.v_4262_N()) {
            c_4037_x.n_1700_B(c_4037_x::J_1907_R);
            X_933_l.w_1457_N(this.v_4262_N);
        }
    }

    public void w_1484_f() {
        if (g_164_R.v_4262_N()) {
            c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
            X_933_l.w_1457_N(0);
        }
    }

    public void J_1907_R(boolean setViewportIn) {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> this.n_1700_B(setViewportIn));
        } else {
            this.n_1700_B(setViewportIn);
        }
    }

    private void n_1700_B(boolean setViewportIn) {
        if (g_164_R.v_4262_N()) {
            c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
            X_933_l.w_1484_f(T_3334_o.n_1700_B, this.u_1723_Y);
            if (setViewportIn) {
                X_933_l.G_564_y(0, 0, this.R_4764_Y, this.G_564_y);
            }
        }
    }

    public void t_148_a() {
        if (g_164_R.v_4262_N()) {
            if (!c_4037_x.J_1907_R()) {
                c_4037_x.n_1700_B(() -> X_933_l.w_1484_f(T_3334_o.n_1700_B, 0));
            } else {
                X_933_l.w_1484_f(T_3334_o.n_1700_B, 0);
            }
        }
    }

    public void n_1700_B(float red, float green, float blue, float alpha) {
        this.t_148_a[0] = red;
        this.t_148_a[1] = green;
        this.t_148_a[2] = blue;
        this.t_148_a[3] = alpha;
    }

    public void n_1700_B(int width, int height) {
        this.R_4764_Y(width, height, true);
    }

    public void R_4764_Y(int width, int height, boolean p_178038_3_) {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        if (!c_4037_x.u_1723_Y()) {
            c_4037_x.n_1700_B(() -> this.P_1922_E(width, height, p_178038_3_));
        } else {
            this.P_1922_E(width, height, p_178038_3_);
        }
    }

    private void P_1922_E(int width, int height, boolean p_227588_3_) {
        if (g_164_R.v_4262_N()) {
            c_4037_x.n_1700_B(c_4037_x::J_1907_R);
            X_933_l.n_1700_B(true, true, true, false);
            X_933_l.M_588_G();
            X_933_l.n_1700_B(false);
            X_933_l.C_2741_M(5889);
            X_933_l.z_4693_k();
            X_933_l.n_1700_B(0.0, (double)width, (double)height, 0.0, 1000.0, 3000.0);
            X_933_l.C_2741_M(5888);
            X_933_l.z_4693_k();
            X_933_l.R_4764_Y(0.0f, 0.0f, -2000.0f);
            X_933_l.G_564_y(0, 0, width, height);
            X_933_l.v_4276_D();
            X_933_l.v_4262_N();
            X_933_l.G_564_y();
            if (p_227588_3_) {
                X_933_l.h_1847_R();
                X_933_l.w_1484_f();
            }
            X_933_l.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.v_4262_N();
            A_4115_X.n_1700_B(new u_796_y(u_796_y.n_1700_B.n_1700_B));
            float f = width;
            float f1 = height;
            float f2 = (float)this.R_4764_Y / (float)this.n_1700_B;
            float f3 = (float)this.G_564_y / (float)this.J_1907_R;
            l_3747_P tessellator = c_4037_x.D_4792_h();
            D_3318_r bufferbuilder = tessellator.R_4764_Y();
            bufferbuilder.n_1700_B(7, E_688_b.k_2293_S);
            bufferbuilder.pos(0.0, f1, 0.0).tex(0.0f, 0.0f).color(255, 255, 255, 255).endVertex();
            bufferbuilder.pos(f, f1, 0.0).tex(f2, 0.0f).color(255, 255, 255, 255).endVertex();
            bufferbuilder.pos(f, 0.0, 0.0).tex(f2, f3).color(255, 255, 255, 255).endVertex();
            bufferbuilder.pos(0.0, 0.0, 0.0).tex(0.0f, f3).color(255, 255, 255, 255).endVertex();
            tessellator.J_1907_R();
            A_4115_X.n_1700_B(new u_796_y(u_796_y.n_1700_B.J_1907_R));
            this.w_1484_f();
            X_933_l.n_1700_B(true);
            X_933_l.n_1700_B(true, true, true, true);
        }
    }

    public void s_956_w() {
        this.R_4764_Y(MinecraftClient.n_1700_B);
    }

    public void R_4764_Y(boolean onMac) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        this.J_1907_R(true);
        X_933_l.J_1907_R(this.t_148_a[0], this.t_148_a[1], this.t_148_a[2], this.t_148_a[3]);
        int i = 16384;
        if (this.P_1922_E) {
            X_933_l.n_1700_B(1.0);
            i |= 0x100;
        }
        X_933_l.n_1700_B(i, onMac);
        this.t_148_a();
    }

    public int u_2550_I() {
        return this.v_4262_N;
    }

    public int M_588_G() {
        return this.w_1484_f;
    }

    public void P_4830_p() {
        if (!this.u_2550_I) {
            this.u_2550_I = true;
            this.n_1700_B(this.R_4764_Y, this.G_564_y, MinecraftClient.n_1700_B);
        }
    }

    public boolean h_1847_R() {
        return this.u_2550_I;
    }
}



