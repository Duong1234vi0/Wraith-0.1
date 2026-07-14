/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.annotations.SerializedName
 */
package lightning.product;

import com.google.gson.annotations.SerializedName;
import java.util.Locale;
import lightning.product.ValueObject;
import lightning.product.c_1314_D;

public class RegionPingResult
extends ValueObject
implements c_1314_D {
    @SerializedName(value="regionName")
    private final String n_1700_B;
    @SerializedName(value="ping")
    private final int J_1907_R;

    public RegionPingResult(String entityRendererIn, int mcIn) {
        this.n_1700_B = entityRendererIn;
        this.J_1907_R = mcIn;
    }

    public int n_1700_B() {
        return this.J_1907_R;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%s --> %.2f ms", this.n_1700_B, Float.valueOf(this.J_1907_R));
    }
}


