/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.RandomStrollGoal;
import lightning.product.L_2225_p;
import lightning.product.W_3371_U;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.PathfinderMob;
import lightning.product.t_5_h;
import lightning.product.y_2339_p;

public class n_1778_y
extends RandomStrollGoal {
    public n_1778_y(PathfinderMob creature, double speed) {
        super(creature, speed, 240, false);
    }

    @Override
    @Nullable
    protected e_2866_D v_4262_N() {
        e_2866_D vector3d;
        float f = this.n_1700_B.O_508_d.w_1457_N.nextFloat();
        if (this.n_1700_B.O_508_d.w_1457_N.nextFloat() < 0.3f) {
            return this.s_956_w();
        }
        if (f < 0.7f) {
            vector3d = this.u_2550_I();
            if (vector3d == null) {
                vector3d = this.M_588_G();
            }
        } else {
            vector3d = this.M_588_G();
            if (vector3d == null) {
                vector3d = this.u_2550_I();
            }
        }
        return vector3d == null ? this.s_956_w() : vector3d;
    }

    @Nullable
    private e_2866_D s_956_w() {
        return W_3371_U.J_1907_R(this.n_1700_B, 10, 7);
    }

    @Nullable
    private e_2866_D u_2550_I() {
        e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
        List<L_2225_p> list = serverworld.n_1700_B(t_5_h.RealmsDefaultUncaughtExceptionHandler, this.n_1700_B.i_601_W().grow(32.0), this::n_1700_B);
        if (list.isEmpty()) {
            return null;
        }
        L_2225_p villagerentity = list.get(this.n_1700_B.O_508_d.w_1457_N.nextInt(list.size()));
        e_2866_D vector3d = villagerentity.s_4990_V();
        return W_3371_U.n_1700_B(this.n_1700_B, 10, 7, vector3d);
    }

    @Nullable
    private e_2866_D M_588_G() {
        SectionPos sectionpos = this.P_4830_p();
        if (sectionpos == null) {
            return null;
        }
        c_1514_x blockpos = this.n_1700_B(sectionpos);
        return blockpos == null ? null : W_3371_U.n_1700_B(this.n_1700_B, 10, 7, e_2866_D.R_4764_Y(blockpos));
    }

    @Nullable
    private SectionPos P_4830_p() {
        e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
        List list = SectionPos.n_1700_B(SectionPos.n_1700_B(this.n_1700_B), 2).filter(p_234030_1_ -> serverworld.J_1907_R((SectionPos)p_234030_1_) == 0).collect(Collectors.toList());
        return list.isEmpty() ? null : (SectionPos)list.get(serverworld.w_1457_N.nextInt(list.size()));
    }

    @Nullable
    private c_1514_x n_1700_B(SectionPos p_234029_1_) {
        e_3591_l serverworld = (e_3591_l)this.n_1700_B.O_508_d;
        b_4946_z pointofinterestmanager = serverworld.p_178_J();
        List list = pointofinterestmanager.R_4764_Y(p_234027_0_ -> true, p_234029_1_.u_2550_I(), 8, b_4946_z.J_1907_R.J_1907_R).map(y_2339_p::P_1922_E).collect(Collectors.toList());
        return list.isEmpty() ? null : (c_1514_x)list.get(serverworld.w_1457_N.nextInt(list.size()));
    }

    private boolean n_1700_B(L_2225_p villager) {
        return villager.n_1700_B(this.n_1700_B.O_508_d.X_933_l());
    }
}


