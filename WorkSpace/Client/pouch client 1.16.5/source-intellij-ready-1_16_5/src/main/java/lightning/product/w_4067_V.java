/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.w_1454_v;

public abstract class w_4067_V
extends T_2915_h {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 0.5, 15.0);
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 1.0, 15.0);
    protected static final I_4817_s Q_4569_t = new I_4817_s(0.125, 0.0, 0.125, 0.875, 0.25, 0.875);

    protected w_4067_V(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return this.v_4262_N(state) > 0 ? P_4830_p : h_1847_R;
    }

    protected int J_1907_R() {
        return 20;
    }

    @Override
    public boolean n_1700_B() {
        return true;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing == b_257_Y.n_1700_B && !stateIn.n_1700_B(worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        c_1514_x blockpos = pos.down();
        return w_4067_V.R_4764_Y(worldIn, blockpos) || w_4067_V.n_1700_B(worldIn, blockpos, b_257_Y.J_1907_R);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        int i = this.v_4262_N(state);
        if (i > 0) {
            this.n_1700_B((b_4507_u)worldIn, pos, state, i);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        int i;
        if (!worldIn.Y_259_p && (i = this.v_4262_N(state)) == 0) {
            this.n_1700_B(worldIn, pos, state, i);
        }
    }

    protected void n_1700_B(b_4507_u worldIn, c_1514_x pos, K_4074_S state, int oldRedstoneStrength) {
        boolean flag1;
        int i = this.J_1907_R(worldIn, pos);
        boolean flag = oldRedstoneStrength > 0;
        boolean bl = flag1 = i > 0;
        if (oldRedstoneStrength != i) {
            K_4074_S blockstate = this.n_1700_B(state, i);
            worldIn.n_1700_B(pos, blockstate, 2);
            this.n_1700_B(worldIn, pos);
            worldIn.n_1700_B(pos, state, blockstate);
        }
        if (!flag1 && flag) {
            this.J_1907_R((LevelAccessor)worldIn, pos);
        } else if (flag1 && !flag) {
            this.n_1700_B((LevelAccessor)worldIn, pos);
        }
        if (flag1) {
            worldIn.u_2550_I().n_1700_B(new c_1514_x(pos), this, this.J_1907_R());
        }
    }

    protected abstract void n_1700_B(LevelAccessor var1, c_1514_x var2);

    protected abstract void J_1907_R(LevelAccessor var1, c_1514_x var2);

    @Override
    public void J_1907_R(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S newState, boolean isMoving) {
        if (!isMoving && !state.n_1700_B(newState.J_1907_R())) {
            if (this.v_4262_N(state) > 0) {
                this.n_1700_B(worldIn, pos);
            }
            super.J_1907_R(state, worldIn, pos, newState, isMoving);
        }
    }

    protected void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        worldIn.J_1907_R(pos, this);
        worldIn.J_1907_R(pos.down(), this);
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return this.v_4262_N(blockState);
    }

    @Override
    public int J_1907_R(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return side == b_257_Y.J_1907_R ? this.v_4262_N(blockState) : 0;
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.J_1907_R;
    }

    protected abstract int J_1907_R(b_4507_u var1, c_1514_x var2);

    protected abstract int v_4262_N(K_4074_S var1);

    protected abstract K_4074_S n_1700_B(K_4074_S var1, int var2);
}


