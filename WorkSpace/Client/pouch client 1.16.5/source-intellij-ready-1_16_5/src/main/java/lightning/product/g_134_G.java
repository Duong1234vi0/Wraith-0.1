/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Party;
import lightning.product.G_1539_D;
import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.b_3528_u;
import lightning.product.y_4642_Y;

public class g_134_G
implements MinecraftAccess {
    @Y_1740_V
    public void n_1700_B(b_3528_u event) {
        if (y_4642_Y.R_4764_Y() || g_134_G.c_3005_b.Y_259_p == null || g_134_G.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!G_1539_D.n_1700_B.P_1922_E() || !G_1539_D.v_4262_N()) {
            return;
        }
        Party.n_1700_B(event);
    }
}


