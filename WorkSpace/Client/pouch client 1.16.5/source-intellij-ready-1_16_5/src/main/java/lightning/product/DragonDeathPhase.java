/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.EndPodiumFeature;
import lightning.product.Z_1164_j;
import lightning.product.b_2971_b;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.ParticleTypes;
import lightning.product.z_2963_s;
import lightning.product.AbstractDragonPhaseInstance;

public class DragonDeathPhase
extends AbstractDragonPhaseInstance {
    private e_2866_D J_1907_R;
    private int R_4764_Y;

    public DragonDeathPhase(b_2971_b dragonIn) {
        super(dragonIn);
    }

    @Override
    public void n_1700_B() {
        if (this.R_4764_Y++ % 10 == 0) {
            float f = (this.n_1700_B.M_3508_C().nextFloat() - 0.5f) * 8.0f;
            float f1 = (this.n_1700_B.M_3508_C().nextFloat() - 0.5f) * 4.0f;
            float f2 = (this.n_1700_B.M_3508_C().nextFloat() - 0.5f) * 8.0f;
            this.n_1700_B.O_508_d.n_1700_B(ParticleTypes.Q_2552_b, this.n_1700_B.O_3598_v() + (double)f, this.n_1700_B.X_2960_b() + 2.0 + (double)f1, this.n_1700_B.l_2647_k() + (double)f2, 0.0, 0.0, 0.0);
        }
    }

    @Override
    public void J_1907_R() {
        double d0;
        ++this.R_4764_Y;
        if (this.J_1907_R == null) {
            c_1514_x blockpos = this.n_1700_B.O_508_d.n_1700_B(z_2963_s.n_1700_B.P_1922_E, EndPodiumFeature.n_1700_B);
            this.J_1907_R = e_2866_D.R_4764_Y(blockpos);
        }
        if (!((d0 = this.J_1907_R.R_4764_Y(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k())) < 100.0 || d0 > 22500.0 || this.n_1700_B.D_60_a || this.n_1700_B.k_3961_g)) {
            this.n_1700_B.t_1786_h(1.0f);
        } else {
            this.n_1700_B.t_1786_h(0.0f);
        }
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = null;
        this.R_4764_Y = 0;
    }

    @Override
    public float P_1922_E() {
        return 3.0f;
    }

    @Override
    @Nullable
    public e_2866_D u_1723_Y() {
        return this.J_1907_R;
    }

    public Z_1164_j<DragonDeathPhase> G_564_y() {
        return Z_1164_j.s_956_w;
    }
}


