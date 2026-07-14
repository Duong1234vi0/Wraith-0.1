/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.K_4074_S;
import lightning.product.ParticleOptions;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;
import lightning.product.LevelAccessor;

public class TorchBlock
extends T_2915_h {
    protected static final s_1395_c M_182_A = T_2915_h.n_1700_B(6.0, 0.0, 6.0, 10.0, 10.0, 10.0);
    protected final ParticleOptions t_1786_h;

    protected TorchBlock(q_4293_E.P_1922_E properties, ParticleOptions particleData) {
        super(properties);
        this.t_1786_h = particleData;
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return M_182_A;
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S stateIn, b_257_Y facing, K_4074_S facingState, LevelAccessor worldIn, c_1514_x currentPos, c_1514_x facingPos) {
        return facing == b_257_Y.n_1700_B && !this.n_1700_B(stateIn, worldIn, currentPos) ? a_3742_W.n_1700_B.multiplayerClientSuggestionProvider() : super.n_1700_B(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    public boolean n_1700_B(K_4074_S state, T_1316_M worldIn, c_1514_x pos) {
        return TorchBlock.n_1700_B(worldIn, pos.down(), b_257_Y.J_1907_R);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        double d0 = (double)pos.getX() + 0.5;
        double d1 = (double)pos.getY() + 0.7;
        double d2 = (double)pos.getZ() + 0.5;
        worldIn.n_1700_B(ParticleTypes.B_1668_F, d0, d1, d2, 0.0, 0.0, 0.0);
        worldIn.n_1700_B(this.t_1786_h, d0, d1, d2, 0.0, 0.0, 0.0);
    }
}


