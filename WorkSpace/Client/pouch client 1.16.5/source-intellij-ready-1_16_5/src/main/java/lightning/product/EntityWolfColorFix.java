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

public class EntityWolfColorFix
extends NamedEntityFix {
    public EntityWolfColorFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "EntityWolfColorFix", References.M_182_A, "minecraft:wolf");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_209655_1_) {
        return p_209655_1_.update("CollarColor", p_209654_0_ -> p_209654_0_.createByte((byte)(15 - p_209654_0_.asInt(0))));
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }
}


