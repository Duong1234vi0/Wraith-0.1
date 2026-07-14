/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.LevelAccessor;
import lightning.product.Material;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;

public class CactusBlock
extends T_2915_h {
    public static final g_88_D P_4830_p = BlockStateProperties.Ping;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 15.0, 15.0);
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    protected CactusBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (!state.n_1700_B((T_1316_M)worldIn, pos)) {
            worldIn.J_1907_R(pos, true);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        c_1514_x blockpos = pos.up();
        if (worldIn.u_1723_Y(blockpos)) {
            int i = 1;
            while (worldIn.getBlockState(pos.down(i)).n_1700_B(this)) {
                ++i;
            }
            if (i < 3) {
                int j = state.R_4764_Y(P_4830_p);
                if (j == 15) {
                    worldIn.J_1907_R(blockpos, this.multiplayerClientSuggestionProvider());
                    K_4074_S blockstate = (K_4074_S)state.n_1700_B(P_4830_p, 0);
                    worldIn.n_1700_B(pos, blockstate, 4);
                    blockstate.n_1700_B(worldIn, blockpos, this, pos, false);
                } else {
                    worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, j + 1), 4);
                }
            }
        }
    }

    @Override
    public s_1395_c J_1907_R(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return h_1847_R;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return Q_4569_t;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (!stateIn.n_1700_B(worldIn, currentPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 1);
        }
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            K_4074_S blockstate = worldIn.getBlockState(pos.offset(direction));
            Material material = blockstate.R_4764_Y();
            if (!material.J_1907_R() && !worldIn.getFluidState(pos.offset(direction)).n_1700_B(FluidTags.R_4764_Y)) continue;
            return false;
        }
        K_4074_S blockstate1 = worldIn.getBlockState(pos.down());
        return (blockstate1.n_1700_B(a_3742_W.d_3244_b) || blockstate1.n_1700_B(a_3742_W.A_4115_X) || blockstate1.n_1700_B(a_3742_W.Y_1740_V)) && !worldIn.getBlockState(pos.up()).R_4764_Y().n_1700_B();
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        entityIn.n_1700_B(P_11_z.s_956_w, 1.0f);
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


