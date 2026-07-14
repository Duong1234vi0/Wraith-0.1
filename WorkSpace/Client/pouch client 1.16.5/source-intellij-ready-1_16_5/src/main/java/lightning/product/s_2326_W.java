/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.stream.Stream;
import lightning.product.References;
import lightning.product.BlockStateData;

public class s_2326_W
extends DataFix {
    public s_2326_W(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        return this.writeFixAndRead("SavedDataVillageCropFix", this.getInputSchema().getType(References.Y_601_j), this.getOutputSchema().getType(References.Y_601_j), this::n_1700_B);
    }

    private <T> Dynamic<T> n_1700_B(Dynamic<T> p_209677_1_) {
        return p_209677_1_.update("Children", s_2326_W::J_1907_R);
    }

    private static <T> Dynamic<T> J_1907_R(Dynamic<T> p_210590_0_) {
        return p_210590_0_.asStreamOpt().map(s_2326_W::n_1700_B).map(arg_0 -> p_210590_0_.createList(arg_0)).result().orElse(p_210590_0_);
    }

    private static Stream<? extends Dynamic<?>> n_1700_B(Stream<? extends Dynamic<?>> p_210586_0_) {
        return p_210586_0_.map(p_210587_0_ -> {
            String s = p_210587_0_.get("id").asString("");
            if ("ViF".equals(s)) {
                return s_2326_W.R_4764_Y(p_210587_0_);
            }
            return "ViDF".equals(s) ? s_2326_W.G_564_y(p_210587_0_) : p_210587_0_;
        });
    }

    private static <T> Dynamic<T> R_4764_Y(Dynamic<T> p_210588_0_) {
        p_210588_0_ = s_2326_W.n_1700_B(p_210588_0_, "CA");
        return s_2326_W.n_1700_B(p_210588_0_, "CB");
    }

    private static <T> Dynamic<T> G_564_y(Dynamic<T> p_210589_0_) {
        p_210589_0_ = s_2326_W.n_1700_B(p_210589_0_, "CA");
        p_210589_0_ = s_2326_W.n_1700_B(p_210589_0_, "CB");
        p_210589_0_ = s_2326_W.n_1700_B(p_210589_0_, "CC");
        return s_2326_W.n_1700_B(p_210589_0_, "CD");
    }

    private static <T> Dynamic<T> n_1700_B(Dynamic<T> p_209676_0_, String p_209676_1_) {
        return p_209676_0_.get(p_209676_1_).asNumber().result().isPresent() ? p_209676_0_.set(p_209676_1_, BlockStateData.J_1907_R(p_209676_0_.get(p_209676_1_).asInt(0) << 4)) : p_209676_0_;
    }
}


