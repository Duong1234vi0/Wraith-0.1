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
import java.util.Optional;
import lightning.product.M_1462_J;
import lightning.product.References;

public class ObjectiveRenderTypeFix
extends DataFix {
    public ObjectiveRenderTypeFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    private static M_1462_J.n_1700_B n_1700_B(String p_211858_0_) {
        return p_211858_0_.equals("health") ? M_1462_J.n_1700_B.J_1907_R : M_1462_J.n_1700_B.n_1700_B;
    }

    protected TypeRewriteRule makeRule() {
        Type type = DSL.named((String)References.Y_259_p.typeName(), (Type)DSL.remainderType());
        if (!Objects.equals(type, this.getInputSchema().getType(References.Y_259_p))) {
            throw new IllegalStateException("Objective type is not what was expected.");
        }
        return this.fixTypeEverywhere("ObjectiveRenderTypeFix", type, p_211859_0_ -> p_211860_0_ -> p_211860_0_.mapSecond(p_211857_0_ -> {
            Optional optional = p_211857_0_.get("RenderType").asString().result();
            if (!optional.isPresent()) {
                String s = p_211857_0_.get("CriteriaName").asString("");
                M_1462_J.n_1700_B scorecriteria$rendertype = ObjectiveRenderTypeFix.n_1700_B(s);
                return p_211857_0_.set("RenderType", p_211857_0_.createString(scorecriteria$rendertype.n_1700_B()));
            }
            return p_211857_0_;
        }));
    }
}


