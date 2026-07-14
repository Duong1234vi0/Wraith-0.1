/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.n_3832_I;
import lightning.product.u_530_F;

public class MerchantOffer {
    private final Z_1993_T n_1700_B;
    private final Z_1993_T J_1907_R;
    private final Z_1993_T R_4764_Y;
    private int G_564_y;
    private final int P_1922_E;
    private boolean u_1723_Y = true;
    private int v_4262_N;
    private int w_1484_f;
    private float t_148_a;
    private int s_956_w = 1;

    public MerchantOffer(U_2912_j dataTag) {
        this.n_1700_B = Z_1993_T.n_1700_B(dataTag.M_182_A("buy"));
        this.J_1907_R = Z_1993_T.n_1700_B(dataTag.M_182_A("buyB"));
        this.R_4764_Y = Z_1993_T.n_1700_B(dataTag.M_182_A("sell"));
        this.G_564_y = dataTag.w_1484_f("uses");
        this.P_1922_E = dataTag.R_4764_Y("maxUses", 99) ? dataTag.w_1484_f("maxUses") : 4;
        if (dataTag.R_4764_Y("rewardExp", 1)) {
            this.u_1723_Y = dataTag.t_1786_h("rewardExp");
        }
        if (dataTag.R_4764_Y("xp", 3)) {
            this.s_956_w = dataTag.w_1484_f("xp");
        }
        if (dataTag.R_4764_Y("priceMultiplier", 5)) {
            this.t_148_a = dataTag.s_956_w("priceMultiplier");
        }
        this.v_4262_N = dataTag.w_1484_f("specialPrice");
        this.w_1484_f = dataTag.w_1484_f("demand");
    }

    public MerchantOffer(Z_1993_T buyingStackFirstIn, Z_1993_T sellingStackIn, int maxUsesIn, int givenEXPIn, float priceMultiplierIn) {
        this(buyingStackFirstIn, Z_1993_T.J_1907_R, sellingStackIn, maxUsesIn, givenEXPIn, priceMultiplierIn);
    }

    public MerchantOffer(Z_1993_T buyingStackFirstIn, Z_1993_T buyingStackSecondIn, Z_1993_T sellingStackIn, int maxUsesIn, int givenEXPIn, float priceMultiplierIn) {
        this(buyingStackFirstIn, buyingStackSecondIn, sellingStackIn, 0, maxUsesIn, givenEXPIn, priceMultiplierIn);
    }

    public MerchantOffer(Z_1993_T buyingStackFirstIn, Z_1993_T buyingStackSecondIn, Z_1993_T sellingStackIn, int usesIn, int maxUsesIn, int givenEXPIn, float priceMultiplierIn) {
        this(buyingStackFirstIn, buyingStackSecondIn, sellingStackIn, usesIn, maxUsesIn, givenEXPIn, priceMultiplierIn, 0);
    }

    public MerchantOffer(Z_1993_T buyingStackFirstIn, Z_1993_T buyingStackSecondIn, Z_1993_T sellingStackIn, int usesIn, int maxUsesIn, int givenEXPIn, float priceMultiplierIn, int demandIn) {
        this.n_1700_B = buyingStackFirstIn;
        this.J_1907_R = buyingStackSecondIn;
        this.R_4764_Y = sellingStackIn;
        this.G_564_y = usesIn;
        this.P_1922_E = maxUsesIn;
        this.s_956_w = givenEXPIn;
        this.t_148_a = priceMultiplierIn;
        this.w_1484_f = demandIn;
    }

    public Z_1993_T n_1700_B() {
        return this.n_1700_B;
    }

    public Z_1993_T J_1907_R() {
        int i = this.n_1700_B.t_4043_B();
        Z_1993_T itemstack = this.n_1700_B.t_148_a();
        int j = Math.max(0, u_530_F.G_564_y((float)(i * this.w_1484_f) * this.t_148_a));
        itemstack.P_1922_E(u_530_F.n_1700_B(i + j + this.v_4262_N, 1, this.n_1700_B.J_1907_R().u_2550_I()));
        return itemstack;
    }

