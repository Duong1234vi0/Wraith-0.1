/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 *  it.unimi.dsi.fastutil.shorts.ShortArrayList
 *  it.unimi.dsi.fastutil.shorts.ShortList
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import it.unimi.dsi.fastutil.shorts.ShortArrayList;
import it.unimi.dsi.fastutil.shorts.ShortList;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lightning.product.References;

public class ChunkToProtochunkFix
extends DataFix {
    public ChunkToProtochunkFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        Type type1 = this.getOutputSchema().getType(References.R_4764_Y);
        Type type2 = type.findFieldType("Level");
        Type type3 = type1.findFieldType("Level");
        Type type4 = type2.findFieldType("TileTicks");
        OpticFinder opticfinder = DSL.fieldFinder((String)"Level", (Type)type2);
        OpticFinder opticfinder1 = DSL.fieldFinder((String)"TileTicks", (Type)type4);
        return TypeRewriteRule.seq((TypeRewriteRule)this.fixTypeEverywhereTyped("ChunkToProtoChunkFix", type, this.getOutputSchema().getType(References.R_4764_Y), p_209732_3_ -> p_209732_3_.updateTyped(opticfinder, type3, p_207915_2_ -> {
            Dynamic dynamic1;
            Optional optional = p_207915_2_.getOptionalTyped(opticfinder1).flatMap(p_233158_0_ -> p_233158_0_.write().result()).flatMap(p_233159_0_ -> p_233159_0_.asStreamOpt().result());
            Dynamic dynamic = (Dynamic)p_207915_2_.get(DSL.remainderFinder());
            boolean flag = dynamic.get("TerrainPopulated").asBoolean(false) && (!dynamic.get("LightPopulated").asNumber().result().isPresent() || dynamic.get("LightPopulated").asBoolean(false));
            dynamic = dynamic.set("Status", dynamic.createString(flag ? "mobs_spawned" : "empty"));
            dynamic = dynamic.set("hasLegacyStructureData", dynamic.createBoolean(true));
            if (flag) {
                Optional optional1 = dynamic.get("Biomes").asByteBufferOpt().result();
                if (optional1.isPresent()) {
                    ByteBuffer bytebuffer = (ByteBuffer)optional1.get();
                    int[] aint = new int[256];
                    for (int i = 0; i < aint.length; ++i) {
                        if (i >= bytebuffer.capacity()) continue;
                        aint[i] = bytebuffer.get(i) & 0xFF;
                    }
                    dynamic = dynamic.set("Biomes", dynamic.createIntList(Arrays.stream(aint)));
                }
                Dynamic dynamic2 = dynamic;
                List list = IntStream.range(0, 16).mapToObj(p_211428_0_ -> new ShortArrayList()).collect(Collectors.toList());
                if (optional.isPresent()) {
                    ((Stream)optional.get()).forEach(p_233161_1_ -> {
                        int j = p_233161_1_.get("x").asInt(0);
                        int k = p_233161_1_.get("y").asInt(0);
                        int l = p_233161_1_.get("z").asInt(0);
                        short short1 = ChunkToProtochunkFix.n_1700_B(j, k, l);
                        ((ShortList)list.get(k >> 4)).add(short1);
                    });
                    dynamic = dynamic.set("ToBeTicked", dynamic.createList(list.stream().map(p_233160_1_ -> dynamic2.createList(p_233160_1_.stream().map(arg_0 -> ((Dynamic)dynamic2).createShort(arg_0))))));
                }
                dynamic1 = (Dynamic)DataFixUtils.orElse((Optional)p_207915_2_.set(DSL.remainderFinder(), (Object)dynamic).write().result(), (Object)dynamic);
            } else {
                dynamic1 = dynamic;
            }
            return (Typed)((Pair)type3.readTyped(dynamic1).result().orElseThrow(() -> new IllegalStateException("Could not read the new chunk"))).getFirst();
        })), (TypeRewriteRule)this.writeAndRead("Structure biome inject", this.getInputSchema().getType(References.Y_601_j), this.getOutputSchema().getType(References.Y_601_j)));
    }

    private static short n_1700_B(int p_210975_0_, int p_210975_1_, int p_210975_2_) {
        return (short)(p_210975_0_ & 0xF | (p_210975_1_ & 0xF) << 4 | (p_210975_2_ & 0xF) << 8);
    }
}


