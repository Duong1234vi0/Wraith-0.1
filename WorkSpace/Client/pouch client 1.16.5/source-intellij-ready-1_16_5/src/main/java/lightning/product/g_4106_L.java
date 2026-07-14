/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.C_2701_A;
import lightning.product.C_290_v;
import lightning.product.C_3538_G;
import lightning.product.F_1410_V;
import lightning.product.F_2904_S;
import lightning.product.RealmsLongRunningMcoTaskScreen;
import lightning.product.SwitchSlotTask;
import lightning.product.RealmsWorldOptions;
import lightning.product.S_980_j;
import lightning.product.Button;
import lightning.product.OpenServerTask;
import lightning.product.c_4037_x;
import lightning.product.RealmsLongConfirmationScreen;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.r_715_M;
import lightning.product.NarrationHelper;
import lightning.product.u_530_F;
import lightning.product.u_744_e;
import lightning.product.w_728_N;
import lightning.product.x_282_a;
import lightning.product.y_2772_m;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class g_4106_L
extends RealmsScreen {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final k_2603_m J_1907_R;
    private final r_715_M R_4764_Y;
    private q_1982_R G_564_y;
    private final long P_1922_E;
    private final x_282_a u_1723_Y;
    private final x_282_a[] v_4262_N = new x_282_a[]{new F_2904_S("mco.brokenworld.message.line1"), new F_2904_S("mco.brokenworld.message.line2")};
    private int w_1484_f;
    private int t_148_a;
    private final List<Integer> s_956_w = Lists.newArrayList();
    private int u_2550_I;

    public g_4106_L(k_2603_m p_i232200_1_, r_715_M p_i232200_2_, long p_i232200_3_, boolean p_i232200_5_) {
        this.J_1907_R = p_i232200_1_;
        this.R_4764_Y = p_i232200_2_;
        this.P_1922_E = p_i232200_3_;
        this.u_1723_Y = p_i232200_5_ ? new F_2904_S("mco.brokenworld.minigame.title") : new F_2904_S("mco.brokenworld.title");
    }

    @Override
    public void init() {
        this.w_1484_f = this.width / 2 - 150;
        this.t_148_a = this.width / 2 + 190;
        this.addButton(new Button(this.t_148_a - 80 + 8, g_4106_L.G_564_y(13) - 5, 70, 20, CommonComponents.w_1484_f, p_237776_1_ -> this.R_4764_Y()));
        if (this.G_564_y == null) {
            this.n_1700_B(this.P_1922_E);
        } else {
            this.J_1907_R();
        }
        this.minecraft.Q_4569_t.n_1700_B(true);
        NarrationHelper.n_1700_B(Stream.concat(Stream.of(this.u_1723_Y), Stream.of(this.v_4262_N)).map(x_282_a::getString).collect(Collectors.joining(" ")));
    }

    private void J_1907_R() {
        for (Map.Entry<Integer, RealmsWorldOptions> entry : this.G_564_y.t_148_a.entrySet()) {
            int i = entry.getKey();
            boolean flag = i != this.G_564_y.h_1847_R || this.G_564_y.P_4830_p == q_1982_R.J_1907_R.J_1907_R;
            Button button = flag ? new Button(this.n_1700_B(i), g_4106_L.G_564_y(8), 80, 20, new F_2904_S("mco.brokenworld.play"), p_237780_2_ -> {
                if (this.G_564_y.t_148_a.get((Object)Integer.valueOf((int)i)).h_1847_R) {
                    C_3538_G realmsresetworldscreen = new C_3538_G(this, this.G_564_y, new F_2904_S("mco.configure.world.switch.slot"), new F_2904_S("mco.configure.world.switch.slot.subtitle"), 0xA0A0A0, CommonComponents.G_564_y, this::n_1700_B, () -> {
                        this.minecraft.n_1700_B(this);
                        this.n_1700_B();
                    });
                    realmsresetworldscreen.n_1700_B(i);
                    realmsresetworldscreen.n_1700_B(new F_2904_S("mco.create.world.reset.title"));
                    this.minecraft.n_1700_B(realmsresetworldscreen);
                } else {
                    this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.J_1907_R, new SwitchSlotTask(this.G_564_y.n_1700_B, i, this::n_1700_B)));
                }
            }) : new Button(this.n_1700_B(i), g_4106_L.G_564_y(8), 80, 20, new F_2904_S("mco.brokenworld.download"), p_237777_2_ -> {
                F_2904_S itextcomponent = new F_2904_S("mco.configure.world.restore.download.question.line1");
                F_2904_S itextcomponent1 = new F_2904_S("mco.configure.world.restore.download.question.line2");
                this.minecraft.n_1700_B(new RealmsLongConfirmationScreen(p_237778_2_ -> {
                    if (p_237778_2_) {
                        this.J_1907_R(i);
                    } else {
                        this.minecraft.n_1700_B(this);
                    }
                }, RealmsLongConfirmationScreen.n_1700_B.J_1907_R, itextcomponent, itextcomponent1, true));
            });
            if (this.s_956_w.contains(i)) {
                button.active = false;
                button.setMessage(new F_2904_S("mco.brokenworld.downloaded"));
            }
            this.addButton(button);
            this.addButton(new Button(this.n_1700_B(i), g_4106_L.G_564_y(10), 80, 20, new F_2904_S("mco.brokenworld.reset"), p_237773_2_ -> {
                C_3538_G realmsresetworldscreen = new C_3538_G(this, this.G_564_y, this::n_1700_B, () -> {
                    this.minecraft.n_1700_B(this);
                    this.n_1700_B();
                });
                if (i != this.G_564_y.h_1847_R || this.G_564_y.P_4830_p == q_1982_R.J_1907_R.J_1907_R) {
                    realmsresetworldscreen.n_1700_B(i);
                }
                this.minecraft.n_1700_B(realmsresetworldscreen);
            }));
        }
    }

    @Override
    public void tick() {
        ++this.u_2550_I;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        g_4106_L.drawCenteredString(matrixStack, this.font, this.u_1723_Y, this.width / 2, 17, 0xFFFFFF);
        for (int i = 0; i < this.v_4262_N.length; ++i) {
            g_4106_L.drawCenteredString(matrixStack, this.font, this.v_4262_N[i], this.width / 2, g_4106_L.G_564_y(-1) + 3 + i * 12, 0xA0A0A0);
        }
        if (this.G_564_y != null) {
            for (Map.Entry<Integer, RealmsWorldOptions> entry : this.G_564_y.t_148_a.entrySet()) {
                if (entry.getValue().M_588_G != null && entry.getValue().u_2550_I != -1L) {
                    this.n_1700_B(matrixStack, this.n_1700_B(entry.getKey()), g_4106_L.G_564_y(1) + 5, mouseX, mouseY, this.G_564_y.h_1847_R == entry.getKey() && !this.G_564_y(), entry.getValue().n_1700_B(entry.getKey()), entry.getKey(), entry.getValue().u_2550_I, entry.getValue().M_588_G, entry.getValue().h_1847_R);
                    continue;
                }
                this.n_1700_B(matrixStack, this.n_1700_B(entry.getKey()), g_4106_L.G_564_y(1) + 5, mouseX, mouseY, this.G_564_y.h_1847_R == entry.getKey() && !this.G_564_y(), entry.getValue().n_1700_B(entry.getKey()), entry.getKey(), -1L, null, entry.getValue().h_1847_R);
            }
        }
    }

    private int n_1700_B(int p_224065_1_) {
        return this.w_1484_f + (p_224065_1_ - 1) * 110;
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
        this.minecraft.n_1700_B(this.J_1907_R);
    }

    private void n_1700_B(long p_224068_1_) {
        new Thread(() -> {
            p_178_J realmsclient = p_178_J.n_1700_B();
            try {
                this.G_564_y = realmsclient.n_1700_B(p_224068_1_);
                this.J_1907_R();
            }
            catch (u_744_e realmsserviceexception) {
                n_1700_B.error("Couldn't get own world");
                this.minecraft.n_1700_B(new w_728_N(x_282_a.J_1907_R(realmsserviceexception.getMessage()), this.J_1907_R));
            }
        }).start();
    }

    public void n_1700_B() {
        new Thread(() -> {
            p_178_J realmsclient = p_178_J.n_1700_B();
            if (this.G_564_y.P_1922_E == q_1982_R.R_4764_Y.n_1700_B) {
                this.minecraft.execute(() -> this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this, new OpenServerTask(this.G_564_y, this, this.R_4764_Y, true))));
            } else {
                try {
                    this.R_4764_Y.G_564_y().n_1700_B(realmsclient.n_1700_B(this.P_1922_E), this);
                }
                catch (u_744_e realmsserviceexception) {
                    n_1700_B.error("Couldn't get own world");
                    this.minecraft.execute(() -> this.minecraft.n_1700_B(this.J_1907_R));
                }
            }
        }).start();
    }

    private void J_1907_R(int p_224066_1_) {
        p_178_J realmsclient = p_178_J.n_1700_B();
        try {
            F_1410_V worlddownload = realmsclient.J_1907_R(this.G_564_y.n_1700_B, p_224066_1_);
            C_290_v realmsdownloadlatestworldscreen = new C_290_v(this, worlddownload, this.G_564_y.n_1700_B(p_224066_1_), p_237774_2_ -> {
                if (p_237774_2_) {
                    this.s_956_w.add(p_224066_1_);
                    this.children.clear();
                    this.J_1907_R();
                } else {
                    this.minecraft.n_1700_B(this);
                }
            });
            this.minecraft.n_1700_B(realmsdownloadlatestworldscreen);
        }
        catch (u_744_e realmsserviceexception) {
            n_1700_B.error("Couldn't download world data");
            this.minecraft.n_1700_B(new w_728_N(realmsserviceexception, (k_2603_m)this));
        }
    }

    private boolean G_564_y() {
        return this.G_564_y != null && this.G_564_y.P_4830_p == q_1982_R.J_1907_R.J_1907_R;
    }

    private void n_1700_B(g_221_o p_237775_1_, int p_237775_2_, int p_237775_3_, int p_237775_4_, int p_237775_5_, boolean p_237775_6_, String p_237775_7_, int p_237775_8_, long p_237775_9_, String p_237775_11_, boolean p_237775_12_) {
        if (p_237775_12_) {
            this.minecraft.G_624_v().n_1700_B(S_980_j.J_1907_R);
        } else if (p_237775_11_ != null && p_237775_9_ != -1L) {
            y_2772_m.n_1700_B(String.valueOf(p_237775_9_), p_237775_11_);
        } else if (p_237775_8_ == 1) {
            this.minecraft.G_624_v().n_1700_B(S_980_j.R_4764_Y);
        } else if (p_237775_8_ == 2) {
            this.minecraft.G_624_v().n_1700_B(S_980_j.G_564_y);
        } else if (p_237775_8_ == 3) {
            this.minecraft.G_624_v().n_1700_B(S_980_j.P_1922_E);
        } else {
            y_2772_m.n_1700_B(String.valueOf(this.G_564_y.M_182_A), this.G_564_y.t_1786_h);
        }
        if (!p_237775_6_) {
            c_4037_x.G_564_y(0.56f, 0.56f, 0.56f, 1.0f);
        } else if (p_237775_6_) {
            float f = 0.9f + 0.1f * u_530_F.J_1907_R((float)this.u_2550_I * 0.2f);
            c_4037_x.G_564_y(f, f, f, 1.0f);
        }
        C_2701_A.blit(p_237775_1_, p_237775_2_ + 3, p_237775_3_ + 3, 0.0f, 0.0f, 74, 74, 74, 74);
        this.minecraft.G_624_v().n_1700_B(S_980_j.n_1700_B);
        if (p_237775_6_) {
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        } else {
            c_4037_x.G_564_y(0.56f, 0.56f, 0.56f, 1.0f);
        }
        C_2701_A.blit(p_237775_1_, p_237775_2_, p_237775_3_, 0.0f, 0.0f, 80, 80, 80, 80);
        g_4106_L.drawCenteredString(p_237775_1_, this.font, p_237775_7_, p_237775_2_ + 40, p_237775_3_ + 66, 0xFFFFFF);
    }
}


