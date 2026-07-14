/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.annotations.SerializedName
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import lightning.product.ValueObject;
import lightning.product.RegionPingResult;
import lightning.product.c_1314_D;

public class V_1446_Y
extends ValueObject
implements c_1314_D {
    @SerializedName(value="pingResults")
    public List<RegionPingResult> n_1700_B = Lists.newArrayList();
    @SerializedName(value="worldIds")
    public List<Long> J_1907_R = Lists.newArrayList();
}


