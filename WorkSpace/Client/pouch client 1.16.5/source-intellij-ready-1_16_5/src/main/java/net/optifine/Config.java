/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.glfw.GLFW
 *  org.lwjgl.opengl.GL
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL30
 *  org.lwjgl.opengl.GLCapabilities
 */
package net.optifine;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URI;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import lightning.product.C_3240_x;
import lightning.product.D_2103_L;
import lightning.product.PackRepository;
import lightning.product.K_1289_S;
import lightning.product.L_3848_p;
import lightning.product.M_660_m;
import lightning.product.Resource;
import lightning.product.P_3084_J;
import lightning.product.ResourceManager;
import lightning.product.S_4169_p;
import lightning.product.U_2871_b;
import lightning.product.V_4423_d;
import lightning.product.X_933_l;
import lightning.product.PackResources;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.g_164_R;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.i_4221_J;
import lightning.product.ModelManager;
import lightning.product.j_3341_s;
import lightning.product.FrameTimer;
import lightning.product.q_383_x;
import lightning.product.u_2877_K;
import lightning.product.u_530_F;
import lightning.product.z_4547_I;
import lightning.product.z_883_p;
import net.optifine.DynamicLights;
import net.optifine.GlErrors;
import net.optifine.VersionCheckThread;
import net.optifine.config.GlVersion;
import net.optifine.gui.GuiMessage;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.shaders.Shaders;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.TextureUtils;
import net.optifine.util.TimedEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLCapabilities;

public class Config {
    public static final String OF_NAME = "OptiFine";
    public static final String MC_VERSION = "1.16.5";
    public static final String OF_EDITION = "HD_U";
    public static final String OF_RELEASE = "G8";
    public static final String VERSION = "OptiFine_1.16.5_HD_U_G8";
    private static String build = null;
    private static String newRelease = null;
    private static boolean notify64BitJava = false;
    public static String openGlVersion = null;
    public static String openGlRenderer = null;
    public static String openGlVendor = null;
    public static String[] openGlExtensions = null;
    public static GlVersion glVersion = null;
    public static GlVersion glslVersion = null;
    public static int minecraftVersionInt = -1;
    public static boolean fancyFogAvailable = false;
    public static boolean occlusionAvailable = false;
    private static V_4423_d gameSettings = null;
    private static MinecraftClient minecraft = MinecraftClient.A_4115_X();
    private static boolean initialized = false;
    private static Thread minecraftThread = null;
    private static int antialiasingLevel = 0;
    private static int availableProcessors = 0;
    public static boolean zoomMode = false;
    public static boolean zoomSmoothCamera = false;
    private static int texturePackClouds = 0;
    private static boolean fullscreenModeChecked = false;
    private static boolean desktopModeChecked = false;
    public static final Float DEF_ALPHA_FUNC_LEVEL = Float.valueOf(0.1f);
    private static final Logger LOGGER = LogManager.getLogger();
    public static final boolean logDetail = System.getProperty("log.detail", "false").equals("true");
    private static String mcDebugLast = null;
    private static int fpsMinLast = 0;
    private static int chunkUpdatesLast = 0;
    private static L_3848_p textureMapTerrain;
    private static long timeLastFrameMs;
    private static long averageFrameTimeMs;
    private static boolean showFrameTime;

    private Config() {
    }

    public static String getVersion() {
        return VERSION;
    }

    public static String getVersionDebug() {
        StringBuffer stringbuffer = new StringBuffer(32);
        if (Config.isDynamicLights()) {
            stringbuffer.append("DL: ");
            stringbuffer.append(String.valueOf(DynamicLights.getCount()));
            stringbuffer.append(", ");
        }
        stringbuffer.append(VERSION);
        String s = Shaders.getShaderPackName();
        if (s != null) {
            stringbuffer.append(", ");
            stringbuffer.append(s);
        }
        return stringbuffer.toString();
    }

    public static void initGameSettings(V_4423_d settings) {
        if (gameSettings == null) {
            gameSettings = settings;
            Config.updateAvailableProcessors();
            ReflectorForge.putLaunchBlackboard("optifine.ForgeSplashCompatible", Boolean.TRUE);
            antialiasingLevel = Config.gameSettings.C_3538_G;
        }
    }

    public static void initDisplay() {
        Config.checkInitialized();
        minecraftThread = Thread.currentThread();
        Config.updateThreadPriorities();
        Shaders.startup(MinecraftClient.A_4115_X());
    }

    public static void checkInitialized() {
        if (!initialized && MinecraftClient.A_4115_X().RealmsServerPing() != null) {
            initialized = true;
            Config.checkOpenGlCaps();
            Config.startVersionCheckThread();
        }
    }

    private static void checkOpenGlCaps() {
        Config.log("");
        Config.log(Config.getVersion());
        Config.log("Build: " + Config.getBuild());
        Config.log("OS: " + System.getProperty("os.name") + " (" + System.getProperty("os.arch") + ") version " + System.getProperty("os.version"));
        Config.log("Java: " + System.getProperty("java.version") + ", " + System.getProperty("java.vendor"));
        Config.log("VM: " + System.getProperty("java.vm.name") + " (" + System.getProperty("java.vm.info") + "), " + System.getProperty("java.vm.vendor"));
        Config.log("LWJGL: " + GLFW.glfwGetVersionString());
        openGlVersion = GL11.glGetString((int)7938);
        openGlRenderer = GL11.glGetString((int)7937);
        openGlVendor = GL11.glGetString((int)7936);
        Config.log("OpenGL: " + openGlRenderer + ", version " + openGlVersion + ", " + openGlVendor);
        Config.log("OpenGL Version: " + Config.getOpenGlVersionString());
        GLCapabilities glcapabilities = GL.getCapabilities();
        if (!glcapabilities.OpenGL12) {
            Config.log("OpenGL Mipmap levels: Not available (GL12.GL_TEXTURE_MAX_LEVEL)");
        }
        if (!(fancyFogAvailable = glcapabilities.GL_NV_fog_distance)) {
            Config.log("OpenGL Fancy fog: Not available (GL_NV_fog_distance)");
        }
        if (!(occlusionAvailable = glcapabilities.GL_ARB_occlusion_query)) {
            Config.log("OpenGL Occlussion culling: Not available (GL_ARB_occlusion_query)");
        }
        int i = TextureUtils.getGLMaximumTextureSize();
        Config.dbg("Maximum texture size: " + i + "x" + i);
    }

