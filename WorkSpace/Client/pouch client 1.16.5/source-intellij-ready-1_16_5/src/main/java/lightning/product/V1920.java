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

public class V1920
extends NamespacedSchema {
    public V1920(int p_i50414_1_, Schema p_i50414_2_) {
        super(p_i50414_1_, p_i50414_2_);
    }

    protected static void n_1700_B(Schema p_219886_0_, Map<String, Supplier<TypeTemplate>> p_219886_1_, String p_219886_2_) {
        p_219886_0_.register(p_219886_1_, p_219886_2_, () -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_219886_0_))));
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema p_registerBlockEntities_1_) {
        Map map = super.registerBlockEntities(p_registerBlockEntities_1_);
        V1920.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:campfire");
        return map;
    }
}


