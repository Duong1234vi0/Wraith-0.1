/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.Lifecycle
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import lightning.product.F_877_l;
import lightning.product.V_3137_a;
import lightning.product.f_2392_k;
import lightning.product.v_1758_J;

public final class M_3100_a<E>
implements Codec<v_1758_J<E>> {
    private final Codec<v_1758_J<E>> n_1700_B;
    private final f_2392_k<? extends V_3137_a<E>> J_1907_R;
    private final Codec<E> R_4764_Y;

    public static <E> M_3100_a<E> n_1700_B(f_2392_k<? extends V_3137_a<E>> registryKey, Lifecycle lifecycle, Codec<E> rawCodec) {
        return new M_3100_a<E>(registryKey, lifecycle, rawCodec);
    }

    private M_3100_a(f_2392_k<? extends V_3137_a<E>> registryKey, Lifecycle lifecycle, Codec<E> rawCodec) {
        this.n_1700_B = v_1758_J.R_4764_Y(registryKey, lifecycle, rawCodec);
        this.J_1907_R = registryKey;
        this.R_4764_Y = rawCodec;
    }

    public <T> DataResult<T> n_1700_B(v_1758_J<E> p_encode_1_, DynamicOps<T> p_encode_2_, T p_encode_3_) {
        return this.n_1700_B.encode(p_encode_1_, p_encode_2_, p_encode_3_);
    }

    public <T> DataResult<Pair<v_1758_J<E>, T>> decode(DynamicOps<T> p_decode_1_, T p_decode_2_) {
        DataResult dataresult = this.n_1700_B.decode(p_decode_1_, p_decode_2_);
        return p_decode_1_ instanceof F_877_l ? dataresult.flatMap(registryPair -> ((F_877_l)p_decode_1_).n_1700_B((v_1758_J)registryPair.getFirst(), this.J_1907_R, this.R_4764_Y).map(registry -> Pair.of((Object)registry, (Object)registryPair.getSecond()))) : dataresult;
    }

    public String toString() {
        return "RegistryDataPackCodec[" + String.valueOf(this.n_1700_B) + " " + String.valueOf(this.J_1907_R) + " " + String.valueOf(this.R_4764_Y) + "]";
    }

    public /* synthetic */ DataResult encode(Object object, DynamicOps dynamicOps, Object object2) {
        return this.n_1700_B((v_1758_J)object, dynamicOps, object2);
    }
}

