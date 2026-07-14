/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 */
package lightning.product;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import lightning.product.V_2511_L;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class RealmsConfirmScreen
extends RealmsScreen {
    protected BooleanConsumer n_1700_B;
    private final x_282_a J_1907_R;
    private final x_282_a R_4764_Y;
    private int G_564_y;

    public RealmsConfirmScreen(BooleanConsumer p_i232202_1_, x_282_a p_i232202_2_, x_282_a p_i232202_3_) {
        this.n_1700_B = p_i232202_1_;
        this.J_1907_R = p_i232202_2_;
        this.R_4764_Y = p_i232202_3_;
    }

    @Override
    public void init() {
        this.addButton(new Button(this.width / 2 - 105, RealmsConfirmScreen.G_564_y(9), 100, 20, CommonComponents.P_1922_E, p_237826_1_ -> this.n_1700_B.accept(true)));
        this.addButton(new Button(this.width / 2 + 5, RealmsConfirmScreen.G_564_y(9), 100, 20, CommonComponents.u_1723_Y, p_237825_1_ -> this.n_1700_B.accept(false)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        RealmsConfirmScreen.drawCenteredString(matrixStack, this.font, this.J_1907_R, this.width / 2, RealmsConfirmScreen.G_564_y(3), 0xFFFFFF);
        RealmsConfirmScreen.drawCenteredString(matrixStack, this.font, this.R_4764_Y, this.width / 2, RealmsConfirmScreen.G_564_y(5), 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void tick() {
        super.tick();
        if (--this.G_564_y == 0) {
            for (V_2511_L widget : this.buttons) {
                widget.active = true;
            }
        }
    }
}


