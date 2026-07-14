/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import lightning.product.References;

public class ChunkLightRemoveFix
extends DataFix {
    public ChunkLightRemoveFix(Schema p_i50431_1_, boolean p_i50431_2_) {
        super(p_i50431_1_, p_i50431_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        Type type1 = type.findFieldType("Level");
        OpticFinder opticfinder = DSL.fieldFinder((String)"Level", (Type)type1);
        return this.fixTypeEverywhereTyped("ChunkLightRemoveFix", type, this.getOutputSchema().getType(References.R_4764_Y), p_219821_1_ -> p_219821_1_.updateTyped(opticfinder, p_219822_0_ -> p_219822_0_.update(DSL.remainderFinder(), p_219820_0_ -> p_219820_0_.remove("isLightOn"))));
    }
}


