/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.CraftingContainer;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import lightning.product.q_2896_o;
import lightning.product.Items;

public class A_1530_r
extends CustomRecipe {
    private static final b_3278_X n_1700_B = b_3278_X.n_1700_B(Items.l_3370_o);
    private static final b_3278_X J_1907_R = b_3278_X.n_1700_B(Items.Easing);
    private static final b_3278_X R_4764_Y = b_3278_X.n_1700_B(Items.FenceGateBlock);

    public A_1530_r(g_2336_b idIn) {
        super(idIn);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        boolean flag = false;
        int i = 0;
        for (int j = 0; j < inv.Y_259_p(); ++j) {
            Z_1993_T itemstack = inv.s_956_w(j);
            if (itemstack.n_1700_B()) continue;
            if (n_1700_B.n_1700_B(itemstack)) {
                if (flag) {
                    return false;
                }
                flag = true;
                continue;
            }
            if (!(J_1907_R.n_1700_B(itemstack) ? ++i > 3 : !R_4764_Y.n_1700_B(itemstack))) continue;
            return false;
        }
        return flag && i >= 1;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        Z_1993_T itemstack = new Z_1993_T(Items.FenceBlock, 3);
        U_2912_j compoundnbt = itemstack.n_1700_B("Fireworks");
        q_2896_o listnbt = new q_2896_o();
        int i = 0;
        for (int j = 0; j < inv.Y_259_p(); ++j) {
            U_2912_j compoundnbt1;
            Z_1993_T itemstack1 = inv.s_956_w(j);
            if (itemstack1.n_1700_B()) continue;
            if (J_1907_R.n_1700_B(itemstack1)) {
                ++i;
                continue;
            }
            if (!R_4764_Y.n_1700_B(itemstack1) || (compoundnbt1 = itemstack1.J_1907_R("Explosion")) == null) continue;
            listnbt.add(compoundnbt1);
        }
        compoundnbt.n_1700_B("Flight", (byte)i);
        if (!listnbt.isEmpty()) {
            compoundnbt.n_1700_B("Explosions", listnbt);
        }
        return itemstack;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public Z_1993_T R_4764_Y() {
        return new Z_1993_T(Items.FenceBlock);
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.v_4262_N;
    }
}


