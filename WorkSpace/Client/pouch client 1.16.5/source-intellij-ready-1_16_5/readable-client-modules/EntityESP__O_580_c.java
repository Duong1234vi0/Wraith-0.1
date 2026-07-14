/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.joml.Vector2f
 *  org.lwjgl.BufferUtils
 *  org.lwjgl.opengl.GL13
 */
package lightning.product;

import java.awt.Color;
import java.nio.FloatBuffer;
import lightning.product.E_4612_l;
import lightning.product.F_3104_Z;
import lightning.product.F_489_x;
import lightning.product.F_747_P;
import lightning.product.H_2506_c;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.I_686_h;
import lightning.product.K_1200_E;
import lightning.product.M_1336_P;
import lightning.product.N_4263_v;
import lightning.product.N_4463_r;
import lightning.product.X_3546_T;
import lightning.product.X_933_l;
import lightning.product.Y_1740_V;
import lightning.product.Z_2049_e;
import lightning.product.Z_2491_A;
import lightning.product.a_3913_L;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.e_2866_D;
import lightning.product.g_221_o;
import lightning.product.h_2367_h;
import lightning.product.k_1366_K;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.q_3115_L;
import lightning.product.q_3148_R;
import lightning.product.q_366_O;
import lightning.product.r_4811_B;
import lightning.product.s_4405_m;
import lightning.product.v_2826_q;
import lightning.product.y_2603_k;
import net.optifine.util.TextureUtils;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL13;

