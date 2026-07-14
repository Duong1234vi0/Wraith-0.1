/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.datafixers.util.Unit
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.datafixers.util.Unit;
import com.mojang.serialization.Dynamic;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lightning.product.References;

public class U_4330_V
extends DataFix {
    public U_4330_V(Schema p_i231454_1_, boolean p_i231454_2_) {
        super(p_i231454_1_, p_i231454_2_);
    }

    protected TypeRewriteRule makeRule() {
        return this.n_1700_B(this.getOutputSchema().getTypeRaw(References.C_2741_M));
    }

    private <R> TypeRewriteRule n_1700_B(Type<R> p_233248_1_) {
        Type type = DSL.and((Type)DSL.optional((Type)DSL.field((String)"RecipesUsed", (Type)DSL.and((Type)DSL.compoundList(p_233248_1_, (Type)DSL.intType()), (Type)DSL.remainderType()))), (Type)DSL.remainderType());
        OpticFinder opticfinder = DSL.namedChoice((String)"minecraft:furnace", (Type)this.getInputSchema().getChoiceType(References.u_2550_I, "minecraft:furnace"));
        OpticFinder opticfinder1 = DSL.namedChoice((String)"minecraft:blast_furnace", (Type)this.getInputSchema().getChoiceType(References.u_2550_I, "minecraft:blast_furnace"));
        OpticFinder opticfinder2 = DSL.namedChoice((String)"minecraft:smoker", (Type)this.getInputSchema().getChoiceType(References.u_2550_I, "minecraft:smoker"));
        Type type1 = this.getOutputSchema().getChoiceType(References.u_2550_I, "minecraft:furnace");
        Type type2 = this.getOutputSchema().getChoiceType(References.u_2550_I, "minecraft:blast_furnace");
        Type type3 = this.getOutputSchema().getChoiceType(References.u_2550_I, "minecraft:smoker");
        Type type4 = this.getInputSchema().getType(References.u_2550_I);
        Type type5 = this.getOutputSchema().getType(References.u_2550_I);
        return this.fixTypeEverywhereTyped("FurnaceRecipesFix", type4, type5, p_233247_9_ -> p_233247_9_.updateTyped(opticfinder, type1, p_233254_3_ -> this.n_1700_B(p_233248_1_, (Type)type, (Typed<?>)p_233254_3_)).updateTyped(opticfinder1, type2, p_233253_3_ -> this.n_1700_B(p_233248_1_, (Type)type, (Typed<?>)p_233253_3_)).updateTyped(opticfinder2, type3, p_233252_3_ -> this.n_1700_B(p_233248_1_, (Type)type, (Typed<?>)p_233252_3_)));
    }

    private <R> Typed<?> n_1700_B(Type<R> p_233249_1_, Type<Pair<Either<Pair<List<Pair<R, Integer>>, Dynamic<?>>, Unit>, Dynamic<?>>> p_233249_2_, Typed<?> p_233249_3_) {
        Dynamic dynamic = (Dynamic)p_233249_3_.getOrCreate(DSL.remainderFinder());
        int i = dynamic.get("RecipesUsedSize").asInt(0);
        dynamic = dynamic.remove("RecipesUsedSize");
        ArrayList list = Lists.newArrayList();
        for (int j = 0; j < i; ++j) {
            String s = "RecipeLocation" + j;
            String s1 = "RecipeAmount" + j;
            Optional optional = dynamic.get(s).result();
            int k = dynamic.get(s1).asInt(0);
            if (k > 0) {
                optional.ifPresent(p_233250_3_ -> {
                    Optional optional1 = p_233249_1_.read(p_233250_3_).result();
                    optional1.ifPresent(p_233251_2_ -> list.add(Pair.of((Object)p_233251_2_.getFirst(), (Object)k)));
                });
            }
            dynamic = dynamic.remove(s).remove(s1);
        }
        return p_233249_3_.set(DSL.remainderFinder(), p_233249_2_, (Object)Pair.of((Object)Either.left((Object)Pair.of((Object)list, (Object)dynamic.emptyMap())), (Object)dynamic));
    }
}


