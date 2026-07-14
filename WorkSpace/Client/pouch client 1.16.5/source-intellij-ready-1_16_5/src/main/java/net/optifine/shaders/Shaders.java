/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.ARBGeometryShader4
 *  org.lwjgl.opengl.ARBShaderObjects
 *  org.lwjgl.opengl.ARBVertexShader
 *  org.lwjgl.opengl.EXTFramebufferObject
 *  org.lwjgl.opengl.EXTGeometryShader4
 *  org.lwjgl.opengl.GL
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL43
 *  org.lwjgl.opengl.GLCapabilities
 *  org.lwjgl.opengl.KHRDebug
 */
package net.optifine.shaders;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.CallSite;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lightning.product.FluidTags;
import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.MobEffects;
import lightning.product.K_1289_S;
import lightning.product.K_2069_m;
import lightning.product.K_4074_S;
import lightning.product.L_3848_p;
import lightning.product.M_1336_P;
import lightning.product.M_660_m;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.T_2915_h;
import lightning.product.T_3334_o;
import lightning.product.U_2871_b;
import lightning.product.V_3137_a;
import lightning.product.V_4423_d;
import lightning.product.W_3265_k;
import lightning.product.X_933_l;
import lightning.product.Z_1993_T;
import lightning.product.Z_2491_A;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.c_3005_b;
import lightning.product.c_4477_a;
import lightning.product.d_1620_j;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_3572_K;
import lightning.product.i_2154_H;
import lightning.product.k_4690_i;
import lightning.product.TextureMetadataSection;
import lightning.product.l_3747_P;
import lightning.product.o_2576_A;
import lightning.product.q_1613_l;
import lightning.product.q_383_x;
import lightning.product.r_4811_B;
import lightning.product.t_1920_R;
import lightning.product.v_1669_V;
import lightning.product.x_1688_C;
import lightning.product.z_3539_x;
import net.optifine.Config;
import net.optifine.CustomBlockLayers;
import net.optifine.CustomColors;
import net.optifine.GlErrors;
import net.optifine.Lang;
import net.optifine.config.ConnectedParser;
import net.optifine.expr.IExpressionBool;
import net.optifine.reflect.Reflector;
import net.optifine.render.GlAlphaState;
import net.optifine.render.GlBlendState;
import net.optifine.render.RenderTypes;
import net.optifine.render.RenderUtils;
import net.optifine.shaders.BlockAliases;
import net.optifine.shaders.ComputeProgram;
import net.optifine.shaders.CustomTexture;
import net.optifine.shaders.CustomTextureLocation;
import net.optifine.shaders.CustomTextureRaw;
import net.optifine.shaders.DrawBuffers;
import net.optifine.shaders.EntityAliases;
import net.optifine.shaders.FixedFramebuffer;
import net.optifine.shaders.GlState;
import net.optifine.shaders.HFNoiseTexture;
import net.optifine.shaders.ICustomTexture;
import net.optifine.shaders.IShaderPack;
import net.optifine.shaders.ItemAliases;
import net.optifine.shaders.Program;
import net.optifine.shaders.ProgramStack;
import net.optifine.shaders.ProgramStage;
import net.optifine.shaders.ProgramUtils;
import net.optifine.shaders.Programs;
import net.optifine.shaders.RenderStage;
import net.optifine.shaders.SMCLog;
import net.optifine.shaders.SMath;
import net.optifine.shaders.ShaderPackDefault;
import net.optifine.shaders.ShaderPackFolder;
import net.optifine.shaders.ShaderPackNone;
import net.optifine.shaders.ShaderPackZip;
import net.optifine.shaders.ShaderUtils;
import net.optifine.shaders.ShadersFramebuffer;
import net.optifine.shaders.ShadersRender;
import net.optifine.shaders.ShadersTex;
import net.optifine.shaders.SimpleShaderTexture;
import net.optifine.shaders.config.EnumShaderOption;
import net.optifine.shaders.config.MacroProcessor;
import net.optifine.shaders.config.MacroState;
import net.optifine.shaders.config.PropertyDefaultFastFancyOff;
import net.optifine.shaders.config.PropertyDefaultTrueFalse;
import net.optifine.shaders.config.RenderScale;
import net.optifine.shaders.config.ScreenShaderOptions;
import net.optifine.shaders.config.ShaderLine;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionRest;
import net.optifine.shaders.config.ShaderPackParser;
import net.optifine.shaders.config.ShaderParser;
import net.optifine.shaders.config.ShaderProfile;
import net.optifine.shaders.uniform.CustomUniforms;
import net.optifine.shaders.uniform.ShaderUniform1f;
import net.optifine.shaders.uniform.ShaderUniform1i;
import net.optifine.shaders.uniform.ShaderUniform2i;
import net.optifine.shaders.uniform.ShaderUniform3f;
import net.optifine.shaders.uniform.ShaderUniform4f;
import net.optifine.shaders.uniform.ShaderUniform4i;
import net.optifine.shaders.uniform.ShaderUniformM4;
import net.optifine.shaders.uniform.ShaderUniforms;
import net.optifine.shaders.uniform.Smoother;
import net.optifine.texture.InternalFormat;
import net.optifine.texture.PixelFormat;
import net.optifine.texture.PixelType;
import net.optifine.texture.TextureType;
import net.optifine.util.ArrayUtils;
import net.optifine.util.DynamicDimension;
import net.optifine.util.EntityUtils;
import net.optifine.util.LineBuffer;
import net.optifine.util.PropertiesOrdered;
import net.optifine.util.StrUtils;
import net.optifine.util.TimedEvent;
import net.optifine.util.WorldUtils;
import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBGeometryShader4;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.EXTGeometryShader4;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL43;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.KHRDebug;

public class Shaders {
    static MinecraftClient mc;
    static M_660_m entityRenderer;
    public static boolean isInitializedOnce;
    public static boolean isShaderPackInitialized;
    public static GLCapabilities capabilities;
    public static String glVersionString;
    public static String glVendorString;
    public static String glRendererString;
    public static boolean hasGlGenMipmap;
    public static int countResetDisplayLists;
    private static int renderDisplayWidth;
    private static int renderDisplayHeight;
    public static int renderWidth;
    public static int renderHeight;
    public static boolean isRenderingWorld;
    public static boolean isRenderingSky;
    public static boolean isCompositeRendered;
    public static boolean isRenderingDfb;
    public static boolean isShadowPass;
    public static boolean isEntitiesGlowing;
    public static boolean isSleeping;
    private static boolean isRenderingFirstPersonHand;
    private static boolean isHandRenderedMain;
    private static boolean isHandRenderedOff;
    private static boolean skipRenderHandMain;
    private static boolean skipRenderHandOff;
    public static boolean renderItemKeepDepthMask;
    public static boolean itemToRenderMainTranslucent;
    public static boolean itemToRenderOffTranslucent;
    static float[] sunPosition;
    static float[] moonPosition;
    static float[] shadowLightPosition;
    static float[] upPosition;
    static float[] shadowLightPositionVector;
    static float[] upPosModelView;
    static float[] sunPosModelView;
    static float[] moonPosModelView;
    private static float[] tempMat;
    static Z_2491_A clearColor;
    static float skyColorR;
    static float skyColorG;
    static float skyColorB;
    static long worldTime;
    static long lastWorldTime;
    static long diffWorldTime;
    static float celestialAngle;
    static float sunAngle;
    static float shadowAngle;
    static int moonPhase;
    static long systemTime;
    static long lastSystemTime;
    static long diffSystemTime;
    static int frameCounter;
    static float frameTime;
    static float frameTimeCounter;
    static int systemTimeInt32;
    public static t_1920_R pointOfView;
    public static boolean pointOfViewChanged;
    static float rainStrength;
    static float wetness;
    public static float wetnessHalfLife;
    public static float drynessHalfLife;
    public static float eyeBrightnessHalflife;
    static boolean usewetness;
    static int isEyeInWater;
    static int eyeBrightness;
    static float eyeBrightnessFadeX;
    static float eyeBrightnessFadeY;
    static float eyePosY;
    static float centerDepth;
    static float centerDepthSmooth;
    static float centerDepthSmoothHalflife;
    static boolean centerDepthSmoothEnabled;
    static int superSamplingLevel;
    static float nightVision;
    static float blindness;
    static boolean lightmapEnabled;
    static boolean fogEnabled;
    static RenderStage renderStage;
    private static int baseAttribId;
    public static int entityAttrib;
    public static int midTexCoordAttrib;
    public static int tangentAttrib;
    public static int velocityAttrib;
    public static int midBlockAttrib;
    public static boolean useEntityAttrib;
    public static boolean useMidTexCoordAttrib;
    public static boolean useTangentAttrib;
    public static boolean useVelocityAttrib;
    public static boolean useMidBlockAttrib;
    public static boolean progUseEntityAttrib;
    public static boolean progUseMidTexCoordAttrib;
    public static boolean progUseTangentAttrib;
    public static boolean progUseVelocityAttrib;
    public static boolean progUseMidBlockAttrib;
    private static boolean progArbGeometryShader4;
    private static boolean progExtGeometryShader4;
    private static int progMaxVerticesOut;
    private static boolean hasGeometryShaders;
    public static int atlasSizeX;
    public static int atlasSizeY;
    private static ShaderUniforms shaderUniforms;
    public static ShaderUniform4f uniform_entityColor;
    public static ShaderUniform1i uniform_entityId;
    public static ShaderUniform1i uniform_blockEntityId;
    public static ShaderUniform1i uniform_texture;
    public static ShaderUniform1i uniform_lightmap;
    public static ShaderUniform1i uniform_normals;
    public static ShaderUniform1i uniform_specular;
    public static ShaderUniform1i uniform_shadow;
    public static ShaderUniform1i uniform_watershadow;
    public static ShaderUniform1i uniform_shadowtex0;
    public static ShaderUniform1i uniform_shadowtex1;
    public static ShaderUniform1i uniform_depthtex0;
    public static ShaderUniform1i uniform_depthtex1;
    public static ShaderUniform1i uniform_shadowcolor;
    public static ShaderUniform1i uniform_shadowcolor0;
    public static ShaderUniform1i uniform_shadowcolor1;
    public static ShaderUniform1i uniform_noisetex;
    public static ShaderUniform1i uniform_gcolor;
    public static ShaderUniform1i uniform_gdepth;
    public static ShaderUniform1i uniform_gnormal;
    public static ShaderUniform1i uniform_composite;
    public static ShaderUniform1i uniform_gaux1;
    public static ShaderUniform1i uniform_gaux2;
    public static ShaderUniform1i uniform_gaux3;
    public static ShaderUniform1i uniform_gaux4;
    public static ShaderUniform1i uniform_colortex0;
    public static ShaderUniform1i uniform_colortex1;
    public static ShaderUniform1i uniform_colortex2;
    public static ShaderUniform1i uniform_colortex3;
    public static ShaderUniform1i uniform_colortex4;
    public static ShaderUniform1i uniform_colortex5;
    public static ShaderUniform1i uniform_colortex6;
    public static ShaderUniform1i uniform_colortex7;
    public static ShaderUniform1i uniform_gdepthtex;
    public static ShaderUniform1i uniform_depthtex2;
    public static ShaderUniform1i uniform_colortex8;
    public static ShaderUniform1i uniform_colortex9;
    public static ShaderUniform1i uniform_colortex10;
    public static ShaderUniform1i uniform_colortex11;
    public static ShaderUniform1i uniform_colortex12;
    public static ShaderUniform1i uniform_colortex13;
    public static ShaderUniform1i uniform_colortex14;
    public static ShaderUniform1i uniform_colortex15;
    public static ShaderUniform1i uniform_colorimg0;
    public static ShaderUniform1i uniform_colorimg1;
    public static ShaderUniform1i uniform_colorimg2;
    public static ShaderUniform1i uniform_colorimg3;
    public static ShaderUniform1i uniform_colorimg4;
    public static ShaderUniform1i uniform_colorimg5;
    public static ShaderUniform1i uniform_shadowcolorimg0;
    public static ShaderUniform1i uniform_shadowcolorimg1;
    public static ShaderUniform1i uniform_tex;
    public static ShaderUniform1i uniform_heldItemId;
    public static ShaderUniform1i uniform_heldBlockLightValue;
    public static ShaderUniform1i uniform_heldItemId2;
    public static ShaderUniform1i uniform_heldBlockLightValue2;
    public static ShaderUniform1i uniform_fogMode;
    public static ShaderUniform1f uniform_fogDensity;
    public static ShaderUniform3f uniform_fogColor;
    public static ShaderUniform3f uniform_skyColor;
    public static ShaderUniform1i uniform_worldTime;
    public static ShaderUniform1i uniform_worldDay;
    public static ShaderUniform1i uniform_moonPhase;
    public static ShaderUniform1i uniform_frameCounter;
    public static ShaderUniform1f uniform_frameTime;
    public static ShaderUniform1f uniform_frameTimeCounter;
    public static ShaderUniform1f uniform_sunAngle;
    public static ShaderUniform1f uniform_shadowAngle;
    public static ShaderUniform1f uniform_rainStrength;
    public static ShaderUniform1f uniform_aspectRatio;
    public static ShaderUniform1f uniform_viewWidth;
    public static ShaderUniform1f uniform_viewHeight;
    public static ShaderUniform1f uniform_near;
    public static ShaderUniform1f uniform_far;
    public static ShaderUniform3f uniform_sunPosition;
    public static ShaderUniform3f uniform_moonPosition;
    public static ShaderUniform3f uniform_shadowLightPosition;
    public static ShaderUniform3f uniform_upPosition;
    public static ShaderUniform3f uniform_previousCameraPosition;
    public static ShaderUniform3f uniform_cameraPosition;
    public static ShaderUniformM4 uniform_gbufferModelView;
    public static ShaderUniformM4 uniform_gbufferModelViewInverse;
    public static ShaderUniformM4 uniform_gbufferPreviousProjection;
    public static ShaderUniformM4 uniform_gbufferProjection;
    public static ShaderUniformM4 uniform_gbufferProjectionInverse;
    public static ShaderUniformM4 uniform_gbufferPreviousModelView;
    public static ShaderUniformM4 uniform_shadowProjection;
    public static ShaderUniformM4 uniform_shadowProjectionInverse;
    public static ShaderUniformM4 uniform_shadowModelView;
    public static ShaderUniformM4 uniform_shadowModelViewInverse;
    public static ShaderUniform1f uniform_wetness;
    public static ShaderUniform1f uniform_eyeAltitude;
    public static ShaderUniform2i uniform_eyeBrightness;
    public static ShaderUniform2i uniform_eyeBrightnessSmooth;
    public static ShaderUniform2i uniform_terrainTextureSize;
    public static ShaderUniform1i uniform_terrainIconSize;
    public static ShaderUniform1i uniform_isEyeInWater;
    public static ShaderUniform1f uniform_nightVision;
    public static ShaderUniform1f uniform_blindness;
    public static ShaderUniform1f uniform_screenBrightness;
    public static ShaderUniform1i uniform_hideGUI;
    public static ShaderUniform1f uniform_centerDepthSmooth;
    public static ShaderUniform2i uniform_atlasSize;
    public static ShaderUniform4f uniform_spriteBounds;
    public static ShaderUniform4i uniform_blendFunc;
    public static ShaderUniform1i uniform_instanceId;
    public static ShaderUniform1f uniform_playerMood;
    public static ShaderUniform1i uniform_renderStage;
    static double previousCameraPositionX;
    static double previousCameraPositionY;
    static double previousCameraPositionZ;
    static double cameraPositionX;
    static double cameraPositionY;
    static double cameraPositionZ;
    static int cameraOffsetX;
    static int cameraOffsetZ;
    static boolean hasShadowMap;
    public static boolean needResizeShadow;
    static int shadowMapWidth;
    static int shadowMapHeight;
    static int spShadowMapWidth;
    static int spShadowMapHeight;
    static float shadowMapFOV;
    static float shadowMapHalfPlane;
    static boolean shadowMapIsOrtho;
    static float shadowDistanceRenderMul;
    public static boolean shouldSkipDefaultShadow;
    static boolean waterShadowEnabled;
    public static final int MaxDrawBuffers = 8;
    public static final int MaxColorBuffers = 16;
    public static final int MaxDepthBuffers = 3;
    public static final int MaxShadowColorBuffers = 2;
    public static final int MaxShadowDepthBuffers = 2;
    static int usedColorBuffers;
    static int usedDepthBuffers;
    static int usedShadowColorBuffers;
    static int usedShadowDepthBuffers;
    static int usedColorAttachs;
    static int usedDrawBuffers;
    static boolean bindImageTextures;
    static ShadersFramebuffer dfb;
    static ShadersFramebuffer sfb;
    private static int[] gbuffersFormat;
    public static boolean[] gbuffersClear;
    public static Z_2491_A[] gbuffersClearColor;
    private static final Z_2491_A CLEAR_COLOR_0;
    private static final Z_2491_A CLEAR_COLOR_1;
    private static int[] shadowBuffersFormat;
    public static boolean[] shadowBuffersClear;
    public static Z_2491_A[] shadowBuffersClearColor;
    private static Programs programs;
    public static final Program ProgramNone;
    public static final Program ProgramShadow;
    public static final Program ProgramShadowSolid;
    public static final Program ProgramShadowCutout;
    public static final Program[] ProgramsShadowcomp;
    public static final Program[] ProgramsPrepare;
    public static final Program ProgramBasic;
    public static final Program ProgramTextured;
    public static final Program ProgramTexturedLit;
    public static final Program ProgramSkyBasic;
    public static final Program ProgramSkyTextured;
    public static final Program ProgramClouds;
    public static final Program ProgramTerrain;
    public static final Program ProgramTerrainSolid;
    public static final Program ProgramTerrainCutoutMip;
    public static final Program ProgramTerrainCutout;
    public static final Program ProgramDamagedBlock;
    public static final Program ProgramBlock;
    public static final Program ProgramBeaconBeam;
    public static final Program ProgramItem;
    public static final Program ProgramEntities;
    public static final Program ProgramEntitiesGlowing;
    public static final Program ProgramArmorGlint;
    public static final Program ProgramSpiderEyes;
    public static final Program ProgramHand;
    public static final Program ProgramWeather;
    public static final Program ProgramDeferredPre;
    public static final Program[] ProgramsDeferred;
    public static final Program ProgramDeferred;
    public static final Program ProgramWater;
    public static final Program ProgramHandWater;
    public static final Program ProgramCompositePre;
    public static final Program[] ProgramsComposite;
    public static final Program ProgramComposite;
    public static final Program ProgramFinal;
    public static final int ProgramCount;
    public static final Program[] ProgramsAll;
    public static Program activeProgram;
    public static int activeProgramID;
    private static ProgramStack programStack;
    private static boolean hasDeferredPrograms;
    public static boolean hasShadowcompPrograms;
    public static boolean hasPreparePrograms;
    public static Properties loadedShaders;
    public static Properties shadersConfig;
    public static c_4477_a defaultTexture;
    public static boolean[] shadowHardwareFilteringEnabled;
    public static boolean[] shadowMipmapEnabled;
    public static boolean[] shadowFilterNearest;
    public static boolean[] shadowColorMipmapEnabled;
    public static boolean[] shadowColorFilterNearest;
    public static boolean configTweakBlockDamage;
    public static boolean configCloudShadow;
    public static float configHandDepthMul;
    public static float configRenderResMul;
    public static float configShadowResMul;
    public static int configTexMinFilB;
    public static int configTexMinFilN;
    public static int configTexMinFilS;
    public static int configTexMagFilB;
    public static int configTexMagFilN;
    public static int configTexMagFilS;
    public static boolean configShadowClipFrustrum;
    public static boolean configNormalMap;
    public static boolean configSpecularMap;
    public static PropertyDefaultTrueFalse configOldLighting;
    public static PropertyDefaultTrueFalse configOldHandLight;
    public static int configAntialiasingLevel;
    public static final int texMinFilRange = 3;
    public static final int texMagFilRange = 2;
    public static final String[] texMinFilDesc;
    public static final String[] texMagFilDesc;
    public static final int[] texMinFilValue;
    public static final int[] texMagFilValue;
    private static IShaderPack shaderPack;
    public static boolean shaderPackLoaded;
    public static String currentShaderName;
    public static final String SHADER_PACK_NAME_NONE = "OFF";
    public static final String SHADER_PACK_NAME_DEFAULT = "(internal)";
    public static final String SHADER_PACKS_DIR_NAME = "shaderpacks";
    public static final String OPTIONS_FILE_NAME = "optionsshaders.txt";
    public static final File shaderPacksDir;
    static File configFile;
    private static ShaderOption[] shaderPackOptions;
    private static Set<String> shaderPackOptionSliders;
    static ShaderProfile[] shaderPackProfiles;
    static Map<String, ScreenShaderOptions> shaderPackGuiScreens;
    static Map<String, IExpressionBool> shaderPackProgramConditions;
    public static final String PATH_SHADERS_PROPERTIES = "/shaders/shaders.properties";
    public static PropertyDefaultFastFancyOff shaderPackClouds;
    public static PropertyDefaultTrueFalse shaderPackOldLighting;
    public static PropertyDefaultTrueFalse shaderPackOldHandLight;
    public static PropertyDefaultTrueFalse shaderPackDynamicHandLight;
    public static PropertyDefaultTrueFalse shaderPackShadowTerrain;
    public static PropertyDefaultTrueFalse shaderPackShadowTranslucent;
    public static PropertyDefaultTrueFalse shaderPackShadowEntities;
    public static PropertyDefaultTrueFalse shaderPackShadowBlockEntities;
    public static PropertyDefaultTrueFalse shaderPackUnderwaterOverlay;
    public static PropertyDefaultTrueFalse shaderPackSun;
    public static PropertyDefaultTrueFalse shaderPackMoon;
    public static PropertyDefaultTrueFalse shaderPackVignette;
    public static PropertyDefaultTrueFalse shaderPackBackFaceSolid;
    public static PropertyDefaultTrueFalse shaderPackBackFaceCutout;
    public static PropertyDefaultTrueFalse shaderPackBackFaceCutoutMipped;
    public static PropertyDefaultTrueFalse shaderPackBackFaceTranslucent;
    public static PropertyDefaultTrueFalse shaderPackRainDepth;
    public static PropertyDefaultTrueFalse shaderPackBeaconBeamDepth;
    public static PropertyDefaultTrueFalse shaderPackSeparateAo;
    public static PropertyDefaultTrueFalse shaderPackFrustumCulling;
    private static Map<String, String> shaderPackResources;
    private static b_4507_u currentWorld;
    private static List<Integer> shaderPackDimensions;
    private static ICustomTexture[] customTexturesGbuffers;
    private static ICustomTexture[] customTexturesComposite;
    private static ICustomTexture[] customTexturesDeferred;
    private static ICustomTexture[] customTexturesShadowcomp;
    private static ICustomTexture[] customTexturesPrepare;
    private static String noiseTexturePath;
    private static DynamicDimension[] colorBufferSizes;
    private static CustomUniforms customUniforms;
    public static final boolean saveFinalShaders;
    public static float blockLightLevel05;
    public static float blockLightLevel06;
    public static float blockLightLevel08;
    public static float aoLevel;
    public static float sunPathRotation;
    public static float shadowAngleInterval;
    public static int fogMode;
    public static float fogDensity;
    public static float fogColorR;
    public static float fogColorG;
    public static float fogColorB;
    public static float shadowIntervalSize;
    public static int terrainIconSize;
    public static int[] terrainTextureSize;
    private static ICustomTexture noiseTexture;
    private static boolean noiseTextureEnabled;
    private static int noiseTextureResolution;
    static final int[] colorTextureImageUnit;
    static final int[] depthTextureImageUnit;
    static final int[] shadowColorTextureImageUnit;
    static final int[] shadowDepthTextureImageUnit;
    static final int[] colorImageUnit;
    static final int[] shadowColorImageUnit;
    private static final int bigBufferSize;
    private static final ByteBuffer bigBuffer;
    static final float[] faProjection;
    static final float[] faProjectionInverse;
    static final float[] faModelView;
    static final float[] faModelViewInverse;
    static final float[] faShadowProjection;
    static final float[] faShadowProjectionInverse;
    static final float[] faShadowModelView;
    static final float[] faShadowModelViewInverse;
    static final FloatBuffer projection;
    static final FloatBuffer projectionInverse;
    static final FloatBuffer modelView;
    static final FloatBuffer modelViewInverse;
    static final FloatBuffer shadowProjection;
    static final FloatBuffer shadowProjectionInverse;
    static final FloatBuffer shadowModelView;
    static final FloatBuffer shadowModelViewInverse;
    static final FloatBuffer previousProjection;
    static final FloatBuffer previousModelView;
    static final FloatBuffer tempMatrixDirectBuffer;
    static final FloatBuffer tempDirectFloatBuffer;
    static final DrawBuffers dfbDrawBuffers;
    static final DrawBuffers sfbDrawBuffers;
    static final DrawBuffers drawBuffersNone;
    static final DrawBuffers[] drawBuffersColorAtt;
    static boolean glDebugGroups;
    static boolean glDebugGroupProgram;
    static Map<T_2915_h, Integer> mapBlockToEntityData;
    private static final String[] formatNames;
    private static final int[] formatIds;
    private static final Pattern patternLoadEntityDataMap;
    public static int[] entityData;
    public static int entityDataIndex;

    private Shaders() {
    }

    private static ByteBuffer nextByteBuffer(int size) {
        ByteBuffer bytebuffer = bigBuffer;
        int i = bytebuffer.limit();
        ((Buffer)bytebuffer).position(i).limit(i + size);
        return bytebuffer.slice();
    }

    public static IntBuffer nextIntBuffer(int size) {
        ByteBuffer bytebuffer = bigBuffer;
        int i = bytebuffer.limit();
        ((Buffer)bytebuffer).position(i).limit(i + size * 4);
        return bytebuffer.asIntBuffer();
    }

    private static FloatBuffer nextFloatBuffer(int size) {
        ByteBuffer bytebuffer = bigBuffer;
        int i = bytebuffer.limit();
        ((Buffer)bytebuffer).position(i).limit(i + size * 4);
        return bytebuffer.asFloatBuffer();
    }

    private static IntBuffer[] nextIntBufferArray(int count, int size) {
        IntBuffer[] aintbuffer = new IntBuffer[count];
        for (int i = 0; i < count; ++i) {
            aintbuffer[i] = Shaders.nextIntBuffer(size);
        }
        return aintbuffer;
    }

    private static DrawBuffers[] makeDrawBuffersColorSingle(int count) {
        DrawBuffers[] adrawbuffers = new DrawBuffers[count];
        for (int i = 0; i < adrawbuffers.length; ++i) {
            DrawBuffers drawbuffers = new DrawBuffers("single" + i, 16, 8);
            drawbuffers.put(36064 + i);
            drawbuffers.position(0);
            drawbuffers.limit(1);
            adrawbuffers[i] = drawbuffers;
        }
        return adrawbuffers;
    }

