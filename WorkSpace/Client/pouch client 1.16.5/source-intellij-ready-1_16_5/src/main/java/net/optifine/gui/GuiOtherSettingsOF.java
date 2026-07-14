/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.gui;

import lightning.product.H_234_b;
import lightning.product.K_1289_S;
import lightning.product.M_2935_g;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.q_3418_t;
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;

public class GuiOtherSettingsOF
extends GuiScreenOF {
    private k_2603_m prevScreen;
    private V_4423_d settings;
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

    public GuiOtherSettingsOF(k_2603_m guiscreen, V_4423_d gamesettings) {
        super(new U_2871_b(K_1289_S.n_1700_B("of.options.otherTitle", new Object[0])));
        this.prevScreen = guiscreen;
        this.settings = gamesettings;
    }

    @Override
    public void init() {
        this.buttonList.clear();
        H_234_b abstractoption = new H_234_b(this.minecraft.RealmsServerPing());
        M_2935_g[] aabstractoption = new M_2935_g[]{M_2935_g.LAGOMETER, M_2935_g.PROFILER, M_2935_g.SHOW_FPS, M_2935_g.ADVANCED_TOOLTIPS, M_2935_g.WEATHER, M_2935_g.TIME, M_2935_g.FULLSCREEN, M_2935_g.AUTOSAVE_TICKS, M_2935_g.SCREENSHOT_SIZE, M_2935_g.SHOW_GL_ERRORS, abstractoption, null};
        for (int i = 0; i < aabstractoption.length; ++i) {
            M_2935_g abstractoption1 = aabstractoption[i];
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 + 21 * (i / 2) - 12;
            V_2511_L widget = this.addButton(abstractoption1.createWidget(this.minecraft.P_4830_p, j, k, 150));
            if (abstractoption1 != abstractoption) continue;
            widget.setWidth(310);
            ++i;
        }
        this.addButton(new GuiButtonOF(210, this.width / 2 - 100, this.height / 6 + 168 + 11 - 44, K_1289_S.n_1700_B("of.options.other.reset", new Object[0])));
        this.addButton(new GuiButtonOF(200, this.width / 2 - 100, this.height / 6 + 168 + 11, K_1289_S.n_1700_B("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(V_2511_L guiElement) {
        if (guiElement instanceof GuiButtonOF) {
            GuiButtonOF guibuttonof = (GuiButtonOF)guiElement;
            if (guibuttonof.active) {
                if (guibuttonof.id == 200) {
                    this.minecraft.P_4830_p.J_1907_R();
                    this.minecraft.RealmsServerPing().v_4262_N();
                    this.minecraft.n_1700_B(this.prevScreen);
                }
                if (guibuttonof.id == 210) {
                    this.minecraft.P_4830_p.J_1907_R();
                    String s = K_1289_S.n_1700_B("of.message.other.reset", new Object[0]);
                    q_3418_t confirmscreen = new q_3418_t(this::confirmResult, new U_2871_b(s), new U_2871_b(""));
                    this.minecraft.n_1700_B(confirmscreen);
                }
            }
        }
    }

    @Override
    public void onClose() {
        this.minecraft.P_4830_p.J_1907_R();
        this.minecraft.RealmsServerPing().v_4262_N();
        super.onClose();
    }

    public void confirmResult(boolean flag) {
        if (flag) {
            this.minecraft.P_4830_p.s_956_w();
        }
        this.minecraft.n_1700_B(this);
    }

    @Override
    public void render(g_221_o matrixStackIn, int x, int y, float partialTicks) {
        this.renderBackground(matrixStackIn);
        GuiOtherSettingsOF.drawCenteredString(matrixStackIn, this.fontRenderer, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(matrixStackIn, x, y, partialTicks);
        this.tooltipManager.drawTooltips(matrixStackIn, x, y, this.buttonList);
    }
}


