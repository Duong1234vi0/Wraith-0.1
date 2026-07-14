/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import lightning.product.InactiveProfiler;
import lightning.product.PreparableReloadListener;
import lightning.product.ResourceManager;
import lightning.product.X_1446_C;
import lightning.product.ReloadInstance;
import lightning.product.j_3341_s;

public class S_2259_B<S>
implements ReloadInstance {
    protected final ResourceManager n_1700_B;
    protected final CompletableFuture<X_1446_C> J_1907_R = new CompletableFuture();
    protected final CompletableFuture<List<S>> R_4764_Y;
    private final Set<PreparableReloadListener> G_564_y;
    private final int P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;
    private final AtomicInteger w_1484_f = new AtomicInteger();
    private final AtomicInteger t_148_a = new AtomicInteger();

    public static S_2259_B<Void> n_1700_B(ResourceManager resourceManager, List<PreparableReloadListener> listeners, Executor backgroundExecutor, Executor gameExecutor, CompletableFuture<X_1446_C> alsoWaitedFor) {
        return new S_2259_B<Void>(backgroundExecutor, gameExecutor, resourceManager, listeners, (stage, resourceManager2, preparationsProfiler, p_219561_4_, p_219561_5_) -> preparationsProfiler.reload(stage, resourceManager2, InactiveProfiler.n_1700_B, InactiveProfiler.n_1700_B, backgroundExecutor, p_219561_5_), alsoWaitedFor);
    }

    protected S_2259_B(Executor backgroundExecutor, final Executor gameExecutor, ResourceManager resourceManager, List<PreparableReloadListener> listeners, n_1700_B<S> stateFactory, CompletableFuture<X_1446_C> alsoWaitedFor) {
        this.n_1700_B = resourceManager;
        this.P_1922_E = listeners.size();
        this.w_1484_f.incrementAndGet();
        alsoWaitedFor.thenRun(this.t_148_a::incrementAndGet);
        ArrayList list = Lists.newArrayList();
        CompletableFuture<X_1446_C> completablefuture = alsoWaitedFor;
        this.G_564_y = Sets.newHashSet(listeners);
        for (final PreparableReloadListener ifuturereloadlistener : listeners) {
            final CompletableFuture<X_1446_C> completablefuture1 = completablefuture;
            CompletableFuture<S> completablefuture2 = stateFactory.create(new PreparableReloadListener.n_1700_B(){

                @Override
                public <T> CompletableFuture<T> markCompleteAwaitingOthers(T backgroundResult) {
                    gameExecutor.execute(() -> {
                        S_2259_B.this.G_564_y.remove(ifuturereloadlistener);
                        if (S_2259_B.this.G_564_y.isEmpty()) {
                            S_2259_B.this.J_1907_R.complete(X_1446_C.n_1700_B);
                        }
                    });
                    return S_2259_B.this.J_1907_R.thenCombine((CompletionStage)completablefuture1, (unit, instance) -> backgroundResult);
                }
            }, resourceManager, ifuturereloadlistener, runnable -> {
                this.w_1484_f.incrementAndGet();
                backgroundExecutor.execute(() -> {
                    runnable.run();
                    this.t_148_a.incrementAndGet();
                });
            }, runnable -> {
                ++this.u_1723_Y;
                gameExecutor.execute(() -> {
                    runnable.run();
                    ++this.v_4262_N;
                });
            });
            list.add(completablefuture2);
            completablefuture = completablefuture2;
        }
        this.R_4764_Y = j_3341_s.J_1907_R(list);
    }

    @Override
    public CompletableFuture<X_1446_C> n_1700_B() {
        return this.R_4764_Y.thenApply(result -> X_1446_C.n_1700_B);
    }

    @Override
    public float J_1907_R() {
        int i = this.P_1922_E - this.G_564_y.size();
        float f = this.t_148_a.get() * 2 + this.v_4262_N * 2 + i * 1;
        float f1 = this.w_1484_f.get() * 2 + this.u_1723_Y * 2 + this.P_1922_E * 1;
        return f / f1;
    }

    @Override
    public boolean R_4764_Y() {
        return this.J_1907_R.isDone();
    }

    @Override
    public boolean G_564_y() {
        return this.R_4764_Y.isDone();
    }

    @Override
    public void P_1922_E() {
        if (this.R_4764_Y.isCompletedExceptionally()) {
            this.R_4764_Y.join();
        }
    }

    public static interface n_1700_B<S> {
        public CompletableFuture<S> create(PreparableReloadListener.n_1700_B var1, ResourceManager var2, PreparableReloadListener var3, Executor var4, Executor var5);
    }
}


