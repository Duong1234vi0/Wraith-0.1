/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  org.lwjgl.opengl.ARBCopyBuffer
 *  org.lwjgl.opengl.ARBDrawBuffersBlend
 *  org.lwjgl.opengl.ARBFramebufferObject
 *  org.lwjgl.opengl.EXTFramebufferBlit
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL13
 *  org.lwjgl.opengl.GL14
 *  org.lwjgl.opengl.GL15
 *  org.lwjgl.opengl.GL20
 *  org.lwjgl.opengl.GL30
 *  org.lwjgl.opengl.GL31
 *  org.lwjgl.opengl.GL42
 *  org.lwjgl.opengl.GLCapabilities
 *  org.lwjgl.system.MemoryUtil
 */
package lightning.product;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.stream.IntStream;
import javax.annotation.Nullable;
import lightning.product.D_1098_v;
import lightning.product.M_1336_P;
import lightning.product.T_3334_o;
import lightning.product.Z_2491_A;
import lightning.product.c_3314_E;
import lightning.product.c_4037_x;
import lightning.product.g_164_R;
import lightning.product.q_383_x;
import net.optifine.Config;
import net.optifine.SmartAnimations;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;
import net.optifine.render.GlCullState;
import net.optifine.shaders.Shaders;
import net.optifine.util.LockCounter;
import org.lwjgl.opengl.ARBCopyBuffer;
import org.lwjgl.opengl.ARBDrawBuffersBlend;
import org.lwjgl.opengl.ARBFramebufferObject;
import org.lwjgl.opengl.EXTFramebufferBlit;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL31;
import org.lwjgl.opengl.GL42;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.MemoryUtil;

public class X_933_l {
    private static final FloatBuffer h_1847_R = lightning.product.g_164_R.n_1700_B(MemoryUtil.memAllocFloat((int)16), (T p_lambda$static$0_0_) -> c_3314_E.n_1700_B(MemoryUtil.memAddress((FloatBuffer)p_lambda$static$0_0_)));
    private static final n_1700_B Q_4569_t = new n_1700_B();
    private static final R_4764_Y M_182_A = new R_4764_Y(2896);
    private static final R_4764_Y[] t_1786_h = (R_4764_Y[])IntStream.range(0, 8).mapToObj(p_lambda$static$1_0_ -> new R_4764_Y(16384 + p_lambda$static$1_0_)).toArray(R_4764_Y[]::new);
    private static final v_4262_N multiplayerClientSuggestionProvider = new v_4262_N();
    private static final J_1907_R w_1457_N = new J_1907_R();
    private static final t_148_a Y_601_j = new t_148_a();
    private static final M_588_G Y_259_p = new M_588_G();
    private static final w_1484_f Q_2552_b = new w_1484_f();
    private static final Q_4569_t C_2741_M = new Q_4569_t();
    private static final P_1922_E k_2293_S = new P_1922_E();
    private static final C_2741_M q_2307_F = new C_2741_M();
    private static final w_1457_N Z_875_P = new w_1457_N();
    private static final M_182_A c_3005_b = new M_182_A();
    private static final FloatBuffer H_2857_Y = q_383_x.J_1907_R(4);
    private static int A_4115_X;
    private static final k_2293_S[] Y_1740_V;
    private static int t_4043_B;
    private static final R_4764_Y x_607_J;
    private static final u_1723_Y e_4240_b;
    private static final G_564_y n_3318_d;
    private static P_4830_p d_2427_y;
    private static Y_601_j z_1737_N;
    private static LockCounter v_4276_D;
    private static GlAlphaState d_2461_k;
    private static LockCounter G_624_v;
    private static GlBlendState T_2506_i;
    private static LockCounter q_4610_l;
    private static GlCullState z_4693_k;
    private static boolean g_221_o;
    private static int e_2887_G;
    private static boolean B_1668_F;
    public static float n_1700_B;
    public static float J_1907_R;
    public static boolean R_4764_Y;
    public static boolean G_564_y;
    public static int P_1922_E;
    public static int u_1723_Y;
    public static int v_4262_N;
    public static int w_1484_f;
    private static boolean g_164_R;
    public static final int t_148_a = 7;
    public static final int s_956_w = 4;
    public static final int u_2550_I = 33984;
    public static final int M_588_G = 33985;
    public static final int P_4830_p = 33986;
    private static int X_933_l;
    private static int Z_976_R;
    private static final int[] H_1990_U;

