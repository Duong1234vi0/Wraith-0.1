/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.O_2369_F;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_1266_O;
import lightning.product.SoundEvents;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_3591_l;
import lightning.product.o_3946_o;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;
import lightning.product.x_268_Y;

public class BubbleColumnBlock
extends T_2915_h
implements o_3946_o {
    public static final U_1266_O P_4830_p = BlockStateProperties.P_1922_E;

    public BubbleColumnBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, true));
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        K_4074_S blockstate = worldIn.getBlockState(pos.up());
        if (blockstate.v_4262_N()) {
            entityIn.P_4830_p(state.R_4764_Y(P_4830_p));
            if (!worldIn.Y_259_p) {
                e_3591_l serverworld = (e_3591_l)worldIn;
                for (int i = 0; i < 2; ++i) {
                    serverworld.n_1700_B(ParticleTypes.g_2268_R, (double)pos.getX() + worldIn.w_1457_N.nextDouble(), pos.getY() + 1, (double)pos.getZ() + worldIn.w_1457_N.nextDouble(), 1, 0.0, 0.0, 0.0, 1.0);
                    serverworld.n_1700_B(ParticleTypes.P_1922_E, (double)pos.getX() + worldIn.w_1457_N.nextDouble(), pos.getY() + 1, (double)pos.getZ() + worldIn.w_1457_N.nextDouble(), 1, 0.0, 0.01, 0.0, 0.2);
                }
            }
        } else {
            entityIn.h_1847_R(state.R_4764_Y(P_4830_p));
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, K_4074_S oldState, boolean isMoving) {
        BubbleColumnBlock.n_1700_B((LevelAccessor)worldIn, pos.up(), BubbleColumnBlock.n_1700_B((BlockGetter)worldIn, pos.down()));
    }

    @Override
    public void J_1907_R(K_4074_S state, e_3591_l worldIn, c_1514_x pos, Random rand) {
        BubbleColumnBlock.n_1700_B((LevelAccessor)worldIn, pos.up(), BubbleColumnBlock.n_1700_B((BlockGetter)worldIn, pos));
    }

    @Override
    public FluidState P_1922_E(K_4074_S state) {
        return Fluids.R_4764_Y.n_1700_B(false);
    }

    public static void n_1700_B(LevelAccessor world, c_1514_x pos, boolean drag) {
        if (BubbleColumnBlock.n_1700_B(world, pos)) {
            world.n_1700_B(pos, (K_4074_S)a_3742_W.S_4325_V.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, drag), 2);
        }
    }

    public static boolean n_1700_B(LevelAccessor world, c_1514_x pos) {
        FluidState fluidstate = world.getFluidState(pos);
        return world.getBlockState(pos).n_1700_B(a_3742_W.c_3005_b) && fluidstate.P_1922_E() >= 8 && fluidstate.J_1907_R();
    }

    private static boolean n_1700_B(BlockGetter reader, c_1514_x pos) {
        K_4074_S blockstate = reader.getBlockState(pos);
        if (blockstate.n_1700_B(a_3742_W.S_4325_V)) {
            return blockstate.R_4764_Y(P_4830_p);
        }
        return !blockstate.n_1700_B(a_3742_W.C_415_h);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        double d0 = pos.getX();
        double d1 = pos.getY();
        double d2 = pos.getZ();
        if (stateIn.R_4764_Y(P_4830_p).booleanValue()) {
            worldIn.J_1907_R(ParticleTypes.s_2632_s, d0 + 0.5, d1 + 0.8, d2, 0.0, 0.0, 0.0);
            if (rand.nextInt(200) == 0) {
                worldIn.n_1700_B(d0, d1, d2, SoundEvents.C_290_v, D_38_f.P_1922_E, 0.2f + rand.nextFloat() * 0.2f, 0.9f + rand.nextFloat() * 0.15f, false);
            }
        } else {
            worldIn.J_1907_R(ParticleTypes.l_1233_K, d0 + 0.5, d1, d2 + 0.5, 0.0, 0.04, 0.0);
            worldIn.J_1907_R(ParticleTypes.l_1233_K, d0 + (double)rand.nextFloat(), d1 + (double)rand.nextFloat(), d2 + (double)rand.nextFloat(), 0.0, 0.04, 0.0);
            if (rand.nextInt(200) == 0) {
                worldIn.n_1700_B(d0, d1, d2, SoundEvents.RealmsConfirmScreen, D_38_f.P_1922_E, 0.2f + rand.nextFloat() * 0.2f, 0.9f + rand.nextFloat() * 0.15f, false);
            }
        }
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        if (!stateIn.n_1700_B(worldIn, currentPos)) {
            return a_3742_W.c_3005_b.multiplayerClientSuggestionProvider();
        }
        if (facing == b_257_Y.n_1700_B) {
            worldIn.n_1700_B(currentPos, (K_4074_S)a_3742_W.S_4325_V.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, BubbleColumnBlock.n_1700_B((BlockGetter)worldIn, facingPos)), 2);
        } else if (facing == b_257_Y.J_1907_R && !facingState.n_1700_B(a_3742_W.S_4325_V) && BubbleColumnBlock.n_1700_B(worldIn, facingPos)) {
            worldIn.u_2550_I().n_1700_B(currentPos, this, 5);
        }
        worldIn.M_588_G().n_1700_B(currentPos, Fluids.R_4764_Y, Fluids.R_4764_Y.n_1700_B(worldIn));
        return super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        K_4074_S blockstate = worldIn.getBlockState(pos.down());
        return blockstate.n_1700_B(a_3742_W.S_4325_V) || blockstate.n_1700_B(a_3742_W.LevitationControl) || blockstate.n_1700_B(a_3742_W.C_415_h);
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return x_268_Y.n_1700_B();
    }

    @Override
    public O_2369_F n_1700_B(K_4074_S state) {
        return O_2369_F.n_1700_B;
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public Fluid J_1907_R(LevelAccessor worldIn, c_1514_x pos, K_4074_S state) {
        worldIn.n_1700_B(pos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider(), 11);
        return Fluids.R_4764_Y;
    }
}



