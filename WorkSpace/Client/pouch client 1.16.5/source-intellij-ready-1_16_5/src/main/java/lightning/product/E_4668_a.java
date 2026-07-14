/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableList$Builder
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.util.Pair
 *  com.mojang.serialization.Codec
 *  com.mojang.serialization.DataResult
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.MapCodec
 *  com.mojang.serialization.MapLike
 *  com.mojang.serialization.RecordBuilder
 *  it.unimi.dsi.fastutil.objects.ObjectArrayList
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.mutable.MutableObject
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;
import com.mojang.serialization.RecordBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.SensorType;
import lightning.product.O_1984_z;
import lightning.product.S_50_d;
import lightning.product.V_3137_a;
import lightning.product.Sensor;
import lightning.product.e_3591_l;
import lightning.product.Behavior;
import lightning.product.Activity;
import lightning.product.Schedule;
import lightning.product.j_3341_s;
import lightning.product.r_4811_B;
import lightning.product.MemoryModuleType;
import org.apache.commons.lang3.mutable.MutableObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class E_4668_a<E extends r_4811_B> {
    private static final Logger n_1700_B = LogManager.getLogger();
    private final Supplier<Codec<E_4668_a<E>>> J_1907_R;
    private final Map<MemoryModuleType<?>, Optional<? extends O_1984_z<?>>> R_4764_Y = Maps.newHashMap();
    private final Map<SensorType<? extends Sensor<? super E>>, Sensor<? super E>> G_564_y = Maps.newLinkedHashMap();
    private final Map<Integer, Map<Activity, Set<Behavior<? super E>>>> P_1922_E = Maps.newTreeMap();
    private Schedule u_1723_Y = Schedule.n_1700_B;
    private final Map<Activity, Set<Pair<MemoryModuleType<?>, S_50_d>>> v_4262_N = Maps.newHashMap();
    private final Map<Activity, Set<MemoryModuleType<?>>> w_1484_f = Maps.newHashMap();
    private Set<Activity> t_148_a = Sets.newHashSet();
    private final Set<Activity> s_956_w = Sets.newHashSet();
    private Activity u_2550_I = Activity.J_1907_R;
    private long M_588_G = -9999L;

    public static <E extends r_4811_B> n_1700_B<E> n_1700_B(Collection<? extends MemoryModuleType<?>> memoryTypes, Collection<? extends SensorType<? extends Sensor<? super E>>> sensorTypes) {
        return new n_1700_B(memoryTypes, sensorTypes);
    }

    public static <E extends r_4811_B> Codec<E_4668_a<E>> J_1907_R(final Collection<? extends MemoryModuleType<?>> memoryTypes, final Collection<? extends SensorType<? extends Sensor<? super E>>> sensorTypes) {
        final MutableObject mutableobject = new MutableObject();
        mutableobject.setValue((Object)new MapCodec<E_4668_a<E>>(){

            public <T> Stream<T> keys(DynamicOps<T> p_keys_1_) {
                return memoryTypes.stream().flatMap(memoryType -> j_3341_s.n_1700_B(memoryType.n_1700_B().map(memoryCodec -> V_3137_a.i_1637_u.J_1907_R((MemoryModuleType<?>)memoryType)))).map(memoryTypeKey -> p_keys_1_.createString(memoryTypeKey.toString()));
            }

            public <T> DataResult<E_4668_a<E>> decode(DynamicOps<T> p_decode_1_, MapLike<T> p_decode_2_) {
                MutableObject mutableobject1 = new MutableObject((Object)DataResult.success((Object)ImmutableList.builder()));
                p_decode_2_.entries().forEach(inputPair -> {
                    DataResult dataresult = V_3137_a.i_1637_u.parse(p_decode_1_, inputPair.getFirst());
                    DataResult dataresult1 = dataresult.flatMap(memoryType -> this.n_1700_B((MemoryModuleType)memoryType, p_decode_1_, (Object)inputPair.getSecond()));
                    mutableobject1.setValue((Object)((DataResult)mutableobject1.getValue()).apply2(ImmutableList.Builder::add, dataresult1));
                });
                ImmutableList immutablelist = ((DataResult)mutableobject1.getValue()).resultOrPartial(arg_0 -> ((Logger)n_1700_B).error(arg_0)).map(ImmutableList.Builder::build).orElseGet(ImmutableList::of);
                return DataResult.success(new E_4668_a(memoryTypes, sensorTypes, immutablelist, () -> ((MutableObject)mutableobject).getValue()));
            }

            private <T, U> DataResult<J_1907_R<U>> n_1700_B(MemoryModuleType<U> memoryType, DynamicOps<T> ops, T input) {
                return memoryType.n_1700_B().map(DataResult::success).orElseGet(() -> DataResult.error((String)("No codec for memory: " + String.valueOf(memoryType)))).flatMap(memoryCodec -> memoryCodec.parse(ops, input)).map(memory -> new J_1907_R(memoryType, Optional.of(memory)));
            }

            public <T> RecordBuilder<T> n_1700_B(E_4668_a<E> p_encode_1_, DynamicOps<T> p_encode_2_, RecordBuilder<T> p_encode_3_) {
                p_encode_1_.u_1723_Y().forEach(memoryCodec -> memoryCodec.n_1700_B(p_encode_2_, p_encode_3_));
                return p_encode_3_;
            }

            public /* synthetic */ RecordBuilder encode(Object object, DynamicOps dynamicOps, RecordBuilder recordBuilder) {
                return this.n_1700_B((E_4668_a)object, dynamicOps, recordBuilder);
            }
        }.fieldOf("memories").codec());
        return (Codec)mutableobject.getValue();
    }

    public E_4668_a(Collection<? extends MemoryModuleType<?>> memories, Collection<? extends SensorType<? extends Sensor<? super E>>> sensors, ImmutableList<J_1907_R<?>> memoryCodecs, Supplier<Codec<E_4668_a<E>>> brainCodec) {
        this.J_1907_R = brainCodec;
        for (MemoryModuleType<?> v_285_I2 : memories) {
            this.R_4764_Y.put(v_285_I2, Optional.empty());
        }
        for (SensorType d_4691_X : sensors) {
            this.G_564_y.put(d_4691_X, (Sensor<E>)d_4691_X.n_1700_B());
        }
        for (Sensor b_4238_N2 : this.G_564_y.values()) {
            for (MemoryModuleType<?> memorymoduletype1 : b_4238_N2.n_1700_B()) {
                this.R_4764_Y.put(memorymoduletype1, Optional.empty());
            }
        }
        for (J_1907_R j_1907_R : memoryCodecs) {
            j_1907_R.n_1700_B(this);
        }
    }

    public <T> DataResult<T> n_1700_B(DynamicOps<T> ops) {
        return this.J_1907_R.get().encodeStart(ops, (Object)this);
    }

    private Stream<J_1907_R<?>> u_1723_Y() {
        return this.R_4764_Y.entrySet().stream().map(entry -> lightning.product.E_4668_a$J_1907_R.n_1700_B((MemoryModuleType)entry.getKey(), (Optional)entry.getValue()));
    }

    public boolean n_1700_B(MemoryModuleType<?> typeIn) {
        return this.n_1700_B(typeIn, S_50_d.n_1700_B);
    }

    public <U> void J_1907_R(MemoryModuleType<U> type) {
        this.n_1700_B(type, Optional.empty());
    }

    public <U> void n_1700_B(MemoryModuleType<U> memoryType, @Nullable U memory) {
        this.n_1700_B(memoryType, Optional.ofNullable(memory));
    }

    public <U> void n_1700_B(MemoryModuleType<U> memoryType, U memory, long timesToLive) {
        this.J_1907_R(memoryType, Optional.of(O_1984_z.n_1700_B(memory, timesToLive)));
    }

    public <U> void n_1700_B(MemoryModuleType<U> memoryType, Optional<? extends U> memory) {
        this.J_1907_R(memoryType, memory.map(O_1984_z::n_1700_B));
    }

    private <U> void J_1907_R(MemoryModuleType<U> memoryType, Optional<? extends O_1984_z<?>> memory) {
        if (this.R_4764_Y.containsKey(memoryType)) {
            if (memory.isPresent() && this.n_1700_B(memory.get().J_1907_R())) {
                this.J_1907_R(memoryType);
            } else {
                this.R_4764_Y.put(memoryType, memory);
            }
        }
    }

    public <U> Optional<U> R_4764_Y(MemoryModuleType<U> type) {
        return this.R_4764_Y.get(type).map(O_1984_z::J_1907_R);
    }

    public <U> boolean J_1907_R(MemoryModuleType<U> memoryType, U memory) {
        return !this.n_1700_B(memoryType) ? false : this.R_4764_Y(memoryType).filter(memoryIn -> memoryIn.equals(memory)).isPresent();
    }

    public boolean n_1700_B(MemoryModuleType<?> memoryTypeIn, S_50_d memoryStatusIn) {
        Optional<O_1984_z<?>> optional = this.R_4764_Y.get(memoryTypeIn);
        if (optional == null) {
            return false;
        }
        return memoryStatusIn == S_50_d.R_4764_Y || memoryStatusIn == S_50_d.n_1700_B && optional.isPresent() || memoryStatusIn == S_50_d.J_1907_R && !optional.isPresent();
    }

    public Schedule n_1700_B() {
        return this.u_1723_Y;
    }

    public void n_1700_B(Schedule newSchedule) {
        this.u_1723_Y = newSchedule;
    }

    public void n_1700_B(Set<Activity> newActivities) {
        this.t_148_a = newActivities;
    }

    @Deprecated
    public List<Behavior<? super E>> J_1907_R() {
        ObjectArrayList list = new ObjectArrayList();
        for (Map<Activity, Set<Behavior<E>>> map : this.P_1922_E.values()) {
            for (Set<Behavior<E>> set : map.values()) {
                for (Behavior<E> task : set) {
                    if (task.n_1700_B() != Behavior.n_1700_B.J_1907_R) continue;
                    list.add(task);
                }
            }
        }
        return list;
    }

    public void R_4764_Y() {
        this.G_564_y(this.u_2550_I);
    }

    public Optional<Activity> G_564_y() {
        for (Activity activity : this.s_956_w) {
            if (this.t_148_a.contains(activity)) continue;
            return Optional.of(activity);
        }
        return Optional.empty();
    }

    public void n_1700_B(Activity activityIn) {
        if (this.u_1723_Y(activityIn)) {
            this.G_564_y(activityIn);
        } else {
            this.R_4764_Y();
        }
    }

    private void G_564_y(Activity activity) {
        if (!this.R_4764_Y(activity)) {
            this.P_1922_E(activity);
            this.s_956_w.clear();
            this.s_956_w.addAll(this.t_148_a);
            this.s_956_w.add(activity);
        }
    }

    private void P_1922_E(Activity activityIn) {
        for (Activity activity : this.s_956_w) {
            Set<MemoryModuleType<?>> set;
            if (activity == activityIn || (set = this.w_1484_f.get(activity)) == null) continue;
            for (MemoryModuleType<?> memorymoduletype : set) {
                this.J_1907_R(memorymoduletype);
            }
        }
    }

    public void n_1700_B(long dayTime, long gameTime) {
        if (gameTime - this.M_588_G > 20L) {
            this.M_588_G = gameTime;
            Activity activity = this.n_1700_B().n_1700_B((int)(dayTime % 24000L));
            if (!this.s_956_w.contains(activity)) {
                this.n_1700_B(activity);
            }
        }
    }

    public void n_1700_B(List<Activity> activities) {
        for (Activity activity : activities) {
            if (!this.u_1723_Y(activity)) continue;
            this.G_564_y(activity);
            break;
        }
    }

    public void J_1907_R(Activity newFallbackActivity) {
        this.u_2550_I = newFallbackActivity;
    }

    public void n_1700_B(Activity activity, int priorityStart, ImmutableList<? extends Behavior<? super E>> tasks) {
        this.n_1700_B(activity, this.n_1700_B(priorityStart, tasks));
    }

    public void n_1700_B(Activity activity, int priorityStart, ImmutableList<? extends Behavior<? super E>> tasks, MemoryModuleType<?> memoryType) {
        ImmutableSet set = ImmutableSet.of((Object)Pair.of(memoryType, (Object)((Object)S_50_d.n_1700_B)));
        ImmutableSet set1 = ImmutableSet.of(memoryType);
        this.n_1700_B(activity, (ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>>)this.n_1700_B(priorityStart, tasks), (Set<Pair<MemoryModuleType<?>, S_50_d>>)set, (Set<MemoryModuleType<?>>)set1);
    }

    public void n_1700_B(Activity activityIn, ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> tasks) {
        this.n_1700_B(activityIn, tasks, (Set<Pair<MemoryModuleType<?>, S_50_d>>)ImmutableSet.of(), Sets.newHashSet());
    }

    public void n_1700_B(Activity activity, ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> tasks, Set<Pair<MemoryModuleType<?>, S_50_d>> memoryStatuses) {
        this.n_1700_B(activity, tasks, memoryStatuses, Sets.newHashSet());
    }

    private void n_1700_B(Activity activity, ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> tasks, Set<Pair<MemoryModuleType<?>, S_50_d>> memorieStatuses, Set<MemoryModuleType<?>> memoryTypes) {
        this.v_4262_N.put(activity, memorieStatuses);
        if (!memoryTypes.isEmpty()) {
            this.w_1484_f.put(activity, memoryTypes);
        }
        for (Pair pair : tasks) {
            this.P_1922_E.computeIfAbsent((Integer)pair.getFirst(), activityPriority -> Maps.newHashMap()).computeIfAbsent(activity, activityIn -> Sets.newLinkedHashSet()).add((Behavior)pair.getSecond());
        }
    }

    public boolean R_4764_Y(Activity activityIn) {
        return this.s_956_w.contains(activityIn);
    }

    public E_4668_a<E> P_1922_E() {
        E_4668_a<E> brain = new E_4668_a<E>(this.R_4764_Y.keySet(), this.G_564_y.keySet(), ImmutableList.of(), this.J_1907_R);
        for (Map.Entry<MemoryModuleType<?>, Optional<O_1984_z<?>>> entry : this.R_4764_Y.entrySet()) {
            MemoryModuleType<?> memorymoduletype = entry.getKey();
            if (!entry.getValue().isPresent()) continue;
            brain.R_4764_Y.put(memorymoduletype, entry.getValue());
        }
        return brain;
    }

    public void n_1700_B(e_3591_l worldIn, E entityIn) {
        this.v_4262_N();
        this.R_4764_Y(worldIn, entityIn);
        this.G_564_y(worldIn, entityIn);
        this.P_1922_E(worldIn, entityIn);
    }

    private void R_4764_Y(e_3591_l world, E brainHolder) {
        for (Sensor<E> sensor : this.G_564_y.values()) {
            sensor.J_1907_R(world, brainHolder);
        }
    }

    private void v_4262_N() {
        for (Map.Entry<MemoryModuleType<?>, Optional<O_1984_z<?>>> entry : this.R_4764_Y.entrySet()) {
            if (!entry.getValue().isPresent()) continue;
            O_1984_z<?> memory = entry.getValue().get();
            memory.n_1700_B();
            if (!memory.R_4764_Y()) continue;
            this.J_1907_R(entry.getKey());
        }
    }

    public void J_1907_R(e_3591_l worldIn, E owner) {
        long i = ((r_4811_B)owner).O_508_d.X_933_l();
        for (Behavior<E> task : this.J_1907_R()) {
            task.v_4262_N(worldIn, owner, i);
        }
    }

    private void G_564_y(e_3591_l worldIn, E entityIn) {
        long i = worldIn.X_933_l();
        for (Map<Activity, Set<Behavior<E>>> map : this.P_1922_E.values()) {
            for (Map.Entry<Activity, Set<Behavior<E>>> entry : map.entrySet()) {
                Activity activity = entry.getKey();
                if (!this.s_956_w.contains(activity)) continue;
                for (Behavior<E> task : entry.getValue()) {
                    if (task.n_1700_B() != Behavior.n_1700_B.n_1700_B) continue;
                    task.P_1922_E(worldIn, entityIn, i);
                }
            }
        }
    }

    private void P_1922_E(e_3591_l worldIn, E entityIn) {
        long i = worldIn.X_933_l();
        for (Behavior<E> task : this.J_1907_R()) {
            task.u_1723_Y(worldIn, entityIn, i);
        }
    }

    private boolean u_1723_Y(Activity activityIn) {
        if (!this.v_4262_N.containsKey(activityIn)) {
            return false;
        }
        for (Pair<MemoryModuleType<?>, S_50_d> pair : this.v_4262_N.get(activityIn)) {
            S_50_d memorymodulestatus;
            MemoryModuleType memorymoduletype = (MemoryModuleType)pair.getFirst();
            if (this.n_1700_B(memorymoduletype, memorymodulestatus = (S_50_d)((Object)pair.getSecond()))) continue;
            return false;
        }
        return true;
    }

    private boolean n_1700_B(Object collection) {
        return collection instanceof Collection && ((Collection)collection).isEmpty();
    }

    ImmutableList<? extends Pair<Integer, ? extends Behavior<? super E>>> n_1700_B(int priorityStart, ImmutableList<? extends Behavior<? super E>> tasks) {
        int i = priorityStart;
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Behavior task : tasks) {
            builder.add((Object)Pair.of((Object)i++, (Object)task));
        }
        return builder.build();
    }

    public static final class n_1700_B<E extends r_4811_B> {
        private final Collection<? extends MemoryModuleType<?>> n_1700_B;
        private final Collection<? extends SensorType<? extends Sensor<? super E>>> J_1907_R;
        private final Codec<E_4668_a<E>> R_4764_Y;

        private n_1700_B(Collection<? extends MemoryModuleType<?>> memoryTypes, Collection<? extends SensorType<? extends Sensor<? super E>>> sensorTypes) {
            this.n_1700_B = memoryTypes;
            this.J_1907_R = sensorTypes;
            this.R_4764_Y = E_4668_a.J_1907_R(memoryTypes, sensorTypes);
        }

        public E_4668_a<E> n_1700_B(Dynamic<?> ops) {
            return this.R_4764_Y.parse(ops).resultOrPartial(arg_0 -> ((Logger)n_1700_B).error(arg_0)).orElseGet(() -> new E_4668_a(this.n_1700_B, this.J_1907_R, ImmutableList.of(), () -> this.R_4764_Y));
        }
    }

    static final class J_1907_R<U> {
        private final MemoryModuleType<U> n_1700_B;
        private final Optional<? extends O_1984_z<U>> J_1907_R;

        private static <U> J_1907_R<U> n_1700_B(MemoryModuleType<U> memoryType, Optional<? extends O_1984_z<?>> memory) {
            return new J_1907_R<U>(memoryType, memory);
        }

        private J_1907_R(MemoryModuleType<U> memoryType, Optional<? extends O_1984_z<U>> memory) {
            this.n_1700_B = memoryType;
            this.J_1907_R = memory;
        }

        private void n_1700_B(E_4668_a<?> brain) {
            brain.J_1907_R(this.n_1700_B, this.J_1907_R);
        }

        public <T> void n_1700_B(DynamicOps<T> ops, RecordBuilder<T> builder) {
            this.n_1700_B.n_1700_B().ifPresent(memoryCodec -> this.J_1907_R.ifPresent(memory -> builder.add(V_3137_a.i_1637_u.encodeStart(ops, this.n_1700_B), memoryCodec.encodeStart(ops, memory))));
        }
    }
}


