/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL$TypeReference
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.TaggedChoice$TaggedChoiceType
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TaggedChoice;

public class h_3335_F
extends DataFix {
    private final String n_1700_B;
    private final DSL.TypeReference J_1907_R;

    public h_3335_F(Schema outputSchema, String name, DSL.TypeReference type) {
        super(outputSchema, true);
        this.n_1700_B = name;
        this.J_1907_R = type;
    }

    public TypeRewriteRule makeRule() {
        TaggedChoice.TaggedChoiceType taggedchoicetype = this.getInputSchema().findChoiceType(this.J_1907_R);
        TaggedChoice.TaggedChoiceType taggedchoicetype1 = this.getOutputSchema().findChoiceType(this.J_1907_R);
        return this.n_1700_B(this.n_1700_B, taggedchoicetype, taggedchoicetype1);
    }

    protected final <K> TypeRewriteRule n_1700_B(String p_206290_1_, TaggedChoice.TaggedChoiceType<K> p_206290_2_, TaggedChoice.TaggedChoiceType<?> p_206290_3_) {
        if (p_206290_2_.getKeyType() != p_206290_3_.getKeyType()) {
            throw new IllegalStateException("Could not inject: key type is not the same");
        }
        return this.fixTypeEverywhere(p_206290_1_, (Type)p_206290_2_, (Type)p_206290_3_, p_209687_2_ -> p_206291_2_ -> {
            if (!p_206290_3_.hasType(p_206291_2_.getFirst())) {
                throw new IllegalArgumentException(String.format("Unknown type %s in %s ", p_206291_2_.getFirst(), this.J_1907_R));
            }
            return p_206291_2_;
        });
    }
}

