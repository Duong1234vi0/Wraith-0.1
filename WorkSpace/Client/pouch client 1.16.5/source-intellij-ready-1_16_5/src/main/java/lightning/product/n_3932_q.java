/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Locale;
import lightning.product.D_4024_W;
import lightning.product.BooleanSetting;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.Items;
import lightning.product.u_1934_K;
import lightning.product.v_1900_v;

public abstract class n_3932_q {
    public static void n_1700_B() {
        if (u_1934_K.n_1700_B(Items.FenceGateBlock) == -1) {
            v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.P_4830_p) + "\u0423 \u0432\u0430\u0441 \u043e\u0442\u0441\u0443\u0442\u0441\u0442\u0432\u0443\u0435\u0442 \u0430\u043d\u0442\u0438 \u043f\u043e\u043b\u0435\u0442!", new Object[0]);
        } else {
            u_1934_K.n_1700_B(Items.FenceGateBlock, false);
        }
    }

    public abstract String J_1907_R();

    public abstract String R_4764_Y();

    public abstract List<KeyBindSetting> G_564_y();

    public abstract List<q_3386_W.n_1700_B> n_1700_B(boolean var1);

    public abstract boolean n_1700_B(int var1);

    public abstract boolean P_1922_E();

    public abstract void u_1723_Y();

    public BooleanSetting v_4262_N() {
        return null;
    }

    protected static String n_1700_B(String value) {
        String stripped = D_4024_W.n_1700_B(value == null ? "" : value);
        return n_3932_q.J_1907_R(stripped).toLowerCase(Locale.ROOT);
    }

    protected static String J_1907_R(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder(value.length());
        boolean lastWasSpace = false;
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            if (c == '\u00ab' || c == '\u00bb' || c == '\"' || c == '\'' || c == '`') continue;
            if (Character.isWhitespace(c)) {
                if (lastWasSpace || result.length() <= 0) continue;
                result.append(' ');
                lastWasSpace = true;
                continue;
            }
            result.append(c);
            lastWasSpace = false;
        }
        int length = result.length();
        if (length > 0 && result.charAt(length - 1) == ' ') {
            result.setLength(length - 1);
        }
        return result.toString();
    }
}


