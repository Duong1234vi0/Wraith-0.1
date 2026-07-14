/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.EnumMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.e_1174_E;
import lightning.product.MobType;
import lightning.product.j_123_i;
import lightning.product.j_3341_s;
import lightning.product.r_4811_B;
import lightning.product.x_282_a;

public abstract class K_1310_v {
    private final e_1174_E[] n_1700_B;
    private final n_1700_B G_564_y;
    public final j_123_i J_1907_R;
    @Nullable
    protected String R_4764_Y;

    @Nullable
    public static K_1310_v R_4764_Y(int id) {
        return (K_1310_v)V_3137_a.z_4693_k.n_1700_B(id);
    }

    protected K_1310_v(n_1700_B rarityIn, j_123_i typeIn, e_1174_E[] slots) {
        this.G_564_y = rarityIn;
        this.J_1907_R = typeIn;
        this.n_1700_B = slots;
    }

    public Map<e_1174_E, Z_1993_T> n_1700_B(r_4811_B livingEntityIn) {
        EnumMap map = Maps.newEnumMap(e_1174_E.class);
        for (e_1174_E equipmentslottype : this.n_1700_B) {
            Z_1993_T itemstack = livingEntityIn.J_1907_R(equipmentslottype);
            if (itemstack.n_1700_B()) continue;
            map.put(equipmentslottype, itemstack);
        }
        return map;
    }

    public n_1700_B G_564_y() {
        return this.G_564_y;
    }

    public int P_1922_E() {
        return 1;
    }

    public int n_1700_B() {
        return 1;
    }

    public int n_1700_B(int enchantmentLevel) {
        return 1 + enchantmentLevel * 10;
    }

    public int J_1907_R(int enchantmentLevel) {
        return this.n_1700_B(enchantmentLevel) + 5;
    }

    public int n_1700_B(int level, P_11_z source) {
        return 0;
    }

    public float n_1700_B(int level, MobType creatureType) {
        return 0.0f;
    }

    public final boolean J_1907_R(K_1310_v enchantmentIn) {
        return this.n_1700_B(enchantmentIn) && enchantmentIn.n_1700_B(this);
    }

    protected boolean n_1700_B(K_1310_v ench) {
        return this != ench;
    }

    protected String u_1723_Y() {
        if (this.R_4764_Y == null) {
            this.R_4764_Y = j_3341_s.n_1700_B("enchantment", V_3137_a.z_4693_k.J_1907_R(this));
        }
        return this.R_4764_Y;
    }

    public String v_4262_N() {
        return this.u_1723_Y();
    }

    public x_282_a G_564_y(int level) {
        F_2904_S iformattabletextcomponent = new F_2904_S(this.v_4262_N());
        if (this.R_4764_Y()) {
            iformattabletextcomponent.n_1700_B(D_4024_W.P_4830_p);
        } else {
            iformattabletextcomponent.n_1700_B(D_4024_W.w_1484_f);
        }
        if (level != 1 || this.n_1700_B() != 1) {
            iformattabletextcomponent.n_1700_B(" ").n_1700_B(new F_2904_S("enchantment.level." + level));
        }
        return iformattabletextcomponent;
    }

    public boolean n_1700_B(Z_1993_T stack) {
        return this.J_1907_R.n_1700_B(stack.J_1907_R());
    }

    public void n_1700_B(r_4811_B user, N_4263_v target, int level) {
    }

    public void J_1907_R(r_4811_B user, N_4263_v attacker, int level) {
    }

    public boolean J_1907_R() {
        return false;
    }

    public boolean R_4764_Y() {
        return false;
    }

    public boolean w_1484_f() {
        return true;
    }

    public boolean t_148_a() {
        return true;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(10);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(5);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(2);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(1);
        private final int P_1922_E;
        private static final /* synthetic */ n_1700_B[] u_1723_Y;

        public static n_1700_B[] values() {
            return (n_1700_B[])u_1723_Y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(int rarityWeight) {
            this.P_1922_E = rarityWeight;
        }

        public int n_1700_B() {
            return this.P_1922_E;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            u_1723_Y = lightning.product.K_1310_v$n_1700_B.J_1907_R();
        }
    }
}


