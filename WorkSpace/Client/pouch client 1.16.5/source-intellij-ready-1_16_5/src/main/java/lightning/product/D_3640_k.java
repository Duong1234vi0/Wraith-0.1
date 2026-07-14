/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import lightning.product.N_4263_v;
import lightning.product.i_4431_W;
import lightning.product.r_4811_B;

public class D_3640_k {
    public static final D_3640_k n_1700_B = new n_1700_B().J_1907_R();
    @Nullable
    private final Boolean J_1907_R;
    @Nullable
    private final Boolean R_4764_Y;
    @Nullable
    private final Boolean G_564_y;
    @Nullable
    private final Boolean P_1922_E;
    @Nullable
    private final Boolean u_1723_Y;

    public D_3640_k(@Nullable Boolean onFire, @Nullable Boolean sneaking, @Nullable Boolean sprinting, @Nullable Boolean swimming, @Nullable Boolean baby) {
        this.J_1907_R = onFire;
        this.R_4764_Y = sneaking;
        this.G_564_y = sprinting;
        this.P_1922_E = swimming;
        this.u_1723_Y = baby;
    }

    public boolean n_1700_B(N_4263_v entity) {
        if (this.J_1907_R != null && entity.RealmsPersistence() != this.J_1907_R.booleanValue()) {
            return false;
        }
        if (this.R_4764_Y != null && entity.Z_875_P() != this.R_4764_Y.booleanValue()) {
            return false;
        }
        if (this.G_564_y != null && entity.o_2341_D() != this.G_564_y.booleanValue()) {
            return false;
        }
        if (this.P_1922_E != null && entity.C_1269_X() != this.P_1922_E.booleanValue()) {
            return false;
        }
        return this.u_1723_Y == null || !(entity instanceof r_4811_B) || ((r_4811_B)entity).d_() == this.u_1723_Y.booleanValue();
    }

    @Nullable
    private static Boolean n_1700_B(JsonObject jsonObject, String name) {
        return jsonObject.has(name) ? Boolean.valueOf(i_4431_W.w_1484_f(jsonObject, name)) : null;
    }

    public static D_3640_k n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "entity flags");
            Boolean obool = D_3640_k.n_1700_B(jsonobject, "is_on_fire");
            Boolean obool1 = D_3640_k.n_1700_B(jsonobject, "is_sneaking");
            Boolean obool2 = D_3640_k.n_1700_B(jsonobject, "is_sprinting");
            Boolean obool3 = D_3640_k.n_1700_B(jsonobject, "is_swimming");
            Boolean obool4 = D_3640_k.n_1700_B(jsonobject, "is_baby");
            return new D_3640_k(obool, obool1, obool2, obool3, obool4);
        }
        return n_1700_B;
    }

    private void n_1700_B(JsonObject jsonObject, String name, @Nullable Boolean bool) {
        if (bool != null) {
            jsonObject.addProperty(name, bool);
        }
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        this.n_1700_B(jsonobject, "is_on_fire", this.J_1907_R);
        this.n_1700_B(jsonobject, "is_sneaking", this.R_4764_Y);
        this.n_1700_B(jsonobject, "is_sprinting", this.G_564_y);
        this.n_1700_B(jsonobject, "is_swimming", this.P_1922_E);
        this.n_1700_B(jsonobject, "is_baby", this.u_1723_Y);
        return jsonobject;
    }

    public static class n_1700_B {
        @Nullable
        private Boolean n_1700_B;
        @Nullable
        private Boolean J_1907_R;
        @Nullable
        private Boolean R_4764_Y;
        @Nullable
        private Boolean G_564_y;
        @Nullable
        private Boolean P_1922_E;

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(@Nullable Boolean onFire) {
            this.n_1700_B = onFire;
            return this;
        }

        public n_1700_B J_1907_R(@Nullable Boolean baby) {
            this.P_1922_E = baby;
            return this;
        }

        public D_3640_k J_1907_R() {
            return new D_3640_k(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }
    }
}


