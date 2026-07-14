/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.gui;

import java.awt.Rectangle;
import java.util.ArrayList;
import lightning.product.M_2935_g;
import lightning.product.V_2511_L;
import lightning.product.k_2603_m;
import net.optifine.Lang;
import net.optifine.gui.IOptionControl;
import net.optifine.gui.TooltipProvider;

public class TooltipProviderOptions
implements TooltipProvider {
    @Override
    public Rectangle getTooltipBounds(k_2603_m guiScreen, int x, int y) {
        int i = guiScreen.width / 2 - 150;
        int j = guiScreen.height / 6 - 7;
        if (y <= j + 98) {
            j += 105;
        }
        int k = i + 150 + 150;
        int l = j + 84 + 10;
        return new Rectangle(i, j, k - i, l - j);
    }

    @Override
    public boolean isRenderBorder() {
        return false;
    }

    @Override
    public String[] getTooltipLines(V_2511_L btn, int width) {
        if (!(btn instanceof IOptionControl)) {
            return null;
        }
        IOptionControl ioptioncontrol = (IOptionControl)((Object)btn);
        M_2935_g abstractoption = ioptioncontrol.getControlOption();
        return TooltipProviderOptions.getTooltipLines(abstractoption.getResourceKey());
    }

    public static String[] getTooltipLines(String key) {
        String s;
        String s1;
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < 10 && (s1 = Lang.get(s = key + ".tooltip." + (i + 1), null)) != null; ++i) {
            list.add(s1);
        }
        return list.size() <= 0 ? null : list.toArray(new String[list.size()]);
    }
}

