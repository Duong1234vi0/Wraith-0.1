/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.LongRunningTask;
import lightning.product.S_4022_R;
import lightning.product.W_3464_O;
import lightning.product.RetryCallException;
import lightning.product.p_178_J;

public class SwitchMinigameTask
extends LongRunningTask {
    private final long R_4764_Y;
    private final S_4022_R G_564_y;
    private final W_3464_O P_1922_E;

    public SwitchMinigameTask(long p_i232235_1_, S_4022_R p_i232235_3_, W_3464_O p_i232235_4_) {
        this.R_4764_Y = p_i232235_1_;
        this.G_564_y = p_i232235_3_;
        this.P_1922_E = p_i232235_4_;
    }

    @Override
    public void run() {
        p_178_J realmsclient = p_178_J.n_1700_B();
        this.J_1907_R(new F_2904_S("mco.minigame.world.starting.screen.title"));
        for (int i = 0; i < 25; ++i) {
            try {
                if (this.n_1700_B()) {
                    return;
                }
                if (!realmsclient.G_564_y(this.R_4764_Y, this.G_564_y.n_1700_B).booleanValue()) continue;
                SwitchMinigameTask.n_1700_B(this.P_1922_E);
                break;
            }
            catch (RetryCallException retrycallexception) {
                if (this.n_1700_B()) {
                    return;
                }
                SwitchMinigameTask.n_1700_B(retrycallexception.P_1922_E);
                continue;
            }
            catch (Exception exception) {
                if (this.n_1700_B()) {
                    return;
                }
                n_1700_B.error("Couldn't start mini game!");
                this.n_1700_B(exception.toString());
            }
        }
    }
}


