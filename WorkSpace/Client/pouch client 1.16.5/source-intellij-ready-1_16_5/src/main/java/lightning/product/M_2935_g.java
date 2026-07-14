/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Optional;
import lightning.product.D_4024_W;
import lightning.product.E_4704_H;
import lightning.product.F_2904_S;
import lightning.product.G_463_a;
import lightning.product.I_1084_e;
import lightning.product.I_2212_R;
import lightning.product.K_2069_m;
import lightning.product.K_2357_w;
import lightning.product.CycleOption;
import lightning.product.P_3084_J;
import lightning.product.P_4249_L;
import lightning.product.U_1085_u;
import lightning.product.U_2871_b;
import lightning.product.U_679_Y;
import lightning.product.V_2511_L;
import lightning.product.V_4423_d;
import lightning.product.X_933_l;
import lightning.product.BooleanOption;
import lightning.product.MinecraftClient;
import lightning.product.FormattedCharSequence;
import lightning.product.e_3022_i;
import lightning.product.g_164_R;
import lightning.product.g_4418_P;
import lightning.product.j_4067_x;
import lightning.product.CommonComponents;
import lightning.product.u_530_F;
import lightning.product.LogaritmicProgressOption;
import lightning.product.x_282_a;
import net.optifine.Config;
import net.optifine.config.IteratableOptionOF;
import net.optifine.config.SliderPercentageOptionOF;

