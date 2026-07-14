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

public class V1800
extends NamespacedSchema {
    public V1800(int p_i50419_1_, Schema p_i50419_2_) {
        super(p_i50419_1_, p_i50419_2_);
    }

    protected static void n_1700_B(Schema p_219873_0_, Map<String, Supplier<TypeTemplate>> p_219873_1_, String p_219873_2_) {
        p_219873_0_.register(p_219873_1_, p_219873_2_, () -> V100.n_1700_B(p_219873_0_));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        V1800.n_1700_B(p_registerEntities_1_, map, "minecraft:panda");
        p_registerEntities_1_.register(map, "minecraft:pillager", p_219875_1_ -> DSL.optionalFields((String)"Inventory", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        return map;
    }
}


