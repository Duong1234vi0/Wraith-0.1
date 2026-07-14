/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.RealmsScreen;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class RealmsClientOutdatedScreen
extends RealmsScreen {
    private static final x_282_a n_1700_B = new F_2904_S("mco.client.outdated.title");
    private static final x_282_a[] J_1907_R = new x_282_a[]{new F_2904_S("mco.client.outdated.msg.line1"), new F_2904_S("mco.client.outdated.msg.line2")};
    private static final x_282_a R_4764_Y = new F_2904_S("mco.client.incompatible.title");
    private static final x_282_a[] G_564_y = new x_282_a[]{new F_2904_S("mco.client.incompatible.msg.line1"), new F_2904_S("mco.client.incompatible.msg.line2"), new F_2904_S("mco.client.incompatible.msg.line3")};
    private final k_2603_m P_1922_E;
    private final boolean u_1723_Y;

    public RealmsClientOutdatedScreen(k_2603_m p_i232201_1_, boolean p_i232201_2_) {
        this.P_1922_E = p_i232201_1_;
        this.u_1723_Y = p_i232201_2_;
    }

    @Override
    public void init() {
        this.addButton(new Button(this.width / 2 - 100, RealmsClientOutdatedScreen.G_564_y(12), 200, 20, CommonComponents.w_1484_f, p_237786_1_ -> this.minecraft.n_1700_B(this.P_1922_E)));
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        x_282_a[] aitextcomponent;
        x_282_a itextcomponent;
        this.renderBackground(matrixStack);
        if (this.u_1723_Y) {
            itextcomponent = R_4764_Y;
            aitextcomponent = G_564_y;
        } else {
            itextcomponent = n_1700_B;
            aitextcomponent = J_1907_R;
        }
        RealmsClientOutdatedScreen.drawCenteredString(matrixStack, this.font, itextcomponent, this.width / 2, RealmsClientOutdatedScreen.G_564_y(3), 0xFF0000);
        for (int i = 0; i < aitextcomponent.length; ++i) {
            RealmsClientOutdatedScreen.drawCenteredString(matrixStack, this.font, aitextcomponent[i], this.width / 2, RealmsClientOutdatedScreen.G_564_y(5) + i * 12, 0xFFFFFF);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode != 257 && keyCode != 335 && keyCode != 256) {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
        this.minecraft.n_1700_B(this.P_1922_E);
        return true;
    }
}


