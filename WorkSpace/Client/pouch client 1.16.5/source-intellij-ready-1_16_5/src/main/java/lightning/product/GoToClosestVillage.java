/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import java.util.Map;
import lightning.product.WalkTarget;
import lightning.product.L_2225_p;
import lightning.product.S_50_d;
import lightning.product.W_3371_U;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.PathfinderMob;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;

public class GoToClosestVillage
extends Behavior<L_2225_p> {
    private final float n_1700_B;
    private final int R_4764_Y;

    public GoToClosestVillage(float p_i51557_1_, int p_i51557_2_) {
        super((Map<MemoryModuleType<?>, S_50_d>)ImmutableMap.of(MemoryModuleType.P_4830_p, (Object)((Object)S_50_d.J_1907_R)));
        this.n_1700_B = p_i51557_1_;
        this.R_4764_Y = p_i51557_2_;
    }

    @Override
    protected boolean n_1700_B(e_3591_l worldIn, L_2225_p owner) {
        return !worldIn.q_2307_F(owner.b_2312_j());
    }

    protected void n_1700_B(e_3591_l worldIn, L_2225_p entityIn, long gameTimeIn) {
        b_4946_z pointofinterestmanager = worldIn.p_178_J();
        int i = pointofinterestmanager.n_1700_B(SectionPos.n_1700_B(entityIn.b_2312_j()));
        e_2866_D vector3d = null;
        for (int j = 0; j < 5; ++j) {
            e_2866_D vector3d1 = W_3371_U.n_1700_B((PathfinderMob)entityIn, 15, 7, p_225444_1_ -> -worldIn.J_1907_R(SectionPos.n_1700_B(p_225444_1_)));
            if (vector3d1 == null) continue;
            int k = pointofinterestmanager.n_1700_B(SectionPos.n_1700_B(new c_1514_x(vector3d1)));
            if (k < i) {
                vector3d = vector3d1;
                break;
            }
            if (k != i) continue;
            vector3d = vector3d1;
        }
        if (vector3d != null) {
            entityIn.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, new WalkTarget(vector3d, this.n_1700_B, this.R_4764_Y));
        }
    }

    @Override
    protected /* synthetic */ void G_564_y(e_3591_l e_3591_l2, r_4811_B r_4811_B2, long l) {
        this.n_1700_B(e_3591_l2, (L_2225_p)r_4811_B2, l);
    }
}


