/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Lifecycle
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.serialization.Lifecycle;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lightning.product.V_3137_a;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.v_1758_J;

public class DefaultedRegistry<T>
extends v_1758_J<T> {
    private final g_2336_b RealmsConfirmScreen;
    private T RealmsCreateRealmScreen;

    public DefaultedRegistry(String defaultValueKey, f_2392_k<? extends V_3137_a<T>> registryKey, Lifecycle lifecycle) {
        super(registryKey, lifecycle);
        this.RealmsConfirmScreen = new g_2336_b(defaultValueKey);
    }

    @Override
    public <V extends T> V n_1700_B(int id, f_2392_k<T> name, V instance, Lifecycle lifecycle) {
        if (this.RealmsConfirmScreen.equals(name.n_1700_B())) {
            this.RealmsCreateRealmScreen = instance;
        }
        return super.n_1700_B(id, name, instance, lifecycle);
    }

    @Override
    public int n_1700_B(@Nullable T value) {
        int i = super.n_1700_B(value);
        return i == -1 ? super.n_1700_B(this.RealmsCreateRealmScreen) : i;
    }

    @Override
    @Nonnull
    public g_2336_b J_1907_R(T value) {
        g_2336_b resourcelocation = super.J_1907_R(value);
        return resourcelocation == null ? this.RealmsConfirmScreen : resourcelocation;
    }

    @Override
    @Nonnull
    public T n_1700_B(@Nullable g_2336_b name) {
        Object t = super.n_1700_B(name);
        return t == null ? this.RealmsCreateRealmScreen : t;
    }

    @Override
    public Optional<T> J_1907_R(@Nullable g_2336_b id) {
        return Optional.ofNullable(super.n_1700_B(id));
    }

    @Override
    @Nonnull
    public T n_1700_B(int value) {
        Object t = super.n_1700_B(value);
        return t == null ? this.RealmsCreateRealmScreen : t;
    }

    @Override
    @Nonnull
    public T n_1700_B(Random random) {
        Object t = super.n_1700_B(random);
        return t == null ? this.RealmsCreateRealmScreen : t;
    }

    public g_2336_b n_1700_B() {
        return this.RealmsConfirmScreen;
    }
}


