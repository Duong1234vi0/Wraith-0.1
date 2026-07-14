/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.templates.TypeTemplate
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.V100;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class V1470
extends NamespacedSchema {
    public V1470(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    protected static void n_1700_B(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> V100.n_1700_B(schema));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        V1470.n_1700_B(p_registerEntities_1_, map, "minecraft:turtle");
        V1470.n_1700_B(p_registerEntities_1_, map, "minecraft:cod_mob");
        V1470.n_1700_B(p_registerEntities_1_, map, "minecraft:tropical_fish");
        V1470.n_1700_B(p_registerEntities_1_, map, "minecraft:salmon_mob");
        V1470.n_1700_B(p_registerEntities_1_, map, "minecraft:puffer_fish");
        V1470.n_1700_B(p_registerEntities_1_, map, "minecraft:phantom");
        V1470.n_1700_B(p_registerEntities_1_, map, "minecraft:dolphin");
        V1470.n_1700_B(p_registerEntities_1_, map, "minecraft:drowned");
        p_registerEntities_1_.register(map, "minecraft:trident", p_206561_1_ -> DSL.optionalFields((String)"inBlockState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        return map;
    }
}


