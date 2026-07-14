/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.Y_259_p;
import lightning.product.a_3913_L;
import lightning.product.n_1700_B;
import lightning.product.u_1723_Y;
import lightning.product.EntityHitResult;

public class G_564_y
implements u_1723_Y {
    private final String n_1700_B;
    private final double J_1907_R;
    private static final double R_4764_Y = 3.0;
    private final Y_259_p G_564_y;
    private long P_1922_E = 0L;
    private static final long u_1723_Y = 500L;

    public G_564_y(String targetName) {
        this(targetName, 3.0);
    }

    public G_564_y(String targetName, double attackRange) {
        this.n_1700_B = targetName;
        this.J_1907_R = attackRange;
        this.G_564_y = new Y_259_p(null);
    }

    @Override
    public void n_1700_B(n_1700_B bot) {
        a_3913_L target;
        if (bot.R_4764_Y == null || bot.J_1907_R == null) {
            return;
        }
        if (this.G_564_y.n_1700_B() == null) {
            this.G_564_y.n_1700_B(bot);
        }
        if ((target = this.J_1907_R(bot)) == null) {
            return;
        }
        double distanceSq = bot.R_4764_Y.G_564_y((N_4263_v)target);
        bot.u_1723_Y.J_1907_R(target);
        if (distanceSq > this.J_1907_R * this.J_1907_R) {
            bot.u_1723_Y.n_1700_B(target);
        } else {
            bot.u_1723_Y.J_1907_R();
            bot.R_4764_Y.v_4262_N = new EntityHitResult(target);
            long currentTime = System.currentTimeMillis();
            if (currentTime - this.P_1922_E > 500L) {
                this.G_564_y.J_1907_R();
                this.P_1922_E = currentTime;
            }
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


