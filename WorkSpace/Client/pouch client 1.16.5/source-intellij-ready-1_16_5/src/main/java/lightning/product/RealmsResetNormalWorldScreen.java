/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_3538_G;
import lightning.product.F_2904_S;
import lightning.product.O_694_j;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.CommonComponents;
import lightning.product.RealmsLabel;
import lightning.product.x_282_a;

public class RealmsResetNormalWorldScreen
extends RealmsScreen {
    private static final x_282_a n_1700_B = new F_2904_S("mco.reset.world.seed");
    private static final x_282_a[] J_1907_R = new x_282_a[]{new F_2904_S("generator.default"), new F_2904_S("generator.flat"), new F_2904_S("generator.large_biomes"), new F_2904_S("generator.amplified")};
    private final C_3538_G R_4764_Y;
    private RealmsLabel G_564_y;
    private O_694_j P_1922_E;
    private Boolean u_1723_Y = true;
    private Integer v_4262_N = 0;
    private x_282_a w_1484_f;

    public RealmsResetNormalWorldScreen(C_3538_G p_i232214_1_, x_282_a p_i232214_2_) {
        this.R_4764_Y = p_i232214_1_;
        this.w_1484_f = p_i232214_2_;
    }

    @Override
    public void tick() {
        this.P_1922_E.tick();
        super.tick();
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.G_564_y = new RealmsLabel(new F_2904_S("mco.reset.world.generate"), this.width / 2, 17, 0xFFFFFF);
        this.addListener(this.G_564_y);
        this.P_1922_E = new O_694_j(this.minecraft.t_148_a, this.width / 2 - 100, RealmsResetNormalWorldScreen.G_564_y(2), 200, 20, null, new F_2904_S("mco.reset.world.seed"));
        this.P_1922_E.setMaxStringLength(32);
        this.addListener(this.P_1922_E);
        this.n_1700_B(this.P_1922_E);
        this.addButton(new Button(this.width / 2 - 102, RealmsResetNormalWorldScreen.G_564_y(4), 205, 20, this.n_1700_B(), p_237936_1_ -> {
            this.v_4262_N = (this.v_4262_N + 1) % J_1907_R.length;
            p_237936_1_.setMessage(this.n_1700_B());
        }));
        this.addButton(new Button(this.width / 2 - 102, RealmsResetNormalWorldScreen.G_564_y(6) - 2, 205, 20, this.J_1907_R(), p_237935_1_ -> {
            this.u_1723_Y = this.u_1723_Y == false;
            p_237935_1_.setMessage(this.J_1907_R());
        }));
        this.addButton(new Button(this.width / 2 - 102, RealmsResetNormalWorldScreen.G_564_y(12), 97, 20, this.w_1484_f, p_237934_1_ -> this.R_4764_Y.n_1700_B(new C_3538_G.J_1907_R(this.P_1922_E.getText(), this.v_4262_N, this.u_1723_Y))));
        this.addButton(new Button(this.width / 2 + 8, RealmsResetNormalWorldScreen.G_564_y(12), 97, 20, CommonComponents.w_1484_f, p_237933_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
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

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.G_564_y.n_1700_B(this, matrixStack);
        this.font.J_1907_R(matrixStack, n_1700_B, (float)(this.width / 2 - 100), (float)RealmsResetNormalWorldScreen.G_564_y(1), 0xA0A0A0);
        this.P_1922_E.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    private x_282_a n_1700_B() {
        return new F_2904_S("selectWorld.mapType").n_1700_B(" ").n_1700_B(J_1907_R[this.v_4262_N]);
    }

    private x_282_a J_1907_R() {
        return CommonComponents.n_1700_B(new F_2904_S("selectWorld.mapFeatures"), this.u_1723_Y);
    }
}


