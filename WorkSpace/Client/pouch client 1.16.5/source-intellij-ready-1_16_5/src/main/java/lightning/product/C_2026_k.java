/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL$TypeReference
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;

public class C_2026_k
extends DataFix {
    private final String n_1700_B;
    private final DSL.TypeReference J_1907_R;

    public C_2026_k(Schema outputSchema, String name, DSL.TypeReference type) {
        super(outputSchema, true);
        this.n_1700_B = name;
        this.J_1907_R = type;
    }

    protected TypeRewriteRule makeRule() {
        return this.writeAndRead(this.n_1700_B, this.getInputSchema().getType(this.J_1907_R), this.getOutputSchema().getType(this.J_1907_R));
    }
}

