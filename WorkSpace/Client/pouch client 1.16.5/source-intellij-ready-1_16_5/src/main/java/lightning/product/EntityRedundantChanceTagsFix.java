/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.OptionalDynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Codec;
import com.mojang.serialization.OptionalDynamic;
import java.util.List;
import lightning.product.References;

public class EntityRedundantChanceTagsFix
extends DataFix {
    private static final Codec<List<Float>> n_1700_B = Codec.FLOAT.listOf();

    public EntityRedundantChanceTagsFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("EntityRedundantChanceTagsFix", this.getInputSchema().getType(References.M_182_A), p_210996_0_ -> p_210996_0_.update(DSL.remainderFinder(), p_206334_0_ -> {
            if (EntityRedundantChanceTagsFix.n_1700_B(p_206334_0_.get("HandDropChances"), 2)) {
                p_206334_0_ = p_206334_0_.remove("HandDropChances");
            }
            if (EntityRedundantChanceTagsFix.n_1700_B(p_206334_0_.get("ArmorDropChances"), 4)) {
                p_206334_0_ = p_206334_0_.remove("ArmorDropChances");
            }
            return p_206334_0_;
        }));
    }

    private static boolean n_1700_B(OptionalDynamic<?> p_241306_0_, int p_241306_1_) {
        return p_241306_0_.flatMap(arg_0 -> n_1700_B.parse(arg_0)).map(p_241304_1_ -> p_241304_1_.size() == p_241306_1_ && p_241304_1_.stream().allMatch(p_241307_0_ -> p_241307_0_.floatValue() == 0.0f)).result().orElse(false);
    }
}


