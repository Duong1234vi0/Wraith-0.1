/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.RealmsBridge;
import lightning.product.F_2904_S;
import lightning.product.ConfirmLinkScreen;
import lightning.product.N_131_X;
import lightning.product.O_922_L;
import lightning.product.GenericDirtMessageScreen;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.ServerData;
import lightning.product.Z_3926_G;
import lightning.product.BetterMinecraft;
import lightning.product.e_465_j;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import lightning.product.k_596_g;
import lightning.product.l_3350_W;
import lightning.product.ClientBootstrap;
import lightning.product.q_3131_N;
import lightning.product.q_3418_t;
import lightning.product.t_3048_V;
import lightning.product.y_4642_Y;

public class p_402_W
extends k_2603_m {
    private final boolean n_1700_B;

    public p_402_W(boolean isFullMenu) {
        super(isFullMenu ? new F_2904_S("menu.game") : new F_2904_S("menu.paused"));
        this.n_1700_B = isFullMenu;
    }

    @Override
    protected void init() {
        if (this.n_1700_B) {
            this.n_1700_B();
        }
    }

    private void n_1700_B() {
        BetterMinecraft bmc;
        int i = -16;
        int j = 98;
        this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 24 + -16, 204, 20, new F_2904_S("menu.returnToGame"), button2 -> {
            this.minecraft.n_1700_B((k_2603_m)null);
            this.minecraft.h_1847_R.w_1484_f();
        }));
        this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 48 + -16, 98, 20, new F_2904_S("gui.advancements"), button2 -> this.minecraft.n_1700_B(new Z_3926_G(this.minecraft.Y_259_p.n_1700_B.w_1484_f()))));
        this.addButton(new Button(this.width / 2 + 4, this.height / 4 + 48 + -16, 98, 20, new F_2904_S("gui.stats"), button2 -> this.minecraft.n_1700_B(new l_3350_W(this, this.minecraft.Y_259_p.Q_4569_t()))));
        String s = y_4642_Y.R_4764_Y() ? "https://aka.ms/javafeedback" : "https://discord.gg/qjyDp3eySS";
        String bugUrl = y_4642_Y.R_4764_Y() ? "https://bugs.mojang.com/browse/MC" : "https://discord.gg/qjyDp3eySS";
        this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 72 + -16, 98, 20, new F_2904_S("menu.sendFeedback"), button2 -> this.minecraft.n_1700_B(new ConfirmLinkScreen(open -> {
            if (open) {
                j_3341_s.t_148_a().n_1700_B(s);
            }
            this.minecraft.n_1700_B(this);
        }, s, true))));
        this.addButton(new Button(this.width / 2 + 4, this.height / 4 + 72 + -16, 98, 20, new F_2904_S("menu.reportBugs"), button2 -> this.minecraft.n_1700_B(new ConfirmLinkScreen(open -> {
            if (open) {
                j_3341_s.t_148_a().n_1700_B(bugUrl);
            }
            this.minecraft.n_1700_B(this);
        }, bugUrl, true))));
        this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 96 + -16, 98, 20, new F_2904_S("menu.options"), button2 -> this.minecraft.n_1700_B(new e_465_j(this, this.minecraft.P_4830_p))));
        Button button = this.addButton(new Button(this.width / 2 + 4, this.height / 4 + 96 + -16, 98, 20, new F_2904_S("menu.shareToLan"), button2 -> this.minecraft.n_1700_B(new N_131_X(this))));
        button.active = this.minecraft.e_4240_b() && !this.minecraft.n_3318_d().RealmsClientConfig();
        Button button1 = this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 120 + -16, 204, 20, new F_2904_S("menu.returnToMenu"), button2 -> {
            BetterMinecraft betterMinecraft = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
            if (betterMinecraft != null && betterMinecraft.Y_601_j()) {
                this.minecraft.n_1700_B(new q_3418_t(confirmed -> {
                    if (confirmed) {
                        this.J_1907_R();
                    } else {
                        this.minecraft.n_1700_B(this);
                    }
                }, new U_2871_b("\u041f\u043e\u0434\u0442\u0432\u0435\u0440\u0436\u0434\u0435\u043d\u0438\u0435 \u0432\u044b\u0445\u043e\u0434\u0430"), new U_2871_b("\u0412\u044b \u043d\u0430\u0445\u043e\u0434\u0438\u0442\u0435\u0441\u044c \u0432 \u0440\u0435\u0436\u0438\u043c\u0435 \u0431\u043e\u044f. \u0412\u044b \u0443\u0432\u0435\u0440\u0435\u043d\u044b, \u0447\u0442\u043e \u0445\u043e\u0442\u0438\u0442\u0435 \u0432\u044b\u0439\u0442\u0438 \u0441 \u0441\u0435\u0440\u0432\u0435\u0440\u0430?")));
            } else {
                this.J_1907_R();
            }
        }));
        if (!this.minecraft.x_607_J()) {
            button1.setMessage(new F_2904_S("menu.disconnect"));
        }
        if ((bmc = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class)) != null && bmc.w_1484_f() && bmc.k_2293_S().t_148_a().booleanValue() && !this.minecraft.x_607_J()) {
            this.addButton(new Button(this.width / 2 - 102, this.height / 4 + 144 + -16, 204, 20, new U_2871_b("Reconnect"), button2 -> {
                ServerData serverData = this.minecraft.t_4043_B();
                if (serverData != null) {
                    ServerData copy = new ServerData(serverData.n_1700_B, serverData.J_1907_R, false);
                    float delay = ((Float)bmc.q_2307_F().J_1907_R()).floatValue();
                    this.minecraft.Y_601_j.w_1484_f();
                    this.minecraft.Y_601_j();
                    if (delay <= 0.0f) {
                        this.minecraft.n_1700_B(new t_3048_V(new q_3131_N(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L()), this.minecraft, copy));
                    } else {
                        q_3131_N parent = new q_3131_N(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L());
                        this.minecraft.n_1700_B(parent);
                        new Thread(() -> {
                            try {
                                Thread.sleep((long)(delay * 1000.0f));
                            }
                            catch (InterruptedException interruptedException) {
                                // empty catch block
                            }
                            this.minecraft.w_1484_f(() -> {
                                if (this.minecraft.Y_1740_V == parent) {
                                    this.minecraft.n_1700_B(new t_3048_V(parent, this.minecraft, copy));
                                }
                            });
                        }).start();
                    }
                }
            }));
        }
    }

    private void J_1907_R() {
        boolean flag = this.minecraft.x_607_J();
        boolean flag1 = this.minecraft.Ping();
        this.minecraft.Y_601_j.w_1484_f();
        if (flag) {
            this.minecraft.J_1907_R(new GenericDirtMessageScreen(new F_2904_S("menu.savingLevel")));
        } else {
            this.minecraft.Y_601_j();
        }
        if (flag) {
            this.minecraft.n_1700_B(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L());
        } else if (flag1) {
            RealmsBridge realmsbridgescreen = new RealmsBridge();
            realmsbridgescreen.n_1700_B(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L());
        } else {
            this.minecraft.n_1700_B(new q_3131_N(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L()));
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.n_1700_B) {
            this.renderBackground(matrixStack);
            p_402_W.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 40, 0xFFFFFF);
        } else {
            p_402_W.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, 10, 0xFFFFFF);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}



