/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.TieredItem;
import lightning.product.A_4919_q;
import lightning.product.DebugPackets;
import lightning.product.C_4114_x;
import lightning.product.I_1869_h;
import lightning.product.MobEffects;
import lightning.product.U_2912_j;
import lightning.product.GoalUtils;
import lightning.product.b_4507_u;
import lightning.product.e_3591_l;
import lightning.product.h_256_u;
import lightning.product.i_2099_H;
import lightning.product.ZombifiedPiglin;
import lightning.product.EntityDataSerializers;
import lightning.product.Monster;
import lightning.product.k_2610_C;
import lightning.product.q_2464_b;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public abstract class AbstractPiglin
extends Monster {
    protected static final h_256_u<Boolean> n_1700_B = C_4114_x.n_1700_B(AbstractPiglin.class, EntityDataSerializers.t_148_a);
    protected int J_1907_R = 0;

    public AbstractPiglin(t_5_h<? extends AbstractPiglin> p_i241915_1_, b_4507_u p_i241915_2_) {
        super((t_5_h<? extends Monster>)p_i241915_1_, p_i241915_2_);
        this.R_4764_Y(true);
        this.f_2787_O();
        this.n_1700_B(I_1869_h.M_588_G, 16.0f);
        this.n_1700_B(I_1869_h.P_4830_p, -1.0f);
    }

    private void f_2787_O() {
        if (GoalUtils.n_1700_B(this)) {
            ((i_2099_H)this.e_4240_b()).n_1700_B(true);
        }
    }

    protected abstract boolean u_1723_Y();

    public void w_1457_N(boolean p_242340_1_) {
        this.D_60_a().J_1907_R(n_1700_B, p_242340_1_);
    }

    protected boolean y_4642_Y() {
        return this.D_60_a().n_1700_B(n_1700_B);
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(n_1700_B, false);
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        if (this.y_4642_Y()) {
            compound.n_1700_B("IsImmuneToZombification", true);
        }
        compound.J_1907_R("TimeInOverworld", this.J_1907_R);
    }

    @Override
    public double O_2151_c() {
        return this.d_() ? -0.05 : -0.45;
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("IsImmuneToZombification"));
        this.J_1907_R = compound.w_1484_f("TimeInOverworld");
    }

    @Override
    protected void X_933_l() {
        super.X_933_l();
        this.J_1907_R = this.V_1176_p() ? ++this.J_1907_R : 0;
        if (this.J_1907_R > 300) {
            this.h_973_D();
            this.R_4764_Y((e_3591_l)this.O_508_d);
        }
    }

    public boolean V_1176_p() {
        return !this.O_508_d.G_624_v().v_4262_N() && !this.y_4642_Y() && !this.n_473_l();
    }

    protected void R_4764_Y(e_3591_l p_234416_1_) {
        ZombifiedPiglin zombifiedpiglinentity = this.n_1700_B(t_5_h.c_132_F, true);
        if (zombifiedpiglinentity != null) {
            zombifiedpiglinentity.n_1700_B(new k_2610_C(MobEffects.t_148_a, 200, 0));
        }
    }

    public boolean y_2447_C() {
        return !this.d_();
    }

    public abstract q_2464_b J_3635_s();

    @Override
    @Nullable
    public r_4811_B t_148_a() {
        return this.Y_776_s.R_4764_Y(MemoryModuleType.Q_4569_t).orElse(null);
    }

    protected boolean o_82_k() {
        return this.A_2714_y().J_1907_R() instanceof TieredItem;
    }

    @Override
    public void G_624_v() {
        if (A_4919_q.G_564_y(this)) {
            super.G_624_v();
        }
    }

    @Override
    protected void g_164_R() {
        super.g_164_R();
        DebugPackets.n_1700_B(this);
    }

    protected abstract void h_973_D();
}


