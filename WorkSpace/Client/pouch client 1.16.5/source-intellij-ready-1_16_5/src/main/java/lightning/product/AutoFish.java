/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.K_4096_w;
import lightning.product.O_4882_g;
import lightning.product.Q_2753_H;
import lightning.product.SoundEvents;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.ClientboundSoundPacket;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.Z_3504_M;
import lightning.product.h_1015_G;
import lightning.product.p_1183_T;
import lightning.product.BooleanSetting;
import lightning.product.Packet;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class AutoFish
extends Module {
    private final BooleanSetting avtomaticheskiBratUdochkuEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e\u043c\u0430\u0442\u0438\u0447\u0435\u0441\u043a\u0438 \u0431\u0440\u0430\u0442\u044c \u0443\u0434\u043e\u0447\u043a\u0443", true);
    private final NumberSetting zaderzhkaPodsechkiMsSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043f\u043e\u0434\u0441\u0435\u0447\u043a\u0438 (\u043c\u0441)", 600.0f, 100.0f, 2000.0f, 50.0f);
    private final NumberSetting zaderzhkaPerezabrosaMsSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u043f\u0435\u0440\u0435\u0437\u0430\u0431\u0440\u043e\u0441\u0430 (\u043c\u0441)", 300.0f, 100.0f, 1000.0f, 50.0f);
    private final BooleanSetting proveryatProchnostEnabled = new BooleanSetting("\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0442\u044c \u043f\u0440\u043e\u0447\u043d\u043e\u0441\u0442\u044c", true);
    private final NumberSetting minProchnostSetting = new NumberSetting("\u041c\u0438\u043d. \u043f\u0440\u043e\u0447\u043d\u043e\u0441\u0442\u044c (%)", 10.0f, 1.0f, 50.0f, 1.0f, () -> this.proveryatProchnostEnabled.isEnabled());
    private final BooleanSetting proveryatInventarEnabled = new BooleanSetting("\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0442\u044c \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u044c", true);
    private final BooleanSetting ostanavlivatPriPolnomInventareEnabled = new BooleanSetting("\u041e\u0441\u0442\u0430\u043d\u0430\u0432\u043b\u0438\u0432\u0430\u0442\u044c \u043f\u0440\u0438 \u043f\u043e\u043b\u043d\u043e\u043c \u0438\u043d\u0432\u0435\u043d\u0442\u0430\u0440\u0435", false, () -> this.proveryatInventarEnabled.isEnabled());
    private final BooleanSetting proveryatNalichieBobberaEnabled = new BooleanSetting("\u041f\u0440\u043e\u0432\u0435\u0440\u044f\u0442\u044c \u043d\u0430\u043b\u0438\u0447\u0438\u0435 \u0431\u043e\u0431\u0431\u0435\u0440\u0430", true);
    private final BooleanSetting avtoSmenaUdochkiPriPolomkeEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u0441\u043c\u0435\u043d\u0430 \u0443\u0434\u043e\u0447\u043a\u0438 \u043f\u0440\u0438 \u043f\u043e\u043b\u043e\u043c\u043a\u0435", true);
    private final BooleanSetting avtoZabrosVVoduEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e \u0437\u0430\u0431\u0440\u043e\u0441 \u0432 \u0432\u043e\u0434\u0443", true);
    private final NumberSetting zaderzhkaAvtoZabrosaMsSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430 \u0430\u0432\u0442\u043e \u0437\u0430\u0431\u0440\u043e\u0441\u0430 (\u043c\u0441)", 500.0f, 100.0f, 2000.0f, 50.0f, () -> this.avtoZabrosVVoduEnabled.isEnabled());
    private boolean multiplayerClientSuggestionProvider = false;
    private boolean w_1457_N = false;
    private int Y_601_j = -1;
    private final V_4557_X Y_259_p = new V_4557_X();
    private final V_4557_X Q_2552_b = new V_4557_X();

    public AutoFish() {
        super("AutoFish", ModuleCategory.G_564_y);
        this.addSettings(this.avtomaticheskiBratUdochkuEnabled, this.zaderzhkaPodsechkiMsSetting, this.zaderzhkaPerezabrosaMsSetting, this.proveryatProchnostEnabled, this.minProchnostSetting, this.proveryatInventarEnabled, this.ostanavlivatPriPolnomInventareEnabled, this.proveryatNalichieBobberaEnabled, this.avtoSmenaUdochkiPriPolomkeEnabled, this.avtoZabrosVVoduEnabled, this.zaderzhkaAvtoZabrosaMsSetting);
    }

    @Override
    public void onDisable() {
        this.multiplayerClientSuggestionProvider = false;
        this.w_1457_N = false;
        this.Y_601_j = -1;
        this.Y_259_p.n_1700_B();
        this.Q_2552_b.n_1700_B();
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        boolean hasBobber;
        Z_1993_T rodStack;
        if (AutoFish.c_3005_b.Y_259_p == null || AutoFish.c_3005_b.Y_601_j == null) {
            return;
        }
        if (this.proveryatInventarEnabled.isEnabled().booleanValue() && this.M_182_A() && this.ostanavlivatPriPolnomInventareEnabled.isEnabled().booleanValue()) {
            return;
        }
        if (this.avtomaticheskiBratUdochkuEnabled.isEnabled().booleanValue() && this.Y_601_j == -1) {
            this.Q_4569_t();
        }
        if (this.Y_601_j != -1 && this.proveryatProchnostEnabled.isEnabled().booleanValue() && (rodStack = AutoFish.c_3005_b.Y_259_p.l_1268_F.s_956_w(this.Y_601_j)).J_1907_R() instanceof O_4882_g && this.n_1700_B(rodStack)) {
            if (this.avtoSmenaUdochkiPriPolomkeEnabled.isEnabled().booleanValue()) {
                this.Y_601_j = -1;
                this.Q_4569_t();
            } else {
                return;
            }
        }
        if (this.Y_601_j != -1 && AutoFish.c_3005_b.Y_259_p.l_1268_F.G_564_y != this.Y_601_j) {
            AutoFish.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.Y_601_j));
            AutoFish.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.Y_601_j;
        }
        boolean bl = hasBobber = AutoFish.c_3005_b.Y_259_p.X_2960_b != null && !AutoFish.c_3005_b.Y_259_p.X_2960_b.t_4219_U;
        if (this.proveryatNalichieBobberaEnabled.isEnabled().booleanValue() && this.w_1457_N && hasBobber) {
            return;
        }
        if (this.avtoZabrosVVoduEnabled.isEnabled().booleanValue() && this.Y_601_j != -1 && !hasBobber && !this.multiplayerClientSuggestionProvider && !this.w_1457_N) {
            if (this.Q_2552_b.n_1700_B((double)((Float)this.zaderzhkaAvtoZabrosaMsSetting.getValue()).intValue())) {
                this.h_1847_R();
                this.Q_2552_b.n_1700_B();
            }
        } else if (hasBobber || this.multiplayerClientSuggestionProvider || this.w_1457_N) {
            this.Q_2552_b.n_1700_B();
        }
        if (this.multiplayerClientSuggestionProvider && this.Y_259_p.n_1700_B((double)((Float)this.zaderzhkaPodsechkiMsSetting.getValue()).intValue())) {
            if (hasBobber) {
                this.h_1847_R();
                this.multiplayerClientSuggestionProvider = false;
                this.w_1457_N = true;
                this.Y_259_p.n_1700_B();
            } else {
                this.multiplayerClientSuggestionProvider = false;
                this.w_1457_N = true;
                this.Y_259_p.n_1700_B();
            }
        }
        if (this.w_1457_N && this.Y_259_p.n_1700_B((double)((Float)this.zaderzhkaPerezabrosaMsSetting.getValue()).intValue())) {
            if (!hasBobber) {
                this.h_1847_R();
                this.w_1457_N = false;
                this.Y_259_p.n_1700_B();
            } else {
                this.Y_259_p.n_1700_B();
            }
        }
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        ClientboundSoundPacket packet;
        if (AutoFish.c_3005_b.Y_259_p == null || AutoFish.c_3005_b.Y_601_j == null) {
            return;
        }
        Packet<?> t_3138_Z2 = event.G_564_y();
        if (t_3138_Z2 instanceof ClientboundSoundPacket && (packet = (ClientboundSoundPacket)t_3138_Z2).J_1907_R() == SoundEvents.I_3637_j) {
            this.multiplayerClientSuggestionProvider = true;
            this.Y_259_p.n_1700_B();
        }
    }

    private void h_1847_R() {
        if (this.Y_601_j != -1 && AutoFish.c_3005_b.Y_259_p.l_1268_F.s_956_w(this.Y_601_j).J_1907_R() instanceof O_4882_g) {
            if (AutoFish.c_3005_b.Y_259_p.l_1268_F.G_564_y != this.Y_601_j) {
                AutoFish.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.Y_601_j));
                AutoFish.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.Y_601_j;
            }
            AutoFish.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new Z_3504_M(x_1688_C.n_1700_B));
            AutoFish.c_3005_b.Y_259_p.n_1700_B(x_1688_C.n_1700_B);
        }
    }

    private void Q_4569_t() {
        int bestRodSlot = -1;
        int maxEnchantments = -1;
        for (int i = 0; i < 9; ++i) {
            int enchantmentCount;
            Z_1993_T stack = AutoFish.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!(stack.J_1907_R() instanceof O_4882_g) || this.proveryatProchnostEnabled.isEnabled().booleanValue() && this.n_1700_B(stack) || (enchantmentCount = K_4096_w.n_1700_B(stack).size()) <= maxEnchantments) continue;
            maxEnchantments = enchantmentCount;
            bestRodSlot = i;
        }
        if (bestRodSlot != -1) {
            this.Y_601_j = bestRodSlot;
        }
    }

    private boolean n_1700_B(Z_1993_T rodStack) {
        int currentDamage;
        if (!rodStack.P_1922_E()) {
            return false;
        }
        int maxDamage = rodStack.w_1484_f();
        float durabilityPercent = (float)(maxDamage - (currentDamage = rodStack.v_4262_N())) / (float)maxDamage * 100.0f;
        return durabilityPercent < ((Float)this.minProchnostSetting.getValue()).floatValue();
    }

    private boolean M_182_A() {
        for (int i = 0; i < 36; ++i) {
            Z_1993_T stack = AutoFish.c_3005_b.Y_259_p.l_1268_F.s_956_w(i);
            if (!stack.n_1700_B()) continue;
            return false;
        }
        return true;
    }
}



