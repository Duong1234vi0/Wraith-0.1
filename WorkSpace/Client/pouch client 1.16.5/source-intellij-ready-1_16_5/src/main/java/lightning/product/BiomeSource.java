/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.CheckerboardColumnBiomeSource;
import lightning.product.D_3718_K;
import lightning.product.TheEndBiomeSource;
import lightning.product.StructureFeature;
import lightning.product.BiomeManager;
import lightning.product.K_4074_S;
import lightning.product.OverworldBiomeSource;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.k_594_Q;
import lightning.product.n_880_h;

public abstract class BiomeSource
implements BiomeManager.n_1700_B {
    public static final Codec<BiomeSource> n_1700_B = V_3137_a.RealmsScreenWithCallback.dispatchStable(BiomeSource::n_1700_B, Function.identity());
    protected final Map<StructureFeature<?>, Boolean> J_1907_R = Maps.newHashMap();
    protected final Set<K_4074_S> R_4764_Y = Sets.newHashSet();
    protected final List<k_594_Q> G_564_y;

    protected BiomeSource(Stream<Supplier<k_594_Q>> biomes) {
        this((List)biomes.map(Supplier::get).collect(ImmutableList.toImmutableList()));
    }

    protected BiomeSource(List<k_594_Q> biomes) {
        this.G_564_y = biomes;
    }

    protected abstract Codec<? extends BiomeSource> n_1700_B();

    public abstract BiomeSource n_1700_B(long var1);

    public List<k_594_Q> J_1907_R() {
        return this.G_564_y;
    }

    public Set<k_594_Q> n_1700_B(int xIn, int yIn, int zIn, int radius) {
        int i = xIn - radius >> 2;
        int j = yIn - radius >> 2;
        int k = zIn - radius >> 2;
        int l = xIn + radius >> 2;
        int i1 = yIn + radius >> 2;
        int j1 = zIn + radius >> 2;
        int k1 = l - i + 1;
        int l1 = i1 - j + 1;
        int i2 = j1 - k + 1;
        HashSet set = Sets.newHashSet();
        for (int j2 = 0; j2 < i2; ++j2) {
            for (int k2 = 0; k2 < k1; ++k2) {
                for (int l2 = 0; l2 < l1; ++l2) {
                    int i3 = i + k2;
                    int j3 = j + l2;
                    int k3 = k + j2;
                    set.add(this.G_564_y(i3, j3, k3));
                }
            }
        }
        return set;
    }

    @Nullable
    public c_1514_x n_1700_B(int xIn, int yIn, int zIn, int radiusIn, Predicate<k_594_Q> biomesIn, Random randIn) {
        return this.n_1700_B(xIn, yIn, zIn, radiusIn, 1, biomesIn, randIn, false);
    }

    @Nullable
    public c_1514_x n_1700_B(int x, int y, int z, int radius, int increment, Predicate<k_594_Q> biomes, Random rand, boolean findClosest) {
        int j1;
        int i = x >> 2;
        int j = z >> 2;
        int k = radius >> 2;
        int l = y >> 2;
        c_1514_x blockpos = null;
        int i1 = 0;
        for (int k1 = j1 = findClosest ? 0 : k; k1 <= k; k1 += increment) {
            for (int l1 = -k1; l1 <= k1; l1 += increment) {
                boolean flag = Math.abs(l1) == k1;
                for (int i2 = -k1; i2 <= k1; i2 += increment) {
                    int j2;
                    int k2;
                    if (findClosest) {
                        boolean flag1;
                        boolean bl = flag1 = Math.abs(i2) == k1;
                        if (!flag1 && !flag) continue;
                    }
                    if (!biomes.test(this.G_564_y(k2 = i + i2, l, j2 = j + l1))) continue;
                    if (blockpos == null || rand.nextInt(i1 + 1) == 0) {
                        blockpos = new c_1514_x(k2 << 2, y, j2 << 2);
                        if (findClosest) {
                            return blockpos;
                        }
                    }
                    ++i1;
                }
            }
        }
        return blockpos;
    }

    public boolean n_1700_B(StructureFeature<?> structureIn) {
        return this.J_1907_R.computeIfAbsent(structureIn, structure -> this.G_564_y.stream().anyMatch(biome -> biome.P_1922_E().n_1700_B((StructureFeature<?>)structure)));
    }

    public Set<K_4074_S> R_4764_Y() {
        if (this.R_4764_Y.isEmpty()) {
            for (k_594_Q biome : this.G_564_y) {
                this.R_4764_Y.add(biome.P_1922_E().P_1922_E().n_1700_B());
            }
        }
        return this.R_4764_Y;
    }

    static {
        V_3137_a.n_1700_B(V_3137_a.RealmsScreenWithCallback, "fixed", D_3718_K.P_1922_E);
        V_3137_a.n_1700_B(V_3137_a.RealmsScreenWithCallback, "multi_noise", n_880_h.u_1723_Y);
        V_3137_a.n_1700_B(V_3137_a.RealmsScreenWithCallback, "checkerboard", CheckerboardColumnBiomeSource.P_1922_E);
        V_3137_a.n_1700_B(V_3137_a.RealmsScreenWithCallback, "vanilla_layered", OverworldBiomeSource.P_1922_E);
        V_3137_a.n_1700_B(V_3137_a.RealmsScreenWithCallback, "the_end", TheEndBiomeSource.P_1922_E);
    }
}


