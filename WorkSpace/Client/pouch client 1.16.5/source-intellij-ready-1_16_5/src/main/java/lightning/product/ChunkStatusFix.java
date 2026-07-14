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
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import lightning.product.References;

public class ChunkStatusFix
extends DataFix {
    public ChunkStatusFix(Schema p_i50430_1_, boolean p_i50430_2_) {
        super(p_i50430_1_, p_i50430_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        Type type1 = type.findFieldType("Level");
        OpticFinder opticfinder = DSL.fieldFinder((String)"Level", (Type)type1);
        return this.fixTypeEverywhereTyped("ChunkStatusFix", type, this.getOutputSchema().getType(References.R_4764_Y), p_219826_1_ -> p_219826_1_.updateTyped(opticfinder, p_219827_0_ -> {
            Dynamic dynamic = (Dynamic)p_219827_0_.get(DSL.remainderFinder());
            String s = dynamic.get("Status").asString("empty");
            if (Objects.equals(s, "postprocessed")) {
                dynamic = dynamic.set("Status", dynamic.createString("fullchunk"));
            }
            return p_219827_0_.set(DSL.remainderFinder(), (Object)dynamic);
        }));
    }
}


