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
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.a_3236_r;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.n_1494_c;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class GoToWantedItem<E extends r_4811_B>
extends Behavior<E> {
    private final Predicate<E> n_1700_B;
    private final int R_4764_Y;
    private final float G_564_y;

    public GoToWantedItem(float p_i231520_1_, boolean p_i231520_2_, int p_i231520_3_) {
        this(p_233910_0_ -> true, p_i231520_1_, p_i231520_2_, p_i231520_3_);
    }

    public GoToWantedItem(Predicate<E> p_i231521_1_, float p_i231521_2_, boolean p_i231521_3_, int p_i231521_4_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.P_4830_p, (Object)((Object)(p_i231521_3_ ? S_50_d.R_4764_Y : S_50_d.J_1907_R)), MemoryModuleType.z_1737_N, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i231521_1_;
        this.R_4764_Y = p_i231521_4_;
        this.G_564_y = p_i231521_2_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return this.n_1700_B.test(owner) && this.n_1700_B(owner).n_1700_B((N_4263_v)owner, (double)this.R_4764_Y);
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        a_3236_r.n_1700_B(entityIn, this.n_1700_B(entityIn), this.G_564_y, 0);
    }

    private n_1494_c n_1700_B(E p_233909_1_) {
        return ((r_4811_B)p_233909_1_).y_1945_D().R_4764_Y(MemoryModuleType.z_1737_N).get();
    }
}


