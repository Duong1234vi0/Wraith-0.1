/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.I_686_h;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.x_4991_F;
import lightning.product.y_2603_k;

public class y_3130_n
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0423\u0441\u043a\u043e\u0440\u0435\u043d\u0438\u0435", 0.8f, 0.1f, 1.0f, 0.1f);

    public y_3130_n() {
        super("FastBreak", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N);
    }

    @Y_1740_V
    public void n_1700_B(x_4991_F e) {
        if (y_3130_n.c_3005_b.Y_259_p.G_624_v()) {
            return;
        }
        y_3130_n.c_3005_b.w_1457_N.blockHitDelay = 0;
        if (y_3130_n.c_3005_b.w_1457_N.curBlockDamageMP > ((Float)this.v_4262_N.J_1907_R()).floatValue()) {
            y_3130_n.c_3005_b.w_1457_N.curBlockDamageMP = 1.0f;
        }
    }
}

