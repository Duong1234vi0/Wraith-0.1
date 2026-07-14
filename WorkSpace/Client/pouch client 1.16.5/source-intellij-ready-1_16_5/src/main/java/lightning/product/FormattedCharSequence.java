/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  it.unimi.dsi.fastutil.ints.Int2IntFunction
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import java.util.List;
import lightning.product.StringDecomposer;
import lightning.product.FormattedCharSink;
import lightning.product.Z_1567_W;

@FunctionalInterface
public interface FormattedCharSequence {
    public static final FormattedCharSequence n_1700_B = p_242236_0_ -> true;

    public boolean accept(FormattedCharSink var1);

    public static FormattedCharSequence n_1700_B(int codePoint, Z_1567_W style) {
        return p_242243_2_ -> p_242243_2_.accept(0, style, codePoint);
    }

    public static FormattedCharSequence n_1700_B(String string, Z_1567_W style) {
        return string.isEmpty() ? n_1700_B : p_242245_2_ -> StringDecomposer.n_1700_B(string, style, p_242245_2_);
    }

    public static FormattedCharSequence n_1700_B(String p_242246_0_, Z_1567_W p_242246_1_, Int2IntFunction p_242246_2_) {
        return p_242246_0_.isEmpty() ? n_1700_B : p_242240_3_ -> StringDecomposer.J_1907_R(p_242246_0_, p_242246_1_, FormattedCharSequence.n_1700_B(p_242240_3_, p_242246_2_));
    }

    public static FormattedCharSink n_1700_B(FormattedCharSink consumer, Int2IntFunction p_242237_1_) {
        return (p_242238_2_, p_242238_3_, p_242238_4_) -> consumer.accept(p_242238_2_, p_242238_3_, (Integer)p_242237_1_.apply((Object)p_242238_4_));
    }

    public static FormattedCharSequence n_1700_B(FormattedCharSequence p_242234_0_, FormattedCharSequence p_242234_1_) {
        return FormattedCharSequence.J_1907_R(p_242234_0_, p_242234_1_);
    }

    public static FormattedCharSequence n_1700_B(List<FormattedCharSequence> p_242241_0_) {
        int i = p_242241_0_.size();
        switch (i) {
            case 0: {
                return n_1700_B;
            }
            case 1: {
                return p_242241_0_.get(0);
            }
            case 2: {
                return FormattedCharSequence.J_1907_R(p_242241_0_.get(0), p_242241_0_.get(1));
            }
        }
        return FormattedCharSequence.J_1907_R((List<FormattedCharSequence>)ImmutableList.copyOf(p_242241_0_));
    }

    public static FormattedCharSequence J_1907_R(FormattedCharSequence p_242244_0_, FormattedCharSequence p_242244_1_) {
        return p_242235_2_ -> p_242244_0_.accept(p_242235_2_) && p_242244_1_.accept(p_242235_2_);
    }

    public static FormattedCharSequence J_1907_R(List<FormattedCharSequence> p_242247_0_) {
        return p_242242_1_ -> {
            for (FormattedCharSequence ireorderingprocessor : p_242247_0_) {
                if (ireorderingprocessor.accept(p_242242_1_)) continue;
                return false;
            }
            return true;
        };
    }
}


