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
import lightning.product.References;

public class OptionsRenameFieldFix
extends DataFix {
    private final String n_1700_B;
    private final String J_1907_R;
    private final String R_4764_Y;

    public OptionsRenameFieldFix(Schema p_i241231_1_, boolean p_i241231_2_, String p_i241231_3_, String p_i241231_4_, String p_i241231_5_) {
        super(p_i241231_1_, p_i241231_2_);
        this.n_1700_B = p_i241231_3_;
        this.J_1907_R = p_i241231_4_;
        this.R_4764_Y = p_i241231_5_;
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped(this.n_1700_B, this.getInputSchema().getType(References.P_1922_E), p_241318_1_ -> p_241318_1_.update(DSL.remainderFinder(), p_241319_1_ -> (Dynamic)DataFixUtils.orElse(p_241319_1_.get(this.J_1907_R).result().map(p_241320_2_ -> p_241319_1_.set(this.R_4764_Y, p_241320_2_).remove(this.J_1907_R)), (Object)p_241319_1_)));
    }
}


