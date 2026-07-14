/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.List$ListType
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.List;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.function.Function;
import lightning.product.NamespacedSchema;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class VillagerTradeFix
extends NamedEntityFix {
    public VillagerTradeFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "Villager trade fix", References.M_182_A, "minecraft:villager");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        OpticFinder opticfinder = p_207419_1_.getType().findField("Offers");
        OpticFinder opticfinder1 = opticfinder.type().findField("Recipes");
        Type type = opticfinder1.type();
        if (!(type instanceof List.ListType)) {
            throw new IllegalStateException("Recipes are expected to be a list.");
        }
        List.ListType listtype = (List.ListType)type;
        Type type1 = listtype.getElement();
        OpticFinder opticfinder2 = DSL.typeFinder((Type)type1);
        OpticFinder opticfinder3 = type1.findField("buy");
        OpticFinder opticfinder4 = type1.findField("buyB");
        OpticFinder opticfinder5 = type1.findField("sell");
        OpticFinder opticfinder6 = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        Function<Typed, Typed> function = p_209284_2_ -> this.n_1700_B((OpticFinder<Pair<String, String>>)opticfinder6, (Typed<?>)p_209284_2_);
        return p_207419_1_.updateTyped(opticfinder, p_209285_6_ -> p_209285_6_.updateTyped(opticfinder1, p_209287_5_ -> p_209287_5_.updateTyped(opticfinder2, p_209286_4_ -> p_209286_4_.updateTyped(opticfinder3, function).updateTyped(opticfinder4, function).updateTyped(opticfinder5, function))));
    }

    private Typed<?> n_1700_B(OpticFinder<Pair<String, String>> p_210482_1_, Typed<?> p_210482_2_) {
        return p_210482_2_.update(p_210482_1_, p_209288_0_ -> p_209288_0_.mapSecond(p_209289_0_ -> Objects.equals(p_209289_0_, "minecraft:carved_pumpkin") ? "minecraft:pumpkin" : p_209289_0_));
    }
}


