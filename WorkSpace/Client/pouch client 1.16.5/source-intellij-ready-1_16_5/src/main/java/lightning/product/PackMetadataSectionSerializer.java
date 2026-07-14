/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import lightning.product.MutableComponent;
import lightning.product.T_335_n;
import lightning.product.PackMetadataSection;
import lightning.product.i_4431_W;
import lightning.product.x_282_a;

public class PackMetadataSectionSerializer
implements T_335_n<PackMetadataSection> {
    public PackMetadataSection n_1700_B(JsonObject json) {
        MutableComponent itextcomponent = x_282_a.n_1700_B.n_1700_B(json.get("description"));
        if (itextcomponent == null) {
            throw new JsonParseException("Invalid/missing description!");
        }
        int i = i_4431_W.u_2550_I(json, "pack_format");
        return new PackMetadataSection(itextcomponent, i);
    }

    @Override
    public String n_1700_B() {
        return "pack";
    }

    @Override
    public /* synthetic */ Object J_1907_R(JsonObject jsonObject) {
        return this.n_1700_B(jsonObject);
    }
}


