/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Map;
import java.util.Objects;
import lightning.product.References;

public class ChunkStatusFix2
extends DataFix {
    private static final Map<String, String> n_1700_B = ImmutableMap.builder().put((Object)"structure_references", (Object)"empty").put((Object)"biomes", (Object)"empty").put((Object)"base", (Object)"surface").put((Object)"carved", (Object)"carvers").put((Object)"liquid_carved", (Object)"liquid_carvers").put((Object)"decorated", (Object)"features").put((Object)"lighted", (Object)"light").put((Object)"mobs_spawned", (Object)"spawn").put((Object)"finalized", (Object)"heightmaps").put((Object)"fullchunk", (Object)"full").build();

    public ChunkStatusFix2(Schema p_i50429_1_, boolean p_i50429_2_) {
        super(p_i50429_1_, p_i50429_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        Type type1 = type.findFieldType("Level");
        OpticFinder opticfinder = DSL.fieldFinder((String)"Level", (Type)type1);
        return this.fixTypeEverywhereTyped("ChunkStatusFix2", type, this.getOutputSchema().getType(References.R_4764_Y), p_219823_1_ -> p_219823_1_.updateTyped(opticfinder, p_219824_0_ -> {
            String s1;
            Dynamic dynamic = (Dynamic)p_219824_0_.get(DSL.remainderFinder());
            String s = dynamic.get("Status").asString("empty");
            return Objects.equals(s, s1 = n_1700_B.getOrDefault(s, "empty")) ? p_219824_0_ : p_219824_0_.set(DSL.remainderFinder(), (Object)dynamic.set("Status", dynamic.createString(s1)));
        }));
    }
}


