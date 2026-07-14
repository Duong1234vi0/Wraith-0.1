/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_268_Q;
import lightning.product.A_69_b;
import lightning.product.PatrollingMonster;
import lightning.product.G_1455_B;
import lightning.product.Squid;
import lightning.product.L_3233_K;
import lightning.product.M_2433_H;
import lightning.product.Animal;
import lightning.product.N_4263_v;
import lightning.product.Q_3816_H;
import lightning.product.Hoglin;
import lightning.product.R_1299_M;
import lightning.product.S_3848_S;
import lightning.product.S_922_s;
import lightning.product.Ghast;
import lightning.product.V_3137_a;
import lightning.product.AbstractFish;
import lightning.product.Y_559_r;
import lightning.product.Z_530_i;
import lightning.product.a_3160_D;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.Bat;
import lightning.product.d_3786_K;
import lightning.product.Silverfish;
import lightning.product.ZombifiedPiglin;
import lightning.product.Monster;
import lightning.product.l_3090_i;
import lightning.product.Stray;
import lightning.product.s_4023_U;
import lightning.product.t_4149_i;
import lightning.product.t_5_h;
import lightning.product.Endermite;
import lightning.product.z_2963_s;

public class F_4023_g {
    private static final Map<t_5_h<?>, n_1700_B> n_1700_B = Maps.newHashMap();

    private static <T extends Z_530_i> void n_1700_B(t_5_h<T> entityTypeIn, R_4764_Y placementType, z_2963_s.n_1700_B heightMapType, J_1907_R<T> placementPredicate) {
        n_1700_B entityspawnplacementregistry$entry = n_1700_B.put(entityTypeIn, new n_1700_B(heightMapType, placementType, placementPredicate));
        if (entityspawnplacementregistry$entry != null) {
            throw new IllegalStateException("Duplicate registration for type " + String.valueOf(V_3137_a.g_221_o.J_1907_R(entityTypeIn)));
        }
    }

    public static R_4764_Y n_1700_B(t_5_h<?> entityTypeIn) {
        n_1700_B entityspawnplacementregistry$entry = n_1700_B.get(entityTypeIn);
        return entityspawnplacementregistry$entry == null ? R_4764_Y.R_4764_Y : entityspawnplacementregistry$entry.J_1907_R;
    }

    public static z_2963_s.n_1700_B J_1907_R(@Nullable t_5_h<?> entityTypeIn) {
        n_1700_B entityspawnplacementregistry$entry = n_1700_B.get(entityTypeIn);
        return entityspawnplacementregistry$entry == null ? z_2963_s.n_1700_B.u_1723_Y : entityspawnplacementregistry$entry.n_1700_B;
    }

    public static <T extends N_4263_v> boolean n_1700_B(t_5_h<T> entityType, ServerLevelAccessor world, a_3160_D reason, c_1514_x pos, Random rand) {
        n_1700_B entityspawnplacementregistry$entry = n_1700_B.get(entityType);
        return entityspawnplacementregistry$entry == null || entityspawnplacementregistry$entry.R_4764_Y.test(entityType, world, reason, pos, rand);
    }

