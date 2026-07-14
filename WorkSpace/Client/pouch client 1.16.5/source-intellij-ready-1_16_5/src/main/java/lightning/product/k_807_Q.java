/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class k_807_Q
extends NamedEntityFix {
    public k_807_Q(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "Colorless shulker entity fix", References.M_182_A, "minecraft:shulker");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), p_207421_0_ -> p_207421_0_.get("Color").asInt(0) == 10 ? p_207421_0_.set("Color", p_207421_0_.createByte((byte)16)) : p_207421_0_);
    }
}


