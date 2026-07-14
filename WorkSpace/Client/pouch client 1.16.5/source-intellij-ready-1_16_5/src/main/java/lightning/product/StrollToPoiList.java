/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.F_427_K;
import lightning.product.WalkTarget;
import lightning.product.L_2225_p;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class StrollToPoiList
extends Behavior<L_2225_p> {
    private final MemoryModuleType<List<F_427_K>> n_1700_B;
    private final MemoryModuleType<F_427_K> R_4764_Y;
    private final float G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private long v_4262_N;
    @Nullable
    private F_427_K w_1484_f;

    public StrollToPoiList(MemoryModuleType<List<F_427_K>> p_i50340_1_, float p_i50340_2_, int p_i50340_3_, int p_i50340_4_, MemoryModuleType<F_427_K> p_i50340_5_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.R_4764_Y), p_i50340_1_, (Object)((Object)S_50_d.n_1700_B), p_i50340_5_, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = p_i50340_1_;
        this.G_564_y = p_i50340_2_;
        this.P_1922_E = p_i50340_3_;
        this.u_1723_Y = p_i50340_4_;
        this.R_4764_Y = p_i50340_5_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        List<F_427_K> list;
        Optional<List<F_427_K>> optional = owner.y_1945_D().R_4764_Y(this.n_1700_B);
        Optional<F_427_K> optional1 = owner.y_1945_D().R_4764_Y(this.R_4764_Y);
        if (optional.isPresent() && optional1.isPresent() && !(list = optional.get()).isEmpty()) {
            this.w_1484_f = list.get(worldIn.e_4240_b().nextInt(list.size()));
            return this.w_1484_f != null && worldIn.g_2268_R() == this.w_1484_f.n_1700_B() && optional1.get().J_1907_R().withinDistance(owner.s_4990_V(), (double)this.u_1723_Y);
        }
        return false;
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        if (gameTimeIn > this.v_4262_N && this.w_1484_f != null) {
            entityIn.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(this.w_1484_f.J_1907_R(), this.G_564_y, this.P_1922_E));
            this.v_4262_N = gameTimeIn + 100L;
        }
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


