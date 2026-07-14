/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.Stats;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.t_5_h;
import lightning.product.ItemSteerable;
import lightning.product.x_1688_C;

public class FoodOnAStickItem<T extends N_4263_v>
extends q_1613_l {
    private final t_5_h<T> n_1700_B;
    private final int J_1907_R;

    public FoodOnAStickItem(q_1613_l.n_1700_B properties, t_5_h<T> temptedEntity, int damageAmount) {
        super(properties);
        this.n_1700_B = temptedEntity;
        this.J_1907_R = damageAmount;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        ItemSteerable irideable;
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        if (worldIn.Y_259_p) {
            return InteractionResultHolder.R_4764_Y(itemstack);
        }
        N_4263_v entity = playerIn.l_3609_d();
        if (playerIn.y_2772_m() && entity instanceof ItemSteerable && entity.f_4016_n() == this.n_1700_B && (irideable = (ItemSteerable)((Object)entity)).P_1922_E()) {
            itemstack.n_1700_B(this.J_1907_R, playerIn, (T player) -> player.G_564_y(handIn));
            if (itemstack.n_1700_B()) {
                Z_1993_T itemstack1 = new Z_1993_T(Items.w_2223_C);
                itemstack1.R_4764_Y(itemstack.Q_4569_t());
                return InteractionResultHolder.n_1700_B(itemstack1);
            }
            return InteractionResultHolder.n_1700_B(itemstack);
        }
        playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        return InteractionResultHolder.R_4764_Y(itemstack);
    }
}


