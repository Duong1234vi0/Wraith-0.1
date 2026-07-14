/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.config;

import lightning.product.F_2904_S;
import lightning.product.I_2212_R;
import lightning.product.K_1289_S;
import lightning.product.M_2935_g;
import lightning.product.U_2871_b;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;
import net.optifine.Config;
import net.optifine.Lang;

public class FloatOptions {
    public static x_282_a getTextComponent(M_2935_g option, double val) {
        if (option == M_2935_g.RENDER_DISTANCE) {
            return option.getGenericValueComponent(new F_2904_S("options.chunks", (int)val));
        }
        if (option == M_2935_g.MIPMAP_LEVELS) {
            if (val >= 4.0) {
                return option.getGenericValueComponent(new F_2904_S("of.general.max"));
            }
            return val == 0.0 ? CommonComponents.n_1700_B(option.getBaseMessageTranslation(), false) : option.getMessageWithValue((int)val);
        }
        if (option == M_2935_g.BIOME_BLEND_RADIUS) {
            int i = (int)val * 2 + 1;
            return option.getGenericValueComponent(new F_2904_S("options.biomeBlendRadius." + i));
        }
        String s = FloatOptions.getText(option, val);
        return s != null ? new U_2871_b(s) : null;
    }

    public static String getText(M_2935_g option, double val) {
        String s = K_1289_S.n_1700_B(option.getResourceKey(), new Object[0]) + ": ";
        if (option == M_2935_g.AO_LEVEL) {
            return val == 0.0 ? s + K_1289_S.n_1700_B("options.off", new Object[0]) : s + (int)(val * 100.0) + "%";
        }
        if (option == M_2935_g.MIPMAP_TYPE) {
            int k = (int)val;
            switch (k) {
                case 0: {
                    return s + Lang.get("of.options.mipmap.nearest");
                }
                case 1: {
                    return s + Lang.get("of.options.mipmap.linear");
                }
                case 2: {
                    return s + Lang.get("of.options.mipmap.bilinear");
                }
                case 3: {
                    return s + Lang.get("of.options.mipmap.trilinear");
                }
            }
            return s + "of.options.mipmap.nearest";
        }
        if (option == M_2935_g.AA_LEVEL) {
            int j = (int)val;
            Object s1 = "";
            if (j != Config.getAntialiasingLevel()) {
                s1 = " (" + Lang.get("of.general.restart") + ")";
            }
            return j == 0 ? s + Lang.getOff() + (String)s1 : s + j + (String)s1;
        }
        if (option == M_2935_g.AF_LEVEL) {
            int i = (int)val;
            return i == 1 ? s + Lang.getOff() : s + i;
        }
        return null;
    }

    public static boolean supportAdjusting(I_2212_R option) {
        x_282_a itextcomponent = FloatOptions.getTextComponent(option, 0.0);
        return itextcomponent != null;
    }
}


