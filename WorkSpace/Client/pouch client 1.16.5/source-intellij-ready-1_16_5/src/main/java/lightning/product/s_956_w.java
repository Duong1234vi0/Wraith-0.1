/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2871_b;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.j_3341_s;
import lightning.product.n_1700_B;
import lightning.product.u_1723_Y;
import lightning.product.u_2550_I;
import lightning.product.x_282_a;

public class s_956_w
implements u_1723_Y {
    private final c_1514_x n_1700_B;
    private static final double J_1907_R = 0.3;

    public s_956_w(c_1514_x targetPos) {
        this.n_1700_B = targetPos;
    }

    public s_956_w(int x, int y, int z) {
        this.n_1700_B = new c_1514_x(x, y, z);
    }

    @Override
    public void n_1700_B(n_1700_B bot) {
        e_2866_D targetVec;
        if (bot == null || bot.R_4764_Y == null || this.n_1700_B == null) {
            return;
        }
        e_2866_D botPos = bot.R_4764_Y.s_4990_V();
        double distance = botPos.u_1723_Y(targetVec = new e_2866_D((double)this.n_1700_B.getX() + 0.5, this.n_1700_B.getY(), (double)this.n_1700_B.getZ() + 0.5));
        if (distance <= 0.3) {
            bot.u_1723_Y.J_1907_R();
            bot.n_1700_B(new u_2550_I());
            if (bot.P_1922_E != null && bot.P_1922_E.Q_2552_b != null) {
                bot.P_1922_E.Q_2552_b.n_1700_B((x_282_a)new U_2871_b("\u00a7a[Goto] \u0414\u043e\u0441\u0442\u0438\u0433\u043d\u0443\u0442\u0430 \u0446\u0435\u043b\u044c: X:" + this.n_1700_B.getX() + " Y:" + this.n_1700_B.getY() + " Z:" + this.n_1700_B.getZ()), j_3341_s.J_1907_R);
            }
            return;
        }
        bot.u_1723_Y.n_1700_B(this.n_1700_B);
    }

    public c_1514_x n_1700_B() {
        return this.n_1700_B;
    }
}

