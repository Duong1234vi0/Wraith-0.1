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
import lightning.product.A_3959_N;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.G_424_k;
import lightning.product.RealmsLongRunningMcoTaskScreen;
import lightning.product.SwitchSlotTask;
import lightning.product.RealmsResetNormalWorldScreen;
import lightning.product.S_4022_R;
import lightning.product.Button;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.ResettingWorldTask;
import lightning.product.l_4537_E;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.RealmsScreenWithCallback;
import lightning.product.u_744_e;
import lightning.product.RealmsLabel;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class C_3538_G
extends RealmsScreenWithCallback {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final k_2603_m R_4764_Y;
    private final q_1982_R G_564_y;
    private RealmsLabel P_1922_E;
    private RealmsLabel u_1723_Y;
    private x_282_a v_4262_N = new F_2904_S("mco.reset.world.title");
    private x_282_a w_1484_f = new F_2904_S("mco.reset.world.warning");
    private x_282_a t_148_a = CommonComponents.G_564_y;
    private int s_956_w = 0xFF0000;
    private static final g_2336_b u_2550_I = new g_2336_b("realms", "textures/gui/realms/slot_frame.png");
    private static final g_2336_b M_588_G = new g_2336_b("realms", "textures/gui/realms/upload.png");
    private static final g_2336_b P_4830_p = new g_2336_b("realms", "textures/gui/realms/adventure.png");
    private static final g_2336_b h_1847_R = new g_2336_b("realms", "textures/gui/realms/survival_spawn.png");
    private static final g_2336_b Q_4569_t = new g_2336_b("realms", "textures/gui/realms/new_world.png");
    private static final g_2336_b M_182_A = new g_2336_b("realms", "textures/gui/realms/experience.png");
    private static final g_2336_b t_1786_h = new g_2336_b("realms", "textures/gui/realms/inspiration.png");
    private l_4537_E multiplayerClientSuggestionProvider;
    private l_4537_E w_1457_N;
    private l_4537_E Y_601_j;
    private l_4537_E Y_259_p;
    public int n_1700_B = -1;
    private n_1700_B Q_2552_b = lightning.product.C_3538_G$n_1700_B.n_1700_B;
    private J_1907_R C_2741_M;
    private S_4022_R k_2293_S;
    @Nullable
    private x_282_a q_2307_F;
    private final Runnable Z_875_P;
    private final Runnable c_3005_b;

    public C_3538_G(k_2603_m p_i232215_1_, q_1982_R p_i232215_2_, Runnable p_i232215_3_, Runnable p_i232215_4_) {
        this.R_4764_Y = p_i232215_1_;
        this.G_564_y = p_i232215_2_;
        this.Z_875_P = p_i232215_3_;
        this.c_3005_b = p_i232215_4_;
    }

    public C_3538_G(k_2603_m p_i232216_1_, q_1982_R p_i232216_2_, x_282_a p_i232216_3_, x_282_a p_i232216_4_, int p_i232216_5_, x_282_a p_i232216_6_, Runnable p_i232216_7_, Runnable p_i232216_8_) {
        this(p_i232216_1_, p_i232216_2_, p_i232216_7_, p_i232216_8_);
        this.v_4262_N = p_i232216_3_;
        this.w_1484_f = p_i232216_4_;
        this.s_956_w = p_i232216_5_;
        this.t_148_a = p_i232216_6_;
    }

    public void n_1700_B(int p_224445_1_) {
        this.n_1700_B = p_224445_1_;
    }

    public void n_1700_B(x_282_a p_224432_1_) {
        this.q_2307_F = p_224432_1_;
    }

    @Override
    public void init() {
        this.addButton(new Button(this.width / 2 - 40, C_3538_G.G_564_y(14) - 10, 80, 20, this.t_148_a, p_237959_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
        new Thread("Realms-reset-world-fetcher"){

            @Override
            public void run() {
                p_178_J realmsclient = p_178_J.n_1700_B();
                try {
                    l_4537_E worldtemplatepaginatedlist = realmsclient.n_1700_B(1, 10, q_1982_R.J_1907_R.n_1700_B);
                    l_4537_E worldtemplatepaginatedlist1 = realmsclient.n_1700_B(1, 10, q_1982_R.J_1907_R.R_4764_Y);
                    l_4537_E worldtemplatepaginatedlist2 = realmsclient.n_1700_B(1, 10, q_1982_R.J_1907_R.G_564_y);
                    l_4537_E worldtemplatepaginatedlist3 = realmsclient.n_1700_B(1, 10, q_1982_R.J_1907_R.P_1922_E);
                    C_3538_G.this.minecraft.execute(() -> {
                        C_3538_G.this.multiplayerClientSuggestionProvider = worldtemplatepaginatedlist;
                        C_3538_G.this.w_1457_N = worldtemplatepaginatedlist1;
                        C_3538_G.this.Y_601_j = worldtemplatepaginatedlist2;
                        C_3538_G.this.Y_259_p = worldtemplatepaginatedlist3;
                    });
                }
                catch (u_744_e realmsserviceexception) {
                    J_1907_R.error("Couldn't fetch templates in reset world", (Throwable)realmsserviceexception);
                }
            }
        }.start();
        this.P_1922_E = this.addListener(new RealmsLabel(this.v_4262_N, this.width / 2, 7, 0xFFFFFF));
        this.u_1723_Y = this.addListener(new RealmsLabel(this.w_1484_f, this.width / 2, 22, this.s_956_w));
        this.addButton(new R_4764_Y(this.J_1907_R(1), C_3538_G.G_564_y(0) + 10, new F_2904_S("mco.reset.world.generate"), Q_4569_t, p_237958_1_ -> this.minecraft.n_1700_B(new RealmsResetNormalWorldScreen(this, this.v_4262_N))));
        this.addButton(new R_4764_Y(this.J_1907_R(2), C_3538_G.G_564_y(0) + 10, new F_2904_S("mco.reset.world.upload"), M_588_G, p_237957_1_ -> {
            A_3959_N screen = new A_3959_N(this.G_564_y.n_1700_B, this.n_1700_B != -1 ? this.n_1700_B : this.G_564_y.h_1847_R, this, this.c_3005_b);
            this.minecraft.n_1700_B(screen);
        }));
        this.addButton(new R_4764_Y(this.J_1907_R(3), C_3538_G.G_564_y(0) + 10, new F_2904_S("mco.reset.world.template"), h_1847_R, p_237956_1_ -> {
            G_424_k realmsselectworldtemplatescreen = new G_424_k(this, q_1982_R.J_1907_R.n_1700_B, this.multiplayerClientSuggestionProvider);
            realmsselectworldtemplatescreen.n_1700_B((x_282_a)new F_2904_S("mco.reset.world.template"));
            this.minecraft.n_1700_B(realmsselectworldtemplatescreen);
        }));
        this.addButton(new R_4764_Y(this.J_1907_R(1), C_3538_G.G_564_y(6) + 20, new F_2904_S("mco.reset.world.adventure"), P_4830_p, p_237955_1_ -> {
            G_424_k realmsselectworldtemplatescreen = new G_424_k(this, q_1982_R.J_1907_R.R_4764_Y, this.w_1457_N);
            realmsselectworldtemplatescreen.n_1700_B((x_282_a)new F_2904_S("mco.reset.world.adventure"));
            this.minecraft.n_1700_B(realmsselectworldtemplatescreen);
        }));
        this.addButton(new R_4764_Y(this.J_1907_R(2), C_3538_G.G_564_y(6) + 20, new F_2904_S("mco.reset.world.experience"), M_182_A, p_237954_1_ -> {
            G_424_k realmsselectworldtemplatescreen = new G_424_k(this, q_1982_R.J_1907_R.G_564_y, this.Y_601_j);
            realmsselectworldtemplatescreen.n_1700_B((x_282_a)new F_2904_S("mco.reset.world.experience"));
            this.minecraft.n_1700_B(realmsselectworldtemplatescreen);
        }));
        this.addButton(new R_4764_Y(this.J_1907_R(3), C_3538_G.G_564_y(6) + 20, new F_2904_S("mco.reset.world.inspiration"), t_1786_h, p_237951_1_ -> {
            G_424_k realmsselectworldtemplatescreen = new G_424_k(this, q_1982_R.J_1907_R.P_1922_E, this.Y_259_p);
            realmsselectworldtemplatescreen.n_1700_B((x_282_a)new F_2904_S("mco.reset.world.inspiration"));
            this.minecraft.n_1700_B(realmsselectworldtemplatescreen);
        }));
        this.P_1922_E();
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.R_4764_Y);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    private int J_1907_R(int p_224434_1_) {
        return this.width / 2 - 130 + (p_224434_1_ - 1) * 100;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.P_1922_E.n_1700_B(this, matrixStack);
        this.u_1723_Y.n_1700_B(this, matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private void n_1700_B(g_221_o p_237948_1_, int p_237948_2_, int p_237948_3_, x_282_a p_237948_4_, g_2336_b p_237948_5_, boolean p_237948_6_, boolean p_237948_7_) {
        this.minecraft.G_624_v().n_1700_B(p_237948_5_);
        if (p_237948_6_) {
            c_4037_x.G_564_y(0.56f, 0.56f, 0.56f, 1.0f);
        } else {
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        }
        C_2701_A.blit(p_237948_1_, p_237948_2_ + 2, p_237948_3_ + 14, 0.0f, 0.0f, 56, 56, 56, 56);
        this.minecraft.G_624_v().n_1700_B(u_2550_I);
        if (p_237948_6_) {
            c_4037_x.G_564_y(0.56f, 0.56f, 0.56f, 1.0f);
        } else {
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        }
        C_2701_A.blit(p_237948_1_, p_237948_2_, p_237948_3_ + 12, 0.0f, 0.0f, 60, 60, 60, 60);
        int i = p_237948_6_ ? 0xA0A0A0 : 0xFFFFFF;
        C_3538_G.drawCenteredString(p_237948_1_, this.font, p_237948_4_, p_237948_2_ + 30, p_237948_3_, i);
    }

    @Override
    protected void n_1700_B(@Nullable S_4022_R p_223627_1_) {
        if (p_223627_1_ != null) {
            if (this.n_1700_B == -1) {
                this.J_1907_R(p_223627_1_);
            } else {
                switch (p_223627_1_.t_148_a) {
                    case n_1700_B: {
                        this.Q_2552_b = lightning.product.C_3538_G$n_1700_B.P_1922_E;
                        break;
                    }
                    case R_4764_Y: {
                        this.Q_2552_b = lightning.product.C_3538_G$n_1700_B.G_564_y;
                        break;
                    }
                    case G_564_y: {
                        this.Q_2552_b = lightning.product.C_3538_G$n_1700_B.u_1723_Y;
                        break;
                    }
                    case P_1922_E: {
                        this.Q_2552_b = lightning.product.C_3538_G$n_1700_B.v_4262_N;
                    }
                }
                this.k_2293_S = p_223627_1_;
                this.n_1700_B();
            }
        }
    }

    private void n_1700_B() {
        this.n_1700_B(() -> {
            switch (this.Q_2552_b.ordinal()) {
                case 3: 
                case 4: 
                case 5: 
                case 6: {
                    if (this.k_2293_S == null) break;
                    this.J_1907_R(this.k_2293_S);
                    break;
                }
                case 1: {
                    if (this.C_2741_M == null) break;
                    this.J_1907_R(this.C_2741_M);
                }
            }
        });
    }

    public void n_1700_B(Runnable p_237952_1_) {
        this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.R_4764_Y, new SwitchSlotTask(this.G_564_y.n_1700_B, this.n_1700_B, p_237952_1_)));
    }

    public void J_1907_R(S_4022_R p_224435_1_) {
        this.n_1700_B(null, p_224435_1_, -1, true);
    }

    private void J_1907_R(J_1907_R p_224437_1_) {
        this.n_1700_B(p_224437_1_.n_1700_B, null, p_224437_1_.J_1907_R, p_224437_1_.R_4764_Y);
    }

    private void n_1700_B(@Nullable String p_237953_1_, @Nullable S_4022_R p_237953_2_, int p_237953_3_, boolean p_237953_4_) {
        this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.R_4764_Y, new ResettingWorldTask(p_237953_1_, p_237953_2_, p_237953_3_, p_237953_4_, this.G_564_y.n_1700_B, this.q_2307_F, this.Z_875_P)));
    }

    public void n_1700_B(J_1907_R p_224438_1_) {
        if (this.n_1700_B == -1) {
            this.J_1907_R(p_224438_1_);
        } else {
            this.Q_2552_b = lightning.product.C_3538_G$n_1700_B.J_1907_R;
            this.C_2741_M = p_224438_1_;
            this.n_1700_B();
        }
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] w_1484_f;

        public static n_1700_B[] values() {
            return (n_1700_B[])w_1484_f.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            w_1484_f = lightning.product.C_3538_G$n_1700_B.n_1700_B();
        }
    }

    class R_4764_Y
    extends Button {
        private final g_2336_b J_1907_R;

        public R_4764_Y(int p_i232218_2_, int p_i232218_3_, x_282_a p_i232218_4_, g_2336_b p_i232218_5_, Button.n_1700_B p_i232218_6_) {
            super(p_i232218_2_, p_i232218_3_, 60, 72, p_i232218_4_, p_i232218_6_);
            this.J_1907_R = p_i232218_5_;
        }

        @Override
        public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            C_3538_G.this.n_1700_B(matrixStack, this.x, this.y, this.getMessage(), this.J_1907_R, this.isHovered(), this.isMouseOver(mouseX, mouseY));
        }
    }

    public static class J_1907_R {
        private final String n_1700_B;
        private final int J_1907_R;
        private final boolean R_4764_Y;

        public J_1907_R(String p_i51560_1_, int p_i51560_2_, boolean p_i51560_3_) {
            this.n_1700_B = p_i51560_1_;
            this.J_1907_R = p_i51560_2_;
            this.R_4764_Y = p_i51560_3_;
        }
    }
}


