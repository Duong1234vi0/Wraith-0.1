/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.io.IOException;
import lightning.product.Resource;
import lightning.product.ResourceManager;
import lightning.product.g_2336_b;
import lightning.product.i_2518_W;

public class c_3818_C {
    @Deprecated
    public static int[] n_1700_B(ResourceManager manager, g_2336_b location) throws IOException {
        int[] aint;
        try (Resource iresource = manager.n_1700_B(location);
             i_2518_W nativeimage = i_2518_W.n_1700_B(iresource.J_1907_R());){
            aint = nativeimage.G_564_y();
        }
        return aint;
    }
}


