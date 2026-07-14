/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  org.apache.commons.lang3.ArrayUtils
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import lightning.product.D_2242_n;
import lightning.product.LootItemCondition;
import lightning.product.d_614_w;
import lightning.product.g_1866_m;
import lightning.product.ComposableEntryContainer;
import lightning.product.LootPoolEntries;
import lightning.product.u_1373_N;
import org.apache.commons.lang3.ArrayUtils;

public class d_1292_N
extends D_2242_n {
    d_1292_N(u_1373_N[] p_i51263_1_, LootItemCondition[] p_i51263_2_) {
        super(p_i51263_1_, p_i51263_2_);
    }

    @Override
    public d_614_w n_1700_B() {
        return LootPoolEntries.u_1723_Y;
    }

    @Override
    protected ComposableEntryContainer n_1700_B(ComposableEntryContainer[] entries) {
        switch (entries.length) {
            case 0: {
                return n_1700_B;
            }
            case 1: {
                return entries[0];
            }
            case 2: {
                return entries[0].J_1907_R(entries[1]);
            }
        }
        return (p_216150_1_, p_216150_2_) -> {
            for (ComposableEntryContainer ilootentry : entries) {
                if (!ilootentry.expand(p_216150_1_, p_216150_2_)) continue;
                return true;
            }
            return false;
        };
    }

    @Override
    public void n_1700_B(g_1866_m p_225579_1_) {
        super.n_1700_B(p_225579_1_);
        for (int i = 0; i < this.G_564_y.length - 1; ++i) {
            if (!ArrayUtils.isEmpty((Object[])this.G_564_y[i].R_4764_Y)) continue;
            p_225579_1_.n_1700_B("Unreachable entry!");
        }
    }

    public static n_1700_B n_1700_B(u_1373_N.n_1700_B<?> ... p_216149_0_) {
        return new n_1700_B(p_216149_0_);
    }

    public static class n_1700_B
    extends u_1373_N.n_1700_B<n_1700_B> {
        private final List<u_1373_N> n_1700_B = Lists.newArrayList();

        public n_1700_B(u_1373_N.n_1700_B<?> ... p_i50579_1_) {
            for (u_1373_N.n_1700_B<?> builder : p_i50579_1_) {
                this.n_1700_B.add(builder.J_1907_R());
            }
        }

        protected n_1700_B n_1700_B() {
            return this;
        }

        @Override
        public n_1700_B n_1700_B(u_1373_N.n_1700_B<?> p_216080_1_) {
            this.n_1700_B.add(p_216080_1_.J_1907_R());
            return this;
        }

        @Override
        public u_1373_N J_1907_R() {
            return new d_1292_N(this.n_1700_B.toArray(new u_1373_N[0]), this.u_1723_Y());
        }

        @Override
        protected /* synthetic */ u_1373_N.n_1700_B R_4764_Y() {
            return this.n_1700_B();
        }
    }
}


