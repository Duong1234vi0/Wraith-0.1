/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.base.Splitter
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.serialization.Dynamic
 *  org.apache.commons.lang3.math.NumberUtils
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Splitter;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.serialization.Dynamic;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lightning.product.References;
import lightning.product.o_4006_R;
import lightning.product.BlockStateData;
import org.apache.commons.lang3.math.NumberUtils;

public class d_4115_H
extends DataFix {
    private static final Splitter n_1700_B = Splitter.on((char)';').limit(5);
    private static final Splitter J_1907_R = Splitter.on((char)',');
    private static final Splitter R_4764_Y = Splitter.on((char)'x').limit(2);
    private static final Splitter G_564_y = Splitter.on((char)'*').limit(2);
    private static final Splitter P_1922_E = Splitter.on((char)':').limit(3);

    public d_4115_H(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        return this.fixTypeEverywhereTyped("LevelFlatGeneratorInfoFix", this.getInputSchema().getType(References.n_1700_B), p_207414_1_ -> p_207414_1_.update(DSL.remainderFinder(), this::n_1700_B));
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_209636_1_) {
        return p_209636_1_.get("generatorName").asString("").equalsIgnoreCase("flat") ? p_209636_1_.update("generatorOptions", p_209634_1_ -> (Dynamic)DataFixUtils.orElse((Optional)p_209634_1_.asString().map(this::n_1700_B).map(s -> p_209634_1_.createString(s)).result(), (Object)p_209634_1_)) : p_209636_1_;
    }

    @VisibleForTesting
    String n_1700_B(String p_199180_1_) {
        String s1;
        int i;
        if (p_199180_1_.isEmpty()) {
            return "minecraft:bedrock,2*minecraft:dirt,minecraft:grass_block;1;village";
        }
        Iterator iterator = n_1700_B.split((CharSequence)p_199180_1_).iterator();
        String s = (String)iterator.next();
        if (iterator.hasNext()) {
            i = NumberUtils.toInt((String)s, (int)0);
            s1 = (String)iterator.next();
        } else {
            i = 0;
            s1 = s;
        }
        if (i >= 0 && i <= 3) {
            StringBuilder stringbuilder = new StringBuilder();
            Splitter splitter = i < 3 ? R_4764_Y : G_564_y;
            stringbuilder.append(StreamSupport.stream(J_1907_R.split((CharSequence)s1).spliterator(), false).map(p_206368_2_ -> {
                String s2;
                int j;
                List list = splitter.splitToList((CharSequence)p_206368_2_);
                if (list.size() == 2) {
                    j = NumberUtils.toInt((String)((String)list.get(0)));
                    s2 = (String)list.get(1);
                } else {
                    j = 1;
                    s2 = (String)list.get(0);
                }
                List list1 = P_1922_E.splitToList((CharSequence)s2);
                int k = ((String)list1.get(0)).equals("minecraft") ? 1 : 0;
                String s3 = (String)list1.get(k);
                int l = i == 3 ? o_4006_R.n_1700_B("minecraft:" + s3) : NumberUtils.toInt((String)s3, (int)0);
                int i1 = k + 1;
                int j1 = list1.size() > i1 ? NumberUtils.toInt((String)((String)list1.get(i1)), (int)0) : 0;
                return (String)(j == 1 ? "" : j + "*") + BlockStateData.J_1907_R(l << 4 | j1).get("Name").asString("");
            }).collect(Collectors.joining(",")));
            while (iterator.hasNext()) {
                stringbuilder.append(';').append((String)iterator.next());
            }
            return stringbuilder.toString();
        }
        return "minecraft:bedrock,2*minecraft:dirt,minecraft:grass_block;1;village";
    }
}


