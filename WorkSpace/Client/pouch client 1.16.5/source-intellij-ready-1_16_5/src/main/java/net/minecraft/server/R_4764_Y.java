/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.server;

import java.io.OutputStream;
import lightning.product.LoggedPrintStream;

public class R_4764_Y
extends LoggedPrintStream {
    public R_4764_Y(String domainIn, OutputStream outStream) {
        super(domainIn, outStream);
    }

    @Override
    protected void n_1700_B(String string) {
        StackTraceElement[] astacktraceelement = Thread.currentThread().getStackTrace();
        StackTraceElement stacktraceelement = astacktraceelement[Math.min(3, astacktraceelement.length)];
        n_1700_B.info("[{}]@.({}:{}): {}", (Object)this.J_1907_R, (Object)stacktraceelement.getFileName(), (Object)stacktraceelement.getLineNumber(), (Object)string);
    }
}


