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
import java.util.Optional;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class ItemWaterPotionFix
extends DataFix {
    public ItemWaterPotionFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        OpticFinder opticfinder1 = type.findField("tag");
        return this.fixTypeEverywhereTyped("ItemWaterPotionFix", type, p_206363_2_ -> {
            String s;
            Optional optional = p_206363_2_.getOptional(opticfinder);
            if (optional.isPresent() && ("minecraft:potion".equals(s = (String)((Pair)optional.get()).getSecond()) || "minecraft:splash_potion".equals(s) || "minecraft:lingering_potion".equals(s) || "minecraft:tipped_arrow".equals(s))) {
                Typed typed = p_206363_2_.getOrCreateTyped(opticfinder1);
                Dynamic dynamic = (Dynamic)typed.get(DSL.remainderFinder());
                if (!dynamic.get("Potion").asString().result().isPresent()) {
                    dynamic = dynamic.set("Potion", dynamic.createString("minecraft:water"));
                }
                return p_206363_2_.set(opticfinder1, typed.set(DSL.remainderFinder(), (Object)dynamic));
            }
            return p_206363_2_;
        });
    }
}


