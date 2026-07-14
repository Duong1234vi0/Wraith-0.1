/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FormattedText;
import lightning.product.N_2445_q;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.x_282_a;

public class n_633_r
extends RealmsScreen {
    private final x_282_a n_1700_B;
    private final x_282_a J_1907_R;
    private N_2445_q R_4764_Y = N_2445_q.n_1700_B;
    private final k_2603_m G_564_y;
    private int P_1922_E;

    public n_633_r(k_2603_m p_i242069_1_, x_282_a p_i242069_2_, x_282_a p_i242069_3_) {
        this.G_564_y = p_i242069_1_;
        this.n_1700_B = p_i242069_2_;
        this.J_1907_R = p_i242069_3_;
    }

    @Override
    public void init() {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        minecraft.R_4764_Y(false);
        minecraft.z_4693_k().J_1907_R();
        NarrationHelper.n_1700_B(this.n_1700_B.getString() + ": " + this.J_1907_R.getString());
        this.R_4764_Y = N_2445_q.n_1700_B(this.font, (FormattedText)this.J_1907_R, this.width - 50);
        this.P_1922_E = this.R_4764_Y.n_1700_B() * 9;
        this.addButton(new Button(this.width / 2 - 100, this.height / 2 + this.P_1922_E / 2 + 9, 200, 20, CommonComponents.w_1484_f, p_239547_2_ -> minecraft.n_1700_B(this.G_564_y)));
    }

    @Override
    public void closeScreen() {
        MinecraftClient.A_4115_X().n_1700_B(this.G_564_y);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        n_633_r.drawCenteredString(matrixStack, this.font, this.n_1700_B, this.width / 2, this.height / 2 - this.P_1922_E / 2 - 18, 0xAAAAAA);
        this.R_4764_Y.n_1700_B(matrixStack, this.width / 2, this.height / 2 - this.P_1922_E / 2);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}



