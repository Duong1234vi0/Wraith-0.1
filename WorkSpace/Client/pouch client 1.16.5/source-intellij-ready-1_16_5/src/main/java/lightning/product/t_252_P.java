/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  com.google.common.collect.Iterators
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import java.util.Arrays;
import java.util.Iterator;
import javax.annotation.Nullable;
import lightning.product.IdMap;
import lightning.product.u_530_F;

public class t_252_P<K>
implements IdMap<K> {
    private static final Object n_1700_B = null;
    private K[] J_1907_R;
    private int[] R_4764_Y;
    private K[] G_564_y;
    private int P_1922_E;
    private int u_1723_Y;

    public t_252_P(int initialCapacity) {
        initialCapacity = (int)((float)initialCapacity / 0.8f);
        this.J_1907_R = new Object[initialCapacity];
        this.R_4764_Y = new int[initialCapacity];
        this.G_564_y = new Object[initialCapacity];
    }

    @Override
    public int n_1700_B(@Nullable K value) {
        return this.J_1907_R(this.J_1907_R(value, this.R_4764_Y(value)));
    }

    @Override
    @Nullable
    public K n_1700_B(int value) {
        return value >= 0 && value < this.G_564_y.length ? (K)this.G_564_y[value] : null;
    }

    private int J_1907_R(int key) {
        return key == -1 ? -1 : this.R_4764_Y[key];
    }

    public int J_1907_R(K objectIn) {
        int i = this.R_4764_Y();
        this.n_1700_B(objectIn, i);
        return i;
    }

    private int R_4764_Y() {
        while (this.P_1922_E < this.G_564_y.length && this.G_564_y[this.P_1922_E] != null) {
            ++this.P_1922_E;
        }
        return this.P_1922_E;
    }

    private void R_4764_Y(int capacity) {
        K[] ak = this.J_1907_R;
        int[] aint = this.R_4764_Y;
        this.J_1907_R = new Object[capacity];
        this.R_4764_Y = new int[capacity];
        this.G_564_y = new Object[capacity];
        this.P_1922_E = 0;
        this.u_1723_Y = 0;
        for (int i = 0; i < ak.length; ++i) {
            if (ak[i] == null) continue;
            this.n_1700_B(ak[i], aint[i]);
        }
    }

    public void n_1700_B(K objectIn, int intKey) {
        int i = Math.max(intKey, this.u_1723_Y + 1);
        if ((float)i >= (float)this.J_1907_R.length * 0.8f) {
            int j;
            for (j = this.J_1907_R.length << 1; j < intKey; j <<= 1) {
            }
            this.R_4764_Y(j);
        }
        int k = this.G_564_y(this.R_4764_Y(objectIn));
        this.J_1907_R[k] = objectIn;
        this.R_4764_Y[k] = intKey;
        this.G_564_y[intKey] = objectIn;
        ++this.u_1723_Y;
        if (intKey == this.P_1922_E) {
            ++this.P_1922_E;
        }
    }

    private int R_4764_Y(@Nullable K obectIn) {
        return (u_530_F.v_4262_N(System.identityHashCode(obectIn)) & Integer.MAX_VALUE) % this.J_1907_R.length;
    }

    private int J_1907_R(@Nullable K objectIn, int startIndex) {
        for (int i = startIndex; i < this.J_1907_R.length; ++i) {
            if (this.J_1907_R[i] == objectIn) {
                return i;
            }
            if (this.J_1907_R[i] != n_1700_B) continue;
            return -1;
        }
        for (int j = 0; j < startIndex; ++j) {
            if (this.J_1907_R[j] == objectIn) {
                return j;
            }
            if (this.J_1907_R[j] != n_1700_B) continue;
            return -1;
        }
        return -1;
    }

    private int G_564_y(int startIndex) {
        for (int i = startIndex; i < this.J_1907_R.length; ++i) {
            if (this.J_1907_R[i] != n_1700_B) continue;
            return i;
        }
        for (int j = 0; j < startIndex; ++j) {
            if (this.J_1907_R[j] != n_1700_B) continue;
            return j;
        }
        throw new RuntimeException("Overflowed :(");
    }

    @Override
    public Iterator<K> iterator() {
        return Iterators.filter((Iterator)Iterators.forArray((Object[])this.G_564_y), (Predicate)Predicates.notNull());
    }

    public void n_1700_B() {
        Arrays.fill(this.J_1907_R, null);
        Arrays.fill(this.G_564_y, null);
        this.P_1922_E = 0;
        this.u_1723_Y = 0;
    }

    public int J_1907_R() {
        return this.u_1723_Y;
    }
}


