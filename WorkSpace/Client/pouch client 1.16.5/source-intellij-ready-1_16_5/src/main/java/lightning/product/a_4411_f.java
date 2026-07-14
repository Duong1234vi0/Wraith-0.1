/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.N_2445_q;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;

public class a_4411_f
extends k_2603_m {
    private final x_282_a n_1700_B;
    private N_2445_q J_1907_R = N_2445_q.n_1700_B;
    private final k_2603_m R_4764_Y;
    private int G_564_y;

    public a_4411_f(k_2603_m p_i242056_1_, x_282_a p_i242056_2_, x_282_a p_i242056_3_) {
        super(p_i242056_2_);
        this.R_4764_Y = p_i242056_1_;
        this.n_1700_B = p_i242056_3_;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    protected void init() {
        this.J_1907_R = N_2445_q.n_1700_B(this.font, (FormattedText)this.n_1700_B, this.width - 50);
        this.G_564_y = this.J_1907_R.n_1700_B() * 9;
        this.addButton(new Button(this.width / 2 - 100, Math.min(this.height / 2 + this.G_564_y / 2 + 9, this.height - 30), 200, 20, new F_2904_S("gui.toMenu"), p_213033_1_ -> this.minecraft.n_1700_B(this.R_4764_Y)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        a_4411_f.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, this.height / 2 - this.G_564_y / 2 - 18, 0xAAAAAA);
        this.J_1907_R.n_1700_B(matrixStack, this.width / 2, this.height / 2 - this.G_564_y / 2);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


