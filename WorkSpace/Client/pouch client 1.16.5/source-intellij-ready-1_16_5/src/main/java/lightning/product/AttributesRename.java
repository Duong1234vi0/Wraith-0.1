/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Map;
import lightning.product.References;

public class AttributesRename
extends DataFix {
    private static final Map<String, String> n_1700_B = ImmutableMap.builder().put((Object)"generic.maxHealth", (Object)"generic.max_health").put((Object)"Max Health", (Object)"generic.max_health").put((Object)"zombie.spawnReinforcements", (Object)"zombie.spawn_reinforcements").put((Object)"Spawn Reinforcements Chance", (Object)"zombie.spawn_reinforcements").put((Object)"horse.jumpStrength", (Object)"horse.jump_strength").put((Object)"Jump Strength", (Object)"horse.jump_strength").put((Object)"generic.followRange", (Object)"generic.follow_range").put((Object)"Follow Range", (Object)"generic.follow_range").put((Object)"generic.knockbackResistance", (Object)"generic.knockback_resistance").put((Object)"Knockback Resistance", (Object)"generic.knockback_resistance").put((Object)"generic.movementSpeed", (Object)"generic.movement_speed").put((Object)"Movement Speed", (Object)"generic.movement_speed").put((Object)"generic.flyingSpeed", (Object)"generic.flying_speed").put((Object)"Flying Speed", (Object)"generic.flying_speed").put((Object)"generic.attackDamage", (Object)"generic.attack_damage").put((Object)"generic.attackKnockback", (Object)"generic.attack_knockback").put((Object)"generic.attackSpeed", (Object)"generic.attack_speed").put((Object)"generic.armorToughness", (Object)"generic.armor_toughness").build();

    public AttributesRename(Schema p_i231445_1_) {
        super(p_i231445_1_, false);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = type.findField("tag");
        return TypeRewriteRule.seq((TypeRewriteRule)this.fixTypeEverywhereTyped("Rename ItemStack Attributes", type, p_233071_1_ -> p_233071_1_.updateTyped(opticfinder, AttributesRename::n_1700_B)), (TypeRewriteRule[])new TypeRewriteRule[]{this.fixTypeEverywhereTyped("Rename Entity Attributes", this.getInputSchema().getType(References.M_182_A), AttributesRename::J_1907_R), this.fixTypeEverywhereTyped("Rename Player Attributes", this.getInputSchema().getType(References.J_1907_R), AttributesRename::J_1907_R)});
    }

    private static Dynamic<?> n_1700_B(Dynamic<?> p_233073_0_) {
        return (Dynamic)DataFixUtils.orElse(p_233073_0_.asString().result().map(p_233074_0_ -> n_1700_B.getOrDefault(p_233074_0_, (String)p_233074_0_)).map(arg_0 -> p_233073_0_.createString(arg_0)), p_233073_0_);
    }

    private static Typed<?> n_1700_B(Typed<?> p_233072_0_) {
        return p_233072_0_.update(DSL.remainderFinder(), p_233081_0_ -> p_233081_0_.update("AttributeModifiers", p_233082_0_ -> (Dynamic)DataFixUtils.orElse(p_233082_0_.asStreamOpt().result().map(p_233078_0_ -> p_233078_0_.map(p_233083_0_ -> p_233083_0_.update("AttributeName", AttributesRename::n_1700_B))).map(arg_0 -> ((Dynamic)p_233082_0_).createList(arg_0)), (Object)p_233082_0_)));
    }

    private static Typed<?> J_1907_R(Typed<?> p_233076_0_) {
        return p_233076_0_.update(DSL.remainderFinder(), p_233077_0_ -> p_233077_0_.update("Attributes", p_233079_0_ -> (Dynamic)DataFixUtils.orElse(p_233079_0_.asStreamOpt().result().map(p_233075_0_ -> p_233075_0_.map(p_233080_0_ -> p_233080_0_.update("Name", AttributesRename::n_1700_B))).map(arg_0 -> ((Dynamic)p_233079_0_).createList(arg_0)), (Object)p_233079_0_)));
    }
}


