/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.gui;

import java.awt.Rectangle;
import lightning.product.V_2511_L;
import lightning.product.k_2603_m;

public interface TooltipProvider {
    public Rectangle getTooltipBounds(k_2603_m var1, int var2, int var3);

    public String[] getTooltipLines(V_2511_L var1, int var2);

    public boolean isRenderBorder();
}

