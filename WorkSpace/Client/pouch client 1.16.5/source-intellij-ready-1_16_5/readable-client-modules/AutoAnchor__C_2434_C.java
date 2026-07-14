/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.G_3416_z;
import lightning.product.I_4817_s;
import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.q_4592_V;
import lightning.product.u_1934_K;
import lightning.product.u_530_F;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class C_2434_C
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 10.0f, 1.0f, 50.0f, 1.0f);
    private final I_686_h w_1484_f = new I_686_h("\u041c\u0430\u043a\u0441. \u0432\u0441\u0442\u0430\u0432\u043e\u043a", 4.0f, 1.0f, 10.0f, 1.0f);
    private final I_686_h t_148_a = new I_686_h("\u0420\u0430\u0434\u0438\u0443\u0441", 5.0f, 1.0f, 10.0f, 0.5f);
    private final List<c_1514_x> s_956_w = new ArrayList<c_1514_x>();
    private c_1514_x u_2550_I = null;
    private int M_588_G = 0;
    private long P_4830_p = 0L;
    private int h_1847_R = -1;
    private boolean Q_4569_t = false;
    private boolean M_182_A = false;
    private c_1514_x t_1786_h = null;

    public C_2434_C() {
        super("AutoAnchor", y_2603_k.n_1700_B);
        this.n_1700_B(this.v_4262_N, this.w_1484_f, this.t_148_a);
    }

    @Override
    public void n_1700_B() {
        this.s_956_w.clear();
        this.u_2550_I = null;
        this.M_588_G = 0;
        this.M_182_A = false;
        this.t_1786_h = null;
        super.n_1700_B();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        long delay;
        if (C_2434_C.c_3005_b.Y_259_p == null || C_2434_C.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.Q_4569_t) {
            this.Q_4569_t = false;
            if (this.h_1847_R != -1) {
                C_2434_C.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.h_1847_R;
                C_2434_C.c_3005_b.w_1457_N.syncCurrentPlayItem();
                this.h_1847_R = -1;
            }
            return;
        }
        if (this.M_182_A && this.t_1786_h != null) {
            this.J_1907_R(this.t_1786_h);
            this.M_182_A = false;
            this.t_1786_h = null;
            return;
        }
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.P_4830_p < (delay = (long)(1000.0f / ((Float)this.v_4262_N.J_1907_R()).floatValue()))) {
            return;
        }
        int glowstoneSlot = this.h_1847_R();
        if (glowstoneSlot == -1) {
            return;
        }
        if (this.u_2550_I == null || !this.n_1700_B(this.u_2550_I)) {
            this.u_2550_I = this.Q_4569_t();
        }
        if (this.u_2550_I != null) {
            this.n_1700_B(this.u_2550_I, glowstoneSlot);
        }
    }

    private int h_1847_R() {
        for (int i = 0; i < 36; ++i) {
            if (C_2434_C.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != q_4592_V.Q_2753_H) continue;
            return i;
        }
        return -1;
    }

    private c_1514_x Q_4569_t() {
        c_1514_x playerPos = C_2434_C.c_3005_b.Y_259_p.b_2312_j();
        double maxDistance = ((Float)this.t_148_a.J_1907_R()).floatValue();
        c_1514_x nearest = null;
        double nearestDist = maxDistance * maxDistance;
        int rangeInt = (int)Math.ceil(maxDistance);
        for (int x = -rangeInt; x <= rangeInt; ++x) {
            for (int y = -rangeInt; y <= rangeInt; ++y) {
                for (int z = -rangeInt; z <= rangeInt; ++z) {
                    double dist;
                    c_1514_x pos = playerPos.add(x, y, z);
                    if (C_2434_C.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R() != a_3742_W.z_4455_E || this.s_956_w.contains(pos) || !((dist = C_2434_C.c_3005_b.Y_259_p.v_4262_N((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5)) < nearestDist)) continue;
                    nearestDist = dist;
                    nearest = pos;
                }
            }
        }
        return nearest;
    }

    private boolean n_1700_B(c_1514_x pos) {
        if (C_2434_C.c_3005_b.Y_601_j.getBlockState(pos).J_1907_R() != a_3742_W.z_4455_E) {
            return false;
        }
        return !this.s_956_w.contains(pos);
    }

    private void n_1700_B(c_1514_x pos, int glowstoneSlot) {
        e_2866_D eyeVec = C_2434_C.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D hitVec = this.n_1700_B(eyeVec, new I_4817_s(pos));
        e_2866_D offset = hitVec.G_564_y(eyeVec);
        float targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(offset.G_564_y, offset.J_1907_R)) - 90.0);
        float targetPitch = (float)(-Math.toDegrees(Math.atan2(offset.R_4764_Y, Math.hypot(offset.J_1907_R, offset.G_564_y))));
        C_2434_C.c_3005_b.Y_259_p.p_178_J = targetYaw;
        C_2434_C.c_3005_b.Y_259_p.f_4016_n = targetPitch;
        this.h_1847_R = C_2434_C.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        if (glowstoneSlot < 9) {
            C_2434_C.c_3005_b.Y_259_p.l_1268_F.G_564_y = glowstoneSlot;
        } else {
            u_1934_K.n_1700_B(glowstoneSlot, this.h_1847_R);
            C_2434_C.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.h_1847_R;
        }
        C_2434_C.c_3005_b.w_1457_N.syncCurrentPlayItem();
        e_2866_D invOffset = offset.P_1922_E();
        C_2434_C.c_3005_b.w_1457_N.func_217292_a(C_2434_C.c_3005_b.Y_259_p, C_2434_C.c_3005_b.Y_601_j, x_1688_C.n_1700_B, new G_3416_z(hitVec, b_257_Y.n_1700_B(invOffset.J_1907_R, invOffset.R_4764_Y, invOffset.G_564_y), pos, false));
        C_2434_C.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        ++this.M_588_G;
        this.P_4830_p = System.currentTimeMillis();
        this.Q_4569_t = true;
        if ((float)this.M_588_G >= ((Float)this.w_1484_f.J_1907_R()).floatValue()) {
            this.M_182_A = true;
            this.t_1786_h = this.u_2550_I;
            this.s_956_w.add(this.u_2550_I);
            this.u_2550_I = null;
            this.M_588_G = 0;
        }
    }

    private void J_1907_R(c_1514_x pos) {
        e_2866_D eyeVec = C_2434_C.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D hitVec = this.n_1700_B(eyeVec, new I_4817_s(pos));
        e_2866_D offset = hitVec.G_564_y(eyeVec);
        float targetYaw = (float)u_530_F.u_1723_Y(Math.toDegrees(Math.atan2(offset.G_564_y, offset.J_1907_R)) - 90.0);
        float targetPitch = (float)(-Math.toDegrees(Math.atan2(offset.R_4764_Y, Math.hypot(offset.J_1907_R, offset.G_564_y))));
        C_2434_C.c_3005_b.Y_259_p.p_178_J = targetYaw;
        C_2434_C.c_3005_b.Y_259_p.f_4016_n = targetPitch;
        e_2866_D invOffset = offset.P_1922_E();
        C_2434_C.c_3005_b.w_1457_N.func_217292_a(C_2434_C.c_3005_b.Y_259_p, C_2434_C.c_3005_b.Y_601_j, x_1688_C.n_1700_B, new G_3416_z(hitVec, b_257_Y.n_1700_B(invOffset.J_1907_R, invOffset.R_4764_Y, invOffset.G_564_y), pos, false));
        C_2434_C.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
    }

    private e_2866_D n_1700_B(e_2866_D eyeVec, I_4817_s box) {
        double x = Math.max(box.minX, Math.min(eyeVec.J_1907_R, box.maxX));
        double y = Math.max(box.minY, Math.min(eyeVec.R_4764_Y, box.maxY));
        double z = Math.max(box.minZ, Math.min(eyeVec.G_564_y, box.maxZ));
        return new e_2866_D(x, y, z);
    }
}

