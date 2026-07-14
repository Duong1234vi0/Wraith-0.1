/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSerializationContext
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import lightning.product.Serializer;
import lightning.product.I_2011_f;
import lightning.product.N_4263_v;
import lightning.product.LootItemCondition;
import lightning.product.Objective;
import lightning.product.i_4431_W;
import lightning.product.i_4895_l;
import lightning.product.o_3000_u;
import lightning.product.o_3393_s;
import lightning.product.q_1704_m;
import lightning.product.LootItemConditions;

public class C_2007_r
implements LootItemCondition {
    private final Map<String, o_3393_s> n_1700_B;
    private final q_1704_m.J_1907_R J_1907_R;

    private C_2007_r(Map<String, o_3393_s> scoreIn, q_1704_m.J_1907_R targetIn) {
        this.n_1700_B = ImmutableMap.copyOf(scoreIn);
        this.J_1907_R = targetIn;
    }

    @Override
    public o_3000_u J_1907_R() {
        return LootItemConditions.v_4262_N;
    }

    @Override
    public Set<I_2011_f<?>> n_1700_B() {
        return ImmutableSet.of(this.J_1907_R.n_1700_B());
    }

    public boolean n_1700_B(q_1704_m p_test_1_) {
        N_4263_v entity = p_test_1_.J_1907_R(this.J_1907_R.n_1700_B());
        if (entity == null) {
            return false;
        }
        i_4895_l scoreboard = entity.O_508_d.Q_4569_t();
        for (Map.Entry<String, o_3393_s> entry : this.n_1700_B.entrySet()) {
            if (this.n_1700_B(entity, scoreboard, entry.getKey(), entry.getValue())) continue;
            return false;
        }
        return true;
    }

    protected boolean n_1700_B(N_4263_v entityIn, i_4895_l scoreboardIn, String objectiveStr, o_3393_s rand) {
        Objective scoreobjective = scoreboardIn.R_4764_Y(objectiveStr);
        if (scoreobjective == null) {
            return false;
        }
        String s = entityIn.L_3570_A();
        return !scoreboardIn.n_1700_B(s, scoreobjective) ? false : rand.n_1700_B(scoreboardIn.J_1907_R(s, scoreobjective).J_1907_R());
    }

    @Override
    public /* synthetic */ boolean test(Object object) {
        return this.n_1700_B((q_1704_m)object);
    }

    public static class n_1700_B
    implements Serializer<C_2007_r> {
        @Override
        public void n_1700_B(JsonObject p_230424_1_, C_2007_r p_230424_2_, JsonSerializationContext p_230424_3_) {
            JsonObject jsonobject = new JsonObject();
            for (Map.Entry<String, o_3393_s> entry : p_230424_2_.n_1700_B.entrySet()) {
                jsonobject.add(entry.getKey(), p_230424_3_.serialize((Object)entry.getValue()));
            }
            p_230424_1_.add("scores", (JsonElement)jsonobject);
            p_230424_1_.add("entity", p_230424_3_.serialize((Object)p_230424_2_.J_1907_R));
        }

        public C_2007_r J_1907_R(JsonObject p_230423_1_, JsonDeserializationContext p_230423_2_) {
            Set set = i_4431_W.M_588_G(p_230423_1_, "scores").entrySet();
            LinkedHashMap map = Maps.newLinkedHashMap();
            for (Map.Entry entry : set) {
                map.put((String)entry.getKey(), i_4431_W.n_1700_B((JsonElement)entry.getValue(), "score", p_230423_2_, o_3393_s.class));
            }
            return new C_2007_r(map, i_4431_W.n_1700_B(p_230423_1_, "entity", p_230423_2_, q_1704_m.J_1907_R.class));
        }

        @Override
        public /* synthetic */ Object n_1700_B(JsonObject jsonObject, JsonDeserializationContext jsonDeserializationContext) {
            return this.J_1907_R(jsonObject, jsonDeserializationContext);
        }
    }
}


