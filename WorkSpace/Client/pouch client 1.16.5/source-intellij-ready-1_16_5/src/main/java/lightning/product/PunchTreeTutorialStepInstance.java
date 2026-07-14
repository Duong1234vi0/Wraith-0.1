/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.TutorialToast;
import lightning.product.I_14_v;
import lightning.product.K_4074_S;
import lightning.product.ItemTags;
import lightning.product.V_772_m;
import lightning.product.W_1671_y;
import lightning.product.Z_1993_T;
import lightning.product.c_1514_x;
import lightning.product.j_744_k_0;
import lightning.product.k_4690_i;
import lightning.product.BlockTags;
import lightning.product.t_4467_k;
import lightning.product.TutorialStepInstance;
import lightning.product.x_282_a;

public class PunchTreeTutorialStepInstance
implements TutorialStepInstance {
    private static final x_282_a n_1700_B = new F_2904_S("tutorial.punch_tree.title");
    private static final x_282_a J_1907_R = new F_2904_S("tutorial.punch_tree.description", W_1671_y.n_1700_B("attack"));
    private final W_1671_y R_4764_Y;
    private TutorialToast G_564_y;
    private int P_1922_E;
    private int u_1723_Y;

    public PunchTreeTutorialStepInstance(W_1671_y tutorial) {
        this.R_4764_Y = tutorial;
    }

    @Override
    public void n_1700_B() {
        ++this.P_1922_E;
        if (this.R_4764_Y.u_1723_Y() != I_14_v.J_1907_R) {
            this.R_4764_Y.n_1700_B(t_4467_k.u_1723_Y);
        } else {
            V_772_m clientplayerentity;
            if (this.P_1922_E == 1 && (clientplayerentity = this.R_4764_Y.P_1922_E().Y_259_p) != null) {
                if (clientplayerentity.l_1268_F.n_1700_B(ItemTags.t_1786_h)) {
                    this.R_4764_Y.n_1700_B(t_4467_k.P_1922_E);
                    return;
                }
                if (j_744_k_0.n_1700_B(clientplayerentity)) {
                    this.R_4764_Y.n_1700_B(t_4467_k.P_1922_E);
                    return;
                }
            }
            if ((this.P_1922_E >= 600 || this.u_1723_Y > 3) && this.G_564_y == null) {
                this.G_564_y = new TutorialToast(TutorialToast.n_1700_B.R_4764_Y, n_1700_B, J_1907_R, true);
                this.R_4764_Y.P_1922_E().e_1992_r().n_1700_B(this.G_564_y);
            }
        }
    }

    @Override
    public void J_1907_R() {
        if (this.G_564_y != null) {
            this.G_564_y.G_564_y();
            this.G_564_y = null;
        }
    }

    @Override
    public void n_1700_B(k_4690_i worldIn, c_1514_x pos, K_4074_S state, float diggingStage) {
        boolean flag = state.n_1700_B(BlockTags.w_1457_N);
        if (flag && diggingStage > 0.0f) {
            if (this.G_564_y != null) {
                this.G_564_y.n_1700_B(diggingStage);
            }
            if (diggingStage >= 1.0f) {
                this.R_4764_Y.n_1700_B(t_4467_k.G_564_y);
            }
        } else if (this.G_564_y != null) {
            this.G_564_y.n_1700_B(0.0f);
        } else if (flag) {
            ++this.u_1723_Y;
        }
    }

    @Override
    public void n_1700_B(Z_1993_T stack) {
        if (ItemTags.t_1786_h.n_1700_B(stack.J_1907_R())) {
            this.R_4764_Y.n_1700_B(t_4467_k.P_1922_E);
        }
    }
}


