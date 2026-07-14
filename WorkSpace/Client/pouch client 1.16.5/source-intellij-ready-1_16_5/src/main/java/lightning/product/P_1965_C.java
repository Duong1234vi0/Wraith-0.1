/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.SerializationContext;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.h_1723_G;

public abstract class P_1965_C
implements h_1723_G {
    private final g_2336_b n_1700_B;
    private final b_1430_k.n_1700_B J_1907_R;

    public P_1965_C(g_2336_b criterion, b_1430_k.n_1700_B playerCondition) {
        this.n_1700_B = criterion;
        this.J_1907_R = playerCondition;
    }

    @Override
    public g_2336_b n_1700_B() {
        return this.n_1700_B;
    }

    protected b_1430_k.n_1700_B R_4764_Y() {
        return this.J_1907_R;
    }

    @Override
    public JsonObject n_1700_B(SerializationContext conditions) {
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("player", this.J_1907_R.n_1700_B(conditions));
        return jsonobject;
    }

    public String toString() {
        return "AbstractCriterionInstance{criterion=" + String.valueOf(this.n_1700_B) + "}";
    }
}


