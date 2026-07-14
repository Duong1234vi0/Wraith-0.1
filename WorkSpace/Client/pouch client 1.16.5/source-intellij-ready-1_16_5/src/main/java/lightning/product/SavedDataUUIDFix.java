/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import lightning.product.References;
import lightning.product.z_2197_Y;

public class SavedDataUUIDFix
extends z_2197_Y {
    public SavedDataUUIDFix(Schema p_i231465_1_) {
        super(p_i231465_1_, References.w_1484_f);
    }

    protected TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("SavedDataUUIDFix", this.getInputSchema().getType(this.J_1907_R), p_233386_0_ -> p_233386_0_.updateTyped(p_233386_0_.getType().findField("data"), p_233387_0_ -> p_233387_0_.update(DSL.remainderFinder(), p_233388_0_ -> p_233388_0_.update("Raids", p_233389_0_ -> p_233389_0_.createList(p_233389_0_.asStream().map(p_233390_0_ -> p_233390_0_.update("HeroesOfTheVillage", p_233391_0_ -> p_233391_0_.createList(p_233391_0_.asStream().map(p_233392_0_ -> SavedDataUUIDFix.G_564_y(p_233392_0_, "UUIDMost", "UUIDLeast").orElseGet(() -> {
            n_1700_B.warn("HeroesOfTheVillage contained invalid UUIDs.");
            return p_233392_0_;
        }))))))))));
    }
}


