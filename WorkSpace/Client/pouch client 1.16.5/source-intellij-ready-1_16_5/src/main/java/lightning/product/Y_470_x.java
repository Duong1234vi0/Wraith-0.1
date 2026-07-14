/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.F_1573_j;
import lightning.product.ItemUtils;
import lightning.product.L_1875_m;
import lightning.product.NonNullList;
import lightning.product.Potions;
import lightning.product.S_1134_u;
import lightning.product.Stats;
import lightning.product.U_3554_Q;
import lightning.product.V_3137_a;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.g_3316_o;
import lightning.product.k_2610_C;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.y_528_b;

public class Y_470_x
extends q_1613_l {
    public Y_470_x(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public Z_1993_T Y_601_j() {
        return L_1875_m.n_1700_B(super.Y_601_j(), Potions.J_1907_R);
    }

    @Override
    public Z_1993_T n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving) {
        a_3913_L playerentity;
        a_3913_L a_3913_L2 = playerentity = entityLiving instanceof a_3913_L ? (a_3913_L)entityLiving : null;
        if (playerentity instanceof B_4088_l) {
            U_3554_Q.Z_875_P.n_1700_B((B_4088_l)playerentity, stack);
        }
        if (!worldIn.Y_259_p) {
            for (k_2610_C effectinstance : L_1875_m.n_1700_B(stack)) {
                if (effectinstance.n_1700_B().n_1700_B()) {
                    effectinstance.n_1700_B().n_1700_B(playerentity, playerentity, entityLiving, effectinstance.R_4764_Y(), 1.0);
                    continue;
                }
                entityLiving.n_1700_B(new k_2610_C(effectinstance));
            }
        }
        if (playerentity != null) {
            playerentity.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
            if (!playerentity.C_415_h.G_564_y) {
                stack.v_4262_N(1);
            }
        }
        if (playerentity == null || !playerentity.C_415_h.G_564_y) {
            if (stack.n_1700_B()) {
                return new Z_1993_T(Items.Y_3588_g);
            }
            if (playerentity != null) {
                playerentity.l_1268_F.P_1922_E(new Z_1993_T(Items.Y_3588_g));
            }
        }
        return stack;
    }

    @Override
    public int J_1907_R(Z_1993_T stack) {
        return 32;
    }

    @Override
    public F_1573_j R_4764_Y(Z_1993_T stack) {
        return F_1573_j.R_4764_Y;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        return ItemUtils.n_1700_B(worldIn, playerIn, handIn);
    }

    @Override
    public String u_1723_Y(Z_1993_T stack) {
        return L_1875_m.G_564_y(stack).J_1907_R(this.J_1907_R() + ".effect.");
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        L_1875_m.n_1700_B(stack, tooltip, 1.0f);
    }

    @Override
    public boolean P_1922_E(Z_1993_T stack) {
        return super.P_1922_E(stack) || !L_1875_m.n_1700_B(stack).isEmpty();
    }

    @Override
    public void n_1700_B(S_1134_u group, NonNullList<Z_1993_T> items) {
        if (this.n_1700_B(group)) {
            for (y_528_b potion : V_3137_a.B_1668_F) {
                if (potion == Potions.n_1700_B) continue;
                items.add(L_1875_m.n_1700_B(new Z_1993_T(this), potion));
            }
        }
    }
}


