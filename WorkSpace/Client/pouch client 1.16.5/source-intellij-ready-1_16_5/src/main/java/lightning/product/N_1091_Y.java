/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.glfw.GLFWVidMode
 *  org.lwjgl.glfw.GLFWVidMode$Buffer
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lightning.product.J_1565_t;
import lightning.product.c_4037_x;
import net.optifine.util.VideoModeComparator;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public final class N_1091_Y {
    private final long n_1700_B;
    private final List<J_1565_t> J_1907_R;
    private J_1565_t R_4764_Y;
    private int G_564_y;
    private int P_1922_E;

    public N_1091_Y(long pointerIn) {
        this.n_1700_B = pointerIn;
        this.J_1907_R = Lists.newArrayList();
        this.n_1700_B();
    }

    public void n_1700_B() {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        this.J_1907_R.clear();
        GLFWVidMode.Buffer buffer = GLFW.glfwGetVideoModes((long)this.n_1700_B);
        GLFWVidMode glfwvidmode = GLFW.glfwGetVideoMode((long)this.n_1700_B);
        J_1565_t videomode = new J_1565_t(glfwvidmode);
        ArrayList<J_1565_t> list = new ArrayList<J_1565_t>();
        for (int i = buffer.limit() - 1; i >= 0; --i) {
            buffer.position(i);
            J_1565_t videomode1 = new J_1565_t(buffer);
            if (videomode1.R_4764_Y() < 8 || videomode1.G_564_y() < 8 || videomode1.P_1922_E() < 8) continue;
            if (videomode1.u_1723_Y() < videomode.u_1723_Y()) {
                list.add(videomode1);
                continue;
            }
            this.J_1907_R.add(videomode1);
        }
        list.sort(new VideoModeComparator().reversed());
        for (J_1565_t videomode2 : list) {
            if (N_1091_Y.n_1700_B(this.J_1907_R, videomode2.n_1700_B(), videomode2.J_1907_R()) != null) continue;
            this.J_1907_R.add(videomode2);
        }
        this.J_1907_R.sort(new VideoModeComparator());
        int[] aint = new int[1];
        int[] aint1 = new int[1];
        GLFW.glfwGetMonitorPos((long)this.n_1700_B, (int[])aint, (int[])aint1);
        this.G_564_y = aint[0];
        this.P_1922_E = aint1[0];
        GLFWVidMode glfwvidmode1 = GLFW.glfwGetVideoMode((long)this.n_1700_B);
        this.R_4764_Y = new J_1565_t(glfwvidmode1);
    }

    public J_1565_t n_1700_B(Optional<J_1565_t> optionalVideoMode) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        if (optionalVideoMode.isPresent()) {
            J_1565_t videomode = optionalVideoMode.get();
            for (J_1565_t videomode1 : this.J_1907_R) {
                if (!videomode1.equals(videomode)) continue;
                return videomode1;
            }
        }
        return this.J_1907_R();
    }

    public int n_1700_B(J_1565_t modeIn) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        return this.J_1907_R.indexOf(modeIn);
    }

    public J_1565_t J_1907_R() {
        return this.R_4764_Y;
    }

    public int R_4764_Y() {
        return this.G_564_y;
    }

    public int G_564_y() {
        return this.P_1922_E;
    }

    public J_1565_t n_1700_B(int index) {
        return this.J_1907_R.get(index);
    }

    public int P_1922_E() {
        return this.J_1907_R.size();
    }

    public long u_1723_Y() {
        return this.n_1700_B;
    }

    public String toString() {
        return String.format("Monitor[%s %sx%s %s]", this.n_1700_B, this.G_564_y, this.P_1922_E, this.R_4764_Y);
    }

    public static J_1565_t n_1700_B(List<J_1565_t> p_getVideoMode_0_, int p_getVideoMode_1_, int p_getVideoMode_2_) {
        for (J_1565_t videomode : p_getVideoMode_0_) {
            if (videomode.n_1700_B() != p_getVideoMode_1_ || videomode.J_1907_R() != p_getVideoMode_2_) continue;
            return videomode;
        }
        return null;
    }
}

