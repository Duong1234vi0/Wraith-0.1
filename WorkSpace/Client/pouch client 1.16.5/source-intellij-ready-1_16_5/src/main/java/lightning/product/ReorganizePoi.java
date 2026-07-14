/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import lightning.product.References;

public class ReorganizePoi
extends DataFix {
    public ReorganizePoi(Schema p_i50421_1_, boolean p_i50421_2_) {
        super(p_i50421_1_, p_i50421_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = DSL.named((String)References.s_956_w.typeName(), (Type)DSL.remainderType());
        if (!Objects.equals(type, this.getInputSchema().getType(References.s_956_w))) {
            throw new IllegalStateException("Poi type is not what was expected.");
        }
        return this.fixTypeEverywhere("POI reorganization", type, p_219871_0_ -> p_219872_0_ -> p_219872_0_.mapSecond(ReorganizePoi::n_1700_B));
    }

    private static <T> Dynamic<T> n_1700_B(Dynamic<T> p_219870_0_) {
        HashMap map = Maps.newHashMap();
        for (int i = 0; i < 16; ++i) {
            String s = String.valueOf(i);
            Optional optional = p_219870_0_.get(s).result();
            if (!optional.isPresent()) continue;
            Dynamic dynamic = (Dynamic)optional.get();
            Dynamic dynamic1 = p_219870_0_.createMap((Map)ImmutableMap.of((Object)p_219870_0_.createString("Records"), (Object)dynamic));
            map.put(p_219870_0_.createInt(i), dynamic1);
            p_219870_0_ = p_219870_0_.remove(s);
        }
        return p_219870_0_.set("Sections", p_219870_0_.createMap((Map)map));
    }
}