public abstract class M_2935_g {
    public static final I_2212_R BIOME_BLEND_RADIUS = new I_2212_R("options.biomeBlendRadius", 0.0, 7.0, 1.0f, p_lambda$static$0_0_ -> p_lambda$static$0_0_.x_607_J, (p_lambda$static$1_0_, p_lambda$static$1_1_) -> {
        p_lambda$static$1_0_.x_607_J = u_530_F.n_1700_B((int)p_lambda$static$1_1_.doubleValue(), 0, 7);
        MinecraftClient.A_4115_X().u_1723_Y.P_1922_E();
    }, (p_lambda$static$2_0_, p_lambda$static$2_1_) -> {
        double d0 = p_lambda$static$2_1_.get((V_4423_d)p_lambda$static$2_0_);
        int i = (int)d0 * 2 + 1;
        return p_lambda$static$2_1_.getGenericValueComponent(new F_2904_S("options.biomeBlendRadius." + i));
    });
    public static final I_2212_R CHAT_HEIGHT_FOCUSED = new I_2212_R("options.chat.height.focused", 0.0, 1.0, 0.0f, p_lambda$static$3_0_ -> p_lambda$static$3_0_.q_2307_F, (p_lambda$static$4_0_, p_lambda$static$4_1_) -> {
        p_lambda$static$4_0_.q_2307_F = p_lambda$static$4_1_;
        MinecraftClient.A_4115_X().M_588_G.R_4764_Y().n_1700_B();
    }, (p_lambda$static$5_0_, p_lambda$static$5_1_) -> {
        double d0 = p_lambda$static$5_1_.normalizeValue(p_lambda$static$5_1_.get((V_4423_d)p_lambda$static$5_0_));
        return p_lambda$static$5_1_.getPixelValueComponent(U_1085_u.R_4764_Y(d0));
    });
    public static final I_2212_R CHAT_HEIGHT_UNFOCUSED = new I_2212_R("options.chat.height.unfocused", 0.0, 1.0, 0.0f, p_lambda$static$6_0_ -> p_lambda$static$6_0_.k_2293_S, (p_lambda$static$7_0_, p_lambda$static$7_1_) -> {
        p_lambda$static$7_0_.k_2293_S = p_lambda$static$7_1_;
        MinecraftClient.A_4115_X().M_588_G.R_4764_Y().n_1700_B();
    }, (p_lambda$static$8_0_, p_lambda$static$8_1_) -> {
        double d0 = p_lambda$static$8_1_.normalizeValue(p_lambda$static$8_1_.get((V_4423_d)p_lambda$static$8_0_));
        return p_lambda$static$8_1_.getPixelValueComponent(U_1085_u.R_4764_Y(d0));
    });
    public static final I_2212_R CHAT_OPACITY = new I_2212_R("options.chat.opacity", 0.0, 1.0, 0.0f, p_lambda$static$9_0_ -> p_lambda$static$9_0_.u_2550_I, (p_lambda$static$10_0_, p_lambda$static$10_1_) -> {
        p_lambda$static$10_0_.u_2550_I = p_lambda$static$10_1_;
        MinecraftClient.A_4115_X().M_588_G.R_4764_Y().n_1700_B();
    }, (p_lambda$static$11_0_, p_lambda$static$11_1_) -> {
        double d0 = p_lambda$static$11_1_.normalizeValue(p_lambda$static$11_1_.get((V_4423_d)p_lambda$static$11_0_));
        return p_lambda$static$11_1_.getPercentValueComponent(d0 * 0.9 + 0.1);
    });
    public static final I_2212_R CHAT_SCALE = new I_2212_R("options.chat.scale", 0.0, 1.0, 0.0f, p_lambda$static$12_0_ -> p_lambda$static$12_0_.Q_2552_b, (p_lambda$static$13_0_, p_lambda$static$13_1_) -> {
        p_lambda$static$13_0_.Q_2552_b = p_lambda$static$13_1_;
        MinecraftClient.A_4115_X().M_588_G.R_4764_Y().n_1700_B();
    }, (p_lambda$static$14_0_, p_lambda$static$14_1_) -> {
        double d0 = p_lambda$static$14_1_.normalizeValue(p_lambda$static$14_1_.get((V_4423_d)p_lambda$static$14_0_));
        return d0 == 0.0 ? CommonComponents.n_1700_B(p_lambda$static$14_1_.getBaseMessageTranslation(), false) : p_lambda$static$14_1_.getPercentValueComponent(d0);
    });
    public static final I_2212_R CHAT_WIDTH = new I_2212_R("options.chat.width", 0.0, 1.0, 0.0f, p_lambda$static$15_0_ -> p_lambda$static$15_0_.C_2741_M / 4.0571431, (p_lambda$static$16_0_, p_lambda$static$16_1_) -> {
        p_lambda$static$16_1_ = p_lambda$static$16_1_ * 4.0571431;
        p_lambda$static$16_0_.C_2741_M = p_lambda$static$16_1_;
        MinecraftClient.A_4115_X().M_588_G.R_4764_Y().n_1700_B();
    }, (p_lambda$static$17_0_, p_lambda$static$17_1_) -> {
        double d0 = p_lambda$static$17_1_.normalizeValue(p_lambda$static$17_1_.get((V_4423_d)p_lambda$static$17_0_));
        return p_lambda$static$17_1_.getPixelValueComponent(U_1085_u.J_1907_R(d0 * 4.0571431));
    });
    public static final I_2212_R LINE_SPACING = new I_2212_R("options.chat.line_spacing", 0.0, 1.0, 0.0f, p_lambda$static$18_0_ -> p_lambda$static$18_0_.M_588_G, (p_lambda$static$19_0_, p_lambda$static$19_1_) -> {
        p_lambda$static$19_0_.M_588_G = p_lambda$static$19_1_;
    }, (p_lambda$static$20_0_, p_lambda$static$20_1_) -> p_lambda$static$20_1_.getPercentValueComponent(p_lambda$static$20_1_.normalizeValue(p_lambda$static$20_1_.get((V_4423_d)p_lambda$static$20_0_))));
    public static final I_2212_R DELAY_INSTANT = new I_2212_R("options.chat.delay_instant", 0.0, 6.0, 0.1f, p_lambda$static$21_0_ -> p_lambda$static$21_0_.Z_875_P, (p_lambda$static$22_0_, p_lambda$static$22_1_) -> {
        p_lambda$static$22_0_.Z_875_P = p_lambda$static$22_1_;
    }, (p_lambda$static$23_0_, p_lambda$static$23_1_) -> {
        double d0 = p_lambda$static$23_1_.get((V_4423_d)p_lambda$static$23_0_);
        return d0 <= 0.0 ? new F_2904_S("options.chat.delay_none") : new F_2904_S("options.chat.delay", String.format("%.1f", d0));
    });
    public static final I_2212_R FOV = new I_2212_R("options.fov", 30.0, 110.0, 1.0f, p_lambda$static$24_0_ -> p_lambda$static$24_0_.R_3077_Z, (p_lambda$static$25_0_, p_lambda$static$25_1_) -> {
        p_lambda$static$25_0_.R_3077_Z = p_lambda$static$25_1_;
    }, (p_lambda$static$26_0_, p_lambda$static$26_1_) -> {
        double d0 = p_lambda$static$26_1_.get((V_4423_d)p_lambda$static$26_0_);
        if (d0 == 70.0) {
            return p_lambda$static$26_1_.getGenericValueComponent(new F_2904_S("options.fov.min"));
        }
        return d0 == p_lambda$static$26_1_.getMaxValue() ? p_lambda$static$26_1_.getGenericValueComponent(new F_2904_S("options.fov.max")) : p_lambda$static$26_1_.getMessageWithValue((int)d0);
    });
    private static final x_282_a FOV_EFFECT_SCALE_TOOLTIP = new F_2904_S("options.fovEffectScale.tooltip");
    public static final I_2212_R FOV_EFFECT_SCALE_SLIDER = new I_2212_R("options.fovEffectScale", 0.0, 1.0, 0.0f, p_lambda$static$27_0_ -> Math.pow(p_lambda$static$27_0_.M_2677_i, 2.0), (p_lambda$static$28_0_, p_lambda$static$28_1_) -> {
        p_lambda$static$28_0_.M_2677_i = u_530_F.n_1700_B(p_lambda$static$28_1_);
    }, (p_lambda$static$29_0_, p_lambda$static$29_1_) -> {
        p_lambda$static$29_1_.setOptionValues(MinecraftClient.A_4115_X().t_148_a.J_1907_R(FOV_EFFECT_SCALE_TOOLTIP, 200));
        double d0 = p_lambda$static$29_1_.normalizeValue(p_lambda$static$29_1_.get((V_4423_d)p_lambda$static$29_0_));
        return d0 == 0.0 ? p_lambda$static$29_1_.getGenericValueComponent(new F_2904_S("options.fovEffectScale.off")) : p_lambda$static$29_1_.getPercentValueComponent(d0);
    });
    private static final x_282_a SCREEN_EFFECT_SCALE_TOOLTIP = new F_2904_S("options.screenEffectScale.tooltip");
    public static final I_2212_R SCREEN_EFFECT_SCALE_SLIDER = new I_2212_R("options.screenEffectScale", 0.0, 1.0, 0.0f, p_lambda$static$30_0_ -> p_lambda$static$30_0_.RealmsScreenWithCallback, (p_lambda$static$31_0_, p_lambda$static$31_1_) -> {
        p_lambda$static$31_0_.RealmsScreenWithCallback = p_lambda$static$31_1_.floatValue();
    }, (p_lambda$static$32_0_, p_lambda$static$32_1_) -> {
        p_lambda$static$32_1_.setOptionValues(MinecraftClient.A_4115_X().t_148_a.J_1907_R(SCREEN_EFFECT_SCALE_TOOLTIP, 200));
        double d0 = p_lambda$static$32_1_.normalizeValue(p_lambda$static$32_1_.get((V_4423_d)p_lambda$static$32_0_));
        return d0 == 0.0 ? p_lambda$static$32_1_.getGenericValueComponent(new F_2904_S("options.screenEffectScale.off")) : p_lambda$static$32_1_.getPercentValueComponent(d0);
    });
    public static final I_2212_R FRAMERATE_LIMIT = new I_2212_R("options.framerateLimit", 0.0, 260.0, 5.0f, p_lambda$static$33_0_ -> p_lambda$static$33_0_.q_4610_l ? 0.0 : (double)p_lambda$static$33_0_.G_564_y, (p_lambda$static$34_0_, p_lambda$static$34_1_) -> {
        p_lambda$static$34_0_.G_564_y = (int)p_lambda$static$34_1_.doubleValue();
        p_lambda$static$34_0_.q_4610_l = false;
        if (p_lambda$static$34_0_.G_564_y <= 0) {
            p_lambda$static$34_0_.G_564_y = 260;
            p_lambda$static$34_0_.q_4610_l = true;
        }
        p_lambda$static$34_0_.u_2550_I();
        MinecraftClient.A_4115_X().RealmsServerPing().n_1700_B(p_lambda$static$34_0_.G_564_y);
    }, (p_lambda$static$35_0_, p_lambda$static$35_1_) -> {
        if (p_lambda$static$35_0_.q_4610_l) {
            return p_lambda$static$35_1_.getGenericValueComponent(new F_2904_S("of.options.framerateLimit.vsync"));
        }
        double d0 = p_lambda$static$35_1_.get((V_4423_d)p_lambda$static$35_0_);
        return d0 == p_lambda$static$35_1_.getMaxValue() ? p_lambda$static$35_1_.getGenericValueComponent(new F_2904_S("options.framerateLimit.max")) : p_lambda$static$35_1_.getGenericValueComponent(new F_2904_S("options.framerate", (int)d0));
    });
    public static final I_2212_R GAMMA = new I_2212_R("options.gamma", 0.0, 1.0, 0.0f, p_lambda$static$36_0_ -> p_lambda$static$36_0_.c_132_F, (p_lambda$static$37_0_, p_lambda$static$37_1_) -> {
        p_lambda$static$37_0_.c_132_F = p_lambda$static$37_1_;
    }, (p_lambda$static$38_0_, p_lambda$static$38_1_) -> {
        double d0 = p_lambda$static$38_1_.normalizeValue(p_lambda$static$38_1_.get((V_4423_d)p_lambda$static$38_0_));
        if (d0 == 0.0) {
            return p_lambda$static$38_1_.getGenericValueComponent(new F_2904_S("options.gamma.min"));
        }
        return d0 == 1.0 ? p_lambda$static$38_1_.getGenericValueComponent(new F_2904_S("options.gamma.max")) : p_lambda$static$38_1_.getPercentageAddMessage((int)(d0 * 100.0));
    });
    public static final I_2212_R MIPMAP_LEVELS = new I_2212_R("options.mipmapLevels", 0.0, 4.0, 1.0f, p_lambda$static$39_0_ -> p_lambda$static$39_0_.c_3005_b, (p_lambda$static$40_0_, p_lambda$static$40_1_) -> {
        p_lambda$static$40_0_.c_3005_b = (int)p_lambda$static$40_1_.doubleValue();
        p_lambda$static$40_0_.M_588_G();
    }, (p_lambda$static$41_0_, p_lambda$static$41_1_) -> {
        double d0 = p_lambda$static$41_1_.get((V_4423_d)p_lambda$static$41_0_);
        if (d0 >= 4.0) {
            return p_lambda$static$41_1_.getGenericValueComponent(new F_2904_S("of.general.max"));
        }
        return d0 == 0.0 ? CommonComponents.n_1700_B(p_lambda$static$41_1_.getBaseMessageTranslation(), false) : p_lambda$static$41_1_.getMessageWithValue((int)d0);
    });
    public static final I_2212_R MOUSE_WHEEL_SENSITIVITY = new LogaritmicProgressOption("options.mouseWheelSensitivity", 0.01, 10.0, 0.01f, p_lambda$static$42_0_ -> p_lambda$static$42_0_.e_4240_b, (p_lambda$static$43_0_, p_lambda$static$43_1_) -> {
        p_lambda$static$43_0_.e_4240_b = p_lambda$static$43_1_;
    }, (p_lambda$static$44_0_, p_lambda$static$44_1_) -> {
        double d0 = p_lambda$static$44_1_.normalizeValue(p_lambda$static$44_1_.get((V_4423_d)p_lambda$static$44_0_));
        return p_lambda$static$44_1_.getGenericValueComponent(new U_2871_b(String.format("%.2f", p_lambda$static$44_1_.denormalizeValue(d0))));
    });
    public static final BooleanOption RAW_MOUSE_INPUT = new BooleanOption("options.rawMouseInput", p_lambda$static$45_0_ -> p_lambda$static$45_0_.n_3318_d, (p_lambda$static$46_0_, p_lambda$static$46_1_) -> {
        p_lambda$static$46_0_.n_3318_d = p_lambda$static$46_1_;
        U_679_Y mainwindow = MinecraftClient.A_4115_X().RealmsServerPing();
        if (mainwindow != null) {
            mainwindow.R_4764_Y((boolean)p_lambda$static$46_1_);
        }
    });
    public static final I_2212_R RENDER_DISTANCE = new I_2212_R("options.renderDistance", 2.0, 16.0, 1.0f, p_lambda$static$47_0_ -> p_lambda$static$47_0_.J_1907_R, (p_lambda$static$48_0_, p_lambda$static$48_1_) -> {
        p_lambda$static$48_0_.J_1907_R = (int)p_lambda$static$48_1_.doubleValue();
        MinecraftClient.A_4115_X().u_1723_Y.h_1847_R();
    }, (p_lambda$static$49_0_, p_lambda$static$49_1_) -> {
        double d0 = p_lambda$static$49_1_.get((V_4423_d)p_lambda$static$49_0_);
        return p_lambda$static$49_1_.getGenericValueComponent(new F_2904_S("options.chunks", (int)d0));
    });
    public static final I_2212_R ENTITY_DISTANCE_SCALING = new I_2212_R("options.entityDistanceScaling", 0.5, 5.0, 0.25f, p_lambda$static$50_0_ -> p_lambda$static$50_0_.R_4764_Y, (p_lambda$static$51_0_, p_lambda$static$51_1_) -> {
        p_lambda$static$51_0_.R_4764_Y = (float)p_lambda$static$51_1_.doubleValue();
    }, (p_lambda$static$52_0_, p_lambda$static$52_1_) -> {
        double d0 = p_lambda$static$52_1_.get((V_4423_d)p_lambda$static$52_0_);
        return p_lambda$static$52_1_.getPercentValueComponent(d0);
    });
    public static final I_2212_R SENSITIVITY = new I_2212_R("options.sensitivity", 0.0, 1.0, 0.0f, p_lambda$static$53_0_ -> p_lambda$static$53_0_.n_1700_B, (p_lambda$static$54_0_, p_lambda$static$54_1_) -> {
        p_lambda$static$54_0_.n_1700_B = p_lambda$static$54_1_;
    }, (p_lambda$static$55_0_, p_lambda$static$55_1_) -> {
        double d0 = p_lambda$static$55_1_.normalizeValue(p_lambda$static$55_1_.get((V_4423_d)p_lambda$static$55_0_));
        if (d0 == 0.0) {
            return p_lambda$static$55_1_.getGenericValueComponent(new F_2904_S("options.sensitivity.min"));
        }
        return d0 == 1.0 ? p_lambda$static$55_1_.getGenericValueComponent(new F_2904_S("options.sensitivity.max")) : p_lambda$static$55_1_.getPercentValueComponent(2.0 * d0);
    });
    public static final I_2212_R ACCESSIBILITY_TEXT_BACKGROUND_OPACITY = new I_2212_R("options.accessibility.text_background_opacity", 0.0, 1.0, 0.0f, p_lambda$static$56_0_ -> p_lambda$static$56_0_.P_4830_p, (p_lambda$static$57_0_, p_lambda$static$57_1_) -> {
        p_lambda$static$57_0_.P_4830_p = p_lambda$static$57_1_;
        MinecraftClient.A_4115_X().M_588_G.R_4764_Y().n_1700_B();
    }, (p_lambda$static$58_0_, p_lambda$static$58_1_) -> p_lambda$static$58_1_.getPercentValueComponent(p_lambda$static$58_1_.normalizeValue(p_lambda$static$58_1_.get((V_4423_d)p_lambda$static$58_0_))));
    public static final CycleOption AO = new CycleOption("options.ao", (p_lambda$static$59_0_, p_lambda$static$59_1_) -> {
        p_lambda$static$59_0_.v_4262_N = G_463_a.n_1700_B(p_lambda$static$59_0_.v_4262_N.n_1700_B() + p_lambda$static$59_1_);
        MinecraftClient.A_4115_X().u_1723_Y.P_1922_E();
    }, (p_lambda$static$60_0_, p_lambda$static$60_1_) -> p_lambda$static$60_1_.getGenericValueComponent(new F_2904_S(p_lambda$static$60_0_.v_4262_N.J_1907_R())));
    public static final CycleOption ATTACK_INDICATOR = new CycleOption("options.attackIndicator", (p_lambda$static$61_0_, p_lambda$static$61_1_) -> {
        p_lambda$static$61_0_.A_4115_X = E_4704_H.n_1700_B(p_lambda$static$61_0_.A_4115_X.n_1700_B() + p_lambda$static$61_1_);
    }, (p_lambda$static$62_0_, p_lambda$static$62_1_) -> p_lambda$static$62_1_.getGenericValueComponent(new F_2904_S(p_lambda$static$62_0_.A_4115_X.J_1907_R())));
    public static final CycleOption CHAT_VISIBILITY = new CycleOption("options.chat.visibility", (p_lambda$static$63_0_, p_lambda$static$63_1_) -> {
        p_lambda$static$63_0_.s_956_w = g_4418_P.n_1700_B((p_lambda$static$63_0_.s_956_w.n_1700_B() + p_lambda$static$63_1_) % 3);
    }, (p_lambda$static$64_0_, p_lambda$static$64_1_) -> p_lambda$static$64_1_.getGenericValueComponent(new F_2904_S(p_lambda$static$64_0_.s_956_w.J_1907_R())));
    private static final x_282_a FAST_GRAPHICS = new F_2904_S("options.graphics.fast.tooltip");
    private static final x_282_a FABULOUS_GRAPHICS = new F_2904_S("options.graphics.fabulous.tooltip", new F_2904_S("options.graphics.fabulous").n_1700_B(D_4024_W.Y_259_p));
    private static final x_282_a FANCY_GRAPHICS = new F_2904_S("options.graphics.fancy.tooltip");
    public static final CycleOption GRAPHICS = new CycleOption("options.graphics", (p_lambda$static$65_0_, p_lambda$static$65_1_) -> {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        K_2357_w gpuwarning = minecraft.X_933_l();
        if (p_lambda$static$65_0_.u_1723_Y == P_3084_J.J_1907_R && gpuwarning.R_4764_Y()) {
            gpuwarning.G_564_y();
        } else {
            p_lambda$static$65_0_.u_1723_Y = p_lambda$static$65_0_.u_1723_Y.R_4764_Y();
            if (p_lambda$static$65_0_.u_1723_Y == P_3084_J.R_4764_Y && (Config.isShaders() || !g_164_R.v_4262_N() || !X_933_l.X_933_l() || gpuwarning.w_1484_f())) {
                p_lambda$static$65_0_.u_1723_Y = P_3084_J.n_1700_B;
            }
            p_lambda$static$65_0_.t_148_a();
            minecraft.u_1723_Y.P_1922_E();
        }
    }, (p_lambda$static$66_0_, p_lambda$static$66_1_) -> {
        switch (p_lambda$static$66_0_.u_1723_Y) {
            case n_1700_B: {
                p_lambda$static$66_1_.setOptionValues(MinecraftClient.A_4115_X().t_148_a.J_1907_R(FAST_GRAPHICS, 200));
                break;
            }
            case J_1907_R: {
                p_lambda$static$66_1_.setOptionValues(MinecraftClient.A_4115_X().t_148_a.J_1907_R(FANCY_GRAPHICS, 200));
                break;
            }
            case R_4764_Y: {
                p_lambda$static$66_1_.setOptionValues(MinecraftClient.A_4115_X().t_148_a.J_1907_R(FABULOUS_GRAPHICS, 200));
            }
        }
        F_2904_S iformattabletextcomponent = new F_2904_S(p_lambda$static$66_0_.u_1723_Y.J_1907_R());
        return p_lambda$static$66_0_.u_1723_Y == P_3084_J.R_4764_Y ? p_lambda$static$66_1_.getGenericValueComponent(iformattabletextcomponent.n_1700_B(D_4024_W.Y_259_p)) : p_lambda$static$66_1_.getGenericValueComponent(iformattabletextcomponent);
    });
    public static final CycleOption GUI_SCALE = new CycleOption("options.guiScale", (p_lambda$static$67_0_, p_lambda$static$67_1_) -> {
        p_lambda$static$67_0_.g_4106_L = u_530_F.J_1907_R(p_lambda$static$67_0_.g_4106_L + p_lambda$static$67_1_, MinecraftClient.A_4115_X().RealmsServerPing().n_1700_B(0, MinecraftClient.A_4115_X().v_4262_N()) + 1);
    }, (p_lambda$static$68_0_, p_lambda$static$68_1_) -> p_lambda$static$68_0_.g_4106_L == 0 ? p_lambda$static$68_1_.getGenericValueComponent(new F_2904_S("options.guiScale.auto")) : p_lambda$static$68_1_.getMessageWithValue(p_lambda$static$68_0_.g_4106_L));
    public static final CycleOption MAIN_HAND = new CycleOption("options.mainHand", (p_lambda$static$69_0_, p_lambda$static$69_1_) -> {
        p_lambda$static$69_0_.multiplayerClientSuggestionProvider = p_lambda$static$69_0_.multiplayerClientSuggestionProvider.n_1700_B();
    }, (p_lambda$static$70_0_, p_lambda$static$70_1_) -> p_lambda$static$70_1_.getGenericValueComponent(p_lambda$static$70_0_.multiplayerClientSuggestionProvider.J_1907_R()));
    public static final CycleOption NARRATOR = new CycleOption("options.narrator", (p_lambda$static$71_0_, p_lambda$static$71_1_) -> {
        p_lambda$static$71_0_.W_3464_O = I_1084_e.J_1907_R.n_1700_B() ? e_3022_i.n_1700_B(p_lambda$static$71_0_.W_3464_O.n_1700_B() + p_lambda$static$71_1_) : e_3022_i.n_1700_B;
        I_1084_e.J_1907_R.n_1700_B(p_lambda$static$71_0_.W_3464_O);
    }, (p_lambda$static$72_0_, p_lambda$static$72_1_) -> I_1084_e.J_1907_R.n_1700_B() ? p_lambda$static$72_1_.getGenericValueComponent(p_lambda$static$72_0_.W_3464_O.J_1907_R()) : p_lambda$static$72_1_.getGenericValueComponent(new F_2904_S("options.narrator.notavailable")));
    public static final CycleOption PARTICLES = new CycleOption("options.particles", (p_lambda$static$73_0_, p_lambda$static$73_1_) -> {
        p_lambda$static$73_0_.RealmsClientOutdatedScreen = j_4067_x.n_1700_B(p_lambda$static$73_0_.RealmsClientOutdatedScreen.J_1907_R() + p_lambda$static$73_1_);
    }, (p_lambda$static$74_0_, p_lambda$static$74_1_) -> p_lambda$static$74_1_.getGenericValueComponent(new F_2904_S(p_lambda$static$74_0_.RealmsClientOutdatedScreen.n_1700_B())));
    public static final CycleOption RENDER_CLOUDS = new CycleOption("options.renderClouds", (p_lambda$static$75_0_, p_lambda$static$75_1_) -> {
        P_4249_L framebuffer;
        p_lambda$static$75_0_.P_1922_E = K_2069_m.n_1700_B(p_lambda$static$75_0_.P_1922_E.n_1700_B() + p_lambda$static$75_1_);
        if (MinecraftClient.c_3005_b() && (framebuffer = MinecraftClient.A_4115_X().u_1723_Y.z_1737_N()) != null) {
            framebuffer.R_4764_Y(MinecraftClient.n_1700_B);
        }
    }, (p_lambda$static$76_0_, p_lambda$static$76_1_) -> p_lambda$static$76_1_.getGenericValueComponent(new F_2904_S(p_lambda$static$76_0_.P_1922_E.J_1907_R())));
    public static final CycleOption ACCESSIBILITY_TEXT_BACKGROUND = new CycleOption("options.accessibility.text_background", (p_lambda$static$77_0_, p_lambda$static$77_1_) -> {
        p_lambda$static$77_0_.N_2525_X = !p_lambda$static$77_0_.N_2525_X;
    }, (p_lambda$static$78_0_, p_lambda$static$78_1_) -> p_lambda$static$78_1_.getGenericValueComponent(new F_2904_S(p_lambda$static$78_0_.N_2525_X ? "options.accessibility.text_background.chat" : "options.accessibility.text_background.everywhere")));
    private static final x_282_a field_244787_ad = new F_2904_S("options.hideMatchedNames.tooltip");
    public static final BooleanOption AUTO_JUMP = new BooleanOption("options.autoJump", p_lambda$static$79_0_ -> p_lambda$static$79_0_.z_1737_N, (p_lambda$static$80_0_, p_lambda$static$80_1_) -> {
        p_lambda$static$80_0_.z_1737_N = p_lambda$static$80_1_;
    });
    public static final BooleanOption AUTO_SUGGEST_COMMANDS = new BooleanOption("options.autoSuggestCommands", p_lambda$static$81_0_ -> p_lambda$static$81_0_.v_4276_D, (p_lambda$static$82_0_, p_lambda$static$82_1_) -> {
        p_lambda$static$82_0_.v_4276_D = p_lambda$static$82_1_;
    });
    public static final BooleanOption field_244786_G = new BooleanOption("options.hideMatchedNames", field_244787_ad, p_lambda$static$83_0_ -> p_lambda$static$83_0_.z_1333_t, (p_lambda$static$84_0_, p_lambda$static$84_1_) -> {
        p_lambda$static$84_0_.z_1333_t = p_lambda$static$84_1_;
    });
    public static final BooleanOption CHAT_COLOR = new BooleanOption("options.chat.color", p_lambda$static$85_0_ -> p_lambda$static$85_0_.d_2461_k, (p_lambda$static$86_0_, p_lambda$static$86_1_) -> {
        p_lambda$static$86_0_.d_2461_k = p_lambda$static$86_1_;
    });
    public static final BooleanOption CHAT_LINKS = new BooleanOption("options.chat.links", p_lambda$static$87_0_ -> p_lambda$static$87_0_.G_624_v, (p_lambda$static$88_0_, p_lambda$static$88_1_) -> {
        p_lambda$static$88_0_.G_624_v = p_lambda$static$88_1_;
    });
    public static final BooleanOption CHAT_LINKS_PROMPT = new BooleanOption("options.chat.links.prompt", p_lambda$static$89_0_ -> p_lambda$static$89_0_.T_2506_i, (p_lambda$static$90_0_, p_lambda$static$90_1_) -> {
        p_lambda$static$90_0_.T_2506_i = p_lambda$static$90_1_;
    });
    public static final BooleanOption DISCRETE_MOUSE_SCROLL = new BooleanOption("options.discrete_mouse_scroll", p_lambda$static$91_0_ -> p_lambda$static$91_0_.B_1668_F, (p_lambda$static$92_0_, p_lambda$static$92_1_) -> {
        p_lambda$static$92_0_.B_1668_F = p_lambda$static$92_1_;
    });
    public static final BooleanOption VSYNC = new BooleanOption("options.vsync", p_lambda$static$93_0_ -> p_lambda$static$93_0_.q_4610_l, (p_lambda$static$94_0_, p_lambda$static$94_1_) -> {
        p_lambda$static$94_0_.q_4610_l = p_lambda$static$94_1_;
        if (MinecraftClient.A_4115_X().RealmsServerPing() != null) {
            MinecraftClient.A_4115_X().RealmsServerPing().J_1907_R(p_lambda$static$94_0_.q_4610_l);
        }
    });
    public static final BooleanOption ENTITY_SHADOWS = new BooleanOption("options.entityShadows", p_lambda$static$95_0_ -> p_lambda$static$95_0_.z_4693_k, (p_lambda$static$96_0_, p_lambda$static$96_1_) -> {
        p_lambda$static$96_0_.z_4693_k = p_lambda$static$96_1_;
    });
    public static final BooleanOption FORCE_UNICODE_FONT = new BooleanOption("options.forceUnicodeFont", p_lambda$static$97_0_ -> p_lambda$static$97_0_.g_221_o, (p_lambda$static$98_0_, p_lambda$static$98_1_) -> {
        p_lambda$static$98_0_.g_221_o = p_lambda$static$98_1_;
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        if (minecraft.RealmsServerPing() != null) {
            minecraft.n_1700_B((boolean)p_lambda$static$98_1_);
        }
    });
    public static final BooleanOption INVERT_MOUSE = new BooleanOption("options.invertMouse", p_lambda$static$99_0_ -> p_lambda$static$99_0_.e_2887_G, (p_lambda$static$100_0_, p_lambda$static$100_1_) -> {
        p_lambda$static$100_0_.e_2887_G = p_lambda$static$100_1_;
    });
    public static final BooleanOption REALMS_NOTIFICATIONS = new BooleanOption("options.realmsNotifications", p_lambda$static$101_0_ -> p_lambda$static$101_0_.g_164_R, (p_lambda$static$102_0_, p_lambda$static$102_1_) -> {
        p_lambda$static$102_0_.g_164_R = p_lambda$static$102_1_;
    });
    public static final BooleanOption REDUCED_DEBUG_INFO = new BooleanOption("options.reducedDebugInfo", p_lambda$static$103_0_ -> p_lambda$static$103_0_.X_933_l, (p_lambda$static$104_0_, p_lambda$static$104_1_) -> {
        p_lambda$static$104_0_.X_933_l = p_lambda$static$104_1_;
    });
    public static final BooleanOption SHOW_SUBTITLES = new BooleanOption("options.showSubtitles", p_lambda$static$105_0_ -> p_lambda$static$105_0_.H_1990_U, (p_lambda$static$106_0_, p_lambda$static$106_1_) -> {
        p_lambda$static$106_0_.H_1990_U = p_lambda$static$106_1_;
    });
    public static final BooleanOption SNOOPER = new BooleanOption("options.snooper", p_lambda$static$107_0_ -> {
        if (p_lambda$static$107_0_.Z_976_R) {
            // empty if block
        }
        return false;
    }, (p_lambda$static$108_0_, p_lambda$static$108_1_) -> {
        p_lambda$static$108_0_.Z_976_R = p_lambda$static$108_1_;
    });
    public static final CycleOption SNEAK = new CycleOption("key.sneak", (p_lambda$static$109_0_, p_lambda$static$109_1_) -> {
        p_lambda$static$109_0_.D_4792_h = !p_lambda$static$109_0_.D_4792_h;
    }, (p_lambda$static$110_0_, p_lambda$static$110_1_) -> p_lambda$static$110_1_.getGenericValueComponent(new F_2904_S(p_lambda$static$110_0_.D_4792_h ? "options.key.toggle" : "options.key.hold")));
    public static final CycleOption SPRINT = new CycleOption("key.sprint", (p_lambda$static$111_0_, p_lambda$static$111_1_) -> {
        p_lambda$static$111_0_.s_2632_s = !p_lambda$static$111_0_.s_2632_s;
    }, (p_lambda$static$112_0_, p_lambda$static$112_1_) -> p_lambda$static$112_1_.getGenericValueComponent(new F_2904_S(p_lambda$static$112_0_.s_2632_s ? "options.key.toggle" : "options.key.hold")));
    public static final BooleanOption TOUCHSCREEN = new BooleanOption("options.touchscreen", p_lambda$static$113_0_ -> p_lambda$static$113_0_.c_4037_x, (p_lambda$static$114_0_, p_lambda$static$114_1_) -> {
        p_lambda$static$114_0_.c_4037_x = p_lambda$static$114_1_;
    });
    public static final BooleanOption FULLSCREEN = new BooleanOption("options.fullscreen", p_lambda$static$115_0_ -> p_lambda$static$115_0_.g_2268_R, (p_lambda$static$116_0_, p_lambda$static$116_1_) -> {
        p_lambda$static$116_0_.g_2268_R = p_lambda$static$116_1_;
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        if (minecraft.RealmsServerPing() != null && minecraft.RealmsServerPing().s_956_w() != p_lambda$static$116_0_.g_2268_R) {
            minecraft.RealmsServerPing().w_1484_f();
            p_lambda$static$116_0_.g_2268_R = minecraft.RealmsServerPing().s_956_w();
        }
    });
    public static final BooleanOption VIEW_BOBBING = new BooleanOption("options.viewBobbing", p_lambda$static$117_0_ -> p_lambda$static$117_0_.T_3594_S, (p_lambda$static$118_0_, p_lambda$static$118_1_) -> {
        p_lambda$static$118_0_.T_3594_S = p_lambda$static$118_1_;
    });
    private final x_282_a translatedBaseMessage;
    private Optional<List<FormattedCharSequence>> optionValues = Optional.empty();
    private final String translationKey;
    public static final CycleOption FOG_FANCY = new IteratableOptionOF("of.options.FOG_FANCY");
    public static final CycleOption FOG_START = new IteratableOptionOF("of.options.FOG_START");
    public static final I_2212_R MIPMAP_TYPE = new SliderPercentageOptionOF("of.options.MIPMAP_TYPE", 0.0, 3.0, 1.0f);
    public static final CycleOption SMOOTH_FPS = new IteratableOptionOF("of.options.SMOOTH_FPS");
    public static final CycleOption CLOUDS = new IteratableOptionOF("of.options.CLOUDS");
    public static final I_2212_R CLOUD_HEIGHT = new SliderPercentageOptionOF("of.options.CLOUD_HEIGHT");
    public static final CycleOption TREES = new IteratableOptionOF("of.options.TREES");
    public static final CycleOption RAIN = new IteratableOptionOF("of.options.RAIN");
    public static final CycleOption ANIMATED_WATER = new IteratableOptionOF("of.options.ANIMATED_WATER");
    public static final CycleOption ANIMATED_LAVA = new IteratableOptionOF("of.options.ANIMATED_LAVA");
    public static final CycleOption ANIMATED_FIRE = new IteratableOptionOF("of.options.ANIMATED_FIRE");
    public static final CycleOption ANIMATED_PORTAL = new IteratableOptionOF("of.options.ANIMATED_PORTAL");
    public static final I_2212_R AO_LEVEL = new SliderPercentageOptionOF("of.options.AO_LEVEL");
    public static final CycleOption LAGOMETER = new IteratableOptionOF("of.options.LAGOMETER");
    public static final CycleOption SHOW_FPS = new IteratableOptionOF("of.options.SHOW_FPS");
    public static final CycleOption AUTOSAVE_TICKS = new IteratableOptionOF("of.options.AUTOSAVE_TICKS");
    public static final CycleOption BETTER_GRASS = new IteratableOptionOF("of.options.BETTER_GRASS");
    public static final CycleOption ANIMATED_REDSTONE = new IteratableOptionOF("of.options.ANIMATED_REDSTONE");
    public static final CycleOption ANIMATED_EXPLOSION = new IteratableOptionOF("of.options.ANIMATED_EXPLOSION");
    public static final CycleOption ANIMATED_FLAME = new IteratableOptionOF("of.options.ANIMATED_FLAME");
    public static final CycleOption ANIMATED_SMOKE = new IteratableOptionOF("of.options.ANIMATED_SMOKE");
    public static final CycleOption WEATHER = new IteratableOptionOF("of.options.WEATHER");
    public static final CycleOption SKY = new IteratableOptionOF("of.options.SKY");
    public static final CycleOption STARS = new IteratableOptionOF("of.options.STARS");
    public static final CycleOption SUN_MOON = new IteratableOptionOF("of.options.SUN_MOON");
    public static final CycleOption VIGNETTE = new IteratableOptionOF("of.options.VIGNETTE");
    public static final CycleOption CHUNK_UPDATES = new IteratableOptionOF("of.options.CHUNK_UPDATES");
    public static final CycleOption CHUNK_UPDATES_DYNAMIC = new IteratableOptionOF("of.options.CHUNK_UPDATES_DYNAMIC");
    public static final CycleOption TIME = new IteratableOptionOF("of.options.TIME");
    public static final CycleOption SMOOTH_WORLD = new IteratableOptionOF("of.options.SMOOTH_WORLD");
    public static final CycleOption VOID_PARTICLES = new IteratableOptionOF("of.options.VOID_PARTICLES");
    public static final CycleOption WATER_PARTICLES = new IteratableOptionOF("of.options.WATER_PARTICLES");
    public static final CycleOption RAIN_SPLASH = new IteratableOptionOF("of.options.RAIN_SPLASH");
    public static final CycleOption PORTAL_PARTICLES = new IteratableOptionOF("of.options.PORTAL_PARTICLES");
    public static final CycleOption POTION_PARTICLES = new IteratableOptionOF("of.options.POTION_PARTICLES");
    public static final CycleOption FIREWORK_PARTICLES = new IteratableOptionOF("of.options.FIREWORK_PARTICLES");
    public static final CycleOption PROFILER = new IteratableOptionOF("of.options.PROFILER");
    public static final CycleOption DRIPPING_WATER_LAVA = new IteratableOptionOF("of.options.DRIPPING_WATER_LAVA");
    public static final CycleOption BETTER_SNOW = new IteratableOptionOF("of.options.BETTER_SNOW");
    public static final CycleOption ANIMATED_TERRAIN = new IteratableOptionOF("of.options.ANIMATED_TERRAIN");
    public static final CycleOption SWAMP_COLORS = new IteratableOptionOF("of.options.SWAMP_COLORS");
    public static final CycleOption RANDOM_ENTITIES = new IteratableOptionOF("of.options.RANDOM_ENTITIES");
    public static final CycleOption SMOOTH_BIOMES = new IteratableOptionOF("of.options.SMOOTH_BIOMES");
    public static final CycleOption CUSTOM_FONTS = new IteratableOptionOF("of.options.CUSTOM_FONTS");
    public static final CycleOption CUSTOM_COLORS = new IteratableOptionOF("of.options.CUSTOM_COLORS");
    public static final CycleOption SHOW_CAPES = new IteratableOptionOF("of.options.SHOW_CAPES");
    public static final CycleOption CONNECTED_TEXTURES = new IteratableOptionOF("of.options.CONNECTED_TEXTURES");
    public static final CycleOption CUSTOM_ITEMS = new IteratableOptionOF("of.options.CUSTOM_ITEMS");
    public static final I_2212_R AA_LEVEL = new SliderPercentageOptionOF("of.options.AA_LEVEL", 0.0, 16.0, new double[]{0.0, 2.0, 4.0, 6.0, 8.0, 12.0, 16.0});
    public static final I_2212_R AF_LEVEL = new SliderPercentageOptionOF("of.options.AF_LEVEL", 1.0, 16.0, new double[]{1.0, 2.0, 4.0, 8.0, 16.0});
    public static final CycleOption ANIMATED_TEXTURES = new IteratableOptionOF("of.options.ANIMATED_TEXTURES");
    public static final CycleOption NATURAL_TEXTURES = new IteratableOptionOF("of.options.NATURAL_TEXTURES");
    public static final CycleOption EMISSIVE_TEXTURES = new IteratableOptionOF("of.options.EMISSIVE_TEXTURES");
    public static final CycleOption HELD_ITEM_TOOLTIPS = new IteratableOptionOF("of.options.HELD_ITEM_TOOLTIPS");
    public static final CycleOption DROPPED_ITEMS = new IteratableOptionOF("of.options.DROPPED_ITEMS");
    public static final CycleOption LAZY_CHUNK_LOADING = new IteratableOptionOF("of.options.LAZY_CHUNK_LOADING");
    public static final CycleOption CUSTOM_SKY = new IteratableOptionOF("of.options.CUSTOM_SKY");
    public static final CycleOption FAST_MATH = new IteratableOptionOF("of.options.FAST_MATH");
    public static final CycleOption FAST_RENDER = new IteratableOptionOF("of.options.FAST_RENDER");
    public static final CycleOption TRANSLUCENT_BLOCKS = new IteratableOptionOF("of.options.TRANSLUCENT_BLOCKS");
    public static final CycleOption DYNAMIC_FOV = new IteratableOptionOF("of.options.DYNAMIC_FOV");
    public static final CycleOption DYNAMIC_LIGHTS = new IteratableOptionOF("of.options.DYNAMIC_LIGHTS");
    public static final CycleOption ALTERNATE_BLOCKS = new IteratableOptionOF("of.options.ALTERNATE_BLOCKS");
    public static final CycleOption CUSTOM_ENTITY_MODELS = new IteratableOptionOF("of.options.CUSTOM_ENTITY_MODELS");
    public static final CycleOption ADVANCED_TOOLTIPS = new IteratableOptionOF("of.options.ADVANCED_TOOLTIPS");
    public static final CycleOption SCREENSHOT_SIZE = new IteratableOptionOF("of.options.SCREENSHOT_SIZE");
    public static final CycleOption CUSTOM_GUIS = new IteratableOptionOF("of.options.CUSTOM_GUIS");
    public static final CycleOption RENDER_REGIONS = new IteratableOptionOF("of.options.RENDER_REGIONS");
    public static final CycleOption SHOW_GL_ERRORS = new IteratableOptionOF("of.options.SHOW_GL_ERRORS");
    public static final CycleOption SMART_ANIMATIONS = new IteratableOptionOF("of.options.SMART_ANIMATIONS");
    public static final CycleOption CHAT_BACKGROUND = new IteratableOptionOF("of.options.CHAT_BACKGROUND");
    public static final CycleOption CHAT_SHADOW = new IteratableOptionOF("of.options.CHAT_SHADOW");

