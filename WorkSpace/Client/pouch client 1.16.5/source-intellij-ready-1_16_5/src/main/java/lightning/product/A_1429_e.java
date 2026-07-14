/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Map;
import lightning.product.CraftingContainer;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.DyeItem;
import lightning.product.b_3278_X;
import lightning.product.b_4507_u;
import lightning.product.CustomRecipe;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.RecipeSerializer;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.FireworkRocketItem;

public class A_1429_e
extends CustomRecipe {
    private static final b_3278_X n_1700_B = b_3278_X.n_1700_B(Items.CraftingTableBlock, Items.H_274_C, Items.u_3578_p, Items.DragonEggBlock, Items.DropperBlock, Items.EndPortalBlock, Items.C_3560_B, Items.EndPortalFrameBlock, Items.EndGatewayBlock);
    private static final b_3278_X J_1907_R = b_3278_X.n_1700_B(Items.k_2273_q);
    private static final b_3278_X R_4764_Y = b_3278_X.n_1700_B(Items.AdvancementList);
    private static final Map<q_1613_l, FireworkRocketItem.n_1700_B> G_564_y = j_3341_s.n_1700_B(Maps.newHashMap(), itemShapeMap -> {
        itemShapeMap.put(Items.CraftingTableBlock, FireworkRocketItem.n_1700_B.J_1907_R);
        itemShapeMap.put(Items.H_274_C, FireworkRocketItem.n_1700_B.P_1922_E);
        itemShapeMap.put(Items.u_3578_p, FireworkRocketItem.n_1700_B.R_4764_Y);
        itemShapeMap.put(Items.DragonEggBlock, FireworkRocketItem.n_1700_B.G_564_y);
        itemShapeMap.put(Items.DropperBlock, FireworkRocketItem.n_1700_B.G_564_y);
        itemShapeMap.put(Items.EndPortalBlock, FireworkRocketItem.n_1700_B.G_564_y);
        itemShapeMap.put(Items.C_3560_B, FireworkRocketItem.n_1700_B.G_564_y);
        itemShapeMap.put(Items.EndPortalFrameBlock, FireworkRocketItem.n_1700_B.G_564_y);
        itemShapeMap.put(Items.EndGatewayBlock, FireworkRocketItem.n_1700_B.G_564_y);
    });
    private static final b_3278_X P_1922_E = b_3278_X.n_1700_B(Items.Easing);

    public A_1429_e(g_2336_b id) {
        super(id);
    }

    @Override
    public boolean n_1700_B(CraftingContainer inv, b_4507_u worldIn) {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack = inv.s_956_w(i);
            if (itemstack.n_1700_B()) continue;
            if (n_1700_B.n_1700_B(itemstack)) {
                if (flag2) {
                    return false;
                }
                flag2 = true;
                continue;
            }
            if (R_4764_Y.n_1700_B(itemstack)) {
                if (flag4) {
                    return false;
                }
                flag4 = true;
                continue;
            }
            if (J_1907_R.n_1700_B(itemstack)) {
                if (flag3) {
                    return false;
                }
                flag3 = true;
                continue;
            }
            if (P_1922_E.n_1700_B(itemstack)) {
                if (flag) {
                    return false;
                }
                flag = true;
                continue;
            }
            if (!(itemstack.J_1907_R() instanceof DyeItem)) {
                return false;
            }
            flag1 = true;
        }
        return flag && flag1;
    }

    @Override
    public Z_1993_T n_1700_B(CraftingContainer inv) {
        Z_1993_T itemstack = new Z_1993_T(Items.FenceGateBlock);
        U_2912_j compoundnbt = itemstack.n_1700_B("Explosion");
        FireworkRocketItem.n_1700_B fireworkrocketitem$shape = FireworkRocketItem.n_1700_B.n_1700_B;
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i < inv.Y_259_p(); ++i) {
            Z_1993_T itemstack1 = inv.s_956_w(i);
            if (itemstack1.n_1700_B()) continue;
            if (n_1700_B.n_1700_B(itemstack1)) {
                fireworkrocketitem$shape = G_564_y.get(itemstack1.J_1907_R());
                continue;
            }
            if (R_4764_Y.n_1700_B(itemstack1)) {
                compoundnbt.n_1700_B("Flicker", true);
                continue;
            }
            if (J_1907_R.n_1700_B(itemstack1)) {
                compoundnbt.n_1700_B("Trail", true);
                continue;
            }
            if (!(itemstack1.J_1907_R() instanceof DyeItem)) continue;
            list.add(((DyeItem)itemstack1.J_1907_R()).R_4764_Y().u_1723_Y());
        }
        compoundnbt.n_1700_B("Colors", list);
        compoundnbt.n_1700_B("Type", (byte)fireworkrocketitem$shape.n_1700_B());
        return itemstack;
    }

    @Override
    public boolean n_1700_B(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public Z_1993_T R_4764_Y() {
        return new Z_1993_T(Items.FenceGateBlock);
    }

    @Override
    public RecipeSerializer<?> E_() {
        return RecipeSerializer.w_1484_f;
    }
}


