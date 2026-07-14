/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockGetter;
import lightning.product.DispenseItemBehavior;
import lightning.product.Container;
import lightning.product.S_3458_C;
import lightning.product.BlockSourceImpl;
import lightning.product.Z_1993_T;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.i_2154_H;
import lightning.product.l_3848_Y;
import lightning.product.DropperBlockEntity;
import lightning.product.DefaultDispenseItemBehavior;
import lightning.product.q_4293_E;
import lightning.product.w_748_f;

public class DropperBlock
extends S_3458_C {
    private static final DispenseItemBehavior Q_4569_t = new DefaultDispenseItemBehavior();

    public DropperBlock(q_4293_E.P_1922_E properties) {
        super(properties);
    }

    @Override
    protected DispenseItemBehavior n_1700_B(Z_1993_T stack) {
        return Q_4569_t;
    }

    @Override
    public i_2154_H n_1700_B(BlockGetter worldIn) {
        return new DropperBlockEntity();
    }

    @Override
    protected void n_1700_B(e_3591_l worldIn, c_1514_x pos) {
        BlockSourceImpl proxyblocksource = new BlockSourceImpl(worldIn, pos);
        l_3848_Y dispensertileentity = (l_3848_Y)proxyblocksource.u_1723_Y();
        int i = dispensertileentity.v_4262_N();
        if (i < 0) {
            worldIn.R_4764_Y(1001, pos, 0);
        } else {
            Z_1993_T itemstack = dispensertileentity.s_956_w(i);
            if (!itemstack.n_1700_B()) {
                Z_1993_T itemstack1;
                b_257_Y direction = worldIn.getBlockState(pos).R_4764_Y(P_4830_p);
                Container iinventory = w_748_f.n_1700_B(worldIn, pos.offset(direction));
                if (iinventory == null) {
                    itemstack1 = Q_4569_t.dispense(proxyblocksource, itemstack);
                } else {
                    itemstack1 = w_748_f.n_1700_B((Container)dispensertileentity, iinventory, itemstack.t_148_a().n_1700_B(1), direction.u_1723_Y());
                    if (itemstack1.n_1700_B()) {
                        itemstack1 = itemstack.t_148_a();
                        itemstack1.v_4262_N(1);
                    } else {
                        itemstack1 = itemstack.t_148_a();
                    }
                }
                dispensertileentity.J_1907_R(i, itemstack1);
            }
        }
    }
}


