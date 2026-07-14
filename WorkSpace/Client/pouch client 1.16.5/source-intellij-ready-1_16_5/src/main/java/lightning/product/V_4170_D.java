/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2IntMap
 *  it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import java.util.function.LongFunction;
import lightning.product.B_2976_q;
import lightning.product.B_73_M;
import lightning.product.C_2991_c;
import lightning.product.C_4611_m;
import lightning.product.E_2648_v;
import lightning.product.E_4371_e;
import lightning.product.F_3979_r;
import lightning.product.BiomeInitLayer;
import lightning.product.T_927_e;
import lightning.product.U_1444_f;
import lightning.product.W_1036_A;
import lightning.product.W_1802_D;
import lightning.product.BigContext;
import lightning.product.a_1540_b;
import lightning.product.a_7_n;
import lightning.product.AreaTransformer1;
import lightning.product.LazyArea;
import lightning.product.g_693_u;
import lightning.product.j_3341_s;
import lightning.product.k_3362_S;
import lightning.product.l_2569_y;
import lightning.product.n_1670_s;
import lightning.product.n_2175_F;
import lightning.product.p_3451_N;
import lightning.product.t_4013_W;
import lightning.product.v_3489_Y;
import lightning.product.x_4338_L;
import lightning.product.y_2188_j;

