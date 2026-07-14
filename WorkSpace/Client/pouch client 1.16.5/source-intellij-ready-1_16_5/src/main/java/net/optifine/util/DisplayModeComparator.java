/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import java.util.Comparator;
import lightning.product.J_1565_t;

public class DisplayModeComparator
implements Comparator {
    public int compare(Object o1, Object o2) {
        int j;
        J_1565_t videomode = (J_1565_t)o1;
        J_1565_t videomode1 = (J_1565_t)o2;
        if (videomode.n_1700_B() != videomode1.n_1700_B()) {
            return videomode.n_1700_B() - videomode1.n_1700_B();
        }
        if (videomode.J_1907_R() != videomode1.J_1907_R()) {
            return videomode.J_1907_R() - videomode1.J_1907_R();
        }
        int i = videomode.R_4764_Y() + videomode.G_564_y() + videomode.P_1922_E();
        if (i != (j = videomode1.R_4764_Y() + videomode1.G_564_y() + videomode1.P_1922_E())) {
            return i - j;
        }
        return videomode.u_1723_Y() != videomode1.u_1723_Y() ? videomode.u_1723_Y() - videomode1.u_1723_Y() : 0;
    }
}

