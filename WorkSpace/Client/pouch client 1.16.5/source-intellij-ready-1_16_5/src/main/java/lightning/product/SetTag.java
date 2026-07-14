/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableSet
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.List;
import java.util.Set;
import lightning.product.r_109_r;

public class SetTag<T>
implements r_109_r<T> {
    private final ImmutableList<T> J_1907_R;
    private final Set<T> R_4764_Y;
    @VisibleForTesting
    protected final Class<?> n_1700_B;

    protected SetTag(Set<T> contents, Class<?> contentsClassType) {
        this.n_1700_B = contentsClassType;
        this.R_4764_Y = contents;
        this.J_1907_R = ImmutableList.copyOf(contents);
    }

    public static <T> SetTag<T> J_1907_R() {
        return new SetTag<T>(ImmutableSet.of(), Void.class);
    }

    public static <T> SetTag<T> J_1907_R(Set<T> contents) {
        return new SetTag<T>(contents, SetTag.R_4764_Y(contents));
    }

    @Override
    public boolean n_1700_B(T element) {
        return this.n_1700_B.isInstance(element) && this.R_4764_Y.contains(element);
    }

    @Override
    public List<T> n_1700_B() {
        return this.J_1907_R;
    }

    private static <T> Class<?> R_4764_Y(Set<T> contents) {
        if (contents.isEmpty()) {
            return Void.class;
        }
        Class<?> oclass = null;
        for (T t : contents) {
            if (oclass == null) {
                oclass = t.getClass();
                continue;
            }
            oclass = SetTag.n_1700_B(oclass, t.getClass());
        }
        return oclass;
    }

    private static Class<?> n_1700_B(Class<?> input, Class<?> comparison) {
        while (!input.isAssignableFrom(comparison)) {
            input = input.getSuperclass();
        }
        return input;
    }
}


