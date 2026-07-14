/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.MapCodec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.P_1008_U;
import lightning.product.ParticleOptions;
import lightning.product.V_3137_a;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.r_109_r;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.v_3760_Q;

public final class FluidState
extends P_1008_U<Fluid, FluidState> {
    public static final Codec<FluidState> n_1700_B = FluidState.n_1700_B(V_3137_a.G_624_v, Fluid::w_1484_f).stable();

    public FluidState(Fluid p_i232145_1_, ImmutableMap<v_3760_Q<?>, Comparable<?>> p_i232145_2_, MapCodec<FluidState> p_i232145_3_) {
        super(p_i232145_1_, p_i232145_2_, p_i232145_3_);
    }

    public Fluid n_1700_B() {
        return (Fluid)this.R_4764_Y;
    }

    public boolean J_1907_R() {
        return this.n_1700_B().R_4764_Y(this);
    }

    public boolean R_4764_Y() {
        return this.n_1700_B().J_1907_R();
    }

    public float n_1700_B(BlockGetter p_215679_1_, c_1514_x p_215679_2_) {
        return this.n_1700_B().n_1700_B(this, p_215679_1_, p_215679_2_);
    }

    public float G_564_y() {
        return this.n_1700_B().n_1700_B(this);
    }

    public int P_1922_E() {
        return this.n_1700_B().G_564_y(this);
    }

    public boolean J_1907_R(BlockGetter worldIn, c_1514_x pos) {
        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                c_1514_x blockpos = pos.add(i, 0, j);
                FluidState fluidstate = worldIn.getFluidState(blockpos);
                if (fluidstate.n_1700_B().n_1700_B(this.n_1700_B()) || worldIn.getBlockState(blockpos).t_148_a(worldIn, blockpos)) continue;
                return true;
            }
        }
        return false;
    }

    public void n_1700_B(b_4507_u worldIn, c_1514_x pos) {
        this.n_1700_B().n_1700_B(worldIn, pos, this);
    }

    public void n_1700_B(b_4507_u p_206881_1_, c_1514_x p_206881_2_, Random p_206881_3_) {
        this.n_1700_B().n_1700_B(p_206881_1_, p_206881_2_, this, p_206881_3_);
    }

    public boolean u_1723_Y() {
        return this.n_1700_B().s_956_w();
    }

    public void J_1907_R(b_4507_u worldIn, c_1514_x pos, Random random) {
        this.n_1700_B().J_1907_R(worldIn, pos, this, random);
    }

    public e_2866_D R_4764_Y(BlockGetter p_215673_1_, c_1514_x p_215673_2_) {
        return this.n_1700_B().n_1700_B(p_215673_1_, p_215673_2_, this);
    }

    public K_4074_S v_4262_N() {
        return this.n_1700_B().J_1907_R(this);
    }

    @Nullable
    public ParticleOptions w_1484_f() {
        return this.n_1700_B().t_148_a();
    }

    @Override
    public boolean n_1700_B(r_109_r<Fluid> tagIn) {
        return this.n_1700_B().n_1700_B(tagIn);
    }

    public float t_148_a() {
        return this.n_1700_B().R_4764_Y();
    }

    public boolean n_1700_B(BlockGetter p_215677_1_, c_1514_x p_215677_2_, Fluid p_215677_3_, b_257_Y p_215677_4_) {
        return this.n_1700_B().n_1700_B(this, p_215677_1_, p_215677_2_, p_215677_3_, p_215677_4_);
    }

    public s_1395_c G_564_y(BlockGetter p_215676_1_, c_1514_x p_215676_2_) {
        return this.n_1700_B().J_1907_R(this, p_215676_1_, p_215676_2_);
    }
}


