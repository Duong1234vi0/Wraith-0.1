/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.MapLike
 *  com.mojang.serialization.RecordBuilder
 */
package lightning.product;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;
import java.util.stream.Stream;
import lightning.product.F_877_l;
import lightning.product.V_3137_a;
import lightning.product.f_2392_k;

public final class P_1103_o<E>
extends MapCodec<V_3137_a<E>> {
    private final f_2392_k<? extends V_3137_a<E>> n_1700_B;

    public static <E> P_1103_o<E> n_1700_B(f_2392_k<? extends V_3137_a<E>> registryKey) {
        return new P_1103_o<E>(registryKey);
    }

    private P_1103_o(f_2392_k<? extends V_3137_a<E>> registryKey) {
        this.n_1700_B = registryKey;
    }

    public <T> RecordBuilder<T> n_1700_B(V_3137_a<E> p_encode_1_, DynamicOps<T> p_encode_2_, RecordBuilder<T> p_encode_3_) {
        return p_encode_3_;
    }

    public <T> DataResult<V_3137_a<E>> decode(DynamicOps<T> p_decode_1_, MapLike<T> p_decode_2_) {
        return p_decode_1_ instanceof F_877_l ? ((F_877_l)p_decode_1_).n_1700_B(this.n_1700_B) : DataResult.error((String)"Not a registry ops");
    }

    public String toString() {
        return "RegistryLookupCodec[" + String.valueOf(this.n_1700_B) + "]";
    }

    public <T> Stream<T> keys(DynamicOps<T> p_keys_1_) {
        return Stream.empty();
    }

    public /* synthetic */ RecordBuilder encode(Object object, DynamicOps dynamicOps, RecordBuilder recordBuilder) {
        return this.n_1700_B((V_3137_a)object, dynamicOps, recordBuilder);
    }
}

