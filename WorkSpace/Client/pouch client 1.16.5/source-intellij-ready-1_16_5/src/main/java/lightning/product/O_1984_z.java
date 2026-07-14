/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;

public class O_1984_z<T> {
    private final T n_1700_B;
    private long J_1907_R;

    public O_1984_z(T value, long timeToLive) {
        this.n_1700_B = value;
        this.J_1907_R = timeToLive;
    }

    public void n_1700_B() {
        if (this.G_564_y()) {
            --this.J_1907_R;
        }
    }

    public static <T> O_1984_z<T> n_1700_B(T value) {
        return new O_1984_z<T>(value, Long.MAX_VALUE);
    }

    public static <T> O_1984_z<T> n_1700_B(T value, long timeToLive) {
        return new O_1984_z<T>(value, timeToLive);
    }

    public T J_1907_R() {
        return this.n_1700_B;
    }

    public boolean R_4764_Y() {
        return this.J_1907_R <= 0L;
    }

    public String toString() {
        return this.n_1700_B.toString() + (String)(this.G_564_y() ? " (ttl: " + this.J_1907_R + ")" : "");
    }

    public boolean G_564_y() {
        return this.J_1907_R != Long.MAX_VALUE;
    }

    public static <T> Codec<O_1984_z<T>> n_1700_B(Codec<T> valueCodec) {
        return RecordCodecBuilder.create(builder -> builder.group((App)valueCodec.fieldOf("value").forGetter(memory -> memory.n_1700_B), (App)Codec.LONG.optionalFieldOf("ttl").forGetter(memory -> memory.G_564_y() ? Optional.of(memory.J_1907_R) : Optional.empty())).apply((Applicative)builder, (value, timeToLive) -> new O_1984_z<Object>(value, timeToLive.orElse(Long.MAX_VALUE))));
    }
}

