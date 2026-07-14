/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.objects.ObjectArrays
 */
package lightning.product;

import it.unimi.dsi.fastutil.objects.ObjectArrays;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class t_4022_o<T>
extends AbstractSet<T> {
    private final Comparator<T> n_1700_B;
    private T[] J_1907_R;
    private int R_4764_Y;

    private t_4022_o(int p_i225697_1_, Comparator<T> p_i225697_2_) {
        this.n_1700_B = p_i225697_2_;
        if (p_i225697_1_ < 0) {
            throw new IllegalArgumentException("Initial capacity (" + p_i225697_1_ + ") is negative");
        }
        this.J_1907_R = t_4022_o.n_1700_B(new Object[p_i225697_1_]);
    }

    public static <T extends Comparable<T>> t_4022_o<T> n_1700_B(int p_226172_0_) {
        return new t_4022_o(p_226172_0_, Comparator.naturalOrder());
    }

    private static <T> T[] n_1700_B(Object[] p_226177_0_) {
        return p_226177_0_;
    }

    private int J_1907_R(T p_226182_1_) {
        return Arrays.binarySearch(this.J_1907_R, 0, this.R_4764_Y, p_226182_1_, this.n_1700_B);
    }

    private static int J_1907_R(int p_226179_0_) {
        return -p_226179_0_ - 1;
    }

    @Override
    public boolean add(T p_add_1_) {
        int i = this.J_1907_R(p_add_1_);
        if (i >= 0) {
            return false;
        }
        int j = t_4022_o.J_1907_R(i);
        this.n_1700_B(p_add_1_, j);
        return true;
    }

    private void R_4764_Y(int p_226181_1_) {
        if (p_226181_1_ > this.J_1907_R.length) {
            if (this.J_1907_R != ObjectArrays.DEFAULT_EMPTY_ARRAY) {
                p_226181_1_ = (int)Math.max(Math.min((long)this.J_1907_R.length + (long)(this.J_1907_R.length >> 1), 0x7FFFFFF7L), (long)p_226181_1_);
            } else if (p_226181_1_ < 10) {
                p_226181_1_ = 10;
            }
            Object[] aobject = new Object[p_226181_1_];
            System.arraycopy(this.J_1907_R, 0, aobject, 0, this.R_4764_Y);
            this.J_1907_R = t_4022_o.n_1700_B(aobject);
        }
    }

    private void n_1700_B(T p_226176_1_, int p_226176_2_) {
        this.R_4764_Y(this.R_4764_Y + 1);
        if (p_226176_2_ != this.R_4764_Y) {
            System.arraycopy(this.J_1907_R, p_226176_2_, this.J_1907_R, p_226176_2_ + 1, this.R_4764_Y - p_226176_2_);
        }
        this.J_1907_R[p_226176_2_] = p_226176_1_;
        ++this.R_4764_Y;
    }

    private void G_564_y(int p_226183_1_) {
        --this.R_4764_Y;
        if (p_226183_1_ != this.R_4764_Y) {
            System.arraycopy(this.J_1907_R, p_226183_1_ + 1, this.J_1907_R, p_226183_1_, this.R_4764_Y - p_226183_1_);
        }
        this.J_1907_R[this.R_4764_Y] = null;
    }

    private T P_1922_E(int p_226184_1_) {
        return this.J_1907_R[p_226184_1_];
    }

    public T n_1700_B(T p_226175_1_) {
        int i = this.J_1907_R(p_226175_1_);
        if (i >= 0) {
            return this.P_1922_E(i);
        }
        this.n_1700_B(p_226175_1_, t_4022_o.J_1907_R(i));
        return p_226175_1_;
    }

    @Override
    public boolean remove(Object p_remove_1_) {
        int i = this.J_1907_R(p_remove_1_);
        if (i >= 0) {
            this.G_564_y(i);
            return true;
        }
        return false;
    }

    public T n_1700_B() {
        return this.P_1922_E(0);
    }

    @Override
    public boolean contains(Object p_contains_1_) {
        int i = this.J_1907_R(p_contains_1_);
        return i >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new n_1700_B();
    }

    @Override
    public int size() {
        return this.R_4764_Y;
    }

    @Override
    public Object[] toArray() {
        return (Object[])this.J_1907_R.clone();
    }

    @Override
    public <U> U[] toArray(U[] p_toArray_1_) {
        if (p_toArray_1_.length < this.R_4764_Y) {
            return Arrays.copyOf(this.J_1907_R, this.R_4764_Y, p_toArray_1_.getClass());
        }
        System.arraycopy(this.J_1907_R, 0, p_toArray_1_, 0, this.R_4764_Y);
        if (p_toArray_1_.length > this.R_4764_Y) {
            p_toArray_1_[this.R_4764_Y] = null;
        }
        return p_toArray_1_;
    }

    @Override
    public void clear() {
        Arrays.fill(this.J_1907_R, 0, this.R_4764_Y, null);
        this.R_4764_Y = 0;
    }

    @Override
    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ instanceof t_4022_o) {
            t_4022_o sortedarrayset = (t_4022_o)p_equals_1_;
            if (this.n_1700_B.equals(sortedarrayset.n_1700_B)) {
                return this.R_4764_Y == sortedarrayset.R_4764_Y && Arrays.equals(this.J_1907_R, sortedarrayset.J_1907_R);
            }
        }
        return super.equals(p_equals_1_);
    }

    class n_1700_B
    implements Iterator<T> {
        private int J_1907_R;
        private int R_4764_Y = -1;

        private n_1700_B() {
        }

        @Override
        public boolean hasNext() {
            return this.J_1907_R < t_4022_o.this.R_4764_Y;
        }

        @Override
        public T next() {
            if (this.J_1907_R >= t_4022_o.this.R_4764_Y) {
                throw new NoSuchElementException();
            }
            this.R_4764_Y = this.J_1907_R++;
            return t_4022_o.this.J_1907_R[this.R_4764_Y];
        }

        @Override
        public void remove() {
            if (this.R_4764_Y == -1) {
                throw new IllegalStateException();
            }
            t_4022_o.this.G_564_y(this.R_4764_Y);
            --this.J_1907_R;
            this.R_4764_Y = -1;
        }
    }
}

