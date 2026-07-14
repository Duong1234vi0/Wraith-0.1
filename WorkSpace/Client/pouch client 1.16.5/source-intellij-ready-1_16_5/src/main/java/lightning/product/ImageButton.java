/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.x_282_a;

public class ImageButton
extends Button {
    private final g_2336_b n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;

    public ImageButton(int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, g_2336_b resourceLocationIn, Button.n_1700_B onPressIn) {
        this(xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, resourceLocationIn, 256, 256, onPressIn);
    }

    public ImageButton(int xIn, int yIn, int widthIn, int heightIn, int xTexStartIn, int yTexStartIn, int yDiffTextIn, g_2336_b resourceLocationIn, int p_i51135_9_, int p_i51135_10_, Button.n_1700_B onPressIn) {
        this(xIn, yIn, widthIn, heightIn, xTexStartIn, yTexStartIn, yDiffTextIn, resourceLocationIn, p_i51135_9_, p_i51135_10_, onPressIn, U_2871_b.R_4764_Y);
    }

    public ImageButton(int x, int y, int width, int height, int xTexStart, int yTexStart, int yDiffText, g_2336_b resourceLocation, int textureWidth, int textureHeight, Button.n_1700_B onPress, x_282_a title) {
        this(x, y, width, height, xTexStart, yTexStart, yDiffText, resourceLocation, textureWidth, textureHeight, onPress, field_238486_s_, title);
    }

    public ImageButton(int p_i242137_1_, int p_i242137_2_, int p_i242137_3_, int p_i242137_4_, int p_i242137_5_, int p_i242137_6_, int p_i242137_7_, g_2336_b p_i242137_8_, int p_i242137_9_, int p_i242137_10_, Button.n_1700_B p_i242137_11_, Button.J_1907_R p_i242137_12_, x_282_a p_i242137_13_) {
        super(p_i242137_1_, p_i242137_2_, p_i242137_3_, p_i242137_4_, p_i242137_13_, p_i242137_11_, p_i242137_12_);
        this.P_1922_E = p_i242137_9_;
        this.u_1723_Y = p_i242137_10_;
        this.J_1907_R = p_i242137_5_;
        this.R_4764_Y = p_i242137_6_;
        this.G_564_y = p_i242137_7_;
        this.n_1700_B = p_i242137_8_;
    }

    public void n_1700_B(int xIn, int yIn) {
        this.x = xIn;
        this.y = yIn;
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        minecraft.G_624_v().n_1700_B(this.n_1700_B);
        int i = this.R_4764_Y;
        if (this.isHovered()) {
            i += this.G_564_y;
        }
        c_4037_x.multiplayerClientSuggestionProvider();
        ImageButton.blit(matrixStack, this.x, this.y, this.J_1907_R, i, this.width, this.height, this.P_1922_E, this.u_1723_Y);
        if (this.isHovered()) {
            this.renderToolTip(matrixStack, mouseX, mouseY);
        }
    }
}



