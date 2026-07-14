/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import lightning.product.A_2226_Q;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.SpectatorMenuItem;
import lightning.product.SpectatorGui;
import lightning.product.X_2140_T;
import lightning.product.X_4340_E;
import lightning.product.MinecraftClient;
import lightning.product.PlayerTeam;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.TeleportToPlayerMenuCategory;
import lightning.product.s_2614_w;
import lightning.product.s_448_U;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class TeleportToTeamMenuCategory
implements SpectatorMenuItem,
s_448_U {
    private static final x_282_a n_1700_B = new F_2904_S("spectatorMenu.team_teleport");
    private static final x_282_a J_1907_R = new F_2904_S("spectatorMenu.team_teleport.prompt");
    private final List<SpectatorMenuItem> R_4764_Y = Lists.newArrayList();

    public TeleportToTeamMenuCategory() {
        MinecraftClient minecraft = MinecraftClient.A_4115_X();
        for (PlayerTeam scoreplayerteam : minecraft.Y_601_j.Q_4569_t().P_1922_E()) {
            this.R_4764_Y.add(new n_1700_B(this, scoreplayerteam));
        }
    }

    @Override
    public List<SpectatorMenuItem> n_1700_B() {
        return this.R_4764_Y;
    }

    @Override
    public x_282_a J_1907_R() {
        return J_1907_R;
    }

    @Override
    public void n_1700_B(X_2140_T menu) {
        menu.n_1700_B(this);
    }

    @Override
    public x_282_a R_4764_Y() {
        return n_1700_B;
    }

    @Override
    public void n_1700_B(g_221_o p_230485_1_, float p_230485_2_, int p_230485_3_) {
        MinecraftClient.A_4115_X().G_624_v().n_1700_B(SpectatorGui.n_1700_B);
        C_2701_A.blit(p_230485_1_, 0, 0, 16.0f, 0.0f, 16, 16, 256, 256);
    }

    @Override
    public boolean G_564_y() {
        for (SpectatorMenuItem ispectatormenuobject : this.R_4764_Y) {
            if (!ispectatormenuobject.G_564_y()) continue;
            return true;
        }
        return false;
    }

    class n_1700_B
    implements SpectatorMenuItem {
        private final PlayerTeam n_1700_B;
        private final g_2336_b J_1907_R;
        private final List<A_2226_Q> R_4764_Y;

        public n_1700_B(TeleportToTeamMenuCategory this$0, PlayerTeam teamIn) {
            this.n_1700_B = teamIn;
            this.R_4764_Y = Lists.newArrayList();
            for (String s : teamIn.u_1723_Y()) {
                A_2226_Q networkplayerinfo = MinecraftClient.A_4115_X().k_2293_S().n_1700_B(s);
                if (networkplayerinfo == null) continue;
                this.R_4764_Y.add(networkplayerinfo);
            }
            if (this.R_4764_Y.isEmpty()) {
                this.J_1907_R = s_2614_w.n_1700_B();
            } else {
                String s1 = this.R_4764_Y.get(new Random().nextInt(this.R_4764_Y.size())).n_1700_B().getName();
                this.J_1907_R = X_4340_E.R_4764_Y(s1);
                X_4340_E.n_1700_B(this.J_1907_R, s1);
            }
        }

        @Override
        public void n_1700_B(X_2140_T menu) {
            menu.n_1700_B(new TeleportToPlayerMenuCategory(this.R_4764_Y));
        }

        @Override
        public x_282_a R_4764_Y() {
            return this.n_1700_B.J_1907_R();
        }

        @Override
        public void n_1700_B(g_221_o p_230485_1_, float p_230485_2_, int p_230485_3_) {
            Integer integer = this.n_1700_B.P_4830_p().G_564_y();
            if (integer != null) {
                float f = (float)(integer >> 16 & 0xFF) / 255.0f;
                float f1 = (float)(integer >> 8 & 0xFF) / 255.0f;
                float f2 = (float)(integer & 0xFF) / 255.0f;
                C_2701_A.fill(p_230485_1_, 1, 1, 15, 15, u_530_F.P_1922_E(f * p_230485_2_, f1 * p_230485_2_, f2 * p_230485_2_) | p_230485_3_ << 24);
            }
            MinecraftClient.A_4115_X().G_624_v().n_1700_B(this.J_1907_R);
            c_4037_x.G_564_y(p_230485_2_, p_230485_2_, p_230485_2_, (float)p_230485_3_ / 255.0f);
            C_2701_A.blit(p_230485_1_, 2, 2, 12, 12, 8.0f, 8.0f, 8, 8, 64, 64);
            C_2701_A.blit(p_230485_1_, 2, 2, 12, 12, 40.0f, 8.0f, 8, 8, 64, 64);
        }

        @Override
        public boolean G_564_y() {
            return !this.R_4764_Y.isEmpty();
        }
    }
}



