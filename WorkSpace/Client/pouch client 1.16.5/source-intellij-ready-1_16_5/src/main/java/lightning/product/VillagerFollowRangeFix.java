/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class VillagerFollowRangeFix
extends NamedEntityFix {
    public VillagerFollowRangeFix(Schema p_i231467_1_) {
        super(p_i231467_1_, false, "Villager Follow Range Fix", References.M_182_A, "minecraft:villager");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), VillagerFollowRangeFix::n_1700_B);
    }

    private static Dynamic<?> n_1700_B(Dynamic<?> p_233409_0_) {
        return p_233409_0_.update("Attributes", p_233410_1_ -> p_233409_0_.createList(p_233410_1_.asStream().map(p_233411_0_ -> p_233411_0_.get("Name").asString("").equals("generic.follow_range") && p_233411_0_.get("Base").asDouble(0.0) == 16.0 ? p_233411_0_.set("Base", p_233411_0_.createDouble(48.0)) : p_233411_0_)));
    }
}