    public static String getBuild() {
        if (build == null) {
            try {
                InputStream inputstream = Config.getOptiFineResourceStream("/buildof.txt");
                if (inputstream == null) {
                    return null;
                }
                build = Config.readLines(inputstream)[0];
            }
            catch (Exception exception) {
                Config.warn(exception.getClass().getName() + ": " + exception.getMessage());
                build = "";
            }
        }
        return build;
    }

    public static InputStream getOptiFineResourceStream(String name) {
        InputStream inputstream = ReflectorForge.getOptiFineResourceStream(name);
        return inputstream != null ? inputstream : Config.class.getResourceAsStream(name);
    }

    public static boolean isFancyFogAvailable() {
        return fancyFogAvailable;
    }

    public static boolean isOcclusionAvailable() {
        return occlusionAvailable;
    }

    public static int getMinecraftVersionInt() {
        if (minecraftVersionInt < 0) {
            String[] astring = Config.tokenize(MC_VERSION, ".");
            int i = 0;
            if (astring.length > 0) {
                i += 10000 * Config.parseInt(astring[0], 0);
            }
            if (astring.length > 1) {
                i += 100 * Config.parseInt(astring[1], 0);
            }
            if (astring.length > 2) {
                i += 1 * Config.parseInt(astring[2], 0);
            }
            minecraftVersionInt = i;
        }
        return minecraftVersionInt;
    }

    public static String getOpenGlVersionString() {
        GlVersion glversion = Config.getGlVersion();
        return glversion.getMajor() + "." + glversion.getMinor() + "." + glversion.getRelease();
    }

    private static GlVersion getGlVersionLwjgl() {
        GLCapabilities glcapabilities = GL.getCapabilities();
        if (glcapabilities.OpenGL44) {
            return new GlVersion(4, 4);
        }
        if (glcapabilities.OpenGL43) {
            return new GlVersion(4, 3);
        }
        if (glcapabilities.OpenGL42) {
            return new GlVersion(4, 2);
        }
        if (glcapabilities.OpenGL41) {
            return new GlVersion(4, 1);
        }
        if (glcapabilities.OpenGL40) {
            return new GlVersion(4, 0);
        }
        if (glcapabilities.OpenGL33) {
            return new GlVersion(3, 3);
        }
        if (glcapabilities.OpenGL32) {
            return new GlVersion(3, 2);
        }
        if (glcapabilities.OpenGL31) {
            return new GlVersion(3, 1);
        }
        if (glcapabilities.OpenGL30) {
            return new GlVersion(3, 0);
        }
        if (glcapabilities.OpenGL21) {
            return new GlVersion(2, 1);
        }
        if (glcapabilities.OpenGL20) {
            return new GlVersion(2, 0);
        }
        if (glcapabilities.OpenGL15) {
            return new GlVersion(1, 5);
        }
        if (glcapabilities.OpenGL14) {
            return new GlVersion(1, 4);
        }
        if (glcapabilities.OpenGL13) {
            return new GlVersion(1, 3);
        }
        if (glcapabilities.OpenGL12) {
            return new GlVersion(1, 2);
        }
        return glcapabilities.OpenGL11 ? new GlVersion(1, 1) : new GlVersion(1, 0);
    }

    public static GlVersion getGlVersion() {
        if (glVersion == null) {
            String s = GL11.glGetString((int)7938);
            glVersion = Config.parseGlVersion(s, null);
            if (glVersion == null) {
                glVersion = Config.getGlVersionLwjgl();
            }
            if (glVersion == null) {
                glVersion = new GlVersion(1, 0);
            }
        }
        return glVersion;
    }

    public static GlVersion getGlslVersion() {
        String s;
        if (glslVersion == null && (glslVersion = Config.parseGlVersion(s = GL11.glGetString((int)35724), null)) == null) {
            glslVersion = new GlVersion(1, 10);
        }
        return glslVersion;
    }

    public static GlVersion parseGlVersion(String versionString, GlVersion def) {
        try {
            if (versionString == null) {
                return def;
            }
            Pattern pattern = Pattern.compile("([0-9]+)\\.([0-9]+)(\\.([0-9]+))?(.+)?");
            Matcher matcher = pattern.matcher(versionString);
            if (!matcher.matches()) {
                return def;
            }
            int i = Integer.parseInt(matcher.group(1));
            int j = Integer.parseInt(matcher.group(2));
            int k = matcher.group(4) != null ? Integer.parseInt(matcher.group(4)) : 0;
            String s = matcher.group(5);
            return new GlVersion(i, j, k, s);
        }
        catch (Exception exception) {
            Config.error("", exception);
            return def;
        }
    }

    public static String[] getOpenGlExtensions() {
        if (openGlExtensions == null) {
            openGlExtensions = Config.detectOpenGlExtensions();
        }
        return openGlExtensions;
    }

    private static String[] detectOpenGlExtensions() {
        try {
            int i;
            GlVersion glversion = Config.getGlVersion();
            if (glversion.getMajor() >= 3 && (i = GL11.glGetInteger((int)33309)) > 0) {
                String[] astring = new String[i];
                for (int j = 0; j < i; ++j) {
                    astring[j] = GL30.glGetStringi((int)7939, (int)j);
                }
                return astring;
            }
        }
        catch (Exception exception1) {
            Config.error("", exception1);
        }
        try {
            String s = GL11.glGetString((int)7939);
            return s.split(" ");
        }
        catch (Exception exception) {
            Config.error("", exception);
            return new String[0];
        }
    }

    public static void updateThreadPriorities() {
        Config.updateAvailableProcessors();
        int i = 8;
        if (Config.isSingleProcessor()) {
            if (Config.isSmoothWorld()) {
                minecraftThread.setPriority(10);
                Config.setThreadPriority("Server thread", 1);
            } else {
                minecraftThread.setPriority(5);
                Config.setThreadPriority("Server thread", 5);
            }
        } else {
            minecraftThread.setPriority(10);
            Config.setThreadPriority("Server thread", 5);
        }
    }

