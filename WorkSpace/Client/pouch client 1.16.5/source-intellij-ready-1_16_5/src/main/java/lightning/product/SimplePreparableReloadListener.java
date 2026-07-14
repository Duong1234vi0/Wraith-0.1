/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lightning.product.PreparableReloadListener;
import lightning.product.ResourceManager;
import lightning.product.ProfilerFiller;

public abstract class SimplePreparableReloadListener<T>
implements PreparableReloadListener {
    @Override
    public final CompletableFuture<Void> reload(PreparableReloadListener.n_1700_B stage, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        return ((CompletableFuture)CompletableFuture.supplyAsync(() -> this.prepare(resourceManager, preparationsProfiler), backgroundExecutor).thenCompose(stage::markCompleteAwaitingOthers)).thenAcceptAsync(p_215269_3_ -> this.apply(p_215269_3_, resourceManager, reloadProfiler), gameExecutor);
    }

    protected abstract T prepare(ResourceManager var1, ProfilerFiller var2);

    protected abstract void apply(T var1, ResourceManager var2, ProfilerFiller var3);
}


