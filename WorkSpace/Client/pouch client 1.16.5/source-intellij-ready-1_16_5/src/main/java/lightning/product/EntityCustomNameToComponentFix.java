/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import java.util.Optional;
import lightning.product.NamespacedSchema;
import lightning.product.References;
import lightning.product.U_2871_b;
import lightning.product.x_282_a;

public class EntityCustomNameToComponentFix
extends DataFix {
    public EntityCustomNameToComponentFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", NamespacedSchema.n_1700_B());
        return this.fixTypeEverywhereTyped("EntityCustomNameToComponentFix", this.getInputSchema().getType(References.M_182_A), p_207792_1_ -> p_207792_1_.update(DSL.remainderFinder(), p_207791_2_ -> {
            Optional optional = p_207792_1_.getOptional(opticfinder);
            return optional.isPresent() && Objects.equals(optional.get(), "minecraft:commandblock_minecart") ? p_207791_2_ : EntityCustomNameToComponentFix.n_1700_B(p_207791_2_);
        }));
    }

    public static Dynamic<?> n_1700_B(Dynamic<?> p_209740_0_) {
        String s = p_209740_0_.get("CustomName").asString("");
        return s.isEmpty() ? p_209740_0_.remove("CustomName") : p_209740_0_.set("CustomName", p_209740_0_.createString(x_282_a.n_1700_B.n_1700_B(new U_2871_b(s))));
    }
}


