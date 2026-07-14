/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import java.util.Optional;
import java.util.UUID;
import lightning.product.References;

public class EntityStringUuidFix
extends DataFix {
    public EntityStringUuidFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("EntityStringUuidFix", this.getInputSchema().getType(References.M_182_A), p_206344_0_ -> p_206344_0_.update(DSL.remainderFinder(), p_206345_0_ -> {
            Optional optional = p_206345_0_.get("UUID").asString().result();
            if (optional.isPresent()) {
                UUID uuid = UUID.fromString((String)optional.get());
                return p_206345_0_.remove("UUID").set("UUIDMost", p_206345_0_.createLong(uuid.getMostSignificantBits())).set("UUIDLeast", p_206345_0_.createLong(uuid.getLeastSignificantBits()));
            }
            return p_206345_0_;
        }));
    }
}