    public Z_1993_T R_4764_Y() {
        return this.J_1907_R;
    }

    public Z_1993_T G_564_y() {
        return this.R_4764_Y;
    }

    public void P_1922_E() {
        this.w_1484_f = this.w_1484_f + this.G_564_y - (this.P_1922_E - this.G_564_y);
    }

    public Z_1993_T u_1723_Y() {
        return this.R_4764_Y.t_148_a();
    }

    public int v_4262_N() {
        return this.G_564_y;
    }

    public void w_1484_f() {
        this.G_564_y = 0;
    }

    public int t_148_a() {
        return this.P_1922_E;
    }

    public void s_956_w() {
        ++this.G_564_y;
    }

    public int u_2550_I() {
        return this.w_1484_f;
    }

    public void n_1700_B(int add) {
        this.v_4262_N += add;
    }

    public void M_588_G() {
        this.v_4262_N = 0;
    }

    public int P_4830_p() {
        return this.v_4262_N;
    }

    public void J_1907_R(int price) {
        this.v_4262_N = price;
    }

    public float h_1847_R() {
        return this.t_148_a;
    }

    public int Q_4569_t() {
        return this.s_956_w;
    }

    public boolean M_182_A() {
        return this.G_564_y >= this.P_1922_E;
    }

    public void t_1786_h() {
        this.G_564_y = this.P_1922_E;
    }

    public boolean multiplayerClientSuggestionProvider() {
        return this.G_564_y > 0;
    }

    public boolean w_1457_N() {
        return this.u_1723_Y;
    }

    public U_2912_j Y_601_j() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("buy", this.n_1700_B.J_1907_R(new U_2912_j()));
        compoundnbt.n_1700_B("sell", this.R_4764_Y.J_1907_R(new U_2912_j()));
        compoundnbt.n_1700_B("buyB", this.J_1907_R.J_1907_R(new U_2912_j()));
        compoundnbt.J_1907_R("uses", this.G_564_y);
        compoundnbt.J_1907_R("maxUses", this.P_1922_E);
        compoundnbt.n_1700_B("rewardExp", this.u_1723_Y);
        compoundnbt.J_1907_R("xp", this.s_956_w);
        compoundnbt.n_1700_B("priceMultiplier", this.t_148_a);
        compoundnbt.J_1907_R("specialPrice", this.v_4262_N);
        compoundnbt.J_1907_R("demand", this.w_1484_f);
        return compoundnbt;
    }

    public boolean n_1700_B(Z_1993_T p_222204_1_, Z_1993_T p_222204_2_) {
        return this.R_4764_Y(p_222204_1_, this.J_1907_R()) && p_222204_1_.t_4043_B() >= this.J_1907_R().t_4043_B() && this.R_4764_Y(p_222204_2_, this.J_1907_R) && p_222204_2_.t_4043_B() >= this.J_1907_R.t_4043_B();
    }

    private boolean R_4764_Y(Z_1993_T left, Z_1993_T right) {
        if (right.n_1700_B() && left.n_1700_B()) {
            return true;
        }
        Z_1993_T itemstack = left.t_148_a();
        if (itemstack.J_1907_R().P_4830_p()) {
            itemstack.J_1907_R(itemstack.v_4262_N());
        }
        return Z_1993_T.R_4764_Y(itemstack, right) && (!right.h_1847_R() || itemstack.h_1847_R() && n_3832_I.n_1700_B(right.Q_4569_t(), itemstack.Q_4569_t(), false));
    }

    public boolean J_1907_R(Z_1993_T p_222215_1_, Z_1993_T p_222215_2_) {
        if (!this.n_1700_B(p_222215_1_, p_222215_2_)) {
            return false;
        }
        p_222215_1_.v_4262_N(this.J_1907_R().t_4043_B());
        if (!this.R_4764_Y().n_1700_B()) {
            p_222215_2_.v_4262_N(this.R_4764_Y().t_4043_B());
        }
        return true;
    }
}


