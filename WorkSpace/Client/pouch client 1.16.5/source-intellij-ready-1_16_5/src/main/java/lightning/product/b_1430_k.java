/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.A_2487_t;
import lightning.product.LootContextParams;
import lightning.product.B_368_w;
import lightning.product.B_4088_l;
import lightning.product.D_3640_k;
import lightning.product.SerializationContext;
import lightning.product.K_550_M;
import lightning.product.N_4263_v;
import lightning.product.LootItemCondition;
import lightning.product.W_2672_e;
import lightning.product.Z_530_i;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.f_1402_I;
import lightning.product.g_2336_b;
import lightning.product.h_2396_v;
import lightning.product.i_4431_W;
import lightning.product.k_200_a;
import lightning.product.n_3115_n;
import lightning.product.o_3050_h;
import lightning.product.o_3456_E;
import lightning.product.q_1704_m;
import lightning.product.r_109_r;
import lightning.product.FishingHookPredicate;
import lightning.product.t_5_h;
import lightning.product.DeserializationContext;
import lightning.product.LootItemConditions;
import lightning.product.y_2836_h;

public class b_1430_k {
    public static final b_1430_k n_1700_B = new b_1430_k(n_3115_n.n_1700_B, o_3456_E.n_1700_B, B_368_w.n_1700_B, y_2836_h.n_1700_B, h_2396_v.n_1700_B, D_3640_k.n_1700_B, k_200_a.n_1700_B, A_2487_t.n_1700_B, FishingHookPredicate.n_1700_B, null, null);
    private final n_3115_n J_1907_R;
    private final o_3456_E R_4764_Y;
    private final B_368_w G_564_y;
    private final y_2836_h P_1922_E;
    private final h_2396_v u_1723_Y;
    private final D_3640_k v_4262_N;
    private final k_200_a w_1484_f;
    private final A_2487_t t_148_a;
    private final FishingHookPredicate s_956_w;
    private final b_1430_k u_2550_I;
    private final b_1430_k M_588_G;
    @Nullable
    private final String P_4830_p;
    @Nullable
    private final g_2336_b h_1847_R;

    private b_1430_k(n_3115_n type, o_3456_E distance, B_368_w location, y_2836_h effects, h_2396_v nbt, D_3640_k flags, k_200_a equipment, A_2487_t player, FishingHookPredicate fishingCondition, @Nullable String team, @Nullable g_2336_b catType) {
        this.J_1907_R = type;
        this.R_4764_Y = distance;
        this.G_564_y = location;
        this.P_1922_E = effects;
        this.u_1723_Y = nbt;
        this.v_4262_N = flags;
        this.w_1484_f = equipment;
        this.t_148_a = player;
        this.s_956_w = fishingCondition;
        this.u_2550_I = this;
        this.M_588_G = this;
        this.P_4830_p = team;
        this.h_1847_R = catType;
    }

    private b_1430_k(n_3115_n type, o_3456_E distance, B_368_w location, y_2836_h effects, h_2396_v nbt, D_3640_k flags, k_200_a equipment, A_2487_t player, FishingHookPredicate fishingCondition, b_1430_k mountCondition, b_1430_k targetCondition, @Nullable String team, @Nullable g_2336_b catType) {
        this.J_1907_R = type;
        this.R_4764_Y = distance;
        this.G_564_y = location;
        this.P_1922_E = effects;
        this.u_1723_Y = nbt;
        this.v_4262_N = flags;
        this.w_1484_f = equipment;
        this.t_148_a = player;
        this.s_956_w = fishingCondition;
        this.u_2550_I = mountCondition;
        this.M_588_G = targetCondition;
        this.P_4830_p = team;
        this.h_1847_R = catType;
    }

    public boolean n_1700_B(B_4088_l player, @Nullable N_4263_v entity) {
        return this.n_1700_B(player.c_3005_b(), player.s_4990_V(), entity);
    }

