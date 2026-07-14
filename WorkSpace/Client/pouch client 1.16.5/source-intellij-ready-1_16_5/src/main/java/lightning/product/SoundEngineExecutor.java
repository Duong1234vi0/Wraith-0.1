/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.locks.LockSupport;
import lightning.product.H_3272_P;

public class SoundEngineExecutor
extends H_3272_P<Runnable> {
    private Thread n_1700_B = this.R_4764_Y();
    private volatile boolean J_1907_R;

    public SoundEngineExecutor() {
        super("Sound executor");
    }

    private Thread R_4764_Y() {
        Thread thread = new Thread(this::G_564_y);
        thread.setDaemon(true);
        thread.setName("Sound engine");
        thread.start();
        return thread;
    }

    @Override
    protected Runnable n_1700_B(Runnable runnable) {
        return runnable;
    }

    @Override
    protected boolean J_1907_R(Runnable runnable) {
        return !this.J_1907_R;
    }

    @Override
    protected Thread l_1233_K() {
        return this.n_1700_B;
    }

    private void G_564_y() {
        while (!this.J_1907_R) {
            this.R_4764_Y(() -> this.J_1907_R);
        }
    }

    @Override
    protected void m_() {
        LockSupport.park("waiting for tasks");
    }

    public void J_1907_R() {
        this.J_1907_R = true;
        this.n_1700_B.interrupt();
        try {
            this.n_1700_B.join();
        }
        catch (InterruptedException interruptedexception) {
            Thread.currentThread().interrupt();
        }
        this.i_2993_w();
        this.J_1907_R = false;
        this.n_1700_B = this.R_4764_Y();
    }
}


