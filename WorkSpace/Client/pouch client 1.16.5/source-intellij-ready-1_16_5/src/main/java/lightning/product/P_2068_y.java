/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 */
package lightning.product;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lightning.product.A_1604_A;
import lightning.product.B_4088_l;
import lightning.product.SerializationContext;
import lightning.product.P_1965_C;
import lightning.product.W_3491_f;
import lightning.product.SimpleCriterionTrigger;
import lightning.product.Z_1993_T;
import lightning.product.b_1430_k;
import lightning.product.g_2336_b;
import lightning.product.h_2396_v;
import lightning.product.i_4431_W;
import lightning.product.q_1803_e;
import lightning.product.MinMaxBounds;
import lightning.product.DeserializationContext;
import lightning.product.w_4866_k;

public class P_2068_y
extends SimpleCriterionTrigger<n_1700_B> {
    private static final g_2336_b n_1700_B = new g_2336_b("inventory_changed");

    @Override
    public g_2336_b n_1700_B() {
        return n_1700_B;
    }

    public n_1700_B J_1907_R(JsonObject json, b_1430_k.n_1700_B entityPredicate, DeserializationContext conditionsParser) {
        JsonObject jsonobject = i_4431_W.n_1700_B(json, "slots", new JsonObject());
        MinMaxBounds.G_564_y minmaxbounds$intbound = MinMaxBounds.G_564_y.n_1700_B(jsonobject.get("occupied"));
        MinMaxBounds.G_564_y minmaxbounds$intbound1 = MinMaxBounds.G_564_y.n_1700_B(jsonobject.get("full"));
        MinMaxBounds.G_564_y minmaxbounds$intbound2 = MinMaxBounds.G_564_y.n_1700_B(jsonobject.get("empty"));
        w_4866_k[] aitempredicate = w_4866_k.J_1907_R(json.get("items"));
        return new n_1700_B(entityPredicate, minmaxbounds$intbound, minmaxbounds$intbound1, minmaxbounds$intbound2, aitempredicate);
    }

    public void n_1700_B(B_4088_l player, W_3491_f inventory, Z_1993_T stack) {
        int i = 0;
        int j = 0;
        int k = 0;
        for (int l = 0; l < inventory.Y_259_p(); ++l) {
            Z_1993_T itemstack = inventory.s_956_w(l);
            if (itemstack.n_1700_B()) {
                ++j;
                continue;
            }
            ++k;
            if (itemstack.t_4043_B() < itemstack.R_4764_Y()) continue;
            ++i;
        }
        this.n_1700_B(player, inventory, stack, i, j, k);
    }

    private void n_1700_B(B_4088_l player, W_3491_f inventory, Z_1993_T stack, int full, int empty, int occupied) {
        this.n_1700_B(player, (T instance) -> instance.n_1700_B(inventory, stack, full, empty, occupied));
    }

    @Override
    public /* synthetic */ P_1965_C n_1700_B(JsonObject jsonObject, b_1430_k.n_1700_B n_1700_B2, DeserializationContext u_4771_O2) {
        return this.J_1907_R(jsonObject, n_1700_B2, u_4771_O2);
    }

    public static class n_1700_B
    extends P_1965_C {
        private final MinMaxBounds.G_564_y n_1700_B;
        private final MinMaxBounds.G_564_y J_1907_R;
        private final MinMaxBounds.G_564_y R_4764_Y;
        private final w_4866_k[] G_564_y;

        public n_1700_B(b_1430_k.n_1700_B player, MinMaxBounds.G_564_y occupied, MinMaxBounds.G_564_y full, MinMaxBounds.G_564_y empty, w_4866_k[] items) {
            super(n_1700_B, player);
            this.n_1700_B = occupied;
            this.J_1907_R = full;
            this.R_4764_Y = empty;
            this.G_564_y = items;
        }

        public static n_1700_B n_1700_B(w_4866_k ... itemConditions) {
            return new n_1700_B(b_1430_k.n_1700_B.n_1700_B, MinMaxBounds.G_564_y.P_1922_E, MinMaxBounds.G_564_y.P_1922_E, MinMaxBounds.G_564_y.P_1922_E, itemConditions);
        }

        public static n_1700_B n_1700_B(q_1803_e ... items) {
            w_4866_k[] aitempredicate = new w_4866_k[items.length];
            for (int i = 0; i < items.length; ++i) {
                aitempredicate[i] = new w_4866_k(null, items[i].u_1723_Y(), MinMaxBounds.G_564_y.P_1922_E, MinMaxBounds.G_564_y.P_1922_E, A_1604_A.J_1907_R, A_1604_A.J_1907_R, null, h_2396_v.n_1700_B);
            }
            return lightning.product.P_2068_y$n_1700_B.n_1700_B(aitempredicate);
        }

        @Override
        public JsonObject n_1700_B(SerializationContext conditions) {
            JsonObject jsonobject = super.n_1700_B(conditions);
            if (!(this.n_1700_B.R_4764_Y() && this.J_1907_R.R_4764_Y() && this.R_4764_Y.R_4764_Y())) {
                JsonObject jsonobject1 = new JsonObject();
                jsonobject1.add("occupied", this.n_1700_B.G_564_y());
                jsonobject1.add("full", this.J_1907_R.G_564_y());
                jsonobject1.add("empty", this.R_4764_Y.G_564_y());
                jsonobject.add("slots", (JsonElement)jsonobject1);
            }
            if (this.G_564_y.length > 0) {
                JsonArray jsonarray = new JsonArray();
                for (w_4866_k itempredicate : this.G_564_y) {
                    jsonarray.add(itempredicate.n_1700_B());
                }
                jsonobject.add("items", (JsonElement)jsonarray);
            }
            return jsonobject;
        }

        public boolean n_1700_B(W_3491_f inventory, Z_1993_T stack, int full, int empty, int occupied) {
            if (!this.J_1907_R.R_4764_Y(full)) {
                return false;
            }
            if (!this.R_4764_Y.R_4764_Y(empty)) {
                return false;
            }
            if (!this.n_1700_B.R_4764_Y(occupied)) {
                return false;
            }
            int i = this.G_564_y.length;
            if (i == 0) {
                return true;
            }
            if (i != 1) {
                ObjectArrayList list = new ObjectArrayList((Object[])this.G_564_y);
                int j = inventory.Y_259_p();
                for (int k = 0; k < j; ++k) {
                    if (list.isEmpty()) {
                        return true;
                    }
                    Z_1993_T itemstack = inventory.s_956_w(k);
                    if (itemstack.n_1700_B()) continue;
                    list.removeIf(predicate -> predicate.n_1700_B(itemstack));
                }
                return list.isEmpty();
            }
            return !stack.n_1700_B() && this.G_564_y[0].n_1700_B(stack);
        }
    }
}


