/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.L_2225_p;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.VillagerPanicTrigger;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class VillagerCalmDown
extends Behavior<L_2225_p> {
    public VillagerCalmDown() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of());
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        boolean flag;
        boolean bl = flag = VillagerPanicTrigger.J_1907_R(entityIn) || VillagerPanicTrigger.n_1700_B(entityIn) || VillagerCalmDown.n_1700_B(entityIn);
        if (!flag) {
            entityIn.y_1945_D().J_1907_R(MemoryModuleType.k_2293_S);
            entityIn.y_1945_D().J_1907_R(MemoryModuleType.q_2307_F);
            entityIn.y_1945_D().n_1700_B(worldIn.Z_976_R(), worldIn.X_933_l());
        }
    }

    private static boolean n_1700_B(L_2225_p villager) {
        return villager.y_1945_D().R_4764_Y(MemoryModuleType.q_2307_F).filter(attacker -> attacker.G_564_y(villager) <= 36.0).isPresent();
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


