/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.ParticleOptions;
import lightning.product.T_1316_M;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.q_1613_l;
import lightning.product.r_109_r;
import lightning.product.s_1395_c;
import lightning.product.w_424_u;

public abstract class Fluid {
    public static final w_424_u<FluidState> R_4764_Y = new w_424_u();
    protected final Y_1835_y<Fluid, FluidState> G_564_y;
    private FluidState n_1700_B;

    protected Fluid() {
        Y_1835_y.n_1700_B<Fluid, FluidState> builder = new Y_1835_y.n_1700_B<Fluid, FluidState>(this);
        this.n_1700_B(builder);
        this.G_564_y = builder.n_1700_B(Fluid::w_1484_f, FluidState::new);
        this.u_1723_Y(this.G_564_y.J_1907_R());
    }

    protected void n_1700_B(Y_1835_y.n_1700_B<Fluid, FluidState> builder) {
    }

    public Y_1835_y<Fluid, FluidState> v_4262_N() {
        return this.G_564_y;
    }

    protected final void u_1723_Y(FluidState state) {
        this.n_1700_B = state;
    }

    public final FluidState w_1484_f() {
        return this.n_1700_B;
    }

    public abstract q_1613_l n_1700_B();

    protected void n_1700_B(b_4507_u worldIn, c_1514_x pos, FluidState state, Random random) {
    }

    protected void n_1700_B(b_4507_u worldIn, c_1514_x pos, FluidState state) {
    }

    protected void J_1907_R(b_4507_u world, c_1514_x pos, FluidState state, Random random) {
    }

    @Nullable
    protected ParticleOptions t_148_a() {
        return null;
    }

    protected abstract boolean n_1700_B(FluidState var1, BlockGetter var2, c_1514_x var3, Fluid var4, b_257_Y var5);

    protected abstract e_2866_D n_1700_B(BlockGetter var1, c_1514_x var2, FluidState var3);

    public abstract int n_1700_B(T_1316_M var1);

    protected boolean s_956_w() {
        return false;
    }

    protected boolean J_1907_R() {
        return false;
    }

    protected abstract float R_4764_Y();

    public abstract float n_1700_B(FluidState var1, BlockGetter var2, c_1514_x var3);

    public abstract float n_1700_B(FluidState var1);

    protected abstract K_4074_S J_1907_R(FluidState var1);

    public abstract boolean R_4764_Y(FluidState var1);

    public abstract int G_564_y(FluidState var1);

    public boolean n_1700_B(Fluid fluidIn) {
        return fluidIn == this;
    }

    public boolean n_1700_B(r_109_r<Fluid> tagIn) {
        return tagIn.n_1700_B(this);
    }

    public abstract s_1395_c J_1907_R(FluidState var1, BlockGetter var2, c_1514_x var3);
}


