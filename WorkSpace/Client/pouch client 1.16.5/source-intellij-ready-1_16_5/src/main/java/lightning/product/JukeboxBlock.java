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
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.U_2912_j;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.h_3036_f;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.q_1613_l;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.JukeboxBlockEntity;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;

public class JukeboxBlock
extends BaseEntityBlock {
    public static final U_1266_O P_4830_p = BlockStateProperties.h_1847_R;

    protected JukeboxBlock(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, false));
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, @Nullable r_4811_B placer, Z_1993_T stack) {
        U_2912_j compoundnbt1;
        super.n_1700_B(worldIn, pos, state, placer, stack);
        U_2912_j compoundnbt = stack.M_182_A();
        if (compoundnbt.P_1922_E("BlockEntityTag") && (compoundnbt1 = compoundnbt.M_182_A("BlockEntityTag")).P_1922_E("RecordItem")) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, true), 2);
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (state.R_4764_Y(P_4830_p).booleanValue()) {
            this.n_1700_B(worldIn, pos);
            state = (K_4074_S)state.n_1700_B(P_4830_p, false);
            worldIn.n_1700_B(pos, state, 2);
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }

    public void n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state, Z_1993_T recordStack) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof JukeboxBlockEntity) {
            ((JukeboxBlockEntity)tileentity).n_1700_B(recordStack.t_148_a());
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, true), 2);
        }
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        JukeboxBlockEntity jukeboxtileentity;
        Z_1993_T itemstack;
        i_2154_H tileentity;
        if (!worldIn.Y_259_p && (tileentity = worldIn.getTileEntity(pos)) instanceof JukeboxBlockEntity && !(itemstack = (jukeboxtileentity = (JukeboxBlockEntity)tileentity).P_1922_E()).n_1700_B()) {
            worldIn.R_4764_Y(1010, pos, 0);
            jukeboxtileentity.C_2741_M();
            float f = 0.7f;
            double d0 = (double)(worldIn.w_1457_N.nextFloat() * 0.7f) + (double)0.15f;
            double d1 = (double)(worldIn.w_1457_N.nextFloat() * 0.7f) + 0.06000000238418579 + 0.6;
            double d2 = (double)(worldIn.w_1457_N.nextFloat() * 0.7f) + (double)0.15f;
            Z_1993_T itemstack1 = itemstack.t_148_a();
            n_1494_c itementity = new n_1494_c(worldIn, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, itemstack1);
            itementity.t_148_a();
            worldIn.a_(itementity);
        }
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!state.n_1700_B(newState.J_1907_R())) {
            this.n_1700_B(worldIn, pos);
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new JukeboxBlockEntity();
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        q_1613_l item;
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof JukeboxBlockEntity && (item = ((JukeboxBlockEntity)tileentity).P_1922_E().J_1907_R()) instanceof h_3036_f) {
            return ((h_3036_f)item).v_4262_N();
        }
        return 0;
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.R_4764_Y;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


