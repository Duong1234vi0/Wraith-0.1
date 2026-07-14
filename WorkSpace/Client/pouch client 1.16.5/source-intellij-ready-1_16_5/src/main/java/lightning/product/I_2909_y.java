/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_4313_D;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.CollisionContext;
import lightning.product.Z_1993_T;
import lightning.product.WoodType;
import lightning.product.a_3913_L;
import lightning.product.DyeItem;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SimpleWaterloggedBlock;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.x_1688_C;

public abstract class I_2909_y
extends BaseEntityBlock
implements SimpleWaterloggedBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.A_4115_X;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(4.0, 0.0, 4.0, 12.0, 16.0, 12.0);
    private final WoodType Q_4569_t;

    protected I_2909_y(q_4293_E.P_1922_E propertiesIn, WoodType woodTypeIn) {
        super(propertiesIn);
        this.Q_4569_t = woodTypeIn;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
            worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public boolean n_1700_B() {
        return true;
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new A_4313_D();
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        boolean flag;
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        boolean bl = flag = itemstack.J_1907_R() instanceof DyeItem && player.C_415_h.P_1922_E;
        if (worldIn.Y_259_p) {
            return flag ? m_3054_I.n_1700_B : m_3054_I.J_1907_R;
        }
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof A_4313_D) {
            boolean flag1;
            A_4313_D signtileentity = (A_4313_D)tileentity;
            if (flag && (flag1 = signtileentity.n_1700_B(((DyeItem)itemstack.J_1907_R()).R_4764_Y())) && !player.G_624_v()) {
                itemstack.v_4262_N(1);
            }
            return signtileentity.J_1907_R(player) ? m_3054_I.n_1700_B : m_3054_I.R_4764_Y;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) != false ? Fluids.R_4764_Y.n_1700_B(false) : super.P_1922_E(state);
    }

    public WoodType J_1907_R() {
        return this.Q_4569_t;
    }
}


