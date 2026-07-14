/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.AbstractList;
import lightning.product.Tag;

public abstract class CollectionTag<T extends Tag>
extends AbstractList<T>
implements Tag {
    public abstract T G_564_y(int var1, T var2);

    public abstract void R_4764_Y(int var1, T var2);

    public abstract T R_4764_Y(int var1);

    public abstract boolean n_1700_B(int var1, Tag var2);

    public abstract boolean J_1907_R(int var1, Tag var2);

    public abstract byte P_1922_E();

    @Override
    public /* synthetic */ Object remove(int n) {
        return this.R_4764_Y(n);
    }

    @Override
    public /* synthetic */ void add(int n, Object object) {
        this.R_4764_Y(n, (Tag)object);
    }

    @Override
    public /* synthetic */ Object set(int n, Object object) {
        return this.G_564_y(n, (Tag)object);
    }
}


