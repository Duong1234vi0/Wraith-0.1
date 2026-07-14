/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  lombok.Generated
 */
package lightning.product;

import lightning.product.C_2701_A;
import lightning.product.H_3330_w;
import lightning.product.Interface;
import lightning.product.Z_1993_T;
import lightning.product.ServerHandshakePacketListener;
import lightning.product.b_3528_u;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_4231_L;
import lightning.product.Items;
import lightning.product.u_1934_K;
import lombok.Generated;

public class a_1887_j
implements ServerHandshakePacketListener {
    @Override
    public void n_1700_B(b_3528_u event) {
        int totemCount;
        boolean hasArmor = false;
        for (Z_1993_T itemStack : a_1887_j.c_3005_b.Y_259_p.u_55_V()) {
            if (itemStack.n_1700_B()) continue;
            hasArmor = true;
            break;
        }
        if ((totemCount = u_1934_K.v_4262_N(Items.N_81_X)) <= 0) {
            return;
        }
        a_1887_j.c_3005_b.s_956_w.R_4764_Y();
        c_3005_b.G_624_v().n_1700_B(new g_2336_b("textures/gui/widgets.png"));
        g_221_o matrix = event.J_1907_R();
        int screenWidth = c_3005_b.RealmsServerPing().Q_4569_t();
        int screenHeight = c_3005_b.RealmsServerPing().M_182_A();
        int centerX = screenWidth / 2;
        boolean isRightHand = a_1887_j.c_3005_b.Y_259_p.d_2169_p().n_1700_B() == k_4231_L.J_1907_R && !a_1887_j.c_3005_b.Y_259_p.S_4035_N().n_1700_B();
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
        int yPos = screenHeight - 23;
        int xPos2 = centerX + 189 + 1 + handOffset;
        int xPos3 = centerX + 97 + 1 + handOffset;
        boolean isArmorElementEnabled = Interface.t_148_a.J_1907_R("\u0411\u0440\u043e\u043d\u044f");
        int drawX = isArmorElementEnabled && hasArmor ? xPos2 : xPos3;
        C_2701_A.blit(matrix, drawX, yPos, 0, 24.0f, 22.0f, 29, 24, 256, 256);
        Z_1993_T totemStack = new Z_1993_T(Items.N_81_X, totemCount);
        H_3330_w itemRenderer = c_3005_b.r_715_M();
        itemRenderer.n_1700_B(totemStack, drawX + 3, yPos + 4);
        itemRenderer.n_1700_B(a_1887_j.c_3005_b.t_148_a, totemStack, drawX + 3, yPos + 4);
        c_4037_x.multiplayerClientSuggestionProvider();
        c_4037_x.M_588_G();
        c_4037_x.k_2293_S();
        c_4037_x.Y_259_p();
        c_4037_x.w_1484_f(7424);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        a_1887_j.c_3005_b.s_956_w.R_4764_Y();
    }

    @Generated
    public a_1887_j() {
    }
}



