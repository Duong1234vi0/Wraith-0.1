/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.Optional;
import lightning.product.F_427_K;
import lightning.product.L_2225_p;
import lightning.product.N_4263_v;
import lightning.product.VillagerProfession;
import lightning.product.S_50_d;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;
import net.minecraft.server.G_564_y;

public class AssignProfessionFromJobSite
extends Behavior<L_2225_p> {
    public AssignProfessionFromJobSite() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.G_564_y, (Object)((Object)S_50_d.n_1700_B)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        c_1514_x blockpos = owner.y_1945_D().R_4764_Y(MemoryModuleType.G_564_y).get().J_1907_R();
        return blockpos.withinDistance(owner.s_4990_V(), 2.0) || owner.f_2787_O();
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        F_427_K globalpos = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.G_564_y).get();
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.G_564_y);
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.R_4764_Y, globalpos);
        worldIn.n_1700_B((N_4263_v)entityIn, (byte)14);
        if (entityIn.c_2086_l().J_1907_R() == VillagerProfession.n_1700_B) {
            G_564_y minecraftserver = worldIn.T_2506_i();
            Optional.ofNullable(minecraftserver.n_1700_B(globalpos.n_1700_B())).flatMap(world -> world.p_178_J().R_4764_Y(globalpos.J_1907_R())).flatMap(poiType -> V_3137_a.r_715_M.u_1723_Y().filter(profession -> profession.n_1700_B() == poiType).findFirst()).ifPresent(profession -> {
                entityIn.n_1700_B(entityIn.c_2086_l().n_1700_B((VillagerProfession)profession));
                entityIn.R_4764_Y(worldIn);
            });
        }
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


