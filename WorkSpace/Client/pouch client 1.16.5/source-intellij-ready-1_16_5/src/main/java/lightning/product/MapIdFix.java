/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Map;
import java.util.Optional;
import lightning.product.References;

public class MapIdFix
extends DataFix {
    public MapIdFix(Schema p_i50424_1_, boolean p_i50424_2_) {
        super(p_i50424_1_, p_i50424_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.w_1484_f);
        OpticFinder opticfinder = type.findField("data");
        return this.fixTypeEverywhereTyped("Map id fix", type, p_219839_1_ -> {
            Optional optional = p_219839_1_.getOptionalTyped(opticfinder);
            return optional.isPresent() ? p_219839_1_ : p_219839_1_.update(DSL.remainderFinder(), p_219838_0_ -> p_219838_0_.createMap((Map)ImmutableMap.of((Object)p_219838_0_.createString("data"), (Object)p_219838_0_)));
        });
    }
}


