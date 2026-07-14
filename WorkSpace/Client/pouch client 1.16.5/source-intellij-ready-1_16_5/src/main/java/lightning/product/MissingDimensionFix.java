/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.FieldFinder
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.CompoundList$CompoundListType
 *  com.mojang.datafixers.types.templates.TaggedChoice$TaggedChoiceType
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.datafixers.util.Unit
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.FieldFinder;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.CompoundList;
import com.mojang.datafixers.types.templates.TaggedChoice;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.datafixers.util.Unit;
import com.mojang.serialization.Dynamic;
import java.util.List;
import java.util.Map;
import lightning.product.NamespacedSchema;
import lightning.product.M_3907_J;
import lightning.product.References;

public class MissingDimensionFix
extends DataFix {
    public MissingDimensionFix(Schema p_i241230_1_, boolean p_i241230_2_) {
        super(p_i241230_1_, p_i241230_2_);
    }

    private static <A> Type<Pair<A, Dynamic<?>>> n_1700_B(String p_241312_0_, Type<A> p_241312_1_) {
        return DSL.and((Type)DSL.field((String)p_241312_0_, p_241312_1_), (Type)DSL.remainderType());
    }

    private static <A> Type<Pair<Either<A, Unit>, Dynamic<?>>> J_1907_R(String p_241314_0_, Type<A> p_241314_1_) {
        return DSL.and((Type)DSL.optional((Type)DSL.field((String)p_241314_0_, p_241314_1_)), (Type)DSL.remainderType());
    }

    private static <A1, A2> Type<Pair<Either<A1, Unit>, Pair<Either<A2, Unit>, Dynamic<?>>>> n_1700_B(String p_241313_0_, Type<A1> p_241313_1_, String p_241313_2_, Type<A2> p_241313_3_) {
        return DSL.and((Type)DSL.optional((Type)DSL.field((String)p_241313_0_, p_241313_1_)), (Type)DSL.optional((Type)DSL.field((String)p_241313_2_, p_241313_3_)), (Type)DSL.remainderType());
    }

    protected TypeRewriteRule makeRule() {
        Schema schema = this.getInputSchema();
        TaggedChoice.TaggedChoiceType taggedchoicetype = new TaggedChoice.TaggedChoiceType("type", DSL.string(), (Map)ImmutableMap.of((Object)"minecraft:debug", (Object)DSL.remainderType(), (Object)"minecraft:flat", MissingDimensionFix.J_1907_R("settings", MissingDimensionFix.n_1700_B("biome", schema.getType(References.k_2293_S), "layers", DSL.list(MissingDimensionFix.J_1907_R("block", schema.getType(References.t_1786_h))))), (Object)"minecraft:noise", MissingDimensionFix.n_1700_B("biome_source", DSL.taggedChoiceType((String)"type", (Type)DSL.string(), (Map)ImmutableMap.of((Object)"minecraft:fixed", MissingDimensionFix.n_1700_B("biome", schema.getType(References.k_2293_S)), (Object)"minecraft:multi_noise", (Object)DSL.list(MissingDimensionFix.n_1700_B("biome", schema.getType(References.k_2293_S))), (Object)"minecraft:checkerboard", MissingDimensionFix.n_1700_B("biomes", DSL.list((Type)schema.getType(References.k_2293_S))), (Object)"minecraft:vanilla_layered", (Object)DSL.remainderType(), (Object)"minecraft:the_end", (Object)DSL.remainderType())), "settings", DSL.or((Type)DSL.string(), MissingDimensionFix.n_1700_B("default_block", schema.getType(References.t_1786_h), "default_fluid", schema.getType(References.t_1786_h))))));
        CompoundList.CompoundListType compoundlisttype = DSL.compoundList(NamespacedSchema.n_1700_B(), MissingDimensionFix.n_1700_B("generator", taggedchoicetype));
        Type type = DSL.and((Type)compoundlisttype, (Type)DSL.remainderType());
        Type type1 = schema.getType(References.q_2307_F);
        FieldFinder fieldfinder = new FieldFinder("dimensions", type);
        if (!type1.findFieldType("dimensions").equals((Object)type)) {
            throw new IllegalStateException();
        }
        OpticFinder opticfinder = compoundlisttype.finder();
        return this.fixTypeEverywhereTyped("MissingDimensionFix", type1, p_241308_4_ -> p_241308_4_.updateTyped((OpticFinder)fieldfinder, p_241309_4_ -> p_241309_4_.updateTyped(opticfinder, p_241310_3_ -> {
            if (!(p_241310_3_.getValue() instanceof List)) {
                throw new IllegalStateException("List exptected");
            }
            if (((List)p_241310_3_.getValue()).isEmpty()) {
                Dynamic dynamic = (Dynamic)p_241308_4_.get(DSL.remainderFinder());
                Dynamic dynamic1 = this.n_1700_B(dynamic);
                return (Typed)DataFixUtils.orElse(compoundlisttype.readTyped(dynamic1).result().map(Pair::getFirst), (Object)p_241310_3_);
            }
            return p_241310_3_;
        })));
    }

    private <T> Dynamic<T> n_1700_B(Dynamic<T> p_241311_1_) {
        long i = p_241311_1_.get("seed").asLong(0L);
        return new Dynamic(p_241311_1_.getOps(), M_3907_J.n_1700_B(p_241311_1_, i, M_3907_J.n_1700_B(p_241311_1_, i), false));
    }
}


