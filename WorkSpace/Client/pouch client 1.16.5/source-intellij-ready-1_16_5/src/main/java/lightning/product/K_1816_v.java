/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.N_4498_h;
import lightning.product.NonNullList;
import lightning.product.Button;
import lightning.product.W_3491_f;
import lightning.product.Z_1993_T;
import lightning.product.a_2900_S;
import lightning.product.a_669_v;
import lightning.product.ContainerListener;
import lightning.product.i_1140_L;
import lightning.product.k_2603_m;
import lightning.product.CommonComponents;
import lightning.product.x_282_a;

public class K_1816_v
extends i_1140_L
implements N_4498_h<a_669_v> {
    private final a_669_v R_4764_Y;
    private final ContainerListener G_564_y = new ContainerListener(){

        @Override
        public void n_1700_B(a_2900_S containerToSend, NonNullList<Z_1993_T> itemsList) {
            K_1816_v.this.v_4262_N();
        }

        @Override
        public void n_1700_B(a_2900_S containerToSend, int slotInd, Z_1993_T stack) {
            K_1816_v.this.v_4262_N();
        }

        @Override
        public void n_1700_B(a_2900_S containerIn, int varToUpdate, int newValue) {
            if (varToUpdate == 0) {
                K_1816_v.this.w_1484_f();
            }
        }
    };

    public K_1816_v(a_669_v p_i51082_1_, W_3491_f p_i51082_2_, x_282_a p_i51082_3_) {
        this.R_4764_Y = p_i51082_1_;
    }

    public a_669_v n_1700_B() {
        return this.R_4764_Y;
    }

    @Override
    protected void init() {
        super.init();
        this.R_4764_Y.n_1700_B(this.G_564_y);
    }

    @Override
    public void closeScreen() {
        this.minecraft.Y_259_p.P_1922_E();
        super.closeScreen();
    }

    @Override
    public void onClose() {
        super.onClose();
        this.R_4764_Y.J_1907_R(this.G_564_y);
    }

    @Override
    protected void R_4764_Y() {
        if (this.minecraft.Y_259_p.V_537_k()) {
            this.addButton(new Button(this.width / 2 - 100, 196, 98, 20, CommonComponents.R_4764_Y, p_214181_1_ -> this.minecraft.n_1700_B((k_2603_m)null)));
            this.addButton(new Button(this.width / 2 + 2, 196, 98, 20, new F_2904_S("lectern.take_book"), p_214178_1_ -> this.R_4764_Y(3)));
        } else {
            super.R_4764_Y();
        }
    }

    @Override
    protected void G_564_y() {
        this.R_4764_Y(1);
    }

    @Override
    protected void P_1922_E() {
        this.R_4764_Y(2);
    }

    @Override
    protected boolean n_1700_B(int pageNum) {
        if (pageNum != this.R_4764_Y.J_1907_R()) {
            this.R_4764_Y(100 + pageNum);
            return true;
        }
        return false;
    }

    private void R_4764_Y(int p_214179_1_) {
        this.minecraft.w_1457_N.sendEnchantPacket(this.R_4764_Y.u_1723_Y, p_214179_1_);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void v_4262_N() {
        Z_1993_T itemstack = this.R_4764_Y.n_1700_B();
        this.n_1700_B(i_1140_L.n_1700_B.n_1700_B(itemstack));
    }

    private void w_1484_f() {
        this.J_1907_R(this.R_4764_Y.J_1907_R());
    }

    @Override
    public /* synthetic */ a_2900_S n_() {
        return this.n_1700_B();
    }
}


