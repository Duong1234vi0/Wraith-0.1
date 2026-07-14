/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.templates.TypeTemplate
 */
package lightning.product;

import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.V100;
import lightning.product.NamespacedSchema;

public class V1931
extends NamespacedSchema {
    public V1931(int p_i50411_1_, Schema p_i50411_2_) {
        super(p_i50411_1_, p_i50411_2_);
    }

    protected static void n_1700_B(Schema p_219888_0_, Map<String, Supplier<TypeTemplate>> p_219888_1_, String p_219888_2_) {
        p_219888_0_.register(p_219888_1_, p_219888_2_, () -> V100.n_1700_B(p_219888_0_));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        V1931.n_1700_B(p_registerEntities_1_, map, "minecraft:fox");
        return map;
    }
}


