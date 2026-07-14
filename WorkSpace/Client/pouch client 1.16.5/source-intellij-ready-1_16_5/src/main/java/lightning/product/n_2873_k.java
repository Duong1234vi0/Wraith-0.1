/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_38_f;
import lightning.product.SoundEvents;
import lightning.product.AbstractTickableSoundInstance;
import lightning.product.SoundEvent;
import lightning.product.X_4340_E;

public class n_2873_k {

    public static class J_1907_R
    extends AbstractTickableSoundInstance {
        private final X_4340_E n_1700_B;
        private int Q_4569_t;

        public J_1907_R(X_4340_E playerIn) {
            super(SoundEvents.w_1457_N, D_38_f.t_148_a);
            this.n_1700_B = playerIn;
            this.s_956_w = true;
            this.u_2550_I = 0;
            this.P_1922_E = 1.0f;
            this.P_4830_p = true;
            this.h_1847_R = true;
        }

        @Override
        public void R_4764_Y() {
            if (!this.n_1700_B.t_4219_U && this.Q_4569_t >= 0) {
                this.Q_4569_t = this.n_1700_B.z_1737_N() ? ++this.Q_4569_t : (this.Q_4569_t -= 2);
                this.Q_4569_t = Math.min(this.Q_4569_t, 40);
                this.P_1922_E = Math.max(0.0f, Math.min((float)this.Q_4569_t / 40.0f, 1.0f));
            } else {
                this.w_1457_N();
            }
        }
    }

    public static class n_1700_B
    extends AbstractTickableSoundInstance {
        private final X_4340_E n_1700_B;

        protected n_1700_B(X_4340_E playerIn, SoundEvent soundIn) {
            super(soundIn, D_38_f.t_148_a);
            this.n_1700_B = playerIn;
            this.s_956_w = false;
            this.u_2550_I = 0;
            this.P_1922_E = 1.0f;
            this.P_4830_p = true;
            this.h_1847_R = true;
        }

        @Override
        public void R_4764_Y() {
            if (this.n_1700_B.t_4219_U || !this.n_1700_B.z_1737_N()) {
                this.w_1457_N();
            }
        }
    }
}


