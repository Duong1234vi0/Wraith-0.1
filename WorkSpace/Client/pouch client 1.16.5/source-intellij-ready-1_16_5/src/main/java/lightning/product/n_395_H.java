/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  it.unimi.dsi.fastutil.objects.ObjectList
 *  it.unimi.dsi.fastutil.objects.ObjectListIterator
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import lightning.product.ImprovedNoise;
import lightning.product.TheEndBiomeSource;
import lightning.product.E_3771_B;
import lightning.product.BlockGetter;
import lightning.product.StructureFeature;
import lightning.product.G_156_T;
import lightning.product.PoolElementStructurePiece;
import lightning.product.SimplexNoise;
import lightning.product.PerlinSimplexNoise;
import lightning.product.J_3017_d;
import lightning.product.K_3381_i;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.P_3550_Z;
import lightning.product.NoiseSettings;
import lightning.product.X_2241_P;
import lightning.product.MobSpawnSettings;
import lightning.product.Y_1387_d;
import lightning.product.NoiseColumn;
import lightning.product.Z_749_F;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.StructureStart;
import lightning.product.ChunkAccess;
import lightning.product.WorldgenRandom;
import lightning.product.f_2392_k;
import lightning.product.PerlinNoise;
import lightning.product.j_3341_s;
import lightning.product.k_594_Q;
import lightning.product.SurfaceNoise;
import lightning.product.n_1254_X;
import lightning.product.BiomeSource;
import lightning.product.LevelAccessor;
import lightning.product.u_530_F;
import lightning.product.u_743_i;
import lightning.product.y_3814_I;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public final class n_395_H
extends z_1753_f {
    public static final Codec<n_395_H> G_564_y = RecordCodecBuilder.create(p_236091_0_ -> p_236091_0_.group((App)BiomeSource.n_1700_B.fieldOf("biome_source").forGetter(p_236096_0_ -> p_236096_0_.J_1907_R), (App)Codec.LONG.fieldOf("seed").stable().forGetter(p_236093_0_ -> p_236093_0_.C_2741_M), (App)G_156_T.J_1907_R.fieldOf("settings").forGetter(p_236090_0_ -> p_236090_0_.w_1484_f)).apply((Applicative)p_236091_0_, p_236091_0_.stable(n_395_H::new)));
    private static final float[] t_148_a = j_3341_s.n_1700_B(new float[13824], p_236094_0_ -> {
        for (int i = 0; i < 24; ++i) {
            for (int j = 0; j < 24; ++j) {
                for (int k = 0; k < 24; ++k) {
                    p_236094_0_[i * 24 * 24 + j * 24 + k] = (float)n_395_H.J_1907_R(j - 12, k - 12, i - 12);
                }
            }
        }
    });
    private static final float[] s_956_w = j_3341_s.n_1700_B(new float[25], p_236092_0_ -> {
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                float f;
                p_236092_0_[i + 2 + (j + 2) * 5] = f = 10.0f / u_530_F.R_4764_Y((float)(i * i + j * j) + 0.2f);
            }
        }
    });
    private static final K_4074_S u_2550_I = a_3742_W.n_1700_B.multiplayerClientSuggestionProvider();
    private final int M_588_G;
    private final int P_4830_p;
    private final int h_1847_R;
    private final int Q_4569_t;
    private final int M_182_A;
    protected final WorldgenRandom P_1922_E;
    private final PerlinNoise t_1786_h;
    private final PerlinNoise multiplayerClientSuggestionProvider;
    private final PerlinNoise w_1457_N;
    private final SurfaceNoise Y_601_j;
    private final PerlinNoise Y_259_p;
    @Nullable
    private final SimplexNoise Q_2552_b;
    protected final K_4074_S u_1723_Y;
    protected final K_4074_S v_4262_N;
    private final long C_2741_M;
    protected final Supplier<G_156_T> w_1484_f;
    private final int k_2293_S;

    public n_395_H(BiomeSource p_i241975_1_, long p_i241975_2_, Supplier<G_156_T> p_i241975_4_) {
        this(p_i241975_1_, p_i241975_1_, p_i241975_2_, p_i241975_4_);
    }

    private n_395_H(BiomeSource p_i241976_1_, BiomeSource p_i241976_2_, long p_i241976_3_, Supplier<G_156_T> p_i241976_5_) {
        super(p_i241976_1_, p_i241976_2_, p_i241976_5_.get().n_1700_B(), p_i241976_3_);
        this.C_2741_M = p_i241976_3_;
        G_156_T dimensionsettings = p_i241976_5_.get();
        this.w_1484_f = p_i241976_5_;
        NoiseSettings noisesettings = dimensionsettings.J_1907_R();
        this.k_2293_S = noisesettings.n_1700_B();
        this.M_588_G = noisesettings.u_1723_Y() * 4;
        this.P_4830_p = noisesettings.P_1922_E() * 4;
        this.u_1723_Y = dimensionsettings.R_4764_Y();
        this.v_4262_N = dimensionsettings.G_564_y();
        this.h_1847_R = 16 / this.P_4830_p;
        this.Q_4569_t = noisesettings.n_1700_B() / this.M_588_G;
        this.M_182_A = 16 / this.P_4830_p;
        this.P_1922_E = new WorldgenRandom(p_i241976_3_);
        this.t_1786_h = new PerlinNoise(this.P_1922_E, IntStream.rangeClosed(-15, 0));
        this.multiplayerClientSuggestionProvider = new PerlinNoise(this.P_1922_E, IntStream.rangeClosed(-15, 0));
        this.w_1457_N = new PerlinNoise(this.P_1922_E, IntStream.rangeClosed(-7, 0));
        this.Y_601_j = noisesettings.t_148_a() ? new PerlinSimplexNoise(this.P_1922_E, IntStream.rangeClosed(-3, 0)) : new PerlinNoise(this.P_1922_E, IntStream.rangeClosed(-3, 0));
        this.P_1922_E.n_1700_B(2620);
        this.Y_259_p = new PerlinNoise(this.P_1922_E, IntStream.rangeClosed(-15, 0));
        if (noisesettings.u_2550_I()) {
            WorldgenRandom sharedseedrandom = new WorldgenRandom(p_i241976_3_);
            sharedseedrandom.n_1700_B(17292);
            this.Q_2552_b = new SimplexNoise(sharedseedrandom);
        } else {
            this.Q_2552_b = null;
        }
    }

    @Override
    protected Codec<? extends z_1753_f> n_1700_B() {
        return G_564_y;
    }

    @Override
    public z_1753_f n_1700_B(long p_230349_1_) {
        return new n_395_H(this.J_1907_R.n_1700_B(p_230349_1_), p_230349_1_, this.w_1484_f);
    }

    public boolean n_1700_B(long p_236088_1_, f_2392_k<G_156_T> p_236088_3_) {
        return this.C_2741_M == p_236088_1_ && this.w_1484_f.get().n_1700_B(p_236088_3_);
    }

    private double n_1700_B(int p_222552_1_, int p_222552_2_, int p_222552_3_, double p_222552_4_, double p_222552_6_, double p_222552_8_, double p_222552_10_) {
        double d0 = 0.0;
        double d1 = 0.0;
        double d2 = 0.0;
        boolean flag = true;
        double d3 = 1.0;
        for (int i = 0; i < 16; ++i) {
            ImprovedNoise improvednoisegenerator2;
            ImprovedNoise improvednoisegenerator1;
            double d4 = PerlinNoise.n_1700_B((double)p_222552_1_ * p_222552_4_ * d3);
            double d5 = PerlinNoise.n_1700_B((double)p_222552_2_ * p_222552_6_ * d3);
            double d6 = PerlinNoise.n_1700_B((double)p_222552_3_ * p_222552_4_ * d3);
            double d7 = p_222552_6_ * d3;
            ImprovedNoise improvednoisegenerator = this.t_1786_h.n_1700_B(i);
            if (improvednoisegenerator != null) {
                d0 += improvednoisegenerator.n_1700_B(d4, d5, d6, d7, (double)p_222552_2_ * d7) / d3;
            }
            if ((improvednoisegenerator1 = this.multiplayerClientSuggestionProvider.n_1700_B(i)) != null) {
                d1 += improvednoisegenerator1.n_1700_B(d4, d5, d6, d7, (double)p_222552_2_ * d7) / d3;
            }
            if (i < 8 && (improvednoisegenerator2 = this.w_1457_N.n_1700_B(i)) != null) {
                d2 += improvednoisegenerator2.n_1700_B(PerlinNoise.n_1700_B((double)p_222552_1_ * p_222552_8_ * d3), PerlinNoise.n_1700_B((double)p_222552_2_ * p_222552_10_ * d3), PerlinNoise.n_1700_B((double)p_222552_3_ * p_222552_8_ * d3), p_222552_10_ * d3, (double)p_222552_2_ * p_222552_10_ * d3) / d3;
            }
            d3 /= 2.0;
        }
        return u_530_F.J_1907_R(d0 / 512.0, d1 / 512.0, (d2 / 10.0 + 1.0) / 2.0);
    }

    private double[] J_1907_R(int p_222547_1_, int p_222547_2_) {
        double[] adouble = new double[this.Q_4569_t + 1];
        this.n_1700_B(adouble, p_222547_1_, p_222547_2_);
        return adouble;
    }

    private void n_1700_B(double[] noiseColumn, int noiseX, int noiseZ) {
        double d1;
        double d0;
        NoiseSettings noisesettings = this.w_1484_f.get().J_1907_R();
        if (this.Q_2552_b != null) {
            d0 = TheEndBiomeSource.n_1700_B(this.Q_2552_b, noiseX, noiseZ) - 8.0f;
            d1 = d0 > 0.0 ? 0.25 : 1.0;
        } else {
            float f = 0.0f;
            float f1 = 0.0f;
            float f2 = 0.0f;
            int i = 2;
            int j = this.u_1723_Y();
            float f3 = this.J_1907_R.G_564_y(noiseX, j, noiseZ).w_1484_f();
            for (int k = -2; k <= 2; ++k) {
                for (int l = -2; l <= 2; ++l) {
                    float f7;
                    float f6;
                    k_594_Q biome = this.J_1907_R.G_564_y(noiseX + k, j, noiseZ + l);
                    float f4 = biome.w_1484_f();
                    float f5 = biome.s_956_w();
                    if (noisesettings.M_588_G() && f4 > 0.0f) {
                        f6 = 1.0f + f4 * 2.0f;
                        f7 = 1.0f + f5 * 4.0f;
                    } else {
                        f6 = f4;
                        f7 = f5;
                    }
                    float f8 = f4 > f3 ? 0.5f : 1.0f;
                    float f9 = f8 * s_956_w[k + 2 + (l + 2) * 5] / (f6 + 2.0f);
                    f += f7 * f9;
                    f1 += f6 * f9;
                    f2 += f9;
                }
            }
            float f10 = f1 / f2;
            float f11 = f / f2;
            double d16 = f10 * 0.5f - 0.125f;
            double d18 = f11 * 0.9f + 0.1f;
            d0 = d16 * 0.265625;
            d1 = 96.0 / d18;
        }
        double d12 = 684.412 * noisesettings.J_1907_R().n_1700_B();
        double d13 = 684.412 * noisesettings.J_1907_R().J_1907_R();
        double d14 = d12 / noisesettings.J_1907_R().R_4764_Y();
        double d15 = d13 / noisesettings.J_1907_R().G_564_y();
        double d17 = noisesettings.R_4764_Y().n_1700_B();
        double d19 = noisesettings.R_4764_Y().J_1907_R();
        double d20 = noisesettings.R_4764_Y().R_4764_Y();
        double d21 = noisesettings.G_564_y().n_1700_B();
        double d2 = noisesettings.G_564_y().J_1907_R();
        double d3 = noisesettings.G_564_y().R_4764_Y();
        double d4 = noisesettings.s_956_w() ? this.R_4764_Y(noiseX, noiseZ) : 0.0;
        double d5 = noisesettings.v_4262_N();
        double d6 = noisesettings.w_1484_f();
        for (int i1 = 0; i1 <= this.Q_4569_t; ++i1) {
            double d7 = this.n_1700_B(noiseX, i1, noiseZ, d12, d13, d14, d15);
            double d8 = 1.0 - (double)i1 * 2.0 / (double)this.Q_4569_t + d4;
            double d9 = d8 * d5 + d6;
            double d10 = (d9 + d0) * d1;
            d7 = d10 > 0.0 ? (d7 += d10 * 4.0) : (d7 += d10);
            if (d19 > 0.0) {
                double d11 = ((double)(this.Q_4569_t - i1) - d20) / d19;
                d7 = u_530_F.J_1907_R(d17, d7, d11);
            }
            if (d2 > 0.0) {
                double d22 = ((double)i1 - d3) / d2;
                d7 = u_530_F.J_1907_R(d21, d7, d22);
            }
            noiseColumn[i1] = d7;
        }
    }

    private double R_4764_Y(int p_236095_1_, int p_236095_2_) {
        double d0 = this.Y_259_p.n_1700_B(p_236095_1_ * 200, 10.0, p_236095_2_ * 200, 1.0, 0.0, true);
        double d1 = d0 < 0.0 ? -d0 * 0.3 : d0;
        double d2 = d1 * 24.575625 - 2.0;
        return d2 < 0.0 ? d2 * 0.009486607142857142 : Math.min(d2, 1.0) * 0.006640625;
    }

    @Override
    public int n_1700_B(int x, int z, z_2963_s.n_1700_B heightmapType) {
        return this.n_1700_B(x, z, (K_4074_S[])null, heightmapType.P_1922_E());
    }

    @Override
    public BlockGetter n_1700_B(int p_230348_1_, int p_230348_2_) {
        K_4074_S[] ablockstate = new K_4074_S[this.Q_4569_t * this.M_588_G];
        this.n_1700_B(p_230348_1_, p_230348_2_, ablockstate, (Predicate<K_4074_S>)null);
        return new NoiseColumn(ablockstate);
    }

    private int n_1700_B(int p_236087_1_, int p_236087_2_, @Nullable K_4074_S[] p_236087_3_, @Nullable Predicate<K_4074_S> p_236087_4_) {
        int i = Math.floorDiv(p_236087_1_, this.P_4830_p);
        int j = Math.floorDiv(p_236087_2_, this.P_4830_p);
        int k = Math.floorMod(p_236087_1_, this.P_4830_p);
        int l = Math.floorMod(p_236087_2_, this.P_4830_p);
        double d0 = (double)k / (double)this.P_4830_p;
        double d1 = (double)l / (double)this.P_4830_p;
        double[][] adouble = new double[][]{this.J_1907_R(i, j), this.J_1907_R(i, j + 1), this.J_1907_R(i + 1, j), this.J_1907_R(i + 1, j + 1)};
        for (int i1 = this.Q_4569_t - 1; i1 >= 0; --i1) {
            double d2 = adouble[0][i1];
            double d3 = adouble[1][i1];
            double d4 = adouble[2][i1];
            double d5 = adouble[3][i1];
            double d6 = adouble[0][i1 + 1];
            double d7 = adouble[1][i1 + 1];
            double d8 = adouble[2][i1 + 1];
            double d9 = adouble[3][i1 + 1];
            for (int j1 = this.M_588_G - 1; j1 >= 0; --j1) {
                double d10 = (double)j1 / (double)this.M_588_G;
                double d11 = u_530_F.n_1700_B(d10, d0, d1, d2, d6, d4, d8, d3, d7, d5, d9);
                int k1 = i1 * this.M_588_G + j1;
                K_4074_S blockstate = this.n_1700_B(d11, k1);
                if (p_236087_3_ != null) {
                    p_236087_3_[k1] = blockstate;
                }
                if (p_236087_4_ == null || !p_236087_4_.test(blockstate)) continue;
                return k1 + 1;
            }
        }
        return 0;
    }

    protected K_4074_S n_1700_B(double p_236086_1_, int p_236086_3_) {
        K_4074_S blockstate = p_236086_1_ > 0.0 ? this.u_1723_Y : (p_236086_3_ < this.u_1723_Y() ? this.v_4262_N : u_2550_I);
        return blockstate;
    }

    @Override
    public void n_1700_B(K_3381_i p_225551_1_, ChunkAccess p_225551_2_) {
        Y_1387_d chunkpos = p_225551_2_.getPos();
        int i = chunkpos.J_1907_R;
        int j = chunkpos.R_4764_Y;
        WorldgenRandom sharedseedrandom = new WorldgenRandom();
        sharedseedrandom.n_1700_B(i, j);
        Y_1387_d chunkpos1 = p_225551_2_.getPos();
        int k = chunkpos1.J_1907_R();
        int l = chunkpos1.R_4764_Y();
        double d0 = 0.0625;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int i1 = 0; i1 < 16; ++i1) {
            for (int j1 = 0; j1 < 16; ++j1) {
                int k1 = k + i1;
                int l1 = l + j1;
                int i2 = p_225551_2_.getTopBlockY(z_2963_s.n_1700_B.n_1700_B, i1, j1) + 1;
                double d1 = this.Y_601_j.n_1700_B((double)k1 * 0.0625, (double)l1 * 0.0625, 0.0625, (double)i1 * 0.0625) * 15.0;
                p_225551_1_.P_1922_E(blockpos$mutable.n_1700_B(k + i1, i2, l + j1)).n_1700_B(sharedseedrandom, p_225551_2_, k1, l1, i2, d1, this.u_1723_Y, this.v_4262_N, this.u_1723_Y(), p_225551_1_.n_1700_B());
            }
        }
        this.n_1700_B(p_225551_2_, sharedseedrandom);
    }

    private void n_1700_B(ChunkAccess chunkIn, Random rand) {
        boolean flag1;
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        int i = chunkIn.getPos().J_1907_R();
        int j = chunkIn.getPos().R_4764_Y();
        G_156_T dimensionsettings = this.w_1484_f.get();
        int k = dimensionsettings.u_1723_Y();
        int l = this.k_2293_S - 1 - dimensionsettings.P_1922_E();
        int i1 = 5;
        boolean flag = l + 4 >= 0 && l < this.k_2293_S;
        boolean bl = flag1 = k + 4 >= 0 && k < this.k_2293_S;
        if (flag || flag1) {
            for (c_1514_x blockpos : c_1514_x.getAllInBoxMutable(i, 0, j, i + 15, 0, j + 15)) {
                if (flag) {
                    for (int j1 = 0; j1 < 5; ++j1) {
                        if (j1 > rand.nextInt(5)) continue;
                        chunkIn.setBlockState(blockpos$mutable.n_1700_B(blockpos.getX(), l - j1, blockpos.getZ()), a_3742_W.Z_875_P.multiplayerClientSuggestionProvider(), false);
                    }
                }
                if (!flag1) continue;
                for (int k1 = 4; k1 >= 0; --k1) {
                    if (k1 > rand.nextInt(5)) continue;
                    chunkIn.setBlockState(blockpos$mutable.n_1700_B(blockpos.getX(), k + k1, blockpos.getZ()), a_3742_W.Z_875_P.multiplayerClientSuggestionProvider(), false);
                }
            }
        }
    }

    @Override
    public void n_1700_B(LevelAccessor p_230352_1_, J_3017_d p_230352_2_, ChunkAccess p_230352_3_) {
        ObjectArrayList objectlist = new ObjectArrayList(10);
        ObjectArrayList objectlist1 = new ObjectArrayList(32);
        Y_1387_d chunkpos = p_230352_3_.getPos();
        int i = chunkpos.J_1907_R;
        int j = chunkpos.R_4764_Y;
        int k = i << 4;
        int l = j << 4;
        for (StructureFeature<?> structure : StructureFeature.Y_601_j) {
            p_230352_2_.n_1700_B(SectionPos.n_1700_B(chunkpos, 0), structure).forEach(arg_0 -> n_395_H.n_1700_B(chunkpos, (ObjectList)objectlist, k, l, (ObjectList)objectlist1, arg_0));
        }
        double[][][] adouble = new double[2][this.M_182_A + 1][this.Q_4569_t + 1];
        for (int i5 = 0; i5 < this.M_182_A + 1; ++i5) {
            adouble[0][i5] = new double[this.Q_4569_t + 1];
            this.n_1700_B(adouble[0][i5], i * this.h_1847_R, j * this.M_182_A + i5);
            adouble[1][i5] = new double[this.Q_4569_t + 1];
        }
        n_1254_X chunkprimer = (n_1254_X)p_230352_3_;
        z_2963_s heightmap = chunkprimer.getHeightmap(z_2963_s.n_1700_B.R_4764_Y);
        z_2963_s heightmap1 = chunkprimer.getHeightmap(z_2963_s.n_1700_B.n_1700_B);
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        ObjectListIterator objectlistiterator = objectlist.iterator();
        ObjectListIterator objectlistiterator1 = objectlist1.iterator();
        for (int i1 = 0; i1 < this.h_1847_R; ++i1) {
            for (int j1 = 0; j1 < this.M_182_A + 1; ++j1) {
                this.n_1700_B(adouble[1][j1], i * this.h_1847_R + i1 + 1, j * this.M_182_A + j1);
            }
            for (int j5 = 0; j5 < this.M_182_A; ++j5) {
                P_3550_Z chunksection = chunkprimer.n_1700_B(15);
                chunksection.n_1700_B();
                for (int k1 = this.Q_4569_t - 1; k1 >= 0; --k1) {
                    double d0 = adouble[0][j5][k1];
                    double d1 = adouble[0][j5 + 1][k1];
                    double d2 = adouble[1][j5][k1];
                    double d3 = adouble[1][j5 + 1][k1];
                    double d4 = adouble[0][j5][k1 + 1];
                    double d5 = adouble[0][j5 + 1][k1 + 1];
                    double d6 = adouble[1][j5][k1 + 1];
                    double d7 = adouble[1][j5 + 1][k1 + 1];
                    for (int l1 = this.M_588_G - 1; l1 >= 0; --l1) {
                        int i2 = k1 * this.M_588_G + l1;
                        int j2 = i2 & 0xF;
                        int k2 = i2 >> 4;
                        if (chunksection.v_4262_N() >> 4 != k2) {
                            chunksection.J_1907_R();
                            chunksection = chunkprimer.n_1700_B(k2);
                            chunksection.n_1700_B();
                        }
                        double d8 = (double)l1 / (double)this.M_588_G;
                        double d9 = u_530_F.G_564_y(d8, d0, d4);
                        double d10 = u_530_F.G_564_y(d8, d2, d6);
                        double d11 = u_530_F.G_564_y(d8, d1, d5);
                        double d12 = u_530_F.G_564_y(d8, d3, d7);
                        for (int l2 = 0; l2 < this.P_4830_p; ++l2) {
                            int i3 = k + i1 * this.P_4830_p + l2;
                            int j3 = i3 & 0xF;
                            double d13 = (double)l2 / (double)this.P_4830_p;
                            double d14 = u_530_F.G_564_y(d13, d9, d10);
                            double d15 = u_530_F.G_564_y(d13, d11, d12);
                            for (int k3 = 0; k3 < this.P_4830_p; ++k3) {
                                int k4;
                                int j4;
                                int l3 = l + j5 * this.P_4830_p + k3;
                                int i4 = l3 & 0xF;
                                double d16 = (double)k3 / (double)this.P_4830_p;
                                double d17 = u_530_F.G_564_y(d16, d14, d15);
                                double d18 = u_530_F.n_1700_B(d17 / 200.0, -1.0, 1.0);
                                d18 = d18 / 2.0 - d18 * d18 * d18 / 24.0;
                                while (objectlistiterator.hasNext()) {
                                    E_3771_B structurepiece = (E_3771_B)objectlistiterator.next();
                                    BoundingBox mutableboundingbox = structurepiece.v_4262_N();
                                    j4 = Math.max(0, Math.max(mutableboundingbox.n_1700_B - i3, i3 - mutableboundingbox.G_564_y));
                                    k4 = i2 - (mutableboundingbox.J_1907_R + (structurepiece instanceof PoolElementStructurePiece ? ((PoolElementStructurePiece)structurepiece).G_564_y() : 0));
                                    int l4 = Math.max(0, Math.max(mutableboundingbox.R_4764_Y - l3, l3 - mutableboundingbox.u_1723_Y));
                                    d18 += n_395_H.n_1700_B(j4, k4, l4) * 0.8;
                                }
                                objectlistiterator.back(objectlist.size());
                                while (objectlistiterator1.hasNext()) {
                                    y_3814_I jigsawjunction = (y_3814_I)objectlistiterator1.next();
                                    int k5 = i3 - jigsawjunction.n_1700_B();
                                    j4 = i2 - jigsawjunction.J_1907_R();
                                    k4 = l3 - jigsawjunction.R_4764_Y();
                                    d18 += n_395_H.n_1700_B(k5, j4, k4) * 0.4;
                                }
                                objectlistiterator1.back(objectlist1.size());
                                K_4074_S blockstate = this.n_1700_B(d18, i2);
                                if (blockstate == u_2550_I) continue;
                                if (blockstate.u_1723_Y() != 0) {
                                    blockpos$mutable.n_1700_B(i3, i2, l3);
                                    chunkprimer.n_1700_B(blockpos$mutable);
                                }
                                chunksection.n_1700_B(j3, j2, i4, blockstate, false);
                                heightmap.n_1700_B(j3, i2, i4, blockstate);
                                heightmap1.n_1700_B(j3, i2, i4, blockstate);
                            }
                        }
                    }
                }
                chunksection.J_1907_R();
            }
            double[][] adouble1 = adouble[0];
            adouble[0] = adouble[1];
            adouble[1] = adouble1;
        }
    }

    private static double n_1700_B(int p_222556_0_, int p_222556_1_, int p_222556_2_) {
        int i = p_222556_0_ + 12;
        int j = p_222556_1_ + 12;
        int k = p_222556_2_ + 12;
        if (i >= 0 && i < 24) {
            if (j >= 0 && j < 24) {
                return k >= 0 && k < 24 ? (double)t_148_a[k * 24 * 24 + i * 24 + j] : 0.0;
            }
            return 0.0;
        }
        return 0.0;
    }

    private static double J_1907_R(int p_222554_0_, int p_222554_1_, int p_222554_2_) {
        double d0 = p_222554_0_ * p_222554_0_ + p_222554_2_ * p_222554_2_;
        double d1 = (double)p_222554_1_ + 0.5;
        double d2 = d1 * d1;
        double d3 = Math.pow(Math.E, -(d2 / 16.0 + d0 / 16.0));
        double d4 = -d1 * u_530_F.w_1484_f(d2 / 2.0 + d0 / 2.0) / 2.0;
        return d4 * d3;
    }

    @Override
    public int P_1922_E() {
        return this.k_2293_S;
    }

    @Override
    public int u_1723_Y() {
        return this.w_1484_f.get().v_4262_N();
    }

    @Override
    public List<MobSpawnSettings.R_4764_Y> n_1700_B(k_594_Q p_230353_1_, J_3017_d p_230353_2_, Z_749_F p_230353_3_, c_1514_x p_230353_4_) {
        if (p_230353_2_.n_1700_B(p_230353_4_, true, StructureFeature.s_956_w).P_1922_E()) {
            if (p_230353_3_ == Z_749_F.n_1700_B) {
                return StructureFeature.s_956_w.R_4764_Y();
            }
            if (p_230353_3_ == Z_749_F.J_1907_R) {
                return StructureFeature.s_956_w.w_1484_f();
            }
        }
        if (p_230353_3_ == Z_749_F.n_1700_B) {
            if (p_230353_2_.n_1700_B(p_230353_4_, false, StructureFeature.J_1907_R).P_1922_E()) {
                return StructureFeature.J_1907_R.R_4764_Y();
            }
            if (p_230353_2_.n_1700_B(p_230353_4_, false, StructureFeature.M_588_G).P_1922_E()) {
                return StructureFeature.M_588_G.R_4764_Y();
            }
            if (p_230353_2_.n_1700_B(p_230353_4_, true, StructureFeature.h_1847_R).P_1922_E()) {
                return StructureFeature.h_1847_R.R_4764_Y();
            }
        }
        return super.n_1700_B(p_230353_1_, p_230353_2_, p_230353_3_, p_230353_4_);
    }

    @Override
    public void n_1700_B(K_3381_i p_230354_1_) {
        if (!this.w_1484_f.get().w_1484_f()) {
            int i = p_230354_1_.R_4764_Y();
            int j = p_230354_1_.G_564_y();
            k_594_Q biome = p_230354_1_.P_1922_E(new Y_1387_d(i, j).s_956_w());
            WorldgenRandom sharedseedrandom = new WorldgenRandom();
            sharedseedrandom.n_1700_B(p_230354_1_.n_1700_B(), i << 4, j << 4);
            u_743_i.n_1700_B(p_230354_1_, biome, i, j, sharedseedrandom);
        }
    }

    private static /* synthetic */ void n_1700_B(Y_1387_d chunkpos, ObjectList objectlist, int k, int l, ObjectList objectlist1, StructureStart p_236089_5_) {
        for (E_3771_B structurepiece1 : p_236089_5_.G_564_y()) {
            if (!structurepiece1.n_1700_B(chunkpos, 12)) continue;
            if (structurepiece1 instanceof PoolElementStructurePiece) {
                PoolElementStructurePiece abstractvillagepiece = (PoolElementStructurePiece)structurepiece1;
                X_2241_P.n_1700_B jigsawpattern$placementbehaviour = abstractvillagepiece.J_1907_R().R_4764_Y();
                if (jigsawpattern$placementbehaviour == X_2241_P.n_1700_B.J_1907_R) {
                    objectlist.add((Object)abstractvillagepiece);
                }
                for (y_3814_I jigsawjunction1 : abstractvillagepiece.P_1922_E()) {
                    int l5 = jigsawjunction1.n_1700_B();
                    int i6 = jigsawjunction1.R_4764_Y();
                    if (l5 <= k - 12 || i6 <= l - 12 || l5 >= k + 15 + 12 || i6 >= l + 15 + 12) continue;
                    objectlist1.add((Object)jigsawjunction1);
                }
                continue;
            }
            objectlist.add((Object)structurepiece1);
        }
    }
}


