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
import lightning.product.I_3420_V;
import lightning.product.References;
import lightning.product.c_3641_m;
import lightning.product.NamedEntityFix;

public class BlockEntityJukeboxFix
extends NamedEntityFix {
    public BlockEntityJukeboxFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "BlockEntityJukeboxFix", References.u_2550_I, "minecraft:jukebox");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        Type type = this.getInputSchema().getChoiceType(References.u_2550_I, "minecraft:jukebox");
        Type type1 = type.findFieldType("RecordItem");
        OpticFinder opticfinder = DSL.fieldFinder((String)"RecordItem", (Type)type1);
        Dynamic dynamic = (Dynamic)p_207419_1_.get(DSL.remainderFinder());
        int i = dynamic.get("Record").asInt(0);
        if (i > 0) {
            dynamic.remove("Record");
            String s = c_3641_m.n_1700_B(I_3420_V.n_1700_B(i), 0);
            if (s != null) {
                Dynamic dynamic1 = dynamic.emptyMap();
                dynamic1 = dynamic1.set("id", dynamic1.createString(s));
                dynamic1 = dynamic1.set("Count", dynamic1.createByte((byte)1));
                return p_207419_1_.set(opticfinder, (Typed)((Pair)type1.readTyped(dynamic1).result().orElseThrow(() -> new IllegalStateException("Could not create record item stack."))).getFirst()).set(DSL.remainderFinder(), (Object)dynamic);
            }
        }
        return p_207419_1_;
    }
}


