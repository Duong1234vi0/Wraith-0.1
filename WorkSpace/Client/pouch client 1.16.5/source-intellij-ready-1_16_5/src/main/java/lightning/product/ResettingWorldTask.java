/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.LongRunningTask;
import lightning.product.S_4022_R;
import lightning.product.RetryCallException;
import lightning.product.p_178_J;
import lightning.product.x_282_a;

public class ResettingWorldTask
extends LongRunningTask {
    private final String R_4764_Y;
    private final S_4022_R G_564_y;
    private final int P_1922_E;
    private final boolean u_1723_Y;
    private final long v_4262_N;
    private x_282_a w_1484_f = new F_2904_S("mco.reset.world.resetting.screen.title");
    private final Runnable t_148_a;

    public ResettingWorldTask(@Nullable String p_i242048_1_, @Nullable S_4022_R p_i242048_2_, int p_i242048_3_, boolean p_i242048_4_, long p_i242048_5_, @Nullable x_282_a p_i242048_7_, Runnable p_i242048_8_) {
        this.R_4764_Y = p_i242048_1_;
        this.G_564_y = p_i242048_2_;
        this.P_1922_E = p_i242048_3_;
        this.u_1723_Y = p_i242048_4_;
        this.v_4262_N = p_i242048_5_;
        if (p_i242048_7_ != null) {
            this.w_1484_f = p_i242048_7_;
        }
        this.t_148_a = p_i242048_8_;
    }

    @Override
    public void run() {
        p_178_J realmsclient = p_178_J.n_1700_B();
        this.J_1907_R(this.w_1484_f);
        for (int i = 0; i < 25; ++i) {
            try {
                if (this.n_1700_B()) {
                    return;
                }
                if (this.G_564_y != null) {
                    realmsclient.v_4262_N(this.v_4262_N, this.G_564_y.n_1700_B);
                } else {
                    realmsclient.n_1700_B(this.v_4262_N, this.R_4764_Y, this.P_1922_E, this.u_1723_Y);
                }
                if (this.n_1700_B()) {
                    return;
                }
                this.t_148_a.run();
                return;
            }
            catch (RetryCallException retrycallexception) {
                if (this.n_1700_B()) {
                    return;
                }
                ResettingWorldTask.n_1700_B(retrycallexception.P_1922_E);
                continue;
            }
            catch (Exception exception) {
                if (this.n_1700_B()) {
                    return;
                }
                n_1700_B.error("Couldn't reset world");
                this.n_1700_B(exception.toString());
                return;
            }
        }
    }
}


