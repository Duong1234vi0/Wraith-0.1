/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lightning.product.MultiBooleanSetting;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.BooleanSetting;
import lightning.product.Items;
import lightning.product.ModuleCategory;
import lightning.product.z_3427_G;

public class AutoDupe
extends Module {
    private static final List<n_1700_B> v_4262_N = List.of(new n_1700_B("free", 61000L), new n_1700_B("hero", 91000L), new n_1700_B("prince", 91000L), new n_1700_B("killer", 91000L), new n_1700_B("krushitel", 121000L), new n_1700_B("kraken", 121000L), new n_1700_B("alpha", 121000L), new n_1700_B("rabbit", 121000L), new n_1700_B("sponsor", 601000L));
    private final MultiBooleanSetting kityOptions = new MultiBooleanSetting("\u041a\u0438\u0442\u044b", new BooleanSetting("\u041a\u0438\u0442 free", false), new BooleanSetting("\u041a\u0438\u0442 hero", false), new BooleanSetting("\u041a\u0438\u0442 prince", false), new BooleanSetting("\u041a\u0438\u0442 killer", false), new BooleanSetting("\u041a\u0438\u0442 krushitel", false), new BooleanSetting("\u041a\u0438\u0442 kraken", false), new BooleanSetting("\u041a\u0438\u0442 alpha", false), new BooleanSetting("\u041a\u0438\u0442 rabbit", false), new BooleanSetting("\u041a\u0438\u0442 sponsor", false));
    private final MultiBooleanSetting predmetyOptions = new MultiBooleanSetting("\u041f\u0440\u0435\u0434\u043c\u0435\u0442\u044b", new BooleanSetting("\u0427\u0430\u0440\u043a\u0438", false), new BooleanSetting("\u0417\u0435\u043b\u044c\u0435 \u0412\u0438\u043a\u0438\u043d\u0433\u0430", false), new BooleanSetting("\u0412\u043e\u0441\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u044d\u0444\u0444\u0435\u043a\u0442\u044b", false), new BooleanSetting("\u042d\u043d\u0434\u0435\u0440 \u0436\u0435\u043c\u0447\u0443\u0433", false));
    private final V_4557_X s_956_w = new V_4557_X();
    private final V_4557_X u_2550_I = new V_4557_X();
    private final V_4557_X M_588_G = new V_4557_X();
    private boolean P_4830_p = false;
    private String h_1847_R = null;
    private final Map<String, Long> Q_4569_t = new HashMap<String, Long>();

    public AutoDupe() {
        super("AutoDupe", ModuleCategory.G_564_y);
        this.addSettings(this.kityOptions, this.predmetyOptions);
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G event) {
        if (AutoDupe.c_3005_b.Y_259_p == null || AutoDupe.c_3005_b.Y_601_j == null) {
            return;
        }
        try {
            if (!this.M_182_A()) {
                if (this.Q_4569_t() && this.P_4830_p && this.u_2550_I.J_1907_R(1000L)) {
                    if (!(AutoDupe.c_3005_b.Y_1740_V instanceof z_3427_G)) {
                        AutoDupe.c_3005_b.Y_259_p.n_1700_B("/ec");
                        this.u_2550_I.n_1700_B();
                    } else {
                        int startIndex = AutoDupe.c_3005_b.Y_259_p.H_1873_g.u_2550_I().size() - 36;
                        int endIndex = AutoDupe.c_3005_b.Y_259_p.H_1873_g.u_2550_I().size() - 1;
                        boolean movedItem = false;
                        for (int i = startIndex; i <= endIndex; ++i) {
                            Z_1993_T itemStack = AutoDupe.c_3005_b.Y_259_p.H_1873_g.n_1700_B(i).n_1700_B();
                            if (!this.n_1700_B(itemStack)) continue;
                            AutoDupe.c_3005_b.w_1457_N.windowClick(AutoDupe.c_3005_b.Y_259_p.H_1873_g.u_1723_Y, i, 0, a_408_T.J_1907_R, AutoDupe.c_3005_b.Y_259_p);
                            movedItem = true;
                        }
                        if (movedItem) {
                            this.P_4830_p = false;
                            AutoDupe.c_3005_b.Y_259_p.P_1922_E();
                        }
                        this.u_2550_I.n_1700_B();
                    }
                } else if (!this.Q_4569_t() && this.u_2550_I.J_1907_R(1000L)) {
                    AutoDupe.c_3005_b.Y_259_p.n_1700_B("/clear -confirmed");
                    this.u_2550_I.n_1700_B();
                }
                return;
            }
            if (!this.M_588_G.J_1907_R(5500L) && this.h_1847_R != null) {
                return;
            }
            String kitToRequest = this.h_1847_R();
            if (kitToRequest == null) {
                return;
            }
            AutoDupe.c_3005_b.Y_259_p.n_1700_B("/kit " + kitToRequest);
            this.Q_4569_t.put(kitToRequest, System.currentTimeMillis());
            this.h_1847_R = kitToRequest;
            this.P_4830_p = true;
            this.M_588_G.n_1700_B();
            this.s_956_w.n_1700_B();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String h_1847_R() {
        long currentTime = System.currentTimeMillis();
        ArrayList<String> enabledKits = new ArrayList<String>();
        for (n_1700_B kit : v_4262_N) {
            if (!Boolean.TRUE.equals(this.kityOptions.isOptionEnabled("\u041a\u0438\u0442 " + kit.n_1700_B))) continue;
            enabledKits.add(kit.n_1700_B);
        }
        if (enabledKits.isEmpty()) {
            return null;
        }
        for (String kitName : enabledKits) {
            long cooldown;
            long lastRequestTime = this.Q_4569_t.getOrDefault(kitName, 0L);
            if (currentTime - lastRequestTime < (cooldown = v_4262_N.stream().filter(k -> k.n_1700_B.equals(kitName)).findFirst().map(k -> k.J_1907_R).orElse(61000L).longValue())) continue;
            return kitName;
        }
        return null;
    }

    private boolean Q_4569_t() {
        try {
            for (int i = 0; i < AutoDupe.c_3005_b.Y_259_p.o_1800_r.u_2550_I().size(); ++i) {
                Z_1993_T stack = AutoDupe.c_3005_b.Y_259_p.o_1800_r.u_2550_I().get(i);
                if (stack.n_1700_B() || !this.n_1700_B(stack)) continue;
                return true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean n_1700_B(Z_1993_T stack) {
        if (stack.n_1700_B()) {
            return false;
        }
        if (Boolean.TRUE.equals(this.predmetyOptions.isOptionEnabled("\u0427\u0430\u0440\u043a\u0438")) && stack.J_1907_R() == Items.E_4612_l) {
            return true;
        }
        if (Boolean.TRUE.equals(this.predmetyOptions.isOptionEnabled("\u042d\u043d\u0434\u0435\u0440 \u0436\u0435\u043c\u0447\u0443\u0433")) && stack.J_1907_R() == Items.v_2746_S) {
            return true;
        }
        if (stack.J_1907_R() == Items.j_2461_G) {
            String displayName = stack.multiplayerClientSuggestionProvider().getString();
            if (Boolean.TRUE.equals(this.predmetyOptions.isOptionEnabled("\u0417\u0435\u043b\u044c\u0435 \u0412\u0438\u043a\u0438\u043d\u0433\u0430")) && displayName.equals("\u0417\u0435\u043b\u044c\u0435 \u0412\u0438\u043a\u0438\u043d\u0433\u0430")) {
                return true;
            }
            if (Boolean.TRUE.equals(this.predmetyOptions.isOptionEnabled("\u0412\u043e\u0441\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u044d\u0444\u0444\u0435\u043a\u0442\u044b")) && displayName.equals("\u0412\u043e\u0441\u0441\u0442\u0430\u043d\u043e\u0432\u0438\u0442\u044c \u044d\u0444\u0444\u0435\u043a\u0442\u044b")) {
                return true;
            }
        }
        return false;
    }

    private boolean M_182_A() {
        try {
            for (int i = 0; i < AutoDupe.c_3005_b.Y_259_p.o_1800_r.u_2550_I().size(); ++i) {
                Z_1993_T stack = AutoDupe.c_3005_b.Y_259_p.o_1800_r.u_2550_I().get(i);
                if (stack.n_1700_B()) continue;
                return false;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.s_956_w.n_1700_B();
        this.u_2550_I.n_1700_B();
        this.M_588_G.n_1700_B();
        this.P_4830_p = false;
        this.h_1847_R = null;
        this.Q_4569_t.clear();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.P_4830_p = false;
        this.h_1847_R = null;
    }

    private static final class n_1700_B {
        final String n_1700_B;
        final long J_1907_R;

        n_1700_B(String name, long cooldown) {
            this.n_1700_B = name;
            this.J_1907_R = cooldown;
        }
    }
}



