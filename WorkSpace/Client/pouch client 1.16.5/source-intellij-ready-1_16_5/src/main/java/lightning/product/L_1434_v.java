/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Supplier;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.b_3278_X;
import lightning.product.e_1174_E;
import lightning.product.l_52_h;
import lightning.product.ArmorMaterial;
import lightning.product.Items;

public final class L_1434_v
extends Enum<L_1434_v>
implements ArmorMaterial {
    public static final /* enum */ L_1434_v n_1700_B = new L_1434_v("leather", 5, new int[]{1, 2, 3, 1}, 15, SoundEvents.z_4693_k, 0.0f, 0.0f, () -> b_3278_X.n_1700_B(Items.y_254_d));
    public static final /* enum */ L_1434_v J_1907_R = new L_1434_v("chainmail", 15, new int[]{1, 4, 5, 2}, 12, SoundEvents.z_1737_N, 0.0f, 0.0f, () -> b_3278_X.n_1700_B(Items.D_1621_L));
    public static final /* enum */ L_1434_v R_4764_Y = new L_1434_v("iron", 15, new int[]{2, 5, 6, 2}, 9, SoundEvents.q_4610_l, 0.0f, 0.0f, () -> b_3278_X.n_1700_B(Items.D_1621_L));
    public static final /* enum */ L_1434_v G_564_y = new L_1434_v("gold", 7, new int[]{1, 3, 5, 2}, 25, SoundEvents.T_2506_i, 0.0f, 0.0f, () -> b_3278_X.n_1700_B(Items.ServerHandshakePacketListener));
    public static final /* enum */ L_1434_v P_1922_E = new L_1434_v("diamond", 33, new int[]{3, 6, 8, 3}, 10, SoundEvents.v_4276_D, 2.0f, 0.0f, () -> b_3278_X.n_1700_B(Items.k_2273_q));
    public static final /* enum */ L_1434_v u_1723_Y = new L_1434_v("turtle", 25, new int[]{2, 5, 6, 2}, 9, SoundEvents.e_2887_G, 0.0f, 0.0f, () -> b_3278_X.n_1700_B(Items.o_977_F));
    public static final /* enum */ L_1434_v v_4262_N = new L_1434_v("netherite", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.g_221_o, 3.0f, 0.1f, () -> b_3278_X.n_1700_B(Items.q_4124_m));
    private static final int[] w_1484_f;
    private final String t_148_a;
    private final int s_956_w;
    private final int[] u_2550_I;
    private final int M_588_G;
    private final SoundEvent P_4830_p;
    private final float h_1847_R;
    private final float Q_4569_t;
    private final l_52_h<b_3278_X> M_182_A;
    private static final /* synthetic */ L_1434_v[] t_1786_h;

    public static L_1434_v[] values() {
        return (L_1434_v[])t_1786_h.clone();
    }

    public static L_1434_v valueOf(String name) {
        return Enum.valueOf(L_1434_v.class, name);
    }

    private L_1434_v(String p_i231593_3_, int p_i231593_4_, int[] p_i231593_5_, int p_i231593_6_, SoundEvent p_i231593_7_, float p_i231593_8_, float p_i231593_9_, Supplier<b_3278_X> p_i231593_10_) {
        this.t_148_a = p_i231593_3_;
        this.s_956_w = p_i231593_4_;
        this.u_2550_I = p_i231593_5_;
        this.M_588_G = p_i231593_6_;
        this.P_4830_p = p_i231593_7_;
        this.h_1847_R = p_i231593_8_;
        this.Q_4569_t = p_i231593_9_;
        this.M_182_A = new l_52_h<b_3278_X>(p_i231593_10_);
    }

    @Override
    public int n_1700_B(e_1174_E slotIn) {
        return w_1484_f[slotIn.J_1907_R()] * this.s_956_w;
    }

    @Override
    public int J_1907_R(e_1174_E slotIn) {
        return this.u_2550_I[slotIn.J_1907_R()];
    }

    @Override
    public int n_1700_B() {
        return this.M_588_G;
    }

    @Override
    public SoundEvent J_1907_R() {
        return this.P_4830_p;
    }

    @Override
    public b_3278_X R_4764_Y() {
        return this.M_182_A.n_1700_B();
    }

    @Override
    public String G_564_y() {
        return this.t_148_a;
    }

    @Override
    public float P_1922_E() {
        return this.h_1847_R;
    }

    @Override
    public float u_1723_Y() {
        return this.Q_4569_t;
    }

    private static /* synthetic */ L_1434_v[] h_1847_R() {
        return new L_1434_v[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
    }

    static {
        t_1786_h = L_1434_v.h_1847_R();
        w_1484_f = new int[]{13, 15, 16, 11};
    }
}


