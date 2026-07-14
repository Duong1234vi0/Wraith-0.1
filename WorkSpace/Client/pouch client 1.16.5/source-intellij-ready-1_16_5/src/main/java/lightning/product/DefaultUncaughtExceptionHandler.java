/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import org.apache.logging.log4j.Logger;

public class DefaultUncaughtExceptionHandler
implements Thread.UncaughtExceptionHandler {
    private final Logger n_1700_B;

    public DefaultUncaughtExceptionHandler(Logger logger) {
        this.n_1700_B = logger;
    }

    @Override
    public void uncaughtException(Thread p_uncaughtException_1_, Throwable p_uncaughtException_2_) {
        this.n_1700_B.error("Caught previously unhandled exception :", p_uncaughtException_2_);
    }
}


