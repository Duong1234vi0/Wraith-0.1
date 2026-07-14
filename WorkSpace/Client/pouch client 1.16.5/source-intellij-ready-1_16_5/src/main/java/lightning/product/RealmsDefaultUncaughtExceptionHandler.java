/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import org.apache.logging.log4j.Logger;

public class RealmsDefaultUncaughtExceptionHandler
implements Thread.UncaughtExceptionHandler {
    private final Logger n_1700_B;

    public RealmsDefaultUncaughtExceptionHandler(Logger p_i51787_1_) {
        this.n_1700_B = p_i51787_1_;
    }

    @Override
    public void uncaughtException(Thread p_uncaughtException_1_, Throwable p_uncaughtException_2_) {
        this.n_1700_B.error("Caught previously unhandled exception :");
        this.n_1700_B.error((Object)p_uncaughtException_2_);
    }
}


