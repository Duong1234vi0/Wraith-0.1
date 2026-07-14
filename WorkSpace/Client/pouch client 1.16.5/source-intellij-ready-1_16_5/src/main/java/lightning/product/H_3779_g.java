/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.kinds.App
 *  com.mojang.datafixers.kinds.Applicative
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  it.unimi.dsi.fastutil.objects.Object2IntMap
 *  it.unimi.dsi.fastutil.objects.Object2IntMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 */
package lightning.product;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.kinds.App;
import com.mojang.datafixers.kinds.Applicative;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lightning.product.SerializableUUID;
import lightning.product.Q_1649_w;
import lightning.product.j_3341_s;

public class H_3779_g {
    private final Map<UUID, J_1907_R> n_1700_B = Maps.newHashMap();

    public void n_1700_B() {
        Iterator<J_1907_R> iterator = this.n_1700_B.values().iterator();
        while (iterator.hasNext()) {
            J_1907_R gossipmanager$gossips = iterator.next();
            gossipmanager$gossips.n_1700_B();
            if (!gossipmanager$gossips.J_1907_R()) continue;
            iterator.remove();
        }
    }

    private Stream<n_1700_B> J_1907_R() {
        return this.n_1700_B.entrySet().stream().flatMap(uniqueGossipEntry -> ((J_1907_R)uniqueGossipEntry.getValue()).n_1700_B((UUID)uniqueGossipEntry.getKey()));
    }

    private Collection<n_1700_B> n_1700_B(Random rand, int gossipAmount) {
        List list = this.J_1907_R().collect(Collectors.toList());
        if (list.isEmpty()) {
            return Collections.emptyList();
        }
        int[] aint = new int[list.size()];
        int i = 0;
        for (int j = 0; j < list.size(); ++j) {
            n_1700_B gossipmanager$gossipentry = (n_1700_B)list.get(j);
            aint[j] = (i += Math.abs(gossipmanager$gossipentry.n_1700_B())) - 1;
        }
        Set set = Sets.newIdentityHashSet();
        for (int i1 = 0; i1 < gossipAmount; ++i1) {
            int k = rand.nextInt(i);
            int l = Arrays.binarySearch(aint, k);
            set.add((n_1700_B)list.get(l < 0 ? -l - 1 : l));
        }
        return set;
    }

    private J_1907_R n_1700_B(UUID identifier) {
        return this.n_1700_B.computeIfAbsent(identifier, id -> new J_1907_R());
    }

    public void n_1700_B(H_3779_g gossip, Random rand, int gossipAmount) {
        Collection<n_1700_B> collection = gossip.n_1700_B(rand, gossipAmount);
        collection.forEach(gossipEntry -> {
            int i = gossipEntry.R_4764_Y - gossipEntry.J_1907_R.s_956_w;
            if (i >= 2) {
                this.n_1700_B((UUID)gossipEntry.n_1700_B).n_1700_B.mergeInt((Object)gossipEntry.J_1907_R, i, H_3779_g::n_1700_B);
            }
        });
    }

    public int n_1700_B(UUID identifier, Predicate<Q_1649_w> gossip) {
        J_1907_R gossipmanager$gossips = this.n_1700_B.get(identifier);
        return gossipmanager$gossips != null ? gossipmanager$gossips.n_1700_B(gossip) : 0;
    }

    public void n_1700_B(UUID identifier, Q_1649_w gossipType, int gossipValue) {
        J_1907_R gossipmanager$gossips = this.n_1700_B(identifier);
        gossipmanager$gossips.n_1700_B.mergeInt((Object)gossipType, gossipValue, (p_220915_2_, p_220915_3_) -> this.n_1700_B(gossipType, (int)p_220915_2_, (int)p_220915_3_));
        gossipmanager$gossips.n_1700_B(gossipType);
        if (gossipmanager$gossips.J_1907_R()) {
            this.n_1700_B.remove(identifier);
        }
    }

    public <T> Dynamic<T> n_1700_B(DynamicOps<T> dynamic) {
        return new Dynamic(dynamic, dynamic.createList(this.J_1907_R().map(gossipEntry -> gossipEntry.n_1700_B(dynamic)).map(Dynamic::getValue)));
    }

