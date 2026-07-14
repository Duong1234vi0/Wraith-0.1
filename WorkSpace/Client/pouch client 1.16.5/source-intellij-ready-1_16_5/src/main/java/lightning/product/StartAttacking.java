/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class StartAttacking<E extends Z_530_i>
extends Behavior<E> {
    private final Predicate<E> n_1700_B;
    private final Function<E, Optional<? extends r_4811_B>> R_4764_Y;

    public StartAttacking(Predicate<E> p_i231537_1_, Function<E, Optional<? extends r_4811_B>> p_i231537_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.Y_1740_V, (Object)((Object)S_50_d.R_4764_Y)));
        this.n_1700_B = p_i231537_1_;
        this.R_4764_Y = p_i231537_2_;
    }

    public StartAttacking(Function<E, Optional<? extends r_4811_B>> p_i231536_1_) {
        this((E p_233975_0_) -> true, p_i231536_1_);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        if (!this.n_1700_B.test(owner)) {
            return false;
        }
        Optional<? extends r_4811_B> optional = this.R_4764_Y.apply(owner);
        return optional.isPresent() && optional.get().RealmsLongRunningMcoTaskScreen();
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        this.R_4764_Y.apply(entityIn).ifPresent(p_233977_2_ -> this.n_1700_B(entityIn, (r_4811_B)p_233977_2_));
    }

    private void n_1700_B(E p_233976_1_, r_4811_B p_233976_2_) {
        ((r_4811_B)p_233976_1_).y_1945_D().n_1700_B(MemoryModuleType.Q_4569_t, p_233976_2_);
        ((r_4811_B)p_233976_1_).y_1945_D().J_1907_R(MemoryModuleType.Y_1740_V);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


