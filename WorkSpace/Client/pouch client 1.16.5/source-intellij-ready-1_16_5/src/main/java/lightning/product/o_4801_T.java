/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_60_a;
import lightning.product.F_2904_S;
import lightning.product.LongRunningTask;
import lightning.product.W_3464_O;
import lightning.product.RetryCallException;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.u_744_e;
import lightning.product.w_728_N;

public class o_4801_T
extends LongRunningTask {
    private final D_60_a R_4764_Y;
    private final long G_564_y;
    private final W_3464_O P_1922_E;

    public o_4801_T(D_60_a p_i232234_1_, long p_i232234_2_, W_3464_O p_i232234_4_) {
        this.R_4764_Y = p_i232234_1_;
        this.G_564_y = p_i232234_2_;
        this.P_1922_E = p_i232234_4_;
    }

    @Override
    public void run() {
        this.J_1907_R(new F_2904_S("mco.backup.restoring"));
        p_178_J realmsclient = p_178_J.n_1700_B();
        for (int i = 0; i < 25; ++i) {
            try {
                if (this.n_1700_B()) {
                    return;
                }
                realmsclient.R_4764_Y(this.G_564_y, this.R_4764_Y.n_1700_B);
                o_4801_T.n_1700_B(1);
                if (this.n_1700_B()) {
                    return;
                }
                o_4801_T.n_1700_B(this.P_1922_E.J_1907_R());
                return;
            }
            catch (RetryCallException retrycallexception) {
                if (this.n_1700_B()) {
                    return;
                }
                o_4801_T.n_1700_B(retrycallexception.P_1922_E);
                continue;
            }
            catch (u_744_e realmsserviceexception) {
                if (this.n_1700_B()) {
                    return;
                }
                n_1700_B.error("Couldn't restore backup", (Throwable)realmsserviceexception);
                o_4801_T.n_1700_B(new w_728_N(realmsserviceexception, (k_2603_m)this.P_1922_E));
                return;
            }
            catch (Exception exception) {
                if (this.n_1700_B()) {
                    return;
                }
                n_1700_B.error("Couldn't restore backup", (Throwable)exception);
                this.n_1700_B(exception.getLocalizedMessage());
                return;
            }
        }
    }
}


