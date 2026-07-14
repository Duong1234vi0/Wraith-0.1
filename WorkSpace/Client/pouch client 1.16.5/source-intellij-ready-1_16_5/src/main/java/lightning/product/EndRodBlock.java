/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.CollisionContext;
import lightning.product.Y_1835_y;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.DirectionalBlock;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.t_3546_P;
import lightning.product.v_3760_Q;
import lightning.product.w_1454_v;

public class EndRodBlock
extends DirectionalBlock {
    protected static final s_1395_c h_1847_R = T_2915_h.n_1700_B(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    protected static final s_1395_c Q_4569_t = T_2915_h.n_1700_B(6.0, 6.0, 0.0, 10.0, 10.0, 16.0);
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(0.0, 6.0, 6.0, 16.0, 10.0, 10.0);

    protected EndRodBlock(q_4293_E.P_1922_E builder) {
        super(builder);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, b_257_Y.J_1907_R));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return (K_4074_S)state.n_1700_B(P_4830_p, mirrorIn.J_1907_R(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        switch (state.R_4764_Y(P_4830_p).h_1847_R()) {
            default: {
                return M_182_A;
            }
            case R_4764_Y: {
                return Q_4569_t;
            }
            case J_1907_R: 
        }
        return h_1847_R;
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y direction = context.getFace();
        K_4074_S blockstate = context.getWorld().getBlockState(context.getPos().offset(direction.u_1723_Y()));
        return blockstate.n_1700_B(this) && blockstate.R_4764_Y(P_4830_p) == direction ? (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, direction.u_1723_Y()) : (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, direction);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        b_257_Y direction = stateIn.R_4764_Y(P_4830_p);
        double d0 = (double)pos.getX() + 0.55 - (double)(rand.nextFloat() * 0.1f);
        double d1 = (double)pos.getY() + 0.55 - (double)(rand.nextFloat() * 0.1f);
        double d2 = (double)pos.getZ() + 0.55 - (double)(rand.nextFloat() * 0.1f);
        double d3 = 0.4f - (rand.nextFloat() + rand.nextFloat()) * 0.4f;
        if (rand.nextInt(5) == 0) {
            worldIn.n_1700_B(ParticleTypes.Y_601_j, d0 + (double)direction.t_148_a() * d3, d1 + (double)direction.s_956_w() * d3, d2 + (double)direction.u_2550_I() * d3, rand.nextGaussian() * 0.005, rand.nextGaussian() * 0.005, rand.nextGaussian() * 0.005);
        }
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public w_1454_v G_564_y(K_4074_S state) {
        return w_1454_v.n_1700_B;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, t_3546_P type) {
        return false;
    }
}


