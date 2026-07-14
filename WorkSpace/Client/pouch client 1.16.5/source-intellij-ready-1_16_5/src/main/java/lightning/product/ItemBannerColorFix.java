/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
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

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class ItemBannerColorFix
extends DataFix {
    public ItemBannerColorFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        OpticFinder opticfinder1 = type.findField("tag");
        OpticFinder opticfinder2 = opticfinder1.type().findField("BlockEntityTag");
        return this.fixTypeEverywhereTyped("ItemBannerColorFix", type, p_207466_3_ -> {
            Optional optional = p_207466_3_.getOptional(opticfinder);
            if (optional.isPresent() && Objects.equals(((Pair)optional.get()).getSecond(), "minecraft:banner")) {
                Typed typed;
                Optional optional2;
                Dynamic dynamic = (Dynamic)p_207466_3_.get(DSL.remainderFinder());
                Optional optional1 = p_207466_3_.getOptionalTyped(opticfinder1);
                if (optional1.isPresent() && (optional2 = (typed = (Typed)optional1.get()).getOptionalTyped(opticfinder2)).isPresent()) {
                    Typed typed1 = (Typed)optional2.get();
                    Dynamic dynamic1 = (Dynamic)typed.get(DSL.remainderFinder());
                    Dynamic dynamic2 = (Dynamic)typed1.getOrCreate(DSL.remainderFinder());
                    if (dynamic2.get("Base").asNumber().result().isPresent()) {
                        Dynamic dynamic4;
                        Dynamic dynamic3;
                        dynamic = dynamic.set("Damage", dynamic.createShort((short)(dynamic2.get("Base").asInt(0) & 0xF)));
                        Optional optional3 = dynamic1.get("display").result();
                        if (optional3.isPresent() && Objects.equals(dynamic3 = (Dynamic)optional3.get(), dynamic4 = dynamic3.createMap((Map)ImmutableMap.of((Object)dynamic3.createString("Lore"), (Object)dynamic3.createList(Stream.of(dynamic3.createString("(+NBT"))))))) {
                            return p_207466_3_.set(DSL.remainderFinder(), (Object)dynamic);
                        }
                        dynamic2.remove("Base");
                        return p_207466_3_.set(DSL.remainderFinder(), (Object)dynamic).set(opticfinder1, typed.set(opticfinder2, typed1.set(DSL.remainderFinder(), (Object)dynamic2)));
                    }
                }
                return p_207466_3_.set(DSL.remainderFinder(), (Object)dynamic);
            }
            return p_207466_3_;
        });
    }
}


