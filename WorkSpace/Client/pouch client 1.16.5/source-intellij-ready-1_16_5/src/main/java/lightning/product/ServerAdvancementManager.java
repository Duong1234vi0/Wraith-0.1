/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.P_2507_S;
import lightning.product.ResourceManager;
import lightning.product.U_1258_d;
import lightning.product.ProfilerFiller;
import lightning.product.AdvancementList;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.k_1471_n;
import lightning.product.DeserializationContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerAdvancementManager
extends P_2507_S {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final Gson J_1907_R = new GsonBuilder().create();
    private AdvancementList R_4764_Y = new AdvancementList();
    private final k_1471_n G_564_y;

    public ServerAdvancementManager(k_1471_n lootPredicateManager) {
        super(J_1907_R, "advancements");
        this.G_564_y = lootPredicateManager;
    }

    protected void n_1700_B(Map<g_2336_b, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        HashMap map = Maps.newHashMap();
        objectIn.forEach((conditions, advancement) -> {
            try {
                JsonObject jsonobject = i_4431_W.w_1484_f(advancement, "advancement");
                A_2629_w.n_1700_B advancement$builder = A_2629_w.n_1700_B.n_1700_B(jsonobject, new DeserializationContext((g_2336_b)conditions, this.G_564_y));
                map.put(conditions, advancement$builder);
            }
            catch (JsonParseException | IllegalArgumentException jsonparseexception) {
                n_1700_B.error("Parsing error loading custom advancement {}: {}", conditions, (Object)jsonparseexception.getMessage());
            }
        });
        AdvancementList advancementlist = new AdvancementList();
        advancementlist.n_1700_B(map);
        for (A_2629_w advancement2 : advancementlist.J_1907_R()) {
            if (advancement2.R_4764_Y() == null) continue;
            U_1258_d.n_1700_B(advancement2);
        }
        this.R_4764_Y = advancementlist;
    }

    @Nullable
    public A_2629_w n_1700_B(g_2336_b id) {
        return this.R_4764_Y.n_1700_B(id);
    }

    public Collection<A_2629_w> n_1700_B() {
        return this.R_4764_Y.R_4764_Y();
    }

    @Override
    protected /* synthetic */ void apply(Object object, ResourceManager s_2107_a, ProfilerFiller x_2951_U) {
        this.n_1700_B((Map<g_2336_b, JsonElement>)((Map)object), s_2107_a, x_2951_U);
    }
}


