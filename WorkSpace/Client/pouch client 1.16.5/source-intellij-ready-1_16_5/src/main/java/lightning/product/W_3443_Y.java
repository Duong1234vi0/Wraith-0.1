/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.C_4114_x;
import lightning.product.Attributes;
import lightning.product.U_2534_D;
import lightning.product.U_2912_j;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.h_256_u;
import lightning.product.EntityDataSerializers;
import lightning.product.m_3054_I;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.s_1415_m;
import lightning.product.t_5_h;
import lightning.product.x_1688_C;

public abstract class W_3443_Y
extends U_2534_D {
    private static final h_256_u<Boolean> Q_2552_b = C_4114_x.n_1700_B(W_3443_Y.class, EntityDataSerializers.t_148_a);

    protected W_3443_Y(t_5_h<? extends W_3443_Y> type, b_4507_u worldIn) {
        super((t_5_h<? extends U_2534_D>)type, worldIn);
        this.Y_601_j = false;
    }

    @Override
    protected void y_4642_Y() {
        this.n_1700_B(Attributes.n_1700_B).n_1700_B(this.NumberSetting());
    }

    @Override
    protected void a_() {
        super.a_();
        this.l_4537_E.n_1700_B(Q_2552_b, false);
    }

    public static s_1415_m.n_1700_B h_1640_b() {
        return W_3443_Y.BooleanSetting().n_1700_B(Attributes.G_564_y, 0.175f).n_1700_B(Attributes.P_4830_p, 0.5);
    }

    public boolean V_1176_p() {
        return this.l_4537_E.n_1700_B(Q_2552_b);
    }

    public void w_1457_N(boolean chested) {
        this.l_4537_E.J_1907_R(Q_2552_b, chested);
    }

    @Override
    protected int y_2447_C() {
        return this.V_1176_p() ? 17 : super.y_2447_C();
    }

    @Override
    public double s_1671_u() {
        return super.s_1671_u() - 0.25;
    }

    @Override
    protected void A_229_v() {
        super.A_229_v();
        if (this.V_1176_p()) {
            if (!this.O_508_d.Y_259_p) {
                this.n_1700_B(a_3742_W.L_1362_X);
            }
            this.w_1457_N(false);
        }
    }

    @Override
    public void n_1700_B(U_2912_j compound) {
        super.n_1700_B(compound);
        compound.n_1700_B("ChestedHorse", this.V_1176_p());
        if (this.V_1176_p()) {
            q_2896_o listnbt = new q_2896_o();
            for (int i = 2; i < this.t_1786_h.Y_259_p(); ++i) {
                Z_1993_T itemstack = this.t_1786_h.s_956_w(i);
                if (itemstack.n_1700_B()) continue;
                U_2912_j compoundnbt = new U_2912_j();
                compoundnbt.n_1700_B("Slot", (byte)i);
                itemstack.J_1907_R(compoundnbt);
                listnbt.add(compoundnbt);
            }
            compound.n_1700_B("Items", listnbt);
        }
    }

    @Override
    public void J_1907_R(U_2912_j compound) {
        super.J_1907_R(compound);
        this.w_1457_N(compound.t_1786_h("ChestedHorse"));
        if (this.V_1176_p()) {
            q_2896_o listnbt = compound.G_564_y("Items", 10);
            this.p_1458_L();
            for (int i = 0; i < listnbt.size(); ++i) {
                U_2912_j compoundnbt = listnbt.n_1700_B(i);
                int j = compoundnbt.u_1723_Y("Slot") & 0xFF;
                if (j < 2 || j >= this.t_1786_h.Y_259_p()) continue;
                this.t_1786_h.J_1907_R(j, Z_1993_T.n_1700_B(compoundnbt));
            }
        }
        this.Module();
    }

    @Override
    public boolean n_1700_B(int inventorySlot, Z_1993_T itemStackIn) {
        if (inventorySlot == 499) {
            if (this.V_1176_p() && itemStackIn.n_1700_B()) {
                this.w_1457_N(false);
                this.p_1458_L();
                return true;
            }
            if (!this.V_1176_p() && itemStackIn.J_1907_R() == a_3742_W.L_1362_X.u_1723_Y()) {
                this.w_1457_N(true);
                this.p_1458_L();
                return true;
            }
        }
        return super.n_1700_B(inventorySlot, itemStackIn);
    }

    @Override
    public m_3054_I J_1907_R(a_3913_L p_230254_1_, x_1688_C p_230254_2_) {
        Z_1993_T itemstack = p_230254_1_.R_4764_Y(p_230254_2_);
        if (!this.d_()) {
            if (this.o_4117_e() && p_230254_1_.z_3000_g()) {
                this.u_1723_Y(p_230254_1_);
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            if (this.H_1883_T()) {
                return super.J_1907_R(p_230254_1_, p_230254_2_);
            }
        }
        if (!itemstack.n_1700_B()) {
            if (this.u_2550_I(itemstack)) {
                return this.J_1907_R(p_230254_1_, itemstack);
            }
            if (!this.o_4117_e()) {
                this.c_1608_O();
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            if (!this.V_1176_p() && itemstack.J_1907_R() == a_3742_W.L_1362_X.u_1723_Y()) {
                this.w_1457_N(true);
                this.J_3635_s();
                if (!p_230254_1_.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                }
                this.p_1458_L();
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
            if (!this.d_() && !this.G_564_y() && itemstack.J_1907_R() == Items.Z_361_l) {
                this.u_1723_Y(p_230254_1_);
                return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
            }
        }
        if (this.d_()) {
            return super.J_1907_R(p_230254_1_, p_230254_2_);
        }
        this.v_4262_N(p_230254_1_);
        return m_3054_I.n_1700_B(this.O_508_d.Y_259_p);
    }

    protected void J_3635_s() {
        this.n_1700_B(SoundEvents.b_2312_j, 1.0f, (this.RealmsWorldOptions.nextFloat() - this.RealmsWorldOptions.nextFloat()) * 0.2f + 1.0f);
    }

    public int V_537_k() {
        return 5;
    }
}



