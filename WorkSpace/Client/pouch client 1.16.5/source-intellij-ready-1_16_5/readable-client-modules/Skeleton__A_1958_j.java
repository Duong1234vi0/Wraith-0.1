/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import lightning.product.E_688_b;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_686_h;
import lightning.product.K_1200_E;
import lightning.product.K_2034_Y;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.X_3546_T;
import lightning.product.X_4340_E;
import lightning.product.Y_1740_V;
import lightning.product.Z_2491_A;
import lightning.product.a_3913_L;
import lightning.product.b_2152_i;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.e_4189_z;
import lightning.product.g_221_o;
import lightning.product.h_4311_S;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_3148_R;
import lightning.product.u_530_F;
import lightning.product.y_2603_k;
import org.lwjgl.opengl.GL11;

public class A_1958_j
extends X_3546_T
implements b_2152_i {
    private final p_1977_n v_4262_N = new p_1977_n("\u0418\u0433\u0440\u043e\u043a\u0438", true);
    private final p_1977_n w_1484_f = new p_1977_n("\u0414\u0440\u0443\u0437\u044c\u044f", true);
    private final p_1977_n t_148_a = new p_1977_n("\u041d\u0430 \u0441\u0435\u0431\u044f", false);
    private final I_686_h s_956_w = new I_686_h("\u0422\u043e\u043b\u0449\u0438\u043d\u0430 \u043b\u0438\u043d\u0438\u0439", 2.0f, 0.5f, 5.0f, 0.1f);
    private static final float u_2550_I = 128.0f;

    public A_1958_j() {
        super("Skeleton", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w);
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R e) {
        if (A_1958_j.c_3005_b.Y_601_j == null || A_1958_j.c_3005_b.Y_259_p == null) {
            return;
        }
        c_4037_x.v_4276_D();
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_4037_x.t_1786_h();
        GL11.glEnable((int)2848);
        c_4037_x.G_564_y(((Float)this.s_956_w.J_1907_R()).floatValue());
        A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
        for (a_3913_L a_3913_L2 : A_1958_j.c_3005_b.Y_601_j.N_4405_n()) {
            float dist;
            if (!this.n_1700_B(a_3913_L2) || (dist = (float)A_1958_j.c_3005_b.Y_259_p.G_564_y((N_4263_v)a_3913_L2)) > 16384.0f) continue;
            this.n_1700_B(a_3913_L2, e.J_1907_R());
        }
        if (this.t_148_a.t_148_a().booleanValue() && A_1958_j.c_3005_b.Y_259_p != null && A_1958_j.c_3005_b.Y_259_p.H_3699_F() && A_1958_j.c_3005_b.Y_259_p.t_2577_l >= 3 && !A_1958_j.c_3005_b.P_4830_p.P_4830_p().n_1700_B()) {
            this.n_1700_B(A_1958_j.c_3005_b.Y_259_p, e.J_1907_R());
        }
        Y_1740_V.J_1907_R();
        c_4037_x.N_4405_n();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        GL11.glDisable((int)2848);
        c_4037_x.d_2461_k();
    }

    private boolean n_1700_B(a_3913_L player) {
        if (player == null || !player.H_3699_F() || player.t_2577_l < 3) {
            return false;
        }
        if (player == A_1958_j.c_3005_b.Y_259_p) {
            return false;
        }
        boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
        if (isFriend && !this.w_1484_f.t_148_a().booleanValue()) {
            return false;
        }
        return isFriend || this.v_4262_N.t_148_a() != false;
    }

    private void n_1700_B(a_3913_L player, float partialTicks) {
        if (!(player instanceof X_4340_E)) {
            return;
        }
        X_4340_E clientPlayer = (X_4340_E)player;
        int color = this.J_1907_R(player, partialTicks);
        h_4311_S renderer = (h_4311_S)c_3005_b.O_508_d().n_1700_B(clientPlayer);
        K_2034_Y model = (K_2034_Y)renderer.n_1700_B();
        float bodyYaw = u_530_F.w_1484_f(partialTicks, player.D_4361_a, player.C_1162_e);
        float limbSwingAmount = u_530_F.v_4262_N(partialTicks, player.A_3959_N, player.G_424_k);
        float limbSwing = player.i_1610_l - player.G_424_k * (1.0f - partialTicks);
        float age = (float)player.t_2577_l + partialTicks;
        float netHeadYaw = u_530_F.w_1484_f(partialTicks, player.n_2977_f, player.f_3449_S) - bodyYaw;
        float headPitch = u_530_F.v_4262_N(partialTicks, player.y_2356_n, player.f_4016_n);
        model.n_1700_B(clientPlayer, limbSwing, limbSwingAmount, partialTicks);
        model.n_1700_B(clientPlayer, limbSwing, limbSwingAmount, age, netHeadYaw, headPitch);
        g_221_o matrixStack = new g_221_o();
        this.n_1700_B(matrixStack, player, clientPlayer, partialTicks, renderer);
        e_2866_D head = this.n_1700_B(matrixStack, model.n_1700_B, 0.0f, -4.0f, 0.0f);
        e_2866_D neck = this.n_1700_B(matrixStack, model.n_1700_B, 0.0f, 0.0f, 0.0f);
        e_2866_D bodyMid = this.n_1700_B(matrixStack, model.R_4764_Y, 0.0f, 6.0f, 0.0f);
        e_2866_D bodyBottom = this.n_1700_B(matrixStack, model.R_4764_Y, 0.0f, 12.0f, 0.0f);
        e_2866_D rightShoulder = this.n_1700_B(matrixStack, model.G_564_y, -1.0f, 0.0f, 0.0f);
        e_2866_D rightElbow = this.n_1700_B(matrixStack, model.G_564_y, -1.0f, 4.5f, 0.0f);
        e_2866_D rightHand = this.n_1700_B(matrixStack, model.G_564_y, -1.0f, 9.0f, 0.0f);
        e_2866_D leftShoulder = this.n_1700_B(matrixStack, model.P_1922_E, 1.0f, 0.0f, 0.0f);
        e_2866_D leftElbow = this.n_1700_B(matrixStack, model.P_1922_E, 1.0f, 4.5f, 0.0f);
        e_2866_D leftHand = this.n_1700_B(matrixStack, model.P_1922_E, 1.0f, 9.0f, 0.0f);
        e_2866_D rightHip = this.n_1700_B(matrixStack, model.u_1723_Y, 0.0f, 0.0f, 0.0f);
        e_2866_D rightKnee = this.n_1700_B(matrixStack, model.u_1723_Y, 0.0f, 5.0f, 0.0f);
        e_2866_D rightFoot = this.n_1700_B(matrixStack, model.u_1723_Y, 0.0f, 10.0f, 0.0f);
        e_2866_D leftHip = this.n_1700_B(matrixStack, model.v_4262_N, 0.0f, 0.0f, 0.0f);
        e_2866_D leftKnee = this.n_1700_B(matrixStack, model.v_4262_N, 0.0f, 5.0f, 0.0f);
        e_2866_D leftFoot = this.n_1700_B(matrixStack, model.v_4262_N, 0.0f, 10.0f, 0.0f);
        this.n_1700_B(head, neck, color);
        this.n_1700_B(neck, bodyMid, color);
        this.n_1700_B(bodyMid, bodyBottom, color);
        this.n_1700_B(rightShoulder, rightElbow, color);
        this.n_1700_B(rightElbow, rightHand, color);
        this.n_1700_B(leftShoulder, leftElbow, color);
        this.n_1700_B(leftElbow, leftHand, color);
        this.n_1700_B(rightHip, rightKnee, color);
        this.n_1700_B(rightKnee, rightFoot, color);
        this.n_1700_B(leftHip, leftKnee, color);
        this.n_1700_B(leftKnee, leftFoot, color);
        this.n_1700_B(rightShoulder, leftShoulder, color);
        this.n_1700_B(rightHip, leftHip, color);
    }

    private void n_1700_B(g_221_o matrixStack, a_3913_L player, X_4340_E clientPlayer, float partialTicks, h_4311_S renderer) {
        e_2866_D pos = F_747_P.n_1700_B((N_4263_v)player, partialTicks);
        e_2866_D renderOffset = renderer.n_1700_B(clientPlayer, partialTicks);
        double renderX = pos.J_1907_R - renderOffset.J_1907_R - c_3005_b.O_508_d().renderPosX();
        double renderY = pos.R_4764_Y - renderOffset.R_4764_Y - c_3005_b.O_508_d().renderPosY();
        double renderZ = pos.G_564_y - renderOffset.G_564_y - c_3005_b.O_508_d().renderPosZ();
        matrixStack.n_1700_B(renderX, renderY, renderZ);
        float bodyYaw = u_530_F.w_1484_f(partialTicks, player.D_4361_a, player.C_1162_e);
        if (player.k_578_l()) {
            matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - bodyYaw));
            float elytraTicks = (float)player.h_3859_C() + partialTicks;
            float elytraProgress = u_530_F.n_1700_B(elytraTicks * elytraTicks / 100.0f, 0.0f, 1.0f);
            if (!player.B_3040_x()) {
                matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(elytraProgress * (-90.0f - player.f_4016_n)));
            }
            e_2866_D lookVec = player.t_148_a(partialTicks);
            e_2866_D motionVec = player.I_4348_c();
            double motionHorizontal = motionVec.J_1907_R * motionVec.J_1907_R + motionVec.G_564_y * motionVec.G_564_y;
            double lookHorizontal = lookVec.J_1907_R * lookVec.J_1907_R + lookVec.G_564_y * lookVec.G_564_y;
            if (motionHorizontal > 0.0 && lookHorizontal > 0.0) {
                double dot = (motionVec.J_1907_R * lookVec.J_1907_R + motionVec.G_564_y * lookVec.G_564_y) / Math.sqrt(motionHorizontal * lookHorizontal);
                double cross = motionVec.J_1907_R * lookVec.G_564_y - motionVec.G_564_y * lookVec.J_1907_R;
                matrixStack.n_1700_B(M_1336_P.G_564_y.J_1907_R((float)(Math.signum(cross) * Math.acos(dot))));
            }
        } else {
            float headYaw = u_530_F.w_1484_f(partialTicks, player.n_2977_f, player.f_3449_S);
            float yawDiff = u_530_F.v_4262_N(headYaw - bodyYaw);
            float finalYaw = bodyYaw;
            float swimAmount = clientPlayer.u_1723_Y(partialTicks);
            if (swimAmount > 0.0f) {
                finalYaw = bodyYaw - yawDiff * u_530_F.v_4262_N(swimAmount, -0.1f, -0.3f);
            } else if (player.Z_875_P()) {
                finalYaw = bodyYaw - yawDiff * 0.3f;
            }
            matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(180.0f - finalYaw));
            float pitch = u_530_F.v_4262_N(partialTicks, player.y_2356_n, player.f_4016_n);
            if (swimAmount > 0.0f) {
                float pitchRotation = player.a_2180_A() ? -90.0f - pitch : -90.0f;
                float pitchAmount = u_530_F.v_4262_N(swimAmount, 0.0f, pitchRotation);
                matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(pitchAmount));
                if (player.x_612_B()) {
                    matrixStack.n_1700_B(0.0, -1.0, 0.3);
                }
            } else if (player.Z_875_P()) {
                float crouchPitchFactor = u_530_F.n_1700_B(Math.abs(pitch) / 90.0f, 0.0f, 0.3f);
                matrixStack.n_1700_B(M_1336_P.J_1907_R.R_4764_Y(pitch * crouchPitchFactor));
            }
        }
        matrixStack.n_1700_B(-1.0f, -1.0f, 1.0f);
        matrixStack.n_1700_B(0.9375f, 0.9375f, 0.9375f);
        matrixStack.n_1700_B(0.0, -1.501, 0.0);
        if (player.Z_875_P() && !player.k_578_l()) {
            matrixStack.n_1700_B(0.0, 0.3, 0.0);
        }
    }

    private e_2866_D n_1700_B(g_221_o baseStack, e_4189_z part, float x, float y, float z) {
        baseStack.n_1700_B();
        part.n_1700_B(baseStack);
        baseStack.n_1700_B((double)x / 16.0, (double)y / 16.0, (double)z / 16.0);
        Z_2491_A vec = new Z_2491_A(0.0f, 0.0f, 0.0f, 1.0f);
        vec.n_1700_B(baseStack.R_4764_Y().n_1700_B());
        baseStack.J_1907_R();
        return new e_2866_D(vec.n_1700_B(), vec.J_1907_R(), vec.R_4764_Y());
    }

    private void n_1700_B(e_2866_D start, e_2866_D end, int color) {
        float r = (float)H_2506_c.n_1700_B(color) / 255.0f;
        float g = (float)H_2506_c.J_1907_R(color) / 255.0f;
        float b = (float)H_2506_c.R_4764_Y(color) / 255.0f;
        float a = (float)H_2506_c.G_564_y(color) / 255.0f;
        A_4115_X.pos(start.J_1907_R, start.R_4764_Y, start.G_564_y).n_1700_B(r, g, b, a).endVertex();
        A_4115_X.pos(end.J_1907_R, end.R_4764_Y, end.G_564_y).n_1700_B(r, g, b, a).endVertex();
    }

    private int J_1907_R(a_3913_L player, float partialTicks) {
        if (player == A_1958_j.c_3005_b.Y_259_p) {
            return q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        }
        boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName());
        if (isFriend) {
            return H_2506_c.n_1700_B(0, 255, 0);
        }
        float red = u_530_F.n_1700_B(((float)player.H_3699_F - partialTicks) / 10.0f, 0.0f, 1.0f);
        int baseColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
        if (red > 0.0f) {
            int redComponent = Math.min(255, H_2506_c.n_1700_B(baseColor) + (int)(red * 100.0f));
            return H_2506_c.n_1700_B(redComponent, H_2506_c.J_1907_R(baseColor), H_2506_c.R_4764_Y(baseColor));
        }
        return baseColor;
    }
}

