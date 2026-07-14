/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import lightning.product.References;

public class ChunkStructuresTemplateRenameFix
extends DataFix {
    private static final ImmutableMap<String, Pair<String, ImmutableMap<String, String>>> n_1700_B = ImmutableMap.builder().put((Object)"EndCity", (Object)Pair.of((Object)"ECP", (Object)ImmutableMap.builder().put((Object)"second_floor", (Object)"second_floor_1").put((Object)"third_floor", (Object)"third_floor_1").put((Object)"third_floor_c", (Object)"third_floor_2").build())).put((Object)"Mansion", (Object)Pair.of((Object)"WMP", (Object)ImmutableMap.builder().put((Object)"carpet_south", (Object)"carpet_south_1").put((Object)"carpet_west", (Object)"carpet_west_1").put((Object)"indoors_door", (Object)"indoors_door_1").put((Object)"indoors_wall", (Object)"indoors_wall_1").build())).put((Object)"Igloo", (Object)Pair.of((Object)"Iglu", (Object)ImmutableMap.builder().put((Object)"minecraft:igloo/igloo_bottom", (Object)"minecraft:igloo/bottom").put((Object)"minecraft:igloo/igloo_middle", (Object)"minecraft:igloo/middle").put((Object)"minecraft:igloo/igloo_top", (Object)"minecraft:igloo/top").build())).put((Object)"Ocean_Ruin", (Object)Pair.of((Object)"ORP", (Object)ImmutableMap.builder().put((Object)"minecraft:ruin/big_ruin1_brick", (Object)"minecraft:underwater_ruin/big_brick_1").put((Object)"minecraft:ruin/big_ruin2_brick", (Object)"minecraft:underwater_ruin/big_brick_2").put((Object)"minecraft:ruin/big_ruin3_brick", (Object)"minecraft:underwater_ruin/big_brick_3").put((Object)"minecraft:ruin/big_ruin8_brick", (Object)"minecraft:underwater_ruin/big_brick_8").put((Object)"minecraft:ruin/big_ruin1_cracked", (Object)"minecraft:underwater_ruin/big_cracked_1").put((Object)"minecraft:ruin/big_ruin2_cracked", (Object)"minecraft:underwater_ruin/big_cracked_2").put((Object)"minecraft:ruin/big_ruin3_cracked", (Object)"minecraft:underwater_ruin/big_cracked_3").put((Object)"minecraft:ruin/big_ruin8_cracked", (Object)"minecraft:underwater_ruin/big_cracked_8").put((Object)"minecraft:ruin/big_ruin1_mossy", (Object)"minecraft:underwater_ruin/big_mossy_1").put((Object)"minecraft:ruin/big_ruin2_mossy", (Object)"minecraft:underwater_ruin/big_mossy_2").put((Object)"minecraft:ruin/big_ruin3_mossy", (Object)"minecraft:underwater_ruin/big_mossy_3").put((Object)"minecraft:ruin/big_ruin8_mossy", (Object)"minecraft:underwater_ruin/big_mossy_8").put((Object)"minecraft:ruin/big_ruin_warm4", (Object)"minecraft:underwater_ruin/big_warm_4").put((Object)"minecraft:ruin/big_ruin_warm5", (Object)"minecraft:underwater_ruin/big_warm_5").put((Object)"minecraft:ruin/big_ruin_warm6", (Object)"minecraft:underwater_ruin/big_warm_6").put((Object)"minecraft:ruin/big_ruin_warm7", (Object)"minecraft:underwater_ruin/big_warm_7").put((Object)"minecraft:ruin/ruin1_brick", (Object)"minecraft:underwater_ruin/brick_1").put((Object)"minecraft:ruin/ruin2_brick", (Object)"minecraft:underwater_ruin/brick_2").put((Object)"minecraft:ruin/ruin3_brick", (Object)"minecraft:underwater_ruin/brick_3").put((Object)"minecraft:ruin/ruin4_brick", (Object)"minecraft:underwater_ruin/brick_4").put((Object)"minecraft:ruin/ruin5_brick", (Object)"minecraft:underwater_ruin/brick_5").put((Object)"minecraft:ruin/ruin6_brick", (Object)"minecraft:underwater_ruin/brick_6").put((Object)"minecraft:ruin/ruin7_brick", (Object)"minecraft:underwater_ruin/brick_7").put((Object)"minecraft:ruin/ruin8_brick", (Object)"minecraft:underwater_ruin/brick_8").put((Object)"minecraft:ruin/ruin1_cracked", (Object)"minecraft:underwater_ruin/cracked_1").put((Object)"minecraft:ruin/ruin2_cracked", (Object)"minecraft:underwater_ruin/cracked_2").put((Object)"minecraft:ruin/ruin3_cracked", (Object)"minecraft:underwater_ruin/cracked_3").put((Object)"minecraft:ruin/ruin4_cracked", (Object)"minecraft:underwater_ruin/cracked_4").put((Object)"minecraft:ruin/ruin5_cracked", (Object)"minecraft:underwater_ruin/cracked_5").put((Object)"minecraft:ruin/ruin6_cracked", (Object)"minecraft:underwater_ruin/cracked_6").put((Object)"minecraft:ruin/ruin7_cracked", (Object)"minecraft:underwater_ruin/cracked_7").put((Object)"minecraft:ruin/ruin8_cracked", (Object)"minecraft:underwater_ruin/cracked_8").put((Object)"minecraft:ruin/ruin1_mossy", (Object)"minecraft:underwater_ruin/mossy_1").put((Object)"minecraft:ruin/ruin2_mossy", (Object)"minecraft:underwater_ruin/mossy_2").put((Object)"minecraft:ruin/ruin3_mossy", (Object)"minecraft:underwater_ruin/mossy_3").put((Object)"minecraft:ruin/ruin4_mossy", (Object)"minecraft:underwater_ruin/mossy_4").put((Object)"minecraft:ruin/ruin5_mossy", (Object)"minecraft:underwater_ruin/mossy_5").put((Object)"minecraft:ruin/ruin6_mossy", (Object)"minecraft:underwater_ruin/mossy_6").put((Object)"minecraft:ruin/ruin7_mossy", (Object)"minecraft:underwater_ruin/mossy_7").put((Object)"minecraft:ruin/ruin8_mossy", (Object)"minecraft:underwater_ruin/mossy_8").put((Object)"minecraft:ruin/ruin_warm1", (Object)"minecraft:underwater_ruin/warm_1").put((Object)"minecraft:ruin/ruin_warm2", (Object)"minecraft:underwater_ruin/warm_2").put((Object)"minecraft:ruin/ruin_warm3", (Object)"minecraft:underwater_ruin/warm_3").put((Object)"minecraft:ruin/ruin_warm4", (Object)"minecraft:underwater_ruin/warm_4").put((Object)"minecraft:ruin/ruin_warm5", (Object)"minecraft:underwater_ruin/warm_5").put((Object)"minecraft:ruin/ruin_warm6", (Object)"minecraft:underwater_ruin/warm_6").put((Object)"minecraft:ruin/ruin_warm7", (Object)"minecraft:underwater_ruin/warm_7").put((Object)"minecraft:ruin/ruin_warm8", (Object)"minecraft:underwater_ruin/warm_8").put((Object)"minecraft:ruin/big_brick_1", (Object)"minecraft:underwater_ruin/big_brick_1").put((Object)"minecraft:ruin/big_brick_2", (Object)"minecraft:underwater_ruin/big_brick_2").put((Object)"minecraft:ruin/big_brick_3", (Object)"minecraft:underwater_ruin/big_brick_3").put((Object)"minecraft:ruin/big_brick_8", (Object)"minecraft:underwater_ruin/big_brick_8").put((Object)"minecraft:ruin/big_mossy_1", (Object)"minecraft:underwater_ruin/big_mossy_1").put((Object)"minecraft:ruin/big_mossy_2", (Object)"minecraft:underwater_ruin/big_mossy_2").put((Object)"minecraft:ruin/big_mossy_3", (Object)"minecraft:underwater_ruin/big_mossy_3").put((Object)"minecraft:ruin/big_mossy_8", (Object)"minecraft:underwater_ruin/big_mossy_8").put((Object)"minecraft:ruin/big_cracked_1", (Object)"minecraft:underwater_ruin/big_cracked_1").put((Object)"minecraft:ruin/big_cracked_2", (Object)"minecraft:underwater_ruin/big_cracked_2").put((Object)"minecraft:ruin/big_cracked_3", (Object)"minecraft:underwater_ruin/big_cracked_3").put((Object)"minecraft:ruin/big_cracked_8", (Object)"minecraft:underwater_ruin/big_cracked_8").put((Object)"minecraft:ruin/big_warm_4", (Object)"minecraft:underwater_ruin/big_warm_4").put((Object)"minecraft:ruin/big_warm_5", (Object)"minecraft:underwater_ruin/big_warm_5").put((Object)"minecraft:ruin/big_warm_6", (Object)"minecraft:underwater_ruin/big_warm_6").put((Object)"minecraft:ruin/big_warm_7", (Object)"minecraft:underwater_ruin/big_warm_7").build())).build();

    public ChunkStructuresTemplateRenameFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.Y_601_j);
        OpticFinder opticfinder = type.findField("Children");
        return this.fixTypeEverywhereTyped("ChunkStructuresTemplateRenameFix", type, p_207911_2_ -> p_207911_2_.updateTyped(opticfinder, p_207908_2_ -> p_207908_2_.update(DSL.remainderFinder(), p_207909_2_ -> this.n_1700_B((Dynamic<?>)((Dynamic)p_207911_2_.get(DSL.remainderFinder())), (Dynamic<?>)p_207909_2_))));
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_233153_1_, Dynamic<?> p_233153_2_) {
        Pair pair;
        String s = p_233153_1_.get("id").asString("");
        if (n_1700_B.containsKey((Object)s) && ((String)(pair = (Pair)n_1700_B.get((Object)s)).getFirst()).equals(p_233153_2_.get("id").asString(""))) {
            String s1 = p_233153_2_.get("Template").asString("");
            p_233153_2_ = p_233153_2_.set("Template", p_233153_2_.createString((String)((ImmutableMap)pair.getSecond()).getOrDefault((Object)s1, (Object)s1)));
        }
        return p_233153_2_;
    }
}


