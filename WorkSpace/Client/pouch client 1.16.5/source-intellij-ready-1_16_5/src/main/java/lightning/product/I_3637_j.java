/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Y_1740_V;
import lightning.product.MinecraftAccess;
import lightning.product.i_4434_b;
import lightning.product.ClientBootstrap;
import lightning.product.r_4414_L;

public class I_3637_j
implements MinecraftAccess {
    @Y_1740_V
    public void n_1700_B(i_4434_b e) {
        if (I_3637_j.c_3005_b.Y_259_p == null || e.J_1907_R() || I_3637_j.c_3005_b.Y_601_j == null) {
            return;
        }
        for (r_4414_L.n_1700_B m : ClientBootstrap.Y_601_j().t_148_a().P_4830_p()) {
            String cmd;
            if (m.J_1907_R() != e.n_1700_B() || (cmd = m.R_4764_Y()) == null || cmd.isEmpty()) continue;
            I_3637_j.c_3005_b.Y_259_p.n_1700_B(cmd);
        }
    }
}


