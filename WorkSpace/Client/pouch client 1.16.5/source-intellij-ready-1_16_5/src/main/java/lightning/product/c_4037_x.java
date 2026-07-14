/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.glfw.GLFWErrorCallbackI
 */
package lightning.product;

import com.google.common.collect.Queues;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import lightning.product.D_1098_v;
import lightning.product.M_1336_P;
import lightning.product.N_2525_X;
import lightning.product.P_3084_J;
import lightning.product.V_4423_d;
import lightning.product.X_933_l;
import lightning.product.MinecraftClient;
import lightning.product.g_164_R;
import lightning.product.l_3747_P;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallbackI;

public class c_4037_x {
    private static final Logger J_1907_R = LogManager.getLogger();
    private static final ConcurrentLinkedQueue<N_2525_X> R_4764_Y = Queues.newConcurrentLinkedQueue();
    private static final l_3747_P G_564_y = new l_3747_P();
    public static final float n_1700_B = 0.1f;
    private static final int P_1922_E = 1024;
    private static boolean u_1723_Y;
    private static Thread v_4262_N;
    private static Thread w_1484_f;
    private static int t_148_a;
    private static boolean s_956_w;
    private static double u_2550_I;

    public static void n_1700_B() {
        if (w_1484_f != null || v_4262_N == Thread.currentThread()) {
            throw new IllegalStateException("Could not initialize render thread");
        }
        w_1484_f = Thread.currentThread();
    }

    public static boolean J_1907_R() {
        return Thread.currentThread() == w_1484_f;
    }

    public static boolean R_4764_Y() {
        return s_956_w || c_4037_x.J_1907_R();
    }

    public static void n_1700_B(boolean p_initGameThread_0_) {
        boolean flag;
        boolean bl = flag = w_1484_f == Thread.currentThread();
        if (v_4262_N != null || w_1484_f == null || flag == p_initGameThread_0_) {
            throw new IllegalStateException("Could not initialize tick thread");
        }
        v_4262_N = Thread.currentThread();
    }

    public static boolean G_564_y() {
        return true;
    }

    public static boolean P_1922_E() {
        return s_956_w || c_4037_x.G_564_y();
    }

    public static void n_1700_B(Supplier<Boolean> p_assertThread_0_) {
        if (!p_assertThread_0_.get().booleanValue()) {
            throw new IllegalStateException("Rendersystem called from wrong thread");
        }
    }

    public static boolean u_1723_Y() {
        return true;
    }

    public static void n_1700_B(N_2525_X p_recordRenderCall_0_) {
        R_4764_Y.add(p_recordRenderCall_0_);
    }

    public static void n_1700_B(long p_flipFrame_0_) {
        GLFW.glfwPollEvents();
        c_4037_x.v_4262_N();
        l_3747_P.n_1700_B().R_4764_Y().w_1484_f();
        GLFW.glfwSwapBuffers((long)p_flipFrame_0_);
        GLFW.glfwPollEvents();
    }

    public static void v_4262_N() {
        u_1723_Y = true;
        while (!R_4764_Y.isEmpty()) {
            N_2525_X irendercall = R_4764_Y.poll();
            irendercall.execute();
        }
        u_1723_Y = false;
    }

    public static void n_1700_B(int p_limitDisplayFPS_0_) {
        double d0 = u_2550_I + 1.0 / (double)p_limitDisplayFPS_0_;
        double d1 = GLFW.glfwGetTime();
        while (d1 < d0) {
            GLFW.glfwWaitEventsTimeout((double)(d0 - d1));
            d1 = GLFW.glfwGetTime();
        }
        u_2550_I = d1;
    }

