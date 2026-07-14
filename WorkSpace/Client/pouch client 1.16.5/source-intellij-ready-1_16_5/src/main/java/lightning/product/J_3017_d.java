/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.DataFixUtils
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.DataFixUtils;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.ChunkStatus;
import lightning.product.StructureFeature;
import lightning.product.K_3381_i;
import lightning.product.Y_1387_d;
import lightning.product.FeatureAccess;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.StructureStart;
import lightning.product.j_419_j;
import lightning.product.LevelAccessor;

public class J_3017_d {
    private final LevelAccessor n_1700_B;
    private final j_419_j J_1907_R;

    public J_3017_d(LevelAccessor p_i231626_1_, j_419_j p_i231626_2_) {
        this.n_1700_B = p_i231626_1_;
        this.J_1907_R = p_i231626_2_;
    }

    public J_3017_d n_1700_B(K_3381_i p_241464_1_) {
        if (p_241464_1_.J_1907_R() != this.n_1700_B) {
            throw new IllegalStateException("Using invalid feature manager (source level: " + String.valueOf(p_241464_1_.J_1907_R()) + ", region: " + String.valueOf(p_241464_1_));
        }
        return new J_3017_d(p_241464_1_, this.J_1907_R);
    }

    public Stream<? extends StructureStart<?>> n_1700_B(SectionPos p_235011_1_, StructureFeature<?> p_235011_2_) {
        return this.n_1700_B.n_1700_B(p_235011_1_.n_1700_B(), p_235011_1_.R_4764_Y(), ChunkStatus.R_4764_Y).func_230346_b_(p_235011_2_).stream().map(p_235015_0_ -> SectionPos.n_1700_B(new Y_1387_d((long)p_235015_0_), 0)).map(p_235006_2_ -> this.n_1700_B((SectionPos)p_235006_2_, p_235011_2_, this.n_1700_B.n_1700_B(p_235006_2_.n_1700_B(), p_235006_2_.R_4764_Y(), ChunkStatus.J_1907_R))).filter(p_235007_0_ -> p_235007_0_ != null && p_235007_0_.P_1922_E());
    }

    @Nullable
    public StructureStart<?> n_1700_B(SectionPos p_235013_1_, StructureFeature<?> p_235013_2_, FeatureAccess p_235013_3_) {
        return p_235013_3_.func_230342_a_(p_235013_2_);
    }

    public void n_1700_B(SectionPos p_235014_1_, StructureFeature<?> p_235014_2_, StructureStart<?> p_235014_3_, FeatureAccess p_235014_4_) {
        p_235014_4_.func_230344_a_(p_235014_2_, p_235014_3_);
    }

    public void n_1700_B(SectionPos p_235012_1_, StructureFeature<?> p_235012_2_, long p_235012_3_, FeatureAccess p_235012_5_) {
        p_235012_5_.func_230343_a_(p_235012_2_, p_235012_3_);
    }

    public boolean n_1700_B() {
        return this.J_1907_R.J_1907_R();
    }

    public StructureStart<?> n_1700_B(c_1514_x p_235010_1_, boolean p_235010_2_, StructureFeature<?> p_235010_3_) {
        return (StructureStart)DataFixUtils.orElse(this.n_1700_B(SectionPos.n_1700_B(p_235010_1_), p_235010_3_).filter(p_235009_1_ -> p_235009_1_.R_4764_Y().J_1907_R(p_235010_1_)).filter(p_235016_2_ -> !p_235010_2_ || p_235016_2_.G_564_y().stream().anyMatch(p_235008_1_ -> p_235008_1_.v_4262_N().J_1907_R(p_235010_1_))).findFirst(), StructureStart.n_1700_B);
    }
}


