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

public class BlockEntityShulkerBoxColorFix
extends NamedEntityFix {
    public BlockEntityShulkerBoxColorFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "BlockEntityShulkerBoxColorFix", References.u_2550_I, "minecraft:shulker_box");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), p_207420_0_ -> p_207420_0_.remove("Color"));
    }
}


