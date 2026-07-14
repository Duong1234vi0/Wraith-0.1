/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Iterator;
import lightning.product.M_996_h;
import lightning.product.Recipe;
import lightning.product.u_530_F;

public interface PlaceRecipe<T> {
    default public void n_1700_B(int width, int height, int outputSlot, Recipe<?> recipe, Iterator<T> ingredients, int maxAmount) {
        int i = width;
        int j = height;
        if (recipe instanceof M_996_h) {
            M_996_h shapedrecipe = (M_996_h)recipe;
            i = shapedrecipe.P_1922_E();
            j = shapedrecipe.s_956_w();
        }
        int k1 = 0;
        block0: for (int k = 0; k < height; ++k) {
            if (k1 == outputSlot) {
                ++k1;
            }
            boolean flag = (float)j < (float)height / 2.0f;
            int l = u_530_F.G_564_y((float)height / 2.0f - (float)j / 2.0f);
            if (flag && l > k) {
                k1 += width;
                ++k;
            }
            for (int i1 = 0; i1 < width; ++i1) {
                boolean flag1;
                if (!ingredients.hasNext()) {
                    return;
                }
                flag = (float)i < (float)width / 2.0f;
                l = u_530_F.G_564_y((float)width / 2.0f - (float)i / 2.0f);
                int j1 = i;
                boolean bl = flag1 = i1 < i;
                if (flag) {
                    j1 = l + i;
                    boolean bl2 = flag1 = l <= i1 && i1 < l + i;
                }
                if (flag1) {
                    this.n_1700_B(ingredients, k1, maxAmount, k, i1);
                } else if (j1 == i1) {
                    k1 += width - i1;
                    continue block0;
                }
                ++k1;
            }
        }
    }

    public void n_1700_B(Iterator<T> var1, int var2, int var3, int var4, int var5);
}


