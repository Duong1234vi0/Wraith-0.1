/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import java.util.Locale;
import java.util.Optional;
import lightning.product.References;

public class E_2931_H
extends DataFix {
    public E_2931_H(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("OptionsLowerCaseLanguageFix", this.getInputSchema().getType(References.P_1922_E), p_206281_0_ -> p_206281_0_.update(DSL.remainderFinder(), p_207428_0_ -> {
            Optional optional = p_207428_0_.get("lang").asString().result();
            return optional.isPresent() ? p_207428_0_.set("lang", p_207428_0_.createString(((String)optional.get()).toLowerCase(Locale.ROOT))) : p_207428_0_;
        }));
    }
}


