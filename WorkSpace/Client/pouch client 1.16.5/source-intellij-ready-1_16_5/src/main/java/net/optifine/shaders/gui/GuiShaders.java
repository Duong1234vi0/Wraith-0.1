/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.gui;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import lightning.product.K_1289_S;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.k_2603_m;
import net.optifine.Config;
import net.optifine.Lang;
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderEnumShaderOptions;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.ShadersTex;
import net.optifine.shaders.config.EnumShaderOption;
import net.optifine.shaders.gui.GuiButtonDownloadShaders;
import net.optifine.shaders.gui.GuiButtonEnumShaderOption;
import net.optifine.shaders.gui.GuiShaderOptions;
import net.optifine.shaders.gui.GuiSlotShaders;

public class GuiShaders
extends GuiScreenOF {
    protected k_2603_m parentGui;
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderEnumShaderOptions());
    private int updateTimer = -1;
    private GuiSlotShaders shaderList;
    private boolean saved = false;
    private static float[] QUALITY_MULTIPLIERS = new float[]{0.5f, 0.6f, 0.6666667f, 0.75f, 0.8333333f, 0.9f, 1.0f, 1.1666666f, 1.3333334f, 1.5f, 1.6666666f, 1.8f, 2.0f};
    private static String[] QUALITY_MULTIPLIER_NAMES = new String[]{"0.5x", "0.6x", "0.66x", "0.75x", "0.83x", "0.9x", "1x", "1.16x", "1.33x", "1.5x", "1.66x", "1.8x", "2x"};
    private static float QUALITY_MULTIPLIER_DEFAULT = 1.0f;
    private static float[] HAND_DEPTH_VALUES = new float[]{0.0625f, 0.125f, 0.25f};
    private static String[] HAND_DEPTH_NAMES = new String[]{"0.5x", "1x", "2x"};
    private static float HAND_DEPTH_DEFAULT = 0.125f;
    public static final int EnumOS_UNKNOWN = 0;
    public static final int EnumOS_WINDOWS = 1;
    public static final int EnumOS_OSX = 2;
    public static final int EnumOS_SOLARIS = 3;
    public static final int EnumOS_LINUX = 4;

    public GuiShaders(k_2603_m par1GuiScreen, V_4423_d par2GameSettings) {
        super(new U_2871_b(K_1289_S.n_1700_B("of.options.shadersTitle", new Object[0])));
        this.parentGui = par1GuiScreen;
    }

    @Override
    public void init() {
        if (Shaders.shadersConfig == null) {
            Shaders.loadConfig();
        }
        int i = 120;
        int j = 20;
        int k = this.width - i - 10;
        int l = 30;
        int i1 = 20;
        int j1 = this.width - i - 20;
        this.shaderList = new GuiSlotShaders(this, j1, this.height, l, this.height - 50, 16);
        this.children.add(this.shaderList);
        this.addButton(new GuiButtonEnumShaderOption(EnumShaderOption.ANTIALIASING, k, 0 * i1 + l, i, j));
        this.addButton(new GuiButtonEnumShaderOption(EnumShaderOption.NORMAL_MAP, k, 1 * i1 + l, i, j));
        this.addButton(new GuiButtonEnumShaderOption(EnumShaderOption.SPECULAR_MAP, k, 2 * i1 + l, i, j));
        this.addButton(new GuiButtonEnumShaderOption(EnumShaderOption.RENDER_RES_MUL, k, 3 * i1 + l, i, j));
        this.addButton(new GuiButtonEnumShaderOption(EnumShaderOption.SHADOW_RES_MUL, k, 4 * i1 + l, i, j));
        this.addButton(new GuiButtonEnumShaderOption(EnumShaderOption.HAND_DEPTH_MUL, k, 5 * i1 + l, i, j));
        this.addButton(new GuiButtonEnumShaderOption(EnumShaderOption.OLD_HAND_LIGHT, k, 6 * i1 + l, i, j));
        this.addButton(new GuiButtonEnumShaderOption(EnumShaderOption.OLD_LIGHTING, k, 7 * i1 + l, i, j));
        int k1 = Math.min(150, j1 / 2 - 10);
        int l1 = j1 / 4 - k1 / 2;
        int i2 = this.height - 25;
        this.addButton(new GuiButtonOF(201, l1, i2, k1 - 22 + 1, j, Lang.get("of.options.shaders.shadersFolder")));
        this.addButton(new GuiButtonDownloadShaders(210, l1 + k1 - 22 - 1, i2));
        this.addButton(new GuiButtonOF(202, j1 / 4 * 3 - k1 / 2, this.height - 25, k1, j, K_1289_S.n_1700_B("gui.done", new Object[0])));
        this.addButton(new GuiButtonOF(203, k, this.height - 25, i, j, Lang.get("of.options.shaders.shaderOptions")));
        this.setListener(this.shaderList);
        this.updateButtons();
    }

    public void updateButtons() {
        boolean flag = Config.isShaders();
        for (V_2511_L widget : this.buttonList) {
            if (!(widget instanceof GuiButtonOF)) continue;
            GuiButtonOF guibuttonof = (GuiButtonOF)widget;
            if (guibuttonof.id == 201 || guibuttonof.id == 202 || guibuttonof.id == 210 || guibuttonof.id == EnumShaderOption.ANTIALIASING.ordinal()) continue;
            guibuttonof.active = flag;
        }
    }

    @Override
    protected void actionPerformed(V_2511_L button) {
        this.actionPerformed(button, false);
    }

    @Override
    protected void actionPerformedRightClick(V_2511_L button) {
        this.actionPerformed(button, true);
    }

    private void actionPerformed(V_2511_L guiElement, boolean rightClick) {
        if (guiElement.active) {
            if (guiElement instanceof GuiButtonEnumShaderOption) {
                GuiButtonEnumShaderOption guibuttonenumshaderoption = (GuiButtonEnumShaderOption)guiElement;
                switch (guibuttonenumshaderoption.getEnumShaderOption()) {
                    case ANTIALIASING: {
                        Shaders.nextAntialiasingLevel(!rightClick);
                        if (GuiShaders.hasShiftDown()) {
                            Shaders.configAntialiasingLevel = 0;
                        }
                        Shaders.uninit();
                        break;
                    }
                    case NORMAL_MAP: {
                        boolean bl = Shaders.configNormalMap = !Shaders.configNormalMap;
                        if (GuiShaders.hasShiftDown()) {
                            Shaders.configNormalMap = true;
                        }
                        Shaders.uninit();
                        this.minecraft.Y_1740_V();
                        break;
                    }
                    case SPECULAR_MAP: {
                        boolean bl = Shaders.configSpecularMap = !Shaders.configSpecularMap;
                        if (GuiShaders.hasShiftDown()) {
                            Shaders.configSpecularMap = true;
                        }
                        Shaders.uninit();
                        this.minecraft.Y_1740_V();
                        break;
                    }
                    case RENDER_RES_MUL: {
                        Shaders.configRenderResMul = this.getNextValue(Shaders.configRenderResMul, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_DEFAULT, !rightClick, GuiShaders.hasShiftDown());
                        Shaders.uninit();
                        Shaders.scheduleResize();
                        break;
                    }
                    case SHADOW_RES_MUL: {
                        Shaders.configShadowResMul = this.getNextValue(Shaders.configShadowResMul, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_DEFAULT, !rightClick, GuiShaders.hasShiftDown());
                        Shaders.uninit();
                        Shaders.scheduleResizeShadow();
                        break;
                    }
                    case HAND_DEPTH_MUL: {
                        Shaders.configHandDepthMul = this.getNextValue(Shaders.configHandDepthMul, HAND_DEPTH_VALUES, HAND_DEPTH_DEFAULT, !rightClick, GuiShaders.hasShiftDown());
                        Shaders.uninit();
                        break;
                    }
                    case OLD_HAND_LIGHT: {
                        Shaders.configOldHandLight.nextValue(!rightClick);
                        if (GuiShaders.hasShiftDown()) {
                            Shaders.configOldHandLight.resetValue();
                        }
                        Shaders.uninit();
                        break;
                    }
                    case OLD_LIGHTING: {
                        Shaders.configOldLighting.nextValue(!rightClick);
                        if (GuiShaders.hasShiftDown()) {
                            Shaders.configOldLighting.resetValue();
                        }
                        Shaders.updateBlockLightLevel();
                        Shaders.uninit();
                        this.minecraft.Y_1740_V();
                        break;
                    }
                    case TWEAK_BLOCK_DAMAGE: {
                        Shaders.configTweakBlockDamage = !Shaders.configTweakBlockDamage;
                        break;
                    }
                    case CLOUD_SHADOW: {
                        Shaders.configCloudShadow = !Shaders.configCloudShadow;
                        break;
                    }
                    case TEX_MIN_FIL_B: {
                        Shaders.configTexMinFilN = Shaders.configTexMinFilS = (Shaders.configTexMinFilB = (Shaders.configTexMinFilB + 1) % 3);
                        guibuttonenumshaderoption.setMessage("Tex Min: " + Shaders.texMinFilDesc[Shaders.configTexMinFilB]);
                        ShadersTex.updateTextureMinMagFilter();
                        break;
                    }
                    case TEX_MAG_FIL_N: {
                        Shaders.configTexMagFilN = (Shaders.configTexMagFilN + 1) % 2;
                        guibuttonenumshaderoption.setMessage("Tex_n Mag: " + Shaders.texMagFilDesc[Shaders.configTexMagFilN]);
                        ShadersTex.updateTextureMinMagFilter();
                        break;
                    }
                    case TEX_MAG_FIL_S: {
                        Shaders.configTexMagFilS = (Shaders.configTexMagFilS + 1) % 2;
                        guibuttonenumshaderoption.setMessage("Tex_s Mag: " + Shaders.texMagFilDesc[Shaders.configTexMagFilS]);
                        ShadersTex.updateTextureMinMagFilter();
                        break;
                    }
                    case SHADOW_CLIP_FRUSTRUM: {
                        Shaders.configShadowClipFrustrum = !Shaders.configShadowClipFrustrum;
                        guibuttonenumshaderoption.setMessage("ShadowClipFrustrum: " + GuiShaders.toStringOnOff(Shaders.configShadowClipFrustrum));
                        ShadersTex.updateTextureMinMagFilter();
                    }
                }
                guibuttonenumshaderoption.updateButtonText();
            } else if (!rightClick && guiElement instanceof GuiButtonOF) {
                GuiButtonOF guibuttonof = (GuiButtonOF)guiElement;
                switch (guibuttonof.id) {
                    case 201: {
                        switch (GuiShaders.getOSType()) {
                            case 1: {
                                String s = String.format("cmd.exe /C start \"Open file\" \"%s\"", Shaders.shaderPacksDir.getAbsolutePath());
                                try {
                                    Runtime.getRuntime().exec(s);
                                    return;
                                }
                                catch (IOException ioexception) {
                                    ioexception.printStackTrace();
                                    break;
                                }
                            }
                            case 2: {
                                try {
                                    Runtime.getRuntime().exec(new String[]{"/usr/bin/open", Shaders.shaderPacksDir.getAbsolutePath()});
                                    return;
                                }
                                catch (IOException ioexception1) {
                                    ioexception1.printStackTrace();
                                }
                            }
                        }
                        boolean flag = false;
                        try {
                            URI uri1 = new File(this.minecraft.M_182_A, "shaderpacks").toURI();
                            j_3341_s.t_148_a().n_1700_B(uri1);
                        }
                        catch (Throwable throwable1) {
                            throwable1.printStackTrace();
                            flag = true;
                        }
                        if (!flag) break;
                        Config.dbg("Opening via system class!");
                        j_3341_s.t_148_a().n_1700_B("file://" + Shaders.shaderPacksDir.getAbsolutePath());
                        break;
                    }
                    case 202: {
                        Shaders.storeConfig();
                        this.saved = true;
                        this.minecraft.n_1700_B(this.parentGui);
                        break;
                    }
                    case 203: {
                        GuiShaderOptions guishaderoptions = new GuiShaderOptions(this, Config.getGameSettings());
                        Config.getMinecraft().n_1700_B(guishaderoptions);
                    }
                    default: {
                        break;
                    }
                    case 210: {
                        try {
                            URI uri = new URI("http://optifine.net/shaderPacks");
                            j_3341_s.t_148_a().n_1700_B(uri);
                            break;
                        }
                        catch (Throwable throwable1) {
                            throwable1.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    @Override
    public void onClose() {
        if (!this.saved) {
            Shaders.storeConfig();
            this.saved = true;
        }
        super.onClose();
    }

    @Override
    public void render(g_221_o matrixStackIn, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStackIn);
        this.shaderList.render(matrixStackIn, mouseX, mouseY, partialTicks);
        if (this.updateTimer <= 0) {
            this.shaderList.updateList();
            this.updateTimer += 20;
        }
        GuiShaders.drawCenteredString(matrixStackIn, this.fontRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
        String s = "OpenGL: " + Shaders.glVersionString + ", " + Shaders.glVendorString + ", " + Shaders.glRendererString;
        int i = this.fontRenderer.J_1907_R(s);
        if (i < this.width - 5) {
            GuiShaders.drawCenteredString(matrixStackIn, this.fontRenderer, s, this.width / 2, this.height - 40, 0x808080);
        } else {
            GuiShaders.drawString(matrixStackIn, this.fontRenderer, s, 5, this.height - 40, 0x808080);
        }
        super.render(matrixStackIn, mouseX, mouseY, partialTicks);
        this.tooltipManager.drawTooltips(matrixStackIn, mouseX, mouseY, this.buttonList);
    }

    @Override
    public void tick() {
        super.tick();
        --this.updateTimer;
    }

    public MinecraftClient getMc() {
        return this.minecraft;
    }

    public void drawCenteredString(g_221_o matrixStackIn, String text, int x, int y, int color) {
        GuiShaders.drawCenteredString(matrixStackIn, this.fontRenderer, text, x, y, color);
    }

    public static String toStringOnOff(boolean value) {
        String s = Lang.getOn();
        String s1 = Lang.getOff();
        return value ? s : s1;
    }

    public static String toStringAa(int value) {
        if (value == 2) {
            return "FXAA 2x";
        }
        return value == 4 ? "FXAA 4x" : Lang.getOff();
    }

    public static String toStringValue(float val, float[] values, String[] names) {
        int i = GuiShaders.getValueIndex(val, values);
        return names[i];
    }

    private float getNextValue(float val, float[] values, float valDef, boolean forward, boolean reset) {
        if (reset) {
            return valDef;
        }
        int i = GuiShaders.getValueIndex(val, values);
        if (forward) {
            if (++i >= values.length) {
                i = 0;
            }
        } else if (--i < 0) {
            i = values.length - 1;
        }
        return values[i];
    }

    public static int getValueIndex(float val, float[] values) {
        for (int i = 0; i < values.length; ++i) {
            float f = values[i];
            if (!(f >= val)) continue;
            return i;
        }
        return values.length - 1;
    }

    public static String toStringQuality(float val) {
        return GuiShaders.toStringValue(val, QUALITY_MULTIPLIERS, QUALITY_MULTIPLIER_NAMES);
    }

    public static String toStringHandDepth(float val) {
        return GuiShaders.toStringValue(val, HAND_DEPTH_VALUES, HAND_DEPTH_NAMES);
    }

    public static int getOSType() {
        String s = System.getProperty("os.name").toLowerCase();
        if (s.contains("win")) {
            return 1;
        }
        if (s.contains("mac")) {
            return 2;
        }
        if (s.contains("solaris")) {
            return 3;
        }
        if (s.contains("sunos")) {
            return 3;
        }
        if (s.contains("linux")) {
            return 4;
        }
        return s.contains("unix") ? 4 : 0;
    }
}


