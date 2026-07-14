/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Iterator;
import java.util.List;
import lightning.product.C_3901_K;
import lightning.product.j_2368_m;
import lightning.product.s_4514_h;

public class H_4938_m {
    private final s_4514_h n_1700_B = null;
    private final List<C_3901_K> J_1907_R = null;
    private long R_4764_Y;

    public void n_1700_B(long p_229567_1_) {
        try {
            this.R_4764_Y(p_229567_1_);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void J_1907_R(long p_229568_1_) {
        try {
            this.R_4764_Y(p_229568_1_);
        }
        catch (Exception exception) {
            this.n_1700_B.n_1700_B(exception);
        }
    }

    private void R_4764_Y(long p_229569_1_) {
        Iterator<C_3901_K> iterator = this.J_1907_R.iterator();
        while (iterator.hasNext()) {
            C_3901_K testtickresult = iterator.next();
            testtickresult.J_1907_R.run();
            iterator.remove();
            long i = p_229569_1_ - this.R_4764_Y;
            long j = this.R_4764_Y;
            this.R_4764_Y = p_229569_1_;
            if (testtickresult.n_1700_B == null || testtickresult.n_1700_B == i) continue;
            this.n_1700_B.n_1700_B(new j_2368_m("Succeeded in invalid tick: expected " + (j + testtickresult.n_1700_B) + ", but current tick is " + p_229569_1_));
            break;
        }
    }

    private H_4938_m() {
        throw new RuntimeException("Synthetic constructor added by MCP, do not call");
    }
}

