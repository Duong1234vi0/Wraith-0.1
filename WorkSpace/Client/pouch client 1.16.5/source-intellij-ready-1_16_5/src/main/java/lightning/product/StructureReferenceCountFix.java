/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import lightning.product.References;

public class StructureReferenceCountFix
extends DataFix {
    public StructureReferenceCountFix(Schema p_i225704_1_, boolean p_i225704_2_) {
        super(p_i225704_1_, p_i225704_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.Y_601_j);
        return this.fixTypeEverywhereTyped("Structure Reference Fix", type, p_226213_0_ -> p_226213_0_.update(DSL.remainderFinder(), StructureReferenceCountFix::n_1700_B));
    }

    private static <T> Dynamic<T> n_1700_B(Dynamic<T> p_226212_0_) {
        return p_226212_0_.update("references", p_226215_0_ -> p_226215_0_.createInt(p_226215_0_.asNumber().map(Number::intValue).result().filter(p_226214_0_ -> p_226214_0_ > 0).orElse(1).intValue()));
    }
}


