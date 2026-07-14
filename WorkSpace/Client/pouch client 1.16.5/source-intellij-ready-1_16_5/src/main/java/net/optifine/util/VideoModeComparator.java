/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.util.Comparator;
import lightning.product.J_1565_t;

public class VideoModeComparator
implements Comparator<J_1565_t> {
    @Override
    public int compare(J_1565_t vm1, J_1565_t vm2) {
        int j;
        if (vm1.n_1700_B() != vm2.n_1700_B()) {
            return vm1.n_1700_B() - vm2.n_1700_B();
        }
        if (vm1.J_1907_R() != vm2.J_1907_R()) {
            return vm1.J_1907_R() - vm2.J_1907_R();
        }
        if (vm1.u_1723_Y() != vm2.u_1723_Y()) {
            return vm1.u_1723_Y() - vm2.u_1723_Y();
        }
        int i = vm1.R_4764_Y() + vm1.G_564_y() + vm1.P_1922_E();
        return i != (j = vm2.R_4764_Y() + vm2.G_564_y() + vm2.P_1922_E()) ? i - j : 0;
    }
}

