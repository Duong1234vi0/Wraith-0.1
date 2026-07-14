/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Ordering
 *  com.mojang.datafixers.util.Pair
 *  javax.annotation.Nullable
 *  lombok.Generated
 *  org.apache.commons.lang3.StringUtils
 */
package lightning.product;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.mojang.datafixers.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.FluidTags;
import lightning.product.StringDecomposer;
import lightning.product.B_3871_I;
import lightning.product.C_2701_A;
import lightning.product.D_3318_r;
import lightning.product.D_4024_W;
import lightning.product.E_4704_H;
import lightning.product.E_688_b;
import lightning.product.F_2904_S;
import lightning.product.F_489_x;
import lightning.product.FormattedText;
import lightning.product.BlockHitResult;
import lightning.product.H_1468_N;
import lightning.product.H_2506_c;
import lightning.product.MutableComponent;
import lightning.product.Attributes;
import lightning.product.H_3330_w;
import lightning.product.I_1084_e;
import lightning.product.I_14_v;
import lightning.product.HitResult;
import lightning.product.MobEffects;
import lightning.product.K_1200_E;
import lightning.product.L_3848_p;
import lightning.product.M_4239_y;
import lightning.product.N_4263_v;
import lightning.product.OverlayChatListener;
import lightning.product.FoodData;
import lightning.product.Interface;
import lightning.product.T_603_v;
import lightning.product.U_1085_u;
import lightning.product.U_2871_b;
import lightning.product.SpectatorGui;
import lightning.product.V_4423_d;
import lightning.product.V_4557_X;
import lightning.product.V_772_m;
import lightning.product.W_226_N;
import lightning.product.Objective;
import lightning.product.X_4340_E;
import lightning.product.X_933_l;
import lightning.product.Y_3413_I;
import lightning.product.Y_3902_T;
import lightning.product.Y_4083_F;
import lightning.product.Y_408_h;
import lightning.product.Z_1993_T;
import lightning.product.Z_875_P;
import lightning.product.a_1765_z;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_3528_u;
import lightning.product.BetterMinecraft;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.PlayerTeam;
import lightning.product.c_4037_x;
import lightning.product.d_3244_b;
import lightning.product.FreeCam;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.g_422_i;
import lightning.product.h_3270_j;
import lightning.product.h_3572_K;
import lightning.product.i_4895_l;
import lightning.product.j_3341_s;
import lightning.product.NameProtect;
import lightning.product.k_2610_C;
import lightning.product.k_4231_L;
import lightning.product.k_4690_i;
import lightning.product.l_2647_k;
import lightning.product.l_3747_P;
import lightning.product.n_1700_B;
import lightning.product.ClientBootstrap;
import lightning.product.q_3148_R;
import lightning.product.r_4811_B;
import lightning.product.s_446_k;
import lightning.product.t_3286_u;
import lightning.product.u_273_N;
import lightning.product.u_530_F;
import lightning.product.v_4839_y;
import lightning.product.EntityHitResult;
import lightning.product.x_282_a;
import lightning.product.MobEffectTextureManager;
import lightning.product.y_4842_Z;
import lightning.product.z_3427_G;
import lombok.Generated;
import net.optifine.Config;
import net.optifine.CustomColors;
import net.optifine.CustomItems;
import net.optifine.TextureAnimations;
import net.optifine.reflect.Reflector;
import org.apache.commons.lang3.StringUtils;

