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

public class BlockEntityKeepPacked
extends NamedEntityFix {
    public BlockEntityKeepPacked(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "BlockEntityKeepPacked", References.u_2550_I, "DUMMY");
    }

    private static Dynamic<?> n_1700_B(Dynamic<?> p_209645_0_) {
        return p_209645_0_.set("keepPacked", p_209645_0_.createBoolean(true));
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), BlockEntityKeepPacked::n_1700_B);
    }
}


