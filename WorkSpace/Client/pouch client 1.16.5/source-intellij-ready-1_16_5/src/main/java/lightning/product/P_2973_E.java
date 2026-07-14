/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.Validate
 */
package lightning.product;

import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.L_461_d;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.U_2912_j;
import lightning.product.W_2163_m;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.n_1494_c;
import lightning.product.q_4099_E;
import lightning.product.LightningBolt;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.u_782_h;
import org.apache.commons.lang3.Validate;

public abstract class P_2973_E
extends N_4263_v {
    protected static final Predicate<N_4263_v> n_1700_B = entity -> entity instanceof P_2973_E;
    private int G_564_y;
    protected c_1514_x J_1907_R;
    protected b_257_Y R_4764_Y = b_257_Y.G_564_y;

    protected P_2973_E(t_5_h<? extends P_2973_E> type, b_4507_u p_i48561_2_) {
        super(type, p_i48561_2_);
    }

    protected P_2973_E(t_5_h<? extends P_2973_E> type, b_4507_u world, c_1514_x hangingPos) {
        this(type, world);
        this.J_1907_R = hangingPos;
    }

    @Override
    protected void a_() {
    }

    protected void n_1700_B(b_257_Y facingDirectionIn) {
        Validate.notNull((Object)facingDirectionIn);
        Validate.isTrue((boolean)facingDirectionIn.h_1847_R().G_564_y());
        this.R_4764_Y = facingDirectionIn;
        this.j_276_v = this.p_178_J = (float)(this.R_4764_Y.G_564_y() * 90);
        this.P_1922_E();
    }

    protected void P_1922_E() {
        if (this.R_4764_Y != null) {
            double d0 = (double)this.J_1907_R.getX() + 0.5;
            double d1 = (double)this.J_1907_R.getY() + 0.5;
            double d2 = (double)this.J_1907_R.getZ() + 0.5;
            double d3 = 0.46875;
            double d4 = this.n_1700_B(this.v_4262_N());
            double d5 = this.n_1700_B(this.w_1484_f());
            d0 -= (double)this.R_4764_Y.t_148_a() * 0.46875;
            d2 -= (double)this.R_4764_Y.u_2550_I() * 0.46875;
            b_257_Y direction = this.R_4764_Y.w_1484_f();
            this.Q_4569_t(d0 += d4 * (double)direction.t_148_a(), d1 += d5, d2 += d4 * (double)direction.u_2550_I());
            double d6 = this.v_4262_N();
            double d7 = this.w_1484_f();
            double d8 = this.v_4262_N();
            if (this.R_4764_Y.h_1847_R() == b_257_Y.n_1700_B.R_4764_Y) {
                d8 = 1.0;
            } else {
                d6 = 1.0;
            }
            this.n_1700_B(new I_4817_s(d0 - (d6 /= 32.0), d1 - (d7 /= 32.0), d2 - (d8 /= 32.0), d0 + d6, d1 + d7, d2 + d8));
        }
    }

    private double n_1700_B(int p_190202_1_) {
        return p_190202_1_ % 32 == 0 ? 0.5 : 0.0;
    }

    @Override
    public void v_() {
        if (!this.O_508_d.Y_259_p) {
            if (this.X_2960_b() < -64.0) {
                this.j_1564_a();
            }
            if (this.G_564_y++ == 100) {
                this.G_564_y = 0;
                if (!this.t_4219_U && !this.u_1723_Y()) {
                    this.Ops();
                    this.n_1700_B((N_4263_v)null);
                }
            }
        }
    }

    public boolean u_1723_Y() {
        if (!this.O_508_d.u_1723_Y(this)) {
            return false;
        }
        int i = Math.max(1, this.v_4262_N() / 16);
        int j = Math.max(1, this.w_1484_f() / 16);
        c_1514_x blockpos = this.J_1907_R.offset(this.R_4764_Y.u_1723_Y());
        b_257_Y direction = this.R_4764_Y.w_1484_f();
        c_1514_x.n_1700_B blockpos$mutable = new c_1514_x.n_1700_B();
        for (int k = 0; k < i; ++k) {
            for (int l = 0; l < j; ++l) {
                int i1 = (i - 1) / -2;
                int j1 = (j - 1) / -2;
                blockpos$mutable.n_1700_B(blockpos).n_1700_B(direction, k + i1).n_1700_B(b_257_Y.J_1907_R, l + j1);
                K_4074_S blockstate = this.O_508_d.getBlockState(blockpos$mutable);
                if (blockstate.R_4764_Y().J_1907_R() || u_782_h.P_4830_p(blockstate)) continue;
                return false;
            }
        }
        return this.O_508_d.J_1907_R((N_4263_v)this, this.i_601_W(), n_1700_B).isEmpty();
    }

    @Override
    public boolean C_290_v() {
        return true;
    }

    @Override
    public boolean t_1786_h(N_4263_v entityIn) {
        if (entityIn instanceof a_3913_L) {
            a_3913_L playerentity = (a_3913_L)entityIn;
            return !this.O_508_d.n_1700_B(playerentity, this.J_1907_R) ? true : this.n_1700_B(P_11_z.n_1700_B(playerentity), 0.0f);
        }
        return false;
    }

    @Override
    public b_257_Y o_2767_H() {
        return this.R_4764_Y;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        if (this.n_1700_B(source)) {
            return false;
        }
        if (!this.t_4219_U && !this.O_508_d.Y_259_p) {
            this.Ops();
            this.RealmsCreateRealmScreen();
            this.n_1700_B(source.u_2550_I());
        }
        return true;
    }

    @Override
    public void n_1700_B(L_461_d typeIn, e_2866_D pos) {
        if (!this.O_508_d.Y_259_p && !this.t_4219_U && pos.v_4262_N() > 0.0) {
            this.Ops();
            this.n_1700_B((N_4263_v)null);
        }
    }

    @Override
    public void w_1484_f(double x, double y, double z) {
        if (!this.O_508_d.Y_259_p && !this.t_4219_U && x * x + y * y + z * z > 0.0) {
            this.Ops();
            this.n_1700_B((N_4263_v)null);
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        c_1514_x blockpos = this.u_2550_I();
        compound.J_1907_R("TileX", blockpos.getX());
        compound.J_1907_R("TileY", blockpos.getY());
        compound.J_1907_R("TileZ", blockpos.getZ());
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        this.J_1907_R = new c_1514_x(compound.w_1484_f("TileX"), compound.w_1484_f("TileY"), compound.w_1484_f("TileZ"));
    }

    public abstract int v_4262_N();

    public abstract int w_1484_f();

    public abstract void n_1700_B(@Nullable N_4263_v var1);

    public abstract void t_148_a();

    @Override
    public n_1494_c n_1700_B(Z_1993_T stack, float offsetY) {
        n_1494_c itementity = new n_1494_c(this.O_508_d, this.O_3598_v() + (double)((float)this.R_4764_Y.t_148_a() * 0.15f), this.X_2960_b() + (double)offsetY, this.l_2647_k() + (double)((float)this.R_4764_Y.u_2550_I() * 0.15f), stack);
        itementity.t_148_a();
        this.O_508_d.a_(itementity);
        return itementity;
    }

    @Override
    protected boolean J_4256_G() {
        return false;
    }

    @Override
    public void J_1907_R(double x, double y, double z) {
        this.J_1907_R = new c_1514_x(x, y, z);
        this.P_1922_E();
        this.LongRunningTask = true;
    }

    public c_1514_x u_2550_I() {
        return this.J_1907_R;
    }

    @Override
    public float n_1700_B(W_2163_m transformRotation) {
        if (this.R_4764_Y.h_1847_R() != b_257_Y.n_1700_B.J_1907_R) {
            switch (transformRotation) {
                case R_4764_Y: {
                    this.R_4764_Y = this.R_4764_Y.u_1723_Y();
                    break;
                }
                case G_564_y: {
                    this.R_4764_Y = this.R_4764_Y.w_1484_f();
                    break;
                }
                case J_1907_R: {
                    this.R_4764_Y = this.R_4764_Y.v_4262_N();
                }
            }
        }
        float f = u_530_F.v_4262_N(this.p_178_J);
        switch (transformRotation) {
            case R_4764_Y: {
                return f + 180.0f;
            }
            case G_564_y: {
                return f + 90.0f;
            }
            case J_1907_R: {
                return f + 270.0f;
            }
        }
        return f;
    }

    @Override
    public float n_1700_B(q_4099_E transformMirror) {
        return this.n_1700_B(transformMirror.n_1700_B(this.R_4764_Y));
    }

    @Override
    public void n_1700_B(e_3591_l p_241841_1_, LightningBolt p_241841_2_) {
    }

    @Override
    public void g_() {
    }
}