public class V_4170_D {
    private static final Int2IntMap n_1700_B = (Int2IntMap)j_3341_s.n_1700_B(new Int2IntOpenHashMap(), p_242938_0_ -> {
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.s_956_w, 16);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.s_956_w, 26);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.P_4830_p, 2);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.P_4830_p, 17);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.P_4830_p, 130);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.R_4764_Y, 131);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.R_4764_Y, 162);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.R_4764_Y, 20);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.R_4764_Y, 3);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.R_4764_Y, 34);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_2550_I, 27);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_2550_I, 28);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_2550_I, 29);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_2550_I, 157);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_2550_I, 132);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_2550_I, 4);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_2550_I, 155);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_2550_I, 156);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_2550_I, 18);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.t_148_a, 140);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.t_148_a, 13);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.t_148_a, 12);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.G_564_y, 168);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.G_564_y, 169);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.G_564_y, 21);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.G_564_y, 23);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.G_564_y, 22);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.G_564_y, 149);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.G_564_y, 151);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.P_1922_E, 37);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.P_1922_E, 165);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.P_1922_E, 167);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.P_1922_E, 166);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_1723_Y, 39);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.u_1723_Y, 38);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_182_A, 14);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_182_A, 15);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.n_1700_B, 25);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 46);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 49);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 50);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 48);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 24);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 47);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 10);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 45);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 0);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.M_588_G, 44);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.v_4262_N, 1);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.v_4262_N, 129);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.h_1847_R, 11);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.h_1847_R, 7);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.w_1484_f, 35);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.w_1484_f, 36);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.w_1484_f, 163);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.w_1484_f, 164);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.Q_4569_t, 6);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.Q_4569_t, 134);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 160);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 161);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 32);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 33);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 30);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 31);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 158);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 5);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 19);
        V_4170_D.n_1700_B(p_242938_0_, lightning.product.V_4170_D$n_1700_B.J_1907_R, 133);
    });

    private static <T extends t_4013_W, C extends BigContext<T>> p_3451_N<T> n_1700_B(long seed, AreaTransformer1 parent, p_3451_N<T> p_202829_3_, int count, LongFunction<C> contextFactory) {
        p_3451_N<T> iareafactory = p_202829_3_;
        for (int i = 0; i < count; ++i) {
            iareafactory = parent.n_1700_B((BigContext)contextFactory.apply(seed + (long)i), iareafactory);
        }
        return iareafactory;
    }

    private static <T extends t_4013_W, C extends BigContext<T>> p_3451_N<T> n_1700_B(boolean p_237216_0_, int p_237216_1_, int p_237216_2_, LongFunction<C> p_237216_3_) {
        p_3451_N iareafactory = B_2976_q.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1L));
        iareafactory = T_927_e.J_1907_R.n_1700_B((BigContext)p_237216_3_.apply(2000L), iareafactory);
        iareafactory = U_1444_f.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1L), iareafactory);
        iareafactory = T_927_e.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(2001L), iareafactory);
        iareafactory = U_1444_f.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(2L), iareafactory);
        iareafactory = U_1444_f.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(50L), iareafactory);
        iareafactory = U_1444_f.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(70L), iareafactory);
        iareafactory = C_4611_m.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(2L), iareafactory);
        p_3451_N iareafactory1 = n_2175_F.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(2L));
        iareafactory1 = V_4170_D.n_1700_B(2001L, T_927_e.n_1700_B, iareafactory1, 6, p_237216_3_);
        iareafactory = g_693_u.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(2L), iareafactory);
        iareafactory = U_1444_f.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(3L), iareafactory);
        iareafactory = E_2648_v.n_1700_B.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(2L), iareafactory);
        iareafactory = E_2648_v.J_1907_R.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(2L), iareafactory);
        iareafactory = E_2648_v.R_4764_Y.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(3L), iareafactory);
        iareafactory = T_927_e.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(2002L), iareafactory);
        iareafactory = T_927_e.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(2003L), iareafactory);
        iareafactory = U_1444_f.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(4L), iareafactory);
        iareafactory = k_3362_S.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(5L), iareafactory);
        iareafactory = l_2569_y.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(4L), iareafactory);
        iareafactory = V_4170_D.n_1700_B(1000L, T_927_e.n_1700_B, iareafactory, 0, p_237216_3_);
        p_3451_N lvt_6_1_ = V_4170_D.n_1700_B(1000L, T_927_e.n_1700_B, iareafactory, 0, p_237216_3_);
        lvt_6_1_ = y_2188_j.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(100L), lvt_6_1_);
        p_3451_N lvt_7_1_ = new BiomeInitLayer(p_237216_0_).n_1700_B((BigContext)p_237216_3_.apply(200L), iareafactory);
        lvt_7_1_ = W_1802_D.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1001L), lvt_7_1_);
        lvt_7_1_ = V_4170_D.n_1700_B(1000L, T_927_e.n_1700_B, lvt_7_1_, 2, p_237216_3_);
        lvt_7_1_ = B_73_M.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1000L), lvt_7_1_);
        p_3451_N lvt_8_1_ = V_4170_D.n_1700_B(1000L, T_927_e.n_1700_B, lvt_6_1_, 2, p_237216_3_);
        lvt_7_1_ = x_4338_L.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1000L), lvt_7_1_, lvt_8_1_);
        lvt_6_1_ = V_4170_D.n_1700_B(1000L, T_927_e.n_1700_B, lvt_6_1_, 2, p_237216_3_);
        lvt_6_1_ = V_4170_D.n_1700_B(1000L, T_927_e.n_1700_B, lvt_6_1_, p_237216_2_, p_237216_3_);
        lvt_6_1_ = E_4371_e.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1L), lvt_6_1_);
        lvt_6_1_ = a_7_n.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1000L), lvt_6_1_);
        lvt_7_1_ = v_3489_Y.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1001L), lvt_7_1_);
        for (int i = 0; i < p_237216_1_; ++i) {
            lvt_7_1_ = T_927_e.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1000 + i), lvt_7_1_);
            if (i == 0) {
                lvt_7_1_ = U_1444_f.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(3L), lvt_7_1_);
            }
            if (i != 1 && p_237216_1_ != 1) continue;
            lvt_7_1_ = F_3979_r.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1000L), lvt_7_1_);
        }
        lvt_7_1_ = a_7_n.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(1000L), lvt_7_1_);
        lvt_7_1_ = C_2991_c.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(100L), lvt_7_1_, lvt_6_1_);
        return W_1036_A.n_1700_B.n_1700_B((BigContext)p_237216_3_.apply(100L), lvt_7_1_, iareafactory1);
    }

    public static n_1670_s n_1700_B(long p_237215_0_, boolean p_237215_2_, int p_237215_3_, int p_237215_4_) {
        int i = 25;
        p_3451_N<LazyArea> iareafactory = V_4170_D.n_1700_B(p_237215_2_, p_237215_3_, p_237215_4_, p_227473_2_ -> new a_1540_b(25, p_237215_0_, p_227473_2_));
        return new n_1670_s(iareafactory);
    }

    public static boolean n_1700_B(int p_202826_0_, int p_202826_1_) {
        if (p_202826_0_ == p_202826_1_) {
            return true;
        }
        return n_1700_B.get(p_202826_0_) == n_1700_B.get(p_202826_1_);
    }

    private static void n_1700_B(Int2IntOpenHashMap p_242939_0_, n_1700_B p_242939_1_, int p_242939_2_) {
        p_242939_0_.put(p_242939_2_, p_242939_1_.ordinal());
    }

    protected static boolean n_1700_B(int biomeIn) {
        return biomeIn == 44 || biomeIn == 45 || biomeIn == 0 || biomeIn == 46 || biomeIn == 10 || biomeIn == 47 || biomeIn == 48 || biomeIn == 24 || biomeIn == 49 || biomeIn == 50;
    }

    protected static boolean J_1907_R(int biomeIn) {
        return biomeIn == 44 || biomeIn == 45 || biomeIn == 0 || biomeIn == 46 || biomeIn == 10;
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B();
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B();
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B();
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B();
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B();
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B();
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B();
        public static final /* enum */ n_1700_B w_1484_f = new n_1700_B();
        public static final /* enum */ n_1700_B t_148_a = new n_1700_B();
        public static final /* enum */ n_1700_B s_956_w = new n_1700_B();
        public static final /* enum */ n_1700_B u_2550_I = new n_1700_B();
        public static final /* enum */ n_1700_B M_588_G = new n_1700_B();
        public static final /* enum */ n_1700_B P_4830_p = new n_1700_B();
        public static final /* enum */ n_1700_B h_1847_R = new n_1700_B();
        public static final /* enum */ n_1700_B Q_4569_t = new n_1700_B();
        public static final /* enum */ n_1700_B M_182_A = new n_1700_B();
        private static final /* synthetic */ n_1700_B[] t_1786_h;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_1786_h.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A};
        }

        static {
            t_1786_h = lightning.product.V_4170_D$n_1700_B.n_1700_B();
        }
    }
}


