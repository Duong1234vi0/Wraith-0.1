/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.WorldGenLevel;
import lightning.product.SpikeConfiguration;
import lightning.product.V_3354_l;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelAccessor;
import lightning.product.Feature;
import lightning.product.IronBarsBlock;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.z_1753_f;

public class SpikeFeature
extends Feature<SpikeConfiguration> {
    private static final LoadingCache<Long, List<n_1700_B>> n_1700_B = CacheBuilder.newBuilder().expireAfterWrite(5L, TimeUnit.MINUTES).build((CacheLoader)new J_1907_R());

    public SpikeFeature(Codec<SpikeConfiguration> p_i231994_1_) {
        super(p_i231994_1_);
    }

    public static List<n_1700_B> n_1700_B(WorldGenLevel p_236356_0_) {
        Random random = new Random(p_236356_0_.n_1700_B());
        long i = random.nextLong() & 0xFFFFL;
        return (List)n_1700_B.getUnchecked((Object)i);
    }

    @Override
    public boolean n_1700_B(WorldGenLevel p_241855_1_, z_1753_f p_241855_2_, Random p_241855_3_, c_1514_x p_241855_4_, SpikeConfiguration p_241855_5_) {
        List<n_1700_B> list = p_241855_5_.R_4764_Y();
        if (list.isEmpty()) {
            list = SpikeFeature.n_1700_B(p_241855_1_);
        }
        for (n_1700_B endspikefeature$endspike : list) {
            if (!endspikefeature$endspike.n_1700_B(p_241855_4_)) continue;
            this.n_1700_B(p_241855_1_, p_241855_3_, p_241855_5_, endspikefeature$endspike);
        }
        return true;
    }

    private void n_1700_B(ServerLevelAccessor worldIn, Random rand, SpikeConfiguration config, n_1700_B spike) {
        int i = spike.R_4764_Y();
        for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(new c_1514_x(spike.n_1700_B() - i, 0, spike.J_1907_R() - i), new c_1514_x(spike.n_1700_B() + i, spike.G_564_y() + 10, spike.J_1907_R() + i))) {
            if (blockpos.distanceSq(spike.n_1700_B(), blockpos.getY(), spike.J_1907_R(), false) <= (double)(i * i + 1) && blockpos.getY() < spike.G_564_y()) {
                this.n_1700_B(worldIn, blockpos, a_3742_W.ClientBootstrap.multiplayerClientSuggestionProvider());
                continue;
            }
            if (blockpos.getY() <= 65) continue;
            this.n_1700_B(worldIn, blockpos, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider());
        }
        if (spike.P_1922_E()) {
            int j1 = -2;
            int k1 = 2;
            int j = 3;
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (int k = -2; k <= 2; ++k) {
                for (int l = -2; l <= 2; ++l) {
                    for (int i1 = 0; i1 <= 3; ++i1) {
                        boolean flag2;
                        boolean flag = u_530_F.n_1700_B(k) == 2;
                        boolean flag1 = u_530_F.n_1700_B(l) == 2;
                        boolean bl = flag2 = i1 == 3;
                        if (!flag && !flag1 && !flag2) continue;
                        boolean flag3 = k == -2 || k == 2 || flag2;
                        boolean flag4 = l == -2 || l == 2 || flag2;
                        K_4074_S blockstate = (K_4074_S)((K_4074_S)((K_4074_S)((K_4074_S)a_3742_W.Z_4720_K.multiplayerClientSuggestionProvider().n_1700_B(IronBarsBlock.P_4830_p, flag3 && l != -2)).n_1700_B(IronBarsBlock.Q_4569_t, flag3 && l != 2)).n_1700_B(IronBarsBlock.M_182_A, flag4 && k != -2)).n_1700_B(IronBarsBlock.h_1847_R, flag4 && k != 2);
                        this.n_1700_B(worldIn, blockpos$mutable.n_1700_B(spike.n_1700_B() + k, spike.G_564_y() + i1, spike.J_1907_R() + l), blockstate);
                    }
                }
            }
        }
        V_3354_l endercrystalentity = t_5_h.w_1457_N.n_1700_B(worldIn.J_1907_R());
        endercrystalentity.n_1700_B(config.G_564_y());
        endercrystalentity.Q_4569_t(config.J_1907_R());
        endercrystalentity.J_1907_R((double)spike.n_1700_B() + 0.5, spike.G_564_y() + 1, (double)spike.J_1907_R() + 0.5, rand.nextFloat() * 360.0f, 0.0f);
        worldIn.a_(endercrystalentity);
        this.n_1700_B(worldIn, new c_1514_x(spike.n_1700_B(), spike.G_564_y(), spike.J_1907_R()), a_3742_W.Z_875_P.multiplayerClientSuggestionProvider());
    }

    public static class n_1700_B {
        public static final Codec<n_1700_B> n_1700_B = RecordCodecBuilder.create(p_236359_0_ -> p_236359_0_.group((App)Codec.INT.fieldOf("centerX").orElse((Object)0).forGetter(p_236363_0_ -> p_236363_0_.J_1907_R), (App)Codec.INT.fieldOf("centerZ").orElse((Object)0).forGetter(p_236362_0_ -> p_236362_0_.R_4764_Y), (App)Codec.INT.fieldOf("radius").orElse((Object)0).forGetter(p_236361_0_ -> p_236361_0_.G_564_y), (App)Codec.INT.fieldOf("height").orElse((Object)0).forGetter(p_236360_0_ -> p_236360_0_.P_1922_E), (App)Codec.BOOL.fieldOf("guarded").orElse((Object)false).forGetter(p_236358_0_ -> p_236358_0_.u_1723_Y)).apply((Applicative)p_236359_0_, n_1700_B::new));
        private final int J_1907_R;
        private final int R_4764_Y;
        private final int G_564_y;
        private final int P_1922_E;
        private final boolean u_1723_Y;
        private final I_4817_s v_4262_N;

        public n_1700_B(int centerXIn, int centerZIn, int radiusIn, int heightIn, boolean guardedIn) {
            this.J_1907_R = centerXIn;
            this.R_4764_Y = centerZIn;
            this.G_564_y = radiusIn;
            this.P_1922_E = heightIn;
            this.u_1723_Y = guardedIn;
            this.v_4262_N = new I_4817_s(centerXIn - radiusIn, 0.0, centerZIn - radiusIn, centerXIn + radiusIn, 256.0, centerZIn + radiusIn);
        }

        public boolean n_1700_B(c_1514_x pos) {
            return pos.getX() >> 4 == this.J_1907_R >> 4 && pos.getZ() >> 4 == this.R_4764_Y >> 4;
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

        public int G_564_y() {
            return this.P_1922_E;
        }

        public boolean P_1922_E() {
            return this.u_1723_Y;
        }

        public I_4817_s u_1723_Y() {
            return this.v_4262_N;
        }
    }

    static class J_1907_R
    extends CacheLoader<Long, List<n_1700_B>> {
        private J_1907_R() {
        }

        public List<n_1700_B> n_1700_B(Long p_load_1_) {
            List list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
            Collections.shuffle(list, new Random(p_load_1_));
            ArrayList list1 = Lists.newArrayList();
            for (int i = 0; i < 10; ++i) {
                int j = u_530_F.R_4764_Y(42.0 * Math.cos(2.0 * (-Math.PI + 0.3141592653589793 * (double)i)));
                int k = u_530_F.R_4764_Y(42.0 * Math.sin(2.0 * (-Math.PI + 0.3141592653589793 * (double)i)));
                int l = (Integer)list.get(i);
                int i1 = 2 + l / 3;
                int j1 = 76 + l * 3;
                boolean flag = l == 1 || l == 2;
                list1.add(new n_1700_B(j, k, i1, j1, flag));
            }
            return list1;
        }

        public /* synthetic */ Object load(Object object) throws Exception {
            return this.n_1700_B((Long)object);
        }
    }
}



