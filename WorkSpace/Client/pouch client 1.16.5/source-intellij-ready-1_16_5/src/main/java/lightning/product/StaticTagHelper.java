/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.TagContainer;
import lightning.product.E_2561_m;
import lightning.product.g_2336_b;
import lightning.product.r_109_r;
import lightning.product.SetTag;

public class StaticTagHelper<T> {
    private E_2561_m<T> n_1700_B = E_2561_m.R_4764_Y();
    private final List<n_1700_B<T>> J_1907_R = Lists.newArrayList();
    private final Function<TagContainer, E_2561_m<T>> R_4764_Y;

    public StaticTagHelper(Function<TagContainer, E_2561_m<T>> supplierToCollectionFunction) {
        this.R_4764_Y = supplierToCollectionFunction;
    }

    public r_109_r.J_1907_R<T> n_1700_B(String id) {
        n_1700_B namedtag = new n_1700_B(new g_2336_b(id));
        this.J_1907_R.add(namedtag);
        return namedtag;
    }

    public void n_1700_B() {
        this.n_1700_B = E_2561_m.R_4764_Y();
        SetTag itag = SetTag.J_1907_R();
        this.J_1907_R.forEach(tag -> tag.n_1700_B((g_2336_b id) -> itag));
    }

    public void n_1700_B(TagContainer supplier) {
        E_2561_m itagcollection = this.R_4764_Y.apply(supplier);
        this.n_1700_B = itagcollection;
        this.J_1907_R.forEach(tag -> tag.n_1700_B(itagcollection::n_1700_B));
    }

    public E_2561_m<T> J_1907_R() {
        return this.n_1700_B;
    }

    public List<? extends r_109_r.J_1907_R<T>> R_4764_Y() {
        return this.J_1907_R;
    }

    public Set<g_2336_b> J_1907_R(TagContainer supplier) {
        E_2561_m<T> itagcollection = this.R_4764_Y.apply(supplier);
        Set set = this.J_1907_R.stream().map(n_1700_B::J_1907_R).collect(Collectors.toSet());
        ImmutableSet immutableset = ImmutableSet.copyOf(itagcollection.J_1907_R());
        return Sets.difference(set, (Set)immutableset);
    }

    static class n_1700_B<T>
    implements r_109_r.J_1907_R<T> {
        @Nullable
        private r_109_r<T> J_1907_R;
        protected final g_2336_b n_1700_B;

        private n_1700_B(g_2336_b id) {
            this.n_1700_B = id;
        }

        @Override
        public g_2336_b J_1907_R() {
            return this.n_1700_B;
        }

        private r_109_r<T> R_4764_Y() {
            if (this.J_1907_R == null) {
                throw new IllegalStateException("Tag " + String.valueOf(this.n_1700_B) + " used before it was bound");
            }
            return this.J_1907_R;
        }

        void n_1700_B(Function<g_2336_b, r_109_r<T>> idToTagFunction) {
            this.J_1907_R = idToTagFunction.apply(this.n_1700_B);
        }

        @Override
        public boolean n_1700_B(T element) {
            return this.R_4764_Y().n_1700_B(element);
        }

        @Override
        public List<T> n_1700_B() {
            return this.R_4764_Y().n_1700_B();
        }
    }
}


