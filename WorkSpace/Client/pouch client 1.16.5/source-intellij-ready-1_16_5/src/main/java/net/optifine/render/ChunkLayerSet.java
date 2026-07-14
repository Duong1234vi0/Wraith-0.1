/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.render;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import lightning.product.o_2576_A;

public class ChunkLayerSet
implements Set<o_2576_A> {
    private boolean[] layers = new boolean[o_2576_A.N_2525_X.length];

    @Override
    public boolean add(o_2576_A renderType) {
        this.layers[renderType.G_564_y()] = true;
        return false;
    }

    public boolean contains(o_2576_A renderType) {
        return this.layers[renderType.G_564_y()];
    }

    @Override
    public boolean contains(Object obj) {
        return obj instanceof o_2576_A ? this.contains((o_2576_A)obj) : false;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public Iterator<o_2576_A> iterator() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean addAll(Collection<? extends o_2576_A> c) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported");
    }
}

