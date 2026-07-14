/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 */
package lightning.product;

import com.google.common.collect.Queues;
import java.util.ArrayDeque;
import java.util.Deque;
import lightning.product.D_1098_v;
import lightning.product.j_3341_s;
import lightning.product.o_1290_k;
import lightning.product.u_530_F;
import lightning.product.w_3785_E;

public class g_221_o {
    Deque<n_1700_B> n_1700_B = new ArrayDeque<n_1700_B>();
    private final Deque<n_1700_B> J_1907_R = j_3341_s.n_1700_B(Queues.newArrayDeque(), p_lambda$new$0_0_ -> {
        D_1098_v matrix4f = new D_1098_v();
        matrix4f.n_1700_B();
        o_1290_k matrix3f = new o_1290_k();
        matrix3f.R_4764_Y();
        p_lambda$new$0_0_.add(new n_1700_B(matrix4f, matrix3f));
    });

    public void n_1700_B(double x, double y, double z) {
        n_1700_B matrixstack$entry = this.J_1907_R.getLast();
        matrixstack$entry.n_1700_B.R_4764_Y((float)x, (float)y, (float)z);
    }

    public void n_1700_B(float x, float y, float z) {
        n_1700_B matrixstack$entry = this.J_1907_R.getLast();
        matrixstack$entry.n_1700_B.G_564_y(x, y, z);
        if (x == y && y == z) {
            if (x > 0.0f) {
                return;
            }
            matrixstack$entry.J_1907_R.n_1700_B(-1.0f);
        }
        float f = 1.0f / x;
        float f1 = 1.0f / y;
        float f2 = 1.0f / z;
        float f3 = u_530_F.s_956_w(f * f1 * f2);
        matrixstack$entry.J_1907_R.J_1907_R(o_1290_k.n_1700_B(f3 * f, f3 * f1, f3 * f2));
    }

    public void n_1700_B(w_3785_E quaternion) {
        n_1700_B matrixstack$entry = this.J_1907_R.getLast();
        matrixstack$entry.n_1700_B.n_1700_B(quaternion);
        matrixstack$entry.J_1907_R.n_1700_B(quaternion);
    }

    public void n_1700_B() {
        n_1700_B matrixstack$entry = this.J_1907_R.getLast();
        n_1700_B matrixstack$entry1 = this.n_1700_B.pollLast();
        if (matrixstack$entry1 == null) {
            matrixstack$entry1 = new n_1700_B(matrixstack$entry.n_1700_B.u_1723_Y(), matrixstack$entry.J_1907_R.u_1723_Y());
        } else {
            matrixstack$entry1.n_1700_B.J_1907_R(matrixstack$entry.n_1700_B);
            matrixstack$entry1.J_1907_R.n_1700_B(matrixstack$entry.J_1907_R);
        }
        this.J_1907_R.addLast(matrixstack$entry1);
    }

    public void J_1907_R() {
        n_1700_B matrixstack$entry = this.J_1907_R.removeLast();
        if (matrixstack$entry != null) {
            this.n_1700_B.add(matrixstack$entry);
        }
    }

    public n_1700_B R_4764_Y() {
        return this.J_1907_R.getLast();
    }

    public boolean G_564_y() {
        return this.J_1907_R.size() == 1;
    }

    public String toString() {
        return this.R_4764_Y().toString();
    }

    public static final class n_1700_B {
        private final D_1098_v n_1700_B;
        private final o_1290_k J_1907_R;

        private n_1700_B(D_1098_v matrix, o_1290_k normal) {
            this.n_1700_B = matrix;
            this.J_1907_R = normal;
        }

        public D_1098_v n_1700_B() {
            return this.n_1700_B;
        }

        public o_1290_k J_1907_R() {
            return this.J_1907_R;
        }

        public String toString() {
            return this.n_1700_B.toString() + this.J_1907_R.toString();
        }
    }
}

