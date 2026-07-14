/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
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
import java.util.Objects;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public abstract class Z_3827_g
extends DataFix {
    private final String n_1700_B;

    public Z_3827_g(String name, Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
        this.n_1700_B = name;
    }

    public TypeRewriteRule makeRule() {
        TaggedChoice.TaggedChoiceType taggedchoicetype = this.getInputSchema().findChoiceType(References.M_182_A);
        TaggedChoice.TaggedChoiceType taggedchoicetype1 = this.getOutputSchema().findChoiceType(References.M_182_A);
        Type type = DSL.named((String)References.h_1847_R.typeName(), NamespacedSchema.n_1700_B());
        if (!Objects.equals(this.getOutputSchema().getType(References.h_1847_R), type)) {
            throw new IllegalStateException("Entity name type is not what was expected.");
        }
        return TypeRewriteRule.seq((TypeRewriteRule)this.fixTypeEverywhere(this.n_1700_B, (Type)taggedchoicetype, (Type)taggedchoicetype1, p_233400_3_ -> p_211307_3_ -> p_211307_3_.mapFirst(p_211309_3_ -> {
            String s = this.n_1700_B((String)p_211309_3_);
            Type type1 = (Type)taggedchoicetype.types().get(p_211309_3_);
            Type type2 = (Type)taggedchoicetype1.types().get(s);
            if (!type2.equals((Object)type1, true, true)) {
                throw new IllegalStateException(String.format("Dynamic type check failed: %s not equal to %s", type2, type1));
            }
            return s;
        })), (TypeRewriteRule)this.fixTypeEverywhere(this.n_1700_B + " for entity name", type, p_211308_1_ -> p_211310_1_ -> p_211310_1_.mapSecond(this::n_1700_B)));
    }

    protected abstract String n_1700_B(String var1);
}


