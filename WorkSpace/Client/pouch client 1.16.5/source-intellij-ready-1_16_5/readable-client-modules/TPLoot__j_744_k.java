/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import lightning.product.I_686_h;
import lightning.product.N_3268_u;
import lightning.product.N_4263_v;
import lightning.product.U_2871_b;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.b_2152_i;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.n_1494_c;
import lightning.product.q_1613_l;
import lightning.product.q_366_O;
import lightning.product.q_4592_V;
import lightning.product.v_1900_v;
import lightning.product.y_2603_k;

public class j_744_k
extends X_3546_T
implements b_2152_i {
    private final q_366_O v_4262_N = new q_366_O("\u0420\u0435\u0436\u0438\u043c", "LonyGrief", "LonyGrief");
    private final I_686_h w_1484_f = new I_686_h("\u0414\u0438\u0441\u0442\u0430\u043d\u0446\u0438\u044f", 50.0f, 5.0f, 100.0f, 1.0f);
    private e_2866_D t_148_a = null;
    private n_1494_c s_956_w = null;
    private boolean u_2550_I = false;

    public j_744_k() {
        super("TPLoot", y_2603_k.P_1922_E);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    @Override
    public void n_1700_B() {
        v_1900_v.n_1700_B(new U_2871_b("\u00a77\u0414\u043b\u044f \u0440\u0430\u0431\u043e\u0442\u044b \u0422\u041f \u041b\u0443\u0442\u0430 \u043d\u0443\u0436\u043d\u043e \u0434\u0435\u0440\u0436\u0430\u0442\u044c \u0432 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435 \u0448\u0430\u043b\u043a\u0435\u0440 \u0438 \u0438\u043c\u0435\u0442\u044c \u0437\u0430\u043f\u043e\u043b\u043d\u0435\u043d\u043d\u044b\u0439 \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c"), new Object[0]);
        super.n_1700_B();
        this.t_148_a = null;
        this.s_956_w = null;
        this.u_2550_I = false;
    }

    @Override
    public void J_1907_R() {
        super.J_1907_R();
        if (this.t_148_a != null && j_744_k.c_3005_b.Y_259_p != null) {
            this.n_1700_B(this.t_148_a);
        }
        this.t_148_a = null;
        this.s_956_w = null;
        this.u_2550_I = false;
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        n_1494_c nearestValuable;
        if (j_744_k.c_3005_b.Y_259_p == null || j_744_k.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.u_2550_I) {
            this.u_2550_I = false;
            this.t_148_a = null;
            return;
        }
        if (this.t_148_a == null) {
            this.t_148_a = j_744_k.c_3005_b.Y_259_p.s_4990_V();
        }
        if ((nearestValuable = this.h_1847_R()) != null && nearestValuable.H_3699_F()) {
            this.s_956_w = nearestValuable;
            e_2866_D itemPos = nearestValuable.s_4990_V();
            this.n_1700_B(itemPos);
            if (!nearestValuable.H_3699_F()) {
                this.Q_4569_t();
            }
        } else if (this.s_956_w != null && !this.s_956_w.H_3699_F()) {
            this.Q_4569_t();
            this.s_956_w = null;
        }
    }

    private n_1494_c h_1847_R() {
        if (j_744_k.c_3005_b.Y_601_j == null || j_744_k.c_3005_b.Y_259_p == null) {
            return null;
        }
        double maxDistance = ((Float)this.w_1484_f.J_1907_R()).doubleValue();
        e_2866_D playerPos = j_744_k.c_3005_b.Y_259_p.s_4990_V();
        List valuables = StreamSupport.stream(j_744_k.c_3005_b.Y_601_j.J_1907_R().spliterator(), false).filter(entity -> entity instanceof n_1494_c).map(entity -> (n_1494_c)entity).filter(N_4263_v::H_3699_F).filter(item -> {
            Z_1993_T stack = item.P_1922_E();
            if (stack.n_1700_B()) {
                return false;
            }
            q_1613_l itemObj = stack.J_1907_R();
            if (itemObj == q_4592_V.u_488_m || itemObj == q_4592_V.O_1043_U || itemObj == q_4592_V.v_1900_v || itemObj == q_4592_V.j_2129_E) {
                return true;
            }
            if (itemObj == q_4592_V.p_863_D) {
                return true;
            }
            if (itemObj == q_4592_V.E_4612_l) {
                return true;
            }
            if (itemObj == q_4592_V.N_81_X) {
                return true;
            }
            return itemObj == q_4592_V.C_3560_B;
        }).filter(item -> {
            double distance = playerPos.u_1723_Y(item.s_4990_V());
            return distance <= maxDistance && distance > 0.4;
        }).sorted(Comparator.comparingDouble(item -> playerPos.u_1723_Y(item.s_4990_V()))).collect(Collectors.toList());
        return valuables.isEmpty() ? null : (n_1494_c)valuables.get(0);
    }

    private void n_1700_B(e_2866_D targetPos) {
        if (j_744_k.c_3005_b.Y_259_p == null || j_744_k.c_3005_b.Y_259_p.n_1700_B == null) {
            return;
        }
        j_744_k.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.n_1700_B(targetPos.J_1907_R, targetPos.R_4764_Y, targetPos.G_564_y, j_744_k.c_3005_b.Y_259_p.M_1641_O()));
        j_744_k.c_3005_b.Y_259_p.J_1907_R(targetPos.J_1907_R, targetPos.R_4764_Y, targetPos.G_564_y);
    }

    private void Q_4569_t() {
        if (this.t_148_a == null || j_744_k.c_3005_b.Y_259_p == null) {
            return;
        }
        e_2866_D currentPos = j_744_k.c_3005_b.Y_259_p.s_4990_V();
        if (currentPos.u_1723_Y(this.t_148_a) > 0.6) {
            this.n_1700_B(this.t_148_a);
            this.u_2550_I = true;
        } else {
            this.t_148_a = null;
            this.u_2550_I = false;
        }
    }
}

