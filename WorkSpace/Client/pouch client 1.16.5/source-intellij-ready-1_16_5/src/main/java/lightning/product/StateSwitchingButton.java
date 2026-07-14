/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;

public class StateSwitchingButton
extends V_2511_L {
    protected g_2336_b n_1700_B;
    protected boolean J_1907_R;
    protected int R_4764_Y;
    protected int G_564_y;
    protected int P_1922_E;
    protected int u_1723_Y;

    public StateSwitchingButton(int xIn, int yIn, int widthIn, int heightIn, boolean triggered) {
        super(xIn, yIn, widthIn, heightIn, U_2871_b.R_4764_Y);
        this.J_1907_R = triggered;
    }

    public void n_1700_B(int xTexStartIn, int yTexStartIn, int xDiffTexIn, int yDiffTexIn, g_2336_b resourceLocationIn) {
        this.R_4764_Y = xTexStartIn;
        this.G_564_y = yTexStartIn;
        this.P_1922_E = xDiffTexIn;
        this.u_1723_Y = yDiffTexIn;
        this.n_1700_B = resourceLocationIn;
    }

    public void n_1700_B(boolean triggered) {
        this.J_1907_R = triggered;
    }

    public boolean J_1907_R() {
        return this.J_1907_R;
    }

    public void n_1700_B(int xIn, int yIn) {
        this.x = xIn;
        this.y = yIn;
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        minecraft.G_624_v().n_1700_B(this.n_1700_B);
        c_4037_x.t_1786_h();
        int i = this.R_4764_Y;
        int j = this.G_564_y;
        if (this.J_1907_R) {
            i += this.P_1922_E;
        }
        if (this.isHovered()) {
            j += this.u_1723_Y;
        }
        this.blit(matrixStack, this.x, this.y, i, j, this.width, this.height);
        c_4037_x.multiplayerClientSuggestionProvider();
    }
}



