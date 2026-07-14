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
import lightning.product.References;

public class V703
extends Schema {
    public V703(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        map.remove("EntityHorse");
        p_registerEntities_1_.register(map, "Horse", () -> DSL.optionalFields((String)"ArmorItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "Donkey", () -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "Mule", () -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "ZombieHorse", () -> DSL.optionalFields((String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "SkeletonHorse", () -> DSL.optionalFields((String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        return map;
    }
}