    public static void loadConfig() {
        SMCLog.info("Load shaders configuration.");
        try {
            if (!shaderPacksDir.exists()) {
                shaderPacksDir.mkdir();
            }
        }
        catch (Exception exception2) {
            SMCLog.severe("Failed to open the shaderpacks directory: " + String.valueOf(shaderPacksDir));
        }
        shadersConfig = new PropertiesOrdered();
        shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), "");
        if (configFile.exists()) {
            try {
                FileReader filereader = new FileReader(configFile);
                shadersConfig.load(filereader);
                filereader.close();
            }
            catch (Exception filereader) {
                // empty catch block
            }
        }
        if (!configFile.exists()) {
            try {
                Shaders.storeConfig();
            }
            catch (Exception filereader) {
                // empty catch block
            }
        }
        EnumShaderOption[] aenumshaderoption = EnumShaderOption.values();
        for (int i = 0; i < aenumshaderoption.length; ++i) {
            EnumShaderOption enumshaderoption = aenumshaderoption[i];
            String s = enumshaderoption.getPropertyKey();
            String s1 = enumshaderoption.getValueDefault();
            String s2 = shadersConfig.getProperty(s, s1);
            Shaders.setEnumShaderOption(enumshaderoption, s2);
        }
        Shaders.loadShaderPack();
    }

    private static void setEnumShaderOption(EnumShaderOption eso, String str) {
        if (str == null) {
            str = eso.getValueDefault();
        }
        switch (eso) {
            case ANTIALIASING: {
                configAntialiasingLevel = Config.parseInt(str, 0);
                break;
            }
            case NORMAL_MAP: {
                configNormalMap = Config.parseBoolean(str, true);
                break;
            }
            case SPECULAR_MAP: {
                configSpecularMap = Config.parseBoolean(str, true);
                break;
            }
            case RENDER_RES_MUL: {
                configRenderResMul = Config.parseFloat(str, 1.0f);
                break;
            }
            case SHADOW_RES_MUL: {
                configShadowResMul = Config.parseFloat(str, 1.0f);
                break;
            }
            case HAND_DEPTH_MUL: {
                configHandDepthMul = Config.parseFloat(str, 0.125f);
                break;
            }
            case CLOUD_SHADOW: {
                configCloudShadow = Config.parseBoolean(str, true);
                break;
            }
            case OLD_HAND_LIGHT: {
                configOldHandLight.setPropertyValue(str);
                break;
            }
            case OLD_LIGHTING: {
                configOldLighting.setPropertyValue(str);
                break;
            }
            case SHADER_PACK: {
                currentShaderName = str;
                break;
            }
            case TWEAK_BLOCK_DAMAGE: {
                configTweakBlockDamage = Config.parseBoolean(str, true);
                break;
            }
            case SHADOW_CLIP_FRUSTRUM: {
                configShadowClipFrustrum = Config.parseBoolean(str, true);
                break;
            }
            case TEX_MIN_FIL_B: {
                configTexMinFilB = Config.parseInt(str, 0);
                break;
            }
            case TEX_MIN_FIL_N: {
                configTexMinFilN = Config.parseInt(str, 0);
                break;
            }
            case TEX_MIN_FIL_S: {
                configTexMinFilS = Config.parseInt(str, 0);
                break;
            }
            case TEX_MAG_FIL_B: {
                configTexMagFilB = Config.parseInt(str, 0);
                break;
            }
            case TEX_MAG_FIL_N: {
                configTexMagFilB = Config.parseInt(str, 0);
                break;
            }
            case TEX_MAG_FIL_S: {
                configTexMagFilB = Config.parseInt(str, 0);
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown option: " + String.valueOf((Object)eso));
            }
        }
    }

    public static void storeConfig() {
        SMCLog.info("Save shaders configuration.");
        if (shadersConfig == null) {
            shadersConfig = new PropertiesOrdered();
        }
        EnumShaderOption[] aenumshaderoption = EnumShaderOption.values();
        for (int i = 0; i < aenumshaderoption.length; ++i) {
            EnumShaderOption enumshaderoption = aenumshaderoption[i];
            String s = enumshaderoption.getPropertyKey();
            String s1 = Shaders.getEnumShaderOption(enumshaderoption);
            shadersConfig.setProperty(s, s1);
        }
        try {
            FileWriter filewriter = new FileWriter(configFile);
            shadersConfig.store(filewriter, (String)null);
            filewriter.close();
        }
        catch (Exception exception) {
            SMCLog.severe("Error saving configuration: " + exception.getClass().getName() + ": " + exception.getMessage());
        }
    }

    public static String getEnumShaderOption(EnumShaderOption eso) {
        switch (eso) {
            case ANTIALIASING: {
                return Integer.toString(configAntialiasingLevel);
            }
            case NORMAL_MAP: {
                return Boolean.toString(configNormalMap);
            }
            case SPECULAR_MAP: {
                return Boolean.toString(configSpecularMap);
            }
            case RENDER_RES_MUL: {
                return Float.toString(configRenderResMul);
            }
            case SHADOW_RES_MUL: {
                return Float.toString(configShadowResMul);
            }
            case HAND_DEPTH_MUL: {
                return Float.toString(configHandDepthMul);
            }
            case CLOUD_SHADOW: {
                return Boolean.toString(configCloudShadow);
            }
            case OLD_HAND_LIGHT: {
                return configOldHandLight.getPropertyValue();
            }
            case OLD_LIGHTING: {
                return configOldLighting.getPropertyValue();
            }
            case SHADER_PACK: {
                return currentShaderName;
            }
            case TWEAK_BLOCK_DAMAGE: {
                return Boolean.toString(configTweakBlockDamage);
            }
            case SHADOW_CLIP_FRUSTRUM: {
                return Boolean.toString(configShadowClipFrustrum);
            }
            case TEX_MIN_FIL_B: {
                return Integer.toString(configTexMinFilB);
            }
            case TEX_MIN_FIL_N: {
                return Integer.toString(configTexMinFilN);
            }
            case TEX_MIN_FIL_S: {
                return Integer.toString(configTexMinFilS);
            }
            case TEX_MAG_FIL_B: {
                return Integer.toString(configTexMagFilB);
            }
            case TEX_MAG_FIL_N: {
                return Integer.toString(configTexMagFilB);
            }
            case TEX_MAG_FIL_S: {
                return Integer.toString(configTexMagFilB);
            }
        }
        throw new IllegalArgumentException("Unknown option: " + String.valueOf((Object)eso));
    }

    public static void setShaderPack(String par1name) {
        currentShaderName = par1name;
        shadersConfig.setProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), par1name);
        Shaders.loadShaderPack();
    }

    public static void loadShaderPack() {
        boolean flag4;
        mc = MinecraftClient.A_4115_X();
        boolean flag = shaderPackLoaded;
        boolean flag1 = Shaders.isOldLighting();
        if (Shaders.mc.u_1723_Y != null) {
            Shaders.mc.u_1723_Y.C_2741_M();
        }
        shaderPackLoaded = false;
        if (shaderPack != null) {
            shaderPack.close();
            shaderPack = null;
            shaderPackResources.clear();
            shaderPackDimensions.clear();
            shaderPackOptions = null;
            shaderPackOptionSliders = null;
            shaderPackProfiles = null;
            shaderPackGuiScreens = null;
            shaderPackProgramConditions.clear();
            shaderPackClouds.resetValue();
            shaderPackOldHandLight.resetValue();
            shaderPackDynamicHandLight.resetValue();
            shaderPackOldLighting.resetValue();
            Shaders.resetCustomTextures();
            noiseTexturePath = null;
        }
        boolean flag2 = false;
        if (Config.isAntialiasing()) {
            SMCLog.info("Shaders can not be loaded, Antialiasing is enabled: " + Config.getAntialiasingLevel() + "x");
            flag2 = true;
        }
        if (Config.isGraphicsFabulous()) {
            SMCLog.info("Shaders can not be loaded, Fabulous Graphics is enabled.");
            flag2 = true;
        }
        String s = shadersConfig.getProperty(EnumShaderOption.SHADER_PACK.getPropertyKey(), SHADER_PACK_NAME_DEFAULT);
        if (!flag2) {
            shaderPack = Shaders.getShaderPack(s);
            boolean bl = shaderPackLoaded = shaderPack != null;
        }
        if (shaderPackLoaded) {
            SMCLog.info("Loaded shaderpack: " + Shaders.getShaderPackName());
        } else {
            SMCLog.info("No shaderpack loaded.");
            shaderPack = new ShaderPackNone();
        }
        if (saveFinalShaders) {
            Shaders.clearDirectory(new File(shaderPacksDir, "debug"));
        }
        Shaders.loadShaderPackResources();
        Shaders.loadShaderPackDimensions();
        shaderPackOptions = Shaders.loadShaderPackOptions();
        Shaders.loadShaderPackFixedProperties();
        Shaders.loadShaderPackDynamicProperties();
        boolean flag3 = shaderPackLoaded != flag;
        boolean bl = flag4 = Shaders.isOldLighting() != flag1;
        if (flag3 || flag4) {
            E_688_b.n_1700_B();
            if (Reflector.LightUtil.exists()) {
                Reflector.LightUtil_itemConsumer.setValue(null);
                Reflector.LightUtil_tessellator.setValue(null);
            }
            Shaders.updateBlockLightLevel();
        }
        if (mc.T_2506_i() != null) {
            CustomBlockLayers.update();
        }
        if (Shaders.mc.u_1723_Y != null) {
            Shaders.mc.u_1723_Y.k_2293_S();
        }
        if ((flag3 || flag4) && mc.T_2506_i() != null) {
            mc.Y_1740_V();
        }
    }

    public static IShaderPack getShaderPack(String name) {
        if (name == null) {
            return null;
        }
        if (!(name = name.trim()).isEmpty() && !name.equals(SHADER_PACK_NAME_NONE)) {
            if (name.equals(SHADER_PACK_NAME_DEFAULT)) {
                return new ShaderPackDefault();
            }
            try {
                File file1 = new File(shaderPacksDir, name);
                if (file1.isDirectory()) {
                    return new ShaderPackFolder(name, file1);
                }
                return file1.isFile() && name.toLowerCase().endsWith(".zip") ? new ShaderPackZip(name, file1) : null;
            }
            catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static IShaderPack getShaderPack() {
        return shaderPack;
    }

    private static void loadShaderPackDimensions() {
        shaderPackDimensions.clear();
        for (int i = -128; i <= 128; ++i) {
            String s = "/shaders/world" + i;
            if (!shaderPack.hasDirectory(s)) continue;
            shaderPackDimensions.add(i);
        }
        if (shaderPackDimensions.size() > 0) {
            Integer[] ainteger = shaderPackDimensions.toArray(new Integer[shaderPackDimensions.size()]);
            Config.dbg("[Shaders] Worlds: " + Config.arrayToString((Object[])ainteger));
        }
    }

    private static void loadShaderPackFixedProperties() {
        shaderPackOldLighting.resetValue();
        shaderPackSeparateAo.resetValue();
        if (shaderPack != null) {
            String s = PATH_SHADERS_PROPERTIES;
            try {
                InputStream inputstream = shaderPack.getResourceAsStream(s);
                if (inputstream == null) {
                    return;
                }
                inputstream = MacroProcessor.process(inputstream, s, false);
                PropertiesOrdered properties = new PropertiesOrdered();
                properties.load(inputstream);
                inputstream.close();
                shaderPackOldLighting.loadFrom(properties);
                shaderPackSeparateAo.loadFrom(properties);
                shaderPackOptionSliders = ShaderPackParser.parseOptionSliders(properties, shaderPackOptions);
                shaderPackProfiles = ShaderPackParser.parseProfiles(properties, shaderPackOptions);
                shaderPackGuiScreens = ShaderPackParser.parseGuiScreens(properties, shaderPackProfiles, shaderPackOptions);
            }
            catch (IOException ioexception) {
                Config.warn("[Shaders] Error reading: " + s);
            }
        }
    }

    private static void loadShaderPackDynamicProperties() {
        shaderPackClouds.resetValue();
        shaderPackOldHandLight.resetValue();
        shaderPackDynamicHandLight.resetValue();
        shaderPackShadowTerrain.resetValue();
        shaderPackShadowTranslucent.resetValue();
        shaderPackShadowEntities.resetValue();
        shaderPackShadowBlockEntities.resetValue();
        shaderPackUnderwaterOverlay.resetValue();
        shaderPackSun.resetValue();
        shaderPackMoon.resetValue();
        shaderPackVignette.resetValue();
        shaderPackBackFaceSolid.resetValue();
        shaderPackBackFaceCutout.resetValue();
        shaderPackBackFaceCutoutMipped.resetValue();
        shaderPackBackFaceTranslucent.resetValue();
        shaderPackRainDepth.resetValue();
        shaderPackBeaconBeamDepth.resetValue();
        shaderPackFrustumCulling.resetValue();
        BlockAliases.reset();
        ItemAliases.reset();
        EntityAliases.reset();
        customUniforms = null;
        for (int i = 0; i < ProgramsAll.length; ++i) {
            Program program = ProgramsAll[i];
            program.resetProperties();
        }
        Arrays.fill(colorBufferSizes, null);
        if (shaderPack != null) {
            BlockAliases.update(shaderPack);
            ItemAliases.update(shaderPack);
            EntityAliases.update(shaderPack);
            String s = PATH_SHADERS_PROPERTIES;
            try {
                InputStream inputstream = shaderPack.getResourceAsStream(s);
                if (inputstream == null) {
                    return;
                }
                inputstream = MacroProcessor.process(inputstream, s, true);
                PropertiesOrdered properties = new PropertiesOrdered();
                properties.load(inputstream);
                inputstream.close();
                shaderPackClouds.loadFrom(properties);
                shaderPackOldHandLight.loadFrom(properties);
                shaderPackDynamicHandLight.loadFrom(properties);
                shaderPackShadowTerrain.loadFrom(properties);
                shaderPackShadowTranslucent.loadFrom(properties);
                shaderPackShadowEntities.loadFrom(properties);
                shaderPackShadowBlockEntities.loadFrom(properties);
                shaderPackUnderwaterOverlay.loadFrom(properties);
                shaderPackSun.loadFrom(properties);
                shaderPackVignette.loadFrom(properties);
                shaderPackMoon.loadFrom(properties);
                shaderPackBackFaceSolid.loadFrom(properties);
                shaderPackBackFaceCutout.loadFrom(properties);
                shaderPackBackFaceCutoutMipped.loadFrom(properties);
                shaderPackBackFaceTranslucent.loadFrom(properties);
                shaderPackRainDepth.loadFrom(properties);
                shaderPackBeaconBeamDepth.loadFrom(properties);
                shaderPackFrustumCulling.loadFrom(properties);
                shaderPackProgramConditions = ShaderPackParser.parseProgramConditions(properties, shaderPackOptions);
                customTexturesGbuffers = Shaders.loadCustomTextures(properties, ProgramStage.GBUFFERS);
                customTexturesComposite = Shaders.loadCustomTextures(properties, ProgramStage.COMPOSITE);
                customTexturesDeferred = Shaders.loadCustomTextures(properties, ProgramStage.DEFERRED);
                customTexturesShadowcomp = Shaders.loadCustomTextures(properties, ProgramStage.SHADOWCOMP);
                customTexturesPrepare = Shaders.loadCustomTextures(properties, ProgramStage.PREPARE);
                noiseTexturePath = properties.getProperty("texture.noise");
                if (noiseTexturePath != null) {
                    noiseTextureEnabled = true;
                }
                customUniforms = ShaderPackParser.parseCustomUniforms(properties);
                ShaderPackParser.parseAlphaStates(properties);
                ShaderPackParser.parseBlendStates(properties);
                ShaderPackParser.parseRenderScales(properties);
                ShaderPackParser.parseBuffersFlip(properties);
                colorBufferSizes = ShaderPackParser.parseBufferSizes(properties, 16);
            }
            catch (IOException ioexception) {
                Config.warn("[Shaders] Error reading: " + s);
            }
        }
    }

    private static ICustomTexture[] loadCustomTextures(Properties props, ProgramStage stage) {
        String s = "texture." + stage.getName() + ".";
        Set<Object> set = props.keySet();
        ArrayList<ICustomTexture> list = new ArrayList<ICustomTexture>();
        for (String string : set) {
            if (!string.startsWith(s)) continue;
            String s2 = StrUtils.removePrefix(string, s);
            s2 = StrUtils.removeSuffix(s2, new String[]{".0", ".1", ".2", ".3", ".4", ".5", ".6", ".7", ".8", ".9"});
            String s3 = props.getProperty(string).trim();
            int i = Shaders.getTextureIndex(stage, s2);
            if (i < 0) {
                SMCLog.warning("Invalid texture name: " + string);
                continue;
            }
            ICustomTexture icustomtexture = Shaders.loadCustomTexture(i, s3);
            if (icustomtexture == null) continue;
            SMCLog.info("Custom texture: " + string + " = " + s3);
            list.add(icustomtexture);
        }
        if (list.size() <= 0) {
            return null;
        }
        ICustomTexture[] aicustomtexture = list.toArray(new ICustomTexture[list.size()]);
        return aicustomtexture;
    }

    private static ICustomTexture loadCustomTexture(int textureUnit, String path) {
        if (path == null) {
            return null;
        }
        if ((path = path.trim()).indexOf(58) >= 0) {
            return Shaders.loadCustomTextureLocation(textureUnit, path);
        }
        return path.indexOf(32) >= 0 ? Shaders.loadCustomTextureRaw(textureUnit, path) : Shaders.loadCustomTextureShaders(textureUnit, path);
    }

    private static ICustomTexture loadCustomTextureLocation(int textureUnit, String path) {
        String s = path.trim();
        int i = 0;
        if (s.startsWith("minecraft:textures/")) {
            if ((s = StrUtils.addSuffixCheck(s, ".png")).endsWith("_n.png")) {
                s = StrUtils.replaceSuffix(s, "_n.png", ".png");
                i = 1;
            } else if (s.endsWith("_s.png")) {
                s = StrUtils.replaceSuffix(s, "_s.png", ".png");
                i = 2;
            }
        }
        if (s.startsWith("minecraft:dynamic/lightmap_")) {
            s = s.replace("lightmap", "light_map");
        }
        g_2336_b resourcelocation = new g_2336_b(s);
        return new CustomTextureLocation(textureUnit, resourcelocation, i);
    }

    private static void reloadCustomTexturesLocation(ICustomTexture[] cts) {
        if (cts != null) {
            for (int i = 0; i < cts.length; ++i) {
                ICustomTexture icustomtexture = cts[i];
                if (!(icustomtexture instanceof CustomTextureLocation)) continue;
                CustomTextureLocation customtexturelocation = (CustomTextureLocation)icustomtexture;
                customtexturelocation.reloadTexture();
            }
        }
    }

    private static ICustomTexture loadCustomTextureRaw(int textureUnit, String line) {
        ConnectedParser connectedparser = new ConnectedParser("Shaders");
        String[] astring = Config.tokenize(line, " ");
        ArrayDeque<String> deque = new ArrayDeque<String>(Arrays.asList(astring));
        String s = (String)deque.poll();
        TextureType texturetype = (TextureType)connectedparser.parseEnum((String)deque.poll(), TextureType.values(), "texture type");
        if (texturetype == null) {
            SMCLog.warning("Invalid raw texture type: " + line);
            return null;
        }
        InternalFormat internalformat = (InternalFormat)connectedparser.parseEnum((String)deque.poll(), InternalFormat.values(), "internal format");
        if (internalformat == null) {
            SMCLog.warning("Invalid raw texture internal format: " + line);
            return null;
        }
        int i = 0;
        int j = 0;
        int k = 0;
        switch (texturetype) {
            case TEXTURE_1D: {
                i = connectedparser.parseInt((String)deque.poll(), -1);
                break;
            }
            case TEXTURE_2D: {
                i = connectedparser.parseInt((String)deque.poll(), -1);
                j = connectedparser.parseInt((String)deque.poll(), -1);
                break;
            }
            case TEXTURE_3D: {
                i = connectedparser.parseInt((String)deque.poll(), -1);
                j = connectedparser.parseInt((String)deque.poll(), -1);
                k = connectedparser.parseInt((String)deque.poll(), -1);
                break;
            }
            case TEXTURE_RECTANGLE: {
                i = connectedparser.parseInt((String)deque.poll(), -1);
                j = connectedparser.parseInt((String)deque.poll(), -1);
                break;
            }
            default: {
                SMCLog.warning("Invalid raw texture type: " + String.valueOf((Object)texturetype));
                return null;
            }
        }
        if (i >= 0 && j >= 0 && k >= 0) {
            PixelFormat pixelformat = (PixelFormat)connectedparser.parseEnum((String)deque.poll(), PixelFormat.values(), "pixel format");
            if (pixelformat == null) {
                SMCLog.warning("Invalid raw texture pixel format: " + line);
                return null;
            }
            PixelType pixeltype = (PixelType)connectedparser.parseEnum((String)deque.poll(), PixelType.values(), "pixel type");
            if (pixeltype == null) {
                SMCLog.warning("Invalid raw texture pixel type: " + line);
                return null;
            }
            if (!deque.isEmpty()) {
                SMCLog.warning("Invalid raw texture, too many parameters: " + line);
                return null;
            }
            return Shaders.loadCustomTextureRaw(textureUnit, line, s, texturetype, internalformat, i, j, k, pixelformat, pixeltype);
        }
        SMCLog.warning("Invalid raw texture size: " + line);
        return null;
    }

    private static ICustomTexture loadCustomTextureRaw(int textureUnit, String line, String path, TextureType type, InternalFormat internalFormat, int width, int height, int depth, PixelFormat pixelFormat, PixelType pixelType) {
        try {
            String s = "shaders/" + StrUtils.removePrefix(path, "/");
            InputStream inputstream = shaderPack.getResourceAsStream(s);
            if (inputstream == null) {
                SMCLog.warning("Raw texture not found: " + path);
                return null;
            }
            byte[] abyte = Config.readAll(inputstream);
            IOUtils.closeQuietly((InputStream)inputstream);
            ByteBuffer bytebuffer = q_383_x.n_1700_B(abyte.length);
            bytebuffer.put(abyte);
            ((Buffer)bytebuffer).flip();
            TextureMetadataSection texturemetadatasection = SimpleShaderTexture.loadTextureMetadataSection(s, new TextureMetadataSection(true, true));
            return new CustomTextureRaw(type, internalFormat, width, height, depth, pixelFormat, pixelType, bytebuffer, textureUnit, texturemetadatasection.n_1700_B(), texturemetadatasection.J_1907_R());
        }
        catch (IOException ioexception) {
            SMCLog.warning("Error loading raw texture: " + path);
            SMCLog.warning(ioexception.getClass().getName() + ": " + ioexception.getMessage());
            return null;
        }
    }

    private static ICustomTexture loadCustomTextureShaders(int textureUnit, String path) {
        if (((String)(path = ((String)path).trim())).indexOf(46) < 0) {
            path = (String)path + ".png";
        }
        try {
            String s = "shaders/" + StrUtils.removePrefix((String)path, "/");
            InputStream inputstream = shaderPack.getResourceAsStream(s);
            if (inputstream == null) {
                SMCLog.warning("Texture not found: " + (String)path);
                return null;
            }
            IOUtils.closeQuietly((InputStream)inputstream);
            SimpleShaderTexture simpleshadertexture = new SimpleShaderTexture(s);
            simpleshadertexture.loadTexture(mc.T_2506_i());
            return new CustomTexture(textureUnit, s, simpleshadertexture);
        }
        catch (IOException ioexception) {
            SMCLog.warning("Error loading texture: " + (String)path);
            SMCLog.warning(ioexception.getClass().getName() + ": " + ioexception.getMessage());
            return null;
        }
    }

    private static int getTextureIndex(ProgramStage stage, String name) {
        if (stage == ProgramStage.GBUFFERS) {
            int i = ShaderParser.getIndex(name, "colortex", 4, 15);
            if (i >= 0) {
                return colorTextureImageUnit[i];
            }
            if (name.equals("texture")) {
                return 0;
            }
            if (name.equals("lightmap")) {
                return 1;
            }
            if (name.equals("normals")) {
                return 2;
            }
            if (name.equals("specular")) {
                return 3;
            }
            if (name.equals("shadowtex0") || name.equals("watershadow")) {
                return 4;
            }
            if (name.equals("shadow")) {
                return waterShadowEnabled ? 5 : 4;
            }
            if (name.equals("shadowtex1")) {
                return 5;
            }
            if (name.equals("depthtex0")) {
                return 6;
            }
            if (name.equals("gaux1")) {
                return 7;
            }
            if (name.equals("gaux2")) {
                return 8;
            }
            if (name.equals("gaux3")) {
                return 9;
            }
            if (name.equals("gaux4")) {
                return 10;
            }
            if (name.equals("depthtex1")) {
                return 12;
            }
            if (name.equals("shadowcolor0") || name.equals("shadowcolor")) {
                return 13;
            }
            if (name.equals("shadowcolor1")) {
                return 14;
            }
            if (name.equals("noisetex")) {
                return 15;
            }
        }
        if (stage.isAnyComposite()) {
            int j = ShaderParser.getIndex(name, "colortex", 0, 15);
            if (j >= 0) {
                return colorTextureImageUnit[j];
            }
            if (name.equals("colortex0")) {
                return 0;
            }
            if (name.equals("gdepth")) {
                return 1;
            }
            if (name.equals("gnormal")) {
                return 2;
            }
            if (name.equals("composite")) {
                return 3;
            }
            if (name.equals("shadowtex0") || name.equals("watershadow")) {
                return 4;
            }
            if (name.equals("shadow")) {
                return waterShadowEnabled ? 5 : 4;
            }
            if (name.equals("shadowtex1")) {
                return 5;
            }
            if (name.equals("depthtex0") || name.equals("gdepthtex")) {
                return 6;
            }
            if (name.equals("gaux1")) {
                return 7;
            }
            if (name.equals("gaux2")) {
                return 8;
            }
            if (name.equals("gaux3")) {
                return 9;
            }
            if (name.equals("gaux4")) {
                return 10;
            }
            if (name.equals("depthtex1")) {
                return 11;
            }
            if (name.equals("depthtex2")) {
                return 12;
            }
            if (name.equals("shadowcolor0") || name.equals("shadowcolor")) {
                return 13;
            }
            if (name.equals("shadowcolor1")) {
                return 14;
            }
            if (name.equals("noisetex")) {
                return 15;
            }
        }
        return -1;
    }

    private static void bindCustomTextures(ICustomTexture[] cts) {
        if (cts != null) {
            for (int i = 0; i < cts.length; ++i) {
                ICustomTexture icustomtexture = cts[i];
                X_933_l.t_1786_h(33984 + icustomtexture.getTextureUnit());
                int j = icustomtexture.getTextureId();
                int k = icustomtexture.getTarget();
                if (k == 3553) {
                    X_933_l.w_1457_N(j);
                    continue;
                }
                GL11.glBindTexture((int)k, (int)j);
            }
            X_933_l.t_1786_h(33984);
        }
    }

    private static void resetCustomTextures() {
        Shaders.deleteCustomTextures(customTexturesGbuffers);
        Shaders.deleteCustomTextures(customTexturesComposite);
        Shaders.deleteCustomTextures(customTexturesDeferred);
        Shaders.deleteCustomTextures(customTexturesShadowcomp);
        Shaders.deleteCustomTextures(customTexturesPrepare);
        customTexturesGbuffers = null;
        customTexturesComposite = null;
        customTexturesDeferred = null;
        customTexturesShadowcomp = null;
        customTexturesPrepare = null;
    }

    private static void deleteCustomTextures(ICustomTexture[] cts) {
        if (cts != null) {
            for (int i = 0; i < cts.length; ++i) {
                ICustomTexture icustomtexture = cts[i];
                icustomtexture.deleteTexture();
            }
        }
    }

    public static ShaderOption[] getShaderPackOptions(String screenName) {
        Object[] ashaderoption = (ShaderOption[])shaderPackOptions.clone();
        if (shaderPackGuiScreens == null) {
            if (shaderPackProfiles != null) {
                ShaderOptionProfile shaderoptionprofile = new ShaderOptionProfile(shaderPackProfiles, (ShaderOption[])ashaderoption);
                ashaderoption = (ShaderOption[])Config.addObjectToArray(ashaderoption, shaderoptionprofile, 0);
            }
            return Shaders.getVisibleOptions((ShaderOption[])ashaderoption);
        }
        Object s = screenName != null ? "screen." + screenName : "screen";
        ScreenShaderOptions screenshaderoptions = shaderPackGuiScreens.get(s);
        if (screenshaderoptions == null) {
            return new ShaderOption[0];
        }
        ShaderOption[] ashaderoption1 = screenshaderoptions.getShaderOptions();
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        for (int i = 0; i < ashaderoption1.length; ++i) {
            ShaderOption shaderoption = ashaderoption1[i];
            if (shaderoption == null) {
                list.add(null);
                continue;
            }
            if (shaderoption instanceof ShaderOptionRest) {
                ShaderOption[] ashaderoption2 = Shaders.getShaderOptionsRest(shaderPackGuiScreens, (ShaderOption[])ashaderoption);
                list.addAll(Arrays.asList(ashaderoption2));
                continue;
            }
            list.add(shaderoption);
        }
        return list.toArray(new ShaderOption[list.size()]);
    }

    public static int getShaderPackColumns(String screenName, int def) {
        Object s;
        Object object = s = screenName != null ? "screen." + screenName : "screen";
        if (shaderPackGuiScreens == null) {
            return def;
        }
        ScreenShaderOptions screenshaderoptions = shaderPackGuiScreens.get(s);
        return screenshaderoptions == null ? def : screenshaderoptions.getColumns();
    }

    private static ShaderOption[] getShaderOptionsRest(Map<String, ScreenShaderOptions> mapScreens, ShaderOption[] ops) {
        HashSet<String> set = new HashSet<String>();
        for (String s : mapScreens.keySet()) {
            ScreenShaderOptions screenshaderoptions = mapScreens.get(s);
            ShaderOption[] ashaderoption = screenshaderoptions.getShaderOptions();
            for (int i = 0; i < ashaderoption.length; ++i) {
                ShaderOption shaderoption = ashaderoption[i];
                if (shaderoption == null) continue;
                set.add(shaderoption.getName());
            }
        }
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        for (int j = 0; j < ops.length; ++j) {
            String s1;
            ShaderOption shaderoption1 = ops[j];
            if (!shaderoption1.isVisible() || set.contains(s1 = shaderoption1.getName())) continue;
            list.add(shaderoption1);
        }
        return list.toArray(new ShaderOption[list.size()]);
    }

    public static ShaderOption getShaderOption(String name) {
        return ShaderUtils.getShaderOption(name, shaderPackOptions);
    }

    public static ShaderOption[] getShaderPackOptions() {
        return shaderPackOptions;
    }

    public static boolean isShaderPackOptionSlider(String name) {
        return shaderPackOptionSliders == null ? false : shaderPackOptionSliders.contains(name);
    }

    private static ShaderOption[] getVisibleOptions(ShaderOption[] ops) {
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        for (int i = 0; i < ops.length; ++i) {
            ShaderOption shaderoption = ops[i];
            if (!shaderoption.isVisible()) continue;
            list.add(shaderoption);
        }
        return list.toArray(new ShaderOption[list.size()]);
    }

    public static void saveShaderPackOptions() {
        Shaders.saveShaderPackOptions(shaderPackOptions, shaderPack);
    }

    private static void saveShaderPackOptions(ShaderOption[] sos, IShaderPack sp) {
        PropertiesOrdered properties = new PropertiesOrdered();
        if (shaderPackOptions != null) {
            for (int i = 0; i < sos.length; ++i) {
                ShaderOption shaderoption = sos[i];
                if (!shaderoption.isChanged() || !shaderoption.isEnabled()) continue;
                properties.setProperty(shaderoption.getName(), shaderoption.getValue());
            }
        }
        try {
            Shaders.saveOptionProperties(sp, properties);
        }
        catch (IOException ioexception) {
            Config.warn("[Shaders] Error saving configuration for " + shaderPack.getName());
            ioexception.printStackTrace();
        }
    }

    private static void saveOptionProperties(IShaderPack sp, Properties props) throws IOException {
        String s = "shaderpacks/" + sp.getName() + ".txt";
        File file1 = new File(MinecraftClient.A_4115_X().M_182_A, s);
        if (props.isEmpty()) {
            file1.delete();
        } else {
            FileOutputStream fileoutputstream = new FileOutputStream(file1);
            props.store(fileoutputstream, (String)null);
            fileoutputstream.flush();
            fileoutputstream.close();
        }
    }

    private static ShaderOption[] loadShaderPackOptions() {
        try {
            String[] astring = programs.getProgramNames();
            Properties properties = Shaders.loadOptionProperties(shaderPack);
            ShaderOption[] ashaderoption = ShaderPackParser.parseShaderPackOptions(shaderPack, astring, shaderPackDimensions);
            for (int i = 0; i < ashaderoption.length; ++i) {
                ShaderOption shaderoption = ashaderoption[i];
                String s = properties.getProperty(shaderoption.getName());
                if (s == null) continue;
                shaderoption.resetValue();
                if (shaderoption.setValue(s)) continue;
                Config.warn("[Shaders] Invalid value, option: " + shaderoption.getName() + ", value: " + s);
            }
            return ashaderoption;
        }
        catch (IOException ioexception) {
            Config.warn("[Shaders] Error reading configuration for " + shaderPack.getName());
            ioexception.printStackTrace();
            return null;
        }
    }

    private static Properties loadOptionProperties(IShaderPack sp) throws IOException {
        PropertiesOrdered properties = new PropertiesOrdered();
        String s = "shaderpacks/" + sp.getName() + ".txt";
        File file1 = new File(MinecraftClient.A_4115_X().M_182_A, s);
        if (file1.exists() && file1.isFile() && file1.canRead()) {
            FileInputStream fileinputstream = new FileInputStream(file1);
            properties.load(fileinputstream);
            fileinputstream.close();
            return properties;
        }
        return properties;
    }

    public static ShaderOption[] getChangedOptions(ShaderOption[] ops) {
        ArrayList<ShaderOption> list = new ArrayList<ShaderOption>();
        for (int i = 0; i < ops.length; ++i) {
            ShaderOption shaderoption = ops[i];
            if (!shaderoption.isEnabled() || !shaderoption.isChanged()) continue;
            list.add(shaderoption);
        }
        return list.toArray(new ShaderOption[list.size()]);
    }

    private static String applyOptions(String line, ShaderOption[] ops) {
        if (ops != null && ops.length > 0) {
            for (int i = 0; i < ops.length; ++i) {
                ShaderOption shaderoption = ops[i];
                if (!shaderoption.matchesLine(line)) continue;
                line = shaderoption.getSourceLine();
                break;
            }
            return line;
        }
        return line;
    }

    public static ArrayList listOfShaders() {
        ArrayList<String> arraylist = new ArrayList<String>();
        ArrayList<String> arraylist1 = new ArrayList<String>();
        try {
            if (!shaderPacksDir.exists()) {
                shaderPacksDir.mkdir();
            }
            File[] afile = shaderPacksDir.listFiles();
            for (int i = 0; i < afile.length; ++i) {
                File file1 = afile[i];
                String s = file1.getName();
                if (file1.isDirectory()) {
                    File file2;
                    if (s.equals("debug") || !(file2 = new File(file1, "shaders")).exists() || !file2.isDirectory()) continue;
                    arraylist.add(s);
                    continue;
                }
                if (!file1.isFile() || !s.toLowerCase().endsWith(".zip")) continue;
                arraylist1.add(s);
            }
        }
        catch (Exception afile) {
            // empty catch block
        }
        Collections.sort(arraylist, String.CASE_INSENSITIVE_ORDER);
        Collections.sort(arraylist1, String.CASE_INSENSITIVE_ORDER);
        ArrayList<String> arraylist2 = new ArrayList<String>();
        arraylist2.add(SHADER_PACK_NAME_NONE);
        arraylist2.add(SHADER_PACK_NAME_DEFAULT);
        arraylist2.addAll(arraylist);
        arraylist2.addAll(arraylist1);
        return arraylist2;
    }

    public static int checkFramebufferStatus(String location) {
        int i = EXTFramebufferObject.glCheckFramebufferStatusEXT((int)36160);
        if (i != 36053) {
            SMCLog.severe("FramebufferStatus 0x%04X at '%s'", i, location);
        }
        return i;
    }

    public static int checkGLError(String location) {
        int i = X_933_l.g_164_R();
        if (i != 0 && GlErrors.isEnabled(i)) {
            String s = Config.getGlErrorString(i);
            String s1 = Shaders.getErrorInfo(i, location);
            String s2 = String.format("OpenGL error: %s (%s)%s, at: %s", i, s, s1, location);
            SMCLog.severe(s2);
            if (Config.isShowGlErrors() && TimedEvent.isActive("ShowGlErrorShaders", 10000L)) {
                String s3 = K_1289_S.n_1700_B("of.message.openglError", i, s);
                Shaders.printChat(s3);
            }
        }
        return i;
    }

    private static String getErrorInfo(int errorCode, String location) {
        String s2;
        StringBuilder stringbuilder = new StringBuilder();
        if (errorCode == 1286) {
            int i = EXTFramebufferObject.glCheckFramebufferStatusEXT((int)36160);
            String s = Shaders.getFramebufferStatusText(i);
            String s1 = ", fbStatus: " + i + " (" + s + ")";
            stringbuilder.append(s1);
        }
        if ((s2 = activeProgram.getName()).isEmpty()) {
            s2 = "none";
        }
        stringbuilder.append(", program: " + s2);
        Program program = Shaders.getProgramById(activeProgramID);
        if (program != activeProgram) {
            String s3 = program.getName();
            if (s3.isEmpty()) {
                s3 = "none";
            }
            stringbuilder.append(" (" + s3 + ")");
        }
        if (location.equals("setDrawBuffers")) {
            stringbuilder.append(", drawBuffers: " + ArrayUtils.arrayToString(activeProgram.getDrawBufSettings()));
        }
        return stringbuilder.toString();
    }

    private static Program getProgramById(int programID) {
        for (int i = 0; i < ProgramsAll.length; ++i) {
            Program program = ProgramsAll[i];
            if (program.getId() != programID) continue;
            return program;
        }
        return ProgramNone;
    }

    private static String getFramebufferStatusText(int fbStatusCode) {
        switch (fbStatusCode) {
            case 33305: {
                return "Undefined";
            }
            case 36053: {
                return "Complete";
            }
            case 36054: {
                return "Incomplete attachment";
            }
            case 36055: {
                return "Incomplete missing attachment";
            }
            case 36059: {
                return "Incomplete draw buffer";
            }
            case 36060: {
                return "Incomplete read buffer";
            }
            case 36061: {
                return "Unsupported";
            }
            case 36182: {
                return "Incomplete multisample";
            }
            case 36264: {
                return "Incomplete layer targets";
            }
        }
        return "Unknown";
    }

    private static void printChat(String str) {
        Shaders.mc.M_588_G.R_4764_Y().n_1700_B(new U_2871_b(str));
    }

    public static void printChatAndLogError(String str) {
        SMCLog.severe(str);
        Shaders.mc.M_588_G.R_4764_Y().n_1700_B(new U_2871_b(str));
    }

    public static void printIntBuffer(String title, IntBuffer buf) {
        StringBuilder stringbuilder = new StringBuilder(128);
        stringbuilder.append(title).append(" [pos ").append(buf.position()).append(" lim ").append(buf.limit()).append(" cap ").append(buf.capacity()).append(" :");
        int i = buf.limit();
        for (int j = 0; j < i; ++j) {
            stringbuilder.append(" ").append(buf.get(j));
        }
        stringbuilder.append("]");
        SMCLog.info(stringbuilder.toString());
    }

    public static void startup(MinecraftClient mc) {
        Shaders.checkShadersModInstalled();
        Shaders.mc = mc;
        mc = MinecraftClient.A_4115_X();
        capabilities = GL.getCapabilities();
        glVersionString = GL11.glGetString((int)7938);
        glVendorString = GL11.glGetString((int)7936);
        glRendererString = GL11.glGetString((int)7937);
        SMCLog.info("OpenGL Version: " + glVersionString);
        SMCLog.info("Vendor:  " + glVendorString);
        SMCLog.info("Renderer: " + glRendererString);
        SMCLog.info("Capabilities: " + (Shaders.capabilities.OpenGL20 ? " 2.0 " : " - ") + (Shaders.capabilities.OpenGL21 ? " 2.1 " : " - ") + (Shaders.capabilities.OpenGL30 ? " 3.0 " : " - ") + (Shaders.capabilities.OpenGL32 ? " 3.2 " : " - ") + (Shaders.capabilities.OpenGL40 ? " 4.0 " : " - "));
        SMCLog.info("GL_MAX_DRAW_BUFFERS: " + GL43.glGetInteger((int)34852));
        SMCLog.info("GL_MAX_COLOR_ATTACHMENTS_EXT: " + GL43.glGetInteger((int)36063));
        SMCLog.info("GL_MAX_TEXTURE_IMAGE_UNITS: " + GL43.glGetInteger((int)34930));
        hasGlGenMipmap = Shaders.capabilities.OpenGL30;
        boolean bl = glDebugGroups = Boolean.getBoolean("gl.debug.groups") && Shaders.capabilities.GL_KHR_debug;
        if (glDebugGroups) {
            SMCLog.info("glDebugGroups: true");
        }
        Shaders.loadConfig();
    }

    public static void updateBlockLightLevel() {
        if (Shaders.isOldLighting()) {
            blockLightLevel05 = 0.5f;
            blockLightLevel06 = 0.6f;
            blockLightLevel08 = 0.8f;
        } else {
            blockLightLevel05 = 1.0f;
            blockLightLevel06 = 1.0f;
            blockLightLevel08 = 1.0f;
        }
    }

    public static boolean isOldHandLight() {
        if (!configOldHandLight.isDefault()) {
            return configOldHandLight.isTrue();
        }
        return !shaderPackOldHandLight.isDefault() ? shaderPackOldHandLight.isTrue() : true;
    }

    public static boolean isDynamicHandLight() {
        return !shaderPackDynamicHandLight.isDefault() ? shaderPackDynamicHandLight.isTrue() : true;
    }

    public static boolean isOldLighting() {
        if (!configOldLighting.isDefault()) {
            return configOldLighting.isTrue();
        }
        return !shaderPackOldLighting.isDefault() ? shaderPackOldLighting.isTrue() : true;
    }

    public static boolean isRenderShadowTerrain() {
        return !shaderPackShadowTerrain.isFalse();
    }

    public static boolean isRenderShadowTranslucent() {
        return !shaderPackShadowTranslucent.isFalse();
    }

    public static boolean isRenderShadowEntities() {
        return !shaderPackShadowEntities.isFalse();
    }

    public static boolean isRenderShadowBlockEntities() {
        return !shaderPackShadowBlockEntities.isFalse();
    }

    public static boolean isUnderwaterOverlay() {
        return !shaderPackUnderwaterOverlay.isFalse();
    }

    public static boolean isSun() {
        return !shaderPackSun.isFalse();
    }

    public static boolean isMoon() {
        return !shaderPackMoon.isFalse();
    }

    public static boolean isVignette() {
        return !shaderPackVignette.isFalse();
    }

    public static boolean isRenderBackFace(o_2576_A blockLayerIn) {
        if (blockLayerIn == RenderTypes.SOLID) {
            return shaderPackBackFaceSolid.isTrue();
        }
        if (blockLayerIn == RenderTypes.CUTOUT) {
            return shaderPackBackFaceCutout.isTrue();
        }
        if (blockLayerIn == RenderTypes.CUTOUT_MIPPED) {
            return shaderPackBackFaceCutoutMipped.isTrue();
        }
        return blockLayerIn == RenderTypes.TRANSLUCENT ? shaderPackBackFaceTranslucent.isTrue() : false;
    }

    public static boolean isRainDepth() {
        return shaderPackRainDepth.isTrue();
    }

    public static boolean isBeaconBeamDepth() {
        return shaderPackBeaconBeamDepth.isTrue();
    }

    public static boolean isSeparateAo() {
        return shaderPackSeparateAo.isTrue();
    }

    public static boolean isFrustumCulling() {
        return !shaderPackFrustumCulling.isFalse();
    }

    public static void init() {
        boolean flag;
        if (!isInitializedOnce) {
            isInitializedOnce = true;
            flag = true;
        } else {
            flag = false;
        }
        if (!isShaderPackInitialized) {
            int i;
            Shaders.checkGLError("Shaders.init pre");
            if (Shaders.getShaderPackName() != null) {
                // empty if block
            }
            if (!Shaders.capabilities.OpenGL20) {
                Shaders.printChatAndLogError("No OpenGL 2.0");
            }
            if (!Shaders.capabilities.GL_EXT_framebuffer_object) {
                Shaders.printChatAndLogError("No EXT_framebuffer_object");
            }
            dfbDrawBuffers.position(0).limit(8);
            sfbDrawBuffers.position(0).limit(8);
            usedColorBuffers = 4;
            usedDepthBuffers = 1;
            usedShadowColorBuffers = 0;
            usedShadowDepthBuffers = 0;
            usedColorAttachs = 1;
            usedDrawBuffers = 1;
            bindImageTextures = false;
            Arrays.fill(gbuffersFormat, 6408);
            Arrays.fill(gbuffersClear, true);
            Arrays.fill(gbuffersClearColor, null);
            Arrays.fill(shadowBuffersFormat, 6408);
            Arrays.fill(shadowBuffersClear, true);
            Arrays.fill(shadowBuffersClearColor, null);
            Arrays.fill(shadowHardwareFilteringEnabled, false);
            Arrays.fill(shadowMipmapEnabled, false);
            Arrays.fill(shadowFilterNearest, false);
            Arrays.fill(shadowColorMipmapEnabled, false);
            Arrays.fill(shadowColorFilterNearest, false);
            centerDepthSmoothEnabled = false;
            noiseTextureEnabled = false;
            sunPathRotation = 0.0f;
            shadowIntervalSize = 2.0f;
            shadowMapWidth = 1024;
            shadowMapHeight = 1024;
            spShadowMapWidth = 1024;
            spShadowMapHeight = 1024;
            shadowMapFOV = 90.0f;
            shadowMapHalfPlane = 160.0f;
            shadowMapIsOrtho = true;
            shadowDistanceRenderMul = -1.0f;
            aoLevel = -1.0f;
            useEntityAttrib = false;
            useMidTexCoordAttrib = false;
            useTangentAttrib = false;
            useVelocityAttrib = false;
            waterShadowEnabled = false;
            hasGeometryShaders = false;
            Shaders.updateBlockLightLevel();
            Smoother.resetValues();
            shaderUniforms.reset();
            if (customUniforms != null) {
                customUniforms.reset();
            }
            ShaderProfile shaderprofile = ShaderUtils.detectProfile(shaderPackProfiles, shaderPackOptions, false);
            Object s = "";
            if (currentWorld != null && shaderPackDimensions.contains(i = WorldUtils.getDimensionId(currentWorld.g_2268_R()))) {
                s = "world" + i + "/";
            }
            Shaders.loadShaderPackDynamicProperties();
            for (int k = 0; k < ProgramsAll.length; ++k) {
                Program program = ProgramsAll[k];
                program.resetId();
                program.resetConfiguration();
                if (program.getProgramStage() == ProgramStage.NONE) continue;
                String s1 = program.getName();
                String s2 = (String)s + s1;
                boolean flag1 = true;
                if (shaderPackProgramConditions.containsKey(s2)) {
                    boolean bl = flag1 = flag1 && shaderPackProgramConditions.get(s2).eval();
                }
                if (shaderprofile != null) {
                    boolean bl = flag1 = flag1 && !shaderprofile.isProgramDisabled(s2);
                }
                if (!flag1) {
                    SMCLog.info("Program disabled: " + s2);
                    s1 = "<disabled>";
                    s2 = (String)s + s1;
                }
                String s3 = "/shaders/" + s2;
                String s4 = s3 + ".vsh";
                String s5 = s3 + ".gsh";
                String s6 = s3 + ".fsh";
                ComputeProgram[] acomputeprogram = Shaders.setupComputePrograms(program, "/shaders/", s2, ".csh");
                program.setComputePrograms(acomputeprogram);
                Config.sleep(10L);
                Shaders.setupProgram(program, s4, s5, s6);
                int j = program.getId();
                if (j > 0) {
                    SMCLog.info("Program loaded: " + s2);
                }
                Shaders.initDrawBuffers(program);
                Shaders.initBlendStatesIndexed(program);
                Shaders.updateToggleBuffers(program);
                Shaders.updateProgramSize(program);
            }
            hasDeferredPrograms = ProgramUtils.hasActive(ProgramsDeferred);
            hasShadowcompPrograms = ProgramUtils.hasActive(ProgramsShadowcomp);
            hasPreparePrograms = ProgramUtils.hasActive(ProgramsPrepare);
            usedColorAttachs = usedColorBuffers;
            if (usedShadowDepthBuffers > 0 || usedShadowColorBuffers > 0) {
                hasShadowMap = true;
                usedShadowDepthBuffers = Math.max(usedShadowDepthBuffers, 1);
            }
            shouldSkipDefaultShadow = hasShadowMap;
            SMCLog.info("usedColorBuffers: " + usedColorBuffers);
            SMCLog.info("usedDepthBuffers: " + usedDepthBuffers);
            SMCLog.info("usedShadowColorBuffers: " + usedShadowColorBuffers);
            SMCLog.info("usedShadowDepthBuffers: " + usedShadowDepthBuffers);
            SMCLog.info("usedColorAttachs: " + usedColorAttachs);
            SMCLog.info("usedDrawBuffers: " + usedDrawBuffers);
            SMCLog.info("bindImageTextures: " + bindImageTextures);
            int l = GL43.glGetInteger((int)34852);
            if (usedDrawBuffers > l) {
                Shaders.printChatAndLogError("[Shaders] Error: Not enough draw buffers, needed: " + usedDrawBuffers + ", available: " + l);
                usedDrawBuffers = l;
            }
            dfbDrawBuffers.position(0).limit(usedDrawBuffers);
            for (int i1 = 0; i1 < usedDrawBuffers; ++i1) {
                dfbDrawBuffers.put(i1, 36064 + i1);
            }
            sfbDrawBuffers.position(0).limit(usedShadowColorBuffers);
            for (int j1 = 0; j1 < usedShadowColorBuffers; ++j1) {
                sfbDrawBuffers.put(j1, 36064 + j1);
            }
            for (int k1 = 0; k1 < ProgramsAll.length; ++k1) {
                Program program1;
                Program program2;
                for (program2 = program1 = ProgramsAll[k1]; program2.getId() == 0 && program2.getProgramBackup() != program2; program2 = program2.getProgramBackup()) {
                }
                if (program2 == program1 || program1 == ProgramShadow) continue;
                program1.copyFrom(program2);
            }
            Shaders.resize();
            Shaders.resizeShadow();
            if (noiseTextureEnabled) {
                Shaders.setupNoiseTexture();
            }
            if (defaultTexture == null) {
                defaultTexture = ShadersTex.createDefaultTexture();
            }
            g_221_o matrixstack = new g_221_o();
            matrixstack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-90.0f));
            Shaders.preCelestialRotate(matrixstack);
            Shaders.postCelestialRotate(matrixstack);
            isShaderPackInitialized = true;
            Shaders.loadEntityDataMap();
            Shaders.resetDisplayLists();
            if (!flag) {
                // empty if block
            }
            Shaders.checkGLError("Shaders.init");
        }
    }

    private static void initDrawBuffers(Program p) {
        int i = GL43.glGetInteger((int)34852);
        Arrays.fill(p.getToggleColorTextures(), false);
        if (p == ProgramFinal) {
            p.setDrawBuffers(null);
        } else if (p.getId() == 0) {
            if (p == ProgramShadow) {
                p.setDrawBuffers(drawBuffersNone);
            } else {
                p.setDrawBuffers(drawBuffersColorAtt[0]);
            }
        } else {
            String[] astring = p.getDrawBufSettings();
            if (astring == null) {
                if (p != ProgramShadow && p != ProgramShadowSolid && p != ProgramShadowCutout) {
                    p.setDrawBuffers(dfbDrawBuffers);
                    usedDrawBuffers = Math.min(usedColorBuffers, i);
                    Arrays.fill(p.getToggleColorTextures(), 0, usedColorBuffers, true);
                } else {
                    p.setDrawBuffers(sfbDrawBuffers);
                }
            } else {
                String s1;
                DrawBuffers drawbuffers = p.getDrawBuffersCustom();
                int j = astring.length;
                usedDrawBuffers = Math.max(usedDrawBuffers, j);
                j = Math.min(j, i);
                p.setDrawBuffers(drawbuffers);
                drawbuffers.limit(j);
                for (int k = 0; k < j; ++k) {
                    int l = Shaders.getDrawBuffer(p, astring[k]);
                    drawbuffers.put(k, l);
                }
                String s = drawbuffers.getInfo(false);
                if (!Config.equals(s, s1 = drawbuffers.getInfo(true))) {
                    SMCLog.info("Draw buffers: " + s + " -> " + s1);
                }
            }
        }
    }

    private static void initBlendStatesIndexed(Program p) {
        GlBlendState[] aglblendstate = p.getBlendStatesColorIndexed();
        if (aglblendstate != null) {
            for (int i = 0; i < aglblendstate.length; ++i) {
                GlBlendState glblendstate = aglblendstate[i];
                if (glblendstate == null) continue;
                String s = Integer.toHexString(i).toUpperCase();
                int j = 36064 + i;
                int k = p.getDrawBuffers().indexOf(j);
                if (k < 0) {
                    SMCLog.warning("Blend buffer not used in draw buffers: " + s);
                    continue;
                }
                p.setBlendStateIndexed(k, glblendstate);
                SMCLog.info("Blend buffer: " + s);
            }
        }
    }

    private static int getDrawBuffer(Program p, String str) {
        int i = 0;
        int j = Config.parseInt(str, -1);
        if (p == ProgramShadow) {
            if (j >= 0 && j < 2) {
                i = 36064 + j;
                usedShadowColorBuffers = Math.max(usedShadowColorBuffers, j + 1);
            }
            return i;
        }
        if (j >= 0 && j < 16) {
            p.getToggleColorTextures()[j] = true;
            i = 36064 + j;
            usedColorAttachs = Math.max(usedColorAttachs, j + 1);
            usedColorBuffers = Math.max(usedColorBuffers, j + 1);
        }
        return i;
    }

    private static void updateToggleBuffers(Program p) {
        boolean[] aboolean = p.getToggleColorTextures();
        Boolean[] aboolean1 = p.getBuffersFlip();
        for (int i = 0; i < aboolean1.length; ++i) {
            Boolean obool = aboolean1[i];
            if (obool == null) continue;
            aboolean[i] = obool;
        }
    }

    private static void updateProgramSize(Program p) {
        if (p.getProgramStage().isMainComposite()) {
            DynamicDimension dynamicdimension = null;
            int i = 0;
            int j = 0;
            DrawBuffers drawbuffers = p.getDrawBuffers();
            if (drawbuffers != null) {
                for (int k = 0; k < drawbuffers.limit(); ++k) {
                    DynamicDimension dynamicdimension1;
                    int l = drawbuffers.get(k);
                    int i1 = l - 36064;
                    if (i1 < 0 || i1 >= colorBufferSizes.length || (dynamicdimension1 = colorBufferSizes[i1]) == null) continue;
                    ++i;
                    if (dynamicdimension == null) {
                        dynamicdimension = dynamicdimension1;
                    }
                    if (!dynamicdimension1.equals(dynamicdimension)) continue;
                    ++j;
                }
                if (i != 0) {
                    if (j != drawbuffers.limit()) {
                        SMCLog.severe("Program " + p.getName() + " draws to buffers with different sizes");
                    } else {
                        p.setDrawSize(dynamicdimension);
                    }
                }
            }
        }
    }

    public static void resetDisplayLists() {
        SMCLog.info("Reset model renderers");
        ++countResetDisplayLists;
        SMCLog.info("Reset world renderers");
        Shaders.mc.u_1723_Y.P_1922_E();
    }

    private static void setupProgram(Program program, String vShaderPath, String gShaderPath, String fShaderPath) {
        Shaders.checkGLError("pre setupProgram");
        progUseEntityAttrib = false;
        progUseMidTexCoordAttrib = false;
        progUseTangentAttrib = false;
        progUseVelocityAttrib = false;
        progUseMidBlockAttrib = false;
        int i = Shaders.createVertShader(program, vShaderPath);
        int j = Shaders.createGeomShader(program, gShaderPath);
        int k = Shaders.createFragShader(program, fShaderPath);
        Shaders.checkGLError("create");
        if (i != 0 || j != 0 || k != 0) {
            int l = ARBShaderObjects.glCreateProgramObjectARB();
            Shaders.checkGLError("create");
            if (i != 0) {
                ARBShaderObjects.glAttachObjectARB((int)l, (int)i);
                Shaders.checkGLError("attach");
            }
            if (j != 0) {
                ARBShaderObjects.glAttachObjectARB((int)l, (int)j);
                Shaders.checkGLError("attach");
                if (progArbGeometryShader4) {
                    ARBGeometryShader4.glProgramParameteriARB((int)l, (int)36315, (int)4);
                    ARBGeometryShader4.glProgramParameteriARB((int)l, (int)36316, (int)5);
                    ARBGeometryShader4.glProgramParameteriARB((int)l, (int)36314, (int)progMaxVerticesOut);
                    Shaders.checkGLError("arbGeometryShader4");
                }
                if (progExtGeometryShader4) {
                    EXTGeometryShader4.glProgramParameteriEXT((int)l, (int)36315, (int)4);
                    EXTGeometryShader4.glProgramParameteriEXT((int)l, (int)36316, (int)5);
                    EXTGeometryShader4.glProgramParameteriEXT((int)l, (int)36314, (int)progMaxVerticesOut);
                    Shaders.checkGLError("extGeometryShader4");
                }
                hasGeometryShaders = true;
            }
            if (k != 0) {
                ARBShaderObjects.glAttachObjectARB((int)l, (int)k);
                Shaders.checkGLError("attach");
            }
            if (progUseEntityAttrib) {
                ARBVertexShader.glBindAttribLocationARB((int)l, (int)entityAttrib, (CharSequence)"mc_Entity");
                Shaders.checkGLError("mc_Entity");
            }
            if (progUseMidTexCoordAttrib) {
                ARBVertexShader.glBindAttribLocationARB((int)l, (int)midTexCoordAttrib, (CharSequence)"mc_midTexCoord");
                Shaders.checkGLError("mc_midTexCoord");
            }
            if (progUseTangentAttrib) {
                ARBVertexShader.glBindAttribLocationARB((int)l, (int)tangentAttrib, (CharSequence)"at_tangent");
                Shaders.checkGLError("at_tangent");
            }
            if (progUseVelocityAttrib) {
                ARBVertexShader.glBindAttribLocationARB((int)l, (int)velocityAttrib, (CharSequence)"at_velocity");
                Shaders.checkGLError("at_velocity");
            }
            if (progUseMidBlockAttrib) {
                ARBVertexShader.glBindAttribLocationARB((int)l, (int)midBlockAttrib, (CharSequence)"at_midBlock");
                Shaders.checkGLError("at_midBlock");
            }
            ARBShaderObjects.glLinkProgramARB((int)l);
            if (GL43.glGetProgrami((int)l, (int)35714) != 1) {
                SMCLog.severe("Error linking program: " + l + " (" + program.getName() + ")");
            }
            Shaders.printLogInfo(l, program.getName());
            if (i != 0) {
                ARBShaderObjects.glDetachObjectARB((int)l, (int)i);
                ARBShaderObjects.glDeleteObjectARB((int)i);
            }
            if (j != 0) {
                ARBShaderObjects.glDetachObjectARB((int)l, (int)j);
                ARBShaderObjects.glDeleteObjectARB((int)j);
            }
            if (k != 0) {
                ARBShaderObjects.glDetachObjectARB((int)l, (int)k);
                ARBShaderObjects.glDeleteObjectARB((int)k);
            }
            program.setId(l);
            program.setRef(l);
            Shaders.useProgram(program);
            ARBShaderObjects.glValidateProgramARB((int)l);
            Shaders.useProgram(ProgramNone);
            Shaders.printLogInfo(l, program.getName());
            int i1 = GL43.glGetProgrami((int)l, (int)35715);
            if (i1 != 1) {
                String s = "\"";
                Shaders.printChatAndLogError("[Shaders] Error: Invalid program " + s + program.getName() + s);
                ARBShaderObjects.glDeleteObjectARB((int)l);
                l = 0;
                program.resetId();
            }
        }
    }

    private static ComputeProgram[] setupComputePrograms(Program program, String prefixShaders, String programPath, String shaderExt) {
        if (program.getProgramStage() == ProgramStage.GBUFFERS) {
            return new ComputeProgram[0];
        }
        ArrayList<ComputeProgram> list = new ArrayList<ComputeProgram>();
        int i = 27;
        for (int j = 0; j < i; ++j) {
            String s = j > 0 ? "_" + (char)(97 + j - 1) : "";
            String s1 = programPath + s;
            String s2 = prefixShaders + s1 + shaderExt;
            ComputeProgram computeprogram = new ComputeProgram(program.getName(), program.getProgramStage());
            Shaders.setupComputeProgram(computeprogram, s2);
            if (computeprogram.getId() <= 0) continue;
            list.add(computeprogram);
            SMCLog.info("Compute program loaded: " + s1);
        }
        return list.toArray(new ComputeProgram[list.size()]);
    }

    private static void setupComputeProgram(ComputeProgram program, String cShaderPath) {
        Shaders.checkGLError("pre setupProgram");
        int i = Shaders.createCompShader(program, cShaderPath);
        Shaders.checkGLError("create");
        if (i != 0) {
            int j = ARBShaderObjects.glCreateProgramObjectARB();
            Shaders.checkGLError("create");
            if (i != 0) {
                ARBShaderObjects.glAttachObjectARB((int)j, (int)i);
                Shaders.checkGLError("attach");
            }
            ARBShaderObjects.glLinkProgramARB((int)j);
            if (GL43.glGetProgrami((int)j, (int)35714) != 1) {
                SMCLog.severe("Error linking program: " + j + " (" + program.getName() + ")");
            }
            Shaders.printLogInfo(j, program.getName());
            if (i != 0) {
                ARBShaderObjects.glDetachObjectARB((int)j, (int)i);
                ARBShaderObjects.glDeleteObjectARB((int)i);
            }
            program.setId(j);
            program.setRef(j);
            ARBShaderObjects.glUseProgramObjectARB((int)j);
            ARBShaderObjects.glValidateProgramARB((int)j);
            ARBShaderObjects.glUseProgramObjectARB((int)0);
            Shaders.printLogInfo(j, program.getName());
            int k = GL43.glGetProgrami((int)j, (int)35715);
            if (k != 1) {
                String s = "\"";
                Shaders.printChatAndLogError("[Shaders] Error: Invalid program " + s + program.getName() + s);
                ARBShaderObjects.glDeleteObjectARB((int)j);
                j = 0;
                program.resetId();
            }
        }
    }

    private static int createCompShader(ComputeProgram program, String filename) {
        InputStream inputstream = shaderPack.getResourceAsStream(filename);
        if (inputstream == null) {
            return 0;
        }
        int i = ARBShaderObjects.glCreateShaderObjectARB((int)37305);
        if (i == 0) {
            return 0;
        }
        ShaderOption[] ashaderoption = Shaders.getChangedOptions(shaderPackOptions);
        ArrayList<String> list = new ArrayList<String>();
        LineBuffer linebuffer = new LineBuffer();
        if (linebuffer != null) {
            try {
                LineBuffer linebuffer1 = LineBuffer.readAll(new InputStreamReader(inputstream));
                linebuffer1 = ShaderPackParser.resolveIncludes(linebuffer1, filename, shaderPack, 0, list, 0);
                MacroState macrostate = new MacroState();
                for (String s : linebuffer1) {
                    String s3;
                    int l;
                    ShaderLine shaderline;
                    s = Shaders.applyOptions(s, ashaderoption);
                    linebuffer.add(s);
                    if (!macrostate.processLine(s) || (shaderline = ShaderParser.parseLine(s)) == null) continue;
                    if (shaderline.isUniform()) {
                        String s1 = shaderline.getName();
                        int j = ShaderParser.getShadowDepthIndex(s1);
                        if (j >= 0) {
                            usedShadowDepthBuffers = Math.max(usedShadowDepthBuffers, j + 1);
                            continue;
                        }
                        j = ShaderParser.getShadowColorIndex(s1);
                        if (j >= 0) {
                            usedShadowColorBuffers = Math.max(usedShadowColorBuffers, j + 1);
                            continue;
                        }
                        j = ShaderParser.getShadowColorImageIndex(s1);
                        if (j >= 0) {
                            usedShadowColorBuffers = Math.max(usedShadowColorBuffers, j + 1);
                            bindImageTextures = true;
                            continue;
                        }
                        j = ShaderParser.getDepthIndex(s1);
                        if (j >= 0) {
                            usedDepthBuffers = Math.max(usedDepthBuffers, j + 1);
                            continue;
                        }
                        j = ShaderParser.getColorIndex(s1);
                        if (j >= 0) {
                            usedColorBuffers = Math.max(usedColorBuffers, j + 1);
                            continue;
                        }
                        j = ShaderParser.getColorImageIndex(s1);
                        if (j < 0) continue;
                        usedColorBuffers = Math.max(usedColorBuffers, j + 1);
                        bindImageTextures = true;
                        continue;
                    }
                    if (shaderline.isLayout("in")) {
                        z_3539_x vector3i = ShaderParser.parseLocalSize(shaderline.getValue());
                        if (vector3i != null) {
                            program.setLocalSize(vector3i);
                            continue;
                        }
                        SMCLog.severe("Invalid local size: " + s);
                        continue;
                    }
                    if (shaderline.isConstIVec3("workGroups")) {
                        z_3539_x vector3i1 = shaderline.getValueIVec3();
                        if (vector3i1 != null) {
                            program.setWorkGroups(vector3i1);
                            continue;
                        }
                        SMCLog.severe("Invalid workGroups: " + s);
                        continue;
                    }
                    if (shaderline.isConstVec2("workGroupsRender")) {
                        P_3504_Q vector2f = shaderline.getValueVec2();
                        if (vector2f != null) {
                            program.setWorkGroupsRender(vector2f);
                            continue;
                        }
                        SMCLog.severe("Invalid workGroupsRender: " + s);
                        continue;
                    }
                    if (!shaderline.isConstBoolSuffix("MipmapEnabled", true) || (l = Shaders.getBufferIndex(s3 = StrUtils.removeSuffix(shaderline.getName(), "MipmapEnabled"))) < 0) continue;
                    int k = program.getCompositeMipmapSetting();
                    program.setCompositeMipmapSetting(k |= 1 << l);
                    SMCLog.info("%s mipmap enabled", s3);
                }
            }
            catch (Exception exception) {
                SMCLog.severe("Couldn't read " + filename + "!");
                exception.printStackTrace();
                ARBShaderObjects.glDeleteObjectARB((int)i);
                return 0;
            }
        }
        String s2 = linebuffer.toString();
        if (saveFinalShaders) {
            Shaders.saveShader(filename, s2);
        }
        if (program.getLocalSize() == null) {
            SMCLog.severe("Missing local size: " + filename);
            GL43.glDeleteShader((int)i);
            return 0;
        }
        ARBShaderObjects.glShaderSourceARB((int)i, (CharSequence)s2);
        ARBShaderObjects.glCompileShaderARB((int)i);
        if (GL43.glGetShaderi((int)i, (int)35713) != 1) {
            SMCLog.severe("Error compiling compute shader: " + filename);
        }
        Shaders.printShaderLogInfo(i, filename, list);
        return i;
    }

    private static int createVertShader(Program program, String filename) {
        InputStream inputstream = shaderPack.getResourceAsStream(filename);
        if (inputstream == null) {
            return 0;
        }
        int i = ARBShaderObjects.glCreateShaderObjectARB((int)35633);
        if (i == 0) {
            return 0;
        }
        ShaderOption[] ashaderoption = Shaders.getChangedOptions(shaderPackOptions);
        ArrayList<String> list = new ArrayList<String>();
        LineBuffer linebuffer = new LineBuffer();
        if (linebuffer != null) {
            try {
                LineBuffer linebuffer1 = LineBuffer.readAll(new InputStreamReader(inputstream));
                linebuffer1 = ShaderPackParser.resolveIncludes(linebuffer1, filename, shaderPack, 0, list, 0);
                linebuffer1 = ShaderPackParser.remapTextureUnits(linebuffer1);
                MacroState macrostate = new MacroState();
                for (String s : linebuffer1) {
                    ShaderLine shaderline;
                    s = Shaders.applyOptions(s, ashaderoption);
                    linebuffer.add(s);
                    if (!macrostate.processLine(s) || (shaderline = ShaderParser.parseLine(s)) == null) continue;
                    if (shaderline.isAttribute("mc_Entity")) {
                        useEntityAttrib = true;
                        progUseEntityAttrib = true;
                    } else if (shaderline.isAttribute("mc_midTexCoord")) {
                        useMidTexCoordAttrib = true;
                        progUseMidTexCoordAttrib = true;
                    } else if (shaderline.isAttribute("at_tangent")) {
                        useTangentAttrib = true;
                        progUseTangentAttrib = true;
                    } else if (shaderline.isAttribute("at_velocity")) {
                        useVelocityAttrib = true;
                        progUseVelocityAttrib = true;
                    } else if (shaderline.isAttribute("at_midBlock")) {
                        useMidBlockAttrib = true;
                        progUseMidBlockAttrib = true;
                    }
                    if (!shaderline.isConstInt("countInstances")) continue;
                    program.setCountInstances(shaderline.getValueInt());
                    SMCLog.info("countInstances: " + program.getCountInstances());
                }
            }
            catch (Exception exception) {
                SMCLog.severe("Couldn't read " + filename + "!");
                exception.printStackTrace();
                ARBShaderObjects.glDeleteObjectARB((int)i);
                return 0;
            }
        }
        String s1 = linebuffer.toString();
        if (saveFinalShaders) {
            Shaders.saveShader(filename, s1);
        }
        ARBShaderObjects.glShaderSourceARB((int)i, (CharSequence)s1);
        ARBShaderObjects.glCompileShaderARB((int)i);
        if (GL43.glGetShaderi((int)i, (int)35713) != 1) {
            SMCLog.severe("Error compiling vertex shader: " + filename);
        }
        Shaders.printShaderLogInfo(i, filename, list);
        return i;
    }

    private static int createGeomShader(Program program, String filename) {
        InputStream inputstream = shaderPack.getResourceAsStream(filename);
        if (inputstream == null) {
            return 0;
        }
        int i = ARBShaderObjects.glCreateShaderObjectARB((int)36313);
        if (i == 0) {
            return 0;
        }
        ShaderOption[] ashaderoption = Shaders.getChangedOptions(shaderPackOptions);
        ArrayList<String> list = new ArrayList<String>();
        progArbGeometryShader4 = false;
        progExtGeometryShader4 = false;
        progMaxVerticesOut = 3;
        LineBuffer linebuffer = new LineBuffer();
        if (linebuffer != null) {
            try {
                LineBuffer linebuffer1 = LineBuffer.readAll(new InputStreamReader(inputstream));
                linebuffer1 = ShaderPackParser.resolveIncludes(linebuffer1, filename, shaderPack, 0, list, 0);
                MacroState macrostate = new MacroState();
                for (String s : linebuffer1) {
                    String s3;
                    String s1;
                    ShaderLine shaderline;
                    s = Shaders.applyOptions(s, ashaderoption);
                    linebuffer.add(s);
                    if (!macrostate.processLine(s) || (shaderline = ShaderParser.parseLine(s)) == null) continue;
                    if (shaderline.isExtension("GL_ARB_geometry_shader4") && ((s1 = Config.normalize(shaderline.getValue())).equals("enable") || s1.equals("require") || s1.equals("warn"))) {
                        progArbGeometryShader4 = true;
                    }
                    if (shaderline.isExtension("GL_EXT_geometry_shader4") && ((s3 = Config.normalize(shaderline.getValue())).equals("enable") || s3.equals("require") || s3.equals("warn"))) {
                        progExtGeometryShader4 = true;
                    }
                    if (!shaderline.isConstInt("maxVerticesOut")) continue;
                    progMaxVerticesOut = shaderline.getValueInt();
                }
            }
            catch (Exception exception) {
                SMCLog.severe("Couldn't read " + filename + "!");
                exception.printStackTrace();
                ARBShaderObjects.glDeleteObjectARB((int)i);
                return 0;
            }
        }
        String s2 = linebuffer.toString();
        if (saveFinalShaders) {
            Shaders.saveShader(filename, s2);
        }
        ARBShaderObjects.glShaderSourceARB((int)i, (CharSequence)s2);
        ARBShaderObjects.glCompileShaderARB((int)i);
        if (GL43.glGetShaderi((int)i, (int)35713) != 1) {
            SMCLog.severe("Error compiling geometry shader: " + filename);
        }
        Shaders.printShaderLogInfo(i, filename, list);
        return i;
    }

    private static int createFragShader(Program program, String filename) {
        InputStream inputstream = shaderPack.getResourceAsStream(filename);
        if (inputstream == null) {
            return 0;
        }
        int i = ARBShaderObjects.glCreateShaderObjectARB((int)35632);
        if (i == 0) {
            return 0;
        }
        ShaderOption[] ashaderoption = Shaders.getChangedOptions(shaderPackOptions);
        ArrayList<String> list = new ArrayList<String>();
        LineBuffer linebuffer = new LineBuffer();
        if (linebuffer != null) {
            try {
                LineBuffer linebuffer1 = LineBuffer.readAll(new InputStreamReader(inputstream));
                linebuffer1 = ShaderPackParser.resolveIncludes(linebuffer1, filename, shaderPack, 0, list, 0);
                MacroState macrostate = new MacroState();
                for (String s : linebuffer1) {
                    ShaderLine shaderline;
                    s = Shaders.applyOptions(s, ashaderoption);
                    linebuffer.add(s);
                    if (!macrostate.processLine(s) || (shaderline = ShaderParser.parseLine(s)) == null) continue;
                    if (shaderline.isUniform()) {
                        String s9 = shaderline.getName();
                        int l1 = ShaderParser.getShadowDepthIndex(s9);
                        if (l1 >= 0) {
                            usedShadowDepthBuffers = Math.max(usedShadowDepthBuffers, l1 + 1);
                            continue;
                        }
                        l1 = ShaderParser.getShadowColorIndex(s9);
                        if (l1 >= 0) {
                            usedShadowColorBuffers = Math.max(usedShadowColorBuffers, l1 + 1);
                            continue;
                        }
                        l1 = ShaderParser.getShadowColorImageIndex(s9);
                        if (l1 >= 0) {
                            usedShadowColorBuffers = Math.max(usedShadowColorBuffers, l1 + 1);
                            bindImageTextures = true;
                            continue;
                        }
                        l1 = ShaderParser.getDepthIndex(s9);
                        if (l1 >= 0) {
                            usedDepthBuffers = Math.max(usedDepthBuffers, l1 + 1);
                            continue;
                        }
                        if (s9.equals("gdepth") && gbuffersFormat[1] == 6408) {
                            Shaders.gbuffersFormat[1] = 34836;
                            continue;
                        }
                        l1 = ShaderParser.getColorIndex(s9);
                        if (l1 >= 0) {
                            usedColorBuffers = Math.max(usedColorBuffers, l1 + 1);
                            continue;
                        }
                        l1 = ShaderParser.getColorImageIndex(s9);
                        if (l1 >= 0) {
                            usedColorBuffers = Math.max(usedColorBuffers, l1 + 1);
                            bindImageTextures = true;
                            continue;
                        }
                        if (!s9.equals("centerDepthSmooth")) continue;
                        centerDepthSmoothEnabled = true;
                        continue;
                    }
                    if (!shaderline.isConstInt("shadowMapResolution") && !shaderline.isProperty("SHADOWRES")) {
                        if (!shaderline.isConstFloat("shadowMapFov") && !shaderline.isProperty("SHADOWFOV")) {
                            if (!shaderline.isConstFloat("shadowDistance") && !shaderline.isProperty("SHADOWHPL")) {
                                if (shaderline.isConstFloat("shadowDistanceRenderMul")) {
                                    shadowDistanceRenderMul = shaderline.getValueFloat();
                                    SMCLog.info("Shadow distance render mul: " + shadowDistanceRenderMul);
                                    continue;
                                }
                                if (shaderline.isConstFloat("shadowIntervalSize")) {
                                    shadowIntervalSize = shaderline.getValueFloat();
                                    SMCLog.info("Shadow map interval size: " + shadowIntervalSize);
                                    continue;
                                }
                                if (shaderline.isConstBool("generateShadowMipmap", true)) {
                                    Arrays.fill(shadowMipmapEnabled, true);
                                    SMCLog.info("Generate shadow mipmap");
                                    continue;
                                }
                                if (shaderline.isConstBool("generateShadowColorMipmap", true)) {
                                    Arrays.fill(shadowColorMipmapEnabled, true);
                                    SMCLog.info("Generate shadow color mipmap");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowHardwareFiltering", true)) {
                                    Arrays.fill(shadowHardwareFilteringEnabled, true);
                                    SMCLog.info("Hardware shadow filtering enabled.");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowHardwareFiltering0", true)) {
                                    Shaders.shadowHardwareFilteringEnabled[0] = true;
                                    SMCLog.info("shadowHardwareFiltering0");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowHardwareFiltering1", true)) {
                                    Shaders.shadowHardwareFilteringEnabled[1] = true;
                                    SMCLog.info("shadowHardwareFiltering1");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowtex0Mipmap", "shadowtexMipmap", true)) {
                                    Shaders.shadowMipmapEnabled[0] = true;
                                    SMCLog.info("shadowtex0Mipmap");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowtex1Mipmap", true)) {
                                    Shaders.shadowMipmapEnabled[1] = true;
                                    SMCLog.info("shadowtex1Mipmap");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowcolor0Mipmap", "shadowColor0Mipmap", true)) {
                                    Shaders.shadowColorMipmapEnabled[0] = true;
                                    SMCLog.info("shadowcolor0Mipmap");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowcolor1Mipmap", "shadowColor1Mipmap", true)) {
                                    Shaders.shadowColorMipmapEnabled[1] = true;
                                    SMCLog.info("shadowcolor1Mipmap");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowtex0Nearest", "shadowtexNearest", "shadow0MinMagNearest", true)) {
                                    Shaders.shadowFilterNearest[0] = true;
                                    SMCLog.info("shadowtex0Nearest");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowtex1Nearest", "shadow1MinMagNearest", true)) {
                                    Shaders.shadowFilterNearest[1] = true;
                                    SMCLog.info("shadowtex1Nearest");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowcolor0Nearest", "shadowColor0Nearest", "shadowColor0MinMagNearest", true)) {
                                    Shaders.shadowColorFilterNearest[0] = true;
                                    SMCLog.info("shadowcolor0Nearest");
                                    continue;
                                }
                                if (shaderline.isConstBool("shadowcolor1Nearest", "shadowColor1Nearest", "shadowColor1MinMagNearest", true)) {
                                    Shaders.shadowColorFilterNearest[1] = true;
                                    SMCLog.info("shadowcolor1Nearest");
                                    continue;
                                }
                                if (!shaderline.isConstFloat("wetnessHalflife") && !shaderline.isProperty("WETNESSHL")) {
                                    if (!shaderline.isConstFloat("drynessHalflife") && !shaderline.isProperty("DRYNESSHL")) {
                                        if (shaderline.isConstFloat("eyeBrightnessHalflife")) {
                                            eyeBrightnessHalflife = shaderline.getValueFloat();
                                            SMCLog.info("Eye brightness halflife: " + eyeBrightnessHalflife);
                                            continue;
                                        }
                                        if (shaderline.isConstFloat("centerDepthHalflife")) {
                                            centerDepthSmoothHalflife = shaderline.getValueFloat();
                                            SMCLog.info("Center depth halflife: " + centerDepthSmoothHalflife);
                                            continue;
                                        }
                                        if (shaderline.isConstFloat("sunPathRotation")) {
                                            sunPathRotation = shaderline.getValueFloat();
                                            SMCLog.info("Sun path rotation: " + sunPathRotation);
                                            continue;
                                        }
                                        if (shaderline.isConstFloat("ambientOcclusionLevel")) {
                                            aoLevel = Config.limit(shaderline.getValueFloat(), 0.0f, 1.0f);
                                            SMCLog.info("AO Level: " + aoLevel);
                                            continue;
                                        }
                                        if (shaderline.isConstInt("superSamplingLevel")) {
                                            int j = shaderline.getValueInt();
                                            if (j > 1) {
                                                SMCLog.info("Super sampling level: " + j + "x");
                                                superSamplingLevel = j;
                                                continue;
                                            }
                                            superSamplingLevel = 1;
                                            continue;
                                        }
                                        if (shaderline.isConstInt("noiseTextureResolution")) {
                                            noiseTextureResolution = shaderline.getValueInt();
                                            noiseTextureEnabled = true;
                                            SMCLog.info("Noise texture enabled");
                                            SMCLog.info("Noise texture resolution: " + noiseTextureResolution);
                                            continue;
                                        }
                                        if (shaderline.isConstIntSuffix("Format")) {
                                            int i1;
                                            String s3 = StrUtils.removeSuffix(shaderline.getName(), "Format");
                                            String s1 = shaderline.getValue();
                                            int k = Shaders.getTextureFormatFromString(s1);
                                            if (k == 0) continue;
                                            int l = Shaders.getBufferIndex(s3);
                                            if (l >= 0) {
                                                Shaders.gbuffersFormat[l] = k;
                                                SMCLog.info("%s format: %s", s3, s1);
                                            }
                                            if ((i1 = ShaderParser.getShadowColorIndex(s3)) < 0) continue;
                                            Shaders.shadowBuffersFormat[i1] = k;
                                            SMCLog.info("%s format: %s", s3, s1);
                                            continue;
                                        }
                                        if (shaderline.isConstBoolSuffix("Clear", false)) {
                                            int i2;
                                            if (!program.getProgramStage().isAnyComposite()) continue;
                                            String s4 = StrUtils.removeSuffix(shaderline.getName(), "Clear");
                                            int j1 = Shaders.getBufferIndex(s4);
                                            if (j1 >= 0) {
                                                Shaders.gbuffersClear[j1] = false;
                                                SMCLog.info("%s clear disabled", s4);
                                            }
                                            if ((i2 = ShaderParser.getShadowColorIndex(s4)) < 0) continue;
                                            Shaders.shadowBuffersClear[i2] = false;
                                            SMCLog.info("%s clear disabled", s4);
                                            continue;
                                        }
                                        if (shaderline.isConstVec4Suffix("ClearColor")) {
                                            if (!program.getProgramStage().isAnyComposite()) continue;
                                            String s5 = StrUtils.removeSuffix(shaderline.getName(), "ClearColor");
                                            Z_2491_A vector4f = shaderline.getValueVec4();
                                            if (vector4f != null) {
                                                int l2;
                                                int j2 = Shaders.getBufferIndex(s5);
                                                if (j2 >= 0) {
                                                    Shaders.gbuffersClearColor[j2] = vector4f;
                                                    SMCLog.info("%s clear color: %s %s %s %s", s5, Float.valueOf(vector4f.n_1700_B()), Float.valueOf(vector4f.J_1907_R()), Float.valueOf(vector4f.R_4764_Y()), Float.valueOf(vector4f.G_564_y()));
                                                }
                                                if ((l2 = ShaderParser.getShadowColorIndex(s5)) < 0) continue;
                                                Shaders.shadowBuffersClearColor[l2] = vector4f;
                                                SMCLog.info("%s clear color: %s %s %s %s", s5, Float.valueOf(vector4f.n_1700_B()), Float.valueOf(vector4f.J_1907_R()), Float.valueOf(vector4f.R_4764_Y()), Float.valueOf(vector4f.G_564_y()));
                                                continue;
                                            }
                                            SMCLog.warning("Invalid color value: " + shaderline.getValue());
                                            continue;
                                        }
                                        if (shaderline.isProperty("GAUX4FORMAT", "RGBA32F")) {
                                            Shaders.gbuffersFormat[7] = 34836;
                                            SMCLog.info("gaux4 format : RGB32AF");
                                            continue;
                                        }
                                        if (shaderline.isProperty("GAUX4FORMAT", "RGB32F")) {
                                            Shaders.gbuffersFormat[7] = 34837;
                                            SMCLog.info("gaux4 format : RGB32F");
                                            continue;
                                        }
                                        if (shaderline.isProperty("GAUX4FORMAT", "RGB16")) {
                                            Shaders.gbuffersFormat[7] = 32852;
                                            SMCLog.info("gaux4 format : RGB16");
                                            continue;
                                        }
                                        if (shaderline.isConstBoolSuffix("MipmapEnabled", true)) {
                                            String s6;
                                            int k1;
                                            if (!program.getProgramStage().isAnyComposite() || (k1 = Shaders.getBufferIndex(s6 = StrUtils.removeSuffix(shaderline.getName(), "MipmapEnabled"))) < 0) continue;
                                            int k2 = program.getCompositeMipmapSetting();
                                            program.setCompositeMipmapSetting(k2 |= 1 << k1);
                                            SMCLog.info("%s mipmap enabled", s6);
                                            continue;
                                        }
                                        if (shaderline.isProperty("DRAWBUFFERS")) {
                                            String s7 = shaderline.getValue();
                                            String[] astring = ShaderParser.parseDrawBuffers(s7);
                                            if (astring != null) {
                                                program.setDrawBufSettings(astring);
                                                continue;
                                            }
                                            SMCLog.warning("Invalid draw buffers: " + s7);
                                            continue;
                                        }
                                        if (!shaderline.isProperty("RENDERTARGETS")) continue;
                                        String s8 = shaderline.getValue();
                                        String[] astring1 = ShaderParser.parseRenderTargets(s8);
                                        if (astring1 != null) {
                                            program.setDrawBufSettings(astring1);
                                            continue;
                                        }
                                        SMCLog.warning("Invalid render targets: " + s8);
                                        continue;
                                    }
                                    drynessHalfLife = shaderline.getValueFloat();
                                    SMCLog.info("Dryness halflife: " + drynessHalfLife);
                                    continue;
                                }
                                wetnessHalfLife = shaderline.getValueFloat();
                                SMCLog.info("Wetness halflife: " + wetnessHalfLife);
                                continue;
                            }
                            shadowMapHalfPlane = shaderline.getValueFloat();
                            shadowMapIsOrtho = true;
                            SMCLog.info("Shadow map distance: " + shadowMapHalfPlane);
                            continue;
                        }
                        shadowMapFOV = shaderline.getValueFloat();
                        shadowMapIsOrtho = false;
                        SMCLog.info("Shadow map field of view: " + shadowMapFOV);
                        continue;
                    }
                    spShadowMapWidth = spShadowMapHeight = shaderline.getValueInt();
                    shadowMapWidth = shadowMapHeight = Math.round((float)spShadowMapWidth * configShadowResMul);
                    SMCLog.info("Shadow map resolution: " + spShadowMapWidth);
                }
            }
            catch (Exception exception) {
                SMCLog.severe("Couldn't read " + filename + "!");
                exception.printStackTrace();
                ARBShaderObjects.glDeleteObjectARB((int)i);
                return 0;
            }
        }
        String s2 = linebuffer.toString();
        if (saveFinalShaders) {
            Shaders.saveShader(filename, s2);
        }
        ARBShaderObjects.glShaderSourceARB((int)i, (CharSequence)s2);
        ARBShaderObjects.glCompileShaderARB((int)i);
        if (GL43.glGetShaderi((int)i, (int)35713) != 1) {
            SMCLog.severe("Error compiling fragment shader: " + filename);
        }
        Shaders.printShaderLogInfo(i, filename, list);
        return i;
    }

    public static void saveShader(String filename, String code) {
        try {
            File file1 = new File(shaderPacksDir, "debug/" + filename);
            file1.getParentFile().mkdirs();
            Config.writeFile(file1, code);
        }
        catch (IOException ioexception) {
            Config.warn("Error saving: " + filename);
            ioexception.printStackTrace();
        }
    }

    private static void clearDirectory(File dir) {
        File[] afile;
        if (dir.exists() && dir.isDirectory() && (afile = dir.listFiles()) != null) {
            for (int i = 0; i < afile.length; ++i) {
                File file1 = afile[i];
                if (file1.isDirectory()) {
                    Shaders.clearDirectory(file1);
                }
                file1.delete();
            }
        }
    }

    private static boolean printLogInfo(int obj, String name) {
        IntBuffer intbuffer = BufferUtils.createIntBuffer((int)1);
        ARBShaderObjects.glGetObjectParameterivARB((int)obj, (int)35716, (IntBuffer)intbuffer);
        int i = intbuffer.get();
        if (i > 1) {
            ByteBuffer bytebuffer = BufferUtils.createByteBuffer((int)i);
            ((Buffer)intbuffer).flip();
            ARBShaderObjects.glGetInfoLogARB((int)obj, (IntBuffer)intbuffer, (ByteBuffer)bytebuffer);
            byte[] abyte = new byte[i];
            bytebuffer.get(abyte);
            if (abyte[i - 1] == 0) {
                abyte[i - 1] = 10;
            }
            String s = new String(abyte, StandardCharsets.US_ASCII);
            s = StrUtils.trim(s, " \n\r\t");
            SMCLog.info("Info log: " + name + "\n" + s);
            return false;
        }
        return true;
    }

    private static boolean printShaderLogInfo(int shader, String name, List<String> listFiles) {
        IntBuffer intbuffer = BufferUtils.createIntBuffer((int)1);
        int i = GL43.glGetShaderi((int)shader, (int)35716);
        if (i <= 1) {
            return true;
        }
        for (int j = 0; j < listFiles.size(); ++j) {
            String s = listFiles.get(j);
            SMCLog.info("File: " + (j + 1) + " = " + s);
        }
        String s1 = GL43.glGetShaderInfoLog((int)shader, (int)i);
        s1 = StrUtils.trim(s1, " \n\r\t");
        SMCLog.info("Shader info log: " + name + "\n" + s1);
        return false;
    }

    public static void useProgram(Program program) {
        Shaders.checkGLError("pre-useProgram");
        if (isShadowPass) {
            program = ProgramShadow;
        } else if (isEntitiesGlowing) {
            program = ProgramEntitiesGlowing;
        }
        if (activeProgram != program) {
            int i;
            Shaders.flushRenderBuffers();
            Shaders.updateAlphaBlend(activeProgram, program);
            if (glDebugGroups && glDebugGroupProgram) {
                KHRDebug.glPopDebugGroup();
            }
            activeProgram = program;
            if (glDebugGroups) {
                KHRDebug.glPushDebugGroup((int)33354, (int)0, (CharSequence)activeProgram.getRealProgramName());
                glDebugGroupProgram = true;
            }
            activeProgramID = i = program.getId();
            ARBShaderObjects.glUseProgramObjectARB((int)i);
            if (Shaders.checkGLError("useProgram") != 0) {
                program.setId(0);
                activeProgramID = i = program.getId();
                ARBShaderObjects.glUseProgramObjectARB((int)i);
            }
            shaderUniforms.setProgram(i);
            if (customUniforms != null) {
                customUniforms.setProgram(i);
            }
            if (i != 0) {
                DrawBuffers drawbuffers = program.getDrawBuffers();
                if (isRenderingDfb) {
                    GlState.setDrawBuffers(drawbuffers);
                }
                Shaders.setProgramUniforms(program.getProgramStage());
                Shaders.setImageUniforms();
                Shaders.checkGLError("end useProgram");
            }
        }
    }

    private static void setProgramUniforms(ProgramStage programStage) {
        int l;
        switch (programStage) {
            case GBUFFERS: {
                Shaders.setProgramUniform1i(uniform_texture, 0);
                Shaders.setProgramUniform1i(uniform_lightmap, 2);
                Shaders.setProgramUniform1i(uniform_normals, 1);
                Shaders.setProgramUniform1i(uniform_specular, 3);
                Shaders.setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
                Shaders.setProgramUniform1i(uniform_watershadow, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex0, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex1, 5);
                Shaders.setProgramUniform1i(uniform_depthtex0, 6);
                if (customTexturesGbuffers != null || hasDeferredPrograms) {
                    Shaders.setProgramUniform1i(uniform_gaux1, 7);
                    Shaders.setProgramUniform1i(uniform_gaux2, 8);
                    Shaders.setProgramUniform1i(uniform_gaux3, 9);
                    Shaders.setProgramUniform1i(uniform_gaux4, 10);
                    Shaders.setProgramUniform1i(uniform_colortex4, 7);
                    Shaders.setProgramUniform1i(uniform_colortex5, 8);
                    Shaders.setProgramUniform1i(uniform_colortex6, 9);
                    Shaders.setProgramUniform1i(uniform_colortex7, 10);
                    if (usedColorBuffers > 8) {
                        Shaders.setProgramUniform1i(uniform_colortex8, 16);
                        Shaders.setProgramUniform1i(uniform_colortex9, 17);
                        Shaders.setProgramUniform1i(uniform_colortex10, 18);
                        Shaders.setProgramUniform1i(uniform_colortex11, 19);
                        Shaders.setProgramUniform1i(uniform_colortex12, 20);
                        Shaders.setProgramUniform1i(uniform_colortex13, 21);
                        Shaders.setProgramUniform1i(uniform_colortex14, 22);
                        Shaders.setProgramUniform1i(uniform_colortex15, 23);
                    }
                }
                Shaders.setProgramUniform1i(uniform_depthtex1, 11);
                Shaders.setProgramUniform1i(uniform_shadowcolor, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor0, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor1, 14);
                Shaders.setProgramUniform1i(uniform_noisetex, 15);
                break;
            }
            case SHADOWCOMP: 
            case PREPARE: 
            case DEFERRED: 
            case COMPOSITE: {
                Shaders.setProgramUniform1i(uniform_gcolor, 0);
                Shaders.setProgramUniform1i(uniform_gdepth, 1);
                Shaders.setProgramUniform1i(uniform_gnormal, 2);
                Shaders.setProgramUniform1i(uniform_composite, 3);
                Shaders.setProgramUniform1i(uniform_gaux1, 7);
                Shaders.setProgramUniform1i(uniform_gaux2, 8);
                Shaders.setProgramUniform1i(uniform_gaux3, 9);
                Shaders.setProgramUniform1i(uniform_gaux4, 10);
                Shaders.setProgramUniform1i(uniform_colortex0, 0);
                Shaders.setProgramUniform1i(uniform_colortex1, 1);
                Shaders.setProgramUniform1i(uniform_colortex2, 2);
                Shaders.setProgramUniform1i(uniform_colortex3, 3);
                Shaders.setProgramUniform1i(uniform_colortex4, 7);
                Shaders.setProgramUniform1i(uniform_colortex5, 8);
                Shaders.setProgramUniform1i(uniform_colortex6, 9);
                Shaders.setProgramUniform1i(uniform_colortex7, 10);
                if (usedColorBuffers > 8) {
                    Shaders.setProgramUniform1i(uniform_colortex8, 16);
                    Shaders.setProgramUniform1i(uniform_colortex9, 17);
                    Shaders.setProgramUniform1i(uniform_colortex10, 18);
                    Shaders.setProgramUniform1i(uniform_colortex11, 19);
                    Shaders.setProgramUniform1i(uniform_colortex12, 20);
                    Shaders.setProgramUniform1i(uniform_colortex13, 21);
                    Shaders.setProgramUniform1i(uniform_colortex14, 22);
                    Shaders.setProgramUniform1i(uniform_colortex15, 23);
                }
                Shaders.setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
                Shaders.setProgramUniform1i(uniform_watershadow, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex0, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex1, 5);
                Shaders.setProgramUniform1i(uniform_gdepthtex, 6);
                Shaders.setProgramUniform1i(uniform_depthtex0, 6);
                Shaders.setProgramUniform1i(uniform_depthtex1, 11);
                Shaders.setProgramUniform1i(uniform_depthtex2, 12);
                Shaders.setProgramUniform1i(uniform_shadowcolor, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor0, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor1, 14);
                Shaders.setProgramUniform1i(uniform_noisetex, 15);
                break;
            }
            case SHADOW: {
                Shaders.setProgramUniform1i(uniform_tex, 0);
                Shaders.setProgramUniform1i(uniform_texture, 0);
                Shaders.setProgramUniform1i(uniform_lightmap, 2);
                Shaders.setProgramUniform1i(uniform_normals, 1);
                Shaders.setProgramUniform1i(uniform_specular, 3);
                Shaders.setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
                Shaders.setProgramUniform1i(uniform_watershadow, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex0, 4);
                Shaders.setProgramUniform1i(uniform_shadowtex1, 5);
                if (customTexturesGbuffers != null) {
                    Shaders.setProgramUniform1i(uniform_gaux1, 7);
                    Shaders.setProgramUniform1i(uniform_gaux2, 8);
                    Shaders.setProgramUniform1i(uniform_gaux3, 9);
                    Shaders.setProgramUniform1i(uniform_gaux4, 10);
                    Shaders.setProgramUniform1i(uniform_colortex4, 7);
                    Shaders.setProgramUniform1i(uniform_colortex5, 8);
                    Shaders.setProgramUniform1i(uniform_colortex6, 9);
                    Shaders.setProgramUniform1i(uniform_colortex7, 10);
                    if (usedColorBuffers > 8) {
                        Shaders.setProgramUniform1i(uniform_colortex8, 16);
                        Shaders.setProgramUniform1i(uniform_colortex9, 17);
                        Shaders.setProgramUniform1i(uniform_colortex10, 18);
                        Shaders.setProgramUniform1i(uniform_colortex11, 19);
                        Shaders.setProgramUniform1i(uniform_colortex12, 20);
                        Shaders.setProgramUniform1i(uniform_colortex13, 21);
                        Shaders.setProgramUniform1i(uniform_colortex14, 22);
                        Shaders.setProgramUniform1i(uniform_colortex15, 23);
                    }
                }
                Shaders.setProgramUniform1i(uniform_shadowcolor, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor0, 13);
                Shaders.setProgramUniform1i(uniform_shadowcolor1, 14);
                Shaders.setProgramUniform1i(uniform_noisetex, 15);
            }
        }
        Z_1993_T itemstack = Shaders.mc.Y_259_p != null ? Shaders.mc.Y_259_p.A_2714_y() : null;
        q_1613_l item = itemstack != null ? itemstack.J_1907_R() : null;
        int i = -1;
        T_2915_h block = null;
        if (item != null) {
            i = V_3137_a.e_2887_G.n_1700_B(item);
            if (item instanceof v_1669_V) {
                block = ((v_1669_V)item).v_4262_N();
            }
            i = ItemAliases.getItemAliasId(i);
        }
        int j = block != null ? block.multiplayerClientSuggestionProvider().u_1723_Y() : 0;
        Z_1993_T itemstack1 = Shaders.mc.Y_259_p != null ? Shaders.mc.Y_259_p.S_4035_N() : null;
        q_1613_l item1 = itemstack1 != null ? itemstack1.J_1907_R() : null;
        int k = -1;
        T_2915_h block1 = null;
        if (item1 != null) {
            k = V_3137_a.e_2887_G.n_1700_B(item1);
            if (item1 instanceof v_1669_V) {
                block1 = ((v_1669_V)item1).v_4262_N();
            }
            k = ItemAliases.getItemAliasId(k);
        }
        int n = l = block1 != null ? block1.multiplayerClientSuggestionProvider().u_1723_Y() : 0;
        if (Shaders.isOldHandLight() && l > j) {
            i = k;
            j = l;
        }
        float f = Shaders.mc.Y_259_p != null ? Shaders.mc.Y_259_p.R_4764_Y() : 0.0f;
        Shaders.setProgramUniform1i(uniform_heldItemId, i);
        Shaders.setProgramUniform1i(uniform_heldBlockLightValue, j);
        Shaders.setProgramUniform1i(uniform_heldItemId2, k);
        Shaders.setProgramUniform1i(uniform_heldBlockLightValue2, l);
        Shaders.setProgramUniform1i(uniform_fogMode, fogEnabled ? fogMode : 0);
        Shaders.setProgramUniform1f(uniform_fogDensity, fogEnabled ? fogDensity : 0.0f);
        Shaders.setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
        Shaders.setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
        Shaders.setProgramUniform1i(uniform_worldTime, (int)(worldTime % 24000L));
        Shaders.setProgramUniform1i(uniform_worldDay, (int)(worldTime / 24000L));
        Shaders.setProgramUniform1i(uniform_moonPhase, moonPhase);
        Shaders.setProgramUniform1i(uniform_frameCounter, frameCounter);
        Shaders.setProgramUniform1f(uniform_frameTime, frameTime);
        Shaders.setProgramUniform1f(uniform_frameTimeCounter, frameTimeCounter);
        Shaders.setProgramUniform1f(uniform_sunAngle, sunAngle);
        Shaders.setProgramUniform1f(uniform_shadowAngle, shadowAngle);
        Shaders.setProgramUniform1f(uniform_rainStrength, rainStrength);
        Shaders.setProgramUniform1f(uniform_aspectRatio, (float)renderWidth / (float)renderHeight);
        Shaders.setProgramUniform1f(uniform_viewWidth, renderWidth);
        Shaders.setProgramUniform1f(uniform_viewHeight, renderHeight);
        Shaders.setProgramUniform1f(uniform_near, 0.05f);
        Shaders.setProgramUniform1f(uniform_far, Shaders.mc.P_4830_p.J_1907_R * 16);
        Shaders.setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
        Shaders.setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
        Shaders.setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
        Shaders.setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
        Shaders.setProgramUniform3f(uniform_previousCameraPosition, (float)previousCameraPositionX, (float)previousCameraPositionY, (float)previousCameraPositionZ);
        Shaders.setProgramUniform3f(uniform_cameraPosition, (float)cameraPositionX, (float)cameraPositionY, (float)cameraPositionZ);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, previousProjection);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, previousModelView);
        if (hasShadowMap) {
            Shaders.setProgramUniformMatrix4ARB(uniform_shadowProjection, false, shadowProjection);
            Shaders.setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, shadowProjectionInverse);
            Shaders.setProgramUniformMatrix4ARB(uniform_shadowModelView, false, shadowModelView);
            Shaders.setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, shadowModelViewInverse);
        }
        Shaders.setProgramUniform1f(uniform_wetness, wetness);
        Shaders.setProgramUniform1f(uniform_eyeAltitude, eyePosY);
        Shaders.setProgramUniform2i(uniform_eyeBrightness, eyeBrightness & 0xFFFF, eyeBrightness >> 16);
        Shaders.setProgramUniform2i(uniform_eyeBrightnessSmooth, Math.round(eyeBrightnessFadeX), Math.round(eyeBrightnessFadeY));
        Shaders.setProgramUniform2i(uniform_terrainTextureSize, terrainTextureSize[0], terrainTextureSize[1]);
        Shaders.setProgramUniform1i(uniform_terrainIconSize, terrainIconSize);
        Shaders.setProgramUniform1i(uniform_isEyeInWater, isEyeInWater);
        Shaders.setProgramUniform1f(uniform_nightVision, nightVision);
        Shaders.setProgramUniform1f(uniform_blindness, blindness);
        Shaders.setProgramUniform1f(uniform_screenBrightness, (float)Shaders.mc.P_4830_p.c_132_F);
        Shaders.setProgramUniform1i(uniform_hideGUI, Shaders.mc.P_4830_p.RetryCallException ? 1 : 0);
        Shaders.setProgramUniform1f(uniform_centerDepthSmooth, centerDepthSmooth);
        Shaders.setProgramUniform2i(uniform_atlasSize, atlasSizeX, atlasSizeY);
        Shaders.setProgramUniform1f(uniform_playerMood, f);
        Shaders.setProgramUniform1i(uniform_renderStage, renderStage.ordinal());
        if (customUniforms != null) {
            customUniforms.update();
        }
    }

    private static void setImageUniforms() {
        if (bindImageTextures) {
            uniform_colorimg0.setValue(colorImageUnit[0]);
            uniform_colorimg1.setValue(colorImageUnit[1]);
            uniform_colorimg2.setValue(colorImageUnit[2]);
            uniform_colorimg3.setValue(colorImageUnit[3]);
            uniform_colorimg4.setValue(colorImageUnit[4]);
            uniform_colorimg5.setValue(colorImageUnit[5]);
            uniform_shadowcolorimg0.setValue(shadowColorImageUnit[0]);
            uniform_shadowcolorimg1.setValue(shadowColorImageUnit[1]);
        }
    }

    private static void updateAlphaBlend(Program programOld, Program programNew) {
        GlBlendState glblendstate;
        GlAlphaState glalphastate;
        if (programOld.getAlphaState() != null) {
            X_933_l.T_3594_S();
        }
        if (programOld.getBlendState() != null) {
            X_933_l.D_4792_h();
        }
        if (programOld.getBlendStatesIndexed() != null) {
            X_933_l.i_1637_u();
        }
        if ((glalphastate = programNew.getAlphaState()) != null) {
            X_933_l.n_1700_B(glalphastate);
        }
        if ((glblendstate = programNew.getBlendState()) != null) {
            X_933_l.n_1700_B(glblendstate);
        }
        if (programNew.getBlendStatesIndexed() != null) {
            X_933_l.n_1700_B(programNew.getBlendStatesIndexed());
        }
    }

    private static void setProgramUniform1i(ShaderUniform1i su, int value) {
        su.setValue(value);
    }

    private static void setProgramUniform2i(ShaderUniform2i su, int i0, int i1) {
        su.setValue(i0, i1);
    }

    private static void setProgramUniform1f(ShaderUniform1f su, float value) {
        su.setValue(value);
    }

    private static void setProgramUniform3f(ShaderUniform3f su, float f0, float f1, float f2) {
        su.setValue(f0, f1, f2);
    }

    private static void setProgramUniformMatrix4ARB(ShaderUniformM4 su, boolean transpose, FloatBuffer matrix) {
        su.setValue(transpose, matrix);
    }

    public static int getBufferIndex(String name) {
        int i = ShaderParser.getIndex(name, "colortex", 0, 15);
        if (i >= 0) {
            return i;
        }
        int j = ShaderParser.getIndex(name, "colorimg", 0, 15);
        if (j >= 0) {
            return j;
        }
        if (name.equals("gcolor")) {
            return 0;
        }
        if (name.equals("gdepth")) {
            return 1;
        }
        if (name.equals("gnormal")) {
            return 2;
        }
        if (name.equals("composite")) {
            return 3;
        }
        if (name.equals("gaux1")) {
            return 4;
        }
        if (name.equals("gaux2")) {
            return 5;
        }
        if (name.equals("gaux3")) {
            return 6;
        }
        return name.equals("gaux4") ? 7 : -1;
    }

    private static int getTextureFormatFromString(String par) {
        par = par.trim();
        for (int i = 0; i < formatNames.length; ++i) {
            String s = formatNames[i];
            if (!par.equals(s)) continue;
            return formatIds[i];
        }
        return 0;
    }

    public static int getImageFormat(int textureFormat) {
        switch (textureFormat) {
            case 6407: {
                return 32849;
            }
            case 6408: {
                return 32856;
            }
            case 8194: {
                return 33321;
            }
            case 10768: {
                return 32849;
            }
            case 32855: {
                return 32856;
            }
            case 33319: {
                return 33323;
            }
            case 35901: {
                return 32852;
            }
        }
        return textureFormat;
    }

    private static void setupNoiseTexture() {
        if (noiseTexture == null && noiseTexturePath != null) {
            noiseTexture = Shaders.loadCustomTexture(15, noiseTexturePath);
        }
        if (noiseTexture == null) {
            noiseTexture = new HFNoiseTexture(noiseTextureResolution, noiseTextureResolution);
        }
    }

    private static void loadEntityDataMap() {
        mapBlockToEntityData = new IdentityHashMap<T_2915_h, Integer>(300);
        if (mapBlockToEntityData.isEmpty()) {
            for (g_2336_b resourcelocation : V_3137_a.q_4610_l.G_564_y()) {
                T_2915_h block = V_3137_a.q_4610_l.n_1700_B(resourcelocation);
                int i = V_3137_a.q_4610_l.n_1700_B(block);
                mapBlockToEntityData.put(block, i);
            }
        }
        BufferedReader bufferedreader = null;
        try {
            bufferedreader = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream("/mc_Entity_x.txt")));
        }
        catch (Exception resourcelocation) {
            // empty catch block
        }
        if (bufferedreader != null) {
            try {
                String s1;
                while ((s1 = bufferedreader.readLine()) != null) {
                    Matcher matcher = patternLoadEntityDataMap.matcher(s1);
                    if (matcher.matches()) {
                        String s2 = matcher.group(1);
                        String s = matcher.group(2);
                        int j = Integer.parseInt(s);
                        g_2336_b resourcelocation1 = new g_2336_b(s2);
                        if (V_3137_a.q_4610_l.R_4764_Y(resourcelocation1)) {
                            T_2915_h block1 = V_3137_a.q_4610_l.n_1700_B(resourcelocation1);
                            mapBlockToEntityData.put(block1, j);
                            continue;
                        }
                        SMCLog.warning("Unknown block name %s", s2);
                        continue;
                    }
                    SMCLog.warning("unmatched %s\n", s1);
                }
            }
            catch (Exception exception2) {
                SMCLog.warning("Error parsing mc_Entity_x.txt");
            }
        }
        if (bufferedreader != null) {
            try {
                bufferedreader.close();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    private static IntBuffer fillIntBufferZero(IntBuffer buf) {
        int i = buf.limit();
        for (int j = buf.position(); j < i; ++j) {
            buf.put(j, 0);
        }
        return buf;
    }

    private static DrawBuffers fillIntBufferZero(DrawBuffers buf) {
        int i = buf.limit();
        for (int j = buf.position(); j < i; ++j) {
            buf.put(j, 0);
        }
        return buf;
    }

    public static void uninit() {
        if (isShaderPackInitialized) {
            Shaders.checkGLError("Shaders.uninit pre");
            for (int i = 0; i < ProgramsAll.length; ++i) {
                Program program = ProgramsAll[i];
                if (program.getRef() != 0) {
                    ARBShaderObjects.glDeleteObjectARB((int)program.getRef());
                    Shaders.checkGLError("del programRef");
                }
                program.setRef(0);
                program.setId(0);
                program.setDrawBufSettings(null);
                program.setDrawBuffers(null);
                program.setCompositeMipmapSetting(0);
                ComputeProgram[] acomputeprogram = program.getComputePrograms();
                for (int j = 0; j < acomputeprogram.length; ++j) {
                    ComputeProgram computeprogram = acomputeprogram[j];
                    if (computeprogram.getRef() != 0) {
                        ARBShaderObjects.glDeleteObjectARB((int)computeprogram.getRef());
                        Shaders.checkGLError("del programRef");
                    }
                    computeprogram.setRef(0);
                    computeprogram.setId(0);
                }
                program.setComputePrograms(new ComputeProgram[0]);
            }
            hasDeferredPrograms = false;
            hasShadowcompPrograms = false;
            hasPreparePrograms = false;
            if (dfb != null) {
                dfb.delete();
                dfb = null;
                Shaders.checkGLError("del dfb");
            }
            if (sfb != null) {
                sfb.delete();
                sfb = null;
                Shaders.checkGLError("del sfb");
            }
            if (dfbDrawBuffers != null) {
                Shaders.fillIntBufferZero(dfbDrawBuffers);
            }
            if (sfbDrawBuffers != null) {
                Shaders.fillIntBufferZero(sfbDrawBuffers);
            }
            if (noiseTexture != null) {
                noiseTexture.deleteTexture();
                noiseTexture = null;
            }
            for (int k = 0; k < colorImageUnit.length; ++k) {
                X_933_l.n_1700_B(colorImageUnit[k], 0, 0, false, 0, 35000, 32856);
            }
            SMCLog.info("Uninit");
            hasShadowMap = false;
            shouldSkipDefaultShadow = false;
            isShaderPackInitialized = false;
            Shaders.checkGLError("Shaders.uninit");
        }
    }

    public static void scheduleResize() {
        renderDisplayHeight = 0;
    }

    public static void scheduleResizeShadow() {
        needResizeShadow = true;
    }

    private static void resize() {
        renderDisplayWidth = mc.RealmsServerPing().u_2550_I();
        renderDisplayHeight = mc.RealmsServerPing().M_588_G();
        renderWidth = Math.round((float)renderDisplayWidth * configRenderResMul);
        renderHeight = Math.round((float)renderDisplayHeight * configRenderResMul);
        Shaders.setupFrameBuffer();
    }

    private static void resizeShadow() {
        needResizeShadow = false;
        shadowMapWidth = Math.round((float)spShadowMapWidth * configShadowResMul);
        shadowMapHeight = Math.round((float)spShadowMapHeight * configShadowResMul);
        Shaders.setupShadowFrameBuffer();
    }

    private static void setupFrameBuffer() {
        if (dfb != null) {
            dfb.delete();
        }
        boolean[] aboolean = ArrayUtils.newBoolean(usedDepthBuffers, true);
        boolean[] aboolean1 = new boolean[usedDepthBuffers];
        boolean[] aboolean2 = new boolean[usedColorBuffers];
        int[] aint = (int[])(bindImageTextures ? colorImageUnit : null);
        dfb = new ShadersFramebuffer("dfb", renderWidth, renderHeight, usedColorBuffers, usedDepthBuffers, 8, aboolean, aboolean1, aboolean2, colorBufferSizes, gbuffersFormat, colorTextureImageUnit, depthTextureImageUnit, aint, dfbDrawBuffers);
        dfb.setup();
    }

    public static int getPixelFormat(int internalFormat) {
        switch (internalFormat) {
            case 33329: 
            case 33335: 
            case 36238: 
            case 36239: {
                return 36251;
            }
            case 33330: 
            case 33336: 
            case 36220: 
            case 36221: {
                return 36251;
            }
            case 33331: 
            case 33337: 
            case 36232: 
            case 36233: {
                return 36251;
            }
            case 33332: 
            case 33338: 
            case 36214: 
            case 36215: {
                return 36251;
            }
            case 33333: 
            case 33339: 
            case 36226: 
            case 36227: {
                return 36251;
            }
            case 33334: 
            case 33340: 
            case 36208: 
            case 36209: {
                return 36251;
            }
        }
        return 32993;
    }

    private static void setupShadowFrameBuffer() {
        if (hasShadowMap) {
            isShadowPass = true;
            if (sfb != null) {
                sfb.delete();
            }
            DynamicDimension[] adynamicdimension = new DynamicDimension[2];
            int[] aint = (int[])(bindImageTextures ? shadowColorImageUnit : null);
            sfb = new ShadersFramebuffer("sfb", shadowMapWidth, shadowMapHeight, usedShadowColorBuffers, usedShadowDepthBuffers, 8, shadowFilterNearest, shadowHardwareFilteringEnabled, shadowColorFilterNearest, adynamicdimension, shadowBuffersFormat, shadowColorTextureImageUnit, shadowDepthTextureImageUnit, aint, sfbDrawBuffers);
            sfb.setup();
            isShadowPass = false;
        }
    }

    public static void beginRender(MinecraftClient minecraft, h_3572_K activeRenderInfo, float partialTicks, long finishTimeNano) {
        block14: {
            Shaders.checkGLError("pre beginRender");
            Shaders.checkWorldChanged(Shaders.mc.Y_601_j);
            mc = minecraft;
            mc.PlayerInfo().n_1700_B("init");
            entityRenderer = Shaders.mc.s_956_w;
            if (!isShaderPackInitialized) {
                try {
                    Shaders.init();
                }
                catch (IllegalStateException illegalstateexception) {
                    if (!Config.normalize(illegalstateexception.getMessage()).equals("Function is not supported")) break block14;
                    Shaders.printChatAndLogError("[Shaders] Error: " + illegalstateexception.getMessage());
                    illegalstateexception.printStackTrace();
                    Shaders.setShaderPack(SHADER_PACK_NAME_NONE);
                    return;
                }
            }
        }
        if (mc.RealmsServerPing().u_2550_I() != renderDisplayWidth || mc.RealmsServerPing().M_588_G() != renderDisplayHeight) {
            Shaders.resize();
        }
        if (needResizeShadow) {
            Shaders.resizeShadow();
        }
        if (++frameCounter >= 720720) {
            frameCounter = 0;
        }
        systemTime = System.currentTimeMillis();
        if (lastSystemTime == 0L) {
            lastSystemTime = systemTime;
        }
        diffSystemTime = systemTime - lastSystemTime;
        lastSystemTime = systemTime;
        frameTime = (float)diffSystemTime / 1000.0f;
        frameTimeCounter += frameTime;
        frameTimeCounter %= 3600.0f;
        pointOfViewChanged = pointOfView != Shaders.mc.P_4830_p.P_4830_p();
        pointOfView = Shaders.mc.P_4830_p.P_4830_p();
        X_933_l.g_221_o();
        ShadersRender.updateActiveRenderInfo(activeRenderInfo, minecraft, partialTicks);
        X_933_l.e_2887_G();
        k_4690_i clientworld = Shaders.mc.Y_601_j;
        if (clientworld != null) {
            worldTime = clientworld.Z_976_R();
            diffWorldTime = (worldTime - lastWorldTime) % 24000L;
            if (diffWorldTime < 0L) {
                diffWorldTime += 24000L;
            }
            lastWorldTime = worldTime;
            moonPhase = clientworld.t_4043_B();
            rainStrength = clientworld.w_1484_f(partialTicks);
            float f = (float)diffSystemTime * 0.01f;
            float f1 = (float)Math.exp(Math.log(0.5) * (double)f / (double)(wetness < rainStrength ? drynessHalfLife : wetnessHalfLife));
            wetness = wetness * f1 + rainStrength * (1.0f - f1);
            N_4263_v entity = activeRenderInfo.v_4262_N();
            if (entity != null) {
                isSleeping = entity instanceof r_4811_B && ((r_4811_B)entity).z_2372_L();
                eyePosY = (float)activeRenderInfo.J_1907_R().J_1907_R();
                eyeBrightness = mc.O_508_d().n_1700_B(entity, partialTicks);
                float f2 = (float)diffSystemTime * 0.01f;
                float f3 = (float)Math.exp(Math.log(0.5) * (double)f2 / (double)eyeBrightnessHalflife);
                eyeBrightnessFadeX = eyeBrightnessFadeX * f3 + (float)(eyeBrightness & 0xFFFF) * (1.0f - f3);
                eyeBrightnessFadeY = eyeBrightnessFadeY * f3 + (float)(eyeBrightness >> 16) * (1.0f - f3);
                FluidState fluidstate = activeRenderInfo.s_956_w();
                isEyeInWater = fluidstate.n_1700_B(FluidTags.J_1907_R) ? 1 : (fluidstate.n_1700_B(FluidTags.R_4764_Y) ? 2 : 0);
                if (entity instanceof r_4811_B) {
                    r_4811_B livingentity = (r_4811_B)entity;
                    nightVision = 0.0f;
                    if (livingentity.J_1907_R(MobEffects.M_182_A)) {
                        M_660_m gamerenderer = entityRenderer;
                        nightVision = M_660_m.n_1700_B(livingentity, partialTicks);
                    }
                    blindness = 0.0f;
                    if (livingentity.J_1907_R(MobEffects.Q_4569_t)) {
                        int i = livingentity.R_4764_Y(MobEffects.Q_4569_t).J_1907_R();
                        blindness = Config.limit((float)i / 20.0f, 0.0f, 1.0f);
                    }
                }
                e_2866_D vector3d = clientworld.n_1700_B(entity.b_2312_j(), partialTicks);
                vector3d = CustomColors.getWorldSkyColor(vector3d, clientworld, entity, partialTicks);
                skyColorR = (float)vector3d.J_1907_R;
                skyColorG = (float)vector3d.R_4764_Y;
                skyColorB = (float)vector3d.G_564_y;
            }
        }
        isRenderingWorld = true;
        isCompositeRendered = false;
        isShadowPass = false;
        isHandRenderedMain = false;
        isHandRenderedOff = false;
        skipRenderHandMain = false;
        skipRenderHandOff = false;
        dfb.setColorBuffersFiltering(9729, 9729);
        Shaders.bindGbuffersTextures();
        dfb.bindColorImages(true);
        if (sfb != null) {
            sfb.bindColorImages(true);
        }
        previousCameraPositionX = cameraPositionX;
        previousCameraPositionY = cameraPositionY;
        previousCameraPositionZ = cameraPositionZ;
        ((Buffer)previousProjection).position(0);
        ((Buffer)projection).position(0);
        previousProjection.put(projection);
        ((Buffer)previousProjection).position(0);
        ((Buffer)projection).position(0);
        ((Buffer)previousModelView).position(0);
        ((Buffer)modelView).position(0);
        previousModelView.put(modelView);
        ((Buffer)previousModelView).position(0);
        ((Buffer)modelView).position(0);
        Shaders.checkGLError("beginRender");
        ShadersRender.renderShadowMap(entityRenderer, activeRenderInfo, 0, partialTicks, finishTimeNano);
        mc.PlayerInfo().R_4764_Y();
        dfb.setColorTextures(true);
        Shaders.setRenderStage(RenderStage.NONE);
        Shaders.checkGLError("end beginRender");
    }

    private static void bindGbuffersTextures() {
        Shaders.bindTextures(4, customTexturesGbuffers);
    }

    private static void bindTextures(int startColorBuffer, ICustomTexture[] customTextures) {
        if (sfb != null) {
            sfb.bindColorTextures(0);
            sfb.bindDepthTextures(shadowDepthTextureImageUnit);
        }
        dfb.bindColorTextures(startColorBuffer);
        dfb.bindDepthTextures(depthTextureImageUnit);
        if (noiseTextureEnabled) {
            X_933_l.t_1786_h(33984 + noiseTexture.getTextureUnit());
            X_933_l.w_1457_N(noiseTexture.getTextureId());
            X_933_l.t_1786_h(33984);
        }
        Shaders.bindCustomTextures(customTextures);
    }

    public static void checkWorldChanged(b_4507_u worldin) {
        if (currentWorld != worldin) {
            b_4507_u world = currentWorld;
            currentWorld = worldin;
            if (currentWorld == null) {
                cameraPositionX = 0.0;
                cameraPositionY = 0.0;
                cameraPositionZ = 0.0;
                previousCameraPositionX = 0.0;
                previousCameraPositionY = 0.0;
                previousCameraPositionZ = 0.0;
            }
            Shaders.setCameraOffset(mc.g_2268_R());
            int i = WorldUtils.getDimensionId(world);
            int j = WorldUtils.getDimensionId(worldin);
            if (j != i) {
                boolean flag = shaderPackDimensions.contains(i);
                boolean flag1 = shaderPackDimensions.contains(j);
                if (flag || flag1) {
                    Shaders.uninit();
                }
            }
            Smoother.resetValues();
        }
    }

    public static void beginRenderPass(float partialTicks, long finishTimeNano) {
        if (!isShadowPass) {
            dfb.bindFramebuffer();
            GL11.glViewport((int)0, (int)0, (int)renderWidth, (int)renderHeight);
            GlState.setDrawBuffers(null);
            ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
            Shaders.useProgram(ProgramTextured);
            Shaders.checkGLError("end beginRenderPass");
        }
    }

    public static void setViewport(int vx, int vy, int vw, int vh) {
        X_933_l.n_1700_B(true, true, true, true);
        if (isShadowPass) {
            GL11.glViewport((int)0, (int)0, (int)shadowMapWidth, (int)shadowMapHeight);
        } else {
            GL11.glViewport((int)0, (int)0, (int)renderWidth, (int)renderHeight);
            dfb.bindFramebuffer();
            isRenderingDfb = true;
            X_933_l.A_4115_X();
            X_933_l.P_4830_p();
            GlState.setDrawBuffers(drawBuffersNone);
            Shaders.useProgram(ProgramTextured);
            Shaders.checkGLError("beginRenderPass");
        }
    }

    public static void setFogMode(int value) {
        fogMode = value;
        if (fogEnabled) {
            Shaders.setProgramUniform1i(uniform_fogMode, value);
        }
    }

    public static void setFogColor(float r, float g, float b) {
        fogColorR = r;
        fogColorG = g;
        fogColorB = b;
        Shaders.setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
    }

    public static void setClearColor(float red, float green, float blue, float alpha) {
        clearColor.n_1700_B(red, green, blue, 1.0f);
    }

    public static void clearRenderBuffer() {
        if (isShadowPass) {
            Shaders.checkGLError("shadow clear pre");
            sfb.clearDepthBuffer(new Z_2491_A(1.0f, 1.0f, 1.0f, 1.0f));
            Shaders.checkGLError("shadow clear");
        } else {
            Shaders.checkGLError("clear pre");
            Z_2491_A[] avector4f = new Z_2491_A[usedColorBuffers];
            for (int i = 0; i < avector4f.length; ++i) {
                avector4f[i] = Shaders.getBufferClearColor(i);
            }
            dfb.clearColorBuffers(gbuffersClear, avector4f);
            dfb.setDrawBuffers();
            Shaders.checkFramebufferStatus("clear");
            Shaders.checkGLError("clear");
        }
    }

    public static void renderPrepare() {
        if (hasPreparePrograms) {
            Shaders.renderPrepareComposites();
            Shaders.bindGbuffersTextures();
            dfb.setDrawBuffers();
            dfb.setColorTextures(true);
        }
    }

    private static Z_2491_A getBufferClearColor(int buffer) {
        Z_2491_A vector4f = gbuffersClearColor[buffer];
        if (vector4f != null) {
            return vector4f;
        }
        if (buffer == 0) {
            return clearColor;
        }
        return buffer == 1 ? CLEAR_COLOR_1 : CLEAR_COLOR_0;
    }

    public static void setCamera(g_221_o matrixStackIn, h_3572_K activeRenderInfo, float partialTicks) {
        N_4263_v entity = activeRenderInfo.v_4262_N();
        e_2866_D vector3d = activeRenderInfo.J_1907_R();
        double d0 = vector3d.J_1907_R;
        double d1 = vector3d.R_4764_Y;
        double d2 = vector3d.G_564_y;
        Shaders.updateCameraOffset(entity);
        cameraPositionX = d0 - (double)cameraOffsetX;
        cameraPositionY = d1;
        cameraPositionZ = d2 - (double)cameraOffsetZ;
        Shaders.updateProjectionMatrix();
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        D_1098_v matrix4f1 = new D_1098_v(matrix4f);
        matrix4f1.G_564_y();
        matrix4f1.n_1700_B(tempMat);
        ((Buffer)modelView).position(0);
        modelView.put(tempMat);
        SMath.invertMat4FBFA((FloatBuffer)((Buffer)modelViewInverse).position(0), (FloatBuffer)((Buffer)modelView).position(0), faModelViewInverse, faModelView);
        ((Buffer)modelView).position(0);
        ((Buffer)modelViewInverse).position(0);
        Shaders.checkGLError("setCamera");
    }

    public static void updateProjectionMatrix() {
        GL43.glGetFloatv((int)2983, (FloatBuffer)((FloatBuffer)((Buffer)projection).position(0)));
        SMath.invertMat4FBFA((FloatBuffer)((Buffer)projectionInverse).position(0), (FloatBuffer)((Buffer)projection).position(0), faProjectionInverse, faProjection);
        ((Buffer)projection).position(0);
        ((Buffer)projectionInverse).position(0);
    }

    private static void updateShadowProjectionMatrix() {
        GL43.glGetFloatv((int)2983, (FloatBuffer)((FloatBuffer)((Buffer)shadowProjection).position(0)));
        SMath.invertMat4FBFA((FloatBuffer)((Buffer)shadowProjectionInverse).position(0), (FloatBuffer)((Buffer)shadowProjection).position(0), faShadowProjectionInverse, faShadowProjection);
        ((Buffer)shadowProjection).position(0);
        ((Buffer)shadowProjectionInverse).position(0);
    }

    private static void updateCameraOffset(N_4263_v viewEntity) {
        double d0 = Math.abs(cameraPositionX - previousCameraPositionX);
        double d1 = Math.abs(cameraPositionZ - previousCameraPositionZ);
        double d2 = Math.abs(cameraPositionX);
        double d3 = Math.abs(cameraPositionZ);
        if (d0 > 1000.0 || d1 > 1000.0 || d2 > 1000000.0 || d3 > 1000000.0) {
            Shaders.setCameraOffset(viewEntity);
        }
    }

    private static void setCameraOffset(N_4263_v viewEntity) {
        if (viewEntity == null) {
            cameraOffsetX = 0;
            cameraOffsetZ = 0;
        } else {
            cameraOffsetX = (int)viewEntity.O_3598_v() / 1000 * 1000;
            cameraOffsetZ = (int)viewEntity.l_2647_k() / 1000 * 1000;
        }
    }

    public static void setCameraShadow(g_221_o matrixStack, h_3572_K activeRenderInfo, float partialTicks) {
        float f1;
        N_4263_v entity = activeRenderInfo.v_4262_N();
        e_2866_D vector3d = activeRenderInfo.J_1907_R();
        double d0 = vector3d.J_1907_R;
        double d1 = vector3d.R_4764_Y;
        double d2 = vector3d.G_564_y;
        Shaders.updateCameraOffset(entity);
        cameraPositionX = d0 - (double)cameraOffsetX;
        cameraPositionY = d1;
        cameraPositionZ = d2 - (double)cameraOffsetZ;
        GL43.glViewport((int)0, (int)0, (int)shadowMapWidth, (int)shadowMapHeight);
        GL43.glMatrixMode((int)5889);
        GL43.glLoadIdentity();
        if (shadowMapIsOrtho) {
            GL43.glOrtho((double)(-shadowMapHalfPlane), (double)shadowMapHalfPlane, (double)(-shadowMapHalfPlane), (double)shadowMapHalfPlane, (double)0.05f, (double)256.0);
        } else {
            X_933_l.n_1700_B(D_1098_v.n_1700_B((double)shadowMapFOV, (float)shadowMapWidth / (float)shadowMapHeight, 0.05f, 256.0f));
        }
        matrixStack.n_1700_B(0.0, 0.0, -100.0);
        matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(90.0f));
        celestialAngle = Shaders.mc.Y_601_j.G_564_y(partialTicks);
        sunAngle = celestialAngle < 0.75f ? celestialAngle + 0.25f : celestialAngle - 0.75f;
        float f = celestialAngle * -360.0f;
        float f2 = f1 = shadowAngleInterval > 0.0f ? f % shadowAngleInterval - shadowAngleInterval * 0.5f : 0.0f;
        if ((double)sunAngle <= 0.5) {
            matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f - f1));
            matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(sunPathRotation));
            shadowAngle = sunAngle;
        } else {
            matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(f + 180.0f - f1));
            matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(sunPathRotation));
            shadowAngle = sunAngle - 0.5f;
        }
        if (shadowMapIsOrtho) {
            float f22 = shadowIntervalSize;
            float f3 = f22 / 2.0f;
            matrixStack.n_1700_B((double)((float)d0 % f22 - f3), (double)((float)d1 % f22 - f3), (double)((float)d2 % f22 - f3));
        }
        float f9 = sunAngle * ((float)Math.PI * 2);
        float f10 = (float)Math.cos(f9);
        float f4 = (float)Math.sin(f9);
        float f5 = sunPathRotation * ((float)Math.PI * 2);
        float f6 = f10;
        float f7 = f4 * (float)Math.cos(f5);
        float f8 = f4 * (float)Math.sin(f5);
        if ((double)sunAngle > 0.5) {
            f6 = -f10;
            f7 = -f7;
            f8 = -f8;
        }
        Shaders.shadowLightPositionVector[0] = f6;
        Shaders.shadowLightPositionVector[1] = f7;
        Shaders.shadowLightPositionVector[2] = f8;
        Shaders.shadowLightPositionVector[3] = 0.0f;
        Shaders.updateShadowProjectionMatrix();
        D_1098_v matrix4f = matrixStack.R_4764_Y().n_1700_B();
        matrix4f.n_1700_B((FloatBuffer)((Buffer)shadowModelView).position(0));
        SMath.invertMat4FBFA((FloatBuffer)((Buffer)shadowModelViewInverse).position(0), (FloatBuffer)((Buffer)shadowModelView).position(0), faShadowModelViewInverse, faShadowModelView);
        ((Buffer)shadowModelView).position(0);
        ((Buffer)shadowModelViewInverse).position(0);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, previousProjection);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, previousModelView);
        Shaders.setProgramUniformMatrix4ARB(uniform_shadowProjection, false, shadowProjection);
        Shaders.setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, shadowProjectionInverse);
        Shaders.setProgramUniformMatrix4ARB(uniform_shadowModelView, false, shadowModelView);
        Shaders.setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, shadowModelViewInverse);
        Shaders.checkGLError("setCamera");
    }

    public static void preCelestialRotate(g_221_o matrixStackIn) {
        matrixStackIn.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(sunPathRotation * 1.0f));
        Shaders.checkGLError("preCelestialRotate");
    }

    public static void postCelestialRotate(g_221_o matrixStackIn) {
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        D_1098_v matrix4f1 = new D_1098_v(matrix4f);
        matrix4f1.G_564_y();
        matrix4f1.n_1700_B(tempMat);
        SMath.multiplyMat4xVec4(sunPosition, tempMat, sunPosModelView);
        SMath.multiplyMat4xVec4(moonPosition, tempMat, moonPosModelView);
        System.arraycopy(shadowAngle == sunAngle ? sunPosition : moonPosition, 0, shadowLightPosition, 0, 3);
        Shaders.setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
        Shaders.setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
        Shaders.setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
        if (customUniforms != null) {
            customUniforms.update();
        }
        Shaders.checkGLError("postCelestialRotate");
    }

    public static void setUpPosition(g_221_o matrixStackIn) {
        D_1098_v matrix4f = matrixStackIn.R_4764_Y().n_1700_B();
        D_1098_v matrix4f1 = new D_1098_v(matrix4f);
        matrix4f1.G_564_y();
        matrix4f1.n_1700_B(tempMat);
        SMath.multiplyMat4xVec4(upPosition, tempMat, upPosModelView);
        Shaders.setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
        if (customUniforms != null) {
            customUniforms.update();
        }
    }

    public static void drawComposite() {
        GL43.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Shaders.drawCompositeQuad();
        int i = activeProgram.getCountInstances();
        if (i > 1) {
            for (int j = 1; j < i; ++j) {
                uniform_instanceId.setValue(j);
                Shaders.drawCompositeQuad();
            }
            uniform_instanceId.setValue(0);
        }
    }

    private static void drawCompositeQuad() {
        if (!Shaders.canRenderQuads()) {
            GL43.glBegin((int)5);
            GL43.glTexCoord2f((float)0.0f, (float)0.0f);
            GL43.glVertex3f((float)0.0f, (float)0.0f, (float)0.0f);
            GL43.glTexCoord2f((float)1.0f, (float)0.0f);
            GL43.glVertex3f((float)1.0f, (float)0.0f, (float)0.0f);
            GL43.glTexCoord2f((float)0.0f, (float)1.0f);
            GL43.glVertex3f((float)0.0f, (float)1.0f, (float)0.0f);
            GL43.glTexCoord2f((float)1.0f, (float)1.0f);
            GL43.glVertex3f((float)1.0f, (float)1.0f, (float)0.0f);
            GL43.glEnd();
        } else {
            GL43.glBegin((int)7);
            GL43.glTexCoord2f((float)0.0f, (float)0.0f);
            GL43.glVertex3f((float)0.0f, (float)0.0f, (float)0.0f);
            GL43.glTexCoord2f((float)1.0f, (float)0.0f);
            GL43.glVertex3f((float)1.0f, (float)0.0f, (float)0.0f);
            GL43.glTexCoord2f((float)1.0f, (float)1.0f);
            GL43.glVertex3f((float)1.0f, (float)1.0f, (float)0.0f);
            GL43.glTexCoord2f((float)0.0f, (float)1.0f);
            GL43.glVertex3f((float)0.0f, (float)1.0f, (float)0.0f);
            GL43.glEnd();
        }
    }

    public static void renderDeferred() {
        if (!isShadowPass) {
            boolean flag = Shaders.checkBufferFlip(dfb, ProgramDeferredPre);
            if (hasDeferredPrograms) {
                Shaders.checkGLError("pre-render Deferred");
                Shaders.renderDeferredComposites();
                flag = true;
            }
            if (flag) {
                Shaders.bindGbuffersTextures();
                dfb.setColorTextures(true);
                DrawBuffers drawbuffers = ProgramWater.getDrawBuffers() != null ? ProgramWater.getDrawBuffers() : dfb.getDrawBuffers();
                GlState.setDrawBuffers(drawbuffers);
                X_933_l.t_1786_h(33984);
                mc.G_624_v().n_1700_B(L_3848_p.n_1700_B);
            }
        }
    }

    public static void renderCompositeFinal() {
        if (!isShadowPass) {
            Shaders.checkBufferFlip(dfb, ProgramCompositePre);
            Shaders.checkGLError("pre-render CompositeFinal");
            Shaders.renderComposites();
        }
    }

    private static boolean checkBufferFlip(ShadersFramebuffer framebuffer, Program program) {
        boolean flag = false;
        Boolean[] aboolean = program.getBuffersFlip();
        for (int i = 0; i < usedColorBuffers; ++i) {
            if (!Config.isTrue(aboolean[i])) continue;
            framebuffer.flipColorTexture(i);
            flag = true;
        }
        return flag;
    }

    private static void renderComposites() {
        if (!isShadowPass) {
            Shaders.renderComposites(ProgramsComposite, true, customTexturesComposite);
        }
    }

    private static void renderDeferredComposites() {
        if (!isShadowPass) {
            Shaders.renderComposites(ProgramsDeferred, false, customTexturesDeferred);
        }
    }

    public static void renderPrepareComposites() {
        Shaders.renderComposites(ProgramsPrepare, false, customTexturesPrepare);
    }

    private static void renderComposites(Program[] ps, boolean renderFinal, ICustomTexture[] customTextures) {
        Shaders.renderComposites(dfb, ps, renderFinal, customTextures);
    }

    public static void renderShadowComposites() {
        Shaders.renderComposites(sfb, ProgramsShadowcomp, false, customTexturesShadowcomp);
    }

    private static void renderComposites(ShadersFramebuffer framebuffer, Program[] ps, boolean renderFinal, ICustomTexture[] customTextures) {
        GL43.glPushMatrix();
        GL43.glLoadIdentity();
        GL43.glMatrixMode((int)5889);
        GL43.glPushMatrix();
        GL43.glLoadIdentity();
        GL43.glOrtho((double)0.0, (double)1.0, (double)0.0, (double)1.0, (double)0.0, (double)1.0);
        GL43.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        X_933_l.v_4276_D();
        X_933_l.G_564_y();
        X_933_l.h_1847_R();
        X_933_l.P_4830_p();
        X_933_l.J_1907_R(519);
        X_933_l.n_1700_B(false);
        X_933_l.v_4262_N();
        Shaders.bindTextures(0, customTextures);
        framebuffer.bindColorImages(true);
        framebuffer.setColorTextures(false);
        framebuffer.setDepthTexture();
        framebuffer.setDrawBuffers();
        Shaders.checkGLError("pre-composite");
        for (int i = 0; i < ps.length; ++i) {
            Program program = ps[i];
            Shaders.dispatchComputes(framebuffer, program.getComputePrograms());
            if (program.getId() == 0) continue;
            Shaders.useProgram(program);
            Shaders.checkGLError(program.getName());
            if (program.hasCompositeMipmaps()) {
                framebuffer.genCompositeMipmap(program.getCompositeMipmapSetting());
            }
            Shaders.preDrawComposite(framebuffer, program);
            Shaders.drawComposite();
            Shaders.postDrawComposite(framebuffer, program);
            framebuffer.flipColorTextures(program.getToggleColorTextures());
        }
        Shaders.checkGLError("composite");
        if (renderFinal) {
            Shaders.renderFinal();
            isCompositeRendered = true;
        }
        X_933_l.v_4276_D();
        X_933_l.P_1922_E();
        X_933_l.Q_4569_t();
        X_933_l.J_1907_R(515);
        X_933_l.n_1700_B(true);
        GL43.glPopMatrix();
        GL43.glMatrixMode((int)5888);
        GL43.glPopMatrix();
        Shaders.useProgram(ProgramNone);
    }

    private static void preDrawComposite(ShadersFramebuffer framebuffer, Program program) {
        RenderScale renderscale;
        int i = framebuffer.getWidth();
        int j = framebuffer.getHeight();
        if (program.getDrawSize() != null) {
            Dimension dimension = program.getDrawSize().getDimension(i, j);
            i = dimension.width;
            j = dimension.height;
            FixedFramebuffer fixedframebuffer = framebuffer.getFixedFramebuffer(i, j, program.getDrawBuffers(), false);
            fixedframebuffer.bindFramebuffer();
            GL43.glViewport((int)0, (int)0, (int)i, (int)j);
        }
        if ((renderscale = program.getRenderScale()) != null) {
            int j1 = (int)((float)i * renderscale.getOffsetX());
            int k = (int)((float)j * renderscale.getOffsetY());
            int l = (int)((float)i * renderscale.getScale());
            int i1 = (int)((float)j * renderscale.getScale());
            GL43.glViewport((int)j1, (int)k, (int)l, (int)i1);
        }
    }

    private static void postDrawComposite(ShadersFramebuffer framebuffer, Program program) {
        RenderScale renderscale;
        if (program.getDrawSize() != null) {
            framebuffer.bindFramebuffer();
            GL43.glViewport((int)0, (int)0, (int)framebuffer.getWidth(), (int)framebuffer.getHeight());
        }
        if ((renderscale = activeProgram.getRenderScale()) != null) {
            GL43.glViewport((int)0, (int)0, (int)framebuffer.getWidth(), (int)framebuffer.getHeight());
        }
    }

    public static void dispatchComputes(ShadersFramebuffer framebuffer, ComputeProgram[] cps) {
        for (int i = 0; i < cps.length; ++i) {
            ComputeProgram computeprogram = cps[i];
            Shaders.dispatchCompute(computeprogram);
            if (!computeprogram.hasCompositeMipmaps()) continue;
            framebuffer.genCompositeMipmap(computeprogram.getCompositeMipmapSetting());
        }
    }

    public static void dispatchCompute(ComputeProgram cp) {
        if (dfb != null) {
            ARBShaderObjects.glUseProgramObjectARB((int)cp.getId());
            if (Shaders.checkGLError("useComputeProgram") != 0) {
                cp.setId(0);
            } else {
                shaderUniforms.setProgram(cp.getId());
                if (customUniforms != null) {
                    customUniforms.setProgram(cp.getId());
                }
                Shaders.setProgramUniforms(cp.getProgramStage());
                Shaders.setImageUniforms();
                dfb.bindColorImages(true);
                z_3539_x vector3i = cp.getWorkGroups();
                if (vector3i == null) {
                    P_3504_Q vector2f = cp.getWorkGroupsRender();
                    if (vector2f == null) {
                        vector2f = new P_3504_Q(1.0f, 1.0f);
                    }
                    int i = (int)Math.ceil((float)renderWidth * vector2f.t_148_a);
                    int j = (int)Math.ceil((float)renderHeight * vector2f.s_956_w);
                    z_3539_x vector3i1 = cp.getLocalSize();
                    int k = (int)Math.ceil(1.0 * (double)i / (double)vector3i1.getX());
                    int l = (int)Math.ceil(1.0 * (double)j / (double)vector3i1.getY());
                    vector3i = new z_3539_x(k, l, 1);
                }
                GL43.glMemoryBarrier((int)40);
                GL43.glDispatchCompute((int)vector3i.getX(), (int)vector3i.getY(), (int)vector3i.getZ());
                GL43.glMemoryBarrier((int)40);
                Shaders.checkGLError("compute");
            }
        }
    }

    private static void renderFinal() {
        Shaders.dispatchComputes(dfb, ProgramFinal.getComputePrograms());
        isRenderingDfb = false;
        mc.G_564_y().J_1907_R(true);
        X_933_l.n_1700_B(T_3334_o.n_1700_B, T_3334_o.R_4764_Y, 3553, mc.G_564_y().u_2550_I(), 0);
        GL43.glViewport((int)0, (int)0, (int)mc.RealmsServerPing().u_2550_I(), (int)mc.RealmsServerPing().M_588_G());
        X_933_l.n_1700_B(true);
        GL43.glClearColor((float)clearColor.n_1700_B(), (float)clearColor.J_1907_R(), (float)clearColor.R_4764_Y(), (float)1.0f);
        GL43.glClear((int)16640);
        GL43.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        X_933_l.v_4276_D();
        X_933_l.G_564_y();
        X_933_l.h_1847_R();
        X_933_l.P_4830_p();
        X_933_l.J_1907_R(519);
        X_933_l.n_1700_B(false);
        Shaders.checkGLError("pre-final");
        Shaders.useProgram(ProgramFinal);
        Shaders.checkGLError("final");
        if (ProgramFinal.hasCompositeMipmaps()) {
            dfb.genCompositeMipmap(ProgramFinal.getCompositeMipmapSetting());
        }
        Shaders.drawComposite();
        Shaders.checkGLError("renderCompositeFinal");
    }

    public static void endRender() {
        if (isShadowPass) {
            Shaders.checkGLError("shadow endRender");
        } else {
            if (!isCompositeRendered) {
                Shaders.renderCompositeFinal();
            }
            isRenderingWorld = false;
            X_933_l.n_1700_B(true, true, true, true);
            Shaders.useProgram(ProgramNone);
            Shaders.setRenderStage(RenderStage.NONE);
            W_3265_k.J_1907_R();
            Shaders.checkGLError("endRender end");
        }
    }

    public static void beginSky() {
        isRenderingSky = true;
        fogEnabled = true;
        Shaders.useProgram(ProgramSkyTextured);
        Shaders.pushEntity(-2, 0);
        Shaders.setRenderStage(RenderStage.SKY);
    }

    public static void setSkyColor(e_2866_D v3color) {
        skyColorR = (float)v3color.J_1907_R;
        skyColorG = (float)v3color.R_4764_Y;
        skyColorB = (float)v3color.G_564_y;
        Shaders.setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
    }

    public static void drawHorizon(g_221_o matrixStackIn) {
        D_3318_r bufferbuilder = l_3747_P.n_1700_B().R_4764_Y();
        float f = Shaders.mc.P_4830_p.J_1907_R * 16;
        double d0 = (double)f * 0.9238;
        double d1 = (double)f * 0.3826;
        double d2 = -d1;
        double d3 = -d0;
        double d4 = 16.0;
        double d5 = 0.0;
        b_4507_u world = currentWorld;
        double voidFogHeight = 0.0;
        boolean flag = false;
        if (world instanceof k_4690_i) {
            k_4690_i clientWorld = (k_4690_i)world;
            voidFogHeight = clientWorld.Y_259_p().P_4830_p();
            d5 = -cameraPositionY + voidFogHeight + 12.0 - 16.0;
            flag = cameraPositionY < voidFogHeight;
        } else if (world instanceof c_3005_b) {
            c_3005_b botWorld = (c_3005_b)world;
            voidFogHeight = botWorld.Q_2552_b().P_4830_p();
            d5 = -cameraPositionY + voidFogHeight + 12.0 - 16.0;
            boolean bl = flag = cameraPositionY < voidFogHeight;
        }
        if (flag) {
            d5 = -4.0;
        }
        X_933_l.g_221_o();
        X_933_l.n_1700_B(matrixStackIn.R_4764_Y().n_1700_B());
        bufferbuilder.n_1700_B(7, E_688_b.w_1457_N);
        bufferbuilder.pos(d2, d5, d3).endVertex();
        bufferbuilder.pos(d2, d4, d3).endVertex();
        bufferbuilder.pos(d3, d4, d2).endVertex();
        bufferbuilder.pos(d3, d5, d2).endVertex();
        bufferbuilder.pos(d3, d5, d2).endVertex();
        bufferbuilder.pos(d3, d4, d2).endVertex();
        bufferbuilder.pos(d3, d4, d1).endVertex();
        bufferbuilder.pos(d3, d5, d1).endVertex();
        bufferbuilder.pos(d3, d5, d1).endVertex();
        bufferbuilder.pos(d3, d4, d1).endVertex();
        bufferbuilder.pos(d2, d4, d0).endVertex();
        bufferbuilder.pos(d2, d5, d0).endVertex();
        bufferbuilder.pos(d2, d5, d0).endVertex();
        bufferbuilder.pos(d2, d4, d0).endVertex();
        bufferbuilder.pos(d1, d4, d0).endVertex();
        bufferbuilder.pos(d1, d5, d0).endVertex();
        bufferbuilder.pos(d1, d5, d0).endVertex();
        bufferbuilder.pos(d1, d4, d0).endVertex();
        bufferbuilder.pos(d0, d4, d1).endVertex();
        bufferbuilder.pos(d0, d5, d1).endVertex();
        bufferbuilder.pos(d0, d5, d1).endVertex();
        bufferbuilder.pos(d0, d4, d1).endVertex();
        bufferbuilder.pos(d0, d4, d2).endVertex();
        bufferbuilder.pos(d0, d5, d2).endVertex();
        bufferbuilder.pos(d0, d5, d2).endVertex();
        bufferbuilder.pos(d0, d4, d2).endVertex();
        bufferbuilder.pos(d1, d4, d3).endVertex();
        bufferbuilder.pos(d1, d5, d3).endVertex();
        bufferbuilder.pos(d1, d5, d3).endVertex();
        bufferbuilder.pos(d1, d4, d3).endVertex();
        bufferbuilder.pos(d2, d4, d3).endVertex();
        bufferbuilder.pos(d2, d5, d3).endVertex();
        bufferbuilder.pos(d3, d5, d3).endVertex();
        bufferbuilder.pos(d3, d5, d0).endVertex();
        bufferbuilder.pos(d0, d5, d0).endVertex();
        bufferbuilder.pos(d0, d5, d3).endVertex();
        l_3747_P.n_1700_B().J_1907_R();
        X_933_l.e_2887_G();
    }

    public static void preSkyList(g_221_o matrixStackIn) {
        Shaders.setUpPosition(matrixStackIn);
        GL11.glColor3f((float)fogColorR, (float)fogColorG, (float)fogColorB);
        Shaders.drawHorizon(matrixStackIn);
        GL11.glColor3f((float)skyColorR, (float)skyColorG, (float)skyColorB);
    }

    public static void endSky() {
        isRenderingSky = false;
        Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
        Shaders.popEntity();
        Shaders.setRenderStage(RenderStage.NONE);
    }

    public static void beginUpdateChunks() {
        Shaders.checkGLError("beginUpdateChunks1");
        Shaders.checkFramebufferStatus("beginUpdateChunks1");
        if (!isShadowPass) {
            Shaders.useProgram(ProgramTerrain);
        }
        Shaders.checkGLError("beginUpdateChunks2");
        Shaders.checkFramebufferStatus("beginUpdateChunks2");
    }

    public static void endUpdateChunks() {
        Shaders.checkGLError("endUpdateChunks1");
        Shaders.checkFramebufferStatus("endUpdateChunks1");
        if (!isShadowPass) {
            Shaders.useProgram(ProgramTerrain);
        }
        Shaders.checkGLError("endUpdateChunks2");
        Shaders.checkFramebufferStatus("endUpdateChunks2");
    }

    public static boolean shouldRenderClouds(V_4423_d gs) {
        if (!shaderPackLoaded) {
            return true;
        }
        Shaders.checkGLError("shouldRenderClouds");
        return isShadowPass ? configCloudShadow : gs.P_1922_E != K_2069_m.n_1700_B;
    }

    public static void beginClouds() {
        fogEnabled = true;
        Shaders.pushEntity(-3, 0);
        Shaders.useProgram(ProgramClouds);
        Shaders.setRenderStage(RenderStage.CLOUDS);
    }

    public static void endClouds() {
        Shaders.disableFog();
        Shaders.popEntity();
        Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
        Shaders.setRenderStage(RenderStage.NONE);
    }

    public static void beginEntities() {
        if (isRenderingWorld) {
            Shaders.useProgram(ProgramEntities);
            Shaders.setRenderStage(RenderStage.ENTITIES);
        }
    }

    public static void nextEntity(N_4263_v entity) {
        if (isRenderingWorld) {
            if (entity.j_306_t()) {
                Shaders.useProgram(ProgramEntitiesGlowing);
            } else {
                Shaders.useProgram(ProgramEntities);
            }
            Shaders.setEntityId(entity);
        }
    }

    public static void setEntityId(N_4263_v entity) {
        if (uniform_entityId.isDefined()) {
            int i = EntityUtils.getEntityIdByClass(entity);
            int j = EntityAliases.getEntityAliasId(i);
            uniform_entityId.setValue(j);
        }
    }

    public static void beginSpiderEyes() {
        if (isRenderingWorld && ProgramSpiderEyes.getId() != ProgramNone.getId()) {
            Shaders.useProgram(ProgramSpiderEyes);
            X_933_l.P_1922_E();
            X_933_l.n_1700_B(516, 0.0f);
            X_933_l.J_1907_R(770, 771);
        }
    }

    public static void endSpiderEyes() {
        if (isRenderingWorld && ProgramSpiderEyes.getId() != ProgramNone.getId()) {
            Shaders.useProgram(ProgramEntities);
            X_933_l.G_564_y();
        }
    }

    public static void endEntities() {
        if (isRenderingWorld) {
            Shaders.setEntityId(null);
            Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
        }
    }

    public static void beginEntitiesGlowing() {
        if (isRenderingWorld) {
            isEntitiesGlowing = true;
        }
    }

    public static void endEntitiesGlowing() {
        if (isRenderingWorld) {
            isEntitiesGlowing = false;
        }
    }

    public static void setEntityColor(float r, float g, float b, float a) {
        if (isRenderingWorld && !isShadowPass) {
            uniform_entityColor.setValue(r, g, b, a);
        }
    }

    public static void beginLivingDamage() {
        if (isRenderingWorld) {
            ShadersTex.bindTexture(defaultTexture);
            if (!isShadowPass) {
                GlState.setDrawBuffers(drawBuffersColorAtt[0]);
            }
        }
    }

    public static void endLivingDamage() {
        if (isRenderingWorld && !isShadowPass) {
            GlState.setDrawBuffers(ProgramEntities.getDrawBuffers());
        }
    }

    public static void beginBlockEntities() {
        if (isRenderingWorld) {
            Shaders.checkGLError("beginBlockEntities");
            Shaders.useProgram(ProgramBlock);
            Shaders.setRenderStage(RenderStage.BLOCK_ENTITIES);
        }
    }

    public static void nextBlockEntity(i_2154_H tileEntity) {
        if (isRenderingWorld) {
            Shaders.checkGLError("nextBlockEntity");
            Shaders.useProgram(ProgramBlock);
            Shaders.setBlockEntityId(tileEntity);
        }
    }

    public static void setBlockEntityId(i_2154_H tileEntity) {
        if (uniform_blockEntityId.isDefined()) {
            int i = Shaders.getBlockEntityId(tileEntity);
            uniform_blockEntityId.setValue(i);
        }
    }

    private static int getBlockEntityId(i_2154_H tileEntity) {
        if (tileEntity == null) {
            return -1;
        }
        K_4074_S blockstate = tileEntity.e_4240_b();
        return BlockAliases.getAliasBlockId(blockstate);
    }

    public static void endBlockEntities() {
        if (isRenderingWorld) {
            Shaders.checkGLError("endBlockEntities");
            Shaders.setBlockEntityId(null);
            Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
            ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
        }
    }

    public static void beginLitParticles() {
        Shaders.useProgram(ProgramTexturedLit);
    }

    public static void beginParticles() {
        Shaders.useProgram(ProgramTextured);
        Shaders.setRenderStage(RenderStage.PARTICLES);
    }

    public static void endParticles() {
        Shaders.useProgram(ProgramTexturedLit);
        Shaders.setRenderStage(RenderStage.NONE);
    }

    public static void readCenterDepth() {
        if (!isShadowPass && centerDepthSmoothEnabled) {
            ((Buffer)tempDirectFloatBuffer).clear();
            GL43.glReadPixels((int)(renderWidth / 2), (int)(renderHeight / 2), (int)1, (int)1, (int)6402, (int)5126, (FloatBuffer)tempDirectFloatBuffer);
            centerDepth = tempDirectFloatBuffer.get(0);
            float f = (float)diffSystemTime * 0.01f;
            float f1 = (float)Math.exp(Math.log(0.5) * (double)f / (double)centerDepthSmoothHalflife);
            centerDepthSmooth = centerDepthSmooth * f1 + centerDepth * (1.0f - f1);
        }
    }

    public static void beginWeather() {
        if (!isShadowPass) {
            X_933_l.P_4830_p();
            X_933_l.Q_4569_t();
            X_933_l.J_1907_R(770, 771);
            X_933_l.P_1922_E();
            Shaders.useProgram(ProgramWeather);
            Shaders.setRenderStage(RenderStage.RAIN_SNOW);
        }
    }

    public static void endWeather() {
        X_933_l.h_1847_R();
        Shaders.useProgram(ProgramTexturedLit);
        Shaders.setRenderStage(RenderStage.NONE);
    }

    public static void preRenderHand() {
        if (!isShadowPass && usedDepthBuffers >= 3) {
            X_933_l.t_1786_h(33996);
            GL43.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)0, (int)0, (int)renderWidth, (int)renderHeight);
            X_933_l.t_1786_h(33984);
        }
    }

    public static void preWater() {
        if (usedDepthBuffers >= 2) {
            X_933_l.t_1786_h(33995);
            Shaders.checkGLError("pre copy depth");
            GL43.glCopyTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)0, (int)0, (int)renderWidth, (int)renderHeight);
            Shaders.checkGLError("copy depth");
            X_933_l.t_1786_h(33984);
        }
        ShadersTex.bindNSTextures(defaultTexture.getMultiTexID());
    }

    public static void beginWater() {
        if (isRenderingWorld) {
            if (!isShadowPass) {
                Shaders.renderDeferred();
                Shaders.useProgram(ProgramWater);
                X_933_l.Q_4569_t();
                X_933_l.n_1700_B(true);
            } else {
                X_933_l.n_1700_B(true);
            }
        }
    }

    public static void endWater() {
        if (isRenderingWorld) {
            if (isShadowPass) {
                // empty if block
            }
            Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
        }
    }

    public static void applyHandDepth(g_221_o matrixStackIn) {
        if ((double)configHandDepthMul != 1.0) {
            matrixStackIn.n_1700_B(1.0f, 1.0f, configHandDepthMul);
        }
    }

    public static void beginHand(g_221_o matrixStackIn, boolean translucent) {
        GL43.glMatrixMode((int)5888);
        GL43.glPushMatrix();
        GL43.glMatrixMode((int)5889);
        GL43.glPushMatrix();
        GL43.glMatrixMode((int)5888);
        matrixStackIn.n_1700_B();
        if (translucent) {
            Shaders.useProgram(ProgramHandWater);
        } else {
            Shaders.useProgram(ProgramHand);
        }
        Shaders.checkGLError("beginHand");
        Shaders.checkFramebufferStatus("beginHand");
    }

    public static void endHand(g_221_o matrixStackIn) {
        Shaders.checkGLError("pre endHand");
        Shaders.checkFramebufferStatus("pre endHand");
        matrixStackIn.J_1907_R();
        GL43.glMatrixMode((int)5889);
        GL43.glPopMatrix();
        GL43.glMatrixMode((int)5888);
        GL43.glPopMatrix();
        X_933_l.J_1907_R(770, 771);
        Shaders.checkGLError("endHand");
    }

    public static void beginFPOverlay() {
        X_933_l.v_4262_N();
        X_933_l.h_1847_R();
    }

    public static void endFPOverlay() {
    }

    public static void glEnableWrapper(int cap) {
        GL43.glEnable((int)cap);
        if (cap == 3553) {
            Shaders.enableTexture2D();
        } else if (cap == 2912) {
            Shaders.enableFog();
        }
    }

    public static void glDisableWrapper(int cap) {
        GL43.glDisable((int)cap);
        if (cap == 3553) {
            Shaders.disableTexture2D();
        } else if (cap == 2912) {
            Shaders.disableFog();
        }
    }

    public static void sglEnableT2D(int cap) {
        GL43.glEnable((int)cap);
        Shaders.enableTexture2D();
    }

    public static void sglDisableT2D(int cap) {
        GL43.glDisable((int)cap);
        Shaders.disableTexture2D();
    }

    public static void sglEnableFog(int cap) {
        GL43.glEnable((int)cap);
        Shaders.enableFog();
    }

    public static void sglDisableFog(int cap) {
        GL43.glDisable((int)cap);
        Shaders.disableFog();
    }

    public static void enableTexture2D() {
        if (isRenderingSky) {
            Shaders.useProgram(ProgramSkyTextured);
        } else if (activeProgram == ProgramBasic) {
            Shaders.useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
        }
    }

    public static void disableTexture2D() {
        if (isRenderingSky) {
            Shaders.useProgram(ProgramSkyBasic);
        } else if (activeProgram == ProgramTextured || activeProgram == ProgramTexturedLit) {
            Shaders.useProgram(ProgramBasic);
        }
    }

    public static void pushProgram() {
        programStack.push(activeProgram);
    }

    public static void popProgram() {
        Program program = programStack.pop();
        Shaders.useProgram(program);
    }

    public static void beginLeash() {
        Shaders.pushProgram();
        Shaders.useProgram(ProgramBasic);
    }

    public static void endLeash() {
        Shaders.popProgram();
    }

    public static void enableFog() {
        fogEnabled = true;
        Shaders.setProgramUniform1i(uniform_fogMode, fogMode);
        Shaders.setProgramUniform1f(uniform_fogDensity, fogDensity);
    }

    public static void disableFog() {
        fogEnabled = false;
        Shaders.setProgramUniform1i(uniform_fogMode, 0);
    }

    public static void setFogMode(X_933_l.u_2550_I fogMode) {
        Shaders.setFogMode(fogMode.G_564_y);
    }

    public static void setFogDensity(float value) {
        fogDensity = value;
        if (fogEnabled) {
            Shaders.setProgramUniform1f(uniform_fogDensity, value);
        }
    }

    public static void sglFogi(int pname, int param) {
        GL11.glFogi((int)pname, (int)param);
        if (pname == 2917) {
            fogMode = param;
            if (fogEnabled) {
                Shaders.setProgramUniform1i(uniform_fogMode, fogMode);
            }
        }
    }

    public static void enableLightmap() {
        lightmapEnabled = true;
        if (activeProgram == ProgramTextured) {
            Shaders.useProgram(ProgramTexturedLit);
        }
    }

    public static void disableLightmap() {
        lightmapEnabled = false;
        if (activeProgram == ProgramTexturedLit) {
            Shaders.useProgram(ProgramTextured);
        }
    }

    public static int getEntityData() {
        return entityData[entityDataIndex * 2];
    }

    public static int getEntityData2() {
        return entityData[entityDataIndex * 2 + 1];
    }

    public static int setEntityData1(int data1) {
        Shaders.entityData[Shaders.entityDataIndex * 2] = entityData[entityDataIndex * 2] & 0xFFFF | data1 << 16;
        return data1;
    }

    public static int setEntityData2(int data2) {
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = entityData[entityDataIndex * 2 + 1] & 0xFFFF0000 | data2 & 0xFFFF;
        return data2;
    }

    public static void pushEntity(int data0, int data1) {
        Shaders.entityData[++Shaders.entityDataIndex * 2] = data0 & 0xFFFF | data1 << 16;
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = 0;
    }

    public static void pushEntity(int data0) {
        Shaders.entityData[++Shaders.entityDataIndex * 2] = data0 & 0xFFFF;
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = 0;
    }

    public static void pushEntity(T_2915_h block) {
        int i = block.n_1700_B(block.multiplayerClientSuggestionProvider()).ordinal();
        Shaders.entityData[++Shaders.entityDataIndex * 2] = V_3137_a.q_4610_l.n_1700_B(block) & 0xFFFF | i << 16;
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = 0;
    }

    public static void popEntity() {
        Shaders.entityData[Shaders.entityDataIndex * 2] = 0;
        Shaders.entityData[Shaders.entityDataIndex * 2 + 1] = 0;
        --entityDataIndex;
    }

    public static void mcProfilerEndSection() {
        mc.PlayerInfo().R_4764_Y();
    }

    public static String getShaderPackName() {
        if (shaderPack == null) {
            return null;
        }
        return shaderPack instanceof ShaderPackNone ? null : shaderPack.getName();
    }

    public static InputStream getShaderPackResourceStream(String path) {
        return shaderPack == null ? null : shaderPack.getResourceAsStream(path);
    }

    public static void nextAntialiasingLevel(boolean forward) {
        if (forward) {
            if ((configAntialiasingLevel += 2) > 4) {
                configAntialiasingLevel = 0;
            }
        } else if ((configAntialiasingLevel -= 2) < 0) {
            configAntialiasingLevel = 4;
        }
        configAntialiasingLevel = configAntialiasingLevel / 2 * 2;
        configAntialiasingLevel = Config.limit(configAntialiasingLevel, 0, 4);
    }

    public static void checkShadersModInstalled() {
        try {
            Class<?> clazz = Class.forName("shadersmod.transform.SMCClassTransformer");
        }
        catch (Throwable throwable) {
            return;
        }
        throw new RuntimeException("Shaders Mod detected. Please remove it, OptiFine has built-in support for shaders.");
    }

    public static void resourcesReloaded() {
        Shaders.loadShaderPackResources();
        Shaders.reloadCustomTexturesLocation(customTexturesGbuffers);
        Shaders.reloadCustomTexturesLocation(customTexturesComposite);
        Shaders.reloadCustomTexturesLocation(customTexturesDeferred);
        Shaders.reloadCustomTexturesLocation(customTexturesShadowcomp);
        Shaders.reloadCustomTexturesLocation(customTexturesPrepare);
        if (shaderPackLoaded) {
            BlockAliases.resourcesReloaded();
            ItemAliases.resourcesReloaded();
            EntityAliases.resourcesReloaded();
        }
    }

    private static void loadShaderPackResources() {
        shaderPackResources = new HashMap<String, String>();
        if (shaderPackLoaded) {
            ArrayList<CallSite> list = new ArrayList<CallSite>();
            String s = "/shaders/lang/";
            String s1 = "en_us";
            String s2 = ".lang";
            list.add((CallSite)((Object)(s + s1 + s2)));
            list.add((CallSite)((Object)(s + Shaders.getLocaleUppercase(s1) + s2)));
            if (!Config.getGameSettings().RealmsConfirmScreen.equals(s1)) {
                String s3 = Config.getGameSettings().RealmsConfirmScreen;
                list.add((CallSite)((Object)(s + (String)s3 + s2)));
                list.add((CallSite)((Object)(s + Shaders.getLocaleUppercase(s3) + s2)));
            }
            try {
                for (String string : list) {
                    InputStream inputstream = shaderPack.getResourceAsStream(string);
                    if (inputstream == null) continue;
                    PropertiesOrdered properties = new PropertiesOrdered();
                    Lang.loadLocaleData(inputstream, properties);
                    inputstream.close();
                    for (String string2 : ((Properties)properties).keySet()) {
                        String s6 = properties.getProperty(string2);
                        shaderPackResources.put(string2, s6);
                    }
                }
            }
            catch (IOException ioexception) {
                ioexception.printStackTrace();
            }
        }
    }

    private static String getLocaleUppercase(String name) {
        int i = name.indexOf(95);
        return i < 0 ? name : name.substring(0, i) + name.substring(i).toUpperCase(Locale.ROOT);
    }

    public static String translate(String key, String def) {
        String s = shaderPackResources.get(key);
        return s == null ? def : s;
    }

    public static boolean isProgramPath(String path) {
        Program program;
        if (path == null) {
            return false;
        }
        if (path.length() <= 0) {
            return false;
        }
        int i = path.lastIndexOf("/");
        if (i >= 0) {
            path = path.substring(i + 1);
        }
        return (program = Shaders.getProgram(path)) != null;
    }

    public static Program getProgram(String name) {
        return programs.getProgram(name);
    }

    public static void setItemToRenderMain(Z_1993_T itemToRenderMain) {
        itemToRenderMainTranslucent = Shaders.isTranslucentBlock(itemToRenderMain);
    }

    public static void setItemToRenderOff(Z_1993_T itemToRenderOff) {
        itemToRenderOffTranslucent = Shaders.isTranslucentBlock(itemToRenderOff);
    }

    public static boolean isItemToRenderMainTranslucent() {
        return itemToRenderMainTranslucent;
    }

    public static boolean isItemToRenderOffTranslucent() {
        return itemToRenderOffTranslucent;
    }

    public static boolean isBothHandsRendered() {
        return isHandRenderedMain && isHandRenderedOff;
    }

    private static boolean isTranslucentBlock(Z_1993_T stack) {
        if (stack == null) {
            return false;
        }
        q_1613_l item = stack.J_1907_R();
        if (item == null) {
            return false;
        }
        if (!(item instanceof v_1669_V)) {
            return false;
        }
        v_1669_V blockitem = (v_1669_V)item;
        T_2915_h block = blockitem.v_4262_N();
        if (block == null) {
            return false;
        }
        o_2576_A rendertype = d_1620_j.n_1700_B(block.multiplayerClientSuggestionProvider());
        return rendertype == RenderTypes.TRANSLUCENT;
    }

    public static boolean isSkipRenderHand(x_1688_C hand) {
        if (hand == x_1688_C.n_1700_B && skipRenderHandMain) {
            return true;
        }
        return hand == x_1688_C.J_1907_R && skipRenderHandOff;
    }

    public static boolean isRenderBothHands() {
        return !skipRenderHandMain && !skipRenderHandOff;
    }

    public static void setSkipRenderHands(boolean skipMain, boolean skipOff) {
        skipRenderHandMain = skipMain;
        skipRenderHandOff = skipOff;
    }

    public static void setHandsRendered(boolean handMain, boolean handOff) {
        isHandRenderedMain = handMain;
        isHandRenderedOff = handOff;
    }

    public static boolean isHandRenderedMain() {
        return isHandRenderedMain;
    }

    public static boolean isHandRenderedOff() {
        return isHandRenderedOff;
    }

    public static float getShadowRenderDistance() {
        return shadowDistanceRenderMul < 0.0f ? -1.0f : shadowMapHalfPlane * shadowDistanceRenderMul;
    }

    public static void beginRenderFirstPersonHand(boolean translucent) {
        isRenderingFirstPersonHand = true;
        if (translucent) {
            Shaders.setRenderStage(RenderStage.HAND_TRANSLUCENT);
        } else {
            Shaders.setRenderStage(RenderStage.HAND_SOLID);
        }
    }

    public static void endRenderFirstPersonHand() {
        isRenderingFirstPersonHand = false;
        Shaders.setRenderStage(RenderStage.NONE);
    }

    public static boolean isRenderingFirstPersonHand() {
        return isRenderingFirstPersonHand;
    }

    public static void beginBeacon() {
        if (isRenderingWorld) {
            Shaders.useProgram(ProgramBeaconBeam);
        }
    }

    public static void endBeacon() {
        if (isRenderingWorld) {
            Shaders.useProgram(ProgramBlock);
        }
    }

    public static b_4507_u getCurrentWorld() {
        return currentWorld;
    }

    public static c_1514_x getWorldCameraPosition() {
        return new c_1514_x(cameraPositionX + (double)cameraOffsetX, cameraPositionY, cameraPositionZ + (double)cameraOffsetZ);
    }

    public static boolean isCustomUniforms() {
        return customUniforms != null;
    }

    public static boolean canRenderQuads() {
        return hasGeometryShaders ? Shaders.capabilities.GL_NV_geometry_shader4 : true;
    }

    public static boolean isOverlayDisabled() {
        return shaderPackLoaded;
    }

    public static boolean isRemapLightmap() {
        return shaderPackLoaded;
    }

    public static boolean isEffectsModelView() {
        return shaderPackLoaded;
    }

    public static void flushRenderBuffers() {
        RenderUtils.flushRenderBuffers();
    }

    public static void setRenderStage(RenderStage stage) {
        if (shaderPackLoaded) {
            renderStage = stage;
            uniform_renderStage.setValue(stage.ordinal());
        }
    }

    static {
        isInitializedOnce = false;
        isShaderPackInitialized = false;
        hasGlGenMipmap = false;
        countResetDisplayLists = 0;
        renderDisplayWidth = 0;
        renderDisplayHeight = 0;
        renderWidth = 0;
        renderHeight = 0;
        isRenderingWorld = false;
        isRenderingSky = false;
        isCompositeRendered = false;
        isRenderingDfb = false;
        isShadowPass = false;
        isEntitiesGlowing = false;
        renderItemKeepDepthMask = false;
        itemToRenderMainTranslucent = false;
        itemToRenderOffTranslucent = false;
        sunPosition = new float[4];
        moonPosition = new float[4];
        shadowLightPosition = new float[4];
        upPosition = new float[4];
        shadowLightPositionVector = new float[4];
        upPosModelView = new float[]{0.0f, 100.0f, 0.0f, 0.0f};
        sunPosModelView = new float[]{0.0f, 100.0f, 0.0f, 0.0f};
        moonPosModelView = new float[]{0.0f, -100.0f, 0.0f, 0.0f};
        tempMat = new float[16];
        clearColor = new Z_2491_A();
        worldTime = 0L;
        lastWorldTime = 0L;
        diffWorldTime = 0L;
        celestialAngle = 0.0f;
        sunAngle = 0.0f;
        shadowAngle = 0.0f;
        moonPhase = 0;
        systemTime = 0L;
        lastSystemTime = 0L;
        diffSystemTime = 0L;
        frameCounter = 0;
        frameTime = 0.0f;
        frameTimeCounter = 0.0f;
        systemTimeInt32 = 0;
        pointOfView = t_1920_R.n_1700_B;
        pointOfViewChanged = false;
        rainStrength = 0.0f;
        wetness = 0.0f;
        wetnessHalfLife = 600.0f;
        drynessHalfLife = 200.0f;
        eyeBrightnessHalflife = 10.0f;
        usewetness = false;
        isEyeInWater = 0;
        eyeBrightness = 0;
        eyeBrightnessFadeX = 0.0f;
        eyeBrightnessFadeY = 0.0f;
        eyePosY = 0.0f;
        centerDepth = 0.0f;
        centerDepthSmooth = 0.0f;
        centerDepthSmoothHalflife = 1.0f;
        centerDepthSmoothEnabled = false;
        superSamplingLevel = 1;
        nightVision = 0.0f;
        blindness = 0.0f;
        lightmapEnabled = false;
        fogEnabled = true;
        renderStage = RenderStage.NONE;
        baseAttribId = 11;
        entityAttrib = baseAttribId + 0;
        midTexCoordAttrib = baseAttribId + 1;
        tangentAttrib = baseAttribId + 2;
        velocityAttrib = baseAttribId + 3;
        midBlockAttrib = baseAttribId + 4;
        useEntityAttrib = false;
        useMidTexCoordAttrib = false;
        useTangentAttrib = false;
        useVelocityAttrib = false;
        useMidBlockAttrib = false;
        progUseEntityAttrib = false;
        progUseMidTexCoordAttrib = false;
        progUseTangentAttrib = false;
        progUseVelocityAttrib = false;
        progUseMidBlockAttrib = false;
        progArbGeometryShader4 = false;
        progExtGeometryShader4 = false;
        progMaxVerticesOut = 3;
        hasGeometryShaders = false;
        atlasSizeX = 0;
        atlasSizeY = 0;
        shaderUniforms = new ShaderUniforms();
        uniform_entityColor = shaderUniforms.make4f("entityColor");
        uniform_entityId = shaderUniforms.make1i("entityId");
        uniform_blockEntityId = shaderUniforms.make1i("blockEntityId");
        uniform_texture = shaderUniforms.make1i("texture");
        uniform_lightmap = shaderUniforms.make1i("lightmap");
        uniform_normals = shaderUniforms.make1i("normals");
        uniform_specular = shaderUniforms.make1i("specular");
        uniform_shadow = shaderUniforms.make1i("shadow");
        uniform_watershadow = shaderUniforms.make1i("watershadow");
        uniform_shadowtex0 = shaderUniforms.make1i("shadowtex0");
        uniform_shadowtex1 = shaderUniforms.make1i("shadowtex1");
        uniform_depthtex0 = shaderUniforms.make1i("depthtex0");
        uniform_depthtex1 = shaderUniforms.make1i("depthtex1");
        uniform_shadowcolor = shaderUniforms.make1i("shadowcolor");
        uniform_shadowcolor0 = shaderUniforms.make1i("shadowcolor0");
        uniform_shadowcolor1 = shaderUniforms.make1i("shadowcolor1");
        uniform_noisetex = shaderUniforms.make1i("noisetex");
        uniform_gcolor = shaderUniforms.make1i("gcolor");
        uniform_gdepth = shaderUniforms.make1i("gdepth");
        uniform_gnormal = shaderUniforms.make1i("gnormal");
        uniform_composite = shaderUniforms.make1i("composite");
        uniform_gaux1 = shaderUniforms.make1i("gaux1");
        uniform_gaux2 = shaderUniforms.make1i("gaux2");
        uniform_gaux3 = shaderUniforms.make1i("gaux3");
        uniform_gaux4 = shaderUniforms.make1i("gaux4");
        uniform_colortex0 = shaderUniforms.make1i("colortex0");
        uniform_colortex1 = shaderUniforms.make1i("colortex1");
        uniform_colortex2 = shaderUniforms.make1i("colortex2");
        uniform_colortex3 = shaderUniforms.make1i("colortex3");
        uniform_colortex4 = shaderUniforms.make1i("colortex4");
        uniform_colortex5 = shaderUniforms.make1i("colortex5");
        uniform_colortex6 = shaderUniforms.make1i("colortex6");
        uniform_colortex7 = shaderUniforms.make1i("colortex7");
        uniform_gdepthtex = shaderUniforms.make1i("gdepthtex");
        uniform_depthtex2 = shaderUniforms.make1i("depthtex2");
        uniform_colortex8 = shaderUniforms.make1i("colortex8");
        uniform_colortex9 = shaderUniforms.make1i("colortex9");
        uniform_colortex10 = shaderUniforms.make1i("colortex10");
        uniform_colortex11 = shaderUniforms.make1i("colortex11");
        uniform_colortex12 = shaderUniforms.make1i("colortex12");
        uniform_colortex13 = shaderUniforms.make1i("colortex13");
        uniform_colortex14 = shaderUniforms.make1i("colortex14");
        uniform_colortex15 = shaderUniforms.make1i("colortex15");
        uniform_colorimg0 = shaderUniforms.make1i("colorimg0");
        uniform_colorimg1 = shaderUniforms.make1i("colorimg1");
        uniform_colorimg2 = shaderUniforms.make1i("colorimg2");
        uniform_colorimg3 = shaderUniforms.make1i("colorimg3");
        uniform_colorimg4 = shaderUniforms.make1i("colorimg4");
        uniform_colorimg5 = shaderUniforms.make1i("colorimg5");
        uniform_shadowcolorimg0 = shaderUniforms.make1i("shadowcolorimg0");
        uniform_shadowcolorimg1 = shaderUniforms.make1i("shadowcolorimg1");
        uniform_tex = shaderUniforms.make1i("tex");
        uniform_heldItemId = shaderUniforms.make1i("heldItemId");
        uniform_heldBlockLightValue = shaderUniforms.make1i("heldBlockLightValue");
        uniform_heldItemId2 = shaderUniforms.make1i("heldItemId2");
        uniform_heldBlockLightValue2 = shaderUniforms.make1i("heldBlockLightValue2");
        uniform_fogMode = shaderUniforms.make1i("fogMode");
        uniform_fogDensity = shaderUniforms.make1f("fogDensity");
        uniform_fogColor = shaderUniforms.make3f("fogColor");
        uniform_skyColor = shaderUniforms.make3f("skyColor");
        uniform_worldTime = shaderUniforms.make1i("worldTime");
        uniform_worldDay = shaderUniforms.make1i("worldDay");
        uniform_moonPhase = shaderUniforms.make1i("moonPhase");
        uniform_frameCounter = shaderUniforms.make1i("frameCounter");
        uniform_frameTime = shaderUniforms.make1f("frameTime");
        uniform_frameTimeCounter = shaderUniforms.make1f("frameTimeCounter");
        uniform_sunAngle = shaderUniforms.make1f("sunAngle");
        uniform_shadowAngle = shaderUniforms.make1f("shadowAngle");
        uniform_rainStrength = shaderUniforms.make1f("rainStrength");
        uniform_aspectRatio = shaderUniforms.make1f("aspectRatio");
        uniform_viewWidth = shaderUniforms.make1f("viewWidth");
        uniform_viewHeight = shaderUniforms.make1f("viewHeight");
        uniform_near = shaderUniforms.make1f("near");
        uniform_far = shaderUniforms.make1f("far");
        uniform_sunPosition = shaderUniforms.make3f("sunPosition");
        uniform_moonPosition = shaderUniforms.make3f("moonPosition");
        uniform_shadowLightPosition = shaderUniforms.make3f("shadowLightPosition");
        uniform_upPosition = shaderUniforms.make3f("upPosition");
        uniform_previousCameraPosition = shaderUniforms.make3f("previousCameraPosition");
        uniform_cameraPosition = shaderUniforms.make3f("cameraPosition");
        uniform_gbufferModelView = shaderUniforms.makeM4("gbufferModelView");
        uniform_gbufferModelViewInverse = shaderUniforms.makeM4("gbufferModelViewInverse");
        uniform_gbufferPreviousProjection = shaderUniforms.makeM4("gbufferPreviousProjection");
        uniform_gbufferProjection = shaderUniforms.makeM4("gbufferProjection");
        uniform_gbufferProjectionInverse = shaderUniforms.makeM4("gbufferProjectionInverse");
        uniform_gbufferPreviousModelView = shaderUniforms.makeM4("gbufferPreviousModelView");
        uniform_shadowProjection = shaderUniforms.makeM4("shadowProjection");
        uniform_shadowProjectionInverse = shaderUniforms.makeM4("shadowProjectionInverse");
        uniform_shadowModelView = shaderUniforms.makeM4("shadowModelView");
        uniform_shadowModelViewInverse = shaderUniforms.makeM4("shadowModelViewInverse");
        uniform_wetness = shaderUniforms.make1f("wetness");
        uniform_eyeAltitude = shaderUniforms.make1f("eyeAltitude");
        uniform_eyeBrightness = shaderUniforms.make2i("eyeBrightness");
        uniform_eyeBrightnessSmooth = shaderUniforms.make2i("eyeBrightnessSmooth");
        uniform_terrainTextureSize = shaderUniforms.make2i("terrainTextureSize");
        uniform_terrainIconSize = shaderUniforms.make1i("terrainIconSize");
        uniform_isEyeInWater = shaderUniforms.make1i("isEyeInWater");
        uniform_nightVision = shaderUniforms.make1f("nightVision");
        uniform_blindness = shaderUniforms.make1f("blindness");
        uniform_screenBrightness = shaderUniforms.make1f("screenBrightness");
        uniform_hideGUI = shaderUniforms.make1i("hideGUI");
        uniform_centerDepthSmooth = shaderUniforms.make1f("centerDepthSmooth");
        uniform_atlasSize = shaderUniforms.make2i("atlasSize");
        uniform_spriteBounds = shaderUniforms.make4f("spriteBounds");
        uniform_blendFunc = shaderUniforms.make4i("blendFunc");
        uniform_instanceId = shaderUniforms.make1i("instanceId");
        uniform_playerMood = shaderUniforms.make1f("playerMood");
        uniform_renderStage = shaderUniforms.make1i("renderStage");
        hasShadowMap = false;
        needResizeShadow = false;
        shadowMapWidth = 1024;
        shadowMapHeight = 1024;
        spShadowMapWidth = 1024;
        spShadowMapHeight = 1024;
        shadowMapFOV = 90.0f;
        shadowMapHalfPlane = 160.0f;
        shadowMapIsOrtho = true;
        shadowDistanceRenderMul = -1.0f;
        shouldSkipDefaultShadow = false;
        waterShadowEnabled = false;
        usedColorBuffers = 0;
        usedDepthBuffers = 0;
        usedShadowColorBuffers = 0;
        usedShadowDepthBuffers = 0;
        usedColorAttachs = 0;
        usedDrawBuffers = 0;
        bindImageTextures = false;
        gbuffersFormat = new int[16];
        gbuffersClear = new boolean[16];
        gbuffersClearColor = new Z_2491_A[16];
        CLEAR_COLOR_0 = new Z_2491_A(0.0f, 0.0f, 0.0f, 0.0f);
        CLEAR_COLOR_1 = new Z_2491_A(1.0f, 1.0f, 1.0f, 1.0f);
        shadowBuffersFormat = new int[2];
        shadowBuffersClear = new boolean[2];
        shadowBuffersClearColor = new Z_2491_A[2];
        programs = new Programs();
        ProgramNone = programs.getProgramNone();
        ProgramShadow = programs.makeShadow("shadow", ProgramNone);
        ProgramShadowSolid = programs.makeShadow("shadow_solid", ProgramShadow);
        ProgramShadowCutout = programs.makeShadow("shadow_cutout", ProgramShadow);
        ProgramsShadowcomp = programs.makeShadowcomps("shadowcomp", 16);
        ProgramsPrepare = programs.makePrepares("prepare", 16);
        ProgramBasic = programs.makeGbuffers("gbuffers_basic", ProgramNone);
        ProgramTextured = programs.makeGbuffers("gbuffers_textured", ProgramBasic);
        ProgramTexturedLit = programs.makeGbuffers("gbuffers_textured_lit", ProgramTextured);
        ProgramSkyBasic = programs.makeGbuffers("gbuffers_skybasic", ProgramBasic);
        ProgramSkyTextured = programs.makeGbuffers("gbuffers_skytextured", ProgramTextured);
        ProgramClouds = programs.makeGbuffers("gbuffers_clouds", ProgramTextured);
        ProgramTerrain = programs.makeGbuffers("gbuffers_terrain", ProgramTexturedLit);
        ProgramTerrainSolid = programs.makeGbuffers("gbuffers_terrain_solid", ProgramTerrain);
        ProgramTerrainCutoutMip = programs.makeGbuffers("gbuffers_terrain_cutout_mip", ProgramTerrain);
        ProgramTerrainCutout = programs.makeGbuffers("gbuffers_terrain_cutout", ProgramTerrain);
        ProgramDamagedBlock = programs.makeGbuffers("gbuffers_damagedblock", ProgramTerrain);
        ProgramBlock = programs.makeGbuffers("gbuffers_block", ProgramTerrain);
        ProgramBeaconBeam = programs.makeGbuffers("gbuffers_beaconbeam", ProgramTextured);
        ProgramItem = programs.makeGbuffers("gbuffers_item", ProgramTexturedLit);
        ProgramEntities = programs.makeGbuffers("gbuffers_entities", ProgramTexturedLit);
        ProgramEntitiesGlowing = programs.makeGbuffers("gbuffers_entities_glowing", ProgramEntities);
        ProgramArmorGlint = programs.makeGbuffers("gbuffers_armor_glint", ProgramTextured);
        ProgramSpiderEyes = programs.makeGbuffers("gbuffers_spidereyes", ProgramTextured);
        ProgramHand = programs.makeGbuffers("gbuffers_hand", ProgramTexturedLit);
        ProgramWeather = programs.makeGbuffers("gbuffers_weather", ProgramTexturedLit);
        ProgramDeferredPre = programs.makeVirtual("deferred_pre");
        ProgramsDeferred = programs.makeDeferreds("deferred", 16);
        ProgramDeferred = ProgramsDeferred[0];
        ProgramWater = programs.makeGbuffers("gbuffers_water", ProgramTerrain);
        ProgramHandWater = programs.makeGbuffers("gbuffers_hand_water", ProgramHand);
        ProgramCompositePre = programs.makeVirtual("composite_pre");
        ProgramsComposite = programs.makeComposites("composite", 16);
        ProgramComposite = ProgramsComposite[0];
        ProgramFinal = programs.makeComposite("final");
        ProgramCount = programs.getCount();
        ProgramsAll = programs.getPrograms();
        activeProgram = ProgramNone;
        activeProgramID = 0;
        programStack = new ProgramStack();
        hasDeferredPrograms = false;
        hasShadowcompPrograms = false;
        hasPreparePrograms = false;
        loadedShaders = null;
        shadersConfig = null;
        defaultTexture = null;
        shadowHardwareFilteringEnabled = new boolean[2];
        shadowMipmapEnabled = new boolean[2];
        shadowFilterNearest = new boolean[2];
        shadowColorMipmapEnabled = new boolean[2];
        shadowColorFilterNearest = new boolean[2];
        configTweakBlockDamage = false;
        configCloudShadow = false;
        configHandDepthMul = 0.125f;
        configRenderResMul = 1.0f;
        configShadowResMul = 1.0f;
        configTexMinFilB = 0;
        configTexMinFilN = 0;
        configTexMinFilS = 0;
        configTexMagFilB = 0;
        configTexMagFilN = 0;
        configTexMagFilS = 0;
        configShadowClipFrustrum = true;
        configNormalMap = true;
        configSpecularMap = true;
        configOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
        configOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
        configAntialiasingLevel = 0;
        texMinFilDesc = new String[]{"Nearest", "Nearest-Nearest", "Nearest-Linear"};
        texMagFilDesc = new String[]{"Nearest", "Linear"};
        texMinFilValue = new int[]{9728, 9984, 9986};
        texMagFilValue = new int[]{9728, 9729};
        shaderPack = null;
        shaderPackLoaded = false;
        shaderPacksDir = new File(MinecraftClient.A_4115_X().M_182_A, SHADER_PACKS_DIR_NAME);
        configFile = new File(MinecraftClient.A_4115_X().M_182_A, OPTIONS_FILE_NAME);
        shaderPackOptions = null;
        shaderPackOptionSliders = null;
        shaderPackProfiles = null;
        shaderPackGuiScreens = null;
        shaderPackProgramConditions = new HashMap<String, IExpressionBool>();
        shaderPackClouds = new PropertyDefaultFastFancyOff("clouds", "Clouds", 0);
        shaderPackOldLighting = new PropertyDefaultTrueFalse("oldLighting", "Classic Lighting", 0);
        shaderPackOldHandLight = new PropertyDefaultTrueFalse("oldHandLight", "Old Hand Light", 0);
        shaderPackDynamicHandLight = new PropertyDefaultTrueFalse("dynamicHandLight", "Dynamic Hand Light", 0);
        shaderPackShadowTerrain = new PropertyDefaultTrueFalse("shadowTerrain", "Shadow Terrain", 0);
        shaderPackShadowTranslucent = new PropertyDefaultTrueFalse("shadowTranslucent", "Shadow Translucent", 0);
        shaderPackShadowEntities = new PropertyDefaultTrueFalse("shadowEntities", "Shadow Entities", 0);
        shaderPackShadowBlockEntities = new PropertyDefaultTrueFalse("shadowBlockEntities", "Shadow Block Entities", 0);
        shaderPackUnderwaterOverlay = new PropertyDefaultTrueFalse("underwaterOverlay", "Underwater Overlay", 0);
        shaderPackSun = new PropertyDefaultTrueFalse("sun", "Sun", 0);
        shaderPackMoon = new PropertyDefaultTrueFalse("moon", "Moon", 0);
        shaderPackVignette = new PropertyDefaultTrueFalse("vignette", "Vignette", 0);
        shaderPackBackFaceSolid = new PropertyDefaultTrueFalse("backFace.solid", "Back-face Solid", 0);
        shaderPackBackFaceCutout = new PropertyDefaultTrueFalse("backFace.cutout", "Back-face Cutout", 0);
        shaderPackBackFaceCutoutMipped = new PropertyDefaultTrueFalse("backFace.cutoutMipped", "Back-face Cutout Mipped", 0);
        shaderPackBackFaceTranslucent = new PropertyDefaultTrueFalse("backFace.translucent", "Back-face Translucent", 0);
        shaderPackRainDepth = new PropertyDefaultTrueFalse("rain.depth", "Rain Depth", 0);
        shaderPackBeaconBeamDepth = new PropertyDefaultTrueFalse("beacon.beam.depth", "Rain Depth", 0);
        shaderPackSeparateAo = new PropertyDefaultTrueFalse("separateAo", "Separate AO", 0);
        shaderPackFrustumCulling = new PropertyDefaultTrueFalse("frustum.culling", "Frustum Culling", 0);
        shaderPackResources = new HashMap<String, String>();
        currentWorld = null;
        shaderPackDimensions = new ArrayList<Integer>();
        customTexturesGbuffers = null;
        customTexturesComposite = null;
        customTexturesDeferred = null;
        customTexturesShadowcomp = null;
        customTexturesPrepare = null;
        noiseTexturePath = null;
        colorBufferSizes = new DynamicDimension[16];
        customUniforms = null;
        saveFinalShaders = System.getProperty("shaders.debug.save", "false").equals("true");
        blockLightLevel05 = 0.5f;
        blockLightLevel06 = 0.6f;
        blockLightLevel08 = 0.8f;
        aoLevel = -1.0f;
        sunPathRotation = 0.0f;
        shadowAngleInterval = 0.0f;
        fogMode = 0;
        fogDensity = 0.0f;
        shadowIntervalSize = 2.0f;
        terrainIconSize = 16;
        terrainTextureSize = new int[2];
        noiseTextureEnabled = false;
        noiseTextureResolution = 256;
        colorTextureImageUnit = new int[]{0, 1, 2, 3, 7, 8, 9, 10, 16, 17, 18, 19, 20, 21, 22, 23};
        depthTextureImageUnit = new int[]{6, 11, 12};
        shadowColorTextureImageUnit = new int[]{13, 14};
        shadowDepthTextureImageUnit = new int[]{4, 5};
        colorImageUnit = new int[]{0, 1, 2, 3, 4, 5};
        shadowColorImageUnit = new int[]{6, 7};
        bigBufferSize = (295 + 8 * ProgramCount) * 4;
        bigBuffer = (ByteBuffer)((Buffer)BufferUtils.createByteBuffer((int)bigBufferSize)).limit(0);
        faProjection = new float[16];
        faProjectionInverse = new float[16];
        faModelView = new float[16];
        faModelViewInverse = new float[16];
        faShadowProjection = new float[16];
        faShadowProjectionInverse = new float[16];
        faShadowModelView = new float[16];
        faShadowModelViewInverse = new float[16];
        projection = Shaders.nextFloatBuffer(16);
        projectionInverse = Shaders.nextFloatBuffer(16);
        modelView = Shaders.nextFloatBuffer(16);
        modelViewInverse = Shaders.nextFloatBuffer(16);
        shadowProjection = Shaders.nextFloatBuffer(16);
        shadowProjectionInverse = Shaders.nextFloatBuffer(16);
        shadowModelView = Shaders.nextFloatBuffer(16);
        shadowModelViewInverse = Shaders.nextFloatBuffer(16);
        previousProjection = Shaders.nextFloatBuffer(16);
        previousModelView = Shaders.nextFloatBuffer(16);
        tempMatrixDirectBuffer = Shaders.nextFloatBuffer(16);
        tempDirectFloatBuffer = Shaders.nextFloatBuffer(16);
        dfbDrawBuffers = new DrawBuffers("dfbDrawBuffers", 16, 8);
        sfbDrawBuffers = new DrawBuffers("sfbDrawBuffers", 16, 8);
        drawBuffersNone = new DrawBuffers("drawBuffersNone", 16, 8).limit(0);
        drawBuffersColorAtt = Shaders.makeDrawBuffersColorSingle(16);
        formatNames = new String[]{"R8", "RG8", "RGB8", "RGBA8", "R8_SNORM", "RG8_SNORM", "RGB8_SNORM", "RGBA8_SNORM", "R8I", "RG8I", "RGB8I", "RGBA8I", "R8UI", "RG8UI", "RGB8UI", "RGBA8UI", "R16", "RG16", "RGB16", "RGBA16", "R16_SNORM", "RG16_SNORM", "RGB16_SNORM", "RGBA16_SNORM", "R16F", "RG16F", "RGB16F", "RGBA16F", "R16I", "RG16I", "RGB16I", "RGBA16I", "R16UI", "RG16UI", "RGB16UI", "RGBA16UI", "R32F", "RG32F", "RGB32F", "RGBA32F", "R32I", "RG32I", "RGB32I", "RGBA32I", "R32UI", "RG32UI", "RGB32UI", "RGBA32UI", "R3_G3_B2", "RGB5_A1", "RGB10_A2", "R11F_G11F_B10F", "RGB9_E5"};
        formatIds = new int[]{33321, 33323, 32849, 32856, 36756, 36757, 36758, 36759, 33329, 33335, 36239, 36238, 33330, 33336, 36221, 36220, 33322, 33324, 32852, 32859, 36760, 36761, 36762, 36763, 33325, 33327, 34843, 34842, 33331, 33337, 36233, 36232, 33332, 33338, 36215, 36214, 33326, 33328, 34837, 34836, 33333, 33339, 36227, 36226, 33334, 33340, 36209, 36208, 10768, 32855, 32857, 35898, 35901};
        patternLoadEntityDataMap = Pattern.compile("\\s*([\\w:]+)\\s*=\\s*([-]?\\d+)\\s*");
        entityData = new int[32];
        entityDataIndex = 0;
    }
}



