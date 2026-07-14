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
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_1316_M;
import lightning.product.StructureProcessor;
import lightning.product.ProcessorRule;
import lightning.product.a_2886_t;
import lightning.product.c_1514_x;
import lightning.product.u_530_F;
import lightning.product.StructureProcessorType;
import lightning.product.w_1748_S;

public class RuleProcessor
extends StructureProcessor {
    public static final Codec<RuleProcessor> n_1700_B = ProcessorRule.n_1700_B.listOf().fieldOf("rules").xmap(RuleProcessor::new, p_237126_0_ -> p_237126_0_.J_1907_R).codec();
    private final ImmutableList<ProcessorRule> J_1907_R;

    public RuleProcessor(List<? extends ProcessorRule> rules) {
        this.J_1907_R = ImmutableList.copyOf(rules);
    }

    @Override
    @Nullable
    public a_2886_t.J_1907_R n_1700_B(T_1316_M p_230386_1_, c_1514_x p_230386_2_, c_1514_x p_230386_3_, a_2886_t.J_1907_R p_230386_4_, a_2886_t.J_1907_R p_230386_5_, w_1748_S p_230386_6_) {
        Random random = new Random(u_530_F.n_1700_B(p_230386_5_.n_1700_B));
        K_4074_S blockstate = p_230386_1_.getBlockState(p_230386_5_.n_1700_B);
        for (ProcessorRule ruleentry : this.J_1907_R) {
            if (!ruleentry.n_1700_B(p_230386_5_.J_1907_R, blockstate, p_230386_4_.n_1700_B, p_230386_5_.n_1700_B, p_230386_3_, random)) continue;
            return new a_2886_t.J_1907_R(p_230386_5_.n_1700_B, ruleentry.n_1700_B(), ruleentry.J_1907_R());
        }
        return p_230386_5_;
    }

    @Override
    protected StructureProcessorType<?> n_1700_B() {
        return StructureProcessorType.P_1922_E;
    }
}


