/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.l_3595_o;

public class RealmsServerAddress {
    private final String n_1700_B;
    private final int J_1907_R;

    protected RealmsServerAddress(String hostIn, int portIn) {
        this.n_1700_B = hostIn;
        this.J_1907_R = portIn;
    }

    public String n_1700_B() {
        return this.n_1700_B;
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }

    public static RealmsServerAddress n_1700_B(String p_231413_0_) {
        l_3595_o serveraddress = l_3595_o.n_1700_B(p_231413_0_);
        return new RealmsServerAddress(serveraddress.n_1700_B(), serveraddress.J_1907_R());
    }
}


