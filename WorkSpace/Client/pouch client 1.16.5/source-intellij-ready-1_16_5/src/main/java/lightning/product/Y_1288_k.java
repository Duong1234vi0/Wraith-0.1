/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Map;
import java.util.Optional;
import lightning.product.References;

public class Y_1288_k
extends DataFix {
    private static final Map<String, String> n_1700_B = ImmutableMap.builder().put((Object)"down", (Object)"down_south").put((Object)"up", (Object)"up_north").put((Object)"north", (Object)"north_up").put((Object)"south", (Object)"south_up").put((Object)"west", (Object)"west_up").put((Object)"east", (Object)"east_up").build();

    public Y_1288_k(Schema p_i231458_1_, boolean p_i231458_2_) {
        super(p_i231458_1_, p_i231458_2_);
    }

    private static Dynamic<?> n_1700_B(Dynamic<?> p_233292_0_) {
        Optional optional = p_233292_0_.get("Name").asString().result();
        return optional.equals(Optional.of("minecraft:jigsaw")) ? p_233292_0_.update("Properties", p_233293_0_ -> {
            String s = p_233293_0_.get("facing").asString("north");
            return p_233293_0_.remove("facing").set("orientation", p_233293_0_.createString(n_1700_B.getOrDefault(s, s)));
        }) : p_233292_0_;
    }

    protected TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("jigsaw_rotation_fix", this.getInputSchema().getType(References.P_4830_p), p_233291_0_ -> p_233291_0_.update(DSL.remainderFinder(), Y_1288_k::n_1700_B));
    }
}


