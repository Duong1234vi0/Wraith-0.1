/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_4388_s;
import lightning.product.Attribute;
import lightning.product.U_1880_G;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.q_2896_o;
import lightning.product.s_1415_m;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AttributeMap {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Map<Attribute, A_4388_s> J_1907_R = Maps.newHashMap();
    private final Set<A_4388_s> R_4764_Y = Sets.newHashSet();
    private final s_1415_m G_564_y;

    public AttributeMap(s_1415_m attributeMap) {
        this.G_564_y = attributeMap;
    }

    private void n_1700_B(A_4388_s instance) {
        if (instance.n_1700_B().J_1907_R()) {
            this.R_4764_Y.add(instance);
        }
    }

    public Set<A_4388_s> n_1700_B() {
        return this.R_4764_Y;
    }

    public Collection<A_4388_s> J_1907_R() {
        return this.J_1907_R.values().stream().filter(instance -> instance.n_1700_B().J_1907_R()).collect(Collectors.toList());
    }

    @Nullable
    public A_4388_s n_1700_B(Attribute attribute) {
        return this.J_1907_R.computeIfAbsent(attribute, attribute2 -> this.G_564_y.n_1700_B(this::n_1700_B, (Attribute)attribute2));
    }

    public boolean J_1907_R(Attribute attribute) {
        return this.J_1907_R.get(attribute) != null || this.G_564_y.R_4764_Y(attribute);
    }

    public boolean n_1700_B(Attribute attribute, UUID uuid) {
        A_4388_s modifiableattributeinstance = this.J_1907_R.get(attribute);
        return modifiableattributeinstance != null ? modifiableattributeinstance.n_1700_B(uuid) != null : this.G_564_y.J_1907_R(attribute, uuid);
    }

    public double R_4764_Y(Attribute attribute) {
        A_4388_s modifiableattributeinstance = this.J_1907_R.get(attribute);
        return modifiableattributeinstance != null ? modifiableattributeinstance.u_1723_Y() : this.G_564_y.n_1700_B(attribute);
    }

    public double G_564_y(Attribute attribute) {
        A_4388_s modifiableattributeinstance = this.J_1907_R.get(attribute);
        return modifiableattributeinstance != null ? modifiableattributeinstance.J_1907_R() : this.G_564_y.J_1907_R(attribute);
    }

    public double J_1907_R(Attribute attribute, UUID uuid) {
        A_4388_s modifiableattributeinstance = this.J_1907_R.get(attribute);
        return modifiableattributeinstance != null ? modifiableattributeinstance.n_1700_B(uuid).G_564_y() : this.G_564_y.n_1700_B(attribute, uuid);
    }

    public void n_1700_B(Multimap<Attribute, U_1880_G> map) {
        map.asMap().forEach((attribute, modifiers) -> {
            A_4388_s modifiableattributeinstance = this.J_1907_R.get(attribute);
            if (modifiableattributeinstance != null) {
                modifiers.forEach(modifiableattributeinstance::G_564_y);
            }
        });
    }

    public void J_1907_R(Multimap<Attribute, U_1880_G> map) {
        map.forEach((attribute, modifiers) -> {
            A_4388_s modifiableattributeinstance = this.n_1700_B((Attribute)attribute);
            if (modifiableattributeinstance != null) {
                modifiableattributeinstance.G_564_y((U_1880_G)modifiers);
                modifiableattributeinstance.J_1907_R((U_1880_G)modifiers);
            }
        });
    }

    public void n_1700_B(AttributeMap manager) {
        manager.J_1907_R.values().forEach(modifiableInstance -> {
            A_4388_s modifiableattributeinstance = this.n_1700_B(modifiableInstance.n_1700_B());
            if (modifiableattributeinstance != null) {
                modifiableattributeinstance.n_1700_B((A_4388_s)modifiableInstance);
            }
        });
    }

    public q_2896_o R_4764_Y() {
        q_2896_o listnbt = new q_2896_o();
        for (A_4388_s modifiableattributeinstance : this.J_1907_R.values()) {
            listnbt.add(modifiableattributeinstance.v_4262_N());
        }
        return listnbt;
    }

    public void n_1700_B(q_2896_o nbt) {
        for (int i = 0; i < nbt.size(); ++i) {
            U_2912_j compoundnbt = nbt.n_1700_B(i);
            String s = compoundnbt.M_588_G("Name");
            j_3341_s.n_1700_B(V_3137_a.l_1233_K.J_1907_R(g_2336_b.J_1907_R(s)), attribute -> {
                A_4388_s modifiableattributeinstance = this.n_1700_B((Attribute)attribute);
                if (modifiableattributeinstance != null) {
                    modifiableattributeinstance.n_1700_B(compoundnbt);
                }
            }, () -> n_1700_B.warn("Ignoring unknown attribute '{}'", (Object)s));
        }
    }
}


