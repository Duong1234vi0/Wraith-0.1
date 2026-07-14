/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Pair
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import java.util.Objects;
import java.util.Optional;
import lightning.product.References;

public class EntityRidingToPassengersFix
extends DataFix {
    public EntityRidingToPassengersFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Schema schema = this.getInputSchema();
        Schema schema1 = this.getOutputSchema();
        Type type = schema.getTypeRaw(References.Q_4569_t);
        Type type1 = schema1.getTypeRaw(References.Q_4569_t);
        Type type2 = schema.getTypeRaw(References.M_182_A);
        return this.n_1700_B(schema, schema1, type, type1, type2);
    }

    private <OldEntityTree, NewEntityTree, Entity> TypeRewriteRule n_1700_B(Schema p_206340_1_, Schema p_206340_2_, Type<OldEntityTree> p_206340_3_, Type<NewEntityTree> p_206340_4_, Type<Entity> p_206340_5_) {
        Type type = DSL.named((String)References.Q_4569_t.typeName(), (Type)DSL.and((Type)DSL.optional((Type)DSL.field((String)"Riding", p_206340_3_)), p_206340_5_));
        Type type1 = DSL.named((String)References.Q_4569_t.typeName(), (Type)DSL.and((Type)DSL.optional((Type)DSL.field((String)"Passengers", (Type)DSL.list(p_206340_4_))), p_206340_5_));
        Type type2 = p_206340_1_.getType(References.Q_4569_t);
        Type type3 = p_206340_2_.getType(References.Q_4569_t);
        if (!Objects.equals(type2, type)) {
            throw new IllegalStateException("Old entity type is not what was expected.");
        }
        if (!type3.equals((Object)type1, true, true)) {
            throw new IllegalStateException("New entity type is not what was expected.");
        }
        OpticFinder opticfinder = DSL.typeFinder((Type)type);
        OpticFinder opticfinder1 = DSL.typeFinder((Type)type1);
        OpticFinder opticfinder2 = DSL.typeFinder(p_206340_4_);
        Type type4 = p_206340_1_.getType(References.J_1907_R);
        Type type5 = p_206340_2_.getType(References.J_1907_R);
        return TypeRewriteRule.seq((TypeRewriteRule)this.fixTypeEverywhere("EntityRidingToPassengerFix", type, type1, p_209760_5_ -> p_208042_6_ -> {
            Optional<Object> optional = Optional.empty();
            Pair pair = p_208042_6_;
            while (true) {
                Either either = (Either)DataFixUtils.orElse(optional.map(p_208037_4_ -> {
                    Typed typed = (Typed)p_206340_4_.pointTyped(p_209760_5_).orElseThrow(() -> new IllegalStateException("Could not create new entity tree"));
                    Object newentitytree = typed.set(opticfinder1, p_208037_4_).getOptional(opticfinder2).orElseThrow(() -> new IllegalStateException("Should always have an entity tree here"));
                    return Either.left((Object)ImmutableList.of(newentitytree));
                }), (Object)Either.right((Object)DSL.unit()));
                optional = Optional.of(Pair.of((Object)References.Q_4569_t.typeName(), (Object)Pair.of((Object)either, (Object)((Pair)pair.getSecond()).getSecond())));
                Optional optional1 = ((Either)((Pair)pair.getSecond()).getFirst()).left();
                if (!optional1.isPresent()) {
                    return (Pair)optional.orElseThrow(() -> new IllegalStateException("Should always have an entity tree here"));
                }
                pair = (Pair)new Typed(p_206340_3_, p_209760_5_, optional1.get()).getOptional(opticfinder).orElseThrow(() -> new IllegalStateException("Should always have an entity here"));
            }
        }), (TypeRewriteRule)this.writeAndRead("player RootVehicle injecter", type4, type5));
    }
}


