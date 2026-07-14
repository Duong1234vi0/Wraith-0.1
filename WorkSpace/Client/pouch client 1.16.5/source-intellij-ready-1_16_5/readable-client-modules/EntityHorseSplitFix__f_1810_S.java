/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import java.util.Objects;
import lightning.product.N_2017_n;
import lightning.product.m_271_V;

public class f_1810_S
extends m_271_V {
    public f_1810_S(Schema outputSchema, boolean changesType) {
        super("EntityHorseSplitFix", outputSchema, changesType);
    }

    @Override
    protected Pair<String, Typed<?>> n_1700_B(String p_209149_1_, Typed<?> p_209149_2_) {
        Dynamic dynamic = (Dynamic)p_209149_2_.get(DSL.remainderFinder());
        if (Objects.equals("EntityHorse", p_209149_1_)) {
            int i = dynamic.get("Type").asInt(0);
            String s = switch (i) {
                default -> "Horse";
                case 1 -> "Donkey";
                case 2 -> "Mule";
                case 3 -> "ZombieHorse";
                case 4 -> "SkeletonHorse";
            };
            dynamic.remove("Type");
            Type type = (Type)this.getOutputSchema().findChoiceType(N_2017_n.M_182_A).types().get(s);
            return Pair.of((Object)s, (Object)((Typed)((Pair)p_209149_2_.write().flatMap(arg_0 -> ((Type)type).readTyped(arg_0)).result().orElseThrow(() -> new IllegalStateException("Could not parse the new horse"))).getFirst()));
        }
        return Pair.of((Object)p_209149_1_, p_209149_2_);
    }
}

