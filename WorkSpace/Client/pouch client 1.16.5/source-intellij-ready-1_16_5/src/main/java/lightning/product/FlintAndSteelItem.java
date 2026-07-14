/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4088_l;
import lightning.product.C_4998_y;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.K_4074_S;
import lightning.product.BaseFireBlock;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;

public class FlintAndSteelItem
extends q_1613_l {
    public FlintAndSteelItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        c_1514_x blockpos;
        a_3913_L playerentity = context.getPlayer();
        b_4507_u world = context.getWorld();
        K_4074_S blockstate = world.getBlockState(blockpos = context.getPos());
        if (C_4998_y.t_148_a(blockstate)) {
            world.n_1700_B(playerentity, blockpos, SoundEvents.U_144_f, D_38_f.P_1922_E, 1.0f, w_1484_f.nextFloat() * 0.4f + 0.8f);
            world.n_1700_B(blockpos, (K_4074_S)blockstate.n_1700_B(BlockStateProperties.multiplayerClientSuggestionProvider, true), 11);
            if (playerentity != null) {
                context.getItem().n_1700_B(1, playerentity, (T p_219999_1_) -> p_219999_1_.G_564_y(context.getHand()));
            }
            return m_3054_I.n_1700_B(world.v_4276_D());
        }
        c_1514_x blockpos1 = blockpos.offset(context.getFace());
        if (BaseFireBlock.n_1700_B(world, blockpos1, context.getPlacementHorizontalFacing())) {
            world.n_1700_B(playerentity, blockpos1, SoundEvents.U_144_f, D_38_f.P_1922_E, 1.0f, w_1484_f.nextFloat() * 0.4f + 0.8f);
            K_4074_S blockstate1 = BaseFireBlock.n_1700_B(world, blockpos1);
            world.n_1700_B(blockpos1, blockstate1, 11);
            Z_1993_T itemstack = context.getItem();
            if (playerentity instanceof B_4088_l) {
                U_3554_Q.q_2307_F.n_1700_B((B_4088_l)playerentity, blockpos1, itemstack);
                itemstack.n_1700_B(1, playerentity, (T p_219998_1_) -> p_219998_1_.G_564_y(context.getHand()));
            }
            return m_3054_I.n_1700_B(world.v_4276_D());
        }
        return m_3054_I.G_564_y;
    }
}


