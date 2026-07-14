/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Set;
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

public class HoeItem
extends DiggerItem {
    private static final Set<T_2915_h> R_4764_Y = ImmutableSet.of((Object)a_3742_W.LockSlot, (Object)a_3742_W.J_1008_m, (Object)a_3742_W.M_4609_z, (Object)a_3742_W.T_797_O, (Object)a_3742_W.Y_2805_J, (Object)a_3742_W.CriterionTrigger, (Object[])new T_2915_h[]{a_3742_W.j_276_v, a_3742_W.UploadStatus, a_3742_W.p_178_J, a_3742_W.A_1038_p, a_3742_W.i_1637_u, a_3742_W.f_4016_n, a_3742_W.RealmsClientConfig, a_3742_W.Ping});
    protected static final Map<T_2915_h, K_4074_S> n_1700_B = Maps.newHashMap((Map)ImmutableMap.of((Object)a_3742_W.t_148_a, (Object)a_3742_W.Z_735_d.multiplayerClientSuggestionProvider(), (Object)a_3742_W.InvManager, (Object)a_3742_W.Z_735_d.multiplayerClientSuggestionProvider(), (Object)a_3742_W.s_956_w, (Object)a_3742_W.Z_735_d.multiplayerClientSuggestionProvider(), (Object)a_3742_W.u_2550_I, (Object)a_3742_W.s_956_w.multiplayerClientSuggestionProvider()));

    protected HoeItem(Tier p_i231595_1_, int p_i231595_2_, float p_i231595_3_, q_1613_l.n_1700_B p_i231595_4_) {
        super(p_i231595_2_, p_i231595_3_, p_i231595_1_, R_4764_Y, p_i231595_4_);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        K_4074_S blockstate;
        b_4507_u world = context.getWorld();
        c_1514_x blockpos = context.getPos();
        if (context.getFace() != b_257_Y.n_1700_B && world.getBlockState(blockpos.up()).v_4262_N() && (blockstate = n_1700_B.get(world.getBlockState(blockpos).J_1907_R())) != null) {
            a_3913_L playerentity = context.getPlayer();
            world.n_1700_B(playerentity, blockpos, SoundEvents.Q_4222_k, D_38_f.P_1922_E, 1.0f, 1.0f);
            if (!world.Y_259_p) {
                world.n_1700_B(blockpos, blockstate, 11);
                if (playerentity != null) {
                    context.getItem().n_1700_B(1, playerentity, (T p_220043_1_) -> p_220043_1_.G_564_y(context.getHand()));
                }
            }
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }
}



