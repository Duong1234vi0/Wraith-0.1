/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;

public class ExceptionCollector<T extends Throwable> {
    @Nullable
    private T n_1700_B;

    public void n_1700_B(T p_233003_1_) {
        if (this.n_1700_B == null) {
            this.n_1700_B = p_233003_1_;
        } else {
            ((Throwable)this.n_1700_B).addSuppressed((Throwable)p_233003_1_);
        }
    }

    public void n_1700_B() throws T {
        if (this.n_1700_B != null) {
            throw this.n_1700_B;
        }
    }
}


