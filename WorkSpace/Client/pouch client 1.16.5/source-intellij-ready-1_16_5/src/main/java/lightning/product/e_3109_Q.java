/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.datafixers.util.Either
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Decoder
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Decoder;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import lightning.product.JigsawReplacementProcessor;
import lightning.product.J_3017_d;
import lightning.product.BoundingBox;
import lightning.product.M_3212_T;
import lightning.product.WorldGenLevel;
import lightning.product.W_2163_m;
import lightning.product.X_2241_P;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.ProcessorLists;
import lightning.product.g_2336_b;
import lightning.product.StructurePoolElementType;
import lightning.product.StructurePoolElement;
import lightning.product.r_3979_x_0;
import lightning.product.r_4719_P;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;
import lightning.product.z_1753_f;

public class e_3109_Q
extends StructurePoolElement {
    private static final Codec<Either<g_2336_b, a_2886_t>> n_1700_B = Codec.of(e_3109_Q::n_1700_B, (Decoder)g_2336_b.n_1700_B.map(Either::left));
    public static final Codec<e_3109_Q> J_1907_R = RecordCodecBuilder.create(p_236841_0_ -> p_236841_0_.group(e_3109_Q.v_4262_N(), e_3109_Q.u_1723_Y(), e_3109_Q.J_1907_R()).apply((Applicative)p_236841_0_, e_3109_Q::new));
    protected final Either<g_2336_b, a_2886_t> G_564_y;
    protected final Supplier<r_3979_x_0> P_1922_E;

    private static <T> DataResult<T> n_1700_B(Either<g_2336_b, a_2886_t> p_236840_0_, DynamicOps<T> p_236840_1_, T p_236840_2_) {
        Optional optional = p_236840_0_.left();
        return !optional.isPresent() ? DataResult.error((String)"Can not serialize a runtime pool element") : g_2336_b.n_1700_B.encode((Object)((g_2336_b)optional.get()), p_236840_1_, p_236840_2_);
    }

    protected static <E extends e_3109_Q> RecordCodecBuilder<E, Supplier<r_3979_x_0>> u_1723_Y() {
        return StructureProcessorType.P_4830_p.fieldOf("processors").forGetter(p_236845_0_ -> p_236845_0_.P_1922_E);
    }

    protected static <E extends e_3109_Q> RecordCodecBuilder<E, Either<g_2336_b, a_2886_t>> v_4262_N() {
        return n_1700_B.fieldOf("location").forGetter(p_236842_0_ -> p_236842_0_.G_564_y);
    }

    protected e_3109_Q(Either<g_2336_b, a_2886_t> p_i242008_1_, Supplier<r_3979_x_0> p_i242008_2_, X_2241_P.n_1700_B p_i242008_3_) {
        super(p_i242008_3_);
        this.G_564_y = p_i242008_1_;
        this.P_1922_E = p_i242008_2_;
    }

    public e_3109_Q(a_2886_t p_i242009_1_) {
        this((Either<g_2336_b, a_2886_t>)Either.right((Object)p_i242009_1_), () -> ProcessorLists.n_1700_B, X_2241_P.n_1700_B.J_1907_R);
    }

    private a_2886_t n_1700_B(b_2085_h p_236843_1_) {
        return (a_2886_t)this.G_564_y.map(p_236843_1_::n_1700_B, Function.identity());
    }

    public List<a_2886_t.J_1907_R> n_1700_B(b_2085_h p_214857_1_, c_1514_x p_214857_2_, W_2163_m p_214857_3_, boolean p_214857_4_) {
        a_2886_t template = this.n_1700_B(p_214857_1_);
        List<a_2886_t.J_1907_R> list = template.n_1700_B(p_214857_2_, new w_1748_S().n_1700_B(p_214857_3_), a_3742_W.l_14_c, p_214857_4_);
        ArrayList list1 = Lists.newArrayList();
        for (a_2886_t.J_1907_R template$blockinfo : list) {
            M_3212_T structuremode;
            if (template$blockinfo.R_4764_Y == null || (structuremode = M_3212_T.valueOf(template$blockinfo.R_4764_Y.M_588_G("mode"))) != M_3212_T.G_564_y) continue;
            list1.add(template$blockinfo);
        }
        return list1;
    }

    @Override
    public List<a_2886_t.J_1907_R> n_1700_B(b_2085_h templateManagerIn, c_1514_x pos, W_2163_m rotationIn, Random rand) {
        a_2886_t template = this.n_1700_B(templateManagerIn);
        List<a_2886_t.J_1907_R> list = template.n_1700_B(pos, new w_1748_S().n_1700_B(rotationIn), a_3742_W.q_2034_t, true);
        Collections.shuffle(list, rand);
        return list;
    }

    @Override
    public BoundingBox n_1700_B(b_2085_h templateManagerIn, c_1514_x pos, W_2163_m rotationIn) {
        a_2886_t template = this.n_1700_B(templateManagerIn);
        return template.J_1907_R(new w_1748_S().n_1700_B(rotationIn), pos);
    }

    @Override
    public boolean n_1700_B(b_2085_h p_230378_1_, WorldGenLevel p_230378_2_, J_3017_d p_230378_3_, z_1753_f p_230378_4_, c_1514_x p_230378_5_, c_1514_x p_230378_6_, W_2163_m p_230378_7_, BoundingBox p_230378_8_, Random p_230378_9_, boolean p_230378_10_) {
        w_1748_S placementsettings;
        a_2886_t template = this.n_1700_B(p_230378_1_);
        if (!template.n_1700_B(p_230378_2_, p_230378_5_, p_230378_6_, placementsettings = this.n_1700_B(p_230378_7_, p_230378_8_, p_230378_10_), p_230378_9_, 18)) {
            return false;
        }
        for (a_2886_t.J_1907_R template$blockinfo : a_2886_t.n_1700_B(p_230378_2_, p_230378_5_, p_230378_6_, placementsettings, this.n_1700_B(p_230378_1_, p_230378_5_, p_230378_7_, false))) {
            this.n_1700_B(p_230378_2_, template$blockinfo, p_230378_5_, p_230378_7_, p_230378_9_, p_230378_8_);
        }
        return true;
    }

    protected w_1748_S n_1700_B(W_2163_m p_230379_1_, BoundingBox p_230379_2_, boolean p_230379_3_) {
        w_1748_S placementsettings = new w_1748_S();
        placementsettings.n_1700_B(p_230379_2_);
        placementsettings.n_1700_B(p_230379_1_);
        placementsettings.J_1907_R(true);
        placementsettings.n_1700_B(false);
        placementsettings.n_1700_B(r_4719_P.J_1907_R);
        placementsettings.R_4764_Y(true);
        if (!p_230379_3_) {
            placementsettings.n_1700_B(JigsawReplacementProcessor.J_1907_R);
        }
        this.P_1922_E.get().n_1700_B().forEach(placementsettings::n_1700_B);
        this.R_4764_Y().R_4764_Y().forEach(placementsettings::n_1700_B);
        return placementsettings;
    }

    @Override
    public StructurePoolElementType<?> n_1700_B() {
        return StructurePoolElementType.n_1700_B;
    }

    public String toString() {
        return "Single[" + String.valueOf(this.G_564_y) + "]";
    }
}


