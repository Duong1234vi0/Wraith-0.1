/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap
 *  it.unimi.dsi.fastutil.objects.ObjectArraySet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import lightning.product.Attribute;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.q_2896_o;

public class A_4388_s {
    private final Attribute n_1700_B;
    private final Map<U_1880_G.n_1700_B, Set<U_1880_G>> J_1907_R = Maps.newEnumMap(U_1880_G.n_1700_B.class);
    private final Map<UUID, U_1880_G> R_4764_Y = new Object2ObjectArrayMap();
    private final Set<U_1880_G> G_564_y = new ObjectArraySet();
    private double P_1922_E;
    private boolean u_1723_Y = true;
    private double v_4262_N;
    private final Consumer<A_4388_s> w_1484_f;

    public A_4388_s(Attribute attribute, Consumer<A_4388_s> modifiedValueConsumer) {
        this.n_1700_B = attribute;
        this.w_1484_f = modifiedValueConsumer;
        this.P_1922_E = attribute.n_1700_B();
    }

    public Attribute n_1700_B() {
        return this.n_1700_B;
    }

    public double J_1907_R() {
        return this.P_1922_E;
    }

    public void n_1700_B(double baseValue) {
        if (baseValue != this.P_1922_E) {
            this.P_1922_E = baseValue;
            this.G_564_y();
        }
    }

    public Set<U_1880_G> n_1700_B(U_1880_G.n_1700_B operation) {
        return this.J_1907_R.computeIfAbsent(operation, operationIn -> Sets.newHashSet());
    }

    public Set<U_1880_G> R_4764_Y() {
        return ImmutableSet.copyOf(this.R_4764_Y.values());
    }

    @Nullable
    public U_1880_G n_1700_B(UUID uuid) {
        return this.R_4764_Y.get(uuid);
    }

    public boolean n_1700_B(U_1880_G modifier) {
        return this.R_4764_Y.get(modifier.n_1700_B()) != null;
    }

    private void P_1922_E(U_1880_G modifier) {
        U_1880_G attributemodifier = this.R_4764_Y.putIfAbsent(modifier.n_1700_B(), modifier);
        if (attributemodifier != null) {
            throw new IllegalArgumentException("Modifier is already applied on this attribute!");
        }
        this.n_1700_B(modifier.R_4764_Y()).add(modifier);
        this.G_564_y();
    }

    public void J_1907_R(U_1880_G modifier) {
        this.P_1922_E(modifier);
    }

    public void R_4764_Y(U_1880_G modifier) {
        this.P_1922_E(modifier);
        this.G_564_y.add(modifier);
    }

    protected void G_564_y() {
        this.u_1723_Y = true;
        this.w_1484_f.accept(this);
    }

    public void G_564_y(U_1880_G modifier) {
        this.n_1700_B(modifier.R_4764_Y()).remove(modifier);
        this.R_4764_Y.remove(modifier.n_1700_B());
        this.G_564_y.remove(modifier);
        this.G_564_y();
    }

    public void J_1907_R(UUID identifier) {
        U_1880_G attributemodifier = this.n_1700_B(identifier);
        if (attributemodifier != null) {
            this.G_564_y(attributemodifier);
        }
    }

    public boolean R_4764_Y(UUID identifier) {
        U_1880_G attributemodifier = this.n_1700_B(identifier);
        if (attributemodifier != null && this.G_564_y.contains(attributemodifier)) {
            this.G_564_y(attributemodifier);
            return true;
        }
        return false;
    }

    public void P_1922_E() {
        for (U_1880_G attributemodifier : this.R_4764_Y()) {
            this.G_564_y(attributemodifier);
        }
    }

    public double u_1723_Y() {
        if (this.u_1723_Y) {
            this.v_4262_N = this.w_1484_f();
            this.u_1723_Y = false;
        }
        return this.v_4262_N;
    }

    private double w_1484_f() {
        double d0 = this.J_1907_R();
        for (U_1880_G attributemodifier : this.J_1907_R(U_1880_G.n_1700_B.n_1700_B)) {
            d0 += attributemodifier.G_564_y();
        }
        double d1 = d0;
        for (U_1880_G attributemodifier1 : this.J_1907_R(U_1880_G.n_1700_B.J_1907_R)) {
            d1 += d0 * attributemodifier1.G_564_y();
        }
        for (U_1880_G attributemodifier2 : this.J_1907_R(U_1880_G.n_1700_B.R_4764_Y)) {
            d1 *= 1.0 + attributemodifier2.G_564_y();
        }
        return this.n_1700_B.n_1700_B(d1);
    }

    private Collection<U_1880_G> J_1907_R(U_1880_G.n_1700_B operation) {
        return this.J_1907_R.getOrDefault((Object)operation, Collections.emptySet());
    }

    public void n_1700_B(A_4388_s instance) {
        this.P_1922_E = instance.P_1922_E;
        this.R_4764_Y.clear();
        this.R_4764_Y.putAll(instance.R_4764_Y);
        this.G_564_y.clear();
        this.G_564_y.addAll(instance.G_564_y);
        this.J_1907_R.clear();
        instance.J_1907_R.forEach((operation, modifierSet) -> this.n_1700_B((U_1880_G.n_1700_B)((Object)operation)).addAll((Collection<U_1880_G>)modifierSet));
        this.G_564_y();
    }

    public U_2912_j v_4262_N() {
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("Name", V_3137_a.l_1233_K.J_1907_R(this.n_1700_B).toString());
        compoundnbt.n_1700_B("Base", this.P_1922_E);
        if (!this.G_564_y.isEmpty()) {
            q_2896_o listnbt = new q_2896_o();
            for (U_1880_G attributemodifier : this.G_564_y) {
                listnbt.add(attributemodifier.P_1922_E());
            }
            compoundnbt.n_1700_B("Modifiers", listnbt);
        }
        return compoundnbt;
    }

    public void n_1700_B(U_2912_j nbt) {
        this.P_1922_E = nbt.u_2550_I("Base");
        if (nbt.R_4764_Y("Modifiers", 9)) {
            q_2896_o listnbt = nbt.G_564_y("Modifiers", 10);
            for (int i = 0; i < listnbt.size(); ++i) {
                U_1880_G attributemodifier = U_1880_G.n_1700_B(listnbt.n_1700_B(i));
                if (attributemodifier == null) continue;
                this.R_4764_Y.put(attributemodifier.n_1700_B(), attributemodifier);
                this.n_1700_B(attributemodifier.R_4764_Y()).add(attributemodifier);
                this.G_564_y.add(attributemodifier);
            }
        }
        this.G_564_y();
    }
}


