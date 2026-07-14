/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.O_922_L;
import lightning.product.U_2871_b;
import lightning.product.Button;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.k_596_g;
import lightning.product.y_4642_Y;

public class M_3020_i
extends k_2603_m {
    public M_3020_i() {
        super(new U_2871_b("Out of memory!"));
    }

    @Override
    protected void init() {
        this.addButton(new Button(this.width / 2 - 155, this.height / 4 + 120 + 12, 150, 20, new F_2904_S("gui.toTitle"), p_213048_1_ -> this.minecraft.n_1700_B(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L())));
        this.addButton(new Button(this.width / 2 - 155 + 160, this.height / 4 + 120 + 12, 150, 20, new F_2904_S("menu.quit"), p_213047_1_ -> this.minecraft.h_1847_R()));
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        M_3020_i.drawCenteredString(matrixStack, this.font, this.title, this.width / 2, this.height / 4 - 60 + 20, 0xFFFFFF);
        M_3020_i.drawString(matrixStack, this.font, "Minecraft has run out of memory.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 0, 0xA0A0A0);
        M_3020_i.drawString(matrixStack, this.font, "This could be caused by a bug in the game or by the", this.width / 2 - 140, this.height / 4 - 60 + 60 + 18, 0xA0A0A0);
        M_3020_i.drawString(matrixStack, this.font, "Java Virtual Machine not being allocated enough", this.width / 2 - 140, this.height / 4 - 60 + 60 + 27, 0xA0A0A0);
        M_3020_i.drawString(matrixStack, this.font, "memory.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 36, 0xA0A0A0);
        M_3020_i.drawString(matrixStack, this.font, "To prevent level corruption, the current game has quit.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 54, 0xA0A0A0);
        M_3020_i.drawString(matrixStack, this.font, "We've tried to free up enough memory to let you go back to", this.width / 2 - 140, this.height / 4 - 60 + 60 + 63, 0xA0A0A0);
        M_3020_i.drawString(matrixStack, this.font, "the main menu and back to playing, but this may not have worked.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 72, 0xA0A0A0);
        M_3020_i.drawString(matrixStack, this.font, "Please restart the game if you see this message again.", this.width / 2 - 140, this.height / 4 - 60 + 60 + 81, 0xA0A0A0);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}


