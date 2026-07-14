/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import lightning.product.DefaultUncaughtExceptionHandlerWithName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class B_553_u
implements Runnable {
    private static final Logger G_564_y = LogManager.getLogger();
    private static final AtomicInteger P_1922_E = new AtomicInteger(0);
    protected volatile boolean n_1700_B;
    protected final String J_1907_R;
    @Nullable
    protected Thread R_4764_Y;

    protected B_553_u(String p_i231426_1_) {
        this.J_1907_R = p_i231426_1_;
    }

    public synchronized boolean J_1907_R() {
        if (this.n_1700_B) {
            return true;
        }
        this.n_1700_B = true;
        this.R_4764_Y = new Thread((Runnable)this, this.J_1907_R + " #" + P_1922_E.incrementAndGet());
        this.R_4764_Y.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandlerWithName(G_564_y));
        this.R_4764_Y.start();
        G_564_y.info("Thread {} started", (Object)this.J_1907_R);
        return true;
    }

    public synchronized void n_1700_B() {
        this.n_1700_B = false;
        if (null != this.R_4764_Y) {
            int i = 0;
            while (this.R_4764_Y.isAlive()) {
                try {
                    this.R_4764_Y.join(1000L);
                    if (++i >= 5) {
                        G_564_y.warn("Waited {} seconds attempting force stop!", (Object)i);
                        continue;
                    }
                    if (!this.R_4764_Y.isAlive()) continue;
                    G_564_y.warn("Thread {} ({}) failed to exit after {} second(s)", (Object)this, (Object)this.R_4764_Y.getState(), (Object)i, (Object)new Exception("Stack:"));
                    this.R_4764_Y.interrupt();
                }
                catch (InterruptedException interruptedException) {}
            }
            G_564_y.info("Thread {} stopped", (Object)this.J_1907_R);
            this.R_4764_Y = null;
        }
    }

    public boolean R_4764_Y() {
        return this.n_1700_B;
    }
}


