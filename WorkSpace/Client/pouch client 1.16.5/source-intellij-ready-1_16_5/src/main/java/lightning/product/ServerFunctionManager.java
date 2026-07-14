/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.brigadier.CommandDispatcher
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import lightning.product.A_2352_Z;
import lightning.product.g_2336_b;
import lightning.product.n_916_l;
import lightning.product.r_109_r;
import lightning.product.r_3448_Z;
import lightning.product.y_2498_m;
import net.minecraft.server.G_564_y;

public class ServerFunctionManager {
    private static final g_2336_b n_1700_B = new g_2336_b("tick");
    private static final g_2336_b J_1907_R = new g_2336_b("load");
    private final G_564_y R_4764_Y;
    private boolean G_564_y;
    private final ArrayDeque<n_1700_B> P_1922_E = new ArrayDeque();
    private final List<n_1700_B> u_1723_Y = Lists.newArrayList();
    private final List<r_3448_Z> v_4262_N = Lists.newArrayList();
    private boolean w_1484_f;
    private n_916_l t_148_a;

    public ServerFunctionManager(G_564_y server, n_916_l reloader) {
        this.R_4764_Y = server;
        this.t_148_a = reloader;
        this.J_1907_R(reloader);
    }

    public int n_1700_B() {
        return this.R_4764_Y.y_1700_S().R_4764_Y(A_2352_Z.Q_2552_b);
    }

    public CommandDispatcher<y_2498_m> J_1907_R() {
        return this.R_4764_Y.H_1083_k().n_1700_B();
    }

    public void R_4764_Y() {
        this.n_1700_B(this.v_4262_N, n_1700_B);
        if (this.w_1484_f) {
            this.w_1484_f = false;
            List<r_3448_Z> collection = this.t_148_a.R_4764_Y().J_1907_R(J_1907_R).n_1700_B();
            this.n_1700_B(collection, J_1907_R);
        }
    }

    private void n_1700_B(Collection<r_3448_Z> functionObjects, g_2336_b identifier) {
        this.R_4764_Y.LongRunningTask().n_1700_B(identifier::toString);
        for (r_3448_Z functionobject : functionObjects) {
            this.n_1700_B(functionobject, this.G_564_y());
        }
        this.R_4764_Y.LongRunningTask().R_4764_Y();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int n_1700_B(r_3448_Z functionObject, y_2498_m source) {
        int i = this.n_1700_B();
        if (this.G_564_y) {
            if (this.P_1922_E.size() + this.u_1723_Y.size() < i) {
                this.u_1723_Y.add(new n_1700_B(this, source, new r_3448_Z.R_4764_Y(functionObject)));
            }
            return 0;
        }
        try {
            this.G_564_y = true;
            int j = 0;
            r_3448_Z.G_564_y[] afunctionobject$ientry = functionObject.J_1907_R();
            for (int k = afunctionobject$ientry.length - 1; k >= 0; --k) {
                this.P_1922_E.push(new n_1700_B(this, source, afunctionobject$ientry[k]));
            }
            while (!this.P_1922_E.isEmpty()) {
                try {
                    n_1700_B functionmanager$queuedcommand = this.P_1922_E.removeFirst();
                    this.R_4764_Y.LongRunningTask().n_1700_B(functionmanager$queuedcommand::toString);
                    functionmanager$queuedcommand.n_1700_B(this.P_1922_E, i);
                    if (!this.u_1723_Y.isEmpty()) {
                        Lists.reverse(this.u_1723_Y).forEach(this.P_1922_E::addFirst);
                        this.u_1723_Y.clear();
                    }
                }
                finally {
                    this.R_4764_Y.LongRunningTask().R_4764_Y();
                }
                if (++j < i) continue;
                int n = j;
                return n;
            }
            int n = j;
            return n;
        }
        finally {
            this.P_1922_E.clear();
            this.u_1723_Y.clear();
            this.G_564_y = false;
        }
    }

    public void n_1700_B(n_916_l reloader) {
        this.t_148_a = reloader;
        this.J_1907_R(reloader);
    }

    private void J_1907_R(n_916_l reloader) {
        this.v_4262_N.clear();
        this.v_4262_N.addAll(reloader.R_4764_Y().J_1907_R(n_1700_B).n_1700_B());
        this.w_1484_f = true;
    }

    public y_2498_m G_564_y() {
        return this.R_4764_Y.R_3908_n().J_1907_R(2).s_956_w();
    }

    public Optional<r_3448_Z> n_1700_B(g_2336_b functionIdentifier) {
        return this.t_148_a.n_1700_B(functionIdentifier);
    }

    public r_109_r<r_3448_Z> J_1907_R(g_2336_b functionTagIdentifier) {
        return this.t_148_a.J_1907_R(functionTagIdentifier);
    }

    public Iterable<g_2336_b> P_1922_E() {
        return this.t_148_a.J_1907_R().keySet();
    }

    public Iterable<g_2336_b> u_1723_Y() {
        return this.t_148_a.R_4764_Y().J_1907_R();
    }

    public static class n_1700_B {
        private final ServerFunctionManager n_1700_B;
        private final y_2498_m J_1907_R;
        private final r_3448_Z.G_564_y R_4764_Y;

        public n_1700_B(ServerFunctionManager functionReloader, y_2498_m commandSource, r_3448_Z.G_564_y objectEntry) {
            this.n_1700_B = functionReloader;
            this.J_1907_R = commandSource;
            this.R_4764_Y = objectEntry;
        }

        public void n_1700_B(ArrayDeque<n_1700_B> commandQueue, int maxCommandChainLength) {
            try {
                this.R_4764_Y.n_1700_B(this.n_1700_B, this.J_1907_R, commandQueue, maxCommandChainLength);
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }

        public String toString() {
            return this.R_4764_Y.toString();
        }
    }
}