    private static void setThreadPriority(String prefix, int priority) {
        try {
            ThreadGroup threadgroup = Thread.currentThread().getThreadGroup();
            if (threadgroup == null) {
                return;
            }
            int i = (threadgroup.activeCount() + 10) * 2;
            Thread[] athread = new Thread[i];
            threadgroup.enumerate(athread, false);
            for (int j = 0; j < athread.length; ++j) {
                Thread thread = athread[j];
                if (thread == null || !thread.getName().startsWith(prefix)) continue;
                thread.setPriority(priority);
            }
        }
        catch (Throwable throwable) {
            Config.warn(throwable.getClass().getName() + ": " + throwable.getMessage());
        }
    }

    public static boolean isMinecraftThread() {
        return Thread.currentThread() == minecraftThread;
    }

    private static void startVersionCheckThread() {
        VersionCheckThread versioncheckthread = new VersionCheckThread();
        versioncheckthread.start();
    }

    public static boolean isMipmaps() {
        return Config.gameSettings.c_3005_b > 0;
    }

    public static int getMipmapLevels() {
        return Config.gameSettings.c_3005_b;
    }

    public static int getMipmapType() {
        switch (Config.gameSettings.J_4256_G) {
            case 0: {
                return 9986;
            }
            case 1: {
                return 9986;
            }
            case 2: {
                if (Config.isMultiTexture()) {
                    return 9985;
                }
                return 9986;
            }
            case 3: {
                if (Config.isMultiTexture()) {
                    return 9987;
                }
                return 9986;
            }
        }
        return 9986;
    }

    public static boolean isUseAlphaFunc() {
        float f = Config.getAlphaFuncLevel();
        return f > DEF_ALPHA_FUNC_LEVEL.floatValue() + 1.0E-5f;
    }

    public static float getAlphaFuncLevel() {
        return DEF_ALPHA_FUNC_LEVEL.floatValue();
    }

    public static boolean isFogFancy() {
        if (!Config.isFancyFogAvailable()) {
            return false;
        }
        return Config.gameSettings.C_290_v == 2;
    }

    public static boolean isFogFast() {
        return Config.gameSettings.C_290_v == 1;
    }

    public static boolean isFogOff() {
        return Config.gameSettings.C_290_v == 3;
    }

    public static boolean isFogOn() {
        return Config.gameSettings.C_290_v != 3;
    }

    public static float getFogStart() {
        return Config.gameSettings.w_728_N;
    }

    public static void detail(String s) {
        if (logDetail) {
            LOGGER.info("[OptiFine] " + s);
        }
    }

    public static void dbg(String s) {
        LOGGER.info("[OptiFine] " + s);
    }

    public static void warn(String s) {
        LOGGER.warn("[OptiFine] " + s);
    }

    public static void warn(String s, Throwable t) {
        LOGGER.warn("[OptiFine] " + s, t);
    }

    public static void error(String s) {
        LOGGER.error("[OptiFine] " + s);
    }

    public static void error(String s, Throwable t) {
        LOGGER.error("[OptiFine] " + s, t);
    }

    public static void log(String s) {
        Config.dbg(s);
    }

    public static int getUpdatesPerFrame() {
        return Config.gameSettings.UploadTokenCache;
    }

    public static boolean isDynamicUpdates() {
        return Config.gameSettings.U_1341_G;
    }

    public static boolean isGraphicsFancy() {
        return Config.gameSettings.u_1723_Y != P_3084_J.n_1700_B;
    }

    public static boolean isGraphicsFabulous() {
        return Config.gameSettings.u_1723_Y == P_3084_J.R_4764_Y;
    }

    public static boolean isRainFancy() {
        if (Config.gameSettings.F_4247_a == 0) {
            return Config.isGraphicsFancy();
        }
        return Config.gameSettings.F_4247_a == 2;
    }

    public static boolean isRainOff() {
        return Config.gameSettings.F_4247_a == 3;
    }

    public static boolean isCloudsFancy() {
        if (Config.gameSettings.G_424_k != 0) {
            return Config.gameSettings.G_424_k == 2;
        }
        if (Config.isShaders() && !Shaders.shaderPackClouds.isDefault()) {
            return Shaders.shaderPackClouds.isFancy();
        }
        if (texturePackClouds != 0) {
            return texturePackClouds == 2;
        }
        return Config.isGraphicsFancy();
    }

    public static boolean isCloudsOff() {
        if (Config.gameSettings.G_424_k != 0) {
            return Config.gameSettings.G_424_k == 3;
        }
        if (Config.isShaders() && !Shaders.shaderPackClouds.isDefault()) {
            return Shaders.shaderPackClouds.isOff();
        }
        if (texturePackClouds != 0) {
            return texturePackClouds == 3;
        }
        return false;
    }

