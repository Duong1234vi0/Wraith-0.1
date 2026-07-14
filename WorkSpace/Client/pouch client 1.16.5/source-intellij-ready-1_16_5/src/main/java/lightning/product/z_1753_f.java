/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.StrongholdConfiguration;
import lightning.product.DebugPackets;
import lightning.product.BlockGetter;
import lightning.product.StructureFeature;
import lightning.product.BiomeManager;
import lightning.product.J_3017_d;
import lightning.product.K_3381_i;
import lightning.product.StructureSettings;
import lightning.product.ConfiguredWorldCarver;
import lightning.product.WorldGenLevel;
import lightning.product.T_3975_o;
import lightning.product.V_3137_a;
import lightning.product.V_4739_Y;
import lightning.product.W_2121_d;
import lightning.product.MobSpawnSettings;
import lightning.product.Y_1387_d;
import lightning.product.StructureFeatures;
import lightning.product.Z_749_F;
import lightning.product.FeatureAccess;
import lightning.product.b_2085_h;
import lightning.product.c_1108_W;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.BiomeGenerationSettings;
import lightning.product.StructureStart;
import lightning.product.ChunkAccess;
import lightning.product.e_3591_l;
import lightning.product.WorldgenRandom;
import lightning.product.ConfiguredStructureFeature;
import lightning.product.k_594_Q;
import lightning.product.n_1254_X;
import lightning.product.n_3236_c;
import lightning.product.BiomeSource;
import lightning.product.ReportedException;
import lightning.product.n_395_H;
import lightning.product.DebugLevelSource;
import lightning.product.CrashReportCategory;
import lightning.product.r_4097_j;
import lightning.product.LevelAccessor;
import lightning.product.z_2963_s;

public abstract class z_1753_f {
    public static final Codec<z_1753_f> n_1700_B = V_3137_a.M_2677_i.dispatchStable(z_1753_f::n_1700_B, Function.identity());
    protected final BiomeSource J_1907_R;
    protected final BiomeSource R_4764_Y;
    private final StructureSettings G_564_y;
    private final long P_1922_E;
    private final List<Y_1387_d> u_1723_Y = Lists.newArrayList();

    public z_1753_f(BiomeSource p_i231888_1_, StructureSettings p_i231888_2_) {
        this(p_i231888_1_, p_i231888_1_, p_i231888_2_, 0L);
    }

    public z_1753_f(BiomeSource p_i231887_1_, BiomeSource p_i231887_2_, StructureSettings p_i231887_3_, long p_i231887_4_) {
        this.J_1907_R = p_i231887_1_;
        this.R_4764_Y = p_i231887_2_;
        this.G_564_y = p_i231887_3_;
        this.P_1922_E = p_i231887_4_;
    }

    private void v_4262_N() {
        StrongholdConfiguration structurespreadsettings;
        if (this.u_1723_Y.isEmpty() && (structurespreadsettings = this.G_564_y.J_1907_R()) != null && structurespreadsettings.R_4764_Y() != 0) {
            ArrayList list = Lists.newArrayList();
            for (k_594_Q biome : this.J_1907_R.J_1907_R()) {
                if (!biome.P_1922_E().n_1700_B(StructureFeature.u_2550_I)) continue;
                list.add(biome);
            }
            int k1 = structurespreadsettings.n_1700_B();
            int l1 = structurespreadsettings.R_4764_Y();
            int i = structurespreadsettings.J_1907_R();
            Random random = new Random();
            random.setSeed(this.P_1922_E);
            double d0 = random.nextDouble() * Math.PI * 2.0;
            int j = 0;
            int k = 0;
            for (int l = 0; l < l1; ++l) {
                double d1 = (double)(4 * k1 + k1 * k * 6) + (random.nextDouble() - 0.5) * (double)k1 * 2.5;
                int i1 = (int)Math.round(Math.cos(d0) * d1);
                int j1 = (int)Math.round(Math.sin(d0) * d1);
                c_1514_x blockpos = this.J_1907_R.n_1700_B((i1 << 4) + 8, 0, (j1 << 4) + 8, 112, list::contains, random);
                if (blockpos != null) {
                    i1 = blockpos.getX() >> 4;
                    j1 = blockpos.getZ() >> 4;
                }
                this.u_1723_Y.add(new Y_1387_d(i1, j1));
                d0 += Math.PI * 2 / (double)i;
                if (++j != i) continue;
                j = 0;
                i += 2 * i / (++k + 1);
                i = Math.min(i, l1 - l);
                d0 += random.nextDouble() * Math.PI * 2.0;
            }
        }
    }

