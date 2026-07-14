/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Container;
import lightning.product.Slot;
import lightning.product.Z_1993_T;
import lightning.product.k_902_f;
import lightning.product.Items;

public class FurnaceFuelSlot
extends Slot {
    private final k_902_f n_1700_B;

    public FurnaceFuelSlot(k_902_f p_i50084_1_, Container p_i50084_2_, int p_i50084_3_, int p_i50084_4_, int p_i50084_5_) {
        super(p_i50084_2_, p_i50084_3_, p_i50084_4_, p_i50084_5_);
        this.n_1700_B = p_i50084_1_;
    }

    @Override
    public boolean n_1700_B(Z_1993_T stack) {
        return this.n_1700_B.J_1907_R(stack) || FurnaceFuelSlot.P_1922_E(stack);
    }

    @Override
    public int R_4764_Y(Z_1993_T stack) {
        return FurnaceFuelSlot.P_1922_E(stack) ? 1 : super.R_4764_Y(stack);
    }

    public static boolean P_1922_E(Z_1993_T stack) {
        return stack.J_1907_R() == Items.G_1539_D;
    }
}


