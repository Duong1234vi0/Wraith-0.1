/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.A_2226_Q;
import lightning.product.D_4024_W;
import lightning.product.I_686_h;
import lightning.product.K_1310_v;
import lightning.product.K_4096_w;
import lightning.product.P_3201_s;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.W_3943_o;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_256_c;
import lightning.product.a_408_T;
import lightning.product.c_1608_O;
import lightning.product.c_167_q;
import lightning.product.g_2336_b;
import lightning.product.h_1015_G;
import lightning.product.h_2023_q;
import lightning.product.h_2367_h;
import lightning.product.i_4895_l;
import lightning.product.k_1052_R;
import lightning.product.k_2603_m;
import lightning.product.n_3864_h;
import lightning.product.o_3599_Z;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.t_2598_a;
import lightning.product.v_4839_y;
import lightning.product.x_282_a;
import lightning.product.y_2603_k;
import lightning.product.z_3427_G;

public class A_4727_d
extends X_3546_T {
    private q_366_O w_1484_f = new q_366_O("\u0421\u0435\u0440\u0432\u0435\u0440", "HolyWorld", "HolyWorld", "SpookyTime");
    private p_1977_n t_148_a = new p_1977_n("\u0410\u0432\u0442\u043e \u0441\u0435\u0442\u0430\u043f", false);
    private I_686_h s_956_w = new I_686_h("\u041d\u0430 \u0441\u043a\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u043e\u0446\u0435\u043d\u0442\u043e\u0432 \u043c\u0435\u043d\u044c\u0448\u0435 \u043e\u0442 \u0441\u0430\u043c\u043e\u0433\u043e \u0434\u0435\u0448\u0435\u0432\u043e\u0433\u043e \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430", 20.0f, 1.0f, 100.0f, 1.0f);
    private p_1977_n u_2550_I = new p_1977_n("\u041f\u043e\u0434\u0441\u0432\u0435\u0442\u043a\u0430 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u043e\u0432", true);
    private I_686_h M_588_G = new I_686_h("\u041c\u0438\u043d\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0430\u0432\u0442\u043e\u0441\u0435\u0442\u0430\u043f\u0430 (\u0442\u0438\u043a)", 8.0f, 1.0f, 200.0f, 1.0f);
    private I_686_h P_4830_p = new I_686_h("\u041c\u0430\u043a\u0441\u0438\u043c\u0430\u043b\u044c\u043d\u0430\u044f \u0437\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0430\u0432\u0442\u043e\u0441\u0435\u0442\u0430\u043f\u0430 (\u0442\u0438\u043a)", 16.0f, 1.0f, 400.0f, 1.0f);
    private I_686_h h_1847_R = new I_686_h("\u041c\u0430\u043a\u0441. \u0441\u0442\u0440\u0430\u043d\u0438\u0446 \u0434\u043b\u044f \u043f\u0440\u043e\u0441\u043c\u043e\u0442\u0440\u0430", 5.0f, 1.0f, 20.0f, 1.0f);
    private h_2367_h Q_4569_t = new h_2367_h("\u0426\u0432\u0435\u0442 (\u043c\u043e\u0436\u043d\u043e \u043a\u0443\u043f\u0438\u0442\u044c)", true, 1677786880, () -> this.u_2550_I.t_148_a());
    private h_2367_h M_182_A = new h_2367_h("\u0426\u0432\u0435\u0442 (\u0434\u043e\u0440\u043e\u0433\u043e)", true, 1694476800, () -> this.u_2550_I.t_148_a());
    private p_1977_n t_1786_h = new p_1977_n("\u0423\u0447\u0438\u0442\u044b\u0432\u0430\u0442\u044c \u0431\u0430\u043b\u0430\u043d\u0441", true);
    public static h_2023_q v_4262_N = new h_2023_q("AutoBuy", false, "\u041e\u0442\u043a\u0440\u044b\u0442\u044c \u043c\u0435\u043d\u044e Auto Buy", "\u0417\u0430\u043a\u0440\u044b\u0442\u044c \u043c\u0435\u043d\u044e Auto Buy");
    private static final Pattern N_4405_n = Pattern.compile("\\d[\\d\\s]*");
    private long w_1457_N = -1L;
    private int Y_601_j = 0;
    private boolean Y_259_p = false;
    private c_1608_O Q_2552_b = null;
    private boolean C_2741_M = false;
    private long k_2293_S = 0L;
    private boolean q_2307_F = false;
    private int Z_875_P = 1;
    private Z_1993_T t_4043_B = null;
    private long x_607_J = 0L;
    private final List<n_1700_B> e_4240_b = new ArrayList<n_1700_B>();
    private int n_3318_d = 0;
    private int d_2427_y = 0;
    private int z_1737_N = 0;
    private boolean v_4276_D = false;
    private final List<c_1608_O> d_2461_k = new ArrayList<c_1608_O>();
    private int G_624_v = 0;
    private int T_2506_i = 0;
    private long q_4610_l = Long.MAX_VALUE;
    private boolean z_4693_k = false;
    private final Map<c_1608_O, Long> g_221_o = new HashMap<c_1608_O, Long>();
    private int e_2887_G = 0;
    private boolean B_1668_F = false;
    private int g_164_R = 0;
    private int X_933_l = -1;
    private int Z_976_R = -1;
    private int H_1990_U = 0;
    private final int[] N_2525_X = new int[45];
    private List<c_1608_O> c_4037_x = null;
    private boolean g_2268_R = false;

    public A_4727_d() {
        super("AutoBuy", y_2603_k.G_564_y);
        this.n_1700_B(this.w_1484_f, this.t_148_a, this.s_956_w, this.M_588_G, this.P_4830_p, this.h_1847_R, this.u_2550_I, this.Q_4569_t, this.M_182_A, this.t_1786_h, v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(n_3864_h.n_1700_B e) {
    }

    public String h_1847_R() {
        return (String)this.w_1484_f.J_1907_R();
    }

    public boolean Q_4569_t() {
        return this.g_2268_R;
    }

    public void P_1922_E(boolean enabled) {
        this.g_2268_R = enabled;
    }

    public void M_182_A() {
        this.P_1922_E(!this.g_2268_R);
    }

    public boolean t_1786_h() {
        return this.t_148_a.t_148_a();
    }

    public void N_4405_n() {
        this.t_148_a.n_1700_B((Boolean)(this.t_148_a.t_148_a() == false ? 1 : 0));
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (A_4727_d.c_3005_b.Y_259_p == null) {
            return;
        }
        this.q_2307_F();
        if (!this.C_2741_M) {
            return;
        }
        if (A_4727_d.c_3005_b.Y_1740_V != null) {
            this.C_2741_M();
            return;
        }
        if (!this.q_2307_F) {
            int desired;
            int sourceCount;
            int invSlot = this.n_1700_B(this.Q_2552_b);
            if (invSlot >= 0 && (sourceCount = A_4727_d.c_3005_b.Y_259_p.l_1268_F.s_956_w(invSlot).t_4043_B()) >= (desired = Math.max(1, this.Z_875_P))) {
                int targetSlot = sourceCount == desired ? invSlot : this.n_1700_B(invSlot, desired);
                int currentHotbar = A_4727_d.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                if (targetSlot >= 0 && targetSlot < 9 && targetSlot == currentHotbar) {
                    A_4727_d.c_3005_b.w_1457_N.syncCurrentPlayItem();
                    A_4727_d.c_3005_b.Y_259_p.P_1922_E();
                    this.q_2307_F = true;
                    return;
                }
                int safeHotbar = this.P_1922_E(A_4727_d.c_3005_b.Y_259_p.l_1268_F.s_956_w(targetSlot));
                if (safeHotbar != -1) {
                    A_4727_d.c_3005_b.Y_259_p.l_1268_F.G_564_y = safeHotbar;
                    A_4727_d.c_3005_b.w_1457_N.syncCurrentPlayItem();
                }
                int windowId = A_4727_d.c_3005_b.Y_259_p.H_1873_g.u_1723_Y;
                int windowSlot = targetSlot < 9 ? targetSlot + 36 : targetSlot;
                int hotbarIndex = A_4727_d.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                A_4727_d.c_3005_b.w_1457_N.windowClick(windowId, windowSlot, hotbarIndex, a_408_T.R_4764_Y, A_4727_d.c_3005_b.Y_259_p);
                A_4727_d.c_3005_b.w_1457_N.syncCurrentPlayItem();
                A_4727_d.c_3005_b.Y_259_p.P_1922_E();
                this.q_2307_F = true;
            }
            return;
        }
        A_4727_d.c_3005_b.Y_259_p.n_1700_B("/ah sell " + this.k_2293_S);
        this.c_3005_b();
        if (this.e_4240_b.isEmpty()) {
            this.d_2427_y = 25;
        }
    }

    @Y_1740_V
    public void n_1700_B(o_3599_Z e) {
        String title;
        int i;
        String currentTitle;
        boolean isAuctionScreen;
        String title2;
        if (A_4727_d.c_3005_b.Y_259_p == null || c_3005_b.k_2293_S() == null) {
            this.Q_2552_b();
            return;
        }
        if (!this.g_2268_R && !this.t_148_a.t_148_a().booleanValue()) {
            this.Q_2552_b();
            return;
        }
        if (A_4727_d.c_3005_b.Y_259_p.H_1873_g == null && !this.C_2741_M && !this.t_148_a.t_148_a().booleanValue()) {
            this.Q_2552_b();
            return;
        }
        ++this.g_164_R;
        this.c_4037_x = Z_256_c.n_1700_B((String)this.w_1484_f.J_1907_R());
        if (this.t_148_a.t_148_a().booleanValue()) {
            this.J_1907_R(e);
            return;
        }
        if (this.v_4276_D) {
            this.v_4276_D = false;
            this.d_2461_k.clear();
        }
        if (this.n_3318_d > 0) {
            --this.n_3318_d;
        }
        if (this.d_2427_y > 0 && --this.d_2427_y == 0) {
            A_4727_d.c_3005_b.Y_259_p.n_1700_B("/ah");
        }
        if (this.z_1737_N > 0 && --this.z_1737_N == 0) {
            this.q_2307_F();
        }
        if (this.Y_259_p && (title2 = this.k_2293_S()) != null && title2.contains("\u041f\u043e\u043a\u0443\u043f\u043a\u0430 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430") && !A_4727_d.c_3005_b.Y_259_p.H_1873_g.P_1922_E.isEmpty()) {
            if (this.t_4043_B != null) {
                long totalPrice = this.x_607_J * (long)Math.max(1, this.Z_875_P);
                t_2598_a.n_1700_B(this.t_4043_B, totalPrice, this.Z_875_P, false);
            }
            this.J_1907_R(0);
            this.Y_259_p = false;
            if (this.Q_2552_b != null && this.Q_2552_b.M_588_G()) {
                long unitBuyPrice = this.Q_2552_b.u_2550_I();
                long unitSellPrice = unitBuyPrice + Math.round((double)unitBuyPrice * ((double)this.Q_2552_b.P_4830_p() / 100.0));
                long totalSellPrice = unitSellPrice * (long)Math.max(1, this.Z_875_P);
                this.e_4240_b.add(new n_1700_B(this.Q_2552_b, this.Z_875_P, totalSellPrice));
            }
            this.n_3318_d = Math.max(this.n_3318_d, 25);
            this.z_1737_N = 5;
            this.t_4043_B = null;
            this.x_607_J = 0L;
            return;
        }
        t_2598_a.n_1700_B pending = t_2598_a.n_1700_B();
        if (pending != null && A_4727_d.c_3005_b.Y_259_p != null) {
            int requiredTicks;
            pending.P_1922_E();
            int n = requiredTicks = pending.u_1723_Y() ? 5 : 10;
            if (pending.G_564_y() >= requiredTicks) {
                Z_1993_T expectedItem = pending.n_1700_B();
                int totalFound = 0;
                for (int i2 = 0; i2 < 36; ++i2) {
                    Z_1993_T invStack = A_4727_d.c_3005_b.Y_259_p.l_1268_F.s_956_w(i2);
                    if (invStack.n_1700_B() || invStack.J_1907_R() != expectedItem.J_1907_R()) continue;
                    totalFound += invStack.t_4043_B();
                }
                if (totalFound >= pending.R_4764_Y()) {
                    t_2598_a.n_1700_B(expectedItem, pending.J_1907_R(), pending.R_4764_Y());
                }
                t_2598_a.J_1907_R();
            }
        }
        boolean bl = isAuctionScreen = (currentTitle = this.k_2293_S()) != null && (currentTitle.contains("\u0410\u0443\u043a\u0446\u0438\u043e\u043d") || currentTitle.contains("\u041f\u043e\u0438\u0441\u043a:"));
        if (!isAuctionScreen && !this.Y_259_p) {
            this.Z_976_R = -1;
            return;
        }
        c_1608_O matchedSetting = null;
        int foundSlot = -1;
        int maxSlot = Math.min(45, e.R_4764_Y().size());
        List<c_1608_O> activeSettings = this.c_4037_x;
        int colorBuy = (Integer)this.Q_4569_t.J_1907_R();
        int colorExpensive = (Integer)this.M_182_A.J_1907_R();
        for (i = maxSlot; i < this.N_2525_X.length; ++i) {
            this.N_2525_X[i] = 0;
        }
        for (i = 0; i < maxSlot; ++i) {
            this.N_2525_X[i] = 0;
        }
        block3: for (i = 0; i < maxSlot; ++i) {
            long price;
            Z_1993_T stack = e.R_4764_Y().get(i);
            if (stack == null || stack.n_1700_B() || this.J_1907_R(stack) || (price = A_4727_d.n_1700_B(stack)) <= 0L) continue;
            for (c_1608_O setting : activeSettings) {
                int highlightColor;
                if (!((Boolean)setting.J_1907_R()).booleanValue() || setting.t_148_a() != null && stack.J_1907_R() != setting.t_148_a().J_1907_R() || !this.n_1700_B(stack, setting)) continue;
                int count = Math.max(1, stack.t_4043_B());
                long pricePerItem = price / (long)count;
                if (setting.u_2550_I() > 0L && pricePerItem <= setting.u_2550_I()) {
                    if (this.t_1786_h.t_148_a().booleanValue() && (this.w_1457_N < 0L || price > this.w_1457_N)) {
                        this.N_2525_X[i] = colorExpensive;
                        continue block3;
                    }
                    this.N_2525_X[i] = highlightColor = colorBuy;
                    if (foundSlot >= 0) continue block3;
                    foundSlot = i;
                    matchedSetting = setting;
                    this.Z_875_P = count;
                    continue block3;
                }
                this.N_2525_X[i] = highlightColor = colorExpensive;
                continue block3;
            }
        }
        this.X_933_l = this.g_164_R;
        this.Z_976_R = A_4727_d.c_3005_b.Y_259_p.H_1873_g != null ? A_4727_d.c_3005_b.Y_259_p.H_1873_g.u_1723_Y : -1;
        this.H_1990_U = maxSlot;
        if (foundSlot >= 0) {
            Z_1993_T purchasedStack = e.R_4764_Y().get(foundSlot);
            long purchasedPrice = A_4727_d.n_1700_B(purchasedStack);
            if ("SpookyTime".equals(this.w_1484_f.J_1907_R())) {
                this.R_4764_Y(foundSlot);
                if (purchasedStack != null && purchasedPrice > 0L) {
                    t_2598_a.n_1700_B(purchasedStack, purchasedPrice, this.Z_875_P, true);
                }
                this.Q_2552_b = matchedSetting;
                if (this.Q_2552_b != null && this.Q_2552_b.M_588_G()) {
                    long unitBuyPrice = this.Q_2552_b.u_2550_I();
                    long unitSellPrice = unitBuyPrice + Math.round((double)unitBuyPrice * ((double)this.Q_2552_b.P_4830_p() / 100.0));
                    long totalSellPrice = unitSellPrice * (long)Math.max(1, this.Z_875_P);
                    this.e_4240_b.add(new n_1700_B(this.Q_2552_b, this.Z_875_P, totalSellPrice));
                }
                this.n_3318_d = Math.max(this.n_3318_d, 25);
            } else {
                this.J_1907_R(foundSlot);
                this.Y_259_p = true;
                this.Q_2552_b = matchedSetting;
                this.t_4043_B = purchasedStack;
                this.x_607_J = purchasedPrice;
            }
            this.Y_601_j = 0;
            return;
        }
        ++this.Y_601_j;
        int refreshSlot = "SpookyTime".equals(this.w_1484_f.J_1907_R()) ? 49 : 47;
        int refreshInterval = 6;
        if (!(this.Y_601_j % refreshInterval != 0 || A_4727_d.c_3005_b.Y_259_p.H_1873_g.P_1922_E.size() <= refreshSlot || (title = this.k_2293_S()) != null && title.contains("\u041f\u043e\u043a\u0443\u043f\u043a\u0430 \u043f\u0440\u0435\u0434\u043c\u0435\u0442\u0430"))) {
            this.J_1907_R(refreshSlot);
        }
        if (!(this.C_2741_M || this.Y_259_p || this.e_4240_b.isEmpty() || this.n_3318_d > 0 || this.Y_601_j < 3)) {
            n_1700_B task = this.e_4240_b.remove(0);
            this.Q_2552_b = task.n_1700_B;
            this.Z_875_P = Math.max(1, task.J_1907_R);
            this.C_2741_M = true;
            this.k_2293_S = task.R_4764_Y;
            this.q_2307_F = false;
        }
    }

    private void J_1907_R(o_3599_Z e) {
        if (!this.v_4276_D) {
            this.d_2461_k.clear();
            for (c_1608_O setting : Z_256_c.n_1700_B((String)this.w_1484_f.J_1907_R())) {
                if (!((Boolean)setting.J_1907_R()).booleanValue()) continue;
                this.d_2461_k.add(setting);
            }
            this.G_624_v = 0;
            if (!this.d_2461_k.isEmpty()) {
                this.v_4276_D = true;
                this.g_221_o.clear();
                for (c_1608_O s : this.d_2461_k) {
                    this.g_221_o.put(s, Long.MAX_VALUE);
                }
                this.e_2887_G = 0;
                this.B_1668_F = false;
                this.Y_259_p();
            }
            return;
        }
        if (this.T_2506_i > 0) {
            --this.T_2506_i;
            return;
        }
        c_1608_O current = this.d_2461_k.get(this.G_624_v);
        String title = this.k_2293_S();
        if (title == null || !title.contains("\u0410\u0443\u043a\u0446\u0438\u043e\u043d") && !title.contains("\u041f\u043e\u0438\u0441\u043a:")) {
            return;
        }
        if (this.B_1668_F) {
            int nextPageSlot = this.Y_601_j();
            if (nextPageSlot >= 0 && A_4727_d.c_3005_b.Y_259_p.H_1873_g.P_1922_E.size() > nextPageSlot) {
                this.J_1907_R(nextPageSlot);
                this.B_1668_F = false;
                this.z_4693_k = false;
                int minDelay = Math.max(1, Math.round(((Float)this.M_588_G.J_1907_R()).floatValue()));
                int maxDelay = Math.max(minDelay, Math.round(((Float)this.P_4830_p.J_1907_R()).floatValue()));
                this.T_2506_i = ThreadLocalRandom.current().nextInt(minDelay, maxDelay + 1);
            }
            return;
        }
        if (!this.z_4693_k) {
            int foundItems = 0;
            for (int i = 0; i < Math.min(45, e.R_4764_Y().size()); ++i) {
                long curMin;
                long price;
                Z_1993_T stack = e.R_4764_Y().get(i);
                if (stack == null || stack.n_1700_B() || this.J_1907_R(stack) || (price = A_4727_d.n_1700_B(stack)) <= 0L || current.t_148_a() != null && stack.J_1907_R() != current.t_148_a().J_1907_R() || !this.n_1700_B(stack, current)) continue;
                ++foundItems;
                int count = Math.max(1, stack.t_4043_B());
                long pricePerItem = price / (long)count;
                if (pricePerItem < (curMin = this.g_221_o.getOrDefault(current, Long.MAX_VALUE).longValue())) {
                    this.g_221_o.put(current, pricePerItem);
                }
                if (pricePerItem >= this.q_4610_l) continue;
                this.q_4610_l = pricePerItem;
            }
            this.z_4693_k = true;
            ++this.e_2887_G;
            boolean hasNextPage = this.J_1907_R(e.R_4764_Y());
            if (hasNextPage && this.e_2887_G < (int)((Float)this.h_1847_R.J_1907_R()).floatValue() && foundItems > 0) {
                this.B_1668_F = true;
                return;
            }
            long finalMin = this.g_221_o.getOrDefault(current, this.q_4610_l);
            if (finalMin != Long.MAX_VALUE) {
                long adjusted = this.n_1700_B(finalMin);
                current.n_1700_B(adjusted);
            }
            ++this.G_624_v;
            this.e_2887_G = 0;
            this.B_1668_F = false;
            if (this.G_624_v < this.d_2461_k.size()) {
                this.Y_259_p();
            } else {
                this.v_4276_D = false;
                this.d_2461_k.clear();
                this.G_624_v = 0;
                this.t_148_a.n_1700_B((Boolean)false);
                this.C_2741_M();
                this.Q_2552_b();
            }
        }
    }

    private boolean J_1907_R(List<Z_1993_T> items) {
        int nextPageSlot = this.Y_601_j();
        if (nextPageSlot < 0 || nextPageSlot >= items.size()) {
            return false;
        }
        Z_1993_T nextButton = items.get(nextPageSlot);
        if (nextButton == null || nextButton.n_1700_B()) {
            return false;
        }
        String name = nextButton.N_4405_n().getString().toLowerCase();
        return name.contains("\u0432\u043f\u0435\u0440\u0435\u0434") || name.contains("\u0432\u043f\u0435\u0440\u0451\u0434") || name.contains("next") || name.contains("\u2192") || name.contains("\u25b6") || name.contains(">");
    }

    private int Y_601_j() {
        if ("SpookyTime".equals(this.w_1484_f.J_1907_R())) {
            return 53;
        }
        return 53;
    }

    private void Y_259_p() {
        String name;
        long known;
        c_1608_O current = this.d_2461_k.get(this.G_624_v);
        while ((known = this.g_221_o.getOrDefault(current, Long.MAX_VALUE).longValue()) != Long.MAX_VALUE) {
            current.n_1700_B(this.n_1700_B(known));
            ++this.G_624_v;
            if (this.G_624_v >= this.d_2461_k.size()) {
                this.v_4276_D = false;
                this.d_2461_k.clear();
                this.G_624_v = 0;
                this.t_148_a.n_1700_B((Boolean)false);
                this.C_2741_M();
                this.Q_2552_b();
                return;
            }
            current = this.d_2461_k.get(this.G_624_v);
        }
        String string = current.t_1786_h() != null && !current.t_1786_h().isEmpty() ? current.t_1786_h() : (name = current.n_1700_B() == null ? "" : current.n_1700_B());
        if (!name.isEmpty()) {
            int delay;
            A_4727_d.c_3005_b.Y_259_p.n_1700_B("/ah search " + name);
            int minDelay = Math.max(1, Math.round(((Float)this.M_588_G.J_1907_R()).floatValue()));
            int maxDelay = Math.max(minDelay, Math.round(((Float)this.P_4830_p.J_1907_R()).floatValue()));
            this.T_2506_i = delay = ThreadLocalRandom.current().nextInt(minDelay, maxDelay + 1);
        }
        this.q_4610_l = Long.MAX_VALUE;
        this.z_4693_k = false;
    }

    private long n_1700_B(long minPrice) {
        if (minPrice <= 0L || minPrice == Long.MAX_VALUE) {
            return 0L;
        }
        float p = ((Float)this.s_956_w.J_1907_R()).floatValue();
        double factor = Math.max(0.0, 1.0 - (double)p / 100.0);
        long adjusted = (long)Math.floor((double)minPrice * factor);
        return Math.max(0L, adjusted);
    }

    private void Q_2552_b() {
        this.Y_601_j = 0;
        this.Y_259_p = false;
        this.C_2741_M = false;
        this.v_4276_D = false;
        this.d_2461_k.clear();
        this.g_221_o.clear();
        this.Z_976_R = -1;
        this.X_933_l = -1;
        this.H_1990_U = 0;
    }

    private void C_2741_M() {
        c_3005_b.n_1700_B((k_2603_m)null);
        A_4727_d.c_3005_b.Y_259_p.P_1922_E();
    }

    private String k_2293_S() {
        return A_4727_d.c_3005_b.Y_1740_V instanceof z_3427_G ? A_4727_d.c_3005_b.Y_1740_V.getTitle().getString() : null;
    }

    private void J_1907_R(int slot) {
        c_3005_b.k_2293_S().n_1700_B(new P_3201_s(A_4727_d.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slot, 0, a_408_T.n_1700_B, Z_1993_T.J_1907_R, A_4727_d.c_3005_b.Y_259_p.H_1873_g.n_1700_B(A_4727_d.c_3005_b.Y_259_p.l_1268_F)));
    }

    private void R_4764_Y(int slot) {
        A_4727_d.c_3005_b.w_1457_N.windowClick(A_4727_d.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, slot, 0, a_408_T.J_1907_R, A_4727_d.c_3005_b.Y_259_p);
    }

    private boolean J_1907_R(Z_1993_T stack) {
        String name;
        if (stack == null || stack.n_1700_B()) {
            return true;
        }
        q_1613_l item = stack.J_1907_R();
        if (item == q_4592_V.V_983_n || item == q_4592_V.A_2863_p || item == q_4592_V.U_4523_X || item == q_4592_V.r_3815_v || item == q_4592_V.n_94_R || item == q_4592_V.j_4556_h || item == q_4592_V.a_2725_z || item == q_4592_V.K_4427_C || item == q_4592_V.b_4074_q || item == q_4592_V.S_2721_L || item == q_4592_V.w_1297_x || item == q_4592_V.E_3014_r || item == q_4592_V.a_3144_E || item == q_4592_V.U_4792_T || item == q_4592_V.a_3583_t || item == q_4592_V.O_196_G || item == q_4592_V.U_4087_m) {
            return true;
        }
        if ((item == q_4592_V.g_24_p || item == q_4592_V.g_2783_J || item == q_4592_V.p_1838_W) && ((name = stack.N_4405_n().getString().toLowerCase()).contains("\u043d\u0430\u0437\u0430\u0434") || name.contains("\u0432\u043f\u0435\u0440\u0435\u0434") || name.contains("\u0441\u0442\u0440\u0430\u043d\u0438\u0446") || name.contains("back") || name.contains("next") || name.contains("previous") || name.contains("\u25c0") || name.contains("\u25b6") || name.contains("\u2190") || name.contains("\u2192"))) {
            return true;
        }
        if (item == q_4592_V.P_2452_o) {
            return true;
        }
        if (item == q_4592_V.D_3097_e) {
            return true;
        }
        if (!stack.h_1847_R()) {
            return true;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null) {
            return true;
        }
        if (!tag.R_4764_Y("display", 10)) {
            return true;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return true;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        if (lore.size() < 2) {
            return true;
        }
        boolean hasPrice = false;
        for (int i = 0; i < lore.size(); ++i) {
            String text;
            String rawLore = lore.t_148_a(i);
            try {
                text = x_282_a.n_1700_B.J_1907_R(rawLore).getString().toLowerCase();
            }
            catch (Exception e) {
                text = rawLore.toLowerCase();
            }
            if (!text.contains("\u0446\u0435\u043d\u0430")) continue;
            hasPrice = true;
            break;
        }
        return !hasPrice;
    }

    private void q_2307_F() {
        Object objective;
        if (A_4727_d.c_3005_b.Y_601_j == null) {
            this.w_1457_N = -1L;
            return;
        }
        i_4895_l scoreboard = A_4727_d.c_3005_b.Y_601_j.Q_4569_t();
        if (scoreboard != null && (objective = scoreboard.n_1700_B(1)) != null) {
            Collection<v_4839_y> scores = scoreboard.n_1700_B((W_3943_o)objective);
            for (v_4839_y score : scores) {
                String numStr;
                String lower;
                String line;
                String playerName = score.P_1922_E();
                if (playerName == null || (line = this.n_1700_B(scoreboard, playerName)) == null || line.isEmpty() || !(lower = line.toLowerCase()).contains("\u043c\u043e\u043d\u0435\u0442") || (numStr = line.replaceAll("[^0-9]", "")).isEmpty()) continue;
                try {
                    this.w_1457_N = Long.parseLong(numStr);
                    return;
                }
                catch (NumberFormatException numberFormatException) {
                }
            }
        }
        if (c_3005_b.k_2293_S() != null) {
            for (A_2226_Q info : c_3005_b.k_2293_S().P_1922_E()) {
                String numStr;
                String lower;
                String displayText;
                if (info.u_2550_I() == null || (displayText = D_4024_W.n_1700_B(info.u_2550_I().getString())) == null || !(lower = displayText.toLowerCase()).contains("\u043c\u043e\u043d\u0435\u0442") && (!lower.contains("$") || !lower.contains(":")) || (numStr = displayText.replaceAll("[^0-9]", "")).isEmpty()) continue;
                try {
                    this.w_1457_N = Long.parseLong(numStr);
                    return;
                }
                catch (NumberFormatException line) {
                }
            }
            if (A_4727_d.c_3005_b.M_588_G != null && A_4727_d.c_3005_b.M_588_G.v_4262_N() != null) {
                x_282_a header = A_4727_d.c_3005_b.M_588_G.v_4262_N().J_1907_R();
                x_282_a footer = A_4727_d.c_3005_b.M_588_G.v_4262_N().R_4764_Y();
                for (x_282_a component : new x_282_a[]{header, footer}) {
                    String text;
                    if (component == null || (text = D_4024_W.n_1700_B(component.getString())) == null) continue;
                    for (String line : text.split("\n")) {
                        String numStr;
                        String lineLower = line.toLowerCase().trim();
                        if (!lineLower.contains("\u043c\u043e\u043d\u0435\u0442") && !lineLower.contains("\u0431\u0430\u043b\u0430\u043d\u0441") && !lineLower.contains("coins") && !lineLower.contains("balance") && !lineLower.contains("money") && !lineLower.contains("$") || (numStr = line.replaceAll("[^0-9]", "")).isEmpty() || numStr.length() < 1) continue;
                        try {
                            long parsed = Long.parseLong(numStr);
                            if (parsed <= 0L) continue;
                            this.w_1457_N = parsed;
                            return;
                        }
                        catch (NumberFormatException numberFormatException) {
                            // empty catch block
                        }
                    }
                }
            }
        }
    }

    public long w_1457_N() {
        return this.w_1457_N;
    }

    private String n_1700_B(i_4895_l scoreboard, String playerName) {
        c_167_q team = scoreboard.w_1484_f(playerName);
        if (team != null) {
            String prefix = D_4024_W.n_1700_B(team.G_564_y().getString());
            String suffix = D_4024_W.n_1700_B(team.P_1922_E().getString());
            String name = D_4024_W.n_1700_B(playerName);
            return (prefix != null ? prefix : "") + (name != null ? name : "") + (suffix != null ? suffix : "");
        }
        return D_4024_W.n_1700_B(playerName);
    }

    public static long n_1700_B(Z_1993_T stack) {
        if (stack == null || stack.n_1700_B() || !stack.h_1847_R()) {
            return -1L;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return -1L;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return -1L;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        for (int i = 0; i < lore.size(); ++i) {
            String priceText;
            String text;
            String rawLore = lore.t_148_a(i);
            try {
                text = x_282_a.n_1700_B.J_1907_R(rawLore).getString();
            }
            catch (Exception e) {
                continue;
            }
            if (!text.contains("\u0446\u0435\u043d\u0430") && !text.contains("\u0426\u0435\u043d\u0430") || (priceText = text.replaceAll("[^0-9]", "")).isEmpty()) continue;
            try {
                return Long.parseLong(priceText);
            }
            catch (NumberFormatException numberFormatException) {
                // empty catch block
            }
        }
        return -1L;
    }

    private boolean n_1700_B(Z_1993_T stack, c_1608_O setting) {
        String itemName;
        String displayName;
        if (!this.J_1907_R(stack, setting)) {
            return false;
        }
        if (setting.h_1847_R() != null && !setting.h_1847_R().isEmpty()) {
            List<String> loreLines = this.R_4764_Y(stack);
            if (loreLines.isEmpty()) {
                return false;
            }
            Pattern reqWithRoman = Pattern.compile("^(.+?)\\s+([IVXLCDM]+)\\s*$", 2);
            Pattern romanToken = Pattern.compile("\\b[IVXLCDM]+\\b", 2);
            for (String req : setting.h_1847_R()) {
                String trimmed;
                if (req == null || (trimmed = req.trim()).isEmpty()) continue;
                Matcher m = reqWithRoman.matcher(trimmed);
                boolean satisfied = false;
                if (m.matches()) {
                    String base = m.group(1).trim().toLowerCase();
                    int needLevel = this.R_4764_Y(m.group(2));
                    for (String line : loreLines) {
                        Matcher rm;
                        String lower = line.toLowerCase();
                        int idx = lower.indexOf(base);
                        if (idx == -1 || !(rm = romanToken.matcher(lower.substring(idx + base.length()))).find() || this.R_4764_Y(rm.group()) < needLevel) continue;
                        satisfied = true;
                        break;
                    }
                } else {
                    String needle = trimmed.toLowerCase();
                    for (String line : loreLines) {
                        if (!line.toLowerCase().contains(needle)) continue;
                        satisfied = true;
                        break;
                    }
                }
                if (satisfied) continue;
                return false;
            }
        }
        String string = displayName = stack.N_4405_n() != null ? stack.N_4405_n().getString() : "";
        if ("SpookyTime".equals(this.w_1484_f.J_1907_R()) && (itemName = setting.n_1700_B()) != null && !itemName.isEmpty()) {
            boolean attributesMatch;
            String lowerName = itemName.toLowerCase();
            String lowerDisplayName = displayName.toLowerCase();
            boolean bl = attributesMatch = k_1052_R.n_1700_B(stack) || k_1052_R.J_1907_R(stack) || k_1052_R.R_4764_Y(stack) || k_1052_R.G_564_y(stack) || k_1052_R.P_1922_E(stack) || k_1052_R.u_1723_Y(stack) || k_1052_R.v_4262_N(stack) || k_1052_R.w_1484_f(stack) || k_1052_R.t_148_a(stack) || k_1052_R.s_956_w(stack) || k_1052_R.u_2550_I(stack) || k_1052_R.M_588_G(stack) || k_1052_R.P_4830_p(stack) || k_1052_R.h_1847_R(stack) || k_1052_R.Q_4569_t(stack) || k_1052_R.M_182_A(stack) || k_1052_R.t_1786_h(stack) || k_1052_R.N_4405_n(stack) || k_1052_R.w_1457_N(stack) || k_1052_R.Y_601_j(stack) || k_1052_R.Y_259_p(stack) || k_1052_R.Q_2552_b(stack) || k_1052_R.C_2741_M(stack) || k_1052_R.k_2293_S(stack) || k_1052_R.q_2307_F(stack) || k_1052_R.Z_875_P(stack) || k_1052_R.c_3005_b(stack) || k_1052_R.t_4043_B(stack) || k_1052_R.Y_1740_V(stack) || k_1052_R.H_2857_Y(stack) || k_1052_R.A_4115_X(stack) || k_1052_R.x_607_J(stack) || k_1052_R.e_4240_b(stack) || k_1052_R.n_3318_d(stack) || k_1052_R.d_2427_y(stack) || k_1052_R.z_1737_N(stack) || k_1052_R.v_4276_D(stack) || k_1052_R.d_2461_k(stack) || k_1052_R.G_624_v(stack) || k_1052_R.z_4693_k(stack) || k_1052_R.g_221_o(stack) || k_1052_R.e_2887_G(stack) || k_1052_R.B_1668_F(stack) || k_1052_R.g_164_R(stack) || k_1052_R.N_2525_X(stack) || k_1052_R.c_4037_x(stack) || k_1052_R.g_2268_R(stack) || k_1052_R.T_3594_S(stack);
            if (attributesMatch) {
                return lowerDisplayName.contains(lowerName);
            }
            return k_1052_R.n_1700_B(stack, itemName);
        }
        if (setting.t_1786_h() != null && !setting.t_1786_h().isEmpty() && !displayName.toLowerCase().contains(setting.t_1786_h().toLowerCase())) {
            return false;
        }
        if (setting.t_148_a() != null && stack.J_1907_R() != setting.t_148_a().J_1907_R()) {
            return false;
        }
        if (stack.h_1847_R()) {
            U_2912_j tag = stack.Q_4569_t();
            if (setting.M_182_A()) {
                return tag.t_1786_h("Unbreakable") || tag.u_1723_Y("Unbreakable") != 0;
            }
        }
        return true;
    }

    private int R_4764_Y(String roman) {
        if (roman == null) {
            return 0;
        }
        Map<Character, Integer> map = Map.of(Character.valueOf('I'), 1, Character.valueOf('V'), 5, Character.valueOf('X'), 10, Character.valueOf('L'), 50, Character.valueOf('C'), 100, Character.valueOf('D'), 500, Character.valueOf('M'), 1000);
        int sum = 0;
        int prev = 0;
        for (char c : new StringBuilder(roman.toUpperCase().trim()).reverse().toString().toCharArray()) {
            int val = map.getOrDefault(Character.valueOf(c), 0);
            sum += val < prev ? -val : val;
            prev = val;
        }
        return sum;
    }

    private boolean n_1700_B(U_2912_j tag, c_1608_O setting) {
        Map<String, Integer> req = setting.Q_4569_t();
        if (req == null || req.isEmpty()) {
            return true;
        }
        HashMap<String, Integer> present = new HashMap<String, Integer>();
        if (tag.R_4764_Y("Enchantments", 9)) {
            q_2896_o ench = tag.G_564_y("Enchantments", 10);
            for (int i = 0; i < ench.size(); ++i) {
                U_2912_j e = ench.n_1700_B(i);
                String id = e.M_588_G("id").toLowerCase();
                int lvl = e.w_1484_f("lvl");
                if (id.isEmpty()) continue;
                present.put(id, Math.max(present.getOrDefault(id, 0), lvl));
            }
        }
        return req.entrySet().stream().allMatch(n -> present.getOrDefault(n.getKey(), 0) >= (Integer)n.getValue());
    }

    private boolean J_1907_R(Z_1993_T stack, c_1608_O setting) {
        Map<String, Integer> req = setting.Q_4569_t();
        if (req == null || req.isEmpty()) {
            return true;
        }
        HashMap<String, Integer> present = new HashMap<String, Integer>();
        if (stack.h_1847_R() && stack.Q_4569_t().R_4764_Y("Enchantments", 9)) {
            U_2912_j tag = stack.Q_4569_t();
            q_2896_o ench = tag.G_564_y("Enchantments", 10);
            for (int i = 0; i < ench.size(); ++i) {
                U_2912_j e = ench.n_1700_B(i);
                String id = e.M_588_G("id").toLowerCase();
                int lvl = e.w_1484_f("lvl");
                if (id.isEmpty()) continue;
                present.put(id, Math.max(present.getOrDefault(id, 0), lvl));
            }
        } else {
            Map<K_1310_v, Integer> enchMap = K_4096_w.n_1700_B(stack);
            for (Map.Entry<K_1310_v, Integer> en : enchMap.entrySet()) {
                K_1310_v ench = en.getKey();
                int lvl = en.getValue();
                g_2336_b key = V_3137_a.z_4693_k.J_1907_R(ench);
                String id = key != null ? key.toString().toLowerCase() : ench.v_4262_N().toLowerCase();
                if (id.isEmpty()) continue;
                present.put(id, Math.max(present.getOrDefault(id, 0), lvl));
            }
        }
        return req.entrySet().stream().allMatch(n -> present.getOrDefault(n.getKey(), 0) >= (Integer)n.getValue());
    }

    private List<String> R_4764_Y(Z_1993_T stack) {
        U_2912_j display;
        ArrayList<String> lines = new ArrayList<String>();
        if (stack.h_1847_R() && (display = stack.Q_4569_t().M_182_A("display")).R_4764_Y("Lore", 9)) {
            q_2896_o lore = display.G_564_y("Lore", 8);
            for (int i = 0; i < lore.size(); ++i) {
                String text = x_282_a.n_1700_B.J_1907_R(lore.t_148_a(i)).getString();
                if (text.isEmpty()) continue;
                lines.add(text);
            }
        }
        return lines;
    }

    private boolean G_564_y(Z_1993_T stack) {
        if (!stack.h_1847_R()) {
            return false;
        }
        U_2912_j tag = stack.Q_4569_t();
        if (tag == null || !tag.R_4764_Y("display", 10)) {
            return false;
        }
        U_2912_j display = tag.M_182_A("display");
        if (!display.R_4764_Y("Lore", 9)) {
            return false;
        }
        q_2896_o lore = display.G_564_y("Lore", 8);
        return lore.size() >= 2;
    }

    private boolean n_1700_B(Z_1993_T stack, String ... keywords) {
        List<String> loreLines = this.R_4764_Y(stack);
        if (loreLines.isEmpty()) {
            return false;
        }
        String fullLore = String.join((CharSequence)" ", loreLines).toLowerCase();
        for (String keyword : keywords) {
            if (!fullLore.contains(keyword.toLowerCase())) continue;
            return true;
        }
        return false;
    }

    private int n_1700_B(c_1608_O setting) {
        for (int i = 0; i < 45; ++i) {
            Z_1993_T s = A_4727_d.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (s.n_1700_B() || setting.t_148_a() != null && s.J_1907_R() != setting.t_148_a().J_1907_R() || !this.n_1700_B(s, setting)) continue;
            return i;
        }
        return -1;
    }

    private int n_1700_B(int sourceInvSlot, int desiredCount) {
        if (desiredCount <= 0) {
            return -1;
        }
        int windowId = A_4727_d.c_3005_b.Y_259_p.H_1873_g.u_1723_Y;
        int src = sourceInvSlot < 9 ? sourceInvSlot + 36 : sourceInvSlot;
        int empty = this.Z_875_P();
        if (empty == -1) {
            return -1;
        }
        int windowEmpty = empty < 9 ? empty + 36 : empty;
        A_4727_d.c_3005_b.w_1457_N.windowClick(windowId, src, 0, a_408_T.n_1700_B, A_4727_d.c_3005_b.Y_259_p);
        for (int placed = 0; placed < desiredCount; ++placed) {
            A_4727_d.c_3005_b.w_1457_N.windowClick(windowId, windowEmpty, 1, a_408_T.n_1700_B, A_4727_d.c_3005_b.Y_259_p);
        }
        A_4727_d.c_3005_b.w_1457_N.windowClick(windowId, src, 0, a_408_T.n_1700_B, A_4727_d.c_3005_b.Y_259_p);
        return windowEmpty >= 36 ? windowEmpty - 36 : windowEmpty;
    }

    private int Z_875_P() {
        for (int i = 9; i < 36; ++i) {
            if (!A_4727_d.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).n_1700_B()) continue;
            return i;
        }
        return -1;
    }

    private int P_1922_E(Z_1993_T toPlace) {
        int i;
        for (i = 0; i < 9; ++i) {
            if (!A_4727_d.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).n_1700_B()) continue;
            return i;
        }
        for (i = 0; i < 9; ++i) {
            if (A_4727_d.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).n_1700_B() || A_4727_d.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() == toPlace.J_1907_R()) continue;
            return i;
        }
        return -1;
    }

    private void c_3005_b() {
        this.C_2741_M = false;
        this.k_2293_S = 0L;
        this.q_2307_F = false;
        this.Q_2552_b = null;
    }

    private static class n_1700_B {
        final c_1608_O n_1700_B;
        final int J_1907_R;
        final long R_4764_Y;

        n_1700_B(c_1608_O setting, int count, long price) {
            this.n_1700_B = setting;
            this.J_1907_R = count;
            this.R_4764_Y = price;
        }
    }
}

