/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.DispenseItemBehavior;
import lightning.product.K_4074_S;
import lightning.product.S_3458_C;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_2711_h;
import lightning.product.m_3054_I;
import lightning.product.BlockTags;
import lightning.product.DefaultDispenseItemBehavior;
import lightning.product.q_1613_l;
import lightning.product.w_801_N;
import lightning.product.BlockSource;
import lightning.product.y_4319_k;

public class T_418_P
extends q_1613_l {
    private static final DispenseItemBehavior n_1700_B = new DefaultDispenseItemBehavior(){
        private final DefaultDispenseItemBehavior J_1907_R = new DefaultDispenseItemBehavior();

        @Override
        public Z_1993_T n_1700_B(BlockSource source, Z_1993_T stack) {
            double d3;
            w_801_N railshape;
            b_257_Y direction = source.P_1922_E().R_4764_Y(S_3458_C.P_4830_p);
            e_3591_l world = source.v_4262_N();
            double d0 = source.n_1700_B() + (double)direction.t_148_a() * 1.125;
            double d1 = Math.floor(source.J_1907_R()) + (double)direction.s_956_w();
            double d2 = source.R_4764_Y() + (double)direction.u_2550_I() * 1.125;
            c_1514_x blockpos = source.G_564_y().offset(direction);
            K_4074_S blockstate = world.getBlockState(blockpos);
            w_801_N w_801_N2 = railshape = blockstate.J_1907_R() instanceof g_2711_h ? blockstate.R_4764_Y(((g_2711_h)blockstate.J_1907_R()).t_148_a()) : w_801_N.n_1700_B;
            if (blockstate.n_1700_B(BlockTags.n_3318_d)) {
                d3 = railshape.J_1907_R() ? 0.6 : 0.1;
            } else {
                if (!blockstate.v_4262_N() || !world.getBlockState(blockpos.down()).n_1700_B(BlockTags.n_3318_d)) {
                    return this.J_1907_R.dispense(source, stack);
                }
                K_4074_S blockstate1 = world.getBlockState(blockpos.down());
                w_801_N railshape1 = blockstate1.J_1907_R() instanceof g_2711_h ? blockstate1.R_4764_Y(((g_2711_h)blockstate1.J_1907_R()).t_148_a()) : w_801_N.n_1700_B;
                d3 = direction != b_257_Y.n_1700_B && railshape1.J_1907_R() ? -0.4 : -0.9;
            }
            y_4319_k abstractminecartentity = y_4319_k.n_1700_B(world, d0, d1 + d3, d2, ((T_418_P)stack.J_1907_R()).J_1907_R);
            if (stack.Y_601_j()) {
                abstractminecartentity.n_1700_B(stack.multiplayerClientSuggestionProvider());
            }
            world.a_(abstractminecartentity);
            stack.v_4262_N(1);
            return stack;
        }

        @Override
        protected void n_1700_B(BlockSource source) {
            source.v_4262_N().R_4764_Y(1000, source.G_564_y(), 0);
        }
    };
    private final y_4319_k.n_1700_B J_1907_R;

    public T_418_P(y_4319_k.n_1700_B minecartTypeIn, q_1613_l.n_1700_B builder) {
        super(builder);
        this.J_1907_R = minecartTypeIn;
        S_3458_C.n_1700_B(this, n_1700_B);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        c_1514_x blockpos;
        b_4507_u world = context.getWorld();
        K_4074_S blockstate = world.getBlockState(blockpos = context.getPos());
        if (!blockstate.n_1700_B(BlockTags.n_3318_d)) {
            return m_3054_I.G_564_y;
        }
        Z_1993_T itemstack = context.getItem();
        if (!world.Y_259_p) {
            w_801_N railshape = blockstate.J_1907_R() instanceof g_2711_h ? blockstate.R_4764_Y(((g_2711_h)blockstate.J_1907_R()).t_148_a()) : w_801_N.n_1700_B;
            double d0 = 0.0;
            if (railshape.J_1907_R()) {
                d0 = 0.5;
            }
            y_4319_k abstractminecartentity = y_4319_k.n_1700_B(world, (double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.0625 + d0, (double)blockpos.getZ() + 0.5, this.J_1907_R);
            if (itemstack.Y_601_j()) {
                abstractminecartentity.n_1700_B(itemstack.multiplayerClientSuggestionProvider());
            }
            world.a_(abstractminecartentity);
        }
        itemstack.v_4262_N(1);
        return m_3054_I.n_1700_B(world.Y_259_p);
    }
}


