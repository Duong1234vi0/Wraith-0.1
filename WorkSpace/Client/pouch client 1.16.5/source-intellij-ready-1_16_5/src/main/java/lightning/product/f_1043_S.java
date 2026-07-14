/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.O_694_j;
import lightning.product.RealmsWorldOptions;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.W_3464_O;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.AbstractSliderButton;
import lightning.product.u_530_F;
import lightning.product.RealmsLabel;
import lightning.product.x_282_a;

public class f_1043_S
extends RealmsScreen {
    public static final x_282_a[] n_1700_B = new x_282_a[]{new F_2904_S("options.difficulty.peaceful"), new F_2904_S("options.difficulty.easy"), new F_2904_S("options.difficulty.normal"), new F_2904_S("options.difficulty.hard")};
    public static final x_282_a[] J_1907_R = new x_282_a[]{new F_2904_S("selectWorld.gameMode.survival"), new F_2904_S("selectWorld.gameMode.creative"), new F_2904_S("selectWorld.gameMode.adventure")};
    private static final x_282_a G_564_y = new F_2904_S("mco.configure.world.on");
    private static final x_282_a P_1922_E = new F_2904_S("mco.configure.world.off");
    private static final x_282_a u_1723_Y = new F_2904_S("selectWorld.gameMode");
    private static final x_282_a v_4262_N = new F_2904_S("mco.configure.world.edit.slot.name");
    private O_694_j w_1484_f;
    protected final W_3464_O R_4764_Y;
    private int t_148_a;
    private int s_956_w;
    private int u_2550_I;
    private final RealmsWorldOptions M_588_G;
    private final q_1982_R.J_1907_R P_4830_p;
    private final int h_1847_R;
    private int Q_4569_t;
    private int M_182_A;
    private Boolean t_1786_h;
    private Boolean multiplayerClientSuggestionProvider;
    private Boolean w_1457_N;
    private Boolean Y_601_j;
    private Integer Y_259_p;
    private Boolean Q_2552_b;
    private Boolean C_2741_M;
    private Button k_2293_S;
    private Button q_2307_F;
    private Button Z_875_P;
    private Button c_3005_b;
    private n_1700_B H_2857_Y;
    private Button A_4115_X;
    private Button Y_1740_V;
    private RealmsLabel t_4043_B;
    private RealmsLabel x_607_J;

    public f_1043_S(W_3464_O p_i51750_1_, RealmsWorldOptions p_i51750_2_, q_1982_R.J_1907_R p_i51750_3_, int p_i51750_4_) {
        this.R_4764_Y = p_i51750_1_;
        this.M_588_G = p_i51750_2_;
        this.P_4830_p = p_i51750_3_;
        this.h_1847_R = p_i51750_4_;
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public void tick() {
        this.w_1484_f.tick();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.R_4764_Y);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void init() {
        this.s_956_w = 170;
        this.t_148_a = this.width / 2 - this.s_956_w;
        this.u_2550_I = this.width / 2 + 10;
        this.Q_4569_t = this.M_588_G.w_1484_f;
        this.M_182_A = this.M_588_G.t_148_a;
        if (this.P_4830_p == q_1982_R.J_1907_R.n_1700_B) {
            this.t_1786_h = this.M_588_G.n_1700_B;
            this.Y_259_p = this.M_588_G.P_1922_E;
            this.C_2741_M = this.M_588_G.v_4262_N;
            this.w_1457_N = this.M_588_G.J_1907_R;
            this.Y_601_j = this.M_588_G.R_4764_Y;
            this.multiplayerClientSuggestionProvider = this.M_588_G.G_564_y;
            this.Q_2552_b = this.M_588_G.u_1723_Y;
        } else {
            F_2904_S itextcomponent = this.P_4830_p == q_1982_R.J_1907_R.R_4764_Y ? new F_2904_S("mco.configure.world.edit.subscreen.adventuremap") : (this.P_4830_p == q_1982_R.J_1907_R.P_1922_E ? new F_2904_S("mco.configure.world.edit.subscreen.inspiration") : new F_2904_S("mco.configure.world.edit.subscreen.experience"));
            this.x_607_J = new RealmsLabel(itextcomponent, this.width / 2, 26, 0xFF0000);
            this.t_1786_h = true;
            this.Y_259_p = 0;
            this.C_2741_M = false;
            this.w_1457_N = true;
            this.Y_601_j = true;
            this.multiplayerClientSuggestionProvider = true;
            this.Q_2552_b = true;
        }
        this.w_1484_f = new O_694_j(this.minecraft.t_148_a, this.t_148_a + 2, f_1043_S.G_564_y(1), this.s_956_w - 4, 20, null, new F_2904_S("mco.configure.world.edit.slot.name"));
        this.w_1484_f.setMaxStringLength(10);
        this.w_1484_f.setText(this.M_588_G.n_1700_B(this.h_1847_R));
        this.J_1907_R(this.w_1484_f);
        this.k_2293_S = this.addButton(new Button(this.u_2550_I, f_1043_S.G_564_y(1), this.s_956_w, 20, this.R_4764_Y(), p_238059_1_ -> {
            this.t_1786_h = this.t_1786_h == false;
            p_238059_1_.setMessage(this.R_4764_Y());
        }));
        this.addButton(new Button(this.t_148_a, f_1043_S.G_564_y(3), this.s_956_w, 20, this.J_1907_R(), p_238057_1_ -> {
            this.M_182_A = (this.M_182_A + 1) % J_1907_R.length;
            p_238057_1_.setMessage(this.J_1907_R());
        }));
        this.q_2307_F = this.addButton(new Button(this.u_2550_I, f_1043_S.G_564_y(3), this.s_956_w, 20, this.G_564_y(), p_238056_1_ -> {
            this.w_1457_N = this.w_1457_N == false;
            p_238056_1_.setMessage(this.G_564_y());
        }));
        this.addButton(new Button(this.t_148_a, f_1043_S.G_564_y(5), this.s_956_w, 20, this.n_1700_B(), p_238055_1_ -> {
            this.Q_4569_t = (this.Q_4569_t + 1) % n_1700_B.length;
            p_238055_1_.setMessage(this.n_1700_B());
            if (this.P_4830_p == q_1982_R.J_1907_R.n_1700_B) {
                this.Z_875_P.active = this.Q_4569_t != 0;
                this.Z_875_P.setMessage(this.u_1723_Y());
            }
        }));
        this.Z_875_P = this.addButton(new Button(this.u_2550_I, f_1043_S.G_564_y(5), this.s_956_w, 20, this.u_1723_Y(), p_238053_1_ -> {
            this.Y_601_j = this.Y_601_j == false;
            p_238053_1_.setMessage(this.u_1723_Y());
        }));
        this.H_2857_Y = this.addButton(new n_1700_B(this.t_148_a, f_1043_S.G_564_y(7), this.s_956_w, this.Y_259_p, 0.0f, 16.0f));
        this.c_3005_b = this.addButton(new Button(this.u_2550_I, f_1043_S.G_564_y(7), this.s_956_w, 20, this.v_4262_N(), p_238052_1_ -> {
            this.multiplayerClientSuggestionProvider = this.multiplayerClientSuggestionProvider == false;
            p_238052_1_.setMessage(this.v_4262_N());
        }));
        this.Y_1740_V = this.addButton(new Button(this.t_148_a, f_1043_S.G_564_y(9), this.s_956_w, 20, this.t_148_a(), p_238051_1_ -> {
            this.C_2741_M = this.C_2741_M == false;
            p_238051_1_.setMessage(this.t_148_a());
        }));
        this.A_4115_X = this.addButton(new Button(this.u_2550_I, f_1043_S.G_564_y(9), this.s_956_w, 20, this.w_1484_f(), p_238049_1_ -> {
            this.Q_2552_b = this.Q_2552_b == false;
            p_238049_1_.setMessage(this.w_1484_f());
        }));
        if (this.P_4830_p != q_1982_R.J_1907_R.n_1700_B) {
            this.k_2293_S.active = false;
            this.q_2307_F.active = false;
            this.c_3005_b.active = false;
            this.Z_875_P.active = false;
            this.H_2857_Y.active = false;
            this.A_4115_X.active = false;
            this.Y_1740_V.active = false;
        }
        if (this.Q_4569_t == 0) {
            this.Z_875_P.active = false;
        }
        this.addButton(new Button(this.t_148_a, f_1043_S.G_564_y(13), this.s_956_w, 20, new F_2904_S("mco.configure.world.buttons.done"), p_238048_1_ -> this.u_2550_I()));
        this.addButton(new Button(this.u_2550_I, f_1043_S.G_564_y(13), this.s_956_w, 20, CommonComponents.G_564_y, p_238046_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
        this.addListener(this.w_1484_f);
        this.t_4043_B = this.addListener(new RealmsLabel(new F_2904_S("mco.configure.world.buttons.options"), this.width / 2, 17, 0xFFFFFF));
        if (this.x_607_J != null) {
            this.addListener(this.x_607_J);
        }
        this.P_1922_E();
    }

    private x_282_a n_1700_B() {
        return new F_2904_S("options.difficulty").n_1700_B(": ").n_1700_B(n_1700_B[this.Q_4569_t]);
    }

    private x_282_a J_1907_R() {
        return new F_2904_S("options.generic_value", u_1723_Y, J_1907_R[this.M_182_A]);
    }

    private x_282_a R_4764_Y() {
        return new F_2904_S("mco.configure.world.pvp").n_1700_B(": ").n_1700_B(f_1043_S.n_1700_B(this.t_1786_h));
    }

    private x_282_a G_564_y() {
        return new F_2904_S("mco.configure.world.spawnAnimals").n_1700_B(": ").n_1700_B(f_1043_S.n_1700_B(this.w_1457_N));
    }

    private x_282_a u_1723_Y() {
        return this.Q_4569_t == 0 ? new F_2904_S("mco.configure.world.spawnMonsters").n_1700_B(": ").n_1700_B(new F_2904_S("mco.configure.world.off")) : new F_2904_S("mco.configure.world.spawnMonsters").n_1700_B(": ").n_1700_B(f_1043_S.n_1700_B(this.Y_601_j));
    }

    private x_282_a v_4262_N() {
        return new F_2904_S("mco.configure.world.spawnNPCs").n_1700_B(": ").n_1700_B(f_1043_S.n_1700_B(this.multiplayerClientSuggestionProvider));
    }

    private x_282_a w_1484_f() {
        return new F_2904_S("mco.configure.world.commandBlocks").n_1700_B(": ").n_1700_B(f_1043_S.n_1700_B(this.Q_2552_b));
    }

    private x_282_a t_148_a() {
        return new F_2904_S("mco.configure.world.forceGameMode").n_1700_B(": ").n_1700_B(f_1043_S.n_1700_B(this.C_2741_M));
    }

    private static x_282_a n_1700_B(boolean p_238050_0_) {
        return p_238050_0_ ? G_564_y : P_1922_E;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.font.J_1907_R(matrixStack, v_4262_N, (float)(this.t_148_a + this.s_956_w / 2 - this.font.n_1700_B((FormattedText)v_4262_N) / 2), (float)(f_1043_S.G_564_y(0) - 5), 0xFFFFFF);
        this.t_4043_B.n_1700_B(this, matrixStack);
        if (this.x_607_J != null) {
            this.x_607_J.n_1700_B(this, matrixStack);
        }
        this.w_1484_f.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private String s_956_w() {
        return this.w_1484_f.getText().equals(this.M_588_G.J_1907_R(this.h_1847_R)) ? "" : this.w_1484_f.getText();
    }

    private void u_2550_I() {
        if (this.P_4830_p != q_1982_R.J_1907_R.R_4764_Y && this.P_4830_p != q_1982_R.J_1907_R.G_564_y && this.P_4830_p != q_1982_R.J_1907_R.P_1922_E) {
            this.R_4764_Y.n_1700_B(new RealmsWorldOptions(this.t_1786_h, this.w_1457_N, this.Y_601_j, this.multiplayerClientSuggestionProvider, this.Y_259_p, this.Q_2552_b, this.Q_4569_t, this.M_182_A, this.C_2741_M, this.s_956_w()));
        } else {
            this.R_4764_Y.n_1700_B(new RealmsWorldOptions(this.M_588_G.n_1700_B, this.M_588_G.J_1907_R, this.M_588_G.R_4764_Y, this.M_588_G.G_564_y, this.M_588_G.P_1922_E, this.M_588_G.u_1723_Y, this.Q_4569_t, this.M_182_A, this.M_588_G.v_4262_N, this.s_956_w()));
        }
    }

    class n_1700_B
    extends AbstractSliderButton {
        private final double J_1907_R;
        private final double R_4764_Y;

        public n_1700_B(int p_i232222_2_, int p_i232222_3_, int p_i232222_4_, int p_i232222_5_, float p_i232222_6_, float p_i232222_7_) {
            super(p_i232222_2_, p_i232222_3_, p_i232222_4_, 20, U_2871_b.R_4764_Y, 0.0);
            this.J_1907_R = p_i232222_6_;
            this.R_4764_Y = p_i232222_7_;
            this.sliderValue = (u_530_F.n_1700_B((float)p_i232222_5_, p_i232222_6_, p_i232222_7_) - p_i232222_6_) / (p_i232222_7_ - p_i232222_6_);
            this.func_230979_b_();
        }

        @Override
        public void func_230972_a_() {
            if (f_1043_S.this.H_2857_Y.active) {
                f_1043_S.this.Y_259_p = (int)u_530_F.G_564_y(u_530_F.n_1700_B(this.sliderValue, 0.0, 1.0), this.J_1907_R, this.R_4764_Y);
            }
        }

        @Override
        protected void func_230979_b_() {
            this.setMessage(new F_2904_S("mco.configure.world.spawnProtection").n_1700_B(": ").n_1700_B(f_1043_S.this.Y_259_p == 0 ? new F_2904_S("mco.configure.world.off") : new U_2871_b(String.valueOf(f_1043_S.this.Y_259_p))));
        }

        @Override
        public void onClick(double mouseX, double mouseY) {
        }

        @Override
        public void onRelease(double mouseX, double mouseY) {
        }
    }
}


