/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.A_4388_s;
import lightning.product.Attribute;
import lightning.product.U_1880_G;
import lightning.product.V_3137_a;

public class s_1415_m {
    private final Map<Attribute, A_4388_s> n_1700_B;

    public s_1415_m(Map<Attribute, A_4388_s> attributeMap) {
        this.n_1700_B = ImmutableMap.copyOf(attributeMap);
    }

    private A_4388_s G_564_y(Attribute attribute) {
        A_4388_s modifiableattributeinstance = this.n_1700_B.get(attribute);
        if (modifiableattributeinstance == null) {
            throw new IllegalArgumentException("Can't find attribute " + String.valueOf(V_3137_a.l_1233_K.J_1907_R(attribute)));
        }
        return modifiableattributeinstance;
    }

    public double n_1700_B(Attribute attribute) {
        return this.G_564_y(attribute).u_1723_Y();
    }

    public double J_1907_R(Attribute attribute) {
        return this.G_564_y(attribute).J_1907_R();
    }

    public double n_1700_B(Attribute attribute, UUID id) {
        U_1880_G attributemodifier = this.G_564_y(attribute).n_1700_B(id);
        if (attributemodifier == null) {
            throw new IllegalArgumentException("Can't find modifier " + String.valueOf(id) + " on attribute " + String.valueOf(V_3137_a.l_1233_K.J_1907_R(attribute)));
        }
        return attributemodifier.G_564_y();
    }

    @Nullable
    public A_4388_s n_1700_B(Consumer<A_4388_s> onChangedCallback, Attribute attribute) {
        A_4388_s modifiableattributeinstance = this.n_1700_B.get(attribute);
        if (modifiableattributeinstance == null) {
            return null;
        }
        A_4388_s modifiableattributeinstance1 = new A_4388_s(attribute, onChangedCallback);
        modifiableattributeinstance1.n_1700_B(modifiableattributeinstance);
        return modifiableattributeinstance1;
    }

    public static n_1700_B n_1700_B() {
        return new n_1700_B();
    }

    public boolean R_4764_Y(Attribute attribute) {
        return this.n_1700_B.containsKey(attribute);
    }

    public boolean J_1907_R(Attribute attribute, UUID id) {
        A_4388_s modifiableattributeinstance = this.n_1700_B.get(attribute);
        return modifiableattributeinstance != null && modifiableattributeinstance.n_1700_B(id) != null;
    }

    public static class n_1700_B {
        private final Map<Attribute, A_4388_s> n_1700_B = Maps.newHashMap();
        private boolean J_1907_R;

        private A_4388_s J_1907_R(Attribute attribute) {
            A_4388_s modifiableattributeinstance = new A_4388_s(attribute, modifiableInstance -> {
                if (this.J_1907_R) {
                    throw new UnsupportedOperationException("Tried to change value for default attribute instance: " + String.valueOf(V_3137_a.l_1233_K.J_1907_R(attribute)));
                }
            });
            this.n_1700_B.put(attribute, modifiableattributeinstance);
            return modifiableattributeinstance;
        }

        public n_1700_B n_1700_B(Attribute attribute) {
            this.J_1907_R(attribute);
            return this;
        }

        public n_1700_B n_1700_B(Attribute attribute, double value) {
            A_4388_s modifiableattributeinstance = this.J_1907_R(attribute);
            modifiableattributeinstance.n_1700_B(value);
            return this;
        }

        public s_1415_m n_1700_B() {
            this.J_1907_R = true;
            return new s_1415_m(this.n_1700_B);
        }
    }
}


