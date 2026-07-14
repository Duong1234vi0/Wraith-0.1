/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import lightning.product.References;

public class RedstoneWireConnectionsFix
extends DataFix {
    public RedstoneWireConnectionsFix(Schema p_i231462_1_) {
        super(p_i231462_1_, false);
    }

    protected TypeRewriteRule makeRule() {
        Schema schema = this.getInputSchema();
        return this.fixTypeEverywhereTyped("RedstoneConnectionsFix", schema.getType(References.P_4830_p), p_233367_1_ -> p_233367_1_.update(DSL.remainderFinder(), this::n_1700_B));
    }

    private <T> Dynamic<T> n_1700_B(Dynamic<T> p_233368_1_) {
        boolean flag = p_233368_1_.get("Name").asString().result().filter("minecraft:redstone_wire"::equals).isPresent();
        return !flag ? p_233368_1_ : p_233368_1_.update("Properties", p_233371_0_ -> {
            String s = p_233371_0_.get("east").asString("none");
            String s1 = p_233371_0_.get("west").asString("none");
            String s2 = p_233371_0_.get("north").asString("none");
            String s3 = p_233371_0_.get("south").asString("none");
            boolean flag1 = RedstoneWireConnectionsFix.n_1700_B(s) || RedstoneWireConnectionsFix.n_1700_B(s1);
            boolean flag2 = RedstoneWireConnectionsFix.n_1700_B(s2) || RedstoneWireConnectionsFix.n_1700_B(s3);
            String s4 = !RedstoneWireConnectionsFix.n_1700_B(s) && !flag2 ? "side" : s;
            String s5 = !RedstoneWireConnectionsFix.n_1700_B(s1) && !flag2 ? "side" : s1;
            String s6 = !RedstoneWireConnectionsFix.n_1700_B(s2) && !flag1 ? "side" : s2;
            String s7 = !RedstoneWireConnectionsFix.n_1700_B(s3) && !flag1 ? "side" : s3;
            return p_233371_0_.update("east", p_233374_1_ -> p_233374_1_.createString(s4)).update("west", p_233373_1_ -> p_233373_1_.createString(s5)).update("north", p_233372_1_ -> p_233372_1_.createString(s6)).update("south", p_233370_1_ -> p_233370_1_.createString(s7));
        });
    }

    private static boolean n_1700_B(String p_233369_0_) {
        return !"none".equals(p_233369_0_);
    }
}


