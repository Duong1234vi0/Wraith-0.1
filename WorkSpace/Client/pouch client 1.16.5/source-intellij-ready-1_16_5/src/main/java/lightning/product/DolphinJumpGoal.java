/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.FluidTags;
import lightning.product.E_2941_n;
import lightning.product.N_4263_v;
import lightning.product.SoundEvents;
import lightning.product.Y_559_r;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.e_2866_D;
import lightning.product.u_530_F;

public class DolphinJumpGoal
extends E_2941_n {
    private static final int[] n_1700_B = new int[]{0, 1, 4, 5, 6, 7};
    private final Y_559_r J_1907_R;
    private final int R_4764_Y;
    private boolean G_564_y;

    public DolphinJumpGoal(Y_559_r dolphin, int p_i50329_2_) {
        this.J_1907_R = dolphin;
        this.R_4764_Y = p_i50329_2_;
    }

    @Override
    public boolean n_1700_B() {
        if (this.J_1907_R.M_3508_C().nextInt(this.R_4764_Y) != 0) {
            return false;
        }
        b_257_Y direction = this.J_1907_R.d_2545_n();
        int i = direction.t_148_a();
        int j = direction.u_2550_I();
        c_1514_x blockpos = this.J_1907_R.b_2312_j();
        for (int k : n_1700_B) {
            if (this.n_1700_B(blockpos, i, j, k) && this.J_1907_R(blockpos, i, j, k)) continue;
            return false;
        }
        return true;
    }

    private boolean n_1700_B(c_1514_x pos, int dx, int dz, int scale) {
        c_1514_x blockpos = pos.add(dx * scale, 0, dz * scale);
        return this.J_1907_R.O_508_d.getFluidState(blockpos).n_1700_B(FluidTags.J_1907_R) && !this.J_1907_R.O_508_d.getBlockState(blockpos).R_4764_Y().R_4764_Y();
    }

    private boolean J_1907_R(c_1514_x pos, int dx, int dz, int scale) {
        return this.J_1907_R.O_508_d.getBlockState(pos.add(dx * scale, 1, dz * scale)).v_4262_N() && this.J_1907_R.O_508_d.getBlockState(pos.add(dx * scale, 2, dz * scale)).v_4262_N();
    }

    @Override
    public boolean J_1907_R() {
        double d0 = this.J_1907_R.I_4348_c().R_4764_Y;
        return !(d0 * d0 < (double)0.03f && this.J_1907_R.f_4016_n != 0.0f && Math.abs(this.J_1907_R.f_4016_n) < 10.0f && this.J_1907_R.RowButton() || this.J_1907_R.M_1641_O());
    }

    @Override
    public boolean r_() {
        return false;
    }

    @Override
    public void R_4764_Y() {
        b_257_Y direction = this.J_1907_R.d_2545_n();
        this.J_1907_R.v_4262_N(this.J_1907_R.I_4348_c().J_1907_R((double)direction.t_148_a() * 0.6, 0.7, (double)direction.u_2550_I() * 0.6));
        this.J_1907_R.e_4240_b().h_1847_R();
    }

    @Override
    public void G_564_y() {
        this.J_1907_R.f_4016_n = 0.0f;
    }

    @Override
    public void P_1922_E() {
        boolean flag = this.G_564_y;
        if (!flag) {
            FluidState fluidstate = this.J_1907_R.O_508_d.getFluidState(this.J_1907_R.b_2312_j());
            this.G_564_y = fluidstate.n_1700_B(FluidTags.J_1907_R);
        }
        if (this.G_564_y && !flag) {
            this.J_1907_R.n_1700_B(SoundEvents.h_2848_I, 1.0f, 1.0f);
        }
        e_2866_D vector3d = this.J_1907_R.I_4348_c();
        if (vector3d.R_4764_Y * vector3d.R_4764_Y < (double)0.03f && this.J_1907_R.f_4016_n != 0.0f) {
            this.J_1907_R.f_4016_n = u_530_F.t_148_a(this.J_1907_R.f_4016_n, 0.0f, 0.2f);
        } else {
            double d0 = Math.sqrt(N_4263_v.R_4764_Y(vector3d));
            double d1 = Math.signum(-vector3d.R_4764_Y) * Math.acos(d0 / vector3d.u_1723_Y()) * 57.2957763671875;
            this.J_1907_R.f_4016_n = (float)d1;
        }
    }
}


