/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Maps
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.ARBDebugOutput
 *  org.lwjgl.opengl.GL
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GLCapabilities
 *  org.lwjgl.opengl.GLDebugMessageARBCallback
 *  org.lwjgl.opengl.GLDebugMessageARBCallbackI
 *  org.lwjgl.opengl.GLDebugMessageCallback
 *  org.lwjgl.opengl.GLDebugMessageCallbackI
 *  org.lwjgl.opengl.KHRDebug
 */
package lightning.product;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lightning.product.K_1289_S;
import lightning.product.U_2871_b;
import lightning.product.MinecraftClient;
import lightning.product.c_3314_E;
import lightning.product.c_4037_x;
import lightning.product.g_164_R;
import lightning.product.q_383_x;
import net.optifine.Config;
import net.optifine.GlErrors;
import net.optifine.util.ArrayUtils;
import net.optifine.util.StrUtils;
import net.optifine.util.TimedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.ARBDebugOutput;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.GLDebugMessageARBCallback;
import org.lwjgl.opengl.GLDebugMessageARBCallbackI;
import org.lwjgl.opengl.GLDebugMessageCallback;
import org.lwjgl.opengl.GLDebugMessageCallbackI;
import org.lwjgl.opengl.KHRDebug;

public class q_2612_j {
    private static final Logger G_564_y = LogManager.getLogger();
    protected static final ByteBuffer n_1700_B = q_383_x.n_1700_B(64);
    protected static final FloatBuffer J_1907_R = n_1700_B.asFloatBuffer();
    protected static final IntBuffer R_4764_Y = n_1700_B.asIntBuffer();
    private static final Joiner P_1922_E = Joiner.on((char)'\n');
    private static final Joiner u_1723_Y = Joiner.on((String)"; ");
    private static final Map<Integer, String> v_4262_N = Maps.newHashMap();
    private static final List<Integer> w_1484_f = ImmutableList.of((Object)37190, (Object)37191, (Object)37192, (Object)33387);
    private static final List<Integer> t_148_a = ImmutableList.of((Object)37190, (Object)37191, (Object)37192);
    private static final Map<String, List<String>> s_956_w = Maps.newHashMap();
    private static int[] u_2550_I = q_2612_j.n_1700_B();

    private static int[] n_1700_B() {
        String s = System.getProperty("gl.ignore.errors");
        if (s == null) {
            return new int[0];
        }
        String[] astring = Config.tokenize(s, ",");
        int[] aint = new int[]{};
        for (int i = 0; i < astring.length; ++i) {
            int j;
            String s1 = astring[i].trim();
            int n = j = s1.startsWith("0x") ? Config.parseHexInt(s1, -1) : Config.parseInt(s1, -1);
            if (j < 0) {
                Config.warn("Invalid error id: " + s1);
                continue;
            }
            Config.log("Ignore OpenGL error: " + j);
            aint = ArrayUtils.addIntToArray(aint, j);
        }
        return aint;
    }

    private static String n_1700_B(int p_209245_0_) {
        return "Unknown (0x" + Integer.toHexString(p_209245_0_).toUpperCase() + ")";
    }

    private static String J_1907_R(int p_209242_0_) {
        switch (p_209242_0_) {
            case 33350: {
                return "API";
            }
            case 33351: {
                return "WINDOW SYSTEM";
            }
            case 33352: {
                return "SHADER COMPILER";
            }
            case 33353: {
                return "THIRD PARTY";
            }
            case 33354: {
                return "APPLICATION";
            }
            case 33355: {
                return "OTHER";
            }
        }
        return q_2612_j.n_1700_B(p_209242_0_);
    }

    private static String R_4764_Y(int p_209248_0_) {
        switch (p_209248_0_) {
            case 33356: {
                return "ERROR";
            }
            case 33357: {
                return "DEPRECATED BEHAVIOR";
            }
            case 33358: {
                return "UNDEFINED BEHAVIOR";
            }
            case 33359: {
                return "PORTABILITY";
            }
            case 33360: {
                return "PERFORMANCE";
            }
            case 33361: {
                return "OTHER";
            }
            case 33384: {
                return "MARKER";
            }
        }
        return q_2612_j.n_1700_B(p_209248_0_);
    }

    private static String G_564_y(int p_209246_0_) {
        switch (p_209246_0_) {
            case 33387: {
                return "NOTIFICATION";
            }
            case 37190: {
                return "Send";
            }
            case 37191: {
                return "MEDIUM";
            }
            case 37192: {
                return "LOW";
            }
        }
        return q_2612_j.n_1700_B(p_209246_0_);
    }

    private static void n_1700_B(int source, int type, int id, int severity, int messageLength, long message, long p_209244_7_) {
        MinecraftClient minecraft;
        if (!(type == 33385 || type == 33386 || ArrayUtils.contains(u_2550_I, id) || Config.isShaders() && source == 33352 || (minecraft = MinecraftClient.A_4115_X()) != null && minecraft.RealmsServerPing() != null && minecraft.RealmsServerPing().Y_259_p() || !GlErrors.isEnabled(id))) {
            String s = q_2612_j.J_1907_R(source);
            String s1 = q_2612_j.R_4764_Y(type);
            String s2 = q_2612_j.G_564_y(severity);
            String s3 = GLDebugMessageCallback.getMessage((int)messageLength, (long)message);
            s3 = StrUtils.trim(s3, " \n\r\t");
            String s4 = String.format("OpenGL %s %s: %s (%s)", s, s1, id, s3);
            Exception exception = new Exception("Stack trace");
            StackTraceElement[] astacktraceelement = exception.getStackTrace();
            StackTraceElement[] astacktraceelement1 = astacktraceelement.length > 2 ? Arrays.copyOfRange(astacktraceelement, 2, astacktraceelement.length) : astacktraceelement;
            exception.setStackTrace(astacktraceelement1);
            if (type == 33356) {
                G_564_y.error(s4, (Throwable)exception);
            } else {
                G_564_y.info(s4, (Throwable)exception);
            }
            if (Config.isShowGlErrors() && TimedEvent.isActive("ShowGlErrorDebug", 10000L)) {
                String s5 = Config.getGlErrorString(id);
                if (id == 0 || Config.equals(s5, "Unknown")) {
                    s5 = s3;
                }
                String s6 = K_1289_S.n_1700_B("of.message.openglError", id, s5);
                MinecraftClient.A_4115_X().M_588_G.R_4764_Y().n_1700_B(new U_2871_b(s6));
            }
        }
    }

    private static void n_1700_B(int value, String name) {
        v_4262_N.merge(value, name, (p_lambda$registerGlConstantName$0_0_, p_lambda$registerGlConstantName$0_1_) -> p_lambda$registerGlConstantName$0_0_ + "/" + p_lambda$registerGlConstantName$0_1_);
    }

