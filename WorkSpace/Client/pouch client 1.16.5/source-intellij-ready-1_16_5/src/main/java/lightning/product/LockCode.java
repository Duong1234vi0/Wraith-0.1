/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.concurrent.Immutable
 */
package lightning.product;

import javax.annotation.concurrent.Immutable;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;

@Immutable
public class LockCode {
    public static final LockCode n_1700_B = new LockCode("");
    private final String J_1907_R;

    public LockCode(String code) {
        this.J_1907_R = code;
    }

    public boolean n_1700_B(Z_1993_T p_219964_1_) {
        return this.J_1907_R.isEmpty() || !p_219964_1_.n_1700_B() && p_219964_1_.Y_601_j() && this.J_1907_R.equals(p_219964_1_.multiplayerClientSuggestionProvider().getString());
    }

    public void n_1700_B(U_2912_j nbt) {
        if (!this.J_1907_R.isEmpty()) {
            nbt.n_1700_B("Lock", this.J_1907_R);
        }
    }

    public static LockCode J_1907_R(U_2912_j nbt) {
        return nbt.R_4764_Y("Lock", 8) ? new LockCode(nbt.M_588_G("Lock")) : n_1700_B;
    }
}


