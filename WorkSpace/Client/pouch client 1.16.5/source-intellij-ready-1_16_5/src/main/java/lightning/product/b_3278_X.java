/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSyntaxException
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntComparators
 *  it.unimi.dsi.fastutil.ints.IntList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.annotation.Nullable;
import lightning.product.SerializationTags;
import lightning.product.V_3137_a;
import lightning.product.Z_1993_T;
import lightning.product.b_2585_i;
import lightning.product.g_2336_b;
import lightning.product.i_4431_W;
import lightning.product.q_1613_l;
import lightning.product.q_1803_e;
import lightning.product.r_109_r;
import lightning.product.r_4432_i;

public final class b_3278_X
implements Predicate<Z_1993_T> {
    public static final b_3278_X n_1700_B = new b_3278_X(Stream.empty());
    private final n_1700_B[] J_1907_R;
    private Z_1993_T[] R_4764_Y;
    private IntList G_564_y;

    private b_3278_X(Stream<? extends n_1700_B> itemLists) {
        this.J_1907_R = (n_1700_B[])itemLists.toArray(n_1700_B[]::new);
    }

    public Z_1993_T[] n_1700_B() {
        this.P_1922_E();
        return this.R_4764_Y;
    }

    private void P_1922_E() {
        if (this.R_4764_Y == null) {
            this.R_4764_Y = (Z_1993_T[])Arrays.stream(this.J_1907_R).flatMap(ingredientList -> ingredientList.n_1700_B().stream()).distinct().toArray(Z_1993_T[]::new);
        }
    }

    public boolean n_1700_B(@Nullable Z_1993_T p_test_1_) {
        if (p_test_1_ == null) {
            return false;
        }
        this.P_1922_E();
        if (this.R_4764_Y.length == 0) {
            return p_test_1_.n_1700_B();
        }
        for (Z_1993_T itemstack : this.R_4764_Y) {
            if (itemstack.J_1907_R() != p_test_1_.J_1907_R()) continue;
            return true;
        }
        return false;
    }

    public IntList J_1907_R() {
        if (this.G_564_y == null) {
            this.P_1922_E();
            this.G_564_y = new IntArrayList(this.R_4764_Y.length);
            for (Z_1993_T itemstack : this.R_4764_Y) {
                this.G_564_y.add(r_4432_i.R_4764_Y(itemstack));
            }
            this.G_564_y.sort((Comparator)IntComparators.NATURAL_COMPARATOR);
        }
        return this.G_564_y;
    }

    public void n_1700_B(b_2585_i buffer) {
        this.P_1922_E();
        buffer.G_564_y(this.R_4764_Y.length);
        for (int i = 0; i < this.R_4764_Y.length; ++i) {
            buffer.n_1700_B(this.R_4764_Y[i]);
        }
    }

    public JsonElement R_4764_Y() {
        if (this.J_1907_R.length == 1) {
            return this.J_1907_R[0].J_1907_R();
        }
        JsonArray jsonarray = new JsonArray();
        for (n_1700_B ingredient$iitemlist : this.J_1907_R) {
            jsonarray.add((JsonElement)ingredient$iitemlist.J_1907_R());
        }
        return jsonarray;
    }

    public boolean G_564_y() {
        return !(this.J_1907_R.length != 0 || this.R_4764_Y != null && this.R_4764_Y.length != 0 || this.G_564_y != null && !this.G_564_y.isEmpty());
    }

    private static b_3278_X J_1907_R(Stream<? extends n_1700_B> stream) {
        b_3278_X ingredient = new b_3278_X(stream);
        return ingredient.J_1907_R.length == 0 ? n_1700_B : ingredient;
    }

    public static b_3278_X n_1700_B(q_1803_e ... itemsIn) {
        return b_3278_X.n_1700_B(Arrays.stream(itemsIn).map(Z_1993_T::new));
    }

    public static b_3278_X n_1700_B(Z_1993_T ... stacks) {
        return b_3278_X.n_1700_B(Arrays.stream(stacks));
    }

    public static b_3278_X n_1700_B(Stream<Z_1993_T> stacks) {
        return b_3278_X.J_1907_R(stacks.filter(stack -> !stack.n_1700_B()).map(stack -> new J_1907_R((Z_1993_T)stack)));
    }

    public static b_3278_X n_1700_B(r_109_r<q_1613_l> tagIn) {
        return b_3278_X.J_1907_R(Stream.of(new R_4764_Y(tagIn)));
    }

    public static b_3278_X J_1907_R(b_2585_i buffer) {
        int i = buffer.u_1723_Y();
        return b_3278_X.J_1907_R(Stream.generate(() -> new J_1907_R(buffer.u_2550_I())).limit(i));
    }

    public static b_3278_X n_1700_B(@Nullable JsonElement json) {
        if (json != null && !json.isJsonNull()) {
            if (json.isJsonObject()) {
                return b_3278_X.J_1907_R(Stream.of(b_3278_X.n_1700_B(json.getAsJsonObject())));
            }
            if (json.isJsonArray()) {
                JsonArray jsonarray = json.getAsJsonArray();
                if (jsonarray.size() == 0) {
                    throw new JsonSyntaxException("Item array cannot be empty, at least one item must be defined");
                }
                return b_3278_X.J_1907_R(StreamSupport.stream(jsonarray.spliterator(), false).map(element -> b_3278_X.n_1700_B(i_4431_W.w_1484_f(element, "item"))));
            }
            throw new JsonSyntaxException("Expected item to be object or array of objects");
        }
        throw new JsonSyntaxException("Item cannot be null");
    }

    private static n_1700_B n_1700_B(JsonObject json) {
        if (json.has("item") && json.has("tag")) {
            throw new JsonParseException("An ingredient entry is either a tag or an item, not both");
        }
        if (json.has("item")) {
            g_2336_b resourcelocation1 = new g_2336_b(i_4431_W.u_1723_Y(json, "item"));
            q_1613_l item = V_3137_a.e_2887_G.J_1907_R(resourcelocation1).orElseThrow(() -> new JsonSyntaxException("Unknown item '" + String.valueOf(resourcelocation1) + "'"));
            return new J_1907_R(new Z_1993_T(item));
        }
        if (json.has("tag")) {
            g_2336_b resourcelocation = new g_2336_b(i_4431_W.u_1723_Y(json, "tag"));
            r_109_r<q_1613_l> itag = SerializationTags.n_1700_B().J_1907_R().n_1700_B(resourcelocation);
            if (itag == null) {
                throw new JsonSyntaxException("Unknown item tag '" + String.valueOf(resourcelocation) + "'");
            }
            return new R_4764_Y(itag);
        }
        throw new JsonParseException("An ingredient entry needs either a tag or an item");
    }

    @Override
    public /* synthetic */ boolean test(@Nullable Object object) {
        return this.n_1700_B((Z_1993_T)object);
    }

    static interface n_1700_B {
        public Collection<Z_1993_T> n_1700_B();

        public JsonObject J_1907_R();
    }

    static class R_4764_Y
    implements n_1700_B {
        private final r_109_r<q_1613_l> n_1700_B;

        private R_4764_Y(r_109_r<q_1613_l> tagIn) {
            this.n_1700_B = tagIn;
        }

        @Override
        public Collection<Z_1993_T> n_1700_B() {
            ArrayList list = Lists.newArrayList();
            for (q_1613_l item : this.n_1700_B.n_1700_B()) {
                list.add(new Z_1993_T(item));
            }
            return list;
        }

        @Override
        public JsonObject J_1907_R() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("tag", SerializationTags.n_1700_B().J_1907_R().J_1907_R(this.n_1700_B).toString());
            return jsonobject;
        }
    }

    static class J_1907_R
    implements n_1700_B {
        private final Z_1993_T n_1700_B;

        private J_1907_R(Z_1993_T stackIn) {
            this.n_1700_B = stackIn;
        }

        @Override
        public Collection<Z_1993_T> n_1700_B() {
            return Collections.singleton(this.n_1700_B);
        }

        @Override
        public JsonObject J_1907_R() {
            JsonObject jsonobject = new JsonObject();
            jsonobject.addProperty("item", V_3137_a.e_2887_G.J_1907_R(this.n_1700_B.J_1907_R()).toString());
            return jsonobject;
        }
    }
}


