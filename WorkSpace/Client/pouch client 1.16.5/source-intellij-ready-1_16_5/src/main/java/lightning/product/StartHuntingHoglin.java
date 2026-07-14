/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.A_4919_q;
import lightning.product.A_69_b;
import lightning.product.Hoglin;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class StartHuntingHoglin<E extends A_69_b>
extends Behavior<E> {
    public StartHuntingHoglin() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.X_933_l, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.d_2461_k, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.e_2887_G, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.c_4037_x, (Object)((Object)S_50_d.R_4764_Y)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, A_69_b owner) {
        return !owner.d_() && !A_4919_q.P_1922_E(owner);
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        Hoglin hoglinentity = ((A_69_b)entityIn).y_1945_D().R_4764_Y(MemoryModuleType.X_933_l).get();
        A_4919_q.R_4764_Y(entityIn, (r_4811_B)hoglinentity);
        A_4919_q.R_4764_Y(entityIn);
        A_4919_q.J_1907_R(entityIn, (r_4811_B)hoglinentity);
        A_4919_q.u_1723_Y(entityIn);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (A_69_b)r_4811_B2, l);
    }
}


