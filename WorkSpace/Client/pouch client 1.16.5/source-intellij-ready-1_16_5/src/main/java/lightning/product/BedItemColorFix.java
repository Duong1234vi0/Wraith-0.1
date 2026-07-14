/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
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
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import java.util.Optional;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class BedItemColorFix
extends DataFix {
    public BedItemColorFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        return this.fixTypeEverywhereTyped("BedItemColorFix", this.getInputSchema().getType(References.M_588_G), p_207435_1_ -> {
            Dynamic dynamic;
            Optional optional = p_207435_1_.getOptional(opticfinder);
            if (optional.isPresent() && Objects.equals(((Pair)optional.get()).getSecond(), "minecraft:bed") && (dynamic = (Dynamic)p_207435_1_.get(DSL.remainderFinder())).get("Damage").asInt(0) == 0) {
                return p_207435_1_.set(DSL.remainderFinder(), (Object)dynamic.set("Damage", dynamic.createShort((short)14)));
            }
            return p_207435_1_;
        });
    }
}


