/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.Attributes;
import lightning.product.I_1170_F;
import lightning.product.MobEffects;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.monsterSpider;
import lightning.product.R_1815_U;
import lightning.product.R_2450_T;
import lightning.product.U_2912_j;
import lightning.product.V_3157_k;
import lightning.product.a_3160_D;
import lightning.product.b_4507_u;
import lightning.product.ServerLevelAccessor;
import lightning.product.k_2610_C;
import lightning.product.r_4811_B;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;

public class CaveSpider
extends monsterSpider {
    public CaveSpider(t_5_h<? extends CaveSpider> type, b_4507_u worldIn) {
        super((t_5_h<? extends monsterSpider>)type, worldIn);
    }

    public static s_1415_m.n_1700_B u_1723_Y() {
        return monsterSpider.y_4642_Y().n_1700_B(Attributes.n_1700_B, 12.0);
    }

    @Override
    public boolean q_2307_F(N_4263_v entityIn) {
        if (super.q_2307_F(entityIn)) {
            if (entityIn instanceof r_4811_B) {
                int i = 0;
                if (this.O_508_d.x_607_J() == R_2450_T.R_4764_Y) {
                    i = 7;
                } else if (this.O_508_d.x_607_J() == R_2450_T.G_564_y) {
                    i = 15;
                }
                if (i > 0) {
                    ((r_4811_B)entityIn).n_1700_B(new k_2610_C(MobEffects.w_1457_N, i * 20, 0));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public V_3157_k n_1700_B(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, a_3160_D reason, @Nullable V_3157_k spawnDataIn, @Nullable U_2912_j dataTag) {
        return spawnDataIn;
    }

    @Override
    protected float J_1907_R(I_1170_F poseIn, R_1815_U sizeIn) {
        return 0.45f;
    }
}


