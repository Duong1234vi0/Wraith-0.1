/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import lightning.product.N_4263_v;
import lightning.product.Z_530_i;

public class Sensing {
    private final Z_530_i n_1700_B;
    private final List<N_4263_v> J_1907_R = Lists.newArrayList();
    private final List<N_4263_v> R_4764_Y = Lists.newArrayList();

    public Sensing(Z_530_i entityIn) {
        this.n_1700_B = entityIn;
    }

    public void n_1700_B() {
        this.J_1907_R.clear();
        this.R_4764_Y.clear();
    }

    public boolean n_1700_B(N_4263_v entityIn) {
        if (this.J_1907_R.contains(entityIn)) {
            return true;
        }
        if (this.R_4764_Y.contains(entityIn)) {
            return false;
        }
        this.n_1700_B.O_508_d.D_4792_h().n_1700_B("canSee");
        boolean flag = this.n_1700_B.c_3005_b(entityIn);
        this.n_1700_B.O_508_d.D_4792_h().R_4764_Y();
        if (flag) {
            this.J_1907_R.add(entityIn);
        } else {
            this.R_4764_Y.add(entityIn);
        }
        return flag;
    }
}


