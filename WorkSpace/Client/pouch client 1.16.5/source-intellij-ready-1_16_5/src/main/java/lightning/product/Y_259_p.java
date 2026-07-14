/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.Z_875_P;
import lightning.product.c_1514_x;
import lightning.product.n_1700_B;
import lightning.product.EntityHitResult;
import lightning.product.x_1688_C;

public class Y_259_p {
    private n_1700_B n_1700_B;

    public Y_259_p(n_1700_B bot) {
        this.n_1700_B = bot;
    }

    public n_1700_B n_1700_B() {
        return this.n_1700_B;
    }

    public void n_1700_B(n_1700_B bot) {
        this.n_1700_B = bot;
    }

    public void J_1907_R() {
        if (this.n_1700_B == null) {
            return;
        }
        Z_875_P player = this.n_1700_B.P_1922_E.Q_2552_b;
        if (!player.e_4240_b()) {
            switch (player.v_4262_N.R_4764_Y()) {
                case R_4764_Y: {
                    this.n_1700_B.G_564_y.n_1700_B(player, ((EntityHitResult)player.v_4262_N).n_1700_B());
                    break;
                }
                case J_1907_R: {
                    BlockHitResult blockraytraceresult = (BlockHitResult)player.v_4262_N;
                    c_1514_x blockpos = blockraytraceresult.n_1700_B();
                    if (!this.n_1700_B.P_1922_E.G_564_y().getBlockState(blockpos).v_4262_N()) {
                        this.n_1700_B.G_564_y.n_1700_B(blockpos, blockraytraceresult.J_1907_R());
                        break;
                    }
                }
                case n_1700_B: {
                    player.ModuleCategory();
                }
            }
            player.n_1700_B(x_1688_C.n_1700_B);
        }
    }
}



