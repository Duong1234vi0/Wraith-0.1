/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.Hook$HookFunction
 *  com.mojang.datafixers.types.templates.TypeTemplate
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.Hook;
import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import lightning.product.NamespacedSchema;
import lightning.product.References;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class R_4769_o
extends Schema {
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final Map<String, String> R_4764_Y = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209320_0_ -> {
        p_209320_0_.put("minecraft:furnace", "Furnace");
        p_209320_0_.put("minecraft:lit_furnace", "Furnace");
        p_209320_0_.put("minecraft:chest", "Chest");
        p_209320_0_.put("minecraft:trapped_chest", "Chest");
        p_209320_0_.put("minecraft:ender_chest", "EnderChest");
        p_209320_0_.put("minecraft:jukebox", "RecordPlayer");
        p_209320_0_.put("minecraft:dispenser", "Trap");
        p_209320_0_.put("minecraft:dropper", "Dropper");
        p_209320_0_.put("minecraft:sign", "Sign");
        p_209320_0_.put("minecraft:mob_spawner", "MobSpawner");
        p_209320_0_.put("minecraft:noteblock", "Music");
        p_209320_0_.put("minecraft:brewing_stand", "Cauldron");
        p_209320_0_.put("minecraft:enhanting_table", "EnchantTable");
        p_209320_0_.put("minecraft:command_block", "CommandBlock");
        p_209320_0_.put("minecraft:beacon", "Beacon");
        p_209320_0_.put("minecraft:skull", "Skull");
        p_209320_0_.put("minecraft:daylight_detector", "DLDetector");
        p_209320_0_.put("minecraft:hopper", "Hopper");
        p_209320_0_.put("minecraft:banner", "Banner");
        p_209320_0_.put("minecraft:flower_pot", "FlowerPot");
        p_209320_0_.put("minecraft:repeating_command_block", "CommandBlock");
        p_209320_0_.put("minecraft:chain_command_block", "CommandBlock");
        p_209320_0_.put("minecraft:standing_sign", "Sign");
        p_209320_0_.put("minecraft:wall_sign", "Sign");
        p_209320_0_.put("minecraft:piston_head", "Piston");
        p_209320_0_.put("minecraft:daylight_detector_inverted", "DLDetector");
        p_209320_0_.put("minecraft:unpowered_comparator", "Comparator");
        p_209320_0_.put("minecraft:powered_comparator", "Comparator");
        p_209320_0_.put("minecraft:wall_banner", "Banner");
        p_209320_0_.put("minecraft:standing_banner", "Banner");
        p_209320_0_.put("minecraft:structure_block", "Structure");
        p_209320_0_.put("minecraft:end_portal", "Airportal");
        p_209320_0_.put("minecraft:end_gateway", "EndGateway");
        p_209320_0_.put("minecraft:shield", "Banner");
    });
    protected static final Hook.HookFunction n_1700_B = new Hook.HookFunction(){

        public <T> T apply(DynamicOps<T> p_apply_1_, T p_apply_2_) {
            return R_4769_o.n_1700_B(new Dynamic(p_apply_1_, p_apply_2_), R_4764_Y, "ArmorStand");
        }
    };

    public R_4769_o(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    protected static TypeTemplate n_1700_B(Schema schema) {
        return DSL.optionalFields((String)"Equipment", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(schema)));
    }

    protected static void n_1700_B(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> R_4769_o.n_1700_B(schema));
    }

    protected static void J_1907_R(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> DSL.optionalFields((String)"inTile", (TypeTemplate)References.t_1786_h.in(schema)));
    }

    protected static void R_4764_Y(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(schema)));
    }

    protected static void G_564_y(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(schema))));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        HashMap map = Maps.newHashMap();
        p_registerEntities_1_.register((Map)map, "Item", p_206678_1_ -> DSL.optionalFields((String)"Item", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "XPOrb");
        R_4769_o.J_1907_R(p_registerEntities_1_, map, "ThrownEgg");
        p_registerEntities_1_.registerSimple((Map)map, "LeashKnot");
        p_registerEntities_1_.registerSimple((Map)map, "Painting");
        p_registerEntities_1_.register((Map)map, "Arrow", p_206682_1_ -> DSL.optionalFields((String)"inTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "TippedArrow", p_206655_1_ -> DSL.optionalFields((String)"inTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "SpectralArrow", p_206671_1_ -> DSL.optionalFields((String)"inTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        R_4769_o.J_1907_R(p_registerEntities_1_, map, "Snowball");
        R_4769_o.J_1907_R(p_registerEntities_1_, map, "Fireball");
        R_4769_o.J_1907_R(p_registerEntities_1_, map, "SmallFireball");
        R_4769_o.J_1907_R(p_registerEntities_1_, map, "ThrownEnderpearl");
        p_registerEntities_1_.registerSimple((Map)map, "EyeOfEnderSignal");
        p_registerEntities_1_.register((Map)map, "ThrownPotion", p_206688_1_ -> DSL.optionalFields((String)"inTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (String)"Potion", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        R_4769_o.J_1907_R(p_registerEntities_1_, map, "ThrownExpBottle");
        p_registerEntities_1_.register((Map)map, "ItemFrame", p_206661_1_ -> DSL.optionalFields((String)"Item", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        R_4769_o.J_1907_R(p_registerEntities_1_, map, "WitherSkull");
        p_registerEntities_1_.registerSimple((Map)map, "PrimedTnt");
        p_registerEntities_1_.register((Map)map, "FallingSand", p_206679_1_ -> DSL.optionalFields((String)"Block", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (String)"TileEntityData", (TypeTemplate)References.u_2550_I.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "FireworksRocketEntity", p_206651_1_ -> DSL.optionalFields((String)"FireworksItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "Boat");
        p_registerEntities_1_.register((Map)map, "Minecart", () -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_))));
        R_4769_o.R_4764_Y(p_registerEntities_1_, map, "MinecartRideable");
        p_registerEntities_1_.register((Map)map, "MinecartChest", p_206663_1_ -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_))));
        R_4769_o.R_4764_Y(p_registerEntities_1_, map, "MinecartFurnace");
        R_4769_o.R_4764_Y(p_registerEntities_1_, map, "MinecartTNT");
        p_registerEntities_1_.register((Map)map, "MinecartSpawner", () -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (TypeTemplate)References.w_1457_N.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "MinecartHopper", p_210752_1_ -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_))));
        R_4769_o.R_4764_Y(p_registerEntities_1_, map, "MinecartCommandBlock");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "ArmorStand");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Creeper");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Skeleton");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Spider");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Giant");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Zombie");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Slime");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Ghast");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "PigZombie");
        p_registerEntities_1_.register((Map)map, "Enderman", p_206686_1_ -> DSL.optionalFields((String)"carried", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (TypeTemplate)R_4769_o.n_1700_B(p_registerEntities_1_)));
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "CaveSpider");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Silverfish");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Blaze");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "LavaSlime");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "EnderDragon");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "WitherBoss");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Bat");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Witch");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Endermite");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Guardian");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Pig");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Sheep");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Cow");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Chicken");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Squid");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Wolf");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "MushroomCow");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "SnowMan");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Ozelot");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "VillagerGolem");
        p_registerEntities_1_.register((Map)map, "EntityHorse", p_206670_1_ -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"ArmorItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)R_4769_o.n_1700_B(p_registerEntities_1_)));
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Rabbit");
        p_registerEntities_1_.register((Map)map, "Villager", p_206656_1_ -> DSL.optionalFields((String)"Inventory", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"Offers", (TypeTemplate)DSL.optionalFields((String)"Recipes", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"buy", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"buyB", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"sell", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)))), (TypeTemplate)R_4769_o.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "EnderCrystal");
        p_registerEntities_1_.registerSimple((Map)map, "AreaEffectCloud");
        p_registerEntities_1_.registerSimple((Map)map, "ShulkerBullet");
        R_4769_o.n_1700_B(p_registerEntities_1_, (Map<String, Supplier<TypeTemplate>>)map, "Shulker");
        return map;
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema p_registerBlockEntities_1_) {
        HashMap map = Maps.newHashMap();
        R_4769_o.G_564_y(p_registerBlockEntities_1_, map, "Furnace");
        R_4769_o.G_564_y(p_registerBlockEntities_1_, map, "Chest");
        p_registerBlockEntities_1_.registerSimple((Map)map, "EnderChest");
        p_registerBlockEntities_1_.register((Map)map, "RecordPlayer", p_206684_1_ -> DSL.optionalFields((String)"RecordItem", (TypeTemplate)References.M_588_G.in(p_registerBlockEntities_1_)));
        R_4769_o.G_564_y(p_registerBlockEntities_1_, map, "Trap");
        R_4769_o.G_564_y(p_registerBlockEntities_1_, map, "Dropper");
        p_registerBlockEntities_1_.registerSimple((Map)map, "Sign");
        p_registerBlockEntities_1_.register((Map)map, "MobSpawner", p_206667_1_ -> References.w_1457_N.in(p_registerBlockEntities_1_));
        p_registerBlockEntities_1_.registerSimple((Map)map, "Music");
        p_registerBlockEntities_1_.registerSimple((Map)map, "Piston");
        R_4769_o.G_564_y(p_registerBlockEntities_1_, map, "Cauldron");
        p_registerBlockEntities_1_.registerSimple((Map)map, "EnchantTable");
        p_registerBlockEntities_1_.registerSimple((Map)map, "Airportal");
        p_registerBlockEntities_1_.registerSimple((Map)map, "Control");
        p_registerBlockEntities_1_.registerSimple((Map)map, "Beacon");
        p_registerBlockEntities_1_.registerSimple((Map)map, "Skull");
        p_registerBlockEntities_1_.registerSimple((Map)map, "DLDetector");
        R_4769_o.G_564_y(p_registerBlockEntities_1_, map, "Hopper");
        p_registerBlockEntities_1_.registerSimple((Map)map, "Comparator");
        p_registerBlockEntities_1_.register((Map)map, "FlowerPot", p_206653_1_ -> DSL.optionalFields((String)"Item", (TypeTemplate)DSL.or((TypeTemplate)DSL.constType((Type)DSL.intType()), (TypeTemplate)References.multiplayerClientSuggestionProvider.in(p_registerBlockEntities_1_))));
        p_registerBlockEntities_1_.registerSimple((Map)map, "Banner");
        p_registerBlockEntities_1_.registerSimple((Map)map, "Structure");
        p_registerBlockEntities_1_.registerSimple((Map)map, "EndGateway");
        return map;
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        p_registerTypes_1_.registerType(false, References.n_1700_B, DSL::remainder);
        p_registerTypes_1_.registerType(false, References.J_1907_R, () -> DSL.optionalFields((String)"Inventory", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerTypes_1_)), (String)"EnderItems", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerTypes_1_))));
        p_registerTypes_1_.registerType(false, References.R_4764_Y, () -> DSL.fields((String)"Level", (TypeTemplate)DSL.optionalFields((String)"Entities", (TypeTemplate)DSL.list((TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_)), (String)"TileEntities", (TypeTemplate)DSL.list((TypeTemplate)References.u_2550_I.in(p_registerTypes_1_)), (String)"TileTicks", (TypeTemplate)DSL.list((TypeTemplate)DSL.fields((String)"i", (TypeTemplate)References.t_1786_h.in(p_registerTypes_1_))))));
        p_registerTypes_1_.registerType(true, References.u_2550_I, () -> DSL.taggedChoiceLazy((String)"id", (Type)DSL.string(), (Map)p_registerTypes_3_));
        p_registerTypes_1_.registerType(true, References.Q_4569_t, () -> DSL.optionalFields((String)"Riding", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (TypeTemplate)References.M_182_A.in(p_registerTypes_1_)));
        p_registerTypes_1_.registerType(false, References.h_1847_R, () -> DSL.constType(NamespacedSchema.n_1700_B()));
        p_registerTypes_1_.registerType(true, References.M_182_A, () -> DSL.taggedChoiceLazy((String)"id", (Type)DSL.string(), (Map)p_registerTypes_2_));
        p_registerTypes_1_.registerType(true, References.M_588_G, () -> DSL.hook((TypeTemplate)DSL.optionalFields((String)"id", (TypeTemplate)DSL.or((TypeTemplate)DSL.constType((Type)DSL.intType()), (TypeTemplate)References.multiplayerClientSuggestionProvider.in(p_registerTypes_1_)), (String)"tag", (TypeTemplate)DSL.optionalFields((String)"EntityTag", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (String)"BlockEntityTag", (TypeTemplate)References.u_2550_I.in(p_registerTypes_1_), (String)"CanDestroy", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)), (String)"CanPlaceOn", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)))), (Hook.HookFunction)n_1700_B, (Hook.HookFunction)Hook.HookFunction.IDENTITY));
        p_registerTypes_1_.registerType(false, References.P_1922_E, DSL::remainder);
        p_registerTypes_1_.registerType(false, References.t_1786_h, () -> DSL.or((TypeTemplate)DSL.constType((Type)DSL.intType()), (TypeTemplate)DSL.constType(NamespacedSchema.n_1700_B())));
        p_registerTypes_1_.registerType(false, References.multiplayerClientSuggestionProvider, () -> DSL.constType(NamespacedSchema.n_1700_B()));
        p_registerTypes_1_.registerType(false, References.v_4262_N, DSL::remainder);
        p_registerTypes_1_.registerType(false, References.w_1484_f, () -> DSL.optionalFields((String)"data", (TypeTemplate)DSL.optionalFields((String)"Features", (TypeTemplate)DSL.compoundList((TypeTemplate)References.Y_601_j.in(p_registerTypes_1_)), (String)"Objectives", (TypeTemplate)DSL.list((TypeTemplate)References.Y_259_p.in(p_registerTypes_1_)), (String)"Teams", (TypeTemplate)DSL.list((TypeTemplate)References.Q_2552_b.in(p_registerTypes_1_)))));
        p_registerTypes_1_.registerType(false, References.Y_601_j, DSL::remainder);
        p_registerTypes_1_.registerType(false, References.Y_259_p, DSL::remainder);
        p_registerTypes_1_.registerType(false, References.Q_2552_b, DSL::remainder);
        p_registerTypes_1_.registerType(true, References.w_1457_N, DSL::remainder);
        p_registerTypes_1_.registerType(false, References.s_956_w, DSL::remainder);
        p_registerTypes_1_.registerType(true, References.q_2307_F, DSL::remainder);
    }

    protected static <T> T n_1700_B(Dynamic<T> p_209869_0_, Map<String, String> p_209869_1_, String p_209869_2_) {
        return (T)p_209869_0_.update("tag", p_209868_3_ -> p_209868_3_.update("BlockEntityTag", p_209870_2_ -> {
            String s = p_209869_0_.get("id").asString("");
            String s1 = (String)p_209869_1_.get(NamespacedSchema.n_1700_B(s));
            if (s1 == null) {
                J_1907_R.warn("Unable to resolve BlockEntity for ItemStack: {}", (Object)s);
                return p_209870_2_;
            }
            return p_209870_2_.set("id", p_209869_0_.createString(s1));
        }).update("EntityTag", p_209866_2_ -> {
            String s = p_209869_0_.get("id").asString("");
            return Objects.equals(NamespacedSchema.n_1700_B(s), "minecraft:armor_stand") ? p_209866_2_.set("id", p_209869_0_.createString(p_209869_2_)) : p_209866_2_;
        })).getValue();
    }
}


