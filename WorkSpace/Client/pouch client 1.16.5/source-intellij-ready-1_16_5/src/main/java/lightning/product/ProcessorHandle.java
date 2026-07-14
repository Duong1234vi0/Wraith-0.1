/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.datafixers.util.Either
 */
package lightning.product;

import com.mojang.datafixers.util.Either;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ProcessorHandle<Msg>
extends AutoCloseable {
    public String k_();

    public void n_1700_B(Msg var1);

    @Override
    default public void close() {
    }

    default public <Source> CompletableFuture<Source> J_1907_R(Function<? super ProcessorHandle<Source>, ? extends Msg> p_213141_1_) {
        CompletableFuture completablefuture = new CompletableFuture();
        Msg msg = p_213141_1_.apply(ProcessorHandle.n_1700_B("ask future procesor handle", completablefuture::complete));
        this.n_1700_B(msg);
        return completablefuture;
    }

    default public <Source> CompletableFuture<Source> R_4764_Y(Function<? super ProcessorHandle<Either<Source, Exception>>, ? extends Msg> p_233528_1_) {
        CompletableFuture completablefuture = new CompletableFuture();
        Msg msg = p_233528_1_.apply(ProcessorHandle.n_1700_B("ask future procesor handle", (Msg p_233527_1_) -> {
            p_233527_1_.ifLeft(completablefuture::complete);
            p_233527_1_.ifRight(completablefuture::completeExceptionally);
        }));
        this.n_1700_B(msg);
        return completablefuture;
    }

    public static <Msg> ProcessorHandle<Msg> n_1700_B(final String name, final Consumer<Msg> p_213140_1_) {
        return new ProcessorHandle<Msg>(){

            @Override
            public String k_() {
                return name;
            }

            @Override
            public void n_1700_B(Msg taskIn) {
                p_213140_1_.accept(taskIn);
            }

            public String toString() {
                return name;
            }
        };
    }
}


