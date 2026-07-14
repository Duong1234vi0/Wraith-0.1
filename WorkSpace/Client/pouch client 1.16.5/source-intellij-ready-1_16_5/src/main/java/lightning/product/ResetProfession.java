/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.VillagerData;
import lightning.product.L_2225_p;
import lightning.product.VillagerProfession;
import lightning.product.S_50_d;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class ResetProfession
extends Behavior<L_2225_p> {
    public ResetProfession() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.R_4764_Y, (Object)((Object)S_50_d.J_1907_R)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        VillagerData villagerdata = owner.c_2086_l();
        return villagerdata.J_1907_R() != VillagerProfession.n_1700_B && villagerdata.J_1907_R() != VillagerProfession.M_588_G && owner.G_564_y() == 0 && villagerdata.R_4764_Y() <= 1;
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        entityIn.n_1700_B(entityIn.c_2086_l().n_1700_B(VillagerProfession.n_1700_B));
        entityIn.R_4764_Y(worldIn);
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