    public M_2935_g(String translationKeyIn) {
        this.translatedBaseMessage = new F_2904_S(translationKeyIn);
        this.translationKey = translationKeyIn;
    }

    public abstract V_2511_L createWidget(V_4423_d var1, int var2, int var3, int var4);

    public x_282_a getBaseMessageTranslation() {
        return this.translatedBaseMessage;
    }

    public void setOptionValues(List<FormattedCharSequence> values) {
        this.optionValues = Optional.of(values);
    }

    public Optional<List<FormattedCharSequence>> getOptionValues() {
        return this.optionValues;
    }

    protected x_282_a getPixelValueComponent(int value) {
        return new F_2904_S("options.pixel_value", this.getBaseMessageTranslation(), value);
    }

    protected x_282_a getPercentValueComponent(double percentage) {
        return new F_2904_S("options.percent_value", this.getBaseMessageTranslation(), (int)(percentage * 100.0));
    }

    protected x_282_a getPercentageAddMessage(int doubleIn) {
        return new F_2904_S("options.percent_add_value", this.getBaseMessageTranslation(), doubleIn);
    }

    public x_282_a getGenericValueComponent(x_282_a valueMessage) {
        return new F_2904_S("options.generic_value", this.getBaseMessageTranslation(), valueMessage);
    }

    public x_282_a getMessageWithValue(int value) {
        return this.getGenericValueComponent(new U_2871_b(Integer.toString(value)));
    }

    public String getResourceKey() {
        return this.translationKey;
    }
}



