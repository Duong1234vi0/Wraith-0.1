/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import lightning.product.A_2352_Z;
import lightning.product.C_3622_I;
import lightning.product.I_4817_s;
import lightning.product.J_133_e;
import lightning.product.Z_530_i;
import lightning.product.TargetingConditions;
import lightning.product.PathfinderMob;
import lightning.product.Goal;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;

public class g_3408_G
extends J_133_e {
    private static final TargetingConditions n_1700_B = new TargetingConditions().R_4764_Y().P_1922_E();
    private boolean J_1907_R;
    private int R_4764_Y;
    private final Class<?>[] G_564_y;
    private Class<?>[] t_148_a;

    public g_3408_G(PathfinderMob creatureIn, Class<?> ... excludeReinforcementTypes) {
        super(creatureIn, true);
        this.G_564_y = excludeReinforcementTypes;
        this.n_1700_B(EnumSet.of(Goal.n_1700_B.G_564_y));
    }

    @Override
    public boolean n_1700_B() {
        int i = this.P_1922_E.r_260_T();
        r_4811_B livingentity = this.P_1922_E.q_817_e();
        if (i != this.R_4764_Y && livingentity != null) {
            if (livingentity.f_4016_n() == t_5_h.g_4106_L && this.P_1922_E.O_508_d.H_1990_U().J_1907_R(A_2352_Z.e_4240_b)) {
                return false;
            }
            for (Class<?> oclass : this.G_564_y) {
                if (!oclass.isAssignableFrom(livingentity.getClass())) continue;
                return false;
            }
            return this.n_1700_B(livingentity, n_1700_B);
        }
        return false;
    }

    public g_3408_G n_1700_B(Class<?> ... reinforcementTypes) {
        this.J_1907_R = true;
        this.t_148_a = reinforcementTypes;
        return this;
    }

    @Override
    public void R_4764_Y() {
        this.P_1922_E.R_4764_Y(this.P_1922_E.q_817_e());
        this.v_4262_N = this.P_1922_E.t_148_a();
        this.R_4764_Y = this.P_1922_E.r_260_T();
        this.w_1484_f = 300;
        if (this.J_1907_R) {
            this.v_4262_N();
        }
        super.R_4764_Y();
    }

    protected void v_4262_N() {
        double d0 = this.u_2550_I();
        I_4817_s axisalignedbb = I_4817_s.fromVector(this.P_1922_E.s_4990_V()).grow(d0, 10.0, d0);
        List<?> list = this.P_1922_E.O_508_d.J_1907_R(this.P_1922_E.getClass(), axisalignedbb);
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Z_530_i mobentity = (Z_530_i)iterator.next();
            if (this.P_1922_E == mobentity || mobentity.t_148_a() != null || this.P_1922_E instanceof C_3622_I && ((C_3622_I)this.P_1922_E).A_1306_N() != ((C_3622_I)mobentity).A_1306_N() || mobentity.Q_4569_t(this.P_1922_E.q_817_e())) continue;
            if (this.t_148_a != null) {
                boolean flag = false;
                for (Class<?> oclass : this.t_148_a) {
                    if (mobentity.getClass() != oclass) continue;
                    flag = true;
                    break;
                }
                if (flag) continue;
            }
            this.n_1700_B(mobentity, this.P_1922_E.q_817_e());
        }
        return;
    }

    protected void n_1700_B(Z_530_i mobIn, r_4811_B targetIn) {
        mobIn.R_4764_Y(targetIn);
    }
}


