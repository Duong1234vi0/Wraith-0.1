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

public class x_4631_y
extends NamedEntityFix {
    public x_4631_y(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "EntityShulkerColorFix", References.M_182_A, "minecraft:shulker");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_209653_1_) {
        return !p_209653_1_.get("Color").map(Dynamic::asNumber).result().isPresent() ? p_209653_1_.set("Color", p_209653_1_.createByte((byte)10)) : p_209653_1_;
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }
}


