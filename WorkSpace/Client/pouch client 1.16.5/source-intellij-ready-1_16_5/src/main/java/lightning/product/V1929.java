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

public class V1929
extends NamespacedSchema {
    public V1929(int p_i50412_1_, Schema p_i50412_2_) {
        super(p_i50412_1_, p_i50412_2_);
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        p_registerEntities_1_.register(map, "minecraft:wandering_trader", p_219890_1_ -> DSL.optionalFields((String)"Inventory", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"Offers", (TypeTemplate)DSL.optionalFields((String)"Recipes", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"buy", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"buyB", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"sell", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)))), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "minecraft:trader_llama", p_219891_1_ -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"DecorItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        return map;
    }
}


