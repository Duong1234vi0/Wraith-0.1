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

public class V2519
extends NamespacedSchema {
    public V2519(int p_i231476_1_, Schema p_i231476_2_) {
        super(p_i231476_1_, p_i231476_2_);
    }

    protected static void n_1700_B(Schema p_233469_0_, Map<String, Supplier<TypeTemplate>> p_233469_1_, String p_233469_2_) {
        p_233469_0_.register(p_233469_1_, p_233469_2_, () -> V100.n_1700_B(p_233469_0_));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        V2519.n_1700_B(p_registerEntities_1_, map, "minecraft:strider");
        return map;
    }
}