    public static void updateTexturePackClouds() {
        texturePackClouds = 0;
        ResourceManager iresourcemanager = Config.getResourceManager();
        if (iresourcemanager != null) {
            try {
                InputStream inputstream = iresourcemanager.n_1700_B(new g_2336_b("optifine/color.properties")).J_1907_R();
                if (inputstream == null) {
                    return;
                }
                PropertiesOrdered properties = new PropertiesOrdered();
                properties.load(inputstream);
                inputstream.close();
                String s = properties.getProperty("clouds");
                if (s == null) {
                    return;
                }
                Config.dbg("Texture pack clouds: " + s);
                s = s.toLowerCase();
                if (s.equals("fast")) {
                    texturePackClouds = 1;
                }
                if (s.equals("fancy")) {
                    texturePackClouds = 2;
                }
                if (s.equals("off")) {
                    texturePackClouds = 3;
                }
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public static ModelManager getModelManager() {
        return Config.minecraft.r_715_M().R_4764_Y;
    }

    public static boolean isTreesFancy() {
        if (Config.gameSettings.f_1043_S == 0) {
            return Config.isGraphicsFancy();
        }
        return Config.gameSettings.f_1043_S != 1;
    }

    public static boolean isTreesSmart() {
        return Config.gameSettings.f_1043_S == 4;
    }

    public static boolean isCullFacesLeaves() {
        if (Config.gameSettings.f_1043_S == 0) {
            return !Config.isGraphicsFancy();
        }
        return Config.gameSettings.f_1043_S == 4;
    }

    public static boolean isDroppedItemsFancy() {
        if (Config.gameSettings.J_739_q == 0) {
            return Config.isGraphicsFancy();
        }
        return Config.gameSettings.J_739_q == 2;
    }

    public static int limit(int val, int min, int max) {
        if (val < min) {
            return min;
        }
        return val > max ? max : val;
    }

    public static long limit(long val, long min, long max) {
        if (val < min) {
            return min;
        }
        return val > max ? max : val;
    }

    public static float limit(float val, float min, float max) {
        if (val < min) {
            return min;
        }
        return val > max ? max : val;
    }

    public static double limit(double val, double min, double max) {
        if (val < min) {
            return min;
        }
        return val > max ? max : val;
    }

    public static float limitTo1(float val) {
        if (val < 0.0f) {
            return 0.0f;
        }
        return val > 1.0f ? 1.0f : val;
    }

    public static boolean isAnimatedWater() {
        return Config.gameSettings.S_3139_t != 2;
    }

    public static boolean isGeneratedWater() {
        return Config.gameSettings.S_3139_t == 1;
    }

    public static boolean isAnimatedPortal() {
        return Config.gameSettings.V_118_c;
    }

    public static boolean isAnimatedLava() {
        return Config.gameSettings.k_2302_P != 2;
    }

    public static boolean isGeneratedLava() {
        return Config.gameSettings.k_2302_P == 1;
    }

    public static boolean isAnimatedFire() {
        return Config.gameSettings.t_3452_g;
    }

    public static boolean isAnimatedRedstone() {
        return Config.gameSettings.I_1407_m;
    }

    public static boolean isAnimatedExplosion() {
        return Config.gameSettings.o_2767_H;
    }

    public static boolean isAnimatedFlame() {
        return Config.gameSettings.d_2545_n;
    }

    public static boolean isAnimatedSmoke() {
        return Config.gameSettings.x_92_N;
    }

    public static boolean isVoidParticles() {
        return Config.gameSettings.i_601_W;
    }

    public static boolean isWaterParticles() {
        return Config.gameSettings.h_2739_B;
    }

    public static boolean isRainSplash() {
        return Config.gameSettings.X_1313_W;
    }

    public static boolean isPortalParticles() {
        return Config.gameSettings.x_4991_F;
    }

    public static boolean isPotionParticles() {
        return Config.gameSettings.Z_759_W;
    }

    public static boolean isFireworkParticles() {
        return Config.gameSettings.f_1574_f;
    }

    public static float getAmbientOcclusionLevel() {
        return Config.isShaders() && Shaders.aoLevel >= 0.0f ? Shaders.aoLevel : (float)Config.gameSettings.RealmsResetNormalWorldScreen;
    }

    public static String listToString(List list) {
        return Config.listToString(list, ", ");
    }

    public static String listToString(List list, String separator) {
        if (list == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(list.size() * 5);
        for (int i = 0; i < list.size(); ++i) {
            Object object = list.get(i);
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.valueOf(object));
        }
        return stringbuffer.toString();
    }

    public static String arrayToString(Object[] arr) {
        return Config.arrayToString(arr, ", ");
    }

    public static String arrayToString(Object[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            Object object = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.valueOf(object));
        }
        return stringbuffer.toString();
    }

    public static String arrayToString(int[] arr) {
        return Config.arrayToString(arr, ", ");
    }

    public static String arrayToString(int[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            int j = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.valueOf(j));
        }
        return stringbuffer.toString();
    }

    public static String arrayToString(float[] arr) {
        return Config.arrayToString(arr, ", ");
    }

    public static String arrayToString(float[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            float f = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.valueOf(f));
        }
        return stringbuffer.toString();
    }

    public static MinecraftClient getMinecraft() {
        return minecraft;
    }

    public static C_3240_x getTextureManager() {
        return minecraft.G_624_v();
    }

    public static ResourceManager getResourceManager() {
        return minecraft.T_2506_i();
    }

    public static InputStream getResourceStream(g_2336_b location) throws IOException {
        return Config.getResourceStream(minecraft.T_2506_i(), location);
    }

    public static InputStream getResourceStream(ResourceManager resourceManager, g_2336_b location) throws IOException {
        Resource iresource = resourceManager.n_1700_B(location);
        return iresource == null ? null : iresource.J_1907_R();
    }

    public static Resource getResource(g_2336_b location) throws IOException {
        return minecraft.T_2506_i().n_1700_B(location);
    }

    public static boolean hasResource(g_2336_b location) {
        if (location == null) {
            return false;
        }
        PackResources iresourcepack = Config.getDefiningResourcePack(location);
        return iresourcepack != null;
    }

    public static boolean hasResource(ResourceManager resourceManager, g_2336_b location) {
        try {
            Resource iresource = resourceManager.n_1700_B(location);
            return iresource != null;
        }
        catch (IOException ioexception) {
            return false;
        }
    }

    public static boolean hasResource(PackResources rp, g_2336_b loc) {
        return rp != null && loc != null ? rp.resourceExists(i_4221_J.n_1700_B, loc) : false;
    }

    public static PackResources[] getResourcePacks() {
        PackRepository resourcepacklist = minecraft.q_4610_l();
        Collection<D_2103_L> collection = resourcepacklist.P_1922_E();
        ArrayList<PackResources> list = new ArrayList<PackResources>();
        for (D_2103_L resourcepackinfo : collection) {
            PackResources iresourcepack = resourcepackinfo.G_564_y();
            if (iresourcepack == Config.getDefaultResourcePack()) continue;
            list.add(iresourcepack);
        }
        PackResources[] airesourcepack = list.toArray(new PackResources[list.size()]);
        return airesourcepack;
    }

    public static String getResourcePackNames() {
        if (minecraft.T_2506_i() == null) {
            return "";
        }
        PackResources[] airesourcepack = Config.getResourcePacks();
        if (airesourcepack.length <= 0) {
            return Config.getDefaultResourcePack().getName();
        }
        String[] astring = new String[airesourcepack.length];
        for (int i = 0; i < airesourcepack.length; ++i) {
            astring[i] = airesourcepack[i].getName();
        }
        return Config.arrayToString(astring);
    }

    public static S_4169_p getDefaultResourcePack() {
        return minecraft.z_4693_k().n_1700_B();
    }

