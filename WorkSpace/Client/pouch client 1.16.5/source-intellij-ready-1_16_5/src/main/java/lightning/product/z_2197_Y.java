/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DSL
 *  com.mojang.datafixers.DSL$TypeReference
 *  com.mojang.datafixers.DataFix
 *  com.mojang.datafixers.Typed
 *  com.mojang.datafixers.schemas.Schema
 *  com.mojang.datafixers.types.Type
 *  com.mojang.serialization.Dynamic
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFix;
import com.mojang.datafixers.Typed;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.Type;
import com.mojang.serialization.Dynamic;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class z_2197_Y
extends DataFix {
    protected static final Logger n_1700_B = LogManager.getLogger();
    protected DSL.TypeReference J_1907_R;

    public z_2197_Y(Schema outputSchema, DSL.TypeReference reference) {
        super(outputSchema, false);
        this.J_1907_R = reference;
    }

    protected Typed<?> n_1700_B(Typed<?> p_233053_1_, String p_233053_2_, Function<Dynamic<?>, Dynamic<?>> p_233053_3_) {
        Type type = this.getInputSchema().getChoiceType(this.J_1907_R, p_233053_2_);
        Type type1 = this.getOutputSchema().getChoiceType(this.J_1907_R, p_233053_2_);
        return p_233053_1_.updateTyped(DSL.namedChoice((String)p_233053_2_, (Type)type), type1, p_233061_1_ -> p_233061_1_.update(DSL.remainderFinder(), p_233053_3_));
    }

    protected static Optional<Dynamic<?>> n_1700_B(Dynamic<?> p_233058_0_, String p_233058_1_, String p_233058_2_) {
        return z_2197_Y.n_1700_B(p_233058_0_, p_233058_1_).map(p_233063_3_ -> p_233058_0_.remove(p_233058_1_).set(p_233058_2_, p_233063_3_));
    }

    protected static Optional<Dynamic<?>> J_1907_R(Dynamic<?> p_233062_0_, String p_233062_1_, String p_233062_2_) {
        return p_233062_0_.get(p_233062_1_).result().flatMap(z_2197_Y::n_1700_B).map(p_233059_3_ -> p_233062_0_.remove(p_233062_1_).set(p_233062_2_, p_233059_3_));
    }

    protected static Optional<Dynamic<?>> R_4764_Y(Dynamic<?> p_233064_0_, String p_233064_1_, String p_233064_2_) {
        String s = p_233064_1_ + "Most";
        String s1 = p_233064_1_ + "Least";
        return z_2197_Y.G_564_y(p_233064_0_, s, s1).map(p_233060_4_ -> p_233064_0_.remove(s).remove(s1).set(p_233064_2_, p_233060_4_));
    }

    protected static Optional<Dynamic<?>> n_1700_B(Dynamic<?> p_233057_0_, String p_233057_1_) {
        return p_233057_0_.get(p_233057_1_).result().flatMap(p_233056_1_ -> {
            String s = p_233056_1_.asString((String)null);
            if (s != null) {
                try {
                    UUID uuid = UUID.fromString(s);
                    return z_2197_Y.n_1700_B(p_233057_0_, uuid.getMostSignificantBits(), uuid.getLeastSignificantBits());
                }
                catch (IllegalArgumentException illegalArgumentException) {
                    // empty catch block
                }
            }
            return Optional.empty();
        });
    }

    protected static Optional<Dynamic<?>> n_1700_B(Dynamic<?> p_233054_0_) {
        return z_2197_Y.G_564_y(p_233054_0_, "M", "L");
    }

    protected static Optional<Dynamic<?>> G_564_y(Dynamic<?> p_233065_0_, String p_233065_1_, String p_233065_2_) {
        long i = p_233065_0_.get(p_233065_1_).asLong(0L);
        long j = p_233065_0_.get(p_233065_2_).asLong(0L);
        return i != 0L && j != 0L ? z_2197_Y.n_1700_B(p_233065_0_, i, j) : Optional.empty();
    }

    protected static Optional<Dynamic<?>> n_1700_B(Dynamic<?> p_233055_0_, long p_233055_1_, long p_233055_3_) {
        return Optional.of(p_233055_0_.createIntList(Arrays.stream(new int[]{(int)(p_233055_1_ >> 32), (int)p_233055_1_, (int)(p_233055_3_ >> 32), (int)p_233055_3_})));
    }
}

