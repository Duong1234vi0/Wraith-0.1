/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Either
 *  it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.longs.LongCollection
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongCollection;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.Y_1387_d;
import lightning.product.y_1195_s;

public class z_1136_g<T> {
    public static final int n_1700_B = y_1195_s.J_1907_R + 2 + 32;
    private final List<Long2ObjectLinkedOpenHashMap<List<Optional<T>>>> J_1907_R = IntStream.range(0, n_1700_B).mapToObj(p_lambda$new$0_0_ -> new Long2ObjectLinkedOpenHashMap()).collect(Collectors.toList());
    private volatile int R_4764_Y = n_1700_B;
    private final String G_564_y;
    private final LongSet P_1922_E = new LongOpenHashSet();
    private final int u_1723_Y;

    public z_1136_g(String queueName, int priority) {
        this.G_564_y = queueName;
        this.u_1723_Y = priority;
    }

    protected void n_1700_B(int p_219407_1_, Y_1387_d pos, int p_219407_3_) {
        if (p_219407_1_ < n_1700_B) {
            Long2ObjectLinkedOpenHashMap<List<Optional<T>>> long2objectlinkedopenhashmap = this.J_1907_R.get(p_219407_1_);
            List list = (List)long2objectlinkedopenhashmap.remove(pos.n_1700_B());
            if (p_219407_1_ == this.R_4764_Y) {
                while (this.R_4764_Y < n_1700_B && this.J_1907_R.get(this.R_4764_Y).isEmpty()) {
                    ++this.R_4764_Y;
                }
            }
            if (list != null && !list.isEmpty()) {
                ((List)this.J_1907_R.get(p_219407_3_).computeIfAbsent(pos.n_1700_B(), p_lambda$func_219407_a$1_0_ -> Lists.newArrayList())).addAll(list);
                this.R_4764_Y = Math.min(this.R_4764_Y, p_219407_3_);
            }
        }
    }

    protected void n_1700_B(Optional<T> task, long chunkPos, int chunkLevel) {
        ((List)this.J_1907_R.get(chunkLevel).computeIfAbsent(chunkPos, p_lambda$addTaskToChunk$2_0_ -> Lists.newArrayList())).add(task);
        this.R_4764_Y = Math.min(this.R_4764_Y, chunkLevel);
    }

    protected void n_1700_B(long chunkPos, boolean fullClear) {
        for (Long2ObjectLinkedOpenHashMap<List<Optional<T>>> long2objectlinkedopenhashmap : this.J_1907_R) {
            List list = (List)long2objectlinkedopenhashmap.get(chunkPos);
            if (list == null) continue;
            if (fullClear) {
                list.clear();
            } else {
                list.removeIf(p_lambda$clearChunkFromQueue$3_0_ -> !p_lambda$clearChunkFromQueue$3_0_.isPresent());
            }
            if (!list.isEmpty()) continue;
            long2objectlinkedopenhashmap.remove(chunkPos);
        }
        while (this.R_4764_Y < n_1700_B && this.J_1907_R.get(this.R_4764_Y).isEmpty()) {
            ++this.R_4764_Y;
        }
        this.P_1922_E.remove(chunkPos);
    }

    private Runnable n_1700_B(long chunkPos) {
        return () -> this.P_1922_E.add(chunkPos);
    }

    @Nullable
    public Stream<Object> n_1700_B() {
        if (this.P_1922_E.size() >= this.u_1723_Y) {
            return null;
        }
        if (this.R_4764_Y >= n_1700_B) {
            return null;
        }
        int i = this.R_4764_Y;
        Long2ObjectLinkedOpenHashMap<List<Optional<T>>> long2objectlinkedopenhashmap = this.J_1907_R.get(i);
        long j = long2objectlinkedopenhashmap.firstLongKey();
        List list = (List)long2objectlinkedopenhashmap.removeFirst();
        while (this.R_4764_Y < n_1700_B && this.J_1907_R.get(this.R_4764_Y).isEmpty()) {
            ++this.R_4764_Y;
        }
        return list.stream().map(p_lambda$func_219417_a$6_3_ -> p_lambda$func_219417_a$6_3_.map(Either::left).orElseGet(() -> Either.right((Object)this.n_1700_B(j))));
    }

    public String toString() {
        return this.G_564_y + " " + this.R_4764_Y + "...";
    }

    @VisibleForTesting
    LongSet J_1907_R() {
        return new LongOpenHashSet((LongCollection)this.P_1922_E);
    }
}

