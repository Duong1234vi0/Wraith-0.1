/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lightning.product.I_2176_d;
import lightning.product.LootItemCondition;
import lightning.product.g_1866_m;
import lightning.product.g_2336_b;
import lightning.product.k_1471_n;
import lightning.product.Deserializers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeserializationContext {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final g_2336_b J_1907_R;
    private final k_1471_n R_4764_Y;
    private final Gson G_564_y = Deserializers.n_1700_B().create();

    public DeserializationContext(g_2336_b p_i231549_1_, k_1471_n p_i231549_2_) {
        this.J_1907_R = p_i231549_1_;
        this.R_4764_Y = p_i231549_2_;
    }

    public final LootItemCondition[] n_1700_B(JsonArray p_234050_1_, String p_234050_2_, I_2176_d p_234050_3_) {
        LootItemCondition[] ailootcondition = (LootItemCondition[])this.G_564_y.fromJson((JsonElement)p_234050_1_, LootItemCondition[].class);
        g_1866_m validationtracker = new g_1866_m(p_234050_3_, this.R_4764_Y::n_1700_B, p_234052_0_ -> null);
        for (LootItemCondition ilootcondition : ailootcondition) {
            ilootcondition.n_1700_B(validationtracker);
            validationtracker.n_1700_B().forEach((p_234051_1_, p_234051_2_) -> n_1700_B.warn("Found validation problem in advancement trigger {}/{}: {}", (Object)p_234050_2_, p_234051_1_, p_234051_2_));
        }
        return ailootcondition;
    }

    public g_2336_b n_1700_B() {
        return this.J_1907_R;
    }
}


