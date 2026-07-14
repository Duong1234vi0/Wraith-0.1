/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 */
package lightning.product;

import com.google.gson.Gson;
import lightning.product.c_1314_D;

public class GuardedSerializer {
    private final Gson n_1700_B = new Gson();

    public String n_1700_B(c_1314_D p_237694_1_) {
        return this.n_1700_B.toJson((Object)p_237694_1_);
    }

    public <T extends c_1314_D> T n_1700_B(String p_237695_1_, Class<T> p_237695_2_) {
        return (T)((c_1314_D)this.n_1700_B.fromJson(p_237695_1_, p_237695_2_));
    }
}


