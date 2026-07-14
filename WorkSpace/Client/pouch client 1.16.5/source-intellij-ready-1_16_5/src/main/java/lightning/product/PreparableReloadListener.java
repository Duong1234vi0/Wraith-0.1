/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;

public interface PreparableReloadListener {
    public CompletableFuture<Void> reload(n_1700_B var1, ResourceManager var2, ProfilerFiller var3, ProfilerFiller var4, Executor var5, Executor var6);

    default public String i_() {
        return this.getClass().getSimpleName();
    }

    public static interface n_1700_B {
        public <T> CompletableFuture<T> markCompleteAwaitingOthers(T var1);
    }
}


