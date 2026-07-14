/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;
import lightning.product.A_4115_X;
import lightning.product.I_686_h;
import lightning.product.S_2828_i;
import lightning.product.T_2915_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.h_1015_G;
import lightning.product.o_148_s;
import lightning.product.p_1977_n;
import lightning.product.x_1688_C;
import lightning.product.x_4991_F;
import lightning.product.y_2603_k;

public class E_816_W
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 3.0f, 1.0f, 5.0f, 1.0f);
    private final p_1977_n w_1484_f = new p_1977_n("\u041b\u043e\u043c\u0430\u0442\u044c \u0432\u0441\u0435 \u0431\u043b\u043e\u043a\u0438", false);
    private c_1514_x t_148_a = null;
    private boolean s_956_w = false;

    public E_816_W() {
        super("Nuker", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        S_2828_i manager = o_148_s.Y_601_j().M_588_G();
        if (!this.w_1484_f.t_148_a().booleanValue() && manager.h_1847_R()) {
            this.t_1786_h();
            return;
        }
        if (!this.h_1847_R()) {
            this.Q_4569_t();
        }
        if (this.t_148_a != null) {
            this.M_182_A();
        }
    }

    private boolean h_1847_R() {
        if (this.t_148_a == null) {
            return false;
        }
        if (E_816_W.c_3005_b.Y_601_j.u_1723_Y(this.t_148_a)) {
            this.t_1786_h();
            return false;
        }
        double distance = E_816_W.c_3005_b.Y_259_p.v_4262_N((double)this.t_148_a.getX() + 0.5, (double)this.t_148_a.getY() + 0.5, (double)this.t_148_a.getZ() + 0.5);
        if (distance > (double)(((Float)this.v_4262_N.J_1907_R()).floatValue() * ((Float)this.v_4262_N.J_1907_R()).floatValue())) {
            this.t_1786_h();
            return false;
        }
        return true;
    }

    private void Q_4569_t() {
        S_2828_i manager = o_148_s.Y_601_j().M_588_G();
        int range = ((Float)this.v_4262_N.J_1907_R()).intValue();
        CopyOnWriteArrayList<c_1514_x> blockPositions = new CopyOnWriteArrayList<c_1514_x>();
        for (int x = -range; x <= range; ++x) {
            for (int y = 0; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    boolean shouldBreak;
                    c_1514_x blockPos = E_816_W.c_3005_b.Y_259_p.b_2312_j().add(x, y, z);
                    if (E_816_W.c_3005_b.Y_601_j.u_1723_Y(blockPos)) continue;
                    T_2915_h block = E_816_W.c_3005_b.Y_601_j.getBlockState(blockPos).J_1907_R();
                    boolean bl = shouldBreak = this.w_1484_f.t_148_a() != false || manager.n_1700_B(block);
                    if (!shouldBreak) continue;
                    blockPositions.add(blockPos);
                }
            }
        }
        if (!blockPositions.isEmpty()) {
            blockPositions.sort(Comparator.comparingDouble(pos -> E_816_W.c_3005_b.Y_259_p.v_4262_N((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5)));
            this.t_148_a = (c_1514_x)blockPositions.get(0);
            this.s_956_w = false;
        }
    }

    private void M_182_A() {
        if (this.t_148_a == null) {
            return;
        }
        A_4115_X.n_1700_B(new x_4991_F(E_816_W.c_3005_b.Y_601_j.getBlockState(this.t_148_a), this.t_148_a, x_4991_F.n_1700_B.n_1700_B));
        boolean result = E_816_W.c_3005_b.w_1457_N.onPlayerDamageBlock(this.t_148_a, b_257_Y.J_1907_R);
        if (result) {
            E_816_W.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
            this.s_956_w = true;
        }
        if (this.s_956_w && !E_816_W.c_3005_b.w_1457_N.getIsHittingBlock()) {
            this.t_1786_h();
        }
        A_4115_X.n_1700_B(new x_4991_F(E_816_W.c_3005_b.Y_601_j.getBlockState(this.t_148_a), this.t_148_a, x_4991_F.n_1700_B.J_1907_R));
    }

    private void t_1786_h() {
        this.t_148_a = null;
        this.s_956_w = false;
        if (E_816_W.c_3005_b.w_1457_N.getIsHittingBlock()) {
            E_816_W.c_3005_b.w_1457_N.resetBlockRemoving();
        }
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        this.t_1786_h();
    }
}

