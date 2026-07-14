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

public class ItemShulkerBoxColorFix
extends DataFix {
    public static final String[] n_1700_B = new String[]{"minecraft:white_shulker_box", "minecraft:orange_shulker_box", "minecraft:magenta_shulker_box", "minecraft:light_blue_shulker_box", "minecraft:yellow_shulker_box", "minecraft:lime_shulker_box", "minecraft:pink_shulker_box", "minecraft:gray_shulker_box", "minecraft:silver_shulker_box", "minecraft:cyan_shulker_box", "minecraft:purple_shulker_box", "minecraft:blue_shulker_box", "minecraft:brown_shulker_box", "minecraft:green_shulker_box", "minecraft:red_shulker_box", "minecraft:black_shulker_box"};

    public ItemShulkerBoxColorFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.M_588_G);
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        OpticFinder opticfinder1 = type.findField("tag");
        OpticFinder opticfinder2 = opticfinder1.type().findField("BlockEntityTag");
        return this.fixTypeEverywhereTyped("ItemShulkerBoxColorFix", type, p_206358_3_ -> {
            Typed typed;
            Optional optional2;
            Optional optional1;
            Optional optional = p_206358_3_.getOptional(opticfinder);
            if (optional.isPresent() && Objects.equals(((Pair)optional.get()).getSecond(), "minecraft:shulker_box") && (optional1 = p_206358_3_.getOptionalTyped(opticfinder1)).isPresent() && (optional2 = (typed = (Typed)optional1.get()).getOptionalTyped(opticfinder2)).isPresent()) {
                Typed typed1 = (Typed)optional2.get();
                Dynamic dynamic = (Dynamic)typed1.get(DSL.remainderFinder());
                int i = dynamic.get("Color").asInt(0);
                dynamic.remove("Color");
                return p_206358_3_.set(opticfinder1, typed.set(opticfinder2, typed1.set(DSL.remainderFinder(), (Object)dynamic))).set(opticfinder, (Object)Pair.of((Object)References.multiplayerClientSuggestionProvider.typeName(), (Object)n_1700_B[i % 16]));
            }
            return p_206358_3_;
        });
    }
}


