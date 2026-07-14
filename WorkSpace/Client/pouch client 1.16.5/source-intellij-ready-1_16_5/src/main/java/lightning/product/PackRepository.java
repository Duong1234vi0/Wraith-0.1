/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Functions
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.base.Functions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.D_2103_L;
import lightning.product.I_2946_k;
import lightning.product.PackResources;

public class PackRepository
implements AutoCloseable {
    private final Set<I_2946_k> n_1700_B;
    private Map<String, D_2103_L> J_1907_R = ImmutableMap.of();
    private List<D_2103_L> R_4764_Y = ImmutableList.of();
    private final D_2103_L.n_1700_B G_564_y;

    public PackRepository(D_2103_L.n_1700_B p_i231423_1_, I_2946_k ... p_i231423_2_) {
        this.G_564_y = p_i231423_1_;
        this.n_1700_B = ImmutableSet.copyOf((Object[])p_i231423_2_);
    }

    public PackRepository(I_2946_k ... p_i241886_1_) {
        this(D_2103_L::new, p_i241886_1_);
    }

    public void n_1700_B() {
        List list = (List)this.R_4764_Y.stream().map(D_2103_L::P_1922_E).collect(ImmutableList.toImmutableList());
        this.close();
        this.J_1907_R = this.v_4262_N();
        this.R_4764_Y = this.J_1907_R(list);
    }

    private Map<String, D_2103_L> v_4262_N() {
        TreeMap map = Maps.newTreeMap();
        for (I_2946_k ipackfinder : this.n_1700_B) {
            ipackfinder.n_1700_B(p_232615_1_ -> {
                D_2103_L resourcepackinfo = map.put(p_232615_1_.P_1922_E(), p_232615_1_);
            }, this.G_564_y);
        }
        return ImmutableMap.copyOf((Map)map);
    }

    public void n_1700_B(Collection<String> p_198985_1_) {
        this.R_4764_Y = this.J_1907_R(p_198985_1_);
    }

    private List<D_2103_L> J_1907_R(Collection<String> p_232618_1_) {
        List list = this.R_4764_Y(p_232618_1_).collect(Collectors.toList());
        for (D_2103_L resourcepackinfo : this.J_1907_R.values()) {
            if (!resourcepackinfo.u_1723_Y() || list.contains(resourcepackinfo)) continue;
            resourcepackinfo.w_1484_f().n_1700_B(list, resourcepackinfo, Functions.identity(), false);
        }
        return ImmutableList.copyOf(list);
    }

    private Stream<D_2103_L> R_4764_Y(Collection<String> p_232620_1_) {
        return p_232620_1_.stream().map(this.J_1907_R::get).filter(Objects::nonNull);
    }

    public Collection<String> J_1907_R() {
        return this.J_1907_R.keySet();
    }

    public Collection<D_2103_L> R_4764_Y() {
        return this.J_1907_R.values();
    }

    public Collection<String> G_564_y() {
        return (Collection)this.R_4764_Y.stream().map(D_2103_L::P_1922_E).collect(ImmutableSet.toImmutableSet());
    }

    public Collection<D_2103_L> P_1922_E() {
        return this.R_4764_Y;
    }

    @Nullable
    public D_2103_L n_1700_B(String name) {
        return this.J_1907_R.get(name);
    }

    @Override
    public void close() {
        this.J_1907_R.values().forEach(D_2103_L::close);
    }

    public boolean J_1907_R(String p_232617_1_) {
        return this.J_1907_R.containsKey(p_232617_1_);
    }

    public List<PackResources> u_1723_Y() {
        return (List)this.R_4764_Y.stream().map(D_2103_L::G_564_y).collect(ImmutableList.toImmutableList());
    }
}


