/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.TutorialToast;
import lightning.product.I_14_v;
import lightning.product.ItemTags;
import lightning.product.Stats;
import lightning.product.V_772_m;
import lightning.product.W_1671_y;
import lightning.product.Z_1993_T;
import lightning.product.q_1613_l;
import lightning.product.r_109_r;
import lightning.product.t_4467_k;
import lightning.product.TutorialStepInstance;
import lightning.product.x_282_a;

public class x_3584_Y
implements TutorialStepInstance {
    private static final x_282_a n_1700_B = new F_2904_S("tutorial.craft_planks.title");
    private static final x_282_a J_1907_R = new F_2904_S("tutorial.craft_planks.description");
    private final W_1671_y R_4764_Y;
    private TutorialToast G_564_y;
    private int P_1922_E;

    public x_3584_Y(W_1671_y tutorial) {
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
                if (clientplayerentity.l_1268_F.n_1700_B(ItemTags.R_4764_Y)) {
                    this.R_4764_Y.n_1700_B(t_4467_k.u_1723_Y);
                    return;
                }
                if (x_3584_Y.n_1700_B(clientplayerentity, ItemTags.R_4764_Y)) {
                    this.R_4764_Y.n_1700_B(t_4467_k.u_1723_Y);
                    return;
                }
            }
            if (this.P_1922_E >= 1200 && this.G_564_y == null) {
                this.G_564_y = new TutorialToast(TutorialToast.n_1700_B.P_1922_E, n_1700_B, J_1907_R, false);
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
    public void n_1700_B(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        if (ItemTags.R_4764_Y.n_1700_B(item)) {
            this.R_4764_Y.n_1700_B(t_4467_k.u_1723_Y);
        }
    }

    public static boolean n_1700_B(V_772_m player, r_109_r<q_1613_l> itemsIn) {
        for (q_1613_l item : itemsIn.n_1700_B()) {
            if (player.Q_4569_t().n_1700_B(Stats.J_1907_R.J_1907_R(item)) <= 0) continue;
            return true;
        }
        return false;
    }
}


