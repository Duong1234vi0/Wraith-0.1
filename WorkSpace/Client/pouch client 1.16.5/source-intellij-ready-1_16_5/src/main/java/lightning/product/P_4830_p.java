/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.HashSet;
import java.util.Set;
import lightning.product.BlockHitResult;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.Y_259_p;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.n_1700_B;
import lightning.product.u_1723_Y;

public class P_4830_p
implements u_1723_Y {
    private final Set<T_2915_h> n_1700_B = new HashSet<T_2915_h>();
    private final double J_1907_R;
    private final Y_259_p R_4764_Y;
    private c_1514_x G_564_y = null;
    private static final double P_1922_E = 4.0;
    private static final int u_1723_Y = 20;

    public P_4830_p() {
        this(4.0);
    }

    public P_4830_p(double miningRange) {
        this.J_1907_R = miningRange;
        this.R_4764_Y = new Y_259_p(null);
    }

    public void n_1700_B(T_2915_h block) {
        this.n_1700_B.add(block);
    }

    public void n_1700_B() {
        this.n_1700_B.clear();
    }

    @Override
    public void n_1700_B(n_1700_B bot) {
        if (bot == null || bot.R_4764_Y == null || bot.J_1907_R == null) {
            return;
        }
        if (this.R_4764_Y.n_1700_B() == null) {
            this.R_4764_Y.n_1700_B(bot);
        }
        if (this.G_564_y == null || !this.J_1907_R(bot, this.G_564_y)) {
            this.G_564_y = this.J_1907_R(bot);
        }
        if (this.G_564_y == null) {
            return;
        }
        e_2866_D botPos = bot.R_4764_Y.s_4990_V();
        e_2866_D targetPos = new e_2866_D((double)this.G_564_y.getX() + 0.5, (double)this.G_564_y.getY() + 0.5, (double)this.G_564_y.getZ() + 0.5);
        double distance = botPos.u_1723_Y(targetPos);
        this.n_1700_B(bot, this.G_564_y);
        if (distance > this.J_1907_R) {
            bot.u_1723_Y.n_1700_B(this.G_564_y);
            return;
        }
        bot.u_1723_Y.J_1907_R();
        bot.R_4764_Y.v_4262_N = new BlockHitResult(botPos, this.n_1700_B(botPos, targetPos), this.G_564_y, false);
        this.R_4764_Y.J_1907_R();
    }

    private b_257_Y n_1700_B(e_2866_D from, e_2866_D to) {
        e_2866_D dir = to.G_564_y(from).G_564_y();
        double x = dir.J_1907_R;
        double y = dir.R_4764_Y;
        double z = dir.G_564_y;
        if (Math.abs(x) > Math.abs(y) && Math.abs(x) > Math.abs(z)) {
            return x > 0.0 ? b_257_Y.u_1723_Y : b_257_Y.P_1922_E;
        }
        if (Math.abs(y) > Math.abs(z)) {
            return y > 0.0 ? b_257_Y.J_1907_R : b_257_Y.n_1700_B;
        }
        return z > 0.0 ? b_257_Y.G_564_y : b_257_Y.R_4764_Y;
    }

    private void n_1700_B(n_1700_B bot, c_1514_x blockPos) {
        e_2866_D botPos = bot.R_4764_Y.s_4990_V().J_1907_R(0.0, bot.R_4764_Y.X_1313_W(), 0.0);
        e_2866_D targetPos = new e_2866_D((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
        double deltaX = targetPos.J_1907_R - botPos.J_1907_R;
        double deltaY = targetPos.R_4764_Y - botPos.R_4764_Y;
        double deltaZ = targetPos.G_564_y - botPos.G_564_y;
        double horizontalDistance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        float yaw = (float)Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(deltaY, horizontalDistance)));
        bot.R_4764_Y.p_178_J = yaw;
        bot.R_4764_Y.f_4016_n = pitch;
    }

    private boolean J_1907_R(n_1700_B bot, c_1514_x pos) {
        if (!bot.J_1907_R.M_588_G(pos)) {
            return false;
        }
        K_4074_S state = bot.J_1907_R.getBlockState(pos);
        return this.n_1700_B.contains(state.J_1907_R());
    }

    private c_1514_x J_1907_R(n_1700_B bot) {
        c_1514_x botPos = bot.R_4764_Y.b_2312_j();
        c_1514_x nearest = null;
        double minDistance = Double.MAX_VALUE;
        for (int x = -20; x <= 20; ++x) {
            for (int y = -20; y <= 20; ++y) {
                for (int z = -20; z <= 20; ++z) {
                    double distance;
                    c_1514_x pos = botPos.add(x, y, z);
                    if (!this.J_1907_R(bot, pos) || !((distance = pos.distanceSq(botPos)) < minDistance)) continue;
                    minDistance = distance;
                    nearest = pos;
                }
            }
        }
        return nearest;
    }
}


