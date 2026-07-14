/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.a_3913_L;
import lightning.product.n_1700_B;
import lightning.product.u_1723_Y;

public class t_148_a
implements u_1723_Y {
    private final String n_1700_B;
    private final double J_1907_R;
    private static final double R_4764_Y = 2.0;

    public t_148_a(String targetName) {
        this(targetName, 2.0);
    }

    public t_148_a(String targetName, double followDistance) {
        this.n_1700_B = targetName;
        this.J_1907_R = followDistance;
    }

    @Override
    public void n_1700_B(n_1700_B bot) {
        if (bot.R_4764_Y == null || bot.J_1907_R == null) {
            return;
        }
        a_3913_L target = this.J_1907_R(bot);
        if (target == null) {
            return;
        }
        double distanceSq = bot.R_4764_Y.G_564_y((N_4263_v)target);
        if (distanceSq > this.J_1907_R * this.J_1907_R) {
            bot.u_1723_Y.n_1700_B(target);
        } else {
            bot.u_1723_Y.J_1907_R();
            bot.u_1723_Y.J_1907_R(target);
        }
    }

    private a_3913_L J_1907_R(n_1700_B bot) {
        for (N_4263_v entity : bot.J_1907_R.J_1907_R()) {
            if (!(entity instanceof a_3913_L) || !entity.O_1309_Q().getString().equalsIgnoreCase(this.n_1700_B) || entity == bot.R_4764_Y) continue;
            return (a_3913_L)entity;
        }
        return null;
    }
}

