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

public class V702
extends Schema {
    public V702(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    protected static void n_1700_B(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> V100.n_1700_B(schema));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        V702.n_1700_B(p_registerEntities_1_, map, "ZombieVillager");
        V702.n_1700_B(p_registerEntities_1_, map, "Husk");
        return map;
    }
}


