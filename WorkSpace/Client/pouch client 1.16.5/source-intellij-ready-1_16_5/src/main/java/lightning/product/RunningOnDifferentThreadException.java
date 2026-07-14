/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public final class RunningOnDifferentThreadException
extends RuntimeException {
    public static final RunningOnDifferentThreadException n_1700_B = new RunningOnDifferentThreadException();

    private RunningOnDifferentThreadException() {
        this.setStackTrace(new StackTraceElement[0]);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        this.setStackTrace(new StackTraceElement[0]);
        return this;
    }
}


