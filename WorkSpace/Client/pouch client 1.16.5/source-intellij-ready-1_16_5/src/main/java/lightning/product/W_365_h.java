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
 *  com.mojang.datafixers.types.templates.List$ListType
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
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
import com.mojang.datafixers.types.templates.List;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.List;
import java.util.stream.LongStream;
import lightning.product.References;
import lightning.product.u_530_F;

public class W_365_h
extends DataFix {
    public W_365_h(Schema p_i231446_1_) {
        super(p_i231446_1_, false);
    }

    protected TypeRewriteRule makeRule() {
        Type type = this.getInputSchema().getType(References.R_4764_Y);
        Type type1 = type.findFieldType("Level");
        OpticFinder opticfinder = DSL.fieldFinder((String)"Level", (Type)type1);
        OpticFinder opticfinder1 = opticfinder.type().findField("Sections");
        Type type2 = ((List.ListType)opticfinder1.type()).getElement();
        OpticFinder opticfinder2 = DSL.typeFinder((Type)type2);
        Type type3 = DSL.named((String)References.P_4830_p.typeName(), (Type)DSL.remainderType());
        OpticFinder opticfinder3 = DSL.fieldFinder((String)"Palette", (Type)DSL.list((Type)type3));
        return this.fixTypeEverywhereTyped("BitStorageAlignFix", type, this.getOutputSchema().getType(References.R_4764_Y), p_233088_5_ -> p_233088_5_.updateTyped(opticfinder, p_233099_4_ -> this.n_1700_B(W_365_h.n_1700_B(opticfinder1, opticfinder2, opticfinder3, p_233099_4_))));
    }

    private Typed<?> n_1700_B(Typed<?> p_233092_1_) {
        return p_233092_1_.update(DSL.remainderFinder(), p_233093_0_ -> p_233093_0_.update("Heightmaps", p_233096_1_ -> p_233096_1_.updateMapValues(p_233095_1_ -> p_233095_1_.mapSecond(p_233100_1_ -> W_365_h.n_1700_B(p_233093_0_, p_233100_1_, 256, 9)))));
    }

    private static Typed<?> n_1700_B(OpticFinder<?> p_233089_0_, OpticFinder<?> p_233089_1_, OpticFinder<List<Pair<String, Dynamic<?>>>> p_233089_2_, Typed<?> p_233089_3_) {
        return p_233089_3_.updateTyped(p_233089_0_, p_233090_2_ -> p_233090_2_.updateTyped(p_233089_1_, p_233091_1_ -> {
            int i = p_233091_1_.getOptional(p_233089_2_).map(p_233098_0_ -> Math.max(4, DataFixUtils.ceillog2((int)p_233098_0_.size()))).orElse(0);
            return i != 0 && !u_530_F.G_564_y(i) ? p_233091_1_.update(DSL.remainderFinder(), p_233087_1_ -> p_233087_1_.update("BlockStates", p_233094_2_ -> W_365_h.n_1700_B(p_233087_1_, p_233094_2_, 4096, i))) : p_233091_1_;
        }));
    }

    private static Dynamic<?> n_1700_B(Dynamic<?> p_233097_0_, Dynamic<?> p_233097_1_, int p_233097_2_, int p_233097_3_) {
        long[] along = p_233097_1_.asLongStream().toArray();
        long[] along1 = W_365_h.n_1700_B(p_233097_2_, p_233097_3_, along);
        return p_233097_0_.createLongList(LongStream.of(along1));
    }

    public static long[] n_1700_B(int p_233086_0_, int p_233086_1_, long[] p_233086_2_) {
        int i = p_233086_2_.length;
        if (i == 0) {
            return p_233086_2_;
        }
        long j = (1L << p_233086_1_) - 1L;
        int k = 64 / p_233086_1_;
        int l = (p_233086_0_ + k - 1) / k;
        long[] along = new long[l];
        int i1 = 0;
        int j1 = 0;
        long k1 = 0L;
        int l1 = 0;
        long i2 = p_233086_2_[0];
        long j2 = i > 1 ? p_233086_2_[1] : 0L;
        for (int k2 = 0; k2 < p_233086_0_; ++k2) {
            long l3;
            int l2 = k2 * p_233086_1_;
            int i3 = l2 >> 6;
            int j3 = (k2 + 1) * p_233086_1_ - 1 >> 6;
            int k3 = l2 ^ i3 << 6;
            if (i3 != l1) {
                i2 = j2;
                j2 = i3 + 1 < i ? p_233086_2_[i3 + 1] : 0L;
                l1 = i3;
            }
            if (i3 == j3) {
                l3 = i2 >>> k3 & j;
            } else {
                int i4 = 64 - k3;
                l3 = (i2 >>> k3 | j2 << i4) & j;
            }
            int j4 = j1 + p_233086_1_;
            if (j4 >= 64) {
                along[i1++] = k1;
                k1 = l3;
                j1 = p_233086_1_;
                continue;
            }
            k1 |= l3 << j1;
            j1 = j4;
        }
        if (k1 != 0L) {
            along[i1] = k1;
        }
        return along;
    }
}


