/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import lightning.product.References;
import lightning.product.NamedEntityFix;
import lightning.product.z_2197_Y;

public class w_3746_W
extends NamedEntityFix {
    public w_3746_W(Schema p_i231455_1_, String p_i231455_2_) {
        super(p_i231455_1_, false, "Gossip for for " + p_i231455_2_, References.M_182_A, p_i231455_2_);
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), p_233255_0_ -> p_233255_0_.update("Gossips", p_233257_0_ -> (Dynamic)DataFixUtils.orElse(p_233257_0_.asStreamOpt().result().map(p_233256_0_ -> p_233256_0_.map(p_233258_0_ -> z_2197_Y.R_4764_Y(p_233258_0_, "Target", "Target").orElse((Dynamic<?>)p_233258_0_))).map(arg_0 -> ((Dynamic)p_233257_0_).createList(arg_0)), (Object)p_233257_0_)));
    }
}


