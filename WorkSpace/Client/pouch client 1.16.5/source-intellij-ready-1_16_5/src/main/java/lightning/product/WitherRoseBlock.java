/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.MobEffects;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.CollisionContext;
import lightning.product.a_3742_W;
import lightning.product.FlowerBlock;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.g_422_i;
import lightning.product.k_2610_C;
import lightning.product.q_4293_E;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.ParticleTypes;

public class WitherRoseBlock
extends FlowerBlock {
    public WitherRoseBlock(g_422_i effectIn, q_4293_E.P_1922_E propertiesIn) {
        super(effectIn, 8, propertiesIn);
    }

    @Override
    protected boolean v_4262_N(K_4074_S state, BlockGetter worldIn, c_1514_x pos) {
        return super.v_4262_N(state, worldIn, pos) || state.n_1700_B(a_3742_W.i_3196_G) || state.n_1700_B(a_3742_W.C_415_h) || state.n_1700_B(a_3742_W.v_165_F);
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        s_1395_c voxelshape = this.n_1700_B(stateIn, (BlockGetter)worldIn, pos, CollisionContext.J_1907_R());
        e_2866_D vector3d = voxelshape.n_1700_B().getCenter();
        double d0 = (double)pos.getX() + vector3d.J_1907_R;
        double d1 = (double)pos.getZ() + vector3d.G_564_y;
        for (int i = 0; i < 3; ++i) {
            if (!rand.nextBoolean()) continue;
            worldIn.n_1700_B(ParticleTypes.B_1668_F, d0 + rand.nextDouble() / 5.0, (double)pos.getY() + (0.5 - rand.nextDouble()), d1 + rand.nextDouble() / 5.0, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        r_4811_B livingentity;
        if (!worldIn.Y_259_p && worldIn.x_607_J() != R_2450_T.n_1700_B && entityIn instanceof r_4811_B && !(livingentity = (r_4811_B)entityIn).n_1700_B(P_11_z.M_182_A)) {
            livingentity.n_1700_B(new k_2610_C(MobEffects.Y_601_j, 40));
        }
    }
}


