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
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class V1451_2
extends NamespacedSchema {
    public V1451_2(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema p_registerBlockEntities_1_) {
        Map map = super.registerBlockEntities(p_registerBlockEntities_1_);
        p_registerBlockEntities_1_.register(map, "minecraft:piston", p_206510_1_ -> DSL.optionalFields((String)"blockState", (TypeTemplate)References.P_4830_p.in(p_registerBlockEntities_1_)));
        return map;
    }
}


