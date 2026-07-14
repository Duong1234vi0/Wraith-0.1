/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Painting;
import lightning.product.P_2973_E;
import lightning.product.U_2912_j;
import lightning.product.UseOnContext;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.t_5_h;
import lightning.product.y_740_d;

public class M_44_d
extends q_1613_l {
    private final t_5_h<? extends P_2973_E> n_1700_B;

    public M_44_d(t_5_h<? extends P_2973_E> entityTypeIn, q_1613_l.n_1700_B properties) {
        super(properties);
        this.n_1700_B = entityTypeIn;
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        P_2973_E hangingentity;
        c_1514_x blockpos = context.getPos();
        b_257_Y direction = context.getFace();
        c_1514_x blockpos1 = blockpos.offset(direction);
        a_3913_L playerentity = context.getPlayer();
        Z_1993_T itemstack = context.getItem();
        if (playerentity != null && !this.n_1700_B(playerentity, direction, itemstack, blockpos1)) {
            return m_3054_I.G_564_y;
        }
        b_4507_u world = context.getWorld();
        if (this.n_1700_B == t_5_h.l_1233_K) {
            hangingentity = new Painting(world, blockpos1, direction);
        } else {
            if (this.n_1700_B != t_5_h.G_624_v) {
                return m_3054_I.n_1700_B(world.Y_259_p);
            }
            hangingentity = new y_740_d(world, blockpos1, direction);
        }
        U_2912_j compoundnbt = itemstack.Q_4569_t();
        if (compoundnbt != null) {
            t_5_h.n_1700_B(world, playerentity, hangingentity, compoundnbt);
        }
        if (hangingentity.u_1723_Y()) {
            if (!world.Y_259_p) {
                hangingentity.t_148_a();
                world.a_(hangingentity);
            }
            itemstack.v_4262_N(1);
            return m_3054_I.n_1700_B(world.Y_259_p);
        }
        return m_3054_I.J_1907_R;
    }

    protected boolean n_1700_B(a_3913_L playerIn, b_257_Y directionIn, Z_1993_T itemStackIn, c_1514_x posIn) {
        return !directionIn.h_1847_R().R_4764_Y() && playerIn.n_1700_B(posIn, directionIn, itemStackIn);
    }
}


