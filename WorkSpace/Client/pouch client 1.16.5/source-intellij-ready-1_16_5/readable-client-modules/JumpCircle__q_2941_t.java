/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.F_489_x;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_686_h;
import lightning.product.K_1200_E;
import lightning.product.P_4639_N;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.e_2866_D;
import lightning.product.g_2336_b;
import lightning.product.i_4482_j;
import lightning.product.j_755_i;
import lightning.product.q_3148_R;
import lightning.product.y_2603_k;

public class q_2941_t
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441", 1.5f, 1.0f, 2.0f, 0.1f);
    private final CopyOnWriteArrayList<n_1700_B> w_1484_f = new CopyOnWriteArrayList();
    private static final g_2336_b t_148_a = new g_2336_b("Pouch/icons/world_render/jump.png");

    public q_2941_t() {
        super("JumpCircle", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    private void n_1700_B(P_4639_N e) {
        e_2866_D pos = new e_2866_D(q_2941_t.c_3005_b.Y_259_p.O_3598_v(), q_2941_t.c_3005_b.Y_259_p.X_2960_b(), q_2941_t.c_3005_b.Y_259_p.l_2647_k());
        this.w_1484_f.add(new n_1700_B(pos));
        this.w_1484_f.add(new n_1700_B(pos));
    }

    @Y_1740_V
    private void n_1700_B(I_4477_R e) {
        if (this.w_1484_f.isEmpty()) {
            return;
        }
        for (n_1700_B circle : this.w_1484_f) {
            if (!circle.J_1907_R) {
                circle.P_1922_E.n_1700_B(1.1f);
                if (circle.u_1723_Y.J_1907_R() != 1.0f || !circle.u_1723_Y.R_4764_Y()) {
                    circle.u_1723_Y.n_1700_B(1.0f);
                }
                if (circle.P_1922_E.R_4764_Y()) {
                    circle.J_1907_R = true;
                    circle.P_1922_E.J_1907_R(1.1f);
                    circle.P_1922_E.n_1700_B(1.0f);
                }
            } else {
                circle.P_1922_E.n_1700_B(1.0f);
                if (!circle.R_4764_Y) {
                    if (circle.u_1723_Y.J_1907_R() != 1.0f || !circle.u_1723_Y.R_4764_Y()) {
                        circle.u_1723_Y.n_1700_B(1.0f);
                    }
                    if (circle.u_1723_Y.R_4764_Y()) {
                        circle.R_4764_Y = true;
                        circle.u_1723_Y.R_4764_Y(0.25f);
                        circle.u_1723_Y.n_1700_B(0.0f);
                    }
                } else {
                    if (circle.u_1723_Y.J_1907_R() != 0.0f || !circle.u_1723_Y.R_4764_Y()) {
                        circle.u_1723_Y.n_1700_B(0.0f);
                    }
                    if (circle.P_1922_E.R_4764_Y() && !circle.G_564_y) {
                        circle.G_564_y = true;
                        circle.u_1723_Y.R_4764_Y(3.0f);
                        circle.u_1723_Y.J_1907_R(circle.u_1723_Y.n_1700_B());
                        circle.u_1723_Y.n_1700_B(0.0f);
                    }
                    if (circle.u_1723_Y.R_4764_Y()) {
                        this.w_1484_f.remove(circle);
                        continue;
                    }
                }
            }
            int baseColor = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
            int darkColor = H_2506_c.J_1907_R(baseColor, 0.4f);
            int boost = 15;
            float worldX = (float)(circle.n_1700_B.J_1907_R - q_2941_t.c_3005_b.O_508_d().J_1907_R.J_1907_R().J_1907_R);
            float worldY = (float)(circle.n_1700_B.R_4764_Y - q_2941_t.c_3005_b.O_508_d().J_1907_R.J_1907_R().R_4764_Y) + 0.05f;
            float worldZ = (float)(circle.n_1700_B.G_564_y - q_2941_t.c_3005_b.O_508_d().J_1907_R.J_1907_R().G_564_y);
            float circleRad = (((Float)this.v_4262_N.J_1907_R()).floatValue() + 1.0f) * circle.P_1922_E.n_1700_B() * 0.5f;
            float innerRad = circleRad * 0.02f;
            float alpha = circle.u_1723_Y.n_1700_B();
            int innerColor = H_2506_c.J_1907_R(H_2506_c.n_1700_B(baseColor, alpha), boost);
            float rotation = (float)(System.currentTimeMillis() % 1500L) / 1500.0f * 360.0f;
            for (int i = 0; i < 20; ++i) {
                float angle = i * 18;
                float nextAngle = (i + 1) * 18;
                float rad1 = (float)Math.toRadians(angle);
                float rad2 = (float)Math.toRadians(nextAngle);
                float cos1 = (float)Math.cos(rad1);
                float sin1 = (float)Math.sin(rad1);
                float cos2 = (float)Math.cos(rad2);
                float sin2 = (float)Math.sin(rad2);
                float pos1 = (angle + rotation) % 360.0f / 360.0f;
                float pos2 = (nextAngle + rotation) % 360.0f / 360.0f;
                int color1 = H_2506_c.n_1700_B(H_2506_c.n_1700_B(darkColor, baseColor, 1.0f - Math.abs(pos1 - 0.5f) * 2.0f), alpha);
                int color2 = H_2506_c.n_1700_B(H_2506_c.n_1700_B(darkColor, baseColor, 1.0f - Math.abs(pos2 - 0.5f) * 2.0f), alpha);
                int final1 = H_2506_c.J_1907_R(color1, boost);
                int final2 = H_2506_c.J_1907_R(color2, boost);
                F_489_x.n_1700_B(t_148_a, worldX + cos1 * circleRad, worldY, worldZ + sin1 * circleRad, worldX + cos2 * circleRad, worldY, worldZ + sin2 * circleRad, worldX + cos2 * innerRad, worldY, worldZ + sin2 * innerRad, worldX + cos1 * innerRad, worldY, worldZ + sin1 * innerRad, final1, final2, innerColor, innerColor, cos1 * 0.5f + 0.5f, sin1 * 0.5f + 0.5f, cos2 * 0.5f + 0.5f, sin2 * 0.5f + 0.5f, 0.5f, 0.5f, 0.5f, 0.5f);
            }
        }
        F_489_x.J_1907_R();
    }

    private static class n_1700_B {
        e_2866_D n_1700_B;
        boolean J_1907_R;
        boolean R_4764_Y;
        boolean G_564_y;
        final j_755_i P_1922_E = new j_755_i(0.1f, 3.0f, i_4482_j.Y_601_j);
        final j_755_i u_1723_Y = new j_755_i(0.0f, 3.0f, i_4482_j.Y_601_j);

        n_1700_B(e_2866_D pos) {
            this.n_1700_B = pos;
            this.P_1922_E.n_1700_B(1.1f);
            this.u_1723_Y.n_1700_B(1.0f);
        }
    }
}

