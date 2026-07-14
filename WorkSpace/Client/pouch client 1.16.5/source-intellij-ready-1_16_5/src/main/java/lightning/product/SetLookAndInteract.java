/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import lightning.product.E_4668_a;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.o_4722_d;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class SetLookAndInteract
extends Behavior<r_4811_B> {
    private final t_5_h<?> n_1700_B;
    private final int R_4764_Y;
    private final Predicate<r_4811_B> G_564_y;
    private final Predicate<r_4811_B> P_1922_E;

    public SetLookAndInteract(t_5_h<?> p_i50347_1_, int distance, Predicate<r_4811_B> p_i50347_3_, Predicate<r_4811_B> p_i50347_4_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.t_1786_h, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.w_1484_f, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i50347_1_;
        this.R_4764_Y = distance * distance;
        this.G_564_y = p_i50347_4_;
        this.P_1922_E = p_i50347_3_;
    }

    public SetLookAndInteract(t_5_h<?> p_i50348_1_, int distance) {
        this(p_i50348_1_, distance, p_220528_0_ -> true, p_220531_0_ -> true);
    }

    @Override
    public boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        return this.P_1922_E.test(owner) && this.J_1907_R(owner).stream().anyMatch(this::n_1700_B);
    }

    @Override
    public void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        super.G_564_y(worldIn, entityIn, gameTimeIn);
        E_4668_a<?> brain = entityIn.y_1945_D();
        brain.R_4764_Y(MemoryModuleType.w_1484_f).ifPresent(visibleMobs -> visibleMobs.stream().filter(mob -> mob.G_564_y(entityIn) <= (double)this.R_4764_Y).filter(this::n_1700_B).findFirst().ifPresent(p_220527_1_ -> {
            brain.n_1700_B(MemoryModuleType.t_1786_h, p_220527_1_);
            brain.n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d((N_4263_v)p_220527_1_, true));
        }));
    }

    private boolean n_1700_B(r_4811_B livingEntity) {
        return this.n_1700_B.equals(livingEntity.f_4016_n()) && this.G_564_y.test(livingEntity);
    }

    private List<r_4811_B> J_1907_R(r_4811_B livingEntity) {
        return livingEntity.y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f).get();
    }
}


