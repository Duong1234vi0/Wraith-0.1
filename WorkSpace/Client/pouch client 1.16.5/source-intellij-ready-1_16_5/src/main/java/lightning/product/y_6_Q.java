/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import lightning.product.CraftingContainer;
import lightning.product.I_3887_a;
import lightning.product.K_4096_w;
import lightning.product.Container;
import lightning.product.CraftingMenu;
import lightning.product.W_3491_f;
import lightning.product.ResultContainer;
import lightning.product.ResultSlot;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.e_1174_E;
import lightning.product.g_2336_b;
import lightning.product.Recipe;
import lightning.product.r_4432_i;
import lightning.product.RecipeBookMenu;

public class y_6_Q
extends RecipeBookMenu<CraftingContainer> {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/atlas/blocks.png");
    public static final g_2336_b J_1907_R = new g_2336_b("item/empty_armor_slot_helmet");
    public static final g_2336_b R_4764_Y = new g_2336_b("item/empty_armor_slot_chestplate");
    public static final g_2336_b G_564_y = new g_2336_b("item/empty_armor_slot_leggings");
    public static final g_2336_b v_4262_N = new g_2336_b("item/empty_armor_slot_boots");
    public static final g_2336_b w_1484_f = new g_2336_b("item/empty_armor_slot_shield");
    private static final g_2336_b[] s_956_w = new g_2336_b[]{v_4262_N, G_564_y, R_4764_Y, J_1907_R};
    private static final e_1174_E[] u_2550_I = new e_1174_E[]{e_1174_E.u_1723_Y, e_1174_E.P_1922_E, e_1174_E.G_564_y, e_1174_E.R_4764_Y};
    private final CraftingContainer M_588_G = new CraftingContainer(this, 2, 2);
    private final ResultContainer P_4830_p = new ResultContainer();
    public final boolean t_148_a;
    private final a_3913_L h_1847_R;

    public y_6_Q(W_3491_f playerInventory, boolean localWorld, a_3913_L playerIn) {
        super(null, 0);
        this.t_148_a = localWorld;
        this.h_1847_R = playerIn;
        this.J_1907_R(new ResultSlot(playerInventory.P_1922_E, this.M_588_G, this.P_4830_p, 0, 154, 28));
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 2; ++j) {
                this.J_1907_R(new Slot(this.M_588_G, j + i * 2, 98 + j * 18, 18 + i * 18));
            }
        }
        for (int k = 0; k < 4; ++k) {
            final e_1174_E equipmentslottype = u_2550_I[k];
            this.J_1907_R(new Slot(this, playerInventory, 39 - k, 8, 8 + k * 18){

                @Override
                public int G_564_y() {
                    return 1;
                }

                @Override
                public boolean n_1700_B(Z_1993_T stack) {
                    return equipmentslottype == Z_530_i.s_956_w(stack);
                }

                @Override
                public boolean n_1700_B(a_3913_L playerIn) {
                    Z_1993_T itemstack = this.n_1700_B();
                    return !itemstack.n_1700_B() && !playerIn.G_624_v() && K_4096_w.G_564_y(itemstack) ? false : super.n_1700_B(playerIn);
                }

                @Override
                public Pair<g_2336_b, g_2336_b> P_1922_E() {
                    return Pair.of((Object)n_1700_B, (Object)s_956_w[equipmentslottype.J_1907_R()]);
                }
            });
        }
        for (int l = 0; l < 3; ++l) {
            for (int j1 = 0; j1 < 9; ++j1) {
                this.J_1907_R(new Slot(playerInventory, j1 + (l + 1) * 9, 8 + j1 * 18, 84 + l * 18));
            }
        }
        for (int i1 = 0; i1 < 9; ++i1) {
            this.J_1907_R(new Slot(playerInventory, i1, 8 + i1 * 18, 142));
        }
        this.J_1907_R(new Slot(this, playerInventory, 40, 77, 62){

            @Override
            public Pair<g_2336_b, g_2336_b> P_1922_E() {
                return Pair.of((Object)n_1700_B, (Object)w_1484_f);
            }
        });
    }

    @Override
    public void n_1700_B(r_4432_i itemHelperIn) {
        this.M_588_G.n_1700_B(itemHelperIn);
    }

    @Override
    public void n_1700_B() {
        this.P_4830_p.C_2741_M();
        this.M_588_G.C_2741_M();
    }

    @Override
    public boolean n_1700_B(Recipe<? super CraftingContainer> recipeIn) {
        return recipeIn.n_1700_B(this.M_588_G, this.h_1847_R.O_508_d);
    }

    @Override
    public void n_1700_B(Container inventoryIn) {
        CraftingMenu.n_1700_B(this.u_1723_Y, this.h_1847_R.O_508_d, this.h_1847_R, this.M_588_G, this.P_4830_p);
    }

    @Override
    public void J_1907_R(a_3913_L playerIn) {
        super.J_1907_R(playerIn);
        this.P_4830_p.C_2741_M();
        if (!playerIn.O_508_d.Y_259_p) {
            this.n_1700_B(playerIn, playerIn.O_508_d, this.M_588_G);
        }
    }

    @Override
    public boolean n_1700_B(a_3913_L playerIn) {
        return true;
    }

    @Override
    public Z_1993_T n_1700_B(a_3913_L playerIn, int index) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        Slot slot = (Slot)this.P_1922_E.get(index);
        if (slot != null && slot.J_1907_R()) {
            int i;
            Z_1993_T itemstack1 = slot.n_1700_B();
            itemstack = itemstack1.t_148_a();
            e_1174_E equipmentslottype = Z_530_i.s_956_w(itemstack);
            if (index == 0) {
                if (!this.n_1700_B(itemstack1, 9, 45, true)) {
                    return Z_1993_T.J_1907_R;
                }
                slot.n_1700_B(itemstack1, itemstack);
            } else if (index >= 1 && index < 5 ? !this.n_1700_B(itemstack1, 9, 45, false) : (index >= 5 && index < 9 ? !this.n_1700_B(itemstack1, 9, 45, false) : (equipmentslottype.n_1700_B() == e_1174_E.n_1700_B.J_1907_R && !((Slot)this.P_1922_E.get(8 - equipmentslottype.J_1907_R())).J_1907_R() ? !this.n_1700_B(itemstack1, i = 8 - equipmentslottype.J_1907_R(), i + 1, false) : (equipmentslottype == e_1174_E.J_1907_R && !((Slot)this.P_1922_E.get(45)).J_1907_R() ? !this.n_1700_B(itemstack1, 45, 46, false) : (index >= 9 && index < 36 ? !this.n_1700_B(itemstack1, 36, 45, false) : (index >= 36 && index < 45 ? !this.n_1700_B(itemstack1, 9, 36, false) : !this.n_1700_B(itemstack1, 9, 45, false))))))) {
                return Z_1993_T.J_1907_R;
            }
            if (itemstack1.n_1700_B()) {
                slot.J_1907_R(Z_1993_T.J_1907_R);
            } else {
                slot.R_4764_Y();
            }
            if (itemstack1.t_4043_B() == itemstack.t_4043_B()) {
                return Z_1993_T.J_1907_R;
            }
            Z_1993_T itemstack2 = slot.n_1700_B(playerIn, itemstack1);
            if (index == 0) {
                playerIn.n_1700_B(itemstack2, false);
            }
        }
        return itemstack;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack, Slot slotIn) {
        return slotIn.R_4764_Y != this.P_4830_p && super.n_1700_B(stack, slotIn);
    }

    @Override
    public int J_1907_R() {
        return 0;
    }

    @Override
    public int R_4764_Y() {
        return this.M_588_G.G_564_y();
    }

    @Override
    public int G_564_y() {
        return this.M_588_G.R_4764_Y();
    }

    @Override
    public int P_1922_E() {
        return 5;
    }

    public CraftingContainer u_1723_Y() {
        return this.M_588_G;
    }

    @Override
    public I_3887_a t_148_a() {
        return I_3887_a.n_1700_B;
    }
}


