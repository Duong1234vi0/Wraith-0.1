/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.booleans.BooleanConsumer
 */
package lightning.product;

import it.unimi.dsi.fastutil.booleans.BooleanConsumer;
import lightning.product.F_2904_S;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.CommonComponents;
import lightning.product.NarrationHelper;
import lightning.product.x_282_a;

public class RealmsLongConfirmationScreen
extends RealmsScreen {
    private final n_1700_B J_1907_R;
    private final x_282_a R_4764_Y;
    private final x_282_a G_564_y;
    protected final BooleanConsumer n_1700_B;
    private final boolean P_1922_E;

    public RealmsLongConfirmationScreen(BooleanConsumer p_i232208_1_, n_1700_B p_i232208_2_, x_282_a p_i232208_3_, x_282_a p_i232208_4_, boolean p_i232208_5_) {
        this.n_1700_B = p_i232208_1_;
        this.J_1907_R = p_i232208_2_;
        this.R_4764_Y = p_i232208_3_;
        this.G_564_y = p_i232208_4_;
        this.P_1922_E = p_i232208_5_;
    }

    @Override
    public void init() {
        NarrationHelper.n_1700_B(this.J_1907_R.G_564_y, this.R_4764_Y.getString(), this.G_564_y.getString());
        if (this.P_1922_E) {
            this.addButton(new Button(this.width / 2 - 105, RealmsLongConfirmationScreen.G_564_y(8), 100, 20, CommonComponents.P_1922_E, p_237848_1_ -> this.n_1700_B.accept(true)));
            this.addButton(new Button(this.width / 2 + 5, RealmsLongConfirmationScreen.G_564_y(8), 100, 20, CommonComponents.u_1723_Y, p_237847_1_ -> this.n_1700_B.accept(false)));
        } else {
            this.addButton(new Button(this.width / 2 - 50, RealmsLongConfirmationScreen.G_564_y(8), 100, 20, new F_2904_S("mco.gui.ok"), p_237846_1_ -> this.n_1700_B.accept(true)));
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == 256) {
            this.n_1700_B.accept(false);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        RealmsLongConfirmationScreen.drawCenteredString(matrixStack, this.font, this.J_1907_R.G_564_y, this.width / 2, RealmsLongConfirmationScreen.G_564_y(2), this.J_1907_R.R_4764_Y);
        RealmsLongConfirmationScreen.drawCenteredString(matrixStack, this.font, this.R_4764_Y, this.width / 2, RealmsLongConfirmationScreen.G_564_y(4), 0xFFFFFF);
        RealmsLongConfirmationScreen.drawCenteredString(matrixStack, this.font, this.G_564_y, this.width / 2, RealmsLongConfirmationScreen.G_564_y(6), 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B("Warning!", 0xFF0000);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B("Info!", 8226750);
        public final int R_4764_Y;
        public final String G_564_y;
        private static final /* synthetic */ n_1700_B[] P_1922_E;

        public static n_1700_B[] values() {
            return (n_1700_B[])P_1922_E.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(String p_i51697_3_, int p_i51697_4_) {
            this.G_564_y = p_i51697_3_;
            this.R_4764_Y = p_i51697_4_;
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            P_1922_E = lightning.product.RealmsLongConfirmationScreen$n_1700_B.n_1700_B();
        }
    }
}


