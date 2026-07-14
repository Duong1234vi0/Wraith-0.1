/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.T_335_n;
import lightning.product.i_4431_W;
import lightning.product.TextureMetadataSection;

public class TextureMetadataSectionSerializer
implements T_335_n<TextureMetadataSection> {
    public TextureMetadataSection n_1700_B(JsonObject json) {
        boolean flag = i_4431_W.n_1700_B(json, "blur", false);
        boolean flag1 = i_4431_W.n_1700_B(json, "clamp", false);
        return new TextureMetadataSection(flag, flag1);
    }

    @Override
    public String n_1700_B() {
        return "texture";
    }

    @Override
    public /* synthetic */ Object J_1907_R(JsonObject jsonObject) {
        return this.n_1700_B(jsonObject);
    }
}


