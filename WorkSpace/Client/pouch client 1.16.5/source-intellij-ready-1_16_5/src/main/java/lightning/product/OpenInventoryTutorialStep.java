/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.TutorialToast;
import lightning.product.I_14_v;
import lightning.product.W_1671_y;
import lightning.product.t_4467_k;
import lightning.product.TutorialStepInstance;
import lightning.product.x_282_a;

public class OpenInventoryTutorialStep
implements TutorialStepInstance {
    private static final x_282_a n_1700_B = new F_2904_S("tutorial.open_inventory.title");
    private static final x_282_a J_1907_R = new F_2904_S("tutorial.open_inventory.description", W_1671_y.n_1700_B("inventory"));
    private final W_1671_y R_4764_Y;
    private TutorialToast G_564_y;
    private int P_1922_E;

    public OpenInventoryTutorialStep(W_1671_y tutorial) {
        this.R_4764_Y = tutorial;
    }

    @Override
    public void n_1700_B() {
        ++this.P_1922_E;
        if (this.R_4764_Y.u_1723_Y() != I_14_v.J_1907_R) {
            this.R_4764_Y.n_1700_B(t_4467_k.u_1723_Y);
        } else if (this.P_1922_E >= 600 && this.G_564_y == null) {
            this.G_564_y = new TutorialToast(TutorialToast.n_1700_B.G_564_y, n_1700_B, J_1907_R, false);
            this.R_4764_Y.P_1922_E().e_1992_r().n_1700_B(this.G_564_y);
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
    public void R_4764_Y() {
        this.R_4764_Y.n_1700_B(t_4467_k.P_1922_E);
    }
}


