/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.shaders.gui;

import lightning.product.K_1289_S;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.Y_4083_F;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.u_530_F;
import net.optifine.Config;
import net.optifine.Lang;
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderShaderOptions;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionScreen;
import net.optifine.shaders.gui.GuiButtonShaderOption;
import net.optifine.shaders.gui.GuiSliderShaderOption;

public class GuiShaderOptions
extends GuiScreenOF {
    private k_2603_m prevScreen;
    private V_4423_d settings;
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderShaderOptions());
    private String screenName = null;
    private String screenText = null;
    private boolean changed = false;
    public static final String OPTION_PROFILE = "<profile>";
    public static final String OPTION_EMPTY = "<empty>";
    public static final String OPTION_REST = "*";

    public GuiShaderOptions(k_2603_m guiscreen, V_4423_d gamesettings) {
        super(new U_2871_b(K_1289_S.n_1700_B("of.options.shaderOptionsTitle", new Object[0])));
        this.prevScreen = guiscreen;
        this.settings = gamesettings;
    }

    public GuiShaderOptions(k_2603_m guiscreen, V_4423_d gamesettings, String screenName) {
        this(guiscreen, gamesettings);
        this.screenName = screenName;
        if (screenName != null) {
            this.screenText = Shaders.translate("screen." + screenName, screenName);
        }
    }

    @Override
    public void init() {
        int i = 100;
        int j = 0;
        int k = 30;
        int l = 20;
        int i1 = 120;
        int j1 = 20;
        int k1 = Shaders.getShaderPackColumns(this.screenName, 2);
        ShaderOption[] ashaderoption = Shaders.getShaderPackOptions(this.screenName);
        if (ashaderoption != null) {
            int l1 = u_530_F.P_1922_E((double)ashaderoption.length / 9.0);
            if (k1 < l1) {
                k1 = l1;
            }
            for (int i2 = 0; i2 < ashaderoption.length; ++i2) {
                ShaderOption shaderoption = ashaderoption[i2];
                if (shaderoption == null || !shaderoption.isVisible()) continue;
                int j2 = i2 % k1;
                int k2 = i2 / k1;
                int l2 = Math.min(this.width / k1, 200);
                j = (this.width - l2 * k1) / 2;
                int i3 = j2 * l2 + 5 + j;
                int j3 = k + k2 * l;
                int k3 = l2 - 10;
                String s = GuiShaderOptions.getButtonText(shaderoption, k3);
                GuiButtonShaderOption guibuttonshaderoption = Shaders.isShaderPackOptionSlider(shaderoption.getName()) ? new GuiSliderShaderOption(i + i2, i3, j3, k3, j1, shaderoption, s) : new GuiButtonShaderOption(i + i2, i3, j3, k3, j1, shaderoption, s);
                guibuttonshaderoption.active = shaderoption.isEnabled();
                this.addButton(guibuttonshaderoption);
            }
        }
        this.addButton(new GuiButtonOF(201, this.width / 2 - i1 - 20, this.height / 6 + 168 + 11, i1, j1, K_1289_S.n_1700_B("controls.reset", new Object[0])));
        this.addButton(new GuiButtonOF(200, this.width / 2 + 20, this.height / 6 + 168 + 11, i1, j1, K_1289_S.n_1700_B("gui.done", new Object[0])));
    }

    public static String getButtonText(ShaderOption so, int btnWidth) {
        String s = so.getNameText();
        if (so instanceof ShaderOptionScreen) {
            ShaderOptionScreen shaderoptionscreen = (ShaderOptionScreen)so;
            return s + "...";
        }
        Y_4083_F fontrenderer = Config.getMinecraft().t_148_a;
        int i = fontrenderer.J_1907_R(": " + Lang.getOff()) + 5;
        while (fontrenderer.J_1907_R(s) + i >= btnWidth && s.length() > 0) {
            s = s.substring(0, s.length() - 1);
        }
        String s1 = so.isChanged() ? so.getValueColor(so.getValue()) : "";
        String s2 = so.getValueText(so.getValue());
        return s + ": " + s1 + s2;
    }

    @Override
    protected void actionPerformed(V_2511_L guiElement) {
        if (guiElement instanceof GuiButtonOF) {
            GuiButtonOF guibuttonof = (GuiButtonOF)guiElement;
            if (guibuttonof.active) {
                if (guibuttonof.id < 200 && guibuttonof instanceof GuiButtonShaderOption) {
                    GuiButtonShaderOption guibuttonshaderoption = (GuiButtonShaderOption)guibuttonof;
                    ShaderOption shaderoption = guibuttonshaderoption.getShaderOption();
                    if (shaderoption instanceof ShaderOptionScreen) {
                        String s = shaderoption.getName();
                        GuiShaderOptions guishaderoptions = new GuiShaderOptions(this, this.settings, s);
                        this.minecraft.n_1700_B(guishaderoptions);
                        return;
                    }
                    if (GuiShaderOptions.hasShiftDown()) {
                        shaderoption.resetValue();
                    } else if (guibuttonshaderoption.isSwitchable()) {
                        shaderoption.nextValue();
                    }
                    this.updateAllButtons();
                    this.changed = true;
                }
                if (guibuttonof.id == 201) {
                    ShaderOption[] ashaderoption = Shaders.getChangedOptions(Shaders.getShaderPackOptions());
                    for (int i = 0; i < ashaderoption.length; ++i) {
                        ShaderOption shaderoption1 = ashaderoption[i];
                        shaderoption1.resetValue();
                        this.changed = true;
                    }
                    this.updateAllButtons();
                }
                if (guibuttonof.id == 200) {
                    if (this.changed) {
                        Shaders.saveShaderPackOptions();
                        this.changed = false;
                        Shaders.uninit();
                    }
                    this.minecraft.n_1700_B(this.prevScreen);
                }
            }
        }
    }

    @Override
    public void onClose() {
        if (this.changed) {
            Shaders.saveShaderPackOptions();
            this.changed = false;
            Shaders.uninit();
        }
        super.onClose();
    }

    @Override
    protected void actionPerformedRightClick(V_2511_L guiElement) {
        if (guiElement instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption guibuttonshaderoption = (GuiButtonShaderOption)guiElement;
            ShaderOption shaderoption = guibuttonshaderoption.getShaderOption();
            if (GuiShaderOptions.hasShiftDown()) {
                shaderoption.resetValue();
            } else if (guibuttonshaderoption.isSwitchable()) {
                shaderoption.prevValue();
            }
            this.updateAllButtons();
            this.changed = true;
        }
    }

    private void updateAllButtons() {
        for (V_2511_L button : this.buttonList) {
            if (!(button instanceof GuiButtonShaderOption)) continue;
            GuiButtonShaderOption guibuttonshaderoption = (GuiButtonShaderOption)button;
            ShaderOption shaderoption = guibuttonshaderoption.getShaderOption();
            if (shaderoption instanceof ShaderOptionProfile) {
                ShaderOptionProfile shaderoptionprofile = (ShaderOptionProfile)shaderoption;
                shaderoptionprofile.updateProfile();
            }
            guibuttonshaderoption.setMessage(GuiShaderOptions.getButtonText(shaderoption, guibuttonshaderoption.getWidth()));
            guibuttonshaderoption.valueChanged();
        }
    }

    @Override
    public void render(g_221_o matrixStackIn, int x, int y, float partialTicks) {
        this.renderBackground(matrixStackIn);
        if (this.screenText != null) {
            GuiShaderOptions.drawCenteredString(matrixStackIn, this.fontRenderer, this.screenText, this.width / 2, 15, 0xFFFFFF);
        } else {
            GuiShaderOptions.drawCenteredString(matrixStackIn, this.fontRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
        }
        super.render(matrixStackIn, x, y, partialTicks);
        this.tooltipManager.drawTooltips(matrixStackIn, x, y, this.buttonList);
    }
}

