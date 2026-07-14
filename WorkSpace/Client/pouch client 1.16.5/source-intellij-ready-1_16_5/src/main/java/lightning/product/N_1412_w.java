/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.MutableComponent;
import lightning.product.I_2861_N;
import lightning.product.ObjectSelectionList;
import lightning.product.N_2445_q;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.l_3747_P;
import lightning.product.l_4033_W;
import lightning.product.q_3418_t;
import lightning.product.u_4608_G;
import lightning.product.x_282_a;

public class N_1412_w
extends ObjectSelectionList<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/resource_packs.png");
    private static final x_282_a J_1907_R = new F_2904_S("pack.incompatible");
    private static final x_282_a R_4764_Y = new F_2904_S("pack.incompatible.confirm.title");
    private final x_282_a G_564_y;

    public N_1412_w(MinecraftClient p_i241200_1_, int p_i241200_2_, int p_i241200_3_, x_282_a p_i241200_4_) {
        super(p_i241200_1_, p_i241200_2_, p_i241200_3_, 32, p_i241200_3_ - 55 + 4, 36);
        this.G_564_y = p_i241200_4_;
        this.centerListVertically = false;
        this.setRenderHeader(true, 13);
    }

    @Override
    protected void renderHeader(g_221_o p_230448_1_, int p_230448_2_, int p_230448_3_, l_3747_P p_230448_4_) {
        MutableComponent itextcomponent = new U_2871_b("").n_1700_B(this.G_564_y).n_1700_B(D_4024_W.Y_601_j, D_4024_W.multiplayerClientSuggestionProvider);
        this.minecraft.t_148_a.J_1907_R(p_230448_1_, itextcomponent, (float)(p_230448_2_ + this.width / 2 - this.minecraft.t_148_a.n_1700_B((FormattedText)itextcomponent) / 2), (float)Math.min(this.y0 + 3, p_230448_3_), 0xFFFFFF);
    }

    @Override
    public int getRowWidth() {
        return this.width;
    }

    @Override
    protected int getScrollbarPosition() {
        return this.x1 - 6;
    }

    public static class n_1700_B
    extends ObjectSelectionList.n_1700_B<n_1700_B> {
        private N_1412_w R_4764_Y;
        protected final MinecraftClient n_1700_B;
        protected final k_2603_m J_1907_R;
        private final I_2861_N.G_564_y G_564_y;
        private final FormattedCharSequence P_1922_E;
        private final N_2445_q u_1723_Y;
        private final FormattedCharSequence v_4262_N;
        private final N_2445_q w_1484_f;

        public n_1700_B(MinecraftClient p_i241201_1_, N_1412_w p_i241201_2_, k_2603_m p_i241201_3_, I_2861_N.G_564_y p_i241201_4_) {
            this.n_1700_B = p_i241201_1_;
            this.J_1907_R = p_i241201_3_;
            this.G_564_y = p_i241201_4_;
            this.R_4764_Y = p_i241201_2_;
            this.P_1922_E = lightning.product.N_1412_w$n_1700_B.n_1700_B(p_i241201_1_, p_i241201_4_.P_1922_E());
            this.u_1723_Y = lightning.product.N_1412_w$n_1700_B.J_1907_R(p_i241201_1_, p_i241201_4_.multiplayerClientSuggestionProvider());
            this.v_4262_N = lightning.product.N_1412_w$n_1700_B.n_1700_B(p_i241201_1_, J_1907_R);
            this.w_1484_f = lightning.product.N_1412_w$n_1700_B.J_1907_R(p_i241201_1_, p_i241201_4_.G_564_y().J_1907_R());
        }

        private static FormattedCharSequence n_1700_B(MinecraftClient p_244424_0_, x_282_a p_244424_1_) {
            int i = p_244424_0_.t_148_a.n_1700_B((FormattedText)p_244424_1_);
            if (i > 157) {
                FormattedText itextproperties = FormattedText.n_1700_B(p_244424_0_.t_148_a.n_1700_B(p_244424_1_, 157 - p_244424_0_.t_148_a.J_1907_R("...")), FormattedText.R_4764_Y("..."));
                return l_4033_W.R_4764_Y().n_1700_B(itextproperties);
            }
            return p_244424_1_.u_1723_Y();
        }

        private static N_2445_q J_1907_R(MinecraftClient p_244425_0_, x_282_a p_244425_1_) {
            return N_2445_q.n_1700_B(p_244425_0_.t_148_a, (FormattedText)p_244425_1_, 157, 2);
        }

        @Override
        public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
            u_4608_G packcompatibility = this.G_564_y.G_564_y();
            if (!packcompatibility.n_1700_B()) {
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                C_2701_A.fill(p_230432_1_, p_230432_4_ - 1, p_230432_3_ - 1, p_230432_4_ + p_230432_5_ - 9, p_230432_3_ + p_230432_6_ + 1, -8978432);
            }
            this.n_1700_B.G_624_v().n_1700_B(this.G_564_y.R_4764_Y());
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 0.0f, 0.0f, 32, 32, 32, 32);
            FormattedCharSequence ireorderingprocessor = this.P_1922_E;
            N_2445_q ibidirenderer = this.u_1723_Y;
            if (this.n_1700_B() && (this.n_1700_B.P_4830_p.c_4037_x || p_230432_9_)) {
                this.n_1700_B.G_624_v().n_1700_B(n_1700_B);
                C_2701_A.fill(p_230432_1_, p_230432_4_, p_230432_3_, p_230432_4_ + 32, p_230432_3_ + 32, -1601138544);
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                int i = p_230432_7_ - p_230432_4_;
                int j = p_230432_8_ - p_230432_3_;
                if (!this.G_564_y.G_564_y().n_1700_B()) {
                    ireorderingprocessor = this.v_4262_N;
                    ibidirenderer = this.w_1484_f;
                }
                if (this.G_564_y.w_1457_N()) {
                    if (i < 32) {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 0.0f, 32.0f, 32, 32, 256, 256);
                    } else {
                        C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 0.0f, 0.0f, 32, 32, 256, 256);
                    }
                } else {
                    if (this.G_564_y.Y_601_j()) {
                        if (i < 16) {
                            C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 32.0f, 32.0f, 32, 32, 256, 256);
                        } else {
                            C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 32.0f, 0.0f, 32, 32, 256, 256);
                        }
                    }
                    if (this.G_564_y.u_2550_I()) {
                        if (i < 32 && i > 16 && j < 16) {
                            C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 96.0f, 32.0f, 32, 32, 256, 256);
                        } else {
                            C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 96.0f, 0.0f, 32, 32, 256, 256);
                        }
                    }
                    if (this.G_564_y.P_4830_p()) {
                        if (i < 32 && i > 16 && j > 16) {
                            C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 64.0f, 32.0f, 32, 32, 256, 256);
                        } else {
                            C_2701_A.blit(p_230432_1_, p_230432_4_, p_230432_3_, 64.0f, 0.0f, 32, 32, 256, 256);
                        }
                    }
                }
            }
            this.n_1700_B.t_148_a.n_1700_B(p_230432_1_, ireorderingprocessor, (float)(p_230432_4_ + 32 + 2), (float)(p_230432_3_ + 1), 0xFFFFFF);
            ibidirenderer.J_1907_R(p_230432_1_, p_230432_4_ + 32 + 2, p_230432_3_ + 12, 10, 0x808080);
        }

        private boolean n_1700_B() {
            return !this.G_564_y.w_1484_f() || !this.G_564_y.t_148_a();
        }

        @Override
        public boolean mouseClicked(double mouseX, double mouseY, int button) {
            double d0 = mouseX - (double)this.R_4764_Y.getRowLeft();
            double d1 = mouseY - (double)this.R_4764_Y.getRowTop(this.R_4764_Y.getEventListeners().indexOf(this));
            if (this.n_1700_B() && d0 <= 32.0) {
                if (this.G_564_y.w_1457_N()) {
                    u_4608_G packcompatibility = this.G_564_y.G_564_y();
                    if (packcompatibility.n_1700_B()) {
                        this.G_564_y.M_182_A();
                    } else {
                        x_282_a itextcomponent = packcompatibility.R_4764_Y();
                        this.n_1700_B.n_1700_B(new q_3418_t(p_238921_1_ -> {
                            this.n_1700_B.n_1700_B(this.J_1907_R);
                            if (p_238921_1_) {
                                this.G_564_y.M_182_A();
                            }
                        }, R_4764_Y, itextcomponent));
                    }
                    return true;
                }
                if (d0 < 16.0 && this.G_564_y.Y_601_j()) {
                    this.G_564_y.t_1786_h();
                    return true;
                }
                if (d0 > 16.0 && d1 < 16.0 && this.G_564_y.u_2550_I()) {
                    this.G_564_y.M_588_G();
                    return true;
                }
                if (d0 > 16.0 && d1 > 16.0 && this.G_564_y.P_4830_p()) {
                    this.G_564_y.h_1847_R();
                    return true;
                }
            }
            return false;
        }
    }
}



