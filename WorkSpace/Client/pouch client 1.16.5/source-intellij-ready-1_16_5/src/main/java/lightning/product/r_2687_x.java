/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonNull
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.E_4700_p;
import lightning.product.K_4074_S;
import lightning.product.P_1008_U;
import lightning.product.Y_1835_y;
import lightning.product.FluidState;
import lightning.product.i_4431_W;
import lightning.product.v_3760_Q;

public class r_2687_x {
    public static final r_2687_x n_1700_B = new r_2687_x((List<R_4764_Y>)ImmutableList.of());
    private final List<R_4764_Y> J_1907_R;

    private static R_4764_Y n_1700_B(String name, JsonElement element) {
        if (element.isJsonPrimitive()) {
            String s2 = element.getAsString();
            return new J_1907_R(name, s2);
        }
        JsonObject jsonobject = i_4431_W.w_1484_f(element, "value");
        String s = jsonobject.has("min") ? r_2687_x.J_1907_R(jsonobject.get("min")) : null;
        String s1 = jsonobject.has("max") ? r_2687_x.J_1907_R(jsonobject.get("max")) : null;
        return s != null && s.equals(s1) ? new J_1907_R(name, s) : new G_564_y(name, s, s1);
    }

    @Nullable
    private static String J_1907_R(JsonElement element) {
        return element.isJsonNull() ? null : element.getAsString();
    }

    private r_2687_x(List<R_4764_Y> matchers) {
        this.J_1907_R = ImmutableList.copyOf(matchers);
    }

    public <S extends P_1008_U<?, S>> boolean n_1700_B(Y_1835_y<?, S> properties, S targetProperty) {
        for (R_4764_Y statepropertiespredicate$matcher : this.J_1907_R) {
            if (statepropertiespredicate$matcher.n_1700_B(properties, targetProperty)) continue;
            return false;
        }
        return true;
    }

    public boolean n_1700_B(K_4074_S state) {
        return this.n_1700_B(state.J_1907_R().t_1786_h(), state);
    }

    public boolean n_1700_B(FluidState state) {
        return this.n_1700_B(state.n_1700_B().v_4262_N(), state);
    }

    public void n_1700_B(Y_1835_y<?, ?> properties, Consumer<String> stringConsumer) {
        this.J_1907_R.forEach(m -> m.n_1700_B(properties, stringConsumer));
    }

    public static r_2687_x n_1700_B(@Nullable JsonElement element) {
        if (element != null && !element.isJsonNull()) {
            JsonObject jsonobject = i_4431_W.w_1484_f(element, "properties");
            ArrayList list = Lists.newArrayList();
            for (Map.Entry entry : jsonobject.entrySet()) {
                list.add(r_2687_x.n_1700_B((String)entry.getKey(), (JsonElement)entry.getValue()));
            }
            return new r_2687_x(list);
        }
        return n_1700_B;
    }

    public JsonElement n_1700_B() {
        if (this == n_1700_B) {
            return JsonNull.INSTANCE;
        }
        JsonObject jsonobject = new JsonObject();
        if (!this.J_1907_R.isEmpty()) {
            this.J_1907_R.forEach(matcher -> jsonobject.add(matcher.J_1907_R(), matcher.n_1700_B()));
        }
        return jsonobject;
    }

    static class J_1907_R
    extends R_4764_Y {
        private final String n_1700_B;

        public J_1907_R(String propertyName, String valueToMatch) {
            super(propertyName);
            this.n_1700_B = valueToMatch;
        }

        @Override
        protected <T extends Comparable<T>> boolean n_1700_B(P_1008_U<?, ?> properties, v_3760_Q<T> propertyTarget) {
            Comparable t = properties.R_4764_Y(propertyTarget);
            Optional<T> optional = propertyTarget.J_1907_R(this.n_1700_B);
            return optional.isPresent() && t.compareTo((Comparable)((Comparable)optional.get())) == 0;
        }

        @Override
        public JsonElement n_1700_B() {
            return new JsonPrimitive(this.n_1700_B);
        }
    }

    static class G_564_y
    extends R_4764_Y {
        @Nullable
        private final String n_1700_B;
        @Nullable
        private final String J_1907_R;

        public G_564_y(String propertyName, @Nullable String minimum, @Nullable String maximum) {
            super(propertyName);
            this.n_1700_B = minimum;
            this.J_1907_R = maximum;
        }

        @Override
        protected <T extends Comparable<T>> boolean n_1700_B(P_1008_U<?, ?> properties, v_3760_Q<T> propertyTarget) {
            Optional<T> optional1;
            Optional<T> optional;
            Comparable t = properties.R_4764_Y(propertyTarget);
            if (!(this.n_1700_B == null || (optional = propertyTarget.J_1907_R(this.n_1700_B)).isPresent() && t.compareTo((Comparable)((Comparable)optional.get())) >= 0)) {
                return false;
            }
            return this.J_1907_R == null || (optional1 = propertyTarget.J_1907_R(this.J_1907_R)).isPresent() && t.compareTo((Comparable)((Comparable)optional1.get())) <= 0;
        }

        @Override
        public JsonElement n_1700_B() {
            JsonObject jsonobject = new JsonObject();
            if (this.n_1700_B != null) {
                jsonobject.addProperty("min", this.n_1700_B);
            }
            if (this.J_1907_R != null) {
                jsonobject.addProperty("max", this.J_1907_R);
            }
            return jsonobject;
        }
    }

    static abstract class R_4764_Y {
        private final String n_1700_B;

        public R_4764_Y(String propertyName) {
            this.n_1700_B = propertyName;
        }

        public <S extends P_1008_U<?, S>> boolean n_1700_B(Y_1835_y<?, S> properties, S propertyToMatch) {
            v_3760_Q<?> property = properties.n_1700_B(this.n_1700_B);
            return property == null ? false : this.n_1700_B(propertyToMatch, property);
        }

        protected abstract <T extends Comparable<T>> boolean n_1700_B(P_1008_U<?, ?> var1, v_3760_Q<T> var2);

        public abstract JsonElement n_1700_B();

        public String J_1907_R() {
            return this.n_1700_B;
        }

        public void n_1700_B(Y_1835_y<?, ?> properties, Consumer<String> propertyConsumer) {
            v_3760_Q<?> property = properties.n_1700_B(this.n_1700_B);
            if (property == null) {
                propertyConsumer.accept(this.n_1700_B);
            }
        }
    }

    public static class n_1700_B {
        private final List<R_4764_Y> n_1700_B = Lists.newArrayList();

        private n_1700_B() {
        }

        public static n_1700_B n_1700_B() {
            return new n_1700_B();
        }

        public n_1700_B n_1700_B(v_3760_Q<?> property, String value) {
            this.n_1700_B.add(new J_1907_R(property.P_1922_E(), value));
            return this;
        }

        public n_1700_B n_1700_B(v_3760_Q<Integer> intProp, int value) {
            return this.n_1700_B((v_3760_Q)intProp, (Comparable<T> & E_4700_p)Integer.toString(value));
        }

        public n_1700_B n_1700_B(v_3760_Q<Boolean> boolProp, boolean value) {
            return this.n_1700_B((v_3760_Q)boolProp, (Comparable<T> & E_4700_p)Boolean.toString(value));
        }

        public <T extends Comparable<T> & E_4700_p> n_1700_B n_1700_B(v_3760_Q<T> prop, T value) {
            return this.n_1700_B(prop, (T)((E_4700_p)value).n_1700_B());
        }

        public r_2687_x J_1907_R() {
            return new r_2687_x(this.n_1700_B);
        }
    }
}


