/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.B_4088_l;
import lightning.product.Projectile;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.Stats;
import lightning.product.U_3554_Q;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.g_88_D;
import lightning.product.h_384_L;
import lightning.product.q_4293_E;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;

public class TargetBlock
extends T_2915_h {
    private static final g_88_D P_4830_p = BlockStateProperties.q_1982_R;

    public TargetBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, K_4074_S state, BlockHitResult hit, Projectile projectile) {
        int i = TargetBlock.n_1700_B(worldIn, state, hit, projectile);
        N_4263_v entity = projectile.Y_601_j();
        if (entity instanceof B_4088_l) {
            B_4088_l serverplayerentity = (B_4088_l)entity;
            serverplayerentity.J_1907_R(Stats.j_1564_a);
            U_3554_Q.d_2461_k.n_1700_B(serverplayerentity, projectile, hit.P_1922_E(), i);
        }
    }

    private static int n_1700_B(LevelAccessor world, K_4074_S state, BlockHitResult result, N_4263_v entity) {
        int j;
        int i = TargetBlock.n_1700_B(result, result.P_1922_E());
        int n = j = entity instanceof h_384_L ? 20 : 8;
        if (!world.u_2550_I().n_1700_B(result.n_1700_B(), state.J_1907_R())) {
            TargetBlock.n_1700_B(world, state, i, result.n_1700_B(), j);
        }
        return i;
    }

    private static int n_1700_B(BlockHitResult result, e_2866_D vector) {
        b_257_Y direction = result.J_1907_R();
        double d0 = Math.abs(u_530_F.v_4262_N(vector.J_1907_R) - 0.5);
        double d1 = Math.abs(u_530_F.v_4262_N(vector.R_4764_Y) - 0.5);
        double d2 = Math.abs(u_530_F.v_4262_N(vector.G_564_y) - 0.5);
        b_257_Y.n_1700_B direction$axis = direction.h_1847_R();
        double d3 = direction$axis == b_257_Y.n_1700_B.J_1907_R ? Math.max(d0, d2) : (direction$axis == b_257_Y.n_1700_B.R_4764_Y ? Math.max(d0, d1) : Math.max(d1, d2));
        return Math.max(1, u_530_F.P_1922_E(15.0 * u_530_F.n_1700_B((0.5 - d3) / 0.5, 0.0, 1.0)));
    }

    private static void n_1700_B(LevelAccessor world, K_4074_S state, int power, c_1514_x pos, int waitTime) {
        world.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, power), 3);
        world.u_2550_I().n_1700_B(pos, state.J_1907_R(), waitTime);
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        if (state.R_4764_Y(P_4830_p) != 0) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, 0), 3);
        }
    }

    @Override
    public int n_1700_B(K_4074_S blockState, BlockGetter blockAccess, c_1514_x pos, b_257_Y side) {
        return blockState.R_4764_Y(P_4830_p);
    }

    @Override
    public boolean R_4764_Y(K_4074_S state) {
        return true;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        if (!(worldIn.v_4276_D() || state.n_1700_B(oldState.J_1907_R()) || state.R_4764_Y(P_4830_p) <= 0 || worldIn.u_2550_I().n_1700_B(pos, this))) {
            worldIn.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, 0), 18);
        }
    }
}


