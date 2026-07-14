/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Dynamic
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Dynamic;
import lightning.product.m_271_V;

public abstract class m_2444_z
extends m_271_V {
    public m_2444_z(String name, Schema outputSchema, boolean changesType) {
        super(name, outputSchema, changesType);
    }

    @Override
    protected Pair<String, Typed<?>> n_1700_B(String p_209149_1_, Typed<?> p_209149_2_) {
        Pair<String, Dynamic<?>> pair = this.n_1700_B(p_209149_1_, (Dynamic)p_209149_2_.getOrCreate(DSL.remainderFinder()));
        return Pair.of((Object)((String)pair.getFirst()), (Object)p_209149_2_.set(DSL.remainderFinder(), (Object)((Dynamic)pair.getSecond())));
    }

    protected abstract Pair<String, Dynamic<?>> n_1700_B(String var1, Dynamic<?> var2);
}

