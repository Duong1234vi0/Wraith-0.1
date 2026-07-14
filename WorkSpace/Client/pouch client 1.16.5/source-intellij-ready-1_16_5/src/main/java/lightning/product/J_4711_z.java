/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import lightning.product.a_2436_g;
import lightning.product.j_3341_s;
import lightning.product.s_4514_h;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class J_4711_z
implements a_2436_g {
    private static final Logger n_1700_B = LogManager.getLogger();

    @Override
    public void n_1700_B(s_4514_h p_225646_1_) {
        if (p_225646_1_.u_2550_I()) {
            n_1700_B.error(p_225646_1_.R_4764_Y() + " failed! " + j_3341_s.G_564_y(p_225646_1_.s_956_w()));
        } else {
            n_1700_B.warn("(optional) " + p_225646_1_.R_4764_Y() + " failed. " + j_3341_s.G_564_y(p_225646_1_.s_956_w()));
        }
    }
}

