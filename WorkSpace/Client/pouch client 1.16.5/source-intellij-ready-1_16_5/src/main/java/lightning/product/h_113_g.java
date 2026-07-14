/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.mutable.MutableInt
 */
package lightning.product;

import java.util.List;
import lightning.product.D_38_f;
import lightning.product.I_4817_s;
import lightning.product.MobEffects;
import lightning.product.M_4239_y;
import lightning.product.SoundEvents;
import lightning.product.X_1924_A;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.i_2154_H;
import lightning.product.k_2610_C;
import lightning.product.EntityTypeTags;
import lightning.product.BlockEntityType;
import lightning.product.r_4811_B;
import lightning.product.ParticleTypes;
import lightning.product.u_530_F;
import lightning.product.MemoryModuleType;
import org.apache.commons.lang3.mutable.MutableInt;

public class h_113_g
extends i_2154_H
implements X_1924_A {
    private long G_564_y;
    public int n_1700_B;
    public boolean J_1907_R;
    public b_257_Y R_4764_Y;
    private List<r_4811_B> P_1922_E;
    private boolean u_1723_Y;
    private int v_4262_N;

    public h_113_g() {
        super(BlockEntityType.Y_1740_V);
    }

    @Override
    public boolean a_(int id, int type) {
        if (id == 1) {
            this.w_1484_f();
            this.v_4262_N = 0;
            this.R_4764_Y = b_257_Y.n_1700_B(type);
            this.n_1700_B = 0;
            this.J_1907_R = true;
            return true;
        }
        return super.a_(id, type);
    }

    @Override
    public void P_1922_E() {
        if (this.J_1907_R) {
            ++this.n_1700_B;
        }
        if (this.n_1700_B >= 50) {
            this.J_1907_R = false;
            this.n_1700_B = 0;
        }
        if (this.n_1700_B >= 5 && this.v_4262_N == 0 && this.s_956_w()) {
            this.u_1723_Y = true;
            this.v_4262_N();
        }
        if (this.u_1723_Y) {
            if (this.v_4262_N < 40) {
                ++this.v_4262_N;
            } else {
                this.n_1700_B(this.u_2550_I);
                this.J_1907_R(this.u_2550_I);
                this.u_1723_Y = false;
            }
        }
    }

    private void v_4262_N() {
        this.u_2550_I.n_1700_B((a_3913_L)null, this.x_607_J(), SoundEvents.ValueObject, D_38_f.P_1922_E, 1.0f, 1.0f);
    }

    public void n_1700_B(b_257_Y p_213939_1_) {
        c_1514_x blockpos = this.x_607_J();
        this.R_4764_Y = p_213939_1_;
        if (this.J_1907_R) {
            this.n_1700_B = 0;
        } else {
            this.J_1907_R = true;
        }
        this.u_2550_I.n_1700_B(blockpos, this.e_4240_b().J_1907_R(), 1, p_213939_1_.R_4764_Y());
    }

    private void w_1484_f() {
        c_1514_x blockpos = this.x_607_J();
        if (this.u_2550_I.X_933_l() > this.G_564_y + 60L || this.P_1922_E == null) {
            this.G_564_y = this.u_2550_I.X_933_l();
            I_4817_s axisalignedbb = new I_4817_s(blockpos).grow(48.0);
            this.P_1922_E = this.u_2550_I.n_1700_B(r_4811_B.class, axisalignedbb);
        }
        if (!this.u_2550_I.Y_259_p) {
            for (r_4811_B livingentity : this.P_1922_E) {
                if (!livingentity.RealmsLongRunningMcoTaskScreen() || livingentity.t_4219_U || !blockpos.withinDistance(livingentity.s_4990_V(), 32.0)) continue;
                livingentity.y_1945_D().n_1700_B(MemoryModuleType.A_4115_X, Long.valueOf(this.u_2550_I.X_933_l()));
            }
        }
    }

    private boolean s_956_w() {
        c_1514_x blockpos = this.x_607_J();
        for (r_4811_B livingentity : this.P_1922_E) {
            if (!livingentity.RealmsLongRunningMcoTaskScreen() || livingentity.t_4219_U || !blockpos.withinDistance(livingentity.s_4990_V(), 32.0) || !livingentity.f_4016_n().n_1700_B(EntityTypeTags.R_4764_Y)) continue;
            return true;
        }
        return false;
    }

    private void n_1700_B(b_4507_u p_222828_1_) {
        if (!p_222828_1_.Y_259_p) {
            this.P_1922_E.stream().filter(this::n_1700_B).forEach(this::J_1907_R);
        }
    }

    private void J_1907_R(b_4507_u p_222826_1_) {
        if (p_222826_1_.Y_259_p) {
            c_1514_x blockpos = this.x_607_J();
            MutableInt mutableint = new MutableInt(16700985);
            int i = (int)this.P_1922_E.stream().filter(p_222829_1_ -> blockpos.withinDistance(p_222829_1_.s_4990_V(), 48.0)).count();
            this.P_1922_E.stream().filter(this::n_1700_B).forEach(p_235655_4_ -> {
                float f = 1.0f;
                float f1 = u_530_F.n_1700_B((p_235655_4_.O_3598_v() - (double)blockpos.getX()) * (p_235655_4_.O_3598_v() - (double)blockpos.getX()) + (p_235655_4_.l_2647_k() - (double)blockpos.getZ()) * (p_235655_4_.l_2647_k() - (double)blockpos.getZ()));
                double d0 = (double)((float)blockpos.getX() + 0.5f) + (double)(1.0f / f1) * (p_235655_4_.O_3598_v() - (double)blockpos.getX());
                double d1 = (double)((float)blockpos.getZ() + 0.5f) + (double)(1.0f / f1) * (p_235655_4_.l_2647_k() - (double)blockpos.getZ());
                int j = u_530_F.n_1700_B((i - 21) / -2, 3, 15);
                for (int k = 0; k < j; ++k) {
                    int l = mutableint.addAndGet(5);
                    double d2 = (double)M_4239_y.n_1700_B.J_1907_R(l) / 255.0;
                    double d3 = (double)M_4239_y.n_1700_B.R_4764_Y(l) / 255.0;
                    double d4 = (double)M_4239_y.n_1700_B.G_564_y(l) / 255.0;
                    p_222826_1_.n_1700_B(ParticleTypes.Y_259_p, d0, (double)((float)blockpos.getY() + 0.5f), d1, d2, d3, d4);
                }
            });
        }
    }

    private boolean n_1700_B(r_4811_B p_222832_1_) {
        return p_222832_1_.RealmsLongRunningMcoTaskScreen() && !p_222832_1_.t_4219_U && this.x_607_J().withinDistance(p_222832_1_.s_4990_V(), 48.0) && p_222832_1_.f_4016_n().n_1700_B(EntityTypeTags.R_4764_Y);
    }

    private void J_1907_R(r_4811_B p_222827_1_) {
        p_222827_1_.n_1700_B(new k_2610_C(MobEffects.k_2293_S, 60));
    }
}


