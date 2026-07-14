/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.DataFixUtils
 *  com.mojang.datafixers.TypeRewriteRule
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.TypeRewriteRule;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import java.util.Optional;
import java.util.stream.Stream;
import lightning.product.References;

public class MobSpawnerEntityIdentifiersFix
extends DataFix {
    public MobSpawnerEntityIdentifiersFix(Schema outputSchema, boolean changesType) {
        super(outputSchema, changesType);
    }

    private Dynamic<?> n_1700_B(Dynamic<?> p_209659_1_) {
        Optional optional1;
        if (!"MobSpawner".equals(p_209659_1_.get("id").asString(""))) {
            return p_209659_1_;
        }
        Optional optional = p_209659_1_.get("EntityId").asString().result();
        if (optional.isPresent()) {
            Dynamic dynamic = (Dynamic)DataFixUtils.orElse((Optional)p_209659_1_.get("SpawnData").result(), (Object)p_209659_1_.emptyMap());
            dynamic = dynamic.set("id", dynamic.createString(((String)optional.get()).isEmpty() ? "Pig" : (String)optional.get()));
            p_209659_1_ = p_209659_1_.set("SpawnData", dynamic);
            p_209659_1_ = p_209659_1_.remove("EntityId");
        }
        if ((optional1 = p_209659_1_.get("SpawnPotentials").asStreamOpt().result()).isPresent()) {
            p_209659_1_ = p_209659_1_.set("SpawnPotentials", p_209659_1_.createList(((Stream)optional1.get()).map(p_209657_0_ -> {
                Optional optional2 = p_209657_0_.get("Type").asString().result();
                if (optional2.isPresent()) {
                    Dynamic dynamic1 = ((Dynamic)DataFixUtils.orElse((Optional)p_209657_0_.get("Properties").result(), (Object)p_209657_0_.emptyMap())).set("id", p_209657_0_.createString((String)optional2.get()));
                    return p_209657_0_.set("Entity", dynamic1).remove("Type").remove("Properties");
                }
                return p_209657_0_;
            })));
        }
        return p_209659_1_;
    }

    public TypeRewriteRule makeRule() {
        Type type = this.getOutputSchema().getType(References.w_1457_N);
        return this.fixTypeEverywhereTyped("MobSpawnerEntityIdentifiersFix", this.getInputSchema().getType(References.w_1457_N), type, p_206369_2_ -> {
            Dynamic dynamic = (Dynamic)p_206369_2_.get(DSL.remainderFinder());
            DataResult dataresult = type.readTyped(this.n_1700_B(dynamic = dynamic.set("id", dynamic.createString("MobSpawner"))));
            return !dataresult.result().isPresent() ? p_206369_2_ : (Typed)((Pair)dataresult.result().get()).getFirst();
        });
    }
}