    public void n_1700_B(Dynamic<?> dynamic) {
        dynamic.asStream().map(n_1700_B::n_1700_B).flatMap(p_234056_0_ -> j_3341_s.n_1700_B(p_234056_0_.result())).forEach(gossipEntry -> this.n_1700_B((UUID)gossipEntry.n_1700_B).n_1700_B.put((Object)gossipEntry.J_1907_R, gossipEntry.R_4764_Y));
    }

    private static int n_1700_B(int value1, int value2) {
        return Math.max(value1, value2);
    }

    private int n_1700_B(Q_1649_w gossipTypeIn, int existing, int additive) {
        int i = existing + additive;
        return i > gossipTypeIn.w_1484_f ? Math.max(gossipTypeIn.w_1484_f, existing) : i;
    }

    static class J_1907_R {
        private final Object2IntMap<Q_1649_w> n_1700_B = new Object2IntOpenHashMap();

        private J_1907_R() {
        }

        public int n_1700_B(Predicate<Q_1649_w> gossipType) {
            return this.n_1700_B.object2IntEntrySet().stream().filter(p_220898_1_ -> gossipType.test((Q_1649_w)((Object)((Object)p_220898_1_.getKey())))).mapToInt(p_220894_0_ -> p_220894_0_.getIntValue() * ((Q_1649_w)((Object)((Object)p_220894_0_.getKey()))).v_4262_N).sum();
        }

        public Stream<n_1700_B> n_1700_B(UUID identifier) {
            return this.n_1700_B.object2IntEntrySet().stream().map(p_220897_1_ -> new n_1700_B(identifier, (Q_1649_w)((Object)((Object)p_220897_1_.getKey())), p_220897_1_.getIntValue()));
        }

        public void n_1700_B() {
            ObjectIterator objectiterator = this.n_1700_B.object2IntEntrySet().iterator();
            while (objectiterator.hasNext()) {
                Object2IntMap.Entry entry = (Object2IntMap.Entry)objectiterator.next();
                int i = entry.getIntValue() - ((Q_1649_w)((Object)entry.getKey())).t_148_a;
                if (i < 2) {
                    objectiterator.remove();
                    continue;
                }
                entry.setValue(i);
            }
        }

        public boolean J_1907_R() {
            return this.n_1700_B.isEmpty();
        }

        public void n_1700_B(Q_1649_w gossipType) {
            int i = this.n_1700_B.getInt((Object)gossipType);
            if (i > gossipType.w_1484_f) {
                this.n_1700_B.put((Object)gossipType, gossipType.w_1484_f);
            }
            if (i < 2) {
                this.J_1907_R(gossipType);
            }
        }

        public void J_1907_R(Q_1649_w gossipType) {
            this.n_1700_B.removeInt((Object)gossipType);
        }
    }

    static class n_1700_B {
        public final UUID n_1700_B;
        public final Q_1649_w J_1907_R;
        public final int R_4764_Y;

        public n_1700_B(UUID target, Q_1649_w type, int value) {
            this.n_1700_B = target;
            this.J_1907_R = type;
            this.R_4764_Y = value;
        }

        public int n_1700_B() {
            return this.R_4764_Y * this.J_1907_R.v_4262_N;
        }

        public String toString() {
            return "GossipEntry{target=" + String.valueOf(this.n_1700_B) + ", type=" + String.valueOf((Object)this.J_1907_R) + ", value=" + this.R_4764_Y + "}";
        }

        public <T> Dynamic<T> n_1700_B(DynamicOps<T> dynamic) {
            return new Dynamic(dynamic, dynamic.createMap((Map)ImmutableMap.of((Object)dynamic.createString("Target"), SerializableUUID.n_1700_B.encodeStart(dynamic, (Object)this.n_1700_B).result().orElseThrow(RuntimeException::new), (Object)dynamic.createString("Type"), (Object)dynamic.createString(this.J_1907_R.u_1723_Y), (Object)dynamic.createString("Value"), (Object)dynamic.createInt(this.R_4764_Y))));
        }

        public static DataResult<n_1700_B> n_1700_B(Dynamic<?> dynamic) {
            return DataResult.unbox((App)DataResult.instance().group((App)dynamic.get("Target").read(SerializableUUID.n_1700_B), (App)dynamic.get("Type").asString().map(Q_1649_w::n_1700_B), (App)dynamic.get("Value").asNumber().map(Number::intValue)).apply((Applicative)DataResult.instance(), n_1700_B::new));
        }
    }
}


