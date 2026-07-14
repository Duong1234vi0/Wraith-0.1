/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Locale;
import lightning.product.MinecraftAccess;

public class U_2474_c
implements MinecraftAccess {
    public static String n_1700_B(int number) {
        if (number < 1 || number > 3999) {
            return String.valueOf(number);
        }
        String[] thousands = new String[]{"", "M", "MM", "MMM"};
        String[] hundreds = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[number / 1000] + hundreds[number % 1000 / 100] + tens[number % 100 / 10] + ones[number % 10];
    }

    public static String n_1700_B(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String digitsOnly = input.replaceAll("[^0-9]", "");
        if ((digitsOnly = digitsOnly.replaceFirst("^0+(?!$)", "")).isEmpty()) {
            return "";
        }
        try {
            long value = Long.parseLong(digitsOnly);
            return String.format(Locale.US, "%,d", value);
        }
        catch (NumberFormatException e) {
            return "";
        }
    }

    public static int J_1907_R(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        int cnt = 0;
        for (int i = 0; i < input.length(); ++i) {
            if (!Character.isDigit(input.charAt(i))) continue;
            ++cnt;
        }
        return cnt;
    }
}


