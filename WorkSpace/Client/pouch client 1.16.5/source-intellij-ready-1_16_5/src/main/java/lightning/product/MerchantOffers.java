/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.ArrayList;
import javax.annotation.Nullable;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.q_2896_o;
import lightning.product.MerchantOffer;

public class MerchantOffers
extends ArrayList<MerchantOffer> {
    public MerchantOffers() {
    }

    public MerchantOffers(U_2912_j nbt) {
        q_2896_o listnbt = nbt.G_564_y("Recipes", 10);
        for (int i = 0; i < listnbt.size(); ++i) {
            this.add(new MerchantOffer(listnbt.n_1700_B(i)));
        }
    }

    @Nullable
    public MerchantOffer n_1700_B(Z_1993_T p_222197_1_, Z_1993_T p_222197_2_, int recipeIndex) {
        if (recipeIndex > 0 && recipeIndex < this.size()) {
            MerchantOffer merchantoffer1 = (MerchantOffer)this.get(recipeIndex);
            return merchantoffer1.n_1700_B(p_222197_1_, p_222197_2_) ? merchantoffer1 : null;
        }
        for (int i = 0; i < this.size(); ++i) {
            MerchantOffer merchantoffer = (MerchantOffer)this.get(i);
            if (!merchantoffer.n_1700_B(p_222197_1_, p_222197_2_)) continue;
            return merchantoffer;
        }
        return null;
    }

    public void n_1700_B(b_2585_i buffer) {
        buffer.writeByte((byte)(this.size() & 0xFF));
        for (int i = 0; i < this.size(); ++i) {
            MerchantOffer merchantoffer = (MerchantOffer)this.get(i);
            buffer.n_1700_B(merchantoffer.n_1700_B());
            buffer.n_1700_B(merchantoffer.G_564_y());
            Z_1993_T itemstack = merchantoffer.R_4764_Y();
            buffer.writeBoolean(!itemstack.n_1700_B());
            if (!itemstack.n_1700_B()) {
                buffer.n_1700_B(itemstack);
            }
            buffer.writeBoolean(merchantoffer.M_182_A());
            buffer.writeInt(merchantoffer.v_4262_N());
            buffer.writeInt(merchantoffer.t_148_a());
            buffer.writeInt(merchantoffer.Q_4569_t());
            buffer.writeInt(merchantoffer.P_4830_p());
            buffer.writeFloat(merchantoffer.h_1847_R());
            buffer.writeInt(merchantoffer.u_2550_I());
        }
    }

    public static MerchantOffers J_1907_R(b_2585_i buffer) {
        MerchantOffers merchantoffers = new MerchantOffers();
        int i = buffer.readByte() & 0xFF;
        for (int j = 0; j < i; ++j) {
            Z_1993_T itemstack = buffer.u_2550_I();
            Z_1993_T itemstack1 = buffer.u_2550_I();
            Z_1993_T itemstack2 = Z_1993_T.J_1907_R;
            if (buffer.readBoolean()) {
                itemstack2 = buffer.u_2550_I();
            }
            boolean flag = buffer.readBoolean();
            int k = buffer.readInt();
            int l = buffer.readInt();
            int i1 = buffer.readInt();
            int j1 = buffer.readInt();
            float f = buffer.readFloat();
            int k1 = buffer.readInt();
            MerchantOffer merchantoffer = new MerchantOffer(itemstack, itemstack2, itemstack1, k, l, i1, f, k1);
            if (flag) {
                merchantoffer.t_1786_h();
            }
            merchantoffer.J_1907_R(j1);
            merchantoffers.add(merchantoffer);
        }
        return merchantoffers;
    }

    public U_2912_j n_1700_B() {
        U_2912_j compoundnbt = new U_2912_j();
        q_2896_o listnbt = new q_2896_o();
        for (int i = 0; i < this.size(); ++i) {
            MerchantOffer merchantoffer = (MerchantOffer)this.get(i);
            listnbt.add(merchantoffer.Y_601_j());
        }
        compoundnbt.n_1700_B("Recipes", listnbt);
        return compoundnbt;
    }
}


