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

public class ObjectiveDisplayNameFix
extends DataFix {
    public ObjectiveDisplayNameFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    protected TypeRewriteRule makeRule() {
        Type type = DSL.named((String)References.Y_259_p.typeName(), (Type)DSL.remainderType());
        if (!Objects.equals(type, this.getInputSchema().getType(References.Y_259_p))) {
            throw new IllegalStateException("Objective type is not what was expected.");
        }
        return this.fixTypeEverywhere("ObjectiveDisplayNameFix", type, p_211862_0_ -> p_211863_0_ -> p_211863_0_.mapSecond(p_211861_0_ -> p_211861_0_.update("DisplayName", p_211864_1_ -> (Dynamic)DataFixUtils.orElse((Optional)p_211864_1_.asString().map(p_211865_0_ -> x_282_a.n_1700_B.n_1700_B(new U_2871_b((String)p_211865_0_))).map(arg_0 -> ((Dynamic)p_211861_0_).createString(arg_0)).result(), (Object)p_211864_1_))));
    }
}


