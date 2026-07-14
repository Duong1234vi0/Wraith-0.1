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
import java.util.function.Function;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class RecipesRenameFix
extends DataFix {
    private final String n_1700_B;
    private final Function<String, String> J_1907_R;

    public RecipesRenameFix(Schema p_i230047_1_, boolean p_i230047_2_, String p_i230047_3_, Function<String, String> p_i230047_4_) {
        super(p_i230047_1_, p_i230047_2_);
        this.n_1700_B = p_i230047_3_;
        this.J_1907_R = p_i230047_4_;
    }

    protected TypeRewriteRule makeRule() {
        Type type = DSL.named((String)References.C_2741_M.typeName(), NamespacedSchema.n_1700_B());
        if (!Objects.equals(type, this.getInputSchema().getType(References.C_2741_M))) {
            throw new IllegalStateException("Recipe type is not what was expected.");
        }
        return this.fixTypeEverywhere(this.n_1700_B, type, p_230075_1_ -> p_230076_1_ -> p_230076_1_.mapSecond(this.J_1907_R));
    }
}


