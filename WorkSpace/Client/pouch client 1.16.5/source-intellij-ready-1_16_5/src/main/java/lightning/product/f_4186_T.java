/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableMap$Builder
 *  com.google.gson.Gson
 *  com.google.gson.JsonElement
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.Map;
import java.util.Set;
import lightning.product.P_2507_S;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;
import lightning.product.f_1402_I;
import lightning.product.g_1866_m;
import lightning.product.g_2336_b;
import lightning.product.k_1471_n;
import lightning.product.o_4810_o;
import lightning.product.p_4985_U;
import lightning.product.Deserializers;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class f_4186_T
extends P_2507_S {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Gson J_1907_R = Deserializers.R_4764_Y().create();
    private Map<g_2336_b, p_4985_U> R_4764_Y = ImmutableMap.of();
    private final k_1471_n G_564_y;

    public f_4186_T(k_1471_n lootPredicateManager) {
        super(J_1907_R, "loot_tables");
        this.G_564_y = lootPredicateManager;
    }

    public p_4985_U n_1700_B(g_2336_b ressources) {
        return this.R_4764_Y.getOrDefault(ressources, p_4985_U.n_1700_B);
    }

    protected void n_1700_B(Map<g_2336_b, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        ImmutableMap.Builder builder = ImmutableMap.builder();
        JsonElement jsonelement = objectIn.remove(o_4810_o.n_1700_B);
        if (jsonelement != null) {
            n_1700_B.warn("Datapack tried to redefine {} loot table, ignoring", (Object)o_4810_o.n_1700_B);
        }
        objectIn.forEach((id, element) -> {
            try {
                p_4985_U loottable = (p_4985_U)J_1907_R.fromJson(element, p_4985_U.class);
                builder.put(id, (Object)loottable);
            }
            catch (Exception exception) {
                n_1700_B.error("Couldn't parse loot table {}", id, (Object)exception);
            }
        });
        builder.put((Object)o_4810_o.n_1700_B, (Object)p_4985_U.n_1700_B);
        ImmutableMap immutablemap = builder.build();
        g_1866_m validationtracker = new g_1866_m(f_1402_I.u_2550_I, this.G_564_y::n_1700_B, arg_0 -> ((ImmutableMap)immutablemap).get(arg_0));
        immutablemap.forEach((id, lootTable) -> f_4186_T.n_1700_B(validationtracker, id, lootTable));
        validationtracker.n_1700_B().forEach((errorLocation, p_215303_1_) -> n_1700_B.warn("Found validation problem in " + errorLocation + ": " + p_215303_1_));
        this.R_4764_Y = immutablemap;
    }

    public static void n_1700_B(g_1866_m validator, g_2336_b id, p_4985_U lootTable) {
        lootTable.n_1700_B(validator.n_1700_B(lootTable.n_1700_B()).n_1700_B("{" + String.valueOf(id) + "}", id));
    }

    public static JsonElement n_1700_B(p_4985_U lootTableIn) {
        return J_1907_R.toJsonTree((Object)lootTableIn);
    }

    public Set<g_2336_b> J_1907_R() {
        return this.R_4764_Y.keySet();
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((Map)object, s_2107_a, x_2951_U);
    }
}


