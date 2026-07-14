/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import lightning.product.C_3240_x;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.H_3330_w;
import lightning.product.I_1084_e;
import lightning.product.I_14_v;
import lightning.product.Q_4113_P;
import lightning.product.V_2511_L;
import lightning.product.Z_1993_T;
import lightning.product.ServerboundPlayerAbilitiesPacket;
import lightning.product.a_3742_W;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_2603_m;
import lightning.product.Items;
import lightning.product.x_282_a;

public class O_1487_n
extends k_2603_m {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/container/gamemode_switcher.png");
    private static final int J_1907_R = lightning.product.O_1487_n$n_1700_B.values().length * 30 - 5;
    private static final x_282_a R_4764_Y = new F_2904_S("debug.gamemodes.select_next", new F_2904_S("debug.gamemodes.press_f4").n_1700_B(D_4024_W.M_588_G));
    private final Optional<n_1700_B> G_564_y;
    private Optional<n_1700_B> P_1922_E = Optional.empty();
    private int u_1723_Y;
    private int v_4262_N;
    private boolean w_1484_f;
    private final List<J_1907_R> t_148_a = Lists.newArrayList();

    public O_1487_n() {
        super(I_1084_e.n_1700_B);
        this.G_564_y = lightning.product.O_1487_n$n_1700_B.n_1700_B(this.n_1700_B());
    }

    private I_14_v n_1700_B() {
        I_14_v gametype = MinecraftClient.A_4115_X().w_1457_N.getCurrentGameType();
        I_14_v gametype1 = MinecraftClient.A_4115_X().w_1457_N.func_241822_k();
        if (gametype1 == I_14_v.n_1700_B) {
            gametype1 = gametype == I_14_v.R_4764_Y ? I_14_v.J_1907_R : I_14_v.R_4764_Y;
        }
        return gametype1;
    }

    @Override
    protected void init() {
        super.init();
        this.P_1922_E = this.G_564_y.isPresent() ? this.G_564_y : lightning.product.O_1487_n$n_1700_B.n_1700_B(this.minecraft.w_1457_N.getCurrentGameType());
        for (int i = 0; i < lightning.product.O_1487_n$n_1700_B.P_1922_E.length; ++i) {
            n_1700_B gamemodeselectionscreen$mode = lightning.product.O_1487_n$n_1700_B.P_1922_E[i];
            this.t_148_a.add(new J_1907_R(gamemodeselectionscreen$mode, this.width / 2 - J_1907_R / 2 + i * 30, this.height / 2 - 30));
        }
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (!this.R_4764_Y()) {
            matrixStack.n_1700_B();
            c_4037_x.Y_601_j();
            this.minecraft.G_624_v().n_1700_B(n_1700_B);
            int i = this.width / 2 - 62;
            int j = this.height / 2 - 30 - 27;
            O_1487_n.blit(matrixStack, i, j, 0.0f, 0.0f, 125, 75, 128, 128);
            matrixStack.J_1907_R();
            super.render(matrixStack, mouseX, mouseY, partialTicks);
            this.P_1922_E.ifPresent(p_238712_2_ -> O_1487_n.drawCenteredString(matrixStack, this.font, p_238712_2_.n_1700_B(), this.width / 2, this.height / 2 - 30 - 20, -1));
            O_1487_n.drawCenteredString(matrixStack, this.font, R_4764_Y, this.width / 2, this.height / 2 + 5, 0xFFFFFF);
            if (!this.w_1484_f) {
                this.u_1723_Y = mouseX;
                this.v_4262_N = mouseY;
                this.w_1484_f = true;
            }
            boolean flag = this.u_1723_Y == mouseX && this.v_4262_N == mouseY;
            for (J_1907_R gamemodeselectionscreen$selectorwidget : this.t_148_a) {
                gamemodeselectionscreen$selectorwidget.render(matrixStack, mouseX, mouseY, partialTicks);
                this.P_1922_E.ifPresent(p_238714_1_ -> gamemodeselectionscreen$selectorwidget.n_1700_B(p_238714_1_ == gamemodeselectionscreen$selectorwidget.J_1907_R));
                if (flag || !gamemodeselectionscreen$selectorwidget.isHovered()) continue;
                this.P_1922_E = Optional.of(gamemodeselectionscreen$selectorwidget.J_1907_R);
            }
        }
    }

    private void J_1907_R() {
        O_1487_n.n_1700_B(this.minecraft, this.P_1922_E);
    }

    private static void n_1700_B(MinecraftClient p_238713_0_, Optional<n_1700_B> p_238713_1_) {
        if (p_238713_0_.w_1457_N != null && p_238713_0_.Y_259_p != null && p_238713_1_.isPresent()) {
            Optional<n_1700_B> optional = lightning.product.O_1487_n$n_1700_B.n_1700_B(p_238713_0_.w_1457_N.getCurrentGameType());
            n_1700_B gamemodeselectionscreen$mode = p_238713_1_.get();
            if (optional.isPresent() && gamemodeselectionscreen$mode != optional.get()) {
                I_14_v targetType = gamemodeselectionscreen$mode.R_4764_Y();
                p_238713_0_.w_1457_N.setGameType(targetType);
                p_238713_0_.Y_259_p.n_1700_B.n_1700_B(new ServerboundPlayerAbilitiesPacket(p_238713_0_.Y_259_p.C_415_h));
            }
        }
    }

    private boolean R_4764_Y() {
        if (!Q_4113_P.n_1700_B(this.minecraft.RealmsServerPing().t_148_a(), 292)) {
            this.J_1907_R();
            this.minecraft.n_1700_B((k_2603_m)null);
            return true;
        }
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 293 && this.P_1922_E.isPresent()) {
            this.w_1484_f = false;
            this.P_1922_E = this.P_1922_E.get().G_564_y();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(new F_2904_S("gameMode.creative"), "/gmc", new Z_1993_T(a_3742_W.t_148_a), I_14_v.R_4764_Y);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(new F_2904_S("gameMode.survival"), "/gms", new Z_1993_T(Items.w_2152_d), I_14_v.J_1907_R);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(new F_2904_S("gameMode.adventure"), "/gma", new Z_1993_T(Items.S_1431_H), I_14_v.G_564_y);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(new F_2904_S("gameMode.spectator"), "/gmsp", new Z_1993_T(Items.V_1824_v), I_14_v.P_1922_E);
        protected static final n_1700_B[] P_1922_E;
        final x_282_a u_1723_Y;
        final String v_4262_N;
        final Z_1993_T w_1484_f;
        final I_14_v t_148_a;
        private static final /* synthetic */ n_1700_B[] s_956_w;

        public static n_1700_B[] values() {
            return (n_1700_B[])s_956_w.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(x_282_a p_i232285_3_, String p_i232285_4_, Z_1993_T p_i232285_5_, I_14_v gameType) {
            this.u_1723_Y = p_i232285_3_;
            this.v_4262_N = p_i232285_4_;
            this.w_1484_f = p_i232285_5_;
            this.t_148_a = gameType;
        }

        private void n_1700_B(H_3330_w p_238729_1_, int p_238729_2_, int p_238729_3_) {
            p_238729_1_.J_1907_R(this.w_1484_f, p_238729_2_, p_238729_3_);
        }

        private x_282_a n_1700_B() {
            return this.u_1723_Y;
        }

        private String J_1907_R() {
            return this.v_4262_N;
        }

        private I_14_v R_4764_Y() {
            return this.t_148_a;
        }

        private Optional<n_1700_B> G_564_y() {
            switch (this.ordinal()) {
                case 0: {
                    return Optional.of(J_1907_R);
                }
                case 1: {
                    return Optional.of(R_4764_Y);
                }
                case 2: {
                    return Optional.of(G_564_y);
                }
            }
            return Optional.of(n_1700_B);
        }

        private static Optional<n_1700_B> n_1700_B(I_14_v p_238731_0_) {
            switch (p_238731_0_) {
                case P_1922_E: {
                    return Optional.of(G_564_y);
                }
                case J_1907_R: {
                    return Optional.of(J_1907_R);
                }
                case R_4764_Y: {
                    return Optional.of(n_1700_B);
                }
                case G_564_y: {
                    return Optional.of(R_4764_Y);
                }
            }
            return Optional.empty();
        }

        private static /* synthetic */ n_1700_B[] P_1922_E() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            s_956_w = lightning.product.O_1487_n$n_1700_B.P_1922_E();
            P_1922_E = lightning.product.O_1487_n$n_1700_B.values();
        }
    }

    public class J_1907_R
    extends V_2511_L {
        private final n_1700_B J_1907_R;
        private boolean R_4764_Y;

        public J_1907_R(n_1700_B p_i232286_2_, int p_i232286_3_, int p_i232286_4_) {
            super(p_i232286_3_, p_i232286_4_, 25, 25, p_i232286_2_.n_1700_B());
            this.J_1907_R = p_i232286_2_;
        }

        @Override
        public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
            MinecraftClient minecraft = MinecraftClient.A_4115_X();
            this.n_1700_B(matrixStack, minecraft.G_624_v());
            this.J_1907_R.n_1700_B(O_1487_n.this.itemRenderer, this.x + 5, this.y + 5);
            if (this.R_4764_Y) {
                this.J_1907_R(matrixStack, minecraft.G_624_v());
            }
        }

        @Override
        public boolean isHovered() {
            return super.isHovered() || this.R_4764_Y;
        }

        public void n_1700_B(boolean p_238741_1_) {
            this.R_4764_Y = p_238741_1_;
            this.narrate();
        }

        private void n_1700_B(g_221_o p_238738_1_, C_3240_x p_238738_2_) {
            p_238738_2_.n_1700_B(n_1700_B);
            p_238738_1_.n_1700_B();
            p_238738_1_.n_1700_B((double)this.x, (double)this.y, 0.0);
            lightning.product.O_1487_n$J_1907_R.blit(p_238738_1_, 0, 0, 0.0f, 75.0f, 25, 25, 128, 128);
            p_238738_1_.J_1907_R();
        }

        private void J_1907_R(g_221_o p_238740_1_, C_3240_x p_238740_2_) {
            p_238740_2_.n_1700_B(n_1700_B);
            p_238740_1_.n_1700_B();
            p_238740_1_.n_1700_B((double)this.x, (double)this.y, 0.0);
            lightning.product.O_1487_n$J_1907_R.blit(p_238740_1_, 0, 0, 25.0f, 75.0f, 25, 25, 128, 128);
            p_238740_1_.J_1907_R();
        }
    }
}



