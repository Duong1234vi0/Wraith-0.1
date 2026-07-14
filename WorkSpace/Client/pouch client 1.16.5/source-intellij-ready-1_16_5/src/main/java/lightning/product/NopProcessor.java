/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.serialization.Codec;
import javax.annotation.Nullable;
import lightning.product.T_1316_M;
import lightning.product.StructureProcessor;
import lightning.product.a_2886_t;
import lightning.product.c_1514_x;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;

public class NopProcessor
extends StructureProcessor {
    public static final Codec<NopProcessor> n_1700_B;
    public static final NopProcessor J_1907_R;

    private NopProcessor() {
    }

    @Override
    @Nullable
    public a_2886_t.J_1907_R n_1700_B(T_1316_M p_230386_1_, c_1514_x p_230386_2_, c_1514_x p_230386_3_, a_2886_t.J_1907_R p_230386_4_, a_2886_t.J_1907_R p_230386_5_, w_1748_S p_230386_6_) {
        return p_230386_5_;
    }

    @Override
    protected StructureProcessorType<?> n_1700_B() {
        return StructureProcessorType.u_1723_Y;
    }

    static {
        J_1907_R = new NopProcessor();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}


