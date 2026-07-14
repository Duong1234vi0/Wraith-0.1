/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.ints.Int2BooleanFunction
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import it.unimi.dsi.fastutil.ints.Int2BooleanFunction;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import lightning.product.SharedConstants;
import lightning.product.StrictQueue;
import lightning.product.ProcessorHandle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class U_3758_m<T>
implements AutoCloseable,
Runnable,
ProcessorHandle<T> {
    private static final Logger J_1907_R = LogManager.getLogger();
    private final AtomicInteger R_4764_Y = new AtomicInteger(0);
    public final StrictQueue<? super T, ? extends Runnable> n_1700_B;
    private final Executor G_564_y;
    private final String P_1922_E;

    public static U_3758_m<Runnable> n_1700_B(Executor p_213144_0_, String p_213144_1_) {
        return new U_3758_m<Runnable>(new StrictQueue.R_4764_Y(new ConcurrentLinkedQueue()), p_213144_0_, p_213144_1_);
    }

    public U_3758_m(StrictQueue<? super T, ? extends Runnable> queueIn, Executor delegateIn, String nameIn) {
        this.G_564_y = delegateIn;
        this.n_1700_B = queueIn;
        this.P_1922_E = nameIn;
    }

    private boolean J_1907_R() {
        int i;
        do {
            if (((i = this.R_4764_Y.get()) & 3) == 0) continue;
            return false;
        } while (!this.R_4764_Y.compareAndSet(i, i | 2));
        return true;
    }

    private void R_4764_Y() {
        int i;
        while (!this.R_4764_Y.compareAndSet(i = this.R_4764_Y.get(), i & 0xFFFFFFFD)) {
        }
    }

    private boolean G_564_y() {
        if ((this.R_4764_Y.get() & 1) != 0) {
            return false;
        }
        return !this.n_1700_B.J_1907_R();
    }

    @Override
    public void close() {
        int i;
        while (!this.R_4764_Y.compareAndSet(i = this.R_4764_Y.get(), i | 1)) {
        }
    }

    private boolean P_1922_E() {
        return (this.R_4764_Y.get() & 2) != 0;
    }

    private boolean u_1723_Y() {
        String s;
        Thread thread;
        if (!this.P_1922_E()) {
            return false;
        }
        Runnable runnable = this.n_1700_B.n_1700_B();
        if (runnable == null) {
            return false;
        }
        if (SharedConstants.G_564_y) {
            thread = Thread.currentThread();
            s = thread.getName();
            thread.setName(this.P_1922_E);
        } else {
            thread = null;
            s = null;
        }
        runnable.run();
        if (thread != null) {
            thread.setName(s);
        }
        return true;
    }

    @Override
    public void run() {
        try {
            this.n_1700_B(p_213147_0_ -> p_213147_0_ == 0);
        }
        finally {
            this.R_4764_Y();
            this.v_4262_N();
        }
    }

    @Override
    public void n_1700_B(T taskIn) {
        this.n_1700_B.n_1700_B(taskIn);
        this.v_4262_N();
    }

    private void v_4262_N() {
        if (this.G_564_y() && this.J_1907_R()) {
            try {
                this.G_564_y.execute(this);
            }
            catch (RejectedExecutionException rejectedexecutionexception1) {
                try {
                    this.G_564_y.execute(this);
                }
                catch (RejectedExecutionException rejectedexecutionexception) {
                    J_1907_R.error("Cound not schedule mailbox", (Throwable)rejectedexecutionexception);
                }
            }
        }
    }

    private int n_1700_B(Int2BooleanFunction p_213145_1_) {
        int i = 0;
        while (p_213145_1_.get(i) && this.u_1723_Y()) {
            ++i;
        }
        return i;
    }

    public String toString() {
        return this.P_1922_E + " " + this.R_4764_Y.get() + " " + this.n_1700_B.J_1907_R();
    }

    @Override
    public String k_() {
        return this.P_1922_E;
    }
}


