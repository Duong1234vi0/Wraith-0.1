/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.Y_1387_d;
import lightning.product.WorldgenRandom;
import lightning.product.g_4497_s;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.JigsawConfiguration;
import lightning.product.z_1753_f;

public class BastionFeature
extends g_4497_s {
    public BastionFeature(Codec<JigsawConfiguration> p_i231927_1_) {
        super(p_i231927_1_, 33, false, false);
    }

    @Override
    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, JigsawConfiguration p_230363_10_) {
        return p_230363_5_.nextInt(5) >= 2;
    }
}


