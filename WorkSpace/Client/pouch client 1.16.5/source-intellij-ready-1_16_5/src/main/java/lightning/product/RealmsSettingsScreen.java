/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.O_694_j;
import lightning.product.Button;
import lightning.product.W_3464_O;
import lightning.product.RealmsLongConfirmationScreen;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.q_1982_R;
import lightning.product.CommonComponents;
import lightning.product.RealmsLabel;
import lightning.product.x_282_a;

public class RealmsSettingsScreen
extends RealmsScreen {
    private static final x_282_a n_1700_B = new F_2904_S("mco.configure.world.name");
    private static final x_282_a J_1907_R = new F_2904_S("mco.configure.world.description");
    private final W_3464_O R_4764_Y;
    private final q_1982_R G_564_y;
    private Button P_1922_E;
    private O_694_j u_1723_Y;
    private O_694_j v_4262_N;
    private RealmsLabel w_1484_f;

    public RealmsSettingsScreen(W_3464_O p_i51751_1_, q_1982_R p_i51751_2_) {
        this.R_4764_Y = p_i51751_1_;
        this.G_564_y = p_i51751_2_;
    }

    @Override
    public void tick() {
        this.v_4262_N.tick();
        this.u_1723_Y.tick();
        this.P_1922_E.active = !this.v_4262_N.getText().trim().isEmpty();
    }

    @Override
    public void init() {
        this.minecraft.Q_4569_t.n_1700_B(true);
        int i = this.width / 2 - 106;
        this.P_1922_E = this.addButton(new Button(i - 2, RealmsSettingsScreen.G_564_y(12), 106, 20, new F_2904_S("mco.configure.world.buttons.done"), p_238033_1_ -> this.n_1700_B()));
        this.addButton(new Button(this.width / 2 + 2, RealmsSettingsScreen.G_564_y(12), 106, 20, CommonComponents.G_564_y, p_238032_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
        String s = this.G_564_y.P_1922_E == q_1982_R.R_4764_Y.J_1907_R ? "mco.configure.world.buttons.close" : "mco.configure.world.buttons.open";
        Button button = new Button(this.width / 2 - 53, RealmsSettingsScreen.G_564_y(0), 106, 20, new F_2904_S(s), p_238031_1_ -> {
            if (this.G_564_y.P_1922_E == q_1982_R.R_4764_Y.J_1907_R) {
                F_2904_S itextcomponent = new F_2904_S("mco.configure.world.close.question.line1");
                F_2904_S itextcomponent1 = new F_2904_S("mco.configure.world.close.question.line2");
                this.minecraft.n_1700_B(new RealmsLongConfirmationScreen(p_238034_1_ -> {
                    if (p_238034_1_) {
                        this.R_4764_Y.n_1700_B(this);
                    } else {
                        this.minecraft.n_1700_B(this);
                    }
                }, RealmsLongConfirmationScreen.n_1700_B.J_1907_R, itextcomponent, itextcomponent1, true));
            } else {
                this.R_4764_Y.n_1700_B(false, this);
            }
        });
        this.addButton(button);
        this.v_4262_N = new O_694_j(this.minecraft.t_148_a, i, RealmsSettingsScreen.G_564_y(4), 212, 20, null, new F_2904_S("mco.configure.world.name"));
        this.v_4262_N.setMaxStringLength(32);
        this.v_4262_N.setText(this.G_564_y.J_1907_R());
        this.addListener(this.v_4262_N);
        this.J_1907_R(this.v_4262_N);
        this.u_1723_Y = new O_694_j(this.minecraft.t_148_a, i, RealmsSettingsScreen.G_564_y(8), 212, 20, null, new F_2904_S("mco.configure.world.description"));
        this.u_1723_Y.setMaxStringLength(32);
        this.u_1723_Y.setText(this.G_564_y.n_1700_B());
        this.addListener(this.u_1723_Y);
        this.w_1484_f = this.addListener(new RealmsLabel(new F_2904_S("mco.configure.world.settings.title"), this.width / 2, 17, 0xFFFFFF));
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
        this.w_1484_f.n_1700_B(this, matrixStack);
        this.font.J_1907_R(matrixStack, n_1700_B, (float)(this.width / 2 - 106), (float)RealmsSettingsScreen.G_564_y(3), 0xA0A0A0);
        this.font.J_1907_R(matrixStack, J_1907_R, (float)(this.width / 2 - 106), (float)RealmsSettingsScreen.G_564_y(7), 0xA0A0A0);
        this.v_4262_N.render(matrixStack, mouseX, mouseY, partialTicks);
        this.u_1723_Y.render(matrixStack, mouseX, mouseY, partialTicks);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public void n_1700_B() {
        this.R_4764_Y.n_1700_B(this.v_4262_N.getText(), this.u_1723_Y.getText());
    }
}


