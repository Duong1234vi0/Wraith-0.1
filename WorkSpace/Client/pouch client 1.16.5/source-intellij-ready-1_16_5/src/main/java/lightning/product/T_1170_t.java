/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.P_3504_Q;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class T_1170_t
implements MinecraftAccess {
    public static float n_1700_B(double value) {
        return (float)value;
    }

    public static P_3504_Q n_1700_B(r_4811_B entity) {
        e_2866_D vec = entity.s_4990_V().J_1907_R(0.0, (double)entity.v_165_F() / 2.0, 0.0).G_564_y(T_1170_t.c_3005_b.Y_259_p.u_2550_I(1.0f));
        double dist = Math.hypot(vec.J_1907_R, vec.G_564_y);
        return new P_3504_Q(u_530_F.v_4262_N((float)Math.toDegrees(Math.atan2(vec.G_564_y, vec.J_1907_R)) - 90.0f), (float)(-Math.toDegrees(Math.atan2(vec.R_4764_Y, dist))));
    }
}


