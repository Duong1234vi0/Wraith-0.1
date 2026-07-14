/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import lightning.product.NamespacedSchema;
import lightning.product.References;
import lightning.product.z_2197_Y;

public class ItemStackUUIDFix
extends z_2197_Y {
    public ItemStackUUIDFix(Schema p_i231456_1_) {
        super(p_i231456_1_, References.M_588_G);
    }

    public TypeRewriteRule makeRule() {
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        return this.fixTypeEverywhereTyped("ItemStackUUIDFix", this.getInputSchema().getType(this.J_1907_R), p_233277_2_ -> {
            OpticFinder opticfinder1 = p_233277_2_.getType().findField("tag");
            return p_233277_2_.updateTyped(opticfinder1, p_233278_3_ -> p_233278_3_.update(DSL.remainderFinder(), p_233279_3_ -> {
                p_233279_3_ = this.J_1907_R((Dynamic<?>)p_233279_3_);
                if (p_233277_2_.getOptional(opticfinder).map(p_233280_0_ -> "minecraft:player_head".equals(p_233280_0_.getSecond())).orElse(false).booleanValue()) {
                    p_233279_3_ = this.R_4764_Y((Dynamic<?>)p_233279_3_);
                }
                return p_233279_3_;
            }));
        });
    }

    private Dynamic<?> J_1907_R(Dynamic<?> p_233282_1_) {
        return p_233282_1_.update("AttributeModifiers", p_233281_1_ -> p_233282_1_.createList(p_233281_1_.asStream().map(p_233285_0_ -> ItemStackUUIDFix.R_4764_Y(p_233285_0_, "UUID", "UUID").orElse((Dynamic<?>)p_233285_0_))));
    }

    private Dynamic<?> R_4764_Y(Dynamic<?> p_233283_1_) {
        return p_233283_1_.update("SkullOwner", p_233284_0_ -> ItemStackUUIDFix.n_1700_B(p_233284_0_, "Id", "Id").orElse((Dynamic<?>)p_233284_0_));
    }
}


