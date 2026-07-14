/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_1170_F;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1815_U;
import lightning.product.U_2912_j;
import lightning.product.b_2971_b;
import lightning.product.Packet;

public class EnderDragonPart
extends N_4263_v {
    public final b_2971_b n_1700_B;
    public final String J_1907_R;
    private final R_1815_U R_4764_Y;

    public EnderDragonPart(b_2971_b dragon, String p_i50232_2_, float p_i50232_3_, float p_i50232_4_) {
        super(dragon.f_4016_n(), dragon.O_508_d);
        this.R_4764_Y = R_1815_U.J_1907_R(p_i50232_3_, p_i50232_4_);
        this.g_();
        this.n_1700_B = dragon;
        this.J_1907_R = p_i50232_2_;
    }

    @Override
    protected void a_() {
    }

    @Override
    protected void J_1907_R(U_2912_j compound) {
    }

    @Override
    protected void n_1700_B(U_2912_j compound) {
    }

    @Override
    public boolean C_290_v() {
        return true;
    }

    @Override
    public boolean n_1700_B(P_11_z source, float amount) {
        return this.n_1700_B(source) ? false : this.n_1700_B.n_1700_B(this, source, amount);
    }

    @Override
    public boolean M_182_A(N_4263_v entityIn) {
        return this == entityIn || this.n_1700_B == entityIn;
    }

    @Override
    public Packet<?> f_() {
        throw new UnsupportedOperationException();
    }

    @Override
    public R_1815_U n_1700_B(I_1170_F poseIn) {
        return this.R_4764_Y;
    }
}


