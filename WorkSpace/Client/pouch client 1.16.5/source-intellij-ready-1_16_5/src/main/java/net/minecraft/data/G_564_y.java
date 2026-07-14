/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package net.minecraft.data;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.function.Function;

public class G_564_y<T> {
    private final String n_1700_B;
    private final Function<T, JsonElement> J_1907_R;

    public G_564_y(String property, Function<T, JsonElement> value) {
        this.n_1700_B = property;
        this.J_1907_R = value;
    }

    public n_1700_B n_1700_B(T element) {
        return new n_1700_B(element);
    }

    public String toString() {
        return this.n_1700_B;
    }

    public class n_1700_B {
        private final T J_1907_R;

        public n_1700_B(T element) {
            this.J_1907_R = element;
        }

        public void n_1700_B(JsonObject json) {
            json.add(G_564_y.this.n_1700_B, G_564_y.this.J_1907_R.apply(this.J_1907_R));
        }

        public String toString() {
            return G_564_y.this.n_1700_B + "=" + String.valueOf(this.J_1907_R);
        }
    }
}

