/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.TypeTemplate
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class V2551
extends NamespacedSchema {
    public V2551(int p_i231478_1_, Schema p_i231478_2_) {
        super(p_i231478_1_, p_i231478_2_);
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        super.registerTypes(p_registerTypes_1_, p_registerTypes_2_, p_registerTypes_3_);
        p_registerTypes_1_.registerType(false, References.q_2307_F, () -> DSL.fields((String)"dimensions", (TypeTemplate)DSL.compoundList((TypeTemplate)DSL.constType(V2551.n_1700_B()), (TypeTemplate)DSL.fields((String)"generator", (TypeTemplate)DSL.taggedChoiceLazy((String)"type", (Type)DSL.string(), (Map)ImmutableMap.of((Object)"minecraft:debug", DSL::remainder, (Object)"minecraft:flat", () -> DSL.optionalFields((String)"settings", (TypeTemplate)DSL.optionalFields((String)"biome", (TypeTemplate)References.k_2293_S.in(p_registerTypes_1_), (String)"layers", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"block", (TypeTemplate)References.t_1786_h.in(p_registerTypes_1_))))), (Object)"minecraft:noise", () -> DSL.optionalFields((String)"biome_source", (TypeTemplate)DSL.taggedChoiceLazy((String)"type", (Type)DSL.string(), (Map)ImmutableMap.of((Object)"minecraft:fixed", () -> DSL.fields((String)"biome", (TypeTemplate)References.k_2293_S.in(p_registerTypes_1_)), (Object)"minecraft:multi_noise", () -> DSL.list((TypeTemplate)DSL.fields((String)"biome", (TypeTemplate)References.k_2293_S.in(p_registerTypes_1_))), (Object)"minecraft:checkerboard", () -> DSL.fields((String)"biomes", (TypeTemplate)DSL.list((TypeTemplate)References.k_2293_S.in(p_registerTypes_1_))), (Object)"minecraft:vanilla_layered", DSL::remainder, (Object)"minecraft:the_end", DSL::remainder)), (String)"settings", (TypeTemplate)DSL.or((TypeTemplate)DSL.constType((Type)DSL.string()), (TypeTemplate)DSL.optionalFields((String)"default_block", (TypeTemplate)References.t_1786_h.in(p_registerTypes_1_), (String)"default_fluid", (TypeTemplate)References.t_1786_h.in(p_registerTypes_1_))))))))));
    }
}


