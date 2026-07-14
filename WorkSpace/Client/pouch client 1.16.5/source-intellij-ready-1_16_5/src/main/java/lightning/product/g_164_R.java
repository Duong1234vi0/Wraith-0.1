/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.Version
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.glfw.GLFWErrorCallback
 *  org.lwjgl.glfw.GLFWErrorCallbackI
 *  org.lwjgl.glfw.GLFWVidMode
 *  org.lwjgl.opengl.GL
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GLCapabilities
 *  oshi.SystemInfo
 *  oshi.hardware.Processor
 */
package lightning.product;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.U_679_Y;
import lightning.product.X_933_l;
import lightning.product.c_4037_x;
import lightning.product.l_3747_P;
import lightning.product.q_2612_j;
import net.optifine.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.Version;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;
import oshi.SystemInfo;
import oshi.hardware.Processor;

public class g_164_R {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static String J_1907_R = "";
    private static String R_4764_Y;
    private static final Map<Integer, String> G_564_y;

    public static String n_1700_B() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GLFW.glfwGetCurrentContext() == 0L ? "NO CONTEXT" : X_933_l.H_2857_Y(7937) + " GL version " + X_933_l.H_2857_Y(7938) + ", " + X_933_l.H_2857_Y(7936);
    }

    public static int n_1700_B(U_679_Y p__getRefreshRate_0_) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        long i = GLFW.glfwGetWindowMonitor((long)p__getRefreshRate_0_.t_148_a());
        if (i == 0L) {
            i = GLFW.glfwGetPrimaryMonitor();
        }
        GLFWVidMode glfwvidmode = i == 0L ? null : GLFW.glfwGetVideoMode((long)i);
        return glfwvidmode == null ? 0 : glfwvidmode.refreshRate();
    }

    public static String J_1907_R() {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        return Version.getVersion();
    }

    public static LongSupplier R_4764_Y() {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        U_679_Y.n_1700_B((Integer p_lambda$_initGlfw$1_0_, String p_lambda$_initGlfw$1_1_) -> {
            throw new IllegalStateException(String.format("GLFW error before init: [0x%X]%s", p_lambda$_initGlfw$1_0_, p_lambda$_initGlfw$1_1_));
        });
        ArrayList list = Lists.newArrayList();
        GLFWErrorCallback glfwerrorcallback = GLFW.glfwSetErrorCallback((p_lambda$_initGlfw$2_1_, p_lambda$_initGlfw$2_2_) -> list.add(String.format("GLFW error during init: [0x%X]%s", p_lambda$_initGlfw$2_1_, p_lambda$_initGlfw$2_2_)));
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Failed to initialize GLFW, errors: " + Joiner.on((String)",").join((Iterable)list));
        }
        LongSupplier longsupplier = () -> (long)(GLFW.glfwGetTime() * 1.0E9);
        for (String s : list) {
            n_1700_B.error("GLFW error collected during initialization: {}", (Object)s);
        }
        c_4037_x.n_1700_B((GLFWErrorCallbackI)glfwerrorcallback);
        return longsupplier;
    }

    public static void n_1700_B(GLFWErrorCallbackI p__setGlfwErrorCallback_0_) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        GLFWErrorCallback glfwerrorcallback = GLFW.glfwSetErrorCallback((GLFWErrorCallbackI)p__setGlfwErrorCallback_0_);
        if (glfwerrorcallback != null) {
            glfwerrorcallback.free();
        }
    }

    public static boolean J_1907_R(U_679_Y p__shouldClose_0_) {
        return GLFW.glfwWindowShouldClose((long)p__shouldClose_0_.t_148_a());
    }

    public static void G_564_y() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (GL.getCapabilities().GL_NV_fog_distance) {
            if (Config.isFogFancy()) {
                X_933_l.M_588_G(34138, 34139);
            }
            if (Config.isFogFast()) {
                X_933_l.M_588_G(34138, 34140);
            }
        }
    }

    public static void n_1700_B(int p__init_0_, boolean p__init_1_) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        GLCapabilities glcapabilities = GL.getCapabilities();
        J_1907_R = "Using framebuffer using " + X_933_l.n_1700_B(glcapabilities);
        try {
            Processor[] aprocessor = new SystemInfo().getHardware().getProcessors();
            R_4764_Y = String.format("%dx %s", aprocessor.length, aprocessor[0]).replaceAll("\\s+", " ");
        }
        catch (Throwable throwable) {
            // empty catch block
        }
        q_2612_j.n_1700_B(p__init_0_, p__init_1_);
    }

    public static String P_1922_E() {
        return J_1907_R;
    }

    public static String u_1723_Y() {
        return R_4764_Y == null ? "<unknown>" : R_4764_Y;
    }

    public static void n_1700_B(int p__renderCrosshair_0_, boolean p__renderCrosshair_1_, boolean p__renderCrosshair_2_, boolean p__renderCrosshair_3_) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        X_933_l.d_2461_k();
        X_933_l.n_1700_B(false);
        l_3747_P tessellator = c_4037_x.D_4792_h();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        GL11.glLineWidth((float)4.0f);
        bufferbuilder.n_1700_B(1, E_688_b.Y_601_j);
        if (p__renderCrosshair_1_) {
            bufferbuilder.pos(0.0, 0.0, 0.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(p__renderCrosshair_0_, 0.0, 0.0).color(0, 0, 0, 255).endVertex();
        }
        if (p__renderCrosshair_2_) {
            bufferbuilder.pos(0.0, 0.0, 0.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(0.0, p__renderCrosshair_0_, 0.0).color(0, 0, 0, 255).endVertex();
        }
        if (p__renderCrosshair_3_) {
            bufferbuilder.pos(0.0, 0.0, 0.0).color(0, 0, 0, 255).endVertex();
            bufferbuilder.pos(0.0, 0.0, p__renderCrosshair_0_).color(0, 0, 0, 255).endVertex();
        }
        tessellator.J_1907_R();
        GL11.glLineWidth((float)2.0f);
        bufferbuilder.n_1700_B(1, E_688_b.Y_601_j);
        if (p__renderCrosshair_1_) {
            bufferbuilder.pos(0.0, 0.0, 0.0).color(255, 0, 0, 255).endVertex();
            bufferbuilder.pos(p__renderCrosshair_0_, 0.0, 0.0).color(255, 0, 0, 255).endVertex();
        }
        if (p__renderCrosshair_2_) {
            bufferbuilder.pos(0.0, 0.0, 0.0).color(0, 255, 0, 255).endVertex();
            bufferbuilder.pos(0.0, p__renderCrosshair_0_, 0.0).color(0, 255, 0, 255).endVertex();
        }
        if (p__renderCrosshair_3_) {
            bufferbuilder.pos(0.0, 0.0, 0.0).color(127, 127, 255, 255).endVertex();
            bufferbuilder.pos(0.0, 0.0, p__renderCrosshair_0_).color(127, 127, 255, 255).endVertex();
        }
        tessellator.J_1907_R();
        GL11.glLineWidth((float)1.0f);
        X_933_l.n_1700_B(true);
        X_933_l.v_4276_D();
    }

    public static String n_1700_B(int p_getErrorString_0_) {
        return G_564_y.get(p_getErrorString_0_);
    }

    public static <T> T n_1700_B(Supplier<T> p_make_0_) {
        return p_make_0_.get();
    }

    public static <T> T n_1700_B(T p_make_0_, Consumer<T> p_make_1_) {
        p_make_1_.accept(p_make_0_);
        return p_make_0_;
    }

    public static boolean v_4262_N() {
        return !Config.isAntialiasing();
    }

    public static boolean w_1484_f() {
        return true;
    }

    static {
        G_564_y = g_164_R.n_1700_B(Maps.newHashMap(), (T p_lambda$static$0_0_) -> {
            p_lambda$static$0_0_.put(0, "No error");
            p_lambda$static$0_0_.put(1280, "Enum parameter is invalid for this function");
            p_lambda$static$0_0_.put(1281, "Parameter is invalid for this function");
            p_lambda$static$0_0_.put(1282, "Current state is invalid for this function");
            p_lambda$static$0_0_.put(1283, "Stack overflow");
            p_lambda$static$0_0_.put(1284, "Stack underflow");
            p_lambda$static$0_0_.put(1285, "Out of memory");
            p_lambda$static$0_0_.put(1286, "Operation on incomplete framebuffer");
            p_lambda$static$0_0_.put(1286, "Operation on incomplete framebuffer");
        });
    }
}

