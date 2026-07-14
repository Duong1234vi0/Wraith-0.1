/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.SoundEvents;
import lightning.product.V_4964_s;
import lightning.product.X_4340_E;
import lightning.product.SoundInstance;
import lightning.product.k_4218_M;
import lightning.product.n_2873_k;

public class UnderwaterAmbientSoundHandler
implements V_4964_s {
    private final X_4340_E n_1700_B;
    private final k_4218_M J_1907_R;
    private int R_4764_Y = 0;

    public UnderwaterAmbientSoundHandler(X_4340_E playerIn, k_4218_M soundHandlerIn) {
        this.n_1700_B = playerIn;
        this.J_1907_R = soundHandlerIn;
    }

    @Override
    public void J_1907_R() {
        --this.R_4764_Y;
        if (this.R_4764_Y <= 0 && this.n_1700_B.z_1737_N()) {
            float f = this.n_1700_B.O_508_d.w_1457_N.nextFloat();
            if (f < 1.0E-4f) {
                this.R_4764_Y = 0;
                this.J_1907_R.n_1700_B((SoundInstance)new n_2873_k.n_1700_B(this.n_1700_B, SoundEvents.Q_2552_b));
            } else if (f < 0.001f) {
                this.R_4764_Y = 0;
                this.J_1907_R.n_1700_B((SoundInstance)new n_2873_k.n_1700_B(this.n_1700_B, SoundEvents.Y_259_p));
            } else if (f < 0.01f) {
                this.R_4764_Y = 0;
                this.J_1907_R.n_1700_B((SoundInstance)new n_2873_k.n_1700_B(this.n_1700_B, SoundEvents.Y_601_j));
            }
        }
    }
}


