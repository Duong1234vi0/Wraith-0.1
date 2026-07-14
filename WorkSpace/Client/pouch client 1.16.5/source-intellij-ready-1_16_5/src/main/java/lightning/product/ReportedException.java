/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.n_3236_c;

public class ReportedException
extends RuntimeException {
    private final n_3236_c n_1700_B;

    public ReportedException(n_3236_c report) {
        this.n_1700_B = report;
    }

    public n_3236_c n_1700_B() {
        return this.n_1700_B;
    }

    @Override
    public Throwable getCause() {
        return this.n_1700_B.J_1907_R();
    }

    @Override
    public String getMessage() {
        return this.n_1700_B.n_1700_B();
    }
}


