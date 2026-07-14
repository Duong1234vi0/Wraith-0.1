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
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;

public class GuiDetailSettingsOF
extends GuiScreenOF {
    private k_2603_m prevScreen;
    private V_4423_d settings;
    private static M_2935_g[] enumOptions = new M_2935_g[]{M_2935_g.CLOUDS, M_2935_g.CLOUD_HEIGHT, M_2935_g.TREES, M_2935_g.RAIN, M_2935_g.SKY, M_2935_g.STARS, M_2935_g.SUN_MOON, M_2935_g.SHOW_CAPES, M_2935_g.FOG_FANCY, M_2935_g.FOG_START, M_2935_g.TRANSLUCENT_BLOCKS, M_2935_g.HELD_ITEM_TOOLTIPS, M_2935_g.DROPPED_ITEMS, M_2935_g.SWAMP_COLORS, M_2935_g.VIGNETTE, M_2935_g.ALTERNATE_BLOCKS, M_2935_g.ENTITY_DISTANCE_SCALING, M_2935_g.BIOME_BLEND_RADIUS};
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

    public GuiDetailSettingsOF(k_2603_m guiscreen, V_4423_d gamesettings) {
        super(new U_2871_b(K_1289_S.n_1700_B("of.options.detailsTitle", new Object[0])));
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
        this.addButton(new GuiButtonOF(200, this.width / 2 - 100, this.height / 6 + 168 + 11, K_1289_S.n_1700_B("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(V_2511_L guiElement) {
        if (guiElement instanceof GuiButtonOF) {
            GuiButtonOF guibuttonof = (GuiButtonOF)guiElement;
            if (guibuttonof.active && guibuttonof.id == 200) {
                this.minecraft.P_4830_p.J_1907_R();
                this.minecraft.n_1700_B(this.prevScreen);
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
        GuiDetailSettingsOF.drawCenteredString(matrixStackIn, this.minecraft.t_148_a, this.title, this.width / 2, 15, 0xFFFFFF);
        super.render(matrixStackIn, x, y, partialTicks);
        this.tooltipManager.drawTooltips(matrixStackIn, x, y, this.buttonList);
    }
}

