/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.lwjgl.glfw.GLFWVidMode
 *  org.lwjgl.glfw.GLFWVidMode$Buffer
 */
package lightning.product;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import org.lwjgl.glfw.GLFWVidMode;

public final class J_1565_t {
    private final int n_1700_B;
    private final int J_1907_R;
    private final int R_4764_Y;
    private final int G_564_y;
    private final int P_1922_E;
    private final int u_1723_Y;
    private static final Pattern v_4262_N = Pattern.compile("(\\d+)x(\\d+)(?:@(\\d+)(?::(\\d+))?)?");

    public J_1565_t(int widthIn, int heightIn, int redBitsIn, int greenBitsIn, int blueBitsIn, int refreshRateIn) {
        this.n_1700_B = widthIn;
        this.J_1907_R = heightIn;
        this.R_4764_Y = redBitsIn;
        this.G_564_y = greenBitsIn;
        this.P_1922_E = blueBitsIn;
        this.u_1723_Y = refreshRateIn;
    }

    public J_1565_t(GLFWVidMode.Buffer buffer) {
        this.n_1700_B = buffer.width();
        this.J_1907_R = buffer.height();
        this.R_4764_Y = buffer.redBits();
        this.G_564_y = buffer.greenBits();
        this.P_1922_E = buffer.blueBits();
        this.u_1723_Y = buffer.refreshRate();
    }

    public J_1565_t(GLFWVidMode glfwVidMode) {
        this.n_1700_B = glfwVidMode.width();
        this.J_1907_R = glfwVidMode.height();
        this.R_4764_Y = glfwVidMode.redBits();
        this.G_564_y = glfwVidMode.greenBits();
        this.P_1922_E = glfwVidMode.blueBits();
        this.u_1723_Y = glfwVidMode.refreshRate();
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public int J_1907_R() {
        return this.J_1907_R;
    }

    public int R_4764_Y() {
        return this.R_4764_Y;
    }

    public int G_564_y() {
        return this.G_564_y;
    }

    public int P_1922_E() {
        return this.P_1922_E;
    }

    public int u_1723_Y() {
        return this.u_1723_Y;
    }

    public boolean equals(Object p_equals_1_) {
        if (this == p_equals_1_) {
            return true;
        }
        if (p_equals_1_ != null && this.getClass() == p_equals_1_.getClass()) {
            J_1565_t videomode = (J_1565_t)p_equals_1_;
            return this.n_1700_B == videomode.n_1700_B && this.J_1907_R == videomode.J_1907_R && this.R_4764_Y == videomode.R_4764_Y && this.G_564_y == videomode.G_564_y && this.P_1922_E == videomode.P_1922_E && this.u_1723_Y == videomode.u_1723_Y;
        }
        return false;
    }

    public int hashCode() {
        return Objects.hash(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y);
    }

    public String toString() {
        return String.format("%sx%s@%s (%sbit)", this.n_1700_B, this.J_1907_R, this.u_1723_Y, this.R_4764_Y + this.G_564_y + this.P_1922_E);
    }

    public static Optional<J_1565_t> n_1700_B(@Nullable String videoModeIn) {
        if (videoModeIn == null) {
            return Optional.empty();
        }
        try {
            Matcher matcher = v_4262_N.matcher(videoModeIn);
            if (matcher.matches()) {
                int i = Integer.parseInt(matcher.group(1));
                int j = Integer.parseInt(matcher.group(2));
                String s = matcher.group(3);
                int k = s == null ? 60 : Integer.parseInt(s);
                String s1 = matcher.group(4);
                int l = s1 == null ? 24 : Integer.parseInt(s1);
                int i1 = l / 3;
                return Optional.of(new J_1565_t(i, j, i1, i1, i1, k));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return Optional.empty();
    }

    public String v_4262_N() {
        return String.format("%sx%s@%s:%s", this.n_1700_B, this.J_1907_R, this.u_1723_Y, this.R_4764_Y + this.G_564_y + this.P_1922_E);
    }
}

