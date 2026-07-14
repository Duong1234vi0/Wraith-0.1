/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import java.util.function.Function;
import lightning.product.N_4263_v;
import lightning.product.Q_1187_u;
import lightning.product.T_4002_g;
import lightning.product.a_3913_L;
import lightning.product.Emotions;
import lightning.product.AgeableListModel;
import lightning.product.MinecraftClient;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_4231_L;
import lightning.product.AnimationUtils;
import lightning.product.ClientBootstrap;
import lightning.product.o_2576_A;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;
import lightning.product.w_720_O;
import lightning.product.x_1688_C;

public class n_1658_l<T extends r_4811_B>
extends AgeableListModel<T>
implements T_4002_g,
w_720_O {
    public e_4189_z n_1700_B;
    public e_4189_z J_1907_R;
    public e_4189_z R_4764_Y;
    public e_4189_z G_564_y;
    public e_4189_z P_1922_E;
    public e_4189_z u_1723_Y;
    public e_4189_z v_4262_N;
    public n_1700_B w_1484_f = lightning.product.n_1658_l$n_1700_B.n_1700_B;
    public n_1700_B t_148_a = lightning.product.n_1658_l$n_1700_B.n_1700_B;
    public boolean s_956_w;
    public float u_2550_I;

    public n_1658_l(float modelSize) {
        this(o_2576_A::G_564_y, modelSize, 0.0f, 64, 32);
    }

    protected n_1658_l(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
        this(o_2576_A::G_564_y, modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
    }

    public n_1658_l(Function<g_2336_b, o_2576_A> renderTypeIn, float modelSizeIn, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
        super(renderTypeIn, true, 16.0f, 0.0f, 2.0f, 2.0f, 24.0f);
        this.textureWidth = textureWidthIn;
        this.textureHeight = textureHeightIn;
        this.n_1700_B = new e_4189_z(this, 0, 0);
        this.n_1700_B.n_1700_B(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, modelSizeIn);
        this.n_1700_B.n_1700_B(0.0f, 0.0f + yOffsetIn, 0.0f);
        this.J_1907_R = new e_4189_z(this, 32, 0);
        this.J_1907_R.n_1700_B(-4.0f, -8.0f, -4.0f, 8.0f, 8.0f, 8.0f, modelSizeIn + 0.5f);
        this.J_1907_R.n_1700_B(0.0f, 0.0f + yOffsetIn, 0.0f);
        this.R_4764_Y = new e_4189_z(this, 16, 16);
        this.R_4764_Y.n_1700_B(-4.0f, 0.0f, -2.0f, 8.0f, 12.0f, 4.0f, modelSizeIn);
        this.R_4764_Y.n_1700_B(0.0f, 0.0f + yOffsetIn, 0.0f);
        this.G_564_y = new e_4189_z(this, 40, 16);
        this.G_564_y.n_1700_B(-3.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSizeIn);
        this.G_564_y.n_1700_B(-5.0f, 2.0f + yOffsetIn, 0.0f);
        this.P_1922_E = new e_4189_z(this, 40, 16);
        this.P_1922_E.t_148_a = true;
        this.P_1922_E.n_1700_B(-1.0f, -2.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSizeIn);
        this.P_1922_E.n_1700_B(5.0f, 2.0f + yOffsetIn, 0.0f);
        this.u_1723_Y = new e_4189_z(this, 0, 16);
        this.u_1723_Y.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSizeIn);
        this.u_1723_Y.n_1700_B(-1.9f, 12.0f + yOffsetIn, 0.0f);
        this.v_4262_N = new e_4189_z(this, 0, 16);
        this.v_4262_N.t_148_a = true;
        this.v_4262_N.n_1700_B(-2.0f, 0.0f, -2.0f, 4.0f, 12.0f, 4.0f, modelSizeIn);
        this.v_4262_N.n_1700_B(1.9f, 12.0f + yOffsetIn, 0.0f);
    }

    @Override
    protected Iterable<e_4189_z> n_1700_B() {
        return ImmutableList.of((Object)this.n_1700_B);
    }

    @Override
    protected Iterable<e_4189_z> J_1907_R() {
        return ImmutableList.of((Object)this.R_4764_Y, (Object)this.G_564_y, (Object)this.P_1922_E, (Object)this.u_1723_Y, (Object)this.v_4262_N, (Object)this.J_1907_R);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float partialTick) {
        this.u_2550_I = ((r_4811_B)entityIn).u_1723_Y(partialTick);
        super.n_1700_B(entityIn, limbSwing, limbSwingAmount, partialTick);
    }

    @Override
    public void n_1700_B(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        boolean hideSwimAnim;
        boolean hideSneakAnim;
        boolean flag3;
        Emotions emotionsPreview = Emotions.h_1847_R();
        if (emotionsPreview != null && emotionsPreview.Y_259_p() != null) {
            this.s_956_w = false;
            this.Q_4569_t = false;
            this.u_2550_I = 0.0f;
            this.h_1847_R = 0.0f;
            this.w_1484_f = lightning.product.n_1658_l$n_1700_B.n_1700_B;
            this.t_148_a = lightning.product.n_1658_l$n_1700_B.n_1700_B;
            this.n_1700_B(emotionsPreview.Y_259_p(), 1.5f, limbSwing, limbSwingAmount, 1.0f);
            this.J_1907_R.n_1700_B(this.n_1700_B);
            return;
        }
        Emotions emotions = Emotions.h_1847_R();
        MinecraftClient mc = MinecraftClient.A_4115_X();
        boolean isEmotionActive = emotions != null && emotions.w_1484_f() && !emotions.Y_601_j() && mc.Y_259_p != null;
        boolean shouldAnimateEmotion = false;
        if (isEmotionActive) {
            if (emotions.t_1786_h().t_148_a().booleanValue() && entityIn == mc.Y_259_p) {
                shouldAnimateEmotion = true;
            } else if (emotions.M_182_A().t_148_a().booleanValue() && ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(((N_4263_v)entityIn).L_3570_A())) {
                shouldAnimateEmotion = true;
            } else if (emotions.multiplayerClientSuggestionProvider().t_148_a().booleanValue() && entityIn != mc.Y_259_p && (entityIn instanceof a_3913_L || entityIn instanceof Q_1187_u)) {
                shouldAnimateEmotion = true;
            }
        }
        boolean flag = !shouldAnimateEmotion && ((r_4811_B)entityIn).h_3859_C() > 4;
        boolean flag1 = !shouldAnimateEmotion && ((r_4811_B)entityIn).x_612_B();
        this.n_1700_B.v_4262_N = netHeadYaw * ((float)Math.PI / 180);
        this.n_1700_B.u_1723_Y = flag ? -0.7853982f : (!shouldAnimateEmotion && this.u_2550_I > 0.0f ? (flag1 ? this.n_1700_B(this.u_2550_I, this.n_1700_B.u_1723_Y, -0.7853982f) : this.n_1700_B(this.u_2550_I, this.n_1700_B.u_1723_Y, headPitch * ((float)Math.PI / 180))) : headPitch * ((float)Math.PI / 180));
        this.n_1700_B.w_1484_f = 0.0f;
        this.R_4764_Y.u_1723_Y = 0.0f;
        this.R_4764_Y.v_4262_N = 0.0f;
        this.R_4764_Y.w_1484_f = 0.0f;
        this.G_564_y.P_1922_E = 0.0f;
        this.G_564_y.R_4764_Y = -5.0f;
        this.P_1922_E.P_1922_E = 0.0f;
        this.P_1922_E.R_4764_Y = 5.0f;
        float f = 1.0f;
        if (flag) {
            f = (float)((N_4263_v)entityIn).I_4348_c().v_4262_N();
            f /= 0.2f;
            f = f * f * f;
        }
        if (f < 1.0f) {
            f = 1.0f;
        }
        this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 2.0f * limbSwingAmount * 0.5f / f;
        this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f / f;
        this.G_564_y.w_1484_f = 0.0f;
        this.P_1922_E.w_1484_f = 0.0f;
        this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount / f;
        this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount / f;
        if (shouldAnimateEmotion) {
            String mode = emotions.w_1457_N();
            float time = (float)(System.currentTimeMillis() % 10000L) / 1000.0f;
            if (mode.equals("\u041f\u0440\u0438\u0432\u0435\u0442\u0441\u0442\u0432\u0438\u0435")) {
                waveSpeed = 10.0f;
                waveAmplitude = 0.2f;
                this.G_564_y.u_1723_Y = -3.0f;
                this.G_564_y.v_4262_N = u_530_F.n_1700_B(time * waveSpeed) * waveAmplitude;
                this.G_564_y.w_1484_f = -0.5f + u_530_F.n_1700_B(time * waveSpeed) * waveAmplitude;
                this.P_1922_E.u_1723_Y = 0.0f;
                this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount / f;
                this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount / f;
                this.n_1700_B.u_1723_Y = 0.0f;
            } else if (mode.equals("\u0422\u0430\u043d\u0435\u0446")) {
                float danceTime = (float)(System.currentTimeMillis() % 6000L) / 1000.0f;
                this.R_4764_Y.v_4262_N = u_530_F.n_1700_B(danceTime * 2.0f) * 0.3f;
                this.R_4764_Y.u_1723_Y = u_530_F.n_1700_B(danceTime * 1.5f) * 0.1f;
                this.n_1700_B.v_4262_N = u_530_F.n_1700_B(danceTime * 1.8f) * 0.4f;
                this.n_1700_B.u_1723_Y = u_530_F.n_1700_B(danceTime * 2.2f) * 0.2f;
                this.G_564_y.u_1723_Y = -1.5f + u_530_F.n_1700_B(danceTime * 3.0f) * 0.8f;
                this.G_564_y.v_4262_N = u_530_F.n_1700_B(danceTime * 1.5f) * 0.5f;
                this.G_564_y.w_1484_f = u_530_F.n_1700_B(danceTime * 2.5f) * 0.7f;
                this.P_1922_E.u_1723_Y = -1.5f + u_530_F.n_1700_B(danceTime * 3.0f + (float)Math.PI) * 0.8f;
                this.P_1922_E.v_4262_N = u_530_F.n_1700_B(danceTime * 1.5f + (float)Math.PI) * 0.5f;
                this.P_1922_E.w_1484_f = u_530_F.n_1700_B(danceTime * 2.5f + (float)Math.PI) * 0.7f;
                this.u_1723_Y.u_1723_Y = 0.5f + u_530_F.n_1700_B(danceTime * 2.0f) * 0.6f;
                this.v_4262_N.u_1723_Y = 0.5f + u_530_F.n_1700_B(danceTime * 2.0f + (float)Math.PI) * 0.6f;
                if (danceTime % 6.0f > 3.0f && danceTime % 6.0f < 4.0f) {
                    float squat = danceTime % 6.0f - 3.0f;
                    this.R_4764_Y.G_564_y = squat * 2.0f;
                    this.u_1723_Y.G_564_y = 12.0f + squat * 2.0f;
                    this.v_4262_N.G_564_y = 12.0f + squat * 2.0f;
                    this.G_564_y.G_564_y = 2.0f + squat * 2.0f;
                    this.P_1922_E.G_564_y = 2.0f + squat * 2.0f;
                } else {
                    this.R_4764_Y.G_564_y = 0.0f;
                    this.u_1723_Y.G_564_y = 12.0f;
                    this.v_4262_N.G_564_y = 12.0f;
                    this.G_564_y.G_564_y = 2.0f;
                    this.P_1922_E.G_564_y = 2.0f;
                }
                if (danceTime % 6.0f > 4.5f && danceTime % 6.0f < 5.0f) {
                    float jump = u_530_F.n_1700_B((danceTime % 6.0f - 4.5f) * (float)Math.PI * 2.0f);
                    this.R_4764_Y.G_564_y = -jump * 1.5f;
                    this.n_1700_B.G_564_y = jump * 1.5f;
                }
            } else if (mode.equals("\u0414\u0440\u043e\u0447\u043a\u0430")) {
                waveSpeed = 30.0f;
                waveAmplitude = 0.2f;
                this.G_564_y.u_1723_Y = 50.0f;
                this.G_564_y.v_4262_N = u_530_F.n_1700_B(time * waveSpeed) * waveAmplitude;
                this.G_564_y.w_1484_f = -0.5f + u_530_F.n_1700_B(time * waveSpeed) * waveAmplitude;
                this.P_1922_E.u_1723_Y = 0.0f;
                this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount / f;
                this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount / f;
                this.n_1700_B.u_1723_Y = 0.0f;
            } else if (mode.equals("\u041d\u0430\u043c\u0430\u0437")) {
                float legYOff;
                float armYOff;
                float bodyYOff;
                float legRotX;
                float lArmRotZ;
                float lArmRotY;
                float lArmRotX;
                float rArmRotZ;
                float rArmRotY;
                float rArmRotX;
                float bodyRotX;
                float headRotX;
                float cycleTime = (float)(System.currentTimeMillis() % 10000L) / 1000.0f;
                float phase = cycleTime / 10.0f;
                if (phase < 0.15f) {
                    headRotX = 0.3f;
                    bodyRotX = 0.0f;
                    rArmRotX = -0.9f;
                    rArmRotY = -0.3f;
                    rArmRotZ = 0.0f;
                    lArmRotX = -0.9f;
                    lArmRotY = 0.3f;
                    lArmRotZ = 0.0f;
                    legRotX = 0.0f;
                    bodyYOff = 0.0f;
                    armYOff = 0.0f;
                    legYOff = 0.0f;
                } else if (phase < 0.28f) {
                    float t = (phase - 0.15f) / 0.13f;
                    headRotX = 0.3f + t * 0.55f;
                    bodyRotX = t * 1.3f;
                    rArmRotX = -0.9f + t * 1.3f;
                    rArmRotY = -0.3f * (1.0f - t);
                    rArmRotZ = t * -0.1f;
                    lArmRotX = -0.9f + t * 1.3f;
                    lArmRotY = 0.3f * (1.0f - t);
                    lArmRotZ = t * 0.1f;
                    legRotX = 0.0f;
                    bodyYOff = t * 3.0f;
                    armYOff = t * 3.0f;
                    legYOff = 0.0f;
                } else if (phase < 0.36f) {
                    float r = 1.0f - (phase - 0.28f) / 0.08f;
                    headRotX = 0.3f + r * 0.55f;
                    bodyRotX = r * 1.3f;
                    rArmRotX = -0.9f + r * 1.3f;
                    rArmRotY = -0.3f * r;
                    rArmRotZ = r * -0.1f;
                    lArmRotX = -0.9f + r * 1.3f;
                    lArmRotY = 0.3f * r;
                    lArmRotZ = r * 0.1f;
                    legRotX = 0.0f;
                    bodyYOff = r * 3.0f;
                    armYOff = r * 3.0f;
                    legYOff = 0.0f;
                } else if (phase < 0.52f) {
                    float t = Math.min((phase - 0.36f) / 0.08f, 1.0f);
                    headRotX = 0.3f + t * 1.1f;
                    bodyRotX = t * 1.7f;
                    rArmRotX = -0.9f * (1.0f - t) + -2.2f * t;
                    rArmRotY = -0.3f * (1.0f - t) + 0.3f * t;
                    rArmRotZ = 0.0f;
                    lArmRotX = -0.9f * (1.0f - t) + -2.2f * t;
                    lArmRotY = 0.3f * (1.0f - t) + -0.3f * t;
                    lArmRotZ = 0.0f;
                    legRotX = t * 2.0f;
                    bodyYOff = t * 10.0f;
                    armYOff = t * 10.0f;
                    legYOff = t * 3.0f;
                } else if (phase < 0.64f) {
                    float t = Math.min((phase - 0.52f) / 0.05f, 1.0f);
                    headRotX = 1.4f * (1.0f - t) + 0.25f * t;
                    bodyRotX = 1.7f * (1.0f - t) + 0.1f * t;
                    rArmRotX = -2.2f * (1.0f - t) + -0.3f * t;
                    rArmRotY = 0.3f * (1.0f - t);
                    rArmRotZ = 0.0f;
                    lArmRotX = -2.2f * (1.0f - t) + -0.3f * t;
                    lArmRotY = -0.3f * (1.0f - t);
                    lArmRotZ = 0.0f;
                    legRotX = 2.0f;
                    bodyYOff = 10.0f;
                    armYOff = 10.0f;
                    legYOff = 3.0f;
                } else if (phase < 0.8f) {
                    float t = Math.min((phase - 0.64f) / 0.06f, 1.0f);
                    headRotX = 0.25f * (1.0f - t) + 1.4f * t;
                    bodyRotX = 0.1f * (1.0f - t) + 1.7f * t;
                    rArmRotX = -0.3f * (1.0f - t) + -2.2f * t;
                    rArmRotY = 0.3f * t;
                    rArmRotZ = 0.0f;
                    lArmRotX = -0.3f * (1.0f - t) + -2.2f * t;
                    lArmRotY = -0.3f * t;
                    lArmRotZ = 0.0f;
                    legRotX = 2.0f;
                    bodyYOff = 10.0f;
                    armYOff = 10.0f;
                    legYOff = 3.0f;
                } else {
                    float t = (phase - 0.8f) / 0.2f;
                    float r = 1.0f - t;
                    headRotX = 1.4f * r + 0.3f * t;
                    bodyRotX = 1.7f * r;
                    rArmRotX = -2.2f * r + -0.9f * t;
                    rArmRotY = 0.3f * r + -0.3f * t;
                    rArmRotZ = 0.0f;
                    lArmRotX = -2.2f * r + -0.9f * t;
                    lArmRotY = -0.3f * r + 0.3f * t;
                    lArmRotZ = 0.0f;
                    legRotX = 2.0f * r;
                    bodyYOff = 10.0f * r;
                    armYOff = 10.0f * r;
                    legYOff = 3.0f * r;
                }
                this.n_1700_B.u_1723_Y = headRotX;
                this.n_1700_B.v_4262_N = 0.0f;
                this.R_4764_Y.u_1723_Y = bodyRotX;
                this.G_564_y.u_1723_Y = rArmRotX;
                this.G_564_y.v_4262_N = rArmRotY;
                this.G_564_y.w_1484_f = rArmRotZ;
                this.P_1922_E.u_1723_Y = lArmRotX;
                this.P_1922_E.v_4262_N = lArmRotY;
                this.P_1922_E.w_1484_f = lArmRotZ;
                this.u_1723_Y.u_1723_Y = legRotX;
                this.v_4262_N.u_1723_Y = legRotX;
                this.R_4764_Y.G_564_y = bodyYOff;
                this.n_1700_B.G_564_y = bodyYOff;
                this.G_564_y.G_564_y = 2.0f + armYOff;
                this.P_1922_E.G_564_y = 2.0f + armYOff;
                this.u_1723_Y.G_564_y = 12.0f + legYOff;
                this.v_4262_N.G_564_y = 12.0f + legYOff;
            } else if (mode.equals("\u0410\u043b\u044c\u0444\u0430 \u0445\u043e\u0434\u044c\u0431\u0430")) {
                float legSpread;
                float armFlap;
                this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 2.0f * limbSwingAmount;
                this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 2.0f * limbSwingAmount;
                this.G_564_y.w_1484_f = armFlap = (u_530_F.J_1907_R(limbSwing * 0.2312f) + 1.0f) * 1.0f * limbSwingAmount;
                this.P_1922_E.w_1484_f = -armFlap;
                this.G_564_y.v_4262_N = 0.0f;
                this.P_1922_E.v_4262_N = 0.0f;
                this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
                this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
                this.u_1723_Y.w_1484_f = legSpread = u_530_F.J_1907_R(limbSwing * 0.6662f) * 0.4f * limbSwingAmount;
                this.v_4262_N.w_1484_f = -legSpread;
                this.R_4764_Y.v_4262_N = 0.0f;
                this.R_4764_Y.u_1723_Y = 0.0f;
                this.R_4764_Y.w_1484_f = 0.0f;
            }
        }
        this.u_1723_Y.v_4262_N = 0.0f;
        this.v_4262_N.v_4262_N = 0.0f;
        this.u_1723_Y.w_1484_f = 0.0f;
        this.v_4262_N.w_1484_f = 0.0f;
        if (this.Q_4569_t) {
            this.G_564_y.u_1723_Y += -0.62831855f;
            this.P_1922_E.u_1723_Y += -0.62831855f;
            this.u_1723_Y.u_1723_Y = -1.4137167f;
            this.u_1723_Y.v_4262_N = 0.31415927f;
            this.u_1723_Y.w_1484_f = 0.07853982f;
            this.v_4262_N.u_1723_Y = -1.4137167f;
            this.v_4262_N.v_4262_N = -0.31415927f;
            this.v_4262_N.w_1484_f = -0.07853982f;
        }
        this.G_564_y.v_4262_N = 0.0f;
        this.P_1922_E.v_4262_N = 0.0f;
        boolean flag2 = ((r_4811_B)entityIn).d_2169_p() == k_4231_L.J_1907_R;
        boolean bl = flag3 = flag2 ? this.w_1484_f.n_1700_B() : this.t_148_a.n_1700_B();
        if (flag2 != flag3) {
            this.R_4764_Y(entityIn);
            this.J_1907_R(entityIn);
        } else {
            this.J_1907_R(entityIn);
            this.R_4764_Y(entityIn);
        }
        this.n_1700_B(entityIn, ageInTicks);
        Emotions emotionsSneak = Emotions.h_1847_R();
        boolean bl2 = hideSneakAnim = emotionsSneak != null && emotionsSneak.w_1484_f() && !emotionsSneak.Y_601_j();
        if (this.s_956_w && !hideSneakAnim) {
            this.R_4764_Y.u_1723_Y = 0.5f;
            this.G_564_y.u_1723_Y += 0.4f;
            this.P_1922_E.u_1723_Y += 0.4f;
            this.u_1723_Y.P_1922_E = 4.0f;
            this.v_4262_N.P_1922_E = 4.0f;
            this.u_1723_Y.G_564_y = 12.2f;
            this.v_4262_N.G_564_y = 12.2f;
            this.n_1700_B.G_564_y = 4.2f;
            this.R_4764_Y.G_564_y = 3.2f;
            this.P_1922_E.G_564_y = 5.2f;
            this.G_564_y.G_564_y = 5.2f;
        } else {
            this.R_4764_Y.u_1723_Y = 0.0f;
            this.u_1723_Y.P_1922_E = 0.1f;
            this.v_4262_N.P_1922_E = 0.1f;
            this.u_1723_Y.G_564_y = 12.0f;
            this.v_4262_N.G_564_y = 12.0f;
            this.n_1700_B.G_564_y = 0.0f;
            this.R_4764_Y.G_564_y = 0.0f;
            this.P_1922_E.G_564_y = 2.0f;
            this.G_564_y.G_564_y = 2.0f;
        }
        AnimationUtils.n_1700_B(this.G_564_y, this.P_1922_E, ageInTicks);
        Emotions emotionsSwim = Emotions.h_1847_R();
        boolean bl3 = hideSwimAnim = emotionsSwim != null && emotionsSwim.w_1484_f() && !emotionsSwim.Y_601_j();
        if (this.u_2550_I > 0.0f && !hideSwimAnim) {
            float f3;
            float f1 = limbSwing % 26.0f;
            k_4231_L handside = this.n_1700_B(entityIn);
            float f2 = handside == k_4231_L.J_1907_R && this.h_1847_R > 0.0f ? 0.0f : this.u_2550_I;
            float f4 = f3 = handside == k_4231_L.n_1700_B && this.h_1847_R > 0.0f ? 0.0f : this.u_2550_I;
            if (f1 < 14.0f) {
                this.P_1922_E.u_1723_Y = this.n_1700_B(f3, this.P_1922_E.u_1723_Y, 0.0f);
                this.G_564_y.u_1723_Y = u_530_F.v_4262_N(f2, this.G_564_y.u_1723_Y, 0.0f);
                this.P_1922_E.v_4262_N = this.n_1700_B(f3, this.P_1922_E.v_4262_N, (float)Math.PI);
                this.G_564_y.v_4262_N = u_530_F.v_4262_N(f2, this.G_564_y.v_4262_N, (float)Math.PI);
                this.P_1922_E.w_1484_f = this.n_1700_B(f3, this.P_1922_E.w_1484_f, (float)Math.PI + 1.8707964f * this.n_1700_B(f1) / this.n_1700_B(14.0f));
                this.G_564_y.w_1484_f = u_530_F.v_4262_N(f2, this.G_564_y.w_1484_f, (float)Math.PI - 1.8707964f * this.n_1700_B(f1) / this.n_1700_B(14.0f));
            } else if (f1 >= 14.0f && f1 < 22.0f) {
                float f6 = (f1 - 14.0f) / 8.0f;
                this.P_1922_E.u_1723_Y = this.n_1700_B(f3, this.P_1922_E.u_1723_Y, 1.5707964f * f6);
                this.G_564_y.u_1723_Y = u_530_F.v_4262_N(f2, this.G_564_y.u_1723_Y, 1.5707964f * f6);
                this.P_1922_E.v_4262_N = this.n_1700_B(f3, this.P_1922_E.v_4262_N, (float)Math.PI);
                this.G_564_y.v_4262_N = u_530_F.v_4262_N(f2, this.G_564_y.v_4262_N, (float)Math.PI);
                this.P_1922_E.w_1484_f = this.n_1700_B(f3, this.P_1922_E.w_1484_f, 5.012389f - 1.8707964f * f6);
                this.G_564_y.w_1484_f = u_530_F.v_4262_N(f2, this.G_564_y.w_1484_f, 1.2707963f + 1.8707964f * f6);
            } else if (f1 >= 22.0f && f1 < 26.0f) {
                float f42 = (f1 - 22.0f) / 4.0f;
                this.P_1922_E.u_1723_Y = this.n_1700_B(f3, this.P_1922_E.u_1723_Y, 1.5707964f - 1.5707964f * f42);
                this.G_564_y.u_1723_Y = u_530_F.v_4262_N(f2, this.G_564_y.u_1723_Y, 1.5707964f - 1.5707964f * f42);
                this.P_1922_E.v_4262_N = this.n_1700_B(f3, this.P_1922_E.v_4262_N, (float)Math.PI);
                this.G_564_y.v_4262_N = u_530_F.v_4262_N(f2, this.G_564_y.v_4262_N, (float)Math.PI);
                this.P_1922_E.w_1484_f = this.n_1700_B(f3, this.P_1922_E.w_1484_f, (float)Math.PI);
                this.G_564_y.w_1484_f = u_530_F.v_4262_N(f2, this.G_564_y.w_1484_f, (float)Math.PI);
            }
            float f7 = 0.3f;
            float f5 = 0.33333334f;
            this.v_4262_N.u_1723_Y = u_530_F.v_4262_N(this.u_2550_I, this.v_4262_N.u_1723_Y, 0.3f * u_530_F.J_1907_R(limbSwing * 0.33333334f + (float)Math.PI));
            this.u_1723_Y.u_1723_Y = u_530_F.v_4262_N(this.u_2550_I, this.u_1723_Y.u_1723_Y, 0.3f * u_530_F.J_1907_R(limbSwing * 0.33333334f));
        }
        this.J_1907_R.n_1700_B(this.n_1700_B);
    }

    private void n_1700_B(String emotion, float time, float limbSwing, float limbSwingAmount, float f) {
        this.n_1700_B.u_1723_Y = 0.0f;
        this.n_1700_B.v_4262_N = 0.0f;
        this.n_1700_B.w_1484_f = 0.0f;
        this.R_4764_Y.u_1723_Y = 0.0f;
        this.R_4764_Y.v_4262_N = 0.0f;
        this.R_4764_Y.w_1484_f = 0.0f;
        this.G_564_y.u_1723_Y = 0.0f;
        this.G_564_y.v_4262_N = 0.0f;
        this.G_564_y.w_1484_f = 0.0f;
        this.P_1922_E.u_1723_Y = 0.0f;
        this.P_1922_E.v_4262_N = 0.0f;
        this.P_1922_E.w_1484_f = 0.0f;
        this.u_1723_Y.u_1723_Y = 0.0f;
        this.u_1723_Y.v_4262_N = 0.0f;
        this.u_1723_Y.w_1484_f = 0.0f;
        this.v_4262_N.u_1723_Y = 0.0f;
        this.v_4262_N.v_4262_N = 0.0f;
        this.v_4262_N.w_1484_f = 0.0f;
        this.n_1700_B.G_564_y = 0.0f;
        this.R_4764_Y.G_564_y = 0.0f;
        this.G_564_y.R_4764_Y = -5.0f;
        this.G_564_y.G_564_y = 2.0f;
        this.G_564_y.P_1922_E = 0.0f;
        this.P_1922_E.R_4764_Y = 5.0f;
        this.P_1922_E.G_564_y = 2.0f;
        this.P_1922_E.P_1922_E = 0.0f;
        this.u_1723_Y.G_564_y = 12.0f;
        this.u_1723_Y.P_1922_E = 0.1f;
        this.v_4262_N.G_564_y = 12.0f;
        this.v_4262_N.P_1922_E = 0.1f;
        if (emotion.equals("\u041f\u0440\u0438\u0432\u0435\u0442\u0441\u0442\u0432\u0438\u0435")) {
            this.G_564_y.u_1723_Y = -3.0f;
            this.G_564_y.w_1484_f = -0.5f;
        } else if (emotion.equals("\u0422\u0430\u043d\u0435\u0446")) {
            this.R_4764_Y.v_4262_N = 0.2f;
            this.n_1700_B.v_4262_N = 0.3f;
            this.G_564_y.u_1723_Y = -1.5f;
            this.G_564_y.w_1484_f = 0.5f;
            this.P_1922_E.u_1723_Y = -1.2f;
            this.P_1922_E.w_1484_f = -0.5f;
            this.u_1723_Y.u_1723_Y = 0.3f;
            this.v_4262_N.u_1723_Y = -0.3f;
        } else if (emotion.equals("\u0414\u0440\u043e\u0447\u043a\u0430")) {
            this.G_564_y.u_1723_Y = -0.3f;
            this.G_564_y.v_4262_N = -0.0f;
            this.G_564_y.w_1484_f = -0.5f;
            this.n_1700_B.u_1723_Y = 0.3f;
        } else if (emotion.equals("\u041d\u0430\u043c\u0430\u0437")) {
            this.n_1700_B.u_1723_Y = 0.3f;
            this.G_564_y.u_1723_Y = -0.8f;
            this.G_564_y.v_4262_N = -0.3f;
            this.P_1922_E.u_1723_Y = -0.8f;
            this.P_1922_E.v_4262_N = 0.3f;
        } else if (emotion.equals("\u0410\u043b\u044c\u0444\u0430 \u0445\u043e\u0434\u044c\u0431\u0430")) {
            float legSpread;
            float armFlap;
            this.G_564_y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 2.0f * limbSwingAmount;
            this.P_1922_E.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 2.0f * limbSwingAmount;
            this.G_564_y.w_1484_f = armFlap = (u_530_F.J_1907_R(limbSwing * 0.2312f) + 1.0f) * 1.0f * limbSwingAmount;
            this.P_1922_E.w_1484_f = -armFlap;
            this.u_1723_Y.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
            this.v_4262_N.u_1723_Y = u_530_F.J_1907_R(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
            this.u_1723_Y.w_1484_f = legSpread = u_530_F.J_1907_R(limbSwing * 0.6662f) * 0.4f * limbSwingAmount;
            this.v_4262_N.w_1484_f = -legSpread;
        }
    }

    private void J_1907_R(T p_241654_1_) {
        switch (this.t_148_a.ordinal()) {
            case 0: {
                this.G_564_y.v_4262_N = 0.0f;
                break;
            }
            case 2: {
                this.G_564_y.u_1723_Y = this.G_564_y.u_1723_Y * 0.5f - 0.9424779f;
                this.G_564_y.v_4262_N = -0.5235988f;
                break;
            }
            case 1: {
                this.G_564_y.u_1723_Y = this.G_564_y.u_1723_Y * 0.5f - 0.31415927f;
                this.G_564_y.v_4262_N = 0.0f;
                break;
            }
            case 4: {
                this.G_564_y.u_1723_Y = this.G_564_y.u_1723_Y * 0.5f - (float)Math.PI;
                this.G_564_y.v_4262_N = 0.0f;
                break;
            }
            case 3: {
                this.G_564_y.v_4262_N = -0.1f + this.n_1700_B.v_4262_N;
                this.P_1922_E.v_4262_N = 0.1f + this.n_1700_B.v_4262_N + 0.4f;
                this.G_564_y.u_1723_Y = -1.5707964f + this.n_1700_B.u_1723_Y;
                this.P_1922_E.u_1723_Y = -1.5707964f + this.n_1700_B.u_1723_Y;
                break;
            }
            case 5: {
                AnimationUtils.n_1700_B(this.G_564_y, this.P_1922_E, p_241654_1_, true);
                break;
            }
            case 6: {
                AnimationUtils.n_1700_B(this.G_564_y, this.P_1922_E, this.n_1700_B, true);
            }
        }
    }

    private void R_4764_Y(T p_241655_1_) {
        switch (this.w_1484_f.ordinal()) {
            case 0: {
                this.P_1922_E.v_4262_N = 0.0f;
                break;
            }
            case 2: {
                this.P_1922_E.u_1723_Y = this.P_1922_E.u_1723_Y * 0.5f - 0.9424779f;
                this.P_1922_E.v_4262_N = 0.5235988f;
                break;
            }
            case 1: {
                this.P_1922_E.u_1723_Y = this.P_1922_E.u_1723_Y * 0.5f - 0.31415927f;
                this.P_1922_E.v_4262_N = 0.0f;
                break;
            }
            case 4: {
                this.P_1922_E.u_1723_Y = this.P_1922_E.u_1723_Y * 0.5f - (float)Math.PI;
                this.P_1922_E.v_4262_N = 0.0f;
                break;
            }
            case 3: {
                this.G_564_y.v_4262_N = -0.1f + this.n_1700_B.v_4262_N - 0.4f;
                this.P_1922_E.v_4262_N = 0.1f + this.n_1700_B.v_4262_N;
                this.G_564_y.u_1723_Y = -1.5707964f + this.n_1700_B.u_1723_Y;
                this.P_1922_E.u_1723_Y = -1.5707964f + this.n_1700_B.u_1723_Y;
                break;
            }
            case 5: {
                AnimationUtils.n_1700_B(this.G_564_y, this.P_1922_E, p_241655_1_, false);
                break;
            }
            case 6: {
                AnimationUtils.n_1700_B(this.G_564_y, this.P_1922_E, this.n_1700_B, false);
            }
        }
    }

    protected void n_1700_B(T p_230486_1_, float p_230486_2_) {
        if (!(this.h_1847_R <= 0.0f)) {
            k_4231_L handside = this.n_1700_B(p_230486_1_);
            e_4189_z modelrenderer = this.n_1700_B(handside);
            float f = this.h_1847_R;
            this.R_4764_Y.v_4262_N = u_530_F.n_1700_B(u_530_F.R_4764_Y(f) * ((float)Math.PI * 2)) * 0.2f;
            if (handside == k_4231_L.n_1700_B) {
                this.R_4764_Y.v_4262_N *= -1.0f;
            }
            this.G_564_y.P_1922_E = u_530_F.n_1700_B(this.R_4764_Y.v_4262_N) * 5.0f;
            this.G_564_y.R_4764_Y = -u_530_F.J_1907_R(this.R_4764_Y.v_4262_N) * 5.0f;
            this.P_1922_E.P_1922_E = -u_530_F.n_1700_B(this.R_4764_Y.v_4262_N) * 5.0f;
            this.P_1922_E.R_4764_Y = u_530_F.J_1907_R(this.R_4764_Y.v_4262_N) * 5.0f;
            this.G_564_y.v_4262_N += this.R_4764_Y.v_4262_N;
            this.P_1922_E.v_4262_N += this.R_4764_Y.v_4262_N;
            this.P_1922_E.u_1723_Y += this.R_4764_Y.v_4262_N;
            f = 1.0f - this.h_1847_R;
            f *= f;
            f *= f;
            f = 1.0f - f;
            float f1 = u_530_F.n_1700_B(f * (float)Math.PI);
            float f2 = u_530_F.n_1700_B(this.h_1847_R * (float)Math.PI) * -(this.n_1700_B.u_1723_Y - 0.7f) * 0.75f;
            modelrenderer.u_1723_Y = (float)((double)modelrenderer.u_1723_Y - ((double)f1 * 1.2 + (double)f2));
            modelrenderer.v_4262_N += this.R_4764_Y.v_4262_N * 2.0f;
            modelrenderer.w_1484_f += u_530_F.n_1700_B(this.h_1847_R * (float)Math.PI) * -0.4f;
        }
    }

    protected float n_1700_B(float angleIn, float maxAngleIn, float mulIn) {
        float f = (mulIn - maxAngleIn) % ((float)Math.PI * 2);
        if (f < (float)(-Math.PI)) {
            f += (float)Math.PI * 2;
        }
        if (f >= (float)Math.PI) {
            f -= (float)Math.PI * 2;
        }
        return maxAngleIn + angleIn * f;
    }

    private float n_1700_B(float limbSwing) {
        return -65.0f * limbSwing + limbSwing * limbSwing;
    }

    @Override
    public void n_1700_B(n_1658_l<T> modelIn) {
        super.n_1700_B(modelIn);
        modelIn.w_1484_f = this.w_1484_f;
        modelIn.t_148_a = this.t_148_a;
        modelIn.s_956_w = this.s_956_w;
        modelIn.n_1700_B.n_1700_B(this.n_1700_B);
        modelIn.J_1907_R.n_1700_B(this.J_1907_R);
        modelIn.R_4764_Y.n_1700_B(this.R_4764_Y);
        modelIn.G_564_y.n_1700_B(this.G_564_y);
        modelIn.P_1922_E.n_1700_B(this.P_1922_E);
        modelIn.u_1723_Y.n_1700_B(this.u_1723_Y);
        modelIn.v_4262_N.n_1700_B(this.v_4262_N);
    }

    public void a_(boolean visible) {
        this.n_1700_B.s_956_w = visible;
        this.J_1907_R.s_956_w = visible;
        this.R_4764_Y.s_956_w = visible;
        this.G_564_y.s_956_w = visible;
        this.P_1922_E.s_956_w = visible;
        this.u_1723_Y.s_956_w = visible;
        this.v_4262_N.s_956_w = visible;
    }

    @Override
    public void n_1700_B(k_4231_L sideIn, g_221_o matrixStackIn) {
        this.n_1700_B(sideIn).n_1700_B(matrixStackIn);
    }

    protected e_4189_z n_1700_B(k_4231_L side) {
        return side == k_4231_L.n_1700_B ? this.P_1922_E : this.G_564_y;
    }

    @Override
    public e_4189_z R_4764_Y() {
        return this.n_1700_B;
    }

    protected k_4231_L n_1700_B(T entityIn) {
        k_4231_L handside = ((r_4811_B)entityIn).d_2169_p();
        return ((r_4811_B)entityIn).C_290_v == x_1688_C.n_1700_B ? handside : handside.n_1700_B();
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(false);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(false);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(false);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(true);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B(false);
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B(true);
        public static final /* enum */ n_1700_B v_4262_N = new n_1700_B(true);
        private final boolean w_1484_f;
        private static final /* synthetic */ n_1700_B[] t_148_a;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_148_a.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(boolean p_i241257_3_) {
            this.w_1484_f = p_i241257_3_;
        }

        public boolean n_1700_B() {
            return this.w_1484_f;
        }

        private static /* synthetic */ n_1700_B[] J_1907_R() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y, v_4262_N};
        }

        static {
            t_148_a = lightning.product.n_1658_l$n_1700_B.J_1907_R();
        }
    }
}



