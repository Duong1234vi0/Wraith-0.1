/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.types.templates.TaggedChoice$TaggedChoiceType
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TaggedChoice;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import java.util.List;
import java.util.Objects;
import lightning.product.References;

public class EntityMinecartIdentifiersFix
extends DataFix {
    private static final List<String> n_1700_B = Lists.newArrayList((Object[])new String[]{"MinecartRideable", "MinecartChest", "MinecartFurnace"});

    public EntityMinecartIdentifiersFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    public TypeRewriteRule makeRule() {
        TaggedChoice.TaggedChoiceType taggedchoicetype = this.getInputSchema().findChoiceType(References.M_182_A);
        TaggedChoice.TaggedChoiceType taggedchoicetype1 = this.getOutputSchema().findChoiceType(References.M_182_A);
        return this.fixTypeEverywhere("EntityMinecartIdentifiersFix", (Type)taggedchoicetype, (Type)taggedchoicetype1, p_209746_2_ -> p_206328_3_ -> {
            if (!Objects.equals(p_206328_3_.getFirst(), "Minecart")) {
                return p_206328_3_;
            }
            Typed typed = (Typed)taggedchoicetype.point(p_209746_2_, (Object)"Minecart", p_206328_3_.getSecond()).orElseThrow(IllegalStateException::new);
            Dynamic dynamic = (Dynamic)typed.getOrCreate(DSL.remainderFinder());
            int i = dynamic.get("Type").asInt(0);
            String s = i > 0 && i < n_1700_B.size() ? n_1700_B.get(i) : "MinecartRideable";
            return Pair.of((Object)s, (Object)((DataResult)typed.write().map(p_233177_2_ -> ((Type)taggedchoicetype1.types().get(s)).read(p_233177_2_)).result().orElseThrow(() -> new IllegalStateException("Could not read the new minecart."))));
        });
    }
}


