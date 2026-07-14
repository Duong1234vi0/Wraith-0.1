/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.GuiEventListener;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;

public class RealmsLabel
implements GuiEventListener {
    private final x_282_a n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;

    public RealmsLabel(x_282_a p_i232502_1_, int p_i232502_2_, int p_i232502_3_, int p_i232502_4_) {
        this.n_1700_B = p_i232502_1_;
        this.J_1907_R = p_i232502_2_;
        this.R_4764_Y = p_i232502_3_;
        this.G_564_y = p_i232502_4_;
    }

    public void n_1700_B(k_2603_m p_239560_1_, g_221_o p_239560_2_) {
        k_2603_m.drawCenteredString(p_239560_2_, MinecraftClient.A_4115_X().t_148_a, this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y);
    }

    public String n_1700_B() {
        return this.n_1700_B.getString();
    }
}



