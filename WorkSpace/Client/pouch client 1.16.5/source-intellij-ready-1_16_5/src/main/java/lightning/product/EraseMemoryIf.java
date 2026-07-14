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
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class EraseMemoryIf<E extends r_4811_B>
extends Behavior<E> {
    private final Predicate<E> n_1700_B;
    private final MemoryModuleType<?> R_4764_Y;

    public EraseMemoryIf(Predicate<E> p_i231517_1_, MemoryModuleType<?> p_i231517_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(p_i231517_2_, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i231517_1_;
        this.R_4764_Y = p_i231517_2_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return this.n_1700_B.test(owner);
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        ((r_4811_B)entityIn).y_1945_D().J_1907_R(this.R_4764_Y);
    }
}


