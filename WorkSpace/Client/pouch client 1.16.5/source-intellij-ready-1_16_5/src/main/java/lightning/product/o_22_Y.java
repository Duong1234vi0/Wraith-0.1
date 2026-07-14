/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.References;
import lightning.product.c_3641_m;
import lightning.product.BlockStateData;
import org.apache.commons.lang3.StringUtils;

public class o_22_Y
extends DataFix {
    private static final Set<String> n_1700_B = ImmutableSet.builder().add((Object)"stat.craftItem.minecraft.spawn_egg").add((Object)"stat.useItem.minecraft.spawn_egg").add((Object)"stat.breakItem.minecraft.spawn_egg").add((Object)"stat.pickup.minecraft.spawn_egg").add((Object)"stat.drop.minecraft.spawn_egg").build();
    private static final Map<String, String> J_1907_R = ImmutableMap.builder().put((Object)"stat.leaveGame", (Object)"minecraft:leave_game").put((Object)"stat.playOneMinute", (Object)"minecraft:play_one_minute").put((Object)"stat.timeSinceDeath", (Object)"minecraft:time_since_death").put((Object)"stat.sneakTime", (Object)"minecraft:sneak_time").put((Object)"stat.walkOneCm", (Object)"minecraft:walk_one_cm").put((Object)"stat.crouchOneCm", (Object)"minecraft:crouch_one_cm").put((Object)"stat.sprintOneCm", (Object)"minecraft:sprint_one_cm").put((Object)"stat.swimOneCm", (Object)"minecraft:swim_one_cm").put((Object)"stat.fallOneCm", (Object)"minecraft:fall_one_cm").put((Object)"stat.climbOneCm", (Object)"minecraft:climb_one_cm").put((Object)"stat.flyOneCm", (Object)"minecraft:fly_one_cm").put((Object)"stat.diveOneCm", (Object)"minecraft:dive_one_cm").put((Object)"stat.minecartOneCm", (Object)"minecraft:minecart_one_cm").put((Object)"stat.boatOneCm", (Object)"minecraft:boat_one_cm").put((Object)"stat.pigOneCm", (Object)"minecraft:pig_one_cm").put((Object)"stat.horseOneCm", (Object)"minecraft:horse_one_cm").put((Object)"stat.aviateOneCm", (Object)"minecraft:aviate_one_cm").put((Object)"stat.jump", (Object)"minecraft:jump").put((Object)"stat.drop", (Object)"minecraft:drop").put((Object)"stat.damageDealt", (Object)"minecraft:damage_dealt").put((Object)"stat.damageTaken", (Object)"minecraft:damage_taken").put((Object)"stat.deaths", (Object)"minecraft:deaths").put((Object)"stat.mobKills", (Object)"minecraft:mob_kills").put((Object)"stat.animalsBred", (Object)"minecraft:animals_bred").put((Object)"stat.playerKills", (Object)"minecraft:player_kills").put((Object)"stat.fishCaught", (Object)"minecraft:fish_caught").put((Object)"stat.talkedToVillager", (Object)"minecraft:talked_to_villager").put((Object)"stat.tradedWithVillager", (Object)"minecraft:traded_with_villager").put((Object)"stat.cakeSlicesEaten", (Object)"minecraft:eat_cake_slice").put((Object)"stat.cauldronFilled", (Object)"minecraft:fill_cauldron").put((Object)"stat.cauldronUsed", (Object)"minecraft:use_cauldron").put((Object)"stat.armorCleaned", (Object)"minecraft:clean_armor").put((Object)"stat.bannerCleaned", (Object)"minecraft:clean_banner").put((Object)"stat.brewingstandInteraction", (Object)"minecraft:interact_with_brewingstand").put((Object)"stat.beaconInteraction", (Object)"minecraft:interact_with_beacon").put((Object)"stat.dropperInspected", (Object)"minecraft:inspect_dropper").put((Object)"stat.hopperInspected", (Object)"minecraft:inspect_hopper").put((Object)"stat.dispenserInspected", (Object)"minecraft:inspect_dispenser").put((Object)"stat.noteblockPlayed", (Object)"minecraft:play_noteblock").put((Object)"stat.noteblockTuned", (Object)"minecraft:tune_noteblock").put((Object)"stat.flowerPotted", (Object)"minecraft:pot_flower").put((Object)"stat.trappedChestTriggered", (Object)"minecraft:trigger_trapped_chest").put((Object)"stat.enderchestOpened", (Object)"minecraft:open_enderchest").put((Object)"stat.itemEnchanted", (Object)"minecraft:enchant_item").put((Object)"stat.recordPlayed", (Object)"minecraft:play_record").put((Object)"stat.furnaceInteraction", (Object)"minecraft:interact_with_furnace").put((Object)"stat.craftingTableInteraction", (Object)"minecraft:interact_with_crafting_table").put((Object)"stat.chestOpened", (Object)"minecraft:open_chest").put((Object)"stat.sleepInBed", (Object)"minecraft:sleep_in_bed").put((Object)"stat.shulkerBoxOpened", (Object)"minecraft:open_shulker_box").build();
    private static final Map<String, String> R_4764_Y = ImmutableMap.builder().put((Object)"stat.craftItem", (Object)"minecraft:crafted").put((Object)"stat.useItem", (Object)"minecraft:used").put((Object)"stat.breakItem", (Object)"minecraft:broken").put((Object)"stat.pickup", (Object)"minecraft:picked_up").put((Object)"stat.drop", (Object)"minecraft:dropped").build();
    private static final Map<String, String> G_564_y = ImmutableMap.builder().put((Object)"stat.entityKilledBy", (Object)"minecraft:killed_by").put((Object)"stat.killEntity", (Object)"minecraft:killed").build();
    private static final Map<String, String> P_1922_E = ImmutableMap.builder().put((Object)"Bat", (Object)"minecraft:bat").put((Object)"Blaze", (Object)"minecraft:blaze").put((Object)"CaveSpider", (Object)"minecraft:cave_spider").put((Object)"Chicken", (Object)"minecraft:chicken").put((Object)"Cow", (Object)"minecraft:cow").put((Object)"Creeper", (Object)"minecraft:creeper").put((Object)"Donkey", (Object)"minecraft:donkey").put((Object)"ElderGuardian", (Object)"minecraft:elder_guardian").put((Object)"Enderman", (Object)"minecraft:enderman").put((Object)"Endermite", (Object)"minecraft:endermite").put((Object)"EvocationIllager", (Object)"minecraft:evocation_illager").put((Object)"Ghast", (Object)"minecraft:ghast").put((Object)"Guardian", (Object)"minecraft:guardian").put((Object)"Horse", (Object)"minecraft:horse").put((Object)"Husk", (Object)"minecraft:husk").put((Object)"Llama", (Object)"minecraft:llama").put((Object)"LavaSlime", (Object)"minecraft:magma_cube").put((Object)"MushroomCow", (Object)"minecraft:mooshroom").put((Object)"Mule", (Object)"minecraft:mule").put((Object)"Ozelot", (Object)"minecraft:ocelot").put((Object)"Parrot", (Object)"minecraft:parrot").put((Object)"Pig", (Object)"minecraft:pig").put((Object)"PolarBear", (Object)"minecraft:polar_bear").put((Object)"Rabbit", (Object)"minecraft:rabbit").put((Object)"Sheep", (Object)"minecraft:sheep").put((Object)"Shulker", (Object)"minecraft:shulker").put((Object)"Silverfish", (Object)"minecraft:silverfish").put((Object)"SkeletonHorse", (Object)"minecraft:skeleton_horse").put((Object)"Skeleton", (Object)"minecraft:skeleton").put((Object)"Slime", (Object)"minecraft:slime").put((Object)"Spider", (Object)"minecraft:spider").put((Object)"Squid", (Object)"minecraft:squid").put((Object)"Stray", (Object)"minecraft:stray").put((Object)"Vex", (Object)"minecraft:vex").put((Object)"Villager", (Object)"minecraft:villager").put((Object)"VindicationIllager", (Object)"minecraft:vindication_illager").put((Object)"Witch", (Object)"minecraft:witch").put((Object)"WitherSkeleton", (Object)"minecraft:wither_skeleton").put((Object)"Wolf", (Object)"minecraft:wolf").put((Object)"ZombieHorse", (Object)"minecraft:zombie_horse").put((Object)"PigZombie", (Object)"minecraft:zombie_pigman").put((Object)"ZombieVillager", (Object)"minecraft:zombie_villager").put((Object)"Zombie", (Object)"minecraft:zombie").build();

    public o_22_Y(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getOutputSchema().getType(References.v_4262_N);
        return this.fixTypeEverywhereTyped("StatsCounterFix", this.getInputSchema().getType(References.v_4262_N), type, p_209680_2_ -> {
            Dynamic dynamic = (Dynamic)p_209680_2_.get(DSL.remainderFinder());
            HashMap map = Maps.newHashMap();
            Optional optional = dynamic.getMapValues().result();
            if (optional.isPresent()) {
                Iterator iterator = ((Map)optional.get()).entrySet().iterator();
                while (true) {
                    String s2;
                    String s1;
                    String s;
                    if (!iterator.hasNext()) {
                        return (Typed)((Pair)type.readTyped(dynamic.emptyMap().set("stats", dynamic.createMap((Map)map))).result().orElseThrow(() -> new IllegalStateException("Could not parse new stats object."))).getFirst();
                    }
                    Map.Entry entry = iterator.next();
                    if (!((Dynamic)entry.getValue()).asNumber().result().isPresent() || n_1700_B.contains(s = ((Dynamic)entry.getKey()).asString(""))) continue;
                    if (J_1907_R.containsKey(s)) {
                        s1 = "minecraft:custom";
                        s2 = J_1907_R.get(s);
                    } else {
                        int i = StringUtils.ordinalIndexOf((CharSequence)s, (CharSequence)".", (int)2);
                        if (i < 0) continue;
                        String s3 = s.substring(0, i);
                        if ("stat.mineBlock".equals(s3)) {
                            s1 = "minecraft:mined";
                            s2 = this.J_1907_R(s.substring(i + 1).replace('.', ':'));
                        } else if (R_4764_Y.containsKey(s3)) {
                            s1 = R_4764_Y.get(s3);
                            String s6 = s.substring(i + 1).replace('.', ':');
                            String s5 = this.n_1700_B(s6);
                            s2 = s5 == null ? s6 : s5;
                        } else {
                            if (!G_564_y.containsKey(s3)) continue;
                            s1 = G_564_y.get(s3);
                            String s4 = s.substring(i + 1).replace('.', ':');
                            s2 = P_1922_E.getOrDefault(s4, s4);
                        }
                    }
                    Dynamic dynamic1 = dynamic.createString(s1);
                    Dynamic dynamic2 = map.computeIfAbsent(dynamic1, p_209679_1_ -> dynamic.emptyMap());
                    map.put(dynamic1, dynamic2.set(s2, (Dynamic)entry.getValue()));
                }
            }
            return (Typed)((Pair)type.readTyped(dynamic.emptyMap().set("stats", dynamic.createMap((Map)map))).result().orElseThrow(() -> new IllegalStateException("Could not parse new stats object."))).getFirst();
        });
    }

    @Nullable
    protected String n_1700_B(String p_209681_1_) {
        return c_3641_m.n_1700_B(p_209681_1_, 0);
    }

    protected String J_1907_R(String p_206287_1_) {
        return BlockStateData.n_1700_B(p_206287_1_);
    }
}


