/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.GsonBuilder
 */
package lightning.product;

import com.google.gson.GsonBuilder;
import lightning.product.A_2178_U;
import lightning.product.H_3357_D;
import lightning.product.LootItemFunctions;
import lightning.product.J_22_h;
import lightning.product.LootItemCondition;
import lightning.product.S_2110_L;
import lightning.product.n_2967_p;
import lightning.product.o_3393_s;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.LootPoolEntries;
import lightning.product.u_1373_N;
import lightning.product.LootItemConditions;

public class Deserializers {
    public static GsonBuilder n_1700_B() {
        return new GsonBuilder().registerTypeAdapter(o_3393_s.class, (Object)new o_3393_s.n_1700_B()).registerTypeAdapter(J_22_h.class, (Object)new J_22_h.n_1700_B()).registerTypeAdapter(S_2110_L.class, (Object)new S_2110_L.n_1700_B()).registerTypeHierarchyAdapter(LootItemCondition.class, LootItemConditions.n_1700_B()).registerTypeHierarchyAdapter(q_1704_m.J_1907_R.class, (Object)new q_1704_m.J_1907_R.n_1700_B());
    }

    public static GsonBuilder J_1907_R() {
        return Deserializers.n_1700_B().registerTypeAdapter(H_3357_D.class, (Object)new H_3357_D.n_1700_B()).registerTypeHierarchyAdapter(u_1373_N.class, LootPoolEntries.n_1700_B()).registerTypeHierarchyAdapter(A_2178_U.class, LootItemFunctions.n_1700_B());
    }

    public static GsonBuilder R_4764_Y() {
        return Deserializers.J_1907_R().registerTypeAdapter(n_2967_p.class, (Object)new n_2967_p.J_1907_R()).registerTypeAdapter(p_4985_U.class, (Object)new p_4985_U.J_1907_R());
    }
}


