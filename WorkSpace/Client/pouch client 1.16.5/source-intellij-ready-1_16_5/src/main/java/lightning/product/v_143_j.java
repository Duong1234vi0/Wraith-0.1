/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class v_143_j {
    private static final String[][] n_1700_B = new String[][]{{"sTAFF", "STAFF"}, {"sTaff", "Staff"}, {"stAFF", "STAFF"}, {"StAFF", "STAFF"}, {"STaFF", "STAFF"}, {"sTAF", "STAF"}, {"aDMIN", "ADMIN"}, {"aDmin", "Admin"}, {"adMIN", "ADMIN"}, {"hELPER", "HELPER"}, {"hElper", "Helper"}, {"heLPER", "HELPER"}, {"mODER", "MODER"}, {"mODERATOR", "MODERATOR"}, {"mOder", "Moder"}, {"oWNER", "OWNER"}, {"oWner", "Owner"}, {"vIP", "VIP"}, {"vIp", "VIP"}, {"gM", "GM"}};

    public static String n_1700_B(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        String result = text;
        for (String[] replacement : n_1700_B) {
            result = result.replace(replacement[0], replacement[1]);
        }
        result = v_143_j.R_4764_Y(result);
        return result;
    }

    private static String R_4764_Y(String text) {
        StringBuilder result = new StringBuilder(text);
        int i = 0;
        while (i < result.length()) {
            if (i < result.length() - 1 && result.charAt(i) == '\u00a7') {
                i += 2;
                continue;
            }
            char c = result.charAt(i);
            if (v_143_j.n_1700_B(c)) {
                int uppercaseCount = 0;
                int j = i + 1;
                while (j < result.length()) {
                    char nextChar;
                    if (j < result.length() - 1 && result.charAt(j) == '\u00a7') {
                        j += 2;
                        continue;
                    }
                    if (j >= result.length() || !v_143_j.J_1907_R(nextChar = result.charAt(j))) break;
                    ++uppercaseCount;
                    ++j;
                }
                if (uppercaseCount >= 2) {
                    result.setCharAt(i, Character.toUpperCase(c));
                }
            }
            ++i;
        }
        return result.toString();
    }

    private static boolean n_1700_B(char c) {
        return c >= 'a' && c <= 'z' || c >= '\u0430' && c <= '\u044f' || c == '\u0451';
    }

    private static boolean J_1907_R(char c) {
        return c >= 'A' && c <= 'Z' || c >= '\u0410' && c <= '\u042f' || c == '\u0401';
    }

    public static boolean J_1907_R(String text) {
        if (text == null || text.isEmpty()) {
            return false;
        }
        return !text.equals(v_143_j.n_1700_B(text));
    }
}

