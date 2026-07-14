/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import lightning.product.NamespacedSchema;
import lightning.product.References;
import lightning.product.NamedEntityFix;

public class EntityHorseSaddleFix
extends NamedEntityFix {
    public EntityHorseSaddleFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "EntityHorseSaddleFix", References.M_182_A, "EntityHorse");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        OpticFinder opticfinder = DSL.fieldFinder((String)"id", (Type)DSL.named((String)References.multiplayerClientSuggestionProvider.typeName(), NamespacedSchema.n_1700_B()));
        Type type = this.getInputSchema().getTypeRaw(References.M_588_G);
        OpticFinder opticfinder1 = DSL.fieldFinder((String)"SaddleItem", (Type)type);
        Optional optional = p_207419_1_.getOptionalTyped(opticfinder1);
        Dynamic dynamic = (Dynamic)p_207419_1_.get(DSL.remainderFinder());
        if (!optional.isPresent() && dynamic.get("Saddle").asBoolean(false)) {
            Typed typed = (Typed)type.pointTyped(p_207419_1_.getOps()).orElseThrow(IllegalStateException::new);
            typed = typed.set(opticfinder, (Object)Pair.of((Object)References.multiplayerClientSuggestionProvider.typeName(), (Object)"minecraft:saddle"));
            Dynamic dynamic1 = dynamic.emptyMap();
            dynamic1 = dynamic1.set("Count", dynamic1.createByte((byte)1));
            dynamic1 = dynamic1.set("Damage", dynamic1.createShort((short)0));
            typed = typed.set(DSL.remainderFinder(), (Object)dynamic1);
            dynamic.remove("Saddle");
            return p_207419_1_.set(opticfinder1, typed).set(DSL.remainderFinder(), (Object)dynamic);
        }
        return p_207419_1_;
    }
}


