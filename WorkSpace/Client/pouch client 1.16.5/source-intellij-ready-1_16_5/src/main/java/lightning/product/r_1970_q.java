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
import lightning.product.B_4088_l;
import lightning.product.DamageSourcePredicate;
import lightning.product.P_11_z;
import lightning.product.b_1430_k;
import lightning.product.i_4431_W;
import lightning.product.MinMaxBounds;

public class r_1970_q {
    public static final r_1970_q n_1700_B = lightning.product.r_1970_q$n_1700_B.n_1700_B().J_1907_R();
    private final MinMaxBounds.n_1700_B J_1907_R;
    private final MinMaxBounds.n_1700_B R_4764_Y;
    private final b_1430_k G_564_y;
    private final Boolean P_1922_E;
    private final DamageSourcePredicate u_1723_Y;

    public r_1970_q() {
        this.J_1907_R = MinMaxBounds.n_1700_B.P_1922_E;
        this.R_4764_Y = MinMaxBounds.n_1700_B.P_1922_E;
        this.G_564_y = b_1430_k.n_1700_B;
        this.P_1922_E = null;
        this.u_1723_Y = DamageSourcePredicate.n_1700_B;
    }

    public r_1970_q(MinMaxBounds.n_1700_B dealt, MinMaxBounds.n_1700_B taken, b_1430_k sourceEntity, @Nullable Boolean blocked, DamageSourcePredicate type) {
        this.J_1907_R = dealt;
        this.R_4764_Y = taken;
        this.G_564_y = sourceEntity;
        this.P_1922_E = blocked;
        this.u_1723_Y = type;
    }

    public boolean n_1700_B(B_4088_l player, P_11_z source, float dealt, float taken, boolean blocked) {
        if (this == n_1700_B) {
            return true;
        }
        if (!this.J_1907_R.J_1907_R(dealt)) {
            return false;
        }
        if (!this.R_4764_Y.J_1907_R(taken)) {
            return false;
        }
        if (!this.G_564_y.n_1700_B(player, source.u_2550_I())) {
            return false;
        }
        if (this.P_1922_E != null && this.P_1922_E != blocked) {
            return false;
        }
        return this.u_1723_Y.n_1700_B(player, source);
    }

    public static r_1970_q n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "damage");
            MinMaxBounds.n_1700_B minmaxbounds$floatbound = MinMaxBounds.n_1700_B.n_1700_B(jsonobject.get("dealt"));
            MinMaxBounds.n_1700_B minmaxbounds$floatbound1 = MinMaxBounds.n_1700_B.n_1700_B(jsonobject.get("taken"));
            Boolean obool = jsonobject.has("blocked") ? Boolean.valueOf(i_4431_W.w_1484_f(jsonobject, "blocked")) : null;
            b_1430_k entitypredicate = b_1430_k.n_1700_B(jsonobject.get("source_entity"));
            DamageSourcePredicate damagesourcepredicate = DamageSourcePredicate.n_1700_B(jsonobject.get("type"));
            return new r_1970_q(minmaxbounds$floatbound, minmaxbounds$floatbound1, entitypredicate, obool, damagesourcepredicate);
        }
        return n_1700_B;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("dealt", this.J_1907_R.G_564_y());
        jsonobject.add("taken", this.R_4764_Y.G_564_y());
        jsonobject.add("source_entity", this.G_564_y.n_1700_B());
        jsonobject.add("type", this.u_1723_Y.n_1700_B());
        if (this.P_1922_E != null) {
            jsonobject.addProperty("blocked", this.P_1922_E);
        }
        return jsonobject;
    }

    public static class n_1700_B {
        private MinMaxBounds.n_1700_B n_1700_B = MinMaxBounds.n_1700_B.P_1922_E;
        private MinMaxBounds.n_1700_B J_1907_R = MinMaxBounds.n_1700_B.P_1922_E;
        private b_1430_k R_4764_Y = b_1430_k.n_1700_B;
        private Boolean G_564_y;
        private DamageSourcePredicate P_1922_E = DamageSourcePredicate.n_1700_B;

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(Boolean blocked) {
            this.G_564_y = blocked;
            return this;
        }

        public n_1700_B n_1700_B(DamageSourcePredicate.n_1700_B damageType) {
            this.P_1922_E = damageType.J_1907_R();
            return this;
        }

        public r_1970_q J_1907_R() {
            return new r_1970_q(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E);
        }
    }
}


