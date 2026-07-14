/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package net.minecraft.data;

import com.google.gson.JsonObject;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.SimpleRecipeSerializer;
import lightning.product.g_2336_b;
import lightning.product.RecipeSerializer;
import net.minecraft.data.C_2741_M;

public class h_1847_R {
    private final SimpleRecipeSerializer<?> n_1700_B;

    public h_1847_R(SimpleRecipeSerializer<?> p_i50786_1_) {
        this.n_1700_B = p_i50786_1_;
    }

    public static h_1847_R n_1700_B(SimpleRecipeSerializer<?> p_218656_0_) {
        return new h_1847_R(p_218656_0_);
    }

    public void n_1700_B(Consumer<C_2741_M> consumerIn, final String id) {
        consumerIn.accept(new C_2741_M(){

            @Override
            public void n_1700_B(JsonObject json) {
            }

            @Override
            public RecipeSerializer<?> n_1700_B() {
                return h_1847_R.this.n_1700_B;
            }

            @Override
            public g_2336_b J_1907_R() {
                return new g_2336_b(id);
            }

            @Override
            @Nullable
            public JsonObject R_4764_Y() {
                return null;
            }

            @Override
            public g_2336_b G_564_y() {
                return new g_2336_b("");
            }
        });
    }
}


