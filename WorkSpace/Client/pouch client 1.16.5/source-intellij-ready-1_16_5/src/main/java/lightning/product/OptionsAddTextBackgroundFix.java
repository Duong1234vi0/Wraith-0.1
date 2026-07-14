/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import lightning.product.References;

public class OptionsAddTextBackgroundFix
extends DataFix {
    public OptionsAddTextBackgroundFix(Schema p_i50422_1_, boolean p_i50422_2_) {
        super(p_i50422_1_, p_i50422_2_);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("OptionsAddTextBackgroundFix", this.getInputSchema().getType(References.P_1922_E), p_219858_1_ -> p_219858_1_.update(DSL.remainderFinder(), p_219855_1_ -> (Dynamic)DataFixUtils.orElse((Optional)p_219855_1_.get("chatOpacity").asString().map(p_219857_2_ -> p_219855_1_.set("textBackgroundOpacity", p_219855_1_.createDouble(this.n_1700_B((String)p_219857_2_)))).result(), (Object)p_219855_1_)));
    }

    private double n_1700_B(String p_219856_1_) {
        try {
            double d0 = 0.9 * Double.parseDouble(p_219856_1_) + 0.1;
            return d0 / 2.0;
        }
        catch (NumberFormatException numberformatexception) {
            return 0.5;
        }
    }
}


