/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  org.lwjgl.glfw.GLFW
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.FormattedText;
import lightning.product.K_1289_S;
import lightning.product.K_2357_w;
import lightning.product.M_2935_g;
import lightning.product.P_3084_J;
import lightning.product.U_2871_b;
import lightning.product.U_679_Y;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.h_4412_P;
import lightning.product.k_2603_m;
import lightning.product.o_3756_j;
import lightning.product.x_282_a;
import net.optifine.Config;
import net.optifine.Lang;
import net.optifine.gui.GuiAnimationSettingsOF;
import net.optifine.gui.GuiButtonOF;
import net.optifine.gui.GuiDetailSettingsOF;
import net.optifine.gui.GuiOtherSettingsOF;
import net.optifine.gui.GuiPerformanceSettingsOF;
import net.optifine.gui.GuiQualitySettingsOF;
import net.optifine.gui.GuiScreenButtonOF;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;
import net.optifine.shaders.gui.GuiShaders;
import net.optifine.util.GuiUtils;
import org.lwjgl.glfw.GLFW;

public class J_4417_W
extends GuiScreenOF {
    private k_2603_m n_1700_B;
    private V_4423_d J_1907_R;
    private static M_2935_g[] R_4764_Y = new M_2935_g[]{M_2935_g.GRAPHICS, M_2935_g.RENDER_DISTANCE, M_2935_g.AO, M_2935_g.FRAMERATE_LIMIT, M_2935_g.AO_LEVEL, M_2935_g.VIEW_BOBBING, M_2935_g.GUI_SCALE, M_2935_g.ENTITY_SHADOWS, M_2935_g.GAMMA, M_2935_g.ATTACK_INDICATOR, M_2935_g.DYNAMIC_LIGHTS, M_2935_g.DYNAMIC_FOV};
    private K_2357_w G_564_y;
    private static final x_282_a P_1922_E = new F_2904_S("options.graphics.fabulous").n_1700_B(D_4024_W.Y_259_p);
    private static final x_282_a u_1723_Y = new F_2904_S("options.graphics.warning.message", P_1922_E, P_1922_E);
    private static final x_282_a v_4262_N = new F_2904_S("options.graphics.warning.title").n_1700_B(D_4024_W.P_4830_p);
    private static final x_282_a w_1484_f = new F_2904_S("options.graphics.warning.accept");
    private static final x_282_a t_148_a = new F_2904_S("options.graphics.warning.cancel");
    private static final x_282_a s_956_w = new U_2871_b("\n");
    private TooltipManager u_2550_I = new TooltipManager(this, new TooltipProviderOptions());
    private List<V_2511_L> M_588_G = this.buttons;
    private V_2511_L P_4830_p;

    public J_4417_W(k_2603_m parentScreenIn, V_4423_d gameSettingsIn) {
        super(new F_2904_S("options.videoTitle"));
        this.n_1700_B = parentScreenIn;
        this.J_1907_R = gameSettingsIn;
        this.G_564_y = this.n_1700_B.minecraft.X_933_l();
        this.G_564_y.t_148_a();
        if (this.J_1907_R.u_1723_Y == P_3084_J.R_4764_Y) {
            this.G_564_y.P_1922_E();
        }
    }

    @Override
    public void init() {
        this.M_588_G.clear();
        for (int i = 0; i < R_4764_Y.length; ++i) {
            M_2935_g abstractoption = R_4764_Y[i];
            if (abstractoption == null) continue;
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 + 21 * (i / 2) - 12;
            V_2511_L widget = this.addButton(abstractoption.createWidget(this.minecraft.P_4830_p, j, k, 150));
            if (abstractoption != M_2935_g.GUI_SCALE) continue;
            this.P_4830_p = widget;
        }
        int l = this.height / 6 + 21 * (R_4764_Y.length / 2) - 12;
        int i1 = 0;
        i1 = this.width / 2 - 155 + 0;
        this.addButton(new GuiScreenButtonOF(231, i1, l, Lang.get("of.options.shaders")));
        i1 = this.width / 2 - 155 + 160;
        this.addButton(new GuiScreenButtonOF(202, i1, l, Lang.get("of.options.quality")));
        i1 = this.width / 2 - 155 + 0;
        this.addButton(new GuiScreenButtonOF(201, i1, l += 21, Lang.get("of.options.details")));
        i1 = this.width / 2 - 155 + 160;
        this.addButton(new GuiScreenButtonOF(212, i1, l, Lang.get("of.options.performance")));
        i1 = this.width / 2 - 155 + 0;
        this.addButton(new GuiScreenButtonOF(211, i1, l += 21, Lang.get("of.options.animations")));
        i1 = this.width / 2 - 155 + 160;
        this.addButton(new GuiScreenButtonOF(222, i1, l, Lang.get("of.options.other")));
        l += 21;
        this.addButton(new GuiButtonOF(200, this.width / 2 - 100, this.height / 6 + 168 + 11, K_1289_S.n_1700_B("gui.done", new Object[0])));
    }

    @Override
    protected void actionPerformed(V_2511_L p_actionPerformed_1_) {
        if (p_actionPerformed_1_ == this.P_4830_p) {
            this.J_1907_R();
        }
        this.n_1700_B();
        if (p_actionPerformed_1_ instanceof GuiButtonOF) {
            GuiButtonOF guibuttonof = (GuiButtonOF)p_actionPerformed_1_;
            this.n_1700_B(guibuttonof, 1);
        }
    }

    private void n_1700_B() {
        if (this.G_564_y.v_4262_N()) {
            String s2;
            String s1;
            ArrayList list = Lists.newArrayList((Object[])new FormattedText[]{u_1723_Y, s_956_w});
            String s = this.G_564_y.s_956_w();
            if (s != null) {
                list.add(s_956_w);
                list.add(new F_2904_S("options.graphics.warning.renderer", s).n_1700_B(D_4024_W.w_1484_f));
            }
            if ((s1 = this.G_564_y.M_588_G()) != null) {
                list.add(s_956_w);
                list.add(new F_2904_S("options.graphics.warning.vendor", s1).n_1700_B(D_4024_W.w_1484_f));
            }
            if ((s2 = this.G_564_y.u_2550_I()) != null) {
                list.add(s_956_w);
                list.add(new F_2904_S("options.graphics.warning.version", s2).n_1700_B(D_4024_W.w_1484_f));
            }
            this.minecraft.n_1700_B(new o_3756_j(v_4262_N, list, (ImmutableList<o_3756_j.n_1700_B>)ImmutableList.of((Object)new o_3756_j.n_1700_B(w_1484_f, p_lambda$checkFabulousWarning$0_1_ -> {
                this.J_1907_R.u_1723_Y = P_3084_J.R_4764_Y;
                MinecraftClient.A_4115_X().u_1723_Y.P_1922_E();
                this.G_564_y.P_1922_E();
                this.minecraft.n_1700_B(this);
            }), (Object)new o_3756_j.n_1700_B(t_148_a, p_lambda$checkFabulousWarning$1_1_ -> {
                this.G_564_y.u_1723_Y();
                this.minecraft.n_1700_B(this);
            }))));
        }
    }

    @Override
    protected void actionPerformedRightClick(V_2511_L p_actionPerformedRightClick_1_) {
        if (p_actionPerformedRightClick_1_ == this.P_4830_p) {
            M_2935_g.GUI_SCALE.setValueIndex(this.J_1907_R, -1);
            this.J_1907_R();
        }
    }

    private void J_1907_R() {
        this.minecraft.u_2550_I();
        U_679_Y mainwindow = this.minecraft.RealmsServerPing();
        int i = GuiUtils.getWidth(this.P_4830_p);
        int j = GuiUtils.getHeight(this.P_4830_p);
        int k = this.P_4830_p.x + (i - j);
        int l = this.P_4830_p.y + j / 2;
        GLFW.glfwSetCursorPos((long)mainwindow.t_148_a(), (double)((double)k * mainwindow.w_1457_N()), (double)((double)l * mainwindow.w_1457_N()));
    }

    private void n_1700_B(GuiButtonOF p_actionPerformed_1_, int p_actionPerformed_2_) {
        if (p_actionPerformed_1_.active) {
            if (p_actionPerformed_1_.id == 200) {
                this.minecraft.P_4830_p.J_1907_R();
                this.minecraft.n_1700_B(this.n_1700_B);
            }
            if (p_actionPerformed_1_.id == 201) {
                this.minecraft.P_4830_p.J_1907_R();
                GuiDetailSettingsOF guidetailsettingsof = new GuiDetailSettingsOF(this, this.J_1907_R);
                this.minecraft.n_1700_B(guidetailsettingsof);
            }
            if (p_actionPerformed_1_.id == 202) {
                this.minecraft.P_4830_p.J_1907_R();
                GuiQualitySettingsOF guiqualitysettingsof = new GuiQualitySettingsOF(this, this.J_1907_R);
                this.minecraft.n_1700_B(guiqualitysettingsof);
            }
            if (p_actionPerformed_1_.id == 211) {
                this.minecraft.P_4830_p.J_1907_R();
                GuiAnimationSettingsOF guianimationsettingsof = new GuiAnimationSettingsOF(this, this.J_1907_R);
                this.minecraft.n_1700_B(guianimationsettingsof);
            }
            if (p_actionPerformed_1_.id == 212) {
                this.minecraft.P_4830_p.J_1907_R();
                GuiPerformanceSettingsOF guiperformancesettingsof = new GuiPerformanceSettingsOF(this, this.J_1907_R);
                this.minecraft.n_1700_B(guiperformancesettingsof);
            }
            if (p_actionPerformed_1_.id == 222) {
                this.minecraft.P_4830_p.J_1907_R();
                GuiOtherSettingsOF guiothersettingsof = new GuiOtherSettingsOF(this, this.J_1907_R);
                this.minecraft.n_1700_B(guiothersettingsof);
            }
            if (p_actionPerformed_1_.id == 231) {
                if (Config.isAntialiasing() || Config.isAntialiasingConfigured()) {
                    Config.showGuiMessage(Lang.get("of.message.shaders.aa1"), Lang.get("of.message.shaders.aa2"));
                    return;
                }
                if (Config.isGraphicsFabulous()) {
                    Config.showGuiMessage(Lang.get("of.message.shaders.gf1"), Lang.get("of.message.shaders.gf2"));
                    return;
                }
                this.minecraft.P_4830_p.J_1907_R();
                GuiShaders guishaders = new GuiShaders(this, this.J_1907_R);
                this.minecraft.n_1700_B(guishaders);
            }
        }
    }

    @Override
    public void onClose() {
        this.minecraft.P_4830_p.J_1907_R();
        super.onClose();
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        J_4417_W.drawCenteredString(matrixStack, this.minecraft.t_148_a, this.title, this.width / 2, 15, 0xFFFFFF);
        String s = Config.getVersion();
        String s1 = "HD_U";
        if (s1.equals("HD")) {
            s = "OptiFine HD G8";
        }
        if (s1.equals("HD_U")) {
            s = "OptiFine HD G8 Ultra";
        }
        if (s1.equals("L")) {
            s = "OptiFine G8 Light";
        }
        J_4417_W.drawString(matrixStack, this.minecraft.t_148_a, s, 2, this.height - 10, 0x808080);
        String s2 = "Minecraft 1.16.5";
        int i = this.minecraft.t_148_a.J_1907_R(s2);
        J_4417_W.drawString(matrixStack, this.minecraft.t_148_a, s2, this.width - i - 2, this.height - 10, 0x808080);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.u_2550_I.drawTooltips(matrixStack, mouseX, mouseY, this.M_588_G);
    }

    public static String n_1700_B(h_4412_P p_getGuiChatText_0_) {
        return p_getGuiChatText_0_.inputField.getText();
    }
}