    public boolean n_1700_B(e_3591_l world, @Nullable e_2866_D vector, @Nullable N_4263_v entity) {
        o_3050_h team;
        if (this == n_1700_B) {
            return true;
        }
        if (entity == null) {
            return false;
        }
        if (!this.J_1907_R.n_1700_B(entity.f_4016_n())) {
            return false;
        }
        if (vector == null ? this.R_4764_Y != o_3456_E.n_1700_B : !this.R_4764_Y.n_1700_B(vector.J_1907_R, vector.R_4764_Y, vector.G_564_y, entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k())) {
            return false;
        }
        if (!this.G_564_y.n_1700_B(world, entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k())) {
            return false;
        }
        if (!this.P_1922_E.n_1700_B(entity)) {
            return false;
        }
        if (!this.u_1723_Y.n_1700_B(entity)) {
            return false;
        }
        if (!this.v_4262_N.n_1700_B(entity)) {
            return false;
        }
        if (!this.w_1484_f.n_1700_B(entity)) {
            return false;
        }
        if (!this.t_148_a.n_1700_B(entity)) {
            return false;
        }
        if (!this.s_956_w.n_1700_B(entity)) {
            return false;
        }
        if (!this.u_2550_I.n_1700_B(world, vector, entity.l_3609_d())) {
            return false;
        }
        if (!this.M_588_G.n_1700_B(world, vector, entity instanceof Z_530_i ? ((Z_530_i)entity).t_148_a() : null)) {
            return false;
        }
        if (!(this.P_4830_p == null || (team = entity.L_1362_X()) != null && this.P_4830_p.equals(team.n_1700_B()))) {
            return false;
        }
        return this.h_1847_R == null || entity instanceof K_550_M && ((K_550_M)entity).y_4642_Y().equals(this.h_1847_R);
    }

