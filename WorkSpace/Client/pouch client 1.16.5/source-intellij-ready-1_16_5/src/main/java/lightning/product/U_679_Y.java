/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.sun.jna.Function
 *  com.sun.jna.Memory
 *  com.sun.jna.Platform
 *  com.sun.jna.Pointer
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.PointerBuffer
 *  org.lwjgl.glfw.Callbacks
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.glfw.GLFWErrorCallback
 *  org.lwjgl.glfw.GLFWErrorCallbackI
 *  org.lwjgl.glfw.GLFWImage
 *  org.lwjgl.glfw.GLFWImage$Buffer
 *  org.lwjgl.glfw.GLFWNativeWin32
 *  org.lwjgl.opengl.GL
 *  org.lwjgl.stb.STBImage
 *  org.lwjgl.system.MemoryStack
 *  org.lwjgl.system.MemoryUtil
 *  org.lwjgl.util.tinyfd.TinyFileDialogs
 */
package lightning.product;

import com.sun.jna.Function;
import com.sun.jna.Memory;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Optional;
import java.util.function.BiConsumer;
import javax.annotation.Nullable;
import lightning.product.J_1565_t;
import lightning.product.J_1851_y;
import lightning.product.N_1091_Y;
import lightning.product.N_1972_P;
import lightning.product.Q_4113_P;
import lightning.product.c_4037_x;
import lightning.product.g_164_R;
import lightning.product.q_1272_r;
import lightning.product.q_570_v;
import lightning.product.w_4886_q;
import net.minecraftforge.fml.loading.progress.EarlyProgressVisualization;
import net.optifine.Config;
import net.optifine.reflect.Reflector;
import net.optifine.util.TextureUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.PointerBuffer;
import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.glfw.GLFWImage;
import org.lwjgl.glfw.GLFWNativeWin32;
import org.lwjgl.opengl.GL;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

