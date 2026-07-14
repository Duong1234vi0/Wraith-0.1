/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.AbstractButton;
import lightning.product.X_933_l;
import lightning.product.Y_4083_F;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class Checkbox
extends AbstractButton {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/gui/checkbox.png");
    private boolean J_1907_R;
    private final boolean R_4764_Y;

    public Checkbox(int x, int y, int width, int height, x_282_a title, boolean checked) {
        this(x, y, width, height, title, checked, true);
    }

    public Checkbox(int p_i232258_1_, int p_i232258_2_, int p_i232258_3_, int p_i232258_4_, x_282_a p_i232258_5_, boolean p_i232258_6_, boolean drawTitle) {
        super(p_i232258_1_, p_i232258_2_, p_i232258_3_, p_i232258_4_, p_i232258_5_);
        this.J_1907_R = p_i232258_6_;
        this.R_4764_Y = drawTitle;
    }

    @Override
    public void onPress() {
        this.J_1907_R = !this.J_1907_R;
    }

    public boolean n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        minecraft.G_624_v().n_1700_B(n_1700_B);
        c_4037_x.multiplayerClientSuggestionProvider();
        Y_4083_F fontrenderer = minecraft.t_148_a;
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, this.alpha);
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        Checkbox.blit(matrixStack, this.x, this.y, this.isFocused() ? 20.0f : 0.0f, this.J_1907_R ? 20.0f : 0.0f, 20, this.height, 64, 64);
        this.renderBg(matrixStack, minecraft, mouseX, mouseY);
        if (this.R_4764_Y) {
            Checkbox.drawString(matrixStack, fontrenderer, this.getMessage(), this.x + 24, this.y + (this.height - 8) / 2, 0xE0E0E0 | u_530_F.u_1723_Y(this.alpha * 255.0f) << 24);
        }
    }
}



