/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.C_2701_A;
import lightning.product.Z_1993_T;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_4231_L;
import lombok.Generated;

public class q_4124_m
implements ServerHandshakePacketListener {
    @Override
    public void n_1700_B(b_3528_u event) {
        boolean hasArmor = false;
        for (Z_1993_T itemStack : q_4124_m.c_3005_b.Y_259_p.u_55_V()) {
            if (itemStack.n_1700_B()) continue;
            hasArmor = true;
            break;
        }
        if (!hasArmor) {
            return;
        }
        q_4124_m.c_3005_b.s_956_w.R_4764_Y();
        c_3005_b.G_624_v().n_1700_B(new g_2336_b("textures/gui/widgets.png"));
        g_221_o matrix = event.J_1907_R();
        int screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        int screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        int centerX = screenWidth / 2;
        boolean isRightHand = q_4124_m.c_3005_b.Y_259_p.d_2169_p().n_1700_B() == k_4231_L.J_1907_R && !q_4124_m.c_3005_b.Y_259_p.S_4035_N().n_1700_B();
        int handOffset = isRightHand ? 29 : 0;
        c_4037_x.G_624_v();
        c_4037_x.Y_601_j();
        c_4037_x.q_2307_F();
        c_4037_x.u_2550_I();
        c_4037_x.t_1786_h();
        c_4037_x.w_1484_f(7425);
        c_4037_x.l_1233_K();
        c_4037_x.s_2632_s();
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        int xPos1 = centerX + 91 + 10 + handOffset;
        int xPos2 = centerX + 141 + 1 + handOffset;
        int yPos = screenHeight - 22;
        C_2701_A.blit(matrix, xPos1, yPos, 0, 0, 0, 41, 22);
        C_2701_A.blit(matrix, xPos2, yPos, 0, 141, 0, 41, 22);
        int xOffset = 0;
        for (Z_1993_T itemStack : q_4124_m.c_3005_b.Y_259_p.u_55_V()) {
            q_4124_m.c_3005_b.M_588_G.n_1700_B(xPos1 + 3 + xOffset, yPos + 3, event.R_4764_Y(), q_4124_m.c_3005_b.Y_259_p, itemStack);
            xOffset += 20;
        }
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.M_588_G();
        c_4037_x.k_2293_S();
        c_4037_x.Y_259_p();
        c_4037_x.G_624_v();
        c_4037_x.w_1484_f(7424);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
    }

    @Generated
    public q_4124_m() {
    }
}


