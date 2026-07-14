/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1098_v;
import lightning.product.D_3318_r;
import lightning.product.E_688_b;
import lightning.product.F_2052_z;
import lightning.product.F_3698_k;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.K_1200_E;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.X_933_l;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.h_2739_B;
import lightning.product.h_3572_K;
import lightning.product.Easing;
import lightning.product.Animation;
import lightning.product.ClientBootstrap;
import lightning.product.q_3148_R;
import lightning.product.AttackAura;
import lightning.product.r_4811_B;
import lightning.product.s_4405_m;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;
import lightning.product.z_283_n;

public class c_776_E
implements MinecraftAccess {
    private final Animation n_1700_B = new Animation(0.0f, 10.0f, Easing.M_588_G);
    private final Animation J_1907_R = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation R_4764_Y = new Animation(1.0f, 6.0f, Easing.u_1723_Y);
    private final Animation G_564_y = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation P_1922_E = new Animation(1.0f, 6.0f, Easing.u_1723_Y);
    private final Animation u_1723_Y = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation v_4262_N = new Animation(1.0f, 6.0f, Easing.u_1723_Y);
    private final Animation w_1484_f = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation t_148_a = new Animation(1.0f, 6.0f, Easing.u_1723_Y);
    private final Animation s_956_w = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation u_2550_I = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation M_588_G = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation P_4830_p = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation h_1847_R = new Animation(1.0f, 6.0f, Easing.u_1723_Y);
    private final Animation Q_4569_t = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private r_4811_B M_182_A;
    private static final g_2336_b t_1786_h = new g_2336_b("Pouch/icons/world_render/glow.png");
    private final Animation multiplayerClientSuggestionProvider = new Animation(0.0f, 6.0f, Easing.Y_601_j);
    private final Animation w_1457_N = new Animation(0.5f, 4.0f, Easing.Y_601_j);
    private final Animation Y_601_j = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation Y_259_p = new Animation(0.0f, 6.0f, Easing.u_1723_Y);
    private final Animation Q_2552_b = new Animation(0.0f, 8.0f, Easing.u_2550_I);
    private final Animation C_2741_M = new Animation(0.0f, 6.0f, Easing.M_588_G);
    private r_4811_B k_2293_S;
    private long q_2307_F;
    private float Z_875_P;
    private int t_4043_B = -1;
    private long x_607_J = 0L;
    private boolean e_4240_b = false;
    private N_4263_v n_3318_d;
    private N_4263_v d_2427_y;
    private N_4263_v z_1737_N;
    private e_2866_D v_4276_D;
    private e_2866_D d_2461_k;
    private final int G_624_v = 200;
    private static final float T_2506_i = 0.92f;
    private static final float q_4610_l = 0.72f;
    private static final float z_4693_k = 4.0f;
    private float g_221_o;
    private long e_2887_G;
    private float B_1668_F;
    private float g_164_R;
    private boolean X_933_l;
    private boolean Z_976_R;
    private double H_1990_U;
    private double N_2525_X;
    private long c_4037_x;
    private long g_2268_R;

    @Y_1740_V
    private void n_1700_B(I_4477_R event) {
        N_4263_v currentEntity;
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        e_2866_D cameraPosition = c_776_E.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        r_4811_B target = aura.w_1484_f() && aura.multiplayerClientSuggestionProvider().J_1907_R("\u0420\u043e\u043c\u0431") ? aura.h_1847_R() : null;
        boolean hasTarget = target != null;
        this.G_564_y.n_1700_B(hasTarget ? 1.0f : 0.0f);
        this.R_4764_Y.n_1700_B(hasTarget ? 1.0f : 2.0f);
        if (!hasTarget && this.G_564_y.n_1700_B() <= 0.01f) {
            this.X_933_l = false;
            this.d_2427_y = null;
            return;
        }
        e_2866_D entityPos = this.v_4276_D;
        float halfHeight = this.B_1668_F;
        N_4263_v n_4263_v = currentEntity = hasTarget ? target : this.d_2427_y;
        if (currentEntity != null && currentEntity.RealmsLongRunningMcoTaskScreen()) {
            entityPos = F_747_P.n_1700_B(currentEntity, event.J_1907_R());
            halfHeight = currentEntity.v_165_F() / 2.0f;
        } else if (this.v_4276_D == null || !this.X_933_l) {
            return;
        }
        if (hasTarget) {
            this.v_4276_D = entityPos;
            this.B_1668_F = halfHeight;
            this.X_933_l = true;
            this.d_2427_y = target;
        }
        g_221_o matrixStack = new g_221_o();
        matrixStack.n_1700_B(entityPos.J_1907_R - cameraPosition.J_1907_R, entityPos.R_4764_Y + (double)halfHeight - cameraPosition.R_4764_Y, entityPos.G_564_y - cameraPosition.G_564_y);
        float hurtFactor = hasTarget && target.i_2993_w > 0 ? u_530_F.n_1700_B((float)target.RealmsLongRunningMcoTaskScreen / (float)target.i_2993_w, 0.0f, 1.0f) : 0.0f;
        this.P_1922_E.n_1700_B(u_530_F.v_4262_N(hurtFactor, 1.0f, 0.25f));
        this.u_1723_Y.n_1700_B(hurtFactor);
        float size = this.R_4764_Y.n_1700_B() * this.P_1922_E.n_1700_B();
        float halfSize = size / 2.0f;
        double deltaTime = this.c_4037_x == 0L ? 0.0 : (double)((float)(System.currentTimeMillis() - this.c_4037_x) / 1000.0f);
        this.c_4037_x = System.currentTimeMillis();
        this.H_1990_U += 2.0 * (hasTarget ? 1.0 : 1.5) * deltaTime;
        matrixStack.n_1700_B();
        matrixStack.n_1700_B(c_776_E.c_3005_b.O_508_d().J_1907_R.u_1723_Y().v_4262_N());
        matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)(Math.sin(this.H_1990_U) * 180.0)));
        D_1098_v rotationMatrix = matrixStack.R_4764_Y().n_1700_B();
        matrixStack.J_1907_R();
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.x_607_J();
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.u_2550_I();
        lightning.product.c_4037_x.J_1907_R(770, 1, 0, 1);
        lightning.product.c_4037_x.w_1484_f(7425);
        lightning.product.c_4037_x.q_2307_F();
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.J_1907_R(false);
        lightning.product.c_4037_x.n_1700_B(516, 0.01f);
        int baseColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        int color = H_2506_c.J_1907_R(H_2506_c.n_1700_B(u_530_F.n_1700_B((int)((float)H_2506_c.n_1700_B(baseColor) * (1.0f - this.u_1723_Y.n_1700_B()) + 200.0f * this.u_1723_Y.n_1700_B()), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.J_1907_R(baseColor) * (1.0f - this.u_1723_Y.n_1700_B())), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.R_4764_Y(baseColor) * (1.0f - this.u_1723_Y.n_1700_B())), 0, 255), (int)(q_3148_R.J_1907_R(K_1200_E.J_1907_R) * u_530_F.n_1700_B(this.G_564_y.n_1700_B(), 0.0f, 1.0f))), 45);
        int r = H_2506_c.n_1700_B(color);
        int g = H_2506_c.J_1907_R(color);
        int b = H_2506_c.R_4764_Y(color);
        int a = H_2506_c.G_564_y(color);
        c_3005_b.G_624_v().n_1700_B(new g_2336_b("Pouch/icons/world_render/target.png"));
        A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
        A_4115_X.n_1700_B(rotationMatrix, -halfSize, -halfSize + size, 0.0f).tex(0.0f, 1.0f).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(rotationMatrix, halfSize, -halfSize + size, 0.0f).tex(1.0f, 1.0f).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(rotationMatrix, halfSize, -halfSize, 0.0f).tex(1.0f, 0.0f).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(rotationMatrix, -halfSize, -halfSize, 0.0f).tex(0.0f, 0.0f).color(r, g, b, a).endVertex();
        Y_1740_V.J_1907_R();
        lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
        lightning.product.c_4037_x.J_1907_R(true);
        lightning.product.c_4037_x.w_1484_f(7424);
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        lightning.product.c_4037_x.M_588_G();
        lightning.product.c_4037_x.k_2293_S();
        lightning.product.c_4037_x.d_2461_k();
    }

    @Y_1740_V
    private void J_1907_R(I_4477_R event) {
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        boolean enabled = aura.w_1484_f() && aura.multiplayerClientSuggestionProvider().J_1907_R("\u041f\u0440\u0438\u0437\u0440\u0430\u043a\u0438");
        r_4811_B target = enabled ? aura.h_1847_R() : null;
        boolean alive = target != null && target.RealmsLongRunningMcoTaskScreen();
        this.n_1700_B.n_1700_B(enabled && alive ? 1.0f : 0.0f);
        this.J_1907_R.n_1700_B(enabled && alive ? 1.0f : 0.0f);
        if (this.n_1700_B.n_1700_B() <= 0.01f && this.J_1907_R.n_1700_B() <= 0.01f) {
            this.n_3318_d = null;
            this.g_221_o = 0.0f;
            this.e_2887_G = 0L;
            return;
        }
        if (alive) {
            if (this.n_3318_d == null) {
                this.e_2887_G = System.currentTimeMillis();
            }
            this.n_3318_d = target;
        }
        if (this.n_3318_d == null) {
            return;
        }
        this.g_221_o += 4.0f * (float)(System.currentTimeMillis() - this.e_2887_G) / 600.0f;
        this.e_2887_G = System.currentTimeMillis();
        e_2866_D cameraPosition = c_776_E.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        double x = this.n_3318_d.q_1982_R + (this.n_3318_d.O_3598_v() - this.n_3318_d.q_1982_R) * (double)event.J_1907_R() - cameraPosition.J_1907_R;
        double y = this.n_3318_d.dtoRealmsServerAddress + (this.n_3318_d.X_2960_b() - this.n_3318_d.dtoRealmsServerAddress) * (double)event.J_1907_R() - cameraPosition.R_4764_Y;
        double z = this.n_3318_d.w_612_n + (this.n_3318_d.l_2647_k() - this.n_3318_d.w_612_n) * (double)event.J_1907_R() - cameraPosition.G_564_y;
        float rawHurt = alive && target.i_2993_w > 0 ? u_530_F.n_1700_B((float)target.RealmsLongRunningMcoTaskScreen / (float)target.i_2993_w, 0.0f, 1.0f) : 0.0f;
        this.u_2550_I.n_1700_B(rawHurt);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.x_607_J();
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.u_2550_I();
        lightning.product.c_4037_x.J_1907_R(770, 1, 0, 1);
        lightning.product.c_4037_x.w_1484_f(7425);
        lightning.product.c_4037_x.q_2307_F();
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.J_1907_R(false);
        lightning.product.c_4037_x.n_1700_B(516, 0.01f);
        c_3005_b.G_624_v().n_1700_B(new g_2336_b("Pouch/icons/world_render/glow.png"));
        A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
        int baseColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        int color = H_2506_c.n_1700_B(u_530_F.n_1700_B((int)((float)H_2506_c.n_1700_B(baseColor) * (1.0f - this.u_2550_I.n_1700_B()) + 200.0f * this.u_2550_I.n_1700_B()), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.J_1907_R(baseColor) * (1.0f - this.u_2550_I.n_1700_B())), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.R_4764_Y(baseColor) * (1.0f - this.u_2550_I.n_1700_B())), 0, 255), H_2506_c.G_564_y(baseColor));
        for (int ringLayer = 0; ringLayer < 9; ringLayer += 3) {
            for (int spriteIndex = 0; spriteIndex < 12; ++spriteIndex) {
                g_221_o stack = new g_221_o();
                stack.n_1700_B(x + (double)(0.8f * u_530_F.n_1700_B(this.g_221_o + (float)spriteIndex * 0.1f + (float)((int)Math.pow(ringLayer, 2.0)))), y + 0.5 + (double)(0.3f * u_530_F.n_1700_B(this.g_221_o + (float)spriteIndex * 0.2f)) + (double)(0.2f * (float)ringLayer), z + (double)(0.8f * u_530_F.J_1907_R(this.g_221_o + (float)spriteIndex * 0.1f - (float)((int)Math.pow(ringLayer, 2.0)))));
                float spriteScale = this.J_1907_R.n_1700_B() * (0.0035f + (float)spriteIndex / 2000.0f);
                stack.n_1700_B(spriteScale, spriteScale, spriteScale);
                stack.n_1700_B(c_776_E.c_3005_b.O_508_d().J_1907_R.u_1723_Y().v_4262_N());
                int spriteColor = H_2506_c.n_1700_B(color, (int)(this.J_1907_R.n_1700_B() * 255.0f));
                int r = H_2506_c.n_1700_B(spriteColor);
                int g = H_2506_c.J_1907_R(spriteColor);
                int b = H_2506_c.R_4764_Y(spriteColor);
                int a = H_2506_c.G_564_y(spriteColor);
                A_4115_X.n_1700_B(stack.R_4764_Y().n_1700_B(), -25.0f, 25.0f, 0.0f).tex(0.0f, 1.0f).color(r, g, b, a).endVertex();
                A_4115_X.n_1700_B(stack.R_4764_Y().n_1700_B(), 25.0f, 25.0f, 0.0f).tex(1.0f, 1.0f).color(r, g, b, a).endVertex();
                A_4115_X.n_1700_B(stack.R_4764_Y().n_1700_B(), 25.0f, -25.0f, 0.0f).tex(1.0f, 0.0f).color(r, g, b, a).endVertex();
                A_4115_X.n_1700_B(stack.R_4764_Y().n_1700_B(), -25.0f, -25.0f, 0.0f).tex(0.0f, 0.0f).color(r, g, b, a).endVertex();
            }
        }
        Y_1740_V.J_1907_R();
        lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
        lightning.product.c_4037_x.J_1907_R(true);
        lightning.product.c_4037_x.w_1484_f(7424);
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        lightning.product.c_4037_x.M_588_G();
        lightning.product.c_4037_x.k_2293_S();
        lightning.product.c_4037_x.d_2461_k();
    }

    @Y_1740_V
    private void R_4764_Y(I_4477_R event) {
        double angleRadians;
        int ringColor;
        int gradientColor;
        int angleDegree;
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        if (!aura.w_1484_f() || !aura.multiplayerClientSuggestionProvider().J_1907_R("\u041a\u043e\u043b\u044c\u0446\u043e")) {
            return;
        }
        r_4811_B target = aura.h_1847_R();
        if (target == null) {
            return;
        }
        float rawHurt = target.i_2993_w > 0 ? u_530_F.n_1700_B((float)target.RealmsLongRunningMcoTaskScreen / (float)target.i_2993_w, 0.0f, 1.0f) : 0.0f;
        this.M_588_G.n_1700_B(rawHurt);
        float radius = target.C_415_h() * 0.8f;
        e_2866_D targetPosition = F_747_P.n_1700_B((N_4263_v)target, event.J_1907_R());
        double duration = 2000.0;
        double elapsedMillis = (double)System.currentTimeMillis() % duration;
        double progress = elapsedMillis / (duration / 2.0);
        progress = elapsedMillis > duration / 2.0 ? progress - 1.0 : 1.0 - progress;
        progress = progress < 0.5 ? 2.0 * progress * progress : 1.0 - Math.pow(-2.0 * progress + 2.0, 2.0) / 2.0;
        g_221_o stack = new g_221_o();
        e_2866_D cameraPosition = c_776_E.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.e_4240_b();
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.u_2550_I();
        lightning.product.c_4037_x.w_1484_f(7425);
        lightning.product.c_4037_x.q_2307_F();
        lightning.product.c_4037_x.J_1907_R(false);
        lightning.product.c_4037_x.G_564_y(2.0f);
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
        A_4115_X.n_1700_B(8, E_688_b.Y_601_j);
        for (angleDegree = 0; angleDegree <= 360; ++angleDegree) {
            gradientColor = H_2506_c.J_1907_R(10, angleDegree * 5, q_3148_R.n_1700_B(K_1200_E.J_1907_R), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.J_1907_R), 0.5f));
            ringColor = H_2506_c.n_1700_B(u_530_F.n_1700_B((int)((float)H_2506_c.n_1700_B(gradientColor) * (1.0f - this.M_588_G.n_1700_B()) + 200.0f * this.M_588_G.n_1700_B()), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.J_1907_R(gradientColor) * (1.0f - this.M_588_G.n_1700_B())), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.R_4764_Y(gradientColor) * (1.0f - this.M_588_G.n_1700_B())), 0, 255), 255);
            angleRadians = Math.toRadians(angleDegree);
            double cos = Math.cos(angleRadians);
            double sin = Math.sin(angleRadians);
            double heightOffset = (double)(target.v_165_F() / 2.0f) * (progress > 0.5 ? 1.0 - progress : progress) * (double)(elapsedMillis > duration / 2.0 ? -1 : 1);
            A_4115_X.n_1700_B(stack.R_4764_Y().n_1700_B(), (float)(targetPosition.J_1907_R + cos * (double)radius - cameraPosition.J_1907_R), (float)(targetPosition.R_4764_Y + (double)target.v_165_F() * progress - cameraPosition.R_4764_Y), (float)(targetPosition.G_564_y + sin * (double)radius - cameraPosition.G_564_y)).n_1700_B(ringColor).endVertex();
            A_4115_X.n_1700_B(stack.R_4764_Y().n_1700_B(), (float)(targetPosition.J_1907_R + cos * (double)radius - cameraPosition.J_1907_R), (float)(targetPosition.R_4764_Y + (double)target.v_165_F() * progress + heightOffset - cameraPosition.R_4764_Y), (float)(targetPosition.G_564_y + sin * (double)radius - cameraPosition.G_564_y)).n_1700_B(H_2506_c.n_1700_B(ringColor, 0)).endVertex();
        }
        Y_1740_V.J_1907_R();
        A_4115_X.n_1700_B(2, E_688_b.Y_601_j);
        for (angleDegree = 0; angleDegree <= 360; ++angleDegree) {
            gradientColor = H_2506_c.J_1907_R(10, angleDegree * 5, q_3148_R.n_1700_B(K_1200_E.J_1907_R), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.J_1907_R), 0.5f));
            ringColor = H_2506_c.n_1700_B(u_530_F.n_1700_B((int)((float)H_2506_c.n_1700_B(gradientColor) * (1.0f - this.M_588_G.n_1700_B()) + 200.0f * this.M_588_G.n_1700_B()), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.J_1907_R(gradientColor) * (1.0f - this.M_588_G.n_1700_B())), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.R_4764_Y(gradientColor) * (1.0f - this.M_588_G.n_1700_B())), 0, 255), 255);
            angleRadians = Math.toRadians(angleDegree);
            A_4115_X.n_1700_B(stack.R_4764_Y().n_1700_B(), (float)(targetPosition.J_1907_R + Math.cos(angleRadians) * (double)radius - cameraPosition.J_1907_R), (float)(targetPosition.R_4764_Y + (double)target.v_165_F() * progress - cameraPosition.R_4764_Y), (float)(targetPosition.G_564_y + Math.sin(angleRadians) * (double)radius - cameraPosition.G_564_y)).n_1700_B(ringColor).endVertex();
        }
        Y_1740_V.J_1907_R();
        lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        lightning.product.c_4037_x.k_2293_S();
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.x_607_J();
        lightning.product.c_4037_x.M_588_G();
        lightning.product.c_4037_x.w_1484_f(7424);
        lightning.product.c_4037_x.J_1907_R(true);
        lightning.product.c_4037_x.d_2461_k();
    }

    @Y_1740_V
    private void G_564_y(I_4477_R event) {
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        if (!aura.w_1484_f() || !aura.multiplayerClientSuggestionProvider().J_1907_R("Triangle")) {
            return;
        }
        r_4811_B target = aura.h_1847_R();
        if (target == null || !target.RealmsLongRunningMcoTaskScreen()) {
            return;
        }
        e_2866_D targetPos = F_747_P.n_1700_B((N_4263_v)target, event.J_1907_R());
        e_2866_D cameraPos = c_776_E.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        h_3572_K camera = c_776_E.c_3005_b.O_508_d().J_1907_R;
        float hurt = target.i_2993_w > 0 ? u_530_F.n_1700_B((float)target.RealmsLongRunningMcoTaskScreen / (float)target.i_2993_w, 0.0f, 1.0f) : 0.0f;
        float anim = 1.0f;
        float time = (float)(System.currentTimeMillis() % 120000L) / 1000.0f;
        float distance = (float)camera.J_1907_R().u_1723_Y(targetPos);
        float perspectiveScale = u_530_F.n_1700_B(distance / 4.0f, 0.45f, 2.35f);
        float width = 0.92f * perspectiveScale;
        float height = 0.72f * perspectiveScale;
        float verticalBob = (float)Math.sin(time * 4.8f) * 0.05f * perspectiveScale;
        float roll = (float)Math.sin(time * 2.4f) * 1.25f;
        g_221_o ms = new g_221_o();
        ms.n_1700_B(targetPos.J_1907_R - cameraPos.J_1907_R, targetPos.R_4764_Y + (double)target.v_165_F() + (double)0.4f + (double)verticalBob - cameraPos.R_4764_Y, targetPos.G_564_y - cameraPos.G_564_y);
        ms.n_1700_B(new w_3785_E(M_1336_P.G_564_y, -camera.P_1922_E(), true));
        ms.n_1700_B(new w_3785_E(M_1336_P.J_1907_R, camera.G_564_y(), true));
        ms.n_1700_B(new w_3785_E(M_1336_P.u_1723_Y, roll, true));
        ms.n_1700_B(0.0, (double)(height * 0.47f), 0.0);
        int c0 = this.n_1700_B(0.0f, hurt);
        int c1 = this.n_1700_B(120.0f, hurt);
        int c2 = this.n_1700_B(240.0f, hurt);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.q_2307_F();
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.J_1907_R(false);
        this.n_1700_B(ms, width, height, anim, hurt, c0, c1, c2);
        this.n_1700_B(ms, width, height, anim, c0, c1, c2);
        lightning.product.c_4037_x.J_1907_R(true);
        lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
        lightning.product.c_4037_x.k_2293_S();
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.s_2632_s();
        lightning.product.c_4037_x.d_2461_k();
    }

    private int n_1700_B(float offset, float hurt) {
        int base = H_2506_c.J_1907_R(10, (int)offset, q_3148_R.n_1700_B(K_1200_E.J_1907_R), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.J_1907_R), 0.5f));
        int r = u_530_F.n_1700_B((int)((float)H_2506_c.n_1700_B(base) * (1.0f - hurt) + 200.0f * hurt), 0, 255);
        int g = u_530_F.n_1700_B((int)((float)H_2506_c.J_1907_R(base) * (1.0f - hurt)), 0, 255);
        int b = u_530_F.n_1700_B((int)((float)H_2506_c.R_4764_Y(base) * (1.0f - hurt)), 0, 255);
        return H_2506_c.n_1700_B(r, g, b, 255);
    }

    private void n_1700_B(g_221_o ms, int color, float width, float height, float anim) {
        c_3005_b.G_624_v().n_1700_B(t_1786_h);
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
        D_1098_v matrix = ms.R_4764_Y().n_1700_B();
        float bloomWidth = width * 2.15f;
        float bloomHeight = height * 1.85f;
        int bloomColor = H_2506_c.n_1700_B(color, u_530_F.n_1700_B((int)(92.0f * anim), 0, 135));
        int r = H_2506_c.n_1700_B(bloomColor);
        int g = H_2506_c.J_1907_R(bloomColor);
        int b = H_2506_c.R_4764_Y(bloomColor);
        int a = H_2506_c.G_564_y(bloomColor);
        A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
        A_4115_X.n_1700_B(matrix, -bloomWidth / 2.0f, -bloomHeight / 2.0f, -0.01f).tex(0.0f, 1.0f).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, bloomWidth / 2.0f, -bloomHeight / 2.0f, -0.01f).tex(1.0f, 1.0f).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, bloomWidth / 2.0f, bloomHeight / 2.0f, -0.01f).tex(1.0f, 0.0f).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, -bloomWidth / 2.0f, bloomHeight / 2.0f, -0.01f).tex(0.0f, 0.0f).color(r, g, b, a).endVertex();
        Y_1740_V.J_1907_R();
    }

    private void n_1700_B(g_221_o ms, float width, float height, float anim, float hurt, int c0, int c1, int c2) {
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        D_1098_v matrix = ms.R_4764_Y().n_1700_B();
        float halfWidth = width * 0.5f;
        float halfHeight = height * 0.5f;
        int bottomLeft = H_2506_c.n_1700_B(c0, c2, 0.65f);
        int bottomRight = H_2506_c.n_1700_B(c1, c2, 0.65f);
        int a = u_530_F.n_1700_B((int)(anim * 255.0f), 0, 255);
        if (s_4405_m.x_607_J.n_1700_B()) {
            s_4405_m.x_607_J.J_1907_R();
            s_4405_m.x_607_J.J_1907_R("Time", (float)(System.currentTimeMillis() % 120000L) / 1000.0f);
            s_4405_m.x_607_J.J_1907_R("Alpha", anim);
            s_4405_m.x_607_J.J_1907_R("Hurt", u_530_F.n_1700_B(hurt, 0.0f, 1.0f));
            A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
            A_4115_X.n_1700_B(matrix, -halfWidth, -halfHeight, 0.0f).tex(0.0f, 1.0f).color(H_2506_c.n_1700_B(bottomLeft), H_2506_c.J_1907_R(bottomLeft), H_2506_c.R_4764_Y(bottomLeft), a).endVertex();
            A_4115_X.n_1700_B(matrix, halfWidth, -halfHeight, 0.0f).tex(1.0f, 1.0f).color(H_2506_c.n_1700_B(bottomRight), H_2506_c.J_1907_R(bottomRight), H_2506_c.R_4764_Y(bottomRight), a).endVertex();
            A_4115_X.n_1700_B(matrix, halfWidth, halfHeight, 0.0f).tex(1.0f, 0.0f).color(H_2506_c.n_1700_B(c1), H_2506_c.J_1907_R(c1), H_2506_c.R_4764_Y(c1), a).endVertex();
            A_4115_X.n_1700_B(matrix, -halfWidth, halfHeight, 0.0f).tex(0.0f, 0.0f).color(H_2506_c.n_1700_B(c0), H_2506_c.J_1907_R(c0), H_2506_c.R_4764_Y(c0), a).endVertex();
            Y_1740_V.J_1907_R();
            s_4405_m.x_607_J.R_4764_Y();
            return;
        }
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        A_4115_X.n_1700_B(matrix, -halfWidth, -halfHeight, 0.0f).color(H_2506_c.n_1700_B(bottomLeft), H_2506_c.J_1907_R(bottomLeft), H_2506_c.R_4764_Y(bottomLeft), a).endVertex();
        A_4115_X.n_1700_B(matrix, halfWidth, -halfHeight, 0.0f).color(H_2506_c.n_1700_B(bottomRight), H_2506_c.J_1907_R(bottomRight), H_2506_c.R_4764_Y(bottomRight), a).endVertex();
        A_4115_X.n_1700_B(matrix, halfWidth, halfHeight, 0.0f).color(H_2506_c.n_1700_B(c1), H_2506_c.J_1907_R(c1), H_2506_c.R_4764_Y(c1), a).endVertex();
        A_4115_X.n_1700_B(matrix, -halfWidth, halfHeight, 0.0f).color(H_2506_c.n_1700_B(c0), H_2506_c.J_1907_R(c0), H_2506_c.R_4764_Y(c0), a).endVertex();
        Y_1740_V.J_1907_R();
    }

    private void n_1700_B(g_221_o ms, float width, float height, float anim, int c0, int c1, int c2) {
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
        D_1098_v matrix = ms.R_4764_Y().n_1700_B();
        float halfWidth = width * 0.45f;
        float top = height * 0.44f;
        float apex = -height * 0.47f;
        int leftColor = H_2506_c.n_1700_B(H_2506_c.n_1700_B(c0, -1, 0.25f), (int)(anim * 255.0f));
        int rightColor = H_2506_c.n_1700_B(H_2506_c.n_1700_B(c1, -1, 0.25f), (int)(anim * 255.0f));
        int apexColor = H_2506_c.n_1700_B(H_2506_c.n_1700_B(c2, -1, 0.32f), (int)(anim * 255.0f));
        A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
        this.n_1700_B(matrix, -halfWidth, top, 0.01f, halfWidth, top, 0.01f, leftColor, rightColor);
        this.n_1700_B(matrix, halfWidth, top, 0.01f, 0.0f, apex, 0.01f, rightColor, apexColor);
        this.n_1700_B(matrix, 0.0f, apex, 0.01f, -halfWidth, top, 0.01f, apexColor, leftColor);
        Y_1740_V.J_1907_R();
    }

    private void n_1700_B(D_1098_v matrix, float x1, float y1, float z1, float x2, float y2, float z2, int colorStart, int colorEnd) {
        A_4115_X.n_1700_B(matrix, x1, y1, z1).color(H_2506_c.n_1700_B(colorStart), H_2506_c.J_1907_R(colorStart), H_2506_c.R_4764_Y(colorStart), H_2506_c.G_564_y(colorStart)).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y2, z2).color(H_2506_c.n_1700_B(colorEnd), H_2506_c.J_1907_R(colorEnd), H_2506_c.R_4764_Y(colorEnd), H_2506_c.G_564_y(colorEnd)).endVertex();
    }

    @Y_1740_V
    private void P_1922_E(I_4477_R event) {
        N_4263_v currentEntity;
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        e_2866_D cameraPosition = c_776_E.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        r_4811_B target = aura.w_1484_f() && aura.multiplayerClientSuggestionProvider().J_1907_R("\u0420\u043e\u043c\u0431 New") ? aura.h_1847_R() : null;
        boolean hasTarget = target != null;
        this.w_1484_f.n_1700_B(hasTarget ? 1.0f : 0.0f);
        this.v_4262_N.n_1700_B(hasTarget ? 2.0f : 4.0f);
        if (!hasTarget && this.w_1484_f.n_1700_B() <= 0.01f) {
            this.Z_976_R = false;
            this.z_1737_N = null;
            return;
        }
        e_2866_D entityPos = this.d_2461_k;
        float halfHeight = this.g_164_R;
        N_4263_v n_4263_v = currentEntity = hasTarget ? target : this.z_1737_N;
        if (currentEntity != null && currentEntity.RealmsLongRunningMcoTaskScreen()) {
            entityPos = F_747_P.n_1700_B(currentEntity, event.J_1907_R());
            halfHeight = currentEntity.v_165_F() / 2.0f;
        } else if (this.d_2461_k == null || !this.Z_976_R) {
            return;
        }
        if (hasTarget) {
            this.d_2461_k = entityPos;
            this.g_164_R = halfHeight;
            this.Z_976_R = true;
            this.z_1737_N = target;
        }
        g_221_o matrixStack = new g_221_o();
        matrixStack.n_1700_B(entityPos.J_1907_R - cameraPosition.J_1907_R, entityPos.R_4764_Y + (double)halfHeight - cameraPosition.R_4764_Y, entityPos.G_564_y - cameraPosition.G_564_y);
        float hurtFactor = hasTarget && target.i_2993_w > 0 ? u_530_F.n_1700_B((float)target.RealmsLongRunningMcoTaskScreen / (float)target.i_2993_w, 0.0f, 1.0f) : 0.0f;
        this.t_148_a.n_1700_B(u_530_F.v_4262_N(hurtFactor, 1.0f, 0.25f));
        this.s_956_w.n_1700_B(hurtFactor);
        float size = this.v_4262_N.n_1700_B() * this.t_148_a.n_1700_B();
        float halfSize = size / 2.0f;
        double deltaTime = this.g_2268_R == 0L ? 0.0 : (double)((float)(System.currentTimeMillis() - this.g_2268_R) / 1000.0f);
        this.g_2268_R = System.currentTimeMillis();
        this.N_2525_X += 2.0 * (hasTarget ? 1.0 : 1.5) * deltaTime;
        matrixStack.n_1700_B();
        matrixStack.n_1700_B(c_776_E.c_3005_b.O_508_d().J_1907_R.u_1723_Y().v_4262_N());
        matrixStack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y((float)(Math.sin(this.N_2525_X) * 180.0)));
        D_1098_v rotationMatrix = matrixStack.R_4764_Y().n_1700_B();
        matrixStack.J_1907_R();
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.x_607_J();
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.u_2550_I();
        lightning.product.c_4037_x.J_1907_R(770, 1, 0, 1);
        lightning.product.c_4037_x.w_1484_f(7425);
        lightning.product.c_4037_x.q_2307_F();
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.J_1907_R(false);
        lightning.product.c_4037_x.n_1700_B(516, 0.01f);
        int baseColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        int color = H_2506_c.J_1907_R(H_2506_c.n_1700_B(u_530_F.n_1700_B((int)((float)H_2506_c.n_1700_B(baseColor) * (1.0f - this.s_956_w.n_1700_B()) + 200.0f * this.s_956_w.n_1700_B()), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.J_1907_R(baseColor) * (1.0f - this.s_956_w.n_1700_B())), 0, 255), u_530_F.n_1700_B((int)((float)H_2506_c.R_4764_Y(baseColor) * (1.0f - this.s_956_w.n_1700_B())), 0, 255), (int)(q_3148_R.J_1907_R(K_1200_E.J_1907_R) * u_530_F.n_1700_B(this.w_1484_f.n_1700_B(), 0.0f, 1.0f))), 45);
        int r = H_2506_c.n_1700_B(color);
        int g = H_2506_c.J_1907_R(color);
        int b = H_2506_c.R_4764_Y(color);
        int a = H_2506_c.G_564_y(color);
        c_3005_b.G_624_v().n_1700_B(new g_2336_b("Pouch/icons/world_render/targetnew.png"));
        A_4115_X.n_1700_B(7, E_688_b.k_2293_S);
        A_4115_X.n_1700_B(rotationMatrix, -halfSize, -halfSize + size, 0.0f).tex(0.0f, 1.0f).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(rotationMatrix, halfSize, -halfSize + size, 0.0f).tex(1.0f, 1.0f).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(rotationMatrix, halfSize, -halfSize, 0.0f).tex(1.0f, 0.0f).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(rotationMatrix, -halfSize, -halfSize, 0.0f).tex(0.0f, 0.0f).color(r, g, b, a).endVertex();
        Y_1740_V.J_1907_R();
        lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
        lightning.product.c_4037_x.J_1907_R(true);
        lightning.product.c_4037_x.w_1484_f(7424);
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        lightning.product.c_4037_x.M_588_G();
        lightning.product.c_4037_x.k_2293_S();
        lightning.product.c_4037_x.d_2461_k();
    }

    @Y_1740_V
    private void u_1723_Y(I_4477_R event) {
        r_4811_B cur;
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        boolean pentagramMode = aura.w_1484_f() && aura.multiplayerClientSuggestionProvider().J_1907_R("\u041f\u0435\u043d\u0442\u0430\u0433\u0440\u0430\u043c\u043c\u0430");
        r_4811_B r_4811_B2 = cur = pentagramMode ? aura.h_1847_R() : null;
        if (cur != null) {
            this.M_182_A = cur;
        }
        this.P_4830_p.n_1700_B(cur != null ? 1.0f : 0.0f);
        if (this.M_182_A == null || this.P_4830_p.n_1700_B() < 0.01f) {
            if (cur == null) {
                this.M_182_A = null;
            }
            return;
        }
        float hurtFactor = this.M_182_A.i_2993_w > 0 ? u_530_F.n_1700_B((float)this.M_182_A.RealmsLongRunningMcoTaskScreen / (float)this.M_182_A.i_2993_w, 0.0f, 1.0f) : 0.0f;
        this.h_1847_R.n_1700_B(u_530_F.v_4262_N(hurtFactor, 1.0f, 0.25f));
        this.Q_4569_t.n_1700_B(hurtFactor);
        e_2866_D p = F_747_P.n_1700_B((N_4263_v)this.M_182_A, event.J_1907_R());
        e_2866_D cv = c_776_E.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        double bx = p.J_1907_R - cv.J_1907_R;
        double by = p.R_4764_Y - cv.R_4764_Y + 0.05;
        double bz = p.G_564_y - cv.G_564_y;
        float apc = this.P_4830_p.n_1700_B();
        float time = (float)(System.currentTimeMillis() % 100000L) / 1000.0f;
        float radius = this.M_182_A.C_415_h() * 1.3f * this.h_1847_R.n_1700_B();
        g_221_o ms = new g_221_o();
        ms.n_1700_B(bx, by, bz);
        h_3572_K cam = c_776_E.c_3005_b.O_508_d().J_1907_R;
        lightning.product.c_4037_x.e_4240_b();
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.u_2550_I();
        lightning.product.c_4037_x.w_1484_f(7425);
        lightning.product.c_4037_x.q_2307_F();
        lightning.product.c_4037_x.J_1907_R(false);
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
        lightning.product.c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        int base = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        float hurtMix = this.Q_4569_t.n_1700_B();
        s_4405_m.Z_875_P.J_1907_R();
        s_4405_m.Z_875_P.n_1700_B("u_Color", (float)H_2506_c.n_1700_B(base) / 255.0f, (float)H_2506_c.J_1907_R(base) / 255.0f, (float)H_2506_c.R_4764_Y(base) / 255.0f, q_3148_R.J_1907_R(K_1200_E.J_1907_R) / 255.0f);
        s_4405_m.Z_875_P.J_1907_R("u_Alpha", apc);
        s_4405_m.Z_875_P.J_1907_R("u_HurtMix", hurtMix);
        D_3318_r buf = Y_1740_V.R_4764_Y();
        ms.n_1700_B();
        ms.n_1700_B(M_1336_P.G_564_y.R_4764_Y(time * 35.0f));
        D_1098_v mat = ms.R_4764_Y().n_1700_B();
        float halfSize = radius * 1.35f;
        float y = 0.01f;
        buf.n_1700_B(7, E_688_b.Q_2552_b);
        buf.n_1700_B(mat, -halfSize, y, halfSize).tex(0.0f, 1.0f).endVertex();
        buf.n_1700_B(mat, halfSize, y, halfSize).tex(1.0f, 1.0f).endVertex();
        buf.n_1700_B(mat, halfSize, y, -halfSize).tex(1.0f, 0.0f).endVertex();
        buf.n_1700_B(mat, -halfSize, y, -halfSize).tex(0.0f, 0.0f).endVertex();
        Y_1740_V.J_1907_R();
        ms.J_1907_R();
        s_4405_m.Z_875_P.R_4764_Y();
        lightning.product.c_4037_x.x_607_J();
        lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        lightning.product.c_4037_x.k_2293_S();
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.M_588_G();
        lightning.product.c_4037_x.w_1484_f(7424);
        lightning.product.c_4037_x.J_1907_R(true);
    }

    private int n_1700_B(int hue) {
        int from = (Integer)z_283_n.u_2550_I.J_1907_R();
        int to = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        int base = H_2506_c.J_1907_R(10, Math.floorMod(hue, 360), from, to);
        float h = this.Q_4569_t.n_1700_B();
        int r = u_530_F.n_1700_B((int)((float)H_2506_c.n_1700_B(base) * (1.0f - h) + 200.0f * h), 0, 255);
        int g = u_530_F.n_1700_B((int)((float)H_2506_c.J_1907_R(base) * (1.0f - h)), 0, 255);
        int b = u_530_F.n_1700_B((int)((float)H_2506_c.R_4764_Y(base) * (1.0f - h)), 0, 255);
        return H_2506_c.n_1700_B(r, g, b, 255);
    }

    private static void n_1700_B(D_3318_r buf, g_221_o stack, h_3572_K cam, float lx, float ly, float lz, float halfSize, int r, int g, int b, int a) {
        stack.n_1700_B();
        stack.n_1700_B((double)lx, (double)ly, (double)lz);
        stack.n_1700_B(cam.u_1723_Y().v_4262_N());
        stack.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
        D_1098_v m = stack.R_4764_Y().n_1700_B();
        buf.n_1700_B(m, -halfSize, halfSize, 0.0f).tex(0.0f, 1.0f).color(r, g, b, a).endVertex();
        buf.n_1700_B(m, halfSize, halfSize, 0.0f).tex(1.0f, 1.0f).color(r, g, b, a).endVertex();
        buf.n_1700_B(m, halfSize, -halfSize, 0.0f).tex(1.0f, 0.0f).color(r, g, b, a).endVertex();
        buf.n_1700_B(m, -halfSize, -halfSize, 0.0f).tex(0.0f, 0.0f).color(r, g, b, a).endVertex();
        stack.J_1907_R();
    }

    @Y_1740_V
    private void v_4262_N(I_4477_R event) {
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        F_3698_k elytraTarget = ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
        if (elytraTarget == null || !aura.w_1484_f() || !elytraTarget.w_1484_f()) {
            return;
        }
        r_4811_B target = aura.h_1847_R();
        if (target == null || c_776_E.c_3005_b.Y_259_p == null || !c_776_E.c_3005_b.Y_259_p.k_578_l() || !target.k_578_l()) {
            return;
        }
        e_2866_D targetVec = target.s_4990_V().J_1907_R(0.0, (double)target.v_165_F() / 2.0, 0.0).P_1922_E(target.RealmsSettingsScreen().n_1700_B((double)((Float)F_3698_k.u_2550_I.J_1907_R()).floatValue()));
        if (targetVec == null) {
            return;
        }
        e_2866_D cameraPosition = c_776_E.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        float boxSize = 0.5f;
        I_4817_s box = new I_4817_s(targetVec.J_1907_R - (double)boxSize - cameraPosition.J_1907_R, targetVec.R_4764_Y - (double)boxSize - cameraPosition.R_4764_Y, targetVec.G_564_y - (double)boxSize - cameraPosition.G_564_y, targetVec.J_1907_R + (double)boxSize - cameraPosition.J_1907_R, targetVec.R_4764_Y + (double)boxSize - cameraPosition.R_4764_Y, targetVec.G_564_y + (double)boxSize - cameraPosition.G_564_y);
        lightning.product.c_4037_x.v_4276_D();
        lightning.product.c_4037_x.e_4240_b();
        lightning.product.c_4037_x.Y_601_j();
        lightning.product.c_4037_x.u_2550_I();
        lightning.product.c_4037_x.q_2307_F();
        lightning.product.c_4037_x.t_1786_h();
        lightning.product.c_4037_x.J_1907_R(false);
        int interfaceColor = (Integer)z_283_n.u_2550_I.J_1907_R();
        int rgbBoost = H_2506_c.J_1907_R(interfaceColor, 70);
        int rgbMid = H_2506_c.J_1907_R(interfaceColor, 35);
        int rgbCore = interfaceColor;
        int alphaBase = H_2506_c.G_564_y(interfaceColor);
        alphaBase = u_530_F.n_1700_B(alphaBase, 0, 255);
        float pulse = 0.75f + 0.25f * (float)Math.sin((double)System.currentTimeMillis() / 180.0);
        int fillAlpha = u_530_F.n_1700_B((int)((float)alphaBase * 0.1f * pulse), 0, 140);
        int outerAlpha = u_530_F.n_1700_B((int)((float)alphaBase * 0.85f * pulse), 0, 255);
        int midAlpha = u_530_F.n_1700_B((int)((float)alphaBase * 0.65f), 0, 255);
        int coreAlpha = u_530_F.n_1700_B((int)((float)alphaBase * 0.9f), 0, 255);
        int fillColor = H_2506_c.n_1700_B(rgbBoost, fillAlpha);
        int outerColor = H_2506_c.n_1700_B(rgbBoost, outerAlpha);
        int midColor = H_2506_c.n_1700_B(rgbMid, midAlpha);
        int coreColor = H_2506_c.n_1700_B(rgbCore, coreAlpha);
        g_221_o stack = new g_221_o();
        D_1098_v matrix = stack.R_4764_Y().n_1700_B();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        this.n_1700_B(matrix, box, fillColor);
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.P_1922_E);
        lightning.product.c_4037_x.G_564_y(4.0f);
        this.J_1907_R(matrix, box, outerColor);
        lightning.product.c_4037_x.G_564_y(2.2f);
        this.J_1907_R(matrix, box, midColor);
        lightning.product.c_4037_x.G_564_y(1.2f);
        this.J_1907_R(matrix, box, coreColor);
        lightning.product.c_4037_x.multiplayerClientSuggestionProvider();
        lightning.product.c_4037_x.J_1907_R(true);
        lightning.product.c_4037_x.k_2293_S();
        lightning.product.c_4037_x.Y_259_p();
        lightning.product.c_4037_x.x_607_J();
        lightning.product.c_4037_x.M_588_G();
        lightning.product.c_4037_x.n_1700_B(X_933_l.t_1786_h.M_588_G, X_933_l.s_956_w.s_956_w);
        lightning.product.c_4037_x.d_2461_k();
    }

    private void n_1700_B(D_1098_v matrix, I_4817_s box, int color) {
        int r = H_2506_c.n_1700_B(color);
        int g = H_2506_c.J_1907_R(color);
        int b = H_2506_c.R_4764_Y(color);
        int a = H_2506_c.G_564_y(color);
        float x1 = (float)box.minX;
        float y1 = (float)box.minY;
        float z1 = (float)box.minZ;
        float x2 = (float)box.maxX;
        float y2 = (float)box.maxY;
        float z2 = (float)box.maxZ;
        A_4115_X.n_1700_B(7, E_688_b.Y_601_j);
        A_4115_X.n_1700_B(matrix, x1, y1, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y1, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y1, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y1, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y2, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y2, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y2, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y2, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y1, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y2, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y2, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y1, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y1, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y1, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y2, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y2, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y1, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y1, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y2, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x1, y2, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y1, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y2, z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y2, z2).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, x2, y1, z2).color(r, g, b, a).endVertex();
        Y_1740_V.J_1907_R();
    }

    private void J_1907_R(D_1098_v matrix, I_4817_s box, int color) {
        A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
        this.n_1700_B(matrix, box.minX, box.minY, box.minZ, box.maxX, box.minY, box.minZ, color);
        this.n_1700_B(matrix, box.maxX, box.minY, box.minZ, box.maxX, box.minY, box.maxZ, color);
        this.n_1700_B(matrix, box.maxX, box.minY, box.maxZ, box.minX, box.minY, box.maxZ, color);
        this.n_1700_B(matrix, box.minX, box.minY, box.maxZ, box.minX, box.minY, box.minZ, color);
        this.n_1700_B(matrix, box.minX, box.maxY, box.minZ, box.maxX, box.maxY, box.minZ, color);
        this.n_1700_B(matrix, box.maxX, box.maxY, box.minZ, box.maxX, box.maxY, box.maxZ, color);
        this.n_1700_B(matrix, box.maxX, box.maxY, box.maxZ, box.minX, box.maxY, box.maxZ, color);
        this.n_1700_B(matrix, box.minX, box.maxY, box.maxZ, box.minX, box.maxY, box.minZ, color);
        this.n_1700_B(matrix, box.minX, box.minY, box.minZ, box.minX, box.maxY, box.minZ, color);
        this.n_1700_B(matrix, box.maxX, box.minY, box.minZ, box.maxX, box.maxY, box.minZ, color);
        this.n_1700_B(matrix, box.maxX, box.minY, box.maxZ, box.maxX, box.maxY, box.maxZ, color);
        this.n_1700_B(matrix, box.minX, box.minY, box.maxZ, box.minX, box.maxY, box.maxZ, color);
        Y_1740_V.J_1907_R();
    }

    private void n_1700_B(D_1098_v matrix, double x1, double y1, double z1, double x2, double y2, double z2, int color) {
        int r = H_2506_c.n_1700_B(color);
        int g = H_2506_c.J_1907_R(color);
        int b = H_2506_c.R_4764_Y(color);
        int a = H_2506_c.G_564_y(color);
        A_4115_X.n_1700_B(matrix, (float)x1, (float)y1, (float)z1).color(r, g, b, a).endVertex();
        A_4115_X.n_1700_B(matrix, (float)x2, (float)y2, (float)z2).color(r, g, b, a).endVertex();
    }

    @Y_1740_V
    private void w_1484_f(I_4477_R event) {
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        boolean enabled = aura.w_1484_f() && aura.multiplayerClientSuggestionProvider().J_1907_R("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b");
        r_4811_B target = enabled ? aura.h_1847_R() : null;
        boolean alive = target != null && target.RealmsLongRunningMcoTaskScreen();
        this.Y_259_p.n_1700_B(enabled && alive ? 1.0f : 0.0f);
        if (this.Y_259_p.n_1700_B() <= 0.01f) {
            this.k_2293_S = null;
            this.q_2307_F = 0L;
            return;
        }
        if (alive) {
            if (this.k_2293_S == null) {
                this.q_2307_F = System.currentTimeMillis();
            }
            this.k_2293_S = target;
        }
        if (this.k_2293_S == null) {
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (this.q_2307_F != 0L) {
            float deltaTime = (float)(currentTime - this.q_2307_F) / 1000.0f;
            this.Z_875_P += deltaTime * 60.0f;
            if (this.Z_875_P >= 360.0f) {
                this.Z_875_P -= 360.0f;
            }
        }
        this.q_2307_F = currentTime;
        if (this.t_4043_B >= 0) {
            if (!this.e_4240_b) {
                this.Q_2552_b.n_1700_B(1.0f);
                if (this.Q_2552_b.n_1700_B() >= 0.99f) {
                    this.e_4240_b = true;
                    this.C_2741_M.J_1907_R(0.0f);
                }
            } else {
                this.C_2741_M.n_1700_B(1.0f);
                if (this.C_2741_M.n_1700_B() >= 0.99f) {
                    this.t_4043_B = -1;
                    this.e_4240_b = false;
                    this.Q_2552_b.J_1907_R(0.0f);
                    this.C_2741_M.J_1907_R(0.0f);
                }
            }
        }
        this.n_1700_B(this.k_2293_S, event.J_1907_R());
    }

    @Y_1740_V
    private void n_1700_B(h_2739_B event) {
        AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
        if (!aura.w_1484_f() || !aura.multiplayerClientSuggestionProvider().J_1907_R("\u041a\u0440\u0438\u0441\u0442\u0430\u043b\u043b\u044b")) {
            return;
        }
        if (event.J_1907_R() != this.k_2293_S) {
            return;
        }
        long now = System.currentTimeMillis();
        if (now - this.x_607_J < 300L) {
            return;
        }
        this.x_607_J = now;
        this.t_4043_B = (int)(Math.random() * 18.0);
        this.Q_2552_b.J_1907_R(0.0f);
    }

    private void n_1700_B(r_4811_B target, float partialTicks) {
        e_2866_D cameraPos = c_776_E.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        int baseColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        int color = H_2506_c.n_1700_B(baseColor, (int)(255.0f * this.Y_259_p.n_1700_B()));
        float width = target.C_415_h() * 1.5f;
        e_2866_D renderPos = F_747_P.n_1700_B((N_4263_v)target, partialTicks);
        g_221_o ms = new g_221_o();
        ms.n_1700_B(renderPos.J_1907_R - cameraPos.J_1907_R, renderPos.R_4764_Y - cameraPos.R_4764_Y, renderPos.G_564_y - cameraPos.G_564_y);
        int crystalIndex = 0;
        for (int i = 0; i < 360; i += 20) {
            float val = 1.2f - 0.5f * this.Y_259_p.n_1700_B();
            float angleRad = (float)Math.toRadians((float)i + this.Z_875_P);
            float sin = (float)(Math.sin(angleRad) * (double)width * (double)val);
            float cos = (float)(Math.cos(angleRad) * (double)width * (double)val);
            float size = 0.1f;
            float yOffset = 0.1f + target.v_165_F() * Math.abs(u_530_F.n_1700_B((float)i));
            float offsetX = sin;
            float offsetY = yOffset;
            float offsetZ = cos;
            if (crystalIndex == this.t_4043_B) {
                float targetCenterY = target.v_165_F() / 2.0f;
                if (!this.e_4240_b && this.Q_2552_b.n_1700_B() > 0.01f) {
                    float progress = this.Q_2552_b.n_1700_B();
                    offsetX = u_530_F.v_4262_N(progress, sin, 0.0f);
                    offsetY = u_530_F.v_4262_N(progress, yOffset, targetCenterY);
                    offsetZ = u_530_F.v_4262_N(progress, cos, 0.0f);
                } else if (this.e_4240_b) {
                    float progress = this.C_2741_M.n_1700_B();
                    offsetX = u_530_F.v_4262_N(progress, 0.0f, sin);
                    offsetY = u_530_F.v_4262_N(progress, targetCenterY, yOffset);
                    offsetZ = u_530_F.v_4262_N(progress, 0.0f, cos);
                }
            }
            ms.n_1700_B();
            ms.n_1700_B((double)offsetX, (double)offsetY, (double)offsetZ);
            e_2866_D crystalPos = renderPos.J_1907_R(offsetX, offsetY, offsetZ);
            e_2866_D targetCenter = target.s_4990_V().J_1907_R(0.0, (double)target.v_165_F() / 2.0, 0.0);
            e_2866_D direction = targetCenter.G_564_y(crystalPos);
            double length = direction.u_1723_Y();
            if (length < 0.001) {
                ms.J_1907_R();
                ++crystalIndex;
                continue;
            }
            direction = direction.G_564_y();
            M_1336_P directionToTarget = new M_1336_P((float)direction.J_1907_R, (float)direction.R_4764_Y, (float)direction.G_564_y);
            M_1336_P initialDirection = new M_1336_P(0.0f, 1.0f, 0.0f);
            M_1336_P axis = initialDirection.P_1922_E();
            axis.G_564_y(directionToTarget);
            float axisLength = (float)Math.sqrt(axis.n_1700_B() * axis.n_1700_B() + axis.J_1907_R() * axis.J_1907_R() + axis.R_4764_Y() * axis.R_4764_Y());
            if (axisLength < 0.001f) {
                ms.J_1907_R();
                ++crystalIndex;
                continue;
            }
            axis = new M_1336_P(axis.n_1700_B() / axisLength, axis.J_1907_R() / axisLength, axis.R_4764_Y() / axisLength);
            float dot = u_530_F.n_1700_B(initialDirection.n_1700_B() * directionToTarget.n_1700_B() + initialDirection.J_1907_R() * directionToTarget.J_1907_R() + initialDirection.R_4764_Y() * directionToTarget.R_4764_Y(), -1.0f, 1.0f);
            float angle = (float)Math.acos(dot);
            w_3785_E rotation = new w_3785_E(axis, angle, false);
            ms.n_1700_B(rotation);
            F_2052_z.n_1700_B(ms, 0.0f, 0.0f, 0.0f, size, color, false);
            int boosted = H_2506_c.J_1907_R(color, 90);
            F_2052_z.n_1700_B(ms, 0.0f, 0.0f, 0.0f, size, boosted, true);
            ms.J_1907_R();
            ++crystalIndex;
        }
        g_2336_b glowTexture = new g_2336_b("Pouch/icons/world_render/glow.png");
        float bigSize = 0.6f;
        crystalIndex = 0;
        for (int i = 0; i < 360; i += 20) {
            float val = 1.2f - 0.5f * this.Y_259_p.n_1700_B();
            float angleRad = (float)Math.toRadians((float)i + this.Z_875_P);
            float sin = (float)(Math.sin(angleRad) * (double)width * (double)val);
            float cos = (float)(Math.cos(angleRad) * (double)width * (double)val);
            float yOffset = 0.1f + target.v_165_F() * Math.abs(u_530_F.n_1700_B((float)i));
            float offsetX = sin;
            float offsetY = yOffset;
            float offsetZ = cos;
            if (crystalIndex == this.t_4043_B) {
                float targetCenterY = target.v_165_F() / 2.0f;
                if (!this.e_4240_b && this.Q_2552_b.n_1700_B() > 0.01f) {
                    float progress = this.Q_2552_b.n_1700_B();
                    offsetX = u_530_F.v_4262_N(progress, sin, 0.0f);
                    offsetY = u_530_F.v_4262_N(progress, yOffset, targetCenterY);
                    offsetZ = u_530_F.v_4262_N(progress, cos, 0.0f);
                } else if (this.e_4240_b) {
                    float progress = this.C_2741_M.n_1700_B();
                    offsetX = u_530_F.v_4262_N(progress, 0.0f, sin);
                    offsetY = u_530_F.v_4262_N(progress, targetCenterY, yOffset);
                    offsetZ = u_530_F.v_4262_N(progress, 0.0f, cos);
                }
            }
            e_2866_D glowPos = renderPos.J_1907_R(offsetX, offsetY, offsetZ);
            e_2866_D toCenter = glowPos.G_564_y(cameraPos);
            w_3785_E camRotation = new w_3785_E(c_776_E.c_3005_b.O_508_d().J_1907_R.u_1723_Y());
            camRotation.n_1700_B(M_1336_P.u_1723_Y.R_4764_Y(180.0f));
            M_1336_P right3f = new M_1336_P(1.0f, 0.0f, 0.0f);
            M_1336_P up3f = new M_1336_P(0.0f, 1.0f, 0.0f);
            right3f.n_1700_B(camRotation);
            up3f.n_1700_B(camRotation);
            double halfSize = bigSize * 0.5f;
            e_2866_D halfRight = new e_2866_D(right3f.n_1700_B(), right3f.J_1907_R(), right3f.R_4764_Y()).n_1700_B(halfSize);
            e_2866_D halfUp = new e_2866_D(up3f.n_1700_B(), up3f.J_1907_R(), up3f.R_4764_Y()).n_1700_B(halfSize);
            e_2866_D p0 = toCenter.G_564_y(halfRight).G_564_y(halfUp);
            e_2866_D p1 = toCenter.P_1922_E(halfRight).G_564_y(halfUp);
            e_2866_D p2 = toCenter.P_1922_E(halfRight).P_1922_E(halfUp);
            e_2866_D p3 = toCenter.G_564_y(halfRight).P_1922_E(halfUp);
            int glowColor = H_2506_c.n_1700_B(color, (int)(255.0f * this.Y_259_p.n_1700_B() * 0.3f));
            F_489_x.n_1700_B(glowTexture, false, (float)p0.J_1907_R, (float)p0.R_4764_Y, (float)p0.G_564_y, (float)p1.J_1907_R, (float)p1.R_4764_Y, (float)p1.G_564_y, (float)p2.J_1907_R, (float)p2.R_4764_Y, (float)p2.G_564_y, (float)p3.J_1907_R, (float)p3.R_4764_Y, (float)p3.G_564_y, glowColor);
            int boostedGlow = H_2506_c.J_1907_R(glowColor, 90);
            F_489_x.n_1700_B(glowTexture, (float)p0.J_1907_R, (float)p0.R_4764_Y, (float)p0.G_564_y, (float)p1.J_1907_R, (float)p1.R_4764_Y, (float)p1.G_564_y, (float)p2.J_1907_R, (float)p2.R_4764_Y, (float)p2.G_564_y, (float)p3.J_1907_R, (float)p3.R_4764_Y, (float)p3.G_564_y, boostedGlow, boostedGlow, boostedGlow, boostedGlow, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f);
            ++crystalIndex;
        }
        F_489_x.J_1907_R();
    }
}



