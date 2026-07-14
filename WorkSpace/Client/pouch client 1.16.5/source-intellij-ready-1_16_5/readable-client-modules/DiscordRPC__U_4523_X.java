/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.jagrosh.discordipc.IPCClient
 *  com.jagrosh.discordipc.IPCListener
 *  com.jagrosh.discordipc.entities.DiscordBuild
 *  com.jagrosh.discordipc.entities.RichPresence$Builder
 */
package lightning.product;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.DiscordBuild;
import com.jagrosh.discordipc.entities.RichPresence;
import java.time.OffsetDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import lightning.product.G_624_v;
import lightning.product.S_4088_D;
import lightning.product.X_3546_T;
import lightning.product.y_2603_k;

public class U_4523_X
extends X_3546_T {
    private IPCClient v_4262_N;
    private volatile ScheduledExecutorService w_1484_f;
    private final AtomicBoolean t_148_a = new AtomicBoolean(false);
    private volatile boolean s_956_w = false;
    private long u_2550_I = 2000L;
    private static final long M_588_G = 120000L;
    private static final long P_4830_p = 30000L;
    private long h_1847_R = 0L;

    public U_4523_X() {
        super("DiscordRPC", y_2603_k.P_1922_E);
    }

    private RichPresence.Builder h_1847_R() {
        RichPresence.Builder builder = new RichPresence.Builder();
        return builder.setDetails("Build: 1.16.5").setButton1Text("Discord").setButton2Text("Telegram").setButton1Url("https://discord.gg/2bGKdXWesS").setButton2Url("https://t.me/pouchclientik").setState("UID: " + G_624_v.t_148_a.R_4764_Y).setStartTimestamp(OffsetDateTime.now()).setLargeImage("https://s13.gifyu.com/images/bvayN.gif", S_4088_D.n_1700_B());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void Q_4569_t() {
        U_4523_X u_4523_X = this;
        synchronized (u_4523_X) {
            if (this.v_4262_N != null) {
                return;
            }
            this.t_148_a.set(true);
            try {
                this.v_4262_N = new IPCClient(1434360448957939885L);
                this.v_4262_N.setListener(new IPCListener(){

                    public void onReady(IPCClient client) {
                        try {
                            client.sendRichPresence(U_4523_X.this.h_1847_R().build());
                            U_4523_X.this.u_2550_I = 2000L;
                        }
                        catch (Exception ex) {
                            U_4523_X.this.n_1700_B(ex);
                        }
                    }
                });
                this.v_4262_N.connect(new DiscordBuild[0]);
            }
            catch (Exception e) {
                this.n_1700_B(e);
                this.Y_259_p();
                this.Y_601_j();
            }
            finally {
                this.t_148_a.set(false);
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void M_182_A() {
        U_4523_X u_4523_X = this;
        synchronized (u_4523_X) {
            try {
                this.Y_259_p();
            }
            catch (Exception e) {
                this.n_1700_B(e);
            }
        }
    }

    private ScheduledExecutorService t_1786_h() {
        if (this.w_1484_f == null || this.w_1484_f.isShutdown() || this.w_1484_f.isTerminated()) {
            this.w_1484_f = Executors.newSingleThreadScheduledExecutor(r -> {
                Thread t = new Thread(r, "DiscordRPC-Exec");
                t.setDaemon(true);
                t.setUncaughtExceptionHandler((th, ex) -> this.n_1700_B(ex));
                return t;
            });
        }
        return this.w_1484_f;
    }

    private void N_4405_n() {
        this.t_1786_h().submit(() -> {
            if (this.t_148_a.get()) {
                return;
            }
            this.Q_4569_t();
        });
    }

    private void w_1457_N() {
        ScheduledExecutorService exec = this.t_1786_h();
        exec.submit(() -> {
            if (this.t_148_a.get()) {
                return;
            }
            this.M_182_A();
        });
    }

    private void Y_601_j() {
        if (this.s_956_w || !this.w_1484_f()) {
            return;
        }
        this.s_956_w = true;
        long delay = this.u_2550_I;
        this.t_1786_h().schedule(() -> {
            this.s_956_w = false;
            if (!this.w_1484_f() || this.v_4262_N != null || this.t_148_a.get()) {
                return;
            }
            this.Q_4569_t();
        }, delay, TimeUnit.MILLISECONDS);
        this.u_2550_I = Math.min(120000L, this.u_2550_I * 2L);
    }

    private void Y_259_p() {
        if (this.v_4262_N != null) {
            try {
                this.v_4262_N.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.v_4262_N = null;
        }
    }

    private synchronized void n_1700_B(Throwable error) {
        long now = System.currentTimeMillis();
        if (now - this.h_1847_R >= 30000L) {
            String msg = error == null ? "Unknown error" : error.getMessage();
            System.out.println("[DiscordRPC] Connection failed: " + (msg == null ? error.getClass().getSimpleName() : msg));
            this.h_1847_R = now;
        }
    }

    @Override
    public void n_1700_B() {
        super.n_1700_B();
        this.u_2550_I = 2000L;
        this.s_956_w = false;
        this.N_4405_n();
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.M_182_A();
        if (this.w_1484_f != null) {
            try {
                this.w_1484_f.shutdownNow();
            }
            catch (Exception exception) {
                // empty catch block
            }
            this.w_1484_f = null;
        }
    }
}

