/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.LongRunningTask;
import lightning.product.RealmsServerAddress;
import lightning.product.b_3534_h;
import lightning.product.MinecraftClient;
import lightning.product.k_2603_m;
import lightning.product.q_1982_R;
import lightning.product.dtoRealmsServerAddress;

public class ConnectTask
extends LongRunningTask {
    private final b_3534_h R_4764_Y;
    private final q_1982_R G_564_y;
    private final dtoRealmsServerAddress P_1922_E;

    public ConnectTask(k_2603_m p_i242127_1_, q_1982_R p_i242127_2_, dtoRealmsServerAddress p_i242127_3_) {
        this.G_564_y = p_i242127_2_;
        this.P_1922_E = p_i242127_3_;
        this.R_4764_Y = new b_3534_h(p_i242127_1_);
    }

    @Override
    public void run() {
        this.J_1907_R(new F_2904_S("mco.connect.connecting"));
        RealmsServerAddress realmsserveraddress = RealmsServerAddress.n_1700_B(this.P_1922_E.n_1700_B);
        this.R_4764_Y.n_1700_B(this.G_564_y, realmsserveraddress.n_1700_B(), realmsserveraddress.J_1907_R());
    }

    @Override
    public void G_564_y() {
        this.R_4764_Y.n_1700_B();
        MinecraftClient.A_4115_X().z_4693_k().J_1907_R();
    }

    @Override
    public void J_1907_R() {
        this.R_4764_Y.J_1907_R();
    }
}



