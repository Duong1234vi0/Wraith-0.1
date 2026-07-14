/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package net.optifine.gui;

import com.google.common.collect.Lists;
import java.util.List;
import lightning.product.F_2904_S;
import lightning.product.K_1289_S;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import net.optifine.Config;
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiScreenOF;

public class GuiMessage
extends GuiScreenOF {
    private k_2603_m parentScreen;
    private x_282_a messageLine1;
    private x_282_a messageLine2;
    private final List<FormattedCharSequence> listLines2 = Lists.newArrayList();
    protected String confirmButtonText;
    private int ticksUntilEnable;

    public GuiMessage(k_2603_m parentScreen, String line1, String line2) {
        super(new F_2904_S("of.options.detailsTitle"));
        this.parentScreen = parentScreen;
        this.messageLine1 = new U_2871_b(line1);
        this.messageLine2 = new U_2871_b(line2);
        this.confirmButtonText = K_1289_S.n_1700_B("gui.done", new Object[0]);
    }

    @Override
    public void init() {
        this.addButton(new GuiButtonOF(0, this.width / 2 - 100, this.height / 6 + 96, this.confirmButtonText));
        this.listLines2.clear();
        this.listLines2.addAll(this.minecraft.t_148_a.J_1907_R(this.messageLine2, this.width - 50));
    }

    @Override
    protected void actionPerformed(V_2511_L button) {
        Config.getMinecraft().n_1700_B(this.parentScreen);
    }

    @Override
    public void render(g_221_o matrixStackIn, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStackIn);
        GuiMessage.drawCenteredString(matrixStackIn, this.fontRenderer, this.messageLine1, this.width / 2, 70, 0xFFFFFF);
        int i = 90;
        for (FormattedCharSequence ireorderingprocessor : this.listLines2) {
            GuiMessage.drawCenteredString(matrixStackIn, this.fontRenderer, ireorderingprocessor, this.width / 2, i, 0xFFFFFF);
            i += 9;
        }
        super.render(matrixStackIn, mouseX, mouseY, partialTicks);
    }

    public void setButtonDelay(int ticksUntilEnable) {
        this.ticksUntilEnable = ticksUntilEnable;
        for (V_2511_L button : this.buttonList) {
            button.active = false;
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (--this.ticksUntilEnable == 0) {
            for (V_2511_L button : this.buttonList) {
                button.active = true;
            }
        }
    }
}


