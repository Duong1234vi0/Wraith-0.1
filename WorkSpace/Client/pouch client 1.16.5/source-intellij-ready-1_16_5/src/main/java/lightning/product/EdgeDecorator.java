/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.serialization.Codec
 */
package lightning.product;

import com.mojang.serialization.Codec;
import lightning.product.P_1781_m;
import lightning.product.y_2419_Z;
import lightning.product.z_2963_s;

public abstract class EdgeDecorator<DC extends P_1781_m>
extends y_2419_Z<DC> {
    public EdgeDecorator(Codec<DC> p_i242024_1_) {
        super(p_i242024_1_);
    }

    protected abstract z_2963_s.n_1700_B n_1700_B(DC var1);
}


