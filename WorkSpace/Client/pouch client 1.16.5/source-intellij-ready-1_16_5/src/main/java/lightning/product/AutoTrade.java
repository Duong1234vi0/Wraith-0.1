/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.NumberSetting;
import lightning.product.K_3710_b;
import lightning.product.V_4557_X;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.a_408_T;
import lightning.product.g_904_S;
import lightning.product.h_1015_G;
import lightning.product.MerchantOffers;
import lightning.product.BooleanSetting;
import lightning.product.q_1613_l;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.MerchantOffer;
import lightning.product.ModuleCategory;

public class AutoTrade
extends Module {
    private final NumberSetting zaderzhkaSetting = new NumberSetting("\u0417\u0430\u0434\u0435\u0440\u0436\u043a\u0430", 100.0f, 0.0f, 500.0f, 10.0f);
    private final NumberSetting nomerTreydaSetting = new NumberSetting("\u041d\u043e\u043c\u0435\u0440 \u0442\u0440\u0435\u0439\u0434\u0430", 1.0f, 1.0f, 10.0f, 1.0f);
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "\u0412\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0439", "\u0412\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0439", "\u0412\u0441\u0435", "\u0412\u044b\u0433\u043e\u0434\u043d\u044b\u0435");
    private final BooleanSetting avtoVyborTreydaEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e-\u0432\u044b\u0431\u043e\u0440 \u0442\u0440\u0435\u0439\u0434\u0430", true);
    private final BooleanSetting propuskatIscherpannyeEnabled = new BooleanSetting("\u041f\u0440\u043e\u043f\u0443\u0441\u043a\u0430\u0442\u044c \u0438\u0441\u0447\u0435\u0440\u043f\u0430\u043d\u043d\u044b\u0435", true);
    private final BooleanSetting tolkoZacharovaniyaEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0437\u0430\u0447\u0430\u0440\u043e\u0432\u0430\u043d\u0438\u044f", false, () -> this.rezhimMode.isMode("\u0412\u044b\u0433\u043e\u0434\u043d\u044b\u0435"));
    private final BooleanSetting zakrytKogdaGotovoEnabled = new BooleanSetting("\u0417\u0430\u043a\u0440\u044b\u0442\u044c \u043a\u043e\u0433\u0434\u0430 \u0433\u043e\u0442\u043e\u0432\u043e", false);
    private final V_4557_X h_1847_R = new V_4557_X();
    private int Q_4569_t = 0;
    private boolean M_182_A = true;

    public AutoTrade() {
        super("AutoTrade", ModuleCategory.G_564_y);
        this.addSettings(this.zaderzhkaSetting, this.nomerTreydaSetting, this.rezhimMode, this.avtoVyborTreydaEnabled, this.propuskatIscherpannyeEnabled, this.tolkoZacharovaniyaEnabled, this.zakrytKogdaGotovoEnabled);
    }

    @Override
    public void onEnable() {
        this.Q_4569_t = 0;
        this.M_182_A = true;
        super.onEnable();
    }

    @Y_1740_V
    private void n_1700_B(h_1015_G e) {
        if (AutoTrade.c_3005_b.Y_259_p == null || AutoTrade.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!(AutoTrade.c_3005_b.Y_1740_V instanceof g_904_S)) {
            this.Q_4569_t = 0;
            this.M_182_A = true;
            return;
        }
        if (!(AutoTrade.c_3005_b.Y_259_p.H_1873_g instanceof K_3710_b)) {
            return;
        }
        K_3710_b container = (K_3710_b)AutoTrade.c_3005_b.Y_259_p.H_1873_g;
        MerchantOffers offers = container.P_1922_E();
        if (offers == null || offers.isEmpty()) {
            return;
        }
        if (!this.h_1847_R.J_1907_R(((Float)this.zaderzhkaSetting.getValue()).longValue())) {
            return;
        }
        switch ((String)this.rezhimMode.getValue()) {
            case "\u0412\u044b\u0431\u0440\u0430\u043d\u043d\u044b\u0439": {
                this.n_1700_B(container, offers);
                break;
            }
            case "\u0412\u0441\u0435": {
                this.J_1907_R(container, offers);
                break;
            }
            case "\u0412\u044b\u0433\u043e\u0434\u043d\u044b\u0435": {
                this.R_4764_Y(container, offers);
            }
        }
    }

    private void n_1700_B(K_3710_b container, MerchantOffers offers) {
        int index = ((Float)this.nomerTreydaSetting.getValue()).intValue() - 1;
        if (index >= offers.size()) {
            return;
        }
        MerchantOffer offer = (MerchantOffer)offers.get(index);
        if (this.propuskatIscherpannyeEnabled.isEnabled().booleanValue() && offer.M_182_A()) {
            return;
        }
        if (!this.J_1907_R(offer)) {
            return;
        }
        if (this.avtoVyborTreydaEnabled.isEnabled().booleanValue() && this.M_182_A) {
            this.n_1700_B(container, index);
            this.M_182_A = false;
            this.h_1847_R.n_1700_B();
            return;
        }
        this.n_1700_B(container);
        this.h_1847_R.n_1700_B();
    }

    private void J_1907_R(K_3710_b container, MerchantOffers offers) {
        if (this.Q_4569_t >= offers.size()) {
            if (this.zakrytKogdaGotovoEnabled.isEnabled().booleanValue()) {
                AutoTrade.c_3005_b.Y_259_p.P_1922_E();
            }
            this.Q_4569_t = 0;
            return;
        }
        MerchantOffer offer = (MerchantOffer)offers.get(this.Q_4569_t);
        if (this.propuskatIscherpannyeEnabled.isEnabled().booleanValue() && offer.M_182_A()) {
            ++this.Q_4569_t;
            this.M_182_A = true;
            return;
        }
        if (!this.J_1907_R(offer)) {
            ++this.Q_4569_t;
            this.M_182_A = true;
            return;
        }
        if (this.avtoVyborTreydaEnabled.isEnabled().booleanValue() && this.M_182_A) {
            this.n_1700_B(container, this.Q_4569_t);
            this.M_182_A = false;
            this.h_1847_R.n_1700_B();
            return;
        }
        this.n_1700_B(container);
        this.h_1847_R.n_1700_B();
        if (offer.M_182_A()) {
            ++this.Q_4569_t;
            this.M_182_A = true;
        }
    }

    private void R_4764_Y(K_3710_b container, MerchantOffers offers) {
        for (int i = 0; i < offers.size(); ++i) {
            MerchantOffer offer = (MerchantOffer)offers.get(i);
            if (this.propuskatIscherpannyeEnabled.isEnabled().booleanValue() && offer.M_182_A() || !this.J_1907_R(offer) || !this.n_1700_B(offer)) continue;
            if (this.avtoVyborTreydaEnabled.isEnabled().booleanValue() && (this.M_182_A || this.Q_4569_t != i)) {
                this.n_1700_B(container, i);
                this.Q_4569_t = i;
                this.M_182_A = false;
                this.h_1847_R.n_1700_B();
                return;
            }
            this.n_1700_B(container);
            this.h_1847_R.n_1700_B();
            return;
        }
        if (this.zakrytKogdaGotovoEnabled.isEnabled().booleanValue()) {
            AutoTrade.c_3005_b.Y_259_p.P_1922_E();
        }
    }

    private boolean n_1700_B(MerchantOffer offer) {
        q_1613_l input;
        Z_1993_T result = offer.G_564_y();
        if (this.tolkoZacharovaniyaEnabled.isEnabled().booleanValue()) {
            return result.k_2293_S() || result.J_1907_R() == Items.M_4472_P;
        }
        q_1613_l resultItem = result.J_1907_R();
        if (result.k_2293_S()) {
            return true;
        }
        if (resultItem == Items.M_4472_P) {
            return true;
        }
        if (resultItem == Items.N_2592_G) {
            return true;
        }
        if (resultItem == Items.M_2029_A) {
            return true;
        }
        if (resultItem == Items.C_1577_A) {
            return true;
        }
        if (resultItem == Items.f_508_U) {
            return true;
        }
        if (resultItem == Items.q_4361_M) {
            return true;
        }
        if (resultItem == Items.A_1603_w) {
            return true;
        }
        if (resultItem == Items.V_4557_X) {
            return true;
        }
        if (resultItem == Items.v_2746_S) {
            return true;
        }
        if (resultItem == Items.HorizontalDirectionalBlock) {
            return true;
        }
        if (resultItem == Items.Z_361_l) {
            return true;
        }
        return resultItem == Items.Y_2905_A && ((input = offer.n_1700_B().J_1907_R()) == Items.l_3370_o || input == Items.m_1964_F || input == Items.Animation || input == Items.V_3441_j || input == Items.T_797_O || input == Items.D_1621_L);
    }

    private boolean J_1907_R(MerchantOffer offer) {
        int needed2;
        int count2;
        int needed1;
        Z_1993_T cost1 = offer.n_1700_B();
        Z_1993_T cost2 = offer.R_4764_Y();
        int count1 = this.n_1700_B(cost1.J_1907_R());
        if (count1 < (needed1 = cost1.t_4043_B())) {
            return false;
        }
        return cost2.n_1700_B() || (count2 = this.n_1700_B(cost2.J_1907_R())) >= (needed2 = cost2.t_4043_B());
    }

    private int n_1700_B(q_1613_l item) {
        int count = 0;
        for (Z_1993_T stack : AutoTrade.c_3005_b.Y_259_p.l_1268_F.n_1700_B) {
            if (stack.J_1907_R() != item) continue;
            count += stack.t_4043_B();
        }
        return count;
    }

    private void n_1700_B(K_3710_b container, int index) {
        container.G_564_y(index);
        container.v_4262_N(index);
    }

    private void n_1700_B(K_3710_b container) {
        Slot resultSlot = container.n_1700_B(2);
        if (resultSlot.J_1907_R()) {
            AutoTrade.c_3005_b.w_1457_N.windowClick(container.u_1723_Y, 2, 0, a_408_T.J_1907_R, AutoTrade.c_3005_b.Y_259_p);
        }
    }

    @Override
    public void onDisable() {
        this.Q_4569_t = 0;
        this.M_182_A = true;
        super.onDisable();
    }
}



