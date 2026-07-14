/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.OpticFinder
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.OpticFinder;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.IntStream;
import lightning.product.References;

public class ChunkBiomeFix
extends DataFix {
    public ChunkBiomeFix(Schema p_i225701_1_, boolean p_i225701_2_) {
        super(p_i225701_1_, p_i225701_2_);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        OpticFinder opticfinder = type.findField("Level");
        return this.fixTypeEverywhereTyped("Leaves fix", type, p_226193_1_ -> p_226193_1_.updateTyped(opticfinder, p_226194_0_ -> p_226194_0_.update(DSL.remainderFinder(), p_226192_0_ -> {
            Optional optional = p_226192_0_.get("Biomes").asIntStreamOpt().result();
            if (!optional.isPresent()) {
                return p_226192_0_;
            }
            int[] aint = ((IntStream)optional.get()).toArray();
            int[] aint1 = new int[1024];
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 4; ++j) {
                    int l = (i << 2) + 2;
                    int k = (j << 2) + 2;
                    int i1 = l << 4 | k;
                    aint1[i << 2 | j] = i1 < aint.length ? aint[i1] : -1;
                }
            }
            for (int j1 = 1; j1 < 64; ++j1) {
                System.arraycopy(aint1, 0, aint1, j1 * 16, 16);
            }
            return p_226192_0_.set("Biomes", p_226192_0_.createIntList(Arrays.stream(aint1)));
        })));
    }
}


