/*
 * Decompiled with CFR 0.152.
 */
package net.minecraftforge.registries;

import lightning.product.g_2336_b;

public interface IRegistryDelegate<T> {
    public T get();

    public g_2336_b name();

    public Class<T> type();
}