    public static b_1430_k n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "entity");
            n_3115_n entitytypepredicate = n_3115_n.n_1700_B(jsonobject.get("type"));
            o_3456_E distancepredicate = o_3456_E.n_1700_B(jsonobject.get("distance"));
            B_368_w locationpredicate = B_368_w.n_1700_B(jsonobject.get("location"));
            y_2836_h mobeffectspredicate = y_2836_h.n_1700_B(jsonobject.get("effects"));
            h_2396_v nbtpredicate = h_2396_v.n_1700_B(jsonobject.get("nbt"));
            D_3640_k entityflagspredicate = D_3640_k.n_1700_B(jsonobject.get("flags"));
            k_200_a entityequipmentpredicate = k_200_a.n_1700_B(jsonobject.get("equipment"));
            A_2487_t playerpredicate = A_2487_t.n_1700_B(jsonobject.get("player"));
            FishingHookPredicate fishingpredicate = FishingHookPredicate.n_1700_B(jsonobject.get("fishing_hook"));
            b_1430_k entitypredicate = b_1430_k.n_1700_B(jsonobject.get("vehicle"));
            b_1430_k entitypredicate1 = b_1430_k.n_1700_B(jsonobject.get("targeted_entity"));
            String s = i_4431_W.n_1700_B(jsonobject, "team", (String)null);
            g_2336_b resourcelocation = jsonobject.has("catType") ? new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "catType")) : null;
            return new J_1907_R().n_1700_B(entitytypepredicate).n_1700_B(distancepredicate).n_1700_B(locationpredicate).n_1700_B(mobeffectspredicate).n_1700_B(nbtpredicate).n_1700_B(entityflagspredicate).n_1700_B(entityequipmentpredicate).n_1700_B(playerpredicate).n_1700_B(fishingpredicate).n_1700_B(s).n_1700_B(entitypredicate).J_1907_R(entitypredicate1).J_1907_R(resourcelocation).J_1907_R();
        }
        return n_1700_B;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("type", this.J_1907_R.n_1700_B());
        jsonobject.add("distance", this.R_4764_Y.n_1700_B());
        jsonobject.add("location", this.G_564_y.n_1700_B());
        jsonobject.add("effects", this.P_1922_E.J_1907_R());
        jsonobject.add("nbt", this.u_1723_Y.n_1700_B());
        jsonobject.add("flags", this.v_4262_N.n_1700_B());
        jsonobject.add("equipment", this.w_1484_f.n_1700_B());
        jsonobject.add("player", this.t_148_a.n_1700_B());
        jsonobject.add("fishing_hook", this.s_956_w.n_1700_B());
        jsonobject.add("vehicle", this.u_2550_I.n_1700_B());
        jsonobject.add("targeted_entity", this.M_588_G.n_1700_B());
        jsonobject.addProperty("team", this.P_4830_p);
        if (this.h_1847_R != null) {
            jsonobject.addProperty("catType", this.h_1847_R.toString());
        }
        return jsonobject;
    }

    public static q_1704_m J_1907_R(B_4088_l player, N_4263_v entity) {
        return new q_1704_m.n_1700_B(player.c_3005_b()).n_1700_B(LootContextParams.n_1700_B, entity).n_1700_B(LootContextParams.u_1723_Y, player.s_4990_V()).n_1700_B(player.M_3508_C()).n_1700_B(f_1402_I.s_956_w);
    }

    public static class J_1907_R {
        private n_3115_n n_1700_B = n_3115_n.n_1700_B;
        private o_3456_E J_1907_R = o_3456_E.n_1700_B;
        private B_368_w R_4764_Y = B_368_w.n_1700_B;
        private y_2836_h G_564_y = y_2836_h.n_1700_B;
        private h_2396_v P_1922_E = h_2396_v.n_1700_B;
        private D_3640_k u_1723_Y = D_3640_k.n_1700_B;
        private k_200_a v_4262_N = k_200_a.n_1700_B;
        private A_2487_t w_1484_f = A_2487_t.n_1700_B;
        private FishingHookPredicate t_148_a = FishingHookPredicate.n_1700_B;
        private b_1430_k s_956_w = n_1700_B;
        private b_1430_k u_2550_I = n_1700_B;
        private String M_588_G;
        private g_2336_b P_4830_p;

        public static J_1907_R n_1700_B() {
            return new J_1907_R();
        }

        public J_1907_R n_1700_B(t_5_h<?> typeIn) {
            this.n_1700_B = n_3115_n.J_1907_R(typeIn);
            return this;
        }

        public J_1907_R n_1700_B(r_109_r<t_5_h<?>> typeIn) {
            this.n_1700_B = n_3115_n.n_1700_B(typeIn);
            return this;
        }

        public J_1907_R n_1700_B(g_2336_b catTypeIn) {
            this.P_4830_p = catTypeIn;
            return this;
        }

        public J_1907_R n_1700_B(n_3115_n typeIn) {
            this.n_1700_B = typeIn;
            return this;
        }

        public J_1907_R n_1700_B(o_3456_E distanceIn) {
            this.J_1907_R = distanceIn;
            return this;
        }

        public J_1907_R n_1700_B(B_368_w locationIn) {
            this.R_4764_Y = locationIn;
            return this;
        }

        public J_1907_R n_1700_B(y_2836_h effectsIn) {
            this.G_564_y = effectsIn;
            return this;
        }

        public J_1907_R n_1700_B(h_2396_v nbtIn) {
            this.P_1922_E = nbtIn;
            return this;
        }

        public J_1907_R n_1700_B(D_3640_k flagsIn) {
            this.u_1723_Y = flagsIn;
            return this;
        }

        public J_1907_R n_1700_B(k_200_a equipmentIn) {
            this.v_4262_N = equipmentIn;
            return this;
        }

        public J_1907_R n_1700_B(A_2487_t player) {
            this.w_1484_f = player;
            return this;
        }

        public J_1907_R n_1700_B(FishingHookPredicate fishing) {
            this.t_148_a = fishing;
            return this;
        }

        public J_1907_R n_1700_B(b_1430_k mount) {
            this.s_956_w = mount;
            return this;
        }

        public J_1907_R J_1907_R(b_1430_k target) {
            this.u_2550_I = target;
            return this;
        }

        public J_1907_R n_1700_B(@Nullable String team) {
            this.M_588_G = team;
            return this;
        }

        public J_1907_R J_1907_R(@Nullable g_2336_b catTypeIn) {
            this.P_4830_p = catTypeIn;
            return this;
        }

        public b_1430_k J_1907_R() {
            return new b_1430_k(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w, this.u_2550_I, this.M_588_G, this.P_4830_p);
        }
    }

    public static class n_1700_B {
        public static final n_1700_B n_1700_B = new n_1700_B(new LootItemCondition[0]);
        private final LootItemCondition[] J_1907_R;
        private final Predicate<q_1704_m> R_4764_Y;

        private n_1700_B(LootItemCondition[] lootConditions) {
            this.J_1907_R = lootConditions;
            this.R_4764_Y = LootItemConditions.n_1700_B(lootConditions);
        }

        public static n_1700_B n_1700_B(LootItemCondition ... conditions) {
            return new n_1700_B(conditions);
        }

        public static n_1700_B n_1700_B(JsonObject jsonObject, String name, DeserializationContext conditions) {
            JsonElement jsonelement = jsonObject.get(name);
            return lightning.product.b_1430_k$n_1700_B.n_1700_B(name, conditions, jsonelement);
        }

        public static n_1700_B[] J_1907_R(JsonObject jsonObject, String name, DeserializationContext conditions) {
            JsonElement jsonelement = jsonObject.get(name);
            if (jsonelement != null && !jsonelement.isJsonNull()) {
                JsonArray jsonarray = i_4431_W.t_148_a(jsonelement, name);
                n_1700_B[] aentitypredicate$andpredicate = new n_1700_B[jsonarray.size()];
                for (int i = 0; i < jsonarray.size(); ++i) {
                    aentitypredicate$andpredicate[i] = lightning.product.b_1430_k$n_1700_B.n_1700_B(name + "[" + i + "]", conditions, jsonarray.get(i));
                }
                return aentitypredicate$andpredicate;
            }
            return new n_1700_B[0];
        }

        private static n_1700_B n_1700_B(String name, DeserializationContext conditions, @Nullable JsonElement element) {
            if (element != null && element.isJsonArray()) {
                LootItemCondition[] ailootcondition = conditions.n_1700_B(element.getAsJsonArray(), conditions.n_1700_B().toString() + "/" + name, f_1402_I.s_956_w);
                return new n_1700_B(ailootcondition);
            }
            b_1430_k entitypredicate = b_1430_k.n_1700_B(element);
            return lightning.product.b_1430_k$n_1700_B.n_1700_B(entitypredicate);
        }

        public static n_1700_B n_1700_B(b_1430_k entityCondition) {
            if (entityCondition == n_1700_B) {
                return n_1700_B;
            }
            LootItemCondition ilootcondition = W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, entityCondition).build();
            return new n_1700_B(new LootItemCondition[]{ilootcondition});
        }

        public boolean n_1700_B(q_1704_m context) {
            return this.R_4764_Y.test(context);
        }

        public JsonElement n_1700_B(SerializationContext serializer) {
            return this.J_1907_R.length == 0 ? JsonNull.INSTANCE : serializer.n_1700_B(this.J_1907_R);
        }

        public static JsonElement n_1700_B(n_1700_B[] predicates, SerializationContext serializer) {
            if (predicates.length == 0) {
                return JsonNull.INSTANCE;
            }
            JsonArray jsonarray = new JsonArray();
            for (n_1700_B entitypredicate$andpredicate : predicates) {
                jsonarray.add(entitypredicate$andpredicate.n_1700_B(serializer));
            }
            return jsonarray;
        }
    }
}