public class u_1406_j
extends C_2701_A {
    private static final g_2336_b J_1907_R = new g_2336_b("textures/misc/vignette.png");
    private static final g_2336_b R_4764_Y = new g_2336_b("textures/gui/widgets.png");
    private static final g_2336_b G_564_y = new g_2336_b("textures/misc/pumpkinblur.png");
    private static final x_282_a P_1922_E = new F_2904_S("demo.demoExpired");
    private final Random u_1723_Y = new Random();
    private final MinecraftClient v_4262_N;
    private final H_3330_w w_1484_f;
    private final U_1085_u t_148_a;
    private int s_956_w;
    @Nullable
    private x_282_a u_2550_I;
    private int M_588_G;
    private boolean P_4830_p;
    public float n_1700_B = 1.0f;
    private int h_1847_R;
    private Z_1993_T Q_4569_t = Z_1993_T.J_1907_R;
    private final a_1765_z M_182_A;
    private final Y_3902_T t_1786_h;
    private x_282_a multiplayerClientSuggestionProvider;
    private x_282_a w_1457_N;
    private final SpectatorGui Y_601_j;
    private final W_226_N Y_259_p;
    private final Y_3413_I Q_2552_b;
    private int C_2741_M;
    @Nullable
    private x_282_a k_2293_S;
    @Nullable
    private x_282_a q_2307_F;
    private int Z_875_P;
    private int c_3005_b;
    private int H_2857_Y;
    private int A_4115_X;
    private int Y_1740_V;
    private long t_4043_B;
    private long x_607_J;
    private int e_4240_b;
    private int n_3318_d;
    private final Map<Y_408_h, List<u_273_N>> d_2427_y = Maps.newHashMap();
    private static final V_4557_X z_1737_N = new V_4557_X();
    private static boolean v_4276_D = false;

    public u_1406_j(MinecraftClient mcIn) {
        this.v_4262_N = mcIn;
        this.w_1484_f = mcIn.r_715_M();
        this.M_182_A = new a_1765_z(mcIn);
        this.Y_601_j = new SpectatorGui(mcIn);
        this.t_148_a = new U_1085_u(mcIn);
        this.Y_259_p = new W_226_N(mcIn, this);
        this.Q_2552_b = new Y_3413_I(mcIn);
        this.t_1786_h = new Y_3902_T(mcIn);
        for (Y_408_h chattype : Y_408_h.values()) {
            this.d_2427_y.put(chattype, Lists.newArrayList());
        }
        I_1084_e ichatlistener = I_1084_e.J_1907_R;
        this.d_2427_y.get((Object)Y_408_h.n_1700_B).add(new s_446_k(mcIn));
        this.d_2427_y.get((Object)Y_408_h.n_1700_B).add(ichatlistener);
        this.d_2427_y.get((Object)Y_408_h.J_1907_R).add(new s_446_k(mcIn));
        this.d_2427_y.get((Object)Y_408_h.J_1907_R).add(ichatlistener);
        this.d_2427_y.get((Object)Y_408_h.R_4764_Y).add(new OverlayChatListener(mcIn));
        this.n_1700_B();
    }

    private n_1700_B P_4830_p() {
        if (this.v_4262_N.k_2293_S != null && this.v_4262_N.C_2741_M == this.v_4262_N.k_2293_S.P_1922_E.Q_2552_b) {
            return this.v_4262_N.k_2293_S;
        }
        if (this.v_4262_N.T_3594_S() != null) {
            return this.v_4262_N.k_2293_S;
        }
        return null;
    }

    public void n_1700_B() {
        this.Z_875_P = 10;
        this.c_3005_b = 70;
        this.H_2857_Y = 20;
    }

    public void n_1700_B(g_221_o matrixStack, float partialTicks) {
        int sleepTimer;
        I_14_v currentGameType;
        boolean hasNausea;
        float f;
        Z_1993_T itemstack;
        this.e_4240_b = this.v_4262_N.RealmsServerPing().Q_4569_t();
        this.n_3318_d = this.v_4262_N.RealmsServerPing().M_182_A();
        Y_4083_F fontrenderer = this.P_1922_E();
        c_4037_x.Y_601_j();
        if (Config.isVignetteEnabled()) {
            this.J_1907_R(this.v_4262_N.g_2268_R());
        } else {
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.s_2632_s();
        }
        this.t_1786_h();
        n_1700_B bot1 = this.P_4830_p();
        Z_1993_T z_1993_T = itemstack = bot1 != null ? bot1.P_1922_E.Q_2552_b.l_1268_F.R_4764_Y(3) : this.v_4262_N.Y_259_p.l_1268_F.R_4764_Y(3);
        if (this.v_4262_N.P_4830_p.P_4830_p().n_1700_B() && itemstack.J_1907_R() == a_3742_W.X_2048_Y.u_1723_Y()) {
            this.M_182_A();
        }
        if (bot1 != null && this.v_4262_N.T_3594_S() != null) {
            Z_875_P botPlayer = this.v_4262_N.T_3594_S();
            f = u_530_F.v_4262_N(partialTicks, botPlayer.t_4043_B, botPlayer.Y_1740_V);
            hasNausea = botPlayer.J_1907_R(MobEffects.t_148_a);
        } else {
            f = u_530_F.v_4262_N(partialTicks, this.v_4262_N.Y_259_p.P_4830_p, this.v_4262_N.Y_259_p.M_588_G);
            hasNausea = this.v_4262_N.Y_259_p.J_1907_R(MobEffects.t_148_a);
        }
        if (f > 0.0f && !hasNausea) {
            this.n_1700_B(f);
        }
        I_14_v i_14_v = currentGameType = bot1 != null ? bot1.P_1922_E.C_2741_M.P_4830_p() : this.v_4262_N.w_1457_N.getCurrentGameType();
        if (currentGameType == I_14_v.P_1922_E) {
            this.Y_601_j.n_1700_B(matrixStack, partialTicks);
        } else if (!this.v_4262_N.P_4830_p.RetryCallException) {
            this.n_1700_B(partialTicks, matrixStack);
        }
        if (!this.v_4262_N.P_4830_p.RetryCallException) {
            I_14_v tooltipGameType;
            boolean isRidingHorse;
            boolean shouldDrawHUD;
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.v_4262_N.G_624_v().n_1700_B(GUI_ICONS_LOCATION);
            c_4037_x.Y_601_j();
            c_4037_x.M_588_G();
            this.v_4262_N.s_956_w.n_1700_B(2.0f);
            d_3244_b eventCrosshair = new d_3244_b(matrixStack, partialTicks);
            lightning.product.A_4115_X.n_1700_B(eventCrosshair);
            this.v_4262_N.s_956_w.R_4764_Y();
            if (!eventCrosshair.n_1700_B()) {
                this.G_564_y(matrixStack);
            }
            X_933_l.P_1922_E();
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.v_4262_N.G_624_v().n_1700_B(GUI_ICONS_LOCATION);
            this.v_4262_N.PlayerInfo().n_1700_B("bossHealth");
            this.Q_2552_b.n_1700_B(matrixStack);
            this.v_4262_N.PlayerInfo().R_4764_Y();
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.v_4262_N.G_624_v().n_1700_B(GUI_ICONS_LOCATION);
            boolean bl = shouldDrawHUD = bot1 != null ? bot1.P_1922_E.C_2741_M.n_1700_B() : this.v_4262_N.w_1457_N.shouldDrawHUD();
            if (shouldDrawHUD) {
                this.P_1922_E(matrixStack);
            }
            this.u_1723_Y(matrixStack);
            c_4037_x.Y_259_p();
            int i = this.e_4240_b / 2 - 91;
            boolean bl2 = isRidingHorse = bot1 != null ? bot1.P_1922_E.Q_2552_b.C_2741_M() : this.v_4262_N.Y_259_p.C_2741_M();
            if (isRidingHorse) {
                this.n_1700_B(matrixStack, i);
            } else {
                boolean isSurvivalOrAdventure;
                boolean bl3 = isSurvivalOrAdventure = bot1 != null ? bot1.P_1922_E.C_2741_M.u_1723_Y() : this.v_4262_N.w_1457_N.gameIsSurvivalOrAdventure();
                if (isSurvivalOrAdventure) {
                    this.J_1907_R(matrixStack, i);
                }
            }
            I_14_v i_14_v2 = bot1 != null ? bot1.P_1922_E.C_2741_M.P_4830_p() : (tooltipGameType = this.v_4262_N.w_1457_N != null ? this.v_4262_N.w_1457_N.getCurrentGameType() : null);
            if (this.v_4262_N.P_4830_p.Y_259_p && tooltipGameType != I_14_v.P_1922_E) {
                this.J_1907_R(matrixStack);
            } else {
                boolean isSpectator;
                boolean bl4 = isSpectator = bot1 != null ? bot1.P_1922_E.Q_2552_b.d_2461_k() : this.v_4262_N.Y_259_p.d_2461_k();
                if (isSpectator) {
                    this.Y_601_j.n_1700_B(matrixStack);
                }
            }
        }
        int n = sleepTimer = bot1 != null ? bot1.P_1922_E.Q_2552_b.V_1176_p() : this.v_4262_N.Y_259_p.V_1176_p();
        if (sleepTimer > 0) {
            this.v_4262_N.PlayerInfo().n_1700_B("sleep");
            c_4037_x.t_1786_h();
            c_4037_x.u_2550_I();
            float f2 = sleepTimer;
            float f1 = f2 / 100.0f;
            if (f1 > 1.0f) {
                f1 = 1.0f - (f2 - 100.0f) / 10.0f;
            }
            int j = (int)(220.0f * f1) << 24 | 0x101020;
            u_1406_j.fill(matrixStack, 0, 0, this.e_4240_b, this.n_3318_d, j);
            c_4037_x.M_588_G();
            c_4037_x.multiplayerClientSuggestionProvider();
            this.v_4262_N.PlayerInfo().R_4764_Y();
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        }
        if (this.v_4262_N.C_2741_M()) {
            this.R_4764_Y(matrixStack);
        }
        h_3270_j potionEvent = new h_3270_j(h_3270_j.n_1700_B.multiplayerClientSuggestionProvider);
        lightning.product.A_4115_X.n_1700_B(potionEvent);
        if (!potionEvent.n_1700_B()) {
            this.n_1700_B(matrixStack);
        }
        if (this.v_4262_N.P_4830_p.r_3651_U) {
            this.M_182_A.n_1700_B(matrixStack);
        }
        if (!this.v_4262_N.P_4830_p.RetryCallException) {
            boolean wantsVisible;
            Objective scoreobjective1;
            int j2;
            if (this.u_2550_I != null && this.M_588_G > 0) {
                this.v_4262_N.PlayerInfo().n_1700_B("overlayMessage");
                float f3 = (float)this.M_588_G - partialTicks;
                int i1 = (int)(f3 * 255.0f / 20.0f);
                if (i1 > 255) {
                    i1 = 255;
                }
                if (i1 > 8) {
                    c_4037_x.v_4276_D();
                    c_4037_x.R_4764_Y((float)(this.e_4240_b / 2), (float)(this.n_3318_d - 68), 0.0f);
                    c_4037_x.Y_601_j();
                    c_4037_x.s_2632_s();
                    int k1 = 0xFFFFFF;
                    if (this.P_4830_p) {
                        k1 = u_530_F.u_1723_Y(f3 / 50.0f, 0.7f, 0.6f) & 0xFFFFFF;
                    }
                    int k = i1 << 24 & 0xFF000000;
                    int l = fontrenderer.n_1700_B((FormattedText)this.u_2550_I);
                    this.n_1700_B(matrixStack, fontrenderer, -4, l, 0xFFFFFF | k);
                    fontrenderer.J_1907_R(matrixStack, this.u_2550_I, (float)(-l / 2), -4.0f, k1 | k);
                    c_4037_x.Y_259_p();
                    c_4037_x.d_2461_k();
                }
                this.v_4262_N.PlayerInfo().R_4764_Y();
            }
            h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.G_564_y);
            lightning.product.A_4115_X.n_1700_B(event);
            if (!event.n_1700_B() && this.k_2293_S != null && this.C_2741_M > 0) {
                this.v_4262_N.PlayerInfo().n_1700_B("titleAndSubtitle");
                float f4 = (float)this.C_2741_M - partialTicks;
                int j1 = 255;
                if (this.C_2741_M > this.H_2857_Y + this.c_3005_b) {
                    float f5 = (float)(this.Z_875_P + this.c_3005_b + this.H_2857_Y) - f4;
                    j1 = (int)(f5 * 255.0f / (float)this.Z_875_P);
                }
                if (this.C_2741_M <= this.H_2857_Y) {
                    j1 = (int)(f4 * 255.0f / (float)this.H_2857_Y);
                }
                if ((j1 = u_530_F.n_1700_B(j1, 0, 255)) > 8) {
                    c_4037_x.v_4276_D();
                    c_4037_x.R_4764_Y((float)(this.e_4240_b / 2), (float)(this.n_3318_d / 2), 0.0f);
                    c_4037_x.Y_601_j();
                    c_4037_x.s_2632_s();
                    c_4037_x.v_4276_D();
                    c_4037_x.J_1907_R(4.0f, 4.0f, 4.0f);
                    int l1 = j1 << 24 & 0xFF000000;
                    int i2 = fontrenderer.n_1700_B((FormattedText)this.k_2293_S);
                    this.n_1700_B(matrixStack, fontrenderer, -10, i2, 0xFFFFFF | l1);
                    fontrenderer.n_1700_B(matrixStack, this.k_2293_S, (float)(-i2 / 2), -10.0f, 0xFFFFFF | l1);
                    c_4037_x.d_2461_k();
                    if (this.q_2307_F != null) {
                        c_4037_x.v_4276_D();
                        c_4037_x.J_1907_R(2.0f, 2.0f, 2.0f);
                        int k2 = fontrenderer.n_1700_B((FormattedText)this.q_2307_F);
                        this.n_1700_B(matrixStack, fontrenderer, 5, k2, 0xFFFFFF | l1);
                        fontrenderer.n_1700_B(matrixStack, this.q_2307_F, (float)(-k2 / 2), 5.0f, 0xFFFFFF | l1);
                        c_4037_x.d_2461_k();
                    }
                    c_4037_x.Y_259_p();
                    c_4037_x.d_2461_k();
                }
                this.v_4262_N.PlayerInfo().R_4764_Y();
            }
            this.t_1786_h.n_1700_B(matrixStack);
            i_4895_l scoreboard = bot1 != null ? bot1.P_1922_E.G_564_y().Q_4569_t() : this.v_4262_N.Y_601_j.Q_4569_t();
            Objective scoreobjective = null;
            PlayerTeam scoreplayerteam = scoreboard.w_1484_f(bot1 != null ? bot1.P_1922_E.Q_2552_b.L_3570_A() : this.v_4262_N.Y_259_p.L_3570_A());
            if (scoreplayerteam != null && (j2 = scoreplayerteam.P_4830_p().n_1700_B()) >= 0) {
                scoreobjective = scoreboard.n_1700_B(3 + j2);
            }
            Objective w_3943_o = scoreobjective1 = scoreobjective != null ? scoreobjective : scoreboard.n_1700_B(1);
            if (scoreobjective1 != null) {
                this.n_1700_B(matrixStack, scoreobjective1);
            }
            c_4037_x.Y_601_j();
            c_4037_x.s_2632_s();
            c_4037_x.u_2550_I();
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y(0.0f, (float)(this.n_3318_d - 48), 0.0f);
            this.v_4262_N.PlayerInfo().n_1700_B("chat");
            this.t_148_a.n_1700_B(matrixStack, this.s_956_w);
            this.v_4262_N.PlayerInfo().R_4764_Y();
            c_4037_x.d_2461_k();
            scoreobjective1 = scoreboard.n_1700_B(0);
            int playerInfoSize = bot1 != null ? bot1.P_1922_E.P_1922_E().size() : this.v_4262_N.Y_259_p.n_1700_B.P_1922_E().size();
            boolean bl = wantsVisible = this.v_4262_N.P_4830_p.h_4320_q.G_564_y() && (!this.v_4262_N.x_607_J() || playerInfoSize > 1 || scoreobjective1 != null);
            if (wantsVisible) {
                this.Y_259_p.n_1700_B(true);
                this.Y_259_p.n_1700_B(matrixStack, this.e_4240_b, scoreboard, scoreobjective1);
            } else {
                this.Y_259_p.n_1700_B(false);
            }
        }
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        if (!this.v_4262_N.P_4830_p.RetryCallException && !this.v_4262_N.P_4830_p.r_3651_U) {
            this.v_4262_N.s_956_w.n_1700_B(2.0f);
            y_4842_Z.n_1700_B.n_1700_B(2.0f, 4);
            lightning.product.A_4115_X.n_1700_B(new b_3528_u(matrixStack, partialTicks));
            lightning.product.A_4115_X.n_1700_B(new b_3528_u.R_4764_Y(matrixStack, partialTicks));
            lightning.product.A_4115_X.n_1700_B(new b_3528_u.J_1907_R(matrixStack, partialTicks));
            lightning.product.A_4115_X.n_1700_B(new b_3528_u.G_564_y(matrixStack, partialTicks));
            this.v_4262_N.s_956_w.R_4764_Y();
        }
        c_4037_x.M_588_G();
    }

    private void n_1700_B(g_221_o p_238448_1_, Y_4083_F p_238448_2_, int p_238448_3_, int p_238448_4_, int p_238448_5_) {
        int i = this.v_4262_N.P_4830_p.J_1907_R(0.0f);
        if (i != 0) {
            int j = -p_238448_4_ / 2;
            u_1406_j.fill(p_238448_1_, j - 2, p_238448_3_ - 2, j + p_238448_4_ + 2, p_238448_3_ + 9 + 2, M_4239_y.n_1700_B.n_1700_B(i, p_238448_5_));
        }
    }

    private void G_564_y(g_221_o p_238456_1_) {
        V_4423_d gamesettings = this.v_4262_N.P_4830_p;
        if (gamesettings.P_4830_p().n_1700_B() && (this.v_4262_N.w_1457_N.getCurrentGameType() != I_14_v.P_1922_E || this.n_1700_B(this.v_4262_N.Z_875_P)) || ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(FreeCam.class).w_1484_f()) {
            if (gamesettings.r_3651_U && !gamesettings.RetryCallException && !this.v_4262_N.Y_259_p.y_3417_N() && !gamesettings.X_933_l) {
                c_4037_x.v_4276_D();
                c_4037_x.R_4764_Y((float)(this.e_4240_b / 2), (float)(this.n_3318_d / 2), (float)this.getBlitOffset());
                h_3572_K activerenderinfo = this.v_4262_N.s_956_w.M_588_G();
                c_4037_x.R_4764_Y(activerenderinfo.G_564_y(), -1.0f, 0.0f, 0.0f);
                c_4037_x.R_4764_Y(activerenderinfo.P_1922_E(), 0.0f, 1.0f, 0.0f);
                c_4037_x.J_1907_R(-1.0f, -1.0f, -1.0f);
                c_4037_x.M_588_G(10);
                c_4037_x.d_2461_k();
            } else {
                c_4037_x.n_1700_B(X_933_l.t_1786_h.t_148_a, X_933_l.s_956_w.u_2550_I, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.h_1847_R);
                int i = 15;
                this.blit(p_238456_1_, (this.e_4240_b - 15) / 2, (this.n_3318_d - 15) / 2, 0, 0, 15, 15);
                if (this.v_4262_N.P_4830_p.A_4115_X == E_4704_H.J_1907_R) {
                    float f = this.v_4262_N.Y_259_p.k_2293_S(0.0f);
                    boolean flag = false;
                    if (this.v_4262_N.q_2307_F != null && this.v_4262_N.q_2307_F instanceof r_4811_B && f >= 1.0f) {
                        flag = this.v_4262_N.Y_259_p.R_2822_N() > 5.0f;
                        flag &= this.v_4262_N.q_2307_F.RealmsLongRunningMcoTaskScreen();
                    }
                    int j = this.n_3318_d / 2 - 7 + 16;
                    int k = this.e_4240_b / 2 - 8;
                    if (flag) {
                        this.blit(p_238456_1_, k, j, 68, 94, 16, 16);
                    } else if (f < 1.0f) {
                        int l = (int)(f * 17.0f);
                        this.blit(p_238456_1_, k, j, 36, 94, 16, 4);
                        this.blit(p_238456_1_, k, j, 52, 94, l, 4);
                    }
                }
            }
        }
    }

    private boolean n_1700_B(HitResult rayTraceIn) {
        if (rayTraceIn == null) {
            return false;
        }
        if (rayTraceIn.R_4764_Y() == HitResult.n_1700_B.R_4764_Y) {
            return ((EntityHitResult)rayTraceIn).n_1700_B() instanceof t_3286_u;
        }
        if (rayTraceIn.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            c_1514_x blockpos = ((BlockHitResult)rayTraceIn).n_1700_B();
            n_1700_B activeBot = this.P_4830_p();
            b_4507_u world = activeBot != null ? activeBot.P_1922_E.G_564_y() : this.v_4262_N.Y_601_j;
            return world.getBlockState(blockpos).J_1907_R(world, blockpos) != null;
        }
        return false;
    }

    protected void n_1700_B(g_221_o matrixStack) {
        Collection<k_2610_C> collection;
        n_1700_B bot1 = this.P_4830_p();
        Collection<k_2610_C> collection2 = collection = bot1 != null ? this.v_4262_N.T_3594_S().I_3457_f() : this.v_4262_N.Y_259_p.I_3457_f();
        if (!collection.isEmpty()) {
            c_4037_x.Y_601_j();
            int i = 0;
            int j = 0;
            MobEffectTextureManager potionspriteuploader = this.v_4262_N.V_1446_Y();
            ArrayList list = Lists.newArrayListWithExpectedSize((int)collection.size());
            this.v_4262_N.G_624_v().n_1700_B(z_3427_G.w_1484_f);
            Iterator iterator = Ordering.natural().reverse().sortedCopy(collection).iterator();
            while (true) {
                if (!iterator.hasNext()) {
                    list.forEach(Runnable::run);
                    return;
                }
                k_2610_C effectinstance = (k_2610_C)iterator.next();
                g_422_i effect = effectinstance.n_1700_B();
                if (Reflector.IForgeEffectInstance_shouldRenderHUD.exists()) {
                    if (!Reflector.callBoolean(effectinstance, Reflector.IForgeEffectInstance_shouldRenderHUD, new Object[0])) continue;
                    this.v_4262_N.G_624_v().n_1700_B(z_3427_G.w_1484_f);
                }
                if (!effectinstance.u_1723_Y()) continue;
                int k = this.e_4240_b;
                int l = 1;
                if (this.v_4262_N.C_2741_M()) {
                    l += 15;
                }
                if (effect.w_1484_f()) {
                    k -= 25 * ++i;
                } else {
                    k -= 25 * ++j;
                    l += 26;
                }
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                float f = 1.0f;
                if (effectinstance.G_564_y()) {
                    this.blit(matrixStack, k, l, 165, 166, 24, 24);
                } else {
                    this.blit(matrixStack, k, l, 141, 166, 24, 24);
                    if (effectinstance.J_1907_R() <= 200) {
                        int i1 = 10 - effectinstance.J_1907_R() / 20;
                        f = u_530_F.n_1700_B((float)effectinstance.J_1907_R() / 10.0f / 5.0f * 0.5f, 0.0f, 0.5f) + u_530_F.J_1907_R((float)effectinstance.J_1907_R() * (float)Math.PI / 5.0f) * u_530_F.n_1700_B((float)i1 / 10.0f * 0.25f, 0.0f, 0.25f);
                    }
                }
                B_3871_I textureatlassprite = potionspriteuploader.n_1700_B(effect);
                int j1 = k;
                int k1 = l;
                float f1 = f;
                list.add(() -> {
                    this.v_4262_N.G_624_v().n_1700_B(textureatlassprite.u_2550_I().R_4764_Y());
                    c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, f1);
                    u_1406_j.blit(matrixStack, j1 + 3, k1 + 3, this.getBlitOffset(), 18, 18, textureatlassprite);
                });
                if (!Reflector.IForgeEffectInstance_renderHUDEffect.exists()) continue;
                Reflector.call(effectinstance, Reflector.IForgeEffectInstance_renderHUDEffect, this, matrixStack, k, l, this.getBlitOffset(), Float.valueOf(f));
            }
        }
    }

    protected void n_1700_B(float partialTicks, g_221_o matrixStack) {
        int i2;
        float offhandBoxX;
        V_772_m player = this.v_4262_N.Y_259_p;
        n_1700_B bot1 = this.P_4830_p();
        if (bot1 != null ? bot1.P_1922_E.Q_2552_b == null : player == null) {
            return;
        }
        Z_1993_T itemstack = bot1 != null ? bot1.P_1922_E.Q_2552_b.S_4035_N() : player.S_4035_N();
        k_4231_L handside = bot1 != null ? bot1.P_1922_E.Q_2552_b.d_2169_p().n_1700_B() : player.d_2169_p().n_1700_B();
        int i = this.e_4240_b / 2;
        int selectedSlot = bot1 != null ? bot1.P_1922_E.Q_2552_b.l_1268_F.G_564_y : player.l_1268_F.G_564_y;
        float panelX = (float)i - 91.0f;
        float panelY = (float)this.n_3318_d - 23.0f;
        float panelWidth = 182.0f;
        float panelHeight = 22.0f;
        float slotStartX = (float)i - 89.0f;
        float slotY = (float)this.n_3318_d - 21.0f;
        float slotSize = 18.0f;
        float slotStep = 20.0f;
        int themeBg = q_3148_R.n_1700_B(K_1200_E.C_2741_M);
        int themeOutline = q_3148_R.n_1700_B(K_1200_E.h_1847_R);
        int themeGlow = q_3148_R.n_1700_B(K_1200_E.q_2307_F);
        int themeAccent = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        float alphaBg = q_3148_R.J_1907_R(K_1200_E.C_2741_M) / 255.0f;
        float alphaOutline = q_3148_R.J_1907_R(K_1200_E.h_1847_R) / 255.0f;
        float alphaGlow = q_3148_R.J_1907_R(K_1200_E.q_2307_F) / 255.0f;
        int panelColor = H_2506_c.n_1700_B(themeBg, alphaBg * 0.95f);
        int slotColor = H_2506_c.n_1700_B(H_2506_c.J_1907_R(themeBg, 0.85f), Math.max(0.35f, alphaBg * 0.7f));
        int selectedColor = H_2506_c.n_1700_B(themeAccent, 0.45f);
        int outlineColor = H_2506_c.n_1700_B(themeOutline, Math.max(0.55f, alphaOutline));
        boolean useCustomHotbar = false;
        Interface interfaceModule = (Interface)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Interface.class);
        if (interfaceModule != null && interfaceModule.w_1484_f()) {
            Boolean customEnabled = Interface.t_148_a.J_1907_R("Custom Hotbar");
            useCustomHotbar = customEnabled == null || customEnabled != false;
        }
        k_4231_L primarySide = bot1 != null ? bot1.P_1922_E.Q_2552_b.d_2169_p() : player.d_2169_p();
        boolean offhandOnLeft = primarySide == k_4231_L.J_1907_R;
        float handBoxY = slotY;
        float leftHandBoxX = panelX - 20.0f;
        float rightHandBoxX = panelX + panelWidth + 2.0f;
        float f = offhandBoxX = offhandOnLeft ? leftHandBoxX : rightHandBoxX;
        if (useCustomHotbar) {
            F_489_x.n_1700_B(panelX - 8.0f, panelY - 8.0f, panelWidth + 16.0f, panelHeight + 16.0f, 5.0f, themeGlow, themeGlow, themeGlow, themeGlow, alphaGlow * 0.75f, 8.0f);
            F_489_x.n_1700_B(panelX, panelY, panelWidth, panelHeight, 4.0f, panelColor, 1.0f);
            F_489_x.J_1907_R(panelX, panelY, panelWidth, panelHeight, 4.0f, outlineColor, alphaOutline);
            for (int slot = 0; slot < 9; ++slot) {
                float x = slotStartX + (float)slot * slotStep;
                F_489_x.n_1700_B(x, slotY, slotSize, slotSize, 2.5f, slotColor, 1.0f);
                if (slot != selectedSlot) continue;
                F_489_x.n_1700_B(x - 4.0f, slotY - 4.0f, slotSize + 8.0f, slotSize + 8.0f, 3.0f, themeAccent, themeAccent, themeAccent, themeAccent, 0.5f, 6.0f);
                F_489_x.n_1700_B(x, slotY, slotSize, slotSize, 2.5f, selectedColor, 1.0f);
                F_489_x.J_1907_R(x, slotY, slotSize, slotSize, 2.5f, outlineColor, Math.max(0.65f, alphaOutline));
            }
            if (!itemstack.n_1700_B()) {
                float offhandPlateX = offhandBoxX - 2.0f;
                float offhandPlateY = panelY;
                float offhandPlateW = slotSize + 4.0f;
                float offhandPlateH = panelHeight;
                F_489_x.n_1700_B(offhandPlateX, offhandPlateY, offhandPlateW, offhandPlateH, 4.0f, panelColor, 1.0f);
                F_489_x.J_1907_R(offhandPlateX, offhandPlateY, offhandPlateW, offhandPlateH, 4.0f, outlineColor, Math.max(0.5f, alphaOutline));
                F_489_x.n_1700_B(offhandBoxX, handBoxY, slotSize, slotSize, 3.0f, slotColor, 1.0f);
                F_489_x.J_1907_R(offhandBoxX, handBoxY, slotSize, slotSize, 3.0f, outlineColor, Math.max(0.55f, alphaOutline));
            }
        } else {
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            this.v_4262_N.G_624_v().n_1700_B(R_4764_Y);
            int j = this.getBlitOffset();
            this.setBlitOffset(-90);
            this.blit(matrixStack, i - 91, this.n_3318_d - 22, 0, 0, 182, 22);
            this.blit(matrixStack, i - 91 - 1 + selectedSlot * 20, this.n_3318_d - 22 - 1, 0, 22, 24, 22);
            if (!itemstack.n_1700_B()) {
                if (handside == k_4231_L.n_1700_B) {
                    this.blit(matrixStack, i - 91 - 29, this.n_3318_d - 23, 24, 22, 29, 24);
                } else {
                    this.blit(matrixStack, i + 91, this.n_3318_d - 23, 53, 22, 29, 24);
                }
            }
            this.setBlitOffset(j);
        }
        c_4037_x.n_3318_d();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        CustomItems.setRenderOffHand(false);
        lightning.product.A_4115_X.n_1700_B(new l_2647_k(matrixStack, partialTicks));
        for (i2 = 0; i2 < 9; ++i2) {
            int j2 = i - 90 + i2 * 20 + 2;
            int k2 = this.n_3318_d - 16 - 3;
            this.n_1700_B(j2, k2, partialTicks, bot1 != null ? bot1.P_1922_E.Q_2552_b : player, bot1 != null ? this.v_4262_N.T_3594_S().l_1268_F.n_1700_B.get(i2) : player.l_1268_F.n_1700_B.get(i2));
        }
        if (useCustomHotbar) {
            if (!itemstack.n_1700_B()) {
                CustomItems.setRenderOffHand(true);
                this.n_1700_B((int)offhandBoxX + 1, (int)handBoxY + 1, partialTicks, bot1 != null ? bot1.P_1922_E.Q_2552_b : player, itemstack);
                CustomItems.setRenderOffHand(false);
            }
        } else if (!itemstack.n_1700_B()) {
            CustomItems.setRenderOffHand(true);
            i2 = this.n_3318_d - 16 - 3;
            if (handside == k_4231_L.n_1700_B) {
                this.n_1700_B(i - 91 - 26, i2, partialTicks, bot1 != null ? bot1.P_1922_E.Q_2552_b : player, itemstack);
            } else {
                this.n_1700_B(i + 91 + 10, i2, partialTicks, bot1 != null ? bot1.P_1922_E.Q_2552_b : player, itemstack);
            }
            CustomItems.setRenderOffHand(false);
        }
        if (this.v_4262_N.P_4830_p.A_4115_X == E_4704_H.R_4764_Y) {
            float f2;
            float f3 = f2 = bot1 != null ? bot1.P_1922_E.Q_2552_b.k_2293_S(0.0f) : player.k_2293_S(0.0f);
            if (f2 < 1.0f) {
                int j2 = this.n_3318_d - 20;
                int k2 = i + 91 + 6;
                if (handside == k_4231_L.J_1907_R) {
                    k2 = i - 91 - 22;
                }
                this.v_4262_N.G_624_v().n_1700_B(C_2701_A.GUI_ICONS_LOCATION);
                int l1 = (int)(f2 * 19.0f);
                c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
                this.blit(matrixStack, k2, j2, 0, 94, 18, 18);
                this.blit(matrixStack, k2, j2 + 18 - l1, 18, 112 - l1, 18, l1);
            }
        }
        c_4037_x.d_2427_y();
        c_4037_x.Y_259_p();
    }

    public void n_1700_B(g_221_o matrixStack, int xPosition) {
        this.v_4262_N.PlayerInfo().n_1700_B("jumpBar");
        this.v_4262_N.G_624_v().n_1700_B(C_2701_A.GUI_ICONS_LOCATION);
        float f = this.v_4262_N.Y_259_p.k_2293_S();
        int i = 182;
        int j = (int)(f * 183.0f);
        int k = this.n_3318_d - 32 + 3;
        this.blit(matrixStack, xPosition, k, 0, 84, 182, 5);
        if (j > 0) {
            this.blit(matrixStack, xPosition, k, 0, 89, j, 5);
        }
        this.v_4262_N.PlayerInfo().R_4764_Y();
    }

    public void J_1907_R(g_221_o p_238454_1_, int p_238454_2_) {
        X_4340_E expHolder;
        n_1700_B activeBot = this.P_4830_p();
        X_4340_E x_4340_E = expHolder = activeBot != null ? activeBot.P_1922_E.Q_2552_b : this.v_4262_N.Y_259_p;
        if (expHolder == null) {
            return;
        }
        this.v_4262_N.PlayerInfo().n_1700_B("expBar");
        this.v_4262_N.G_624_v().n_1700_B(C_2701_A.GUI_ICONS_LOCATION);
        int i = expHolder.f_2787_O();
        if (i > 0) {
            int j = 182;
            int k = (int)(expHolder.b_2312_j * 183.0f);
            int l = this.n_3318_d - 32 + 3;
            this.blit(p_238454_1_, p_238454_2_, l, 0, 64, 182, 5);
            if (k > 0) {
                this.blit(p_238454_1_, p_238454_2_, l, 0, 69, k, 5);
            }
        }
        this.v_4262_N.PlayerInfo().R_4764_Y();
        if (expHolder.v_165_F > 0) {
            this.v_4262_N.PlayerInfo().n_1700_B("expLevel");
            int j1 = 8453920;
            if (Config.isCustomColors()) {
                j1 = CustomColors.getExpBarTextColor(j1);
            }
            String s = "" + expHolder.v_165_F;
            int k1 = (this.e_4240_b - this.P_1922_E().J_1907_R(s)) / 2;
            int i1 = this.n_3318_d - 31 - 4;
            this.P_1922_E().J_1907_R(p_238454_1_, s, (float)(k1 + 1), (float)i1, 0);
            this.P_1922_E().J_1907_R(p_238454_1_, s, (float)(k1 - 1), (float)i1, 0);
            this.P_1922_E().J_1907_R(p_238454_1_, s, (float)k1, (float)(i1 + 1), 0);
            this.P_1922_E().J_1907_R(p_238454_1_, s, (float)k1, (float)(i1 - 1), 0);
            this.P_1922_E().J_1907_R(p_238454_1_, s, (float)k1, (float)i1, j1);
            this.v_4262_N.PlayerInfo().R_4764_Y();
        }
    }

    public void J_1907_R(g_221_o p_238453_1_) {
        this.v_4262_N.PlayerInfo().n_1700_B("selectedItemName");
        if (this.h_1847_R > 0 && !this.Q_4569_t.n_1700_B()) {
            int l;
            MutableComponent iformattabletextcomponent = new U_2871_b("").n_1700_B(this.Q_4569_t.multiplayerClientSuggestionProvider()).n_1700_B(this.Q_4569_t.Q_2552_b().P_1922_E);
            if (this.Q_4569_t.Y_601_j()) {
                iformattabletextcomponent.n_1700_B(D_4024_W.Y_259_p);
            }
            x_282_a itextcomponent = iformattabletextcomponent;
            if (Reflector.IForgeItemStack_getHighlightTip.exists()) {
                itextcomponent = (x_282_a)Reflector.call(this.Q_4569_t, Reflector.IForgeItemStack_getHighlightTip, iformattabletextcomponent);
            }
            int i = this.P_1922_E().n_1700_B((FormattedText)itextcomponent);
            int j = (this.e_4240_b - i) / 2;
            int k = this.n_3318_d - 59;
            if (!this.v_4262_N.w_1457_N.shouldDrawHUD()) {
                k += 14;
            }
            if ((l = (int)((float)this.h_1847_R * 256.0f / 10.0f)) > 255) {
                l = 255;
            }
            if (l > 0) {
                c_4037_x.v_4276_D();
                c_4037_x.Y_601_j();
                c_4037_x.s_2632_s();
                u_1406_j.fill(p_238453_1_, j - 2, k - 2, j + i + 2, k + 9 + 2, this.v_4262_N.P_4830_p.n_1700_B(0));
                Y_4083_F fontrenderer = null;
                if (Reflector.IForgeItem_getFontRenderer.exists()) {
                    fontrenderer = (Y_4083_F)Reflector.call(this.Q_4569_t.J_1907_R(), Reflector.IForgeItem_getFontRenderer, this.Q_4569_t);
                }
                if (fontrenderer != null) {
                    i = (this.e_4240_b - fontrenderer.n_1700_B((FormattedText)itextcomponent)) / 2;
                    fontrenderer.J_1907_R(p_238453_1_, itextcomponent.u_1723_Y(), (float)j, (float)k, 0xFFFFFF + (l << 24));
                } else {
                    this.P_1922_E().n_1700_B(p_238453_1_, itextcomponent, (float)j, (float)k, 0xFFFFFF + (l << 24));
                }
                c_4037_x.Y_259_p();
                c_4037_x.d_2461_k();
            }
        }
        this.v_4262_N.PlayerInfo().R_4764_Y();
    }

    public void R_4764_Y(g_221_o p_238455_1_) {
        this.v_4262_N.PlayerInfo().n_1700_B("demo");
        x_282_a itextcomponent = this.v_4262_N.Y_601_j.X_933_l() >= 120500L ? P_1922_E : new F_2904_S("demo.remainingTime", H_1468_N.n_1700_B((int)(120500L - this.v_4262_N.Y_601_j.X_933_l())));
        int i = this.P_1922_E().n_1700_B((FormattedText)itextcomponent);
        this.P_1922_E().n_1700_B(p_238455_1_, itextcomponent, (float)(this.e_4240_b - i - 10), 5.0f, 0xFFFFFF);
        this.v_4262_N.PlayerInfo().R_4764_Y();
    }

    private void n_1700_B(g_221_o p_238447_1_, Objective p_238447_2_) {
        int i;
        String protectedTitle;
        String titleText;
        i_4895_l scoreboard;
        List<Object> collection;
        List list;
        h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.R_4764_Y);
        lightning.product.A_4115_X.n_1700_B(event);
        if (event.n_1700_B()) {
            return;
        }
        if (this.v_4262_N.t_4043_B() == null || !this.v_4262_N.t_4043_B().J_1907_R.contains("bravohvh")) {
            v_4276_D = false;
        }
        collection = (list = (collection = (scoreboard = p_238447_2_.n_1700_B()).n_1700_B(p_238447_2_)).stream().filter(p_lambda$renderScoreboard$1_0_ -> p_lambda$renderScoreboard$1_0_.P_1922_E() != null && !p_lambda$renderScoreboard$1_0_.P_1922_E().startsWith("#")).collect(Collectors.toList())).size() > 15 ? Lists.newArrayList((Iterable)Iterables.skip(list, (int)(collection.size() - 15))) : list;
        ArrayList list1 = Lists.newArrayListWithCapacity((int)collection.size());
        x_282_a itextcomponent = p_238447_2_.G_564_y();
        NameProtect nameProtectTitle = (NameProtect)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(NameProtect.class);
        if (nameProtectTitle != null && nameProtectTitle.w_1484_f() && !(titleText = itextcomponent.getString()).equals(protectedTitle = NameProtect.P_1922_E(titleText))) {
            itextcomponent = new U_2871_b(protectedTitle).n_1700_B(itextcomponent.n_1700_B());
        }
        int j = i = this.P_1922_E().n_1700_B((FormattedText)itextcomponent);
        int k = this.P_1922_E().J_1907_R(": ");
        for (v_4839_y score : collection) {
            PlayerTeam scoreplayerteam = scoreboard.w_1484_f(score.P_1922_E());
            MutableComponent itextcomponent1 = PlayerTeam.n_1700_B(scoreplayerteam, new U_2871_b(score.P_1922_E()));
            list1.add(Pair.of((Object)score, (Object)itextcomponent1));
            j = Math.max(j, this.P_1922_E().n_1700_B((FormattedText)itextcomponent1) + k + this.P_1922_E().J_1907_R(Integer.toString(score.J_1907_R())));
        }
        int i2 = collection.size() * 9;
        int j2 = this.n_3318_d / 2 + i2 / 3;
        int k2 = 3;
        int l2 = this.e_4240_b - j - 3;
        int l = 0;
        int i1 = this.v_4262_N.P_4830_p.J_1907_R(0.3f);
        int j1 = this.v_4262_N.P_4830_p.J_1907_R(0.4f);
        for (Pair pair : list1) {
            ++l;
            v_4839_y score1 = (v_4839_y)pair.getFirst();
            x_282_a itextcomponent2 = (x_282_a)pair.getSecond();
            NameProtect nameProtect = (NameProtect)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(NameProtect.class);
            if (nameProtect != null && nameProtect.w_1484_f()) {
                String originalText = itextcomponent2.getString();
                String protectedText = NameProtect.G_564_y(originalText);
                if (!originalText.equals(protectedText = NameProtect.P_1922_E(protectedText))) {
                    itextcomponent2 = new U_2871_b(protectedText).n_1700_B(itextcomponent2.n_1700_B());
                }
            }
            String s = String.valueOf((Object)D_4024_W.P_4830_p) + score1.J_1907_R();
            int k1 = j2 - l * 9;
            int l1 = this.e_4240_b - 3 + 2;
            u_1406_j.fill(p_238447_1_, l2 - 2, k1, l1, k1 + 9, i1);
            this.P_1922_E().J_1907_R(p_238447_1_, itextcomponent2, (float)l2, (float)k1, -1);
            this.P_1922_E().J_1907_R(p_238447_1_, s, (float)(l1 - this.P_1922_E().J_1907_R(s)), (float)k1, -1);
            if (itextcomponent2.getString().contains("\u0422\u0438\u0442\u0443\u043b")) {
                try {
                    String fullText;
                    String[] parts;
                    if (this.v_4262_N.t_4043_B() != null && this.v_4262_N.t_4043_B().J_1907_R.contains("bravohvh") && (parts = (fullText = D_4024_W.n_1700_B(itextcomponent2.getString())).split(" ")).length > 4) {
                        String titul = parts[4].trim();
                        if (titul.equals("2d7c1") || titul.equals("POUCH")) {
                            v_4276_D = true;
                        } else {
                            if (v_4276_D) {
                                v_4276_D = false;
                            }
                            if (!titul.contains("En") && this.v_4262_N.Y_259_p != null && z_1737_N.J_1907_R(6000L)) {
                                this.v_4262_N.Y_259_p.n_1700_B("/titul set 2d7c1");
                                z_1737_N.n_1700_B();
                            }
                        }
                    }
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            if (l != collection.size()) continue;
            u_1406_j.fill(p_238447_1_, l2 - 2, k1 - 9 - 1, l1, k1 - 1, j1);
            u_1406_j.fill(p_238447_1_, l2 - 2, k1 - 1, l1, k1, i1);
            this.P_1922_E().J_1907_R(p_238447_1_, itextcomponent, (float)(l2 + j / 2 - i / 2), (float)(k1 - 9), -1);
        }
    }

    private a_3913_L h_1847_R() {
        return !(this.v_4262_N.g_2268_R() instanceof a_3913_L) ? null : (a_3913_L)this.v_4262_N.g_2268_R();
    }

    private r_4811_B Q_4569_t() {
        a_3913_L playerentity = this.h_1847_R();
        if (playerentity != null) {
            N_4263_v entity = playerentity.l_3609_d();
            if (entity == null) {
                return null;
            }
            if (entity instanceof r_4811_B) {
                return (r_4811_B)entity;
            }
        }
        return null;
    }

    private int n_1700_B(r_4811_B mountEntity) {
        if (mountEntity != null && mountEntity.RealmsResetNormalWorldScreen()) {
            float f = mountEntity.L_1733_J();
            int i = (int)(f + 0.5f) / 2;
            if (i > 30) {
                i = 30;
            }
            return i;
        }
        return 0;
    }

    private int n_1700_B(int mountHealth) {
        return (int)Math.ceil((double)mountHealth / 10.0);
    }

    private void P_1922_E(g_221_o p_238457_1_) {
        a_3913_L playerentity = this.h_1847_R();
        if (playerentity != null) {
            int i = u_530_F.u_1723_Y(playerentity.g_46_E());
            boolean flag = this.x_607_J > (long)this.s_956_w && (this.x_607_J - (long)this.s_956_w) / 3L % 2L == 1L;
            long j = j_3341_s.J_1907_R();
            if (i < this.A_4115_X && playerentity.F_1410_V > 0) {
                this.t_4043_B = j;
                this.x_607_J = this.s_956_w + 20;
            } else if (i > this.A_4115_X && playerentity.F_1410_V > 0) {
                this.t_4043_B = j;
                this.x_607_J = this.s_956_w + 10;
            }
            if (j - this.t_4043_B > 1000L) {
                this.A_4115_X = i;
                this.Y_1740_V = i;
                this.t_4043_B = j;
            }
            this.A_4115_X = i;
            int k = this.Y_1740_V;
            this.u_1723_Y.setSeed(this.s_956_w * 312871);
            FoodData foodstats = playerentity.P_2295_B();
            int l = foodstats.n_1700_B();
            int i1 = this.e_4240_b / 2 - 91;
            int j1 = this.e_4240_b / 2 + 91;
            int k1 = this.n_3318_d - 39;
            float f = (float)playerentity.J_1907_R(Attributes.n_1700_B);
            int l1 = u_530_F.u_1723_Y(playerentity.U_3823_u());
            int i2 = u_530_F.u_1723_Y((f + (float)l1) / 2.0f / 10.0f);
            int j2 = Math.max(10 - (i2 - 2), 3);
            int k2 = k1 - (i2 - 1) * j2 - 10;
            int l2 = k1 - 10;
            int i3 = l1;
            int j3 = playerentity.E_3343_g();
            h_3270_j healthEvent = new h_3270_j(h_3270_j.n_1700_B.M_182_A);
            lightning.product.A_4115_X.n_1700_B(healthEvent);
            int k3 = -1;
            if (!healthEvent.n_1700_B() && playerentity.J_1907_R(MobEffects.s_956_w)) {
                k3 = this.s_956_w % u_530_F.u_1723_Y(f + 5.0f);
            }
            this.v_4262_N.PlayerInfo().n_1700_B("armor");
            for (int l3 = 0; l3 < 10; ++l3) {
                if (j3 <= 0) continue;
                int i4 = i1 + l3 * 8;
                if (l3 * 2 + 1 < j3) {
                    this.blit(p_238457_1_, i4, k2, 34, 9, 9, 9);
                }
                if (l3 * 2 + 1 == j3) {
                    this.blit(p_238457_1_, i4, k2, 25, 9, 9, 9);
                }
                if (l3 * 2 + 1 <= j3) continue;
                this.blit(p_238457_1_, i4, k2, 16, 9, 9, 9);
            }
            this.v_4262_N.PlayerInfo().J_1907_R("health");
            for (int l5 = u_530_F.u_1723_Y((f + (float)l1) / 2.0f) - 1; l5 >= 0; --l5) {
                int i6 = 16;
                if (playerentity.J_1907_R(MobEffects.w_1457_N)) {
                    i6 += 36;
                } else if (playerentity.J_1907_R(MobEffects.Y_601_j)) {
                    i6 += 72;
                }
                int j4 = 0;
                if (flag) {
                    j4 = 1;
                }
                int k4 = u_530_F.u_1723_Y((float)(l5 + 1) / 10.0f) - 1;
                int l4 = i1 + l5 % 10 * 8;
                int i5 = k1 - k4 * j2;
                if (i <= 4) {
                    i5 += this.u_1723_Y.nextInt(2);
                }
                if (i3 <= 0 && l5 == k3) {
                    i5 -= 2;
                }
                int j5 = 0;
                if (playerentity.O_508_d.k_2293_S().n_1700_B()) {
                    j5 = 5;
                }
                this.blit(p_238457_1_, l4, i5, 16 + j4 * 9, 9 * j5, 9, 9);
                if (flag) {
                    if (l5 * 2 + 1 < k) {
                        this.blit(p_238457_1_, l4, i5, i6 + 54, 9 * j5, 9, 9);
                    }
                    if (l5 * 2 + 1 == k) {
                        this.blit(p_238457_1_, l4, i5, i6 + 63, 9 * j5, 9, 9);
                    }
                }
                if (i3 > 0) {
                    if (i3 == l1 && l1 % 2 == 1) {
                        this.blit(p_238457_1_, l4, i5, i6 + 153, 9 * j5, 9, 9);
                        --i3;
                        continue;
                    }
                    this.blit(p_238457_1_, l4, i5, i6 + 144, 9 * j5, 9, 9);
                    i3 -= 2;
                    continue;
                }
                if (l5 * 2 + 1 < i) {
                    this.blit(p_238457_1_, l4, i5, i6 + 36, 9 * j5, 9, 9);
                }
                if (l5 * 2 + 1 != i) continue;
                this.blit(p_238457_1_, l4, i5, i6 + 45, 9 * j5, 9, 9);
            }
            r_4811_B livingentity = this.Q_4569_t();
            int j6 = this.n_1700_B(livingentity);
            if (j6 == 0) {
                this.v_4262_N.PlayerInfo().J_1907_R("food");
                for (int k6 = 0; k6 < 10; ++k6) {
                    int i7 = k1;
                    int k7 = 16;
                    int i8 = 0;
                    if (playerentity.J_1907_R(MobEffects.t_1786_h)) {
                        k7 += 36;
                        i8 = 13;
                    }
                    if (playerentity.P_2295_B().R_4764_Y() <= 0.0f && this.s_956_w % (l * 3 + 1) == 0) {
                        i7 = k1 + (this.u_1723_Y.nextInt(3) - 1);
                    }
                    int k8 = j1 - k6 * 8 - 9;
                    this.blit(p_238457_1_, k8, i7, 16 + i8 * 9, 27, 9, 9);
                    if (k6 * 2 + 1 < l) {
                        this.blit(p_238457_1_, k8, i7, k7 + 36, 27, 9, 9);
                    }
                    if (k6 * 2 + 1 != l) continue;
                    this.blit(p_238457_1_, k8, i7, k7 + 45, 27, 9, 9);
                }
                if (!playerentity.G_624_v() && foodstats.n_1700_B() >= 20 && foodstats.R_4764_Y() >= 0.5f) {
                    this.v_4262_N.G_624_v().n_1700_B(C_2701_A.GUI_ICONS_LOCATION);
                    float saturation = foodstats.R_4764_Y();
                    int fullIcons = (int)(saturation / 2.0f);
                    boolean hasHalfIcon = saturation % 2.0f >= 1.0f;
                    int totalIcons = fullIcons + (hasHalfIcon || saturation < 1.0f ? 1 : 0);
                    totalIcons = Math.min(totalIcons, 10);
                    for (int idx = 0; idx < totalIcons; ++idx) {
                        int x = j1 - idx * 8 - 9;
                        this.blit(p_238457_1_, x, l2, 16, 27, 9, 9);
                        if (idx < fullIcons) {
                            this.blit(p_238457_1_, x, l2, 52, 27, 9, 9);
                            continue;
                        }
                        if (idx != fullIcons || !hasHalfIcon && !(saturation < 1.0f)) continue;
                        this.blit(p_238457_1_, x, l2, 61, 27, 9, 9);
                    }
                }
                l2 -= 10;
            }
            this.v_4262_N.PlayerInfo().J_1907_R("air");
            int l6 = playerentity.P_5000_x();
            int j7 = Math.min(playerentity.L_4248_u(), l6);
            if (((N_4263_v)playerentity).n_1700_B(FluidTags.J_1907_R) || j7 < l6) {
                int l7 = this.n_1700_B(j6) - 1;
                l2 -= l7 * 10;
                if (foodstats.R_4764_Y() >= 0.5f) {
                    l2 -= 8;
                }
                int j8 = u_530_F.P_1922_E((double)(j7 - 2) * 10.0 / (double)l6);
                int l8 = u_530_F.P_1922_E((double)j7 * 10.0 / (double)l6) - j8;
                for (int k5 = 0; k5 < j8 + l8; ++k5) {
                    if (k5 < j8) {
                        this.blit(p_238457_1_, j1 - k5 * 8 - 9, l2, 16, 18, 9, 9);
                        continue;
                    }
                    this.blit(p_238457_1_, j1 - k5 * 8 - 9, l2, 25, 18, 9, 9);
                }
            }
            this.v_4262_N.PlayerInfo().R_4764_Y();
        }
    }

    private void u_1723_Y(g_221_o p_238458_1_) {
        int i;
        r_4811_B livingentity = this.Q_4569_t();
        if (livingentity != null && (i = this.n_1700_B(livingentity)) != 0) {
            int j = (int)Math.ceil(livingentity.g_46_E());
            this.v_4262_N.PlayerInfo().J_1907_R("mountHealth");
            int k = this.n_3318_d - 39;
            int l = this.e_4240_b / 2 + 91;
            int i1 = k;
            int j1 = 0;
            boolean flag = false;
            while (i > 0) {
                int k1 = Math.min(i, 10);
                i -= k1;
                for (int l1 = 0; l1 < k1; ++l1) {
                    int i2 = 52;
                    int j2 = 0;
                    int k2 = l - l1 * 8 - 9;
                    this.blit(p_238458_1_, k2, i1, 52 + j2 * 9, 9, 9, 9);
                    if (l1 * 2 + 1 + j1 < j) {
                        this.blit(p_238458_1_, k2, i1, 88, 9, 9, 9);
                    }
                    if (l1 * 2 + 1 + j1 != j) continue;
                    this.blit(p_238458_1_, k2, i1, 97, 9, 9, 9);
                }
                i1 -= 10;
                j1 += 20;
            }
        }
    }

    private void M_182_A() {
        h_3270_j pumpkinEvent = new h_3270_j(h_3270_j.n_1700_B.M_588_G);
        lightning.product.A_4115_X.n_1700_B(pumpkinEvent);
        if (pumpkinEvent.n_1700_B()) {
            return;
        }
        c_4037_x.t_1786_h();
        c_4037_x.J_1907_R(false);
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.u_2550_I();
        this.v_4262_N.G_624_v().n_1700_B(G_564_y);
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.Q_2552_b);
        bufferbuilder.pos(0.0, this.n_3318_d, -90.0).tex(0.0f, 1.0f).endVertex();
        bufferbuilder.pos(this.e_4240_b, this.n_3318_d, -90.0).tex(1.0f, 1.0f).endVertex();
        bufferbuilder.pos(this.e_4240_b, 0.0, -90.0).tex(1.0f, 0.0f).endVertex();
        bufferbuilder.pos(0.0, 0.0, -90.0).tex(0.0f, 0.0f).endVertex();
        tessellator.J_1907_R();
        c_4037_x.J_1907_R(true);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.M_588_G();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void n_1700_B(N_4263_v entityIn) {
        if (entityIn != null) {
            float f = u_530_F.n_1700_B(1.0f - entityIn.RealmsConfirmScreen(), 0.0f, 1.0f);
            this.n_1700_B = (float)((double)this.n_1700_B + (double)(f - this.n_1700_B) * 0.01);
        }
    }

    private void t_1786_h() {
        if (ClientBootstrap.Y_601_j() == null || this.v_4262_N.Y_259_p == null) {
            return;
        }
        BetterMinecraft betterMinecraft = (BetterMinecraft)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(BetterMinecraft.class);
        if (betterMinecraft == null) {
            return;
        }
        float intensity = betterMinecraft.t_1786_h();
        if (intensity <= 0.0f) {
            return;
        }
        c_4037_x.t_1786_h();
        c_4037_x.J_1907_R(false);
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        float red = 1.0f;
        float green = 0.0f;
        float blue = 0.0f;
        float alpha = intensity * 0.4f;
        try {
            bufferbuilder.n_1700_B(7, E_688_b.Y_601_j);
            bufferbuilder.pos(0.0, this.n_3318_d, -90.0).n_1700_B(red, green, blue, alpha).endVertex();
            bufferbuilder.pos(this.e_4240_b, this.n_3318_d, -90.0).n_1700_B(red, green, blue, alpha).endVertex();
            bufferbuilder.pos(this.e_4240_b, 0.0, -90.0).n_1700_B(red, green, blue, alpha).endVertex();
            bufferbuilder.pos(0.0, 0.0, -90.0).n_1700_B(red, green, blue, alpha).endVertex();
            tessellator.J_1907_R();
        }
        catch (IllegalStateException illegalStateException) {
            // empty catch block
        }
        c_4037_x.x_607_J();
        c_4037_x.J_1907_R(true);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.Y_259_p();
    }

    private void J_1907_R(N_4263_v entityIn) {
        h_3270_j vignetteEvent = new h_3270_j(h_3270_j.n_1700_B.M_588_G);
        lightning.product.A_4115_X.n_1700_B(vignetteEvent);
        if (vignetteEvent.n_1700_B()) {
            return;
        }
        if (!Config.isVignetteEnabled()) {
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.h_1847_R);
        } else {
            T_603_v worldborder = this.v_4262_N.Y_601_j.H_2857_Y();
            float f = (float)worldborder.n_1700_B(entityIn);
            double d0 = Math.min(worldborder.M_182_A() * (double)worldborder.t_1786_h() * 1000.0, Math.abs(worldborder.u_2550_I() - worldborder.t_148_a()));
            double d1 = Math.max((double)worldborder.multiplayerClientSuggestionProvider(), d0);
            f = (double)f < d1 ? 1.0f - (float)((double)f / d1) : 0.0f;
            c_4037_x.t_1786_h();
            c_4037_x.J_1907_R(false);
            c_4037_x.n_1700_B(X_933_l.t_1786_h.Q_4569_t, X_933_l.s_956_w.u_2550_I, X_933_l.t_1786_h.P_1922_E, X_933_l.s_956_w.h_1847_R);
            if (f > 0.0f) {
                c_4037_x.G_564_y(0.0f, f, f, 1.0f);
            } else {
                c_4037_x.G_564_y(this.n_1700_B, this.n_1700_B, this.n_1700_B, 1.0f);
            }
            this.v_4262_N.G_624_v().n_1700_B(J_1907_R);
            l_3747_P tessellator = l_3747_P.n_1700_B();
            D_3318_r bufferbuilder = tessellator.R_4764_Y();
            bufferbuilder.n_1700_B(7, E_688_b.Q_2552_b);
            bufferbuilder.pos(0.0, this.n_3318_d, -90.0).tex(0.0f, 1.0f).endVertex();
            bufferbuilder.pos(this.e_4240_b, this.n_3318_d, -90.0).tex(1.0f, 1.0f).endVertex();
            bufferbuilder.pos(this.e_4240_b, 0.0, -90.0).tex(1.0f, 0.0f).endVertex();
            bufferbuilder.pos(0.0, 0.0, -90.0).tex(0.0f, 0.0f).endVertex();
            tessellator.J_1907_R();
            c_4037_x.J_1907_R(true);
            c_4037_x.multiplayerClientSuggestionProvider();
            c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
            c_4037_x.s_2632_s();
        }
    }

    private void n_1700_B(float timeInPortal) {
        if (timeInPortal < 1.0f) {
            timeInPortal *= timeInPortal;
            timeInPortal *= timeInPortal;
            timeInPortal = timeInPortal * 0.8f + 0.2f;
        }
        c_4037_x.u_2550_I();
        c_4037_x.t_1786_h();
        c_4037_x.J_1907_R(false);
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, timeInPortal);
        this.v_4262_N.G_624_v().n_1700_B(L_3848_p.n_1700_B);
        B_3871_I textureatlassprite = this.v_4262_N.z_1333_t().J_1907_R().n_1700_B(a_3742_W.M_766_z.multiplayerClientSuggestionProvider());
        float f = textureatlassprite.u_1723_Y();
        float f1 = textureatlassprite.w_1484_f();
        float f2 = textureatlassprite.v_4262_N();
        float f3 = textureatlassprite.t_148_a();
        l_3747_P tessellator = l_3747_P.n_1700_B();
        D_3318_r bufferbuilder = tessellator.R_4764_Y();
        bufferbuilder.n_1700_B(7, E_688_b.Q_2552_b);
        bufferbuilder.pos(0.0, this.n_3318_d, -90.0).tex(f, f3).endVertex();
        bufferbuilder.pos(this.e_4240_b, this.n_3318_d, -90.0).tex(f2, f3).endVertex();
        bufferbuilder.pos(this.e_4240_b, 0.0, -90.0).tex(f2, f1).endVertex();
        bufferbuilder.pos(0.0, 0.0, -90.0).tex(f, f1).endVertex();
        tessellator.J_1907_R();
        c_4037_x.J_1907_R(true);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.M_588_G();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public void n_1700_B(int x, int y, float partialTicks, a_3913_L player, Z_1993_T stack) {
        if (!stack.n_1700_B()) {
            float f = (float)stack.Y_1740_V() - partialTicks;
            if (f > 0.0f) {
                c_4037_x.v_4276_D();
                float f1 = 1.0f + f / 5.0f;
                c_4037_x.R_4764_Y((float)(x + 8), (float)(y + 12), 0.0f);
                c_4037_x.J_1907_R(1.0f / f1, (f1 + 1.0f) / 2.0f, 1.0f);
                c_4037_x.R_4764_Y((float)(-(x + 8)), (float)(-(y + 12)), 0.0f);
            }
            this.w_1484_f.n_1700_B(player, stack, x, y);
            if (f > 0.0f) {
                c_4037_x.d_2461_k();
            }
            this.w_1484_f.n_1700_B(this.v_4262_N.t_148_a, stack, x, y);
        }
    }

    public void J_1907_R() {
        n_1700_B bot1;
        k_4690_i world;
        n_1700_B activeBot = this.P_4830_p();
        b_4507_u b_4507_u2 = world = activeBot != null && activeBot.P_1922_E != null ? activeBot.P_1922_E.G_564_y() : this.v_4262_N.Y_601_j;
        if (world == null) {
            TextureAnimations.updateAnimations();
        }
        if (this.M_588_G > 0) {
            --this.M_588_G;
        }
        if (this.C_2741_M > 0) {
            --this.C_2741_M;
            if (this.C_2741_M <= 0) {
                this.k_2293_S = null;
                this.q_2307_F = null;
            }
        }
        ++this.s_956_w;
        N_4263_v entity = this.v_4262_N.g_2268_R();
        if (entity != null) {
            this.n_1700_B(entity);
        }
        if ((bot1 = this.P_4830_p()) != null ? this.v_4262_N.T_3594_S() == null : this.v_4262_N.Y_259_p == null) {
            return;
        }
        X_4340_E viewPlayer = bot1 != null ? this.v_4262_N.T_3594_S() : this.v_4262_N.Y_259_p;
        Z_1993_T itemstack = viewPlayer.l_1268_F.R_4764_Y();
        boolean flag = true;
        if (Reflector.IForgeItemStack_getHighlightTip.exists()) {
            x_282_a itextcomponent = (x_282_a)Reflector.call(itemstack, Reflector.IForgeItemStack_getHighlightTip, itemstack.multiplayerClientSuggestionProvider());
            x_282_a itextcomponent1 = (x_282_a)Reflector.call(this.Q_4569_t, Reflector.IForgeItemStack_getHighlightTip, this.Q_4569_t.multiplayerClientSuggestionProvider());
            flag = Config.equals(itextcomponent, itextcomponent1);
        }
        if (itemstack.n_1700_B()) {
            this.h_1847_R = 0;
        } else if (!this.Q_4569_t.n_1700_B() && itemstack.J_1907_R() == this.Q_4569_t.J_1907_R() && itemstack.multiplayerClientSuggestionProvider().equals(this.Q_4569_t.multiplayerClientSuggestionProvider()) && flag) {
            if (this.h_1847_R > 0) {
                --this.h_1847_R;
            }
        } else {
            this.h_1847_R = 40;
        }
        this.Q_4569_t = itemstack;
    }

    public void n_1700_B(x_282_a p_238451_1_) {
        this.n_1700_B(new F_2904_S("record.nowPlaying", p_238451_1_), true);
    }

    public void n_1700_B(x_282_a component, boolean animateColor) {
        this.u_2550_I = component;
        this.M_588_G = 60;
        this.P_4830_p = animateColor;
    }

    public void n_1700_B(@Nullable x_282_a p_238452_1_, @Nullable x_282_a p_238452_2_, int p_238452_3_, int p_238452_4_, int p_238452_5_) {
        if (p_238452_1_ == null && p_238452_2_ == null && p_238452_3_ < 0 && p_238452_4_ < 0 && p_238452_5_ < 0) {
            this.k_2293_S = null;
            this.q_2307_F = null;
            this.C_2741_M = 0;
        } else if (p_238452_1_ != null) {
            this.k_2293_S = p_238452_1_;
            this.C_2741_M = this.Z_875_P + this.c_3005_b + this.H_2857_Y;
        } else if (p_238452_2_ != null) {
            this.q_2307_F = p_238452_2_;
        } else {
            if (p_238452_3_ >= 0) {
                this.Z_875_P = p_238452_3_;
            }
            if (p_238452_4_ >= 0) {
                this.c_3005_b = p_238452_4_;
            }
            if (p_238452_5_ >= 0) {
                this.H_2857_Y = p_238452_5_;
            }
            if (this.C_2741_M > 0) {
                this.C_2741_M = this.Z_875_P + this.c_3005_b + this.H_2857_Y;
            }
        }
    }

    public UUID J_1907_R(x_282_a p_244795_1_) {
        String s = StringDecomposer.n_1700_B(p_244795_1_);
        String s1 = StringUtils.substringBetween((String)s, (String)"<", (String)">");
        return s1 == null ? j_3341_s.J_1907_R : this.v_4262_N.dtoRealmsServerAddress().n_1700_B(s1);
    }

    public void n_1700_B(Y_408_h p_238450_1_, x_282_a p_238450_2_, UUID p_238450_3_) {
        if (!(this.v_4262_N.n_1700_B(p_238450_3_) || this.v_4262_N.P_4830_p.z_1333_t && this.v_4262_N.n_1700_B(this.J_1907_R(p_238450_2_)))) {
            for (u_273_N ichatlistener : this.d_2427_y.get((Object)p_238450_1_)) {
                ichatlistener.n_1700_B(p_238450_1_, p_238450_2_, p_238450_3_);
            }
        }
    }

    public U_1085_u R_4764_Y() {
        return this.t_148_a;
    }

    public int G_564_y() {
        return this.s_956_w;
    }

    public Y_4083_F P_1922_E() {
        return this.v_4262_N.t_148_a;
    }

    public SpectatorGui u_1723_Y() {
        return this.Y_601_j;
    }

    public W_226_N v_4262_N() {
        return this.Y_259_p;
    }

    public void w_1484_f() {
        this.Y_259_p.G_564_y();
        this.Q_2552_b.R_4764_Y();
        this.v_4262_N.e_1992_r().n_1700_B();
    }

    public Y_3413_I t_148_a() {
        return this.Q_2552_b;
    }

    public void s_956_w() {
        this.M_182_A.n_1700_B();
    }

    @Generated
    public x_282_a u_2550_I() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Generated
    public x_282_a M_588_G() {
        return this.w_1457_N;
    }
}



