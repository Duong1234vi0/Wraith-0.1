/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_1266_O;
import lightning.product.Y_1835_y;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_563_h;
import lightning.product.g_88_D;
import lightning.product.m_3054_I;
import lightning.product.q_4293_E;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.w_628_J;
import lightning.product.x_1688_C;

public class NoteBlock
extends T_2915_h {
    public static final e_563_h<w_628_J> P_4830_p = BlockStateProperties.H_1083_k;
    public static final U_1266_O h_1847_R = BlockStateProperties.C_2741_M;
    public static final g_88_D Q_4569_t = BlockStateProperties.V_1225_t;

    public NoteBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, w_628_J.n_1700_B)).n_1700_B(Q_4569_t, 0)).n_1700_B(h_1847_R, false));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, w_628_J.n_1700_B(context.getWorld().getBlockState(context.getPos().down())));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing == b_257_Y.n_1700_B ? (K_4074_S)stateIn.n_1700_B(P_4830_p, w_628_J.n_1700_B(facingState)) : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos, boolean isMoving) {
        boolean flag = worldIn.Y_601_j(pos);
        if (flag != state.R_4764_Y(h_1847_R)) {
            if (flag) {
                this.n_1700_B(worldIn, pos);
            }
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(h_1847_R, flag), 3);
        }
    }

    private void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        if (worldIn.getBlockState(pos.up()).v_4262_N()) {
            worldIn.n_1700_B(pos, this, 0, 0);
        }
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        if (worldIn.Y_259_p) {
            return m_3054_I.n_1700_B;
        }
        state = (K_4074_S)state.n_1700_B(Q_4569_t);
        worldIn.n_1700_B(pos, state, 3);
        this.n_1700_B(worldIn, pos);
        player.J_1907_R(Stats.O_508_d);
        return m_3054_I.J_1907_R;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player) {
        if (!worldIn.Y_259_p) {
            this.n_1700_B(worldIn, pos);
            player.J_1907_R(Stats.z_1333_t);
        }
    }

    @Override
    public boolean n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, int id, int param) {
        int i = state.R_4764_Y(Q_4569_t);
        float f = (float)Math.pow(2.0, (double)(i - 12) / 12.0);
        worldIn.n_1700_B((a_3913_L)null, pos, state.R_4764_Y(P_4830_p).J_1907_R(), D_38_f.R_4764_Y, 3.0f, f);
        worldIn.n_1700_B(ParticleTypes.q_4610_l, (double)pos.getX() + 0.5, (double)pos.getY() + 1.2, (double)pos.getZ() + 0.5, (double)i / 24.0, 0.0, 0.0);
        return true;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(P_4830_p, h_1847_R, Q_4569_t);
    }
}


