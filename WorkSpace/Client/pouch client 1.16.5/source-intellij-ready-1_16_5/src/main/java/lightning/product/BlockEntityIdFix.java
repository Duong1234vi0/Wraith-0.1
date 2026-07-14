/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.TaggedChoice$TaggedChoiceType
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TaggedChoice;
import java.util.Map;
import lightning.product.References;

public class BlockEntityIdFix
extends DataFix {
    private static final Map<String, String> n_1700_B = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209293_0_ -> {
        p_209293_0_.put("Airportal", "minecraft:end_portal");
        p_209293_0_.put("Banner", "minecraft:banner");
        p_209293_0_.put("Beacon", "minecraft:beacon");
        p_209293_0_.put("Cauldron", "minecraft:brewing_stand");
        p_209293_0_.put("Chest", "minecraft:chest");
        p_209293_0_.put("Comparator", "minecraft:comparator");
        p_209293_0_.put("Control", "minecraft:command_block");
        p_209293_0_.put("DLDetector", "minecraft:daylight_detector");
        p_209293_0_.put("Dropper", "minecraft:dropper");
        p_209293_0_.put("EnchantTable", "minecraft:enchanting_table");
        p_209293_0_.put("EndGateway", "minecraft:end_gateway");
        p_209293_0_.put("EnderChest", "minecraft:ender_chest");
        p_209293_0_.put("FlowerPot", "minecraft:flower_pot");
        p_209293_0_.put("Furnace", "minecraft:furnace");
        p_209293_0_.put("Hopper", "minecraft:hopper");
        p_209293_0_.put("MobSpawner", "minecraft:mob_spawner");
        p_209293_0_.put("Music", "minecraft:noteblock");
        p_209293_0_.put("Piston", "minecraft:piston");
        p_209293_0_.put("RecordPlayer", "minecraft:jukebox");
        p_209293_0_.put("Sign", "minecraft:sign");
        p_209293_0_.put("Skull", "minecraft:skull");
        p_209293_0_.put("Structure", "minecraft:structure_block");
        p_209293_0_.put("Trap", "minecraft:dispenser");
    });

    public BlockEntityIdFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        Type type1 = this.getOutputSchema().getType(References.M_588_G);
        TaggedChoice.TaggedChoiceType taggedchoicetype = this.getInputSchema().findChoiceType(References.u_2550_I);
        TaggedChoice.TaggedChoiceType taggedchoicetype1 = this.getOutputSchema().findChoiceType(References.u_2550_I);
        return TypeRewriteRule.seq((TypeRewriteRule)this.convertUnchecked("item stack block entity name hook converter", type, type1), (TypeRewriteRule)this.fixTypeEverywhere("BlockEntityIdFix", (Type)taggedchoicetype, (Type)taggedchoicetype1, p_209700_0_ -> p_206301_0_ -> p_206301_0_.mapFirst(p_206302_0_ -> n_1700_B.getOrDefault(p_206302_0_, (String)p_206302_0_))));
    }
}