public class O_580_c
extends X_3546_T {
    public N_4463_r v_4262_N = new N_4463_r("\u041e\u0442\u043e\u0431\u0440\u0430\u0436\u0430\u0442\u044c", new p_1977_n("\u0418\u0433\u0440\u043e\u043a\u043e\u0432", true), new p_1977_n("\u041c\u043e\u043d\u0441\u0442\u0440\u043e\u0432", false), new p_1977_n("\u0414\u0440\u0443\u0437\u0435\u0439", false), new p_1977_n("\u0416\u0438\u0432\u043e\u0442\u043d\u044b\u0445", false), new p_1977_n("\u0421\u0435\u0431\u044f", false), new p_1977_n("\u0416\u0438\u0442\u0435\u043b\u0435\u0439", false), new p_1977_n("\u0413\u043e\u043b\u044b\u0445", false), new p_1977_n("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", true));
    private final q_366_O M_588_G = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "\u041e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u043e", "\u041a\u0432\u0430\u0434\u0440\u0430\u0442", "\u0423\u0433\u043b\u044b", "3D \u0411\u043e\u043a\u0441", "\u041e\u0442\u043a\u043b\u044e\u0447\u0435\u043d\u043e");
    private final p_1977_n P_4830_p = new p_1977_n("\u0417\u0430\u043b\u0438\u0432\u043a\u0430", false, () -> this.M_588_G.J_1907_R("3D \u0411\u043e\u043a\u0441"));
    private final p_1977_n h_1847_R = new p_1977_n("\u041f\u043e\u0432\u043e\u0440\u0430\u0447\u0438\u0432\u0430\u0442\u044c \u0437\u0430 \u0441\u0443\u0449\u043d\u043e\u0441\u0442\u044c\u044e", false, () -> this.M_588_G.J_1907_R("3D \u0411\u043e\u043a\u0441"));
    private final q_366_O Q_4569_t = new q_366_O("\u0421\u0442\u0438\u043b\u044c \u0431\u043e\u043a\u0441\u0430", "\u0421\u043f\u043b\u043e\u0448\u043d\u043e\u0439", () -> this.M_588_G.J_1907_R("3D \u0411\u043e\u043a\u0441"), "\u0421\u043f\u043b\u043e\u0448\u043d\u043e\u0439", "\u0422\u043e\u0447\u0435\u0447\u043d\u044b\u0439");
    private final q_366_O M_182_A = new q_366_O("\u0420\u0435\u0436\u0438\u043c \u0446\u0432\u0435\u0442\u0430", "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", () -> this.M_588_G.J_1907_R("\u041a\u0432\u0430\u0434\u0440\u0430\u0442") || this.M_588_G.J_1907_R("\u0423\u0433\u043b\u044b") || this.M_588_G.J_1907_R("3D \u0411\u043e\u043a\u0441"), "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u0421\u0442\u0430\u0442\u0438\u0447\u043d\u044b\u0439");
    private final h_2367_h t_1786_h = new h_2367_h("\u0426\u0432\u0435\u0442 \u0434\u0440\u0437\u0435\u0439", true, H_2506_c.n_1700_B(0, 255, 0), () -> this.v_4262_N.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439") != false && (this.M_588_G.J_1907_R("\u041a\u0432\u0430\u0434\u0440\u0430\u0442") || this.M_588_G.J_1907_R("\u0423\u0433\u043b\u044b") || this.M_588_G.J_1907_R("3D \u0411\u043e\u043a\u0441")));
    private final h_2367_h N_4405_n = new h_2367_h("\u0426\u0432\u0435\u0442", true, -1, () -> this.M_182_A.J_1907_R("\u0421\u0442\u0430\u0442\u0438\u0447\u043d\u044b\u0439"));
    private final q_366_O w_1457_N = new q_366_O("\u0411\u0430\u0440 \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f", "\u041e\u0442\u043a\u043b\u044e\u0447\u0435\u043d", "\u0421\u043e\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f", "\u0418\u043d\u0434\u0438\u0432\u0438\u0434\u0443\u0430\u043b\u044c\u043d\u044b\u0439", "\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439", "\u041e\u0442\u043a\u043b\u044e\u0447\u0435\u043d");
    private final h_2367_h Y_601_j = new h_2367_h("\u0412\u0435\u0440\u0445\u043d\u0438\u0439 \u0446\u0432\u0435\u0442", true, H_2506_c.n_1700_B(0, 255, 0), () -> this.w_1457_N.J_1907_R("\u0418\u043d\u0434\u0438\u0432\u0438\u0434\u0443\u0430\u043b\u044c\u043d\u044b\u0439"));
    private final h_2367_h Y_259_p = new h_2367_h("\u041d\u0438\u0436\u043d\u0438\u0439 \u0446\u0432\u0435\u0442", true, H_2506_c.n_1700_B(255, 0, 0), () -> this.w_1457_N.J_1907_R("\u0418\u043d\u0434\u0438\u0432\u0438\u0434\u0443\u0430\u043b\u044c\u043d\u044b\u0439"));
    private final p_1977_n Q_2552_b = new p_1977_n("\u0421\u0432\u0435\u0447\u0435\u043d\u0438\u0435", false);
    private final h_2367_h C_2741_M = new h_2367_h("\u0426\u0432\u0435\u0442 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", false, -1, this.Q_2552_b::t_148_a);
    private final I_686_h k_2293_S = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441 \u0441\u0432\u0435\u0447\u0435\u043d\u0438\u044f", 6.0f, 1.0f, 12.0f, 1.0f, this.Q_2552_b::t_148_a);
    private final p_1977_n q_2307_F = new p_1977_n("\u041e\u0431\u0432\u043e\u0434\u043a\u0430", false, this.Q_2552_b::t_148_a);
    public k_1366_K w_1484_f;
    public k_1366_K t_148_a;
    public k_1366_K s_956_w;
    public k_1366_K u_2550_I;

    public O_580_c() {
        super("EntityESP", y_2603_k.R_4764_Y);
        this.n_1700_B(this.v_4262_N, this.M_588_G, this.P_4830_p, this.h_1847_R, this.Q_4569_t, this.M_182_A, this.t_1786_h, this.N_4405_n, this.w_1457_N, this.Y_601_j, this.Y_259_p, this.Q_2552_b, this.C_2741_M, this.k_2293_S, this.q_2307_F);
    }

    public void h_1847_R() {
        this.w_1484_f = k_1366_K.J_1907_R(this.w_1484_f);
        this.t_148_a = k_1366_K.J_1907_R(this.t_148_a);
        this.s_956_w = k_1366_K.J_1907_R(this.s_956_w);
        this.u_2550_I = k_1366_K.J_1907_R(this.u_2550_I);
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R e) {
        if (this.M_588_G.J_1907_R("3D \u0411\u043e\u043a\u0441")) {
            this.J_1907_R(e);
        }
        if (!this.Q_2552_b.t_148_a().booleanValue()) {
            return;
        }
        this.h_1847_R();
        this.w_1484_f.n_1700_B(true);
        this.n_1700_B(e.J_1907_R());
        this.w_1484_f.t_148_a();
        c_3005_b.G_564_y().J_1907_R(true);
        X_933_l.v_4262_N();
    }

    @Y_1740_V
    public void n_1700_B(b_3528_u e) {
        this.R_4764_Y(e);
        this.J_1907_R(e);
        if (this.Q_2552_b.t_148_a().booleanValue() && this.w_1484_f != null && this.t_148_a != null && this.s_956_w != null) {
            this.M_182_A();
            this.t_148_a.n_1700_B(true);
            s_4405_m.P_4830_p.J_1907_R();
            this.n_1700_B(0.0f, 1.0f);
            TextureUtils.bindTexture(this.w_1484_f.v_4262_N);
            k_1366_K.R_4764_Y();
            this.n_1700_B(1.0f, 0.0f);
            TextureUtils.bindTexture(this.w_1484_f.v_4262_N);
            k_1366_K.R_4764_Y();
            s_4405_m.P_4830_p.R_4764_Y();
            this.t_148_a.t_148_a();
            this.s_956_w.n_1700_B(true);
            s_4405_m.h_1847_R.J_1907_R();
            this.J_1907_R(1.0f, 0.0f);
            TextureUtils.bindTexture(this.t_148_a.v_4262_N);
            k_1366_K.R_4764_Y();
            this.s_956_w.t_148_a();
            c_3005_b.G_564_y().J_1907_R(true);
            this.J_1907_R(0.0f, 1.0f);
            GL13.glActiveTexture((int)33984);
            TextureUtils.bindTexture(this.s_956_w.v_4262_N);
            k_1366_K.R_4764_Y();
            s_4405_m.h_1847_R.R_4764_Y();
            if (this.q_2307_F.t_148_a().booleanValue()) {
                this.u_2550_I.n_1700_B(true);
                s_4405_m.Q_4569_t.J_1907_R();
                this.Q_4569_t();
                TextureUtils.bindTexture(this.w_1484_f.v_4262_N);
                k_1366_K.R_4764_Y();
                s_4405_m.Q_4569_t.R_4764_Y();
                this.u_2550_I.t_148_a();
                c_3005_b.G_564_y().J_1907_R(true);
                TextureUtils.bindTexture(this.u_2550_I.v_4262_N);
                k_1366_K.R_4764_Y();
            }
            this.t_1786_h();
        }
    }

    private void M_182_A() {
        c_4037_x.v_4276_D();
        X_933_l.P_1922_E();
        X_933_l.n_1700_B(516, 0.0f);
        X_933_l.Q_4569_t();
        X_933_l.J_1907_R(770, 771, 1, 0);
        X_933_l.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        c_4037_x.w_1484_f(7425);
    }

    private void t_1786_h() {
        c_4037_x.w_1484_f(7424);
        X_933_l.h_1847_R();
        X_933_l.G_564_y();
        c_4037_x.d_2461_k();
    }

    private void J_1907_R(I_4477_R event) {
        if (!this.M_588_G.J_1907_R("3D \u0411\u043e\u043a\u0441")) {
            return;
        }
        if (O_580_c.c_3005_b.Y_601_j == null) {
            return;
        }
        e_2866_D view = O_580_c.c_3005_b.O_508_d().J_1907_R.J_1907_R();
        c_4037_x.v_4276_D();
        c_4037_x.J_1907_R(-view.J_1907_R, -view.R_4764_Y, -view.G_564_y);
        for (N_4263_v entity : O_580_c.c_3005_b.Y_601_j.J_1907_R()) {
            if (!entity.H_3699_F() || !v_2826_q.n_1700_B(entity) || this.J_1907_R(entity)) continue;
            float partialTicks = c_3005_b.P_2565_J();
            double interpX = entity.q_1982_R + (entity.O_3598_v() - entity.q_1982_R) * (double)partialTicks;
            double interpY = entity.w_1474_C + (entity.X_2960_b() - entity.w_1474_C) * (double)partialTicks;
            double interpZ = entity.w_612_n + (entity.l_2647_k() - entity.w_612_n) * (double)partialTicks;
            I_4817_s originalBox = entity.i_601_W();
            I_4817_s box = originalBox.offset(interpX - entity.O_3598_v(), interpY - entity.X_2960_b(), interpZ - entity.l_2647_k());
            int color = this.n_1700_B(entity);
            if (this.h_1847_R.t_148_a().booleanValue() && entity instanceof r_4811_B) {
                r_4811_B livingEntity = (r_4811_B)entity;
                float rotationYaw = livingEntity.j_276_v + (livingEntity.p_178_J - livingEntity.j_276_v) * partialTicks;
                double centerX = (box.minX + box.maxX) / 2.0;
                double centerY = (box.minY + box.maxY) / 2.0;
                double centerZ = (box.minZ + box.maxZ) / 2.0;
                double halfWidth = (box.maxX - box.minX) / 2.0;
                double halfHeight = (box.maxY - box.minY) / 2.0;
                double halfDepth = (box.maxZ - box.minZ) / 2.0;
                c_4037_x.v_4276_D();
                g_221_o matrixStack = new g_221_o();
                matrixStack.n_1700_B();
                matrixStack.n_1700_B(centerX, centerY, centerZ);
                matrixStack.n_1700_B(M_1336_P.G_564_y.R_4764_Y(-rotationYaw));
                c_4037_x.n_1700_B(matrixStack.R_4764_Y().n_1700_B());
                I_4817_s rotatedBox = new I_4817_s(-halfWidth, -halfHeight, -halfDepth, halfWidth, halfHeight, halfDepth);
                if (this.Q_4569_t.J_1907_R("\u0422\u043e\u0447\u0435\u0447\u043d\u044b\u0439")) {
                    F_489_x.J_1907_R(rotatedBox, color, this.P_4830_p.t_148_a());
                } else {
                    F_489_x.n_1700_B(rotatedBox, color, this.P_4830_p.t_148_a());
                }
                matrixStack.J_1907_R();
                c_4037_x.d_2461_k();
                continue;
            }
            if (this.Q_4569_t.J_1907_R("\u0422\u043e\u0447\u0435\u0447\u043d\u044b\u0439")) {
                F_489_x.J_1907_R(box, color, this.P_4830_p.t_148_a());
                continue;
            }
            F_489_x.n_1700_B(box, color, this.P_4830_p.t_148_a());
        }
        c_4037_x.d_2461_k();
    }

    private void J_1907_R(b_3528_u event) {
        if (!this.M_588_G.J_1907_R("\u041a\u0432\u0430\u0434\u0440\u0430\u0442") && !this.M_588_G.J_1907_R("\u0423\u0433\u043b\u044b")) {
            return;
        }
        if (O_580_c.c_3005_b.Y_601_j == null) {
            return;
        }
        for (N_4263_v base : O_580_c.c_3005_b.Y_601_j.J_1907_R()) {
            if (!base.H_3699_F() || !v_2826_q.n_1700_B(base) || this.J_1907_R(base)) continue;
            e_2866_D interpolated = F_747_P.n_1700_B(base, c_3005_b.P_2565_J());
            I_4817_s aabb = v_2826_q.n_1700_B(base, interpolated);
            double extra = 0.2 - (base.q_2307_F() && !O_580_c.c_3005_b.Y_259_p.C_415_h.J_1907_R ? 0.1 : 0.0);
            I_4817_s adjusted = new I_4817_s(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY - extra, aabb.maxZ);
            Z_2491_A b = this.n_1700_B(adjusted);
            float minX = b.n_1700_B();
            float minY = b.J_1907_R();
            float maxX = b.R_4764_Y();
            float maxY = b.G_564_y();
            if (maxX <= minX || maxY <= minY) continue;
            int colorInt = this.n_1700_B(base);
            if (this.M_588_G.J_1907_R("\u041a\u0432\u0430\u0434\u0440\u0430\u0442")) {
                this.n_1700_B(event.J_1907_R(), minX, minY, maxX, maxY, colorInt);
                continue;
            }
            if (!this.M_588_G.J_1907_R("\u0423\u0433\u043b\u044b")) continue;
            this.J_1907_R(event.J_1907_R(), minX, minY, maxX, maxY, colorInt);
        }
    }

    private int n_1700_B(N_4263_v entity) {
        if (entity instanceof a_3913_L) {
            a_3913_L p = (a_3913_L)entity;
            boolean isFriend = o_148_s.Y_601_j().v_4262_N().R_4764_Y(p.y_4642_Y().getName());
            if (isFriend && this.v_4262_N.J_1907_R("\u0414\u0440\u0443\u0437\u0435\u0439").booleanValue() && (this.M_588_G.J_1907_R("\u041a\u0432\u0430\u0434\u0440\u0430\u0442") || this.M_588_G.J_1907_R("\u0423\u0433\u043b\u044b") || this.M_588_G.J_1907_R("3D \u0411\u043e\u043a\u0441"))) {
                return (Integer)this.t_1786_h.J_1907_R();
            }
        }
        if (this.M_182_A.J_1907_R("\u0421\u0442\u0430\u0442\u0438\u0447\u043d\u044b\u0439")) {
            return (Integer)this.N_4405_n.J_1907_R();
        }
        return q_3148_R.n_1700_B(K_1200_E.J_1907_R);
    }

    private void n_1700_B(g_221_o matrixStack, float x, float y, float right, float bottom, int colorInt) {
        float outlineThickness = 0.5f;
        int firstColor = H_2506_c.J_1907_R(5, 0, colorInt, H_2506_c.J_1907_R(colorInt, 0.5f));
        int secondColor = H_2506_c.J_1907_R(5, 90, colorInt, H_2506_c.J_1907_R(colorInt, 0.5f));
        int thirdColor = H_2506_c.J_1907_R(5, 180, colorInt, H_2506_c.J_1907_R(colorInt, 0.5f));
        int fourthColor = H_2506_c.J_1907_R(5, 270, colorInt, H_2506_c.J_1907_R(colorInt, 0.5f));
        F_489_x.n_1700_B(matrixStack, x, y, 0.5f, bottom - y, secondColor, firstColor);
        F_489_x.n_1700_B(matrixStack, x, bottom, right - x, 0.5f, fourthColor, firstColor);
        F_489_x.n_1700_B(matrixStack, right, y, 0.5f, bottom - y + 0.5f, thirdColor, fourthColor);
        F_489_x.n_1700_B(matrixStack, x, y, right - x, 0.5f, thirdColor, secondColor);
        F_489_x.n_1700_B(matrixStack, x - 0.5f, y - outlineThickness, right - x + 1.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x - outlineThickness, y, outlineThickness, bottom - y + 0.5f, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x - 0.5f, bottom + 0.5f, right - x + 1.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right + 0.5f, y, outlineThickness, bottom - y + 0.5f, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x + 0.5f, y + 0.5f, right - x - 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x + 0.5f, y + 0.5f, outlineThickness, bottom - y - 0.5f, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x + 0.5f, bottom - outlineThickness, right - x - 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right - outlineThickness, y + 0.5f, outlineThickness, bottom - y - 0.5f, H_2506_c.n_1700_B(0, 0, 0));
    }

    private void J_1907_R(g_221_o matrixStack, float x, float y, float right, float bottom, int colorInt) {
        float cornerLength = Math.min((right - x) * 0.25f, (bottom - y) * 0.25f);
        float lineThickness = 0.5f;
        float outlineThickness = 0.5f;
        int firstColor = H_2506_c.J_1907_R(5, 0, colorInt, H_2506_c.J_1907_R(colorInt, 0.5f));
        int secondColor = H_2506_c.J_1907_R(5, 90, colorInt, H_2506_c.J_1907_R(colorInt, 0.5f));
        int thirdColor = H_2506_c.J_1907_R(5, 180, colorInt, H_2506_c.J_1907_R(colorInt, 0.5f));
        int fourthColor = H_2506_c.J_1907_R(5, 270, colorInt, H_2506_c.J_1907_R(colorInt, 0.5f));
        F_489_x.n_1700_B(matrixStack, x, y, cornerLength, lineThickness, thirdColor, secondColor);
        F_489_x.n_1700_B(matrixStack, x, y, lineThickness, cornerLength, secondColor, firstColor);
        F_489_x.n_1700_B(matrixStack, right - cornerLength + 0.5f, y, cornerLength - 0.5f, lineThickness, thirdColor, secondColor);
        F_489_x.n_1700_B(matrixStack, right, y, lineThickness, cornerLength, thirdColor, fourthColor);
        F_489_x.n_1700_B(matrixStack, x, bottom, cornerLength, lineThickness, fourthColor, firstColor);
        F_489_x.n_1700_B(matrixStack, x, bottom - cornerLength + 0.5f, lineThickness, cornerLength - 0.5f, secondColor, firstColor);
        F_489_x.n_1700_B(matrixStack, right - cornerLength + lineThickness, bottom, cornerLength, lineThickness, fourthColor, firstColor);
        F_489_x.n_1700_B(matrixStack, right, bottom - cornerLength + 0.5f, lineThickness, cornerLength - 0.5f, thirdColor, fourthColor);
        F_489_x.n_1700_B(matrixStack, x - 0.5f, y - outlineThickness, cornerLength + 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x - outlineThickness, y, outlineThickness, cornerLength, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x + 0.5f, y + 0.5f, cornerLength - 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x + 0.5f, y + 0.5f, outlineThickness, cornerLength - 0.5f, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right - outlineThickness, y + 0.5f, outlineThickness, cornerLength - 0.5f, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right + 0.5f, y, outlineThickness, cornerLength, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right - cornerLength + 0.5f, y - outlineThickness, cornerLength + 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right - cornerLength + 0.5f, y + 0.5f, cornerLength - 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x - outlineThickness, bottom - cornerLength + 0.5f, outlineThickness, cornerLength, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x + 0.5f, bottom - cornerLength + 0.5f, outlineThickness, cornerLength - 0.5f, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x + 0.5f, bottom - outlineThickness, cornerLength - 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, x - 0.5f, bottom + 0.5f, cornerLength + 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right - outlineThickness, bottom - cornerLength + 0.5f, outlineThickness, cornerLength - 0.5f, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right + 0.5f, bottom - cornerLength + 0.5f, outlineThickness, cornerLength, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right - cornerLength + 0.5f, bottom - outlineThickness, cornerLength - 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
        F_489_x.n_1700_B(matrixStack, right - cornerLength + 0.5f, bottom + 0.5f, cornerLength + 0.5f, outlineThickness, H_2506_c.n_1700_B(0, 0, 0));
    }

    public void n_1700_B(float direction, float direction2) {
        Color color = new Color((Integer)this.C_2741_M.J_1907_R());
        s_4405_m.P_4830_p.n_1700_B("texture", new int[]{0});
        s_4405_m.P_4830_p.J_1907_R("radius", (((Float)this.k_2293_S.J_1907_R()).floatValue() + 3.0f) / 1.5f);
        s_4405_m.P_4830_p.J_1907_R("texelSize", 1.0f / (float)H_2857_Y.u_2550_I(), 1.0f / (float)H_2857_Y.M_588_G());
        s_4405_m.P_4830_p.J_1907_R("direction", direction, direction2);
        s_4405_m.P_4830_p.J_1907_R("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f);
    }

    public void J_1907_R(float direction, float direction2) {
        Color color = new Color((Integer)this.C_2741_M.J_1907_R());
        s_4405_m.h_1847_R.n_1700_B("textureIn", new int[]{0});
        s_4405_m.h_1847_R.J_1907_R("radius", ((Float)this.k_2293_S.J_1907_R()).floatValue() + 3.0f);
        s_4405_m.h_1847_R.J_1907_R("texelSize", 1.0f / (float)H_2857_Y.u_2550_I(), 1.0f / (float)H_2857_Y.M_588_G());
        s_4405_m.h_1847_R.J_1907_R("direction", direction, direction2);
        s_4405_m.h_1847_R.J_1907_R("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        s_4405_m.h_1847_R.J_1907_R("exposure", 1.0f);
        s_4405_m.h_1847_R.n_1700_B("avoidTexture", new int[]{0});
        FloatBuffer buffer = BufferUtils.createFloatBuffer((int)32);
        int i = 1;
        while ((float)i <= ((Float)this.k_2293_S.J_1907_R()).floatValue() + 3.0f) {
            buffer.put(F_747_P.n_1700_B(i, (((Float)this.k_2293_S.J_1907_R()).floatValue() + 3.0f) / 2.0f));
            ++i;
        }
        buffer.rewind();
        c_4037_x.n_1700_B(s_4405_m.h_1847_R.n_1700_B("weights"), buffer);
    }

    public void Q_4569_t() {
        Color color = new Color((Integer)this.C_2741_M.J_1907_R());
        s_4405_m.Q_4569_t.n_1700_B("texture", new int[]{0});
        s_4405_m.Q_4569_t.J_1907_R("texelSize", 1.0f / (float)H_2857_Y.u_2550_I(), 1.0f / (float)H_2857_Y.M_588_G());
        s_4405_m.Q_4569_t.J_1907_R("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
    }

    public void n_1700_B(float ticks) {
        boolean prevShadow = c_3005_b.O_508_d().v_4262_N();
        c_3005_b.O_508_d().n_1700_B(false);
        for (N_4263_v entity : O_580_c.c_3005_b.Y_601_j.J_1907_R()) {
            if (entity == null || !entity.H_3699_F() || !v_2826_q.n_1700_B(entity) || this.J_1907_R(entity)) continue;
            Z_2049_e<N_4263_v> renderer = c_3005_b.O_508_d().n_1700_B(entity);
            boolean prevRenderName = renderer.G_564_y();
            renderer.J_1907_R(false);
            c_3005_b.O_508_d().n_1700_B(entity, ticks, false);
            renderer.J_1907_R(prevRenderName);
        }
        c_3005_b.O_508_d().n_1700_B(prevShadow);
    }

    private void R_4764_Y(b_3528_u event) {
        if (!(this.w_1457_N.J_1907_R("\u0421\u043e\u0441\u0442\u043e\u044f\u043d\u0438\u0435 \u0437\u0434\u043e\u0440\u043e\u0432\u044c\u044f") || this.w_1457_N.J_1907_R("\u0418\u043d\u0434\u0438\u0432\u0438\u0434\u0443\u0430\u043b\u044c\u043d\u044b\u0439") || this.w_1457_N.J_1907_R("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439"))) {
            return;
        }
        if (O_580_c.c_3005_b.Y_601_j == null) {
            return;
        }
        for (N_4263_v base : O_580_c.c_3005_b.Y_601_j.J_1907_R()) {
            int bottomColor;
            int topColor;
            r_4811_B entity;
            if (!(base instanceof r_4811_B) || !(entity = (r_4811_B)base).H_3699_F() || !v_2826_q.n_1700_B(entity) || this.J_1907_R(entity)) continue;
            e_2866_D interpolated = F_747_P.n_1700_B((N_4263_v)entity, c_3005_b.P_2565_J());
            I_4817_s aabb = v_2826_q.n_1700_B(entity, interpolated);
            double extra = 0.2 - (entity.q_2307_F() && !O_580_c.c_3005_b.Y_259_p.C_415_h.J_1907_R ? 0.1 : 0.0);
            I_4817_s adjusted = new I_4817_s(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY - extra, aabb.maxZ);
            Z_2491_A bounds = this.n_1700_B(adjusted);
            float minX = bounds.n_1700_B();
            float minY = bounds.J_1907_R();
            float maxY = bounds.G_564_y();
            float barWidth = 0.5f;
            float barHeight = maxY - minY;
            float barX = minX - 2.0f - barWidth;
            boolean useScoreboard = o_148_s.Y_601_j().J_1907_R().n_1700_B(F_3104_Z.class).w_1484_f();
            float currentHP = Math.max(0.0f, useScoreboard ? q_3115_L.n_1700_B(entity) : entity.g_46_E());
            float maxHP = Math.max(1.0f, entity.L_1733_J());
            float percent = Math.min(currentHP / maxHP, 1.0f);
            float fillHeight = barHeight * percent;
            float fillY = minY + (barHeight - fillHeight);
            if (this.w_1457_N.J_1907_R("\u0418\u043d\u0434\u0438\u0432\u0438\u0434\u0443\u0430\u043b\u044c\u043d\u044b\u0439")) {
                topColor = (Integer)this.Y_601_j.J_1907_R();
                bottomColor = (Integer)this.Y_259_p.J_1907_R();
            } else if (this.w_1457_N.J_1907_R("\u041a\u043b\u0438\u0435\u043d\u0442\u0441\u043a\u0438\u0439")) {
                int active = q_3148_R.n_1700_B(K_1200_E.J_1907_R);
                int darker = H_2506_c.J_1907_R(active, 0.6f);
                topColor = active;
                bottomColor = darker;
            } else {
                int mixed;
                int red = H_2506_c.n_1700_B(255, 0, 0);
                int green = H_2506_c.n_1700_B(0, 255, 0);
                topColor = mixed = H_2506_c.n_1700_B(red, green, percent);
                bottomColor = H_2506_c.J_1907_R(mixed, 0.6f);
            }
            F_489_x.n_1700_B(event.J_1907_R(), barX - 0.5f, minY - 0.5f, barWidth + 1.0f, barHeight + 1.5f, H_2506_c.n_1700_B(0, 0, 0, 255));
            F_489_x.n_1700_B(event.J_1907_R(), barX, fillY, barWidth, fillHeight + 0.5f, topColor, bottomColor);
        }
    }

    private boolean J_1907_R(N_4263_v entity) {
        return !E_4612_l.G_564_y(entity, this.v_4262_N);
    }

    private Z_2491_A n_1700_B(I_4817_s aabb) {
        e_2866_D[] corners = v_2826_q.n_1700_B(aabb);
        Vector2f min = new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
        Vector2f max = new Vector2f(Float.MIN_VALUE, Float.MIN_VALUE);
        for (e_2866_D corner : corners) {
            Vector2f projected = v_2826_q.n_1700_B(corner);
            if (projected.x < min.x) {
                min.x = projected.x;
            }
            if (projected.y < min.y) {
                min.y = projected.y;
            }
            if (projected.x > max.x) {
                max.x = projected.x;
            }
            if (!(projected.y > max.y)) continue;
            max.y = projected.y;
        }
        return new Z_2491_A(min.x, min.y, max.x, max.y);
    }
}

