/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import lightning.product.LootContextParams;
import lightning.product.B_368_w;
import lightning.product.Serializer;
import lightning.product.LootItemCondition;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.i_4431_W;
import lightning.product.o_3000_u;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class d_1384_D
implements LootItemCondition {
    private final B_368_w n_1700_B;
    private final c_1514_x J_1907_R;

    private d_1384_D(B_368_w p_i225895_1_, c_1514_x p_i225895_2_) {
        this.n_1700_B = p_i225895_1_;
        this.J_1907_R = p_i225895_2_;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.P_4830_p;
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        e_2866_D vector3d = p_test_1_.J_1907_R(LootContextParams.u_1723_Y);
        return vector3d != null && this.n_1700_B.n_1700_B(p_test_1_.R_4764_Y(), vector3d.n_1700_B() + (double)this.J_1907_R.getX(), vector3d.J_1907_R() + (double)this.J_1907_R.getY(), vector3d.R_4764_Y() + (double)this.J_1907_R.getZ());
    }

    public static LootItemCondition.n_1700_B n_1700_B(B_368_w.n_1700_B p_215975_0_) {
        return () -> new d_1384_D(p_215975_0_.J_1907_R(), c_1514_x.ZERO);
    }

    public static LootItemCondition.n_1700_B n_1700_B(B_368_w.n_1700_B p_241547_0_, c_1514_x p_241547_1_) {
        return () -> new d_1384_D(p_241547_0_.J_1907_R(), p_241547_1_);
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<d_1384_D> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, d_1384_D p_230424_2_, JsonSerializationContext p_230424_3_) {
            p_230424_1_.add("predicate", p_230424_2_.n_1700_B.n_1700_B());
            if (p_230424_2_.J_1907_R.getX() != 0) {
                p_230424_1_.addProperty("offsetX", (Number)p_230424_2_.J_1907_R.getX());
            }
            if (p_230424_2_.J_1907_R.getY() != 0) {
                p_230424_1_.addProperty("offsetY", (Number)p_230424_2_.J_1907_R.getY());
            }
            if (p_230424_2_.J_1907_R.getZ() != 0) {
                p_230424_1_.addProperty("offsetZ", (Number)p_230424_2_.J_1907_R.getZ());
            }
        }

        public d_1384_D J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            B_368_w locationpredicate = B_368_w.n_1700_B(p_230423_1_.get("predicate"));
            int i = i_4431_W.n_1700_B(p_230423_1_, "offsetX", 0);
            int j = i_4431_W.n_1700_B(p_230423_1_, "offsetY", 0);
            int k = i_4431_W.n_1700_B(p_230423_1_, "offsetZ", 0);
            return new d_1384_D(locationpredicate, new c_1514_x(i, j, k));
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