    @Deprecated
    public static void n_1700_B() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glPushAttrib((int)8256);
    }

    @Deprecated
    public static void J_1907_R() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glPushAttrib((int)270336);
    }

    @Deprecated
    public static void R_4764_Y() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glPopAttrib();
    }

    @Deprecated
    public static void G_564_y() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (v_4276_D.isLocked()) {
            d_2461_k.setDisabled();
        } else {
            lightning.product.X_933_l.Q_4569_t.n_1700_B.n_1700_B();
        }
    }

    @Deprecated
    public static void P_1922_E() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (v_4276_D.isLocked()) {
            d_2461_k.setEnabled();
        } else {
            lightning.product.X_933_l.Q_4569_t.n_1700_B.J_1907_R();
        }
    }

    @Deprecated
    public static void n_1700_B(int func, float ref) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (v_4276_D.isLocked()) {
            d_2461_k.setFuncRef(func, ref);
        } else if (func != lightning.product.X_933_l.Q_4569_t.J_1907_R || ref != lightning.product.X_933_l.Q_4569_t.R_4764_Y) {
            lightning.product.X_933_l.Q_4569_t.J_1907_R = func;
            lightning.product.X_933_l.Q_4569_t.R_4764_Y = ref;
            GL11.glAlphaFunc((int)func, (float)ref);
        }
    }

    @Deprecated
    public static void u_1723_Y() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        M_182_A.J_1907_R();
    }

    @Deprecated
    public static void v_4262_N() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        M_182_A.n_1700_B();
    }

    @Deprecated
    public static void n_1700_B(int light) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        t_1786_h[light].J_1907_R();
    }

    @Deprecated
    public static void w_1484_f() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.multiplayerClientSuggestionProvider.n_1700_B.J_1907_R();
    }

    @Deprecated
    public static void t_148_a() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.multiplayerClientSuggestionProvider.n_1700_B.n_1700_B();
    }

    @Deprecated
    public static void n_1700_B(int face, int mode) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (face != lightning.product.X_933_l.multiplayerClientSuggestionProvider.J_1907_R || mode != lightning.product.X_933_l.multiplayerClientSuggestionProvider.R_4764_Y) {
            lightning.product.X_933_l.multiplayerClientSuggestionProvider.J_1907_R = face;
            lightning.product.X_933_l.multiplayerClientSuggestionProvider.R_4764_Y = mode;
            GL11.glColorMaterial((int)face, (int)mode);
        }
    }

    @Deprecated
    public static void n_1700_B(int light, int pname, FloatBuffer params) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glLightfv((int)light, (int)pname, (FloatBuffer)params);
    }

    @Deprecated
    public static void n_1700_B(int pname, FloatBuffer params) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glLightModelfv((int)pname, (FloatBuffer)params);
    }

    @Deprecated
    public static void n_1700_B(float nx, float ny, float nz) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glNormal3f((float)nx, (float)ny, (float)nz);
    }

    public static void s_956_w() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        lightning.product.X_933_l.c_3005_b.n_1700_B.n_1700_B();
    }

    public static void u_2550_I() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        lightning.product.X_933_l.c_3005_b.n_1700_B.J_1907_R();
    }

    public static void n_1700_B(int p_244592_0_, int p_244592_1_, int p_244592_2_, int p_244592_3_) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL20.glScissor((int)p_244592_0_, (int)p_244592_1_, (int)p_244592_2_, (int)p_244592_3_);
    }

    public static void M_588_G() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        lightning.product.X_933_l.Y_601_j.n_1700_B.n_1700_B();
    }

    public static void P_4830_p() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        lightning.product.X_933_l.Y_601_j.n_1700_B.J_1907_R();
    }

    public static void J_1907_R(int depthFunc) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (depthFunc != lightning.product.X_933_l.Y_601_j.R_4764_Y) {
            lightning.product.X_933_l.Y_601_j.R_4764_Y = depthFunc;
            GL11.glDepthFunc((int)depthFunc);
        }
    }

    public static void n_1700_B(boolean flagIn) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (flagIn != lightning.product.X_933_l.Y_601_j.J_1907_R) {
            lightning.product.X_933_l.Y_601_j.J_1907_R = flagIn;
            GL11.glDepthMask((boolean)flagIn);
        }
    }

    public static void h_1847_R() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (G_624_v.isLocked()) {
            T_2506_i.setDisabled();
        } else {
            lightning.product.X_933_l.w_1457_N.n_1700_B.n_1700_B();
        }
    }

    public static void Q_4569_t() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (G_624_v.isLocked()) {
            T_2506_i.setEnabled();
        } else {
            lightning.product.X_933_l.w_1457_N.n_1700_B.J_1907_R();
        }
    }

    public static void J_1907_R(int srcFactor, int dstFactor) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (G_624_v.isLocked()) {
            T_2506_i.setFactors(srcFactor, dstFactor);
        } else if (srcFactor != lightning.product.X_933_l.w_1457_N.J_1907_R || dstFactor != lightning.product.X_933_l.w_1457_N.R_4764_Y || srcFactor != lightning.product.X_933_l.w_1457_N.G_564_y || dstFactor != lightning.product.X_933_l.w_1457_N.P_1922_E) {
            lightning.product.X_933_l.w_1457_N.J_1907_R = srcFactor;
            lightning.product.X_933_l.w_1457_N.R_4764_Y = dstFactor;
            lightning.product.X_933_l.w_1457_N.G_564_y = srcFactor;
            lightning.product.X_933_l.w_1457_N.P_1922_E = dstFactor;
            if (Config.isShaders()) {
                Shaders.uniform_blendFunc.setValue(srcFactor, dstFactor, srcFactor, dstFactor);
            }
            GL11.glBlendFunc((int)srcFactor, (int)dstFactor);
        }
    }

    public static void J_1907_R(int srcFactor, int dstFactor, int srcFactorAlpha, int dstFactorAlpha) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (G_624_v.isLocked()) {
            T_2506_i.setFactors(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
        } else if (srcFactor != lightning.product.X_933_l.w_1457_N.J_1907_R || dstFactor != lightning.product.X_933_l.w_1457_N.R_4764_Y || srcFactorAlpha != lightning.product.X_933_l.w_1457_N.G_564_y || dstFactorAlpha != lightning.product.X_933_l.w_1457_N.P_1922_E) {
            lightning.product.X_933_l.w_1457_N.J_1907_R = srcFactor;
            lightning.product.X_933_l.w_1457_N.R_4764_Y = dstFactor;
            lightning.product.X_933_l.w_1457_N.G_564_y = srcFactorAlpha;
            lightning.product.X_933_l.w_1457_N.P_1922_E = dstFactorAlpha;
            if (Config.isShaders()) {
                Shaders.uniform_blendFunc.setValue(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
            }
            lightning.product.X_933_l.R_4764_Y(srcFactor, dstFactor, srcFactorAlpha, dstFactorAlpha);
        }
    }

    public static void n_1700_B(float red, float green, float blue, float alpha) {
        GL14.glBlendColor((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void R_4764_Y(int blendEquation) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL14.glBlendEquation((int)blendEquation);
    }

    public static String n_1700_B(GLCapabilities glCapabilities) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        Config.initDisplay();
        R_4764_Y = glCapabilities.OpenGL31;
        if (R_4764_Y) {
            P_1922_E = 36662;
            u_1723_Y = 36663;
        } else {
            P_1922_E = 36662;
            u_1723_Y = 36663;
        }
        if (glCapabilities.OpenGL15) {
            v_4262_N = 34962;
            w_1484_f = 35044;
        } else {
            v_4262_N = 34962;
            w_1484_f = 35044;
        }
        boolean flag = R_4764_Y || glCapabilities.GL_ARB_copy_buffer;
        boolean flag1 = glCapabilities.OpenGL14;
        boolean bl = G_564_y = flag && flag1;
        if (!G_564_y) {
            ArrayList<Object> list = new ArrayList<Object>();
            if (!flag) {
                list.add("OpenGL 1.3, ARB_copy_buffer");
            }
            if (!flag1) {
                list.add("OpenGL 1.4");
            }
            String s = "VboRegions not supported, missing: " + Config.listToString(list);
            Config.dbg(s);
            list.add(s);
        }
        z_1737_N = glCapabilities.OpenGL30 ? lightning.product.X_933_l$Y_601_j.n_1700_B : (glCapabilities.GL_EXT_framebuffer_blit ? lightning.product.X_933_l$Y_601_j.J_1907_R : lightning.product.X_933_l$Y_601_j.R_4764_Y);
        if (glCapabilities.OpenGL30) {
            d_2427_y = lightning.product.X_933_l$P_4830_p.n_1700_B;
            T_3334_o.n_1700_B = 36160;
            T_3334_o.J_1907_R = 36161;
            T_3334_o.R_4764_Y = 36064;
            T_3334_o.G_564_y = 36096;
            T_3334_o.P_1922_E = 36053;
            T_3334_o.u_1723_Y = 36054;
            T_3334_o.v_4262_N = 36055;
            T_3334_o.w_1484_f = 36059;
            T_3334_o.t_148_a = 36060;
            return "OpenGL 3.0";
        }
        if (glCapabilities.GL_ARB_framebuffer_object) {
            d_2427_y = lightning.product.X_933_l$P_4830_p.J_1907_R;
            T_3334_o.n_1700_B = 36160;
            T_3334_o.J_1907_R = 36161;
            T_3334_o.R_4764_Y = 36064;
            T_3334_o.G_564_y = 36096;
            T_3334_o.P_1922_E = 36053;
            T_3334_o.v_4262_N = 36055;
            T_3334_o.u_1723_Y = 36054;
            T_3334_o.w_1484_f = 36059;
            T_3334_o.t_148_a = 36060;
            return "ARB_framebuffer_object extension";
        }
        if (glCapabilities.GL_EXT_framebuffer_object) {
            d_2427_y = lightning.product.X_933_l$P_4830_p.R_4764_Y;
            T_3334_o.n_1700_B = 36160;
            T_3334_o.J_1907_R = 36161;
            T_3334_o.R_4764_Y = 36064;
            T_3334_o.G_564_y = 36096;
            T_3334_o.P_1922_E = 36053;
            T_3334_o.v_4262_N = 36055;
            T_3334_o.u_1723_Y = 36054;
            T_3334_o.w_1484_f = 36059;
            T_3334_o.t_148_a = 36060;
            return "EXT_framebuffer_object extension";
        }
        throw new IllegalStateException("Could not initialize framebuffer support.");
    }

    public static int R_4764_Y(int program, int pname) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL20.glGetProgrami((int)program, (int)pname);
    }

    public static void G_564_y(int program, int shaderIn) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glAttachShader((int)program, (int)shaderIn);
    }

    public static void G_564_y(int shaderIn) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glDeleteShader((int)shaderIn);
    }

    public static int P_1922_E(int type) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL20.glCreateShader((int)type);
    }

    public static void n_1700_B(int shaderIn, CharSequence source) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glShaderSource((int)shaderIn, (CharSequence)source);
    }

    public static void u_1723_Y(int shaderIn) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glCompileShader((int)shaderIn);
    }

    public static int P_1922_E(int shaderIn, int pname) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL20.glGetShaderi((int)shaderIn, (int)pname);
    }

    public static void v_4262_N(int program) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUseProgram((int)program);
    }

    public static int M_182_A() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL20.glCreateProgram();
    }

    public static void w_1484_f(int program) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glDeleteProgram((int)program);
    }

    public static void t_148_a(int program) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glLinkProgram((int)program);
    }

    public static int J_1907_R(int program, CharSequence name) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL20.glGetUniformLocation((int)program, (CharSequence)name);
    }

    public static void n_1700_B(int location, IntBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniform1iv((int)location, (IntBuffer)value);
    }

    public static void u_1723_Y(int location, int value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniform1i((int)location, (int)value);
    }

    public static void J_1907_R(int location, FloatBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniform1fv((int)location, (FloatBuffer)value);
    }

    public static void J_1907_R(int location, IntBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniform2iv((int)location, (IntBuffer)value);
    }

    public static void R_4764_Y(int location, FloatBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniform2fv((int)location, (FloatBuffer)value);
    }

    public static void R_4764_Y(int location, IntBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniform3iv((int)location, (IntBuffer)value);
    }

    public static void G_564_y(int location, FloatBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniform3fv((int)location, (FloatBuffer)value);
    }

    public static void G_564_y(int location, IntBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniform4iv((int)location, (IntBuffer)value);
    }

    public static void P_1922_E(int location, FloatBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniform4fv((int)location, (FloatBuffer)value);
    }

    public static void n_1700_B(int location, boolean transpose, FloatBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniformMatrix2fv((int)location, (boolean)transpose, (FloatBuffer)value);
    }

    public static void J_1907_R(int location, boolean transpose, FloatBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniformMatrix3fv((int)location, (boolean)transpose, (FloatBuffer)value);
    }

    public static void R_4764_Y(int location, boolean transpose, FloatBuffer value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glUniformMatrix4fv((int)location, (boolean)transpose, (FloatBuffer)value);
    }

    public static int R_4764_Y(int program, CharSequence name) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL20.glGetAttribLocation((int)program, (CharSequence)name);
    }

    public static int t_1786_h() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        return GL15.glGenBuffers();
    }

    public static void v_4262_N(int target, int buffer) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL15.glBindBuffer((int)target, (int)buffer);
    }

    public static void n_1700_B(int target, ByteBuffer data, int usage) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL15.glBufferData((int)target, (ByteBuffer)data, (int)usage);
    }

    public static void s_956_w(int buffer) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL15.glDeleteBuffers((int)buffer);
    }

    public static void n_1700_B(int target, int level, int xOffset, int yOffset, int x, int y, int width, int height) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL20.glCopyTexSubImage2D((int)target, (int)level, (int)xOffset, (int)yOffset, (int)x, (int)y, (int)width, (int)height);
    }

    public static void w_1484_f(int target, int framebufferIn) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (target == 36160) {
            if (X_933_l == framebufferIn && Z_976_R == framebufferIn) {
                return;
            }
            X_933_l = framebufferIn;
            Z_976_R = framebufferIn;
        } else if (target == 36008) {
            if (X_933_l == framebufferIn) {
                return;
            }
            X_933_l = framebufferIn;
        }
        if (target == 36009) {
            if (Z_976_R == framebufferIn) {
                return;
            }
            Z_976_R = framebufferIn;
        }
        switch (d_2427_y.ordinal()) {
            case 0: {
                GL30.glBindFramebuffer((int)target, (int)framebufferIn);
                break;
            }
            case 1: {
                ARBFramebufferObject.glBindFramebuffer((int)target, (int)framebufferIn);
                break;
            }
            case 2: {
                EXTFramebufferObject.glBindFramebufferEXT((int)target, (int)framebufferIn);
            }
        }
    }

    public static int multiplayerClientSuggestionProvider() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        switch (d_2427_y.ordinal()) {
            case 0: {
                if (GL30.glGetFramebufferAttachmentParameteri((int)36160, (int)36096, (int)36048) != 5890) break;
                return GL30.glGetFramebufferAttachmentParameteri((int)36160, (int)36096, (int)36049);
            }
            case 1: {
                if (ARBFramebufferObject.glGetFramebufferAttachmentParameteri((int)36160, (int)36096, (int)36048) != 5890) break;
                return ARBFramebufferObject.glGetFramebufferAttachmentParameteri((int)36160, (int)36096, (int)36049);
            }
            case 2: {
                if (EXTFramebufferObject.glGetFramebufferAttachmentParameteriEXT((int)36160, (int)36096, (int)36048) != 5890) break;
                return EXTFramebufferObject.glGetFramebufferAttachmentParameteriEXT((int)36160, (int)36096, (int)36049);
            }
        }
        return 0;
    }

    public static void n_1700_B(int srcX0, int srcY0, int srcX1, int srcY1, int dstX0, int dstY0, int dstX1, int dstY1, int mask, int filter) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        switch (z_1737_N.ordinal()) {
            case 0: {
                GL30.glBlitFramebuffer((int)srcX0, (int)srcY0, (int)srcX1, (int)srcY1, (int)dstX0, (int)dstY0, (int)dstX1, (int)dstY1, (int)mask, (int)filter);
                break;
            }
            case 1: {
                EXTFramebufferBlit.glBlitFramebufferEXT((int)srcX0, (int)srcY0, (int)srcX1, (int)srcY1, (int)dstX0, (int)dstY0, (int)dstX1, (int)dstY1, (int)mask, (int)filter);
            }
        }
    }

    public static void u_2550_I(int frameBuffer) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        switch (d_2427_y.ordinal()) {
            case 0: {
                GL30.glDeleteFramebuffers((int)frameBuffer);
                break;
            }
            case 1: {
                ARBFramebufferObject.glDeleteFramebuffers((int)frameBuffer);
                break;
            }
            case 2: {
                EXTFramebufferObject.glDeleteFramebuffersEXT((int)frameBuffer);
            }
        }
    }

    public static int w_1457_N() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        switch (d_2427_y.ordinal()) {
            case 0: {
                return GL30.glGenFramebuffers();
            }
            case 1: {
                return ARBFramebufferObject.glGenFramebuffers();
            }
            case 2: {
                return EXTFramebufferObject.glGenFramebuffersEXT();
            }
        }
        return -1;
    }

    public static int M_588_G(int target) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        switch (d_2427_y.ordinal()) {
            case 0: {
                return GL30.glCheckFramebufferStatus((int)target);
            }
            case 1: {
                return ARBFramebufferObject.glCheckFramebufferStatus((int)target);
            }
            case 2: {
                return EXTFramebufferObject.glCheckFramebufferStatusEXT((int)target);
            }
        }
        return -1;
    }

    public static void n_1700_B(int target, int attachment, int texTarget, int texture, int level) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        switch (d_2427_y.ordinal()) {
            case 0: {
                GL30.glFramebufferTexture2D((int)target, (int)attachment, (int)texTarget, (int)texture, (int)level);
                break;
            }
            case 1: {
                ARBFramebufferObject.glFramebufferTexture2D((int)target, (int)attachment, (int)texTarget, (int)texture, (int)level);
                break;
            }
            case 2: {
                EXTFramebufferObject.glFramebufferTexture2DEXT((int)target, (int)attachment, (int)texTarget, (int)texture, (int)level);
            }
        }
    }

    @Deprecated
    public static int Y_601_j() {
        return lightning.product.X_933_l.Y_1740_V[lightning.product.X_933_l.A_4115_X].J_1907_R;
    }

    public static void P_4830_p(int textureIn) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL13.glActiveTexture((int)textureIn);
    }

    @Deprecated
    public static void h_1847_R(int texture) {
        if (texture != e_2887_G) {
            c_4037_x.n_1700_B(c_4037_x::J_1907_R);
            GL13.glClientActiveTexture((int)texture);
            e_2887_G = texture;
        }
    }

    @Deprecated
    public static void n_1700_B(int texture, float s, float t) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL13.glMultiTexCoord2f((int)texture, (float)s, (float)t);
        if (texture == 33986) {
            n_1700_B = s;
            J_1907_R = t;
        }
    }

    public static void R_4764_Y(int sFactorRGB, int dFactorRGB, int sFactorAlpha, int dFactorAlpha) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL14.glBlendFuncSeparate((int)sFactorRGB, (int)dFactorRGB, (int)sFactorAlpha, (int)dFactorAlpha);
    }

    public static String t_148_a(int shader, int maxLength) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL20.glGetShaderInfoLog((int)shader, (int)maxLength);
    }

    public static String s_956_w(int program, int maxLength) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL20.glGetProgramInfoLog((int)program, (int)maxLength);
    }

    public static void Y_259_p() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.n_1700_B(8960, 8704, 34160);
        lightning.product.X_933_l.Q_4569_t(7681, 34168);
    }

    public static void Q_2552_b() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.n_1700_B(8960, 8704, 8448);
        lightning.product.X_933_l.P_1922_E(8448, 5890, 34168, 34166);
    }

    public static void u_2550_I(int texture, int bitSpace) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.t_1786_h(33985);
        lightning.product.X_933_l.v_4276_D();
        lightning.product.X_933_l.C_2741_M(5890);
        lightning.product.X_933_l.z_4693_k();
        float f = 1.0f / (float)(bitSpace - 1);
        lightning.product.X_933_l.J_1907_R(f, f, f);
        lightning.product.X_933_l.C_2741_M(5888);
        lightning.product.X_933_l.w_1457_N(texture);
        lightning.product.X_933_l.J_1907_R(3553, 10241, 9728);
        lightning.product.X_933_l.J_1907_R(3553, 10240, 9728);
        lightning.product.X_933_l.J_1907_R(3553, 10242, 10496);
        lightning.product.X_933_l.J_1907_R(3553, 10243, 10496);
        lightning.product.X_933_l.n_1700_B(8960, 8704, 34160);
        lightning.product.X_933_l.P_1922_E(34165, 34168, 5890, 5890);
        lightning.product.X_933_l.M_182_A(7681, 34168);
        lightning.product.X_933_l.t_1786_h(33984);
    }

    public static void C_2741_M() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.t_1786_h(33985);
        lightning.product.X_933_l.d_2461_k();
        lightning.product.X_933_l.t_1786_h(33984);
    }

    private static void Q_4569_t(int color1, int color2) {
        lightning.product.X_933_l.n_1700_B(8960, 34161, color1);
        lightning.product.X_933_l.n_1700_B(8960, 34176, color2);
        lightning.product.X_933_l.n_1700_B(8960, 34192, 768);
    }

    private static void P_1922_E(int red, int green, int blue, int alpha) {
        lightning.product.X_933_l.n_1700_B(8960, 34161, red);
        lightning.product.X_933_l.n_1700_B(8960, 34176, green);
        lightning.product.X_933_l.n_1700_B(8960, 34192, 768);
        lightning.product.X_933_l.n_1700_B(8960, 34177, blue);
        lightning.product.X_933_l.n_1700_B(8960, 34193, 768);
        lightning.product.X_933_l.n_1700_B(8960, 34178, alpha);
        lightning.product.X_933_l.n_1700_B(8960, 34194, 770);
    }

    private static void M_182_A(int alpha1, int alpha2) {
        lightning.product.X_933_l.n_1700_B(8960, 34162, alpha1);
        lightning.product.X_933_l.n_1700_B(8960, 34184, alpha2);
        lightning.product.X_933_l.n_1700_B(8960, 34200, 770);
    }

    public static void n_1700_B(M_1336_P lightingVector1, M_1336_P lightingVector2, D_1098_v matrix) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.g_221_o();
        lightning.product.X_933_l.z_4693_k();
        lightning.product.X_933_l.n_1700_B(0);
        lightning.product.X_933_l.n_1700_B(1);
        Z_2491_A vector4f = new Z_2491_A(lightingVector1);
        vector4f.n_1700_B(matrix);
        lightning.product.X_933_l.n_1700_B(16384, 4611, lightning.product.X_933_l.P_1922_E(vector4f.n_1700_B(), vector4f.J_1907_R(), vector4f.R_4764_Y(), 0.0f));
        float f = 0.6f;
        lightning.product.X_933_l.n_1700_B(16384, 4609, lightning.product.X_933_l.P_1922_E(0.6f, 0.6f, 0.6f, 1.0f));
        lightning.product.X_933_l.n_1700_B(16384, 4608, lightning.product.X_933_l.P_1922_E(0.0f, 0.0f, 0.0f, 1.0f));
        lightning.product.X_933_l.n_1700_B(16384, 4610, lightning.product.X_933_l.P_1922_E(0.0f, 0.0f, 0.0f, 1.0f));
        Z_2491_A vector4f1 = new Z_2491_A(lightingVector2);
        vector4f1.n_1700_B(matrix);
        lightning.product.X_933_l.n_1700_B(16385, 4611, lightning.product.X_933_l.P_1922_E(vector4f1.n_1700_B(), vector4f1.J_1907_R(), vector4f1.R_4764_Y(), 0.0f));
        lightning.product.X_933_l.n_1700_B(16385, 4609, lightning.product.X_933_l.P_1922_E(0.6f, 0.6f, 0.6f, 1.0f));
        lightning.product.X_933_l.n_1700_B(16385, 4608, lightning.product.X_933_l.P_1922_E(0.0f, 0.0f, 0.0f, 1.0f));
        lightning.product.X_933_l.n_1700_B(16385, 4610, lightning.product.X_933_l.P_1922_E(0.0f, 0.0f, 0.0f, 1.0f));
        lightning.product.X_933_l.Y_601_j(7424);
        float f1 = 0.4f;
        lightning.product.X_933_l.n_1700_B(2899, lightning.product.X_933_l.P_1922_E(0.4f, 0.4f, 0.4f, 1.0f));
        lightning.product.X_933_l.e_2887_G();
    }

    public static void n_1700_B(M_1336_P lighting1, M_1336_P lighting2) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B();
        matrix4f.n_1700_B(D_1098_v.n_1700_B(1.0f, -1.0f, 1.0f));
        matrix4f.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-22.5f));
        matrix4f.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(135.0f));
        lightning.product.X_933_l.n_1700_B(lighting1, lighting2, matrix4f);
    }

    public static void J_1907_R(M_1336_P lightingVector1, M_1336_P lightingVector2) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B();
        matrix4f.n_1700_B(M_1336_P.G_564_y.R_4764_Y(62.0f));
        matrix4f.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(185.5f));
        matrix4f.n_1700_B(D_1098_v.n_1700_B(1.0f, -1.0f, 1.0f));
        matrix4f.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-22.5f));
        matrix4f.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(135.0f));
        lightning.product.X_933_l.n_1700_B(lightingVector1, lightingVector2, matrix4f);
    }

    private static FloatBuffer P_1922_E(float float1, float float2, float float3, float float4) {
        ((Buffer)H_2857_Y).clear();
        H_2857_Y.put(float1).put(float2).put(float3).put(float4);
        ((Buffer)H_2857_Y).flip();
        return H_2857_Y;
    }

    public static void k_2293_S() {
        lightning.product.X_933_l.n_1700_B(lightning.product.X_933_l$Y_259_p.n_1700_B, 9216);
        lightning.product.X_933_l.n_1700_B(lightning.product.X_933_l$Y_259_p.J_1907_R, 9216);
        lightning.product.X_933_l.n_1700_B(lightning.product.X_933_l$Y_259_p.R_4764_Y, 9216);
        lightning.product.X_933_l.n_1700_B(lightning.product.X_933_l$Y_259_p.n_1700_B, 9474, lightning.product.X_933_l.P_1922_E(1.0f, 0.0f, 0.0f, 0.0f));
        lightning.product.X_933_l.n_1700_B(lightning.product.X_933_l$Y_259_p.J_1907_R, 9474, lightning.product.X_933_l.P_1922_E(0.0f, 1.0f, 0.0f, 0.0f));
        lightning.product.X_933_l.n_1700_B(lightning.product.X_933_l$Y_259_p.R_4764_Y, 9474, lightning.product.X_933_l.P_1922_E(0.0f, 0.0f, 1.0f, 0.0f));
        lightning.product.X_933_l.n_1700_B(lightning.product.X_933_l$Y_259_p.n_1700_B);
        lightning.product.X_933_l.n_1700_B(lightning.product.X_933_l$Y_259_p.J_1907_R);
        lightning.product.X_933_l.n_1700_B(lightning.product.X_933_l$Y_259_p.R_4764_Y);
    }

    public static void q_2307_F() {
        lightning.product.X_933_l.J_1907_R(lightning.product.X_933_l$Y_259_p.n_1700_B);
        lightning.product.X_933_l.J_1907_R(lightning.product.X_933_l$Y_259_p.J_1907_R);
        lightning.product.X_933_l.J_1907_R(lightning.product.X_933_l$Y_259_p.R_4764_Y);
    }

    public static void Z_875_P() {
        lightning.product.X_933_l.u_1723_Y(2983, h_1847_R);
        lightning.product.X_933_l.n_1700_B(h_1847_R);
        lightning.product.X_933_l.u_1723_Y(2982, h_1847_R);
        lightning.product.X_933_l.n_1700_B(h_1847_R);
    }

    @Deprecated
    public static void c_3005_b() {
        if (g_164_R) {
            c_4037_x.n_1700_B(c_4037_x::J_1907_R);
            lightning.product.X_933_l.Y_259_p.n_1700_B.J_1907_R();
        }
    }

    @Deprecated
    public static void H_2857_Y() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.Y_259_p.n_1700_B.n_1700_B();
    }

    @Deprecated
    public static void Q_4569_t(int fogMode) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (fogMode != lightning.product.X_933_l.Y_259_p.J_1907_R) {
            lightning.product.X_933_l.Y_259_p.J_1907_R = fogMode;
            lightning.product.X_933_l.M_588_G(2917, fogMode);
            if (Config.isShaders()) {
                Shaders.setFogMode(fogMode);
            }
        }
    }

    @Deprecated
    public static void n_1700_B(float param) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (param < 0.0f) {
            param = 0.0f;
        }
        if (param != lightning.product.X_933_l.Y_259_p.R_4764_Y) {
            lightning.product.X_933_l.Y_259_p.R_4764_Y = param;
            GL11.glFogf((int)2914, (float)param);
            if (Config.isShaders()) {
                Shaders.setFogDensity(param);
            }
        }
    }

    @Deprecated
    public static void J_1907_R(float param) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (param != lightning.product.X_933_l.Y_259_p.G_564_y) {
            lightning.product.X_933_l.Y_259_p.G_564_y = param;
            GL11.glFogf((int)2915, (float)param);
        }
    }

    @Deprecated
    public static void R_4764_Y(float param) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (param != lightning.product.X_933_l.Y_259_p.P_1922_E) {
            lightning.product.X_933_l.Y_259_p.P_1922_E = param;
            GL11.glFogf((int)2916, (float)param);
        }
    }

    @Deprecated
    public static void n_1700_B(int pname, float[] param) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glFogfv((int)pname, (float[])param);
    }

    @Deprecated
    public static void M_588_G(int pname, int param) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glFogi((int)pname, (int)param);
    }

    public static void A_4115_X() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (q_4610_l.isLocked()) {
            z_4693_k.setEnabled();
        } else {
            lightning.product.X_933_l.Q_2552_b.n_1700_B.J_1907_R();
        }
    }

    public static void Y_1740_V() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (q_4610_l.isLocked()) {
            z_4693_k.setDisabled();
        } else {
            lightning.product.X_933_l.Q_2552_b.n_1700_B.n_1700_B();
        }
    }

    public static void P_4830_p(int face, int mode) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glPolygonMode((int)face, (int)mode);
    }

    public static void t_4043_B() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.C_2741_M.n_1700_B.J_1907_R();
    }

    public static void x_607_J() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.C_2741_M.n_1700_B.n_1700_B();
    }

    public static void e_4240_b() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.C_2741_M.J_1907_R.J_1907_R();
    }

    public static void n_3318_d() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.C_2741_M.J_1907_R.n_1700_B();
    }

    public static void n_1700_B(float factor, float units) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (factor != lightning.product.X_933_l.C_2741_M.R_4764_Y || units != lightning.product.X_933_l.C_2741_M.G_564_y) {
            lightning.product.X_933_l.C_2741_M.R_4764_Y = factor;
            lightning.product.X_933_l.C_2741_M.G_564_y = units;
            GL11.glPolygonOffset((float)factor, (float)units);
        }
    }

    public static void d_2427_y() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.k_2293_S.n_1700_B.J_1907_R();
    }

    public static void z_1737_N() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.k_2293_S.n_1700_B.n_1700_B();
    }

    public static void M_182_A(int logicOperation) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (logicOperation != lightning.product.X_933_l.k_2293_S.J_1907_R) {
            lightning.product.X_933_l.k_2293_S.J_1907_R = logicOperation;
            GL11.glLogicOp((int)logicOperation);
        }
    }

    @Deprecated
    public static void n_1700_B(Y_259_p texGen) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.R_4764_Y((Y_259_p)texGen).n_1700_B.J_1907_R();
    }

    @Deprecated
    public static void J_1907_R(Y_259_p texGen) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.R_4764_Y((Y_259_p)texGen).n_1700_B.n_1700_B();
    }

    @Deprecated
    public static void n_1700_B(Y_259_p texGen, int mode) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        Q_2552_b glstatemanager$texgencoord = lightning.product.X_933_l.R_4764_Y(texGen);
        if (mode != glstatemanager$texgencoord.R_4764_Y) {
            glstatemanager$texgencoord.R_4764_Y = mode;
            GL11.glTexGeni((int)glstatemanager$texgencoord.J_1907_R, (int)9472, (int)mode);
        }
    }

    @Deprecated
    public static void n_1700_B(Y_259_p texGen, int pname, FloatBuffer params) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glTexGenfv((int)lightning.product.X_933_l.R_4764_Y((Y_259_p)texGen).J_1907_R, (int)pname, (FloatBuffer)params);
    }

    @Deprecated
    private static Q_2552_b R_4764_Y(Y_259_p texGen) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        switch (texGen.ordinal()) {
            case 0: {
                return lightning.product.X_933_l.q_2307_F.n_1700_B;
            }
            case 1: {
                return lightning.product.X_933_l.q_2307_F.J_1907_R;
            }
            case 2: {
                return lightning.product.X_933_l.q_2307_F.R_4764_Y;
            }
            case 3: {
                return lightning.product.X_933_l.q_2307_F.G_564_y;
            }
        }
        return lightning.product.X_933_l.q_2307_F.n_1700_B;
    }

    public static void t_1786_h(int textureIn) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (A_4115_X != textureIn - 33984) {
            A_4115_X = textureIn - 33984;
            lightning.product.X_933_l.P_4830_p(textureIn);
        }
    }

    public static void v_4276_D() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        lightning.product.X_933_l.Y_1740_V[lightning.product.X_933_l.A_4115_X].n_1700_B.J_1907_R();
    }

    public static void d_2461_k() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.Y_1740_V[lightning.product.X_933_l.A_4115_X].n_1700_B.n_1700_B();
    }

    @Deprecated
    public static void n_1700_B(int target, int parameterName, int parameters) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glTexEnvi((int)target, (int)parameterName, (int)parameters);
    }

    public static void n_1700_B(int target, int parameterName, float parameter) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glTexParameterf((int)target, (int)parameterName, (float)parameter);
    }

    public static void J_1907_R(int target, int parameterName, int parameter) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glTexParameteri((int)target, (int)parameterName, (int)parameter);
    }

    public static int R_4764_Y(int target, int level, int parameterName) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        return GL11.glGetTexLevelParameteri((int)target, (int)level, (int)parameterName);
    }

    public static int G_624_v() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        return GL11.glGenTextures();
    }

    public static void n_1700_B(int[] textures) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glGenTextures((int[])textures);
    }

    public static void multiplayerClientSuggestionProvider(int textureIn) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (textureIn != 0) {
            for (int i = 0; i < H_1990_U.length; ++i) {
                if (H_1990_U[i] != textureIn) continue;
                lightning.product.X_933_l.H_1990_U[i] = 0;
            }
            GL11.glDeleteTextures((int)textureIn);
            for (k_2293_S glstatemanager$texturestate : Y_1740_V) {
                if (glstatemanager$texturestate.J_1907_R != textureIn) continue;
                glstatemanager$texturestate.J_1907_R = 0;
            }
        }
    }

    public static void J_1907_R(int[] textures) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        for (k_2293_S glstatemanager$texturestate : Y_1740_V) {
            for (int i : textures) {
                if (glstatemanager$texturestate.J_1907_R != i) continue;
                glstatemanager$texturestate.J_1907_R = -1;
            }
        }
        GL11.glDeleteTextures((int[])textures);
    }

    public static void w_1457_N(int textureIn) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (textureIn != lightning.product.X_933_l.Y_1740_V[lightning.product.X_933_l.A_4115_X].J_1907_R) {
            lightning.product.X_933_l.Y_1740_V[lightning.product.X_933_l.A_4115_X].J_1907_R = textureIn;
            GL11.glBindTexture((int)3553, (int)textureIn);
            if (SmartAnimations.isActive()) {
                SmartAnimations.textureRendered(textureIn);
            }
        }
    }

    public static void n_1700_B(int target, int level, int internalFormat, int width, int height, int border, int format, int type, @Nullable IntBuffer pixels) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glTexImage2D((int)target, (int)level, (int)internalFormat, (int)width, (int)height, (int)border, (int)format, (int)type, (IntBuffer)pixels);
    }

    public static void n_1700_B(int target, int level, int xOffset, int yOffset, int width, int height, int format, int type, long pixels) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glTexSubImage2D((int)target, (int)level, (int)xOffset, (int)yOffset, (int)width, (int)height, (int)format, (int)type, (long)pixels);
    }

    public static void n_1700_B(int tex, int level, int format, int type, long pixels) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glGetTexImage((int)tex, (int)level, (int)format, (int)type, (long)pixels);
    }

    @Deprecated
    public static void Y_601_j(int mode) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        if (mode != t_4043_B) {
            t_4043_B = mode;
            GL11.glShadeModel((int)mode);
        }
    }

    @Deprecated
    public static void T_2506_i() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        x_607_J.J_1907_R();
    }

    @Deprecated
    public static void q_4610_l() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        x_607_J.n_1700_B();
    }

    public static void G_564_y(int x, int y, int width, int height) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        lightning.product.X_933_l$q_2307_F.n_1700_B.J_1907_R = x;
        lightning.product.X_933_l$q_2307_F.n_1700_B.R_4764_Y = y;
        lightning.product.X_933_l$q_2307_F.n_1700_B.G_564_y = width;
        lightning.product.X_933_l$q_2307_F.n_1700_B.P_1922_E = height;
        GL11.glViewport((int)x, (int)y, (int)width, (int)height);
    }

    public static void n_1700_B(boolean red, boolean green, boolean blue, boolean alpha) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (red != lightning.product.X_933_l.e_4240_b.n_1700_B || green != lightning.product.X_933_l.e_4240_b.J_1907_R || blue != lightning.product.X_933_l.e_4240_b.R_4764_Y || alpha != lightning.product.X_933_l.e_4240_b.G_564_y) {
            lightning.product.X_933_l.e_4240_b.n_1700_B = red;
            lightning.product.X_933_l.e_4240_b.J_1907_R = green;
            lightning.product.X_933_l.e_4240_b.R_4764_Y = blue;
            lightning.product.X_933_l.e_4240_b.G_564_y = alpha;
            GL11.glColorMask((boolean)red, (boolean)green, (boolean)blue, (boolean)alpha);
        }
    }

    public static void G_564_y(int func, int ref, int mask) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (func != lightning.product.X_933_l.Z_875_P.n_1700_B.n_1700_B || func != lightning.product.X_933_l.Z_875_P.n_1700_B.J_1907_R || func != lightning.product.X_933_l.Z_875_P.n_1700_B.R_4764_Y) {
            lightning.product.X_933_l.Z_875_P.n_1700_B.n_1700_B = func;
            lightning.product.X_933_l.Z_875_P.n_1700_B.J_1907_R = ref;
            lightning.product.X_933_l.Z_875_P.n_1700_B.R_4764_Y = mask;
            GL11.glStencilFunc((int)func, (int)ref, (int)mask);
        }
    }

    public static void Y_259_p(int mask) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (mask != lightning.product.X_933_l.Z_875_P.J_1907_R) {
            lightning.product.X_933_l.Z_875_P.J_1907_R = mask;
            GL11.glStencilMask((int)mask);
        }
    }

    public static void P_1922_E(int sfail, int dpfail, int dppass) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (sfail != lightning.product.X_933_l.Z_875_P.R_4764_Y || dpfail != lightning.product.X_933_l.Z_875_P.G_564_y || dppass != lightning.product.X_933_l.Z_875_P.P_1922_E) {
            lightning.product.X_933_l.Z_875_P.R_4764_Y = sfail;
            lightning.product.X_933_l.Z_875_P.G_564_y = dpfail;
            lightning.product.X_933_l.Z_875_P.P_1922_E = dppass;
            GL11.glStencilOp((int)sfail, (int)dpfail, (int)dppass);
        }
    }

    public static void n_1700_B(double depth) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glClearDepth((double)depth);
    }

    public static void J_1907_R(float red, float green, float blue, float alpha) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glClearColor((float)red, (float)green, (float)blue, (float)alpha);
    }

    public static void Q_2552_b(int index) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glClearStencil((int)index);
    }

    public static void n_1700_B(int mask, boolean checkError) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glClear((int)mask);
        if (checkError) {
            lightning.product.X_933_l.g_164_R();
        }
    }

    @Deprecated
    public static void C_2741_M(int mode) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glMatrixMode((int)mode);
    }

    @Deprecated
    public static void z_4693_k() {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glLoadIdentity();
    }

    @Deprecated
    public static void g_221_o() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glPushMatrix();
    }

    @Deprecated
    public static void e_2887_G() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glPopMatrix();
    }

    @Deprecated
    public static void u_1723_Y(int pname, FloatBuffer params) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glGetFloatv((int)pname, (FloatBuffer)params);
    }

    @Deprecated
    public static void n_1700_B(double left, double right, double bottom, double top, double zNear, double zFar) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glOrtho((double)left, (double)right, (double)bottom, (double)top, (double)zNear, (double)zFar);
    }

    @Deprecated
    public static void R_4764_Y(float angle, float x, float y, float z) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glRotatef((float)angle, (float)x, (float)y, (float)z);
    }

    @Deprecated
    public static void J_1907_R(float x, float y, float z) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glScalef((float)x, (float)y, (float)z);
    }

    @Deprecated
    public static void n_1700_B(double x, double y, double z) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glScaled((double)x, (double)y, (double)z);
    }

    @Deprecated
    public static void R_4764_Y(float x, float y, float z) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glTranslatef((float)x, (float)y, (float)z);
    }

    @Deprecated
    public static void J_1907_R(double x, double y, double z) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glTranslated((double)x, (double)y, (double)z);
    }

    @Deprecated
    public static void n_1700_B(FloatBuffer matrix) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glMultMatrixf((FloatBuffer)matrix);
    }

    @Deprecated
    public static void n_1700_B(D_1098_v matrix) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        matrix.n_1700_B(h_1847_R);
        ((Buffer)h_1847_R).rewind();
        lightning.product.X_933_l.n_1700_B(h_1847_R);
    }

    @Deprecated
    public static void G_564_y(float red, float green, float blue, float alpha) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        if (red != lightning.product.X_933_l.n_3318_d.n_1700_B || green != lightning.product.X_933_l.n_3318_d.J_1907_R || blue != lightning.product.X_933_l.n_3318_d.R_4764_Y || alpha != lightning.product.X_933_l.n_3318_d.G_564_y) {
            lightning.product.X_933_l.n_3318_d.n_1700_B = red;
            lightning.product.X_933_l.n_3318_d.J_1907_R = green;
            lightning.product.X_933_l.n_3318_d.R_4764_Y = blue;
            lightning.product.X_933_l.n_3318_d.G_564_y = alpha;
            GL11.glColor4f((float)red, (float)green, (float)blue, (float)alpha);
        }
    }

    @Deprecated
    public static void B_1668_F() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        lightning.product.X_933_l.n_3318_d.n_1700_B = -1.0f;
        lightning.product.X_933_l.n_3318_d.J_1907_R = -1.0f;
        lightning.product.X_933_l.n_3318_d.R_4764_Y = -1.0f;
        lightning.product.X_933_l.n_3318_d.G_564_y = -1.0f;
    }

    @Deprecated
    public static void n_1700_B(int type, int stride, long pointer) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glNormalPointer((int)type, (int)stride, (long)pointer);
    }

    @Deprecated
    public static void n_1700_B(int size, int type, int stride, long pointer) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glTexCoordPointer((int)size, (int)type, (int)stride, (long)pointer);
    }

    @Deprecated
    public static void J_1907_R(int size, int type, int stride, long pointer) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glVertexPointer((int)size, (int)type, (int)stride, (long)pointer);
    }

    @Deprecated
    public static void R_4764_Y(int size, int type, int stride, long pointer) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glColorPointer((int)size, (int)type, (int)stride, (long)pointer);
    }

    public static void n_1700_B(int index, int size, int type, boolean normalized, int stride, long pointer) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glVertexAttribPointer((int)index, (int)size, (int)type, (boolean)normalized, (int)stride, (long)pointer);
    }

    @Deprecated
    public static void k_2293_S(int cap) {
        if (!g_221_o) {
            c_4037_x.n_1700_B(c_4037_x::J_1907_R);
            GL11.glEnableClientState((int)cap);
        }
    }

    @Deprecated
    public static void q_2307_F(int cap) {
        if (!g_221_o) {
            c_4037_x.n_1700_B(c_4037_x::J_1907_R);
            GL11.glDisableClientState((int)cap);
        }
    }

    public static void Z_875_P(int index) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glEnableVertexAttribArray((int)index);
    }

    public static void c_3005_b(int index) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL20.glEnableVertexAttribArray((int)index);
    }

    public static void u_1723_Y(int mode, int first, int count) {
        int i;
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glDrawArrays((int)mode, (int)first, (int)count);
        if (Config.isShaders() && !B_1668_F && (i = Shaders.activeProgram.getCountInstances()) > 1) {
            for (int j = 1; j < i; ++j) {
                Shaders.uniform_instanceId.setValue(j);
                GL11.glDrawArrays((int)mode, (int)first, (int)count);
            }
            Shaders.uniform_instanceId.setValue(0);
        }
    }

    public static void G_564_y(float width) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glLineWidth((float)width);
    }

    public static void h_1847_R(int pname, int param) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        GL11.glPixelStorei((int)pname, (int)param);
    }

    public static void J_1907_R(int param, float value) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glPixelTransferf((int)param, (float)value);
    }

    public static void n_1700_B(int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        GL11.glReadPixels((int)x, (int)y, (int)width, (int)height, (int)format, (int)type, (ByteBuffer)pixels);
    }

    public static int g_164_R() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL11.glGetError();
    }

    public static String H_2857_Y(int name) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return GL11.glGetString((int)name);
    }

    public static int A_4115_X(int pname) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        return GL11.glGetInteger((int)pname);
    }

    public static boolean X_933_l() {
        return z_1737_N != lightning.product.X_933_l$Y_601_j.R_4764_Y;
    }

    public static int Z_976_R() {
        return 33984 + A_4115_X;
    }

    public static void H_1990_U() {
        GL11.glBindTexture((int)3553, (int)lightning.product.X_933_l.Y_1740_V[lightning.product.X_933_l.A_4115_X].J_1907_R);
    }

    public static int N_2525_X() {
        return lightning.product.X_933_l.Y_1740_V[lightning.product.X_933_l.A_4115_X].J_1907_R;
    }

    public static void c_4037_x() {
        if (Config.isMinecraftThread()) {
            int i = GL11.glGetInteger((int)34016);
            int j = GL11.glGetInteger((int)32873);
            int k = lightning.product.X_933_l.Z_976_R();
            int l = lightning.product.X_933_l.N_2525_X();
            if (l > 0 && (i != k || j != l)) {
                Config.dbg("checkTexture: act: " + k + ", glAct: " + i + ", tex: " + l + ", glTex: " + j);
            }
        }
    }

    public static void n_1700_B(IntBuffer p_genTextures_0_) {
        GL11.glGenTextures((IntBuffer)p_genTextures_0_);
    }

    public static void J_1907_R(IntBuffer p_deleteTextures_0_) {
        ((Buffer)p_deleteTextures_0_).rewind();
        while (p_deleteTextures_0_.position() < p_deleteTextures_0_.limit()) {
            int i = p_deleteTextures_0_.get();
            lightning.product.X_933_l.multiplayerClientSuggestionProvider(i);
        }
        ((Buffer)p_deleteTextures_0_).rewind();
    }

    public static boolean g_2268_R() {
        return lightning.product.X_933_l.Y_259_p.n_1700_B.J_1907_R;
    }

    public static void J_1907_R(boolean p_setFogEnabled_0_) {
        lightning.product.X_933_l.Y_259_p.n_1700_B.n_1700_B(p_setFogEnabled_0_);
    }

    public static void n_1700_B(GlAlphaState p_lockAlpha_0_) {
        if (!v_4276_D.isLocked()) {
            lightning.product.X_933_l.J_1907_R(d_2461_k);
            lightning.product.X_933_l.R_4764_Y(p_lockAlpha_0_);
            v_4276_D.lock();
        }
    }

    public static void T_3594_S() {
        if (v_4276_D.unlock()) {
            lightning.product.X_933_l.R_4764_Y(d_2461_k);
        }
    }

    public static void J_1907_R(GlAlphaState p_getAlphaState_0_) {
        if (v_4276_D.isLocked()) {
            p_getAlphaState_0_.setState(d_2461_k);
        } else {
            p_getAlphaState_0_.setState(lightning.product.X_933_l.Q_4569_t.n_1700_B.J_1907_R, lightning.product.X_933_l.Q_4569_t.J_1907_R, lightning.product.X_933_l.Q_4569_t.R_4764_Y);
        }
    }

    public static void R_4764_Y(GlAlphaState p_setAlphaState_0_) {
        if (v_4276_D.isLocked()) {
            d_2461_k.setState(p_setAlphaState_0_);
        } else {
            lightning.product.X_933_l.Q_4569_t.n_1700_B.n_1700_B(p_setAlphaState_0_.isEnabled());
            lightning.product.X_933_l.n_1700_B(p_setAlphaState_0_.getFunc(), p_setAlphaState_0_.getRef());
        }
    }

    public static void n_1700_B(GlBlendState p_lockBlend_0_) {
        if (!G_624_v.isLocked()) {
            lightning.product.X_933_l.J_1907_R(T_2506_i);
            lightning.product.X_933_l.R_4764_Y(p_lockBlend_0_);
            G_624_v.lock();
        }
    }

    public static void D_4792_h() {
        if (G_624_v.unlock()) {
            lightning.product.X_933_l.R_4764_Y(T_2506_i);
        }
    }

    public static void J_1907_R(GlBlendState p_getBlendState_0_) {
        if (G_624_v.isLocked()) {
            p_getBlendState_0_.setState(T_2506_i);
        } else {
            p_getBlendState_0_.setState(lightning.product.X_933_l.w_1457_N.n_1700_B.J_1907_R, lightning.product.X_933_l.w_1457_N.J_1907_R, lightning.product.X_933_l.w_1457_N.R_4764_Y, lightning.product.X_933_l.w_1457_N.G_564_y, lightning.product.X_933_l.w_1457_N.P_1922_E);
        }
    }

    public static void R_4764_Y(GlBlendState p_setBlendState_0_) {
        if (G_624_v.isLocked()) {
            T_2506_i.setState(p_setBlendState_0_);
        } else {
            lightning.product.X_933_l.w_1457_N.n_1700_B.n_1700_B(p_setBlendState_0_.isEnabled());
            if (!p_setBlendState_0_.isSeparate()) {
                lightning.product.X_933_l.J_1907_R(p_setBlendState_0_.getSrcFactor(), p_setBlendState_0_.getDstFactor());
            } else {
                lightning.product.X_933_l.J_1907_R(p_setBlendState_0_.getSrcFactor(), p_setBlendState_0_.getDstFactor(), p_setBlendState_0_.getSrcFactorAlpha(), p_setBlendState_0_.getDstFactorAlpha());
            }
        }
    }

    public static void n_1700_B(GlCullState p_lockCull_0_) {
        if (!q_4610_l.isLocked()) {
            lightning.product.X_933_l.J_1907_R(z_4693_k);
            lightning.product.X_933_l.R_4764_Y(p_lockCull_0_);
            q_4610_l.lock();
        }
    }

    public static void s_2632_s() {
        if (q_4610_l.unlock()) {
            lightning.product.X_933_l.R_4764_Y(z_4693_k);
        }
    }

    public static void J_1907_R(GlCullState p_getCullState_0_) {
        if (q_4610_l.isLocked()) {
            p_getCullState_0_.setState(z_4693_k);
        } else {
            p_getCullState_0_.setState(lightning.product.X_933_l.Q_2552_b.n_1700_B.J_1907_R, lightning.product.X_933_l.Q_2552_b.J_1907_R);
        }
    }

    public static void R_4764_Y(GlCullState p_setCullState_0_) {
        if (q_4610_l.isLocked()) {
            z_4693_k.setState(p_setCullState_0_);
        } else {
            lightning.product.X_933_l.Q_2552_b.n_1700_B.n_1700_B(p_setCullState_0_.isEnabled());
            lightning.product.X_933_l.Q_2552_b.J_1907_R = p_setCullState_0_.getMode();
        }
    }

    public static void n_1700_B(int p_glMultiDrawArrays_0_, IntBuffer p_glMultiDrawArrays_1_, IntBuffer p_glMultiDrawArrays_2_) {
        int i;
        GL14.glMultiDrawArrays((int)p_glMultiDrawArrays_0_, (IntBuffer)p_glMultiDrawArrays_1_, (IntBuffer)p_glMultiDrawArrays_2_);
        if (Config.isShaders() && !B_1668_F && (i = Shaders.activeProgram.getCountInstances()) > 1) {
            for (int j = 1; j < i; ++j) {
                Shaders.uniform_instanceId.setValue(j);
                GL14.glMultiDrawArrays((int)p_glMultiDrawArrays_0_, (IntBuffer)p_glMultiDrawArrays_1_, (IntBuffer)p_glMultiDrawArrays_2_);
            }
            Shaders.uniform_instanceId.setValue(0);
        }
    }

    public static void Y_1740_V(int p_clear_0_) {
        lightning.product.X_933_l.n_1700_B(p_clear_0_, false);
    }

    public static void R_4764_Y(IntBuffer p_callLists_0_) {
        int i;
        GL11.glCallLists((IntBuffer)p_callLists_0_);
        if (Config.isShaders() && !B_1668_F && (i = Shaders.activeProgram.getCountInstances()) > 1) {
            for (int j = 1; j < i; ++j) {
                Shaders.uniform_instanceId.setValue(j);
                GL11.glCallLists((IntBuffer)p_callLists_0_);
            }
            Shaders.uniform_instanceId.setValue(0);
        }
    }

    public static void n_1700_B(int p_bufferData_0_, long p_bufferData_1_, int p_bufferData_3_) {
        GL15.glBufferData((int)p_bufferData_0_, (long)p_bufferData_1_, (int)p_bufferData_3_);
    }

    public static void n_1700_B(int p_bufferSubData_0_, long p_bufferSubData_1_, ByteBuffer p_bufferSubData_3_) {
        GL15.glBufferSubData((int)p_bufferSubData_0_, (long)p_bufferSubData_1_, (ByteBuffer)p_bufferSubData_3_);
    }

    public static void n_1700_B(int p_copyBufferSubData_0_, int p_copyBufferSubData_1_, long p_copyBufferSubData_2_, long p_copyBufferSubData_4_, long p_copyBufferSubData_6_) {
        if (R_4764_Y) {
            GL31.glCopyBufferSubData((int)p_copyBufferSubData_0_, (int)p_copyBufferSubData_1_, (long)p_copyBufferSubData_2_, (long)p_copyBufferSubData_4_, (long)p_copyBufferSubData_6_);
        } else {
            ARBCopyBuffer.glCopyBufferSubData((int)p_copyBufferSubData_0_, (int)p_copyBufferSubData_1_, (long)p_copyBufferSubData_2_, (long)p_copyBufferSubData_4_, (long)p_copyBufferSubData_6_);
        }
    }

    public static boolean l_1233_K() {
        return g_164_R;
    }

    public static void R_4764_Y(boolean p_setFogAllowed_0_) {
        g_164_R = p_setFogAllowed_0_;
    }

    public static void z_1333_t() {
        g_221_o = true;
    }

    public static void O_508_d() {
        g_221_o = false;
    }

    public static void n_1700_B(int p_readPixels_0_, int p_readPixels_1_, int p_readPixels_2_, int p_readPixels_3_, int p_readPixels_4_, int p_readPixels_5_, long p_readPixels_6_) {
        GL11.glReadPixels((int)p_readPixels_0_, (int)p_readPixels_1_, (int)p_readPixels_2_, (int)p_readPixels_3_, (int)p_readPixels_4_, (int)p_readPixels_5_, (long)p_readPixels_6_);
    }

    public static int r_715_M() {
        return X_933_l;
    }

    public static int A_1038_p() {
        return Z_976_R;
    }

    public static void i_1637_u() {
        if (lightning.product.X_933_l.w_1457_N.n_1700_B.J_1907_R) {
            GL11.glEnable((int)3042);
        } else {
            GL11.glDisable((int)3042);
        }
        GL14.glBlendFuncSeparate((int)lightning.product.X_933_l.w_1457_N.J_1907_R, (int)lightning.product.X_933_l.w_1457_N.R_4764_Y, (int)lightning.product.X_933_l.w_1457_N.G_564_y, (int)lightning.product.X_933_l.w_1457_N.P_1922_E);
    }

    public static void n_1700_B(GlBlendState[] p_setBlendsIndexed_0_) {
        if (p_setBlendsIndexed_0_ != null) {
            for (int i = 0; i < p_setBlendsIndexed_0_.length; ++i) {
                GlBlendState glblendstate = p_setBlendsIndexed_0_[i];
                if (glblendstate == null) continue;
                if (glblendstate.isEnabled()) {
                    GL30.glEnablei((int)3042, (int)i);
                } else {
                    GL30.glDisablei((int)3042, (int)i);
                }
                ARBDrawBuffersBlend.glBlendFuncSeparateiARB((int)i, (int)glblendstate.getSrcFactor(), (int)glblendstate.getDstFactor(), (int)glblendstate.getSrcFactorAlpha(), (int)glblendstate.getDstFactorAlpha());
            }
        }
    }

    public static void n_1700_B(int p_bindImageTexture_0_, int p_bindImageTexture_1_, int p_bindImageTexture_2_, boolean p_bindImageTexture_3_, int p_bindImageTexture_4_, int p_bindImageTexture_5_, int p_bindImageTexture_6_) {
        if (p_bindImageTexture_0_ >= 0 && p_bindImageTexture_0_ < H_1990_U.length) {
            if (H_1990_U[p_bindImageTexture_0_] == p_bindImageTexture_1_) {
                return;
            }
            lightning.product.X_933_l.H_1990_U[p_bindImageTexture_0_] = p_bindImageTexture_1_;
        }
        GL42.glBindImageTexture((int)p_bindImageTexture_0_, (int)p_bindImageTexture_1_, (int)p_bindImageTexture_2_, (boolean)p_bindImageTexture_3_, (int)p_bindImageTexture_4_, (int)p_bindImageTexture_5_, (int)p_bindImageTexture_6_);
    }

    static {
        Y_1740_V = (k_2293_S[])IntStream.range(0, 32).mapToObj(p_lambda$static$3_0_ -> new k_2293_S()).toArray(k_2293_S[]::new);
        t_4043_B = 7425;
        x_607_J = new R_4764_Y(32826);
        e_4240_b = new u_1723_Y();
        n_3318_d = new G_564_y();
        v_4276_D = new LockCounter();
        d_2461_k = new GlAlphaState();
        G_624_v = new LockCounter();
        T_2506_i = new GlBlendState();
        q_4610_l = new LockCounter();
        z_4693_k = new GlCullState();
        g_221_o = false;
        e_2887_G = 0;
        B_1668_F = false;
        n_1700_B = 0.0f;
        J_1907_R = 0.0f;
        g_164_R = true;
        H_1990_U = new int[8];
    }

    @Deprecated
    static class n_1700_B {
        public final R_4764_Y n_1700_B = new R_4764_Y(3008);
        public int J_1907_R = 519;
        public float R_4764_Y = -1.0f;

        private n_1700_B() {
        }
    }

    static class R_4764_Y {
        private final int n_1700_B;
        private boolean J_1907_R;

        public R_4764_Y(int capability) {
            this.n_1700_B = capability;
        }

        public void n_1700_B() {
            this.n_1700_B(false);
        }

        public void J_1907_R() {
            this.n_1700_B(true);
        }

        public void n_1700_B(boolean enabled) {
            c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
            if (enabled != this.J_1907_R) {
                this.J_1907_R = enabled;
                if (enabled) {
                    GL11.glEnable((int)this.n_1700_B);
                } else {
                    GL11.glDisable((int)this.n_1700_B);
                }
            }
        }
    }

    @Deprecated
    static class v_4262_N {
        public final R_4764_Y n_1700_B = new R_4764_Y(2903);
        public int J_1907_R = 1032;
        public int R_4764_Y = 5634;

        private v_4262_N() {
        }
    }

    static class M_182_A {
        public final R_4764_Y n_1700_B = new R_4764_Y(3089);

        private M_182_A() {
        }
    }

    static class t_148_a {
        public final R_4764_Y n_1700_B = new R_4764_Y(2929);
        public boolean J_1907_R = true;
        public int R_4764_Y = 513;

        private t_148_a() {
        }
    }

    static class J_1907_R {
        public final R_4764_Y n_1700_B = new R_4764_Y(3042);
        public int J_1907_R = 1;
        public int R_4764_Y = 0;
        public int G_564_y = 1;
        public int P_1922_E = 0;

        private J_1907_R() {
        }
    }

    public static final class Y_601_j
    extends Enum<Y_601_j> {
        public static final /* enum */ Y_601_j n_1700_B = new Y_601_j();
        public static final /* enum */ Y_601_j J_1907_R = new Y_601_j();
        public static final /* enum */ Y_601_j R_4764_Y = new Y_601_j();
        private static final /* synthetic */ Y_601_j[] G_564_y;

        public static Y_601_j[] values() {
            return (Y_601_j[])G_564_y.clone();
        }

        public static Y_601_j valueOf(String name) {
            return Enum.valueOf(Y_601_j.class, name);
        }

        private static /* synthetic */ Y_601_j[] n_1700_B() {
            return new Y_601_j[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.X_933_l$Y_601_j.n_1700_B();
        }
    }

    public static final class P_4830_p
    extends Enum<P_4830_p> {
        public static final /* enum */ P_4830_p n_1700_B = new P_4830_p();
        public static final /* enum */ P_4830_p J_1907_R = new P_4830_p();
        public static final /* enum */ P_4830_p R_4764_Y = new P_4830_p();
        private static final /* synthetic */ P_4830_p[] G_564_y;

        public static P_4830_p[] values() {
            return (P_4830_p[])G_564_y.clone();
        }

        public static P_4830_p valueOf(String name) {
            return Enum.valueOf(P_4830_p.class, name);
        }

        private static /* synthetic */ P_4830_p[] n_1700_B() {
            return new P_4830_p[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            G_564_y = lightning.product.X_933_l$P_4830_p.n_1700_B();
        }
    }

    static class k_2293_S {
        public final R_4764_Y n_1700_B = new R_4764_Y(3553);
        public int J_1907_R;

        private k_2293_S() {
        }
    }

    @Deprecated
    public static final class Y_259_p
    extends Enum<Y_259_p> {
        public static final /* enum */ Y_259_p n_1700_B = new Y_259_p();
        public static final /* enum */ Y_259_p J_1907_R = new Y_259_p();
        public static final /* enum */ Y_259_p R_4764_Y = new Y_259_p();
        public static final /* enum */ Y_259_p G_564_y = new Y_259_p();
        private static final /* synthetic */ Y_259_p[] P_1922_E;

        public static Y_259_p[] values() {
            return (Y_259_p[])P_1922_E.clone();
        }

        public static Y_259_p valueOf(String name) {
            return Enum.valueOf(Y_259_p.class, name);
        }

        private static /* synthetic */ Y_259_p[] n_1700_B() {
            return new Y_259_p[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y};
        }

        static {
            P_1922_E = lightning.product.X_933_l$Y_259_p.n_1700_B();
        }
    }

    @Deprecated
    static class M_588_G {
        public final R_4764_Y n_1700_B = new R_4764_Y(2912);
        public int J_1907_R = 2048;
        public float R_4764_Y = 1.0f;
        public float G_564_y;
        public float P_1922_E = 1.0f;

        private M_588_G() {
        }
    }

    static class w_1484_f {
        public final R_4764_Y n_1700_B = new R_4764_Y(2884);
        public int J_1907_R = 1029;

        private w_1484_f() {
        }
    }

    static class Q_4569_t {
        public final R_4764_Y n_1700_B = new R_4764_Y(32823);
        public final R_4764_Y J_1907_R = new R_4764_Y(10754);
        public float R_4764_Y;
        public float G_564_y;

        private Q_4569_t() {
        }
    }

    static class P_1922_E {
        public final R_4764_Y n_1700_B = new R_4764_Y(3058);
        public int J_1907_R = 5379;

        private P_1922_E() {
        }
    }

    @Deprecated
    static class Q_2552_b {
        public final R_4764_Y n_1700_B;
        public final int J_1907_R;
        public int R_4764_Y = -1;

        public Q_2552_b(int coord, int textureGen) {
            this.J_1907_R = coord;
            this.n_1700_B = new R_4764_Y(textureGen);
        }
    }

    @Deprecated
    static class C_2741_M {
        public final Q_2552_b n_1700_B = new Q_2552_b(8192, 3168);
        public final Q_2552_b J_1907_R = new Q_2552_b(8193, 3169);
        public final Q_2552_b R_4764_Y = new Q_2552_b(8194, 3170);
        public final Q_2552_b G_564_y = new Q_2552_b(8195, 3171);

        private C_2741_M() {
        }
    }

    public static final class q_2307_F
    extends Enum<q_2307_F> {
        public static final /* enum */ q_2307_F n_1700_B = new q_2307_F();
        protected int J_1907_R;
        protected int R_4764_Y;
        protected int G_564_y;
        protected int P_1922_E;
        private static final /* synthetic */ q_2307_F[] u_1723_Y;

        public static q_2307_F[] values() {
            return (q_2307_F[])u_1723_Y.clone();
        }

        public static q_2307_F valueOf(String name) {
            return Enum.valueOf(q_2307_F.class, name);
        }

        private static /* synthetic */ q_2307_F[] n_1700_B() {
            return new q_2307_F[]{n_1700_B};
        }

        static {
            u_1723_Y = lightning.product.X_933_l$q_2307_F.n_1700_B();
        }
    }

    static class u_1723_Y {
        public boolean n_1700_B = true;
        public boolean J_1907_R = true;
        public boolean R_4764_Y = true;
        public boolean G_564_y = true;

        private u_1723_Y() {
        }
    }

    static class w_1457_N {
        public final multiplayerClientSuggestionProvider n_1700_B = new multiplayerClientSuggestionProvider();
        public int J_1907_R = -1;
        public int R_4764_Y = 7680;
        public int G_564_y = 7680;
        public int P_1922_E = 7680;

        private w_1457_N() {
        }
    }

    static class multiplayerClientSuggestionProvider {
        public int n_1700_B = 519;
        public int J_1907_R;
        public int R_4764_Y = -1;

        private multiplayerClientSuggestionProvider() {
        }
    }

    @Deprecated
    static class G_564_y {
        public float n_1700_B = 1.0f;
        public float J_1907_R = 1.0f;
        public float R_4764_Y = 1.0f;
        public float G_564_y = 1.0f;

        public G_564_y() {
            this(1.0f, 1.0f, 1.0f, 1.0f);
        }

        public G_564_y(float red, float green, float blue, float alpha) {
            this.n_1700_B = red;
            this.J_1907_R = green;
            this.R_4764_Y = blue;
            this.G_564_y = alpha;
        }
    }

    public static final class t_1786_h
    extends Enum<t_1786_h> {
        public static final /* enum */ t_1786_h n_1700_B = new t_1786_h(32771);
        public static final /* enum */ t_1786_h J_1907_R = new t_1786_h(32769);
        public static final /* enum */ t_1786_h R_4764_Y = new t_1786_h(772);
        public static final /* enum */ t_1786_h G_564_y = new t_1786_h(774);
        public static final /* enum */ t_1786_h P_1922_E = new t_1786_h(1);
        public static final /* enum */ t_1786_h u_1723_Y = new t_1786_h(32772);
        public static final /* enum */ t_1786_h v_4262_N = new t_1786_h(32770);
        public static final /* enum */ t_1786_h w_1484_f = new t_1786_h(773);
        public static final /* enum */ t_1786_h t_148_a = new t_1786_h(775);
        public static final /* enum */ t_1786_h s_956_w = new t_1786_h(771);
        public static final /* enum */ t_1786_h u_2550_I = new t_1786_h(769);
        public static final /* enum */ t_1786_h M_588_G = new t_1786_h(770);
        public static final /* enum */ t_1786_h P_4830_p = new t_1786_h(776);
        public static final /* enum */ t_1786_h h_1847_R = new t_1786_h(768);
        public static final /* enum */ t_1786_h Q_4569_t = new t_1786_h(0);
        public final int M_182_A;
        private static final /* synthetic */ t_1786_h[] t_1786_h;

        public static t_1786_h[] values() {
            return (t_1786_h[])t_1786_h.clone();
        }

        public static t_1786_h valueOf(String name) {
            return Enum.valueOf(t_1786_h.class, name);
        }

        private t_1786_h(int param) {
            this.M_182_A = param;
        }

        private static /* synthetic */ t_1786_h[] n_1700_B() {
            return new t_1786_h[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t};
        }

        static {
            t_1786_h = lightning.product.X_933_l$t_1786_h.n_1700_B();
        }
    }

    public static final class h_1847_R
    extends Enum<h_1847_R> {
        public static final /* enum */ h_1847_R n_1700_B = new h_1847_R(5377);
        public static final /* enum */ h_1847_R J_1907_R = new h_1847_R(5380);
        public static final /* enum */ h_1847_R R_4764_Y = new h_1847_R(5378);
        public static final /* enum */ h_1847_R G_564_y = new h_1847_R(5376);
        public static final /* enum */ h_1847_R P_1922_E = new h_1847_R(5379);
        public static final /* enum */ h_1847_R u_1723_Y = new h_1847_R(5388);
        public static final /* enum */ h_1847_R v_4262_N = new h_1847_R(5385);
        public static final /* enum */ h_1847_R w_1484_f = new h_1847_R(5386);
        public static final /* enum */ h_1847_R t_148_a = new h_1847_R(5390);
        public static final /* enum */ h_1847_R s_956_w = new h_1847_R(5381);
        public static final /* enum */ h_1847_R u_2550_I = new h_1847_R(5384);
        public static final /* enum */ h_1847_R M_588_G = new h_1847_R(5383);
        public static final /* enum */ h_1847_R P_4830_p = new h_1847_R(5389);
        public static final /* enum */ h_1847_R h_1847_R = new h_1847_R(5387);
        public static final /* enum */ h_1847_R Q_4569_t = new h_1847_R(5391);
        public static final /* enum */ h_1847_R M_182_A = new h_1847_R(5382);
        public final int t_1786_h;
        private static final /* synthetic */ h_1847_R[] multiplayerClientSuggestionProvider;

        public static h_1847_R[] values() {
            return (h_1847_R[])multiplayerClientSuggestionProvider.clone();
        }

        public static h_1847_R valueOf(String name) {
            return Enum.valueOf(h_1847_R.class, name);
        }

        private h_1847_R(int opCode) {
            this.t_1786_h = opCode;
        }

        private static /* synthetic */ h_1847_R[] n_1700_B() {
            return new h_1847_R[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R, Q_4569_t, M_182_A};
        }

        static {
            multiplayerClientSuggestionProvider = lightning.product.X_933_l$h_1847_R.n_1700_B();
        }
    }

    @Deprecated
    public static final class u_2550_I
    extends Enum<u_2550_I> {
        public static final /* enum */ u_2550_I n_1700_B = new u_2550_I(9729);
        public static final /* enum */ u_2550_I J_1907_R = new u_2550_I(2048);
        public static final /* enum */ u_2550_I R_4764_Y = new u_2550_I(2049);
        public final int G_564_y;
        private static final /* synthetic */ u_2550_I[] P_1922_E;

        public static u_2550_I[] values() {
            return (u_2550_I[])P_1922_E.clone();
        }

        public static u_2550_I valueOf(String name) {
            return Enum.valueOf(u_2550_I.class, name);
        }

        private u_2550_I(int param) {
            this.G_564_y = param;
        }

        private static /* synthetic */ u_2550_I[] n_1700_B() {
            return new u_2550_I[]{n_1700_B, J_1907_R, R_4764_Y};
        }

        static {
            P_1922_E = lightning.product.X_933_l$u_2550_I.n_1700_B();
        }
    }

    public static final class s_956_w
    extends Enum<s_956_w> {
        public static final /* enum */ s_956_w n_1700_B = new s_956_w(32771);
        public static final /* enum */ s_956_w J_1907_R = new s_956_w(32769);
        public static final /* enum */ s_956_w R_4764_Y = new s_956_w(772);
        public static final /* enum */ s_956_w G_564_y = new s_956_w(774);
        public static final /* enum */ s_956_w P_1922_E = new s_956_w(1);
        public static final /* enum */ s_956_w u_1723_Y = new s_956_w(32772);
        public static final /* enum */ s_956_w v_4262_N = new s_956_w(32770);
        public static final /* enum */ s_956_w w_1484_f = new s_956_w(773);
        public static final /* enum */ s_956_w t_148_a = new s_956_w(775);
        public static final /* enum */ s_956_w s_956_w = new s_956_w(771);
        public static final /* enum */ s_956_w u_2550_I = new s_956_w(769);
        public static final /* enum */ s_956_w M_588_G = new s_956_w(770);
        public static final /* enum */ s_956_w P_4830_p = new s_956_w(768);
        public static final /* enum */ s_956_w h_1847_R = new s_956_w(0);
        public final int Q_4569_t;
        private static final /* synthetic */ s_956_w[] M_182_A;

        public static s_956_w[] values() {
            return (s_956_w[])M_182_A.clone();
        }

        public static s_956_w valueOf(String name) {
            return Enum.valueOf(s_956_w.class, name);
        }

        private s_956_w(int param) {
            this.Q_4569_t = param;
        }

        private static /* synthetic */ s_956_w[] n_1700_B() {
            return new s_956_w[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N, w_1484_f, t_148_a, s_956_w, u_2550_I, M_588_G, P_4830_p, h_1847_R};
        }

        static {
            M_182_A = lightning.product.X_933_l$s_956_w.n_1700_B();
        }
    }
}


