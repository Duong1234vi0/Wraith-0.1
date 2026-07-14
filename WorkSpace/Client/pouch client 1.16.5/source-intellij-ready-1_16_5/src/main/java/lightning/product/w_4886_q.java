/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  javax.annotation.Nullable
 *  org.lwjgl.PointerBuffer
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.glfw.GLFWMonitorCallback
 *  org.lwjgl.glfw.GLFWMonitorCallbackI
 */
package lightning.product;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import javax.annotation.Nullable;
import lightning.product.N_1091_Y;
import lightning.product.R_1410_D;
import lightning.product.U_679_Y;
import lightning.product.c_4037_x;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMonitorCallback;
import org.lwjgl.glfw.GLFWMonitorCallbackI;

public class w_4886_q {
    private final Long2ObjectMap<N_1091_Y> n_1700_B = new Long2ObjectOpenHashMap();
    private final R_1410_D J_1907_R;

    public w_4886_q(R_1410_D monitorFactory) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        this.J_1907_R = monitorFactory;
        GLFW.glfwSetMonitorCallback(this::n_1700_B);
        PointerBuffer pointerbuffer = GLFW.glfwGetMonitors();
        if (pointerbuffer != null) {
            for (int i = 0; i < pointerbuffer.limit(); ++i) {
                long j = pointerbuffer.get(i);
                this.n_1700_B.put(j, (Object)monitorFactory.createMonitor(j));
            }
        }
    }

    private void n_1700_B(long monitorID, int opCode) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (opCode == 262145) {
            this.n_1700_B.put(monitorID, (Object)this.J_1907_R.createMonitor(monitorID));
        } else if (opCode == 262146) {
            this.n_1700_B.remove(monitorID);
        }
    }

    @Nullable
    public N_1091_Y n_1700_B(long monitorID) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        return (N_1091_Y)this.n_1700_B.get(monitorID);
    }

    @Nullable
    public N_1091_Y n_1700_B(U_679_Y window) {
        long i = GLFW.glfwGetWindowMonitor((long)window.t_148_a());
        if (i != 0L) {
            return this.n_1700_B(i);
        }
        int j = window.t_1786_h();
        int k = j + window.P_4830_p();
        int l = window.multiplayerClientSuggestionProvider();
        int i1 = l + window.h_1847_R();
        int j1 = -1;
        N_1091_Y monitor = null;
        for (N_1091_Y monitor1 : this.n_1700_B.values()) {
            int l3;
            int k1 = monitor1.R_4764_Y();
            int l1 = k1 + monitor1.J_1907_R().n_1700_B();
            int i2 = monitor1.G_564_y();
            int j2 = i2 + monitor1.J_1907_R().J_1907_R();
            int k2 = w_4886_q.n_1700_B(j, k1, l1);
            int l2 = w_4886_q.n_1700_B(k, k1, l1);
            int i3 = w_4886_q.n_1700_B(l, i2, j2);
            int j3 = w_4886_q.n_1700_B(i1, i2, j2);
            int k3 = Math.max(0, l2 - k2);
            int i4 = k3 * (l3 = Math.max(0, j3 - i3));
            if (i4 <= j1) continue;
            monitor = monitor1;
            j1 = i4;
        }
        return monitor;
    }

    public static int n_1700_B(int minValue, int value, int maxValue) {
        if (minValue < value) {
            return value;
        }
        return minValue > maxValue ? maxValue : minValue;
    }

    public void n_1700_B() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GLFWMonitorCallback glfwmonitorcallback = GLFW.glfwSetMonitorCallback((GLFWMonitorCallbackI)null);
        if (glfwmonitorcallback != null) {
            glfwmonitorcallback.free();
        }
    }
}


