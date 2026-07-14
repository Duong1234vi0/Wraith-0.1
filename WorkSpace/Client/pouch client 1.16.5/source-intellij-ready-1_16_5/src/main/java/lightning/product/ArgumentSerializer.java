/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  com.mojang.brigadier.arguments.ArgumentType
 */
package lightning.product;

import com.google.gson.JsonObject;
import com.mojang.brigadier.arguments.ArgumentType;
import lightning.product.b_2585_i;

public interface ArgumentSerializer<T extends ArgumentType<?>> {
    public void n_1700_B(T var1, b_2585_i var2);

    public T n_1700_B(b_2585_i var1);

    public void n_1700_B(T var1, JsonObject var2);
}


