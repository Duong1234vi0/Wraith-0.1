/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.serialization.Codec;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.T_1316_M;
import lightning.product.StructureProcessor;
import lightning.product.a_2886_t;
import lightning.product.c_1514_x;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;

public class d_862_x
extends StructureProcessor {
    public static final Codec<d_862_x> n_1700_B = Codec.FLOAT.fieldOf("integrity").orElse((Object)Float.valueOf(1.0f)).xmap(d_862_x::new, p_237078_0_ -> Float.valueOf(p_237078_0_.J_1907_R)).codec();
    private final float J_1907_R;

    public d_862_x(float integrity) {
        this.J_1907_R = integrity;
    }

    @Override
    @Nullable
    public a_2886_t.J_1907_R n_1700_B(T_1316_M p_230386_1_, c_1514_x p_230386_2_, c_1514_x p_230386_3_, a_2886_t.J_1907_R p_230386_4_, a_2886_t.J_1907_R p_230386_5_, w_1748_S p_230386_6_) {
        Random random = p_230386_6_.J_1907_R(p_230386_5_.n_1700_B);
        return !(this.J_1907_R >= 1.0f) && !(random.nextFloat() <= this.J_1907_R) ? null : p_230386_5_;
    }

    @Override
    protected StructureProcessorType<?> n_1700_B() {
        return StructureProcessorType.J_1907_R;
    }
}


