/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.GrassColor;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.c_3818_C;
import lightning.product.g_2336_b;
import lightning.product.SimplePreparableReloadListener;

public class GrassColorReloadListener
extends SimplePreparableReloadListener<int[]> {
    private static final g_2336_b n_1700_B = new g_2336_b("textures/colormap/grass.png");

    protected int[] n_1700_B(ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        try {
            return c_3818_C.n_1700_B(resourceManagerIn, n_1700_B);
        }
        catch (IOException ioexception) {
            throw new IllegalStateException("Failed to load grass color texture", ioexception);
        }
    }

    protected void n_1700_B(int[] objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        GrassColor.n_1700_B(objectIn);
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((int[])object, s_2107_a, x_2951_U);
    }

    @Override
    protected /* synthetic */ Object prepare(ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        return this.n_1700_B(s_2107_a, x_2951_U);
    }
}


