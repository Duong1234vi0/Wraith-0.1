/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.O_728_b;
import lightning.product.SoundEvent;
import lightning.product.SoundInstance;
import lightning.product.g_2336_b;
import lightning.product.k_4218_M;
import lightning.product.WeighedSoundEvents;

public abstract class V_2473_P
implements SoundInstance {
    protected O_728_b J_1907_R;
    protected final D_38_f R_4764_Y;
    protected final g_2336_b G_564_y;
    protected float P_1922_E = 1.0f;
    protected float u_1723_Y = 1.0f;
    protected double v_4262_N;
    protected double w_1484_f;
    protected double t_148_a;
    protected boolean s_956_w;
    protected int u_2550_I;
    protected SoundInstance.n_1700_B M_588_G = SoundInstance.n_1700_B.J_1907_R;
    protected boolean P_4830_p;
    protected boolean h_1847_R;

    protected V_2473_P(SoundEvent soundIn, D_38_f categoryIn) {
        this(soundIn.n_1700_B(), categoryIn);
    }

    protected V_2473_P(g_2336_b soundId, D_38_f categoryIn) {
        this.G_564_y = soundId;
        this.R_4764_Y = categoryIn;
    }

    @Override
    public g_2336_b u_1723_Y() {
        return this.G_564_y;
    }

    @Override
    public WeighedSoundEvents n_1700_B(k_4218_M handler) {
        WeighedSoundEvents soundeventaccessor = handler.n_1700_B(this.G_564_y);
        this.J_1907_R = soundeventaccessor == null ? k_4218_M.n_1700_B : soundeventaccessor.R_4764_Y();
        return soundeventaccessor;
    }

    @Override
    public O_728_b v_4262_N() {
        return this.J_1907_R;
    }

    @Override
    public D_38_f w_1484_f() {
        return this.R_4764_Y;
    }

    @Override
    public boolean t_148_a() {
        return this.s_956_w;
    }

    @Override
    public int u_2550_I() {
        return this.u_2550_I;
    }

    @Override
    public float M_588_G() {
        return this.P_1922_E * this.J_1907_R.P_1922_E();
    }

    @Override
    public float P_4830_p() {
        return this.u_1723_Y * this.J_1907_R.u_1723_Y();
    }

    @Override
    public double h_1847_R() {
        return this.v_4262_N;
    }

    @Override
    public double Q_4569_t() {
        return this.w_1484_f;
    }

    @Override
    public double M_182_A() {
        return this.t_148_a;
    }

    @Override
    public SoundInstance.n_1700_B t_1786_h() {
        return this.M_588_G;
    }

    @Override
    public boolean s_956_w() {
        return this.h_1847_R;
    }

    public String toString() {
        return "SoundInstance[" + String.valueOf(this.G_564_y) + "]";
    }
}


