/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Objects;
import lightning.product.NamespacedSchema;
import lightning.product.References;
import lightning.product.BlockStateData;

public class BlockNameFlatteningFix
extends DataFix {
    public BlockNameFlatteningFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.t_1786_h);
        Type type1 = this.getOutputSchema().getType(References.t_1786_h);
        Type type2 = DSL.named((String)References.t_1786_h.typeName(), (Type)DSL.or((Type)DSL.intType(), NamespacedSchema.n_1700_B()));
        Type type3 = DSL.named((String)References.t_1786_h.typeName(), NamespacedSchema.n_1700_B());
        if (Objects.equals(type, type2) && Objects.equals(type1, type3)) {
            return this.fixTypeEverywhere("BlockNameFlatteningFix", type2, type3, p_209702_0_ -> p_206303_0_ -> p_206303_0_.mapSecond(p_206304_0_ -> (String)p_206304_0_.map(BlockStateData::n_1700_B, p_206305_0_ -> BlockStateData.n_1700_B(NamespacedSchema.n_1700_B(p_206305_0_)))));
        }
        throw new IllegalStateException("Expected and actual types don't match.");
    }
}


