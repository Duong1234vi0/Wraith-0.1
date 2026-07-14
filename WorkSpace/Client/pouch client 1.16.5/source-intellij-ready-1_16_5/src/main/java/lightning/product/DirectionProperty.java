/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lightning.product.b_257_Y;
import lightning.product.e_563_h;

public class DirectionProperty
extends e_563_h<b_257_Y> {
    protected DirectionProperty(String name, Collection<b_257_Y> values) {
        super(name, b_257_Y.class, values);
    }

    public static DirectionProperty n_1700_B(String name, Predicate<b_257_Y> filter) {
        return DirectionProperty.n_1700_B(name, Arrays.stream(b_257_Y.values()).filter(filter).collect(Collectors.toList()));
    }

    public static DirectionProperty n_1700_B(String p_196962_0_, b_257_Y ... p_196962_1_) {
        return DirectionProperty.n_1700_B(p_196962_0_, Lists.newArrayList((Object[])p_196962_1_));
    }

    public static DirectionProperty n_1700_B(String name, Collection<b_257_Y> values) {
        return new DirectionProperty(name, values);
    }
}


