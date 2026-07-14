/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import java.util.Map;
import java.util.Set;
import lightning.product.D_38_f;
import lightning.product.K_4074_S;
import lightning.product.RotatedPillarBlock;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.DiggerItem;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.Tier;
import lightning.product.q_1613_l;
import lightning.product.Material;

public class AxeItem
extends DiggerItem {
    private static final Set<Material> R_4764_Y = Sets.newHashSet((Object[])new Material[]{Material.q_2307_F, Material.Z_875_P, Material.P_1922_E, Material.v_4262_N, Material.H_2857_Y, Material.z_4693_k});
    private static final Set<T_2915_h> G_564_y = Sets.newHashSet((Object[])new T_2915_h[]{a_3742_W.L_3570_A, a_3742_W.i_770_g, a_3742_W.V_537_k, a_3742_W.c_2086_l, a_3742_W.o_4117_e, a_3742_W.U_3758_B, a_3742_W.A_1306_N, a_3742_W.y_3417_N, a_3742_W.V_3982_O, a_3742_W.k_200_a});
    protected static final Map<T_2915_h, T_2915_h> n_1700_B = new ImmutableMap.Builder().put((Object)a_3742_W.Z_976_R, (Object)a_3742_W.D_4792_h).put((Object)a_3742_W.z_1737_N, (Object)a_3742_W.X_933_l).put((Object)a_3742_W.T_3594_S, (Object)a_3742_W.r_715_M).put((Object)a_3742_W.q_4610_l, (Object)a_3742_W.g_164_R).put((Object)a_3742_W.g_2268_R, (Object)a_3742_W.O_508_d).put((Object)a_3742_W.T_2506_i, (Object)a_3742_W.B_1668_F).put((Object)a_3742_W.N_2525_X, (Object)a_3742_W.l_1233_K).put((Object)a_3742_W.d_2461_k, (Object)a_3742_W.g_221_o).put((Object)a_3742_W.c_4037_x, (Object)a_3742_W.z_1333_t).put((Object)a_3742_W.G_624_v, (Object)a_3742_W.e_2887_G).put((Object)a_3742_W.H_1990_U, (Object)a_3742_W.s_2632_s).put((Object)a_3742_W.v_4276_D, (Object)a_3742_W.z_4693_k).put((Object)a_3742_W.X_1303_p, (Object)a_3742_W.w_2223_C).put((Object)a_3742_W.A_2629_w, (Object)a_3742_W.AdvancementList).put((Object)a_3742_W.T_4001_f, (Object)a_3742_W.B_3068_A).put((Object)a_3742_W.M_712_N, (Object)a_3742_W.W_4813_f).build();

    protected AxeItem(Tier tier, float attackDamageIn, float attackSpeedIn, q_1613_l.n_1700_B builder) {
        super(attackDamageIn, attackSpeedIn, tier, G_564_y, builder);
    }

    @Override
    public float n_1700_B(Z_1993_T stack, K_4074_S state) {
        Material material = state.R_4764_Y();
        return R_4764_Y.contains(material) ? this.J_1907_R : super.n_1700_B(stack, state);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        c_1514_x blockpos;
        b_4507_u world = context.getWorld();
        K_4074_S blockstate = world.getBlockState(blockpos = context.getPos());
        T_2915_h block = n_1700_B.get(blockstate.J_1907_R());
        if (block != null) {
            a_3913_L playerentity = context.getPlayer();
            world.n_1700_B(playerentity, blockpos, SoundEvents.g_2268_R, D_38_f.P_1922_E, 1.0f, 1.0f);
            if (!world.Y_259_p) {
                world.n_1700_B(blockpos, (K_4074_S)block.multiplayerClientSuggestionProvider().n_1700_B(RotatedPillarBlock.t_1786_h, blockstate.R_4764_Y(RotatedPillarBlock.t_1786_h)), 11);
                if (playerentity != null) {
                    context.getItem().n_1700_B(1, playerentity, (T p_220040_1_) -> p_220040_1_.G_564_y(context.getHand()));
                }
            }
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }
}


