/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.E_4668_a;
import lightning.product.L_2225_p;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.Activity;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class VillagerPanicTrigger
extends Behavior<L_2225_p> {
    public VillagerPanicTrigger() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of());
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return VillagerPanicTrigger.J_1907_R(entityIn) || VillagerPanicTrigger.n_1700_B(entityIn);
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        if (VillagerPanicTrigger.J_1907_R(entityIn) || VillagerPanicTrigger.n_1700_B(entityIn)) {
            E_4668_a<L_2225_p> brain = entityIn.y_1945_D();
            if (!brain.R_4764_Y(Activity.v_4262_N)) {
                brain.J_1907_R(MemoryModuleType.Y_601_j);
                brain.J_1907_R(MemoryModuleType.P_4830_p);
                brain.J_1907_R(MemoryModuleType.h_1847_R);
                brain.J_1907_R(MemoryModuleType.multiplayerClientSuggestionProvider);
                brain.J_1907_R(MemoryModuleType.t_1786_h);
            }
            brain.n_1700_B(Activity.v_4262_N);
        }
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        if (gameTime % 100L == 0L) {
            owner.n_1700_B(worldIn, gameTime, 3);
        }
    }

    public static boolean n_1700_B(r_4811_B entity) {
        return entity.y_1945_D().n_1700_B(MemoryModuleType.c_3005_b);
    }

    public static boolean J_1907_R(r_4811_B entity) {
        return entity.y_1945_D().n_1700_B(MemoryModuleType.k_2293_S);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


