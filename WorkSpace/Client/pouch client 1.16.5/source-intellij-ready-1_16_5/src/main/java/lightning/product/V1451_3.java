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

public class V1451_3
extends NamespacedSchema {
    public V1451_3(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        Map map = super.registerEntities(p_registerEntities_1_);
        p_registerEntities_1_.registerSimple(map, "minecraft:egg");
        p_registerEntities_1_.registerSimple(map, "minecraft:ender_pearl");
        p_registerEntities_1_.registerSimple(map, "minecraft:fireball");
        p_registerEntities_1_.register(map, "minecraft:potion", p_206498_1_ -> DSL.optionalFields((String)"Potion", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple(map, "minecraft:small_fireball");
        p_registerEntities_1_.registerSimple(map, "minecraft:snowball");
        p_registerEntities_1_.registerSimple(map, "minecraft:wither_skull");
        p_registerEntities_1_.registerSimple(map, "minecraft:xp_bottle");
        p_registerEntities_1_.register(map, "minecraft:arrow", () -> DSL.optionalFields((String)"inBlockState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "minecraft:enderman", () -> DSL.optionalFields((String)"carriedBlockState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "minecraft:falling_block", () -> DSL.optionalFields((String)"BlockState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (String)"TileEntityData", (TypeTemplate)References.u_2550_I.in(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "minecraft:spectral_arrow", () -> DSL.optionalFields((String)"inBlockState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "minecraft:chest_minecart", () -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_))));
        p_registerEntities_1_.register(map, "minecraft:commandblock_minecart", () -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "minecraft:furnace_minecart", () -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "minecraft:hopper_minecart", () -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_))));
        p_registerEntities_1_.register(map, "minecraft:minecart", () -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "minecraft:spawner_minecart", () -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (TypeTemplate)References.w_1457_N.in(p_registerEntities_1_)));
        p_registerEntities_1_.register(map, "minecraft:tnt_minecart", () -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        return map;
    }
}


