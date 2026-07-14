/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.Keyable
 */
package lightning.product;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Keyable;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface E_4700_p {
    public String n_1700_B();

    public static <E extends Enum<E>> Codec<E> n_1700_B(Supplier<E[]> elementSupplier, Function<? super String, ? extends E> namingFunction) {
        Enum[] ae = (Enum[])elementSupplier.get();
        return E_4700_p.n_1700_B(rec$ -> ((Enum)rec$).ordinal(), enumId -> ae[enumId], namingFunction);
    }

    public static <E extends E_4700_p> Codec<E> n_1700_B(final ToIntFunction<E> elementSupplier, final IntFunction<E> selectorFunction, final Function<? super String, ? extends E> namingFunction) {
        return new Codec<E>(){

            public <T> DataResult<T> n_1700_B(E p_encode_1_, DynamicOps<T> p_encode_2_, T p_encode_3_) {
                return p_encode_2_.compressMaps() ? p_encode_2_.mergeToPrimitive(p_encode_3_, p_encode_2_.createInt(elementSupplier.applyAsInt(p_encode_1_))) : p_encode_2_.mergeToPrimitive(p_encode_3_, p_encode_2_.createString(p_encode_1_.n_1700_B()));
            }

            public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> p_decode_1_, T p_decode_2_) {
                return p_decode_1_.compressMaps() ? p_decode_1_.getNumberValue(p_decode_2_).flatMap(id -> Optional.ofNullable((E_4700_p)selectorFunction.apply(id.intValue())).map(DataResult::success).orElseGet(() -> DataResult.error((String)("Unknown element id: " + String.valueOf(id))))).map(serializable -> Pair.of((Object)serializable, (Object)p_decode_1_.empty())) : p_decode_1_.getStringValue(p_decode_2_).flatMap(name -> Optional.ofNullable((E_4700_p)namingFunction.apply(name)).map(DataResult::success).orElseGet(() -> DataResult.error((String)("Unknown element name: " + name)))).map(serializable -> Pair.of((Object)serializable, (Object)p_decode_1_.empty()));
            }

            public String toString() {
                return "StringRepresentable[" + String.valueOf(elementSupplier) + "]";
            }

            public /* synthetic */ DataResult encode(Object object, DynamicOps dynamicOps, Object object2) {
                return this.n_1700_B((E_4700_p)object, dynamicOps, object2);
            }
        };
    }

    public static Keyable n_1700_B(final E_4700_p[] serializables) {
        return new Keyable(){

            public <T> Stream<T> keys(DynamicOps<T> p_keys_1_) {
                return p_keys_1_.compressMaps() ? IntStream.range(0, serializables.length).mapToObj(arg_0 -> p_keys_1_.createInt(arg_0)) : Arrays.stream(serializables).map(E_4700_p::n_1700_B).map(arg_0 -> p_keys_1_.createString(arg_0));
            }
        };
    }
}

