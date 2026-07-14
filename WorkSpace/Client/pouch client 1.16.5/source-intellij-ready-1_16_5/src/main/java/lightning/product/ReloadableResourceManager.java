/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import lightning.product.PreparableReloadListener;
import lightning.product.ResourceManager;
import lightning.product.X_1446_C;
import lightning.product.PackResources;
import lightning.product.ReloadInstance;

public interface ReloadableResourceManager
extends AutoCloseable,
ResourceManager {
    default public CompletableFuture<X_1446_C> n_1700_B(Executor backgroundExecutor, Executor gameExecutor, List<PackResources> resourcePacks, CompletableFuture<X_1446_C> waitingFor) {
        return this.n_1700_B(backgroundExecutor, gameExecutor, waitingFor, resourcePacks).n_1700_B();
    }

    public ReloadInstance n_1700_B(Executor var1, Executor var2, CompletableFuture<X_1446_C> var3, List<PackResources> var4);

    public void n_1700_B(PreparableReloadListener var1);

    @Override
    public void close();
}


