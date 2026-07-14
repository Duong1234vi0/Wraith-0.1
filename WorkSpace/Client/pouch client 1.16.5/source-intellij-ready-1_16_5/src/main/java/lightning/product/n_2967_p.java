/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  org.apache.commons.lang3.ArrayUtils
 *  org.apache.commons.lang3.mutable.MutableInt
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import lightning.product.A_2178_U;
import lightning.product.A_3895_D;
import lightning.product.LootItemFunctions;
import lightning.product.LootPoolEntry;
import lightning.product.LootItemCondition;
import lightning.product.Y_4400_R;
import lightning.product.Z_1993_T;
import lightning.product.Z_3128_E;
import lightning.product.g_1866_m;
import lightning.product.i_4431_W;
import lightning.product.RandomIntGenerator;
import lightning.product.o_3393_s;
import lightning.product.q_1704_m;
import lightning.product.u_1373_N;
import lightning.product.u_530_F;
import lightning.product.LootItemConditions;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.mutable.MutableInt;

public class n_2967_p {
    private final u_1373_N[] n_1700_B;
    private final LootItemCondition[] J_1907_R;
    private final Predicate<q_1704_m> R_4764_Y;
    private final A_2178_U[] G_564_y;
    private final BiFunction<Z_1993_T, q_1704_m, Z_1993_T> P_1922_E;
    private final RandomIntGenerator u_1723_Y;
    private final o_3393_s v_4262_N;

    private n_2967_p(u_1373_N[] p_i51268_1_, LootItemCondition[] p_i51268_2_, A_2178_U[] p_i51268_3_, RandomIntGenerator p_i51268_4_, o_3393_s p_i51268_5_) {
        this.n_1700_B = p_i51268_1_;
        this.J_1907_R = p_i51268_2_;
        this.R_4764_Y = LootItemConditions.n_1700_B(p_i51268_2_);
        this.G_564_y = p_i51268_3_;
        this.P_1922_E = LootItemFunctions.n_1700_B(p_i51268_3_);
        this.u_1723_Y = p_i51268_4_;
        this.v_4262_N = p_i51268_5_;
    }

    private void J_1907_R(Consumer<Z_1993_T> p_216095_1_, q_1704_m p_216095_2_) {
        Random random = p_216095_2_.n_1700_B();
        ArrayList list = Lists.newArrayList();
        MutableInt mutableint = new MutableInt();
        for (u_1373_N lootentry : this.n_1700_B) {
            lootentry.expand(p_216095_2_, p_216097_3_ -> {
                int k = p_216097_3_.n_1700_B(p_216095_2_.J_1907_R());
                if (k > 0) {
                    list.add(p_216097_3_);
                    mutableint.add(k);
                }
            });
        }
        int i = list.size();
        if (mutableint.intValue() != 0 && i != 0) {
            if (i == 1) {
                ((LootPoolEntry)list.get(0)).n_1700_B(p_216095_1_, p_216095_2_);
            } else {
                int j = random.nextInt(mutableint.intValue());
                for (LootPoolEntry ilootgenerator : list) {
                    if ((j -= ilootgenerator.n_1700_B(p_216095_2_.J_1907_R())) >= 0) continue;
                    ilootgenerator.n_1700_B(p_216095_1_, p_216095_2_);
                    return;
                }
            }
        }
    }

    public void n_1700_B(Consumer<Z_1993_T> p_216091_1_, q_1704_m p_216091_2_) {
        if (this.R_4764_Y.test(p_216091_2_)) {
            Consumer<Z_1993_T> consumer = A_2178_U.n_1700_B(this.P_1922_E, p_216091_1_, p_216091_2_);
            Random random = p_216091_2_.n_1700_B();
            int i = this.u_1723_Y.n_1700_B(random) + u_530_F.G_564_y(this.v_4262_N.J_1907_R(random) * p_216091_2_.J_1907_R());
            for (int j = 0; j < i; ++j) {
                this.J_1907_R(consumer, p_216091_2_);
            }
        }
    }

    public void n_1700_B(g_1866_m p_227505_1_) {
        for (int i = 0; i < this.J_1907_R.length; ++i) {
            this.J_1907_R[i].n_1700_B(p_227505_1_.J_1907_R(".condition[" + i + "]"));
        }
        for (int j = 0; j < this.G_564_y.length; ++j) {
            this.G_564_y[j].n_1700_B(p_227505_1_.J_1907_R(".functions[" + j + "]"));
        }
        for (int k = 0; k < this.n_1700_B.length; ++k) {
            this.n_1700_B[k].n_1700_B(p_227505_1_.J_1907_R(".entries[" + k + "]"));
        }
    }

    public static n_1700_B n_1700_B() {
        return new n_1700_B();
    }

