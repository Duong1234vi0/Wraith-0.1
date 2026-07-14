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
import java.util.List;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class EntityShulkerRotationFix
extends NamedEntityFix {
    public EntityShulkerRotationFix(Schema p_i231451_1_) {
        super(p_i231451_1_, false, "EntityShulkerRotationFix", References.M_182_A, "minecraft:shulker");
    }

    public Dynamic<?> n_1700_B(Dynamic<?> p_233201_1_) {
        List list = p_233201_1_.get("Rotation").asList(p_233202_0_ -> p_233202_0_.asDouble(180.0));
        if (!list.isEmpty()) {
            list.set(0, (Double)list.get(0) - 180.0);
            return p_233201_1_.set("Rotation", p_233201_1_.createList(list.stream().map(arg_0 -> p_233201_1_.createDouble(arg_0))));
        }
        return p_233201_1_;
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        return p_207419_1_.update(DSL.remainderFinder(), this::n_1700_B);
    }
}


