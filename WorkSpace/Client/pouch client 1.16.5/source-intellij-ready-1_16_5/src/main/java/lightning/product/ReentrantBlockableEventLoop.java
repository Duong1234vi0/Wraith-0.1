/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.H_3272_P;

public abstract class ReentrantBlockableEventLoop<R extends Runnable>
extends H_3272_P<R> {
    private int n_1700_B;

    public ReentrantBlockableEventLoop(String name) {
        super(name);
    }

    @Override
    protected boolean j_() {
        return this.J_4256_G() || super.j_();
    }

    protected boolean J_4256_G() {
        return this.n_1700_B != 0;
    }

    @Override
    protected void P_1922_E(R taskIn) {
        ++this.n_1700_B;
        try {
            super.P_1922_E(taskIn);
        }
        finally {
            --this.n_1700_B;
        }
    }
}


