/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.LootContextParams;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.N_295_T;
import lightning.product.N_81_X;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_563_h;
import lightning.product.DirectionProperty;
import lightning.product.h_4152_b;
import lightning.product.i_2154_H;
import lightning.product.m_3054_I;
import lightning.product.q_1704_m;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.t_3546_P;
import lightning.product.x_1688_C;
import lightning.product.x_268_Y;
import lightning.product.y_1539_W;

public class s_3401_U
extends BaseEntityBlock {
    public static final DirectionProperty P_4830_p = N_81_X.P_4830_p;
    public static final e_563_h<y_1539_W> h_1847_R = N_81_X.h_1847_R;

    public s_3401_U(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.R_4764_Y)).n_1700_B(h_1847_R, y_1539_W.n_1700_B));
    }

    @Override
    @Nullable
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return null;
    }

    public static i_2154_H n_1700_B(K_4074_S state, b_257_Y direction, boolean extending, boolean shouldHeadBeRendered) {
        return new N_295_T(state, direction, extending, shouldHeadBeRendered);
    }

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        i_2154_H tileentity;
        if (!state.n_1700_B(newState.J_1907_R()) && (tileentity = worldIn.getTileEntity(pos)) instanceof N_295_T) {
            ((N_295_T)tileentity).P_4830_p();
        }
    }

    @Override
    public void n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state) {
        c_1514_x blockpos = pos.offset(state.R_4764_Y(P_4830_p).u_1723_Y());
        K_4074_S blockstate = worldIn.getBlockState(blockpos);
        if (blockstate.J_1907_R() instanceof h_4152_b && blockstate.R_4764_Y(h_4152_b.h_1847_R).booleanValue()) {
            worldIn.n_1700_B(blockpos, false);
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (!worldIn.Y_259_p && worldIn.getTileEntity(pos) == null) {
            worldIn.n_1700_B(pos, false);
            return m_3054_I.J_1907_R;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public List<Z_1993_T> n_1700_B(K_4074_S state, q_1704_m.n_1700_B builder) {
        N_295_T pistontileentity = this.n_1700_B((BlockGetter)builder.n_1700_B(), new c_1514_x(builder.n_1700_B(LootContextParams.u_1723_Y)));
        return pistontileentity == null ? Collections.emptyList() : pistontileentity.M_588_G().n_1700_B(builder);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return x_268_Y.n_1700_B();
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        N_295_T pistontileentity = this.n_1700_B(worldIn, pos);
        return pistontileentity != null ? pistontileentity.n_1700_B(worldIn, pos) : x_268_Y.n_1700_B();
    }

    @Nullable
    private N_295_T n_1700_B(BlockGetter blockReader, c_1514_x pos) {
        i_2154_H tileentity = blockReader.getTileEntity(pos);
        return tileentity instanceof N_295_T ? (N_295_T)tileentity : null;
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return Z_1993_T.J_1907_R;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return state.n_1700_B(mirrorIn.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


