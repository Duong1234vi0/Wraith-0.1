/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_290_v;
import lightning.product.F_1410_V;
import lightning.product.F_2904_S;
import lightning.product.LongRunningTask;
import lightning.product.RetryCallException;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.u_744_e;
import lightning.product.w_728_N;

public class DownloadTask
extends LongRunningTask {
    private final long R_4764_Y;
    private final int G_564_y;
    private final k_2603_m P_1922_E;
    private final String u_1723_Y;

    public DownloadTask(long p_i232230_1_, int p_i232230_3_, String p_i232230_4_, k_2603_m p_i232230_5_) {
        this.R_4764_Y = p_i232230_1_;
        this.G_564_y = p_i232230_3_;
        this.P_1922_E = p_i232230_5_;
        this.u_1723_Y = p_i232230_4_;
    }

    @Override
    public void run() {
        this.J_1907_R(new F_2904_S("mco.download.preparing"));
        p_178_J realmsclient = p_178_J.n_1700_B();
        for (int i = 0; i < 25; ++i) {
            try {
                if (this.n_1700_B()) {
                    return;
                }
                F_1410_V worlddownload = realmsclient.J_1907_R(this.R_4764_Y, this.G_564_y);
                DownloadTask.n_1700_B(1);
                if (this.n_1700_B()) {
                    return;
                }
                DownloadTask.n_1700_B(new C_290_v(this.P_1922_E, worlddownload, this.u_1723_Y, p_238115_0_ -> {}));
                return;
            }
            catch (RetryCallException retrycallexception) {
                if (this.n_1700_B()) {
                    return;
                }
                DownloadTask.n_1700_B(retrycallexception.P_1922_E);
                continue;
            }
            catch (u_744_e realmsserviceexception) {
                if (this.n_1700_B()) {
                    return;
                }
                n_1700_B.error("Couldn't download world data");
                DownloadTask.n_1700_B(new w_728_N(realmsserviceexception, this.P_1922_E));
                return;
            }
            catch (Exception exception) {
                if (this.n_1700_B()) {
                    return;
                }
                n_1700_B.error("Couldn't download world data", (Throwable)exception);
                this.n_1700_B(exception.getLocalizedMessage());
                return;
            }
        }
    }
}


