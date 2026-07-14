/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.LongRunningTask;
import lightning.product.W_3464_O;
import lightning.product.RetryCallException;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;
import lightning.product.r_715_M;

public class OpenServerTask
extends LongRunningTask {
    private final q_1982_R R_4764_Y;
    private final k_2603_m G_564_y;
    private final boolean P_1922_E;
    private final r_715_M u_1723_Y;

    public OpenServerTask(q_1982_R p_i232232_1_, k_2603_m p_i232232_2_, r_715_M p_i232232_3_, boolean p_i232232_4_) {
        this.R_4764_Y = p_i232232_1_;
        this.G_564_y = p_i232232_2_;
        this.P_1922_E = p_i232232_4_;
        this.u_1723_Y = p_i232232_3_;
    }

    @Override
    public void run() {
        this.J_1907_R(new F_2904_S("mco.configure.world.opening"));
        p_178_J realmsclient = p_178_J.n_1700_B();
        for (int i = 0; i < 25; ++i) {
            if (this.n_1700_B()) {
                return;
            }
            try {
                boolean flag = realmsclient.P_1922_E(this.R_4764_Y.n_1700_B);
                if (!flag) continue;
                if (this.G_564_y instanceof W_3464_O) {
                    ((W_3464_O)this.G_564_y).n_1700_B();
                }
                this.R_4764_Y.P_1922_E = q_1982_R.R_4764_Y.J_1907_R;
                if (this.P_1922_E) {
                    this.u_1723_Y.n_1700_B(this.R_4764_Y, this.G_564_y);
                    break;
                }
                OpenServerTask.n_1700_B(this.G_564_y);
                break;
            }
            catch (RetryCallException retrycallexception) {
                if (this.n_1700_B()) {
                    return;
                }
                OpenServerTask.n_1700_B(retrycallexception.P_1922_E);
                continue;
            }
            catch (Exception exception) {
                if (this.n_1700_B()) {
                    return;
                }
                n_1700_B.error("Failed to open server", (Throwable)exception);
                this.n_1700_B("Failed to open the server");
            }
        }
    }
}


