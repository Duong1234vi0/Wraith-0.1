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
import lightning.product.DebugPackets;
import lightning.product.F_427_K;
import lightning.product.L_2225_p;
import lightning.product.S_50_d;
import lightning.product.a_3236_r;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.Activity;
import lightning.product.q_2232_A;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class GoToPotentialJobSite
extends Behavior<L_2225_p> {
    final float n_1700_B;

    public GoToPotentialJobSite(float speed) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.G_564_y, (Object)((Object)S_50_d.n_1700_B)), 1200);
        this.n_1700_B = speed;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        return owner.y_1945_D().G_564_y().map(activity -> activity == Activity.J_1907_R || activity == Activity.R_4764_Y || activity == Activity.G_564_y).orElse(true);
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        return entityIn.y_1945_D().n_1700_B(MemoryModuleType.G_564_y);
    }

    @Override
    protected void J_1907_R(e_3591_l worldIn, L_2225_p owner, long gameTime) {
        a_3236_r.n_1700_B((r_4811_B)owner, owner.y_1945_D().R_4764_Y(MemoryModuleType.G_564_y).get().J_1907_R(), this.n_1700_B, 1);
    }

    @Override
    protected void R_4764_Y(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        Optional<F_427_K> optional = entityIn.y_1945_D().R_4764_Y(MemoryModuleType.G_564_y);
        optional.ifPresent(globalPos -> {
            c_1514_x blockpos = globalPos.J_1907_R();
            e_3591_l serverworld = worldIn.T_2506_i().n_1700_B(globalPos.n_1700_B());
            if (serverworld != null) {
                b_4946_z pointofinterestmanager = serverworld.p_178_J();
                if (pointofinterestmanager.n_1700_B(blockpos, (q_2232_A p_241377_0_) -> true)) {
                    pointofinterestmanager.J_1907_R(blockpos);
                }
                DebugPackets.R_4764_Y(worldIn, blockpos);
            }
        });
        entityIn.y_1945_D().J_1907_R(MemoryModuleType.G_564_y);
    }

    @Override
    protected /* synthetic */ void J_1907_R(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.R_4764_Y(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }

    @Override
    protected /* synthetic */ void R_4764_Y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.J_1907_R(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


