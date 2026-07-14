/*
 * Decompiled with CFR 0.152.
 */
package net.minecraftforge.fml.common.registry;

import lightning.product.g_2336_b;

public interface RegistryDelegate<T> {
    public T get();

    public g_2336_b name();

    public Class<T> type();
}

