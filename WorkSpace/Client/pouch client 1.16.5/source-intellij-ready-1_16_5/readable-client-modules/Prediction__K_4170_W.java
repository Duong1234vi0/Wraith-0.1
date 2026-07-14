/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.text.DecimalFormat;
import lightning.product.E_4925_L;
import lightning.product.E_688_b;
import lightning.product.F_489_x;
import lightning.product.G_3416_z;
import lightning.product.H_2034_c;
import lightning.product.H_2506_c;
import lightning.product.H_3699_T;
import lightning.product.I_3710_B;
import lightning.product.I_4477_R;
import lightning.product.K_1200_E;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.b_2152_i;
import lightning.product.b_3528_u;
import lightning.product.c_1514_x;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.l_3370_o;
import lightning.product.p_1977_n;
import lightning.product.q_1613_l;
import lightning.product.q_3148_R;
import lightning.product.q_4592_V;
import lightning.product.v_2826_q;
import lightning.product.w_2989_N;
import lightning.product.y_2603_k;
import org.joml.Vector2f;
import org.lwjgl.opengl.GL11;

public class K_4170_W
extends X_3546_T
implements b_2152_i {
    private final N_4463_r v_4262_N = new N_4463_r("\u0421\u043d\u0430\u0440\u044f\u0434\u044b", new p_1977_n("\u042d\u043d\u0434\u0435\u0440 \u041f\u0451\u0440\u043b", true), new p_1977_n("\u0421\u0442\u0440\u0435\u043b\u0430", true), new p_1977_n("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446", true));
    private static final DecimalFormat w_1484_f = new DecimalFormat("0.0");
    private p_1977_n t_148_a = new p_1977_n("\u0411\u043b\u044e\u0440 \u0444\u043e\u043d", false);
    private h_2367_h s_956_w = new h_2367_h("\u0424\u043e\u043d", true, H_2506_c.n_1700_B("#141416FF"));

    public K_4170_W() {
        super("Prediction", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.t_148_a, this.s_956_w);
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u e) {
        for (N_4263_v entity : K_4170_W.c_3005_b.Y_601_j.J_1907_R()) {
            if (!(entity instanceof w_2989_N && this.v_4262_N.J_1907_R("\u042d\u043d\u0434\u0435\u0440 \u041f\u0451\u0440\u043b") != false || entity instanceof H_3699_T && this.v_4262_N.J_1907_R("\u0421\u0442\u0440\u0435\u043b\u0430") != false) && (!(entity instanceof E_4925_L) || !this.v_4262_N.J_1907_R("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue()) || entity.A_1038_p == entity.X_2960_b() && entity.r_715_M == entity.O_3598_v() && entity.i_1637_u == entity.l_2647_k()) continue;
            q_1613_l item = entity instanceof w_2989_N ? q_4592_V.v_2746_S : (entity instanceof H_3699_T ? q_4592_V.g_24_p : q_4592_V.P_2605_j);
            e_2866_D pearlPosition = entity.s_4990_V();
            e_2866_D pearlMotion = entity.I_4348_c();
            e_2866_D lastPosition = pearlPosition;
            int steps = 0;
            for (int i = 0; i <= 300; ++i) {
                ++steps;
                lastPosition = pearlPosition;
                pearlPosition = pearlPosition.P_1922_E(pearlMotion);
                e_2866_D motionUpdated = pearlMotion;
                if ((entity.a_2180_A() || K_4170_W.c_3005_b.Y_601_j.getBlockState(new c_1514_x(pearlPosition)).J_1907_R() == a_3742_W.c_3005_b) && !(entity instanceof E_4925_L)) {
                    float scale = entity instanceof w_2989_N ? 0.8f : 0.6f;
                    motionUpdated = motionUpdated.n_1700_B((double)scale);
                } else {
                    motionUpdated = motionUpdated.n_1700_B((double)0.99f);
                }
                if (!entity.u_744_e()) {
                    motionUpdated.R_4764_Y = motionUpdated.R_4764_Y - (entity instanceof w_2989_N ? 0.03 : 0.05);
                }
                pearlMotion = motionUpdated;
                H_2034_c rayTraceContext = new H_2034_c(lastPosition, pearlPosition, H_2034_c.n_1700_B.n_1700_B, H_2034_c.J_1907_R.n_1700_B, K_4170_W.c_3005_b.Y_259_p);
                G_3416_z blockHitResult = K_4170_W.c_3005_b.Y_601_j.n_1700_B(rayTraceContext);
                if (blockHitResult.R_4764_Y() == I_3710_B.n_1700_B.J_1907_R || pearlPosition.R_4764_Y <= 0.0) break;
            }
            Vector2f position = v_2826_q.n_1700_B(lastPosition.J_1907_R, lastPosition.R_4764_Y, lastPosition.G_564_y);
            if (position.x == Float.MAX_VALUE && position.y == Float.MAX_VALUE) continue;
            float timeInSeconds = (float)steps * 0.05f;
            float x = position.x;
            float y = position.y + 3.0f;
            String timeText = w_1484_f.format(timeInSeconds) + " \u0441\u0435\u043a";
            float timeWidth = l_3370_o.J_1907_R[14].n_1700_B(timeText) / 2.0f;
            if (this.t_148_a.t_148_a().booleanValue()) {
                F_489_x.n_1700_B(e.J_1907_R(), x - 20.0f, y - 3.0f, l_3370_o.J_1907_R[14].n_1700_B(timeText) + 15.5f, 11.0f, 2.0f, (int)((Integer)this.s_956_w.J_1907_R()), 1.0f);
            } else {
                F_489_x.n_1700_B(e.J_1907_R(), x - 20.0f, y - 3.0f, l_3370_o.J_1907_R[14].n_1700_B(timeText) + 15.5f, 11.0f, 2.0f, (int)((Integer)this.s_956_w.J_1907_R()));
            }
            Z_1993_T itemStack = new Z_1993_T(item);
            F_489_x.n_1700_B(itemStack, x - 18.0f, y - 1.5f, 0.5f, false);
            l_3370_o.J_1907_R[14].n_1700_B(e.J_1907_R(), timeText, (double)(x - timeWidth / 2.0f - 2.0f), (double)(y + 0.5f), -1);
        }
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R e) {
        g_221_o matrix = new g_221_o();
        c_4037_x.v_4276_D();
        c_4037_x.n_1700_B(matrix.R_4764_Y().n_1700_B());
        c_4037_x.J_1907_R(-c_3005_b.O_508_d().renderPosX(), -c_3005_b.O_508_d().renderPosY(), -c_3005_b.O_508_d().renderPosZ());
        c_4037_x.Y_601_j();
        c_4037_x.s_2632_s();
        c_4037_x.e_4240_b();
        c_4037_x.t_1786_h();
        GL11.glEnable((int)2848);
        c_4037_x.G_564_y(2.5f);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        A_4115_X.n_1700_B(1, E_688_b.Y_601_j);
        for (N_4263_v entity : K_4170_W.c_3005_b.Y_601_j.J_1907_R()) {
            if (!(entity instanceof w_2989_N && this.v_4262_N.J_1907_R("\u042d\u043d\u0434\u0435\u0440 \u041f\u0451\u0440\u043b") != false || entity instanceof H_3699_T && this.v_4262_N.J_1907_R("\u0421\u0442\u0440\u0435\u043b\u0430") != false) && (!(entity instanceof E_4925_L) || !this.v_4262_N.J_1907_R("\u0422\u0440\u0435\u0437\u0443\u0431\u0435\u0446").booleanValue()) || entity.A_1038_p == entity.X_2960_b() && entity.r_715_M == entity.O_3598_v() && entity.i_1637_u == entity.l_2647_k()) continue;
            this.n_1700_B(entity);
        }
        Y_1740_V.J_1907_R();
        c_4037_x.N_4405_n();
        c_4037_x.x_607_J();
        c_4037_x.Y_259_p();
        GL11.glDisable((int)2848);
        c_4037_x.J_1907_R(c_3005_b.O_508_d().renderPosX(), c_3005_b.O_508_d().renderPosY(), c_3005_b.O_508_d().renderPosZ());
        c_4037_x.d_2461_k();
    }

    private void n_1700_B(N_4263_v pearl) {
        e_2866_D pearlPosition = pearl.s_4990_V().J_1907_R(0.0, 0.0, 0.0);
        e_2866_D pearlMotion = pearl.I_4348_c();
        for (int i = 0; i <= 300; ++i) {
            e_2866_D lastPosition = pearlPosition;
            pearlPosition = pearlPosition.P_1922_E(pearlMotion);
            e_2866_D motionUpdated = pearlMotion;
            if ((pearl.a_2180_A() || K_4170_W.c_3005_b.Y_601_j.getBlockState(new c_1514_x(lastPosition)).J_1907_R() == a_3742_W.c_3005_b) && !(pearl instanceof E_4925_L)) {
                float scale = pearl instanceof w_2989_N ? 0.8f : 0.6f;
                motionUpdated = motionUpdated.n_1700_B((double)scale);
            } else {
                motionUpdated = motionUpdated.n_1700_B((double)0.99f);
            }
            if (!pearl.u_744_e()) {
                motionUpdated.R_4764_Y = motionUpdated.R_4764_Y - (pearl instanceof w_2989_N ? 0.03 : 0.05);
            }
            pearlMotion = motionUpdated;
            H_2034_c rayTraceContext = new H_2034_c(lastPosition, pearlPosition, H_2034_c.n_1700_B.n_1700_B, H_2034_c.J_1907_R.n_1700_B, K_4170_W.c_3005_b.Y_259_p);
            G_3416_z blockHitResult = K_4170_W.c_3005_b.Y_601_j.n_1700_B(rayTraceContext);
            if (blockHitResult.R_4764_Y() == I_3710_B.n_1700_B.J_1907_R || pearlPosition.R_4764_Y <= 0.0) break;
            int color = H_2506_c.J_1907_R(3, i * 8, q_3148_R.n_1700_B(K_1200_E.J_1907_R), H_2506_c.J_1907_R(q_3148_R.n_1700_B(K_1200_E.J_1907_R), 0.5f));
            A_4115_X.pos(lastPosition.J_1907_R, lastPosition.R_4764_Y, lastPosition.G_564_y).n_1700_B(color).endVertex();
            A_4115_X.pos(pearlPosition.J_1907_R, pearlPosition.R_4764_Y, pearlPosition.G_564_y).n_1700_B(color).endVertex();
        }
    }
}

