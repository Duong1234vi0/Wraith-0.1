/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Iterators
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class e_1322_b<T>
extends AbstractCollection<T> {
    private final Map<Class<?>, List<T>> n_1700_B = Maps.newHashMap();
    private final Class<T> J_1907_R;
    private final List<T> R_4764_Y = Lists.newArrayList();

    public e_1322_b(Class<T> baseClassIn) {
        this.J_1907_R = baseClassIn;
        this.n_1700_B.put(baseClassIn, this.R_4764_Y);
    }

    @Override
    public boolean add(T p_add_1_) {
        boolean flag = false;
        for (Map.Entry<Class<?>, List<T>> entry : this.n_1700_B.entrySet()) {
            if (!entry.getKey().isInstance(p_add_1_)) continue;
            flag |= entry.getValue().add(p_add_1_);
        }
        return flag;
    }

    @Override
    public boolean remove(Object p_remove_1_) {
        boolean flag = false;
        for (Map.Entry<Class<?>, List<T>> entry : this.n_1700_B.entrySet()) {
            if (!entry.getKey().isInstance(p_remove_1_)) continue;
            List<T> list = entry.getValue();
            flag |= list.remove(p_remove_1_);
        }
        return flag;
    }

    @Override
    public boolean contains(Object p_contains_1_) {
        return this.n_1700_B(p_contains_1_.getClass()).contains(p_contains_1_);
    }

    public <S> Collection<S> n_1700_B(Class<S> p_219790_1_) {
        if (!this.J_1907_R.isAssignableFrom(p_219790_1_)) {
            throw new IllegalArgumentException("Don't know how to search for " + String.valueOf(p_219790_1_));
        }
        List list = this.n_1700_B.computeIfAbsent(p_219790_1_, p_219791_1_ -> this.R_4764_Y.stream().filter(p_219791_1_::isInstance).collect(Collectors.toList()));
        return Collections.unmodifiableCollection(list);
    }

    @Override
    public Iterator<T> iterator() {
        return this.R_4764_Y.isEmpty() ? Collections.emptyIterator() : Iterators.unmodifiableIterator(this.R_4764_Y.iterator());
    }

    public List<T> n_1700_B() {
        return ImmutableList.copyOf(this.R_4764_Y);
    }

    @Override
    public int size() {
        return this.R_4764_Y.size();
    }
}

