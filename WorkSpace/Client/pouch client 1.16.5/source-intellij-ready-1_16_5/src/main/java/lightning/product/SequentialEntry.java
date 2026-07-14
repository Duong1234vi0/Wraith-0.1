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

public class SequentialEntry
extends D_2242_n {
    SequentialEntry(u_1373_N[] children, LootItemCondition[] conditions) {
        super(children, conditions);
    }

    @Override
    public d_614_w n_1700_B() {
        return LootPoolEntries.v_4262_N;
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
                return entries[0].n_1700_B(entries[1]);
            }
        }
        return (context, generatorConsumer) -> {
            for (ComposableEntryContainer ilootentry : entries) {
                if (ilootentry.expand(context, generatorConsumer)) continue;
                return false;
            }
            return true;
        };
    }
}


