/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lightning.product.F_1446_q;
import lightning.product.MobEffects;
import lightning.product.MultiBooleanSetting;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_3504_M;
import lightning.product.g_422_i;
import lightning.product.h_1015_G;
import lightning.product.i_2572_h;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.q_3115_L;
import lightning.product.r_4790_y;
import lightning.product.u_1934_K;
import lightning.product.u_925_K;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class AutoPotion
extends Module {
    private int v_4262_N;
    private final MultiBooleanSetting brosatOptions = new MultiBooleanSetting("\u0411\u0440\u043e\u0441\u0430\u0442\u044c", new BooleanSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", true), new BooleanSetting("\u0421\u0438\u043b\u0430", true), new BooleanSetting("\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c", true));
    private final BooleanSetting tolkoPriPvpEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u043f\u0440\u0438 \u043f\u0432\u043f", true);
    private final BooleanSetting otklyuchatPosleIspolzovaniyaEnabled = new BooleanSetting("\u041e\u0442\u043a\u043b\u044e\u0447\u0430\u0442\u044c \u043f\u043e\u0441\u043b\u0435 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u044f", false);
    private final BooleanSetting tolkoSHotbaraEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0441 \u0445\u043e\u0442\u0431\u0430\u0440\u0430", false);
    private final BooleanSetting prioritetSilyEnabled = new BooleanSetting("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442 \u0441\u0438\u043b\u044b", true);
    private boolean P_4830_p = false;

    public AutoPotion() {
        super("AutoPotion", ModuleCategory.n_1700_B);
        this.addSettings(this.brosatOptions, this.tolkoPriPvpEnabled, this.otklyuchatPosleIspolzovaniyaEnabled, this.tolkoSHotbaraEnabled, this.prioritetSilyEnabled);
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.v_4262_N = 20;
        this.P_4830_p = false;
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        boolean allFromHotbar;
        if (this.otklyuchatPosleIspolzovaniyaEnabled.isEnabled().booleanValue() && this.P_4830_p) {
            return;
        }
        ++this.v_4262_N;
        ArrayList<Integer> slotsToThrow = new ArrayList<Integer>();
        if (this.prioritetSilyEnabled.isEnabled().booleanValue()) {
            this.addSettings(slotsToThrow, this.brosatOptions.isOptionEnabled("\u0421\u0438\u043b\u0430"), MobEffects.P_1922_E);
            this.addSettings(slotsToThrow, this.brosatOptions.isOptionEnabled("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c"), MobEffects.n_1700_B);
            this.addSettings(slotsToThrow, this.brosatOptions.isOptionEnabled("\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c"), MobEffects.M_588_G);
        } else {
            this.addSettings(slotsToThrow, this.brosatOptions.isOptionEnabled("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c"), MobEffects.n_1700_B);
            this.addSettings(slotsToThrow, this.brosatOptions.isOptionEnabled("\u0421\u0438\u043b\u0430"), MobEffects.P_1922_E);
            this.addSettings(slotsToThrow, this.brosatOptions.isOptionEnabled("\u041e\u0433\u043d\u0435\u0441\u0442\u043e\u0439\u043a\u043e\u0441\u0442\u044c"), MobEffects.M_588_G);
        }
        if (this.tolkoPriPvpEnabled.isEnabled().booleanValue() && !q_3115_L.n_1700_B() || this.v_4262_N < 20 || u_925_K.n_1700_B(0.5f) || slotsToThrow.isEmpty()) {
            return;
        }
        if (this.tolkoSHotbaraEnabled.isEnabled().booleanValue() && !(allFromHotbar = slotsToThrow.stream().allMatch(slot -> slot < 9))) {
            ArrayList<Integer> hotbarSlots = new ArrayList<Integer>();
            for (Integer slot2 : slotsToThrow) {
                if (slot2 >= 9) continue;
                hotbarSlots.add(slot2);
            }
            if (hotbarSlots.isEmpty()) {
                return;
            }
            slotsToThrow = hotbarSlots;
        }
        float turnSpeed = ThreadLocalRandom.current().nextFloat(275.0f, 444.0f);
        r_4790_y.n_1700_B(new F_1446_q(AutoPotion.c_3005_b.Y_259_p.p_178_J, 90.0f), turnSpeed, 1, 10);
        F_1446_q f_1446_q = new F_1446_q(AutoPotion.c_3005_b.Y_259_p);
        if (f_1446_q.n_1700_B(F_1446_q.J_1907_R()) > 1.0) {
            return;
        }
        int original = AutoPotion.c_3005_b.Y_259_p.l_1268_F.G_564_y;
        boolean thrownAnyPotions = false;
        for (Integer slot3 : slotsToThrow) {
            if (slot3 < 9) {
                AutoPotion.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(slot3));
                AutoPotion.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
                thrownAnyPotions = true;
                continue;
            }
            if (this.tolkoSHotbaraEnabled.isEnabled().booleanValue()) continue;
            AutoPotion.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(slot3));
            AutoPotion.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            AutoPotion.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new i_2572_h(slot3));
            thrownAnyPotions = true;
        }
        AutoPotion.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(original));
        if (thrownAnyPotions) {
            this.v_4262_N = 0;
            this.P_4830_p = true;
            if (this.otklyuchatPosleIspolzovaniyaEnabled.isEnabled().booleanValue()) {
                this.R_4764_Y();
            }
        }
    }

    private void n_1700_B(List<Integer> slotsToThrow, boolean enabled, g_422_i effect) {
        int inventorySlot;
        if (!enabled || AutoPotion.c_3005_b.Y_259_p.J_1907_R(effect)) {
            return;
        }
        int hotbarSlot = u_1934_K.n_1700_B(true, false, true, false, effect);
        if (hotbarSlot != -1) {
            slotsToThrow.add(hotbarSlot);
            return;
        }
        if (!this.tolkoSHotbaraEnabled.isEnabled().booleanValue() && (inventorySlot = u_1934_K.n_1700_B(false, false, true, false, effect)) != -1) {
            slotsToThrow.add(inventorySlot);
        }
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.P_4830_p = false;
    }
}



