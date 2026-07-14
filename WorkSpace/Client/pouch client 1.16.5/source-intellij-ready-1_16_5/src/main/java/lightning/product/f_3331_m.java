/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import lightning.product.TrunkPlacerType;
import lightning.product.K_4074_S;
import lightning.product.TreeFeature;
import lightning.product.BoundingBox;
import lightning.product.LevelSimulatedRW;
import lightning.product.TreeConfiguration;
import lightning.product.RotatedPillarBlock;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.TrunkPlacer;
import lightning.product.FoliagePlacer;
import lightning.product.u_530_F;

public class f_3331_m
extends TrunkPlacer {
    public static final Codec<f_3331_m> P_1922_E = RecordCodecBuilder.create(p_236891_0_ -> f_3331_m.n_1700_B(p_236891_0_).apply((Applicative)p_236891_0_, f_3331_m::new));

    public f_3331_m(int p_i232054_1_, int p_i232054_2_, int p_i232054_3_) {
        super(p_i232054_1_, p_i232054_2_, p_i232054_3_);
    }

    @Override
    protected TrunkPlacerType<?> n_1700_B() {
        return TrunkPlacerType.u_1723_Y;
    }

    @Override
    public List<FoliagePlacer.n_1700_B> n_1700_B(LevelSimulatedRW p_230382_1_, Random p_230382_2_, int p_230382_3_, c_1514_x p_230382_4_, Set<c_1514_x> p_230382_5_, BoundingBox p_230382_6_, TreeConfiguration p_230382_7_) {
        int j1;
        int i = 5;
        int j = p_230382_3_ + 2;
        int k = u_530_F.R_4764_Y((double)j * 0.618);
        if (!p_230382_7_.P_1922_E) {
            f_3331_m.n_1700_B(p_230382_1_, p_230382_4_.down());
        }
        double d0 = 1.0;
        int l = Math.min(1, u_530_F.R_4764_Y(1.382 + Math.pow(1.0 * (double)j / 13.0, 2.0)));
        int i1 = p_230382_4_.getY() + k;
        ArrayList list = Lists.newArrayList();
        list.add(new n_1700_B(p_230382_4_.up(j1), i1));
        for (j1 = j - 5; j1 >= 0; --j1) {
            float f = this.J_1907_R(j, j1);
            if (f < 0.0f) continue;
            for (int k1 = 0; k1 < l; ++k1) {
                c_1514_x blockpos1;
                double d5;
                double d3;
                double d1 = 1.0;
                double d2 = 1.0 * (double)f * ((double)p_230382_2_.nextFloat() + 0.328);
                double d4 = d2 * Math.sin(d3 = (double)(p_230382_2_.nextFloat() * 2.0f) * Math.PI) + 0.5;
                c_1514_x blockpos = p_230382_4_.add(d4, (double)(j1 - 1), d5 = d2 * Math.cos(d3) + 0.5);
                if (!this.n_1700_B(p_230382_1_, p_230382_2_, blockpos, blockpos1 = blockpos.up(5), false, p_230382_5_, p_230382_6_, p_230382_7_)) continue;
                int l1 = p_230382_4_.getX() - blockpos.getX();
                int i2 = p_230382_4_.getZ() - blockpos.getZ();
                double d6 = (double)blockpos.getY() - Math.sqrt(l1 * l1 + i2 * i2) * 0.381;
                int j2 = d6 > (double)i1 ? i1 : (int)d6;
                c_1514_x blockpos2 = new c_1514_x(p_230382_4_.getX(), j2, p_230382_4_.getZ());
                if (!this.n_1700_B(p_230382_1_, p_230382_2_, blockpos2, blockpos, false, p_230382_5_, p_230382_6_, p_230382_7_)) continue;
                list.add(new n_1700_B(blockpos, blockpos2.getY()));
            }
        }
        this.n_1700_B(p_230382_1_, p_230382_2_, p_230382_4_, p_230382_4_.up(k), true, p_230382_5_, p_230382_6_, p_230382_7_);
        this.n_1700_B(p_230382_1_, p_230382_2_, j, p_230382_4_, list, p_230382_5_, p_230382_6_, p_230382_7_);
        ArrayList list1 = Lists.newArrayList();
        for (n_1700_B fancytrunkplacer$foliage : list) {
            if (!this.n_1700_B(j, fancytrunkplacer$foliage.n_1700_B() - p_230382_4_.getY())) continue;
            list1.add(fancytrunkplacer$foliage.n_1700_B);
        }
        return list1;
    }

    private boolean n_1700_B(LevelSimulatedRW p_236887_1_, Random p_236887_2_, c_1514_x p_236887_3_, c_1514_x p_236887_4_, boolean p_236887_5_, Set<c_1514_x> p_236887_6_, BoundingBox p_236887_7_, TreeConfiguration p_236887_8_) {
        if (!p_236887_5_ && Objects.equals(p_236887_3_, p_236887_4_)) {
            return true;
        }
        c_1514_x blockpos = p_236887_4_.add(-p_236887_3_.getX(), -p_236887_3_.getY(), -p_236887_3_.getZ());
        int i = this.n_1700_B(blockpos);
        float f = (float)blockpos.getX() / (float)i;
        float f1 = (float)blockpos.getY() / (float)i;
        float f2 = (float)blockpos.getZ() / (float)i;
        for (int j = 0; j <= i; ++j) {
            c_1514_x blockpos1 = p_236887_3_.add(0.5f + (float)j * f, 0.5f + (float)j * f1, 0.5f + (float)j * f2);
            if (p_236887_5_) {
                f_3331_m.n_1700_B(p_236887_1_, blockpos1, (K_4074_S)p_236887_8_.J_1907_R.n_1700_B(p_236887_2_, blockpos1).n_1700_B(RotatedPillarBlock.t_1786_h, this.n_1700_B(p_236887_3_, blockpos1)), p_236887_7_);
                p_236887_6_.add(blockpos1.toImmutable());
                continue;
            }
            if (TreeFeature.R_4764_Y(p_236887_1_, blockpos1)) continue;
            return false;
        }
        return true;
    }

    private int n_1700_B(c_1514_x p_236888_1_) {
        int i = u_530_F.n_1700_B(p_236888_1_.getX());
        int j = u_530_F.n_1700_B(p_236888_1_.getY());
        int k = u_530_F.n_1700_B(p_236888_1_.getZ());
        return Math.max(i, Math.max(j, k));
    }

    private b_257_Y.n_1700_B n_1700_B(c_1514_x p_236889_1_, c_1514_x p_236889_2_) {
        int j;
        b_257_Y.n_1700_B direction$axis = b_257_Y.n_1700_B.J_1907_R;
        int i = Math.abs(p_236889_2_.getX() - p_236889_1_.getX());
        int k = Math.max(i, j = Math.abs(p_236889_2_.getZ() - p_236889_1_.getZ()));
        if (k > 0) {
            direction$axis = i == k ? b_257_Y.n_1700_B.n_1700_B : b_257_Y.n_1700_B.R_4764_Y;
        }
        return direction$axis;
    }

    private boolean n_1700_B(int p_236885_1_, int p_236885_2_) {
        return (double)p_236885_2_ >= (double)p_236885_1_ * 0.2;
    }

    private void n_1700_B(LevelSimulatedRW p_236886_1_, Random p_236886_2_, int p_236886_3_, c_1514_x p_236886_4_, List<n_1700_B> p_236886_5_, Set<c_1514_x> p_236886_6_, BoundingBox p_236886_7_, TreeConfiguration p_236886_8_) {
        for (n_1700_B fancytrunkplacer$foliage : p_236886_5_) {
            int i = fancytrunkplacer$foliage.n_1700_B();
            c_1514_x blockpos = new c_1514_x(p_236886_4_.getX(), i, p_236886_4_.getZ());
            if (blockpos.equals(fancytrunkplacer$foliage.n_1700_B.n_1700_B()) || !this.n_1700_B(p_236886_3_, i - p_236886_4_.getY())) continue;
            this.n_1700_B(p_236886_1_, p_236886_2_, blockpos, fancytrunkplacer$foliage.n_1700_B.n_1700_B(), true, p_236886_6_, p_236886_7_, p_236886_8_);
        }
    }

    private float J_1907_R(int p_236890_1_, int p_236890_2_) {
        if ((float)p_236890_2_ < (float)p_236890_1_ * 0.3f) {
            return -1.0f;
        }
        float f = (float)p_236890_1_ / 2.0f;
        float f1 = f - (float)p_236890_2_;
        float f2 = u_530_F.R_4764_Y(f * f - f1 * f1);
        if (f1 == 0.0f) {
            f2 = f;
        } else if (Math.abs(f1) >= f) {
            return 0.0f;
        }
        return f2 * 0.5f;
    }

    static class n_1700_B {
        private final FoliagePlacer.n_1700_B n_1700_B;
        private final int J_1907_R;

        public n_1700_B(c_1514_x p_i232055_1_, int p_i232055_2_) {
            this.n_1700_B = new FoliagePlacer.n_1700_B(p_i232055_1_, 0, false);
            this.J_1907_R = p_i232055_2_;
        }

        public int n_1700_B() {
            return this.J_1907_R;
        }
    }
}


