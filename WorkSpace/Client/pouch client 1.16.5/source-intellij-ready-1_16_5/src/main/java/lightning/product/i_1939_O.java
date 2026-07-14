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

public class i_1939_O
extends DataFix {
    private static final Map<String, String> n_1700_B = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209312_0_ -> {
        p_209312_0_.put("AreaEffectCloud", "minecraft:area_effect_cloud");
        p_209312_0_.put("ArmorStand", "minecraft:armor_stand");
        p_209312_0_.put("Arrow", "minecraft:arrow");
        p_209312_0_.put("Bat", "minecraft:bat");
        p_209312_0_.put("Blaze", "minecraft:blaze");
        p_209312_0_.put("Boat", "minecraft:boat");
        p_209312_0_.put("CaveSpider", "minecraft:cave_spider");
        p_209312_0_.put("Chicken", "minecraft:chicken");
        p_209312_0_.put("Cow", "minecraft:cow");
        p_209312_0_.put("Creeper", "minecraft:creeper");
        p_209312_0_.put("Donkey", "minecraft:donkey");
        p_209312_0_.put("DragonFireball", "minecraft:dragon_fireball");
        p_209312_0_.put("ElderGuardian", "minecraft:elder_guardian");
        p_209312_0_.put("EnderCrystal", "minecraft:ender_crystal");
        p_209312_0_.put("EnderDragon", "minecraft:ender_dragon");
        p_209312_0_.put("Enderman", "minecraft:enderman");
        p_209312_0_.put("Endermite", "minecraft:endermite");
        p_209312_0_.put("EyeOfEnderSignal", "minecraft:eye_of_ender_signal");
        p_209312_0_.put("FallingSand", "minecraft:falling_block");
        p_209312_0_.put("Fireball", "minecraft:fireball");
        p_209312_0_.put("FireworksRocketEntity", "minecraft:fireworks_rocket");
        p_209312_0_.put("Ghast", "minecraft:ghast");
        p_209312_0_.put("Giant", "minecraft:giant");
        p_209312_0_.put("Guardian", "minecraft:guardian");
        p_209312_0_.put("Horse", "minecraft:horse");
        p_209312_0_.put("Husk", "minecraft:husk");
        p_209312_0_.put("Item", "minecraft:item");
        p_209312_0_.put("ItemFrame", "minecraft:item_frame");
        p_209312_0_.put("LavaSlime", "minecraft:magma_cube");
        p_209312_0_.put("LeashKnot", "minecraft:leash_knot");
        p_209312_0_.put("MinecartChest", "minecraft:chest_minecart");
        p_209312_0_.put("MinecartCommandBlock", "minecraft:commandblock_minecart");
        p_209312_0_.put("MinecartFurnace", "minecraft:furnace_minecart");
        p_209312_0_.put("MinecartHopper", "minecraft:hopper_minecart");
        p_209312_0_.put("MinecartRideable", "minecraft:minecart");
        p_209312_0_.put("MinecartSpawner", "minecraft:spawner_minecart");
        p_209312_0_.put("MinecartTNT", "minecraft:tnt_minecart");
        p_209312_0_.put("Mule", "minecraft:mule");
        p_209312_0_.put("MushroomCow", "minecraft:mooshroom");
        p_209312_0_.put("Ozelot", "minecraft:ocelot");
        p_209312_0_.put("Painting", "minecraft:painting");
        p_209312_0_.put("Pig", "minecraft:pig");
        p_209312_0_.put("PigZombie", "minecraft:zombie_pigman");
        p_209312_0_.put("PolarBear", "minecraft:polar_bear");
        p_209312_0_.put("PrimedTnt", "minecraft:tnt");
        p_209312_0_.put("Rabbit", "minecraft:rabbit");
        p_209312_0_.put("Sheep", "minecraft:sheep");
        p_209312_0_.put("Shulker", "minecraft:shulker");
        p_209312_0_.put("ShulkerBullet", "minecraft:shulker_bullet");
        p_209312_0_.put("Silverfish", "minecraft:silverfish");
        p_209312_0_.put("Skeleton", "minecraft:skeleton");
        p_209312_0_.put("SkeletonHorse", "minecraft:skeleton_horse");
        p_209312_0_.put("Slime", "minecraft:slime");
        p_209312_0_.put("SmallFireball", "minecraft:small_fireball");
        p_209312_0_.put("SnowMan", "minecraft:snowman");
        p_209312_0_.put("Snowball", "minecraft:snowball");
        p_209312_0_.put("SpectralArrow", "minecraft:spectral_arrow");
        p_209312_0_.put("Spider", "minecraft:spider");
        p_209312_0_.put("Squid", "minecraft:squid");
        p_209312_0_.put("Stray", "minecraft:stray");
        p_209312_0_.put("ThrownEgg", "minecraft:egg");
        p_209312_0_.put("ThrownEnderpearl", "minecraft:ender_pearl");
        p_209312_0_.put("ThrownExpBottle", "minecraft:xp_bottle");
        p_209312_0_.put("ThrownPotion", "minecraft:potion");
        p_209312_0_.put("Villager", "minecraft:villager");
        p_209312_0_.put("VillagerGolem", "minecraft:villager_golem");
        p_209312_0_.put("Witch", "minecraft:witch");
        p_209312_0_.put("WitherBoss", "minecraft:wither");
        p_209312_0_.put("WitherSkeleton", "minecraft:wither_skeleton");
        p_209312_0_.put("WitherSkull", "minecraft:wither_skull");
        p_209312_0_.put("Wolf", "minecraft:wolf");
        p_209312_0_.put("XPOrb", "minecraft:xp_orb");
        p_209312_0_.put("Zombie", "minecraft:zombie");
        p_209312_0_.put("ZombieHorse", "minecraft:zombie_horse");
        p_209312_0_.put("ZombieVillager", "minecraft:zombie_villager");
    });

    public i_1939_O(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        TaggedChoice.TaggedChoiceType taggedchoicetype = this.getInputSchema().findChoiceType(References.M_182_A);
        TaggedChoice.TaggedChoiceType taggedchoicetype1 = this.getOutputSchema().findChoiceType(References.M_182_A);
        Type type = this.getInputSchema().getType(References.M_588_G);
        Type type1 = this.getOutputSchema().getType(References.M_588_G);
        return TypeRewriteRule.seq((TypeRewriteRule)this.convertUnchecked("item stack entity name hook converter", type, type1), (TypeRewriteRule)this.fixTypeEverywhere("EntityIdFix", (Type)taggedchoicetype, (Type)taggedchoicetype1, p_209744_0_ -> p_206326_0_ -> p_206326_0_.mapFirst(p_206327_0_ -> n_1700_B.getOrDefault(p_206327_0_, (String)p_206327_0_))));
    }
}


