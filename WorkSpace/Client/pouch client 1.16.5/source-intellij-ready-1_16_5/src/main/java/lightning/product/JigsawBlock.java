/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.BlockStateProperties;
import lightning.product.BlockGetter;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.O_4606_n;
import lightning.product.BlockPlaceContext;
import lightning.product.T_2915_h;
import lightning.product.W_2163_m;
import lightning.product.Y_1835_y;
import lightning.product.a_2886_t;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_563_h;
import lightning.product.i_2154_H;
import lightning.product.k_2789_z;
import lightning.product.m_3054_I;
import lightning.product.JigsawBlockEntity;
import lightning.product.q_4099_E;
import lightning.product.q_4293_E;
import lightning.product.v_3760_Q;
import lightning.product.x_1688_C;

public class JigsawBlock
extends T_2915_h
implements k_2789_z {
    public static final e_563_h<O_4606_n> P_4830_p = BlockStateProperties.z_4693_k;

    protected JigsawBlock(q_4293_E.P_1922_E properties) {
        super(properties);
        this.u_2550_I((K_4074_S)((K_4074_S)this.x_607_J.J_1907_R()).n_1700_B(P_4830_p, O_4606_n.u_2550_I));
    }

    @Override
    protected void n_1700_B(Y_1835_y.n_1700_B<T_2915_h, K_4074_S> builder) {
        builder.n_1700_B(new v_3760_Q[]{P_4830_p});
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, W_2163_m rot) {
        return (K_4074_S)state.n_1700_B(P_4830_p, rot.n_1700_B().n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(K_4074_S state, q_4099_E mirrorIn) {
        return (K_4074_S)state.n_1700_B(P_4830_p, mirrorIn.n_1700_B().n_1700_B(state.R_4764_Y(P_4830_p)));
    }

    @Override
    public K_4074_S n_1700_B(BlockPlaceContext context) {
        b_257_Y direction = context.getFace();
        b_257_Y direction1 = direction.h_1847_R() == b_257_Y.n_1700_B.J_1907_R ? context.getPlacementHorizontalFacing().u_1723_Y() : b_257_Y.J_1907_R;
        return (K_4074_S)this.multiplayerClientSuggestionProvider().n_1700_B(P_4830_p, O_4606_n.n_1700_B(direction, direction1));
    }

    @Override
    @Nullable
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new JigsawBlockEntity();
    }

    @Override
    public m_3054_I n_1700_B(K_4074_S state, b_4507_u worldIn, c_1514_x pos, a_3913_L player, x_1688_C handIn, BlockHitResult hit) {
        i_2154_H tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof JigsawBlockEntity && player.ModuleManager()) {
            player.n_1700_B((JigsawBlockEntity)tileentity);
            return m_3054_I.n_1700_B(worldIn.Y_259_p);
        }
        return m_3054_I.R_4764_Y;
    }

    public static boolean n_1700_B(a_2886_t.J_1907_R info, a_2886_t.J_1907_R info2) {
        b_257_Y direction = JigsawBlock.w_1484_f(info.J_1907_R);
        b_257_Y direction1 = JigsawBlock.w_1484_f(info2.J_1907_R);
        b_257_Y direction2 = JigsawBlock.t_148_a(info.J_1907_R);
        b_257_Y direction3 = JigsawBlock.t_148_a(info2.J_1907_R);
        JigsawBlockEntity.n_1700_B jigsawtileentity$orientationtype = JigsawBlockEntity.n_1700_B.n_1700_B(info.R_4764_Y.M_588_G("joint")).orElseGet(() -> direction.h_1847_R().G_564_y() ? JigsawBlockEntity.n_1700_B.J_1907_R : JigsawBlockEntity.n_1700_B.n_1700_B);
        boolean flag = jigsawtileentity$orientationtype == JigsawBlockEntity.n_1700_B.n_1700_B;
        return direction == direction1.u_1723_Y() && (flag || direction2 == direction3) && info.R_4764_Y.M_588_G("target").equals(info2.R_4764_Y.M_588_G("name"));
    }

    public static b_257_Y w_1484_f(K_4074_S state) {
        return state.R_4764_Y(P_4830_p).J_1907_R();
    }

    public static b_257_Y t_148_a(K_4074_S state) {
        return state.R_4764_Y(P_4830_p).R_4764_Y();
    }
}



