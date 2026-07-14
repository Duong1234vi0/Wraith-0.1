/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.lang.invoke.MethodHandle;
import java.lang.runtime.ObjectMethods;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.ContainerScreen;
import lightning.product.D_4024_W;
import lightning.product.ChestMenu;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.g_3316_o;
import lightning.product.h_1015_G;
import lightning.product.k_2603_m;
import lightning.product.v_1900_v;
import lightning.product.x_282_a;
import lightning.product.ModuleCategory;

public class X_812_G
extends Module {
    private final V_4557_X v_4262_N = new V_4557_X();
    private final List<n_1700_B> w_1484_f = new ArrayList<n_1700_B>();
    private int t_148_a = 0;
    private int s_956_w = 27;
    private boolean u_2550_I = false;
    private boolean M_588_G = false;
    private boolean P_4830_p = false;
    private int h_1847_R = 0;
    private static final Pattern Q_4569_t = Pattern.compile("\u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0446\u0435\u043d\u0430:\\s*([\\d,.]+)");

    public X_812_G() {
        super("MarketHelper", "\u0421\u043a\u0430\u043d\u0438\u0440\u0443\u0435\u0442 \u043c\u0430\u0440\u043a\u0435\u0442 \u0434\u043b\u044f \u0432\u044b\u044f\u0432\u043b\u0435\u043d\u0438\u044f \u0441\u0430\u043c\u043e\u0433\u043e \u0431\u043e\u0433\u0430\u0442\u043e\u0433\u043e \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", ModuleCategory.P_1922_E);
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        if (X_812_G.c_3005_b.Y_259_p == null) {
            this.Q_4569_t();
            return;
        }
        this.w_1484_f.clear();
        this.t_148_a = 0;
        this.s_956_w = 29;
        this.u_2550_I = true;
        this.M_588_G = false;
        this.P_4830_p = true;
        this.h_1847_R = 0;
        X_812_G.c_3005_b.Y_259_p.n_1700_B("/market");
        this.v_4262_N.n_1700_B();
        v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.Q_4569_t) + "\u0417\u0430\u043f\u0443\u0449\u0435\u043d \u0441\u043a\u0430\u043d \u043c\u0430\u0440\u043a\u0435\u0442\u0430", new Object[0]);
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.u_2550_I = false;
        this.M_588_G = false;
        this.P_4830_p = false;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (!this.u_2550_I || X_812_G.c_3005_b.Y_259_p == null) {
            return;
        }
        if (this.P_4830_p) {
            ++this.h_1847_R;
            if (X_812_G.c_3005_b.Y_1740_V instanceof ContainerScreen) {
                this.P_4830_p = false;
                this.h_1847_R = 0;
                this.v_4262_N.n_1700_B();
            } else if (this.h_1847_R > 100) {
                v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.P_4830_p) + "\u041d\u0435 \u043f\u043e\u043b\u0443\u0447\u0438\u043b\u043e\u0441\u044c \u043e\u0442\u043a\u0440\u044b\u0442\u044c \u043c\u0430\u0440\u043a\u0435\u0442", new Object[0]);
                this.Q_4569_t();
            }
            return;
        }
        k_2603_m k_2603_m2 = X_812_G.c_3005_b.Y_1740_V;
        if (!(k_2603_m2 instanceof ContainerScreen)) {
            if (this.t_148_a > 0) {
                this.h_1847_R();
            }
            return;
        }
        ContainerScreen screen = (ContainerScreen)k_2603_m2;
        if (!this.v_4262_N.n_1700_B(250.0)) {
            return;
        }
        ChestMenu container = (ChestMenu)screen.n_();
        if (!this.M_588_G) {
            this.n_1700_B(container);
            ++this.t_148_a;
            if (this.t_148_a >= this.s_956_w) {
                this.h_1847_R();
                return;
            }
            this.M_588_G = true;
            this.v_4262_N.n_1700_B();
        } else {
            this.J_1907_R(container);
            this.M_588_G = false;
            this.v_4262_N.n_1700_B();
        }
    }

    private void n_1700_B(ChestMenu container) {
        for (int slot = 10; slot <= 43; ++slot) {
            Z_1993_T stack;
            if (slot % 9 == 0 || slot % 9 == 8 || (stack = container.n_1700_B(slot).n_1700_B()).n_1700_B()) continue;
            String itemName = stack.multiplayerClientSuggestionProvider().getString();
            List<x_282_a> tooltip = stack.n_1700_B(X_812_G.c_3005_b.Y_259_p, g_3316_o.n_1700_B.n_1700_B);
            long minPrice = this.J_1907_R(tooltip);
            if (minPrice <= 0L) continue;
            this.w_1484_f.add(new n_1700_B(itemName, minPrice, stack.t_4043_B()));
        }
    }

    private long J_1907_R(List<x_282_a> tooltip) {
        for (x_282_a line : tooltip) {
            Matcher matcher;
            String text = D_4024_W.n_1700_B(line.getString());
            if (text == null || !(matcher = Q_4569_t.matcher(text)).find()) continue;
            String priceStr = matcher.group(1).replace(",", "").replace(".", "");
            try {
                return Long.parseLong(priceStr);
            }
            catch (NumberFormatException e) {
                return 0L;
            }
        }
        return 0L;
    }

    private void J_1907_R(ChestMenu container) {
        if (X_812_G.c_3005_b.w_1457_N != null && X_812_G.c_3005_b.Y_259_p != null) {
            X_812_G.c_3005_b.w_1457_N.windowClick(container.u_1723_Y, 53, 0, a_408_T.n_1700_B, X_812_G.c_3005_b.Y_259_p);
        }
    }

    private void h_1847_R() {
        this.u_2550_I = false;
        if (X_812_G.c_3005_b.Y_259_p != null) {
            X_812_G.c_3005_b.Y_259_p.P_1922_E();
        }
        if (this.w_1484_f.isEmpty()) {
            v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.P_4830_p) + "\u041b\u043e\u0442\u044b \u043d\u0435 \u043d\u0430\u0439\u0434\u0435\u043d\u044b", new Object[0]);
            this.Q_4569_t();
            return;
        }
        this.w_1484_f.sort((a, b) -> Long.compare(b.J_1907_R(), a.J_1907_R()));
        int count = Math.min(10, this.w_1484_f.size());
        v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.v_4262_N) + "\u0422\u043e\u043f \u0434\u043e\u0440\u043e\u0433\u0438\u0445 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432:", new Object[0]);
        for (int i = 0; i < count; ++i) {
            n_1700_B item = this.w_1484_f.get(i);
            v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.v_4262_N) + String.valueOf(i + 1) + ". " + String.valueOf((Object)D_4024_W.M_182_A) + item.n_1700_B() + String.valueOf((Object)D_4024_W.w_1484_f) + " - " + String.valueOf((Object)D_4024_W.M_182_A) + item.J_1907_R(), new Object[0]);
        }
        this.Q_4569_t();
    }

    private void Q_4569_t() {
        this.n_1700_B(false);
    }

    private record n_1700_B(String n_1700_B, long J_1907_R, int R_4764_Y) {
        @Override
        public final String toString() {
            return ObjectMethods.bootstrap("toString", new MethodHandle[]{n_1700_B.class, "name;price;count", "n_1700_B", "J_1907_R", "R_4764_Y"}, this);
        }

        @Override
        public final int hashCode() {
            return (int)ObjectMethods.bootstrap("hashCode", new MethodHandle[]{n_1700_B.class, "name;price;count", "n_1700_B", "J_1907_R", "R_4764_Y"}, this);
        }

        @Override
        public final boolean equals(Object o) {
            return (boolean)ObjectMethods.bootstrap("equals", new MethodHandle[]{n_1700_B.class, "name;price;count", "n_1700_B", "J_1907_R", "R_4764_Y"}, this, o);
        }
    }
}



