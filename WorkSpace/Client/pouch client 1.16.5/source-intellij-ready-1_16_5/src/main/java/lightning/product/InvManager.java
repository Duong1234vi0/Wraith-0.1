/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import java.util.List;
import lightning.product.NumberSetting;
import lightning.product.MultiBooleanSetting;
import lightning.product.Q_1939_l;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.ModuleCategory;

public class InvManager
extends Module {
    private final NumberSetting zaderzhkaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 100.0f, 0.0f, 1000.0f, 10.0f);
    private final MultiBooleanSetting vybrasyvatMusorOptions = new MultiBooleanSetting("\u0412\u044b\u0431\u0440\u0430\u0441\u044b\u0432\u0430\u0442\u044c \u043c\u0443\u0441\u043e\u0440", new BooleanSetting("\u041f\u0430\u0443\u0442\u0438\u043d\u0430", false), new BooleanSetting("\u041d\u0438\u0442\u043a\u0438", false), new BooleanSetting("\u0421\u0442\u0440\u0435\u043b\u044b", false), new BooleanSetting("\u0413\u043d\u0438\u043b\u0430\u044f \u043f\u043b\u043e\u0442\u044c", true), new BooleanSetting("\u041a\u043e\u0441\u0442\u0438", true), new BooleanSetting("\u041f\u043e\u0440\u043e\u0445", false), new BooleanSetting("\u0423\u0433\u043e\u043b\u044c", false), new BooleanSetting("\u041a\u0440\u0435\u043c\u0435\u043d\u044c", false), new BooleanSetting("\u0413\u0440\u0430\u0432\u0438\u0439", false), new BooleanSetting("\u0420\u0443\u0434\u044b", false));
    private final BooleanSetting chistitTolkoHotbarEnabled = new BooleanSetting("\u0427\u0438\u0441\u0442\u0438\u0442\u044c \u0442\u043e\u043b\u044c\u043a\u043e \u0445\u043e\u0442\u0431\u0430\u0440", false);
    private long s_956_w = 0L;
    private static final List<q_1613_l> u_2550_I = Arrays.asList(Items.ValueObject);
    private static final List<q_1613_l> M_588_G = Arrays.asList(Items.Animation);
    private static final List<q_1613_l> P_4830_p = Arrays.asList(Items.g_24_p, Items.g_2783_J, Items.NetherWartBlock);
    private static final List<q_1613_l> h_1847_R = Arrays.asList(Items.m_1964_F);
    private static final List<q_1613_l> Q_4569_t = Arrays.asList(Items.DamageSourcePredicate);
    private static final List<q_1613_l> M_182_A = Arrays.asList(Items.Easing);
    private static final List<q_1613_l> t_1786_h = Arrays.asList(Items.T_797_O, Items.d_560_A);
    private static final List<q_1613_l> multiplayerClientSuggestionProvider = Arrays.asList(Items.W_1488_x);
    private static final List<q_1613_l> w_1457_N = Arrays.asList(Items.e_4240_b);
    private static final List<q_1613_l> Y_601_j = Arrays.asList(Items.z_1737_N, Items.d_2427_y, Items.n_3318_d, Items.H_1873_g, Items.g_134_G, Items.dtoRealmsServerAddress, Items.X_2960_b, Items.BooleanSetting, Items.v_4276_D, Items.TallFlowerBlock, Items.f_1186_l, Items.u_3578_p);

    public InvManager() {
        super("InvManager", ModuleCategory.G_564_y);
        this.addSettings(this.zaderzhkaSetting, this.vybrasyvatMusorOptions, this.chistitTolkoHotbarEnabled);
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G event) {
        if (InvManager.c_3005_b.Y_259_p == null) {
            return;
        }
        if ((InvManager.c_3005_b.Y_1740_V == null || InvManager.c_3005_b.Y_1740_V instanceof Q_1939_l) && System.currentTimeMillis() - this.s_956_w >= ((Float)this.zaderzhkaSetting.getValue()).longValue()) {
            int startSlot;
            for (int i = startSlot = this.chistitTolkoHotbarEnabled.isEnabled() != false ? 0 : 9; i < 36; ++i) {
                Z_1993_T stack = InvManager.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
                if (stack.n_1700_B() || !this.n_1700_B(stack)) continue;
                int containerSlot = i < 9 ? i + 36 : i;
                InvManager.c_3005_b.w_1457_N.windowClick(InvManager.c_3005_b.Y_259_p.o_1800_r.u_1723_Y, containerSlot, 1, a_408_T.P_1922_E, InvManager.c_3005_b.Y_259_p);
                this.s_956_w = System.currentTimeMillis();
                return;
            }
        }
    }

    private boolean n_1700_B(Z_1993_T stack) {
        q_1613_l item = stack.J_1907_R();
        if (this.vybrasyvatMusorOptions.isOptionEnabled("\u041f\u0430\u0443\u0442\u0438\u043d\u0430") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u041f\u0430\u0443\u0442\u0438\u043d\u0430").booleanValue() && u_2550_I.contains(item)) {
            return true;
        }
        if (this.vybrasyvatMusorOptions.isOptionEnabled("\u041d\u0438\u0442\u043a\u0438") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u041d\u0438\u0442\u043a\u0438").booleanValue() && M_588_G.contains(item)) {
            return true;
        }
        if (this.vybrasyvatMusorOptions.isOptionEnabled("\u0421\u0442\u0440\u0435\u043b\u044b") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u0421\u0442\u0440\u0435\u043b\u044b").booleanValue() && P_4830_p.contains(item)) {
            return true;
        }
        if (this.vybrasyvatMusorOptions.isOptionEnabled("\u0413\u043d\u0438\u043b\u0430\u044f \u043f\u043b\u043e\u0442\u044c") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u0413\u043d\u0438\u043b\u0430\u044f \u043f\u043b\u043e\u0442\u044c").booleanValue() && h_1847_R.contains(item)) {
            return true;
        }
        if (this.vybrasyvatMusorOptions.isOptionEnabled("\u041a\u043e\u0441\u0442\u0438") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u041a\u043e\u0441\u0442\u0438").booleanValue() && Q_4569_t.contains(item)) {
            return true;
        }
        if (this.vybrasyvatMusorOptions.isOptionEnabled("\u041f\u043e\u0440\u043e\u0445") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u041f\u043e\u0440\u043e\u0445").booleanValue() && M_182_A.contains(item)) {
            return true;
        }
        if (this.vybrasyvatMusorOptions.isOptionEnabled("\u0423\u0433\u043e\u043b\u044c") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u0423\u0433\u043e\u043b\u044c").booleanValue() && t_1786_h.contains(item)) {
            return true;
        }
        if (this.vybrasyvatMusorOptions.isOptionEnabled("\u041a\u0440\u0435\u043c\u0435\u043d\u044c") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u041a\u0440\u0435\u043c\u0435\u043d\u044c").booleanValue() && multiplayerClientSuggestionProvider.contains(item)) {
            return true;
        }
        if (this.vybrasyvatMusorOptions.isOptionEnabled("\u0413\u0440\u0430\u0432\u0438\u0439") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u0413\u0440\u0430\u0432\u0438\u0439").booleanValue() && w_1457_N.contains(item)) {
            return true;
        }
        return this.vybrasyvatMusorOptions.isOptionEnabled("\u0420\u0443\u0434\u044b") != null && this.vybrasyvatMusorOptions.isOptionEnabled("\u0420\u0443\u0434\u044b") != false && Y_601_j.contains(item);
    }
}



