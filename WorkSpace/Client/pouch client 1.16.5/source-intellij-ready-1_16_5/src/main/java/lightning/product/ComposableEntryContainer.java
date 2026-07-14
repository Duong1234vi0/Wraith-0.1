/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Objects;
import java.util.function.Consumer;
import lightning.product.LootPoolEntry;
import lightning.product.q_1704_m;

@FunctionalInterface
interface ComposableEntryContainer {
    public static final ComposableEntryContainer n_1700_B = (p_216134_0_, p_216134_1_) -> false;
    public static final ComposableEntryContainer J_1907_R = (p_216136_0_, p_216136_1_) -> true;

    public boolean expand(q_1704_m var1, Consumer<LootPoolEntry> var2);

    default public ComposableEntryContainer n_1700_B(ComposableEntryContainer entry) {
        Objects.requireNonNull(entry);
        return (p_216137_2_, p_216137_3_) -> this.expand(p_216137_2_, p_216137_3_) && entry.expand(p_216137_2_, p_216137_3_);
    }

    default public ComposableEntryContainer J_1907_R(ComposableEntryContainer entry) {
        Objects.requireNonNull(entry);
        return (p_216138_2_, p_216138_3_) -> this.expand(p_216138_2_, p_216138_3_) || entry.expand(p_216138_2_, p_216138_3_);
    }
}


