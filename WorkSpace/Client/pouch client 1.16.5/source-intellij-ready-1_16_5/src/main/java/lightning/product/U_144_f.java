/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Party;
import lightning.product.Y_1740_V;
import lightning.product.i_4434_b;
import lightning.product.ClientBootstrap;
import lightning.product.y_4642_Y;

public class U_144_f {
    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (y_4642_Y.R_4764_Y()) {
            return;
        }
        Party party = (Party)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(Party.class);
        if (party == null) {
            return;
        }
        if (e.J_1907_R()) {
            return;
        }
        int bind = (Integer)party.v_4262_N.J_1907_R();
        if (bind == -1 || e.n_1700_B() != bind) {
            return;
        }
        Party.h_1847_R();
    }
}


