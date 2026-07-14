/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Sets
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.c_1514_x;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;

public class D_3718_K
extends BiomeSource {
    public static final Codec<D_3718_K> P_1922_E = k_594_Q.G_564_y.fieldOf("biome").xmap(D_3718_K::new, provider -> provider.u_1723_Y).stable().codec();
    private final Supplier<k_594_Q> u_1723_Y;

    public D_3718_K(k_594_Q p_i46709_1_) {
        this(() -> p_i46709_1_);
    }

    public D_3718_K(Supplier<k_594_Q> biome) {
        super((List<k_594_Q>)ImmutableList.of((Object)biome.get()));
        this.u_1723_Y = biome;
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
        return this.u_1723_Y.get();
    }

    @Override
    @Nullable
    public c_1514_x n_1700_B(int x, int y, int z, int radius, int increment, Predicate<k_594_Q> biomes, Random rand, boolean findClosest) {
        if (biomes.test(this.u_1723_Y.get())) {
            return findClosest ? new c_1514_x(x, y, z) : new c_1514_x(x - radius + rand.nextInt(radius * 2 + 1), y, z - radius + rand.nextInt(radius * 2 + 1));
        }
        return null;
    }

    @Override
    public Set<k_594_Q> n_1700_B(int xIn, int yIn, int zIn, int radius) {
        return Sets.newHashSet((Object[])new k_594_Q[]{this.u_1723_Y.get()});
    }
}


