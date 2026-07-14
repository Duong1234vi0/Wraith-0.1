/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.K_1310_v;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.g_2336_b;
import lightning.product.Setting;
import lombok.Generated;

public class c_1608_O
extends Setting<Boolean> {
    private final Z_1993_T G_564_y;
    private String P_1922_E = "";
    private long u_1723_Y = 0L;
    private boolean v_4262_N = false;
    private int w_1484_f = 0;
    private final List<String> t_148_a = new ArrayList<String>();
    private final Map<String, Integer> s_956_w = new HashMap<String, Integer>();
    private boolean u_2550_I = false;
    private String M_588_G = null;

    public c_1608_O(Z_1993_T itemStack, String name, Boolean defaultVal) {
        super(name, defaultVal);
        this.G_564_y = itemStack;
    }

    public c_1608_O n_1700_B(String ... params) {
        if (params == null) {
            return this;
        }
        for (String p : params) {
            String trimmed;
            if (p == null || (trimmed = p.trim()).isEmpty()) continue;
            this.t_148_a.add(trimmed);
        }
        return this;
    }

    public c_1608_O n_1700_B(K_1310_v enchantment, int level) {
        if (enchantment == null) {
            return this;
        }
        g_2336_b key = V_3137_a.z_4693_k.J_1907_R(enchantment);
        if (key != null) {
            this.s_956_w.put(key.toString().toLowerCase(), Math.max(1, level));
        }
        return this;
    }

    public c_1608_O w_1484_f() {
        this.u_2550_I = true;
        return this;
    }

    public c_1608_O J_1907_R(String query) {
        this.M_588_G = query == null ? null : query.trim();
        return this;
    }

    public void R_4764_Y(String priceText) {
        if (priceText == null) {
            this.u_1723_Y = 0L;
            return;
        }
        String digits = priceText.replaceAll("[^0-9]", "");
        if (digits.isEmpty()) {
            this.u_1723_Y = 0L;
            return;
        }
        try {
            this.u_1723_Y = Long.parseLong(digits);
        }
        catch (NumberFormatException e) {
            this.u_1723_Y = 0L;
        }
    }

    public void G_564_y(String percentText) {
        if (percentText == null) {
            this.w_1484_f = 0;
            return;
        }
        String digits = percentText.replaceAll("[^0-9]", "");
        if (digits.isEmpty()) {
            this.w_1484_f = 0;
            return;
        }
        try {
            this.w_1484_f = Integer.parseInt(digits);
        }
        catch (NumberFormatException e) {
            this.w_1484_f = 0;
        }
    }

    @Generated
    public Z_1993_T t_148_a() {
        return this.G_564_y;
    }

    @Generated
    public String s_956_w() {
        return this.P_1922_E;
    }

    @Generated
    public long u_2550_I() {
        return this.u_1723_Y;
    }

    @Generated
    public boolean M_588_G() {
        return this.v_4262_N;
    }

    @Generated
    public int P_4830_p() {
        return this.w_1484_f;
    }

    @Generated
    public List<String> h_1847_R() {
        return this.t_148_a;
    }

    @Generated
    public Map<String, Integer> Q_4569_t() {
        return this.s_956_w;
    }

    @Generated
    public boolean M_182_A() {
        return this.u_2550_I;
    }

    @Generated
    public String t_1786_h() {
        return this.M_588_G;
    }

    @Generated
    public void P_1922_E(String nbt) {
        this.P_1922_E = nbt;
    }

    @Override
    @Generated
    public void n_1700_B(long maxPrice) {
        this.u_1723_Y = maxPrice;
    }

    @Override
    @Generated
    public void J_1907_R(boolean sellEnabled) {
        this.v_4262_N = sellEnabled;
    }

    @Override
    @Generated
    public void n_1700_B(int sellPercent) {
        this.w_1484_f = sellPercent;
    }

    @Generated
    public void R_4764_Y(boolean requireUnbreakable) {
        this.u_2550_I = requireUnbreakable;
    }

    @Generated
    public void u_1723_Y(String searchQuery) {
        this.M_588_G = searchQuery;
    }
}

