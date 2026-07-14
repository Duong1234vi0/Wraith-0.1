/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_2701_A;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.RealmsScreen;
import lightning.product.j_2266_I;
import lightning.product.j_3341_s;
import lightning.product.k_596_g;
import lightning.product.p_178_J;
import lightning.product.u_744_e;

public class i_2993_w
extends RealmsScreen {
    private static final g_2336_b n_1700_B = new g_2336_b("realms", "textures/gui/realms/invite_icon.png");
    private static final g_2336_b J_1907_R = new g_2336_b("realms", "textures/gui/realms/trial_icon.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("realms", "textures/gui/realms/news_notification_mainscreen.png");
    private static final j_2266_I G_564_y = new j_2266_I();
    private volatile int P_1922_E;
    private static boolean u_1723_Y;
    private static boolean v_4262_N;
    private static boolean w_1484_f;
    private static boolean t_148_a;

    @Override
    public void init() {
        this.R_4764_Y();
        this.minecraft.Q_4569_t.n_1700_B(true);
    }

    @Override
    public void tick() {
        if (!(this.n_1700_B() && this.J_1907_R() && w_1484_f || G_564_y.n_1700_B())) {
            G_564_y.M_588_G();
        } else if (w_1484_f && this.n_1700_B()) {
            G_564_y.R_4764_Y();
            if (G_564_y.n_1700_B(j_2266_I.G_564_y.J_1907_R)) {
                this.P_1922_E = G_564_y.v_4262_N();
            }
            if (G_564_y.n_1700_B(j_2266_I.G_564_y.R_4764_Y)) {
                v_4262_N = G_564_y.w_1484_f();
            }
            if (G_564_y.n_1700_B(j_2266_I.G_564_y.P_1922_E)) {
                t_148_a = G_564_y.s_956_w();
            }
            G_564_y.G_564_y();
        }
    }

    private boolean n_1700_B() {
        return this.minecraft.P_4830_p.g_164_R;
    }

    private boolean J_1907_R() {
        return this.minecraft.Y_1740_V instanceof k_596_g;
    }

    private void R_4764_Y() {
        if (!u_1723_Y) {
            u_1723_Y = true;
            new Thread(this, "Realms Notification Availability checker #1"){

                @Override
                public void run() {
                    p_178_J realmsclient = p_178_J.n_1700_B();
                    try {
                        p_178_J.n_1700_B realmsclient$compatibleversionresponse = realmsclient.t_148_a();
                        if (realmsclient$compatibleversionresponse != p_178_J.n_1700_B.n_1700_B) {
                            return;
                        }
                    }
                    catch (u_744_e realmsserviceexception) {
                        if (realmsserviceexception.n_1700_B != 401) {
                            u_1723_Y = false;
                        }
                        return;
                    }
                    w_1484_f = true;
                }
            }.start();
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (w_1484_f) {
            this.n_1700_B(matrixStack, mouseX, mouseY);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void n_1700_B(g_221_o p_237857_1_, int p_237857_2_, int p_237857_3_) {
        int i = this.P_1922_E;
        int j = 24;
        int k = this.height / 4 + 48;
        int l = this.width / 2 + 80;
        int i1 = k + 48 + 2;
        int j1 = 0;
        if (t_148_a) {
            this.minecraft.G_624_v().n_1700_B(R_4764_Y);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            c_4037_x.v_4276_D();
            c_4037_x.J_1907_R(0.4f, 0.4f, 0.4f);
            C_2701_A.blit(p_237857_1_, (int)((double)(l + 2 - j1) * 2.5), (int)((double)i1 * 2.5), 0.0f, 0.0f, 40, 40, 40, 40);
            c_4037_x.d_2461_k();
            j1 += 14;
        }
        if (i != 0) {
            this.minecraft.G_624_v().n_1700_B(n_1700_B);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            C_2701_A.blit(p_237857_1_, l - j1, i1 - 6, 0.0f, 0.0f, 15, 25, 31, 25);
            j1 += 16;
        }
        if (v_4262_N) {
            this.minecraft.G_624_v().n_1700_B(J_1907_R);
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            int k1 = 0;
            if ((j_3341_s.J_1907_R() / 800L & 1L) == 1L) {
                k1 = 8;
            }
            C_2701_A.blit(p_237857_1_, l + 4 - j1, i1 + 4, 0.0f, k1, 8, 8, 8, 16);
        }
    }

    @Override
    public void onClose() {
        G_564_y.M_588_G();
    }
}


