/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Sets
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_1630_i;
import lightning.product.J_4805_f;

public class I_4576_W {
    private final Set<n_1700_B> n_1700_B = Sets.newIdentityHashSet();
    private final J_4805_f J_1907_R;
    private final Executor R_4764_Y;

    public I_4576_W(J_4805_f sndSystem, Executor executor) {
        this.J_1907_R = sndSystem;
        this.R_4764_Y = executor;
    }

    public CompletableFuture<n_1700_B> n_1700_B(J_4805_f.R_4764_Y systemMode) {
        CompletableFuture<n_1700_B> completablefuture = new CompletableFuture<n_1700_B>();
        this.R_4764_Y.execute(() -> {
            A_1630_i soundsource = this.J_1907_R.n_1700_B(systemMode);
            if (soundsource != null) {
                n_1700_B channelmanager$entry = new n_1700_B(soundsource);
                this.n_1700_B.add(channelmanager$entry);
                completablefuture.complete(channelmanager$entry);
            } else {
                completablefuture.complete(null);
            }
        });
        return completablefuture;
    }

    public void n_1700_B(Consumer<Stream<A_1630_i>> sourceStreamConsumer) {
        this.R_4764_Y.execute(() -> sourceStreamConsumer.accept(this.n_1700_B.stream().map(managerEntry -> managerEntry.J_1907_R).filter(Objects::nonNull)));
    }

    public void n_1700_B() {
        this.R_4764_Y.execute(() -> {
            Iterator<n_1700_B> iterator = this.n_1700_B.iterator();
            while (iterator.hasNext()) {
                n_1700_B channelmanager$entry = iterator.next();
                channelmanager$entry.J_1907_R.t_148_a();
                if (!channelmanager$entry.J_1907_R.v_4262_N()) continue;
                channelmanager$entry.J_1907_R();
                iterator.remove();
            }
        });
    }

    public void J_1907_R() {
        this.n_1700_B.forEach(n_1700_B::J_1907_R);
        this.n_1700_B.clear();
    }

    public class n_1700_B {
        @Nullable
        private A_1630_i J_1907_R;
        private boolean R_4764_Y;

        public boolean n_1700_B() {
            return this.R_4764_Y;
        }

        public n_1700_B(A_1630_i sound) {
            this.J_1907_R = sound;
        }

        public void n_1700_B(Consumer<A_1630_i> soundConsumer) {
            I_4576_W.this.R_4764_Y.execute(() -> {
                if (this.J_1907_R != null) {
                    soundConsumer.accept(this.J_1907_R);
                }
            });
        }

        public void J_1907_R() {
            this.R_4764_Y = true;
            I_4576_W.this.J_1907_R.n_1700_B(this.J_1907_R);
            this.J_1907_R = null;
        }
    }
}

