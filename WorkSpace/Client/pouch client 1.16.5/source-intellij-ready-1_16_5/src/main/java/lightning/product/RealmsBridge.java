/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.MinecraftClient;
import lightning.product.RealmsScreen;
import lightning.product.i_2993_w;
import lightning.product.k_2603_m;
import lightning.product.r_715_M;

public class RealmsBridge
extends RealmsScreen {
    private k_2603_m n_1700_B;

    public void n_1700_B(k_2603_m p_231394_1_) {
        this.n_1700_B = p_231394_1_;
        MinecraftClient.A_4115_X().n_1700_B(new r_715_M(this));
    }

    @Nullable
    public RealmsScreen J_1907_R(k_2603_m p_239555_1_) {
        this.n_1700_B = p_239555_1_;
        return new i_2993_w();
    }

    @Override
    public void init() {
        MinecraftClient.A_4115_X().n_1700_B(this.n_1700_B);
    }
}



