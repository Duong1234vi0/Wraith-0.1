/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.TypeTemplate
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class V2501
extends NamespacedSchema {
    public V2501(int p_i231472_1_, Schema p_i231472_2_) {
        super(p_i231472_1_, p_i231472_2_);
    }

    private static void n_1700_B(Schema p_233461_0_, Map<String, Supplier<TypeTemplate>> p_233461_1_, String p_233461_2_) {
        p_233461_0_.register(p_233461_1_, p_233461_2_, () -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_233461_0_)), (String)"RecipesUsed", (TypeTemplate)DSL.compoundList((TypeTemplate)References.C_2741_M.in(p_233461_0_), (TypeTemplate)DSL.constType((Type)DSL.intType()))));
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema p_registerBlockEntities_1_) {
        Map map = super.registerBlockEntities(p_registerBlockEntities_1_);
        V2501.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:furnace");
        V2501.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:smoker");
        V2501.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:blast_furnace");
        return map;
    }
}


