/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.C_3538_G;
import lightning.product.F_2904_S;
import lightning.product.F_4247_a;
import lightning.product.FormattedText;
import lightning.product.G_424_k;
import lightning.product.RealmsLongRunningMcoTaskScreen;
import lightning.product.SwitchMinigameTask;
import lightning.product.SwitchSlotTask;
import lightning.product.RealmsWorldOptions;
import lightning.product.S_4022_R;
import lightning.product.S_980_j;
import lightning.product.Button;
import lightning.product.OpenServerTask;
import lightning.product.c_132_F;
import lightning.product.c_4037_x;
import lightning.product.RealmsLongConfirmationScreen;
import lightning.product.f_1043_S;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.RealmsSettingsScreen;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.r_715_M;
import lightning.product.s_1671_u;
import lightning.product.RealmsScreenWithCallback;
import lightning.product.u_744_e;
import lightning.product.w_728_N;
import lightning.product.x_282_a;
import lightning.product.CloseServerTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class W_3464_O
extends RealmsScreenWithCallback {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final g_2336_b J_1907_R = new g_2336_b("realms", "textures/gui/realms/on_icon.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("realms", "textures/gui/realms/off_icon.png");
    private static final g_2336_b G_564_y = new g_2336_b("realms", "textures/gui/realms/expired_icon.png");
    private static final g_2336_b P_1922_E = new g_2336_b("realms", "textures/gui/realms/expires_soon_icon.png");
    private static final x_282_a u_1723_Y = new F_2904_S("mco.configure.worlds.title");
    private static final x_282_a v_4262_N = new F_2904_S("mco.configure.world.title");
    private static final x_282_a w_1484_f = new F_2904_S("mco.configure.current.minigame").n_1700_B(": ");
    private static final x_282_a t_148_a = new F_2904_S("mco.selectServer.expired");
    private static final x_282_a s_956_w = new F_2904_S("mco.selectServer.expires.soon");
    private static final x_282_a u_2550_I = new F_2904_S("mco.selectServer.expires.day");
    private static final x_282_a M_588_G = new F_2904_S("mco.selectServer.open");
    private static final x_282_a P_4830_p = new F_2904_S("mco.selectServer.closed");
    @Nullable
    private x_282_a h_1847_R;
    private final r_715_M Q_4569_t;
    @Nullable
    private q_1982_R M_182_A;
    private final long t_1786_h;
    private int multiplayerClientSuggestionProvider;
    private int w_1457_N;
    private Button Y_601_j;
    private Button Y_259_p;
    private Button Q_2552_b;
    private Button C_2741_M;
    private Button k_2293_S;
    private Button q_2307_F;
    private Button Z_875_P;
    private boolean c_3005_b;
    private int H_2857_Y;
    private int A_4115_X;

    public W_3464_O(r_715_M p_i51774_1_, long p_i51774_2_) {
        this.Q_4569_t = p_i51774_1_;
        this.t_1786_h = p_i51774_2_;
    }

    @Override
    public void init() {
        if (this.M_182_A == null) {
            this.n_1700_B(this.t_1786_h);
        }
        this.multiplayerClientSuggestionProvider = this.width / 2 - 187;
        this.w_1457_N = this.width / 2 + 190;
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.Y_601_j = this.addButton(new Button(this.n_1700_B(0, 3), W_3464_O.G_564_y(0), 100, 20, new F_2904_S("mco.configure.world.buttons.players"), p_237818_1_ -> this.minecraft.n_1700_B(new s_1671_u(this, this.M_182_A))));
        this.Y_259_p = this.addButton(new Button(this.n_1700_B(1, 3), W_3464_O.G_564_y(0), 100, 20, new F_2904_S("mco.configure.world.buttons.settings"), p_237817_1_ -> this.minecraft.n_1700_B(new RealmsSettingsScreen(this, this.M_182_A.G_564_y()))));
        this.Q_2552_b = this.addButton(new Button(this.n_1700_B(2, 3), W_3464_O.G_564_y(0), 100, 20, new F_2904_S("mco.configure.world.buttons.subscription"), p_237816_1_ -> this.minecraft.n_1700_B(new F_4247_a(this, this.M_182_A.G_564_y(), this.Q_4569_t))));
        for (int i = 1; i < 5; ++i) {
            this.n_1700_B(i);
        }
        this.Z_875_P = this.addButton(new Button(this.J_1907_R(0), W_3464_O.G_564_y(13) - 5, 100, 20, new F_2904_S("mco.configure.world.buttons.switchminigame"), p_237815_1_ -> {
            G_424_k realmsselectworldtemplatescreen = new G_424_k(this, q_1982_R.J_1907_R.J_1907_R);
            realmsselectworldtemplatescreen.n_1700_B((x_282_a)new F_2904_S("mco.template.title.minigame"));
            this.minecraft.n_1700_B(realmsselectworldtemplatescreen);
        }));
        this.C_2741_M = this.addButton(new Button(this.J_1907_R(0), W_3464_O.G_564_y(13) - 5, 90, 20, new F_2904_S("mco.configure.world.buttons.options"), p_237814_1_ -> this.minecraft.n_1700_B(new f_1043_S(this, this.M_182_A.t_148_a.get(this.M_182_A.h_1847_R).G_564_y(), this.M_182_A.P_4830_p, this.M_182_A.h_1847_R))));
        this.k_2293_S = this.addButton(new Button(this.J_1907_R(1), W_3464_O.G_564_y(13) - 5, 90, 20, new F_2904_S("mco.configure.world.backup"), p_237812_1_ -> this.minecraft.n_1700_B(new c_132_F(this, this.M_182_A.G_564_y(), this.M_182_A.h_1847_R))));
        this.q_2307_F = this.addButton(new Button(this.J_1907_R(2), W_3464_O.G_564_y(13) - 5, 90, 20, new F_2904_S("mco.configure.world.buttons.resetworld"), p_237810_1_ -> this.minecraft.n_1700_B(new C_3538_G(this, this.M_182_A.G_564_y(), () -> this.minecraft.n_1700_B(this.J_1907_R()), () -> this.minecraft.n_1700_B(this.J_1907_R())))));
        this.addButton(new Button(this.w_1457_N - 80 + 8, W_3464_O.G_564_y(13) - 5, 70, 20, CommonComponents.w_1484_f, p_237808_1_ -> this.R_4764_Y()));
        this.k_2293_S.active = true;
        if (this.M_182_A == null) {
            this.t_148_a();
            this.w_1484_f();
            this.Y_601_j.active = false;
            this.Y_259_p.active = false;
            this.Q_2552_b.active = false;
        } else {
            this.G_564_y();
            if (this.v_4262_N()) {
                this.w_1484_f();
            } else {
                this.t_148_a();
            }
        }
    }

    private void n_1700_B(int p_224402_1_) {
        int i = this.R_4764_Y(p_224402_1_);
        int j = W_3464_O.G_564_y(5) + 5;
        S_980_j realmsserverslotbutton = new S_980_j(i, j, 80, 80, () -> this.M_182_A, p_237801_1_ -> {
            this.h_1847_R = p_237801_1_;
        }, p_224402_1_, p_237795_2_ -> {
            S_980_j.J_1907_R realmsserverslotbutton$serverdata = ((S_980_j)p_237795_2_).n_1700_B();
            if (realmsserverslotbutton$serverdata != null) {
                switch (realmsserverslotbutton$serverdata.R_4764_Y) {
                    case n_1700_B: {
                        break;
                    }
                    case R_4764_Y: {
                        this.n_1700_B(this.M_182_A);
                        break;
                    }
                    case J_1907_R: {
                        if (realmsserverslotbutton$serverdata.J_1907_R) {
                            this.u_1723_Y();
                            break;
                        }
                        if (realmsserverslotbutton$serverdata.n_1700_B) {
                            this.J_1907_R(p_224402_1_, this.M_182_A);
                            break;
                        }
                        this.n_1700_B(p_224402_1_, this.M_182_A);
                        break;
                    }
                    default: {
                        throw new IllegalStateException("Unknown action " + String.valueOf((Object)realmsserverslotbutton$serverdata.R_4764_Y));
                    }
                }
            }
        });
        this.addButton(realmsserverslotbutton);
    }

    private int J_1907_R(int p_224411_1_) {
        return this.multiplayerClientSuggestionProvider + p_224411_1_ * 95;
    }

    private int n_1700_B(int p_224374_1_, int p_224374_2_) {
        return this.width / 2 - (p_224374_2_ * 105 - 5) / 2 + p_224374_1_ * 105;
    }

    @Override
    public void tick() {
        super.tick();
        ++this.H_2857_Y;
        --this.A_4115_X;
        if (this.A_4115_X < 0) {
            this.A_4115_X = 0;
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.h_1847_R = null;
        this.renderBackground(matrixStack);
        W_3464_O.drawCenteredString(matrixStack, this.font, u_1723_Y, this.width / 2, W_3464_O.G_564_y(4), 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        if (this.M_182_A == null) {
            W_3464_O.drawCenteredString(matrixStack, this.font, v_4262_N, this.width / 2, 17, 0xFFFFFF);
        } else {
            String s = this.M_182_A.J_1907_R();
            int i = this.font.J_1907_R(s);
            int j = this.M_182_A.P_1922_E == q_1982_R.R_4764_Y.n_1700_B ? 0xA0A0A0 : 0x7FFF7F;
            int k = this.font.n_1700_B((FormattedText)v_4262_N);
            W_3464_O.drawCenteredString(matrixStack, this.font, v_4262_N, this.width / 2, 12, 0xFFFFFF);
            W_3464_O.drawCenteredString(matrixStack, this.font, s, this.width / 2, 24, j);
            int l = Math.min(this.n_1700_B(2, 3) + 80 - 11, this.width / 2 + i / 2 + k / 2 + 10);
            this.n_1700_B(matrixStack, l, 7, mouseX, mouseY);
            if (this.v_4262_N()) {
                this.font.J_1907_R(matrixStack, w_1484_f.P_1922_E().n_1700_B(this.M_182_A.R_4764_Y()), (float)(this.multiplayerClientSuggestionProvider + 80 + 20 + 10), (float)W_3464_O.G_564_y(13), 0xFFFFFF);
            }
            if (this.h_1847_R != null) {
                this.n_1700_B(matrixStack, this.h_1847_R, mouseX, mouseY);
            }
        }
    }

    private int R_4764_Y(int p_224368_1_) {
        return this.multiplayerClientSuggestionProvider + (p_224368_1_ - 1) * 98;
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.R_4764_Y();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private void R_4764_Y() {
        if (this.c_3005_b) {
            this.Q_4569_t.R_4764_Y();
        }
        this.minecraft.n_1700_B(this.Q_4569_t);
    }

    private void n_1700_B(long p_224387_1_) {
        new Thread(() -> {
            p_178_J realmsclient = p_178_J.n_1700_B();
            try {
                this.M_182_A = realmsclient.n_1700_B(p_224387_1_);
                this.G_564_y();
                if (this.v_4262_N()) {
                    this.J_1907_R(this.Z_875_P);
                } else {
                    this.J_1907_R(this.C_2741_M);
                    this.J_1907_R(this.k_2293_S);
                    this.J_1907_R(this.q_2307_F);
                }
            }
            catch (u_744_e realmsserviceexception) {
                n_1700_B.error("Couldn't get own world");
                this.minecraft.execute(() -> this.minecraft.n_1700_B(new w_728_N(x_282_a.J_1907_R(realmsserviceexception.getMessage()), (k_2603_m)this.Q_4569_t)));
            }
        }).start();
    }

    private void G_564_y() {
        this.Y_601_j.active = !this.M_182_A.s_956_w;
        this.Y_259_p.active = !this.M_182_A.s_956_w;
        this.Q_2552_b.active = true;
        this.Z_875_P.active = !this.M_182_A.s_956_w;
        this.C_2741_M.active = !this.M_182_A.s_956_w;
        this.q_2307_F.active = !this.M_182_A.s_956_w;
    }

    private void n_1700_B(q_1982_R p_224385_1_) {
        if (this.M_182_A.P_1922_E == q_1982_R.R_4764_Y.J_1907_R) {
            this.Q_4569_t.n_1700_B(p_224385_1_, new W_3464_O(this.Q_4569_t.G_564_y(), this.t_1786_h));
        } else {
            this.n_1700_B(true, new W_3464_O(this.Q_4569_t.G_564_y(), this.t_1786_h));
        }
    }

    private void u_1723_Y() {
        G_424_k realmsselectworldtemplatescreen = new G_424_k(this, q_1982_R.J_1907_R.J_1907_R);
        realmsselectworldtemplatescreen.n_1700_B((x_282_a)new F_2904_S("mco.template.title.minigame"));
        realmsselectworldtemplatescreen.n_1700_B(new F_2904_S("mco.minigame.world.info.line1"), new F_2904_S("mco.minigame.world.info.line2"));
        this.minecraft.n_1700_B(realmsselectworldtemplatescreen);
    }

    private void n_1700_B(int p_224403_1_, q_1982_R p_224403_2_) {
        F_2904_S itextcomponent = new F_2904_S("mco.configure.world.slot.switch.question.line1");
        F_2904_S itextcomponent1 = new F_2904_S("mco.configure.world.slot.switch.question.line2");
        this.minecraft.n_1700_B(new RealmsLongConfirmationScreen(p_237805_3_ -> {
            if (p_237805_3_) {
                this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.Q_4569_t, new SwitchSlotTask(p_224403_2_.n_1700_B, p_224403_1_, () -> this.minecraft.n_1700_B(this.J_1907_R()))));
            } else {
                this.minecraft.n_1700_B(this);
            }
        }, RealmsLongConfirmationScreen.n_1700_B.J_1907_R, itextcomponent, itextcomponent1, true));
    }

    private void J_1907_R(int p_224388_1_, q_1982_R p_224388_2_) {
        F_2904_S itextcomponent = new F_2904_S("mco.configure.world.slot.switch.question.line1");
        F_2904_S itextcomponent1 = new F_2904_S("mco.configure.world.slot.switch.question.line2");
        this.minecraft.n_1700_B(new RealmsLongConfirmationScreen(p_237797_3_ -> {
            if (p_237797_3_) {
                C_3538_G realmsresetworldscreen = new C_3538_G(this, p_224388_2_, new F_2904_S("mco.configure.world.switch.slot"), new F_2904_S("mco.configure.world.switch.slot.subtitle"), 0xA0A0A0, CommonComponents.G_564_y, () -> this.minecraft.n_1700_B(this.J_1907_R()), () -> this.minecraft.n_1700_B(this.J_1907_R()));
                realmsresetworldscreen.n_1700_B(p_224388_1_);
                realmsresetworldscreen.n_1700_B(new F_2904_S("mco.create.world.reset.title"));
                this.minecraft.n_1700_B(realmsresetworldscreen);
            } else {
                this.minecraft.n_1700_B(this);
            }
        }, RealmsLongConfirmationScreen.n_1700_B.J_1907_R, itextcomponent, itextcomponent1, true));
    }

    protected void n_1700_B(g_221_o p_237796_1_, @Nullable x_282_a p_237796_2_, int p_237796_3_, int p_237796_4_) {
        int i = p_237796_3_ + 12;
        int j = p_237796_4_ - 12;
        int k = this.font.n_1700_B((FormattedText)p_237796_2_);
        if (i + k + 3 > this.w_1457_N) {
            i = i - k - 20;
        }
        W_3464_O.fillGradient(p_237796_1_, i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
        this.font.n_1700_B(p_237796_1_, p_237796_2_, (float)i, (float)j, 0xFFFFFF);
    }

    private void n_1700_B(g_221_o p_237807_1_, int p_237807_2_, int p_237807_3_, int p_237807_4_, int p_237807_5_) {
        if (this.M_182_A.s_956_w) {
            this.J_1907_R(p_237807_1_, p_237807_2_, p_237807_3_, p_237807_4_, p_237807_5_);
        } else if (this.M_182_A.P_1922_E == q_1982_R.R_4764_Y.n_1700_B) {
            this.G_564_y(p_237807_1_, p_237807_2_, p_237807_3_, p_237807_4_, p_237807_5_);
        } else if (this.M_182_A.P_1922_E == q_1982_R.R_4764_Y.J_1907_R) {
            if (this.M_182_A.M_588_G < 7) {
                this.n_1700_B(p_237807_1_, p_237807_2_, p_237807_3_, p_237807_4_, p_237807_5_, this.M_182_A.M_588_G);
            } else {
                this.R_4764_Y(p_237807_1_, p_237807_2_, p_237807_3_, p_237807_4_, p_237807_5_);
            }
        }
    }

    private void J_1907_R(g_221_o p_237809_1_, int p_237809_2_, int p_237809_3_, int p_237809_4_, int p_237809_5_) {
        this.minecraft.G_624_v().n_1700_B(G_564_y);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        C_2701_A.blit(p_237809_1_, p_237809_2_, p_237809_3_, 0.0f, 0.0f, 10, 28, 10, 28);
        if (p_237809_4_ >= p_237809_2_ && p_237809_4_ <= p_237809_2_ + 9 && p_237809_5_ >= p_237809_3_ && p_237809_5_ <= p_237809_3_ + 27) {
            this.h_1847_R = t_148_a;
        }
    }

    private void n_1700_B(g_221_o p_237804_1_, int p_237804_2_, int p_237804_3_, int p_237804_4_, int p_237804_5_, int p_237804_6_) {
        this.minecraft.G_624_v().n_1700_B(P_1922_E);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        if (this.H_2857_Y % 20 < 10) {
            C_2701_A.blit(p_237804_1_, p_237804_2_, p_237804_3_, 0.0f, 0.0f, 10, 28, 20, 28);
        } else {
            C_2701_A.blit(p_237804_1_, p_237804_2_, p_237804_3_, 10.0f, 0.0f, 10, 28, 20, 28);
        }
        if (p_237804_4_ >= p_237804_2_ && p_237804_4_ <= p_237804_2_ + 9 && p_237804_5_ >= p_237804_3_ && p_237804_5_ <= p_237804_3_ + 27) {
            this.h_1847_R = p_237804_6_ <= 0 ? s_956_w : (p_237804_6_ == 1 ? u_2550_I : new F_2904_S("mco.selectServer.expires.days", p_237804_6_));
        }
    }

    private void R_4764_Y(g_221_o p_237811_1_, int p_237811_2_, int p_237811_3_, int p_237811_4_, int p_237811_5_) {
        this.minecraft.G_624_v().n_1700_B(J_1907_R);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        C_2701_A.blit(p_237811_1_, p_237811_2_, p_237811_3_, 0.0f, 0.0f, 10, 28, 10, 28);
        if (p_237811_4_ >= p_237811_2_ && p_237811_4_ <= p_237811_2_ + 9 && p_237811_5_ >= p_237811_3_ && p_237811_5_ <= p_237811_3_ + 27) {
            this.h_1847_R = M_588_G;
        }
    }

    private void G_564_y(g_221_o p_237813_1_, int p_237813_2_, int p_237813_3_, int p_237813_4_, int p_237813_5_) {
        this.minecraft.G_624_v().n_1700_B(R_4764_Y);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        C_2701_A.blit(p_237813_1_, p_237813_2_, p_237813_3_, 0.0f, 0.0f, 10, 28, 10, 28);
        if (p_237813_4_ >= p_237813_2_ && p_237813_4_ <= p_237813_2_ + 9 && p_237813_5_ >= p_237813_3_ && p_237813_5_ <= p_237813_3_ + 27) {
            this.h_1847_R = P_4830_p;
        }
    }

    private boolean v_4262_N() {
        return this.M_182_A != null && this.M_182_A.P_4830_p == q_1982_R.J_1907_R.J_1907_R;
    }

    private void w_1484_f() {
        this.n_1700_B(this.C_2741_M);
        this.n_1700_B(this.k_2293_S);
        this.n_1700_B(this.q_2307_F);
    }

    private void n_1700_B(Button p_237799_1_) {
        p_237799_1_.visible = false;
        this.children.remove(p_237799_1_);
        this.buttons.remove(p_237799_1_);
    }

    private void J_1907_R(Button p_237806_1_) {
        p_237806_1_.visible = true;
        this.addButton(p_237806_1_);
    }

    private void t_148_a() {
        this.n_1700_B(this.Z_875_P);
    }

    public void n_1700_B(RealmsWorldOptions p_224386_1_) {
        RealmsWorldOptions realmsworldoptions = this.M_182_A.t_148_a.get(this.M_182_A.h_1847_R);
        p_224386_1_.u_2550_I = realmsworldoptions.u_2550_I;
        p_224386_1_.M_588_G = realmsworldoptions.M_588_G;
        p_178_J realmsclient = p_178_J.n_1700_B();
        try {
            realmsclient.n_1700_B(this.M_182_A.n_1700_B, this.M_182_A.h_1847_R, p_224386_1_);
            this.M_182_A.t_148_a.put(this.M_182_A.h_1847_R, p_224386_1_);
        }
        catch (u_744_e realmsserviceexception) {
            n_1700_B.error("Couldn't save slot settings");
            this.minecraft.n_1700_B(new w_728_N(realmsserviceexception, (k_2603_m)this));
            return;
        }
        this.minecraft.n_1700_B(this);
    }

    public void n_1700_B(String p_224410_1_, String p_224410_2_) {
        String s = p_224410_2_.trim().isEmpty() ? null : p_224410_2_;
        p_178_J realmsclient = p_178_J.n_1700_B();
        try {
            realmsclient.J_1907_R(this.M_182_A.n_1700_B, p_224410_1_, s);
            this.M_182_A.n_1700_B(p_224410_1_);
            this.M_182_A.J_1907_R(s);
        }
        catch (u_744_e realmsserviceexception) {
            n_1700_B.error("Couldn't save settings");
            this.minecraft.n_1700_B(new w_728_N(realmsserviceexception, (k_2603_m)this));
            return;
        }
        this.minecraft.n_1700_B(this);
    }

    public void n_1700_B(boolean p_237802_1_, k_2603_m p_237802_2_) {
        this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(p_237802_2_, new OpenServerTask(this.M_182_A, this, this.Q_4569_t, p_237802_1_)));
    }

    public void n_1700_B(k_2603_m p_237800_1_) {
        this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(p_237800_1_, new CloseServerTask(this.M_182_A, this)));
    }

    public void n_1700_B() {
        this.c_3005_b = true;
    }

    @Override
    protected void n_1700_B(@Nullable S_4022_R p_223627_1_) {
        if (p_223627_1_ != null && S_4022_R.n_1700_B.J_1907_R == p_223627_1_.t_148_a) {
            this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.Q_4569_t, new SwitchMinigameTask(this.M_182_A.n_1700_B, p_223627_1_, this.J_1907_R())));
        }
    }

    public W_3464_O J_1907_R() {
        return new W_3464_O(this.Q_4569_t, this.t_1786_h);
    }
}


