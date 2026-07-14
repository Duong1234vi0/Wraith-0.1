/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.Optional;
import java.util.Random;
import lightning.product.FluidTags;
import lightning.product.B_4088_l;
import lightning.product.BlockStateProperties;
import lightning.product.D_38_f;
import lightning.product.F_1241_B;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.G_652_w;
import lightning.product.K_4074_S;
import lightning.product.P_11_z;
import lightning.product.T_2915_h;
import lightning.product.SoundEvents;
import lightning.product.ExplosionDamageCalculator;
import lightning.product.Y_1835_y;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.g_88_D;
import lightning.product.m_3054_I;
import lightning.product.o_3283_D;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.ParticleTypes;
import lightning.product.t_3546_P;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;
import lightning.product.z_3539_x;

public class P_2605_j
extends T_2915_h {
    public static final g_88_D P_4830_p = BlockStateProperties.RealmsServerPing;
    private static final ImmutableList<z_3539_x> h_1847_R = ImmutableList.of((Object)new z_3539_x(0, 0, -1), (Object)new z_3539_x(-1, 0, 0), (Object)new z_3539_x(0, 0, 1), (Object)new z_3539_x(1, 0, 0), (Object)new z_3539_x(-1, 0, -1), (Object)new z_3539_x(1, 0, -1), (Object)new z_3539_x(-1, 0, 1), (Object)new z_3539_x(1, 0, 1));
    private static final ImmutableList<z_3539_x> Q_4569_t = new ImmutableList.Builder().addAll(h_1847_R).addAll(h_1847_R.stream().map(z_3539_x::down).iterator()).addAll(h_1847_R.stream().map(z_3539_x::up).iterator()).add((Object)new z_3539_x(0, 1, 0)).build();

    public P_2605_j(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, 0));
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        B_4088_l serverplayerentity;
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        if (handIn == x_1688_C.n_1700_B && !P_2605_j.n_1700_B(itemstack) && P_2605_j.n_1700_B(player.R_4764_Y(x_1688_C.J_1907_R))) {
            return m_3054_I.R_4764_Y;
        }
        if (P_2605_j.n_1700_B(itemstack) && P_2605_j.w_1484_f(state)) {
            P_2605_j.n_1700_B(worldIn, pos, state);
            if (!player.C_415_h.G_564_y) {
                itemstack.v_4262_N(1);
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        if (state.R_4764_Y(P_4830_p) == 0) {
            return m_3054_I.R_4764_Y;
        }
        if (!P_2605_j.n_1700_B(worldIn)) {
            if (!worldIn.Y_259_p) {
                this.R_4764_Y(state, worldIn, pos);
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        if (!(worldIn.Y_259_p || (serverplayerentity = (B_4088_l)player).H_1990_U() == worldIn.g_2268_R() && serverplayerentity.X_933_l().equals(pos))) {
            serverplayerentity.n_1700_B(worldIn.g_2268_R(), pos, 0.0f, false, true);
            worldIn.n_1700_B((a_3913_L)null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.T_2391_T, D_38_f.P_1922_E, 1.0f, 1.0f);
            return m_3054_I.n_1700_B;
        }
        return m_3054_I.J_1907_R;
    }

    private static boolean n_1700_B(Z_1993_T stack) {
        return stack.J_1907_R() == Items.Q_2753_H;
    }

    private static boolean w_1484_f(K_4074_S state) {
        return state.R_4764_Y(P_4830_p) < 4;
    }

    private static boolean n_1700_B(c_1514_x pos, b_4507_u world) {
        FluidState fluidstate = world.getFluidState(pos);
        if (!fluidstate.n_1700_B(FluidTags.J_1907_R)) {
            return false;
        }
        if (fluidstate.J_1907_R()) {
            return true;
        }
        float f = fluidstate.P_1922_E();
        if (f < 2.0f) {
            return false;
        }
        FluidState fluidstate1 = world.getFluidState(pos.down());
        return !fluidstate1.n_1700_B(FluidTags.J_1907_R);
    }

    private void R_4764_Y(K_4074_S state, b_4507_u world, c_1514_x pos) {
        world.n_1700_B(pos, false);
        boolean flag = b_257_Y.R_4764_Y.n_1700_B.n_1700_B().map(pos::offset).anyMatch(posIn -> P_2605_j.n_1700_B(posIn, world));
        final boolean flag1 = flag || world.getFluidState(pos.up()).n_1700_B(FluidTags.J_1907_R);
        ExplosionDamageCalculator explosioncontext = new ExplosionDamageCalculator(this){

            @Override
            public Optional<Float> n_1700_B(F_1241_B explosion, BlockGetter reader, c_1514_x pos, K_4074_S state, FluidState fluid) {
                return pos.equals(pos) && flag1 ? Optional.of(Float.valueOf(a_3742_W.c_3005_b.u_2550_I())) : super.n_1700_B(explosion, reader, pos, state, fluid);
            }
        };
        world.n_1700_B(null, P_11_z.n_1700_B(), explosioncontext, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 5.0f, true, F_1241_B.n_1700_B.R_4764_Y);
    }

    public static boolean n_1700_B(b_4507_u world) {
        return world.G_624_v().t_148_a();
    }

    public static void n_1700_B(b_4507_u world, c_1514_x pos, K_4074_S state) {
        world.n_1700_B(pos, (K_4074_S)state.n_1700_B(P_4830_p, state.R_4764_Y(P_4830_p) + 1), 3);
        world.n_1700_B((a_3913_L)null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.S_4998_h, D_38_f.P_1922_E, 1.0f, 1.0f);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        if (stateIn.R_4764_Y(P_4830_p) != 0) {
            if (rand.nextInt(100) == 0) {
                worldIn.n_1700_B((a_3913_L)null, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.RequirementsStrategy, D_38_f.P_1922_E, 1.0f, 1.0f);
            }
            double d0 = (double)pos.getX() + 0.5 + (0.5 - rand.nextDouble());
            double d1 = (double)pos.getY() + 1.0;
            double d2 = (double)pos.getZ() + 0.5 + (0.5 - rand.nextDouble());
            double d3 = (double)rand.nextFloat() * 0.04;
            worldIn.n_1700_B(ParticleTypes.Ops, d0, d1, d2, 0.0, d3, 0.0);
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public boolean u_1723_Y(K_4074_S state) {
        return true;
    }

    public static int n_1700_B(K_4074_S state, int scale) {
        return u_530_F.G_564_y((float)(state.R_4764_Y(P_4830_p) - 0) / 4.0f * (float)scale);
    }

    @Override
    public int J_1907_R(K_4074_S blockState, b_4507_u worldIn, c_1514_x pos) {
        return P_2605_j.n_1700_B(blockState, 15);
    }

    public static Optional<e_2866_D> n_1700_B(t_5_h<?> entity, o_3283_D reader, c_1514_x pos) {
        Optional<e_2866_D> optional = P_2605_j.n_1700_B(entity, reader, pos, true);
        return optional.isPresent() ? optional : P_2605_j.n_1700_B(entity, reader, pos, false);
    }

    private static Optional<e_2866_D> n_1700_B(t_5_h<?> type, o_3283_D collisionReader, c_1514_x pos, boolean checkCanSpawn) {
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (z_3539_x vector3i : Q_4569_t) {
            blockpos$mutable.n_1700_B(pos).J_1907_R(vector3i);
            e_2866_D vector3d = G_652_w.n_1700_B(type, collisionReader, blockpos$mutable, checkCanSpawn);
            if (vector3d == null) continue;
            return Optional.of(vector3d);
        }
        return Optional.empty();
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


