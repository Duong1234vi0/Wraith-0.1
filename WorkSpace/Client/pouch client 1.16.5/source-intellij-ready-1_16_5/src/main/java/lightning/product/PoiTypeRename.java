/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import java.util.Optional;
import lightning.product.References;

public abstract class PoiTypeRename
extends DataFix {
    public PoiTypeRename(Schema p_i225703_1_, boolean p_i225703_2_) {
        super(p_i225703_1_, p_i225703_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = DSL.named((String)References.s_956_w.typeName(), (Type)DSL.remainderType());
        if (!Objects.equals(type, this.getInputSchema().getType(References.s_956_w))) {
            throw new IllegalStateException("Poi type is not what was expected.");
        }
        return this.fixTypeEverywhere("POI rename", type, p_226203_1_ -> p_226206_1_ -> p_226206_1_.mapSecond(this::n_1700_B));
    }

    private <T> Dynamic<T> n_1700_B(Dynamic<T> p_226201_1_) {
        return p_226201_1_.update("Sections", p_226209_1_ -> p_226209_1_.updateMapValues(p_226204_1_ -> p_226204_1_.mapSecond(p_226210_1_ -> p_226210_1_.update("Records", p_226211_1_ -> (Dynamic)DataFixUtils.orElse(this.J_1907_R((Dynamic)p_226211_1_), (Object)p_226211_1_)))));
    }

    private <T> Optional<Dynamic<T>> J_1907_R(Dynamic<T> p_226205_1_) {
        return p_226205_1_.asStreamOpt().map(p_226202_2_ -> p_226205_1_.createList(p_226202_2_.map(p_226207_1_ -> p_226207_1_.update("type", p_226208_1_ -> (Dynamic)DataFixUtils.orElse((Optional)p_226208_1_.asString().map(this::n_1700_B).map(arg_0 -> ((Dynamic)p_226208_1_).createString(arg_0)).result(), (Object)p_226208_1_))))).result();
    }

    protected abstract String n_1700_B(String var1);
}


