/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.u_744_e;

public class RetryCallException
extends u_744_e {
    public final int P_1922_E;

    public RetryCallException(int p_i242136_1_, int p_i242136_2_) {
        super(p_i242136_2_, "Retry operation", -1, "");
        this.P_1922_E = p_i242136_1_ >= 0 && p_i242136_1_ <= 120 ? p_i242136_1_ : 5;
    }
}


