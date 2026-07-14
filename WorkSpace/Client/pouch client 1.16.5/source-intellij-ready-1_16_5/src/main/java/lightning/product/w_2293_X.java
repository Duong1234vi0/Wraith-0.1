/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DSL$TypeReference
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.Hook$HookFunction
 *  com.mojang.datafixers.types.templates.TypeTemplate
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
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
import lightning.product.R_4769_o;

public class w_2293_X
extends Schema {
    protected static final Map<String, String> n_1700_B = (Map)DataFixUtils.make((Object)Maps.newHashMap(), p_209318_0_ -> {
        p_209318_0_.put("minecraft:furnace", "minecraft:furnace");
        p_209318_0_.put("minecraft:lit_furnace", "minecraft:furnace");
        p_209318_0_.put("minecraft:chest", "minecraft:chest");
        p_209318_0_.put("minecraft:trapped_chest", "minecraft:chest");
        p_209318_0_.put("minecraft:ender_chest", "minecraft:ender_chest");
        p_209318_0_.put("minecraft:jukebox", "minecraft:jukebox");
        p_209318_0_.put("minecraft:dispenser", "minecraft:dispenser");
        p_209318_0_.put("minecraft:dropper", "minecraft:dropper");
        p_209318_0_.put("minecraft:sign", "minecraft:sign");
        p_209318_0_.put("minecraft:mob_spawner", "minecraft:mob_spawner");
        p_209318_0_.put("minecraft:noteblock", "minecraft:noteblock");
        p_209318_0_.put("minecraft:brewing_stand", "minecraft:brewing_stand");
        p_209318_0_.put("minecraft:enhanting_table", "minecraft:enchanting_table");
        p_209318_0_.put("minecraft:command_block", "minecraft:command_block");
        p_209318_0_.put("minecraft:beacon", "minecraft:beacon");
        p_209318_0_.put("minecraft:skull", "minecraft:skull");
        p_209318_0_.put("minecraft:daylight_detector", "minecraft:daylight_detector");
        p_209318_0_.put("minecraft:hopper", "minecraft:hopper");
        p_209318_0_.put("minecraft:banner", "minecraft:banner");
        p_209318_0_.put("minecraft:flower_pot", "minecraft:flower_pot");
        p_209318_0_.put("minecraft:repeating_command_block", "minecraft:command_block");
        p_209318_0_.put("minecraft:chain_command_block", "minecraft:command_block");
        p_209318_0_.put("minecraft:shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:white_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:orange_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:magenta_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:light_blue_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:yellow_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:lime_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:pink_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:gray_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:silver_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:cyan_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:purple_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:blue_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:brown_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:green_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:red_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:black_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:bed", "minecraft:bed");
        p_209318_0_.put("minecraft:light_gray_shulker_box", "minecraft:shulker_box");
        p_209318_0_.put("minecraft:banner", "minecraft:banner");
        p_209318_0_.put("minecraft:white_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:orange_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:magenta_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:light_blue_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:yellow_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:lime_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:pink_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:gray_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:silver_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:cyan_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:purple_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:blue_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:brown_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:green_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:red_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:black_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:standing_sign", "minecraft:sign");
        p_209318_0_.put("minecraft:wall_sign", "minecraft:sign");
        p_209318_0_.put("minecraft:piston_head", "minecraft:piston");
        p_209318_0_.put("minecraft:daylight_detector_inverted", "minecraft:daylight_detector");
        p_209318_0_.put("minecraft:unpowered_comparator", "minecraft:comparator");
        p_209318_0_.put("minecraft:powered_comparator", "minecraft:comparator");
        p_209318_0_.put("minecraft:wall_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:standing_banner", "minecraft:banner");
        p_209318_0_.put("minecraft:structure_block", "minecraft:structure_block");
        p_209318_0_.put("minecraft:end_portal", "minecraft:end_portal");
        p_209318_0_.put("minecraft:end_gateway", "minecraft:end_gateway");
        p_209318_0_.put("minecraft:sign", "minecraft:sign");
        p_209318_0_.put("minecraft:shield", "minecraft:banner");
    });
    protected static final Hook.HookFunction J_1907_R = new Hook.HookFunction(){

        public <T> T apply(DynamicOps<T> p_apply_1_, T p_apply_2_) {
            return R_4769_o.n_1700_B(new Dynamic(p_apply_1_, p_apply_2_), n_1700_B, "ArmorStand");
        }
    };

    public w_2293_X(int versionKey, Schema parent) {
        super(versionKey, parent);
    }

    protected static void n_1700_B(Schema schema, Map<String, Supplier<TypeTemplate>> map, String name) {
        schema.register(map, name, () -> DSL.optionalFields((String)"Items", (TypeTemplate)DSL.list((TypeTemplate)References.M_588_G.in(schema))));
    }

    public Type<?> getChoiceType(DSL.TypeReference p_getChoiceType_1_, String p_getChoiceType_2_) {
        return Objects.equals(p_getChoiceType_1_.typeName(), References.u_2550_I.typeName()) ? super.getChoiceType(p_getChoiceType_1_, NamespacedSchema.n_1700_B(p_getChoiceType_2_)) : super.getChoiceType(p_getChoiceType_1_, p_getChoiceType_2_);
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema p_registerBlockEntities_1_) {
        HashMap map = Maps.newHashMap();
        w_2293_X.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:furnace");
        w_2293_X.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:chest");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:ender_chest");
        p_registerBlockEntities_1_.register((Map)map, "minecraft:jukebox", p_206641_1_ -> DSL.optionalFields((String)"RecordItem", (TypeTemplate)References.M_588_G.in(p_registerBlockEntities_1_)));
        w_2293_X.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:dispenser");
        w_2293_X.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:dropper");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:sign");
        p_registerBlockEntities_1_.register((Map)map, "minecraft:mob_spawner", p_206646_1_ -> References.w_1457_N.in(p_registerBlockEntities_1_));
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:noteblock");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:piston");
        w_2293_X.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:brewing_stand");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:enchanting_table");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:end_portal");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:beacon");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:skull");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:daylight_detector");
        w_2293_X.n_1700_B(p_registerBlockEntities_1_, map, "minecraft:hopper");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:comparator");
        p_registerBlockEntities_1_.register((Map)map, "minecraft:flower_pot", p_206640_1_ -> DSL.optionalFields((String)"Item", (TypeTemplate)DSL.or((TypeTemplate)DSL.constType((Type)DSL.intType()), (TypeTemplate)References.multiplayerClientSuggestionProvider.in(p_registerBlockEntities_1_))));
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:banner");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:structure_block");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:end_gateway");
        p_registerBlockEntities_1_.registerSimple((Map)map, "minecraft:command_block");
        return map;
    }

    public void registerTypes(Schema p_registerTypes_1_, Map<String, Supplier<TypeTemplate>> p_registerTypes_2_, Map<String, Supplier<TypeTemplate>> p_registerTypes_3_) {
        super.registerTypes(p_registerTypes_1_, p_registerTypes_2_, p_registerTypes_3_);
        p_registerTypes_1_.registerType(false, References.u_2550_I, () -> DSL.taggedChoiceLazy((String)"id", NamespacedSchema.n_1700_B(), (Map)p_registerTypes_3_));
        p_registerTypes_1_.registerType(true, References.M_588_G, () -> DSL.hook((TypeTemplate)DSL.optionalFields((String)"id", (TypeTemplate)References.multiplayerClientSuggestionProvider.in(p_registerTypes_1_), (String)"tag", (TypeTemplate)DSL.optionalFields((String)"EntityTag", (TypeTemplate)References.Q_4569_t.in(p_registerTypes_1_), (String)"BlockEntityTag", (TypeTemplate)References.u_2550_I.in(p_registerTypes_1_), (String)"CanDestroy", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)), (String)"CanPlaceOn", (TypeTemplate)DSL.list((TypeTemplate)References.t_1786_h.in(p_registerTypes_1_)))), (Hook.HookFunction)J_1907_R, (Hook.HookFunction)Hook.HookFunction.IDENTITY));
    }
}


