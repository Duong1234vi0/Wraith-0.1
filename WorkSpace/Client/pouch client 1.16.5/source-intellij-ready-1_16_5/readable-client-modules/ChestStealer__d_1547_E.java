/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import lightning.product.I_686_h;
import lightning.product.P_225_f;
import lightning.product.V_4557_X;
import lightning.product.X_3546_T;
import lightning.product.Y_1740_V;
import lightning.product.Y_2498_n;
import lightning.product.a_2900_S;
import lightning.product.a_408_T;
import lightning.product.h_1015_G;
import lightning.product.p_1977_n;
import lightning.product.y_2603_k;
import lightning.product.z_1477_l;

public class d_1547_E
extends X_3546_T {
    private final I_686_h v_4262_N = new I_686_h("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 100.0f, 0.0f, 1000.0f, 1.0f);
    private final p_1977_n w_1484_f = new p_1977_n("\u0420\u0430\u043d\u0434\u043e\u043c\u0438\u0437\u0430\u0446\u0438\u044f", false);
    private final V_4557_X t_148_a = new V_4557_X();

    public d_1547_E() {
        super("ChestStealer", y_2603_k.G_564_y);
        this.n_1700_B(this.v_4262_N, this.w_1484_f);
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        a_2900_S openContainer = d_1547_E.c_3005_b.Y_259_p.H_1873_g;
        if (openContainer instanceof P_225_f || openContainer instanceof z_1477_l) {
            List<Y_2498_n> slots = openContainer.P_1922_E;
            if (this.t_148_a.J_1907_R(((Float)this.v_4262_N.J_1907_R()).longValue())) {
                this.J_1907_R(slots).ifPresent(slot -> {
                    if (d_1547_E.c_3005_b.Y_259_p.H_1873_g == openContainer) {
                        d_1547_E.c_3005_b.w_1457_N.windowClick(openContainer.u_1723_Y, slot.G_564_y, 0, a_408_T.J_1907_R, d_1547_E.c_3005_b.Y_259_p);
                        this.t_148_a.n_1700_B();
                    }
                });
            }
        }
    }

    private Optional<Y_2498_n> J_1907_R(List<Y_2498_n> slots) {
        int containerSlotCount = slots.size() - d_1547_E.c_3005_b.Y_259_p.l_1268_F.n_1700_B.size();
        List<Y_2498_n> containerSlots = slots.subList(0, containerSlotCount);
        List<Y_2498_n> validSlots = containerSlots.stream().filter(slot -> !slot.n_1700_B().n_1700_B()).filter(slot -> !d_1547_E.c_3005_b.Y_259_p.p_1458_L().n_1700_B(slot.n_1700_B().J_1907_R())).toList();
        if (validSlots.isEmpty()) {
            return Optional.empty();
        }
        return this.w_1484_f.t_148_a() != false ? Optional.of(validSlots.get(ThreadLocalRandom.current().nextInt(validSlots.size()))) : validSlots.stream().findFirst();
    }
}