    protected abstract Codec<? extends z_1753_f> n_1700_B();

    public abstract z_1753_f n_1700_B(long var1);

    public void n_1700_B(V_3137_a<k_594_Q> p_242706_1_, ChunkAccess p_242706_2_) {
        Y_1387_d chunkpos = p_242706_2_.getPos();
        ((n_1254_X)p_242706_2_).n_1700_B(new c_1108_W(p_242706_1_, chunkpos, this.R_4764_Y));
    }

    public void n_1700_B(long p_230350_1_, BiomeManager p_230350_3_, ChunkAccess p_230350_4_, T_3975_o.n_1700_B p_230350_5_) {
        BiomeManager biomemanager = p_230350_3_.n_1700_B(this.J_1907_R);
        WorldgenRandom sharedseedrandom = new WorldgenRandom();
        int i = 8;
        Y_1387_d chunkpos = p_230350_4_.getPos();
        int j = chunkpos.J_1907_R;
        int k = chunkpos.R_4764_Y;
        BiomeGenerationSettings biomegenerationsettings = this.J_1907_R.G_564_y(chunkpos.J_1907_R << 2, 0, chunkpos.R_4764_Y << 2).P_1922_E();
        BitSet bitset = ((n_1254_X)p_230350_4_).J_1907_R(p_230350_5_);
        for (int l = j - 8; l <= j + 8; ++l) {
            for (int i1 = k - 8; i1 <= k + 8; ++i1) {
                List<Supplier<ConfiguredWorldCarver<?>>> list = biomegenerationsettings.n_1700_B(p_230350_5_);
                ListIterator<Supplier<ConfiguredWorldCarver<?>>> listiterator = list.listIterator();
                while (listiterator.hasNext()) {
                    int j1 = listiterator.nextIndex();
                    ConfiguredWorldCarver<?> configuredcarver = listiterator.next().get();
                    sharedseedrandom.R_4764_Y(p_230350_1_ + (long)j1, l, i1);
                    if (!configuredcarver.n_1700_B(sharedseedrandom, l, i1)) continue;
                    configuredcarver.n_1700_B(p_230350_4_, biomemanager::n_1700_B, sharedseedrandom, this.u_1723_Y(), l, i1, j, k, bitset);
                }
            }
        }
    }

    @Nullable
    public c_1514_x n_1700_B(e_3591_l p_235956_1_, StructureFeature<?> p_235956_2_, c_1514_x p_235956_3_, int p_235956_4_, boolean p_235956_5_) {
        if (!this.J_1907_R.n_1700_B(p_235956_2_)) {
            return null;
        }
        if (p_235956_2_ == StructureFeature.u_2550_I) {
            this.v_4262_N();
            c_1514_x blockpos = null;
            double d0 = Double.MAX_VALUE;
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
            for (Y_1387_d chunkpos : this.u_1723_Y) {
                blockpos$mutable.n_1700_B((chunkpos.J_1907_R << 4) + 8, 32, (chunkpos.R_4764_Y << 4) + 8);
                double d1 = blockpos$mutable.distanceSq(p_235956_3_);
                if (blockpos == null) {
                    blockpos = new c_1514_x(blockpos$mutable);
                    d0 = d1;
                    continue;
                }
                if (!(d1 < d0)) continue;
                blockpos = new c_1514_x(blockpos$mutable);
                d0 = d1;
            }
            return blockpos;
        }
        V_4739_Y structureseparationsettings = this.G_564_y.n_1700_B(p_235956_2_);
        return structureseparationsettings == null ? null : p_235956_2_.n_1700_B(p_235956_1_, p_235956_1_.R_4764_Y(), p_235956_3_, p_235956_4_, p_235956_5_, p_235956_1_.n_1700_B(), structureseparationsettings);
    }

