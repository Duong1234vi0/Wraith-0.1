/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_3538_G;
import lightning.product.F_2904_S;
import lightning.product.RealmsLongRunningMcoTaskScreen;
import lightning.product.O_694_j;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.r_715_M;
import lightning.product.RealmsLabel;
import lightning.product.x_282_a;
import lightning.product.WorldCreationTask;

public class RealmsCreateRealmScreen
extends RealmsScreen {
    private static final x_282_a n_1700_B = new F_2904_S("mco.configure.world.name");
    private static final x_282_a J_1907_R = new F_2904_S("mco.configure.world.description");
    private final q_1982_R R_4764_Y;
    private final r_715_M G_564_y;
    private O_694_j P_1922_E;
    private O_694_j u_1723_Y;
    private Button v_4262_N;
    private RealmsLabel w_1484_f;

    public RealmsCreateRealmScreen(q_1982_R p_i51772_1_, r_715_M p_i51772_2_) {
        this.R_4764_Y = p_i51772_1_;
        this.G_564_y = p_i51772_2_;
    }

    @Override
    public void tick() {
        if (this.P_1922_E != null) {
            this.P_1922_E.tick();
        }
        if (this.u_1723_Y != null) {
            this.u_1723_Y.tick();
        }
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        this.v_4262_N = this.addButton(new Button(this.width / 2 - 100, this.height / 4 + 120 + 17, 97, 20, new F_2904_S("mco.create.world"), p_237828_1_ -> this.n_1700_B()));
        this.addButton(new Button(this.width / 2 + 5, this.height / 4 + 120 + 17, 95, 20, CommonComponents.G_564_y, p_237827_1_ -> this.minecraft.n_1700_B(this.G_564_y)));
        this.v_4262_N.active = false;
        this.P_1922_E = new O_694_j(this.minecraft.t_148_a, this.width / 2 - 100, 65, 200, 20, null, new F_2904_S("mco.configure.world.name"));
        this.addListener(this.P_1922_E);
        this.n_1700_B(this.P_1922_E);
        this.u_1723_Y = new O_694_j(this.minecraft.t_148_a, this.width / 2 - 100, 115, 200, 20, null, new F_2904_S("mco.configure.world.description"));
        this.addListener(this.u_1723_Y);
        this.w_1484_f = new RealmsLabel(new F_2904_S("mco.selectServer.create"), this.width / 2, 11, 0xFFFFFF);
        this.addListener(this.w_1484_f);
        this.P_1922_E();
    }

    @Override
    public void onClose() {
        this.minecraft.Q_4569_t.n_1700_B(false);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        boolean flag = super.charTyped(codePoint, modifiers);
        this.v_4262_N.active = this.J_1907_R();
        return flag;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.minecraft.n_1700_B(this.G_564_y);
            return true;
        }
        boolean flag = super.keyPressed(keyCode, scanCode, modifiers);
        this.v_4262_N.active = this.J_1907_R();
        return flag;
    }

    private void n_1700_B() {
        if (this.J_1907_R()) {
            C_3538_G realmsresetworldscreen = new C_3538_G(this.G_564_y, this.R_4764_Y, new F_2904_S("mco.selectServer.create"), new F_2904_S("mco.create.world.subtitle"), 0xA0A0A0, new F_2904_S("mco.create.world.skip"), () -> this.minecraft.n_1700_B(this.G_564_y.G_564_y()), () -> this.minecraft.n_1700_B(this.G_564_y.G_564_y()));
            realmsresetworldscreen.n_1700_B(new F_2904_S("mco.create.world.reset.title"));
            this.minecraft.n_1700_B(new RealmsLongRunningMcoTaskScreen(this.G_564_y, new WorldCreationTask(this.R_4764_Y.n_1700_B, this.P_1922_E.getText(), this.u_1723_Y.getText(), realmsresetworldscreen)));
        }
    }

    private boolean J_1907_R() {
        return !this.P_1922_E.getText().trim().isEmpty();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.w_1484_f.n_1700_B(this, matrixStack);
        this.font.J_1907_R(matrixStack, n_1700_B, (float)(this.width / 2 - 100), 52.0f, 0xA0A0A0);
        this.font.J_1907_R(matrixStack, J_1907_R, (float)(this.width / 2 - 100), 102.0f, 0xA0A0A0);
        if (this.P_1922_E != null) {
            this.P_1922_E.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        if (this.u_1723_Y != null) {
            this.u_1723_Y.render(matrixStack, mouseX, mouseY, partialTicks);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


