/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.regex.Pattern;
import lightning.product.D_4024_W;
import lightning.product.Module;
import lightning.product.n_473_l;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.ModuleCategory;

public class NameProtect
extends Module {
    private static final Pattern M_182_A = Pattern.compile("(\u00a7[0-9a-fk-or])*\\s*\\[.*?\\]\\s*");
    private static final Pattern t_1786_h = Pattern.compile("^(\u00a7[0-9a-fk-or])+\\s*");
    private static final Pattern multiplayerClientSuggestionProvider = Pattern.compile("\\s+");
    private static final Pattern w_1457_N = Pattern.compile("(\u0413\u0420\u0418\u0424\\s*#)\\d+");
    private static NameProtect Y_601_j;
    public static BooleanSetting v_4262_N;
    public static BooleanSetting w_1484_f;
    public static BooleanSetting t_148_a;
    public static BooleanSetting s_956_w;
    public static BooleanSetting u_2550_I;
    public static BooleanSetting M_588_G;
    public static BooleanSetting P_4830_p;
    public static BooleanSetting h_1847_R;
    public static BooleanSetting Q_4569_t;

    public static NameProtect h_1847_R() {
        return Y_601_j;
    }

    public NameProtect() {
        super("NameProtect", ModuleCategory.P_1922_E);
        Y_601_j = this;
        this.n_1700_B(v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t);
    }

    public static String R_4764_Y(String text) {
        return NameProtect.n_1700_B(text, false);
    }

    public static String n_1700_B(String text, boolean friendNametag) {
        String result;
        boolean stripBrackets;
        NameProtect module = NameProtect.h_1847_R();
        boolean bl = stripBrackets = Q_4569_t.t_148_a() != false || w_1484_f.t_148_a() != false && friendNametag;
        if (module == null || !module.w_1484_f() || !stripBrackets) {
            return text;
        }
        if (text.indexOf(91) < 0) {
            result = text;
        } else {
            String prev;
            result = M_182_A.matcher(text).replaceAll("");
            do {
                prev = result;
            } while (!(result = M_182_A.matcher(result).replaceAll("")).equals(prev));
        }
        String cleanText = D_4024_W.n_1700_B(result).trim();
        if (cleanText.isEmpty()) {
            return text;
        }
        String[] words = multiplayerClientSuggestionProvider.split(cleanText);
        if (words.length <= 1) {
            return t_1786_h.matcher(result).replaceAll("");
        }
        int nameWords = 1;
        if (words.length > 1 && words[words.length - 1].length() <= 2) {
            nameWords = 2;
        }
        int nameStartPos = 0;
        int wordsToSkip = words.length - nameWords;
        if (wordsToSkip > 0) {
            int wordIndex = 0;
            boolean inColorCode = false;
            boolean inWord = false;
            for (int i = 0; i < result.length(); ++i) {
                char c = result.charAt(i);
                if (c == '\u00a7' || c == '\u00a7') {
                    inColorCode = true;
                    continue;
                }
                if (inColorCode) {
                    inColorCode = false;
                    continue;
                }
                if (Character.isWhitespace(c)) {
                    if (!inWord) continue;
                    inWord = false;
                    if (++wordIndex < wordsToSkip) continue;
                    nameStartPos = i + 1;
                    break;
                }
                if (inWord) continue;
                inWord = true;
            }
        }
        if (nameStartPos == 0) {
            for (int i = result.length() - 1; i >= 0; --i) {
                char c = result.charAt(i);
                if (c == '\u00a7' || c == '\u00a7') {
                    --i;
                    continue;
                }
                if (!Character.isWhitespace(c)) continue;
                nameStartPos = i + 1;
                break;
            }
        }
        String namePart = nameStartPos > 0 ? result.substring(nameStartPos) : result;
        if ((namePart = t_1786_h.matcher(namePart).replaceAll("")).trim().isEmpty()) {
            return text;
        }
        return namePart;
    }

    private static String n_1700_B(String text, String search, String replacement) {
        if (text == null || search == null || search.isEmpty()) {
            return text;
        }
        int sl = search.length();
        if (sl == 0) {
            return text;
        }
        int first = -1;
        int n = text.length();
        for (int i = 0; i <= n - sl; ++i) {
            if (!text.regionMatches(true, i, search, 0, sl)) continue;
            first = i;
            break;
        }
        if (first == -1) {
            return text;
        }
        StringBuilder sb = new StringBuilder(n + replacement.length() * 2);
        int i = 0;
        while (i < n) {
            if (i <= n - sl && text.regionMatches(true, i, search, 0, sl)) {
                sb.append(replacement);
                i += sl;
                continue;
            }
            sb.append(text.charAt(i));
            ++i;
        }
        return sb.toString();
    }

    private static boolean J_1907_R(String text, String search) {
        if (text == null || search == null || search.isEmpty()) {
            return false;
        }
        int sl = search.length();
        int n = text.length();
        for (int i = 0; i <= n - sl; ++i) {
            if (!text.regionMatches(true, i, search, 0, sl)) continue;
            return true;
        }
        return false;
    }

    public static String n_1700_B(String cleanedVisible, String gameProfileName) {
        n_473_l.n_1700_B fe;
        String self;
        NameProtect np = NameProtect.h_1847_R();
        if (np == null || !np.w_1484_f() || cleanedVisible == null) {
            return cleanedVisible;
        }
        if (gameProfileName == null || gameProfileName.isEmpty()) {
            return cleanedVisible;
        }
        String string = self = NameProtect.c_3005_b.w_1484_f != null ? NameProtect.c_3005_b.w_1484_f.R_4764_Y() : null;
        if (self != null && !self.isEmpty() && gameProfileName.equalsIgnoreCase(self)) {
            if (!NameProtect.J_1907_R(cleanedVisible, self)) {
                return "Protected";
            }
            return NameProtect.n_1700_B(cleanedVisible, self, "Protected");
        }
        if (v_4262_N.t_148_a().booleanValue() && ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(gameProfileName) && (fe = ClientBootstrap.Y_601_j().v_4262_N().G_564_y(gameProfileName)) != null) {
            String fn = fe.n_1700_B();
            String repl = fe.R_4764_Y();
            if (repl == null || repl.isEmpty()) {
                repl = "Protected";
            }
            if (fn != null && !fn.isEmpty() && NameProtect.J_1907_R(cleanedVisible, fn)) {
                return NameProtect.n_1700_B(cleanedVisible, fn, repl);
            }
            return repl;
        }
        return cleanedVisible;
    }

    public static String G_564_y(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        String username = NameProtect.c_3005_b.w_1484_f.R_4764_Y();
        if (username != null && !username.isEmpty()) {
            name = NameProtect.n_1700_B(name, username, "Protected");
        }
        if (v_4262_N.t_148_a().booleanValue()) {
            for (n_473_l.n_1700_B friend : ClientBootstrap.Y_601_j().v_4262_N().P_4830_p()) {
                String fn = friend.n_1700_B();
                if (fn == null || fn.isEmpty()) continue;
                String repl = friend.R_4764_Y();
                if (repl == null || repl.isEmpty()) {
                    repl = "Protected";
                }
                name = NameProtect.n_1700_B(name, fn, repl);
            }
        }
        return name;
    }

    public static String P_1922_E(String line) {
        if (line == null) {
            return line;
        }
        if (line.contains("\u041d\u0438\u043a:")) {
            return NameProtect.J_1907_R(line, "\u041d\u0438\u043a:", "Protected");
        }
        if (line.contains("\u043d\u0438\u043a:")) {
            return NameProtect.J_1907_R(line, "\u043d\u0438\u043a:", "Protected");
        }
        if (w_1484_f.t_148_a().booleanValue() && line.contains("\u041a\u043b\u0430\u043d:")) {
            return NameProtect.J_1907_R(line, "\u041a\u043b\u0430\u043d:", "Pouch");
        }
        if (t_148_a.t_148_a().booleanValue() && line.contains("\u0420\u0430\u043d\u0433:")) {
            return NameProtect.J_1907_R(line, "\u0420\u0430\u043d\u0433:", "Pouch");
        }
        if (s_956_w.t_148_a().booleanValue() && line.contains("\u0413\u0420\u0418\u0424")) {
            return w_1457_N.matcher(line).replaceAll("$1POUCH");
        }
        if (u_2550_I.t_148_a().booleanValue() && (line.contains(".ru") || line.contains(".net") || line.contains(".com") || line.contains(".su") || line.contains(".org") || line.contains(".me"))) {
            StringBuilder colorCode = new StringBuilder();
            for (int i = 0; i < line.length() - 1; ++i) {
                char c = line.charAt(i);
                if (c == '\u00a7' || c == '\u00a7') {
                    colorCode.append(c).append(line.charAt(i + 1));
                    ++i;
                    continue;
                }
                if (!Character.isWhitespace(c)) break;
            }
            return String.valueOf(colorCode) + "t.me/pouchclient";
        }
        if (M_588_G.t_148_a().booleanValue() && line.contains("\u0411\u0430\u043b\u0430\u043d\u0441:")) {
            return NameProtect.J_1907_R(line, "\u0411\u0430\u043b\u0430\u043d\u0441:", "100000");
        }
        if (P_4830_p.t_148_a().booleanValue() && line.contains("\u0420\u0438\u043b\u043b\u0438\u043a\u043e\u0432:")) {
            return NameProtect.J_1907_R(line, "\u0420\u0438\u043b\u043b\u0438\u043a\u043e\u0432:", "666666");
        }
        if (h_1847_R.t_148_a().booleanValue() && line.contains("\u0420\u043e\u043c\u0430\u0448\u043a\u0438:")) {
            return NameProtect.J_1907_R(line, "\u0420\u043e\u043c\u0430\u0448\u043a\u0438:", "0");
        }
        return line;
    }

    private static String J_1907_R(String line, String label, String newValue) {
        int labelIndex = line.indexOf(label);
        if (labelIndex == -1) {
            return line;
        }
        String beforeLabel = line.substring(0, labelIndex);
        String afterLabel = line.substring(labelIndex + label.length());
        StringBuilder colorCodes = new StringBuilder();
        StringBuilder remaining = new StringBuilder();
        for (int i = 0; i < afterLabel.length(); ++i) {
            char c = afterLabel.charAt(i);
            if ((c == '\u00a7' || c == '\u00a7' || c == '&') && i + 1 < afterLabel.length()) {
                colorCodes.append(c).append(afterLabel.charAt(i + 1));
                ++i;
                continue;
            }
            if (colorCodes.length() == 0 && remaining.length() == 0 && Character.isWhitespace(c)) {
                colorCodes.append(c);
                continue;
            }
            remaining.append(c);
        }
        if (!colorCodes.toString().contains("\u00a7") && !colorCodes.toString().contains("\u00a7")) {
            if (label.contains("\u0411\u0430\u043b\u0430\u043d\u0441")) {
                colorCodes = new StringBuilder(" \u00a76");
            } else if (label.contains("\u0420\u0438\u043b\u043b\u0438\u043a")) {
                colorCodes = new StringBuilder(" \u00a7b");
            } else if (label.contains("\u0420\u043e\u043c\u0430\u0448\u043a")) {
                colorCodes = new StringBuilder(" \u00a7a");
            } else if (label.contains("\u041a\u043b\u0430\u043d") || label.contains("\u0420\u0430\u043d\u0433") || label.contains("\u041d\u0438\u043a") || label.contains("\u043d\u0438\u043a")) {
                colorCodes = new StringBuilder(" \u00a7f");
            }
        }
        return beforeLabel + label + colorCodes.toString() + newValue;
    }

    static {
        v_4262_N = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0434\u0440\u0443\u0437\u0435\u0439", false);
        w_1484_f = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u043a\u043b\u0430\u043d", false);
        t_148_a = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0440\u0430\u043d\u0433", false);
        s_956_w = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0433\u0440\u0438\u0444", false);
        u_2550_I = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c IP", false);
        M_588_G = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0431\u0430\u043b\u0430\u043d\u0441", false);
        P_4830_p = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0440\u0438\u043b\u043b\u0438\u043a\u0438", false);
        h_1847_R = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u0440\u043e\u043c\u0430\u0448\u043a\u0438", false);
        Q_4569_t = new BooleanSetting("\u0421\u043a\u0440\u044b\u0432\u0430\u0442\u044c \u043f\u0440\u0435\u0444\u0438\u043a\u0441\u044b \u0432 \u043d\u0435\u0439\u043c\u0442\u0435\u0433\u0430\u0445", true);
    }
}



