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

public class EntityArmorStandSilentFix
extends NamedEntityFix {
    public EntityArmorStandSilentFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "EntityArmorStandSilentFix", References.M_182_A, "ArmorStand");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_209650_1_) {
        return p_209650_1_.get("Silent").asBoolean(false) && !p_209650_1_.get("Marker").asBoolean(false) ? p_209650_1_.remove("Silent") : p_209650_1_;
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }
}


