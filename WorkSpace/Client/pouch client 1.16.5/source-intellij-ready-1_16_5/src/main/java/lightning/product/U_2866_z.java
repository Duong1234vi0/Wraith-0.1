/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.DebugPackets;
import lightning.product.C_1375_J;
import lightning.product.MobEffects;
import lightning.product.Stats;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.W_4304_a;
import lightning.product.Z_3903_F;
import lightning.product.a_3913_L;
import lightning.product.b_3129_s;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.q_2232_A;
import lightning.product.q_2896_o;
import lightning.product.y_2339_p;
import lightning.product.SavedData;

public class U_2866_z
extends SavedData {
    private final Map<Integer, b_3129_s> n_1700_B = Maps.newHashMap();
    private final e_3591_l J_1907_R;
    private int R_4764_Y;
    private int G_564_y;

    public U_2866_z(e_3591_l p_i50142_1_) {
        super(U_2866_z.n_1700_B(p_i50142_1_.G_624_v()));
        this.J_1907_R = p_i50142_1_;
        this.R_4764_Y = 1;
        this.R_4764_Y();
    }

    public b_3129_s n_1700_B(int id) {
        return this.n_1700_B.get(id);
    }

    public void n_1700_B() {
        ++this.G_564_y;
        Iterator<b_3129_s> iterator = this.n_1700_B.values().iterator();
        while (iterator.hasNext()) {
            b_3129_s raid = iterator.next();
            if (this.J_1907_R.H_1990_U().J_1907_R(A_2352_Z.k_2293_S)) {
                raid.M_588_G();
            }
            if (raid.G_564_y()) {
                iterator.remove();
                this.R_4764_Y();
                continue;
            }
            raid.P_4830_p();
        }
        if (this.G_564_y % 200 == 0) {
            this.R_4764_Y();
        }
        DebugPackets.n_1700_B(this.J_1907_R, this.n_1700_B.values());
    }

    public static boolean n_1700_B(W_4304_a p_215165_0_, b_3129_s p_215165_1_) {
        if (p_215165_0_ != null && p_215165_1_ != null && p_215165_1_.v_4262_N() != null) {
            return p_215165_0_.RealmsLongRunningMcoTaskScreen() && p_215165_0_.y_4642_Y() && p_215165_0_.g_4560_H() <= 2400 && p_215165_0_.O_508_d.G_624_v() == p_215165_1_.v_4262_N().G_624_v();
        }
        return false;
    }

    @Nullable
    public b_3129_s n_1700_B(B_4088_l p_215170_1_) {
        c_1514_x blockpos1;
        if (p_215170_1_.d_2461_k()) {
            return null;
        }
        if (this.J_1907_R.H_1990_U().J_1907_R(A_2352_Z.k_2293_S)) {
            return null;
        }
        Z_3903_F dimensiontype = p_215170_1_.O_508_d.G_624_v();
        if (!dimensiontype.s_956_w()) {
            return null;
        }
        c_1514_x blockpos = p_215170_1_.b_2312_j();
        List list = this.J_1907_R.p_178_J().R_4764_Y(q_2232_A.J_1907_R, blockpos, 64, b_4946_z.J_1907_R.J_1907_R).collect(Collectors.toList());
        int i = 0;
        e_2866_D vector3d = e_2866_D.n_1700_B;
        for (y_2339_p pointofinterest : list) {
            c_1514_x blockpos2 = pointofinterest.P_1922_E();
            vector3d = vector3d.J_1907_R(blockpos2.getX(), blockpos2.getY(), blockpos2.getZ());
            ++i;
        }
        if (i > 0) {
            vector3d = vector3d.n_1700_B(1.0 / (double)i);
            blockpos1 = new c_1514_x(vector3d);
        } else {
            blockpos1 = blockpos;
        }
        b_3129_s raid = this.n_1700_B(p_215170_1_.c_3005_b(), blockpos1);
        boolean flag = false;
        if (!raid.w_1484_f()) {
            if (!this.n_1700_B.containsKey(raid.w_1457_N())) {
                this.n_1700_B.put(raid.w_1457_N(), raid);
            }
            flag = true;
        } else if (raid.u_2550_I() < raid.s_956_w()) {
            flag = true;
        } else {
            p_215170_1_.G_564_y(MobEffects.t_4043_B);
            p_215170_1_.n_1700_B.n_1700_B(new C_1375_J(p_215170_1_, 43));
        }
        if (flag) {
            raid.n_1700_B((a_3913_L)p_215170_1_);
            p_215170_1_.n_1700_B.n_1700_B(new C_1375_J(p_215170_1_, 43));
            if (!raid.R_4764_Y()) {
                p_215170_1_.J_1907_R(Stats.q_1982_R);
                U_3554_Q.d_2427_y.n_1700_B(p_215170_1_);
            }
        }
        this.R_4764_Y();
        return raid;
    }

    private b_3129_s n_1700_B(e_3591_l p_215168_1_, c_1514_x p_215168_2_) {
        b_3129_s raid = p_215168_1_.Z_875_P(p_215168_2_);
        return raid != null ? raid : new b_3129_s(this.J_1907_R(), p_215168_1_, p_215168_2_);
    }

    @Override
    public void n_1700_B(U_2912_j nbt) {
        this.R_4764_Y = nbt.w_1484_f("NextAvailableID");
        this.G_564_y = nbt.w_1484_f("Tick");
        q_2896_o listnbt = nbt.G_564_y("Raids", 10);
        for (int i = 0; i < listnbt.size(); ++i) {
            U_2912_j compoundnbt = listnbt.n_1700_B(i);
            b_3129_s raid = new b_3129_s(this.J_1907_R, compoundnbt);
            this.n_1700_B.put(raid.w_1457_N(), raid);
        }
    }

    @Override
    public U_2912_j R_4764_Y(U_2912_j compound) {
        compound.J_1907_R("NextAvailableID", this.R_4764_Y);
        compound.J_1907_R("Tick", this.G_564_y);
        q_2896_o listnbt = new q_2896_o();
        for (b_3129_s raid : this.n_1700_B.values()) {
            U_2912_j compoundnbt = new U_2912_j();
            raid.n_1700_B(compoundnbt);
            listnbt.add(compoundnbt);
        }
        compound.n_1700_B("Raids", listnbt);
        return compound;
    }

    public static String n_1700_B(Z_3903_F p_234620_0_) {
        return "raids" + p_234620_0_.n_1700_B();
    }

    private int J_1907_R() {
        return ++this.R_4764_Y;
    }

    @Nullable
    public b_3129_s n_1700_B(c_1514_x p_215174_1_, int distance) {
        b_3129_s raid = null;
        double d0 = distance;
        for (b_3129_s raid1 : this.n_1700_B.values()) {
            double d1 = raid1.multiplayerClientSuggestionProvider().distanceSq(p_215174_1_);
            if (!raid1.Y_601_j() || !(d1 < d0)) continue;
            raid = raid1;
            d0 = d1;
        }
        return raid;
    }
}


