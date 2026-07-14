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
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.n_1494_c;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class StartAdmiringItemIfSeen<E extends A_69_b>
extends Behavior<E> {
    private final int n_1700_B;

    public StartAdmiringItemIfSeen(int p_i231573_1_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.z_1737_N, (Object)((Object)S_50_d.n_1700_B), MemoryModuleType.T_2506_i, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.g_221_o, (Object)((Object)S_50_d.J_1907_R), MemoryModuleType.z_4693_k, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = p_i231573_1_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, E owner) {
        n_1494_c itementity = ((A_69_b)owner).y_1945_D().R_4764_Y(MemoryModuleType.z_1737_N).get();
        return A_4919_q.n_1700_B(itementity.P_1922_E().J_1907_R());
    }

    protected void n_1700_B(e_3591_l worldIn, E entityIn, long gameTimeIn) {
        ((A_69_b)entityIn).y_1945_D().n_1700_B(MemoryModuleType.T_2506_i, true, this.n_1700_B);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (A_69_b)r_4811_B2, l);
    }
}


