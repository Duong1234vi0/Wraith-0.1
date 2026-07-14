/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package net.minecraft.data;

import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;

public interface C_2741_M {
    public void n_1700_B(JsonObject var1);

    default public JsonObject P_1922_E() {
        JsonObject jsonobject = new JsonObject();
        jsonobject.addProperty("type", V_3137_a.s_2632_s.J_1907_R(this.n_1700_B()).toString());
        this.n_1700_B(jsonobject);
        return jsonobject;
    }

    public g_2336_b J_1907_R();

    public RecipeSerializer<?> n_1700_B();

    @Nullable
    public JsonObject R_4764_Y();

    @Nullable
    public g_2336_b G_564_y();
}


