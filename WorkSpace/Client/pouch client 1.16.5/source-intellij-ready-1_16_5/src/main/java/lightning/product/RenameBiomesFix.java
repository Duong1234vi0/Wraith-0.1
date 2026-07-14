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
import java.util.Map;
import java.util.Objects;
import lightning.product.NamespacedSchema;
import lightning.product.References;

public class RenameBiomesFix
extends DataFix {
    private final String n_1700_B;
    private final Map<String, String> J_1907_R;

    public RenameBiomesFix(Schema p_i231463_1_, boolean p_i231463_2_, String p_i231463_3_, Map<String, String> p_i231463_4_) {
        super(p_i231463_1_, p_i231463_2_);
        this.J_1907_R = p_i231463_4_;
        this.n_1700_B = p_i231463_3_;
    }

    protected TypeRewriteRule makeRule() {
        Type type = DSL.named((String)References.k_2293_S.typeName(), NamespacedSchema.n_1700_B());
        if (!Objects.equals(type, this.getInputSchema().getType(References.k_2293_S))) {
            throw new IllegalStateException("Biome type is not what was expected.");
        }
        return this.fixTypeEverywhere(this.n_1700_B, type, p_233380_1_ -> p_233379_1_ -> p_233379_1_.mapSecond(p_233381_1_ -> this.J_1907_R.getOrDefault(p_233381_1_, (String)p_233381_1_)));
    }
}


