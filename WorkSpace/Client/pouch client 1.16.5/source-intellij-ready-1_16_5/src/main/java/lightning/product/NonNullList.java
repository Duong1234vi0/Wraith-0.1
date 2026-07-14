/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.apache.commons.lang3.Validate;

public class NonNullList<E>
extends AbstractList<E> {
    private final List<E> n_1700_B;
    private final E J_1907_R;

    public static <E> NonNullList<E> n_1700_B() {
        return new NonNullList<E>();
    }

    public static <E> NonNullList<E> n_1700_B(int size, E fill) {
        Validate.notNull(fill);
        Object[] aobject = new Object[size];
        Arrays.fill(aobject, fill);
        return new NonNullList<Object>(Arrays.asList(aobject), fill);
    }

    @SafeVarargs
    public static <E> NonNullList<E> n_1700_B(E defaultElementIn, E ... elements) {
        return new NonNullList<E>(Arrays.asList(elements), defaultElementIn);
    }

    protected NonNullList() {
        this(Lists.newArrayList(), null);
    }

    protected NonNullList(List<E> delegateIn, @Nullable E listType) {
        this.n_1700_B = delegateIn;
        this.J_1907_R = listType;
    }

    @Override
    @Nonnull
    public E get(int p_get_1_) {
        return this.n_1700_B.get(p_get_1_);
    }

    @Override
    public E set(int p_set_1_, E p_set_2_) {
        Validate.notNull(p_set_2_);
        return this.n_1700_B.set(p_set_1_, p_set_2_);
    }

    @Override
    public void add(int p_add_1_, E p_add_2_) {
        Validate.notNull(p_add_2_);
        this.n_1700_B.add(p_add_1_, p_add_2_);
    }

    @Override
    public E remove(int p_remove_1_) {
        return this.n_1700_B.remove(p_remove_1_);
    }

    @Override
    public int size() {
        return this.n_1700_B.size();
    }

    @Override
    public void clear() {
        if (this.J_1907_R == null) {
            super.clear();
        } else {
            for (int i = 0; i < this.size(); ++i) {
                this.set(i, this.J_1907_R);
            }
        }
    }
}


