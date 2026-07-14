/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.util.concurrent.RateLimiter
 */
package lightning.product;

import com.google.common.util.concurrent.RateLimiter;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;
import lightning.product.I_1084_e;
import lightning.product.U_2871_b;
import lightning.product.Y_408_h;
import lightning.product.j_3341_s;

public class RepeatedNarrator {
    private final float n_1700_B;
    private final AtomicReference<n_1700_B> J_1907_R = new AtomicReference();

    public RepeatedNarrator(Duration p_i49961_1_) {
        this.n_1700_B = 1000.0f / (float)p_i49961_1_.toMillis();
    }

    public void n_1700_B(String p_231415_1_) {
        n_1700_B repeatednarrator$parameter = this.J_1907_R.updateAndGet(p_229956_2_ -> p_229956_2_ != null && p_231415_1_.equals(p_229956_2_.n_1700_B) ? p_229956_2_ : new n_1700_B(p_231415_1_, RateLimiter.create((double)this.n_1700_B)));
        if (repeatednarrator$parameter.J_1907_R.tryAcquire(1)) {
            I_1084_e.J_1907_R.n_1700_B(Y_408_h.J_1907_R, new U_2871_b(p_231415_1_), j_3341_s.J_1907_R);
        }
    }

    static class n_1700_B {
        private final String n_1700_B;
        private final RateLimiter J_1907_R;

        n_1700_B(String p_i50913_1_, RateLimiter p_i50913_2_) {
            this.n_1700_B = p_i50913_1_;
            this.J_1907_R = p_i50913_2_;
        }
    }
}


