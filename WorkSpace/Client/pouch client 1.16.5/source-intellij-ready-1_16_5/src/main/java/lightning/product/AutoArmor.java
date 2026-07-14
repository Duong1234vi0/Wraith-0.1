/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import lightning.product.NumberSetting;
import lightning.product.K_4096_w;
import lightning.product.R_2515_i;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.Enchantments;
import lightning.product.e_1174_E;
import lightning.product.h_1015_G;
import lightning.product.ArmorMaterial;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.ModuleCategory;

public class AutoArmor
extends Module {
    private final NumberSetting zaderzhkaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 150.0f, 0.0f, 1000.0f, 5.0f);
    private final BooleanSetting ignorirovatElitruEnabled = new BooleanSetting("\u0418\u0433\u043d\u043e\u0440\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u044d\u043b\u0438\u0442\u0440\u0443", true);
    private final BooleanSetting prioritetPochinkiEnabled = new BooleanSetting("\u041f\u0440\u0438\u043e\u0440\u0438\u0442\u0435\u0442 \u043f\u043e\u0447\u0438\u043d\u043a\u0438", true);
    private final V_4557_X s_956_w = new V_4557_X();

    public AutoArmor() {
        super("AutoArmor", ModuleCategory.G_564_y);
        this.addSettings(this.zaderzhkaSetting, this.ignorirovatElitruEnabled, this.prioritetPochinkiEnabled);
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        R_2515_i armorItem;
        int i;
        if (AutoArmor.c_3005_b.Y_1740_V != null) {
            return;
        }
        if (!this.s_956_w.J_1907_R(((Float)this.zaderzhkaSetting.getValue()).longValue())) {
            return;
        }
        int[] bestIndexes = new int[]{-1, -1, -1, -1};
        int[] bestValues = new int[4];
        for (i = 0; i < 4; ++i) {
            Z_1993_T currentArmor = AutoArmor.c_3005_b.Y_259_p.l_1268_F.R_4764_Y(i);
            if (!this.n_1700_B(currentArmor)) continue;
            armorItem = (R_2515_i)currentArmor.J_1907_R();
            bestValues[i] = this.n_1700_B(armorItem, currentArmor);
        }
        for (i = 0; i < 36; ++i) {
            q_1613_l item;
            Z_1993_T stack = AutoArmor.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (stack == null || stack.n_1700_B() || !((item = stack.J_1907_R()) instanceof R_2515_i)) continue;
            armorItem = (R_2515_i)item;
            int slotIndex = armorItem.t_148_a().J_1907_R();
            int value = this.n_1700_B(armorItem, stack);
            if (value < bestValues[slotIndex]) continue;
            if (value == bestValues[slotIndex] && this.prioritetPochinkiEnabled.isEnabled().booleanValue()) {
                Z_1993_T currentBest;
                boolean equippedHasMending;
                boolean currentHasMending = K_4096_w.n_1700_B(Enchantments.v_4276_D, stack) > 0;
                Z_1993_T equippedArmor = AutoArmor.c_3005_b.Y_259_p.l_1268_F.R_4764_Y(slotIndex);
                boolean bl = equippedHasMending = equippedArmor != null && !equippedArmor.n_1700_B() && K_4096_w.n_1700_B(Enchantments.v_4276_D, equippedArmor) > 0;
                if (equippedHasMending && !currentHasMending) continue;
                if (bestIndexes[slotIndex] != -1 && (currentBest = AutoArmor.c_3005_b.Y_259_p.l_1268_F.s_956_w(bestIndexes[slotIndex])) != null && !currentBest.n_1700_B()) {
                    boolean bestHasMending;
                    boolean bl2 = bestHasMending = K_4096_w.n_1700_B(Enchantments.v_4276_D, currentBest) > 0;
                    if (bestHasMending && !currentHasMending || currentHasMending == bestHasMending) continue;
                }
            }
            bestIndexes[slotIndex] = i;
            bestValues[slotIndex] = value;
        }
        ArrayList<Integer> slots = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3));
        Collections.shuffle(slots);
        Iterator iterator = slots.iterator();
        while (iterator.hasNext()) {
            int bestIndex;
            int slot = (Integer)iterator.next();
            Z_1993_T currentArmor = AutoArmor.c_3005_b.Y_259_p.l_1268_F.R_4764_Y(slot);
            if (this.ignorirovatElitruEnabled.isEnabled().booleanValue() && slot == e_1174_E.P_1922_E.J_1907_R() && currentArmor != null && !currentArmor.n_1700_B() && currentArmor.J_1907_R() == Items.NyliumBlock || (bestIndex = bestIndexes[slot]) == -1 || this.n_1700_B(currentArmor) && AutoArmor.c_3005_b.Y_259_p.l_1268_F.P_1922_E() == -1) continue;
            if (bestIndex < 9) {
                bestIndex += 36;
            }
            if (this.n_1700_B(currentArmor)) {
                AutoArmor.c_3005_b.w_1457_N.windowClick(0, 8 - slot, 0, a_408_T.J_1907_R, AutoArmor.c_3005_b.Y_259_p);
            }
            AutoArmor.c_3005_b.w_1457_N.windowClick(0, bestIndex, 0, a_408_T.J_1907_R, AutoArmor.c_3005_b.Y_259_p);
            this.s_956_w.n_1700_B();
            break;
        }
    }

    private int n_1700_B(R_2515_i armor, Z_1993_T stack) {
        int protection = K_4096_w.n_1700_B(Enchantments.n_1700_B, stack);
        ArmorMaterial material = armor.P_1922_E();
        int damageReduction = material.J_1907_R(armor.t_148_a());
        float toughness = armor.s_956_w();
        return (int)(((float)(damageReduction * 20 + protection * 12) + toughness * 2.0f + (float)(damageReduction * 5)) / 8.0f);
    }

    private boolean n_1700_B(Z_1993_T stack) {
        return stack != null && !stack.n_1700_B() && stack.J_1907_R() instanceof R_2515_i;
    }
}



