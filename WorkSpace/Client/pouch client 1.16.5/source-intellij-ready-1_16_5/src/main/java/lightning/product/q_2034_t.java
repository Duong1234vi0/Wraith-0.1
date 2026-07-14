/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package lightning.product;

import com.google.gson.JsonObject;
import lightning.product.SerializationContext;
import lightning.product.CriterionTrigger;
import lightning.product.S_4998_h;
import lightning.product.g_2336_b;
import lightning.product.h_1723_G;
import lightning.product.DeserializationContext;

public class q_2034_t
implements CriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("impossible");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    @Override
    public void n_1700_B(S_4998_h playerAdvancementsIn, CriterionTrigger.n_1700_B<n_1700_B> listener) {
    }

    @Override
    public void J_1907_R(S_4998_h playerAdvancementsIn, CriterionTrigger.n_1700_B<n_1700_B> listener) {
    }

    @Override
    public void n_1700_B(S_4998_h playerAdvancementsIn) {
    }

    public n_1700_B J_1907_R(JsonObject object, DeserializationContext conditions) {
        return new n_1700_B();
    }

    @Override
    public /* synthetic */ h_1723_G n_1700_B(JsonObject jsonObject, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, u_4771_O2);
    }

    public static class n_1700_B
    implements h_1723_G {
        @Override
        public g_2336_b n_1700_B() {
            return n_1700_B;
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            return new JsonObject();
        }
    }
}


