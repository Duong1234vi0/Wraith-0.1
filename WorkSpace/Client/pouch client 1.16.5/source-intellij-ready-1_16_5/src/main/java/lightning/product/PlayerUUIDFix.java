/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import lightning.product.References;
import lightning.product.a_1330_H;
import lightning.product.z_2197_Y;

public class PlayerUUIDFix
extends z_2197_Y {
    public PlayerUUIDFix(Schema p_i231461_1_) {
        super(p_i231461_1_, References.J_1907_R);
    }

    protected TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("PlayerUUIDFix", this.getInputSchema().getType(this.J_1907_R), p_233353_0_ -> {
            OpticFinder opticfinder = p_233353_0_.getType().findField("RootVehicle");
            return p_233353_0_.updateTyped(opticfinder, opticfinder.type(), p_233354_0_ -> p_233354_0_.update(DSL.remainderFinder(), p_233356_0_ -> PlayerUUIDFix.R_4764_Y(p_233356_0_, "Attach", "Attach").orElse((Dynamic<?>)p_233356_0_))).update(DSL.remainderFinder(), p_233355_0_ -> a_1330_H.R_4764_Y(a_1330_H.J_1907_R(p_233355_0_)));
        });
    }
}


