/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.gui;

import lightning.product.K_1289_S;
import lightning.product.M_2935_g;
import lightning.product.U_2871_b;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import net.optifine.Lang;
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiScreenButtonOF;
import net.optifine.gui.GuiScreenOF;

public class GuiAnimationSettingsOF
extends GuiScreenOF {
    private k_2603_m prevScreen;
    private V_4423_d settings;
    private static M_2935_g[] enumOptions = new M_2935_g[]{M_2935_g.ANIMATED_WATER, M_2935_g.ANIMATED_LAVA, M_2935_g.ANIMATED_FIRE, M_2935_g.ANIMATED_PORTAL, M_2935_g.ANIMATED_REDSTONE, M_2935_g.ANIMATED_EXPLOSION, M_2935_g.ANIMATED_FLAME, M_2935_g.ANIMATED_SMOKE, M_2935_g.VOID_PARTICLES, M_2935_g.WATER_PARTICLES, M_2935_g.RAIN_SPLASH, M_2935_g.PORTAL_PARTICLES, M_2935_g.POTION_PARTICLES, M_2935_g.DRIPPING_WATER_LAVA, M_2935_g.ANIMATED_TERRAIN, M_2935_g.ANIMATED_TEXTURES, M_2935_g.FIREWORK_PARTICLES, M_2935_g.PARTICLES};

    public GuiAnimationSettingsOF(k_2603_m guiscreen, V_4423_d gamesettings) {
        super(new U_2871_b(K_1289_S.n_1700_B("of.options.animationsTitle", new Object[0])));
        this.prevScreen = guiscreen;
        this.settings = gamesettings;
    }

    @Override
    public void init() {
        this.buttonList.clear();
        for (int i = 0; i < enumOptions.length; ++i) {
            M_2935_g abstractoption = enumOptions[i];
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 + 21 * (i / 2) - 12;
            this.addButton(abstractoption.createWidget(this.minecraft.P_4830_p, j, k, 150));
        }
        this.addButton(new GuiButtonOF(210, this.width / 2 - 155, this.height / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOn")));
        this.addButton(new GuiButtonOF(211, this.width / 2 - 155 + 80, this.height / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOff")));
        this.addButton(new GuiScreenButtonOF(200, this.width / 2 + 5, this.height / 6 + 168 + 11, K_1289_S.n_1700_B("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(V_2511_L guiElement) {
        if (guiElement instanceof GuiButtonOF) {
            GuiButtonOF guibuttonof = (GuiButtonOF)guiElement;
            if (guibuttonof.active) {
                if (guibuttonof.id == 200) {
                    this.minecraft.P_4830_p.J_1907_R();
                    this.minecraft.n_1700_B(this.prevScreen);
                }
                if (guibuttonof.id == 210) {
                    this.minecraft.P_4830_p.n_1700_B(true);
                }
                if (guibuttonof.id == 211) {
                    this.minecraft.P_4830_p.n_1700_B(false);
                }
                this.minecraft.u_2550_I();
            }
        }
    }

    @Override
    public void onClose() {
        this.minecraft.P_4830_p.J_1907_R();
        super.onClose();
    }

    @Override
    public void render(g_221_o matrixStackIn, int x, int y, float partialTicks) {
        this.renderBackground(matrixStackIn);
        GuiAnimationSettingsOF.drawCenteredString(matrixStackIn, this.minecraft.t_148_a, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(matrixStackIn, x, y, partialTicks);
    }
}

