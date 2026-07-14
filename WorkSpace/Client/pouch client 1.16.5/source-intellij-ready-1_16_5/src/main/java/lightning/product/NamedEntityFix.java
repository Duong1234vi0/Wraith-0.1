/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DSL$TypeReference
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;

public abstract class NamedEntityFix
extends DataFix {
    private final String n_1700_B;
    private final String J_1907_R;
    private final DSL.TypeReference R_4764_Y;

    public NamedEntityFix(Schema outputSchema, boolean changesType, String name, DSL.TypeReference type, String entityName) {
        super(outputSchema, changesType);
        this.n_1700_B = name;
        this.R_4764_Y = type;
        this.J_1907_R = entityName;
    }

    public TypeRewriteRule makeRule() {
        OpticFinder opticfinder = DSL.namedChoice((String)this.J_1907_R, (Type)this.getInputSchema().getChoiceType(this.R_4764_Y, this.J_1907_R));
        return this.fixTypeEverywhereTyped(this.n_1700_B, this.getInputSchema().getType(this.R_4764_Y), this.getOutputSchema().getType(this.R_4764_Y), p_206371_2_ -> p_206371_2_.updateTyped(opticfinder, this.getOutputSchema().getChoiceType(this.R_4764_Y, this.J_1907_R), this::n_1700_B));
    }

    protected abstract Typed<?> n_1700_B(Typed<?> var1);
}


