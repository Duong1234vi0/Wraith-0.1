/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.LootContextParams;
import lightning.product.B_4088_l;
import lightning.product.D_38_f;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.f_1402_I;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.n_1494_c;
import lightning.product.q_1704_m;
import lightning.product.r_3448_Z;
import net.minecraft.server.G_564_y;

public class J_1008_m {
    public static final J_1008_m n_1700_B = new J_1008_m(0, new g_2336_b[0], new g_2336_b[0], r_3448_Z.n_1700_B.n_1700_B);
    private final int J_1907_R;
    private final g_2336_b[] R_4764_Y;
    private final g_2336_b[] G_564_y;
    private final r_3448_Z.n_1700_B P_1922_E;

    public J_1008_m(int experience, g_2336_b[] loot, g_2336_b[] recipes, r_3448_Z.n_1700_B function) {
        this.J_1907_R = experience;
        this.R_4764_Y = loot;
        this.G_564_y = recipes;
        this.P_1922_E = function;
    }

    public void n_1700_B(B_4088_l player) {
        player.multiplayerClientSuggestionProvider(this.J_1907_R);
        q_1704_m lootcontext = new q_1704_m.n_1700_B(player.c_3005_b()).n_1700_B(LootContextParams.n_1700_B, player).n_1700_B(LootContextParams.u_1723_Y, player.s_4990_V()).n_1700_B(player.M_3508_C()).n_1700_B(f_1402_I.t_148_a);
        boolean flag = false;
        for (g_2336_b resourcelocation : this.R_4764_Y) {
            for (Z_1993_T itemstack : player.J_1907_R.F_2624_D().n_1700_B(resourcelocation).n_1700_B(lootcontext)) {
                if (player.v_4262_N(itemstack)) {
                    player.O_508_d.n_1700_B((a_3913_L)null, player.O_3598_v(), player.X_2960_b(), player.l_2647_k(), SoundEvents.DeathCoords, D_38_f.w_1484_f, 0.2f, ((player.M_3508_C().nextFloat() - player.M_3508_C().nextFloat()) * 0.7f + 1.0f) * 2.0f);
                    flag = true;
                    continue;
                }
                n_1494_c itementity = player.n_1700_B(itemstack, false);
                if (itementity == null) continue;
                itementity.u_2550_I();
                itementity.J_1907_R(player.w_2705_t());
            }
        }
        if (flag) {
            player.o_1800_r.M_588_G();
        }
        if (this.G_564_y.length > 0) {
            player.n_1700_B(this.G_564_y);
        }
        G_564_y minecraftserver = player.J_1907_R;
        this.P_1922_E.n_1700_B(minecraftserver.RealmsWorldResetDto()).ifPresent(commandFunction -> minecraftserver.RealmsWorldResetDto().n_1700_B((r_3448_Z)commandFunction, player.A_3244_K().s_956_w().J_1907_R(2)));
    }

    public String toString() {
        return "AdvancementRewards{experience=" + this.J_1907_R + ", loot=" + Arrays.toString(this.R_4764_Y) + ", recipes=" + Arrays.toString(this.G_564_y) + ", function=" + String.valueOf(this.P_1922_E) + "}";
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        if (this.J_1907_R != 0) {
            jsonobject.addProperty("experience", (Number)this.J_1907_R);
        }
        if (this.R_4764_Y.length > 0) {
            JsonArray jsonarray = new JsonArray();
            for (g_2336_b resourcelocation : this.R_4764_Y) {
                jsonarray.add(resourcelocation.toString());
            }
            jsonobject.add("loot", (JsonElement)jsonarray);
        }
        if (this.G_564_y.length > 0) {
            JsonArray jsonarray1 = new JsonArray();
            for (g_2336_b resourcelocation1 : this.G_564_y) {
                jsonarray1.add(resourcelocation1.toString());
            }
            jsonobject.add("recipes", (JsonElement)jsonarray1);
        }
        if (this.P_1922_E.n_1700_B() != null) {
            jsonobject.addProperty("function", this.P_1922_E.n_1700_B().toString());
        }
        return jsonobject;
    }

    public static J_1008_m n_1700_B(JsonObject json) throws JsonParseException {
        int i = i_4431_W.n_1700_B(json, "experience", 0);
        JsonArray jsonarray = i_4431_W.n_1700_B(json, "loot", new JsonArray());
        g_2336_b[] aresourcelocation = new g_2336_b[jsonarray.size()];
        for (int j = 0; j < aresourcelocation.length; ++j) {
            aresourcelocation[j] = new g_2336_b(i_4431_W.n_1700_B(jsonarray.get(j), "loot[" + j + "]"));
        }
        JsonArray jsonarray1 = i_4431_W.n_1700_B(json, "recipes", new JsonArray());
        g_2336_b[] aresourcelocation1 = new g_2336_b[jsonarray1.size()];
        for (int k = 0; k < aresourcelocation1.length; ++k) {
            aresourcelocation1[k] = new g_2336_b(i_4431_W.n_1700_B(jsonarray1.get(k), "recipes[" + k + "]"));
        }
        r_3448_Z.n_1700_B functionobject$cacheablefunction = json.has("function") ? new r_3448_Z.n_1700_B(new g_2336_b(i_4431_W.u_1723_Y(json, "function"))) : r_3448_Z.n_1700_B.n_1700_B;
        return new J_1008_m(i, aresourcelocation, aresourcelocation1, functionobject$cacheablefunction);
    }

    public static class n_1700_B {
        private int n_1700_B;
        private final List<g_2336_b> J_1907_R = Lists.newArrayList();
        private final List<g_2336_b> R_4764_Y = Lists.newArrayList();
        @Nullable
        private g_2336_b G_564_y;

        public static n_1700_B n_1700_B(int experienceIn) {
            return new n_1700_B().J_1907_R(experienceIn);
        }

        public n_1700_B J_1907_R(int experienceIn) {
            this.n_1700_B += experienceIn;
            return this;
        }

        public static n_1700_B n_1700_B(g_2336_b recipeIn) {
            return new n_1700_B().J_1907_R(recipeIn);
        }

        public n_1700_B J_1907_R(g_2336_b recipeIn) {
            this.R_4764_Y.add(recipeIn);
            return this;
        }

        public J_1008_m n_1700_B() {
            return new J_1008_m(this.n_1700_B, this.J_1907_R.toArray(new g_2336_b[0]), this.R_4764_Y.toArray(new g_2336_b[0]), this.G_564_y == null ? r_3448_Z.n_1700_B.n_1700_B : new r_3448_Z.n_1700_B(this.G_564_y));
        }
    }
}



