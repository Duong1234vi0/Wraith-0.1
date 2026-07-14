/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.IntOpenHashSet
 *  it.unimi.dsi.fastutil.ints.IntSet
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import lightning.product.V_4170_D;
import lightning.product.c_1325_f;
import lightning.product.Context;

public final class F_3979_r
extends Enum<F_3979_r>
implements c_1325_f {
    public static final /* enum */ F_3979_r n_1700_B = new F_3979_r();
    private static final IntSet J_1907_R;
    private static final IntSet R_4764_Y;
    private static final /* synthetic */ F_3979_r[] G_564_y;

    public static F_3979_r[] values() {
        return (F_3979_r[])G_564_y.clone();
    }

    public static F_3979_r valueOf(String name) {
        return Enum.valueOf(F_3979_r.class, name);
    }

    @Override
    public int n_1700_B(Context context, int north, int west, int south, int east, int center) {
        if (center == 14) {
            if (V_4170_D.J_1907_R(north) || V_4170_D.J_1907_R(west) || V_4170_D.J_1907_R(south) || V_4170_D.J_1907_R(east)) {
                return 15;
            }
        } else if (R_4764_Y.contains(center)) {
            if (!(F_3979_r.R_4764_Y(north) && F_3979_r.R_4764_Y(west) && F_3979_r.R_4764_Y(south) && F_3979_r.R_4764_Y(east))) {
                return 23;
            }
            if (V_4170_D.n_1700_B(north) || V_4170_D.n_1700_B(west) || V_4170_D.n_1700_B(south) || V_4170_D.n_1700_B(east)) {
                return 16;
            }
        } else if (center != 3 && center != 34 && center != 20) {
            if (J_1907_R.contains(center)) {
                if (!V_4170_D.n_1700_B(center) && (V_4170_D.n_1700_B(north) || V_4170_D.n_1700_B(west) || V_4170_D.n_1700_B(south) || V_4170_D.n_1700_B(east))) {
                    return 26;
                }
            } else if (center != 37 && center != 38) {
                if (!V_4170_D.n_1700_B(center) && center != 7 && center != 6 && (V_4170_D.n_1700_B(north) || V_4170_D.n_1700_B(west) || V_4170_D.n_1700_B(south) || V_4170_D.n_1700_B(east))) {
                    return 16;
                }
            } else if (!(V_4170_D.n_1700_B(north) || V_4170_D.n_1700_B(west) || V_4170_D.n_1700_B(south) || V_4170_D.n_1700_B(east) || this.G_564_y(north) && this.G_564_y(west) && this.G_564_y(south) && this.G_564_y(east))) {
                return 2;
            }
        } else if (!V_4170_D.n_1700_B(center) && (V_4170_D.n_1700_B(north) || V_4170_D.n_1700_B(west) || V_4170_D.n_1700_B(south) || V_4170_D.n_1700_B(east))) {
            return 25;
        }
        return center;
    }

    private static boolean R_4764_Y(int p_151631_0_) {
        return R_4764_Y.contains(p_151631_0_) || p_151631_0_ == 4 || p_151631_0_ == 5 || V_4170_D.n_1700_B(p_151631_0_);
    }

    private boolean G_564_y(int p_151633_1_) {
        return p_151633_1_ == 37 || p_151633_1_ == 38 || p_151633_1_ == 39 || p_151633_1_ == 165 || p_151633_1_ == 166 || p_151633_1_ == 167;
    }

    private static /* synthetic */ F_3979_r[] n_1700_B() {
        return new F_3979_r[]{n_1700_B};
    }

    static {
        G_564_y = F_3979_r.n_1700_B();
        J_1907_R = new IntOpenHashSet(new int[]{26, 11, 12, 13, 140, 30, 31, 158, 10});
        R_4764_Y = new IntOpenHashSet(new int[]{168, 169, 21, 22, 23, 149, 151});
    }
}


