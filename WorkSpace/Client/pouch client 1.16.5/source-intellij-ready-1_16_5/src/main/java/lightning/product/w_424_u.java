/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  com.google.common.collect.Iterators
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.IdMap;

public class w_424_u<T>
implements IdMap<T> {
    private int n_1700_B;
    private final IdentityHashMap<T, Integer> J_1907_R;
    private final List<T> R_4764_Y;

    public w_424_u() {
        this(512);
    }

    public w_424_u(int expectedSize) {
        this.R_4764_Y = Lists.newArrayListWithExpectedSize((int)expectedSize);
        this.J_1907_R = new IdentityHashMap(expectedSize);
    }

    public void n_1700_B(T key, int value) {
        this.J_1907_R.put(key, value);
        while (this.R_4764_Y.size() <= value) {
            this.R_4764_Y.add(null);
        }
        this.R_4764_Y.set(value, key);
        if (this.n_1700_B <= value) {
            this.n_1700_B = value + 1;
        }
    }

    public void J_1907_R(T key) {
        this.n_1700_B(key, this.n_1700_B);
    }

    @Override
    public int n_1700_B(T value) {
        Integer integer = this.J_1907_R.get(value);
        return integer == null ? -1 : integer;
    }

    @Override
    @Nullable
    public final T n_1700_B(int value) {
        return value >= 0 && value < this.R_4764_Y.size() ? (T)this.R_4764_Y.get(value) : null;
    }

    @Override
    public Iterator<T> iterator() {
        return Iterators.filter(this.R_4764_Y.iterator(), (Predicate)Predicates.notNull());
    }

    public int n_1700_B() {
        return this.J_1907_R.size();
    }
}


