/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayDeque;
import java.util.Deque;
import lightning.product.A_4115_X;
import lightning.product.N_3268_u;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;

public class m_1628_s
implements MinecraftAccess {
    private static m_1628_s n_1700_B;
    private final Deque<e_2866_D> J_1907_R = new ArrayDeque<e_2866_D>();
    private boolean R_4764_Y = false;
    private e_2866_D G_564_y = null;
    private int P_1922_E = 0;

    public m_1628_s() {
        n_1700_B = this;
        A_4115_X.n_1700_B(this);
    }

    public static m_1628_s n_1700_B() {
        if (n_1700_B == null) {
            n_1700_B = new m_1628_s();
        }
        return n_1700_B;
    }

    public void n_1700_B(e_2866_D from, e_2866_D to) {
        this.J_1907_R.clear();
        double safeY = Math.max(from.R_4764_Y, to.R_4764_Y) + 10.0;
        this.n_1700_B(from.J_1907_R, from.R_4764_Y, from.G_564_y, safeY, 1.0);
        this.n_1700_B(from.J_1907_R, safeY, from.G_564_y, to.J_1907_R, to.G_564_y, 6);
        this.n_1700_B(to.J_1907_R, safeY, to.G_564_y, to.R_4764_Y, 1.0);
        this.G_564_y = to;
        this.P_1922_E = 3;
        this.R_4764_Y = true;
    }

    public void n_1700_B(e_2866_D from, e_2866_D to, int totalSteps) {
        this.J_1907_R.clear();
        totalSteps = Math.max(3, Math.min(5, totalSteps));
        double safeY = Math.max(from.R_4764_Y, to.R_4764_Y) + 16.0;
        this.n_1700_B(from.J_1907_R, from.R_4764_Y, from.G_564_y, safeY, 1.0);
        int horiz = Math.max(1, totalSteps - 2);
        for (int i = 1; i <= horiz; ++i) {
            double t = (double)i / (double)horiz;
            double nx = from.J_1907_R + (to.J_1907_R - from.J_1907_R) * t;
            double nz = from.G_564_y + (to.G_564_y - from.G_564_y) * t;
            this.J_1907_R.addLast(new e_2866_D(nx, safeY, nz));
        }
        this.n_1700_B(to.J_1907_R, safeY, to.G_564_y, to.R_4764_Y, 1.0);
        this.G_564_y = to;
        this.P_1922_E = 4;
        this.R_4764_Y = true;
    }

    private void n_1700_B(double x, double fromY, double z, double toY, double step) {
        int steps = Math.max(1, (int)Math.ceil(Math.abs(toY - fromY) / step));
        for (int i = 1; i <= steps; ++i) {
            double ny = fromY + (toY - fromY) * ((double)i / (double)steps);
            this.J_1907_R.addLast(new e_2866_D(x, ny, z));
        }
    }

    private void n_1700_B(double fromX, double y, double fromZ, double toX, double toZ, int steps) {
        steps = Math.max(2, Math.min(steps, 6));
        for (int i = 1; i <= steps; ++i) {
            double t = (double)i / (double)steps;
            double nx = fromX + (toX - fromX) * t;
            double nz = fromZ + (toZ - fromZ) * t;
            this.J_1907_R.addLast(new e_2866_D(nx, y, nz));
        }
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (!this.R_4764_Y || m_1628_s.c_3005_b.Y_259_p == null || m_1628_s.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        if (!this.J_1907_R.isEmpty()) {
            e_2866_D p;
            int burst = Math.min(3, this.J_1907_R.size());
            for (int i = 0; i < burst && (p = this.J_1907_R.pollFirst()) != null; ++i) {
                m_1628_s.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.n_1700_B(p.J_1907_R, p.R_4764_Y, p.G_564_y, false));
                m_1628_s.c_3005_b.Y_259_p.J_1907_R(p.J_1907_R, p.R_4764_Y, p.G_564_y);
            }
            return;
        }
        if (this.P_1922_E > 0 && this.G_564_y != null) {
            boolean onGround = this.P_1922_E == 1;
            m_1628_s.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.n_1700_B(this.G_564_y.J_1907_R, this.G_564_y.R_4764_Y, this.G_564_y.G_564_y, onGround));
            m_1628_s.c_3005_b.Y_259_p.J_1907_R(this.G_564_y.J_1907_R, this.G_564_y.R_4764_Y, this.G_564_y.G_564_y);
            --this.P_1922_E;
            return;
        }
        this.R_4764_Y = false;
    }

    public boolean J_1907_R() {
        return this.R_4764_Y;
    }

    public void R_4764_Y() {
        this.R_4764_Y = false;
        this.J_1907_R.clear();
        this.G_564_y = null;
        this.P_1922_E = 0;
    }
}


