/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Random;
import javax.annotation.Nullable;
import lightning.product.EndPodiumFeature;
import lightning.product.N_4263_v;
import lightning.product.Z_1164_j;
import lightning.product.b_2971_b;
import lightning.product.e_2866_D;
import lightning.product.ParticleTypes;
import lightning.product.u_530_F;
import lightning.product.z_2963_s;
import lightning.product.AbstractDragonPhaseInstance;

public class DragonLandingPhase
extends AbstractDragonPhaseInstance {
    private e_2866_D J_1907_R;

    public DragonLandingPhase(b_2971_b dragonIn) {
        super(dragonIn);
    }

    @Override
    public void n_1700_B() {
        e_2866_D vector3d = this.n_1700_B.G_564_y(1.0f).G_564_y();
        vector3d.J_1907_R(-0.7853982f);
        double d0 = this.n_1700_B.h_1847_R.O_3598_v();
        double d1 = this.n_1700_B.h_1847_R.P_1922_E(0.5);
        double d2 = this.n_1700_B.h_1847_R.l_2647_k();
        for (int i = 0; i < 8; ++i) {
            Random random = this.n_1700_B.M_3508_C();
            double d3 = d0 + random.nextGaussian() / 2.0;
            double d4 = d1 + random.nextGaussian() / 2.0;
            double d5 = d2 + random.nextGaussian() / 2.0;
            e_2866_D vector3d1 = this.n_1700_B.I_4348_c();
            this.n_1700_B.O_508_d.n_1700_B(ParticleTypes.t_148_a, d3, d4, d5, -vector3d.J_1907_R * (double)0.08f + vector3d1.J_1907_R, -vector3d.R_4764_Y * (double)0.3f + vector3d1.R_4764_Y, -vector3d.G_564_y * (double)0.08f + vector3d1.G_564_y);
            vector3d.J_1907_R(0.19634955f);
        }
    }

    @Override
    public void J_1907_R() {
        if (this.J_1907_R == null) {
            this.J_1907_R = e_2866_D.R_4764_Y(this.n_1700_B.O_508_d.n_1700_B(z_2963_s.n_1700_B.u_1723_Y, EndPodiumFeature.n_1700_B));
        }
        if (this.J_1907_R.R_4764_Y(this.n_1700_B.O_3598_v(), this.n_1700_B.X_2960_b(), this.n_1700_B.l_2647_k()) < 1.0) {
            this.n_1700_B.y_4642_Y().J_1907_R(Z_1164_j.u_1723_Y).w_1484_f();
            this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.v_4262_N);
        }
    }

    @Override
    public float P_1922_E() {
        return 1.5f;
    }

    @Override
    public float t_148_a() {
        float f = u_530_F.n_1700_B(N_4263_v.R_4764_Y(this.n_1700_B.I_4348_c())) + 1.0f;
        float f1 = Math.min(f, 40.0f);
        return f1 / f;
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = null;
    }

    @Override
    @Nullable
    public e_2866_D u_1723_Y() {
        return this.J_1907_R;
    }

    public Z_1164_j<DragonLandingPhase> G_564_y() {
        return Z_1164_j.G_564_y;
    }
}


