/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Maps
 *  com.mojang.datafixers.util.Either
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Either;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.P_2395_j;
import lightning.product.StrictQueue;
import lightning.product.U_2912_j;
import lightning.product.U_3758_m;
import lightning.product.X_1446_C;
import lightning.product.Y_1387_d;
import lightning.product.j_3341_s;
import lightning.product.ProcessorHandle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class d_1030_n
implements AutoCloseable {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final AtomicBoolean J_1907_R = new AtomicBoolean();
    private final U_3758_m<StrictQueue.J_1907_R> R_4764_Y;
    private final P_2395_j G_564_y;
    private final Map<Y_1387_d, n_1700_B> P_1922_E = Maps.newLinkedHashMap();

    protected d_1030_n(File p_i231890_1_, boolean p_i231890_2_, String p_i231890_3_) {
        this.G_564_y = new P_2395_j(p_i231890_1_, p_i231890_2_);
        this.R_4764_Y = new U_3758_m<StrictQueue.J_1907_R>(new StrictQueue.n_1700_B(lightning.product.d_1030_n$J_1907_R.values().length), j_3341_s.v_4262_N(), "IOWorker-" + p_i231890_3_);
    }

    public CompletableFuture<Void> n_1700_B(Y_1387_d p_227093_1_, U_2912_j p_227093_2_) {
        return this.n_1700_B(() -> {
            n_1700_B ioworker$entry = this.P_1922_E.computeIfAbsent(p_227093_1_, p_235977_1_ -> new n_1700_B(p_227093_2_));
            ioworker$entry.n_1700_B = p_227093_2_;
            return Either.left(ioworker$entry.J_1907_R);
        }).thenCompose(Function.identity());
    }

    @Nullable
    public U_2912_j n_1700_B(Y_1387_d p_227090_1_) throws IOException {
        CompletableFuture completablefuture = this.n_1700_B(() -> {
            n_1700_B ioworker$entry = this.P_1922_E.get(p_227090_1_);
            if (ioworker$entry != null) {
                return Either.left((Object)ioworker$entry.n_1700_B);
            }
            try {
                U_2912_j compoundnbt = this.G_564_y.n_1700_B(p_227090_1_);
                return Either.left((Object)compoundnbt);
            }
            catch (Exception exception) {
                n_1700_B.warn("Failed to read chunk {}", (Object)p_227090_1_, (Object)exception);
                return Either.right((Object)exception);
            }
        });
        try {
            return (U_2912_j)completablefuture.join();
        }
        catch (CompletionException completionexception) {
            if (completionexception.getCause() instanceof IOException) {
                throw (IOException)completionexception.getCause();
            }
            throw completionexception;
        }
    }

    public CompletableFuture<Void> n_1700_B() {
        CompletionStage completablefuture = this.n_1700_B(() -> Either.left(CompletableFuture.allOf((CompletableFuture[])this.P_1922_E.values().stream().map(p_235973_0_ -> p_235973_0_.J_1907_R).toArray(CompletableFuture[]::new)))).thenCompose(Function.identity());
        return ((CompletableFuture)completablefuture).thenCompose(p_235974_1_ -> this.n_1700_B(() -> {
            try {
                this.G_564_y.n_1700_B();
                return Either.left((Object)null);
            }
            catch (Exception exception) {
                n_1700_B.warn("Failed to synchronized chunks", (Throwable)exception);
                return Either.right((Object)exception);
            }
        }));
    }

    private <T> CompletableFuture<T> n_1700_B(Supplier<Either<T, Exception>> p_235975_1_) {
        return this.R_4764_Y.R_4764_Y(p_235976_2_ -> new StrictQueue.J_1907_R(lightning.product.d_1030_n$J_1907_R.n_1700_B.ordinal(), () -> this.n_1700_B(p_235976_2_, (Supplier)p_235975_1_)));
    }

    private void J_1907_R() {
        Iterator<Map.Entry<Y_1387_d, n_1700_B>> iterator = this.P_1922_E.entrySet().iterator();
        if (iterator.hasNext()) {
            Map.Entry<Y_1387_d, n_1700_B> entry = iterator.next();
            iterator.remove();
            this.n_1700_B(entry.getKey(), entry.getValue());
            this.R_4764_Y();
        }
    }

    private void R_4764_Y() {
        this.R_4764_Y.n_1700_B(new StrictQueue.J_1907_R(lightning.product.d_1030_n$J_1907_R.J_1907_R.ordinal(), this::J_1907_R));
    }

    private void n_1700_B(Y_1387_d p_227091_1_, n_1700_B p_227091_2_) {
        try {
            this.G_564_y.n_1700_B(p_227091_1_, p_227091_2_.n_1700_B);
            p_227091_2_.J_1907_R.complete(null);
        }
        catch (Exception exception) {
            n_1700_B.error("Failed to store chunk {}", (Object)p_227091_1_, (Object)exception);
            p_227091_2_.J_1907_R.completeExceptionally(exception);
        }
    }

    @Override
    public void close() throws IOException {
        if (this.J_1907_R.compareAndSet(false, true)) {
            CompletableFuture completablefuture = this.R_4764_Y.J_1907_R((? super ProcessorHandle<Source> p_235971_0_) -> new StrictQueue.J_1907_R(lightning.product.d_1030_n$J_1907_R.n_1700_B.ordinal(), () -> p_235971_0_.n_1700_B(X_1446_C.n_1700_B)));
            try {
                completablefuture.join();
            }
            catch (CompletionException completionexception) {
                if (completionexception.getCause() instanceof IOException) {
                    throw (IOException)completionexception.getCause();
                }
                throw completionexception;
            }
            this.R_4764_Y.close();
            this.P_1922_E.forEach(this::n_1700_B);
            this.P_1922_E.clear();
            try {
                this.G_564_y.close();
            }
            catch (Exception exception) {
                n_1700_B.error("Failed to close storage", (Throwable)exception);
            }
        }
    }

    private /* synthetic */ void n_1700_B(ProcessorHandle p_235976_2_, Supplier p_235975_1_) {
        if (!this.J_1907_R.get()) {
            p_235976_2_.n_1700_B((Either)p_235975_1_.get());
        }
        this.R_4764_Y();
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] R_4764_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])R_4764_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.d_1030_n$J_1907_R.n_1700_B();
        }
    }

    static class n_1700_B {
        private U_2912_j n_1700_B;
        private final CompletableFuture<Void> J_1907_R = new CompletableFuture();

        public n_1700_B(U_2912_j p_i231891_1_) {
            this.n_1700_B = p_i231891_1_;
        }
    }
}


