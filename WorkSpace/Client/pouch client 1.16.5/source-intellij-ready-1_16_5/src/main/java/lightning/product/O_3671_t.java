/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.A_2352_Z;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.Z_4149_q;
import lightning.product.a_3160_D;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.e_563_h;
import lightning.product.ZombifiedPiglin;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.v_3760_Q;

public class O_3671_t
extends T_2915_h {
    public static final e_563_h<b_257_Y.n_1700_B> P_4830_p = BlockStateProperties.t_4043_B;
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);

    public O_3671_t(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.n_1700_B.n_1700_B));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        switch (state.R_4764_Y(P_4830_p)) {
            case R_4764_Y: {
                return Q_4569_t;
            }
        }
        return h_1847_R;
    }

    @Override
    public void n_1700_B(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random random) {
        if (worldIn.G_624_v().P_1922_E() && worldIn.H_1990_U().J_1907_R(A_2352_Z.G_564_y) && random.nextInt(2000) < worldIn.x_607_J().n_1700_B()) {
            ZombifiedPiglin entity;
            while (worldIn.getBlockState(pos).n_1700_B(this)) {
                pos = pos.down();
            }
            if (worldIn.getBlockState(pos).n_1700_B((BlockGetter)worldIn, pos, t_5_h.c_132_F) && (entity = t_5_h.c_132_F.n_1700_B(worldIn, null, null, null, pos.up(), a_3160_D.G_564_y, false, false)) != null) {
                entity.PlayerInfo();
            }
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        b_257_Y.n_1700_B direction$axis = facing.h_1847_R();
        b_257_Y.n_1700_B direction$axis1 = stateIn.R_4764_Y(P_4830_p);
        boolean flag = direction$axis1 != direction$axis && direction$axis.G_564_y();
        return !flag && !facingState.n_1700_B(this) && !new Z_4149_q(worldIn, currentPos, direction$axis1).R_4764_Y() ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (!entityIn.y_2772_m() && !entityIn.H_1883_T() && entityIn.L_103_L()) {
            entityIn.J_1907_R(pos);
        }
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (rand.nextInt(100) == 0) {
            worldIn.n_1700_B((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.u_925_K, D_38_f.P_1922_E, 0.5f, rand.nextFloat() * 0.4f + 0.8f, false);
        }
        for (int i = 0; i < 4; ++i) {
            double d0 = (double)pos.getX() + rand.nextDouble();
            double d1 = (double)pos.getY() + rand.nextDouble();
            double d2 = (double)pos.getZ() + rand.nextDouble();
            double d3 = ((double)rand.nextFloat() - 0.5) * 0.5;
            double d4 = ((double)rand.nextFloat() - 0.5) * 0.5;
            double d5 = ((double)rand.nextFloat() - 0.5) * 0.5;
            int j = rand.nextInt(2) * 2 - 1;
            if (!worldIn.getBlockState(pos.west()).n_1700_B(this) && !worldIn.getBlockState(pos.east()).n_1700_B(this)) {
                d0 = (double)pos.getX() + 0.5 + 0.25 * (double)j;
                d3 = rand.nextFloat() * 2.0f * (float)j;
            } else {
                d2 = (double)pos.getZ() + 0.5 + 0.25 * (double)j;
                d5 = rand.nextFloat() * 2.0f * (float)j;
            }
            worldIn.n_1700_B(ParticleTypes.g_221_o, d0, d1, d2, d3, d4, d5);
        }
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return Z_1993_T.J_1907_R;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        switch (rot) {
            case G_564_y: 
            case J_1907_R: {
                switch (state.R_4764_Y(P_4830_p)) {
                    case R_4764_Y: {
                        return (K_4074_S)state.n_1700_B(P_4830_p, b_257_Y.n_1700_B.n_1700_B);
                    }
                    case n_1700_B: {
                        return (K_4074_S)state.n_1700_B(P_4830_p, b_257_Y.n_1700_B.R_4764_Y);
                    }
                }
                return state;
            }
        }
        return state;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }
}


