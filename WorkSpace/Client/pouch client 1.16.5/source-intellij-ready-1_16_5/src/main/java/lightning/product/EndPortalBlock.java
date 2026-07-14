/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.BlockGetter;
import lightning.product.BaseEntityBlock;
import lightning.product.K_4074_S;
import lightning.product.TheEndPortalBlockEntity;
import lightning.product.N_4263_v;
import lightning.product.T_2915_h;
import lightning.product.CollisionContext;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.i_2154_H;
import lightning.product.q_4293_E;
import lightning.product.s_1395_c;
import lightning.product.Fluid;
import lightning.product.ParticleTypes;
import lightning.product.x_268_Y;
import lightning.product.BooleanOp;

public class EndPortalBlock
extends BaseEntityBlock {
    protected static final s_1395_c P_4830_p = T_2915_h.n_1700_B(0.0, 0.0, 0.0, 16.0, 12.0, 16.0);

    protected EndPortalBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new TheEndPortalBlockEntity();
    }

    @Override
    public s_1395_c n_1700_B(K_4074_S state, BlockGetter worldIn, c_1514_x pos, CollisionContext context) {
        return P_4830_p;
    }

    @Override
    public void n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, N_4263_v entityIn) {
        if (worldIn instanceof e_3591_l && !entityIn.y_2772_m() && !entityIn.H_1883_T() && entityIn.L_103_L() && x_268_Y.R_4764_Y(x_268_Y.n_1700_B(entityIn.i_601_W().offset(-pos.getX(), -pos.getY(), -pos.getZ())), state.s_956_w(worldIn, pos), BooleanOp.t_148_a)) {
            f_2392_k<b_4507_u> registrykey = worldIn.g_2268_R() == b_4507_u.w_1484_f ? b_4507_u.u_1723_Y : b_4507_u.w_1484_f;
            e_3591_l serverworld = ((e_3591_l)worldIn).T_2506_i().n_1700_B(registrykey);
            if (serverworld == null) {
                return;
            }
            entityIn.n_1700_B(serverworld);
        }
    }

    @Override
    public void n_1700_B(K_4074_S stateIn, b_4507_u worldIn, c_1514_x pos, Random rand) {
        double d0 = (double)pos.getX() + rand.nextDouble();
        double d1 = (double)pos.getY() + 0.8;
        double d2 = (double)pos.getZ() + rand.nextDouble();
        worldIn.n_1700_B(ParticleTypes.B_1668_F, d0, d1, d2, 0.0, 0.0, 0.0);
    }

    @Override
    public Z_1993_T n_1700_B(BlockGetter worldIn, c_1514_x pos, K_4074_S state) {
        return Z_1993_T.J_1907_R;
    }

    @Override
    public boolean n_1700_B(K_4074_S state, Fluid fluid) {
        return false;
    }
}


