/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class L_1885_c<E extends r_4811_B>
extends Behavior<E> {
    private final Predicate<E> n_1700_B;
    private final Behavior<? super E> R_4764_Y;
    private final boolean G_564_y;

    public L_1885_c(Map<MemoryModuleType<?>, S_50_d> p_i231528_1_, Predicate<E> p_i231528_2_, Behavior<? super E> p_i231528_3_, boolean p_i231528_4_) {
        super(L_1885_c.n_1700_B(p_i231528_1_, p_i231528_3_.J_1907_R));
        this.n_1700_B = p_i231528_2_;
        this.R_4764_Y = p_i231528_3_;
        this.G_564_y = p_i231528_4_;
    }

    private static Map<MemoryModuleType<?>, S_50_d> n_1700_B(Map<MemoryModuleType<?>, S_50_d> p_233943_0_, Map<MemoryModuleType<?>, S_50_d> p_233943_1_) {
        HashMap map = Maps.newHashMap();
        map.putAll(p_233943_0_);
        map.putAll(p_233943_1_);
        return map;
    }

    public L_1885_c(Predicate<E> p_i231529_1_, Behavior<? super E> p_i231529_2_) {
        this((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(), (Predicate<? super E>)p_i231529_1_, p_i231529_2_, false);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return this.n_1700_B.test(owner) && this.R_4764_Y.n_1700_B(worldIn, owner);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        return this.G_564_y && this.n_1700_B.test(entityIn) && this.R_4764_Y.n_1700_B(worldIn, entityIn, gameTimeIn);
    }

    @Override
    protected boolean n_1700_B(long gameTime) {
        return false;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        this.R_4764_Y.G_564_y(worldIn, entityIn, gameTimeIn);
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, E owner, long gameTime) {
        this.R_4764_Y.R_4764_Y(worldIn, owner, gameTime);
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        this.R_4764_Y.J_1907_R(worldIn, entityIn, gameTimeIn);
    }

    @Override
    public String toString() {
        return "RunIf: " + String.valueOf(this.R_4764_Y);
    }
}


