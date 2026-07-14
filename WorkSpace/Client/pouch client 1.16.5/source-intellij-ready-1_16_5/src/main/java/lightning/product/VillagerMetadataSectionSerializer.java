/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.K_2872_v;
import lightning.product.T_335_n;
import lightning.product.i_4431_W;

public class VillagerMetadataSectionSerializer
implements T_335_n<K_2872_v> {
    public K_2872_v n_1700_B(JsonObject json) {
        return new K_2872_v(K_2872_v.n_1700_B.n_1700_B(i_4431_W.n_1700_B(json, "hat", "none")));
    }

    @Override
    public String n_1700_B() {
        return "villager";
    }

    @Override
    public /* synthetic */ Object J_1907_R(JsonObject jsonObject) {
        return this.n_1700_B(jsonObject);
    }
}


