/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonSyntaxException
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import javax.annotation.Nullable;
import lightning.product.K_4074_S;
import lightning.product.T_2915_h;
import lightning.product.SerializationTags;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.h_2396_v;
import lightning.product.i_2154_H;
import lightning.product.i_4431_W;
import lightning.product.r_109_r;
import lightning.product.r_2687_x;

public class D_4237_z {
    public static final D_4237_z n_1700_B = new D_4237_z(null, null, r_2687_x.n_1700_B, h_2396_v.n_1700_B);
    @Nullable
    private final r_109_r<T_2915_h> J_1907_R;
    @Nullable
    private final T_2915_h R_4764_Y;
    private final r_2687_x G_564_y;
    private final h_2396_v P_1922_E;

    public D_4237_z(@Nullable r_109_r<T_2915_h> tag, @Nullable T_2915_h block, r_2687_x statePredicate, h_2396_v nbtPredicate) {
        this.J_1907_R = tag;
        this.R_4764_Y = block;
        this.G_564_y = statePredicate;
        this.P_1922_E = nbtPredicate;
    }

    public boolean n_1700_B(e_3591_l world, c_1514_x pos) {
        i_2154_H tileentity;
        if (this == n_1700_B) {
            return true;
        }
        if (!world.multiplayerClientSuggestionProvider(pos)) {
            return false;
        }
        K_4074_S blockstate = world.getBlockState(pos);
        T_2915_h block = blockstate.J_1907_R();
        if (this.J_1907_R != null && !this.J_1907_R.n_1700_B(block)) {
            return false;
        }
        if (this.R_4764_Y != null && block != this.R_4764_Y) {
            return false;
        }
        if (!this.G_564_y.n_1700_B(blockstate)) {
            return false;
        }
        return this.P_1922_E == h_2396_v.n_1700_B || (tileentity = world.getTileEntity(pos)) != null && this.P_1922_E.n_1700_B(tileentity.n_1700_B(new U_2912_j()));
    }

    public static D_4237_z n_1700_B(@Nullable JsonElement json) {
        if (json != null && !json.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(json, "block");
            h_2396_v nbtpredicate = h_2396_v.n_1700_B(jsonobject.get("nbt"));
            T_2915_h block = null;
            if (jsonobject.has("block")) {
                g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "block"));
                block = V_3137_a.q_4610_l.n_1700_B(resourcelocation);
            }
            r_109_r<T_2915_h> itag = null;
            if (jsonobject.has("tag")) {
                g_2336_b resourcelocation1 = new g_2336_b(i_4431_W.u_1723_Y(jsonobject, "tag"));
                itag = SerializationTags.n_1700_B().n_1700_B().n_1700_B(resourcelocation1);
                if (itag == null) {
                    throw new JsonSyntaxException("Unknown block tag '" + String.valueOf(resourcelocation1) + "'");
                }
            }
            r_2687_x statepropertiespredicate = r_2687_x.n_1700_B(jsonobject.get("state"));
            return new D_4237_z(itag, block, statepropertiespredicate, nbtpredicate);
        }
        return n_1700_B;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        if (this.R_4764_Y != null) {
            jsonobject.addProperty("block", V_3137_a.q_4610_l.J_1907_R(this.R_4764_Y).toString());
        }
        if (this.J_1907_R != null) {
            jsonobject.addProperty("tag", SerializationTags.n_1700_B().n_1700_B().J_1907_R(this.J_1907_R).toString());
        }
        jsonobject.add("nbt", this.P_1922_E.n_1700_B());
        jsonobject.add("state", this.G_564_y.n_1700_B());
        return jsonobject;
    }

    public static class n_1700_B {
        @Nullable
        private T_2915_h n_1700_B;
        @Nullable
        private r_109_r<T_2915_h> J_1907_R;
        private r_2687_x R_4764_Y = r_2687_x.n_1700_B;
        private h_2396_v G_564_y = h_2396_v.n_1700_B;

        private n_1700_B() {
        }

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(T_2915_h block) {
            this.n_1700_B = block;
            return this;
        }

        public n_1700_B n_1700_B(r_109_r<T_2915_h> tag) {
            this.J_1907_R = tag;
            return this;
        }

        public n_1700_B n_1700_B(r_2687_x statePredicate) {
            this.R_4764_Y = statePredicate;
            return this;
        }

        public D_4237_z J_1907_R() {
            return new D_4237_z(this.J_1907_R, this.n_1700_B, this.R_4764_Y, this.G_564_y);
        }
    }
}


