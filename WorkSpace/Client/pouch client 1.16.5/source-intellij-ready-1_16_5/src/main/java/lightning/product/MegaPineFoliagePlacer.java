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
import java.util.Random;
import java.util.Set;
import lightning.product.BoundingBox;
import lightning.product.LevelSimulatedRW;
import lightning.product.TreeConfiguration;
import lightning.product.FoliagePlacerType;
import lightning.product.c_1514_x;
import lightning.product.g_1198_o;
import lightning.product.FoliagePlacer;
import lightning.product.u_530_F;

public class MegaPineFoliagePlacer
extends FoliagePlacer {
    public static final Codec<MegaPineFoliagePlacer> n_1700_B = RecordCodecBuilder.create(p_242832_0_ -> MegaPineFoliagePlacer.J_1907_R(p_242832_0_).and((App)g_1198_o.n_1700_B(0, 16, 8).fieldOf("crown_height").forGetter(p_242831_0_ -> p_242831_0_.J_1907_R)).apply((Applicative)p_242832_0_, MegaPineFoliagePlacer::new));
    private final g_1198_o J_1907_R;

    public MegaPineFoliagePlacer(g_1198_o p_i242001_1_, g_1198_o p_i242001_2_, g_1198_o p_i242001_3_) {
        super(p_i242001_1_, p_i242001_2_);
        this.J_1907_R = p_i242001_3_;
    }

    @Override
    protected FoliagePlacerType<?> n_1700_B() {
        return FoliagePlacerType.w_1484_f;
    }

    @Override
    protected void n_1700_B(LevelSimulatedRW p_230372_1_, Random p_230372_2_, TreeConfiguration p_230372_3_, int p_230372_4_, FoliagePlacer.n_1700_B p_230372_5_, int p_230372_6_, int p_230372_7_, Set<c_1514_x> p_230372_8_, int p_230372_9_, BoundingBox p_230372_10_) {
        c_1514_x blockpos = p_230372_5_.n_1700_B();
        int i = 0;
        for (int j = blockpos.getY() - p_230372_6_ + p_230372_9_; j <= blockpos.getY() + p_230372_9_; ++j) {
            int k = blockpos.getY() - j;
            int l = p_230372_7_ + p_230372_5_.J_1907_R() + u_530_F.G_564_y((float)k / (float)p_230372_6_ * 3.5f);
            int i1 = k > 0 && l == i && (j & 1) == 0 ? l + 1 : l;
            this.n_1700_B(p_230372_1_, p_230372_2_, p_230372_3_, new c_1514_x(blockpos.getX(), j, blockpos.getZ()), i1, p_230372_8_, 0, p_230372_5_.R_4764_Y(), p_230372_10_);
            i = l;
        }
    }

    @Override
    public int n_1700_B(Random p_230374_1_, int p_230374_2_, TreeConfiguration p_230374_3_) {
        return this.J_1907_R.n_1700_B(p_230374_1_);
    }

    @Override
    protected boolean n_1700_B(Random p_230373_1_, int p_230373_2_, int p_230373_3_, int p_230373_4_, int p_230373_5_, boolean p_230373_6_) {
        if (p_230373_2_ + p_230373_4_ >= 7) {
            return true;
        }
        return p_230373_2_ * p_230373_2_ + p_230373_4_ * p_230373_4_ > p_230373_5_ * p_230373_5_;
    }
}


