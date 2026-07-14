/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_2011_f;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.i_2154_H;

public class LootContextParams {
    public static final I_2011_f<N_4263_v> n_1700_B = LootContextParams.n_1700_B("this_entity");
    public static final I_2011_f<a_3913_L> J_1907_R = LootContextParams.n_1700_B("last_damage_player");
    public static final I_2011_f<P_11_z> R_4764_Y = LootContextParams.n_1700_B("damage_source");
    public static final I_2011_f<N_4263_v> G_564_y = LootContextParams.n_1700_B("killer_entity");
    public static final I_2011_f<N_4263_v> P_1922_E = LootContextParams.n_1700_B("direct_killer_entity");
    public static final I_2011_f<e_2866_D> u_1723_Y = LootContextParams.n_1700_B("origin");
    public static final I_2011_f<K_4074_S> v_4262_N = LootContextParams.n_1700_B("block_state");
    public static final I_2011_f<i_2154_H> w_1484_f = LootContextParams.n_1700_B("block_entity");
    public static final I_2011_f<Z_1993_T> t_148_a = LootContextParams.n_1700_B("tool");
    public static final I_2011_f<Float> s_956_w = LootContextParams.n_1700_B("explosion_radius");

    private static <T> I_2011_f<T> n_1700_B(String p_216280_0_) {
        return new I_2011_f(new g_2336_b(p_216280_0_));
    }
}


