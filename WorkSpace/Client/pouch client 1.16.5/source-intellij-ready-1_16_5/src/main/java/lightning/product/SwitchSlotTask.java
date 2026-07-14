/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.LongRunningTask;
import lightning.product.RetryCallException;
import lightning.product.p_178_J;

public class SwitchSlotTask
extends LongRunningTask {
    private final long R_4764_Y;
    private final int G_564_y;
    private final Runnable P_1922_E;

    public SwitchSlotTask(long p_i232236_1_, int p_i232236_3_, Runnable p_i232236_4_) {
        this.R_4764_Y = p_i232236_1_;
        this.G_564_y = p_i232236_3_;
        this.P_1922_E = p_i232236_4_;
    }

    @Override
    public void run() {
        p_178_J realmsclient = p_178_J.n_1700_B();
        this.J_1907_R(new F_2904_S("mco.minigame.world.slot.screen.title"));
        for (int i = 0; i < 25; ++i) {
            try {
                if (this.n_1700_B()) {
                    return;
                }
                if (!realmsclient.n_1700_B(this.R_4764_Y, this.G_564_y)) continue;
                this.P_1922_E.run();
                break;
            }
            catch (RetryCallException retrycallexception) {
                if (this.n_1700_B()) {
                    return;
                }
                SwitchSlotTask.n_1700_B(retrycallexception.P_1922_E);
                continue;
            }
            catch (Exception exception) {
                if (this.n_1700_B()) {
                    return;
                }
                n_1700_B.error("Couldn't switch world!");
                this.n_1700_B(exception.toString());
            }
        }
    }
}


