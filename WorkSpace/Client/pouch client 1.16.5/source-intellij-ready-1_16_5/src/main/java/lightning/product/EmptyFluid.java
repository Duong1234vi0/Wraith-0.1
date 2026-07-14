/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.x_268_Y;

public class EmptyFluid
extends Fluid {
    @Override
    public q_1613_l n_1700_B() {
        return Items.n_1700_B;
    }

    @Override
    public boolean n_1700_B(FluidState fluidState, BlockGetter blockReader, c_1514_x pos, Fluid fluid, b_257_Y direction) {
        return true;
    }

    @Override
    public e_2866_D n_1700_B(BlockGetter blockReader, c_1514_x pos, FluidState fluidState) {
        return e_2866_D.n_1700_B;
    }

    @Override
    public int n_1700_B(T_1316_M p_205569_1_) {
        return 0;
    }

    @Override
    protected boolean J_1907_R() {
        return true;
    }

    @Override
    protected float R_4764_Y() {
        return 0.0f;
    }

    @Override
    public float n_1700_B(FluidState p_215662_1_, BlockGetter p_215662_2_, c_1514_x p_215662_3_) {
        return 0.0f;
    }

    @Override
    public float n_1700_B(FluidState p_223407_1_) {
        return 0.0f;
    }

    @Override
    protected K_4074_S J_1907_R(FluidState state) {
        return a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    }

    @Override
    public boolean R_4764_Y(FluidState state) {
        return false;
    }

    @Override
    public int G_564_y(FluidState state) {
        return 0;
    }

    @Override
    public s_1395_c J_1907_R(FluidState p_215664_1_, BlockGetter p_215664_2_, c_1514_x p_215664_3_) {
        return x_268_Y.n_1700_B();
    }
}


