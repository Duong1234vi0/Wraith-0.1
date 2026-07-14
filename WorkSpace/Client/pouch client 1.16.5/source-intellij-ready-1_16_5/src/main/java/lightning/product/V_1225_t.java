/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package lightning.product;

import com.google.gson.annotations.SerializedName;
import lightning.product.ValueObject;
import lightning.product.c_1314_D;

public class V_1225_t
extends ValueObject
implements c_1314_D {
    @SerializedName(value="name")
    public String n_1700_B;
    @SerializedName(value="description")
    public String J_1907_R;

    public V_1225_t(String p_i51655_1_, String p_i51655_2_) {
        this.n_1700_B = p_i51655_1_;
        this.J_1907_R = p_i51655_2_;
    }
}


