/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

public class FastArrayList<E> {
    private E[] array;
    private int size;

    public FastArrayList(int capacity) {
        this.array = new Object[capacity];
    }

    public void add(E element) {
        this.array[this.size] = element;
        ++this.size;
    }

    public E get(int index) {
        return this.array[index];
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        this.size = 0;
    }
}

