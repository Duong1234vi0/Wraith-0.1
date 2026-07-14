/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.gui;

import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;
import lightning.product.C_2701_A;
import lightning.product.V_2511_L;
import lightning.product.Y_4083_F;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipProvider;

public class TooltipManager {
    private k_2603_m guiScreen;
    private TooltipProvider tooltipProvider;
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    private long mouseStillTime = 0L;

    public TooltipManager(k_2603_m guiScreen, TooltipProvider tooltipProvider) {
        this.guiScreen = guiScreen;
        this.tooltipProvider = tooltipProvider;
    }

    public void drawTooltips(g_221_o matrixStackIn, int x, int y, List<V_2511_L> buttonList) {
        if (Math.abs(x - this.lastMouseX) <= 5 && Math.abs(y - this.lastMouseY) <= 5) {
            V_2511_L widget;
            int i = 700;
            if (System.currentTimeMillis() >= this.mouseStillTime + (long)i && (widget = GuiScreenOF.getSelectedButton(x, y, buttonList)) != null) {
                Rectangle rectangle = this.tooltipProvider.getTooltipBounds(this.guiScreen, x, y);
                String[] astring = this.tooltipProvider.getTooltipLines(widget, rectangle.width);
                if (astring != null) {
                    if (astring.length > 8) {
                        astring = Arrays.copyOf(astring, 8);
                        astring[astring.length - 1] = astring[astring.length - 1] + " ...";
                    }
                    if (this.tooltipProvider.isRenderBorder()) {
                        int j = -528449408;
                        this.drawRectBorder(matrixStackIn, rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, j);
                    }
                    C_2701_A.fill(matrixStackIn, rectangle.x, rectangle.y, rectangle.x + rectangle.width, rectangle.y + rectangle.height, -536870912);
                    for (int l = 0; l < astring.length; ++l) {
                        String s = astring[l];
                        int k = 0xDDDDDD;
                        if (s.endsWith("!")) {
                            k = 0xFF2020;
                        }
                        Y_4083_F fontrenderer = MinecraftClient.A_4115_X().t_148_a;
                        fontrenderer.n_1700_B(matrixStackIn, s, (float)(rectangle.x + 5), (float)(rectangle.y + 5 + l * 11), k);
                    }
                }
            }
        } else {
            this.lastMouseX = x;
            this.lastMouseY = y;
            this.mouseStillTime = System.currentTimeMillis();
        }
    }

    private void drawRectBorder(g_221_o matrixStackIn, int x1, int y1, int x2, int y2, int col) {
        C_2701_A.fill(matrixStackIn, x1, y1 - 1, x2, y1, col);
        C_2701_A.fill(matrixStackIn, x1, y2, x2, y2 + 1, col);
        C_2701_A.fill(matrixStackIn, x1 - 1, y1, x1, y2, col);
        C_2701_A.fill(matrixStackIn, x2, y1, x2 + 1, y2, col);
    }
}


