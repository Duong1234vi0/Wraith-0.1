/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.H_2000_A;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.Merchant;
import lightning.product.MerchantOffers;
import lightning.product.MerchantOffer;

public class ClientSideMerchant
implements Merchant {
    private final H_2000_A n_1700_B;
    private final a_3913_L J_1907_R;
    private MerchantOffers R_4764_Y = new MerchantOffers();
    private int G_564_y;

    public ClientSideMerchant(a_3913_L player) {
        this.J_1907_R = player;
        this.n_1700_B = new H_2000_A(this);
    }

    @Override
    @Nullable
    public a_3913_L n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player) {
    }

    @Override
    public MerchantOffers J_1907_R() {
        return this.R_4764_Y;
    }

    @Override
    public void n_1700_B(@Nullable MerchantOffers offers) {
        this.R_4764_Y = offers;
    }

    @Override
    public void n_1700_B(MerchantOffer offer) {
        offer.s_956_w();
    }

    @Override
    public void n_1700_B(Z_1993_T stack) {
    }

    @Override
    public b_4507_u R_4764_Y() {
        return this.J_1907_R.O_508_d;
    }

    @Override
    public int G_564_y() {
        return this.G_564_y;
    }

    @Override
    public void n_1700_B(int xpIn) {
        this.G_564_y = xpIn;
    }

    @Override
    public boolean P_1922_E() {
        return true;
    }

    @Override
    public SoundEvent u_1723_Y() {
        return SoundEvents.LeavesBlock;
    }
}


