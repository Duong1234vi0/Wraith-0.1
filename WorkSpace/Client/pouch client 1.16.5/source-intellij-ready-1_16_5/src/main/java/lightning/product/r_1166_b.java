/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

public class r_1166_b {
    public static void n_1700_B() {
        MemoryUtil.memSet((long)0L, (int)0, (long)1L);
    }

    public static double J_1907_R() {
        return GLFW.glfwGetTime();
    }
}

