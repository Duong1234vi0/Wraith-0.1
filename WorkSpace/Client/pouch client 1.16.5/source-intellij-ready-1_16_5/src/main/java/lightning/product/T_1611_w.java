/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import lightning.product.Serializer;
import lightning.product.LootItemCondition;
import lightning.product.g_1866_m;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class T_1611_w
implements LootItemCondition {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final g_2336_b J_1907_R;

    private T_1611_w(g_2336_b p_i225894_1_) {
        this.J_1907_R = p_i225894_1_;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.Q_4569_t;
    }

    @Override
    public void n_1700_B(g_1866_m p_225580_1_) {
        if (p_225580_1_.J_1907_R(this.J_1907_R)) {
            p_225580_1_.n_1700_B("Condition " + String.valueOf(this.J_1907_R) + " is recursively called");
        } else {
            LootItemCondition.super.n_1700_B(p_225580_1_);
            LootItemCondition ilootcondition = p_225580_1_.G_564_y(this.J_1907_R);
            if (ilootcondition == null) {
                p_225580_1_.n_1700_B("Unknown condition table called " + String.valueOf(this.J_1907_R));
            } else {
                ilootcondition.n_1700_B(p_225580_1_.n_1700_B(".{" + String.valueOf(this.J_1907_R) + "}", this.J_1907_R));
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public boolean n_1700_B(q_1704_m p_test_1_) {
        LootItemCondition ilootcondition = p_test_1_.J_1907_R(this.J_1907_R);
        if (p_test_1_.n_1700_B(ilootcondition)) {
            boolean flag;
            try {
                flag = ilootcondition.test(p_test_1_);
            }
            finally {
                p_test_1_.J_1907_R(ilootcondition);
            }
            return flag;
        }
        n_1700_B.warn("Detected infinite loop in loot tables");
        return false;
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<T_1611_w> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, T_1611_w p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.addProperty("name", p_230424_2_.J_1907_R.toString());
        }

        public T_1611_w J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(p_230423_1_, "name"));
            return new T_1611_w(resourcelocation);
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


