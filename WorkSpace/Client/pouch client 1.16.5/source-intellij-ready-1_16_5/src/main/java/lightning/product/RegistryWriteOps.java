/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 */
package lightning.product;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import java.util.Optional;
import lightning.product.V_3137_a;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.DelegatingOps;
import lightning.product.WritableRegistry;
import lightning.product.r_4097_j;

public class RegistryWriteOps<T>
extends DelegatingOps<T> {
    private final r_4097_j J_1907_R;

    public static <T> RegistryWriteOps<T> n_1700_B(DynamicOps<T> ops, r_4097_j dynamicRegistries) {
        return new RegistryWriteOps<T>(ops, dynamicRegistries);
    }

    private RegistryWriteOps(DynamicOps<T> ops, r_4097_j dynamicRegistries) {
        super(ops);
        this.J_1907_R = dynamicRegistries;
    }

    protected <E> DataResult<T> n_1700_B(E instance, T prefix, f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> mapCodec) {
        WritableRegistry mutableregistry;
        Optional<f_2392_k<E>> optional1;
        Optional optional = this.J_1907_R.n_1700_B(registryKey);
        if (optional.isPresent() && (optional1 = (mutableregistry = optional.get()).R_4764_Y(instance)).isPresent()) {
            f_2392_k<E> registrykey = optional1.get();
            return g_2336_b.n_1700_B.encode((Object)registrykey.n_1700_B(), this.n_1700_B, prefix);
        }
        return mapCodec.encode(instance, (DynamicOps)this, prefix);
    }
}


