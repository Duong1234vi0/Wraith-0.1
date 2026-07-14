/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  it.unimi.dsi.fastutil.ints.Int2IntFunction
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.UnaryOperator;
import lightning.product.StringDecomposer;
import lightning.product.FormattedText;
import lightning.product.Z_1567_W;
import lightning.product.FormattedCharSequence;

public class SubStringSource {
    private final String n_1700_B;
    private final List<Z_1567_W> J_1907_R;
    private final Int2IntFunction R_4764_Y;

    private SubStringSource(String p_i242079_1_, List<Z_1567_W> p_i242079_2_, Int2IntFunction p_i242079_3_) {
        this.n_1700_B = p_i242079_1_;
        this.J_1907_R = ImmutableList.copyOf(p_i242079_2_);
        this.R_4764_Y = p_i242079_3_;
    }

    public String n_1700_B() {
        return this.n_1700_B;
    }

    public List<FormattedCharSequence> n_1700_B(int p_244287_1_, int p_244287_2_, boolean p_244287_3_) {
        if (p_244287_2_ == 0) {
            return ImmutableList.of();
        }
        ArrayList list = Lists.newArrayList();
        Z_1567_W style = this.J_1907_R.get(p_244287_1_);
        int i = p_244287_1_;
        for (int j = 1; j < p_244287_2_; ++j) {
            int k = p_244287_1_ + j;
            Z_1567_W style1 = this.J_1907_R.get(k);
            if (style1.equals(style)) continue;
            String s = this.n_1700_B.substring(i, k);
            list.add(p_244287_3_ ? FormattedCharSequence.n_1700_B(s, style, this.R_4764_Y) : FormattedCharSequence.n_1700_B(s, style));
            style = style1;
            i = k;
        }
        if (i < p_244287_1_ + p_244287_2_) {
            String s1 = this.n_1700_B.substring(i, p_244287_1_ + p_244287_2_);
            list.add(p_244287_3_ ? FormattedCharSequence.n_1700_B(s1, style, this.R_4764_Y) : FormattedCharSequence.n_1700_B(s1, style));
        }
        return p_244287_3_ ? Lists.reverse((List)list) : list;
    }

    public static SubStringSource n_1700_B(FormattedText p_244290_0_, Int2IntFunction p_244290_1_, UnaryOperator<String> p_244290_2_) {
        StringBuilder stringbuilder = new StringBuilder();
        ArrayList list = Lists.newArrayList();
        p_244290_0_.n_1700_B((p_244289_2_, p_244289_3_) -> {
            StringDecomposer.R_4764_Y(p_244289_3_, p_244289_2_, (p_244288_2_, p_244288_3_, p_244288_4_) -> {
                stringbuilder.appendCodePoint(p_244288_4_);
                int i = Character.charCount(p_244288_4_);
                for (int j = 0; j < i; ++j) {
                    list.add(p_244288_3_);
                }
                return true;
            });
            return Optional.empty();
        }, Z_1567_W.n_1700_B);
        return new SubStringSource((String)p_244290_2_.apply(stringbuilder.toString()), list, p_244290_1_);
    }
}


