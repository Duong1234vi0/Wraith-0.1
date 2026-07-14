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
import lightning.product.b_3129_s;
import lightning.product.e_1174_E;
import lightning.product.i_4431_W;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.w_4866_k;

public class k_200_a {
    public static final k_200_a n_1700_B = new k_200_a(w_4866_k.n_1700_B, w_4866_k.n_1700_B, w_4866_k.n_1700_B, w_4866_k.n_1700_B, w_4866_k.n_1700_B, w_4866_k.n_1700_B);
    public static final k_200_a J_1907_R = new k_200_a(w_4866_k.n_1700_B.n_1700_B().n_1700_B(Items.o_3946_o).n_1700_B(b_3129_s.t_1786_h().Q_4569_t()).J_1907_R(), w_4866_k.n_1700_B, w_4866_k.n_1700_B, w_4866_k.n_1700_B, w_4866_k.n_1700_B, w_4866_k.n_1700_B);
    private final w_4866_k R_4764_Y;
    private final w_4866_k G_564_y;
    private final w_4866_k P_1922_E;
    private final w_4866_k u_1723_Y;
    private final w_4866_k v_4262_N;
    private final w_4866_k w_1484_f;

    public k_200_a(w_4866_k head, w_4866_k chest, w_4866_k legs, w_4866_k feet, w_4866_k mainHand, w_4866_k offHand) {
        this.R_4764_Y = head;
        this.G_564_y = chest;
        this.P_1922_E = legs;
        this.u_1723_Y = feet;
        this.v_4262_N = mainHand;
        this.w_1484_f = offHand;
    }

    public boolean n_1700_B(@Nullable N_4263_v entity) {
        if (this == n_1700_B) {
            return true;
        }
        if (!(entity instanceof r_4811_B)) {
            return false;
        }
        r_4811_B livingentity = (r_4811_B)entity;
        if (!this.R_4764_Y.n_1700_B(livingentity.J_1907_R(e_1174_E.u_1723_Y))) {
            return false;
        }
        if (!this.G_564_y.n_1700_B(livingentity.J_1907_R(e_1174_E.P_1922_E))) {
            return false;
        }
        if (!this.P_1922_E.n_1700_B(livingentity.J_1907_R(e_1174_E.G_564_y))) {
            return false;
        }
        if (!this.u_1723_Y.n_1700_B(livingentity.J_1907_R(e_1174_E.R_4764_Y))) {
            return false;
        }
        if (!this.v_4262_N.n_1700_B(livingentity.J_1907_R(e_1174_E.n_1700_B))) {
            return false;
        }
        return this.w_1484_f.n_1700_B(livingentity.J_1907_R(e_1174_E.J_1907_R));
    }

    public static k_200_a n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "equipment");
            w_4866_k itempredicate = w_4866_k.n_1700_B(jsonobject.get("head"));
            w_4866_k itempredicate1 = w_4866_k.n_1700_B(jsonobject.get("chest"));
            w_4866_k itempredicate2 = w_4866_k.n_1700_B(jsonobject.get("legs"));
            w_4866_k itempredicate3 = w_4866_k.n_1700_B(jsonobject.get("feet"));
            w_4866_k itempredicate4 = w_4866_k.n_1700_B(jsonobject.get("mainhand"));
            w_4866_k itempredicate5 = w_4866_k.n_1700_B(jsonobject.get("offhand"));
            return new k_200_a(itempredicate, itempredicate1, itempredicate2, itempredicate3, itempredicate4, itempredicate5);
        }
        return n_1700_B;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        jsonobject.add("head", this.R_4764_Y.n_1700_B());
        jsonobject.add("chest", this.G_564_y.n_1700_B());
        jsonobject.add("legs", this.P_1922_E.n_1700_B());
        jsonobject.add("feet", this.u_1723_Y.n_1700_B());
        jsonobject.add("mainhand", this.v_4262_N.n_1700_B());
        jsonobject.add("offhand", this.w_1484_f.n_1700_B());
        return jsonobject;
    }

    public static class n_1700_B {
        private w_4866_k n_1700_B = w_4866_k.n_1700_B;
        private w_4866_k J_1907_R = w_4866_k.n_1700_B;
        private w_4866_k R_4764_Y = w_4866_k.n_1700_B;
        private w_4866_k G_564_y = w_4866_k.n_1700_B;
        private w_4866_k P_1922_E = w_4866_k.n_1700_B;
        private w_4866_k u_1723_Y = w_4866_k.n_1700_B;

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(w_4866_k condition) {
            this.n_1700_B = condition;
            return this;
        }

        public n_1700_B J_1907_R(w_4866_k condition) {
            this.J_1907_R = condition;
            return this;
        }

        public n_1700_B R_4764_Y(w_4866_k condition) {
            this.R_4764_Y = condition;
            return this;
        }

        public n_1700_B G_564_y(w_4866_k condition) {
            this.G_564_y = condition;
            return this;
        }

        public k_200_a J_1907_R() {
            return new k_200_a(this.n_1700_B, this.J_1907_R, this.R_4764_Y, this.G_564_y, this.P_1922_E, this.u_1723_Y);
        }
    }
}


