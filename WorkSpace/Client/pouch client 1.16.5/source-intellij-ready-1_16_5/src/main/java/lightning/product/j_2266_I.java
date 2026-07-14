/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import lightning.product.RealmsPersistence;
import lightning.product.M_1641_O;
import lightning.product.U_1241_n;
import lightning.product.MinecraftClient;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class j_2266_I {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final ScheduledExecutorService J_1907_R = Executors.newScheduledThreadPool(3);
    private volatile boolean R_4764_Y = true;
    private final Runnable G_564_y = new R_4764_Y();
    private final Runnable P_1922_E = new J_1907_R();
    private final Runnable u_1723_Y = new P_1922_E();
    private final Runnable v_4262_N = new n_1700_B();
    private final Runnable w_1484_f = new u_1723_Y();
    private final Set<q_1982_R> t_148_a = Sets.newHashSet();
    private List<q_1982_R> s_956_w = Lists.newArrayList();
    private M_1641_O u_2550_I;
    private int M_588_G;
    private boolean P_4830_p;
    private boolean h_1847_R;
    private String Q_4569_t;
    private ScheduledFuture<?> M_182_A;
    private ScheduledFuture<?> t_1786_h;
    private ScheduledFuture<?> multiplayerClientSuggestionProvider;
    private ScheduledFuture<?> w_1457_N;
    private ScheduledFuture<?> Y_601_j;
    private final Map<G_564_y, Boolean> Y_259_p = new ConcurrentHashMap<G_564_y, Boolean>(lightning.product.j_2266_I$G_564_y.values().length);

    public boolean n_1700_B() {
        return this.R_4764_Y;
    }

    public synchronized void J_1907_R() {
        if (this.R_4764_Y) {
            this.R_4764_Y = false;
            this.h_1847_R();
            this.P_4830_p();
        }
    }

    public synchronized void R_4764_Y() {
        if (this.R_4764_Y) {
            this.R_4764_Y = false;
            this.h_1847_R();
            this.Y_259_p.put(lightning.product.j_2266_I$G_564_y.J_1907_R, false);
            this.t_1786_h = this.J_1907_R.scheduleAtFixedRate(this.P_1922_E, 0L, 10L, TimeUnit.SECONDS);
            this.Y_259_p.put(lightning.product.j_2266_I$G_564_y.R_4764_Y, false);
            this.multiplayerClientSuggestionProvider = this.J_1907_R.scheduleAtFixedRate(this.u_1723_Y, 0L, 60L, TimeUnit.SECONDS);
            this.Y_259_p.put(lightning.product.j_2266_I$G_564_y.P_1922_E, false);
            this.Y_601_j = this.J_1907_R.scheduleAtFixedRate(this.w_1484_f, 0L, 300L, TimeUnit.SECONDS);
        }
    }

    public boolean n_1700_B(G_564_y p_225083_1_) {
        Boolean obool = this.Y_259_p.get((Object)p_225083_1_);
        return obool == null ? false : obool;
    }

    public void G_564_y() {
        for (G_564_y realmsdatafetcher$task : this.Y_259_p.keySet()) {
            this.Y_259_p.put(realmsdatafetcher$task, false);
        }
    }

    public synchronized void P_1922_E() {
        this.M_588_G();
        this.J_1907_R();
    }

    public synchronized List<q_1982_R> u_1723_Y() {
        return Lists.newArrayList(this.s_956_w);
    }

    public synchronized int v_4262_N() {
        return this.M_588_G;
    }

    public synchronized boolean w_1484_f() {
        return this.P_4830_p;
    }

    public synchronized M_1641_O t_148_a() {
        return this.u_2550_I;
    }

    public synchronized boolean s_956_w() {
        return this.h_1847_R;
    }

    public synchronized String u_2550_I() {
        return this.Q_4569_t;
    }

    public synchronized void M_588_G() {
        this.R_4764_Y = true;
        this.h_1847_R();
    }

    private void P_4830_p() {
        for (G_564_y realmsdatafetcher$task : lightning.product.j_2266_I$G_564_y.values()) {
            this.Y_259_p.put(realmsdatafetcher$task, false);
        }
        this.M_182_A = this.J_1907_R.scheduleAtFixedRate(this.G_564_y, 0L, 60L, TimeUnit.SECONDS);
        this.t_1786_h = this.J_1907_R.scheduleAtFixedRate(this.P_1922_E, 0L, 10L, TimeUnit.SECONDS);
        this.multiplayerClientSuggestionProvider = this.J_1907_R.scheduleAtFixedRate(this.u_1723_Y, 0L, 60L, TimeUnit.SECONDS);
        this.w_1457_N = this.J_1907_R.scheduleAtFixedRate(this.v_4262_N, 0L, 10L, TimeUnit.SECONDS);
        this.Y_601_j = this.J_1907_R.scheduleAtFixedRate(this.w_1484_f, 0L, 300L, TimeUnit.SECONDS);
    }

    private void h_1847_R() {
        try {
            if (this.M_182_A != null) {
                this.M_182_A.cancel(false);
            }
            if (this.t_1786_h != null) {
                this.t_1786_h.cancel(false);
            }
            if (this.multiplayerClientSuggestionProvider != null) {
                this.multiplayerClientSuggestionProvider.cancel(false);
            }
            if (this.w_1457_N != null) {
                this.w_1457_N.cancel(false);
            }
            if (this.Y_601_j != null) {
                this.Y_601_j.cancel(false);
            }
        }
        catch (Exception exception) {
            n_1700_B.error("Failed to cancel Realms tasks", (Throwable)exception);
        }
    }

    private synchronized void n_1700_B(List<q_1982_R> p_225080_1_) {
        int i = 0;
        for (q_1982_R realmsserver : this.t_148_a) {
            if (!p_225080_1_.remove(realmsserver)) continue;
            ++i;
        }
        if (i == 0) {
            this.t_148_a.clear();
        }
        this.s_956_w = p_225080_1_;
    }

    public synchronized void n_1700_B(q_1982_R p_225085_1_) {
        this.s_956_w.remove(p_225085_1_);
        this.t_148_a.add(p_225085_1_);
    }

    private boolean Q_4569_t() {
        return !this.R_4764_Y;
    }

    class R_4764_Y
    implements Runnable {
        private R_4764_Y() {
        }

        @Override
        public void run() {
            if (j_2266_I.this.Q_4569_t()) {
                this.n_1700_B();
            }
        }

        private void n_1700_B() {
            try {
                p_178_J realmsclient = p_178_J.n_1700_B();
                List<q_1982_R> list = realmsclient.P_1922_E().n_1700_B;
                if (list != null) {
                    list.sort(new q_1982_R.n_1700_B(MinecraftClient.A_4115_X().z_1737_N().R_4764_Y()));
                    j_2266_I.this.n_1700_B(list);
                    j_2266_I.this.Y_259_p.put(lightning.product.j_2266_I$G_564_y.n_1700_B, true);
                } else {
                    n_1700_B.warn("Realms server list was null or empty");
                }
            }
            catch (Exception exception) {
                j_2266_I.this.Y_259_p.put(lightning.product.j_2266_I$G_564_y.n_1700_B, true);
                n_1700_B.error("Couldn't get server list", (Throwable)exception);
            }
        }
    }

    class J_1907_R
    implements Runnable {
        private J_1907_R() {
        }

        @Override
        public void run() {
            if (j_2266_I.this.Q_4569_t()) {
                this.n_1700_B();
            }
        }

        private void n_1700_B() {
            try {
                p_178_J realmsclient = p_178_J.n_1700_B();
                j_2266_I.this.M_588_G = realmsclient.s_956_w();
                j_2266_I.this.Y_259_p.put(lightning.product.j_2266_I$G_564_y.J_1907_R, true);
            }
            catch (Exception exception) {
                n_1700_B.error("Couldn't get pending invite count", (Throwable)exception);
            }
        }
    }

    class P_1922_E
    implements Runnable {
        private P_1922_E() {
        }

        @Override
        public void run() {
            if (j_2266_I.this.Q_4569_t()) {
                this.n_1700_B();
            }
        }

        private void n_1700_B() {
            try {
                p_178_J realmsclient = p_178_J.n_1700_B();
                j_2266_I.this.P_4830_p = realmsclient.h_1847_R();
                j_2266_I.this.Y_259_p.put(lightning.product.j_2266_I$G_564_y.R_4764_Y, true);
            }
            catch (Exception exception) {
                n_1700_B.error("Couldn't get trial availability", (Throwable)exception);
            }
        }
    }

    class n_1700_B
    implements Runnable {
        private n_1700_B() {
        }

        @Override
        public void run() {
            if (j_2266_I.this.Q_4569_t()) {
                this.n_1700_B();
            }
        }

        private void n_1700_B() {
            try {
                p_178_J realmsclient = p_178_J.n_1700_B();
                j_2266_I.this.u_2550_I = realmsclient.u_1723_Y();
                j_2266_I.this.Y_259_p.put(lightning.product.j_2266_I$G_564_y.G_564_y, true);
            }
            catch (Exception exception) {
                n_1700_B.error("Couldn't get live stats", (Throwable)exception);
            }
        }
    }

    class u_1723_Y
    implements Runnable {
        private u_1723_Y() {
        }

        @Override
        public void run() {
            if (j_2266_I.this.Q_4569_t()) {
                this.n_1700_B();
            }
        }

        private void n_1700_B() {
            try {
                String s;
                p_178_J realmsclient = p_178_J.n_1700_B();
                U_1241_n realmsnews = null;
                try {
                    realmsnews = realmsclient.P_4830_p();
                }
                catch (Exception exception) {
                    // empty catch block
                }
                RealmsPersistence.n_1700_B realmspersistence$realmspersistencedata = RealmsPersistence.n_1700_B();
                if (realmsnews != null && (s = realmsnews.n_1700_B) != null && !s.equals(realmspersistence$realmspersistencedata.n_1700_B)) {
                    realmspersistence$realmspersistencedata.J_1907_R = true;
                    realmspersistence$realmspersistencedata.n_1700_B = s;
                    RealmsPersistence.n_1700_B(realmspersistence$realmspersistencedata);
                }
                j_2266_I.this.h_1847_R = realmspersistence$realmspersistencedata.J_1907_R;
                j_2266_I.this.Q_4569_t = realmspersistence$realmspersistencedata.n_1700_B;
                j_2266_I.this.Y_259_p.put(lightning.product.j_2266_I$G_564_y.P_1922_E, true);
            }
            catch (Exception exception1) {
                n_1700_B.error("Couldn't get unread news", (Throwable)exception1);
            }
        }
    }

    public static final class G_564_y
    extends Enum<G_564_y> {
        public static final /* enum */ G_564_y n_1700_B = new G_564_y();
        public static final /* enum */ G_564_y J_1907_R = new G_564_y();
        public static final /* enum */ G_564_y R_4764_Y = new G_564_y();
        public static final /* enum */ G_564_y G_564_y = new G_564_y();
        public static final /* enum */ G_564_y P_1922_E = new G_564_y();
        private static final /* synthetic */ G_564_y[] u_1723_Y;

        public static G_564_y[] values() {
            return (G_564_y[])u_1723_Y.clone();
        }

        public static G_564_y valueOf(String name) {
            return Enum.valueOf(G_564_y.class, name);
        }

        private static /* synthetic */ G_564_y[] n_1700_B() {
            return new G_564_y[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            u_1723_Y = lightning.product.j_2266_I$G_564_y.n_1700_B();
        }
    }
}



