/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.function.Supplier;
import lightning.product.StructureFeature;
import lightning.product.V_3137_a;
import lightning.product.V_4739_Y;
import lightning.product.Y_1387_d;
import lightning.product.b_2085_h;
import lightning.product.StructureStart;
import lightning.product.WorldgenRandom;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.n_4684_C;
import lightning.product.r_4097_j;
import lightning.product.FeatureConfiguration;
import lightning.product.z_1753_f;

public class ConfiguredStructureFeature<FC extends FeatureConfiguration, F extends StructureFeature<FC>> {
    public static final Codec<ConfiguredStructureFeature<?, ?>> n_1700_B = V_3137_a.M_1641_O.dispatch(p_236271_0_ -> p_236271_0_.G_564_y, StructureFeature::u_1723_Y);
    public static final Codec<Supplier<ConfiguredStructureFeature<?, ?>>> J_1907_R = n_4684_C.n_1700_B(V_3137_a.h_4320_q, n_1700_B);
    public static final Codec<List<Supplier<ConfiguredStructureFeature<?, ?>>>> R_4764_Y = n_4684_C.J_1907_R(V_3137_a.h_4320_q, n_1700_B);
    public final F G_564_y;
    public final FC P_1922_E;

    public ConfiguredStructureFeature(F p_i231937_1_, FC p_i231937_2_) {
        this.G_564_y = p_i231937_1_;
        this.P_1922_E = p_i231937_2_;
    }

    public StructureStart<?> n_1700_B(r_4097_j p_242771_1_, z_1753_f p_242771_2_, BiomeSource p_242771_3_, b_2085_h p_242771_4_, long p_242771_5_, Y_1387_d p_242771_7_, k_594_Q p_242771_8_, int p_242771_9_, V_4739_Y p_242771_10_) {
        return ((StructureFeature)this.G_564_y).n_1700_B(p_242771_1_, p_242771_2_, p_242771_3_, p_242771_4_, p_242771_5_, p_242771_7_, p_242771_8_, p_242771_9_, new WorldgenRandom(), p_242771_10_, this.P_1922_E);
    }
}


