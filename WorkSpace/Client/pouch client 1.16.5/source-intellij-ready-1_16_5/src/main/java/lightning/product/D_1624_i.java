/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Queues;
import java.util.Arrays;
import java.util.Deque;
import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.Toast;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.j_3341_s;
import lightning.product.u_530_F;

public class D_1624_i
extends C_2701_A {
    private final MinecraftClient n_1700_B;
    private final n_1700_B<?>[] J_1907_R = new n_1700_B[5];
    private final Deque<Toast> R_4764_Y = Queues.newArrayDeque();

    public D_1624_i(MinecraftClient mcIn) {
        this.n_1700_B = mcIn;
    }

    public void n_1700_B(g_221_o p_238541_1_) {
        if (!this.n_1700_B.P_4830_p.RetryCallException) {
            for (int i = 0; i < this.J_1907_R.length; ++i) {
                n_1700_B<?> toastinstance = this.J_1907_R[i];
                if (toastinstance != null && toastinstance.n_1700_B(this.n_1700_B.RealmsServerPing().Q_4569_t(), i, p_238541_1_)) {
                    this.J_1907_R[i] = null;
                }
                if (this.J_1907_R[i] != null || this.R_4764_Y.isEmpty()) continue;
                this.J_1907_R[i] = new n_1700_B(this, this.R_4764_Y.removeFirst());
            }
        }
    }

    @Nullable
    public <T extends Toast> T n_1700_B(Class<? extends T> p_192990_1_, Object p_192990_2_) {
        for (n_1700_B<?> toastinstance : this.J_1907_R) {
            if (toastinstance == null || !p_192990_1_.isAssignableFrom(toastinstance.n_1700_B().getClass()) || !toastinstance.n_1700_B().n_1700_B().equals(p_192990_2_)) continue;
            return (T)toastinstance.n_1700_B();
        }
        for (Toast itoast : this.R_4764_Y) {
            if (!p_192990_1_.isAssignableFrom(itoast.getClass()) || !itoast.n_1700_B().equals(p_192990_2_)) continue;
            return (T)itoast;
        }
        return (T)((Toast)null);
    }

    public void n_1700_B() {
        Arrays.fill(this.J_1907_R, null);
        this.R_4764_Y.clear();
    }

    public void n_1700_B(Toast toastIn) {
        this.R_4764_Y.add(toastIn);
    }

    public MinecraftClient J_1907_R() {
        return this.n_1700_B;
    }

    static class n_1700_B<T extends Toast> {
        private final T J_1907_R;
        private long R_4764_Y = -1L;
        private long G_564_y = -1L;
        private Toast.n_1700_B P_1922_E = Toast.n_1700_B.n_1700_B;
        final /* synthetic */ D_1624_i n_1700_B;

        private n_1700_B(T toastIn) {
            this.n_1700_B = this$0;
            this.J_1907_R = toastIn;
        }

        public T n_1700_B() {
            return this.J_1907_R;
        }

        private float n_1700_B(long p_193686_1_) {
            float f = u_530_F.n_1700_B((float)(p_193686_1_ - this.R_4764_Y) / 600.0f, 0.0f, 1.0f);
            f *= f;
            return this.P_1922_E == Toast.n_1700_B.J_1907_R ? 1.0f - f : f;
        }

        public boolean n_1700_B(int p_193684_1_, int p_193684_2_, g_221_o p_193684_3_) {
            long i = j_3341_s.J_1907_R();
            if (this.R_4764_Y == -1L) {
                this.R_4764_Y = i;
                this.P_1922_E.n_1700_B(this.n_1700_B.n_1700_B.Z_976_R());
            }
            if (this.P_1922_E == Toast.n_1700_B.n_1700_B && i - this.R_4764_Y <= 600L) {
                this.G_564_y = i;
            }
            c_4037_x.v_4276_D();
            c_4037_x.R_4764_Y((float)p_193684_1_ - (float)this.J_1907_R.J_1907_R() * this.n_1700_B(i), (float)(p_193684_2_ * this.J_1907_R.R_4764_Y()), (float)(800 + p_193684_2_));
            Toast.n_1700_B itoast$visibility = this.J_1907_R.func_230444_a_(p_193684_3_, this.n_1700_B, i - this.G_564_y);
            c_4037_x.d_2461_k();
            if (itoast$visibility != this.P_1922_E) {
                this.R_4764_Y = i - (long)((int)((1.0f - this.n_1700_B(i)) * 600.0f));
                this.P_1922_E = itoast$visibility;
                this.P_1922_E.n_1700_B(this.n_1700_B.n_1700_B.Z_976_R());
            }
            return this.P_1922_E == Toast.n_1700_B.J_1907_R && i - this.R_4764_Y > 600L;
        }
    }
}



