/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.OptionalInt;
import javax.annotation.Nullable;
import lightning.product.K_3710_b;
import lightning.product.SoundEvent;
import lightning.product.Z_1993_T;
import lightning.product.SimpleMenuProvider;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.MerchantOffers;
import lightning.product.MerchantOffer;
import lightning.product.x_282_a;

public interface Merchant {
    public void n_1700_B(@Nullable a_3913_L var1);

    @Nullable
    public a_3913_L n_1700_B();

    public MerchantOffers J_1907_R();

    public void n_1700_B(@Nullable MerchantOffers var1);

    public void n_1700_B(MerchantOffer var1);

    public void n_1700_B(Z_1993_T var1);

    public b_4507_u R_4764_Y();

    public int G_564_y();

    public void n_1700_B(int var1);

    public boolean P_1922_E();

    public SoundEvent u_1723_Y();

    default public boolean v_4262_N() {
        return false;
    }

    default public void n_1700_B(a_3913_L player, x_282_a displayName, int level) {
        MerchantOffers merchantoffers;
        OptionalInt optionalint = player.n_1700_B(new SimpleMenuProvider((id, playerInventory, player2) -> new K_3710_b(id, playerInventory, this), displayName));
        if (optionalint.isPresent() && !(merchantoffers = this.J_1907_R()).isEmpty()) {
            player.n_1700_B(optionalint.getAsInt(), merchantoffers, level, this.G_564_y(), this.P_1922_E(), this.v_4262_N());
        }
    }
}


