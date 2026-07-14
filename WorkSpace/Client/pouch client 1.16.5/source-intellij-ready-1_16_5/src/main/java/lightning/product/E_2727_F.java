/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 */
package lightning.product;

import com.google.common.collect.Maps;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Timer;
import java.util.UUID;
import lightning.product.Y_589_b;

public class E_2727_F {
    private final Map<String, Object> n_1700_B = Maps.newHashMap();
    private final Map<String, Object> J_1907_R = Maps.newHashMap();
    private final String R_4764_Y = UUID.randomUUID().toString();
    private final URL G_564_y;
    private final Y_589_b P_1922_E;
    private final Timer u_1723_Y = new Timer("Snooper Timer", true);
    private final Object v_4262_N = new Object();
    private final long w_1484_f;
    private boolean t_148_a;

    public E_2727_F(String side, Y_589_b playerStatCollector, long startTime) {
        try {
            this.G_564_y = new URL("http://snoop.minecraft.net/" + side + "?version=2");
        }
        catch (MalformedURLException malformedurlexception) {
            throw new IllegalArgumentException();
        }
        this.P_1922_E = playerStatCollector;
        this.w_1484_f = startTime;
    }

    public void n_1700_B() {
        if (!this.t_148_a) {
            // empty if block
        }
    }

    public void J_1907_R() {
        this.J_1907_R("memory_total", Runtime.getRuntime().totalMemory());
        this.J_1907_R("memory_max", Runtime.getRuntime().maxMemory());
        this.J_1907_R("memory_free", Runtime.getRuntime().freeMemory());
        this.J_1907_R("cpu_cores", Runtime.getRuntime().availableProcessors());
        this.P_1922_E.n_1700_B(this);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void n_1700_B(String statName, Object statValue) {
        Object object = this.v_4262_N;
        synchronized (object) {
            this.J_1907_R.put(statName, statValue);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void J_1907_R(String statName, Object statValue) {
        Object object = this.v_4262_N;
        synchronized (object) {
            this.n_1700_B.put(statName, statValue);
        }
    }

    public boolean R_4764_Y() {
        return this.t_148_a;
    }

    public void G_564_y() {
        this.u_1723_Y.cancel();
    }

    public String P_1922_E() {
        return this.R_4764_Y;
    }

    public long u_1723_Y() {
        return this.w_1484_f;
    }
}

