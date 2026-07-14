/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import lightning.product.C_4998_y;
import lightning.product.D_38_f;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.UseOnContext;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.DiggerItem;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.Tier;
import lightning.product.q_1613_l;

public class X_3955_y
extends DiggerItem {
    private static final Set<T_2915_h> R_4764_Y = Sets.newHashSet((Object[])new T_2915_h[]{a_3742_W.v_887_r, a_3742_W.s_956_w, a_3742_W.u_2550_I, a_3742_W.M_588_G, a_3742_W.Z_735_d, a_3742_W.t_148_a, a_3742_W.t_4043_B, a_3742_W.A_2714_y, a_3742_W.A_4115_X, a_3742_W.Y_1740_V, a_3742_W.l_697_B, a_3742_W.X_290_I, a_3742_W.C_415_h, a_3742_W.InvManager, a_3742_W.CavityFinder, a_3742_W.e_87_p, a_3742_W.K_2336_H, a_3742_W.n_421_x, a_3742_W.p_1976_q, a_3742_W.A_4252_m, a_3742_W.a_794_m, a_3742_W.E_170_p, a_3742_W.m_229_F, a_3742_W.f_4340_D, a_3742_W.A_2204_Z, a_3742_W.R_4912_F, a_3742_W.S_315_z, a_3742_W.o_977_F, a_3742_W.S_1165_y, a_3742_W.E_738_L, a_3742_W.v_165_F});
    protected static final Map<T_2915_h, K_4074_S> n_1700_B = Maps.newHashMap((Map)ImmutableMap.of((Object)a_3742_W.t_148_a, (Object)a_3742_W.InvManager.multiplayerClientSuggestionProvider()));

    public X_3955_y(Tier tier, float attackDamageIn, float attackSpeedIn, q_1613_l.n_1700_B builder) {
        super(attackDamageIn, attackSpeedIn, tier, R_4764_Y, builder);
    }

    @Override
    public boolean J_1907_R(K_4074_S blockIn) {
        return blockIn.n_1700_B(a_3742_W.X_290_I) || blockIn.n_1700_B(a_3742_W.l_697_B);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        b_4507_u world = context.getWorld();
        c_1514_x blockpos = context.getPos();
        K_4074_S blockstate = world.getBlockState(blockpos);
        if (context.getFace() == b_257_Y.n_1700_B) {
            return m_3054_I.R_4764_Y;
        }
        a_3913_L playerentity = context.getPlayer();
        K_4074_S blockstate1 = n_1700_B.get(blockstate.J_1907_R());
        K_4074_S blockstate2 = null;
        if (blockstate1 != null && world.getBlockState(blockpos.up()).v_4262_N()) {
            world.n_1700_B(playerentity, blockpos, SoundEvents.Y_2805_J, D_38_f.P_1922_E, 1.0f, 1.0f);
            blockstate2 = blockstate1;
        } else if (blockstate.J_1907_R() instanceof C_4998_y && blockstate.R_4764_Y(C_4998_y.h_1847_R).booleanValue()) {
            if (!world.v_4276_D()) {
                world.n_1700_B((a_3913_L)null, 1009, blockpos, 0);
            }
            C_4998_y.R_4764_Y(world, blockpos, blockstate);
            blockstate2 = (K_4074_S)blockstate.n_1700_B(C_4998_y.h_1847_R, false);
        }
        if (blockstate2 != null) {
            if (!world.Y_259_p) {
                world.n_1700_B(blockpos, blockstate2, 11);
                if (playerentity != null) {
                    context.getItem().n_1700_B(1, playerentity, (T player) -> player.G_564_y(context.getHand()));
                }
            }
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }
}



