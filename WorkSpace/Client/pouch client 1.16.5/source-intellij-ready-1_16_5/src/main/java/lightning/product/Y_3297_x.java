/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.Hook$HookFunction
 *  com.mojang.datafixers.types.templates.TypeTemplate
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.Hook;
import com.mojang.datafixers.types.templates.TypeTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.V100;
import lightning.product.NamespacedSchema;
import lightning.product.References;
import lightning.product.a_939_m;

public class Y_3297_x
extends NamespacedSchema {
    public Y_3297_x(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    protected static void n_1700_B(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> V100.n_1700_B(schema));
    }

    protected static void J_1907_R(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(schema))));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        HashMap map = Maps.newHashMap();
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:area_effect_cloud");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:armor_stand");
        p_registerEntities_1_.register((Map)map, "minecraft:arrow", p_206552_1_ -> DSL.optionalFields((String)"inBlockState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:bat");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:blaze");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:boat");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:cave_spider");
        p_registerEntities_1_.register((Map)map, "minecraft:chest_minecart", p_206546_1_ -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_))));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:chicken");
        p_registerEntities_1_.register((Map)map, "minecraft:commandblock_minecart", p_206529_1_ -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:cow");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:creeper");
        p_registerEntities_1_.register((Map)map, "minecraft:donkey", p_206533_1_ -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:dragon_fireball");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:egg");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:elder_guardian");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:ender_crystal");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:ender_dragon");
        p_registerEntities_1_.register((Map)map, "minecraft:enderman", p_206523_1_ -> DSL.optionalFields((String)"carriedBlockState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:endermite");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:ender_pearl");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:evocation_fangs");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:evocation_illager");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:eye_of_ender_signal");
        p_registerEntities_1_.register((Map)map, "minecraft:falling_block", p_206524_1_ -> DSL.optionalFields((String)"BlockState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (String)"TileEntityData", (TypeTemplate)References.u_2550_I.in(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:fireball");
        p_registerEntities_1_.register((Map)map, "minecraft:fireworks_rocket", p_206554_1_ -> DSL.optionalFields((String)"FireworksItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "minecraft:furnace_minecart", p_206515_1_ -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:ghast");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:giant");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:guardian");
        p_registerEntities_1_.register((Map)map, "minecraft:hopper_minecart", p_206541_1_ -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_))));
        p_registerEntities_1_.register((Map)map, "minecraft:horse", p_206545_1_ -> DSL.optionalFields((String)"ArmorItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:husk");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:illusion_illager");
        p_registerEntities_1_.register((Map)map, "minecraft:item", p_206520_1_ -> DSL.optionalFields((String)"Item", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "minecraft:item_frame", p_206535_1_ -> DSL.optionalFields((String)"Item", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:leash_knot");
        p_registerEntities_1_.register((Map)map, "minecraft:llama", p_209327_1_ -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"DecorItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:llama_spit");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:magma_cube");
        p_registerEntities_1_.register((Map)map, "minecraft:minecart", p_206555_1_ -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:mooshroom");
        p_registerEntities_1_.register((Map)map, "minecraft:mule", p_206526_1_ -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:ocelot");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:painting");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:parrot");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:pig");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:polar_bear");
        p_registerEntities_1_.register((Map)map, "minecraft:potion", p_206542_1_ -> DSL.optionalFields((String)"Potion", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:rabbit");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:sheep");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:shulker");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:shulker_bullet");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:silverfish");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:skeleton");
        p_registerEntities_1_.register((Map)map, "minecraft:skeleton_horse", p_206516_1_ -> DSL.optionalFields((String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:slime");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:small_fireball");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:snowball");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:snowman");
        p_registerEntities_1_.register((Map)map, "minecraft:spawner_minecart", p_206527_1_ -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_), (TypeTemplate)References.w_1457_N.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "minecraft:spectral_arrow", p_206522_1_ -> DSL.optionalFields((String)"inBlockState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:spider");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:squid");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:stray");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:tnt");
        p_registerEntities_1_.register((Map)map, "minecraft:tnt_minecart", p_206551_1_ -> DSL.optionalFields((String)"DisplayState", (TypeTemplate)References.P_4830_p.in(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:vex");
        p_registerEntities_1_.register((Map)map, "minecraft:villager", p_206534_1_ -> DSL.optionalFields((String)"Inventory", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"Offers", (TypeTemplate)DSL.optionalFields((String)"Recipes", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"buy", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"buyB", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"sell", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)))), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:villager_golem");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:vindication_illager");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:witch");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:wither");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:wither_skeleton");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:wither_skull");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:wolf");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:xp_bottle");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:xp_orb");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:zombie");
        p_registerEntities_1_.register((Map)map, "minecraft:zombie_horse", p_206521_1_ -> DSL.optionalFields((String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:zombie_pigman");
        Y_3297_x.n_1700_B(p_registerEntities_1_, map, "minecraft:zombie_villager");
        return map;
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema p_registerBlockEntities_1_) {
        HashMap map = Maps.newHashMap();
        Y_3297_x.J_1907_R(p_registerBlockEntities_1_, map, "minecraft:furnace");
        Y_3297_x.J_1907_R(p_registerBlockEntities_1_, map, "minecraft:chest");
        Y_3297_x.J_1907_R(p_registerBlockEntities_1_, map, "minecraft:trapped_chest");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:ender_chest");
        p_registerBlockEntities_1_.register((Map)map, "minecraft:jukebox", p_206549_1_ -> DSL.optionalFields((String)"RecordItem", (TypeTemplate)References.M_588_G.in(p_registerBlockEntities_1_)));
        Y_3297_x.J_1907_R(p_registerBlockEntities_1_, map, "minecraft:dispenser");
        Y_3297_x.J_1907_R(p_registerBlockEntities_1_, map, "minecraft:dropper");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:sign");
        p_registerBlockEntities_1_.register((Map)map, "minecraft:mob_spawner", p_206530_1_ -> References.w_1457_N.in(p_registerBlockEntities_1_));
        p_registerBlockEntities_1_.register((Map)map, "minecraft:piston", p_206518_1_ -> DSL.optionalFields((String)"blockState", (TypeTemplate)References.P_4830_p.in(p_registerBlockEntities_1_)));
        Y_3297_x.J_1907_R(p_registerBlockEntities_1_, map, "minecraft:brewing_stand");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:enchanting_table");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:end_portal");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:beacon");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:skull");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:daylight_detector");
        Y_3297_x.J_1907_R(p_registerBlockEntities_1_, map, "minecraft:hopper");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:comparator");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:banner");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:structure_block");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:end_gateway");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:command_block");
        Y_3297_x.J_1907_R(p_registerBlockEntities_1_, map, "minecraft:shulker_box");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:bed");
        return map;
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        p_registerTypes_1_.registerType(false, References.n_1700_B, DSL::remainder);
        p_registerTypes_1_.registerType(false, References.C_2741_M, () -> DSL.constType(Y_3297_x.n_1700_B()));
        p_registerTypes_1_.registerType(false, References.J_1907_R, () -> DSL.optionalFields((String)"RootVehicle", (TypeTemplate)DSL.optionalFields((String)"Entity", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_)), (String)"Inventory", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerTypes_1_)), (String)"EnderItems", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerTypes_1_)), (TypeTemplate)DSL.optionalFields((String)"ShoulderEntityLeft", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (String)"ShoulderEntityRight", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (String)"recipeBook", (TypeTemplate)DSL.optionalFields((String)"recipes", (TypeTemplate)DSL.list((TypeTemplate)References.C_2741_M.in(p_registerTypes_1_)), (String)"toBeDisplayed", (TypeTemplate)DSL.list((TypeTemplate)References.C_2741_M.in(p_registerTypes_1_))))));
        p_registerTypes_1_.registerType(false, References.R_4764_Y, () -> DSL.fields((String)"Level", (TypeTemplate)DSL.optionalFields((String)"Entities", (TypeTemplate)DSL.list((TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_)), (String)"TileEntities", (TypeTemplate)DSL.list((TypeTemplate)References.u_2550_I.in(p_registerTypes_1_)), (String)"TileTicks", (TypeTemplate)DSL.list((TypeTemplate)DSL.fields((String)"i", (TypeTemplate)References.t_1786_h.in(p_registerTypes_1_))), (String)"Sections", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"Palette", (TypeTemplate)DSL.list((TypeTemplate)References.P_4830_p.in(p_registerTypes_1_)))))));
        p_registerTypes_1_.registerType(true, References.u_2550_I, () -> DSL.taggedChoiceLazy((String)"id", Y_3297_x.n_1700_B(), (Map)p_registerTypes_3_));
        p_registerTypes_1_.registerType(true, References.Q_4569_t, () -> DSL.optionalFields((String)"Passengers", (TypeTemplate)DSL.list((TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_)), (TypeTemplate)References.M_182_A.in(p_registerTypes_1_)));
        p_registerTypes_1_.registerType(true, References.M_182_A, () -> DSL.taggedChoiceLazy((String)"id", Y_3297_x.n_1700_B(), (Map)p_registerTypes_2_));
        p_registerTypes_1_.registerType(true, References.M_588_G, () -> DSL.hook((TypeTemplate)DSL.optionalFields((String)"id", (TypeTemplate)References.multiplayerClientSuggestionProvider.in(p_registerTypes_1_), (String)"tag", (TypeTemplate)DSL.optionalFields((String)"EntityTag", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (String)"BlockEntityTag", (TypeTemplate)References.u_2550_I.in(p_registerTypes_1_), (String)"CanDestroy", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)), (String)"CanPlaceOn", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)))), (Hook.HookFunction)a_939_m.J_1907_R, (Hook.HookFunction)Hook.HookFunction.IDENTITY));
        p_registerTypes_1_.registerType(false, References.G_564_y, () -> DSL.compoundList((TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerTypes_1_))));
        p_registerTypes_1_.registerType(false, References.P_1922_E, DSL::remainder);
        p_registerTypes_1_.registerType(false, References.u_1723_Y, () -> DSL.optionalFields((String)"entities", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"nbt", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_))), (String)"blocks", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"nbt", (TypeTemplate)References.u_2550_I.in(p_registerTypes_1_))), (String)"palette", (TypeTemplate)DSL.list((TypeTemplate)References.P_4830_p.in(p_registerTypes_1_))));
        p_registerTypes_1_.registerType(false, References.t_1786_h, () -> DSL.constType(Y_3297_x.n_1700_B()));
        p_registerTypes_1_.registerType(false, References.multiplayerClientSuggestionProvider, () -> DSL.constType(Y_3297_x.n_1700_B()));
        p_registerTypes_1_.registerType(false, References.P_4830_p, DSL::remainder);
        Supplier<TypeTemplate> supplier = () -> DSL.compoundList((TypeTemplate)References.multiplayerClientSuggestionProvider.in(p_registerTypes_1_), (TypeTemplate)DSL.constType((Type)DSL.intType()));
        p_registerTypes_1_.registerType(false, References.v_4262_N, () -> DSL.optionalFields((String)"stats", (TypeTemplate)DSL.optionalFields((String)"minecraft:mined", (TypeTemplate)DSL.compoundList((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_), (TypeTemplate)DSL.constType((Type)DSL.intType())), (String)"minecraft:crafted", (TypeTemplate)((TypeTemplate)supplier.get()), (String)"minecraft:used", (TypeTemplate)((TypeTemplate)supplier.get()), (String)"minecraft:broken", (TypeTemplate)((TypeTemplate)supplier.get()), (String)"minecraft:picked_up", (TypeTemplate)((TypeTemplate)supplier.get()), (TypeTemplate)DSL.optionalFields((String)"minecraft:dropped", (TypeTemplate)((TypeTemplate)supplier.get()), (String)"minecraft:killed", (TypeTemplate)DSL.compoundList((TypeTemplate)References.h_1847_R.in(p_registerTypes_1_), (TypeTemplate)DSL.constType((Type)DSL.intType())), (String)"minecraft:killed_by", (TypeTemplate)DSL.compoundList((TypeTemplate)References.h_1847_R.in(p_registerTypes_1_), (TypeTemplate)DSL.constType((Type)DSL.intType())), (String)"minecraft:custom", (TypeTemplate)DSL.compoundList((TypeTemplate)DSL.constType(Y_3297_x.n_1700_B()), (TypeTemplate)DSL.constType((Type)DSL.intType()))))));
        p_registerTypes_1_.registerType(false, References.w_1484_f, () -> DSL.optionalFields((String)"data", (TypeTemplate)DSL.optionalFields((String)"Features", (TypeTemplate)DSL.compoundList((TypeTemplate)References.Y_601_j.in(p_registerTypes_1_)), (String)"Objectives", (TypeTemplate)DSL.list((TypeTemplate)References.Y_259_p.in(p_registerTypes_1_)), (String)"Teams", (TypeTemplate)DSL.list((TypeTemplate)References.Q_2552_b.in(p_registerTypes_1_)))));
        p_registerTypes_1_.registerType(false, References.Y_601_j, () -> DSL.optionalFields((String)"Children", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"CA", (TypeTemplate)References.P_4830_p.in(p_registerTypes_1_), (String)"CB", (TypeTemplate)References.P_4830_p.in(p_registerTypes_1_), (String)"CC", (TypeTemplate)References.P_4830_p.in(p_registerTypes_1_), (String)"CD", (TypeTemplate)References.P_4830_p.in(p_registerTypes_1_)))));
        p_registerTypes_1_.registerType(false, References.Y_259_p, DSL::remainder);
        p_registerTypes_1_.registerType(false, References.Q_2552_b, DSL::remainder);
        p_registerTypes_1_.registerType(true, References.w_1457_N, () -> DSL.optionalFields((String)"SpawnPotentials", (TypeTemplate)DSL.list((TypeTemplate)DSL.fields((String)"Entity", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_))), (String)"SpawnData", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_)));
        p_registerTypes_1_.registerType(false, References.t_148_a, () -> DSL.optionalFields((String)"minecraft:adventure/adventuring_time", (TypeTemplate)DSL.optionalFields((String)"criteria", (TypeTemplate)DSL.compoundList((TypeTemplate)References.k_2293_S.in(p_registerTypes_1_), (TypeTemplate)DSL.constType((Type)DSL.string()))), (String)"minecraft:adventure/kill_a_mob", (TypeTemplate)DSL.optionalFields((String)"criteria", (TypeTemplate)DSL.compoundList((TypeTemplate)References.h_1847_R.in(p_registerTypes_1_), (TypeTemplate)DSL.constType((Type)DSL.string()))), (String)"minecraft:adventure/kill_all_mobs", (TypeTemplate)DSL.optionalFields((String)"criteria", (TypeTemplate)DSL.compoundList((TypeTemplate)References.h_1847_R.in(p_registerTypes_1_), (TypeTemplate)DSL.constType((Type)DSL.string()))), (String)"minecraft:husbandry/bred_all_animals", (TypeTemplate)DSL.optionalFields((String)"criteria", (TypeTemplate)DSL.compoundList((TypeTemplate)References.h_1847_R.in(p_registerTypes_1_), (TypeTemplate)DSL.constType((Type)DSL.string())))));
        p_registerTypes_1_.registerType(false, References.k_2293_S, () -> DSL.constType(Y_3297_x.n_1700_B()));
        p_registerTypes_1_.registerType(false, References.h_1847_R, () -> DSL.constType(Y_3297_x.n_1700_B()));
        p_registerTypes_1_.registerType(false, References.s_956_w, DSL::remainder);
        p_registerTypes_1_.registerType(true, References.q_2307_F, DSL::remainder);
    }
}


