/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lightning.product.PreparableReloadListener;
import lightning.product.ResourceManager;
import lightning.product.X_1446_C;
import lightning.product.ProfilerFiller;

public interface ResourceManagerReloadListener
extends PreparableReloadListener {
    @Override
    default public CompletableFuture<Void> reload(PreparableReloadListener.n_1700_B stage, ResourceManager resourceManager, ProfilerFiller preparationsProfiler, ProfilerFiller reloadProfiler, Executor backgroundExecutor, Executor gameExecutor) {
        return stage.markCompleteAwaitingOthers(X_1446_C.n_1700_B).thenRunAsync(() -> {
            reloadProfiler.n_1700_B();
            reloadProfiler.n_1700_B("listener");
            this.onResourceManagerReload(resourceManager);
            reloadProfiler.R_4764_Y();
            reloadProfiler.J_1907_R();
        }, gameExecutor);
    }

    public void onResourceManagerReload(ResourceManager var1);
}


