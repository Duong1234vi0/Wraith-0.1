/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import lightning.product.Q_2552_b;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.n_1700_B;
import lightning.product.u_1723_Y;

public class h_1847_R
implements u_1723_Y {
    private final List<c_1514_x> n_1700_B = new ArrayList<c_1514_x>();
    private int J_1907_R = 0;
    private final double R_4764_Y;
    private Q_2552_b G_564_y;
    private static final double P_1922_E = 1.5;

    public h_1847_R() {
        this(1.5);
    }

    public h_1847_R(double reachDistance) {
        this.R_4764_Y = reachDistance;
    }

    public void n_1700_B(c_1514_x point) {
        this.n_1700_B.add(point);
    }

    public void n_1700_B() {
        this.n_1700_B.clear();
        this.J_1907_R = 0;
    }

    public void n_1700_B(int index) {
        if (index >= 0 && index < this.n_1700_B.size()) {
            this.J_1907_R = index;
        }
    }

    public boolean J_1907_R() {
        return this.n_1700_B.isEmpty();
    }

    @Override
    public void n_1700_B(n_1700_B bot) {
        if (bot == null || bot.R_4764_Y == null) {
            return;
        }
        if (this.G_564_y == null) {
            this.G_564_y = new Q_2552_b(bot);
        }
        if (this.n_1700_B.isEmpty()) {
            this.G_564_y.J_1907_R();
            return;
        }
        c_1514_x targetPoint = this.n_1700_B.get(this.J_1907_R);
        e_2866_D targetPos = new e_2866_D((double)targetPoint.getX() + 0.5, targetPoint.getY(), (double)targetPoint.getZ() + 0.5);
        e_2866_D botPos = bot.R_4764_Y.s_4990_V();
        double distance = botPos.u_1723_Y(targetPos);
        if (distance <= this.R_4764_Y) {
            this.J_1907_R = (this.J_1907_R + 1) % this.n_1700_B.size();
            return;
        }
        this.G_564_y.J_1907_R(targetPos);
        this.G_564_y.R_4764_Y(targetPos);
    }
}

