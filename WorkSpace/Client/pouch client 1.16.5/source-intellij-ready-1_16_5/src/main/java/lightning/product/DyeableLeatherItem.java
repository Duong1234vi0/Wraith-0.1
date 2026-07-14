/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.DyeItem;
import lightning.product.q_1613_l;

public interface DyeableLeatherItem {
    default public boolean c_(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.J_1907_R("display");
        return compoundnbt != null && compoundnbt.R_4764_Y("color", 99);
    }

    default public int d_(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.J_1907_R("display");
        return compoundnbt != null && compoundnbt.R_4764_Y("color", 99) ? compoundnbt.w_1484_f("color") : 10511680;
    }

    default public void e_(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.J_1907_R("display");
        if (compoundnbt != null && compoundnbt.P_1922_E("color")) {
            compoundnbt.multiplayerClientSuggestionProvider("color");
        }
    }

    default public void n_1700_B(Z_1993_T stack, int color) {
        stack.n_1700_B("display").J_1907_R("color", color);
    }

    public static Z_1993_T n_1700_B(Z_1993_T stack, List<DyeItem> dyes) {
        Z_1993_T itemstack = Z_1993_T.J_1907_R;
        int[] aint = new int[3];
        int i = 0;
        int j = 0;
        DyeableLeatherItem idyeablearmoritem = null;
        q_1613_l item = stack.J_1907_R();
        if (item instanceof DyeableLeatherItem) {
            idyeablearmoritem = (DyeableLeatherItem)((Object)item);
            itemstack = stack.t_148_a();
            itemstack.P_1922_E(1);
            if (idyeablearmoritem.c_(stack)) {
                int k = idyeablearmoritem.d_(itemstack);
                float f = (float)(k >> 16 & 0xFF) / 255.0f;
                float f1 = (float)(k >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(k & 0xFF) / 255.0f;
                i = (int)((float)i + Math.max(f, Math.max(f1, f2)) * 255.0f);
                aint[0] = (int)((float)aint[0] + f * 255.0f);
                aint[1] = (int)((float)aint[1] + f1 * 255.0f);
                aint[2] = (int)((float)aint[2] + f2 * 255.0f);
                ++j;
            }
            for (DyeItem dyeitem : dyes) {
                float[] afloat = dyeitem.R_4764_Y().G_564_y();
                int i2 = (int)(afloat[0] * 255.0f);
                int l = (int)(afloat[1] * 255.0f);
                int i1 = (int)(afloat[2] * 255.0f);
                i += Math.max(i2, Math.max(l, i1));
                aint[0] = aint[0] + i2;
                aint[1] = aint[1] + l;
                aint[2] = aint[2] + i1;
                ++j;
            }
        }
        if (idyeablearmoritem == null) {
            return Z_1993_T.J_1907_R;
        }
        int j1 = aint[0] / j;
        int k1 = aint[1] / j;
        int l1 = aint[2] / j;
        float f3 = (float)i / (float)j;
        float f4 = Math.max(j1, Math.max(k1, l1));
        j1 = (int)((float)j1 * f3 / f4);
        k1 = (int)((float)k1 * f3 / f4);
        l1 = (int)((float)l1 * f3 / f4);
        int j2 = (j1 << 8) + k1;
        j2 = (j2 << 8) + l1;
        idyeablearmoritem.n_1700_B(itemstack, j2);
        return itemstack;
    }
}