    @Deprecated
    public static void w_1484_f() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B();
    }

    @Deprecated
    public static void t_148_a() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R();
    }

    @Deprecated
    public static void s_956_w() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.R_4764_Y();
    }

    @Deprecated
    public static void u_2550_I() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.G_564_y();
    }

    @Deprecated
    public static void M_588_G() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.P_1922_E();
    }

    @Deprecated
    public static void n_1700_B(int p_alphaFunc_0_, float p_alphaFunc_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_alphaFunc_0_, p_alphaFunc_1_);
    }

    @Deprecated
    public static void P_4830_p() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.u_1723_Y();
    }

    @Deprecated
    public static void h_1847_R() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.v_4262_N();
    }

    @Deprecated
    public static void Q_4569_t() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.w_1484_f();
    }

    @Deprecated
    public static void M_182_A() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.t_148_a();
    }

    @Deprecated
    public static void n_1700_B(int p_colorMaterial_0_, int p_colorMaterial_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_colorMaterial_0_, p_colorMaterial_1_);
    }

    @Deprecated
    public static void n_1700_B(float p_normal3f_0_, float p_normal3f_1_, float p_normal3f_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_normal3f_0_, p_normal3f_1_, p_normal3f_2_);
    }

    public static void t_1786_h() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.M_588_G();
    }

    public static void multiplayerClientSuggestionProvider() {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        X_933_l.P_4830_p();
    }

    public static void n_1700_B(int p_enableScissor_0_, int p_enableScissor_1_, int p_enableScissor_2_, int p_enableScissor_3_) {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        X_933_l.u_2550_I();
        X_933_l.n_1700_B(p_enableScissor_0_, p_enableScissor_1_, p_enableScissor_2_, p_enableScissor_3_);
    }

    public static void w_1457_N() {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        X_933_l.s_956_w();
    }

    public static void J_1907_R(int p_depthFunc_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_depthFunc_0_);
    }

    public static void J_1907_R(boolean p_depthMask_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_depthMask_0_);
    }

    public static void Y_601_j() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Q_4569_t();
    }

    public static void Y_259_p() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.h_1847_R();
    }

    public static void n_1700_B(X_933_l.t_1786_h p_blendFunc_0_, X_933_l.s_956_w p_blendFunc_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_blendFunc_0_.M_182_A, p_blendFunc_1_.Q_4569_t);
    }

    public static void J_1907_R(int p_blendFunc_0_, int p_blendFunc_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_blendFunc_0_, p_blendFunc_1_);
    }

    public static void n_1700_B(X_933_l.t_1786_h p_blendFuncSeparate_0_, X_933_l.s_956_w p_blendFuncSeparate_1_, X_933_l.t_1786_h p_blendFuncSeparate_2_, X_933_l.s_956_w p_blendFuncSeparate_3_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_blendFuncSeparate_0_.M_182_A, p_blendFuncSeparate_1_.Q_4569_t, p_blendFuncSeparate_2_.M_182_A, p_blendFuncSeparate_3_.Q_4569_t);
    }

    public static void J_1907_R(int p_blendFuncSeparate_0_, int p_blendFuncSeparate_1_, int p_blendFuncSeparate_2_, int p_blendFuncSeparate_3_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_blendFuncSeparate_0_, p_blendFuncSeparate_1_, p_blendFuncSeparate_2_, p_blendFuncSeparate_3_);
    }

    public static void R_4764_Y(int p_blendEquation_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.R_4764_Y(p_blendEquation_0_);
    }

    public static void n_1700_B(float p_blendColor_0_, float p_blendColor_1_, float p_blendColor_2_, float p_blendColor_3_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_blendColor_0_, p_blendColor_1_, p_blendColor_2_, p_blendColor_3_);
    }

    @Deprecated
    public static void Q_2552_b() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.c_3005_b();
    }

    @Deprecated
    public static void C_2741_M() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.H_2857_Y();
    }

    @Deprecated
    public static void n_1700_B(X_933_l.u_2550_I p_fogMode_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Q_4569_t(p_fogMode_0_.G_564_y);
    }

    @Deprecated
    public static void G_564_y(int p_fogMode_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Q_4569_t(p_fogMode_0_);
    }

    @Deprecated
    public static void n_1700_B(float p_fogDensity_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_fogDensity_0_);
    }

    @Deprecated
    public static void J_1907_R(float p_fogStart_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_fogStart_0_);
    }

    @Deprecated
    public static void R_4764_Y(float p_fogEnd_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.R_4764_Y(p_fogEnd_0_);
    }

    @Deprecated
    public static void n_1700_B(int p_fog_0_, float p_fog_1_, float p_fog_2_, float p_fog_3_, float p_fog_4_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_fog_0_, new float[]{p_fog_1_, p_fog_2_, p_fog_3_, p_fog_4_});
    }

    @Deprecated
    public static void R_4764_Y(int p_fogi_0_, int p_fogi_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.M_588_G(p_fogi_0_, p_fogi_1_);
    }

    public static void k_2293_S() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.A_4115_X();
    }

    public static void q_2307_F() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Y_1740_V();
    }

    public static void G_564_y(int p_polygonMode_0_, int p_polygonMode_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.P_4830_p(p_polygonMode_0_, p_polygonMode_1_);
    }

    public static void Z_875_P() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.t_4043_B();
    }

    public static void c_3005_b() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.x_607_J();
    }

    public static void H_2857_Y() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.e_4240_b();
    }

    public static void A_4115_X() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_3318_d();
    }

    public static void n_1700_B(float p_polygonOffset_0_, float p_polygonOffset_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_polygonOffset_0_, p_polygonOffset_1_);
    }

    public static void Y_1740_V() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.d_2427_y();
    }

    public static void t_4043_B() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.z_1737_N();
    }

    public static void n_1700_B(X_933_l.h_1847_R p_logicOp_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.M_182_A(p_logicOp_0_.t_1786_h);
    }

    public static void P_1922_E(int p_activeTexture_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.t_1786_h(p_activeTexture_0_);
    }

    public static void x_607_J() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.v_4276_D();
    }

    public static void e_4240_b() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.d_2461_k();
    }

    public static void n_1700_B(int p_texParameter_0_, int p_texParameter_1_, int p_texParameter_2_) {
        X_933_l.J_1907_R(p_texParameter_0_, p_texParameter_1_, p_texParameter_2_);
    }

    public static void u_1723_Y(int p_deleteTexture_0_) {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        X_933_l.multiplayerClientSuggestionProvider(p_deleteTexture_0_);
    }

    public static void v_4262_N(int p_bindTexture_0_) {
        X_933_l.w_1457_N(p_bindTexture_0_);
    }

    @Deprecated
    public static void w_1484_f(int p_shadeModel_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Y_601_j(p_shadeModel_0_);
    }

    @Deprecated
    public static void n_3318_d() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.T_2506_i();
    }

    @Deprecated
    public static void d_2427_y() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.q_4610_l();
    }

    public static void R_4764_Y(int p_viewport_0_, int p_viewport_1_, int p_viewport_2_, int p_viewport_3_) {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        X_933_l.G_564_y(p_viewport_0_, p_viewport_1_, p_viewport_2_, p_viewport_3_);
    }

    public static void n_1700_B(boolean p_colorMask_0_, boolean p_colorMask_1_, boolean p_colorMask_2_, boolean p_colorMask_3_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_colorMask_0_, p_colorMask_1_, p_colorMask_2_, p_colorMask_3_);
    }

    public static void J_1907_R(int p_stencilFunc_0_, int p_stencilFunc_1_, int p_stencilFunc_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.G_564_y(p_stencilFunc_0_, p_stencilFunc_1_, p_stencilFunc_2_);
    }

    public static void t_148_a(int p_stencilMask_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Y_259_p(p_stencilMask_0_);
    }

    public static void R_4764_Y(int p_stencilOp_0_, int p_stencilOp_1_, int p_stencilOp_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.P_1922_E(p_stencilOp_0_, p_stencilOp_1_, p_stencilOp_2_);
    }

    public static void n_1700_B(double p_clearDepth_0_) {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        X_933_l.n_1700_B(p_clearDepth_0_);
    }

    public static void J_1907_R(float p_clearColor_0_, float p_clearColor_1_, float p_clearColor_2_, float p_clearColor_3_) {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        X_933_l.J_1907_R(p_clearColor_0_, p_clearColor_1_, p_clearColor_2_, p_clearColor_3_);
    }

    public static void s_956_w(int p_clearStencil_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Q_2552_b(p_clearStencil_0_);
    }

    public static void n_1700_B(int p_clear_0_, boolean p_clear_1_) {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        X_933_l.n_1700_B(p_clear_0_, p_clear_1_);
    }

    @Deprecated
    public static void u_2550_I(int p_matrixMode_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.C_2741_M(p_matrixMode_0_);
    }

    @Deprecated
    public static void z_1737_N() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.z_4693_k();
    }

    @Deprecated
    public static void v_4276_D() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.g_221_o();
    }

    @Deprecated
    public static void d_2461_k() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.e_2887_G();
    }

    @Deprecated
    public static void n_1700_B(double p_ortho_0_, double p_ortho_2_, double p_ortho_4_, double p_ortho_6_, double p_ortho_8_, double p_ortho_10_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_ortho_0_, p_ortho_2_, p_ortho_4_, p_ortho_6_, p_ortho_8_, p_ortho_10_);
    }

    @Deprecated
    public static void R_4764_Y(float p_rotatef_0_, float p_rotatef_1_, float p_rotatef_2_, float p_rotatef_3_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.R_4764_Y(p_rotatef_0_, p_rotatef_1_, p_rotatef_2_, p_rotatef_3_);
    }

    @Deprecated
    public static void J_1907_R(float p_scalef_0_, float p_scalef_1_, float p_scalef_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_scalef_0_, p_scalef_1_, p_scalef_2_);
    }

    @Deprecated
    public static void n_1700_B(double p_scaled_0_, double p_scaled_2_, double p_scaled_4_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_scaled_0_, p_scaled_2_, p_scaled_4_);
    }

    @Deprecated
    public static void R_4764_Y(float p_translatef_0_, float p_translatef_1_, float p_translatef_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.R_4764_Y(p_translatef_0_, p_translatef_1_, p_translatef_2_);
    }

    @Deprecated
    public static void J_1907_R(double p_translated_0_, double p_translated_2_, double p_translated_4_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_translated_0_, p_translated_2_, p_translated_4_);
    }

    @Deprecated
    public static void n_1700_B(D_1098_v p_multMatrix_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_multMatrix_0_);
    }

    @Deprecated
    public static void G_564_y(float p_color4f_0_, float p_color4f_1_, float p_color4f_2_, float p_color4f_3_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.G_564_y(p_color4f_0_, p_color4f_1_, p_color4f_2_, p_color4f_3_);
    }

    @Deprecated
    public static void G_564_y(float p_color3f_0_, float p_color3f_1_, float p_color3f_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.G_564_y(p_color3f_0_, p_color3f_1_, p_color3f_2_, 1.0f);
    }

    @Deprecated
    public static void G_624_v() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.B_1668_F();
    }

    public static void G_564_y(int p_drawArrays_0_, int p_drawArrays_1_, int p_drawArrays_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.u_1723_Y(p_drawArrays_0_, p_drawArrays_1_, p_drawArrays_2_);
    }

    public static void G_564_y(float p_lineWidth_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.G_564_y(p_lineWidth_0_);
    }

    public static void P_1922_E(int p_pixelStore_0_, int p_pixelStore_1_) {
        c_4037_x.n_1700_B(c_4037_x::P_1922_E);
        X_933_l.h_1847_R(p_pixelStore_0_, p_pixelStore_1_);
    }

    public static void J_1907_R(int p_pixelTransfer_0_, float p_pixelTransfer_1_) {
        X_933_l.J_1907_R(p_pixelTransfer_0_, p_pixelTransfer_1_);
    }

    public static void n_1700_B(int p_readPixels_0_, int p_readPixels_1_, int p_readPixels_2_, int p_readPixels_3_, int p_readPixels_4_, int p_readPixels_5_, ByteBuffer p_readPixels_6_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_readPixels_0_, p_readPixels_1_, p_readPixels_2_, p_readPixels_3_, p_readPixels_4_, p_readPixels_5_, p_readPixels_6_);
    }

    public static void n_1700_B(int p_getString_0_, Consumer<String> p_getString_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        p_getString_1_.accept(X_933_l.H_2857_Y(p_getString_0_));
    }

    public static String T_2506_i() {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        return String.format("LWJGL version %s", g_164_R.J_1907_R());
    }

    public static String q_4610_l() {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        return g_164_R.n_1700_B();
    }

    public static LongSupplier z_4693_k() {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        return g_164_R.R_4764_Y();
    }

    public static void J_1907_R(int p_initRenderer_0_, boolean p_initRenderer_1_) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        g_164_R.n_1700_B(p_initRenderer_0_, p_initRenderer_1_);
    }

    public static void n_1700_B(GLFWErrorCallbackI p_setErrorCallback_0_) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        g_164_R.n_1700_B(p_setErrorCallback_0_);
    }

    public static void M_588_G(int p_renderCrosshair_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        g_164_R.n_1700_B(p_renderCrosshair_0_, true, true, true);
    }

    public static void g_221_o() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        g_164_R.G_564_y();
    }

    @Deprecated
    public static void n_1700_B(int p_glMultiTexCoord2f_0_, float p_glMultiTexCoord2f_1_, float p_glMultiTexCoord2f_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_glMultiTexCoord2f_0_, p_glMultiTexCoord2f_1_, p_glMultiTexCoord2f_2_);
    }

    public static String e_2887_G() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        return g_164_R.P_1922_E();
    }

    public static void G_564_y(int p_setupDefaultState_0_, int p_setupDefaultState_1_, int p_setupDefaultState_2_, int p_setupDefaultState_3_) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        X_933_l.v_4276_D();
        X_933_l.Y_601_j(7425);
        X_933_l.n_1700_B(1.0);
        X_933_l.P_4830_p();
        X_933_l.J_1907_R(515);
        X_933_l.P_1922_E();
        X_933_l.n_1700_B(516, 0.1f);
        X_933_l.C_2741_M(5889);
        X_933_l.z_4693_k();
        X_933_l.C_2741_M(5888);
        X_933_l.G_564_y(p_setupDefaultState_0_, p_setupDefaultState_1_, p_setupDefaultState_2_, p_setupDefaultState_3_);
    }

    public static int B_1668_F() {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        if (t_148_a == -1) {
            int i = X_933_l.A_4115_X(3379);
            for (int j = Math.max(32768, i); j >= 1024; j >>= 1) {
                X_933_l.n_1700_B(32868, 0, 6408, j, j, 0, 6408, 5121, null);
                int k = X_933_l.R_4764_Y(32868, 0, 4096);
                if (k == 0) continue;
                t_148_a = j;
                return j;
            }
            t_148_a = Math.max(i, 1024);
            J_1907_R.info("Failed to determine maximum texture size by probing, trying GL_MAX_TEXTURE_SIZE = {}", (Object)t_148_a);
        }
        return t_148_a;
    }

    public static void n_1700_B(int p_glBindBuffer_0_, Supplier<Integer> p_glBindBuffer_1_) {
        X_933_l.v_4262_N(p_glBindBuffer_0_, p_glBindBuffer_1_.get());
    }

    public static void n_1700_B(int p_glBufferData_0_, ByteBuffer p_glBufferData_1_, int p_glBufferData_2_) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        X_933_l.n_1700_B(p_glBufferData_0_, p_glBufferData_1_, p_glBufferData_2_);
    }

    public static void P_4830_p(int p_glDeleteBuffers_0_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.s_956_w(p_glDeleteBuffers_0_);
    }

    public static void u_1723_Y(int p_glUniform1i_0_, int p_glUniform1i_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.u_1723_Y(p_glUniform1i_0_, p_glUniform1i_1_);
    }

    public static void n_1700_B(int p_glUniform1_0_, IntBuffer p_glUniform1_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_glUniform1_0_, p_glUniform1_1_);
    }

    public static void J_1907_R(int p_glUniform2_0_, IntBuffer p_glUniform2_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_glUniform2_0_, p_glUniform2_1_);
    }

    public static void R_4764_Y(int p_glUniform3_0_, IntBuffer p_glUniform3_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.R_4764_Y(p_glUniform3_0_, p_glUniform3_1_);
    }

    public static void G_564_y(int p_glUniform4_0_, IntBuffer p_glUniform4_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.G_564_y(p_glUniform4_0_, p_glUniform4_1_);
    }

    public static void n_1700_B(int p_glUniform1_0_, FloatBuffer p_glUniform1_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_glUniform1_0_, p_glUniform1_1_);
    }

    public static void J_1907_R(int p_glUniform2_0_, FloatBuffer p_glUniform2_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.R_4764_Y(p_glUniform2_0_, p_glUniform2_1_);
    }

    public static void R_4764_Y(int p_glUniform3_0_, FloatBuffer p_glUniform3_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.G_564_y(p_glUniform3_0_, p_glUniform3_1_);
    }

    public static void G_564_y(int p_glUniform4_0_, FloatBuffer p_glUniform4_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.P_1922_E(p_glUniform4_0_, p_glUniform4_1_);
    }

    public static void n_1700_B(int p_glUniformMatrix2_0_, boolean p_glUniformMatrix2_1_, FloatBuffer p_glUniformMatrix2_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_glUniformMatrix2_0_, p_glUniformMatrix2_1_, p_glUniformMatrix2_2_);
    }

    public static void J_1907_R(int p_glUniformMatrix3_0_, boolean p_glUniformMatrix3_1_, FloatBuffer p_glUniformMatrix3_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_glUniformMatrix3_0_, p_glUniformMatrix3_1_, p_glUniformMatrix3_2_);
    }

    public static void R_4764_Y(int p_glUniformMatrix4_0_, boolean p_glUniformMatrix4_1_, FloatBuffer p_glUniformMatrix4_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.R_4764_Y(p_glUniformMatrix4_0_, p_glUniformMatrix4_1_, p_glUniformMatrix4_2_);
    }

    public static void g_164_R() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Y_259_p();
    }

    public static void X_933_l() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Q_2552_b();
    }

    public static void n_1700_B(IntSupplier p_setupOverlayColor_0_, int p_setupOverlayColor_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.u_2550_I(p_setupOverlayColor_0_.getAsInt(), p_setupOverlayColor_1_);
    }

    public static void Z_976_R() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.C_2741_M();
    }

    public static void n_1700_B(M_1336_P p_setupLevelDiffuseLighting_0_, M_1336_P p_setupLevelDiffuseLighting_1_, D_1098_v p_setupLevelDiffuseLighting_2_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_setupLevelDiffuseLighting_0_, p_setupLevelDiffuseLighting_1_, p_setupLevelDiffuseLighting_2_);
    }

    public static void n_1700_B(M_1336_P p_setupGuiFlatDiffuseLighting_0_, M_1336_P p_setupGuiFlatDiffuseLighting_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.n_1700_B(p_setupGuiFlatDiffuseLighting_0_, p_setupGuiFlatDiffuseLighting_1_);
    }

    public static void J_1907_R(M_1336_P p_setupGui3DDiffuseLighting_0_, M_1336_P p_setupGui3DDiffuseLighting_1_) {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.J_1907_R(p_setupGui3DDiffuseLighting_0_, p_setupGui3DDiffuseLighting_1_);
    }

    public static void H_1990_U() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.Z_875_P();
    }

    public static void N_2525_X() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.k_2293_S();
    }

    public static void c_4037_x() {
        c_4037_x.n_1700_B(c_4037_x::G_564_y);
        X_933_l.q_2307_F();
    }

    public static void g_2268_R() {
        s_956_w = true;
    }

    public static void T_3594_S() {
        s_956_w = false;
        if (!R_4764_Y.isEmpty()) {
            c_4037_x.v_4262_N();
        }
        if (!R_4764_Y.isEmpty()) {
            throw new IllegalStateException("Recorded to render queue during initialization");
        }
    }

    public static void n_1700_B(Consumer<Integer> p_glGenBuffers_0_) {
        if (!c_4037_x.J_1907_R()) {
            c_4037_x.n_1700_B(() -> p_glGenBuffers_0_.accept(X_933_l.t_1786_h()));
        } else {
            p_glGenBuffers_0_.accept(X_933_l.t_1786_h());
        }
    }

    public static l_3747_P D_4792_h() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return G_564_y;
    }

    public static void s_2632_s() {
        c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.h_1847_R);
    }

    public static void l_1233_K() {
        c_4037_x.n_1700_B(516, 0.1f);
    }

    @Deprecated
    public static void n_1700_B(Runnable p_runAsFancy_0_) {
        boolean flag = MinecraftClient.c_3005_b();
        if (!flag) {
            p_runAsFancy_0_.run();
        } else {
            V_4423_d gamesettings = MinecraftClient.A_4115_X().P_4830_p;
            P_3084_J graphicsfanciness = gamesettings.u_1723_Y;
            gamesettings.u_1723_Y = P_3084_J.J_1907_R;
            p_runAsFancy_0_.run();
            gamesettings.u_1723_Y = graphicsfanciness;
        }
    }

    static {
        t_148_a = -1;
        u_2550_I = Double.MIN_VALUE;
    }
}



