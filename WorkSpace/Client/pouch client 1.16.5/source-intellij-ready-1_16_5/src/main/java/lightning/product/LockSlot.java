/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_4024_W;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.h_2848_I;
import lightning.product.BooleanSetting;
import lightning.product.q_3115_L;
import lightning.product.Items;
import lightning.product.v_1900_v;
import lightning.product.ModuleCategory;

public class LockSlot
extends Module {
    private final BooleanSetting tolkoPriPvpEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043f\u0432\u043f", true);
    public final MultiBooleanSetting vyberiteSlotyOptions = new MultiBooleanSetting("\u0412\u044b\u0431\u0435\u0440\u0438\u0442\u0435 \u0441\u043b\u043e\u0442\u044b", new BooleanSetting("1", false), new BooleanSetting("2", false), new BooleanSetting("3", false), new BooleanSetting("4", false), new BooleanSetting("5", false), new BooleanSetting("6", false), new BooleanSetting("7", false), new BooleanSetting("8", false), new BooleanSetting("9", false));

    public LockSlot() {
        super("LockSlot", ModuleCategory.G_564_y);
        this.addSettings(this.tolkoPriPvpEnabled, this.vyberiteSlotyOptions);
    }

    @Y_1740_V
    public void n_1700_B(h_2848_I e) {
        if (LockSlot.c_3005_b.Y_259_p == null || LockSlot.c_3005_b.Y_259_p.l_1268_F == null || LockSlot.c_3005_b.Y_259_p.A_2714_y().J_1907_R() == Items.n_1700_B) {
            return;
        }
        if (this.tolkoPriPvpEnabled.isEnabled().booleanValue() && !q_3115_L.n_1700_B()) {
            return;
        }
        int currentSlot = e.J_1907_R();
        BooleanSetting setting = this.vyberiteSlotyOptions.getOption(currentSlot);
        if (setting.t_148_a().booleanValue()) {
            e.n_1700_B(true);
            v_1900_v.n_1700_B(String.valueOf((Object)D_4024_W.w_1484_f) + "\u0412\u044b\u0431\u0440\u043e\u0441 \u043f\u0440\u0435\u0434\u043c\u0435\u0442 \u0438\u0437 \u0441\u043b\u043e\u0442\u0430 " + (currentSlot + 1) + " \u0431\u044b\u043b \u0437\u0430\u0431\u043b\u043e\u043a\u0438\u0440\u043e\u0432\u0430\u043d", new Object[0]);
        }
    }
}



