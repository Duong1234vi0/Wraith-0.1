/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.I_1084_e;
import lightning.product.U_2871_b;
import lightning.product.g_221_o;
import lightning.product.ProgressListener;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;

public class u_4650_L
extends k_2603_m
implements ProgressListener {
    @Nullable
    private x_282_a n_1700_B;
    @Nullable
    private x_282_a J_1907_R;
    private int R_4764_Y;
    private boolean G_564_y;
    private CustomLoadingScreen P_1922_E = CustomLoadingScreens.getCustomLoadingScreen();

    public u_4650_L() {
        super(I_1084_e.n_1700_B);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void n_1700_B(x_282_a component) {
        this.J_1907_R(component);
    }

    @Override
    public void J_1907_R(x_282_a component) {
        this.n_1700_B = component;
        this.R_4764_Y(new F_2904_S("progress.working"));
    }

    @Override
    public void R_4764_Y(x_282_a component) {
        this.J_1907_R = component;
        this.n_1700_B(0);
    }

    @Override
    public void n_1700_B(int progress) {
        this.R_4764_Y = progress;
    }

    @Override
    public void n_1700_B() {
        this.G_564_y = true;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        if (this.G_564_y) {
            if (!this.minecraft.Ping()) {
                this.minecraft.n_1700_B((k_2603_m)null);
            }
        } else {
            if (this.P_1922_E != null && this.minecraft.Y_601_j == null) {
                this.P_1922_E.drawBackground(this.width, this.height);
            } else {
                this.renderBackground(matrixStack);
            }
            if (this.R_4764_Y > 0) {
                if (this.n_1700_B != null) {
                    u_4650_L.drawCenteredString(matrixStack, this.font, this.n_1700_B, this.width / 2, 70, 0xFFFFFF);
                }
                if (this.J_1907_R != null && this.R_4764_Y != 0) {
                    u_4650_L.drawCenteredString(matrixStack, this.font, new U_2871_b("").n_1700_B(this.J_1907_R).n_1700_B(" " + this.R_4764_Y + "%"), this.width / 2, 90, 0xFFFFFF);
                }
            }
            super.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}


