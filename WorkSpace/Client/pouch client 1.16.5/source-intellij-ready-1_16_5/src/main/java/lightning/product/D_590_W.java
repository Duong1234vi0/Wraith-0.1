/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import lightning.product.F_2904_S;
import lightning.product.K_1289_S;
import lightning.product.Q_4113_P;
import lightning.product.MinecraftClient;
import lightning.product.j_3341_s;
import lightning.product.x_282_a;
import net.minecraftforge.client.settings.IForgeKeybinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.client.settings.KeyModifier;

public class D_590_W
implements Comparable<D_590_W>,
IForgeKeybinding {
    private static final Map<String, D_590_W> n_1700_B = Maps.newHashMap();
    private static final Map<Q_4113_P.n_1700_B, D_590_W> J_1907_R = Maps.newHashMap();
    private static final KeyBindingMap R_4764_Y = new KeyBindingMap();
    private static final Set<String> G_564_y = Sets.newHashSet();
    private static final Map<String, Integer> P_1922_E = j_3341_s.n_1700_B(Maps.newHashMap(), p_205215_0_ -> {
        p_205215_0_.put("key.categories.movement", 1);
        p_205215_0_.put("key.categories.gameplay", 2);
        p_205215_0_.put("key.categories.inventory", 3);
        p_205215_0_.put("key.categories.creative", 4);
        p_205215_0_.put("key.categories.multiplayer", 5);
        p_205215_0_.put("key.categories.ui", 6);
        p_205215_0_.put("key.categories.misc", 7);
    });
    private final String u_1723_Y;
    private final Q_4113_P.n_1700_B v_4262_N;
    private final String w_1484_f;
    private Q_4113_P.n_1700_B t_148_a;
    private boolean s_956_w;
    private int u_2550_I;
    private KeyModifier M_588_G = KeyModifier.NONE;
    private KeyModifier P_4830_p = KeyModifier.NONE;
    private IKeyConflictContext h_1847_R = KeyModifier.KeyConflictContext.UNIVERSAL;

    public static void n_1700_B(Q_4113_P.n_1700_B key) {
        D_590_W keybinding = J_1907_R.get(key);
        if (keybinding != null) {
            ++keybinding.u_2550_I;
        }
    }

    public static void n_1700_B(Q_4113_P.n_1700_B key, boolean held) {
        D_590_W keybinding = J_1907_R.get(key);
        if (keybinding != null) {
            keybinding.n_1700_B(held);
        }
    }

    public static void n_1700_B() {
        for (D_590_W keybinding : n_1700_B.values()) {
            if (keybinding.t_148_a.n_1700_B() != Q_4113_P.J_1907_R.n_1700_B || keybinding.t_148_a.J_1907_R() == Q_4113_P.n_1700_B.J_1907_R()) continue;
            keybinding.n_1700_B(Q_4113_P.n_1700_B(MinecraftClient.A_4115_X().RealmsServerPing().t_148_a(), keybinding.t_148_a.J_1907_R()));
        }
    }

    public static void J_1907_R() {
        for (D_590_W keybinding : n_1700_B.values()) {
            keybinding.h_1847_R();
        }
    }

    public static void R_4764_Y() {
        J_1907_R.clear();
        for (D_590_W keybinding : n_1700_B.values()) {
            J_1907_R.put(keybinding.t_148_a, keybinding);
        }
    }

    public D_590_W(String description, int keyCode, String category) {
        this(description, Q_4113_P.J_1907_R.n_1700_B, keyCode, category);
    }

    public D_590_W(String description, Q_4113_P.J_1907_R type, int code, String category) {
        this.u_1723_Y = description;
        this.v_4262_N = this.t_148_a = type.n_1700_B(code);
        this.w_1484_f = category;
        n_1700_B.put(description, this);
        J_1907_R.put(this.t_148_a, this);
        G_564_y.add(category);
    }

    public boolean G_564_y() {
        return this.s_956_w;
    }

    public String P_1922_E() {
        return this.w_1484_f;
    }

    public boolean u_1723_Y() {
        if (this.u_2550_I == 0) {
            return false;
        }
        --this.u_2550_I;
        return true;
    }

    private void h_1847_R() {
        this.u_2550_I = 0;
        this.n_1700_B(false);
    }

    public String v_4262_N() {
        return this.u_1723_Y;
    }

    public Q_4113_P.n_1700_B w_1484_f() {
        return this.v_4262_N;
    }

    public void J_1907_R(Q_4113_P.n_1700_B key) {
        this.t_148_a = key;
    }

    public int n_1700_B(D_590_W p_compareTo_1_) {
        if (this.w_1484_f.equals(p_compareTo_1_.w_1484_f)) {
            return K_1289_S.n_1700_B(this.u_1723_Y, new Object[0]).compareTo(K_1289_S.n_1700_B(p_compareTo_1_.u_1723_Y, new Object[0]));
        }
        Integer tCat = P_1922_E.get(this.w_1484_f);
        Integer oCat = P_1922_E.get(p_compareTo_1_.w_1484_f);
        if (tCat == null && oCat != null) {
            return 1;
        }
        if (tCat != null && oCat == null) {
            return -1;
        }
        return tCat == null && oCat == null ? K_1289_S.n_1700_B(this.w_1484_f, new Object[0]).compareTo(K_1289_S.n_1700_B(p_compareTo_1_.w_1484_f, new Object[0])) : tCat.compareTo(oCat);
    }

    public static Supplier<x_282_a> n_1700_B(String key) {
        D_590_W keybinding = n_1700_B.get(key);
        return keybinding == null ? () -> new F_2904_S(key) : keybinding::u_2550_I;
    }

    public boolean J_1907_R(D_590_W binding) {
        return this.t_148_a.equals(binding.t_148_a);
    }

    public boolean t_148_a() {
        return this.t_148_a.equals(Q_4113_P.n_1700_B);
    }

    public boolean n_1700_B(int keysym, int scancode) {
        if (keysym == Q_4113_P.n_1700_B.J_1907_R()) {
            return this.t_148_a.n_1700_B() == Q_4113_P.J_1907_R.J_1907_R && this.t_148_a.J_1907_R() == scancode;
        }
        return this.t_148_a.n_1700_B() == Q_4113_P.J_1907_R.n_1700_B && this.t_148_a.J_1907_R() == keysym;
    }

    public x_282_a s_956_w() {
        return this.getKeyModifier().getCombinedName(this.t_148_a, () -> this.t_148_a.G_564_y());
    }

    public boolean n_1700_B(int key) {
        return this.t_148_a.n_1700_B() == Q_4113_P.J_1907_R.R_4764_Y && this.t_148_a.J_1907_R() == key;
    }

    public x_282_a u_2550_I() {
        return this.t_148_a.G_564_y();
    }

    public boolean M_588_G() {
        return this.t_148_a.equals(this.v_4262_N) && this.getKeyModifier() == this.getKeyModifierDefault();
    }

    public String P_4830_p() {
        return this.t_148_a.R_4764_Y();
    }

    public void n_1700_B(boolean valueIn) {
        this.s_956_w = valueIn;
    }

    public D_590_W(String p_i244830_1_, IKeyConflictContext p_i244830_2_, KeyModifier p_i244830_3_, Q_4113_P.n_1700_B p_i244830_4_, String p_i244830_5_) {
        this.u_1723_Y = p_i244830_1_;
        this.t_148_a = p_i244830_4_;
        this.v_4262_N = p_i244830_4_;
        this.w_1484_f = p_i244830_5_;
        this.h_1847_R = p_i244830_2_;
        this.P_4830_p = p_i244830_3_;
        this.M_588_G = p_i244830_3_;
        if (this.P_4830_p.matches(p_i244830_4_)) {
            this.P_4830_p = KeyModifier.NONE;
        }
        n_1700_B.put(p_i244830_1_, this);
        R_4764_Y.addKey(p_i244830_4_, this);
        G_564_y.add(p_i244830_5_);
    }

    @Override
    public Q_4113_P.n_1700_B getKey() {
        return this.t_148_a;
    }

    @Override
    public void setKeyConflictContext(IKeyConflictContext p_setKeyConflictContext_1_) {
        this.h_1847_R = p_setKeyConflictContext_1_;
    }

    @Override
    public IKeyConflictContext getKeyConflictContext() {
        return this.h_1847_R;
    }

    @Override
    public KeyModifier getKeyModifierDefault() {
        return this.M_588_G;
    }

    @Override
    public KeyModifier getKeyModifier() {
        return this.P_4830_p;
    }

    @Override
    public void setKeyModifierAndCode(KeyModifier p_setKeyModifierAndCode_1_, Q_4113_P.n_1700_B p_setKeyModifierAndCode_2_) {
        this.t_148_a = p_setKeyModifierAndCode_2_;
        if (p_setKeyModifierAndCode_1_.matches(p_setKeyModifierAndCode_2_)) {
            p_setKeyModifierAndCode_1_ = KeyModifier.NONE;
        }
        R_4764_Y.removeKey(this);
        this.P_4830_p = p_setKeyModifierAndCode_1_;
        R_4764_Y.addKey(p_setKeyModifierAndCode_2_, this);
    }

    @Override
    public /* synthetic */ int compareTo(Object object) {
        return this.n_1700_B((D_590_W)object);
    }
}