    public void n_1700_B(K_3381_i p_230351_1_, J_3017_d p_230351_2_) {
        int i = p_230351_1_.R_4764_Y();
        int j = p_230351_1_.G_564_y();
        int k = i * 16;
        int l = j * 16;
        c_1514_x blockpos = new c_1514_x(k, 0, l);
        k_594_Q biome = this.J_1907_R.G_564_y((i << 2) + 2, 2, (j << 2) + 2);
        WorldgenRandom sharedseedrandom = new WorldgenRandom();
        long i1 = sharedseedrandom.n_1700_B(p_230351_1_.n_1700_B(), k, l);
        try {
            biome.n_1700_B(p_230351_2_, this, p_230351_1_, i1, sharedseedrandom, blockpos);
        }
        catch (Exception exception) {
            n_3236_c crashreport = n_3236_c.n_1700_B(exception, "Biome decoration");
            crashreport.n_1700_B("Generation").n_1700_B("CenterX", i).n_1700_B("CenterZ", j).n_1700_B("Seed", i1).n_1700_B("Biome", biome);
            throw new ReportedException(crashreport);
        }
    }

    public abstract void n_1700_B(K_3381_i var1, ChunkAccess var2);

    public void n_1700_B(K_3381_i p_230354_1_) {
    }

    public StructureSettings J_1907_R() {
        return this.G_564_y;
    }

    public int R_4764_Y() {
        return 64;
    }

    public BiomeSource G_564_y() {
        return this.R_4764_Y;
    }

    public int P_1922_E() {
        return 256;
    }

    public List<MobSpawnSettings.R_4764_Y> n_1700_B(k_594_Q p_230353_1_, J_3017_d p_230353_2_, Z_749_F p_230353_3_, c_1514_x p_230353_4_) {
        return p_230353_1_.J_1907_R().n_1700_B(p_230353_3_);
    }

    public void n_1700_B(r_4097_j p_242707_1_, J_3017_d p_242707_2_, ChunkAccess p_242707_3_, b_2085_h p_242707_4_, long p_242707_5_) {
        Y_1387_d chunkpos = p_242707_3_.getPos();
        k_594_Q biome = this.J_1907_R.G_564_y((chunkpos.J_1907_R << 2) + 2, 0, (chunkpos.R_4764_Y << 2) + 2);
        this.n_1700_B(StructureFeatures.u_2550_I, p_242707_1_, p_242707_2_, p_242707_3_, p_242707_4_, p_242707_5_, chunkpos, biome);
        for (Supplier<ConfiguredStructureFeature<?, ?>> supplier : biome.P_1922_E().n_1700_B()) {
            this.n_1700_B(supplier.get(), p_242707_1_, p_242707_2_, p_242707_3_, p_242707_4_, p_242707_5_, chunkpos, biome);
        }
    }

