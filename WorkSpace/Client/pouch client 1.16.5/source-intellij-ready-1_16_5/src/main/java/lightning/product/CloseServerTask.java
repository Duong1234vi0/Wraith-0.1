/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.LongRunningTask;
import lightning.product.W_3464_O;
import lightning.product.RetryCallException;
import lightning.product.p_178_J;
import lightning.product.q_1982_R;

public class CloseServerTask
extends LongRunningTask {
    private final q_1982_R R_4764_Y;
    private final W_3464_O G_564_y;

    public CloseServerTask(q_1982_R p_i232228_1_, W_3464_O p_i232228_2_) {
        this.R_4764_Y = p_i232228_1_;
        this.G_564_y = p_i232228_2_;
    }

    @Override
    public void run() {
        this.J_1907_R(new F_2904_S("mco.configure.world.closing"));
        p_178_J realmsclient = p_178_J.n_1700_B();
        for (int i = 0; i < 25; ++i) {
            if (this.n_1700_B()) {
                return;
            }
            try {
                boolean flag = realmsclient.u_1723_Y(this.R_4764_Y.n_1700_B);
                if (!flag) continue;
                this.G_564_y.n_1700_B();
                this.R_4764_Y.P_1922_E = q_1982_R.R_4764_Y.n_1700_B;
                CloseServerTask.n_1700_B(this.G_564_y);
                break;
            }
            catch (RetryCallException retrycallexception) {
                if (this.n_1700_B()) {
                    return;
                }
                CloseServerTask.n_1700_B(retrycallexception.P_1922_E);
                continue;
            }
            catch (Exception exception) {
                if (this.n_1700_B()) {
                    return;
                }
                n_1700_B.error("Failed to close server", (Throwable)exception);
                this.n_1700_B("Failed to close the server");
            }
        }
    }
}


