/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.K_1289_S;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.k_2603_m;
import lightning.product.NarrationHelper;
import lightning.product.u_744_e;
import lightning.product.x_282_a;

public class w_728_N
extends RealmsScreen {
    private final k_2603_m n_1700_B;
    private x_282_a J_1907_R;
    private x_282_a R_4764_Y;

    public w_728_N(u_744_e p_i232204_1_, k_2603_m p_i232204_2_) {
        this.n_1700_B = p_i232204_2_;
        this.n_1700_B(p_i232204_1_);
    }

    public w_728_N(x_282_a p_i232205_1_, k_2603_m p_i232205_2_) {
        this.n_1700_B = p_i232205_2_;
        this.n_1700_B(p_i232205_1_);
    }

    public w_728_N(x_282_a p_i232206_1_, x_282_a p_i232206_2_, k_2603_m p_i232206_3_) {
        this.n_1700_B = p_i232206_3_;
        this.n_1700_B(p_i232206_1_, p_i232206_2_);
    }

    private void n_1700_B(u_744_e p_224224_1_) {
        if (p_224224_1_.R_4764_Y == -1) {
            this.J_1907_R = new U_2871_b("An error occurred (" + p_224224_1_.n_1700_B + "):");
            this.R_4764_Y = new U_2871_b(p_224224_1_.J_1907_R);
        } else {
            this.J_1907_R = new U_2871_b("Realms (" + p_224224_1_.R_4764_Y + "):");
            String s = "mco.errorMessage." + p_224224_1_.R_4764_Y;
            this.R_4764_Y = K_1289_S.n_1700_B(s) ? new F_2904_S(s) : x_282_a.J_1907_R(p_224224_1_.G_564_y);
        }
    }

    private void n_1700_B(x_282_a p_237841_1_) {
        this.J_1907_R = new U_2871_b("An error occurred: ");
        this.R_4764_Y = p_237841_1_;
    }

    private void n_1700_B(x_282_a p_237842_1_, x_282_a p_237842_2_) {
        this.J_1907_R = p_237842_1_;
        this.R_4764_Y = p_237842_2_;
    }

    @Override
    public void init() {
        NarrationHelper.n_1700_B(this.J_1907_R.getString() + ": " + this.R_4764_Y.getString());
        this.addButton(new Button(this.width / 2 - 100, this.height - 52, 200, 20, new U_2871_b("Ok"), p_237840_1_ -> this.minecraft.n_1700_B(this.n_1700_B)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        w_728_N.drawCenteredString(matrixStack, this.font, this.J_1907_R, this.width / 2, 80, 0xFFFFFF);
        w_728_N.drawCenteredString(matrixStack, this.font, this.R_4764_Y, this.width / 2, 100, 0xFF0000);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


