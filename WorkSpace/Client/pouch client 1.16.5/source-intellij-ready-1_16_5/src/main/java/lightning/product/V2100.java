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

public class V2100
extends NamespacedSchema {
    public V2100(int p_i225705_1_, Schema p_i225705_2_) {
        super(p_i225705_1_, p_i225705_2_);
    }

    protected static void n_1700_B(Schema p_226217_0_, Map<String, Supplier<TypeTemplate>> p_226217_1_, String p_226217_2_) {
        p_226217_0_.register(p_226217_1_, p_226217_2_, () -> V100.n_1700_B(p_226217_0_));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        V2100.n_1700_B(p_registerEntities_1_, map, "minecraft:bee");
        V2100.n_1700_B(p_registerEntities_1_, map, "minecraft:bee_stinger");
        return map;
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema p_registerBlockEntities_1_) {
        Map map = super.registerBlockEntities(p_registerBlockEntities_1_);
        p_registerBlockEntities_1_.register(map, "minecraft:beehive", () -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerBlockEntities_1_)), (String)"Bees", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"EntityData", (TypeTemplate)References.Q_4569_t.in(p_registerBlockEntities_1_)))));
        return map;
    }
}


