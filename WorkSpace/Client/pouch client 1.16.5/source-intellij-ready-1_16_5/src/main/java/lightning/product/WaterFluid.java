/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.FluidTags;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.ParticleOptions;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.U_4243_e;
import lightning.product.SoundEvents;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.i_2154_H;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.s_3834_w;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;

public abstract class WaterFluid
extends U_4243_e {
    @Override
    public Fluid G_564_y() {
        return Fluids.J_1907_R;
    }

    @Override
    public Fluid P_1922_E() {
        return Fluids.R_4764_Y;
    }

    @Override
    public q_1613_l n_1700_B() {
        return Items.W_2770_z;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, FluidState state, Random random) {
        if (!state.J_1907_R() && !state.R_4764_Y(n_1700_B).booleanValue()) {
            if (random.nextInt(64) == 0) {
                worldIn.n_1700_B((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundEvents.w_4059_h, D_38_f.P_1922_E, random.nextFloat() * 0.25f + 0.75f, random.nextFloat() + 0.5f, false);
            }
        } else if (random.nextInt(10) == 0) {
            worldIn.n_1700_B(ParticleTypes.c_4037_x, (double)pos.getX() + random.nextDouble(), (double)pos.getY() + random.nextDouble(), (double)pos.getZ() + random.nextDouble(), 0.0, 0.0, 0.0);
        }
    }

    @Override
    @Nullable
    public ParticleOptions t_148_a() {
        return ParticleTypes.P_4830_p;
    }

    @Override
    protected boolean u_1723_Y() {
        return true;
    }

    @Override
    protected void n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state) {
        i_2154_H tileentity = state.J_1907_R().G_564_y() ? worldIn.getTileEntity(pos) : null;
        T_2915_h.n_1700_B(state, worldIn, pos, tileentity);
    }

    @Override
    public int J_1907_R(T_1316_M worldIn) {
        return 4;
    }

    @Override
    public K_4074_S J_1907_R(FluidState state) {
        return (K_4074_S)a_3742_W.c_3005_b.multiplayerClientSuggestionProvider().n_1700_B(s_3834_w.P_4830_p, WaterFluid.P_1922_E(state));
    }

    @Override
    public boolean n_1700_B(Fluid fluidIn) {
        return fluidIn == Fluids.R_4764_Y || fluidIn == Fluids.J_1907_R;
    }

    @Override
    public int R_4764_Y(T_1316_M worldIn) {
        return 1;
    }

    @Override
    public int n_1700_B(T_1316_M p_205569_1_) {
        return 5;
    }

    @Override
    public boolean n_1700_B(FluidState fluidState, BlockGetter blockReader, c_1514_x pos, Fluid fluid, b_257_Y direction) {
        return direction == b_257_Y.n_1700_B && !fluid.n_1700_B(FluidTags.J_1907_R);
    }

    @Override
    protected float R_4764_Y() {
        return 100.0f;
    }

    public static class J_1907_R
    extends WaterFluid {
        @Override
        public int G_564_y(FluidState state) {
            return 8;
        }

        @Override
        public boolean R_4764_Y(FluidState state) {
            return true;
        }
    }

    public static class n_1700_B
    extends WaterFluid {
        @Override
        protected void n_1700_B(Y_1835_y.n_1700_B<Fluid, FluidState> builder) {
            super.n_1700_B(builder);
            builder.n_1700_B(new v_3760_Q[]{J_1907_R});
        }

        @Override
        public int G_564_y(FluidState state) {
            return state.R_4764_Y(J_1907_R);
        }

        @Override
        public boolean R_4764_Y(FluidState state) {
            return false;
        }
    }
}


