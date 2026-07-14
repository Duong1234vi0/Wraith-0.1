/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.MutableComponent;
import lightning.product.Q_2753_H;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.W_4328_U;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_256_c;
import lightning.product.MinecraftClient;
import lightning.product.c_1608_O;
import lightning.product.ClientboundChatPacket;
import lightning.product.n_3932_q;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.KeyBindSetting;
import lightning.product.q_3386_W;
import lightning.product.Items;
import lightning.product.Packet;
import lightning.product.v_1900_v;
import lightning.product.x_282_a;
import lightning.product.y_4642_Y;

public class f_360_U
extends n_3932_q {
    private static final Pattern n_1700_B = Pattern.compile("([xX\u0445\u0425])\\s*([+\\-*/])\\s*([\\d.,]+)\\s*=\\s*([\\d.,]+)");
    private static final Pattern J_1907_R = Pattern.compile("([\\d.,]+)\\s*([+\\-*/])\\s*([xX\u0445\u0425])\\s*=\\s*([\\d.,]+)");
    private final MinecraftClient R_4764_Y = MinecraftClient.A_4115_X();
    private final List<KeyBindSetting> G_564_y;
    private final BooleanSupplier P_1922_E;
    private long u_1723_Y;
    private String v_4262_N;
    private q_1613_l w_1484_f;
    private boolean t_148_a = false;
    private final String[] s_956_w = new String[]{"\u041e\u0431\u044b\u0447\u043d\u0430\u044f \u043b\u0438\u0432\u0430\u043b\u043a\u0430", "\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u043e\u0435 \u043f\u0435\u0440\u044b\u0448\u043a\u043e", "\u041b\u0438\u0432\u0430\u043b\u043a\u0430 \u0441 \u043f\u043b\u0430\u0442\u0444\u043e\u0440\u043c\u043e\u0439", "\u0423\u043d\u0438\u043a\u0430\u043b\u044c\u043d\u0430\u044f \u0442\u0440\u0430\u043f\u043a\u0430"};
    private final q_1613_l[] u_2550_I = new q_1613_l[]{Items.S_3844_E, Items.H_274_C, Items.i_4833_u, Items.TallSeagrass};
    private final q_1613_l[] M_588_G = new q_1613_l[]{Items.S_3844_E, Items.H_274_C, Items.i_4833_u, Items.TallSeagrass};
    private boolean P_4830_p = false;

    public f_360_U(List<KeyBindSetting> binds, BooleanSupplier chatSolverEnabled) {
        this.G_564_y = binds;
        this.P_1922_E = chatSolverEnabled;
    }

    @Override
    public String J_1907_R() {
        return "LonyGrief";
    }

    @Override
    public String R_4764_Y() {
        return "Lony";
    }

    @Override
    public List<KeyBindSetting> G_564_y() {
        return this.G_564_y;
    }

    @Override
    public List<q_3386_W.n_1700_B> n_1700_B(boolean onlyBar) {
        ArrayList<q_3386_W.n_1700_B> renderItems = new ArrayList<q_3386_W.n_1700_B>();
        for (int i = 0; i < this.s_956_w.length && i < this.G_564_y.size(); ++i) {
            String status;
            int slot;
            c_1608_O itemSetting = Z_256_c.J_1907_R(this.s_956_w[i]);
            int n = slot = itemSetting != null ? this.n_1700_B(itemSetting) : -1;
            if (slot == -1) {
                status = "\u2014";
            } else if (onlyBar && slot >= 9) {
                status = "\u2014";
            } else if (this.R_4764_Y.Y_259_p.p_1458_L().n_1700_B(this.u_2550_I[i])) {
                float cooldown = this.R_4764_Y.Y_259_p.p_1458_L().n_1700_B(this.u_2550_I[i], this.R_4764_Y.RealmsClientConfig());
                float remainingSeconds = cooldown * 20.0f;
                status = String.format("%.0f\u0441", Float.valueOf(remainingSeconds));
            } else {
                status = "\u2713";
            }
            int bindKey = (Integer)this.G_564_y.get(i).J_1907_R();
            String shortName = this.s_956_w[i].length() > 8 ? this.s_956_w[i].substring(0, 8) : this.s_956_w[i];
            renderItems.add(new q_3386_W.n_1700_B(this.s_956_w[i], shortName, status, this.M_588_G[i]).n_1700_B(bindKey));
        }
        return renderItems;
    }

    @Override
    public boolean n_1700_B(int keyCode) {
        for (int i = 0; i < this.s_956_w.length && i < this.G_564_y.size(); ++i) {
            if ((Integer)this.G_564_y.get(i).J_1907_R() == -1 || (Integer)this.G_564_y.get(i).J_1907_R() != keyCode) continue;
            this.n_1700_B(this.s_956_w[i], this.u_2550_I[i]);
            return true;
        }
        return false;
    }

    @Override
    public boolean P_1922_E() {
        return this.t_148_a && this.v_4262_N != null;
    }

    public void w_1484_f() {
        this.t_148_a = false;
        this.v_4262_N = null;
        this.w_1484_f = null;
    }

    @Override
    public void u_1723_Y() {
        this.t_148_a = false;
        this.v_4262_N = null;
        this.w_1484_f = null;
    }

    public String t_148_a() {
        return this.v_4262_N;
    }

    public q_1613_l s_956_w() {
        return this.w_1484_f;
    }

    public boolean u_2550_I() {
        return this.t_148_a;
    }

    public int M_588_G() {
        if (this.v_4262_N == null) {
            return -1;
        }
        c_1608_O itemSetting = Z_256_c.J_1907_R(this.v_4262_N);
        if (itemSetting == null) {
            return -1;
        }
        return this.n_1700_B(itemSetting);
    }

    private void n_1700_B(String itemName, q_1613_l item) {
        c_1608_O itemSetting = Z_256_c.J_1907_R(itemName);
        if (itemSetting == null) {
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u043a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u0438", new Object[0]);
            return;
        }
        int slot = this.n_1700_B(itemSetting);
        if (slot == -1) {
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.P_4830_p && slot >= 9) {
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u043d\u0435 \u0432 \u0445\u043e\u0442\u0431\u0430\u0440\u0435", new Object[0]);
            return;
        }
        if (this.R_4764_Y.Y_259_p.p_1458_L().n_1700_B(item)) {
            float cooldown = this.R_4764_Y.Y_259_p.p_1458_L().n_1700_B(item, this.R_4764_Y.RealmsClientConfig());
            float remainingSeconds = cooldown * 20.0f;
            v_1900_v.n_1700_B("\u00a7c" + itemName + " \u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d \u0435\u0449\u0435 " + String.format("%.1f", Float.valueOf(remainingSeconds)) + " \u0441\u0435\u043a", new Object[0]);
            return;
        }
        this.t_148_a = true;
        this.w_1484_f = item;
        this.v_4262_N = itemName;
    }

    public void J_1907_R(boolean onlyBar) {
        this.P_4830_p = onlyBar;
    }

    @Y_1740_V
    private void n_1700_B(Q_2753_H e) {
        boolean looksLikeChatGame;
        Packet<?> t_3138_Z2;
        if (!e.J_1907_R() || !((t_3138_Z2 = e.G_564_y()) instanceof ClientboundChatPacket)) {
            return;
        }
        ClientboundChatPacket packet = (ClientboundChatPacket)t_3138_Z2;
        if (!this.P_1922_E.getAsBoolean() || y_4642_Y.R_4764_Y()) {
            return;
        }
        if (this.R_4764_Y.Y_259_p == null || this.R_4764_Y.Y_259_p.n_1700_B == null) {
            return;
        }
        if (System.currentTimeMillis() - this.u_1723_Y < 400L) {
            return;
        }
        String raw = packet.J_1907_R().getString();
        String line = f_360_U.R_4764_Y(raw);
        if (line.isEmpty()) {
            return;
        }
        if (!line.contains("=")) {
            return;
        }
        String lower = line.toLowerCase();
        boolean bl = looksLikeChatGame = lower.contains("\u043d\u0430\u0439\u0434\u0438\u0442\u0435") || lower.contains("\u0447\u0430\u0442-\u0438\u0433\u0440\u0430") || lower.contains("\u0440\u0435\u0448\u0438\u0442\u0435 \u0443\u0440\u0430\u0432\u043d\u0435\u043d\u0438\u0435");
        if (!looksLikeChatGame) {
            return;
        }
        String answer = f_360_U.G_564_y(line);
        if (answer == null) {
            return;
        }
        this.u_1723_Y = System.currentTimeMillis();
        if (this.R_4764_Y.M_588_G != null) {
            this.R_4764_Y.M_588_G.R_4764_Y().n_1700_B(new U_2871_b(answer));
        }
        this.R_4764_Y.Y_259_p.n_1700_B.J_1907_R(new W_4328_U(answer));
    }

    private static String R_4764_Y(String raw) {
        String s = raw.replaceAll("\u00a7[0-9a-fk-or]", "");
        s = s.replace("\u23b9", " ").replace("\u23a7", " ").replace("\u23a9", " ");
        s = s.replace("\u22b2", " ").replace("\u22b3", " ");
        s = s.replace(',', '.');
        return s.trim();
    }

    private static String G_564_y(String line) {
        Matcher m1 = n_1700_B.matcher(line);
        if (m1.find()) {
            String op = m1.group(2);
            double b = Double.parseDouble(m1.group(3));
            double c = Double.parseDouble(m1.group(4));
            double x = f_360_U.n_1700_B(op, b, c);
            return f_360_U.n_1700_B(x);
        }
        Matcher m2 = J_1907_R.matcher(line);
        if (m2.find()) {
            double a = Double.parseDouble(m2.group(1));
            String op = m2.group(2);
            double c = Double.parseDouble(m2.group(4));
            double x = f_360_U.n_1700_B(a, op, c);
            return f_360_U.n_1700_B(x);
        }
        return null;
    }

    private static double n_1700_B(String op, double b, double c) {
        return switch (op) {
            case "+" -> c - b;
            case "-" -> c + b;
            case "*" -> c / b;
            case "/" -> c * b;
            default -> Double.NaN;
        };
    }

    private static double n_1700_B(double a, String op, double c) {
        return switch (op) {
            case "+" -> c - a;
            case "-" -> a - c;
            case "*" -> c / a;
            case "/" -> a / c;
            default -> Double.NaN;
        };
    }

    private static String n_1700_B(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            return null;
        }
        long rounded = Math.round(x);
        if (Math.abs(x - (double)rounded) < 1.0E-4) {
            return Long.toString(rounded);
        }
        return Double.toString(x);
    }

    private int n_1700_B(c_1608_O itemSetting) {
        if (this.R_4764_Y == null || this.R_4764_Y.Y_259_p == null || itemSetting == null) {
            return -1;
        }
        q_1613_l targetItem = itemSetting.t_148_a().J_1907_R();
        List<String> requiredNbtParams = itemSetting.h_1847_R();
        String itemName = itemSetting.n_1700_B();
        for (int i = 0; i < 36; ++i) {
            boolean nameMatches;
            Z_1993_T stack = this.R_4764_Y.Y_259_p.l_1268_F.s_956_w(i);
            if (stack.n_1700_B()) continue;
            String cleanDisplayName = f_360_U.n_1700_B(stack.multiplayerClientSuggestionProvider().getString());
            String cleanItemName = f_360_U.n_1700_B(itemName);
            q_1613_l stackItem = stack.J_1907_R();
            boolean bl = nameMatches = cleanDisplayName.contains(cleanItemName) || cleanItemName.contains(cleanDisplayName) || cleanDisplayName.equals(cleanItemName);
            if (nameMatches && (requiredNbtParams.isEmpty() || this.n_1700_B(stack, requiredNbtParams))) {
                return i;
            }
            if (stackItem != targetItem) continue;
            if (requiredNbtParams.isEmpty()) {
                return i;
            }
            if (!this.n_1700_B(stack, requiredNbtParams)) continue;
            return i;
        }
        return -1;
    }

    private boolean n_1700_B(Z_1993_T stack, List<String> requiredParams) {
        U_2912_j display;
        if (requiredParams.isEmpty()) {
            return true;
        }
        U_2912_j nbt = stack.Q_4569_t();
        if (nbt == null) {
            return false;
        }
        if (nbt.R_4764_Y("display", 10) && (display = nbt.M_182_A("display")).R_4764_Y("Lore", 9)) {
            q_2896_o loreList = display.G_564_y("Lore", 8);
            StringBuilder loreText = new StringBuilder();
            for (int i = 0; i < loreList.size(); ++i) {
                String line = loreList.t_148_a(i);
                try {
                    MutableComponent component = x_282_a.n_1700_B.J_1907_R(line);
                    if (component != null) {
                        loreText.append(component.getString().toLowerCase()).append(" ");
                        continue;
                    }
                    loreText.append(line.toLowerCase()).append(" ");
                    continue;
                }
                catch (Exception e) {
                    loreText.append(line.toLowerCase()).append(" ");
                }
            }
            String fullLore = loreText.toString();
            for (String param : requiredParams) {
                if (fullLore.contains(param.toLowerCase())) continue;
                return false;
            }
            return true;
        }
        return false;
    }
}



