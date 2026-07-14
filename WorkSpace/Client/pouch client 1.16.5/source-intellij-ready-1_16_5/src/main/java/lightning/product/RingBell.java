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
import lightning.product.K_4074_S;
import lightning.product.S_50_d;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.r_4811_B;
import lightning.product.u_954_J;
import lightning.product.MemoryModuleType;

public class RingBell
extends Behavior<r_4811_B> {
    public RingBell() {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_1922_E, (Object)((Object)S_50_d.n_1700_B)));
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, r_4811_B owner) {
        return worldIn.w_1457_N.nextFloat() > 0.95f;
    }

    @Override
    protected void G_564_y(e_3591_l worldIn, r_4811_B entityIn, long gameTimeIn) {
        K_4074_S blockstate;
        E_4668_a<?> brain = entityIn.y_1945_D();
        c_1514_x blockpos = brain.R_4764_Y(MemoryModuleType.P_1922_E).get().J_1907_R();
        if (blockpos.withinDistance(entityIn.b_2312_j(), 3.0) && (blockstate = worldIn.getBlockState(blockpos)).n_1700_B(a_3742_W.l_3370_o)) {
            u_954_J bellblock = (u_954_J)blockstate.J_1907_R();
            bellblock.n_1700_B((b_4507_u)worldIn, blockpos, (b_257_Y)null);
        }
    }
}


