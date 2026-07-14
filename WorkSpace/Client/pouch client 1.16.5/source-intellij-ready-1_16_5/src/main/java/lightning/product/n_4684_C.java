/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 */
package lightning.product;

import com.mojang.datafixers.util.Either;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import java.util.List;
import java.util.function.Supplier;
import lightning.product.F_877_l;
import lightning.product.RegistryWriteOps;
import lightning.product.V_3137_a;
import lightning.product.f_2392_k;

public final class n_4684_C<E>
implements Codec<Supplier<E>> {
    private final f_2392_k<? extends V_3137_a<E>> n_1700_B;
    private final Codec<E> J_1907_R;
    private final boolean R_4764_Y;

    public static <E> n_4684_C<E> n_1700_B(f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> codec) {
        return n_4684_C.n_1700_B(registryKey, codec, true);
    }

    public static <E> Codec<List<Supplier<E>>> J_1907_R(f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> registryKeyCodec) {
        return Codec.either((Codec)n_4684_C.n_1700_B(registryKey, registryKeyCodec, false).listOf(), (Codec)registryKeyCodec.xmap(value -> () -> value, Supplier::get).listOf()).xmap(either -> (List)either.map(left -> left, right -> right), Either::left);
    }

    private static <E> n_4684_C<E> n_1700_B(f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> registryKeyCodec, boolean allowInlineDefinitions) {
        return new n_4684_C<E>(registryKey, registryKeyCodec, allowInlineDefinitions);
    }

    private n_4684_C(f_2392_k<? extends V_3137_a<E>> registryKey, Codec<E> registryKeyCodec, boolean allowInlineDefinitions) {
        this.n_1700_B = registryKey;
        this.J_1907_R = registryKeyCodec;
        this.R_4764_Y = allowInlineDefinitions;
    }

    public <T> DataResult<T> n_1700_B(Supplier<E> p_encode_1_, DynamicOps<T> p_encode_2_, T p_encode_3_) {
        return p_encode_2_ instanceof RegistryWriteOps ? ((RegistryWriteOps)p_encode_2_).n_1700_B(p_encode_1_.get(), p_encode_3_, this.n_1700_B, this.J_1907_R) : this.J_1907_R.encode(p_encode_1_.get(), p_encode_2_, p_encode_3_);
    }

    public <T> DataResult<Pair<Supplier<E>, T>> decode(DynamicOps<T> p_decode_1_, T p_decode_2_) {
        return p_decode_1_ instanceof F_877_l ? ((F_877_l)p_decode_1_).n_1700_B(p_decode_2_, this.n_1700_B, this.J_1907_R, this.R_4764_Y) : this.J_1907_R.decode(p_decode_1_, p_decode_2_).map(elementPair -> elementPair.mapFirst(element -> () -> element));
    }

    public String toString() {
        return "RegistryFileCodec[" + String.valueOf(this.n_1700_B) + " " + String.valueOf(this.J_1907_R) + "]";
    }

    public /* synthetic */ DataResult encode(Object object, DynamicOps dynamicOps, Object object2) {
        return this.n_1700_B((Supplier)object, dynamicOps, object2);
    }
}


