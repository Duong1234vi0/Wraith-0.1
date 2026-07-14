/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.LongRunningTask;
import lightning.product.k_2603_m;
import lightning.product.p_178_J;
import lightning.product.u_744_e;

public class WorldCreationTask
extends LongRunningTask {
    private final String R_4764_Y;
    private final String G_564_y;
    private final long P_1922_E;
    private final k_2603_m u_1723_Y;

    public WorldCreationTask(long p_i232237_1_, String p_i232237_3_, String p_i232237_4_, k_2603_m p_i232237_5_) {
        this.P_1922_E = p_i232237_1_;
        this.R_4764_Y = p_i232237_3_;
        this.G_564_y = p_i232237_4_;
        this.u_1723_Y = p_i232237_5_;
    }

    @Override
    public void run() {
        this.J_1907_R(new F_2904_S("mco.create.world.wait"));
        p_178_J realmsclient = p_178_J.n_1700_B();
        try {
            realmsclient.n_1700_B(this.P_1922_E, this.R_4764_Y, this.G_564_y);
            WorldCreationTask.n_1700_B(this.u_1723_Y);
        }
        catch (u_744_e realmsserviceexception) {
            n_1700_B.error("Couldn't create world");
            this.n_1700_B(realmsserviceexception.toString());
        }
        catch (Exception exception) {
            n_1700_B.error("Could not create world");
            this.n_1700_B(exception.getLocalizedMessage());
        }
    }
}


