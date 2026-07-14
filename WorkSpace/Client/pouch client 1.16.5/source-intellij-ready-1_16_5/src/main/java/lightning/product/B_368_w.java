/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.JsonOps
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.JsonOps;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.C_4998_y;
import lightning.product.D_4237_z;
import lightning.product.StructureFeature;
import lightning.product.V_3137_a;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.k_594_Q;
import lightning.product.l_14_c;
import lightning.product.MinMaxBounds;
import lightning.product.LightPredicate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class B_368_w {
    private static final Logger J_1907_R = LogManager.getLogger();
    public static final B_368_w n_1700_B = new B_368_w(MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, null, null, null, null, LightPredicate.n_1700_B, D_4237_z.n_1700_B, l_14_c.n_1700_B);
    private final MinMaxBounds.n_1700_B R_4764_Y;
    private final MinMaxBounds.n_1700_B G_564_y;
    private final MinMaxBounds.n_1700_B P_1922_E;
    @Nullable
    private final f_2392_k<k_594_Q> u_1723_Y;
    @Nullable
    private final StructureFeature<?> v_4262_N;
    @Nullable
    private final f_2392_k<b_4507_u> w_1484_f;
    @Nullable
    private final Boolean t_148_a;
    private final LightPredicate s_956_w;
    private final D_4237_z u_2550_I;
    private final l_14_c M_588_G;

    public B_368_w(MinMaxBounds.n_1700_B x, MinMaxBounds.n_1700_B y, MinMaxBounds.n_1700_B z, @Nullable f_2392_k<k_594_Q> biome, @Nullable StructureFeature<?> feature, @Nullable f_2392_k<b_4507_u> dimension, @Nullable Boolean smokey, LightPredicate light, D_4237_z block, l_14_c fluid) {
        this.R_4764_Y = x;
        this.G_564_y = y;
        this.P_1922_E = z;
        this.u_1723_Y = biome;
        this.v_4262_N = feature;
        this.w_1484_f = dimension;
        this.t_148_a = smokey;
        this.s_956_w = light;
        this.u_2550_I = block;
        this.M_588_G = fluid;
    }

    public static B_368_w n_1700_B(f_2392_k<k_594_Q> biome) {
        return new B_368_w(MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, biome, null, null, null, LightPredicate.n_1700_B, D_4237_z.n_1700_B, l_14_c.n_1700_B);
    }

    public static B_368_w J_1907_R(f_2392_k<b_4507_u> dimension) {
        return new B_368_w(MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, null, null, dimension, null, LightPredicate.n_1700_B, D_4237_z.n_1700_B, l_14_c.n_1700_B);
    }

    public static B_368_w n_1700_B(StructureFeature<?> feature) {
        return new B_368_w(MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, MinMaxBounds.n_1700_B.P_1922_E, null, feature, null, null, LightPredicate.n_1700_B, D_4237_z.n_1700_B, l_14_c.n_1700_B);
    }

    public boolean n_1700_B(e_3591_l world, double x, double y, double z) {
        return this.n_1700_B(world, (float)x, (float)y, (float)z);
    }

    public boolean n_1700_B(e_3591_l world, float x, float y, float z) {
        if (!this.R_4764_Y.J_1907_R(x)) {
            return false;
        }
        if (!this.G_564_y.J_1907_R(y)) {
            return false;
        }
        if (!this.P_1922_E.J_1907_R(z)) {
            return false;
        }
        if (this.w_1484_f != null && this.w_1484_f != world.g_2268_R()) {
            return false;
        }
        c_1514_x blockpos = new c_1514_x(x, y, z);
        boolean flag = world.multiplayerClientSuggestionProvider(blockpos);
        Optional<f_2392_k<k_594_Q>> optional = world.t_1786_h().J_1907_R(V_3137_a.PlayerInfo).R_4764_Y(world.P_1922_E(blockpos));
        if (!optional.isPresent()) {
            return false;
        }
        if (this.u_1723_Y == null || flag && this.u_1723_Y == optional.get()) {
            if (this.v_4262_N == null || flag && world.R_4764_Y().n_1700_B(blockpos, true, this.v_4262_N).P_1922_E()) {
                if (this.t_148_a == null || flag && this.t_148_a == C_4998_y.n_1700_B(world, blockpos)) {
                    if (!this.s_956_w.n_1700_B(world, blockpos)) {
                        return false;
                    }
                    if (!this.u_2550_I.n_1700_B(world, blockpos)) {
                        return false;
                    }
                    return this.M_588_G.n_1700_B(world, blockpos);
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        if (!(this.R_4764_Y.R_4764_Y() && this.G_564_y.R_4764_Y() && this.P_1922_E.R_4764_Y())) {
            JsonObject jsonobject1 = new JsonObject();
            jsonobject1.add("x", this.R_4764_Y.G_564_y());
            jsonobject1.add("y", this.G_564_y.G_564_y());
            jsonobject1.add("z", this.P_1922_E.G_564_y());
            jsonobject.add("position", (JsonElement)jsonobject1);
        }
        if (this.w_1484_f != null) {
            b_4507_u.P_1922_E.encodeStart((DynamicOps)JsonOps.INSTANCE, this.w_1484_f).resultOrPartial(arg_0 -> ((Logger)J_1907_R).error(arg_0)).ifPresent(dimensionID -> jsonobject.add("dimension", dimensionID));
        }
        if (this.v_4262_N != null) {
            jsonobject.addProperty("feature", this.v_4262_N.v_4262_N());
        }
        if (this.u_1723_Y != null) {
            jsonobject.addProperty("biome", this.u_1723_Y.n_1700_B().toString());
        }
        if (this.t_148_a != null) {
            jsonobject.addProperty("smokey", this.t_148_a);
        }
        jsonobject.add("light", this.s_956_w.n_1700_B());
        jsonobject.add("block", this.u_2550_I.n_1700_B());
        jsonobject.add("fluid", this.M_588_G.n_1700_B());
        return jsonobject;
    }

    public static B_368_w n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "location");
            JsonObject jsonobject1 = i_4431_W.n_1700_B(jsonobject, "position", new JsonObject());
            MinMaxBounds.n_1700_B minmaxbounds$floatbound = MinMaxBounds.n_1700_B.n_1700_B(jsonobject1.get("x"));
            MinMaxBounds.n_1700_B minmaxbounds$floatbound1 = MinMaxBounds.n_1700_B.n_1700_B(jsonobject1.get("y"));
            MinMaxBounds.n_1700_B minmaxbounds$floatbound2 = MinMaxBounds.n_1700_B.n_1700_B(jsonobject1.get("z"));
            f_2392_k registrykey = jsonobject.has("dimension") ? g_2336_b.n_1700_B.parse((DynamicOps)JsonOps.INSTANCE, (Object)jsonobject.get("dimension")).resultOrPartial(arg_0 -> ((Logger)J_1907_R).error(arg_0)).map(dimensionKey -> f_2392_k.n_1700_B(V_3137_a.z_1737_N, dimensionKey)).orElse(null) : null;
            StructureFeature structure = jsonobject.has("feature") ? (StructureFeature)StructureFeature.n_1700_B.get((Object)i_4431_W.u_1723_Y(jsonobject, "feature")) : null;
            f_2392_k<k_594_Q> registrykey1 = null;
            if (jsonobject.has("biome")) {
                g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "biome"));
                registrykey1 = f_2392_k.n_1700_B(V_3137_a.PlayerInfo, resourcelocation);
            }
            Boolean obool = jsonobject.has("smokey") ? Boolean.valueOf(jsonobject.get("smokey").getAsBoolean()) : null;
            LightPredicate lightpredicate = LightPredicate.n_1700_B(jsonobject.get("light"));
            D_4237_z blockpredicate = D_4237_z.n_1700_B(jsonobject.get("block"));
            l_14_c fluidpredicate = l_14_c.n_1700_B(jsonobject.get("fluid"));
            return new B_368_w(minmaxbounds$floatbound, minmaxbounds$floatbound1, minmaxbounds$floatbound2, registrykey1, structure, registrykey, obool, lightpredicate, blockpredicate, fluidpredicate);
        }
        return n_1700_B;
    }

    public static class n_1700_B {
        private MinMaxBounds.n_1700_B n_1700_B = MinMaxBounds.n_1700_B.P_1922_E;
        private MinMaxBounds.n_1700_B J_1907_R = MinMaxBounds.n_1700_B.P_1922_E;
        private MinMaxBounds.n_1700_B R_4764_Y = MinMaxBounds.n_1700_B.P_1922_E;
        @Nullable
        private f_2392_k<k_594_Q> G_564_y;
        @Nullable
        private StructureFeature<?> P_1922_E;
        @Nullable
        private f_2392_k<b_4507_u> u_1723_Y;
        @Nullable
        private Boolean v_4262_N;
        private LightPredicate w_1484_f = LightPredicate.n_1700_B;
        private D_4237_z t_148_a = D_4237_z.n_1700_B;
        private l_14_c s_956_w = l_14_c.n_1700_B;

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(@Nullable f_2392_k<k_594_Q> biome) {
            this.G_564_y = biome;
            return this;
        }

        public n_1700_B n_1700_B(D_4237_z block) {
            this.t_148_a = block;
            return this;
        }

        public n_1700_B n_1700_B(Boolean smokey) {
            this.v_4262_N = smokey;
            return this;
        }

        public B_368_w J_1907_R() {
            return new B_368_w(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y, this.v_4262_N, this.w_1484_f, this.t_148_a, this.s_956_w);
        }
    }
}


