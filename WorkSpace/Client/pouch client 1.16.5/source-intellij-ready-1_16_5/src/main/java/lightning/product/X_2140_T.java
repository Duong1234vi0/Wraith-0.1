/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.collect.Lists
 */
package lightning.product;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import lightning.product.C_2701_A;
import lightning.product.F_2904_S;
import lightning.product.SpectatorMenuItem;
import lightning.product.U_2871_b;
import lightning.product.SpectatorGui;
import lightning.product.RootSpectatorMenuCategory;
import lightning.product.SpectatorPage;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.g_372_g;
import lightning.product.s_448_U;
import lightning.product.x_282_a;

public class X_2140_T {
    private static final SpectatorMenuItem J_1907_R = new n_1700_B();
    private static final SpectatorMenuItem R_4764_Y = new J_1907_R(-1, true);
    private static final SpectatorMenuItem G_564_y = new J_1907_R(1, true);
    private static final SpectatorMenuItem P_1922_E = new J_1907_R(1, false);
    private static final x_282_a u_1723_Y = new F_2904_S("spectatorMenu.close");
    private static final x_282_a v_4262_N = new F_2904_S("spectatorMenu.previous_page");
    private static final x_282_a w_1484_f = new F_2904_S("spectatorMenu.next_page");
    public static final SpectatorMenuItem n_1700_B = new SpectatorMenuItem(){

        @Override
        public void n_1700_B(X_2140_T menu) {
        }

        @Override
        public x_282_a R_4764_Y() {
            return U_2871_b.R_4764_Y;
        }

        @Override
        public void n_1700_B(g_221_o p_230485_1_, float p_230485_2_, int p_230485_3_) {
        }

        @Override
        public boolean G_564_y() {
            return false;
        }
    };
    private final g_372_g t_148_a;
    private s_448_U s_956_w = new RootSpectatorMenuCategory();
    private int u_2550_I = -1;
    private int M_588_G;

    public X_2140_T(g_372_g menu) {
        this.t_148_a = menu;
    }

    public SpectatorMenuItem n_1700_B(int index) {
        int i = index + this.M_588_G * 6;
        if (this.M_588_G > 0 && index == 0) {
            return R_4764_Y;
        }
        if (index == 7) {
            return i < this.s_956_w.n_1700_B().size() ? G_564_y : P_1922_E;
        }
        if (index == 8) {
            return J_1907_R;
        }
        return i >= 0 && i < this.s_956_w.n_1700_B().size() ? (SpectatorMenuItem)MoreObjects.firstNonNull((Object)this.s_956_w.n_1700_B().get(i), (Object)n_1700_B) : n_1700_B;
    }

    public List<SpectatorMenuItem> n_1700_B() {
        ArrayList list = Lists.newArrayList();
        for (int i = 0; i <= 8; ++i) {
            list.add(this.n_1700_B(i));
        }
        return list;
    }

    public SpectatorMenuItem J_1907_R() {
        return this.n_1700_B(this.u_2550_I);
    }

    public s_448_U R_4764_Y() {
        return this.s_956_w;
    }

    public void J_1907_R(int slotIn) {
        SpectatorMenuItem ispectatormenuobject = this.n_1700_B(slotIn);
        if (ispectatormenuobject != n_1700_B) {
            if (this.u_2550_I == slotIn && ispectatormenuobject.G_564_y()) {
                ispectatormenuobject.n_1700_B(this);
            } else {
                this.u_2550_I = slotIn;
            }
        }
    }

    public void G_564_y() {
        this.t_148_a.n_1700_B(this);
    }

    public int P_1922_E() {
        return this.u_2550_I;
    }

    public void n_1700_B(s_448_U menuView) {
        this.s_956_w = menuView;
        this.u_2550_I = -1;
        this.M_588_G = 0;
    }

    public SpectatorPage u_1723_Y() {
        return new SpectatorPage(this.s_956_w, this.n_1700_B(), this.u_2550_I);
    }

    static class n_1700_B
    implements SpectatorMenuItem {
        private n_1700_B() {
        }

        @Override
        public void n_1700_B(X_2140_T menu) {
            menu.G_564_y();
        }

        @Override
        public x_282_a R_4764_Y() {
            return u_1723_Y;
        }

        @Override
        public void n_1700_B(g_221_o p_230485_1_, float p_230485_2_, int p_230485_3_) {
            MinecraftClient.A_4115_X().G_624_v().n_1700_B(SpectatorGui.n_1700_B);
            C_2701_A.blit(p_230485_1_, 0, 0, 128.0f, 0.0f, 16, 16, 256, 256);
        }

        @Override
        public boolean G_564_y() {
            return true;
        }
    }

    static class J_1907_R
    implements SpectatorMenuItem {
        private final int n_1700_B;
        private final boolean J_1907_R;

        public J_1907_R(int p_i45495_1_, boolean p_i45495_2_) {
            this.n_1700_B = p_i45495_1_;
            this.J_1907_R = p_i45495_2_;
        }

        @Override
        public void n_1700_B(X_2140_T menu) {
            menu.M_588_G += this.n_1700_B;
        }

        @Override
        public x_282_a R_4764_Y() {
            return this.n_1700_B < 0 ? v_4262_N : w_1484_f;
        }

        @Override
        public void n_1700_B(g_221_o p_230485_1_, float p_230485_2_, int p_230485_3_) {
            MinecraftClient.A_4115_X().G_624_v().n_1700_B(SpectatorGui.n_1700_B);
            if (this.n_1700_B < 0) {
                C_2701_A.blit(p_230485_1_, 0, 0, 144.0f, 0.0f, 16, 16, 256, 256);
            } else {
                C_2701_A.blit(p_230485_1_, 0, 0, 160.0f, 0.0f, 16, 16, 256, 256);
            }
        }

        @Override
        public boolean G_564_y() {
            return this.J_1907_R;
        }
    }
}



