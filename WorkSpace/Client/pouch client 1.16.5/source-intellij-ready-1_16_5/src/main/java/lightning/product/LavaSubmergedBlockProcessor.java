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
import lightning.product.T_2915_h;
import lightning.product.StructureProcessor;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;

public class LavaSubmergedBlockProcessor
extends StructureProcessor {
    public static final Codec<LavaSubmergedBlockProcessor> n_1700_B;
    public static final LavaSubmergedBlockProcessor J_1907_R;

    @Override
    @Nullable
    public a_2886_t.J_1907_R n_1700_B(T_1316_M p_230386_1_, c_1514_x p_230386_2_, c_1514_x p_230386_3_, a_2886_t.J_1907_R p_230386_4_, a_2886_t.J_1907_R p_230386_5_, w_1748_S p_230386_6_) {
        c_1514_x blockpos = p_230386_5_.n_1700_B;
        boolean flag = p_230386_1_.getBlockState(blockpos).n_1700_B(a_3742_W.H_2857_Y);
        return flag && !T_2915_h.n_1700_B(p_230386_5_.J_1907_R.s_956_w(p_230386_1_, blockpos)) ? new a_2886_t.J_1907_R(blockpos, a_3742_W.H_2857_Y.multiplayerClientSuggestionProvider(), p_230386_5_.R_4764_Y) : p_230386_5_;
    }

    @Override
    protected StructureProcessorType<?> n_1700_B() {
        return StructureProcessorType.t_148_a;
    }

    static {
        J_1907_R = new LavaSubmergedBlockProcessor();
        n_1700_B = Codec.unit(() -> J_1907_R);
    }
}


