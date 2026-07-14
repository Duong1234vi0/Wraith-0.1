/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Function
 *  com.google.common.base.Functions
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import lightning.product.A_2629_w;
import lightning.product.g_2336_b;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AdvancementList {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Map<g_2336_b, A_2629_w> J_1907_R = Maps.newHashMap();
    private final Set<A_2629_w> R_4764_Y = Sets.newLinkedHashSet();
    private final Set<A_2629_w> G_564_y = Sets.newLinkedHashSet();
    private n_1700_B P_1922_E;

    private void n_1700_B(A_2629_w advancementIn) {
        for (A_2629_w advancement : advancementIn.P_1922_E()) {
            this.n_1700_B(advancement);
        }
        n_1700_B.info("Forgot about advancement {}", (Object)advancementIn.w_1484_f());
        this.J_1907_R.remove(advancementIn.w_1484_f());
        if (advancementIn.J_1907_R() == null) {
            this.R_4764_Y.remove(advancementIn);
            if (this.P_1922_E != null) {
                this.P_1922_E.J_1907_R(advancementIn);
            }
        } else {
            this.G_564_y.remove(advancementIn);
            if (this.P_1922_E != null) {
                this.P_1922_E.G_564_y(advancementIn);
            }
        }
    }

    public void n_1700_B(Set<g_2336_b> ids) {
        for (g_2336_b resourcelocation : ids) {
            A_2629_w advancement = this.J_1907_R.get(resourcelocation);
            if (advancement == null) {
                n_1700_B.warn("Told to remove advancement {} but I don't know what that is", (Object)resourcelocation);
                continue;
            }
            this.n_1700_B(advancement);
        }
    }

    public void n_1700_B(Map<g_2336_b, A_2629_w.n_1700_B> advancementsIn) {
        Function function = Functions.forMap(this.J_1907_R, (Object)null);
        while (!advancementsIn.isEmpty()) {
            boolean flag = false;
            Iterator<Map.Entry<g_2336_b, A_2629_w.n_1700_B>> iterator = advancementsIn.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<g_2336_b, A_2629_w.n_1700_B> entry = iterator.next();
                g_2336_b resourcelocation = entry.getKey();
                A_2629_w.n_1700_B advancement$builder = entry.getValue();
                if (!advancement$builder.n_1700_B((java.util.function.Function<g_2336_b, A_2629_w>)function)) continue;
                A_2629_w advancement = advancement$builder.J_1907_R(resourcelocation);
                this.J_1907_R.put(resourcelocation, advancement);
                flag = true;
                iterator.remove();
                if (advancement.J_1907_R() == null) {
                    this.R_4764_Y.add(advancement);
                    if (this.P_1922_E == null) continue;
                    this.P_1922_E.n_1700_B(advancement);
                    continue;
                }
                this.G_564_y.add(advancement);
                if (this.P_1922_E == null) continue;
                this.P_1922_E.R_4764_Y(advancement);
            }
            if (flag) continue;
            for (Map.Entry<g_2336_b, A_2629_w.n_1700_B> entry1 : advancementsIn.entrySet()) {
                n_1700_B.error("Couldn't load advancement {}: {}", (Object)entry1.getKey(), (Object)entry1.getValue());
            }
        }
        n_1700_B.info("Loaded {} advancements", (Object)this.J_1907_R.size());
    }

    public void n_1700_B() {
        this.J_1907_R.clear();
        this.R_4764_Y.clear();
        this.G_564_y.clear();
        if (this.P_1922_E != null) {
            this.P_1922_E.n_1700_B();
        }
    }

    public Iterable<A_2629_w> J_1907_R() {
        return this.R_4764_Y;
    }

    public Collection<A_2629_w> R_4764_Y() {
        return this.J_1907_R.values();
    }

    @Nullable
    public A_2629_w n_1700_B(g_2336_b id) {
        return this.J_1907_R.get(id);
    }

    public void n_1700_B(@Nullable n_1700_B listenerIn) {
        this.P_1922_E = listenerIn;
        if (listenerIn != null) {
            for (A_2629_w advancement : this.R_4764_Y) {
                listenerIn.n_1700_B(advancement);
            }
            for (A_2629_w advancement1 : this.G_564_y) {
                listenerIn.R_4764_Y(advancement1);
            }
        }
    }

    public static interface n_1700_B {
        public void n_1700_B(A_2629_w var1);

        public void J_1907_R(A_2629_w var1);

        public void R_4764_Y(A_2629_w var1);

        public void G_564_y(A_2629_w var1);

        public void n_1700_B();
    }
}


