/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Queues
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Queues;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Nullable;

public interface StrictQueue<T, F> {
    @Nullable
    public F n_1700_B();

    public boolean n_1700_B(T var1);

    public boolean J_1907_R();

    public static final class R_4764_Y<T>
    implements StrictQueue<T, T> {
        private final Queue<T> n_1700_B;

        public R_4764_Y(Queue<T> queueIn) {
            this.n_1700_B = queueIn;
        }

        @Override
        @Nullable
        public T n_1700_B() {
            return this.n_1700_B.poll();
        }

        @Override
        public boolean n_1700_B(T value) {
            return this.n_1700_B.add(value);
        }

        @Override
        public boolean J_1907_R() {
            return this.n_1700_B.isEmpty();
        }
    }

    public static final class J_1907_R
    implements Runnable {
        private final int n_1700_B;
        private final Runnable J_1907_R;

        public J_1907_R(int priorityIn, Runnable runnableIn) {
            this.n_1700_B = priorityIn;
            this.J_1907_R = runnableIn;
        }

        @Override
        public void run() {
            this.J_1907_R.run();
        }

        public int n_1700_B() {
            return this.n_1700_B;
        }
    }

    public static final class n_1700_B
    implements StrictQueue<J_1907_R, Runnable> {
        private final List<ConcurrentLinkedQueue<Runnable>> n_1700_B;

        public n_1700_B(int queueCount) {
            this.n_1700_B = IntStream.range(0, queueCount).mapToObj(p_219948_0_ -> Queues.newConcurrentLinkedQueue()).collect(Collectors.toList());
        }

        @Nullable
        public Runnable R_4764_Y() {
            for (ConcurrentLinkedQueue<Runnable> queue : this.n_1700_B) {
                Runnable runnable = queue.poll();
                if (runnable == null) continue;
                return runnable;
            }
            return null;
        }

        @Override
        public boolean n_1700_B(J_1907_R value) {
            int i = value.n_1700_B();
            this.n_1700_B.get(i).add(value);
            return true;
        }

        @Override
        public boolean J_1907_R() {
            return this.n_1700_B.stream().allMatch(Collection::isEmpty);
        }

        @Override
        @Nullable
        public /* synthetic */ Object n_1700_B() {
            return this.R_4764_Y();
        }
    }
}


