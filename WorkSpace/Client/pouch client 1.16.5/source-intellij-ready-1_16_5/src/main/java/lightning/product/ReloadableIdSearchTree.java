/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.AbstractIterator
 *  com.google.common.collect.Iterators
 *  com.google.common.collect.Lists
 *  com.google.common.collect.PeekingIterator
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 */
package lightning.product;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;
import java.util.stream.Stream;
import lightning.product.g_2336_b;
import lightning.product.SuffixArray;
import lightning.product.MutableSearchTree;

public class ReloadableIdSearchTree<T>
implements MutableSearchTree<T> {
    protected SuffixArray<T> J_1907_R = new SuffixArray();
    protected SuffixArray<T> R_4764_Y = new SuffixArray();
    private final Function<T, Stream<g_2336_b>> n_1700_B;
    private final List<T> G_564_y = Lists.newArrayList();
    private final Object2IntMap<T> P_1922_E = new Object2IntOpenHashMap();

    public ReloadableIdSearchTree(Function<T, Stream<g_2336_b>> p_i50896_1_) {
        this.n_1700_B = p_i50896_1_;
    }

    @Override
    public void J_1907_R() {
        this.J_1907_R = new SuffixArray();
        this.R_4764_Y = new SuffixArray();
        for (T t : this.G_564_y) {
            this.J_1907_R(t);
        }
        this.J_1907_R.n_1700_B();
        this.R_4764_Y.n_1700_B();
    }

    @Override
    public void n_1700_B(T element) {
        this.P_1922_E.put(element, this.G_564_y.size());
        this.G_564_y.add(element);
        this.J_1907_R(element);
    }

    @Override
    public void n_1700_B() {
        this.G_564_y.clear();
        this.P_1922_E.clear();
    }

    protected void J_1907_R(T element) {
        this.n_1700_B.apply(element).forEach(p_217873_2_ -> {
            this.J_1907_R.n_1700_B(element, p_217873_2_.R_4764_Y().toLowerCase(Locale.ROOT));
            this.R_4764_Y.n_1700_B(element, p_217873_2_.J_1907_R().toLowerCase(Locale.ROOT));
        });
    }

    protected int n_1700_B(T p_217874_1_, T p_217874_2_) {
        return Integer.compare(this.P_1922_E.getInt(p_217874_1_), this.P_1922_E.getInt(p_217874_2_));
    }

    @Override
    public List<T> n_1700_B(String searchText) {
        int i = searchText.indexOf(58);
        if (i == -1) {
            return this.R_4764_Y.n_1700_B(searchText);
        }
        List<T> list = this.J_1907_R.n_1700_B(searchText.substring(0, i).trim());
        String s = searchText.substring(i + 1).trim();
        List<T> list1 = this.R_4764_Y.n_1700_B(s);
        return Lists.newArrayList(new n_1700_B<T>(list.iterator(), list1.iterator(), this::n_1700_B));
    }

    public static class n_1700_B<T>
    extends AbstractIterator<T> {
        private final PeekingIterator<T> n_1700_B;
        private final PeekingIterator<T> J_1907_R;
        private final Comparator<T> R_4764_Y;

        public n_1700_B(Iterator<T> p_i50270_1_, Iterator<T> p_i50270_2_, Comparator<T> p_i50270_3_) {
            this.n_1700_B = Iterators.peekingIterator(p_i50270_1_);
            this.J_1907_R = Iterators.peekingIterator(p_i50270_2_);
            this.R_4764_Y = p_i50270_3_;
        }

        protected T computeNext() {
            while (this.n_1700_B.hasNext() && this.J_1907_R.hasNext()) {
                int i = this.R_4764_Y.compare(this.n_1700_B.peek(), this.J_1907_R.peek());
                if (i == 0) {
                    this.J_1907_R.next();
                    return (T)this.n_1700_B.next();
                }
                if (i < 0) {
                    this.n_1700_B.next();
                    continue;
                }
                this.J_1907_R.next();
            }
            return (T)this.endOfData();
        }
    }
}


