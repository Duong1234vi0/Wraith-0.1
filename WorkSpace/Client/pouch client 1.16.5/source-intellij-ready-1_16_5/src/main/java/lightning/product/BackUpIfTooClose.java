/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.N_4263_v;
import lightning.product.S_50_d;
import lightning.product.Z_530_i;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.o_4722_d;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.MemoryModuleType;

public class BackUpIfTooClose<E extends Z_530_i>
extends Behavior<E> {
    private final int n_1700_B;
    private final float R_4764_Y;

    public BackUpIfTooClose(int distance, float speed) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.h_1847_R, (Object)((Object)S_50_d.R_4764_Y), MemoryModuleType.Q_4569_t, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.w_1484_f, (Object)((Object)S_50_d.n_1700_B)));
        this.n_1700_B = distance;
        this.R_4764_Y = speed;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        return this.n_1700_B(owner) && this.J_1907_R(owner);
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        ((r_4811_B)entityIn).y_1945_D().n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d(this.R_4764_Y(entityIn), true));
        ((Z_530_i)entityIn).A_4115_X().n_1700_B(-this.R_4764_Y, 0.0f);
        ((Z_530_i)entityIn).p_178_J = u_530_F.J_1907_R(((Z_530_i)entityIn).p_178_J, ((Z_530_i)entityIn).f_3449_S, 0.0f);
    }

    private boolean n_1700_B(E mob) {
        return ((r_4811_B)mob).y_1945_D().R_4764_Y(MemoryModuleType.w_1484_f).get().contains(this.R_4764_Y(mob));
    }

    private boolean J_1907_R(E mob) {
        return this.R_4764_Y(mob).n_1700_B((N_4263_v)mob, (double)this.n_1700_B);
    }

    private r_4811_B R_4764_Y(E mob) {
        return ((r_4811_B)mob).y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t).get();
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (Z_530_i)r_4811_B2, l);
    }
}


