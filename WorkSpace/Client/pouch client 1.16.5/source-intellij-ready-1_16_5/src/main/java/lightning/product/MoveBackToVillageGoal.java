/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.RandomStrollGoal;
import lightning.product.W_3371_U;
import lightning.product.a_3236_r;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.PathfinderMob;

public class MoveBackToVillageGoal
extends RandomStrollGoal {
    public MoveBackToVillageGoal(PathfinderMob creature, double speed, boolean p_i231548_4_) {
        super(creature, speed, 10, p_i231548_4_);
    }

    @Override
    public boolean n_1700_B() {
        e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
        c_1514_x blockpos = this.n_1700_B.b_2312_j();
        return serverworld.q_2307_F(blockpos) ? false : super.n_1700_B();
    }

    @Override
    @Nullable
    protected e_2866_D v_4262_N() {
        e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
        c_1514_x blockpos = this.n_1700_B.b_2312_j();
        SectionPos sectionpos = SectionPos.n_1700_B(blockpos);
        SectionPos sectionpos1 = a_3236_r.n_1700_B(serverworld, sectionpos, 2);
        return sectionpos1 != sectionpos ? W_3371_U.J_1907_R(this.n_1700_B, 10, 7, e_2866_D.R_4764_Y(sectionpos1.u_2550_I())) : null;
    }
}


