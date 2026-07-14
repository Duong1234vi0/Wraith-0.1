/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.K_4573_Z;

public class n_226_d
implements K_4573_Z {
    public static final Codec<n_226_d> n_1700_B;
    public static final n_226_d J_1907_R;

    static {
        J_1907_R = new n_226_d();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}