    public static boolean isFromDefaultResourcePack(g_2336_b loc) {
        return Config.getDefiningResourcePack(loc) == Config.getDefaultResourcePack();
    }

    public static PackResources getDefiningResourcePack(g_2336_b location) {
        PackRepository resourcepacklist = minecraft.q_4610_l();
        Collection<D_2103_L> collection = resourcepacklist.P_1922_E();
        List list = (List)collection;
        for (int i = list.size() - 1; i >= 0; --i) {
            D_2103_L resourcepackinfo = (D_2103_L)list.get(i);
            PackResources iresourcepack = resourcepackinfo.G_564_y();
            if (!iresourcepack.resourceExists(i_4221_J.n_1700_B, location)) continue;
            return iresourcepack;
        }
        return null;
    }

    public static z_883_p getRenderGlobal() {
        return Config.minecraft.u_1723_Y;
    }

    public static z_883_p getWorldRenderer() {
        return Config.minecraft.u_1723_Y;
    }

    public static M_660_m getGameRenderer() {
        return Config.minecraft.s_956_w;
    }

    public static boolean isBetterGrass() {
        return Config.gameSettings.C_1162_e != 3;
    }

    public static boolean isBetterGrassFancy() {
        return Config.gameSettings.C_1162_e == 2;
    }

    public static boolean isWeatherEnabled() {
        return Config.gameSettings.RealmsPersistence;
    }

    public static boolean isSkyEnabled() {
        return Config.gameSettings.y_2772_m;
    }

    public static boolean isSunMoonEnabled() {
        return Config.gameSettings.d_4007_L;
    }

    public static boolean isSunTexture() {
        if (!Config.isSunMoonEnabled()) {
            return false;
        }
        return !Config.isShaders() || Shaders.isSun();
    }

    public static boolean isMoonTexture() {
        if (!Config.isSunMoonEnabled()) {
            return false;
        }
        return !Config.isShaders() || Shaders.isMoon();
    }

    public static boolean isVignetteEnabled() {
        if (Config.isShaders() && !Shaders.isVignette()) {
            return false;
        }
        if (Config.gameSettings.TextRenderingUtils == 0) {
            return Config.isGraphicsFancy();
        }
        return Config.gameSettings.TextRenderingUtils == 2;
    }

    public static boolean isStarsEnabled() {
        return Config.gameSettings.H_1883_T;
    }

