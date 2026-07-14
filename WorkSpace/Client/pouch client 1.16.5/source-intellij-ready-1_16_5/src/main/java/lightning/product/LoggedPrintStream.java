/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.io.OutputStream;
import java.io.PrintStream;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggedPrintStream
extends PrintStream {
    protected static final Logger n_1700_B = LogManager.getLogger();
    protected final String J_1907_R;

    public LoggedPrintStream(String domainIn, OutputStream outStream) {
        super(outStream);
        this.J_1907_R = domainIn;
    }

    @Override
    public void println(@Nullable String p_println_1_) {
        this.n_1700_B(p_println_1_);
    }

    @Override
    public void println(Object p_println_1_) {
        this.n_1700_B(String.valueOf(p_println_1_));
    }

    protected void n_1700_B(@Nullable String string) {
        n_1700_B.info("[{}]: {}", (Object)this.J_1907_R, (Object)string);
    }
}


