/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import lightning.product.NumberSetting;
import lightning.product.ChestMenu;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.a_2900_S;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.BooleanSetting;
import lightning.product.ModuleCategory;
import lightning.product.z_1477_l;

public class ChestStealer
extends Module {
    private final NumberSetting zaderzhkaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 100.0f, 0.0f, 1000.0f, 1.0f);
    private final BooleanSetting randomizaciyaEnabled = new BooleanSetting("\u0420\u0430\u043d\u0434\u043e\u043c\u0438\u0437\u0430\u0446\u0438\u044f", false);
    private final V_4557_X t_148_a = new V_4557_X();

    public ChestStealer() {
        super("ChestStealer", ModuleCategory.G_564_y);
        this.addSettings(this.zaderzhkaSetting, this.randomizaciyaEnabled);
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        a_2900_S openContainer = ChestStealer.c_3005_b.Y_259_p.H_1873_g;
        if (openContainer instanceof ChestMenu || openContainer instanceof z_1477_l) {
            List<Slot> slots = openContainer.P_1922_E;
            if (this.t_148_a.J_1907_R(((Float)this.zaderzhkaSetting.getValue()).longValue())) {
                this.J_1907_R(slots).ifPresent(slot -> {
                    if (ChestStealer.c_3005_b.Y_259_p.H_1873_g == openContainer) {
                        ChestStealer.c_3005_b.w_1457_N.windowClick(openContainer.u_1723_Y, slot.G_564_y, 0, a_408_T.J_1907_R, ChestStealer.c_3005_b.Y_259_p);
                        this.t_148_a.n_1700_B();
                    }
                });
            }
        }
    }

    private Optional<Slot> J_1907_R(List<Slot> slots) {
        int containerSlotCount = slots.size() - ChestStealer.c_3005_b.Y_259_p.l_1268_F.n_1700_B.size();
        List<Slot> containerSlots = slots.subList(0, containerSlotCount);
        List<Slot> validSlots = containerSlots.stream().filter(slot -> !slot.n_1700_B().n_1700_B()).filter(slot -> !ChestStealer.c_3005_b.Y_259_p.p_1458_L().n_1700_B(slot.n_1700_B().J_1907_R())).toList();
        if (validSlots.isEmpty()) {
            return Optional.empty();
        }
        return this.randomizaciyaEnabled.isEnabled() != false ? Optional.of(validSlots.get(ThreadLocalRandom.current().nextInt(validSlots.size()))) : validSlots.stream().findFirst();
    }
}



