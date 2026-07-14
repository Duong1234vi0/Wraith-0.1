/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import lightning.product.T_1316_M;
import lightning.product.c_1514_x;
import lightning.product.PathfinderMob;
import lightning.product.Goal;

public abstract class MoveToBlockGoal
extends Goal {
    protected final PathfinderMob n_1700_B;
    public final double J_1907_R;
    protected int R_4764_Y;
    protected int G_564_y;
    private int v_4262_N;
    protected c_1514_x P_1922_E = c_1514_x.ZERO;
    private boolean w_1484_f;
    private final int t_148_a;
    private final int s_956_w;
    protected int u_1723_Y;

    public MoveToBlockGoal(PathfinderMob creature, double speedIn, int length) {
        this(creature, speedIn, length, 1);
    }

    public MoveToBlockGoal(PathfinderMob creatureIn, double speed, int length, int p_i48796_5_) {
        this.n_1700_B = creatureIn;
        this.J_1907_R = speed;
        this.t_148_a = length;
        this.u_1723_Y = 0;
        this.s_956_w = p_i48796_5_;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.n_1700_B, Goal.n_1700_B.R_4764_Y));
    }

    @Override
    public boolean n_1700_B() {
        if (this.R_4764_Y > 0) {
            --this.R_4764_Y;
            return false;
        }
        this.R_4764_Y = this.n_1700_B(this.n_1700_B);
        return this.P_4830_p();
    }

    protected int n_1700_B(PathfinderMob creatureIn) {
        return 200 + creatureIn.M_3508_C().nextInt(200);
    }

    @Override
    public boolean J_1907_R() {
        return this.G_564_y >= -this.v_4262_N && this.G_564_y <= 1200 && this.n_1700_B(this.n_1700_B.O_508_d, this.P_1922_E);
    }

    @Override
    public void R_4764_Y() {
        this.v_4262_N();
        this.G_564_y = 0;
        this.v_4262_N = this.n_1700_B.M_3508_C().nextInt(this.n_1700_B.M_3508_C().nextInt(1200) + 1200) + 1200;
    }

    protected void v_4262_N() {
        this.n_1700_B.e_4240_b().n_1700_B((double)this.P_1922_E.getX() + 0.5, (double)(this.P_1922_E.getY() + 1), (double)this.P_1922_E.getZ() + 0.5, this.J_1907_R);
    }

    public double w_1484_f() {
        return 1.0;
    }

    protected c_1514_x s_956_w() {
        return this.P_1922_E.up();
    }

    @Override
    public void P_1922_E() {
        c_1514_x blockpos = this.s_956_w();
        if (!blockpos.withinDistance(this.n_1700_B.s_4990_V(), this.w_1484_f())) {
            this.w_1484_f = false;
            ++this.G_564_y;
            if (this.u_2550_I()) {
                this.n_1700_B.e_4240_b().n_1700_B((double)blockpos.getX() + 0.5, (double)blockpos.getY(), (double)blockpos.getZ() + 0.5, this.J_1907_R);
            }
        } else {
            this.w_1484_f = true;
            --this.G_564_y;
        }
    }

    public boolean u_2550_I() {
        return this.G_564_y % 40 == 0;
    }

    protected boolean M_588_G() {
        return this.w_1484_f;
    }

    protected boolean P_4830_p() {
        int i = this.t_148_a;
        int j = this.s_956_w;
        c_1514_x blockpos = this.n_1700_B.b_2312_j();
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        int k = this.u_1723_Y;
        while (k <= j) {
            for (int l = 0; l < i; ++l) {
                int i1 = 0;
                while (i1 <= l) {
                    int j1;
                    int n = j1 = i1 < l && i1 > -l ? l : 0;
                    while (j1 <= l) {
                        blockpos$mutable.n_1700_B(blockpos, i1, k - 1, j1);
                        if (this.n_1700_B.u_1723_Y(blockpos$mutable) && this.n_1700_B(this.n_1700_B.O_508_d, blockpos$mutable)) {
                            this.P_1922_E = blockpos$mutable;
                            return true;
                        }
                        j1 = j1 > 0 ? -j1 : 1 - j1;
                    }
                    i1 = i1 > 0 ? -i1 : 1 - i1;
                }
            }
            k = k > 0 ? -k : 1 - k;
        }
        return false;
    }

    protected abstract boolean n_1700_B(T_1316_M var1, c_1514_x var2);
}


