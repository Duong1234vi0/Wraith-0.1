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
import lightning.product.WalkTarget;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.o_4722_d;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class InteractWith<E extends r_4811_B, T extends r_4811_B>
extends Behavior<E> {
    private final int n_1700_B;
    private final float R_4764_Y;
    private final t_5_h<? extends T> G_564_y;
    private final int P_1922_E;
    private final Predicate<T> u_1723_Y;
    private final Predicate<E> v_4262_N;
    private final MemoryModuleType<T> w_1484_f;

    public InteractWith(t_5_h<? extends T> p_i50363_1_, int p_i50363_2_, Predicate<E> p_i50363_3_, Predicate<T> p_i50363_4_, MemoryModuleType<T> p_i50363_5_, float p_i50363_6_, int p_i50363_7_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.w_1484_f, (Object)((Object)S_50_d.n_1700_B)));
        this.G_564_y = p_i50363_1_;
        this.R_4764_Y = p_i50363_6_;
        this.P_1922_E = p_i50363_2_ * p_i50363_2_;
        this.n_1700_B = p_i50363_7_;
        this.u_1723_Y = p_i50363_4_;
        this.v_4262_N = p_i50363_3_;
        this.w_1484_f = p_i50363_5_;
    }

    public static <T extends r_4811_B> InteractWith<r_4811_B, T> n_1700_B(t_5_h<? extends T> p_220445_0_, int p_220445_1_, MemoryModuleType<T> p_220445_2_, float p_220445_3_, int p_220445_4_) {
        return new InteractWith<r_4811_B, r_4811_B>(p_220445_0_, p_220445_1_, p_220441_0_ -> true, p_220442_0_ -> true, p_220445_2_, p_220445_3_, p_220445_4_);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return this.v_4262_N.test(owner) && this.n_1700_B(owner);
    }

    private boolean n_1700_B(E p_233913_1_) {
        List<r_4811_B> list = ((r_4811_B)p_233913_1_).y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f).get();
        return list.stream().anyMatch(this::J_1907_R);
    }

    private boolean J_1907_R(r_4811_B p_233914_1_) {
        return this.G_564_y.equals(p_233914_1_.f_4016_n()) && this.u_1723_Y.test(p_233914_1_);
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        E_4668_a<?> brain = ((r_4811_B)entityIn).y_1945_D();
        brain.R_4764_Y(MemoryModuleType.w_1484_f).ifPresent(p_220437_3_ -> p_220437_3_.stream().filter(p_220440_1_ -> this.G_564_y.equals(p_220440_1_.f_4016_n())).map(p_220439_0_ -> p_220439_0_).filter(p_220443_2_ -> p_220443_2_.G_564_y((N_4263_v)entityIn) <= (double)this.P_1922_E).filter(this.u_1723_Y).findFirst().ifPresent(p_220438_2_ -> {
            brain.n_1700_B(this.w_1484_f, p_220438_2_);
            brain.n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d((N_4263_v)p_220438_2_, true));
            brain.n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(new o_4722_d((N_4263_v)p_220438_2_, false), this.R_4764_Y, this.n_1700_B));
        }));
    }
}


