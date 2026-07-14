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
import lightning.product.J_2548_M;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class CopyMemoryWithExpiry<E extends Z_530_i, T>
extends Behavior<E> {
    private final Predicate<E> n_1700_B;
    private final MemoryModuleType<? extends T> R_4764_Y;
    private final MemoryModuleType<T> G_564_y;
    private final J_2548_M P_1922_E;

    public CopyMemoryWithExpiry(Predicate<E> p_i231513_1_, MemoryModuleType<? extends T> p_i231513_2_, MemoryModuleType<T> p_i231513_3_, J_2548_M p_i231513_4_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(p_i231513_2_, (Object)((Object)S_50_d.n_1700_B), p_i231513_3_, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = p_i231513_1_;
        this.R_4764_Y = p_i231513_2_;
        this.G_564_y = p_i231513_3_;
        this.P_1922_E = p_i231513_4_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return this.n_1700_B.test(owner);
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        E_4668_a<?> brain = ((r_4811_B)entityIn).y_1945_D();
        brain.n_1700_B(this.G_564_y, brain.R_4764_Y(this.R_4764_Y).get(), this.P_1922_E.n_1700_B(worldIn.w_1457_N));
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


