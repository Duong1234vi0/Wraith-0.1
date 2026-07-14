/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.templates.Hook$HookFunction
 *  com.mojang.datafixers.types.templates.TypeTemplate
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.templates.Hook;
import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lightning.product.V100;
import lightning.product.NamespacedSchema;
import lightning.product.References;
import lightning.product.R_4769_o;
import lightning.product.w_2293_X;

public class a_939_m
extends NamespacedSchema {
    protected static final Hook.HookFunction J_1907_R = new Hook.HookFunction(){

        public <T> T apply(DynamicOps<T> p_apply_1_, T p_apply_2_) {
            return R_4769_o.n_1700_B(new Dynamic(p_apply_1_, p_apply_2_), w_2293_X.n_1700_B, "minecraft:armor_stand");
        }
    };

    public a_939_m(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    protected static void n_1700_B(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> V100.n_1700_B(schema));
    }

    protected static void J_1907_R(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> DSL.optionalFields((String)"inTile", (TypeTemplate)References.t_1786_h.in(schema)));
    }

    public Map<String, Supplier<TypeTemplate>> registerEntities(Schema p_registerEntities_1_) {
        HashMap map = Maps.newHashMap();
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:area_effect_cloud");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:armor_stand");
        p_registerEntities_1_.register((Map)map, "minecraft:arrow", p_206582_1_ -> DSL.optionalFields((String)"inTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:bat");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:blaze");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:boat");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:cave_spider");
        p_registerEntities_1_.register((Map)map, "minecraft:chest_minecart", p_206574_1_ -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_))));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:chicken");
        p_registerEntities_1_.register((Map)map, "minecraft:commandblock_minecart", p_206575_1_ -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:cow");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:creeper");
        p_registerEntities_1_.register((Map)map, "minecraft:donkey", p_206594_1_ -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:dragon_fireball");
        a_939_m.J_1907_R(p_registerEntities_1_, map, "minecraft:egg");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:elder_guardian");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:ender_crystal");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:ender_dragon");
        p_registerEntities_1_.register((Map)map, "minecraft:enderman", p_206567_1_ -> DSL.optionalFields((String)"carried", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:endermite");
        a_939_m.J_1907_R(p_registerEntities_1_, map, "minecraft:ender_pearl");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:eye_of_ender_signal");
        p_registerEntities_1_.register((Map)map, "minecraft:falling_block", p_206586_1_ -> DSL.optionalFields((String)"Block", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (String)"TileEntityData", (TypeTemplate)References.u_2550_I.in(p_registerEntities_1_)));
        a_939_m.J_1907_R(p_registerEntities_1_, map, "minecraft:fireball");
        p_registerEntities_1_.register((Map)map, "minecraft:fireworks_rocket", p_206588_1_ -> DSL.optionalFields((String)"FireworksItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "minecraft:furnace_minecart", p_206570_1_ -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:ghast");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:giant");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:guardian");
        p_registerEntities_1_.register((Map)map, "minecraft:hopper_minecart", p_206584_1_ -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_))));
        p_registerEntities_1_.register((Map)map, "minecraft:horse", p_206595_1_ -> DSL.optionalFields((String)"ArmorItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:husk");
        p_registerEntities_1_.register((Map)map, "minecraft:item", p_206578_1_ -> DSL.optionalFields((String)"Item", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "minecraft:item_frame", p_206587_1_ -> DSL.optionalFields((String)"Item", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:leash_knot");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:magma_cube");
        p_registerEntities_1_.register((Map)map, "minecraft:minecart", p_206568_1_ -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:mooshroom");
        p_registerEntities_1_.register((Map)map, "minecraft:mule", p_206579_1_ -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:ocelot");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:painting");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:parrot");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:pig");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:polar_bear");
        p_registerEntities_1_.register((Map)map, "minecraft:potion", p_206573_1_ -> DSL.optionalFields((String)"Potion", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"inTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:rabbit");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:sheep");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:shulker");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:shulker_bullet");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:silverfish");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:skeleton");
        p_registerEntities_1_.register((Map)map, "minecraft:skeleton_horse", p_206592_1_ -> DSL.optionalFields((String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:slime");
        a_939_m.J_1907_R(p_registerEntities_1_, map, "minecraft:small_fireball");
        a_939_m.J_1907_R(p_registerEntities_1_, map, "minecraft:snowball");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:snowman");
        p_registerEntities_1_.register((Map)map, "minecraft:spawner_minecart", p_206583_1_ -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_), (TypeTemplate)References.w_1457_N.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "minecraft:spectral_arrow", p_206571_1_ -> DSL.optionalFields((String)"inTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:spider");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:squid");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:stray");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:tnt");
        p_registerEntities_1_.register((Map)map, "minecraft:tnt_minecart", p_206591_1_ -> DSL.optionalFields((String)"DisplayTile", (TypeTemplate)References.t_1786_h.in(p_registerEntities_1_)));
        p_registerEntities_1_.register((Map)map, "minecraft:villager", p_206580_1_ -> DSL.optionalFields((String)"Inventory", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"Offers", (TypeTemplate)DSL.optionalFields((String)"Recipes", (TypeTemplate)DSL.list((TypeTemplate)DSL.optionalFields((String)"buy", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"buyB", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"sell", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_)))), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:villager_golem");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:witch");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:wither");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:wither_skeleton");
        a_939_m.J_1907_R(p_registerEntities_1_, map, "minecraft:wither_skull");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:wolf");
        a_939_m.J_1907_R(p_registerEntities_1_, map, "minecraft:xp_bottle");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:xp_orb");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:zombie");
        p_registerEntities_1_.register((Map)map, "minecraft:zombie_horse", p_206569_1_ -> DSL.optionalFields((String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:zombie_pigman");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:zombie_villager");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:evocation_fangs");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:evocation_illager");
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:illusion_illager");
        p_registerEntities_1_.register((Map)map, "minecraft:llama", p_209329_1_ -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(p_registerEntities_1_)), (String)"SaddleItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (String)"DecorItem", (TypeTemplate)References.M_588_G.in(p_registerEntities_1_), (TypeTemplate)V100.n_1700_B(p_registerEntities_1_)));
        p_registerEntities_1_.registerSimple((Map)map, "minecraft:llama_spit");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:vex");
        a_939_m.n_1700_B(p_registerEntities_1_, map, "minecraft:vindication_illager");
        return map;
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        super.registerTypes(p_registerTypes_1_, p_registerTypes_2_, p_registerTypes_3_);
        p_registerTypes_1_.registerType(true, References.M_182_A, () -> DSL.taggedChoiceLazy((String)"id", a_939_m.n_1700_B(), (Map)p_registerTypes_2_));
        p_registerTypes_1_.registerType(true, References.M_588_G, () -> DSL.hook((TypeTemplate)DSL.optionalFields((String)"id", (TypeTemplate)References.multiplayerClientSuggestionProvider.in(p_registerTypes_1_), (String)"tag", (TypeTemplate)DSL.optionalFields((String)"EntityTag", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (String)"BlockEntityTag", (TypeTemplate)References.u_2550_I.in(p_registerTypes_1_), (String)"CanDestroy", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)), (String)"CanPlaceOn", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)))), (Hook.HookFunction)J_1907_R, (Hook.HookFunction)Hook.HookFunction.IDENTITY));
    }
}