    public static class n_1700_B
    implements A_3895_D<n_1700_B>,
    Z_3128_E<n_1700_B> {
        private final List<u_1373_N> n_1700_B = Lists.newArrayList();
        private final List<LootItemCondition> J_1907_R = Lists.newArrayList();
        private final List<A_2178_U> R_4764_Y = Lists.newArrayList();
        private RandomIntGenerator G_564_y = new o_3393_s(1.0f);
        private o_3393_s P_1922_E = new o_3393_s(0.0f, 0.0f);

        public n_1700_B n_1700_B(RandomIntGenerator rollsIn) {
            this.G_564_y = rollsIn;
            return this;
        }

        public n_1700_B n_1700_B() {
            return this;
        }

        public n_1700_B n_1700_B(u_1373_N.n_1700_B<?> entriesBuilder) {
            this.n_1700_B.add(entriesBuilder.J_1907_R());
            return this;
        }

        public n_1700_B J_1907_R(LootItemCondition.n_1700_B conditionBuilder) {
            this.J_1907_R.add(conditionBuilder.build());
            return this;
        }

        public n_1700_B J_1907_R(A_2178_U.n_1700_B functionBuilder) {
            this.R_4764_Y.add(functionBuilder.u_1723_Y());
            return this;
        }

        public n_2967_p J_1907_R() {
            if (this.G_564_y == null) {
                throw new IllegalArgumentException("Rolls not set");
            }
            return new n_2967_p(this.n_1700_B.toArray(new u_1373_N[0]), this.J_1907_R.toArray(new LootItemCondition[0]), this.R_4764_Y.toArray(new A_2178_U[0]), this.G_564_y, this.P_1922_E);
        }

        @Override
        public /* synthetic */ Object G_564_y() {
            return this.n_1700_B();
        }

        @Override
        public /* synthetic */ Object n_1700_B(A_2178_U.n_1700_B n_1700_B2) {
            return this.J_1907_R(n_1700_B2);
        }

        @Override
        public /* synthetic */ Object n_1700_B(LootItemCondition.n_1700_B n_1700_B2) {
            return this.J_1907_R(n_1700_B2);
        }
    }

    public static class J_1907_R
    implements JsonDeserializer<n_2967_p>,
    JsonSerializer<n_2967_p> {
        public n_2967_p n_1700_B(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
            JsonObject jsonobject = i_4431_W.w_1484_f(p_deserialize_1_, "loot pool");
            u_1373_N[] alootentry = i_4431_W.n_1700_B(jsonobject, "entries", p_deserialize_3_, u_1373_N[].class);
            LootItemCondition[] ailootcondition = i_4431_W.n_1700_B(jsonobject, "conditions", new LootItemCondition[0], p_deserialize_3_, LootItemCondition[].class);
            A_2178_U[] ailootfunction = i_4431_W.n_1700_B(jsonobject, "functions", new A_2178_U[0], p_deserialize_3_, A_2178_U[].class);
            RandomIntGenerator irandomrange = Y_4400_R.n_1700_B(jsonobject.get("rolls"), p_deserialize_3_);
            o_3393_s randomvaluerange = i_4431_W.n_1700_B(jsonobject, "bonus_rolls", new o_3393_s(0.0f, 0.0f), p_deserialize_3_, o_3393_s.class);
            return new n_2967_p(alootentry, ailootcondition, ailootfunction, irandomrange, randomvaluerange);
        }

        public JsonElement n_1700_B(n_2967_p p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
            JsonObject jsonobject = new JsonObject();
            jsonobject.add("rolls", Y_4400_R.n_1700_B(p_serialize_1_.u_1723_Y, p_serialize_3_));
            jsonobject.add("entries", p_serialize_3_.serialize((Object)p_serialize_1_.n_1700_B));
            if (p_serialize_1_.v_4262_N.J_1907_R() != 0.0f && p_serialize_1_.v_4262_N.R_4764_Y() != 0.0f) {
                jsonobject.add("bonus_rolls", p_serialize_3_.serialize((Object)p_serialize_1_.v_4262_N));
            }
            if (!ArrayUtils.isEmpty((Object[])p_serialize_1_.J_1907_R)) {
                jsonobject.add("conditions", p_serialize_3_.serialize((Object)p_serialize_1_.J_1907_R));
            }
            if (!ArrayUtils.isEmpty((Object[])p_serialize_1_.G_564_y)) {
                jsonobject.add("functions", p_serialize_3_.serialize((Object)p_serialize_1_.G_564_y));
            }
            return jsonobject;
        }

        public /* synthetic */ Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return this.n_1700_B(jsonElement, type, jsonDeserializationContext);
        }

        public /* synthetic */ JsonElement serialize(Object object, Type type, JsonSerializationContext jsonSerializationContext) {
            return this.n_1700_B((n_2967_p)object, type, jsonSerializationContext);
        }
    }
}