    public static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException interruptedexception) {
            Config.error("", interruptedexception);
        }
    }

    public static boolean isTimeDayOnly() {
        return Config.gameSettings.ClientBootstrap == 1;
    }

    public static boolean isTimeDefault() {
        return Config.gameSettings.ClientBootstrap == 0;
    }

    public static boolean isTimeNightOnly() {
        return Config.gameSettings.ClientBootstrap == 2;
    }

    public static int getAnisotropicFilterLevel() {
        return Config.gameSettings.A_3959_N;
    }

    public static boolean isAnisotropicFiltering() {
        return Config.getAnisotropicFilterLevel() > 1;
    }

    public static int getAntialiasingLevel() {
        return antialiasingLevel;
    }

    public static boolean isAntialiasing() {
        return Config.getAntialiasingLevel() > 0;
    }

    public static boolean isAntialiasingConfigured() {
        return Config.getGameSettings().C_3538_G > 0;
    }

    public static boolean isMultiTexture() {
        if (Config.getAnisotropicFilterLevel() > 1) {
            return true;
        }
        return Config.getAntialiasingLevel() > 0;
    }

    public static boolean between(int val, int min, int max) {
        return val >= min && val <= max;
    }

    public static boolean between(float val, float min, float max) {
        return val >= min && val <= max;
    }

    public static boolean between(double val, double min, double max) {
        return val >= min && val <= max;
    }

    public static boolean isDrippingWaterLava() {
        return Config.gameSettings.l_1268_F;
    }

    public static boolean isBetterSnow() {
        return Config.gameSettings.o_2341_D;
    }

    public static int parseInt(String str, int defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            str = str.trim();
            return Integer.parseInt(str);
        }
        catch (NumberFormatException numberformatexception) {
            return defVal;
        }
    }

    public static int parseHexInt(String str, int defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            if ((str = str.trim()).startsWith("0x")) {
                str = str.substring(2);
            }
            return Integer.parseInt(str, 16);
        }
        catch (NumberFormatException numberformatexception) {
            return defVal;
        }
    }

    public static float parseFloat(String str, float defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            str = str.trim();
            return Float.parseFloat(str);
        }
        catch (NumberFormatException numberformatexception) {
            return defVal;
        }
    }

    public static boolean parseBoolean(String str, boolean defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            str = str.trim();
            return Boolean.parseBoolean(str);
        }
        catch (NumberFormatException numberformatexception) {
            return defVal;
        }
    }

    public static Boolean parseBoolean(String str, Boolean defVal) {
        try {
            if (str == null) {
                return defVal;
            }
            if ((str = str.trim().toLowerCase()).equals("true")) {
                return Boolean.TRUE;
            }
            return str.equals("false") ? Boolean.FALSE : defVal;
        }
        catch (NumberFormatException numberformatexception) {
            return defVal;
        }
    }

    public static String[] tokenize(String str, String delim) {
        StringTokenizer stringtokenizer = new StringTokenizer(str, delim);
        ArrayList<String> list = new ArrayList<String>();
        while (stringtokenizer.hasMoreTokens()) {
            String s = stringtokenizer.nextToken();
            list.add(s);
        }
        String[] astring = list.toArray(new String[list.size()]);
        return astring;
    }

    public static boolean isAnimatedTerrain() {
        return Config.gameSettings.J_303_C;
    }

    public static boolean isAnimatedTextures() {
        return Config.gameSettings.o_1800_r;
    }

    public static boolean isSwampColors() {
        return Config.gameSettings.C_1269_X;
    }

    public static boolean isRandomEntities() {
        return Config.gameSettings.x_612_B;
    }

    public static void checkGlError(String loc) {
        int i = X_933_l.g_164_R();
        if (i != 0 && GlErrors.isEnabled(i)) {
            String s = Config.getGlErrorString(i);
            String s1 = String.format("OpenGL error: %s (%s), at: %s", i, s, loc);
            Config.error(s1);
            if (Config.isShowGlErrors() && TimedEvent.isActive("ShowGlError", 10000L)) {
                String s2 = K_1289_S.n_1700_B("of.message.openglError", i, s);
                Config.minecraft.M_588_G.R_4764_Y().n_1700_B(new U_2871_b(s2));
            }
        }
    }

    public static boolean isSmoothBiomes() {
        return Config.gameSettings.x_607_J > 0;
    }

    public static int getBiomeBlendRadius() {
        return Config.gameSettings.x_607_J;
    }

    public static boolean isCustomColors() {
        return Config.gameSettings.j_306_t;
    }

    public static boolean isCustomSky() {
        return Config.gameSettings.F_3572_x;
    }

    public static boolean isCustomFonts() {
        return Config.gameSettings.t_1446_I;
    }

    public static boolean isShowCapes() {
        return Config.gameSettings.L_1362_X;
    }

    public static boolean isConnectedTextures() {
        return Config.gameSettings.P_5000_x != 3;
    }

    public static boolean isNaturalTextures() {
        return Config.gameSettings.O_1309_Q;
    }

    public static boolean isEmissiveTextures() {
        return Config.gameSettings.O_2934_T;
    }

    public static boolean isConnectedTexturesFancy() {
        return Config.gameSettings.P_5000_x == 2;
    }

    public static boolean isFastRender() {
        return Config.gameSettings.Z_735_d;
    }

    public static boolean isTranslucentBlocksFancy() {
        if (Config.gameSettings.P_925_e == 0) {
            return Config.isGraphicsFancy();
        }
        return Config.gameSettings.P_925_e == 2;
    }

    public static boolean isShaders() {
        return Shaders.shaderPackLoaded;
    }

    public static String[] readLines(File file) throws IOException {
        FileInputStream fileinputstream = new FileInputStream(file);
        return Config.readLines(fileinputstream);
    }

    public static String[] readLines(InputStream is) throws IOException {
        ArrayList<String> list = new ArrayList<String>();
        InputStreamReader inputstreamreader = new InputStreamReader(is, "ASCII");
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
        String s;
        while ((s = bufferedreader.readLine()) != null) {
            list.add(s);
        }
        return list.toArray(new String[list.size()]);
    }

    public static String readFile(File file) throws IOException {
        FileInputStream fileinputstream = new FileInputStream(file);
        return Config.readInputStream(fileinputstream, "ASCII");
    }

    public static String readInputStream(InputStream in) throws IOException {
        return Config.readInputStream(in, "ASCII");
    }

    public static String readInputStream(InputStream in, String encoding) throws IOException {
        InputStreamReader inputstreamreader = new InputStreamReader(in, encoding);
        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
        StringBuffer stringbuffer = new StringBuffer();
        while (true) {
            String s;
            if ((s = bufferedreader.readLine()) == null) {
                in.close();
                return stringbuffer.toString();
            }
            stringbuffer.append(s);
            stringbuffer.append("\n");
        }
    }

    public static byte[] readAll(InputStream is) throws IOException {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte[] abyte = new byte[1024];
        while (true) {
            int i;
            if ((i = is.read(abyte)) < 0) {
                is.close();
                return bytearrayoutputstream.toByteArray();
            }
            bytearrayoutputstream.write(abyte, 0, i);
        }
    }

    public static V_4423_d getGameSettings() {
        return gameSettings;
    }

    public static String getNewRelease() {
        return newRelease;
    }

    public static void setNewRelease(String newRelease) {
        Config.newRelease = newRelease;
    }

    public static int compareRelease(String rel1, String rel2) {
        int j;
        String[] astring1;
        String s1;
        String[] astring = Config.splitRelease(rel1);
        String s = astring[0];
        if (!s.equals(s1 = (astring1 = Config.splitRelease(rel2))[0])) {
            return s.compareTo(s1);
        }
        int i = Config.parseInt(astring[1], -1);
        if (i != (j = Config.parseInt(astring1[1], -1))) {
            return i - j;
        }
        String s2 = astring[2];
        String s3 = astring1[2];
        if (!s2.equals(s3)) {
            if (s2.isEmpty()) {
                return 1;
            }
            if (s3.isEmpty()) {
                return -1;
            }
        }
        return s2.compareTo(s3);
    }

    private static String[] splitRelease(String relStr) {
        if (relStr != null && relStr.length() > 0) {
            Pattern pattern = Pattern.compile("([A-Z])([0-9]+)(.*)");
            Matcher matcher = pattern.matcher(relStr);
            if (!matcher.matches()) {
                return new String[]{"", "", ""};
            }
            String s = Config.normalize(matcher.group(1));
            String s1 = Config.normalize(matcher.group(2));
            String s2 = Config.normalize(matcher.group(3));
            return new String[]{s, s1, s2};
        }
        return new String[]{"", "", ""};
    }

    public static int intHash(int x) {
        x = x ^ 0x3D ^ x >> 16;
        x += x << 3;
        x ^= x >> 4;
        return (x *= 668265261) ^ x >> 15;
    }

    public static int getRandom(c_1514_x blockPos, int face) {
        int i = Config.intHash(face + 37);
        i = Config.intHash(i + blockPos.getX());
        i = Config.intHash(i + blockPos.getZ());
        return Config.intHash(i + blockPos.getY());
    }

    public static int getAvailableProcessors() {
        return availableProcessors;
    }

    public static void updateAvailableProcessors() {
        availableProcessors = Runtime.getRuntime().availableProcessors();
    }

    public static boolean isSingleProcessor() {
        return Config.getAvailableProcessors() <= 1;
    }

    public static boolean isSmoothWorld() {
        return Config.gameSettings.i_2993_w;
    }

    public static boolean isLazyChunkLoading() {
        return Config.gameSettings.RealmsParentalConsentScreen;
    }

    public static boolean isDynamicFov() {
        return Config.gameSettings.X_4895_T;
    }

    public static boolean isAlternateBlocks() {
        return Config.gameSettings.L_103_L;
    }

    public static int getChunkViewDistance() {
        return gameSettings == null ? 10 : Config.gameSettings.J_1907_R;
    }

    public static boolean equals(Object o1, Object o2) {
        if (o1 == o2) {
            return true;
        }
        return o1 == null ? false : o1.equals(o2);
    }

    public static boolean equalsOne(Object a, Object[] bs) {
        if (bs == null) {
            return false;
        }
        for (int i = 0; i < bs.length; ++i) {
            Object object = bs[i];
            if (!Config.equals(a, object)) continue;
            return true;
        }
        return false;
    }

    public static boolean equalsOne(int val, int[] vals) {
        for (int i = 0; i < vals.length; ++i) {
            if (vals[i] != val) continue;
            return true;
        }
        return false;
    }

    public static boolean isSameOne(Object a, Object[] bs) {
        if (bs == null) {
            return false;
        }
        for (int i = 0; i < bs.length; ++i) {
            Object object = bs[i];
            if (a != object) continue;
            return true;
        }
        return false;
    }

    public static String normalize(String s) {
        return s == null ? "" : s;
    }

    private static ByteBuffer readIconImage(InputStream is) throws IOException {
        BufferedImage bufferedimage = ImageIO.read(is);
        int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), null, 0, bufferedimage.getWidth());
        ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
        for (int i : aint) {
            bytebuffer.putInt(i << 8 | i >> 24 & 0xFF);
        }
        ((Buffer)bytebuffer).flip();
        return bytebuffer;
    }

    public static Object[] addObjectToArray(Object[] arr, Object obj) {
        if (arr == null) {
            throw new NullPointerException("The given array is NULL");
        }
        int i = arr.length;
        int j = i + 1;
        Object[] aobject = (Object[])Array.newInstance(arr.getClass().getComponentType(), j);
        System.arraycopy(arr, 0, aobject, 0, i);
        aobject[i] = obj;
        return aobject;
    }

    public static Object[] addObjectToArray(Object[] arr, Object obj, int index) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(arr));
        list.add(index, obj);
        Object[] aobject = (Object[])Array.newInstance(arr.getClass().getComponentType(), list.size());
        return list.toArray(aobject);
    }

    public static Object[] addObjectsToArray(Object[] arr, Object[] objs) {
        if (arr == null) {
            throw new NullPointerException("The given array is NULL");
        }
        if (objs.length == 0) {
            return arr;
        }
        int i = arr.length;
        int j = i + objs.length;
        Object[] aobject = (Object[])Array.newInstance(arr.getClass().getComponentType(), j);
        System.arraycopy(arr, 0, aobject, 0, i);
        System.arraycopy(objs, 0, aobject, i, objs.length);
        return aobject;
    }

    public static Object[] removeObjectFromArray(Object[] arr, Object obj) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(arr));
        list.remove(obj);
        return Config.collectionToArray(list, arr.getClass().getComponentType());
    }

    public static Object[] collectionToArray(Collection coll, Class elementClass) {
        if (coll == null) {
            return null;
        }
        if (elementClass == null) {
            return null;
        }
        if (elementClass.isPrimitive()) {
            throw new IllegalArgumentException("Can not make arrays with primitive elements (int, double), element class: " + String.valueOf(elementClass));
        }
        Object[] aobject = (Object[])Array.newInstance(elementClass, coll.size());
        return coll.toArray(aobject);
    }

    public static boolean isCustomItems() {
        return Config.gameSettings.L_4248_u;
    }

    public static void drawFps(g_221_o matrixStackIn) {
        int i = Config.getChunkUpdates();
        int j = Config.minecraft.u_1723_Y.M_182_A();
        int k = Config.minecraft.u_1723_Y.t_1786_h();
        int l = Config.minecraft.u_1723_Y.multiplayerClientSuggestionProvider();
        String s = Config.getFpsString() + ", C: " + j + ", E: " + k + "+" + l + ", U: " + i;
        Config.minecraft.t_148_a.J_1907_R(matrixStackIn, s, 2.0f, 2.0f, -2039584);
    }

    public static String getFpsString() {
        int i = Config.getFpsAverage();
        int j = Config.getFpsMin();
        if (showFrameTime) {
            String s1 = String.format("%.1f", 1000.0 / (double)Config.limit(i, 1, Integer.MAX_VALUE));
            String s = String.format("%.1f", 1000.0 / (double)Config.limit(j, 1, Integer.MAX_VALUE));
            return s1 + "/" + s + " ms";
        }
        return i + "/" + j + " fps";
    }

    public static boolean isShowFrameTime() {
        return showFrameTime;
    }

    public static int getFpsAverage() {
        return Reflector.getFieldValueInt(Reflector.Minecraft_debugFPS, -1);
    }

    public static int getFpsMin() {
        return fpsMinLast;
    }

    public static int getChunkUpdates() {
        return chunkUpdatesLast;
    }

    public static void updateFpsMin() {
        int j;
        FrameTimer frametimer = minecraft.i_1637_u();
        long[] along = frametimer.R_4764_Y();
        int i = frametimer.J_1907_R();
        if (i != (j = frametimer.n_1700_B())) {
            long l;
            int k = Reflector.getFieldValueInt(Reflector.Minecraft_debugFPS, -1);
            if (k <= 0) {
                k = 1;
            }
            long i1 = l = (long)(1.0 / (double)k * 1.0E9);
            long j1 = 0L;
            int k1 = u_530_F.J_1907_R(i - 1, along.length);
            while (k1 != j && (double)j1 < 1.0E9) {
                long l1 = along[k1];
                if (l1 > i1) {
                    i1 = l1;
                }
                j1 += l1;
                k1 = u_530_F.J_1907_R(k1 - 1, along.length);
            }
            double d0 = (double)i1 / 1.0E9;
            fpsMinLast = (int)(1.0 / d0);
        }
    }

    private static void updateChunkUpdates() {
        chunkUpdatesLast = z_4547_I.J_1907_R;
        z_4547_I.J_1907_R = 0;
    }

    public static int getBitsOs() {
        String s = System.getenv("ProgramFiles(X86)");
        return s != null ? 64 : 32;
    }

    public static int getBitsJre() {
        String[] astring = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};
        for (int i = 0; i < astring.length; ++i) {
            String s = astring[i];
            String s1 = System.getProperty(s);
            if (s1 == null || !s1.contains("64")) continue;
            return 64;
        }
        return 32;
    }

    public static boolean isNotify64BitJava() {
        return notify64BitJava;
    }

    public static void setNotify64BitJava(boolean flag) {
        notify64BitJava = flag;
    }

    public static boolean isConnectedModels() {
        return false;
    }

    public static void showGuiMessage(String line1, String line2) {
        GuiMessage guimessage = new GuiMessage(Config.minecraft.Y_1740_V, line1, line2);
        minecraft.n_1700_B(guimessage);
    }

    public static int[] addIntToArray(int[] intArray, int intValue) {
        return Config.addIntsToArray(intArray, new int[]{intValue});
    }

    public static int[] addIntsToArray(int[] intArray, int[] copyFrom) {
        if (intArray != null && copyFrom != null) {
            int i = intArray.length;
            int j = i + copyFrom.length;
            int[] aint = new int[j];
            System.arraycopy(intArray, 0, aint, 0, i);
            for (int k = 0; k < copyFrom.length; ++k) {
                aint[k + i] = copyFrom[k];
            }
            return aint;
        }
        throw new NullPointerException("The given array is NULL");
    }

    public static void writeFile(File file, String str) throws IOException {
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        byte[] abyte = str.getBytes("ASCII");
        fileoutputstream.write(abyte);
        fileoutputstream.close();
    }

    public static void setTextureMap(L_3848_p textureMapTerrain) {
        Config.textureMapTerrain = textureMapTerrain;
    }

    public static L_3848_p getTextureMap() {
        return textureMapTerrain;
    }

    public static boolean isDynamicLights() {
        return Config.gameSettings.n_3197_X != 3;
    }

    public static boolean isDynamicLightsFast() {
        return Config.gameSettings.n_3197_X == 1;
    }

    public static boolean isDynamicHandLight() {
        if (!Config.isDynamicLights()) {
            return false;
        }
        return Config.isShaders() ? Shaders.isDynamicHandLight() : true;
    }

    public static boolean isCustomEntityModels() {
        return Config.gameSettings.P_2947_S;
    }

    public static boolean isCustomGuis() {
        return Config.gameSettings.O_4761_U;
    }

    public static int getScreenshotSize() {
        return Config.gameSettings.F_518_D;
    }

    public static int[] toPrimitive(Integer[] arr) {
        if (arr == null) {
            return null;
        }
        if (arr.length == 0) {
            return new int[0];
        }
        int[] aint = new int[arr.length];
        for (int i = 0; i < aint.length; ++i) {
            aint[i] = arr[i];
        }
        return aint;
    }

    public static boolean isRenderRegions() {
        if (Config.isMultiTexture()) {
            return false;
        }
        return Config.gameSettings.O_2151_c && X_933_l.G_564_y;
    }

    public static boolean isVbo() {
        return g_164_R.w_1484_f();
    }

    public static boolean isSmoothFps() {
        return Config.gameSettings.RealmsLongRunningMcoTaskScreen;
    }

    public static boolean openWebLink(URI uri) {
        j_3341_s.n_1700_B((Exception)null);
        j_3341_s.t_148_a().n_1700_B(uri);
        Exception exception = j_3341_s.M_588_G();
        return exception == null;
    }

    public static boolean isShowGlErrors() {
        return Config.gameSettings.w_2705_t;
    }

    public static String arrayToString(boolean[] arr, String separator) {
        if (arr == null) {
            return "";
        }
        StringBuffer stringbuffer = new StringBuffer(arr.length * 5);
        for (int i = 0; i < arr.length; ++i) {
            boolean flag = arr[i];
            if (i > 0) {
                stringbuffer.append(separator);
            }
            stringbuffer.append(String.valueOf(flag));
        }
        return stringbuffer.toString();
    }

    public static boolean isIntegratedServerRunning() {
        if (minecraft.n_3318_d() == null) {
            return false;
        }
        return minecraft.x_607_J();
    }

    public static IntBuffer createDirectIntBuffer(int capacity) {
        return q_383_x.n_1700_B(capacity << 2).asIntBuffer();
    }

    public static String getGlErrorString(int err) {
        switch (err) {
            case 0: {
                return "No error";
            }
            case 1280: {
                return "Invalid enum";
            }
            case 1281: {
                return "Invalid value";
            }
            case 1282: {
                return "Invalid operation";
            }
            case 1283: {
                return "Stack overflow";
            }
            case 1284: {
                return "Stack underflow";
            }
            case 1285: {
                return "Out of memory";
            }
            case 1286: {
                return "Invalid framebuffer operation";
            }
        }
        return "Unknown";
    }

    public static boolean isKeyDown(int key) {
        return GLFW.glfwGetKey((long)minecraft.RealmsServerPing().t_148_a(), (int)key) == 1;
    }

    public static boolean isTrue(Boolean val) {
        return val != null && val != false;
    }

    public static boolean isReloadingResources() {
        u_2877_K resourceloadprogressgui;
        if (Config.minecraft.t_4043_B == null) {
            return false;
        }
        return !(Config.minecraft.t_4043_B instanceof u_2877_K) || !(resourceloadprogressgui = (u_2877_K)Config.minecraft.t_4043_B).R_4764_Y();
    }

    public static boolean isQuadsToTriangles() {
        if (!Config.isShaders()) {
            return false;
        }
        return !Shaders.canRenderQuads();
    }

    public static void frameStart() {
        long i = System.currentTimeMillis();
        long j = i - timeLastFrameMs;
        timeLastFrameMs = i;
        j = Config.limit(j, 1L, 1000L);
        averageFrameTimeMs = (averageFrameTimeMs + j) / 2L;
        averageFrameTimeMs = Config.limit(averageFrameTimeMs, 1L, 1000L);
        if (Config.minecraft.e_4240_b != mcDebugLast) {
            mcDebugLast = Config.minecraft.e_4240_b;
            Config.updateFpsMin();
            Config.updateChunkUpdates();
        }
    }

    public static long getAverageFrameTimeMs() {
        return averageFrameTimeMs;
    }

    public static float getAverageFrameTimeSec() {
        return (float)Config.getAverageFrameTimeMs() / 1000.0f;
    }

    public static long getAverageFrameFps() {
        return 1000L / Config.getAverageFrameTimeMs();
    }

    public static void checkNull(Object obj, String msg) throws NullPointerException {
        if (obj == null) {
            throw new NullPointerException(msg);
        }
    }

    static {
        showFrameTime = Boolean.getBoolean("frame.time");
    }
}



