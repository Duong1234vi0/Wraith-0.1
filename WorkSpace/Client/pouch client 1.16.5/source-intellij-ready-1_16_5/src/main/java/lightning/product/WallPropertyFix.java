/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Set;
import lightning.product.References;

public class WallPropertyFix
extends DataFix {
    private static final Set<String> n_1700_B = ImmutableSet.of((Object)"minecraft:andesite_wall", (Object)"minecraft:brick_wall", (Object)"minecraft:cobblestone_wall", (Object)"minecraft:diorite_wall", (Object)"minecraft:end_stone_brick_wall", (Object)"minecraft:granite_wall", (Object[])new String[]{"minecraft:mossy_cobblestone_wall", "minecraft:mossy_stone_brick_wall", "minecraft:nether_brick_wall", "minecraft:prismarine_wall", "minecraft:red_nether_brick_wall", "minecraft:red_sandstone_wall", "minecraft:sandstone_wall", "minecraft:stone_brick_wall"});

    public WallPropertyFix(Schema p_i231468_1_, boolean p_i231468_2_) {
        super(p_i231468_1_, p_i231468_2_);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("WallPropertyFix", this.getInputSchema().getType(References.P_4830_p), p_233416_0_ -> p_233416_0_.update(DSL.remainderFinder(), WallPropertyFix::n_1700_B));
    }

    private static String n_1700_B(String p_233419_0_) {
        return "true".equals(p_233419_0_) ? "low" : "none";
    }

    private static <T> Dynamic<T> n_1700_B(Dynamic<T> p_233418_0_, String p_233418_1_) {
        return p_233418_0_.update(p_233418_1_, p_233421_0_ -> (Dynamic)DataFixUtils.orElse(p_233421_0_.asString().result().map(WallPropertyFix::n_1700_B).map(arg_0 -> ((Dynamic)p_233421_0_).createString(arg_0)), (Object)p_233421_0_));
    }

    private static <T> Dynamic<T> n_1700_B(Dynamic<T> p_233417_0_) {
        boolean flag = p_233417_0_.get("Name").asString().result().filter(n_1700_B::contains).isPresent();
        return !flag ? p_233417_0_ : p_233417_0_.update("Properties", p_233420_0_ -> {
            Dynamic dynamic = WallPropertyFix.n_1700_B(p_233420_0_, "east");
            dynamic = WallPropertyFix.n_1700_B(dynamic, "west");
            dynamic = WallPropertyFix.n_1700_B(dynamic, "north");
            return WallPropertyFix.n_1700_B(dynamic, "south");
        });
    }
}


