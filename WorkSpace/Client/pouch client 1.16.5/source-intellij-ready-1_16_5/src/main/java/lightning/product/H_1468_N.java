/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;

public class H_1468_N {
    private static final Pattern n_1700_B = Pattern.compile("(?i)\\u00A7[0-9A-FK-OR]");

    public static String n_1700_B(int ticks) {
        int i = ticks / 20;
        int j = i / 60;
        return (i %= 60) < 10 ? j + ":0" + i : j + ":" + i;
    }

    public static String n_1700_B(String text) {
        return n_1700_B.matcher(text).replaceAll("");
    }

    public static boolean J_1907_R(@Nullable String string) {
        return StringUtils.isEmpty((CharSequence)string);
    }
}

