/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonObject;
import javax.annotation.Nullable;

public abstract class StoredUserEntry<T> {
    @Nullable
    private final T n_1700_B;

    public StoredUserEntry(@Nullable T valueIn) {
        this.n_1700_B = valueIn;
    }

    @Nullable
    T u_1723_Y() {
        return this.n_1700_B;
    }

    boolean P_1922_E() {
        return false;
    }

    protected abstract void n_1700_B(JsonObject var1);
}


