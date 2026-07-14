/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import java.util.Objects;
import java.util.Optional;
import lightning.product.NamespacedSchema;
import lightning.product.References;
import lightning.product.EntityCustomNameToComponentFix;

public class BlockEntityCustomNameToComponentFix
extends DataFix {
    public BlockEntityCustomNameToComponentFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", NamespacedSchema.n_1700_B());
        return this.fixTypeEverywhereTyped("BlockEntityCustomNameToComponentFix", this.getInputSchema().getType(References.u_2550_I), p_207790_1_ -> p_207790_1_.update(DSL.remainderFinder(), p_207789_2_ -> {
            Optional optional = p_207790_1_.getOptional(opticfinder);
            return optional.isPresent() && Objects.equals(optional.get(), "minecraft:command_block") ? p_207789_2_ : EntityCustomNameToComponentFix.n_1700_B(p_207789_2_);
        }));
    }
}


