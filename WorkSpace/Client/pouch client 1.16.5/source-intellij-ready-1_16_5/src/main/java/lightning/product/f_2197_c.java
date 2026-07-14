/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.Sets
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.StrictQueue;
import lightning.product.U_3758_m;
import lightning.product.X_1446_C;
import lightning.product.Y_1387_d;
import lightning.product.j_3341_s;
import lightning.product.ProcessorHandle;
import lightning.product.y_3683_b;
import lightning.product.z_1136_g;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class f_2197_c
implements AutoCloseable,
y_3683_b.J_1907_R {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Map<ProcessorHandle<?>, z_1136_g<? extends Function<ProcessorHandle<X_1446_C>, ?>>> J_1907_R;
    private final Set<ProcessorHandle<?>> R_4764_Y;
    private final U_3758_m<StrictQueue.J_1907_R> G_564_y;

    public f_2197_c(List<ProcessorHandle<?>> p_i50713_1_, Executor p_i50713_2_, int p_i50713_3_) {
        this.J_1907_R = p_i50713_1_.stream().collect(Collectors.toMap(Function.identity(), p_219084_1_ -> new z_1136_g(p_219084_1_.k_() + "_queue", p_i50713_3_)));
        this.R_4764_Y = Sets.newHashSet(p_i50713_1_);
        this.G_564_y = new U_3758_m<StrictQueue.J_1907_R>(new StrictQueue.n_1700_B(4), p_i50713_2_, "sorter");
    }

    public static n_1700_B<Runnable> n_1700_B(Runnable p_219069_0_, long pos, IntSupplier p_219069_3_) {
        return new n_1700_B<Runnable>(p_219072_1_ -> () -> {
            p_219069_0_.run();
            p_219072_1_.n_1700_B(X_1446_C.n_1700_B);
        }, pos, p_219069_3_);
    }

    public static n_1700_B<Runnable> n_1700_B(y_3683_b p_219081_0_, Runnable p_219081_1_) {
        return f_2197_c.n_1700_B(p_219081_1_, p_219081_0_.t_148_a().n_1700_B(), p_219081_0_::u_2550_I);
    }

    public static J_1907_R n_1700_B(Runnable p_219073_0_, long p_219073_1_, boolean p_219073_3_) {
        return new J_1907_R(p_219073_0_, p_219073_1_, p_219073_3_);
    }

    public <T> ProcessorHandle<n_1700_B<T>> n_1700_B(ProcessorHandle<T> p_219087_1_, boolean p_219087_2_) {
        return (ProcessorHandle)this.G_564_y.J_1907_R((? super ProcessorHandle<Source> p_219086_3_) -> new StrictQueue.J_1907_R(0, () -> {
            this.J_1907_R(p_219087_1_);
            p_219086_3_.n_1700_B(ProcessorHandle.n_1700_B("chunk priority sorter around " + p_219087_1_.k_(), (Msg p_219071_3_) -> this.n_1700_B(p_219087_1_, p_219071_3_.n_1700_B, p_219071_3_.J_1907_R, p_219071_3_.R_4764_Y, p_219087_2_)));
        })).join();
    }

    public ProcessorHandle<J_1907_R> n_1700_B(ProcessorHandle<Runnable> p_219091_1_) {
        return (ProcessorHandle)this.G_564_y.J_1907_R((? super ProcessorHandle<Source> p_219080_2_) -> new StrictQueue.J_1907_R(0, () -> p_219080_2_.n_1700_B(ProcessorHandle.n_1700_B("chunk priority sorter around " + p_219091_1_.k_(), (Msg p_219075_2_) -> this.n_1700_B(p_219091_1_, p_219075_2_.J_1907_R, p_219075_2_.n_1700_B, p_219075_2_.R_4764_Y))))).join();
    }

    @Override
    public void n_1700_B(Y_1387_d pos, IntSupplier p_219066_2_, int p_219066_3_, IntConsumer p_219066_4_) {
        this.G_564_y.n_1700_B(new StrictQueue.J_1907_R(0, () -> {
            int i = p_219066_2_.getAsInt();
            this.J_1907_R.values().forEach(p_219076_3_ -> p_219076_3_.n_1700_B(i, pos, p_219066_3_));
            p_219066_4_.accept(p_219066_3_);
        }));
    }

    private <T> void n_1700_B(ProcessorHandle<T> p_219074_1_, long p_219074_2_, Runnable p_219074_4_, boolean p_219074_5_) {
        this.G_564_y.n_1700_B(new StrictQueue.J_1907_R(1, () -> {
            z_1136_g chunktaskpriorityqueue = this.J_1907_R(p_219074_1_);
            chunktaskpriorityqueue.n_1700_B(p_219074_2_, p_219074_5_);
            if (this.R_4764_Y.remove(p_219074_1_)) {
                this.n_1700_B(chunktaskpriorityqueue, p_219074_1_);
            }
            p_219074_4_.run();
        }));
    }

    private <T> void n_1700_B(ProcessorHandle<T> p_219067_1_, Function<ProcessorHandle<X_1446_C>, T> p_219067_2_, long p_219067_3_, IntSupplier p_219067_5_, boolean p_219067_6_) {
        this.G_564_y.n_1700_B(new StrictQueue.J_1907_R(2, () -> {
            z_1136_g chunktaskpriorityqueue = this.J_1907_R(p_219067_1_);
            int i = p_219067_5_.getAsInt();
            chunktaskpriorityqueue.n_1700_B(Optional.of(p_219067_2_), p_219067_3_, i);
            if (p_219067_6_) {
                chunktaskpriorityqueue.n_1700_B(Optional.empty(), p_219067_3_, i);
            }
            if (this.R_4764_Y.remove(p_219067_1_)) {
                this.n_1700_B(chunktaskpriorityqueue, p_219067_1_);
            }
        }));
    }

    private <T> void n_1700_B(z_1136_g<Function<ProcessorHandle<X_1446_C>, T>> p_219078_1_, ProcessorHandle<T> p_219078_2_) {
        this.G_564_y.n_1700_B(new StrictQueue.J_1907_R(3, () -> {
            Stream<Object> stream = p_219078_1_.n_1700_B();
            if (stream == null) {
                this.R_4764_Y.add(p_219078_2_);
            } else {
                j_3341_s.J_1907_R(stream.map(p_219092_1_ -> (CompletableFuture)p_219092_1_.map(p_219078_2_::J_1907_R, p_219077_0_ -> {
                    p_219077_0_.run();
                    return CompletableFuture.completedFuture(X_1446_C.n_1700_B);
                })).collect(Collectors.toList())).thenAccept(p_219088_3_ -> this.n_1700_B(p_219078_1_, p_219078_2_));
            }
        }));
    }

    private <T> z_1136_g<Function<ProcessorHandle<X_1446_C>, T>> J_1907_R(ProcessorHandle<T> p_219068_1_) {
        z_1136_g<Function<ProcessorHandle<X_1446_C>, T>> chunktaskpriorityqueue = this.J_1907_R.get(p_219068_1_);
        if (chunktaskpriorityqueue == null) {
            throw j_3341_s.R_4764_Y(new IllegalArgumentException("No queue for: " + String.valueOf(p_219068_1_)));
        }
        return chunktaskpriorityqueue;
    }

    @VisibleForTesting
    public String n_1700_B() {
        return this.J_1907_R.entrySet().stream().map(p_225397_0_ -> ((ProcessorHandle)p_225397_0_.getKey()).k_() + "=[" + ((z_1136_g)p_225397_0_.getValue()).J_1907_R().stream().map(p_225398_0_ -> p_225398_0_ + ":" + String.valueOf(new Y_1387_d((long)p_225398_0_))).collect(Collectors.joining(",")) + "]").collect(Collectors.joining(",")) + ", s=" + this.R_4764_Y.size();
    }

    @Override
    public void close() {
        this.J_1907_R.keySet().forEach(ProcessorHandle::close);
    }

    public static final class n_1700_B<T> {
        private final Function<ProcessorHandle<X_1446_C>, T> n_1700_B;
        private final long J_1907_R;
        private final IntSupplier R_4764_Y;

        private n_1700_B(Function<ProcessorHandle<X_1446_C>, T> p_i50028_1_, long p_i50028_2_, IntSupplier p_i50028_4_) {
            this.n_1700_B = p_i50028_1_;
            this.J_1907_R = p_i50028_2_;
            this.R_4764_Y = p_i50028_4_;
        }
    }

    public static final class J_1907_R {
        private final Runnable n_1700_B;
        private final long J_1907_R;
        private final boolean R_4764_Y;

        private J_1907_R(Runnable p_i50026_1_, long p_i50026_2_, boolean p_i50026_4_) {
            this.n_1700_B = p_i50026_1_;
            this.J_1907_R = p_i50026_2_;
            this.R_4764_Y = p_i50026_4_;
        }
    }
}


