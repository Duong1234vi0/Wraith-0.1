/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.codecs.RecordCodecBuilder
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;
import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.PosAlwaysTrueTest;
import lightning.product.RuleTest;
import lightning.product.K_4074_S;
import lightning.product.U_2912_j;
import lightning.product.c_1514_x;
import lightning.product.PosRuleTest;

public class ProcessorRule {
    public static final Codec<ProcessorRule> n_1700_B = RecordCodecBuilder.create(p_237111_0_ -> p_237111_0_.group((App)RuleTest.R_4764_Y.fieldOf("input_predicate").forGetter(p_237116_0_ -> p_237116_0_.J_1907_R), (App)RuleTest.R_4764_Y.fieldOf("location_predicate").forGetter(p_237115_0_ -> p_237115_0_.R_4764_Y), (App)PosRuleTest.R_4764_Y.optionalFieldOf("position_predicate", (Object)PosAlwaysTrueTest.J_1907_R).forGetter(p_237114_0_ -> p_237114_0_.G_564_y), (App)K_4074_S.J_1907_R.fieldOf("output_state").forGetter(p_237113_0_ -> p_237113_0_.P_1922_E), (App)U_2912_j.n_1700_B.optionalFieldOf("output_nbt").forGetter(p_237112_0_ -> Optional.ofNullable(p_237112_0_.u_1723_Y))).apply((Applicative)p_237111_0_, ProcessorRule::new));
    private final RuleTest J_1907_R;
    private final RuleTest R_4764_Y;
    private final PosRuleTest G_564_y;
    private final K_4074_S P_1922_E;
    @Nullable
    private final U_2912_j u_1723_Y;

    public ProcessorRule(RuleTest inputPredicate, RuleTest locationPredicate, K_4074_S outputState) {
        this(inputPredicate, locationPredicate, PosAlwaysTrueTest.J_1907_R, outputState, Optional.empty());
    }

    public ProcessorRule(RuleTest p_i232117_1_, RuleTest p_i232117_2_, PosRuleTest p_i232117_3_, K_4074_S p_i232117_4_) {
        this(p_i232117_1_, p_i232117_2_, p_i232117_3_, p_i232117_4_, Optional.empty());
    }

    public ProcessorRule(RuleTest p_i232118_1_, RuleTest p_i232118_2_, PosRuleTest p_i232118_3_, K_4074_S p_i232118_4_, Optional<U_2912_j> p_i232118_5_) {
        this.J_1907_R = p_i232118_1_;
        this.R_4764_Y = p_i232118_2_;
        this.G_564_y = p_i232118_3_;
        this.P_1922_E = p_i232118_4_;
        this.u_1723_Y = p_i232118_5_.orElse(null);
    }

    public boolean n_1700_B(K_4074_S p_237110_1_, K_4074_S p_237110_2_, c_1514_x p_237110_3_, c_1514_x p_237110_4_, c_1514_x p_237110_5_, Random p_237110_6_) {
        return this.J_1907_R.n_1700_B(p_237110_1_, p_237110_6_) && this.R_4764_Y.n_1700_B(p_237110_2_, p_237110_6_) && this.G_564_y.n_1700_B(p_237110_3_, p_237110_4_, p_237110_5_, p_237110_6_);
    }

    public K_4074_S n_1700_B() {
        return this.P_1922_E;
    }

    @Nullable
    public U_2912_j J_1907_R() {
        return this.u_1723_Y;
    }
}