    public static void n_1700_B(int debugVerbosity, boolean synchronous) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        if (debugVerbosity > 0) {
            GLCapabilities glcapabilities = GL.getCapabilities();
            if (glcapabilities.GL_KHR_debug) {
                GL11.glEnable((int)37600);
                if (synchronous) {
                    GL11.glEnable((int)33346);
                }
                for (int i = 0; i < w_1484_f.size(); ++i) {
                    boolean flag = i < debugVerbosity;
                    KHRDebug.glDebugMessageControl((int)4352, (int)4352, (int)w_1484_f.get(i), (int[])null, (boolean)flag);
                }
                KHRDebug.glDebugMessageCallback((GLDebugMessageCallbackI)((GLDebugMessageCallbackI)g_164_R.n_1700_B(GLDebugMessageCallback.create(q_2612_j::n_1700_B), c_3314_E::n_1700_B)), (long)0L);
            } else if (glcapabilities.GL_ARB_debug_output) {
                if (synchronous) {
                    GL11.glEnable((int)33346);
                }
                for (int j = 0; j < t_148_a.size(); ++j) {
                    boolean flag1 = j < debugVerbosity;
                    ARBDebugOutput.glDebugMessageControlARB((int)4352, (int)4352, (int)t_148_a.get(j), (int[])null, (boolean)flag1);
                }
                ARBDebugOutput.glDebugMessageCallbackARB((GLDebugMessageARBCallbackI)((GLDebugMessageARBCallbackI)g_164_R.n_1700_B(GLDebugMessageARBCallback.create(q_2612_j::n_1700_B), c_3314_E::n_1700_B)), (long)0L);
            }
        }
    }

    static {
        q_2612_j.n_1700_B(256, "GL11.GL_ACCUM");
        q_2612_j.n_1700_B(257, "GL11.GL_LOAD");
        q_2612_j.n_1700_B(258, "GL11.GL_RETURN");
        q_2612_j.n_1700_B(259, "GL11.GL_MULT");
        q_2612_j.n_1700_B(260, "GL11.GL_ADD");
        q_2612_j.n_1700_B(512, "GL11.GL_NEVER");
        q_2612_j.n_1700_B(513, "GL11.GL_LESS");
        q_2612_j.n_1700_B(514, "GL11.GL_EQUAL");
        q_2612_j.n_1700_B(515, "GL11.GL_LEQUAL");
        q_2612_j.n_1700_B(516, "GL11.GL_GREATER");
        q_2612_j.n_1700_B(517, "GL11.GL_NOTEQUAL");
        q_2612_j.n_1700_B(518, "GL11.GL_GEQUAL");
        q_2612_j.n_1700_B(519, "GL11.GL_ALWAYS");
        q_2612_j.n_1700_B(0, "GL11.GL_POINTS");
        q_2612_j.n_1700_B(1, "GL11.GL_LINES");
        q_2612_j.n_1700_B(2, "GL11.GL_LINE_LOOP");
        q_2612_j.n_1700_B(3, "GL11.GL_LINE_STRIP");
        q_2612_j.n_1700_B(4, "GL11.GL_TRIANGLES");
        q_2612_j.n_1700_B(5, "GL11.GL_TRIANGLE_STRIP");
        q_2612_j.n_1700_B(6, "GL11.GL_TRIANGLE_FAN");
        q_2612_j.n_1700_B(7, "GL11.GL_QUADS");
        q_2612_j.n_1700_B(8, "GL11.GL_QUAD_STRIP");
        q_2612_j.n_1700_B(9, "GL11.GL_POLYGON");
        q_2612_j.n_1700_B(0, "GL11.GL_ZERO");
        q_2612_j.n_1700_B(1, "GL11.GL_ONE");
        q_2612_j.n_1700_B(768, "GL11.GL_SRC_COLOR");
        q_2612_j.n_1700_B(769, "GL11.GL_ONE_MINUS_SRC_COLOR");
        q_2612_j.n_1700_B(770, "GL11.GL_SRC_ALPHA");
        q_2612_j.n_1700_B(771, "GL11.GL_ONE_MINUS_SRC_ALPHA");
        q_2612_j.n_1700_B(772, "GL11.GL_DST_ALPHA");
        q_2612_j.n_1700_B(773, "GL11.GL_ONE_MINUS_DST_ALPHA");
        q_2612_j.n_1700_B(774, "GL11.GL_DST_COLOR");
        q_2612_j.n_1700_B(775, "GL11.GL_ONE_MINUS_DST_COLOR");
        q_2612_j.n_1700_B(776, "GL11.GL_SRC_ALPHA_SATURATE");
        q_2612_j.n_1700_B(32769, "GL14.GL_CONSTANT_COLOR");
        q_2612_j.n_1700_B(32770, "GL14.GL_ONE_MINUS_CONSTANT_COLOR");
        q_2612_j.n_1700_B(32771, "GL14.GL_CONSTANT_ALPHA");
        q_2612_j.n_1700_B(32772, "GL14.GL_ONE_MINUS_CONSTANT_ALPHA");
        q_2612_j.n_1700_B(1, "GL11.GL_TRUE");
        q_2612_j.n_1700_B(0, "GL11.GL_FALSE");
        q_2612_j.n_1700_B(12288, "GL11.GL_CLIP_PLANE0");
        q_2612_j.n_1700_B(12289, "GL11.GL_CLIP_PLANE1");
        q_2612_j.n_1700_B(12290, "GL11.GL_CLIP_PLANE2");
        q_2612_j.n_1700_B(12291, "GL11.GL_CLIP_PLANE3");
        q_2612_j.n_1700_B(12292, "GL11.GL_CLIP_PLANE4");
        q_2612_j.n_1700_B(12293, "GL11.GL_CLIP_PLANE5");
        q_2612_j.n_1700_B(5120, "GL11.GL_BYTE");
        q_2612_j.n_1700_B(5121, "GL11.GL_UNSIGNED_BYTE");
        q_2612_j.n_1700_B(5122, "GL11.GL_SHORT");
        q_2612_j.n_1700_B(5123, "GL11.GL_UNSIGNED_SHORT");
        q_2612_j.n_1700_B(5124, "GL11.GL_INT");
        q_2612_j.n_1700_B(5125, "GL11.GL_UNSIGNED_INT");
        q_2612_j.n_1700_B(5126, "GL11.GL_FLOAT");
        q_2612_j.n_1700_B(5127, "GL11.GL_2_BYTES");
        q_2612_j.n_1700_B(5128, "GL11.GL_3_BYTES");
        q_2612_j.n_1700_B(5129, "GL11.GL_4_BYTES");
        q_2612_j.n_1700_B(5130, "GL11.GL_DOUBLE");
        q_2612_j.n_1700_B(0, "GL11.GL_NONE");
        q_2612_j.n_1700_B(1024, "GL11.GL_FRONT_LEFT");
        q_2612_j.n_1700_B(1025, "GL11.GL_FRONT_RIGHT");
        q_2612_j.n_1700_B(1026, "GL11.GL_BACK_LEFT");
        q_2612_j.n_1700_B(1027, "GL11.GL_BACK_RIGHT");
        q_2612_j.n_1700_B(1028, "GL11.GL_FRONT");
        q_2612_j.n_1700_B(1029, "GL11.GL_BACK");
        q_2612_j.n_1700_B(1030, "GL11.GL_LEFT");
        q_2612_j.n_1700_B(1031, "GL11.GL_RIGHT");
        q_2612_j.n_1700_B(1032, "GL11.GL_FRONT_AND_BACK");
        q_2612_j.n_1700_B(1033, "GL11.GL_AUX0");
        q_2612_j.n_1700_B(1034, "GL11.GL_AUX1");
        q_2612_j.n_1700_B(1035, "GL11.GL_AUX2");
        q_2612_j.n_1700_B(1036, "GL11.GL_AUX3");
        q_2612_j.n_1700_B(0, "GL11.GL_NO_ERROR");
        q_2612_j.n_1700_B(1280, "GL11.GL_INVALID_ENUM");
        q_2612_j.n_1700_B(1281, "GL11.GL_INVALID_VALUE");
        q_2612_j.n_1700_B(1282, "GL11.GL_INVALID_OPERATION");
        q_2612_j.n_1700_B(1283, "GL11.GL_STACK_OVERFLOW");
        q_2612_j.n_1700_B(1284, "GL11.GL_STACK_UNDERFLOW");
        q_2612_j.n_1700_B(1285, "GL11.GL_OUT_OF_MEMORY");
        q_2612_j.n_1700_B(1536, "GL11.GL_2D");
        q_2612_j.n_1700_B(1537, "GL11.GL_3D");
        q_2612_j.n_1700_B(1538, "GL11.GL_3D_COLOR");
        q_2612_j.n_1700_B(1539, "GL11.GL_3D_COLOR_TEXTURE");
        q_2612_j.n_1700_B(1540, "GL11.GL_4D_COLOR_TEXTURE");
        q_2612_j.n_1700_B(1792, "GL11.GL_PASS_THROUGH_TOKEN");
        q_2612_j.n_1700_B(1793, "GL11.GL_POINT_TOKEN");
        q_2612_j.n_1700_B(1794, "GL11.GL_LINE_TOKEN");
        q_2612_j.n_1700_B(1795, "GL11.GL_POLYGON_TOKEN");
        q_2612_j.n_1700_B(1796, "GL11.GL_BITMAP_TOKEN");
        q_2612_j.n_1700_B(1797, "GL11.GL_DRAW_PIXEL_TOKEN");
        q_2612_j.n_1700_B(1798, "GL11.GL_COPY_PIXEL_TOKEN");
        q_2612_j.n_1700_B(1799, "GL11.GL_LINE_RESET_TOKEN");
        q_2612_j.n_1700_B(2048, "GL11.GL_EXP");
        q_2612_j.n_1700_B(2049, "GL11.GL_EXP2");
        q_2612_j.n_1700_B(2304, "GL11.GL_CW");
        q_2612_j.n_1700_B(2305, "GL11.GL_CCW");
        q_2612_j.n_1700_B(2560, "GL11.GL_COEFF");
        q_2612_j.n_1700_B(2561, "GL11.GL_ORDER");
        q_2612_j.n_1700_B(2562, "GL11.GL_DOMAIN");
        q_2612_j.n_1700_B(2816, "GL11.GL_CURRENT_COLOR");
        q_2612_j.n_1700_B(2817, "GL11.GL_CURRENT_INDEX");
        q_2612_j.n_1700_B(2818, "GL11.GL_CURRENT_NORMAL");
        q_2612_j.n_1700_B(2819, "GL11.GL_CURRENT_TEXTURE_COORDS");
        q_2612_j.n_1700_B(2820, "GL11.GL_CURRENT_RASTER_COLOR");
        q_2612_j.n_1700_B(2821, "GL11.GL_CURRENT_RASTER_INDEX");
        q_2612_j.n_1700_B(2822, "GL11.GL_CURRENT_RASTER_TEXTURE_COORDS");
        q_2612_j.n_1700_B(2823, "GL11.GL_CURRENT_RASTER_POSITION");
        q_2612_j.n_1700_B(2824, "GL11.GL_CURRENT_RASTER_POSITION_VALID");
        q_2612_j.n_1700_B(2825, "GL11.GL_CURRENT_RASTER_DISTANCE");
        q_2612_j.n_1700_B(2832, "GL11.GL_POINT_SMOOTH");
        q_2612_j.n_1700_B(2833, "GL11.GL_POINT_SIZE");
        q_2612_j.n_1700_B(2834, "GL11.GL_POINT_SIZE_RANGE");
        q_2612_j.n_1700_B(2835, "GL11.GL_POINT_SIZE_GRANULARITY");
        q_2612_j.n_1700_B(2848, "GL11.GL_LINE_SMOOTH");
        q_2612_j.n_1700_B(2849, "GL11.GL_LINE_WIDTH");
        q_2612_j.n_1700_B(2850, "GL11.GL_LINE_WIDTH_RANGE");
        q_2612_j.n_1700_B(2851, "GL11.GL_LINE_WIDTH_GRANULARITY");
        q_2612_j.n_1700_B(2852, "GL11.GL_LINE_STIPPLE");
        q_2612_j.n_1700_B(2853, "GL11.GL_LINE_STIPPLE_PATTERN");
        q_2612_j.n_1700_B(2854, "GL11.GL_LINE_STIPPLE_REPEAT");
        q_2612_j.n_1700_B(2864, "GL11.GL_LIST_MODE");
        q_2612_j.n_1700_B(2865, "GL11.GL_MAX_LIST_NESTING");
        q_2612_j.n_1700_B(2866, "GL11.GL_LIST_BASE");
        q_2612_j.n_1700_B(2867, "GL11.GL_LIST_INDEX");
        q_2612_j.n_1700_B(2880, "GL11.GL_POLYGON_MODE");
        q_2612_j.n_1700_B(2881, "GL11.GL_POLYGON_SMOOTH");
        q_2612_j.n_1700_B(2882, "GL11.GL_POLYGON_STIPPLE");
        q_2612_j.n_1700_B(2883, "GL11.GL_EDGE_FLAG");
        q_2612_j.n_1700_B(2884, "GL11.GL_CULL_FACE");
        q_2612_j.n_1700_B(2885, "GL11.GL_CULL_FACE_MODE");
        q_2612_j.n_1700_B(2886, "GL11.GL_FRONT_FACE");
        q_2612_j.n_1700_B(2896, "GL11.GL_LIGHTING");
        q_2612_j.n_1700_B(2897, "GL11.GL_LIGHT_MODEL_LOCAL_VIEWER");
        q_2612_j.n_1700_B(2898, "GL11.GL_LIGHT_MODEL_TWO_SIDE");
        q_2612_j.n_1700_B(2899, "GL11.GL_LIGHT_MODEL_AMBIENT");
        q_2612_j.n_1700_B(2900, "GL11.GL_SHADE_MODEL");
        q_2612_j.n_1700_B(2901, "GL11.GL_COLOR_MATERIAL_FACE");
        q_2612_j.n_1700_B(2902, "GL11.GL_COLOR_MATERIAL_PARAMETER");
        q_2612_j.n_1700_B(2903, "GL11.GL_COLOR_MATERIAL");
        q_2612_j.n_1700_B(2912, "GL11.GL_FOG");
        q_2612_j.n_1700_B(2913, "GL11.GL_FOG_INDEX");
        q_2612_j.n_1700_B(2914, "GL11.GL_FOG_DENSITY");
        q_2612_j.n_1700_B(2915, "GL11.GL_FOG_START");
        q_2612_j.n_1700_B(2916, "GL11.GL_FOG_END");
        q_2612_j.n_1700_B(2917, "GL11.GL_FOG_MODE");
        q_2612_j.n_1700_B(2918, "GL11.GL_FOG_COLOR");
        q_2612_j.n_1700_B(2928, "GL11.GL_DEPTH_RANGE");
        q_2612_j.n_1700_B(2929, "GL11.GL_DEPTH_TEST");
        q_2612_j.n_1700_B(2930, "GL11.GL_DEPTH_WRITEMASK");
        q_2612_j.n_1700_B(2931, "GL11.GL_DEPTH_CLEAR_VALUE");
        q_2612_j.n_1700_B(2932, "GL11.GL_DEPTH_FUNC");
        q_2612_j.n_1700_B(2944, "GL11.GL_ACCUM_CLEAR_VALUE");
        q_2612_j.n_1700_B(2960, "GL11.GL_STENCIL_TEST");
        q_2612_j.n_1700_B(2961, "GL11.GL_STENCIL_CLEAR_VALUE");
        q_2612_j.n_1700_B(2962, "GL11.GL_STENCIL_FUNC");
        q_2612_j.n_1700_B(2963, "GL11.GL_STENCIL_VALUE_MASK");
        q_2612_j.n_1700_B(2964, "GL11.GL_STENCIL_FAIL");
        q_2612_j.n_1700_B(2965, "GL11.GL_STENCIL_PASS_DEPTH_FAIL");
        q_2612_j.n_1700_B(2966, "GL11.GL_STENCIL_PASS_DEPTH_PASS");
        q_2612_j.n_1700_B(2967, "GL11.GL_STENCIL_REF");
        q_2612_j.n_1700_B(2968, "GL11.GL_STENCIL_WRITEMASK");
        q_2612_j.n_1700_B(2976, "GL11.GL_MATRIX_MODE");
        q_2612_j.n_1700_B(2977, "GL11.GL_NORMALIZE");
        q_2612_j.n_1700_B(2978, "GL11.GL_VIEWPORT");
        q_2612_j.n_1700_B(2979, "GL11.GL_MODELVIEW_STACK_DEPTH");
        q_2612_j.n_1700_B(2980, "GL11.GL_PROJECTION_STACK_DEPTH");
        q_2612_j.n_1700_B(2981, "GL11.GL_TEXTURE_STACK_DEPTH");
        q_2612_j.n_1700_B(2982, "GL11.GL_MODELVIEW_MATRIX");
        q_2612_j.n_1700_B(2983, "GL11.GL_PROJECTION_MATRIX");
        q_2612_j.n_1700_B(2984, "GL11.GL_TEXTURE_MATRIX");
        q_2612_j.n_1700_B(2992, "GL11.GL_ATTRIB_STACK_DEPTH");
        q_2612_j.n_1700_B(2993, "GL11.GL_CLIENT_ATTRIB_STACK_DEPTH");
        q_2612_j.n_1700_B(3008, "GL11.GL_ALPHA_TEST");
        q_2612_j.n_1700_B(3009, "GL11.GL_ALPHA_TEST_FUNC");
        q_2612_j.n_1700_B(3010, "GL11.GL_ALPHA_TEST_REF");
        q_2612_j.n_1700_B(3024, "GL11.GL_DITHER");
        q_2612_j.n_1700_B(3040, "GL11.GL_BLEND_DST");
        q_2612_j.n_1700_B(3041, "GL11.GL_BLEND_SRC");
        q_2612_j.n_1700_B(3042, "GL11.GL_BLEND");
        q_2612_j.n_1700_B(3056, "GL11.GL_LOGIC_OP_MODE");
        q_2612_j.n_1700_B(3057, "GL11.GL_INDEX_LOGIC_OP");
        q_2612_j.n_1700_B(3058, "GL11.GL_COLOR_LOGIC_OP");
        q_2612_j.n_1700_B(3072, "GL11.GL_AUX_BUFFERS");
        q_2612_j.n_1700_B(3073, "GL11.GL_DRAW_BUFFER");
        q_2612_j.n_1700_B(3074, "GL11.GL_READ_BUFFER");
        q_2612_j.n_1700_B(3088, "GL11.GL_SCISSOR_BOX");
        q_2612_j.n_1700_B(3089, "GL11.GL_SCISSOR_TEST");
        q_2612_j.n_1700_B(3104, "GL11.GL_INDEX_CLEAR_VALUE");
        q_2612_j.n_1700_B(3105, "GL11.GL_INDEX_WRITEMASK");
        q_2612_j.n_1700_B(3106, "GL11.GL_COLOR_CLEAR_VALUE");
        q_2612_j.n_1700_B(3107, "GL11.GL_COLOR_WRITEMASK");
        q_2612_j.n_1700_B(3120, "GL11.GL_INDEX_MODE");
        q_2612_j.n_1700_B(3121, "GL11.GL_RGBA_MODE");
        q_2612_j.n_1700_B(3122, "GL11.GL_DOUBLEBUFFER");
        q_2612_j.n_1700_B(3123, "GL11.GL_STEREO");
        q_2612_j.n_1700_B(3136, "GL11.GL_RENDER_MODE");
        q_2612_j.n_1700_B(3152, "GL11.GL_PERSPECTIVE_CORRECTION_HINT");
        q_2612_j.n_1700_B(3153, "GL11.GL_POINT_SMOOTH_HINT");
        q_2612_j.n_1700_B(3154, "GL11.GL_LINE_SMOOTH_HINT");
        q_2612_j.n_1700_B(3155, "GL11.GL_POLYGON_SMOOTH_HINT");
        q_2612_j.n_1700_B(3156, "GL11.GL_FOG_HINT");
        q_2612_j.n_1700_B(3168, "GL11.GL_TEXTURE_GEN_S");
        q_2612_j.n_1700_B(3169, "GL11.GL_TEXTURE_GEN_T");
        q_2612_j.n_1700_B(3170, "GL11.GL_TEXTURE_GEN_R");
        q_2612_j.n_1700_B(3171, "GL11.GL_TEXTURE_GEN_Q");
        q_2612_j.n_1700_B(3184, "GL11.GL_PIXEL_MAP_I_TO_I");
        q_2612_j.n_1700_B(3185, "GL11.GL_PIXEL_MAP_S_TO_S");
        q_2612_j.n_1700_B(3186, "GL11.GL_PIXEL_MAP_I_TO_R");
        q_2612_j.n_1700_B(3187, "GL11.GL_PIXEL_MAP_I_TO_G");
        q_2612_j.n_1700_B(3188, "GL11.GL_PIXEL_MAP_I_TO_B");
        q_2612_j.n_1700_B(3189, "GL11.GL_PIXEL_MAP_I_TO_A");
        q_2612_j.n_1700_B(3190, "GL11.GL_PIXEL_MAP_R_TO_R");
        q_2612_j.n_1700_B(3191, "GL11.GL_PIXEL_MAP_G_TO_G");
        q_2612_j.n_1700_B(3192, "GL11.GL_PIXEL_MAP_B_TO_B");
        q_2612_j.n_1700_B(3193, "GL11.GL_PIXEL_MAP_A_TO_A");
        q_2612_j.n_1700_B(3248, "GL11.GL_PIXEL_MAP_I_TO_I_SIZE");
        q_2612_j.n_1700_B(3249, "GL11.GL_PIXEL_MAP_S_TO_S_SIZE");
        q_2612_j.n_1700_B(3250, "GL11.GL_PIXEL_MAP_I_TO_R_SIZE");
        q_2612_j.n_1700_B(3251, "GL11.GL_PIXEL_MAP_I_TO_G_SIZE");
        q_2612_j.n_1700_B(3252, "GL11.GL_PIXEL_MAP_I_TO_B_SIZE");
        q_2612_j.n_1700_B(3253, "GL11.GL_PIXEL_MAP_I_TO_A_SIZE");
        q_2612_j.n_1700_B(3254, "GL11.GL_PIXEL_MAP_R_TO_R_SIZE");
        q_2612_j.n_1700_B(3255, "GL11.GL_PIXEL_MAP_G_TO_G_SIZE");
        q_2612_j.n_1700_B(3256, "GL11.GL_PIXEL_MAP_B_TO_B_SIZE");
        q_2612_j.n_1700_B(3257, "GL11.GL_PIXEL_MAP_A_TO_A_SIZE");
        q_2612_j.n_1700_B(3312, "GL11.GL_UNPACK_SWAP_BYTES");
        q_2612_j.n_1700_B(3313, "GL11.GL_UNPACK_LSB_FIRST");
        q_2612_j.n_1700_B(3314, "GL11.GL_UNPACK_ROW_LENGTH");
        q_2612_j.n_1700_B(3315, "GL11.GL_UNPACK_SKIP_ROWS");
        q_2612_j.n_1700_B(3316, "GL11.GL_UNPACK_SKIP_PIXELS");
        q_2612_j.n_1700_B(3317, "GL11.GL_UNPACK_ALIGNMENT");
        q_2612_j.n_1700_B(3328, "GL11.GL_PACK_SWAP_BYTES");
        q_2612_j.n_1700_B(3329, "GL11.GL_PACK_LSB_FIRST");
        q_2612_j.n_1700_B(3330, "GL11.GL_PACK_ROW_LENGTH");
        q_2612_j.n_1700_B(3331, "GL11.GL_PACK_SKIP_ROWS");
        q_2612_j.n_1700_B(3332, "GL11.GL_PACK_SKIP_PIXELS");
        q_2612_j.n_1700_B(3333, "GL11.GL_PACK_ALIGNMENT");
        q_2612_j.n_1700_B(3344, "GL11.GL_MAP_COLOR");
        q_2612_j.n_1700_B(3345, "GL11.GL_MAP_STENCIL");
        q_2612_j.n_1700_B(3346, "GL11.GL_INDEX_SHIFT");
        q_2612_j.n_1700_B(3347, "GL11.GL_INDEX_OFFSET");
        q_2612_j.n_1700_B(3348, "GL11.GL_RED_SCALE");
        q_2612_j.n_1700_B(3349, "GL11.GL_RED_BIAS");
        q_2612_j.n_1700_B(3350, "GL11.GL_ZOOM_X");
        q_2612_j.n_1700_B(3351, "GL11.GL_ZOOM_Y");
        q_2612_j.n_1700_B(3352, "GL11.GL_GREEN_SCALE");
        q_2612_j.n_1700_B(3353, "GL11.GL_GREEN_BIAS");
        q_2612_j.n_1700_B(3354, "GL11.GL_BLUE_SCALE");
        q_2612_j.n_1700_B(3355, "GL11.GL_BLUE_BIAS");
        q_2612_j.n_1700_B(3356, "GL11.GL_ALPHA_SCALE");
        q_2612_j.n_1700_B(3357, "GL11.GL_ALPHA_BIAS");
        q_2612_j.n_1700_B(3358, "GL11.GL_DEPTH_SCALE");
        q_2612_j.n_1700_B(3359, "GL11.GL_DEPTH_BIAS");
        q_2612_j.n_1700_B(3376, "GL11.GL_MAX_EVAL_ORDER");
        q_2612_j.n_1700_B(3377, "GL11.GL_MAX_LIGHTS");
        q_2612_j.n_1700_B(3378, "GL11.GL_MAX_CLIP_PLANES");
        q_2612_j.n_1700_B(3379, "GL11.GL_MAX_TEXTURE_SIZE");
        q_2612_j.n_1700_B(3380, "GL11.GL_MAX_PIXEL_MAP_TABLE");
        q_2612_j.n_1700_B(3381, "GL11.GL_MAX_ATTRIB_STACK_DEPTH");
        q_2612_j.n_1700_B(3382, "GL11.GL_MAX_MODELVIEW_STACK_DEPTH");
        q_2612_j.n_1700_B(3383, "GL11.GL_MAX_NAME_STACK_DEPTH");
        q_2612_j.n_1700_B(3384, "GL11.GL_MAX_PROJECTION_STACK_DEPTH");
        q_2612_j.n_1700_B(3385, "GL11.GL_MAX_TEXTURE_STACK_DEPTH");
        q_2612_j.n_1700_B(3386, "GL11.GL_MAX_VIEWPORT_DIMS");
        q_2612_j.n_1700_B(3387, "GL11.GL_MAX_CLIENT_ATTRIB_STACK_DEPTH");
        q_2612_j.n_1700_B(3408, "GL11.GL_SUBPIXEL_BITS");
        q_2612_j.n_1700_B(3409, "GL11.GL_INDEX_BITS");
        q_2612_j.n_1700_B(3410, "GL11.GL_RED_BITS");
        q_2612_j.n_1700_B(3411, "GL11.GL_GREEN_BITS");
        q_2612_j.n_1700_B(3412, "GL11.GL_BLUE_BITS");
        q_2612_j.n_1700_B(3413, "GL11.GL_ALPHA_BITS");
        q_2612_j.n_1700_B(3414, "GL11.GL_DEPTH_BITS");
        q_2612_j.n_1700_B(3415, "GL11.GL_STENCIL_BITS");
        q_2612_j.n_1700_B(3416, "GL11.GL_ACCUM_RED_BITS");
        q_2612_j.n_1700_B(3417, "GL11.GL_ACCUM_GREEN_BITS");
        q_2612_j.n_1700_B(3418, "GL11.GL_ACCUM_BLUE_BITS");
        q_2612_j.n_1700_B(3419, "GL11.GL_ACCUM_ALPHA_BITS");
        q_2612_j.n_1700_B(3440, "GL11.GL_NAME_STACK_DEPTH");
        q_2612_j.n_1700_B(3456, "GL11.GL_AUTO_NORMAL");
        q_2612_j.n_1700_B(3472, "GL11.GL_MAP1_COLOR_4");
        q_2612_j.n_1700_B(3473, "GL11.GL_MAP1_INDEX");
        q_2612_j.n_1700_B(3474, "GL11.GL_MAP1_NORMAL");
        q_2612_j.n_1700_B(3475, "GL11.GL_MAP1_TEXTURE_COORD_1");
        q_2612_j.n_1700_B(3476, "GL11.GL_MAP1_TEXTURE_COORD_2");
        q_2612_j.n_1700_B(3477, "GL11.GL_MAP1_TEXTURE_COORD_3");
        q_2612_j.n_1700_B(3478, "GL11.GL_MAP1_TEXTURE_COORD_4");
        q_2612_j.n_1700_B(3479, "GL11.GL_MAP1_VERTEX_3");
        q_2612_j.n_1700_B(3480, "GL11.GL_MAP1_VERTEX_4");
        q_2612_j.n_1700_B(3504, "GL11.GL_MAP2_COLOR_4");
        q_2612_j.n_1700_B(3505, "GL11.GL_MAP2_INDEX");
        q_2612_j.n_1700_B(3506, "GL11.GL_MAP2_NORMAL");
        q_2612_j.n_1700_B(3507, "GL11.GL_MAP2_TEXTURE_COORD_1");
        q_2612_j.n_1700_B(3508, "GL11.GL_MAP2_TEXTURE_COORD_2");
        q_2612_j.n_1700_B(3509, "GL11.GL_MAP2_TEXTURE_COORD_3");
        q_2612_j.n_1700_B(3510, "GL11.GL_MAP2_TEXTURE_COORD_4");
        q_2612_j.n_1700_B(3511, "GL11.GL_MAP2_VERTEX_3");
        q_2612_j.n_1700_B(3512, "GL11.GL_MAP2_VERTEX_4");
        q_2612_j.n_1700_B(3536, "GL11.GL_MAP1_GRID_DOMAIN");
        q_2612_j.n_1700_B(3537, "GL11.GL_MAP1_GRID_SEGMENTS");
        q_2612_j.n_1700_B(3538, "GL11.GL_MAP2_GRID_DOMAIN");
        q_2612_j.n_1700_B(3539, "GL11.GL_MAP2_GRID_SEGMENTS");
        q_2612_j.n_1700_B(3552, "GL11.GL_TEXTURE_1D");
        q_2612_j.n_1700_B(3553, "GL11.GL_TEXTURE_2D");
        q_2612_j.n_1700_B(3568, "GL11.GL_FEEDBACK_BUFFER_POINTER");
        q_2612_j.n_1700_B(3569, "GL11.GL_FEEDBACK_BUFFER_SIZE");
        q_2612_j.n_1700_B(3570, "GL11.GL_FEEDBACK_BUFFER_TYPE");
        q_2612_j.n_1700_B(3571, "GL11.GL_SELECTION_BUFFER_POINTER");
        q_2612_j.n_1700_B(3572, "GL11.GL_SELECTION_BUFFER_SIZE");
        q_2612_j.n_1700_B(4096, "GL11.GL_TEXTURE_WIDTH");
        q_2612_j.n_1700_B(4097, "GL11.GL_TEXTURE_HEIGHT");
        q_2612_j.n_1700_B(4099, "GL11.GL_TEXTURE_INTERNAL_FORMAT");
        q_2612_j.n_1700_B(4100, "GL11.GL_TEXTURE_BORDER_COLOR");
        q_2612_j.n_1700_B(4101, "GL11.GL_TEXTURE_BORDER");
        q_2612_j.n_1700_B(4352, "GL11.GL_DONT_CARE");
        q_2612_j.n_1700_B(4353, "GL11.GL_FASTEST");
        q_2612_j.n_1700_B(4354, "GL11.GL_NICEST");
        q_2612_j.n_1700_B(16384, "GL11.GL_LIGHT0");
        q_2612_j.n_1700_B(16385, "GL11.GL_LIGHT1");
        q_2612_j.n_1700_B(16386, "GL11.GL_LIGHT2");
        q_2612_j.n_1700_B(16387, "GL11.GL_LIGHT3");
        q_2612_j.n_1700_B(16388, "GL11.GL_LIGHT4");
        q_2612_j.n_1700_B(16389, "GL11.GL_LIGHT5");
        q_2612_j.n_1700_B(16390, "GL11.GL_LIGHT6");
        q_2612_j.n_1700_B(16391, "GL11.GL_LIGHT7");
        q_2612_j.n_1700_B(4608, "GL11.GL_AMBIENT");
        q_2612_j.n_1700_B(4609, "GL11.GL_DIFFUSE");
        q_2612_j.n_1700_B(4610, "GL11.GL_SPECULAR");
        q_2612_j.n_1700_B(4611, "GL11.GL_POSITION");
        q_2612_j.n_1700_B(4612, "GL11.GL_SPOT_DIRECTION");
        q_2612_j.n_1700_B(4613, "GL11.GL_SPOT_EXPONENT");
        q_2612_j.n_1700_B(4614, "GL11.GL_SPOT_CUTOFF");
        q_2612_j.n_1700_B(4615, "GL11.GL_CONSTANT_ATTENUATION");
        q_2612_j.n_1700_B(4616, "GL11.GL_LINEAR_ATTENUATION");
        q_2612_j.n_1700_B(4617, "GL11.GL_QUADRATIC_ATTENUATION");
        q_2612_j.n_1700_B(4864, "GL11.GL_COMPILE");
        q_2612_j.n_1700_B(4865, "GL11.GL_COMPILE_AND_EXECUTE");
        q_2612_j.n_1700_B(5376, "GL11.GL_CLEAR");
        q_2612_j.n_1700_B(5377, "GL11.GL_AND");
        q_2612_j.n_1700_B(5378, "GL11.GL_AND_REVERSE");
        q_2612_j.n_1700_B(5379, "GL11.GL_COPY");
        q_2612_j.n_1700_B(5380, "GL11.GL_AND_INVERTED");
        q_2612_j.n_1700_B(5381, "GL11.GL_NOOP");
        q_2612_j.n_1700_B(5382, "GL11.GL_XOR");
        q_2612_j.n_1700_B(5383, "GL11.GL_OR");
        q_2612_j.n_1700_B(5384, "GL11.GL_NOR");
        q_2612_j.n_1700_B(5385, "GL11.GL_EQUIV");
        q_2612_j.n_1700_B(5386, "GL11.GL_INVERT");
        q_2612_j.n_1700_B(5387, "GL11.GL_OR_REVERSE");
        q_2612_j.n_1700_B(5388, "GL11.GL_COPY_INVERTED");
        q_2612_j.n_1700_B(5389, "GL11.GL_OR_INVERTED");
        q_2612_j.n_1700_B(5390, "GL11.GL_NAND");
        q_2612_j.n_1700_B(5391, "GL11.GL_SET");
        q_2612_j.n_1700_B(5632, "GL11.GL_EMISSION");
        q_2612_j.n_1700_B(5633, "GL11.GL_SHININESS");
        q_2612_j.n_1700_B(5634, "GL11.GL_AMBIENT_AND_DIFFUSE");
        q_2612_j.n_1700_B(5635, "GL11.GL_COLOR_INDEXES");
        q_2612_j.n_1700_B(5888, "GL11.GL_MODELVIEW");
        q_2612_j.n_1700_B(5889, "GL11.GL_PROJECTION");
        q_2612_j.n_1700_B(5890, "GL11.GL_TEXTURE");
        q_2612_j.n_1700_B(6144, "GL11.GL_COLOR");
        q_2612_j.n_1700_B(6145, "GL11.GL_DEPTH");
        q_2612_j.n_1700_B(6146, "GL11.GL_STENCIL");
        q_2612_j.n_1700_B(6400, "GL11.GL_COLOR_INDEX");
        q_2612_j.n_1700_B(6401, "GL11.GL_STENCIL_INDEX");
        q_2612_j.n_1700_B(6402, "GL11.GL_DEPTH_COMPONENT");
        q_2612_j.n_1700_B(6403, "GL11.GL_RED");
        q_2612_j.n_1700_B(6404, "GL11.GL_GREEN");
        q_2612_j.n_1700_B(6405, "GL11.GL_BLUE");
        q_2612_j.n_1700_B(6406, "GL11.GL_ALPHA");
        q_2612_j.n_1700_B(6407, "GL11.GL_RGB");
        q_2612_j.n_1700_B(6408, "GL11.GL_RGBA");
        q_2612_j.n_1700_B(6409, "GL11.GL_LUMINANCE");
        q_2612_j.n_1700_B(6410, "GL11.GL_LUMINANCE_ALPHA");
        q_2612_j.n_1700_B(6656, "GL11.GL_BITMAP");
        q_2612_j.n_1700_B(6912, "GL11.GL_POINT");
        q_2612_j.n_1700_B(6913, "GL11.GL_LINE");
        q_2612_j.n_1700_B(6914, "GL11.GL_FILL");
        q_2612_j.n_1700_B(7168, "GL11.GL_RENDER");
        q_2612_j.n_1700_B(7169, "GL11.GL_FEEDBACK");
        q_2612_j.n_1700_B(7170, "GL11.GL_SELECT");
        q_2612_j.n_1700_B(7424, "GL11.GL_FLAT");
        q_2612_j.n_1700_B(7425, "GL11.GL_SMOOTH");
        q_2612_j.n_1700_B(7680, "GL11.GL_KEEP");
        q_2612_j.n_1700_B(7681, "GL11.GL_REPLACE");
        q_2612_j.n_1700_B(7682, "GL11.GL_INCR");
        q_2612_j.n_1700_B(7683, "GL11.GL_DECR");
        q_2612_j.n_1700_B(7936, "GL11.GL_VENDOR");
        q_2612_j.n_1700_B(7937, "GL11.GL_RENDERER");
        q_2612_j.n_1700_B(7938, "GL11.GL_VERSION");
        q_2612_j.n_1700_B(7939, "GL11.GL_EXTENSIONS");
        q_2612_j.n_1700_B(8192, "GL11.GL_S");
        q_2612_j.n_1700_B(8193, "GL11.GL_T");
        q_2612_j.n_1700_B(8194, "GL11.GL_R");
        q_2612_j.n_1700_B(8195, "GL11.GL_Q");
        q_2612_j.n_1700_B(8448, "GL11.GL_MODULATE");
        q_2612_j.n_1700_B(8449, "GL11.GL_DECAL");
        q_2612_j.n_1700_B(8704, "GL11.GL_TEXTURE_ENV_MODE");
        q_2612_j.n_1700_B(8705, "GL11.GL_TEXTURE_ENV_COLOR");
        q_2612_j.n_1700_B(8960, "GL11.GL_TEXTURE_ENV");
        q_2612_j.n_1700_B(9216, "GL11.GL_EYE_LINEAR");
        q_2612_j.n_1700_B(9217, "GL11.GL_OBJECT_LINEAR");
        q_2612_j.n_1700_B(9218, "GL11.GL_SPHERE_MAP");
        q_2612_j.n_1700_B(9472, "GL11.GL_TEXTURE_GEN_MODE");
        q_2612_j.n_1700_B(9473, "GL11.GL_OBJECT_PLANE");
        q_2612_j.n_1700_B(9474, "GL11.GL_EYE_PLANE");
        q_2612_j.n_1700_B(9728, "GL11.GL_NEAREST");
        q_2612_j.n_1700_B(9729, "GL11.GL_LINEAR");
        q_2612_j.n_1700_B(9984, "GL11.GL_NEAREST_MIPMAP_NEAREST");
        q_2612_j.n_1700_B(9985, "GL11.GL_LINEAR_MIPMAP_NEAREST");
        q_2612_j.n_1700_B(9986, "GL11.GL_NEAREST_MIPMAP_LINEAR");
        q_2612_j.n_1700_B(9987, "GL11.GL_LINEAR_MIPMAP_LINEAR");
        q_2612_j.n_1700_B(10240, "GL11.GL_TEXTURE_MAG_FILTER");
        q_2612_j.n_1700_B(10241, "GL11.GL_TEXTURE_MIN_FILTER");
        q_2612_j.n_1700_B(10242, "GL11.GL_TEXTURE_WRAP_S");
        q_2612_j.n_1700_B(10243, "GL11.GL_TEXTURE_WRAP_T");
        q_2612_j.n_1700_B(10496, "GL11.GL_CLAMP");
        q_2612_j.n_1700_B(10497, "GL11.GL_REPEAT");
        q_2612_j.n_1700_B(-1, "GL11.GL_ALL_CLIENT_ATTRIB_BITS");
        q_2612_j.n_1700_B(32824, "GL11.GL_POLYGON_OFFSET_FACTOR");
        q_2612_j.n_1700_B(10752, "GL11.GL_POLYGON_OFFSET_UNITS");
        q_2612_j.n_1700_B(10753, "GL11.GL_POLYGON_OFFSET_POINT");
        q_2612_j.n_1700_B(10754, "GL11.GL_POLYGON_OFFSET_LINE");
        q_2612_j.n_1700_B(32823, "GL11.GL_POLYGON_OFFSET_FILL");
        q_2612_j.n_1700_B(32827, "GL11.GL_ALPHA4");
        q_2612_j.n_1700_B(32828, "GL11.GL_ALPHA8");
        q_2612_j.n_1700_B(32829, "GL11.GL_ALPHA12");
        q_2612_j.n_1700_B(32830, "GL11.GL_ALPHA16");
        q_2612_j.n_1700_B(32831, "GL11.GL_LUMINANCE4");
        q_2612_j.n_1700_B(32832, "GL11.GL_LUMINANCE8");
        q_2612_j.n_1700_B(32833, "GL11.GL_LUMINANCE12");
        q_2612_j.n_1700_B(32834, "GL11.GL_LUMINANCE16");
        q_2612_j.n_1700_B(32835, "GL11.GL_LUMINANCE4_ALPHA4");
        q_2612_j.n_1700_B(32836, "GL11.GL_LUMINANCE6_ALPHA2");
        q_2612_j.n_1700_B(32837, "GL11.GL_LUMINANCE8_ALPHA8");
        q_2612_j.n_1700_B(32838, "GL11.GL_LUMINANCE12_ALPHA4");
        q_2612_j.n_1700_B(32839, "GL11.GL_LUMINANCE12_ALPHA12");
        q_2612_j.n_1700_B(32840, "GL11.GL_LUMINANCE16_ALPHA16");
        q_2612_j.n_1700_B(32841, "GL11.GL_INTENSITY");
        q_2612_j.n_1700_B(32842, "GL11.GL_INTENSITY4");
        q_2612_j.n_1700_B(32843, "GL11.GL_INTENSITY8");
        q_2612_j.n_1700_B(32844, "GL11.GL_INTENSITY12");
        q_2612_j.n_1700_B(32845, "GL11.GL_INTENSITY16");
        q_2612_j.n_1700_B(10768, "GL11.GL_R3_G3_B2");
        q_2612_j.n_1700_B(32847, "GL11.GL_RGB4");
        q_2612_j.n_1700_B(32848, "GL11.GL_RGB5");
        q_2612_j.n_1700_B(32849, "GL11.GL_RGB8");
        q_2612_j.n_1700_B(32850, "GL11.GL_RGB10");
        q_2612_j.n_1700_B(32851, "GL11.GL_RGB12");
        q_2612_j.n_1700_B(32852, "GL11.GL_RGB16");
        q_2612_j.n_1700_B(32853, "GL11.GL_RGBA2");
        q_2612_j.n_1700_B(32854, "GL11.GL_RGBA4");
        q_2612_j.n_1700_B(32855, "GL11.GL_RGB5_A1");
        q_2612_j.n_1700_B(32856, "GL11.GL_RGBA8");
        q_2612_j.n_1700_B(32857, "GL11.GL_RGB10_A2");
        q_2612_j.n_1700_B(32858, "GL11.GL_RGBA12");
        q_2612_j.n_1700_B(32859, "GL11.GL_RGBA16");
        q_2612_j.n_1700_B(32860, "GL11.GL_TEXTURE_RED_SIZE");
        q_2612_j.n_1700_B(32861, "GL11.GL_TEXTURE_GREEN_SIZE");
        q_2612_j.n_1700_B(32862, "GL11.GL_TEXTURE_BLUE_SIZE");
        q_2612_j.n_1700_B(32863, "GL11.GL_TEXTURE_ALPHA_SIZE");
        q_2612_j.n_1700_B(32864, "GL11.GL_TEXTURE_LUMINANCE_SIZE");
        q_2612_j.n_1700_B(32865, "GL11.GL_TEXTURE_INTENSITY_SIZE");
        q_2612_j.n_1700_B(32867, "GL11.GL_PROXY_TEXTURE_1D");
        q_2612_j.n_1700_B(32868, "GL11.GL_PROXY_TEXTURE_2D");
        q_2612_j.n_1700_B(32870, "GL11.GL_TEXTURE_PRIORITY");
        q_2612_j.n_1700_B(32871, "GL11.GL_TEXTURE_RESIDENT");
        q_2612_j.n_1700_B(32872, "GL11.GL_TEXTURE_BINDING_1D");
        q_2612_j.n_1700_B(32873, "GL11.GL_TEXTURE_BINDING_2D");
        q_2612_j.n_1700_B(32884, "GL11.GL_VERTEX_ARRAY");
        q_2612_j.n_1700_B(32885, "GL11.GL_NORMAL_ARRAY");
        q_2612_j.n_1700_B(32886, "GL11.GL_COLOR_ARRAY");
        q_2612_j.n_1700_B(32887, "GL11.GL_INDEX_ARRAY");
        q_2612_j.n_1700_B(32888, "GL11.GL_TEXTURE_COORD_ARRAY");
        q_2612_j.n_1700_B(32889, "GL11.GL_EDGE_FLAG_ARRAY");
        q_2612_j.n_1700_B(32890, "GL11.GL_VERTEX_ARRAY_SIZE");
        q_2612_j.n_1700_B(32891, "GL11.GL_VERTEX_ARRAY_TYPE");
        q_2612_j.n_1700_B(32892, "GL11.GL_VERTEX_ARRAY_STRIDE");
        q_2612_j.n_1700_B(32894, "GL11.GL_NORMAL_ARRAY_TYPE");
        q_2612_j.n_1700_B(32895, "GL11.GL_NORMAL_ARRAY_STRIDE");
        q_2612_j.n_1700_B(32897, "GL11.GL_COLOR_ARRAY_SIZE");
        q_2612_j.n_1700_B(32898, "GL11.GL_COLOR_ARRAY_TYPE");
        q_2612_j.n_1700_B(32899, "GL11.GL_COLOR_ARRAY_STRIDE");
        q_2612_j.n_1700_B(32901, "GL11.GL_INDEX_ARRAY_TYPE");
        q_2612_j.n_1700_B(32902, "GL11.GL_INDEX_ARRAY_STRIDE");
        q_2612_j.n_1700_B(32904, "GL11.GL_TEXTURE_COORD_ARRAY_SIZE");
        q_2612_j.n_1700_B(32905, "GL11.GL_TEXTURE_COORD_ARRAY_TYPE");
        q_2612_j.n_1700_B(32906, "GL11.GL_TEXTURE_COORD_ARRAY_STRIDE");
        q_2612_j.n_1700_B(32908, "GL11.GL_EDGE_FLAG_ARRAY_STRIDE");
        q_2612_j.n_1700_B(32910, "GL11.GL_VERTEX_ARRAY_POINTER");
        q_2612_j.n_1700_B(32911, "GL11.GL_NORMAL_ARRAY_POINTER");
        q_2612_j.n_1700_B(32912, "GL11.GL_COLOR_ARRAY_POINTER");
        q_2612_j.n_1700_B(32913, "GL11.GL_INDEX_ARRAY_POINTER");
        q_2612_j.n_1700_B(32914, "GL11.GL_TEXTURE_COORD_ARRAY_POINTER");
        q_2612_j.n_1700_B(32915, "GL11.GL_EDGE_FLAG_ARRAY_POINTER");
        q_2612_j.n_1700_B(10784, "GL11.GL_V2F");
        q_2612_j.n_1700_B(10785, "GL11.GL_V3F");
        q_2612_j.n_1700_B(10786, "GL11.GL_C4UB_V2F");
        q_2612_j.n_1700_B(10787, "GL11.GL_C4UB_V3F");
        q_2612_j.n_1700_B(10788, "GL11.GL_C3F_V3F");
        q_2612_j.n_1700_B(10789, "GL11.GL_N3F_V3F");
        q_2612_j.n_1700_B(10790, "GL11.GL_C4F_N3F_V3F");
        q_2612_j.n_1700_B(10791, "GL11.GL_T2F_V3F");
        q_2612_j.n_1700_B(10792, "GL11.GL_T4F_V4F");
        q_2612_j.n_1700_B(10793, "GL11.GL_T2F_C4UB_V3F");
        q_2612_j.n_1700_B(10794, "GL11.GL_T2F_C3F_V3F");
        q_2612_j.n_1700_B(10795, "GL11.GL_T2F_N3F_V3F");
        q_2612_j.n_1700_B(10796, "GL11.GL_T2F_C4F_N3F_V3F");
        q_2612_j.n_1700_B(10797, "GL11.GL_T4F_C4F_N3F_V4F");
        q_2612_j.n_1700_B(3057, "GL11.GL_LOGIC_OP");
        q_2612_j.n_1700_B(4099, "GL11.GL_TEXTURE_COMPONENTS");
        q_2612_j.n_1700_B(32874, "GL12.GL_TEXTURE_BINDING_3D");
        q_2612_j.n_1700_B(32875, "GL12.GL_PACK_SKIP_IMAGES");
        q_2612_j.n_1700_B(32876, "GL12.GL_PACK_IMAGE_HEIGHT");
        q_2612_j.n_1700_B(32877, "GL12.GL_UNPACK_SKIP_IMAGES");
        q_2612_j.n_1700_B(32878, "GL12.GL_UNPACK_IMAGE_HEIGHT");
        q_2612_j.n_1700_B(32879, "GL12.GL_TEXTURE_3D");
        q_2612_j.n_1700_B(32880, "GL12.GL_PROXY_TEXTURE_3D");
        q_2612_j.n_1700_B(32881, "GL12.GL_TEXTURE_DEPTH");
        q_2612_j.n_1700_B(32882, "GL12.GL_TEXTURE_WRAP_R");
        q_2612_j.n_1700_B(32883, "GL12.GL_MAX_3D_TEXTURE_SIZE");
        q_2612_j.n_1700_B(32992, "GL12.GL_BGR");
        q_2612_j.n_1700_B(32993, "GL12.GL_BGRA");
        q_2612_j.n_1700_B(32818, "GL12.GL_UNSIGNED_BYTE_3_3_2");
        q_2612_j.n_1700_B(33634, "GL12.GL_UNSIGNED_BYTE_2_3_3_REV");
        q_2612_j.n_1700_B(33635, "GL12.GL_UNSIGNED_SHORT_5_6_5");
        q_2612_j.n_1700_B(33636, "GL12.GL_UNSIGNED_SHORT_5_6_5_REV");
        q_2612_j.n_1700_B(32819, "GL12.GL_UNSIGNED_SHORT_4_4_4_4");
        q_2612_j.n_1700_B(33637, "GL12.GL_UNSIGNED_SHORT_4_4_4_4_REV");
        q_2612_j.n_1700_B(32820, "GL12.GL_UNSIGNED_SHORT_5_5_5_1");
        q_2612_j.n_1700_B(33638, "GL12.GL_UNSIGNED_SHORT_1_5_5_5_REV");
        q_2612_j.n_1700_B(32821, "GL12.GL_UNSIGNED_INT_8_8_8_8");
        q_2612_j.n_1700_B(33639, "GL12.GL_UNSIGNED_INT_8_8_8_8_REV");
        q_2612_j.n_1700_B(32822, "GL12.GL_UNSIGNED_INT_10_10_10_2");
        q_2612_j.n_1700_B(33640, "GL12.GL_UNSIGNED_INT_2_10_10_10_REV");
        q_2612_j.n_1700_B(32826, "GL12.GL_RESCALE_NORMAL");
        q_2612_j.n_1700_B(33272, "GL12.GL_LIGHT_MODEL_COLOR_CONTROL");
        q_2612_j.n_1700_B(33273, "GL12.GL_SINGLE_COLOR");
        q_2612_j.n_1700_B(33274, "GL12.GL_SEPARATE_SPECULAR_COLOR");
        q_2612_j.n_1700_B(33071, "GL12.GL_CLAMP_TO_EDGE");
        q_2612_j.n_1700_B(33082, "GL12.GL_TEXTURE_MIN_LOD");
        q_2612_j.n_1700_B(33083, "GL12.GL_TEXTURE_MAX_LOD");
        q_2612_j.n_1700_B(33084, "GL12.GL_TEXTURE_BASE_LEVEL");
        q_2612_j.n_1700_B(33085, "GL12.GL_TEXTURE_MAX_LEVEL");
        q_2612_j.n_1700_B(33000, "GL12.GL_MAX_ELEMENTS_VERTICES");
        q_2612_j.n_1700_B(33001, "GL12.GL_MAX_ELEMENTS_INDICES");
        q_2612_j.n_1700_B(33901, "GL12.GL_ALIASED_POINT_SIZE_RANGE");
        q_2612_j.n_1700_B(33902, "GL12.GL_ALIASED_LINE_WIDTH_RANGE");
        q_2612_j.n_1700_B(33984, "GL13.GL_TEXTURE0");
        q_2612_j.n_1700_B(33985, "GL13.GL_TEXTURE1");
        q_2612_j.n_1700_B(33986, "GL13.GL_TEXTURE2");
        q_2612_j.n_1700_B(33987, "GL13.GL_TEXTURE3");
        q_2612_j.n_1700_B(33988, "GL13.GL_TEXTURE4");
        q_2612_j.n_1700_B(33989, "GL13.GL_TEXTURE5");
        q_2612_j.n_1700_B(33990, "GL13.GL_TEXTURE6");
        q_2612_j.n_1700_B(33991, "GL13.GL_TEXTURE7");
        q_2612_j.n_1700_B(33992, "GL13.GL_TEXTURE8");
        q_2612_j.n_1700_B(33993, "GL13.GL_TEXTURE9");
        q_2612_j.n_1700_B(33994, "GL13.GL_TEXTURE10");
        q_2612_j.n_1700_B(33995, "GL13.GL_TEXTURE11");
        q_2612_j.n_1700_B(33996, "GL13.GL_TEXTURE12");
        q_2612_j.n_1700_B(33997, "GL13.GL_TEXTURE13");
        q_2612_j.n_1700_B(33998, "GL13.GL_TEXTURE14");
        q_2612_j.n_1700_B(33999, "GL13.GL_TEXTURE15");
        q_2612_j.n_1700_B(34000, "GL13.GL_TEXTURE16");
        q_2612_j.n_1700_B(34001, "GL13.GL_TEXTURE17");
        q_2612_j.n_1700_B(34002, "GL13.GL_TEXTURE18");
        q_2612_j.n_1700_B(34003, "GL13.GL_TEXTURE19");
        q_2612_j.n_1700_B(34004, "GL13.GL_TEXTURE20");
        q_2612_j.n_1700_B(34005, "GL13.GL_TEXTURE21");
        q_2612_j.n_1700_B(34006, "GL13.GL_TEXTURE22");
        q_2612_j.n_1700_B(34007, "GL13.GL_TEXTURE23");
        q_2612_j.n_1700_B(34008, "GL13.GL_TEXTURE24");
        q_2612_j.n_1700_B(34009, "GL13.GL_TEXTURE25");
        q_2612_j.n_1700_B(34010, "GL13.GL_TEXTURE26");
        q_2612_j.n_1700_B(34011, "GL13.GL_TEXTURE27");
        q_2612_j.n_1700_B(34012, "GL13.GL_TEXTURE28");
        q_2612_j.n_1700_B(34013, "GL13.GL_TEXTURE29");
        q_2612_j.n_1700_B(34014, "GL13.GL_TEXTURE30");
        q_2612_j.n_1700_B(34015, "GL13.GL_TEXTURE31");
        q_2612_j.n_1700_B(34016, "GL13.GL_ACTIVE_TEXTURE");
        q_2612_j.n_1700_B(34017, "GL13.GL_CLIENT_ACTIVE_TEXTURE");
        q_2612_j.n_1700_B(34018, "GL13.GL_MAX_TEXTURE_UNITS");
        q_2612_j.n_1700_B(34065, "GL13.GL_NORMAL_MAP");
        q_2612_j.n_1700_B(34066, "GL13.GL_REFLECTION_MAP");
        q_2612_j.n_1700_B(34067, "GL13.GL_TEXTURE_CUBE_MAP");
        q_2612_j.n_1700_B(34068, "GL13.GL_TEXTURE_BINDING_CUBE_MAP");
        q_2612_j.n_1700_B(34069, "GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_X");
        q_2612_j.n_1700_B(34070, "GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_X");
        q_2612_j.n_1700_B(34071, "GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Y");
        q_2612_j.n_1700_B(34072, "GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Y");
        q_2612_j.n_1700_B(34073, "GL13.GL_TEXTURE_CUBE_MAP_POSITIVE_Z");
        q_2612_j.n_1700_B(34074, "GL13.GL_TEXTURE_CUBE_MAP_NEGATIVE_Z");
        q_2612_j.n_1700_B(34075, "GL13.GL_PROXY_TEXTURE_CUBE_MAP");
        q_2612_j.n_1700_B(34076, "GL13.GL_MAX_CUBE_MAP_TEXTURE_SIZE");
        q_2612_j.n_1700_B(34025, "GL13.GL_COMPRESSED_ALPHA");
        q_2612_j.n_1700_B(34026, "GL13.GL_COMPRESSED_LUMINANCE");
        q_2612_j.n_1700_B(34027, "GL13.GL_COMPRESSED_LUMINANCE_ALPHA");
        q_2612_j.n_1700_B(34028, "GL13.GL_COMPRESSED_INTENSITY");
        q_2612_j.n_1700_B(34029, "GL13.GL_COMPRESSED_RGB");
        q_2612_j.n_1700_B(34030, "GL13.GL_COMPRESSED_RGBA");
        q_2612_j.n_1700_B(34031, "GL13.GL_TEXTURE_COMPRESSION_HINT");
        q_2612_j.n_1700_B(34464, "GL13.GL_TEXTURE_COMPRESSED_IMAGE_SIZE");
        q_2612_j.n_1700_B(34465, "GL13.GL_TEXTURE_COMPRESSED");
        q_2612_j.n_1700_B(34466, "GL13.GL_NUM_COMPRESSED_TEXTURE_FORMATS");
        q_2612_j.n_1700_B(34467, "GL13.GL_COMPRESSED_TEXTURE_FORMATS");
        q_2612_j.n_1700_B(32925, "GL13.GL_MULTISAMPLE");
        q_2612_j.n_1700_B(32926, "GL13.GL_SAMPLE_ALPHA_TO_COVERAGE");
        q_2612_j.n_1700_B(32927, "GL13.GL_SAMPLE_ALPHA_TO_ONE");
        q_2612_j.n_1700_B(32928, "GL13.GL_SAMPLE_COVERAGE");
        q_2612_j.n_1700_B(32936, "GL13.GL_SAMPLE_BUFFERS");
        q_2612_j.n_1700_B(32937, "GL13.GL_SAMPLES");
        q_2612_j.n_1700_B(32938, "GL13.GL_SAMPLE_COVERAGE_VALUE");
        q_2612_j.n_1700_B(32939, "GL13.GL_SAMPLE_COVERAGE_INVERT");
        q_2612_j.n_1700_B(34019, "GL13.GL_TRANSPOSE_MODELVIEW_MATRIX");
        q_2612_j.n_1700_B(34020, "GL13.GL_TRANSPOSE_PROJECTION_MATRIX");
        q_2612_j.n_1700_B(34021, "GL13.GL_TRANSPOSE_TEXTURE_MATRIX");
        q_2612_j.n_1700_B(34022, "GL13.GL_TRANSPOSE_COLOR_MATRIX");
        q_2612_j.n_1700_B(34160, "GL13.GL_COMBINE");
        q_2612_j.n_1700_B(34161, "GL13.GL_COMBINE_RGB");
        q_2612_j.n_1700_B(34162, "GL13.GL_COMBINE_ALPHA");
        q_2612_j.n_1700_B(34176, "GL13.GL_SOURCE0_RGB");
        q_2612_j.n_1700_B(34177, "GL13.GL_SOURCE1_RGB");
        q_2612_j.n_1700_B(34178, "GL13.GL_SOURCE2_RGB");
        q_2612_j.n_1700_B(34184, "GL13.GL_SOURCE0_ALPHA");
        q_2612_j.n_1700_B(34185, "GL13.GL_SOURCE1_ALPHA");
        q_2612_j.n_1700_B(34186, "GL13.GL_SOURCE2_ALPHA");
        q_2612_j.n_1700_B(34192, "GL13.GL_OPERAND0_RGB");
        q_2612_j.n_1700_B(34193, "GL13.GL_OPERAND1_RGB");
        q_2612_j.n_1700_B(34194, "GL13.GL_OPERAND2_RGB");
        q_2612_j.n_1700_B(34200, "GL13.GL_OPERAND0_ALPHA");
        q_2612_j.n_1700_B(34201, "GL13.GL_OPERAND1_ALPHA");
        q_2612_j.n_1700_B(34202, "GL13.GL_OPERAND2_ALPHA");
        q_2612_j.n_1700_B(34163, "GL13.GL_RGB_SCALE");
        q_2612_j.n_1700_B(34164, "GL13.GL_ADD_SIGNED");
        q_2612_j.n_1700_B(34165, "GL13.GL_INTERPOLATE");
        q_2612_j.n_1700_B(34023, "GL13.GL_SUBTRACT");
        q_2612_j.n_1700_B(34166, "GL13.GL_CONSTANT");
        q_2612_j.n_1700_B(34167, "GL13.GL_PRIMARY_COLOR");
        q_2612_j.n_1700_B(34168, "GL13.GL_PREVIOUS");
        q_2612_j.n_1700_B(34478, "GL13.GL_DOT3_RGB");
        q_2612_j.n_1700_B(34479, "GL13.GL_DOT3_RGBA");
        q_2612_j.n_1700_B(33069, "GL13.GL_CLAMP_TO_BORDER");
        q_2612_j.n_1700_B(33169, "GL14.GL_GENERATE_MIPMAP");
        q_2612_j.n_1700_B(33170, "GL14.GL_GENERATE_MIPMAP_HINT");
        q_2612_j.n_1700_B(33189, "GL14.GL_DEPTH_COMPONENT16");
        q_2612_j.n_1700_B(33190, "GL14.GL_DEPTH_COMPONENT24");
        q_2612_j.n_1700_B(33191, "GL14.GL_DEPTH_COMPONENT32");
        q_2612_j.n_1700_B(34890, "GL14.GL_TEXTURE_DEPTH_SIZE");
        q_2612_j.n_1700_B(34891, "GL14.GL_DEPTH_TEXTURE_MODE");
        q_2612_j.n_1700_B(34892, "GL14.GL_TEXTURE_COMPARE_MODE");
        q_2612_j.n_1700_B(34893, "GL14.GL_TEXTURE_COMPARE_FUNC");
        q_2612_j.n_1700_B(34894, "GL14.GL_COMPARE_R_TO_TEXTURE");
        q_2612_j.n_1700_B(33872, "GL14.GL_FOG_COORDINATE_SOURCE");
        q_2612_j.n_1700_B(33873, "GL14.GL_FOG_COORDINATE");
        q_2612_j.n_1700_B(33874, "GL14.GL_FRAGMENT_DEPTH");
        q_2612_j.n_1700_B(33875, "GL14.GL_CURRENT_FOG_COORDINATE");
        q_2612_j.n_1700_B(33876, "GL14.GL_FOG_COORDINATE_ARRAY_TYPE");
        q_2612_j.n_1700_B(33877, "GL14.GL_FOG_COORDINATE_ARRAY_STRIDE");
        q_2612_j.n_1700_B(33878, "GL14.GL_FOG_COORDINATE_ARRAY_POINTER");
        q_2612_j.n_1700_B(33879, "GL14.GL_FOG_COORDINATE_ARRAY");
        q_2612_j.n_1700_B(33062, "GL14.GL_POINT_SIZE_MIN");
        q_2612_j.n_1700_B(33063, "GL14.GL_POINT_SIZE_MAX");
        q_2612_j.n_1700_B(33064, "GL14.GL_POINT_FADE_THRESHOLD_SIZE");
        q_2612_j.n_1700_B(33065, "GL14.GL_POINT_DISTANCE_ATTENUATION");
        q_2612_j.n_1700_B(33880, "GL14.GL_COLOR_SUM");
        q_2612_j.n_1700_B(33881, "GL14.GL_CURRENT_SECONDARY_COLOR");
        q_2612_j.n_1700_B(33882, "GL14.GL_SECONDARY_COLOR_ARRAY_SIZE");
        q_2612_j.n_1700_B(33883, "GL14.GL_SECONDARY_COLOR_ARRAY_TYPE");
        q_2612_j.n_1700_B(33884, "GL14.GL_SECONDARY_COLOR_ARRAY_STRIDE");
        q_2612_j.n_1700_B(33885, "GL14.GL_SECONDARY_COLOR_ARRAY_POINTER");
        q_2612_j.n_1700_B(33886, "GL14.GL_SECONDARY_COLOR_ARRAY");
        q_2612_j.n_1700_B(32968, "GL14.GL_BLEND_DST_RGB");
        q_2612_j.n_1700_B(32969, "GL14.GL_BLEND_SRC_RGB");
        q_2612_j.n_1700_B(32970, "GL14.GL_BLEND_DST_ALPHA");
        q_2612_j.n_1700_B(32971, "GL14.GL_BLEND_SRC_ALPHA");
        q_2612_j.n_1700_B(34055, "GL14.GL_INCR_WRAP");
        q_2612_j.n_1700_B(34056, "GL14.GL_DECR_WRAP");
        q_2612_j.n_1700_B(34048, "GL14.GL_TEXTURE_FILTER_CONTROL");
        q_2612_j.n_1700_B(34049, "GL14.GL_TEXTURE_LOD_BIAS");
        q_2612_j.n_1700_B(34045, "GL14.GL_MAX_TEXTURE_LOD_BIAS");
        q_2612_j.n_1700_B(33648, "GL14.GL_MIRRORED_REPEAT");
        q_2612_j.n_1700_B(32773, "ARBImaging.GL_BLEND_COLOR");
        q_2612_j.n_1700_B(32777, "ARBImaging.GL_BLEND_EQUATION");
        q_2612_j.n_1700_B(32774, "GL14.GL_FUNC_ADD");
        q_2612_j.n_1700_B(32778, "GL14.GL_FUNC_SUBTRACT");
        q_2612_j.n_1700_B(32779, "GL14.GL_FUNC_REVERSE_SUBTRACT");
        q_2612_j.n_1700_B(32775, "GL14.GL_MIN");
        q_2612_j.n_1700_B(32776, "GL14.GL_MAX");
        q_2612_j.n_1700_B(34962, "GL15.GL_ARRAY_BUFFER");
        q_2612_j.n_1700_B(34963, "GL15.GL_ELEMENT_ARRAY_BUFFER");
        q_2612_j.n_1700_B(34964, "GL15.GL_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34965, "GL15.GL_ELEMENT_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34966, "GL15.GL_VERTEX_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34967, "GL15.GL_NORMAL_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34968, "GL15.GL_COLOR_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34969, "GL15.GL_INDEX_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34970, "GL15.GL_TEXTURE_COORD_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34971, "GL15.GL_EDGE_FLAG_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34972, "GL15.GL_SECONDARY_COLOR_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34973, "GL15.GL_FOG_COORDINATE_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34974, "GL15.GL_WEIGHT_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(34975, "GL15.GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING");
        q_2612_j.n_1700_B(35040, "GL15.GL_STREAM_DRAW");
        q_2612_j.n_1700_B(35041, "GL15.GL_STREAM_READ");
        q_2612_j.n_1700_B(35042, "GL15.GL_STREAM_COPY");
        q_2612_j.n_1700_B(35044, "GL15.GL_STATIC_DRAW");
        q_2612_j.n_1700_B(35045, "GL15.GL_STATIC_READ");
        q_2612_j.n_1700_B(35046, "GL15.GL_STATIC_COPY");
        q_2612_j.n_1700_B(35048, "GL15.GL_DYNAMIC_DRAW");
        q_2612_j.n_1700_B(35049, "GL15.GL_DYNAMIC_READ");
        q_2612_j.n_1700_B(35050, "GL15.GL_DYNAMIC_COPY");
        q_2612_j.n_1700_B(35000, "GL15.GL_READ_ONLY");
        q_2612_j.n_1700_B(35001, "GL15.GL_WRITE_ONLY");
        q_2612_j.n_1700_B(35002, "GL15.GL_READ_WRITE");
        q_2612_j.n_1700_B(34660, "GL15.GL_BUFFER_SIZE");
        q_2612_j.n_1700_B(34661, "GL15.GL_BUFFER_USAGE");
        q_2612_j.n_1700_B(35003, "GL15.GL_BUFFER_ACCESS");
        q_2612_j.n_1700_B(35004, "GL15.GL_BUFFER_MAPPED");
        q_2612_j.n_1700_B(35005, "GL15.GL_BUFFER_MAP_POINTER");
        q_2612_j.n_1700_B(34138, "NVFogDistance.GL_FOG_DISTANCE_MODE_NV");
        q_2612_j.n_1700_B(34139, "NVFogDistance.GL_EYE_RADIAL_NV");
        q_2612_j.n_1700_B(34140, "NVFogDistance.GL_EYE_PLANE_ABSOLUTE_NV");
    }
}



