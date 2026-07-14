/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_2242_n;
import lightning.product.LootItemCondition;
import lightning.product.d_614_w;
import lightning.product.ComposableEntryContainer;
import lightning.product.LootPoolEntries;
import lightning.product.u_1373_N;

public class EntryGroup
extends D_2242_n {
    EntryGroup(u_1373_N[] p_i51257_1_, LootItemCondition[] p_i51257_2_) {
        super(p_i51257_1_, p_i51257_2_);
    }

    @Override
    public d_614_w n_1700_B() {
        return LootPoolEntries.w_1484_f;
    }

    @Override
    protected ComposableEntryContainer n_1700_B(ComposableEntryContainer[] entries) {
        switch (entries.length) {
            case 0: {
                return J_1907_R;
            }
            case 1: {
                return entries[0];
            }
            case 2: {
                ComposableEntryContainer ilootentry = entries[0];
                ComposableEntryContainer ilootentry1 = entries[1];
                return (p_216151_2_, p_216151_3_) -> {
                    ilootentry.expand(p_216151_2_, p_216151_3_);
                    ilootentry1.expand(p_216151_2_, p_216151_3_);
                    return true;
                };
            }
        }
        return (p_216152_1_, p_216152_2_) -> {
            for (ComposableEntryContainer ilootentry2 : entries) {
                ilootentry2.expand(p_216152_1_, p_216152_2_);
            }
            return true;
        };
    }
}


