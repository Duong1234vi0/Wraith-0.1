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
import java.util.List;
import java.util.function.Supplier;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;

public class CheckerboardColumnBiomeSource
extends BiomeSource {
    public static final Codec<CheckerboardColumnBiomeSource> P_1922_E = RecordCodecBuilder.create(checkerProviderCodecInstance -> checkerProviderCodecInstance.group((App)k_594_Q.P_1922_E.fieldOf("biomes").forGetter(checkerProvider -> checkerProvider.u_1723_Y), (App)Codec.intRange((int)0, (int)62).fieldOf("scale").orElse((Object)2).forGetter(checkerProvider -> checkerProvider.w_1484_f)).apply((Applicative)checkerProviderCodecInstance, CheckerboardColumnBiomeSource::new));
    private final List<Supplier<k_594_Q>> u_1723_Y;
    private final int v_4262_N;
    private final int w_1484_f;

    public CheckerboardColumnBiomeSource(List<Supplier<k_594_Q>> biomes, int biomeScale) {
        super(biomes.stream());
        this.u_1723_Y = biomes;
        this.v_4262_N = biomeScale + 2;
        this.w_1484_f = biomeScale;
    }

    @Override
    protected Codec<? extends BiomeSource> n_1700_B() {
        return P_1922_E;
    }

    @Override
    public BiomeSource n_1700_B(long seed) {
        return this;
    }

    @Override
    public k_594_Q G_564_y(int x, int y, int z) {
        return this.u_1723_Y.get(Math.floorMod((x >> this.v_4262_N) + (z >> this.v_4262_N), this.u_1723_Y.size())).get();
    }
}


