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

public class V1906
extends NamespacedSchema {
    public V1906(int p_i50416_1_, Schema p_i50416_2_) {
        super(p_i50416_1_, p_i50416_2_);
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema p_registerBlockEntities_1_) {
        Map map = super.registerBlockEntities(p_registerBlockEntities_1_);
        V1906.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:barrel");
        V1906.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:smoker");
        V1906.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:blast_furnace");
        p_registerBlockEntities_1_.register(map, "minecraft:lectern", p_219882_1_ -> DSL.optionalFields((String)"Book", (TypeTemplate)References.M_588_G.in(p_registerBlockEntities_1_)));
        p_registerBlockEntities_1_.registerSimple(map, "minecraft:bell");
        return map;
    }

    protected static void n_1700_B(Schema p_219880_0_, Map<String, Supplier<TypeTemplate>> p_219880_1_, String p_219880_2_) {
        p_219880_0_.register(p_219880_1_, p_219880_2_, () -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_219880_0_))));
    }
}