    private void n_1700_B(ConfiguredStructureFeature<?, ?> p_242705_1_, r_4097_j p_242705_2_, J_3017_d p_242705_3_, ChunkAccess p_242705_4_, b_2085_h p_242705_5_, long p_242705_6_, Y_1387_d p_242705_8_, k_594_Q p_242705_9_) {
        StructureStart<?> structurestart = p_242705_3_.n_1700_B(SectionPos.n_1700_B(p_242705_4_.getPos(), 0), (StructureFeature<?>)p_242705_1_.G_564_y, p_242705_4_);
        int i = structurestart != null ? structurestart.s_956_w() : 0;
        V_4739_Y structureseparationsettings = this.G_564_y.n_1700_B((StructureFeature<?>)p_242705_1_.G_564_y);
        if (structureseparationsettings != null) {
            StructureStart<?> structurestart1 = p_242705_1_.n_1700_B(p_242705_2_, this, this.J_1907_R, p_242705_5_, p_242705_6_, p_242705_8_, p_242705_9_, i, structureseparationsettings);
            p_242705_3_.n_1700_B(SectionPos.n_1700_B(p_242705_4_.getPos(), 0), (StructureFeature<?>)p_242705_1_.G_564_y, structurestart1, (FeatureAccess)p_242705_4_);
        }
    }

    public void n_1700_B(WorldGenLevel p_235953_1_, J_3017_d p_235953_2_, ChunkAccess p_235953_3_) {
        int i = 8;
        int j = p_235953_3_.getPos().J_1907_R;
        int k = p_235953_3_.getPos().R_4764_Y;
        int l = j << 4;
        int i1 = k << 4;
        SectionPos sectionpos = SectionPos.n_1700_B(p_235953_3_.getPos(), 0);
        for (int j1 = j - 8; j1 <= j + 8; ++j1) {
            for (int k1 = k - 8; k1 <= k + 8; ++k1) {
                long l1 = Y_1387_d.n_1700_B(j1, k1);
                for (StructureStart<?> structurestart : p_235953_1_.P_1922_E(j1, k1).getStructureStarts().values()) {
                    try {
                        if (structurestart == StructureStart.n_1700_B || !structurestart.R_4764_Y().n_1700_B(l, i1, l + 15, i1 + 15)) continue;
                        p_235953_2_.n_1700_B(sectionpos, structurestart.M_588_G(), l1, (FeatureAccess)p_235953_3_);
                        DebugPackets.n_1700_B(p_235953_1_, structurestart);
                    }
                    catch (Exception exception) {
                        n_3236_c crashreport = n_3236_c.n_1700_B(exception, "Generating structure reference");
                        CrashReportCategory crashreportcategory = crashreport.n_1700_B("Structure");
                        crashreportcategory.n_1700_B("Id", () -> V_3137_a.M_1641_O.J_1907_R(structurestart.M_588_G()).toString());
                        crashreportcategory.n_1700_B("Name", () -> structurestart.M_588_G().v_4262_N());
                        crashreportcategory.n_1700_B("Class", () -> structurestart.M_588_G().getClass().getCanonicalName());
                        throw new ReportedException(crashreport);
                    }
                }
            }
        }
    }

    public abstract void n_1700_B(LevelAccessor var1, J_3017_d var2, ChunkAccess var3);

    public int u_1723_Y() {
        return 63;
    }

    public abstract int n_1700_B(int var1, int var2, z_2963_s.n_1700_B var3);

    public abstract BlockGetter n_1700_B(int var1, int var2);

    public int J_1907_R(int x, int z, z_2963_s.n_1700_B heightmapType) {
        return this.n_1700_B(x, z, heightmapType);
    }

    public int R_4764_Y(int x, int z, z_2963_s.n_1700_B heightmapType) {
        return this.n_1700_B(x, z, heightmapType) - 1;
    }

    public boolean n_1700_B(Y_1387_d p_235952_1_) {
        this.v_4262_N();
        return this.u_1723_Y.contains(p_235952_1_);
    }

    static {
        V_3137_a.n_1700_B(V_3137_a.M_2677_i, "noise", n_395_H.G_564_y);
        V_3137_a.n_1700_B(V_3137_a.M_2677_i, "flat", W_2121_d.G_564_y);
        V_3137_a.n_1700_B(V_3137_a.M_2677_i, "debug", DebugLevelSource.G_564_y);
    }
}


