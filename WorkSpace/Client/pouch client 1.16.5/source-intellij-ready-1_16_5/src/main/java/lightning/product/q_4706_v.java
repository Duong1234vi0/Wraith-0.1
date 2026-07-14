/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4381_C;
import lightning.product.K_4096_w;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.U_2534_D;
import lightning.product.V_3157_k;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.e_1174_E;
import lightning.product.e_3591_l;
import lightning.product.monsterSkeleton;
import lightning.product.Goal;
import lightning.product.Items;
import lightning.product.LightningBolt;
import lightning.product.t_5_h;

public class q_4706_v
extends Goal {
    private final D_4381_C n_1700_B;

    public q_4706_v(D_4381_C horseIn) {
        this.n_1700_B = horseIn;
    }

    @Override
    public boolean n_1700_B() {
        return this.n_1700_B.O_508_d.n_1700_B(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k(), 10.0);
    }

    @Override
    public void P_1922_E() {
        e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
        DifficultyInstance difficultyinstance = serverworld.J_1907_R(this.n_1700_B.b_2312_j());
        this.n_1700_B.w_1457_N(false);
        this.n_1700_B.Y_601_j(true);
        this.n_1700_B.b_(0);
        LightningBolt lightningboltentity = t_5_h.z_4693_k.n_1700_B(serverworld);
        lightningboltentity.P_1922_E(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k());
        lightningboltentity.n_1700_B(true);
        serverworld.a_(lightningboltentity);
        monsterSkeleton skeletonentity = this.n_1700_B(difficultyinstance, this.n_1700_B);
        skeletonentity.s_956_w(this.n_1700_B);
        serverworld.n_1700_B((N_4263_v)skeletonentity);
        for (int i = 0; i < 3; ++i) {
            U_2534_D abstracthorseentity = this.n_1700_B(difficultyinstance);
            monsterSkeleton skeletonentity1 = this.n_1700_B(difficultyinstance, abstracthorseentity);
            skeletonentity1.s_956_w(abstracthorseentity);
            abstracthorseentity.w_1484_f(this.n_1700_B.M_3508_C().nextGaussian() * 0.5, 0.0, this.n_1700_B.M_3508_C().nextGaussian() * 0.5);
            serverworld.n_1700_B((N_4263_v)abstracthorseentity);
        }
    }

    private U_2534_D n_1700_B(DifficultyInstance p_188515_1_) {
        D_4381_C skeletonhorseentity = t_5_h.PlayerInfo.n_1700_B(this.n_1700_B.O_508_d);
        skeletonhorseentity.n_1700_B((e_3591_l)this.n_1700_B.O_508_d, p_188515_1_, a_3160_D.u_2550_I, (V_3157_k)null, null);
        skeletonhorseentity.J_1907_R(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k());
        skeletonhorseentity.F_1410_V = 60;
        skeletonhorseentity.T_3594_S();
        skeletonhorseentity.Y_601_j(true);
        skeletonhorseentity.b_(0);
        return skeletonhorseentity;
    }

    private monsterSkeleton n_1700_B(DifficultyInstance p_188514_1_, U_2534_D horse) {
        monsterSkeleton skeletonentity = t_5_h.V_1446_Y.n_1700_B(horse.O_508_d);
        skeletonentity.n_1700_B((e_3591_l)horse.O_508_d, p_188514_1_, a_3160_D.u_2550_I, (V_3157_k)null, null);
        skeletonentity.J_1907_R(horse.O_3598_v(), horse.X_2960_b(), horse.l_2647_k());
        skeletonentity.F_1410_V = 60;
        skeletonentity.T_3594_S();
        if (skeletonentity.J_1907_R(e_1174_E.u_1723_Y).n_1700_B()) {
            skeletonentity.n_1700_B(e_1174_E.u_1723_Y, new Z_1993_T(Items.T_1170_t));
        }
        skeletonentity.n_1700_B(e_1174_E.n_1700_B, K_4096_w.n_1700_B(skeletonentity.M_3508_C(), this.n_1700_B(skeletonentity.A_2714_y()), (int)(5.0f + p_188514_1_.R_4764_Y() * (float)skeletonentity.M_3508_C().nextInt(18)), false));
        skeletonentity.n_1700_B(e_1174_E.u_1723_Y, K_4096_w.n_1700_B(skeletonentity.M_3508_C(), this.n_1700_B(skeletonentity.J_1907_R(e_1174_E.u_1723_Y)), (int)(5.0f + p_188514_1_.R_4764_Y() * (float)skeletonentity.M_3508_C().nextInt(18)), false));
        return skeletonentity;
    }

    private Z_1993_T n_1700_B(Z_1993_T p_242327_1_) {
        p_242327_1_.R_4764_Y("Enchantments");
        return p_242327_1_;
    }
}


