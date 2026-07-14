/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.Q_2753_H;
import lightning.product.Y_1740_V;
import lightning.product.Z_390_O;
import lightning.product.MinecraftAccess;
import lightning.product.h_1015_G;
import lightning.product.p_1183_T;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lombok.Generated;

public class N_260_m {
    public static boolean n_1700_B = false;
    public static int J_1907_R = 0;

    public static void n_1700_B() {
        n_1700_B = true;
        J_1907_R = 0;
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (n_1700_B && ++J_1907_R >= 6) {
            n_1700_B = false;
            J_1907_R = 0;
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        Packet<?> t_3138_Z2;
        if (n_1700_B && MinecraftAccess.c_3005_b.Y_259_p != null && MinecraftAccess.c_3005_b.Y_601_j != null && (t_3138_Z2 = e.G_564_y()) instanceof Z_390_O) {
            Z_390_O fix = (Z_390_O)t_3138_Z2;
            if (e.J_1907_R() && fix.J_1907_R() != MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y) {
                int newSlot = u_530_F.n_1700_B(MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y >= 8 ? MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y - 1 : MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y + 1, 0, 8);
                MinecraftAccess.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(newSlot));
                MinecraftAccess.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(MinecraftAccess.c_3005_b.Y_259_p.l_1268_F.G_564_y));
                e.n_1700_B(true);
            }
        }
    }

    @Generated
    public static boolean J_1907_R() {
        return n_1700_B;
    }
}



