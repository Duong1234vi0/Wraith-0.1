/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.lwjgl.opengl.GL11
 */
package lightning.product;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lightning.product.I_4477_R;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.h_2367_h;
import lightning.product.h_3572_K;
import lightning.product.y_2603_k;
import org.lwjgl.opengl.GL11;

public class B_2197_V
extends X_3546_T {
    private static final Set<T_2915_h> v_4262_N = new HashSet<T_2915_h>();
    private final h_2367_h w_1484_f = new h_2367_h("\u0426\u0432\u0435\u0442 ESP", false, new Color(255, 50, 50, 200).getRGB());
    private final List<c_1514_x> t_148_a = new ArrayList<c_1514_x>();

    public B_2197_V() {
        super("AnomalyESP", y_2603_k.R_4764_Y);
        this.n_1700_B(this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (!this.w_1484_f() || B_2197_V.c_3005_b.Y_259_p == null || B_2197_V.c_3005_b.Y_601_j == null) {
            return;
        }
        if (B_2197_V.c_3005_b.Y_259_p.t_2577_l % 5 != 0) {
            return;
        }
        this.t_148_a.clear();
        c_1514_x playerPos = new c_1514_x(B_2197_V.c_3005_b.Y_259_p.O_3598_v(), B_2197_V.c_3005_b.Y_259_p.X_2960_b(), B_2197_V.c_3005_b.Y_259_p.l_2647_k());
        int rangeXZ = 32;
        int rangeY = 16;
        int minY = Math.max(0, playerPos.getY() - rangeY);
        int maxY = Math.min(B_2197_V.c_3005_b.Y_601_j.c_3005_b() - 1, playerPos.getY() + rangeY);
        c_1514_x.n_1700_B mutablePos = new c_1514_x.n_1700_B();
        for (int x = playerPos.getX() - rangeXZ; x <= playerPos.getX() + rangeXZ; ++x) {
            for (int z = playerPos.getZ() - rangeXZ; z <= playerPos.getZ() + rangeXZ; ++z) {
                for (int y = minY; y <= maxY; ++y) {
                    mutablePos.n_1700_B(x, y, z);
                    K_4074_S state = B_2197_V.c_3005_b.Y_601_j.getBlockState(mutablePos);
                    T_2915_h block = state.J_1907_R();
                    if (!v_4262_N.contains(block) || this.n_1700_B(mutablePos)) continue;
                    this.t_148_a.add(mutablePos.toImmutable());
                }
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(I_4477_R event) {
        if (B_2197_V.c_3005_b.Y_259_p == null || B_2197_V.c_3005_b.Y_601_j == null) {
            return;
        }
        h_3572_K renderInfo = B_2197_V.c_3005_b.s_956_w.M_588_G();
        e_2866_D cam = renderInfo.J_1907_R();
        if (this.t_148_a.isEmpty()) {
            return;
        }
        Color color = new Color((Integer)this.w_1484_f.J_1907_R(), true);
        for (c_1514_x pos : this.t_148_a) {
            I_4817_s bb = new I_4817_s(pos.getX(), pos.getY(), pos.getZ(), pos.getX() + 1, pos.getY() + 1, pos.getZ() + 1);
            this.n_1700_B(bb, color, cam);
        }
    }

    private boolean n_1700_B(c_1514_x pos) {
        c_1514_x below = new c_1514_x(pos.getX(), pos.getY() - 1, pos.getZ());
        K_4074_S stateBelow = B_2197_V.c_3005_b.Y_601_j.getBlockState(below);
        return !stateBelow.v_4262_N();
    }

    private void n_1700_B(I_4817_s bb, Color color, e_2866_D cam) {
        double x1 = bb.minX - cam.J_1907_R;
        double y1 = bb.minY - cam.R_4764_Y;
        double z1 = bb.minZ - cam.G_564_y;
        double x2 = bb.maxX - cam.J_1907_R;
        double y2 = bb.maxY - cam.R_4764_Y;
        double z2 = bb.maxZ - cam.G_564_y;
        GL11.glPushMatrix();
        GL11.glLineWidth((float)1.5f);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2929);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)0.15f);
        GL11.glBegin((int)7);
        GL11.glVertex3d((double)x1, (double)y1, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z2);
        GL11.glVertex3d((double)x1, (double)y1, (double)z2);
        GL11.glVertex3d((double)x1, (double)y2, (double)z1);
        GL11.glVertex3d((double)x2, (double)y2, (double)z1);
        GL11.glVertex3d((double)x2, (double)y2, (double)z2);
        GL11.glVertex3d((double)x1, (double)y2, (double)z2);
        GL11.glVertex3d((double)x1, (double)y1, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z1);
        GL11.glVertex3d((double)x2, (double)y2, (double)z1);
        GL11.glVertex3d((double)x1, (double)y2, (double)z1);
        GL11.glVertex3d((double)x1, (double)y1, (double)z2);
        GL11.glVertex3d((double)x2, (double)y1, (double)z2);
        GL11.glVertex3d((double)x2, (double)y2, (double)z2);
        GL11.glVertex3d((double)x1, (double)y2, (double)z2);
        GL11.glVertex3d((double)x1, (double)y1, (double)z1);
        GL11.glVertex3d((double)x1, (double)y1, (double)z2);
        GL11.glVertex3d((double)x1, (double)y2, (double)z2);
        GL11.glVertex3d((double)x1, (double)y2, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z2);
        GL11.glVertex3d((double)x2, (double)y2, (double)z2);
        GL11.glVertex3d((double)x2, (double)y2, (double)z1);
        GL11.glEnd();
        GL11.glColor4f((float)((float)color.getRed() / 255.0f), (float)((float)color.getGreen() / 255.0f), (float)((float)color.getBlue() / 255.0f), (float)((float)color.getAlpha() / 255.0f));
        GL11.glBegin((int)1);
        GL11.glVertex3d((double)x1, (double)y1, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z2);
        GL11.glVertex3d((double)x2, (double)y1, (double)z2);
        GL11.glVertex3d((double)x1, (double)y1, (double)z2);
        GL11.glVertex3d((double)x1, (double)y1, (double)z2);
        GL11.glVertex3d((double)x1, (double)y1, (double)z1);
        GL11.glVertex3d((double)x1, (double)y2, (double)z1);
        GL11.glVertex3d((double)x2, (double)y2, (double)z1);
        GL11.glVertex3d((double)x2, (double)y2, (double)z1);
        GL11.glVertex3d((double)x2, (double)y2, (double)z2);
        GL11.glVertex3d((double)x2, (double)y2, (double)z2);
        GL11.glVertex3d((double)x1, (double)y2, (double)z2);
        GL11.glVertex3d((double)x1, (double)y2, (double)z2);
        GL11.glVertex3d((double)x1, (double)y2, (double)z1);
        GL11.glVertex3d((double)x1, (double)y1, (double)z1);
        GL11.glVertex3d((double)x1, (double)y2, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z1);
        GL11.glVertex3d((double)x2, (double)y2, (double)z1);
        GL11.glVertex3d((double)x2, (double)y1, (double)z2);
        GL11.glVertex3d((double)x2, (double)y2, (double)z2);
        GL11.glVertex3d((double)x1, (double)y1, (double)z2);
        GL11.glVertex3d((double)x1, (double)y2, (double)z2);
        GL11.glEnd();
        GL11.glEnable((int)2929);
        GL11.glEnable((int)3553);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
    }

    static {
        v_4262_N.add(a_3742_W.s_1671_u);
        v_4262_N.add(a_3742_W.Q_75_S);
        v_4262_N.add(a_3742_W.C_3538_G);
        v_4262_N.add(a_3742_W.A_3959_N);
        v_4262_N.add(a_3742_W.G_424_k);
        v_4262_N.add(a_3742_W.i_1610_l);
        v_4262_N.add(a_3742_W.f_1043_S);
        v_4262_N.add(a_3742_W.F_4247_a);
        v_4262_N.add(a_3742_W.J_739_q);
        v_4262_N.add(a_3742_W.C_1162_e);
        v_4262_N.add(a_3742_W.D_4361_a);
        v_4262_N.add(a_3742_W.u_55_V);
        v_4262_N.add(a_3742_W.f_3449_S);
        v_4262_N.add(a_3742_W.V_983_n);
        v_4262_N.add(a_3742_W.X_812_G);
        v_4262_N.add(a_3742_W.k_1320_C);
        v_4262_N.add(a_3742_W.A_4758_i);
        v_4262_N.add(a_3742_W.E_3242_J);
        v_4262_N.add(a_3742_W.F_1541_a);
        v_4262_N.add(a_3742_W.u_744_e);
        v_4262_N.add(a_3742_W.f_1607_n);
        v_4262_N.add(a_3742_W.r_3651_U);
        v_4262_N.add(a_3742_W.Y_601_j);
        v_4262_N.add(a_3742_W.Y_259_p);
        v_4262_N.add(a_3742_W.Q_2552_b);
        v_4262_N.add(a_3742_W.C_2741_M);
        v_4262_N.add(a_3742_W.k_2293_S);
        v_4262_N.add(a_3742_W.q_2307_F);
        v_4262_N.add(a_3742_W.G_3269_e);
        v_4262_N.add(a_3742_W.n_2977_f);
        v_4262_N.add(a_3742_W.o_2341_D);
        v_4262_N.add(a_3742_W.C_1269_X);
        v_4262_N.add(a_3742_W.l_4088_R);
        v_4262_N.add(a_3742_W.P_2295_B);
        v_4262_N.add(a_3742_W.U_1697_c);
        v_4262_N.add(a_3742_W.d_4412_Z);
        v_4262_N.add(a_3742_W.l_3609_d);
        v_4262_N.add(a_3742_W.U_4087_m);
    }
}

