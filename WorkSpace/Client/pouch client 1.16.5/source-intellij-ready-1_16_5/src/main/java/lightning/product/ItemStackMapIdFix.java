/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import java.util.Optional;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class ItemStackMapIdFix
extends DataFix {
    public ItemStackMapIdFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        OpticFinder opticfinder1 = type.findField("tag");
        return this.fixTypeEverywhereTyped("ItemInstanceMapIdFix", type, p_206360_2_ -> {
            Optional optional = p_206360_2_.getOptional(opticfinder);
            if (optional.isPresent() && Objects.equals(((Pair)optional.get()).getSecond(), "minecraft:filled_map")) {
                Dynamic dynamic = (Dynamic)p_206360_2_.get(DSL.remainderFinder());
                Typed typed = p_206360_2_.getOrCreateTyped(opticfinder1);
                Dynamic dynamic1 = (Dynamic)typed.get(DSL.remainderFinder());
                dynamic1 = dynamic1.set("map", dynamic1.createInt(dynamic.get("Damage").asInt(0)));
                return p_206360_2_.set(opticfinder1, typed.set(DSL.remainderFinder(), (Object)dynamic1));
            }
            return p_206360_2_;
        });
    }
}


