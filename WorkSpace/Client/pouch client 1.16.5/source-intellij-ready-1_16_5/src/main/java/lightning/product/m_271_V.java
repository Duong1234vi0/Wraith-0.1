/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.TaggedChoice$TaggedChoiceType
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.DynamicOps
 */
package lightning.product;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TaggedChoice;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DynamicOps;
import lightning.product.References;

public abstract class m_271_V
extends DataFix {
    protected final String n_1700_B;

    public m_271_V(String name, Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
        this.n_1700_B = name;
    }

    public TypeRewriteRule makeRule() {
        TaggedChoice.TaggedChoiceType taggedchoicetype = this.getInputSchema().findChoiceType(References.M_182_A);
        TaggedChoice.TaggedChoiceType taggedchoicetype1 = this.getOutputSchema().findChoiceType(References.M_182_A);
        return this.fixTypeEverywhere(this.n_1700_B, (Type)taggedchoicetype, (Type)taggedchoicetype1, p_209755_3_ -> p_209150_4_ -> {
            String s = (String)p_209150_4_.getFirst();
            Type type = (Type)taggedchoicetype.types().get(s);
            Pair<String, Typed<?>> pair = this.n_1700_B(s, this.n_1700_B(p_209150_4_.getSecond(), (DynamicOps<?>)p_209755_3_, (Type)type));
            Type type1 = (Type)taggedchoicetype1.types().get(pair.getFirst());
            if (!type1.equals((Object)((Typed)pair.getSecond()).getType(), true, true)) {
                throw new IllegalStateException(String.format("Dynamic type check failed: %s not equal to %s", type1, ((Typed)pair.getSecond()).getType()));
            }
            return Pair.of((Object)((String)pair.getFirst()), (Object)((Typed)pair.getSecond()).getValue());
        });
    }

    private <A> Typed<A> n_1700_B(Object p_209757_1_, DynamicOps<?> p_209757_2_, Type<A> p_209757_3_) {
        return new Typed(p_209757_3_, p_209757_2_, p_209757_1_);
    }

    protected abstract Pair<String, Typed<?>> n_1700_B(String var1, Typed<?> var2);
}


