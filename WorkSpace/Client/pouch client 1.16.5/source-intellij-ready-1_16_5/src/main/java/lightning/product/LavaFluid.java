/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.FluidTags;
import lightning.product.D_38_f;
import lightning.product.BlockGetter;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.BaseFireBlock;
import lightning.product.ParticleOptions;
import lightning.product.T_1316_M;
import lightning.product.U_4243_e;
import lightning.product.SoundEvents;
import lightning.product.Y_1835_y;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.s_3834_w;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;
import lightning.product.v_3760_Q;

public abstract class LavaFluid
extends U_4243_e {
    @Override
    public Fluid G_564_y() {
        return Fluids.G_564_y;
    }

    @Override
    public Fluid P_1922_E() {
        return Fluids.P_1922_E;
    }

    @Override
    public q_1613_l n_1700_B() {
        return Items.u_1934_K;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, c_1514_x pos, FluidState state, Random random) {
        c_1514_x blockpos = pos.up();
        if (worldIn.getBlockState(blockpos).v_4262_N() && !worldIn.getBlockState(blockpos).t_148_a(worldIn, blockpos)) {
            if (random.nextInt(100) == 0) {
                double d0 = (double)pos.getX() + random.nextDouble();
                double d1 = (double)pos.getY() + 1.0;
                double d2 = (double)pos.getZ() + random.nextDouble();
                worldIn.n_1700_B(ParticleTypes.G_624_v, d0, d1, d2, 0.0, 0.0, 0.0);
                worldIn.n_1700_B(d0, d1, d2, SoundEvents.Party, D_38_f.P_1922_E, 0.2f + random.nextFloat() * 0.2f, 0.9f + random.nextFloat() * 0.15f, false);
            }
            if (random.nextInt(200) == 0) {
                worldIn.n_1700_B(pos.getX(), (double)pos.getY(), (double)pos.getZ(), SoundEvents.NameProtect, D_38_f.P_1922_E, 0.2f + random.nextFloat() * 0.2f, 0.9f + random.nextFloat() * 0.15f, false);
            }
        }
    }

    @Override
    public void J_1907_R(b_4507_u world, c_1514_x pos, FluidState state, Random random) {
        block7: {
            if (!world.H_1990_U().J_1907_R(A_2352_Z.n_1700_B)) break block7;
            int i = random.nextInt(3);
            if (i > 0) {
                c_1514_x blockpos = pos;
                for (int j = 0; j < i; ++j) {
                    if (!world.multiplayerClientSuggestionProvider(blockpos = blockpos.add(random.nextInt(3) - 1, 1, random.nextInt(3) - 1))) {
                        return;
                    }
                    K_4074_S blockstate = world.getBlockState(blockpos);
                    if (blockstate.v_4262_N()) {
                        if (!this.n_1700_B((T_1316_M)world, blockpos)) continue;
                        world.J_1907_R(blockpos, BaseFireBlock.n_1700_B(world, blockpos));
                        return;
                    }
                    if (!blockstate.R_4764_Y().R_4764_Y()) continue;
                    return;
                }
            } else {
                for (int k = 0; k < 3; ++k) {
                    c_1514_x blockpos1 = pos.add(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
                    if (!world.multiplayerClientSuggestionProvider(blockpos1)) {
                        return;
                    }
                    if (!world.u_1723_Y(blockpos1.up()) || !this.J_1907_R(world, blockpos1)) continue;
                    world.J_1907_R(blockpos1.up(), BaseFireBlock.n_1700_B(world, blockpos1));
                }
            }
        }
    }

    private boolean n_1700_B(T_1316_M worldIn, c_1514_x pos) {
        for (b_257_Y direction : b_257_Y.values()) {
            if (!this.J_1907_R(worldIn, pos.offset(direction))) continue;
            return true;
        }
        return false;
    }

    private boolean J_1907_R(T_1316_M worldIn, c_1514_x pos) {
        return pos.getY() >= 0 && pos.getY() < 256 && !worldIn.M_588_G(pos) ? false : worldIn.getBlockState(pos).R_4764_Y().G_564_y();
    }

    @Override
    @Nullable
    public ParticleOptions t_148_a() {
        return ParticleTypes.s_956_w;
    }

    @Override
    protected void n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S state) {
        this.n_1700_B(worldIn, pos);
    }

    @Override
    public int J_1907_R(T_1316_M worldIn) {
        return worldIn.G_624_v().G_564_y() ? 4 : 2;
    }

    @Override
    public K_4074_S J_1907_R(FluidState state) {
        return (K_4074_S)a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider().n_1700_B(s_3834_w.P_4830_p, LavaFluid.P_1922_E(state));
    }

    @Override
    public boolean n_1700_B(Fluid fluidIn) {
        return fluidIn == Fluids.P_1922_E || fluidIn == Fluids.G_564_y;
    }

    @Override
    public int R_4764_Y(T_1316_M worldIn) {
        return worldIn.G_624_v().G_564_y() ? 1 : 2;
    }

    @Override
    public boolean n_1700_B(FluidState fluidState, BlockGetter blockReader, c_1514_x pos, Fluid fluid, b_257_Y direction) {
        return fluidState.n_1700_B(blockReader, pos) >= 0.44444445f && fluid.n_1700_B(FluidTags.J_1907_R);
    }

    @Override
    public int n_1700_B(T_1316_M p_205569_1_) {
        return p_205569_1_.G_624_v().G_564_y() ? 10 : 30;
    }

    @Override
    public int n_1700_B(b_4507_u world, c_1514_x pos, FluidState p_215667_3_, FluidState p_215667_4_) {
        int i = this.n_1700_B(world);
        if (!(p_215667_3_.R_4764_Y() || p_215667_4_.R_4764_Y() || p_215667_3_.R_4764_Y(n_1700_B).booleanValue() || p_215667_4_.R_4764_Y(n_1700_B).booleanValue() || !(p_215667_4_.n_1700_B((BlockGetter)world, pos) > p_215667_3_.n_1700_B((BlockGetter)world, pos)) || world.e_4240_b().nextInt(4) == 0)) {
            i *= 4;
        }
        return i;
    }

    private void n_1700_B(LevelAccessor world, c_1514_x pos) {
        world.R_4764_Y(1501, pos, 0);
    }

    @Override
    protected boolean u_1723_Y() {
        return false;
    }

    @Override
    protected void n_1700_B(LevelAccessor worldIn, c_1514_x pos, K_4074_S blockStateIn, b_257_Y direction, FluidState fluidStateIn) {
        if (direction == b_257_Y.n_1700_B) {
            FluidState fluidstate = worldIn.getFluidState(pos);
            if (this.n_1700_B(FluidTags.R_4764_Y) && fluidstate.n_1700_B(FluidTags.J_1907_R)) {
                if (blockStateIn.J_1907_R() instanceof s_3834_w) {
                    worldIn.n_1700_B(pos, a_3742_W.J_1907_R.multiplayerClientSuggestionProvider(), 3);
                }
                this.n_1700_B(worldIn, pos);
                return;
            }
        }
        super.n_1700_B(worldIn, pos, blockStateIn, direction, fluidStateIn);
    }

    @Override
    protected boolean s_956_w() {
        return true;
    }

    @Override
    protected float R_4764_Y() {
        return 100.0f;
    }

    public static class J_1907_R
    extends LavaFluid {
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
    extends LavaFluid {
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



