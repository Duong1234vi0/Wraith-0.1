/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import lightning.product.N_4263_v;
import lightning.product.U_2866_z;
import lightning.product.W_3371_U;
import lightning.product.W_4304_a;
import lightning.product.Z_530_i;
import lightning.product.b_3129_s;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.PathfinderMob;
import lightning.product.Goal;

public class PathfindToRaidGoal<T extends W_4304_a>
extends Goal {
    private final T n_1700_B;

    public PathfindToRaidGoal(T raider) {
        this.n_1700_B = raider;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B));
    }

    @Override
    public boolean n_1700_B() {
        return ((Z_530_i)this.n_1700_B).t_148_a() == null && !((N_4263_v)this.n_1700_B).H_1883_T() && ((W_4304_a)this.n_1700_B).J_3635_s() && !((W_4304_a)this.n_1700_B).y_2447_C().n_1700_B() && !((e_3591_l)((W_4304_a)this.n_1700_B).O_508_d).q_2307_F(((N_4263_v)this.n_1700_B).b_2312_j());
    }

    @Override
    public boolean J_1907_R() {
        return ((W_4304_a)this.n_1700_B).J_3635_s() && !((W_4304_a)this.n_1700_B).y_2447_C().n_1700_B() && ((W_4304_a)this.n_1700_B).O_508_d instanceof e_3591_l && !((e_3591_l)((W_4304_a)this.n_1700_B).O_508_d).q_2307_F(((N_4263_v)this.n_1700_B).b_2312_j());
    }

    @Override
    public void P_1922_E() {
        if (((W_4304_a)this.n_1700_B).J_3635_s()) {
            e_2866_D vector3d;
            b_3129_s raid = ((W_4304_a)this.n_1700_B).y_2447_C();
            if (((W_4304_a)this.n_1700_B).RealmsWorldResetDto % 20 == 0) {
                this.n_1700_B(raid);
            }
            if (!((PathfinderMob)this.n_1700_B).w_1484_f() && (vector3d = W_3371_U.J_1907_R(this.n_1700_B, 15, 4, e_2866_D.R_4764_Y(raid.multiplayerClientSuggestionProvider()))) != null) {
                ((Z_530_i)this.n_1700_B).e_4240_b().n_1700_B(vector3d.J_1907_R, vector3d.R_4764_Y, vector3d.G_564_y, 1.0);
            }
        }
    }

    private void n_1700_B(b_3129_s raid) {
        if (raid.Y_601_j()) {
            HashSet set = Sets.newHashSet();
            List<W_4304_a> list = ((W_4304_a)this.n_1700_B).O_508_d.n_1700_B(W_4304_a.class, ((N_4263_v)this.n_1700_B).i_601_W().grow(16.0), raider -> !raider.J_3635_s() && U_2866_z.n_1700_B(raider, raid));
            set.addAll(list);
            for (W_4304_a abstractraiderentity : set) {
                raid.n_1700_B(raid.t_148_a(), abstractraiderentity, null, true);
            }
        }
    }
}


