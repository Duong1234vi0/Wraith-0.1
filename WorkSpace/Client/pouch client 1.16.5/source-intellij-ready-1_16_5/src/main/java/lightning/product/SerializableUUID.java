/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Arrays;
import java.util.UUID;
import lightning.product.j_3341_s;

public final class SerializableUUID {
    public static final Codec<UUID> n_1700_B = Codec.INT_STREAM.comapFlatMap(p_239778_0_ -> j_3341_s.n_1700_B(p_239778_0_, 4).map(SerializableUUID::n_1700_B), p_239780_0_ -> Arrays.stream(SerializableUUID.n_1700_B(p_239780_0_)));

    public static UUID n_1700_B(int[] bits) {
        return new UUID((long)bits[0] << 32 | (long)bits[1] & 0xFFFFFFFFL, (long)bits[2] << 32 | (long)bits[3] & 0xFFFFFFFFL);
    }

    public static int[] n_1700_B(UUID uuid) {
        long i = uuid.getMostSignificantBits();
        long j = uuid.getLeastSignificantBits();
        return SerializableUUID.n_1700_B(i, j);
    }

    private static int[] n_1700_B(long most, long least) {
        return new int[]{(int)(most >> 32), (int)most, (int)(least >> 32), (int)least};
    }
}


