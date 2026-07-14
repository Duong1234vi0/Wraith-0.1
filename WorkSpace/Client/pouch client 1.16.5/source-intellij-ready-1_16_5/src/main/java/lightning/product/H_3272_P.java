/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Queues;
import java.util.Queue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;
import lightning.product.ClientboundLightUpdatePacket;
import lightning.product.U_157_Y;
import lightning.product.W_4148_E;
import lightning.product.MinecraftClient;
import lightning.product.Packet;
import lightning.product.ProcessorHandle;
import net.optifine.Config;
import net.optifine.util.PacketRunnable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class H_3272_P<R extends Runnable>
implements Executor,
ProcessorHandle<R> {
    private final String n_1700_B;
    private static final Logger J_1907_R = LogManager.getLogger();
    private final Queue<R> R_4764_Y = Queues.newConcurrentLinkedQueue();
    private int G_564_y;

    protected H_3272_P(String nameIn) {
        this.n_1700_B = nameIn;
    }

    protected abstract R n_1700_B(Runnable var1);

    protected abstract boolean J_1907_R(R var1);

    public boolean RealmsLongConfirmationScreen() {
        return Thread.currentThread() == this.l_1233_K();
    }

    protected abstract Thread l_1233_K();

    protected boolean j_() {
        return !this.RealmsLongConfirmationScreen();
    }

    public int RealmsLongRunningMcoTaskScreen() {
        return this.R_4764_Y.size();
    }

    @Override
    public String k_() {
        return this.n_1700_B;
    }

    public <V> CompletableFuture<V> n_1700_B(Supplier<V> supplier) {
        return this.j_() ? CompletableFuture.supplyAsync(supplier, this) : CompletableFuture.completedFuture(supplier.get());
    }

    private CompletableFuture<Void> R_4764_Y(Runnable taskIn) {
        return CompletableFuture.supplyAsync(() -> {
            taskIn.run();
            return null;
        }, this);
    }

    public CompletableFuture<Void> u_1723_Y(Runnable taskIn) {
        if (this.j_()) {
            return this.R_4764_Y(taskIn);
        }
        taskIn.run();
        return CompletableFuture.completedFuture(null);
    }

    public void v_4262_N(Runnable taskIn) {
        if (!this.RealmsLongConfirmationScreen()) {
            this.R_4764_Y(taskIn).join();
        } else {
            taskIn.run();
        }
    }

    public void w_1484_f(R taskIn) {
        this.R_4764_Y.add(taskIn);
        LockSupport.unpark(this.l_1233_K());
    }

    @Override
    public void execute(Runnable p_execute_1_) {
        if (this.j_()) {
            this.w_1484_f(this.n_1700_B(p_execute_1_));
        } else {
            p_execute_1_.run();
        }
    }

    protected void i_2993_w() {
        this.R_4764_Y.clear();
    }

    protected void RealmsParentalConsentScreen() {
        int i = Integer.MAX_VALUE;
        if (Config.isLazyChunkLoading() && this == MinecraftClient.A_4115_X()) {
            i = this.J_1907_R();
        }
        while (this.l_() && --i > 0) {
        }
    }

    protected boolean l_() {
        Runnable r = (Runnable)this.R_4764_Y.peek();
        if (r == null) {
            return false;
        }
        if (this.G_564_y == 0 && !this.J_1907_R(r)) {
            return false;
        }
        this.P_1922_E((Runnable)this.R_4764_Y.remove());
        return true;
    }

    public void R_4764_Y(BooleanSupplier isDone) {
        ++this.G_564_y;
        try {
            while (!isDone.getAsBoolean()) {
                if (this.l_()) continue;
                this.m_();
            }
        }
        finally {
            --this.G_564_y;
        }
    }

    protected void m_() {
        Thread.yield();
        LockSupport.parkNanos("waiting for tasks", 100000L);
    }

    protected void P_1922_E(R taskIn) {
        block2: {
            try {
                taskIn.run();
            }
            catch (Exception exception) {
                J_1907_R.fatal("Error executing task on {}", (Object)this.k_(), (Object)exception);
                if (!(exception.getCause() instanceof OutOfMemoryError)) break block2;
                OutOfMemoryError outofmemoryerror = (OutOfMemoryError)exception.getCause();
                throw outofmemoryerror;
            }
        }
    }

    private int J_1907_R() {
        if (this.R_4764_Y.isEmpty()) {
            return 0;
        }
        Runnable[] ar = this.R_4764_Y.toArray(new Runnable[this.R_4764_Y.size()]);
        double d0 = this.n_1700_B(ar);
        if (d0 < 5.0) {
            return Integer.MAX_VALUE;
        }
        int i = ar.length;
        int j = Math.max(Config.getFpsAverage(), 1);
        double d1 = i * 10 / j;
        return this.n_1700_B(ar, d1);
    }

    private int n_1700_B(R[] p_getCount_1_, double p_getCount_2_) {
        double d0 = 0.0;
        for (int i = 0; i < p_getCount_1_.length; ++i) {
            R r = p_getCount_1_[i];
            if (!((d0 += this.G_564_y((Runnable)r)) > p_getCount_2_)) continue;
            return i + 1;
        }
        return p_getCount_1_.length;
    }

    private double n_1700_B(R[] p_getChunkUpdateWeight_1_) {
        double d0 = 0.0;
        for (int i = 0; i < p_getChunkUpdateWeight_1_.length; ++i) {
            R r = p_getChunkUpdateWeight_1_[i];
            d0 += this.G_564_y((Runnable)r);
        }
        return d0;
    }

    private double G_564_y(Runnable p_getChunkUpdateWeight_1_) {
        if (p_getChunkUpdateWeight_1_ instanceof PacketRunnable) {
            PacketRunnable packetrunnable = (PacketRunnable)p_getChunkUpdateWeight_1_;
            Packet ipacket = packetrunnable.getPacket();
            if (ipacket instanceof U_157_Y) {
                return 1.0;
            }
            if (ipacket instanceof ClientboundLightUpdatePacket) {
                return 0.2;
            }
            if (ipacket instanceof W_4148_E) {
                return 2.6;
            }
        }
        return 0.0;
    }

    @Override
    public /* synthetic */ void n_1700_B(Object object) {
        this.w_1484_f((Runnable)object);
    }
}



