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
import java.util.function.Supplier;
import lightning.product.b_2585_i;
import lightning.product.ArgumentSerializer;

public class EmptyArgumentSerializer<T extends ArgumentType<?>>
implements ArgumentSerializer<T> {
    private final Supplier<T> n_1700_B;

    public EmptyArgumentSerializer(Supplier<T> factory) {
        this.n_1700_B = factory;
    }

    @Override
    public void n_1700_B(T argument, b_2585_i buffer) {
    }

    @Override
    public T n_1700_B(b_2585_i buffer) {
        return (T)((ArgumentType)this.n_1700_B.get());
    }

    @Override
    public void n_1700_B(T p_212244_1_, JsonObject p_212244_2_) {
    }
}


