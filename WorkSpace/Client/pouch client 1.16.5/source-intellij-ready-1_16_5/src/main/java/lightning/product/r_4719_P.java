/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.T_2915_h;
import lightning.product.StructureProcessor;
import lightning.product.a_2886_t;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.q_4293_E;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;

public class r_4719_P
extends StructureProcessor {
    public static final Codec<r_4719_P> n_1700_B = K_4074_S.J_1907_R.xmap(q_4293_E.n_1700_B::J_1907_R, T_2915_h::multiplayerClientSuggestionProvider).listOf().fieldOf("blocks").xmap(r_4719_P::new, p_237074_0_ -> p_237074_0_.P_1922_E).codec();
    public static final r_4719_P J_1907_R = new r_4719_P((List<T_2915_h>)ImmutableList.of((Object)a_3742_W.l_14_c));
    public static final r_4719_P R_4764_Y = new r_4719_P((List<T_2915_h>)ImmutableList.of((Object)a_3742_W.n_1700_B));
    public static final r_4719_P G_564_y = new r_4719_P((List<T_2915_h>)ImmutableList.of((Object)a_3742_W.n_1700_B, (Object)a_3742_W.l_14_c));
    private final ImmutableList<T_2915_h> P_1922_E;

    public r_4719_P(List<T_2915_h> blocks) {
        this.P_1922_E = ImmutableList.copyOf(blocks);
    }

    @Override
    @Nullable
    public a_2886_t.J_1907_R n_1700_B(T_1316_M p_230386_1_, c_1514_x p_230386_2_, c_1514_x p_230386_3_, a_2886_t.J_1907_R p_230386_4_, a_2886_t.J_1907_R p_230386_5_, w_1748_S p_230386_6_) {
        return this.P_1922_E.contains((Object)p_230386_5_.J_1907_R.J_1907_R()) ? null : p_230386_5_;
    }

    @Override
    protected StructureProcessorType<?> n_1700_B() {
        return StructureProcessorType.n_1700_B;
    }
}


