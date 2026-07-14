/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.function.Function;

public class V_4739_Y {
    public static final Codec<V_4739_Y> n_1700_B = RecordCodecBuilder.create(p_236669_0_ -> p_236669_0_.group((App)Codec.intRange((int)0, (int)4096).fieldOf("spacing").forGetter(p_236675_0_ -> p_236675_0_.J_1907_R), (App)Codec.intRange((int)0, (int)4096).fieldOf("separation").forGetter(p_236674_0_ -> p_236674_0_.R_4764_Y), (App)Codec.intRange((int)0, (int)Integer.MAX_VALUE).fieldOf("salt").forGetter(p_236672_0_ -> p_236672_0_.G_564_y)).apply((Applicative)p_236669_0_, V_4739_Y::new)).comapFlatMap(p_236670_0_ -> p_236670_0_.J_1907_R <= p_236670_0_.R_4764_Y ? DataResult.error((String)"Spacing has to be smaller than separation") : DataResult.success((Object)p_236670_0_), Function.identity());
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;

    public V_4739_Y(int p_i232019_1_, int p_i232019_2_, int p_i232019_3_) {
        this.J_1907_R = p_i232019_1_;
        this.R_4764_Y = p_i232019_2_;
        this.G_564_y = p_i232019_3_;
    }

    public int n_1700_B() {
        return this.J_1907_R;
    }

    public int J_1907_R() {
        return this.R_4764_Y;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }
}