public final class U_679_Y
implements AutoCloseable {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final GLFWErrorCallback J_1907_R = GLFWErrorCallback.create(this::n_1700_B);
    private final J_1851_y R_4764_Y;
    private final w_4886_q G_564_y;
    private final long P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;
    private int w_1484_f;
    private int t_148_a;
    private Optional<J_1565_t> s_956_w;
    private boolean u_2550_I;
    private boolean M_588_G;
    private int P_4830_p;
    private int h_1847_R;
    private int Q_4569_t;
    private int M_182_A;
    private int t_1786_h;
    private int multiplayerClientSuggestionProvider;
    private int w_1457_N;
    private int Y_601_j;
    private double Y_259_p;
    private String Q_2552_b = "";
    private boolean C_2741_M;
    private int k_2293_S;
    private boolean q_2307_F;
    private boolean Z_875_P;

    public U_679_Y(J_1851_y mc, w_4886_q monitonHandler, q_570_v size, @Nullable String videoModeName, String titleIn) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        this.G_564_y = monitonHandler;
        this.C_2741_M();
        this.n_1700_B("Pre startup");
        this.R_4764_Y = mc;
        Optional<J_1565_t> optional = J_1565_t.n_1700_B(videoModeName);
        this.s_956_w = optional.isPresent() ? optional : (size.R_4764_Y.isPresent() && size.G_564_y.isPresent() ? Optional.of(new J_1565_t(size.R_4764_Y.getAsInt(), size.G_564_y.getAsInt(), 8, 8, 8, 60)) : Optional.empty());
        this.M_588_G = this.u_2550_I = size.P_1922_E;
        N_1091_Y monitor = monitonHandler.n_1700_B(GLFW.glfwGetPrimaryMonitor());
        this.Q_4569_t = size.n_1700_B > 0 ? size.n_1700_B : 1;
        this.w_1484_f = this.Q_4569_t;
        this.M_182_A = size.J_1907_R > 0 ? size.J_1907_R : 1;
        this.t_148_a = this.M_182_A;
        GLFW.glfwDefaultWindowHints();
        if (Config.isAntialiasing()) {
            GLFW.glfwWindowHint((int)135181, (int)Config.getAntialiasingLevel());
        }
        GLFW.glfwWindowHint((int)139265, (int)196609);
        GLFW.glfwWindowHint((int)139275, (int)221185);
        GLFW.glfwWindowHint((int)139266, (int)2);
        GLFW.glfwWindowHint((int)139267, (int)0);
        GLFW.glfwWindowHint((int)139272, (int)0);
        long i = 0L;
        if (Reflector.EarlyProgressVisualization_handOffWindow.exists()) {
            Object object = Reflector.getFieldValue(Reflector.EarlyProgressVisualization_INSTANCE);
            i = Reflector.callLong(object, Reflector.EarlyProgressVisualization_handOffWindow, () -> this.Q_4569_t, () -> this.M_182_A, () -> titleIn, () -> this.u_2550_I && monitor != null ? monitor.u_1723_Y() : 0L);
            if (Config.isAntialiasing()) {
                GLFW.glfwDestroyWindow((long)i);
                i = 0L;
            }
        }
        this.P_1922_E = i != 0L ? i : GLFW.glfwCreateWindow((int)this.Q_4569_t, (int)this.M_182_A, (CharSequence)titleIn, (long)(this.u_2550_I && monitor != null ? monitor.u_1723_Y() : 0L), (long)0L);
        this.Q_2552_b();
        if (monitor != null) {
            J_1565_t videomode = monitor.n_1700_B(this.u_2550_I ? this.s_956_w : Optional.empty());
            this.u_1723_Y = this.P_4830_p = monitor.R_4764_Y() + videomode.n_1700_B() / 2 - this.Q_4569_t / 2;
            this.v_4262_N = this.h_1847_R = monitor.G_564_y() + videomode.J_1907_R() / 2 - this.M_182_A / 2;
        } else {
            int[] aint1 = new int[1];
            int[] aint = new int[1];
            GLFW.glfwGetWindowPos((long)this.P_1922_E, (int[])aint1, (int[])aint);
            this.u_1723_Y = this.P_4830_p = aint1[0];
            this.v_4262_N = this.h_1847_R = aint[0];
        }
        GLFW.glfwMakeContextCurrent((long)this.P_1922_E);
        GL.createCapabilities();
        this.q_2307_F();
        this.k_2293_S();
        GLFW.glfwSetFramebufferSizeCallback((long)this.P_1922_E, this::J_1907_R);
        GLFW.glfwSetWindowPosCallback((long)this.P_1922_E, this::n_1700_B);
        GLFW.glfwSetWindowSizeCallback((long)this.P_1922_E, this::R_4764_Y);
        GLFW.glfwSetWindowFocusCallback((long)this.P_1922_E, this::n_1700_B);
        GLFW.glfwSetCursorEnterCallback((long)this.P_1922_E, this::J_1907_R);
    }

    private void Q_2552_b() {
        this.G_564_y(true);
    }

    public void n_1700_B(boolean dark) {
        this.G_564_y(dark);
    }

    private void G_564_y(boolean dark) {
        try {
            if (!Platform.isWindows()) {
                return;
            }
            long hwnd = GLFWNativeWin32.glfwGetWin32Window((long)this.P_1922_E);
            if (hwnd == 0L) {
                return;
            }
            Pointer hWnd = new Pointer(hwnd);
            Function dwmSetWindowAttribute = Function.getFunction((String)"dwmapi", (String)"DwmSetWindowAttribute", (int)63);
            Memory pvAttribute = new Memory(4L);
            pvAttribute.setInt(0L, dark ? 1 : 0);
            int result = ((Number)dwmSetWindowAttribute.invoke(Integer.TYPE, new Object[]{hWnd, 20, pvAttribute, 4})).intValue();
            if (result != 0) {
                dwmSetWindowAttribute.invoke(Integer.TYPE, new Object[]{hWnd, 19, pvAttribute, 4});
            }
            Function sendMessage = Function.getFunction((String)"user32", (String)"SendMessageW", (int)63);
            int WM_NCACTIVATE = 134;
            sendMessage.invoke(Integer.TYPE, new Object[]{hWnd, WM_NCACTIVATE, 0, 0});
            sendMessage.invoke(Integer.TYPE, new Object[]{hWnd, WM_NCACTIVATE, 1, 0});
        }
        catch (Exception e) {
            n_1700_B.warn("Failed to set title bar theme", (Throwable)e);
        }
    }

    public int n_1700_B() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        return g_164_R.n_1700_B(this);
    }

    public boolean J_1907_R() {
        return g_164_R.J_1907_R(this);
    }

    public static void n_1700_B(BiConsumer<Integer, String> glfwErrorConsumer) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            PointerBuffer pointerbuffer = memorystack.mallocPointer(1);
            int i = GLFW.glfwGetError((PointerBuffer)pointerbuffer);
            if (i != 0) {
                long j = pointerbuffer.get();
                String s = j == 0L ? "" : MemoryUtil.memUTF8((long)j);
                glfwErrorConsumer.accept(i, s);
            }
        }
    }

    public void n_1700_B(InputStream iconStream16X, InputStream iconStream32X) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        try (MemoryStack memorystack = MemoryStack.stackPush();){
            if (iconStream16X == null) {
                throw new FileNotFoundException("icons/icon_16x16.png");
            }
            if (iconStream32X == null) {
                throw new FileNotFoundException("icons/icon_32x32.png");
            }
            IntBuffer intbuffer = memorystack.mallocInt(1);
            IntBuffer intbuffer1 = memorystack.mallocInt(1);
            IntBuffer intbuffer2 = memorystack.mallocInt(1);
            GLFWImage.Buffer buffer = GLFWImage.mallocStack((int)2, (MemoryStack)memorystack);
            ByteBuffer bytebuffer = this.n_1700_B(iconStream16X, intbuffer, intbuffer1, intbuffer2);
            if (bytebuffer == null) {
                throw new IllegalStateException("Could not load icon: " + STBImage.stbi_failure_reason());
            }
            buffer.position(0);
            buffer.width(intbuffer.get(0));
            buffer.height(intbuffer1.get(0));
            buffer.pixels(bytebuffer);
            ByteBuffer bytebuffer1 = this.n_1700_B(iconStream32X, intbuffer, intbuffer1, intbuffer2);
            if (bytebuffer1 == null) {
                throw new IllegalStateException("Could not load icon: " + STBImage.stbi_failure_reason());
            }
            buffer.position(1);
            buffer.width(intbuffer.get(0));
            buffer.height(intbuffer1.get(0));
            buffer.pixels(bytebuffer1);
            buffer.position(0);
            GLFW.glfwSetWindowIcon((long)this.P_1922_E, (GLFWImage.Buffer)buffer);
            STBImage.stbi_image_free((ByteBuffer)bytebuffer);
            STBImage.stbi_image_free((ByteBuffer)bytebuffer1);
        }
        catch (IOException ioexception1) {
            n_1700_B.error("Couldn't set icon", (Throwable)ioexception1);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Nullable
    private ByteBuffer n_1700_B(InputStream textureStream, IntBuffer x, IntBuffer y, IntBuffer channelInFile) throws IOException {
        ByteBuffer bytebuffer1;
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        ByteBuffer bytebuffer = null;
        try {
            bytebuffer = N_1972_P.n_1700_B(textureStream);
            ((Buffer)bytebuffer).rewind();
            bytebuffer1 = STBImage.stbi_load_from_memory((ByteBuffer)bytebuffer, (IntBuffer)x, (IntBuffer)y, (IntBuffer)channelInFile, (int)0);
        }
        finally {
            if (bytebuffer != null) {
                MemoryUtil.memFree((Buffer)bytebuffer);
            }
        }
        return bytebuffer1;
    }

    public void n_1700_B(String renderPhaseIn) {
        this.Q_2552_b = renderPhaseIn;
        if (renderPhaseIn.equals("Startup")) {
            TextureUtils.registerTickableTextures();
        }
    }

    private void C_2741_M() {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        GLFW.glfwSetErrorCallback(U_679_Y::J_1907_R);
    }

    private static void J_1907_R(int error, long description) {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        String s = "GLFW error " + error + ": " + MemoryUtil.memUTF8((long)description);
        TinyFileDialogs.tinyfd_messageBox((CharSequence)"Minecraft", (CharSequence)(s + ".\n\nPlease make sure you have up-to-date drivers (see aka.ms/mcdriver for instructions)."), (CharSequence)"ok", (CharSequence)"error", (boolean)false);
        throw new n_1700_B(s);
    }

    public void n_1700_B(int error, long description) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        String s = MemoryUtil.memUTF8((long)description);
        n_1700_B.error("########## GL ERROR ##########");
        n_1700_B.error("@ {}", (Object)this.Q_2552_b);
        n_1700_B.error("{}: {}", (Object)error, (Object)s);
    }

    public void R_4764_Y() {
        GLFWErrorCallback glfwerrorcallback = GLFW.glfwSetErrorCallback((GLFWErrorCallbackI)this.J_1907_R);
        if (glfwerrorcallback != null) {
            glfwerrorcallback.free();
        }
        TextureUtils.registerResourceListener();
    }

    public void J_1907_R(boolean vsyncEnabled) {
        c_4037_x.n_1700_B(c_4037_x::R_4764_Y);
        this.q_2307_F = vsyncEnabled;
        GLFW.glfwSwapInterval((int)(vsyncEnabled ? 1 : 0));
    }

    @Override
    public void close() {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        this.Z_875_P = true;
        Callbacks.glfwFreeCallbacks((long)this.P_1922_E);
        this.J_1907_R.close();
        GLFW.glfwDestroyWindow((long)this.P_1922_E);
        GLFW.glfwTerminate();
    }

    private void n_1700_B(long windowPointer, int windowXIn, int windowYIn) {
        this.P_4830_p = windowXIn;
        this.h_1847_R = windowYIn;
    }

    private void J_1907_R(long windowPointer, int framebufferWidth, int framebufferHeight) {
        if (windowPointer == this.P_1922_E) {
            int i = this.u_2550_I();
            int j = this.M_588_G();
            if (framebufferWidth != 0 && framebufferHeight != 0) {
                this.t_1786_h = framebufferWidth;
                this.multiplayerClientSuggestionProvider = framebufferHeight;
                if (this.u_2550_I() != i || this.M_588_G() != j) {
                    this.R_4764_Y.u_2550_I();
                }
            }
        }
    }

    private void k_2293_S() {
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        int[] aint = new int[1];
        int[] aint1 = new int[1];
        GLFW.glfwGetFramebufferSize((long)this.P_1922_E, (int[])aint, (int[])aint1);
        this.t_1786_h = aint[0];
        this.multiplayerClientSuggestionProvider = aint1[0];
        if (this.multiplayerClientSuggestionProvider == 0 || this.t_1786_h == 0) {
            EarlyProgressVisualization.INSTANCE.updateFBSize(p_lambda$updateFramebufferSize$4_1_ -> {
                this.t_1786_h = p_lambda$updateFramebufferSize$4_1_;
            }, p_lambda$updateFramebufferSize$5_1_ -> {
                this.multiplayerClientSuggestionProvider = p_lambda$updateFramebufferSize$5_1_;
            });
        }
    }

    private void R_4764_Y(long windowPointer, int windowWidthIn, int windowHeightIn) {
        this.Q_4569_t = windowWidthIn;
        this.M_182_A = windowHeightIn;
    }

    private void n_1700_B(long windowPointer, boolean hasFocus) {
        if (windowPointer == this.P_1922_E) {
            this.R_4764_Y.G_564_y(hasFocus);
        }
    }

    private void J_1907_R(long window, boolean ignoreFirst) {
        if (ignoreFirst) {
            this.R_4764_Y.M_588_G();
        }
    }

    public void n_1700_B(int limit) {
        this.k_2293_S = limit;
    }

    public int G_564_y() {
        return this.k_2293_S;
    }

    public void P_1922_E() {
        c_4037_x.n_1700_B(this.P_1922_E);
        if (this.u_2550_I != this.M_588_G) {
            this.M_588_G = this.u_2550_I;
            this.P_1922_E(this.q_2307_F);
        }
    }

    public Optional<J_1565_t> u_1723_Y() {
        return this.s_956_w;
    }

    public void n_1700_B(Optional<J_1565_t> fullscreenModeIn) {
        boolean flag = !fullscreenModeIn.equals(this.s_956_w);
        this.s_956_w = fullscreenModeIn;
        if (flag) {
            this.C_2741_M = true;
        }
    }

    public void v_4262_N() {
        if (this.u_2550_I && this.C_2741_M) {
            this.C_2741_M = false;
            this.q_2307_F();
            this.R_4764_Y.u_2550_I();
        }
    }

    private void q_2307_F() {
        boolean flag;
        c_4037_x.n_1700_B(c_4037_x::u_1723_Y);
        boolean bl = flag = GLFW.glfwGetWindowMonitor((long)this.P_1922_E) != 0L;
        if (this.u_2550_I) {
            N_1091_Y monitor = this.G_564_y.n_1700_B(this);
            if (monitor == null) {
                n_1700_B.warn("Failed to find suitable monitor for fullscreen mode");
                this.u_2550_I = false;
            } else {
                J_1565_t videomode = monitor.n_1700_B(this.s_956_w);
                if (!flag) {
                    this.u_1723_Y = this.P_4830_p;
                    this.v_4262_N = this.h_1847_R;
                    this.w_1484_f = this.Q_4569_t;
                    this.t_148_a = this.M_182_A;
                }
                this.P_4830_p = 0;
                this.h_1847_R = 0;
                this.Q_4569_t = videomode.n_1700_B();
                this.M_182_A = videomode.J_1907_R();
                GLFW.glfwSetWindowMonitor((long)this.P_1922_E, (long)monitor.u_1723_Y(), (int)this.P_4830_p, (int)this.h_1847_R, (int)this.Q_4569_t, (int)this.M_182_A, (int)videomode.u_1723_Y());
            }
        } else {
            this.P_4830_p = this.u_1723_Y;
            this.h_1847_R = this.v_4262_N;
            this.Q_4569_t = this.w_1484_f;
            this.M_182_A = this.t_148_a;
            GLFW.glfwSetWindowMonitor((long)this.P_1922_E, (long)0L, (int)this.P_4830_p, (int)this.h_1847_R, (int)this.Q_4569_t, (int)this.M_182_A, (int)-1);
        }
    }

    public void w_1484_f() {
        this.u_2550_I = !this.u_2550_I;
    }

    private void P_1922_E(boolean vsyncEnabled) {
        c_4037_x.n_1700_B(c_4037_x::J_1907_R);
        try {
            this.q_2307_F();
            this.R_4764_Y.u_2550_I();
            this.J_1907_R(vsyncEnabled);
            this.P_1922_E();
        }
        catch (Exception exception) {
            n_1700_B.error("Couldn't toggle fullscreen", (Throwable)exception);
        }
    }

    public int n_1700_B(int guiScaleIn, boolean forceUnicode) {
        int i;
        for (i = 1; i != guiScaleIn && i < this.t_1786_h && i < this.multiplayerClientSuggestionProvider && this.t_1786_h / (i + 1) >= 320 && this.multiplayerClientSuggestionProvider / (i + 1) >= 240; ++i) {
        }
        if (forceUnicode && i % 2 != 0) {
            ++i;
        }
        return i;
    }

    public void n_1700_B(double scaleFactor) {
        this.Y_259_p = scaleFactor;
        int i = (int)((double)this.t_1786_h / scaleFactor);
        this.w_1457_N = (double)this.t_1786_h / scaleFactor > (double)i ? i + 1 : i;
        int j = (int)((double)this.multiplayerClientSuggestionProvider / scaleFactor);
        this.Y_601_j = (double)this.multiplayerClientSuggestionProvider / scaleFactor > (double)j ? j + 1 : j;
    }

    public void J_1907_R(String title) {
        GLFW.glfwSetWindowTitle((long)this.P_1922_E, (CharSequence)title);
    }

    public long t_148_a() {
        return this.P_1922_E;
    }

    public boolean s_956_w() {
        return this.u_2550_I;
    }

    public int u_2550_I() {
        return this.t_1786_h;
    }

    public int M_588_G() {
        return this.multiplayerClientSuggestionProvider;
    }

    public int P_4830_p() {
        return this.Q_4569_t;
    }

    public int h_1847_R() {
        return this.M_182_A;
    }

    public int Q_4569_t() {
        return this.w_1457_N;
    }

    public int M_182_A() {
        return this.Y_601_j;
    }

    public int t_1786_h() {
        return this.P_4830_p;
    }

    public int multiplayerClientSuggestionProvider() {
        return this.h_1847_R;
    }

    public double w_1457_N() {
        return this.Y_259_p;
    }

    @Nullable
    public N_1091_Y Y_601_j() {
        return this.G_564_y.n_1700_B(this);
    }

    public void R_4764_Y(boolean valueIn) {
        Q_4113_P.n_1700_B(this.P_1922_E, valueIn);
    }

    public void n_1700_B(int p_resizeFramebuffer_1_, int p_resizeFramebuffer_2_) {
        this.J_1907_R(this.P_1922_E, p_resizeFramebuffer_1_, p_resizeFramebuffer_2_);
    }

    public boolean Y_259_p() {
        return this.Z_875_P;
    }

    public static class n_1700_B
    extends q_1272_r {
        private n_1700_B(String messageIn) {
            super(messageIn);
        }
    }
}


