/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.M_3212_T;
import lightning.product.O_2369_F;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.e_563_h;
import lightning.product.i_2154_H;
import lightning.product.j_2644_e;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;

public class StructureBlock
extends BaseEntityBlock {
    public static final e_563_h<M_3212_T> P_4830_p = BlockStateProperties.S_4022_R;

    protected StructureBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new j_2644_e();
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof j_2644_e) {
            return ((j_2644_e)tileentity).n_1700_B(player) ? m_3054_I.n_1700_B(worldIn.Y_259_p) : m_3054_I.R_4764_Y;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, @Nullable r_4811_B placer, Z_1993_T stack) {
        i_2154_H tileentity;
        if (!worldIn.Y_259_p && placer != null && (tileentity = worldIn.getTileEntity(pos)) instanceof j_2644_e) {
            ((j_2644_e)tileentity).n_1700_B(placer);
        }
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, M_3212_T.G_564_y);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        i_2154_H tileentity;
        if (worldIn instanceof e_3591_l && (tileentity = worldIn.getTileEntity(pos)) instanceof j_2644_e) {
            j_2644_e structureblocktileentity = (j_2644_e)tileentity;
            boolean flag = worldIn.Y_601_j(pos);
            boolean flag1 = structureblocktileentity.q_2307_F();
            if (flag && !flag1) {
                structureblocktileentity.R_4764_Y(true);
                this.n_1700_B((e_3591_l)worldIn, structureblocktileentity);
            } else if (!flag && flag1) {
                structureblocktileentity.R_4764_Y(false);
            }
        }
    }

    private void n_1700_B(e_3591_l world, j_2644_e structureBlock) {
        switch (structureBlock.Q_4569_t()) {
            case n_1700_B: {
                structureBlock.J_1907_R(false);
                break;
            }
            case J_1907_R: {
                structureBlock.n_1700_B(world, false);
                break;
            }
            case R_4764_Y: {
                structureBlock.C_2741_M();
            }
        }
    }
}