    static {
        F_4023_g.n_1700_B(t_5_h.u_2550_I, R_4764_Y.J_1907_R, z_2963_s.n_1700_B.u_1723_Y, AbstractFish::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.h_1847_R, R_4764_Y.J_1907_R, z_2963_s.n_1700_B.u_1723_Y, Y_559_r::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.t_1786_h, R_4764_Y.J_1907_R, z_2963_s.n_1700_B.u_1723_Y, S_3848_S::n_1700_B);
        F_4023_g.n_1700_B(t_5_h.x_607_J, R_4764_Y.J_1907_R, z_2963_s.n_1700_B.u_1723_Y, G_1455_B::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.j_276_v, R_4764_Y.J_1907_R, z_2963_s.n_1700_B.u_1723_Y, AbstractFish::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.D_60_a, R_4764_Y.J_1907_R, z_2963_s.n_1700_B.u_1723_Y, AbstractFish::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.j_1564_a, R_4764_Y.J_1907_R, z_2963_s.n_1700_B.u_1723_Y, Squid::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.S_4022_R, R_4764_Y.J_1907_R, z_2963_s.n_1700_B.u_1723_Y, AbstractFish::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.G_564_y, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Bat::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.u_1723_Y, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.t_148_a, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.s_956_w, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.M_588_G, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.P_4830_p, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.Q_4569_t, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.Y_259_p, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.Q_2552_b, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Endermite::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.Y_601_j, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Z_530_i::n_1700_B);
        F_4023_g.n_1700_B(t_5_h.Y_1740_V, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Ghast::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.t_4043_B, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.n_3318_d, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.d_2427_y, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, d_3786_K::n_1700_B);
        F_4023_g.n_1700_B(t_5_h.v_4276_D, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Z_530_i::n_1700_B);
        F_4023_g.n_1700_B(t_5_h.g_221_o, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.B_1668_F, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, S_922_s::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.D_4792_h, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, s_4023_U::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.T_3594_S, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.s_2632_s, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.P_1922_E, l_3090_i::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.O_508_d, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.P_1922_E, R_1299_M::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.A_1038_p, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.e_4240_b, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Hoglin::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.i_1637_u, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, A_69_b::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.p_178_J, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, PatrollingMonster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.RealmsClientConfig, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Q_3816_H::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.UploadStatus, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, M_2433_H::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.k_3961_g, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.t_4219_U, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Silverfish::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.V_1446_Y, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.PlayerInfo, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.V_1225_t, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, A_268_Q::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.q_1982_R, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Z_530_i::n_1700_B);
        F_4023_g.n_1700_B(t_5_h.RealmsServerPing, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.M_1641_O, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Stray::n_1700_B);
        F_4023_g.n_1700_B(t_5_h.RealmsWorldOptions, R_4764_Y.G_564_y, z_2963_s.n_1700_B.u_1723_Y, L_3233_K::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.l_4537_E, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, t_4149_i::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.RealmsDefaultUncaughtExceptionHandler, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Z_530_i::n_1700_B);
        F_4023_g.n_1700_B(t_5_h.RetryCallException, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.r_3651_U, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.RowButton, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.j_2266_I, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.R_3077_Z, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.RealmsScreenWithCallback, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.c_132_F, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, ZombifiedPiglin::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.M_2677_i, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.w_1484_f, R_4764_Y.n_1700_B, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.multiplayerClientSuggestionProvider, R_4764_Y.J_1907_R, z_2963_s.n_1700_B.u_1723_Y, G_1455_B::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.C_2741_M, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.A_4115_X, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.z_1737_N, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.z_1333_t, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.r_715_M, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Z_530_i::n_1700_B);
        F_4023_g.n_1700_B(t_5_h.e_1992_r, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.Ops, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Z_530_i::n_1700_B);
        F_4023_g.n_1700_B(t_5_h.F_1410_V, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Animal::R_4764_Y);
        F_4023_g.n_1700_B(t_5_h.F_2624_D, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.y_1700_S, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Monster::J_1907_R);
        F_4023_g.n_1700_B(t_5_h.u_744_e, R_4764_Y.R_4764_Y, z_2963_s.n_1700_B.u_1723_Y, Z_530_i::n_1700_B);
    }

    static class n_1700_B {
        private final z_2963_s.n_1700_B n_1700_B;
        private final R_4764_Y J_1907_R;
        private final J_1907_R<?> R_4764_Y;

        public n_1700_B(z_2963_s.n_1700_B typeIn, R_4764_Y placementTypeIn, J_1907_R<?> placementPredicateIn) {
            this.n_1700_B = typeIn;
            this.J_1907_R = placementTypeIn;
            this.R_4764_Y = placementPredicateIn;
        }
    }

    public static final class R_4764_Y
    extends Enum<R_4764_Y> {
        public static final /* enum */ R_4764_Y n_1700_B = new R_4764_Y();
        public static final /* enum */ R_4764_Y J_1907_R = new R_4764_Y();
        public static final /* enum */ R_4764_Y R_4764_Y = new R_4764_Y();
        public static final /* enum */ R_4764_Y G_564_y = new R_4764_Y();
        private static final /* synthetic */ R_4764_Y[] P_1922_E;

        public static R_4764_Y[] values() {
            return (R_4764_Y[])P_1922_E.clone();
        }

        public static R_4764_Y valueOf(String name) {
            return Enum.valueOf(R_4764_Y.class, name);
        }

        private static /* synthetic */ R_4764_Y[] n_1700_B() {
            return new R_4764_Y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.F_4023_g$R_4764_Y.n_1700_B();
        }
    }

    @FunctionalInterface
    public static interface J_1907_R<T extends N_4263_v> {
        public boolean test(t_5_h<T> var1, ServerLevelAccessor var2, a_3160_D var3, c_1514_x var4, Random var5);
    }
}


