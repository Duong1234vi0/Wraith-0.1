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

public class EntityItemFrameDirectionFix
extends NamedEntityFix {
    public EntityItemFrameDirectionFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "EntityItemFrameDirectionFix", References.M_182_A, "minecraft:item_frame");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_209651_1_) {
        return p_209651_1_.set("Facing", p_209651_1_.createByte(EntityItemFrameDirectionFix.n_1700_B(p_209651_1_.get("Facing").asByte((byte)0))));
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }

    private static byte n_1700_B(byte p_210567_0_) {
        switch (p_210567_0_) {
            case 0: {
                return 3;
            }
            case 1: {
                return 4;
            }
            default: {
                return 2;
            }
            case 3: 
        }
        return 5;
    }
}


