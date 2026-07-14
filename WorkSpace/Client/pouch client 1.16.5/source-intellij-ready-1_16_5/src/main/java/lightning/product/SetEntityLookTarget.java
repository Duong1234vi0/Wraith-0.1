/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.function.Predicate;
import lightning.product.E_4668_a;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.Z_749_F;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.o_4722_d;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class SetEntityLookTarget
extends Behavior<r_4811_B> {
    private final Predicate<r_4811_B> n_1700_B;
    private final float R_4764_Y;

    public SetEntityLookTarget(Z_749_F classification, float distance) {
        this((r_4811_B p_220514_1_) -> classification.equals(p_220514_1_.f_4016_n().P_1922_E()), distance);
    }

    public SetEntityLookTarget(t_5_h<?> type, float distance) {
        this((r_4811_B p_220518_1_) -> type.equals(p_220518_1_.f_4016_n()), distance);
    }

    public SetEntityLookTarget(float distance) {
        this((r_4811_B p_233953_0_) -> true, distance);
    }

    public SetEntityLookTarget(Predicate<r_4811_B> targetPredicate, float distance) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.w_1484_f, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = targetPredicate;
        this.R_4764_Y = distance * distance;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        return owner.y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f).get().stream().anyMatch(this.n_1700_B);
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        E_4668_a<?> brain = entityIn.y_1945_D();
        brain.R_4764_Y(MemoryModuleType.w_1484_f).ifPresent(p_220515_3_ -> p_220515_3_.stream().filter(this.n_1700_B).filter(target -> target.G_564_y(entityIn) <= (double)this.R_4764_Y).findFirst().ifPresent(target -> brain.n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d((N_4263_v)target, true))));
    }
}


