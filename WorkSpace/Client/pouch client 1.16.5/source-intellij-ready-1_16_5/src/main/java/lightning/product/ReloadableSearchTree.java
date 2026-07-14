/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.AbstractIterator
 *  com.google.common.collect.Iterators
 *  com.google.common.collect.Lists
 *  com.google.common.collect.PeekingIterator
 */
package lightning.product;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Stream;
import lightning.product.ReloadableIdSearchTree;
import lightning.product.g_2336_b;
import lightning.product.SuffixArray;

public class ReloadableSearchTree<T>
extends ReloadableIdSearchTree<T> {
    protected SuffixArray<T> n_1700_B = new SuffixArray();
    private final Function<T, Stream<String>> G_564_y;

    public ReloadableSearchTree(Function<T, Stream<String>> nameFuncIn, Function<T, Stream<g_2336_b>> idFuncIn) {
        super(idFuncIn);
        this.G_564_y = nameFuncIn;
    }

    @Override
    public void J_1907_R() {
        this.n_1700_B = new SuffixArray();
        super.J_1907_R();
        this.n_1700_B.n_1700_B();
    }

    @Override
    protected void J_1907_R(T element) {
        super.J_1907_R(element);
        this.G_564_y.apply(element).forEach(p_217880_2_ -> this.n_1700_B.n_1700_B(element, p_217880_2_.toLowerCase(Locale.ROOT)));
    }

    @Override
    public List<T> n_1700_B(String searchText) {
        int i = searchText.indexOf(58);
        if (i < 0) {
            return this.n_1700_B.n_1700_B(searchText);
        }
        List list = this.J_1907_R.n_1700_B(searchText.substring(0, i).trim());
        String s = searchText.substring(i + 1).trim();
        List list1 = this.R_4764_Y.n_1700_B(s);
        List<T> list2 = this.n_1700_B.n_1700_B(s);
        return Lists.newArrayList(new ReloadableIdSearchTree.n_1700_B(list.iterator(), new n_1700_B(list1.iterator(), list2.iterator(), this::n_1700_B), this::n_1700_B));
    }

    static class n_1700_B<T>
    extends AbstractIterator<T> {
        private final PeekingIterator<T> n_1700_B;
        private final PeekingIterator<T> J_1907_R;
        private final Comparator<T> R_4764_Y;

        public n_1700_B(Iterator<T> p_i49977_1_, Iterator<T> p_i49977_2_, Comparator<T> p_i49977_3_) {
            this.n_1700_B = Iterators.peekingIterator(p_i49977_1_);
            this.J_1907_R = Iterators.peekingIterator(p_i49977_2_);
            this.R_4764_Y = p_i49977_3_;
        }

        protected T computeNext() {
            boolean flag1;
            boolean flag = !this.n_1700_B.hasNext();
            boolean bl = flag1 = !this.J_1907_R.hasNext();
            if (flag && flag1) {
                return (T)this.endOfData();
            }
            if (flag) {
                return (T)this.J_1907_R.next();
            }
            if (flag1) {
                return (T)this.n_1700_B.next();
            }
            int i = this.R_4764_Y.compare(this.n_1700_B.peek(), this.J_1907_R.peek());
            if (i == 0) {
                this.J_1907_R.next();
            }
            return (T)(i <= 0 ? this.n_1700_B.next() : this.J_1907_R.next());
        }
    }
}


