/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import java.util.Optional;
import lightning.product.References;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;

public class TeamDisplayNameFix
extends DataFix {
    public TeamDisplayNameFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    protected TypeRewriteRule makeRule() {
        Type type = DSL.named((String)References.Q_2552_b.typeName(), (Type)DSL.remainderType());
        if (!Objects.equals(type, this.getInputSchema().getType(References.Q_2552_b))) {
            throw new IllegalStateException("Team type is not what was expected.");
        }
        return this.fixTypeEverywhere("TeamDisplayNameFix", type, p_211876_0_ -> p_211877_0_ -> p_211877_0_.mapSecond(p_211875_0_ -> p_211875_0_.update("DisplayName", p_211878_1_ -> (Dynamic)DataFixUtils.orElse((Optional)p_211878_1_.asString().map(p_211879_0_ -> x_282_a.n_1700_B.n_1700_B(new U_2871_b((String)p_211879_0_))).map(arg_0 -> ((Dynamic)p_211875_0_).createString(arg_0)).result(), (Object)p_211878_1_))));
    }
}


