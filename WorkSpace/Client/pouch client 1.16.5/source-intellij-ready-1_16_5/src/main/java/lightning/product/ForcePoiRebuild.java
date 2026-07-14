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
import java.util.Objects;
import lightning.product.References;

public class ForcePoiRebuild
extends DataFix {
    public ForcePoiRebuild(Schema p_i225702_1_, boolean p_i225702_2_) {
        super(p_i225702_1_, p_i225702_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = DSL.named((String)References.s_956_w.typeName(), (Type)DSL.remainderType());
        if (!Objects.equals(type, this.getInputSchema().getType(References.s_956_w))) {
            throw new IllegalStateException("Poi type is not what was expected.");
        }
        return this.fixTypeEverywhere("POI rebuild", type, p_226196_0_ -> p_226199_0_ -> p_226199_0_.mapSecond(ForcePoiRebuild::n_1700_B));
    }

    private static <T> Dynamic<T> n_1700_B(Dynamic<T> p_226195_0_) {
        return p_226195_0_.update("Sections", p_226198_0_ -> p_226198_0_.updateMapValues(p_226197_0_ -> p_226197_0_.mapSecond(p_226200_0_ -> p_226200_0_.remove("Valid"))));
    }
}


