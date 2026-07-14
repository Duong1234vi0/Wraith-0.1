/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Lifecycle
 */
package lightning.product;

import com.mojang.serialization.Lifecycle;
import java.util.OptionalInt;
import lightning.product.V_3137_a;
import lightning.product.f_2392_k;

public abstract class WritableRegistry<T>
extends V_3137_a<T> {
    public WritableRegistry(f_2392_k<? extends V_3137_a<T>> registryKey, Lifecycle lifecycle) {
        super(registryKey, lifecycle);
    }

    public abstract <V extends T> V n_1700_B(int var1, f_2392_k<T> var2, V var3, Lifecycle var4);

    public abstract <V extends T> V n_1700_B(f_2392_k<T> var1, V var2, Lifecycle var3);

    public abstract <V extends T> V n_1700_B(OptionalInt var1, f_2392_k<T> var2, V var3, Lifecycle var4);
}


