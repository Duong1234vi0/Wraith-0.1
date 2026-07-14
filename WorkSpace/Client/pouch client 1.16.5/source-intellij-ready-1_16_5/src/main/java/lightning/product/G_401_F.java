/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Stopwatch
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.base.Stopwatch;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import lightning.product.PreparableReloadListener;
import lightning.product.ResourceManager;
import lightning.product.S_2259_B;
import lightning.product.X_1446_C;
import lightning.product.ProfileResults;
import lightning.product.j_3341_s;
import lightning.product.q_1764_n;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class G_401_F
extends S_2259_B<n_1700_B> {
    private static final Logger G_564_y = LogManager.getLogger();
    private final Stopwatch P_1922_E = Stopwatch.createUnstarted();

    public G_401_F(ResourceManager p_i50694_1_, List<PreparableReloadListener> listeners, Executor backgroundExecutor, Executor gameExecutor, CompletableFuture<X_1446_C> alsoWaitedFor) {
        super(backgroundExecutor, gameExecutor, p_i50694_1_, listeners, (p_219578_1_, p_219578_2_, p_219578_3_, p_219578_4_, p_219578_5_) -> {
            AtomicLong atomiclong = new AtomicLong();
            AtomicLong atomiclong1 = new AtomicLong();
            q_1764_n profiler = new q_1764_n(j_3341_s.n_1700_B, () -> 0, false);
            q_1764_n profiler1 = new q_1764_n(j_3341_s.n_1700_B, () -> 0, false);
            CompletableFuture<Void> completablefuture = p_219578_3_.reload(p_219578_1_, p_219578_2_, profiler, profiler1, p_219577_2_ -> p_219578_4_.execute(() -> {
                long i = j_3341_s.R_4764_Y();
                p_219577_2_.run();
                atomiclong.addAndGet(j_3341_s.R_4764_Y() - i);
            }), p_219574_2_ -> p_219578_5_.execute(() -> {
                long i = j_3341_s.R_4764_Y();
                p_219574_2_.run();
                atomiclong1.addAndGet(j_3341_s.R_4764_Y() - i);
            }));
            return completablefuture.thenApplyAsync(p_219576_5_ -> new n_1700_B(p_219578_3_.i_(), profiler.G_564_y(), profiler1.G_564_y(), atomiclong, atomiclong1), gameExecutor);
        }, alsoWaitedFor);
        this.P_1922_E.start();
        this.R_4764_Y.thenAcceptAsync(this::n_1700_B, gameExecutor);
    }

    private void n_1700_B(List<n_1700_B> datapoints) {
        this.P_1922_E.stop();
        int i = 0;
        G_564_y.info("Resource reload finished after " + this.P_1922_E.elapsed(TimeUnit.MILLISECONDS) + " ms");
        for (n_1700_B debugasyncreloader$datapoint : datapoints) {
            ProfileResults iprofileresult = debugasyncreloader$datapoint.J_1907_R;
            ProfileResults iprofileresult1 = debugasyncreloader$datapoint.R_4764_Y;
            int j = (int)((double)debugasyncreloader$datapoint.G_564_y.get() / 1000000.0);
            int k = (int)((double)debugasyncreloader$datapoint.P_1922_E.get() / 1000000.0);
            int l = j + k;
            String s = debugasyncreloader$datapoint.n_1700_B;
            G_564_y.info(s + " took approximately " + l + " ms (" + j + " ms preparing, " + k + " ms applying)");
            i += k;
        }
        G_564_y.info("Total blocking time: " + i + " ms");
    }

    public static class n_1700_B {
        private final String n_1700_B;
        private final ProfileResults J_1907_R;
        private final ProfileResults R_4764_Y;
        private final AtomicLong G_564_y;
        private final AtomicLong P_1922_E;

        private n_1700_B(String p_i50542_1_, ProfileResults prepareProfResult, ProfileResults applyProfResult, AtomicLong prepareTime, AtomicLong applyTime) {
            this.n_1700_B = p_i50542_1_;
            this.J_1907_R = prepareProfResult;
            this.R_4764_Y = applyProfResult;
            this.G_564_y = prepareTime;
            this.P_1922_E = applyTime;
        }
    }
}


