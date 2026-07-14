/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.V_2511_L;
import lightning.product.c_4037_x;
import lightning.product.l_3747_P;
import lightning.product.o_2840_r;
import lightning.product.o_3730_L;
import net.optifine.util.GuiRect;

public class GuiUtils {
    public static int getWidth(V_2511_L widget) {
        return o_3730_L.n_1700_B(widget);
    }

    public static int getHeight(V_2511_L widget) {
        return o_3730_L.J_1907_R(widget);
    }

    public static void fill(D_1098_v matrixIn, GuiRect[] rects, int color) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
        c_4037_x.Y_601_j();
        c_4037_x.e_4240_b();
        c_4037_x.s_2632_s();
        bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
        for (int i = 0; i < rects.length; ++i) {
            GuiRect guirect = rects[i];
            if (guirect == null) continue;
            int j = guirect.getLeft();
            int k = guirect.getTop();
            int l = guirect.getRight();
            int i1 = guirect.getBottom();
            if (j < l) {
                int j1 = j;
                j = l;
                l = j1;
            }
            if (k < i1) {
                int k1 = k;
                k = i1;
                i1 = k1;
            }
            bufferbuilder.n_1700_B(matrixIn, (float)j, (float)i1, 0.0f).n_1700_B(f1, f2, f3, f).endVertex();
            bufferbuilder.n_1700_B(matrixIn, (float)l, (float)i1, 0.0f).n_1700_B(f1, f2, f3, f).endVertex();
            bufferbuilder.n_1700_B(matrixIn, (float)l, (float)k, 0.0f).n_1700_B(f1, f2, f3, f).endVertex();
            bufferbuilder.n_1700_B(matrixIn, (float)j, (float)k, 0.0f).n_1700_B(f1, f2, f3, f).endVertex();
        }
        bufferbuilder.u_1723_Y();
        o_2840_r.n_1700_B(bufferbuilder);
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
    }
}

