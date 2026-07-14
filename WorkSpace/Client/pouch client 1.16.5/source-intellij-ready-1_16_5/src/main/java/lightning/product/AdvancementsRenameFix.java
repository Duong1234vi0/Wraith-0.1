/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.function.Function;
import lightning.product.References;

public class AdvancementsRenameFix
extends DataFix {
    private final String n_1700_B;
    private final Function<String, String> J_1907_R;

    public AdvancementsRenameFix(Schema p_i230046_1_, boolean p_i230046_2_, String p_i230046_3_, Function<String, String> p_i230046_4_) {
        super(p_i230046_1_, p_i230046_2_);
        this.n_1700_B = p_i230046_3_;
        this.J_1907_R = p_i230046_4_;
    }

    protected TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped(this.n_1700_B, this.getInputSchema().getType(References.t_148_a), p_230071_1_ -> p_230071_1_.update(DSL.remainderFinder(), p_230068_1_ -> p_230068_1_.updateMapValues(p_233068_2_ -> {
            String s = ((Dynamic)p_233068_2_.getFirst()).asString("");
            return p_233068_2_.mapFirst(p_233069_3_ -> p_230068_1_.createString(this.J_1907_R.apply(s)));
        })));
    }
}


