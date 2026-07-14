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

public class StriderGravityFix
extends NamedEntityFix {
    public StriderGravityFix(Schema p_i231466_1_, boolean p_i231466_2_) {
        super(p_i231466_1_, p_i231466_2_, "StriderGravityFix", References.M_182_A, "minecraft:strider");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_233403_1_) {
        return p_233403_1_.get("NoGravity").asBoolean(false) ? p_233403_1_.set("NoGravity", p_233403_1_.createBoolean(false)) : p_233403_1_;
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }
}


