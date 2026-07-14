/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_4024_W;
import lightning.product.F_1723_g;
import lightning.product.F_2904_S;
import lightning.product.GuiEventListener;
import lightning.product.MutableComponent;
import lightning.product.I_1084_e;
import lightning.product.PlayerSocialManager;
import lightning.product.M_4239_y;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.ImageButton;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.ContainerObjectSelectionList;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;

public class e_3068_v
extends ContainerObjectSelectionList.n_1700_B<e_3068_v> {
    private final MinecraftClient u_1723_Y;
    private final List<GuiEventListener> v_4262_N;
    private final UUID w_1484_f;
    private final String t_148_a;
    private final Supplier<g_2336_b> s_956_w;
    private boolean u_2550_I;
    @Nullable
    private Button M_588_G;
    @Nullable
    private Button P_4830_p;
    private final List<FormattedCharSequence> h_1847_R;
    private final List<FormattedCharSequence> Q_4569_t;
    private float M_182_A;
    private static final x_282_a t_1786_h = new F_2904_S("gui.socialInteractions.status_hidden").n_1700_B(D_4024_W.Y_259_p);
    private static final x_282_a multiplayerClientSuggestionProvider = new F_2904_S("gui.socialInteractions.status_blocked").n_1700_B(D_4024_W.Y_259_p);
    private static final x_282_a w_1457_N = new F_2904_S("gui.socialInteractions.status_offline").n_1700_B(D_4024_W.Y_259_p);
    private static final x_282_a Y_601_j = new F_2904_S("gui.socialInteractions.status_hidden_offline").n_1700_B(D_4024_W.Y_259_p);
    private static final x_282_a Y_259_p = new F_2904_S("gui.socialInteractions.status_blocked_offline").n_1700_B(D_4024_W.Y_259_p);
    public static final int n_1700_B = M_4239_y.n_1700_B.n_1700_B(190, 0, 0, 0);
    public static final int J_1907_R = M_4239_y.n_1700_B.n_1700_B(255, 74, 74, 74);
    public static final int R_4764_Y = M_4239_y.n_1700_B.n_1700_B(255, 48, 48, 48);
    public static final int G_564_y = M_4239_y.n_1700_B.n_1700_B(255, 255, 255, 255);
    public static final int P_1922_E = M_4239_y.n_1700_B.n_1700_B(140, 255, 255, 255);

    public e_3068_v(MinecraftClient p_i242129_1_, F_1723_g p_i242129_2_, UUID p_i242129_3_, String p_i242129_4_, Supplier<g_2336_b> p_i242129_5_) {
        this.u_1723_Y = p_i242129_1_;
        this.w_1484_f = p_i242129_3_;
        this.t_148_a = p_i242129_4_;
        this.s_956_w = p_i242129_5_;
        this.h_1847_R = p_i242129_1_.t_148_a.J_1907_R(new F_2904_S("gui.socialInteractions.tooltip.hide", p_i242129_4_), 150);
        this.Q_4569_t = p_i242129_1_.t_148_a.J_1907_R(new F_2904_S("gui.socialInteractions.tooltip.show", p_i242129_4_), 150);
        PlayerSocialManager filtermanager = p_i242129_1_.dtoRealmsServerAddress();
        if (!p_i242129_1_.Y_259_p.y_4642_Y().getId().equals(p_i242129_3_) && !filtermanager.P_1922_E(p_i242129_3_)) {
            this.M_588_G = new ImageButton(0, 0, 20, 20, 0, 38, 20, F_1723_g.n_1700_B, 256, 256, p_244751_4_ -> {
                filtermanager.n_1700_B(p_i242129_3_);
                this.n_1700_B(true, new F_2904_S("gui.socialInteractions.hidden_in_chat", p_i242129_4_));
            }, (p_244637_3_, p_244637_4_, p_244637_5_, p_244637_6_) -> {
                this.M_182_A += p_i242129_1_.f_4016_n();
                if (this.M_182_A >= 10.0f) {
                    p_i242129_2_.n_1700_B(() -> e_3068_v.n_1700_B(p_i242129_2_, p_244637_4_, this.h_1847_R, p_244637_5_, p_244637_6_));
                }
            }, new F_2904_S("gui.socialInteractions.hide")){

                @Override
                protected MutableComponent getNarrationMessage() {
                    return e_3068_v.this.n_1700_B(super.getNarrationMessage());
                }
            };
            this.P_4830_p = new ImageButton(0, 0, 20, 20, 20, 38, 20, F_1723_g.n_1700_B, 256, 256, p_244749_4_ -> {
                filtermanager.J_1907_R(p_i242129_3_);
                this.n_1700_B(false, new F_2904_S("gui.socialInteractions.shown_in_chat", p_i242129_4_));
            }, (p_244631_3_, p_244631_4_, p_244631_5_, p_244631_6_) -> {
                this.M_182_A += p_i242129_1_.f_4016_n();
                if (this.M_182_A >= 10.0f) {
                    p_i242129_2_.n_1700_B(() -> e_3068_v.n_1700_B(p_i242129_2_, p_244631_4_, this.Q_4569_t, p_244631_5_, p_244631_6_));
                }
            }, new F_2904_S("gui.socialInteractions.show")){

                @Override
                protected MutableComponent getNarrationMessage() {
                    return e_3068_v.this.n_1700_B(super.getNarrationMessage());
                }
            };
            this.P_4830_p.visible = filtermanager.G_564_y(p_i242129_3_);
            this.M_588_G.visible = !this.P_4830_p.visible;
            this.v_4262_N = ImmutableList.of((Object)this.M_588_G, (Object)this.P_4830_p);
        } else {
            this.v_4262_N = ImmutableList.of();
        }
    }

    @Override
    public void render(g_221_o p_230432_1_, int p_230432_2_, int p_230432_3_, int p_230432_4_, int p_230432_5_, int p_230432_6_, int p_230432_7_, int p_230432_8_, boolean p_230432_9_, float p_230432_10_) {
        int l;
        int i = p_230432_4_ + 4;
        int j = p_230432_3_ + (p_230432_6_ - 24) / 2;
        int k = i + 24 + 4;
        x_282_a itextcomponent = this.R_4764_Y();
        if (itextcomponent == U_2871_b.R_4764_Y) {
            C_2701_A.fill(p_230432_1_, p_230432_4_, p_230432_3_, p_230432_4_ + p_230432_5_, p_230432_3_ + p_230432_6_, J_1907_R);
            l = p_230432_3_ + (p_230432_6_ - 9) / 2;
        } else {
            C_2701_A.fill(p_230432_1_, p_230432_4_, p_230432_3_, p_230432_4_ + p_230432_5_, p_230432_3_ + p_230432_6_, R_4764_Y);
            l = p_230432_3_ + (p_230432_6_ - 18) / 2;
            this.u_1723_Y.t_148_a.J_1907_R(p_230432_1_, itextcomponent, (float)k, (float)(l + 12), P_1922_E);
        }
        this.u_1723_Y.G_624_v().n_1700_B(this.s_956_w.get());
        C_2701_A.blit(p_230432_1_, i, j, 24, 24, 8.0f, 8.0f, 8, 8, 64, 64);
        c_4037_x.Y_601_j();
        C_2701_A.blit(p_230432_1_, i, j, 24, 24, 40.0f, 8.0f, 8, 8, 64, 64);
        c_4037_x.Y_259_p();
        this.u_1723_Y.t_148_a.J_1907_R(p_230432_1_, this.t_148_a, (float)k, (float)l, G_564_y);
        if (this.u_2550_I) {
            C_2701_A.fill(p_230432_1_, i, j, i + 24, j + 24, n_1700_B);
        }
        if (this.M_588_G != null && this.P_4830_p != null) {
            float f = this.M_182_A;
            this.M_588_G.x = p_230432_4_ + (p_230432_5_ - this.M_588_G.getWidth() - 4);
            this.M_588_G.y = p_230432_3_ + (p_230432_6_ - this.M_588_G.getHeightRealms()) / 2;
            this.M_588_G.render(p_230432_1_, p_230432_7_, p_230432_8_, p_230432_10_);
            this.P_4830_p.x = p_230432_4_ + (p_230432_5_ - this.P_4830_p.getWidth() - 4);
            this.P_4830_p.y = p_230432_3_ + (p_230432_6_ - this.P_4830_p.getHeightRealms()) / 2;
            this.P_4830_p.render(p_230432_1_, p_230432_7_, p_230432_8_, p_230432_10_);
            if (f == this.M_182_A) {
                this.M_182_A = 0.0f;
            }
        }
    }

    @Override
    public List<? extends GuiEventListener> getEventListeners() {
        return this.v_4262_N;
    }

    public String n_1700_B() {
        return this.t_148_a;
    }

    public UUID J_1907_R() {
        return this.w_1484_f;
    }

    public void n_1700_B(boolean p_244641_1_) {
        this.u_2550_I = p_244641_1_;
    }

    private void n_1700_B(boolean p_244635_1_, x_282_a p_244635_2_) {
        this.P_4830_p.visible = p_244635_1_;
        this.M_588_G.visible = !p_244635_1_;
        this.u_1723_Y.M_588_G.R_4764_Y().n_1700_B(p_244635_2_);
        I_1084_e.J_1907_R.n_1700_B(p_244635_2_.getString());
    }

    private MutableComponent n_1700_B(MutableComponent p_244750_1_) {
        x_282_a itextcomponent = this.R_4764_Y();
        return itextcomponent == U_2871_b.R_4764_Y ? new U_2871_b(this.t_148_a).n_1700_B(", ").n_1700_B(p_244750_1_) : new U_2871_b(this.t_148_a).n_1700_B(", ").n_1700_B(itextcomponent).n_1700_B(", ").n_1700_B(p_244750_1_);
    }

    private x_282_a R_4764_Y() {
        boolean flag = this.u_1723_Y.dtoRealmsServerAddress().G_564_y(this.w_1484_f);
        boolean flag1 = this.u_1723_Y.dtoRealmsServerAddress().P_1922_E(this.w_1484_f);
        if (flag1 && this.u_2550_I) {
            return Y_259_p;
        }
        if (flag && this.u_2550_I) {
            return Y_601_j;
        }
        if (flag1) {
            return multiplayerClientSuggestionProvider;
        }
        if (flag) {
            return t_1786_h;
        }
        return this.u_2550_I ? w_1457_N : U_2871_b.R_4764_Y;
    }

    private static void n_1700_B(F_1723_g p_244634_0_, g_221_o p_244634_1_, List<FormattedCharSequence> p_244634_2_, int p_244634_3_, int p_244634_4_) {
        p_244634_0_.renderTooltip(p_244634_1_, p_244634_2_, p_244634_3_, p_244634_4_);
        p_244634_0_.n_1700_B((Runnable)null);
    }
}



