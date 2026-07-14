/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import lightning.product.References;
import lightning.product.z_2197_Y;

public class LevelUUIDFix
extends z_2197_Y {
    public LevelUUIDFix(Schema p_i231459_1_) {
        super(p_i231459_1_, References.n_1700_B);
    }

    protected TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("LevelUUIDFix", this.getInputSchema().getType(this.J_1907_R), p_233308_1_ -> p_233308_1_.updateTyped(DSL.remainderFinder(), p_233311_1_ -> p_233311_1_.update(DSL.remainderFinder(), p_233323_1_ -> {
            p_233323_1_ = this.G_564_y((Dynamic<?>)p_233323_1_);
            p_233323_1_ = this.R_4764_Y((Dynamic<?>)p_233323_1_);
            return this.J_1907_R((Dynamic<?>)p_233323_1_);
        })));
    }

    private Dynamic<?> J_1907_R(Dynamic<?> p_233313_1_) {
        return LevelUUIDFix.n_1700_B(p_233313_1_, "WanderingTraderId", "WanderingTraderId").orElse(p_233313_1_);
    }

    private Dynamic<?> R_4764_Y(Dynamic<?> p_233314_1_) {
        return p_233314_1_.update("DimensionData", p_233320_0_ -> p_233320_0_.updateMapValues(p_233312_0_ -> p_233312_0_.mapSecond(p_233321_0_ -> p_233321_0_.update("DragonFight", p_233322_0_ -> LevelUUIDFix.R_4764_Y(p_233322_0_, "DragonUUID", "Dragon").orElse((Dynamic<?>)p_233322_0_)))));
    }

    private Dynamic<?> G_564_y(Dynamic<?> p_233315_1_) {
        return p_233315_1_.update("CustomBossEvents", p_233316_0_ -> p_233316_0_.updateMapValues(p_233309_0_ -> p_233309_0_.mapSecond(p_233317_0_ -> p_233317_0_.update("Players", p_233310_1_ -> p_233317_0_.createList(p_233310_1_.asStream().map(p_233318_0_ -> LevelUUIDFix.n_1700_B(p_233318_0_).orElseGet(() -> {
            n_1700_B.warn("CustomBossEvents contains invalid UUIDs.");
            return p_233318_0_;
        })))))));
    }
}


