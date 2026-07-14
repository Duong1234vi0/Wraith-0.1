/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.J_3017_d;
import lightning.product.K_4040_w;
import lightning.product.BoundingBox;
import lightning.product.WorldGenLevel;
import lightning.product.V_3137_a;
import lightning.product.W_2163_m;
import lightning.product.X_2241_P;
import lightning.product.a_2886_t;
import lightning.product.ConfiguredFeature;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.ProcessorLists;
import lightning.product.e_3109_Q;
import lightning.product.EmptyPoolElement;
import lightning.product.g_2336_b;
import lightning.product.StructurePoolElementType;
import lightning.product.p_3713_U;
import lightning.product.r_3979_x_0;
import lightning.product.LevelAccessor;
import lightning.product.z_1753_f;
import lightning.product.z_4596_H;

public abstract class StructurePoolElement {
    public static final Codec<StructurePoolElement> R_4764_Y = V_3137_a.g_4106_L.dispatch("element_type", StructurePoolElement::n_1700_B, StructurePoolElementType::codec);
    @Nullable
    private volatile X_2241_P.n_1700_B n_1700_B;

    protected static <E extends StructurePoolElement> RecordCodecBuilder<E, X_2241_P.n_1700_B> J_1907_R() {
        return X_2241_P.n_1700_B.R_4764_Y.fieldOf("projection").forGetter(StructurePoolElement::R_4764_Y);
    }

    protected StructurePoolElement(X_2241_P.n_1700_B projection) {
        this.n_1700_B = projection;
    }

    public abstract List<a_2886_t.J_1907_R> n_1700_B(b_2085_h var1, c_1514_x var2, W_2163_m var3, Random var4);

    public abstract BoundingBox n_1700_B(b_2085_h var1, c_1514_x var2, W_2163_m var3);

    public abstract boolean n_1700_B(b_2085_h var1, WorldGenLevel var2, J_3017_d var3, z_1753_f var4, c_1514_x var5, c_1514_x var6, W_2163_m var7, BoundingBox var8, Random var9, boolean var10);

    public abstract StructurePoolElementType<?> n_1700_B();

    public void n_1700_B(LevelAccessor worldIn, a_2886_t.J_1907_R p_214846_2_, c_1514_x pos, W_2163_m rotationIn, Random rand, BoundingBox p_214846_6_) {
    }

    public StructurePoolElement n_1700_B(X_2241_P.n_1700_B placementBehaviour) {
        this.n_1700_B = placementBehaviour;
        return this;
    }

    public X_2241_P.n_1700_B R_4764_Y() {
        X_2241_P.n_1700_B jigsawpattern$placementbehaviour = this.n_1700_B;
        if (jigsawpattern$placementbehaviour == null) {
            throw new IllegalStateException();
        }
        return jigsawpattern$placementbehaviour;
    }

    public int G_564_y() {
        return 1;
    }

    public static Function<X_2241_P.n_1700_B, EmptyPoolElement> P_1922_E() {
        return p_242857_0_ -> EmptyPoolElement.J_1907_R;
    }

    public static Function<X_2241_P.n_1700_B, p_3713_U> n_1700_B(String p_242849_0_) {
        return p_242860_1_ -> new p_3713_U((Either<g_2336_b, a_2886_t>)Either.left((Object)new g_2336_b(p_242849_0_)), () -> ProcessorLists.n_1700_B, (X_2241_P.n_1700_B)p_242860_1_);
    }

    public static Function<X_2241_P.n_1700_B, p_3713_U> n_1700_B(String p_242851_0_, r_3979_x_0 p_242851_1_) {
        return p_242862_2_ -> new p_3713_U((Either<g_2336_b, a_2886_t>)Either.left((Object)new g_2336_b(p_242851_0_)), () -> p_242851_1_, (X_2241_P.n_1700_B)p_242862_2_);
    }

    public static Function<X_2241_P.n_1700_B, e_3109_Q> J_1907_R(String p_242859_0_) {
        return p_242850_1_ -> new e_3109_Q((Either<g_2336_b, a_2886_t>)Either.left((Object)new g_2336_b(p_242859_0_)), () -> ProcessorLists.n_1700_B, (X_2241_P.n_1700_B)p_242850_1_);
    }

    public static Function<X_2241_P.n_1700_B, e_3109_Q> J_1907_R(String p_242861_0_, r_3979_x_0 p_242861_1_) {
        return p_242852_2_ -> new e_3109_Q((Either<g_2336_b, a_2886_t>)Either.left((Object)new g_2336_b(p_242861_0_)), () -> p_242861_1_, (X_2241_P.n_1700_B)p_242852_2_);
    }

    public static Function<X_2241_P.n_1700_B, z_4596_H> n_1700_B(ConfiguredFeature<?, ?> p_242845_0_) {
        return p_242846_1_ -> new z_4596_H(() -> p_242845_0_, (X_2241_P.n_1700_B)p_242846_1_);
    }

    public static Function<X_2241_P.n_1700_B, K_4040_w> n_1700_B(List<Function<X_2241_P.n_1700_B, ? extends StructurePoolElement>> p_242853_0_) {
        return p_242854_1_ -> new K_4040_w(p_242853_0_.stream().map(p_242847_1_ -> (StructurePoolElement)p_242847_1_.apply(p_242854_1_)).collect(Collectors.toList()), (X_2241_P.n_1700_B)p_242854_1_);
    }
}


