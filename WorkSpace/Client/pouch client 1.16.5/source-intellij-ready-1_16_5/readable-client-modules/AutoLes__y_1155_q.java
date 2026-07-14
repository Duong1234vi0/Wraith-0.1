/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lightning.product.K_4074_S;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.o_4293_W;
import lightning.product.x_1688_C;
import lightning.product.y_2603_k;

public class y_1155_q
extends X_3546_T {
    private c_1514_x v_4262_N;
    private static final double w_1484_f = 4.5;
    private static final double t_148_a = 20.25;

    public y_1155_q() {
        super("AutoLes", y_2603_k.G_564_y);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        this.h_1847_R();
    }

    private void h_1847_R() {
        if (y_1155_q.c_3005_b.Y_259_p == null || y_1155_q.c_3005_b.Y_601_j == null) {
            this.v_4262_N = null;
            return;
        }
        if (this.v_4262_N != null) {
            if (this.n_1700_B(this.v_4262_N)) {
                K_4074_S state = y_1155_q.c_3005_b.Y_601_j.getBlockState(this.v_4262_N);
                if (state.v_4262_N()) {
                    this.v_4262_N = null;
                } else {
                    y_1155_q.c_3005_b.w_1457_N.spoofInstantDig(this.v_4262_N, b_257_Y.J_1907_R);
                    y_1155_q.c_3005_b.w_1457_N.onPlayerDestroyBlock(this.v_4262_N);
                    y_1155_q.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
                }
            } else {
                this.v_4262_N = null;
            }
        }
        if (this.v_4262_N == null) {
            c_1514_x playerPos = new c_1514_x(y_1155_q.c_3005_b.Y_259_p.O_3598_v(), y_1155_q.c_3005_b.Y_259_p.X_2960_b(), y_1155_q.c_3005_b.Y_259_p.l_2647_k());
            c_1514_x from = playerPos.add(-5, -5, -5);
            c_1514_x to = playerPos.add(5, 5, 5);
            List<c_1514_x> blocks = this.n_1700_B(from, to);
            this.v_4262_N = blocks.stream().filter(this::J_1907_R).filter(this::n_1700_B).min(Comparator.comparing(pos -> y_1155_q.c_3005_b.Y_259_p.u_1723_Y(e_2866_D.n_1700_B(pos)))).orElse(null);
            if (this.v_4262_N != null) {
                y_1155_q.c_3005_b.w_1457_N.spoofInstantDig(this.v_4262_N, b_257_Y.J_1907_R);
                y_1155_q.c_3005_b.w_1457_N.onPlayerDestroyBlock(this.v_4262_N);
                y_1155_q.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
            }
        }
    }

    private List<c_1514_x> n_1700_B(c_1514_x from, c_1514_x to) {
        ArrayList<c_1514_x> result = new ArrayList<c_1514_x>();
        int minX = Math.min(from.getX(), to.getX());
        int minY = Math.min(from.getY(), to.getY());
        int minZ = Math.min(from.getZ(), to.getZ());
        int maxX = Math.max(from.getX(), to.getX());
        int maxY = Math.max(from.getY(), to.getY());
        int maxZ = Math.max(from.getZ(), to.getZ());
        for (int x = minX; x <= maxX; ++x) {
            for (int y = minY; y <= maxY; ++y) {
                for (int z = minZ; z <= maxZ; ++z) {
                    result.add(new c_1514_x(x, y, z));
                }
            }
        }
        return result;
    }

    private boolean n_1700_B(c_1514_x pos) {
        if (y_1155_q.c_3005_b.Y_259_p == null) {
            return false;
        }
        double distanceSq = y_1155_q.c_3005_b.Y_259_p.u_1723_Y(e_2866_D.n_1700_B(pos));
        return distanceSq <= 20.25;
    }

    private boolean J_1907_R(c_1514_x pos) {
        if (y_1155_q.c_3005_b.Y_601_j == null) {
            return false;
        }
        K_4074_S state = y_1155_q.c_3005_b.Y_601_j.getBlockState(pos);
        return !state.v_4262_N() && state.n_1700_B(o_4293_W.w_1457_N);
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.v_4262_N = null;
    }
}

