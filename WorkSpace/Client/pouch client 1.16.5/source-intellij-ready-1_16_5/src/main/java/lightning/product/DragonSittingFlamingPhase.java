/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_1132_Q;
import lightning.product.MobEffects;
import lightning.product.Z_1164_j;
import lightning.product.AbstractDragonSittingPhase;
import lightning.product.b_2971_b;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.k_2610_C;
import lightning.product.ParticleTypes;
import lightning.product.u_530_F;

public class DragonSittingFlamingPhase
extends AbstractDragonSittingPhase {
    private int J_1907_R;
    private int R_4764_Y;
    private B_1132_Q G_564_y;

    public DragonSittingFlamingPhase(b_2971_b dragonIn) {
        super(dragonIn);
    }

    @Override
    public void n_1700_B() {
        ++this.J_1907_R;
        if (this.J_1907_R % 2 == 0 && this.J_1907_R < 10) {
            e_2866_D vector3d = this.n_1700_B.G_564_y(1.0f).G_564_y();
            vector3d.J_1907_R(-0.7853982f);
            double d0 = this.n_1700_B.h_1847_R.O_3598_v();
            double d1 = this.n_1700_B.h_1847_R.P_1922_E(0.5);
            double d2 = this.n_1700_B.h_1847_R.l_2647_k();
            for (int i = 0; i < 8; ++i) {
                double d3 = d0 + this.n_1700_B.M_3508_C().nextGaussian() / 2.0;
                double d4 = d1 + this.n_1700_B.M_3508_C().nextGaussian() / 2.0;
                double d5 = d2 + this.n_1700_B.M_3508_C().nextGaussian() / 2.0;
                for (int j = 0; j < 6; ++j) {
                    this.n_1700_B.O_508_d.n_1700_B(ParticleTypes.t_148_a, d3, d4, d5, -vector3d.J_1907_R * (double)0.08f * (double)j, -vector3d.R_4764_Y * (double)0.6f, -vector3d.G_564_y * (double)0.08f * (double)j);
                }
                vector3d.J_1907_R(0.19634955f);
            }
        }
    }

    @Override
    public void J_1907_R() {
        ++this.J_1907_R;
        if (this.J_1907_R >= 200) {
            if (this.R_4764_Y >= 4) {
                this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.P_1922_E);
            } else {
                this.n_1700_B.y_4642_Y().n_1700_B(Z_1164_j.v_4262_N);
            }
        } else if (this.J_1907_R == 10) {
            double d2;
            e_2866_D vector3d = new e_2866_D(this.n_1700_B.h_1847_R.O_3598_v() - this.n_1700_B.O_3598_v(), 0.0, this.n_1700_B.h_1847_R.l_2647_k() - this.n_1700_B.l_2647_k()).G_564_y();
            float f = 5.0f;
            double d0 = this.n_1700_B.h_1847_R.O_3598_v() + vector3d.J_1907_R * 5.0 / 2.0;
            double d1 = this.n_1700_B.h_1847_R.l_2647_k() + vector3d.G_564_y * 5.0 / 2.0;
            double d3 = d2 = this.n_1700_B.h_1847_R.P_1922_E(0.5);
            c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B(d0, d2, d1);
            while (this.n_1700_B.O_508_d.u_1723_Y(blockpos$mutable)) {
                if ((d3 -= 1.0) < 0.0) {
                    d3 = d2;
                    break;
                }
                blockpos$mutable.n_1700_B(d0, d3, d1);
            }
            d3 = u_530_F.R_4764_Y(d3) + 1;
            this.G_564_y = new B_1132_Q(this.n_1700_B.O_508_d, d0, d3, d1);
            this.G_564_y.n_1700_B(this.n_1700_B);
            this.G_564_y.n_1700_B(5.0f);
            this.G_564_y.J_1907_R(200);
            this.G_564_y.n_1700_B(ParticleTypes.t_148_a);
            this.G_564_y.n_1700_B(new k_2610_C(MobEffects.v_4262_N));
            this.n_1700_B.O_508_d.a_(this.G_564_y);
        }
    }

    @Override
    public void R_4764_Y() {
        this.J_1907_R = 0;
        ++this.R_4764_Y;
    }

    @Override
    public void v_4262_N() {
        if (this.G_564_y != null) {
            this.G_564_y.Ops();
            this.G_564_y = null;
        }
    }

    public Z_1164_j<DragonSittingFlamingPhase> G_564_y() {
        return Z_1164_j.u_1723_Y;
    }

    public void w_1484_f() {
        this.R_4764_Y = 0;
    }
}


