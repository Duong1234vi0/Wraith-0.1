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

public class S_1754_o
extends NamedEntityFix {
    public S_1754_o(Schema p_i50432_1_, boolean p_i50432_2_) {
        super(p_i50432_1_, p_i50432_2_, "CatTypeFix", References.M_182_A, "minecraft:cat");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_219810_1_) {
        return p_219810_1_.get("CatType").asInt(0) == 9 ? p_219810_1_.set("CatType", p_219810_1_.createInt(10)) : p_219810_1_;
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }
}


