/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_3746_J;
import lightning.product.D_38_f;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.n_1494_c;
import lightning.product.q_4293_E;
import lightning.product.Items;
import lightning.product.StemGrownBlock;
import lightning.product.AttachedStemBlock;
import lightning.product.x_1688_C;
import lightning.product.z_127_w;

public class PumpkinBlock
extends StemGrownBlock {
    protected PumpkinBlock(q_4293_E.P_1922_E builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        Z_1993_T itemstack = player.R_4764_Y(handIn);
        if (itemstack.J_1907_R() == Items.LightPredicate) {
            if (!worldIn.Y_259_p) {
                b_257_Y direction = hit.J_1907_R();
                b_257_Y direction1 = direction.h_1847_R() == b_257_Y.n_1700_B.J_1907_R ? player.o_2767_H().u_1723_Y() : direction;
                worldIn.n_1700_B((a_3913_L)null, pos, SoundEvents.v_2826_q, D_38_f.P_1922_E, 1.0f, 1.0f);
                worldIn.n_1700_B(pos, (K_4074_S)a_3742_W.X_2048_Y.multiplayerClientSuggestionProvider().n_1700_B(z_127_w.P_4830_p, direction1), 11);
                n_1494_c itementity = new n_1494_c(worldIn, (double)pos.getX() + 0.5 + (double)direction1.t_148_a() * 0.65, (double)pos.getY() + 0.1, (double)pos.getZ() + 0.5 + (double)direction1.u_2550_I() * 0.65, new Z_1993_T(Items.WrappedMinMaxBounds, 4));
                itementity.h_1847_R(0.05 * (double)direction1.t_148_a() + worldIn.w_1457_N.nextDouble() * 0.02, 0.05, 0.05 * (double)direction1.u_2550_I() + worldIn.w_1457_N.nextDouble() * 0.02);
                worldIn.a_(itementity);
                itemstack.n_1700_B(1, player, (T playerIn) -> playerIn.G_564_y(handIn));
            }
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        return super.n_1700_B(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public D_3746_J J_1907_R() {
        return (D_3746_J)a_3742_W.L_1733_J;
    }

    @Override
    public AttachedStemBlock t_148_a() {
        return (AttachedStemBlock)a_3742_W.i_789_Q;
    }
}


