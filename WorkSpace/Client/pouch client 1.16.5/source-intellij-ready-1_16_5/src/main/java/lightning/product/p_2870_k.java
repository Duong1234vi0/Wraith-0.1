/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.K_4074_S;
import lightning.product.SoundEvents;
import lightning.product.V_4964_s;
import lightning.product.X_4340_E;
import lightning.product.a_3742_W;
import lightning.product.b_4507_u;
import lightning.product.BubbleColumnBlock;

public class p_2870_k
implements V_4964_s {
    private final X_4340_E n_1700_B;
    private boolean J_1907_R;
    private boolean R_4764_Y = true;

    public p_2870_k(X_4340_E player) {
        this.n_1700_B = player;
    }

    @Override
    public void J_1907_R() {
        b_4507_u world = this.n_1700_B.O_508_d;
        K_4074_S blockstate = world.R_4764_Y(this.n_1700_B.i_601_W().grow(0.0, -0.4f, 0.0).shrink(0.001)).filter(state -> state.n_1700_B(a_3742_W.S_4325_V)).findFirst().orElse(null);
        if (blockstate != null) {
            if (!this.J_1907_R && !this.R_4764_Y && blockstate.n_1700_B(a_3742_W.S_4325_V) && !this.n_1700_B.d_2461_k()) {
                boolean flag = blockstate.R_4764_Y(BubbleColumnBlock.P_4830_p);
                if (flag) {
                    this.n_1700_B.n_1700_B(SoundEvents.w_728_N, 1.0f, 1.0f);
                } else {
                    this.n_1700_B.n_1700_B(SoundEvents.RealmsCreateRealmScreen, 1.0f, 1.0f);
                }
            }
            this.J_1907_R = true;
        } else {
            this.J_1907_R = false;
        }
        this.R_4764_Y = false;
    }
}


