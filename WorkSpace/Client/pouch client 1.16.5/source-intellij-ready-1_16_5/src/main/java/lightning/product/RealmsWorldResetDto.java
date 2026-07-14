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

public class RealmsWorldResetDto
extends ValueObject
implements c_1314_D {
    @SerializedName(value="seed")
    private final String n_1700_B;
    @SerializedName(value="worldTemplateId")
    private final long J_1907_R;
    @SerializedName(value="levelType")
    private final int R_4764_Y;
    @SerializedName(value="generateStructures")
    private final boolean G_564_y;

    public RealmsWorldResetDto(String p_i51640_1_, long p_i51640_2_, int p_i51640_4_, boolean p_i51640_5_) {
        this.n_1700_B = p_i51640_1_;
        this.J_1907_R = p_i51640_2_;
        this.R_4764_Y = p_i51640_4_;
        this.G_564_y = p_i51640_5_;
    }
}


