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
import lightning.product.References;
import lightning.product.NamedEntityFix;
import lightning.product.BlockStateData;

public class BlockEntityBlockStateFix
extends NamedEntityFix {
    public BlockEntityBlockStateFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType, "BlockEntityBlockStateFix", References.u_2550_I, "minecraft:piston");
    }

    @Override
    protected Typed<?> n_1700_B(Typed<?> p_207419_1_) {
        Type type = this.getOutputSchema().getChoiceType(References.u_2550_I, "minecraft:piston");
        Type type1 = type.findFieldType("blockState");
        OpticFinder opticfinder = DSL.fieldFinder((String)"blockState", (Type)type1);
        Dynamic dynamic = (Dynamic)p_207419_1_.get(DSL.remainderFinder());
        int i = dynamic.get("blockId").asInt(0);
        dynamic = dynamic.remove("blockId");
        int j = dynamic.get("blockData").asInt(0) & 0xF;
        dynamic = dynamic.remove("blockData");
        Dynamic<?> dynamic1 = BlockStateData.J_1907_R(i << 4 | j);
        Typed typed = (Typed)type.pointTyped(p_207419_1_.getOps()).orElseThrow(() -> new IllegalStateException("Could not create new piston block entity."));
        return typed.set(DSL.remainderFinder(), (Object)dynamic).set(opticfinder, (Typed)((Pair)type1.readTyped(dynamic1).result().orElseThrow(() -> new IllegalStateException("Could not parse newly created block state tag."))).getFirst());
    }
}


