/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.stream.Collectors;
import lightning.product.References;

public class n_2782_G
extends DataFix {
    public n_2782_G(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("OptionsKeyTranslationFix", this.getInputSchema().getType(References.P_1922_E), p_209667_0_ -> p_209667_0_.update(DSL.remainderFinder(), p_209668_0_ -> p_209668_0_.getMapValues().map(p_209669_1_ -> p_209668_0_.createMap(p_209669_1_.entrySet().stream().map(p_209666_1_ -> {
            String s;
            if (((Dynamic)p_209666_1_.getKey()).asString("").startsWith("key_") && !(s = ((Dynamic)p_209666_1_.getValue()).asString("")).startsWith("key.mouse") && !s.startsWith("scancode.")) {
                return Pair.of((Object)((Dynamic)p_209666_1_.getKey()), (Object)p_209668_0_.createString("key.keyboard." + s.substring("key.".length())));
            }
            return Pair.of((Object)((Dynamic)p_209666_1_.getKey()), (Object)((Dynamic)p_209666_1_.getValue()));
        }).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)))).result().orElse(p_209668_0_)));
    }
}


