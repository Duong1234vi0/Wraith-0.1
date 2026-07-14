/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import java.util.Map;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.StructureProcessor;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.j_3341_s;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;
import lightning.product.y_3008_A;
import lightning.product.z_2909_G;

public class n_2313_d
extends StructureProcessor {
    public static final Codec<n_2313_d> n_1700_B;
    public static final n_2313_d J_1907_R;
    private final Map<T_2915_h, T_2915_h> R_4764_Y = j_3341_s.n_1700_B(Maps.newHashMap(), p_237060_0_ -> {
        p_237060_0_.put(a_3742_W.P_4830_p, a_3742_W.m_1964_F);
        p_237060_0_.put(a_3742_W.U_1341_G, a_3742_W.m_1964_F);
        p_237060_0_.put(a_3742_W.J_1907_R, a_3742_W.u_3578_p);
        p_237060_0_.put(a_3742_W.f_691_R, a_3742_W.g_1096_r);
        p_237060_0_.put(a_3742_W.I_4481_g, a_3742_W.g_1096_r);
        p_237060_0_.put(a_3742_W.S_3139_t, a_3742_W.v_2746_S);
        p_237060_0_.put(a_3742_W.k_2282_P, a_3742_W.v_2746_S);
        p_237060_0_.put(a_3742_W.j_2302_z, a_3742_W.Q_1036_Q);
        p_237060_0_.put(a_3742_W.F_2860_q, a_3742_W.Y_3066_B);
        p_237060_0_.put(a_3742_W.F_747_P, a_3742_W.Y_3066_B);
        p_237060_0_.put(a_3742_W.NoSlow, a_3742_W.b_3334_n);
        p_237060_0_.put(a_3742_W.j_2129_E, a_3742_W.b_3334_n);
        p_237060_0_.put(a_3742_W.MoveHelper, a_3742_W.TickTrigger);
        p_237060_0_.put(a_3742_W.Jesus, a_3742_W.TickTrigger);
        p_237060_0_.put(a_3742_W.Phase, a_3742_W.r_2687_x);
        p_237060_0_.put(a_3742_W.O_1043_U, a_3742_W.r_2687_x);
        p_237060_0_.put(a_3742_W.U_3005_m, a_3742_W.C_3528_u);
        p_237060_0_.put(a_3742_W.t_4562_T, a_3742_W.C_3528_u);
        p_237060_0_.put(a_3742_W.h_3859_C, a_3742_W.A_2487_t);
        p_237060_0_.put(a_3742_W.F_1446_q, a_3742_W.A_2487_t);
        p_237060_0_.put(a_3742_W.I_3457_f, a_3742_W.Y_3588_g);
        p_237060_0_.put(a_3742_W.g_1734_y, a_3742_W.j_2461_G);
        p_237060_0_.put(a_3742_W.Z_4720_K, a_3742_W.r_4879_Z);
    });

    private n_2313_d() {
    }

    @Override
    public a_2886_t.J_1907_R n_1700_B(T_1316_M p_230386_1_, c_1514_x p_230386_2_, c_1514_x p_230386_3_, a_2886_t.J_1907_R p_230386_4_, a_2886_t.J_1907_R p_230386_5_, w_1748_S p_230386_6_) {
        T_2915_h block = this.R_4764_Y.get(p_230386_5_.J_1907_R.J_1907_R());
        if (block == null) {
            return p_230386_5_;
        }
        K_4074_S blockstate = p_230386_5_.J_1907_R;
        K_4074_S blockstate1 = block.multiplayerClientSuggestionProvider();
        if (blockstate.J_1907_R(z_2909_G.P_4830_p)) {
            blockstate1 = (K_4074_S)blockstate1.n_1700_B(z_2909_G.P_4830_p, blockstate.R_4764_Y(z_2909_G.P_4830_p));
        }
        if (blockstate.J_1907_R(z_2909_G.h_1847_R)) {
            blockstate1 = (K_4074_S)blockstate1.n_1700_B(z_2909_G.h_1847_R, blockstate.R_4764_Y(z_2909_G.h_1847_R));
        }
        if (blockstate.J_1907_R(y_3008_A.P_4830_p)) {
            blockstate1 = (K_4074_S)blockstate1.n_1700_B(y_3008_A.P_4830_p, blockstate.R_4764_Y(y_3008_A.P_4830_p));
        }
        return new a_2886_t.J_1907_R(p_230386_5_.n_1700_B, blockstate1, p_230386_5_.R_4764_Y);
    }

    @Override
    protected StructureProcessorType<?> n_1700_B() {
        return StructureProcessorType.w_1484_f;
    }

    static {
        J_1907_R = new n_2313_d();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}



