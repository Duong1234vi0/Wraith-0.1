/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.util.Either
 *  it.unimi.dsi.fastutil.longs.Long2ByteMap
 *  it.unimi.dsi.fastutil.longs.Long2ByteMap$Entry
 *  it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2IntMap
 *  it.unimi.dsi.fastutil.longs.Long2IntMaps
 *  it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.longs.LongIterator
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  it.unimi.dsi.fastutil.objects.ObjectOpenHashSet
 *  it.unimi.dsi.fastutil.objects.ObjectSet
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.longs.Long2ByteMap;
import it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2IntMap;
import it.unimi.dsi.fastutil.longs.Long2IntMaps;
import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import it.unimi.dsi.fastutil.objects.ObjectSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.ChunkStatus;
import lightning.product.H_1748_a;
import lightning.product.Y_1387_d;
import lightning.product.SectionPos;
import lightning.product.f_2197_c;
import lightning.product.TicketType;
import lightning.product.n_4663_J;
import lightning.product.r_3890_d;
import lightning.product.t_4022_o;
import lightning.product.ProcessorHandle;
import lightning.product.y_1195_s;
import lightning.product.y_3683_b;
import net.optifine.reflect.Reflector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class l_4108_L {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final int J_1907_R = 33 + ChunkStatus.n_1700_B(ChunkStatus.P_4830_p) - 2;
    private final Long2ObjectMap<ObjectSet<B_4088_l>> R_4764_Y = new Long2ObjectOpenHashMap();
    private final Long2ObjectOpenHashMap<t_4022_o<r_3890_d<?>>> G_564_y = new Long2ObjectOpenHashMap();
    private final n_1700_B P_1922_E = new n_1700_B();
    private final J_1907_R u_1723_Y = new J_1907_R(8);
    private final R_4764_Y v_4262_N = new R_4764_Y(65);
    private final Set<y_3683_b> w_1484_f = Sets.newHashSet();
    private final f_2197_c t_148_a;
    private final ProcessorHandle<f_2197_c.n_1700_B<Runnable>> s_956_w;
    private final ProcessorHandle<f_2197_c.J_1907_R> u_2550_I;
    private final LongSet M_588_G = new LongOpenHashSet();
    private final Executor P_4830_p;
    private long h_1847_R;
    private final Long2ObjectOpenHashMap<t_4022_o<r_3890_d<?>>> Q_4569_t = new Long2ObjectOpenHashMap();

    protected l_4108_L(Executor p_i50707_1_, Executor p_i50707_2_) {
        f_2197_c chunktaskpriorityqueuesorter;
        ProcessorHandle<Runnable> itaskexecutor = ProcessorHandle.n_1700_B("player ticket throttler", p_i50707_2_::execute);
        this.t_148_a = chunktaskpriorityqueuesorter = new f_2197_c((List<ProcessorHandle<?>>)ImmutableList.of(itaskexecutor), p_i50707_1_, 4);
        this.s_956_w = chunktaskpriorityqueuesorter.n_1700_B(itaskexecutor, true);
        this.u_2550_I = chunktaskpriorityqueuesorter.n_1700_B(itaskexecutor);
        this.P_4830_p = p_i50707_2_;
    }

    protected void n_1700_B() {
        ++this.h_1847_R;
        ObjectIterator objectiterator = this.G_564_y.long2ObjectEntrySet().fastIterator();
        while (objectiterator.hasNext()) {
            Long2ObjectMap.Entry entry = (Long2ObjectMap.Entry)objectiterator.next();
            if (((t_4022_o)entry.getValue()).removeIf(p_lambda$tick$0_1_ -> p_lambda$tick$0_1_.J_1907_R(this.h_1847_R))) {
                this.P_1922_E.J_1907_R(entry.getLongKey(), l_4108_L.n_1700_B((t_4022_o)entry.getValue()), false);
            }
            if (!((t_4022_o)entry.getValue()).isEmpty()) continue;
            objectiterator.remove();
        }
    }

    private static int n_1700_B(t_4022_o<r_3890_d<?>> p_229844_0_) {
        return !p_229844_0_.isEmpty() ? p_229844_0_.n_1700_B().J_1907_R() : y_1195_s.J_1907_R + 1;
    }

    protected abstract boolean n_1700_B(long var1);

    @Nullable
    protected abstract y_3683_b J_1907_R(long var1);

    @Nullable
    protected abstract y_3683_b n_1700_B(long var1, int var3, @Nullable y_3683_b var4, int var5);

    public boolean n_1700_B(y_1195_s chunkManager) {
        boolean flag;
        this.u_1723_Y.n_1700_B();
        this.v_4262_N.n_1700_B();
        int i = Integer.MAX_VALUE - this.P_1922_E.J_1907_R(Integer.MAX_VALUE);
        boolean bl = flag = i != 0;
        if (flag) {
            // empty if block
        }
        if (!this.w_1484_f.isEmpty()) {
            this.w_1484_f.forEach(p_lambda$processUpdates$1_1_ -> p_lambda$processUpdates$1_1_.n_1700_B(chunkManager));
            this.w_1484_f.clear();
            return true;
        }
        if (!this.M_588_G.isEmpty()) {
            LongIterator longiterator = this.M_588_G.iterator();
            while (longiterator.hasNext()) {
                long j = longiterator.nextLong();
                if (!this.u_1723_Y(j).stream().anyMatch(p_lambda$processUpdates$2_0_ -> p_lambda$processUpdates$2_0_.n_1700_B() == TicketType.R_4764_Y)) continue;
                y_3683_b chunkholder = chunkManager.n_1700_B(j);
                if (chunkholder == null) {
                    throw new IllegalStateException();
                }
                CompletableFuture<Either<H_1748_a, y_3683_b.n_1700_B>> completablefuture = chunkholder.J_1907_R();
                completablefuture.thenAccept(p_lambda$processUpdates$5_3_ -> this.P_4830_p.execute(() -> this.u_2550_I.n_1700_B(f_2197_c.n_1700_B(() -> {}, j, false))));
            }
            this.M_588_G.clear();
        }
        return flag;
    }

    private void n_1700_B(long chunkPosIn, r_3890_d<?> ticketIn) {
        t_4022_o<r_3890_d<?>> sortedarrayset = this.u_1723_Y(chunkPosIn);
        int i = l_4108_L.n_1700_B(sortedarrayset);
        r_3890_d<?> ticket = sortedarrayset.n_1700_B(ticketIn);
        ticket.n_1700_B(this.h_1847_R);
        if (ticketIn.J_1907_R() < i) {
            this.P_1922_E.J_1907_R(chunkPosIn, ticketIn.J_1907_R(), true);
        }
        if (Reflector.callBoolean(ticketIn, Reflector.ForgeTicket_isForceTicks, new Object[0])) {
            t_4022_o sortedarrayset1 = (t_4022_o)this.Q_4569_t.computeIfAbsent(chunkPosIn, p_lambda$register$6_0_ -> t_4022_o.n_1700_B(4));
            sortedarrayset1.n_1700_B(ticket);
        }
    }

    private void J_1907_R(long chunkPosIn, r_3890_d<?> ticketIn) {
        t_4022_o sortedarrayset1;
        t_4022_o<r_3890_d<?>> sortedarrayset = this.u_1723_Y(chunkPosIn);
        if (sortedarrayset.remove(ticketIn)) {
            // empty if block
        }
        if (sortedarrayset.isEmpty()) {
            this.G_564_y.remove(chunkPosIn);
        }
        this.P_1922_E.J_1907_R(chunkPosIn, l_4108_L.n_1700_B(sortedarrayset), false);
        if (Reflector.callBoolean(ticketIn, Reflector.ForgeTicket_isForceTicks, new Object[0]) && (sortedarrayset1 = (t_4022_o)this.Q_4569_t.get(chunkPosIn)) != null) {
            sortedarrayset1.remove(ticketIn);
        }
    }

    public <T> void n_1700_B(TicketType<T> type, Y_1387_d pos, int level, T value) {
        this.n_1700_B(pos.n_1700_B(), new r_3890_d<T>(type, level, value));
    }

    public <T> void J_1907_R(TicketType<T> type, Y_1387_d pos, int level, T value) {
        r_3890_d<T> ticket = new r_3890_d<T>(type, level, value);
        this.J_1907_R(pos.n_1700_B(), ticket);
    }

    public <T> void R_4764_Y(TicketType<T> type, Y_1387_d pos, int distance, T value) {
        this.n_1700_B(pos.n_1700_B(), new r_3890_d<T>(type, 33 - distance, value));
    }

    public <T> void G_564_y(TicketType<T> type, Y_1387_d pos, int distance, T value) {
        r_3890_d<T> ticket = new r_3890_d<T>(type, 33 - distance, value);
        this.J_1907_R(pos.n_1700_B(), ticket);
    }

    private t_4022_o<r_3890_d<?>> u_1723_Y(long p_229848_1_) {
        return (t_4022_o)this.G_564_y.computeIfAbsent(p_229848_1_, p_lambda$getTicketSet$7_0_ -> t_4022_o.n_1700_B(4));
    }

    protected void n_1700_B(Y_1387_d pos, boolean add) {
        r_3890_d<Y_1387_d> ticket = new r_3890_d<Y_1387_d>(TicketType.G_564_y, 31, pos);
        if (add) {
            this.n_1700_B(pos.n_1700_B(), ticket);
        } else {
            this.J_1907_R(pos.n_1700_B(), ticket);
        }
    }

    public void n_1700_B(SectionPos sectionPosIn, B_4088_l player) {
        long i = sectionPosIn.M_588_G().n_1700_B();
        ((ObjectSet)this.R_4764_Y.computeIfAbsent(i, p_lambda$updatePlayerPosition$8_0_ -> new ObjectOpenHashSet())).add((Object)player);
        this.u_1723_Y.J_1907_R(i, 0, true);
        this.v_4262_N.J_1907_R(i, 0, true);
    }

    public void J_1907_R(SectionPos sectionPosIn, B_4088_l player) {
        long i = sectionPosIn.M_588_G().n_1700_B();
        ObjectSet objectset = (ObjectSet)this.R_4764_Y.get(i);
        objectset.remove((Object)player);
        if (objectset.isEmpty()) {
            this.R_4764_Y.remove(i);
            this.u_1723_Y.J_1907_R(i, Integer.MAX_VALUE, false);
            this.v_4262_N.J_1907_R(i, Integer.MAX_VALUE, false);
        }
    }

    protected String R_4764_Y(long p_225413_1_) {
        t_4022_o sortedarrayset = (t_4022_o)this.G_564_y.get(p_225413_1_);
        String s = sortedarrayset != null && !sortedarrayset.isEmpty() ? ((r_3890_d)sortedarrayset.n_1700_B()).toString() : "no_ticket";
        return s;
    }

    protected void n_1700_B(int viewDistance) {
        this.v_4262_N.J_1907_R(viewDistance);
    }

    public int J_1907_R() {
        this.u_1723_Y.n_1700_B();
        return this.u_1723_Y.n_1700_B.size();
    }

    public boolean G_564_y(long chunkPosIn) {
        this.u_1723_Y.n_1700_B();
        return this.u_1723_Y.n_1700_B.containsKey(chunkPosIn);
    }

    public String R_4764_Y() {
        return this.t_148_a.n_1700_B();
    }

    public <T> void P_1922_E(TicketType<T> p_registerTicking_1_, Y_1387_d p_registerTicking_2_, int p_registerTicking_3_, T p_registerTicking_4_) {
        r_3890_d ticket = (r_3890_d)Reflector.ForgeTicket_Constructor.newInstance(p_registerTicking_1_, 33 - p_registerTicking_3_, p_registerTicking_4_, true);
        this.n_1700_B(p_registerTicking_2_.n_1700_B(), ticket);
    }

    public <T> void u_1723_Y(TicketType<T> p_releaseTicking_1_, Y_1387_d p_releaseTicking_2_, int p_releaseTicking_3_, T p_releaseTicking_4_) {
        r_3890_d ticket = (r_3890_d)Reflector.ForgeTicket_Constructor.newInstance(p_releaseTicking_1_, 33 - p_releaseTicking_3_, p_releaseTicking_4_, true);
        this.J_1907_R(p_releaseTicking_2_.n_1700_B(), ticket);
    }

    public boolean P_1922_E(long p_shouldForceTicks_1_) {
        t_4022_o sortedarrayset = (t_4022_o)this.Q_4569_t.get(p_shouldForceTicks_1_);
        return sortedarrayset != null && !sortedarrayset.isEmpty();
    }

    class n_1700_B
    extends n_4663_J {
        public n_1700_B() {
            super(y_1195_s.J_1907_R + 2, 256, 256);
        }

        @Override
        protected int J_1907_R(long pos) {
            t_4022_o sortedarrayset = (t_4022_o)l_4108_L.this.G_564_y.get(pos);
            if (sortedarrayset == null) {
                return Integer.MAX_VALUE;
            }
            return sortedarrayset.isEmpty() ? Integer.MAX_VALUE : ((r_3890_d)sortedarrayset.n_1700_B()).J_1907_R();
        }

        @Override
        protected int R_4764_Y(long sectionPosIn) {
            y_3683_b chunkholder;
            if (!l_4108_L.this.n_1700_B(sectionPosIn) && (chunkholder = l_4108_L.this.J_1907_R(sectionPosIn)) != null) {
                return chunkholder.s_956_w();
            }
            return y_1195_s.J_1907_R + 1;
        }

        @Override
        protected void n_1700_B(long sectionPosIn, int level) {
            int i;
            y_3683_b chunkholder = l_4108_L.this.J_1907_R(sectionPosIn);
            int n = i = chunkholder == null ? y_1195_s.J_1907_R + 1 : chunkholder.s_956_w();
            if (i != level && (chunkholder = l_4108_L.this.n_1700_B(sectionPosIn, level, chunkholder, i)) != null) {
                l_4108_L.this.w_1484_f.add(chunkholder);
            }
        }

        public int J_1907_R(int toUpdateCount) {
            return this.n_1700_B(toUpdateCount);
        }
    }

    class J_1907_R
    extends n_4663_J {
        protected final Long2ByteMap n_1700_B;
        protected final int J_1907_R;

        protected J_1907_R(int levelCount) {
            super(levelCount + 2, 2048, 2048);
            this.n_1700_B = new Long2ByteOpenHashMap();
            this.J_1907_R = levelCount;
            this.n_1700_B.defaultReturnValue((byte)(levelCount + 2));
        }

        @Override
        protected int R_4764_Y(long sectionPosIn) {
            return this.n_1700_B.get(sectionPosIn);
        }

        @Override
        protected void n_1700_B(long sectionPosIn, int level) {
            byte b0 = level > this.J_1907_R ? this.n_1700_B.remove(sectionPosIn) : this.n_1700_B.put(sectionPosIn, (byte)level);
            this.n_1700_B(sectionPosIn, (int)b0, level);
        }

        protected void n_1700_B(long chunkPosIn, int oldLevel, int newLevel) {
        }

        @Override
        protected int J_1907_R(long pos) {
            return this.G_564_y(pos) ? 0 : Integer.MAX_VALUE;
        }

        private boolean G_564_y(long chunkPosIn) {
            ObjectSet objectset = (ObjectSet)l_4108_L.this.R_4764_Y.get(chunkPosIn);
            return objectset != null && !objectset.isEmpty();
        }

        public void n_1700_B() {
            this.n_1700_B(Integer.MAX_VALUE);
        }
    }

    class R_4764_Y
    extends J_1907_R {
        private int P_1922_E;
        private final Long2IntMap u_1723_Y;
        private final LongSet v_4262_N;

        protected R_4764_Y(int p_i50682_2_) {
            super(p_i50682_2_);
            this.u_1723_Y = Long2IntMaps.synchronize((Long2IntMap)new Long2IntOpenHashMap());
            this.v_4262_N = new LongOpenHashSet();
            this.P_1922_E = 0;
            this.u_1723_Y.defaultReturnValue(p_i50682_2_ + 2);
        }

        @Override
        protected void n_1700_B(long chunkPosIn, int oldLevel, int newLevel) {
            this.v_4262_N.add(chunkPosIn);
        }

        public void J_1907_R(int viewDistanceIn) {
            for (Long2ByteMap.Entry entry : this.n_1700_B.long2ByteEntrySet()) {
                byte b0 = entry.getByteValue();
                long i = entry.getLongKey();
                this.n_1700_B(i, b0, this.R_4764_Y(b0), b0 <= viewDistanceIn - 2);
            }
            this.P_1922_E = viewDistanceIn;
        }

        private void n_1700_B(long chunkPosIn, int p_215504_3_, boolean p_215504_4_, boolean p_215504_5_) {
            if (p_215504_4_ != p_215504_5_) {
                r_3890_d<Y_1387_d> ticket = new r_3890_d<Y_1387_d>(TicketType.R_4764_Y, J_1907_R, new Y_1387_d(chunkPosIn));
                if (p_215504_5_) {
                    l_4108_L.this.s_956_w.n_1700_B(f_2197_c.n_1700_B(() -> l_4108_L.this.P_4830_p.execute(() -> {
                        if (this.R_4764_Y(this.R_4764_Y(chunkPosIn))) {
                            l_4108_L.this.n_1700_B(chunkPosIn, ticket);
                            l_4108_L.this.M_588_G.add(chunkPosIn);
                        } else {
                            l_4108_L.this.u_2550_I.n_1700_B(f_2197_c.n_1700_B(() -> {}, chunkPosIn, false));
                        }
                    }), chunkPosIn, () -> p_215504_3_));
                } else {
                    l_4108_L.this.u_2550_I.n_1700_B(f_2197_c.n_1700_B(() -> l_4108_L.this.P_4830_p.execute(() -> l_4108_L.this.J_1907_R(chunkPosIn, ticket)), chunkPosIn, true));
                }
            }
        }

        @Override
        public void n_1700_B() {
            super.n_1700_B();
            if (!this.v_4262_N.isEmpty()) {
                LongIterator longiterator = this.v_4262_N.iterator();
                while (longiterator.hasNext()) {
                    int k;
                    long i = longiterator.nextLong();
                    int j = this.u_1723_Y.get(i);
                    if (j == (k = this.R_4764_Y(i))) continue;
                    l_4108_L.this.t_148_a.n_1700_B(new Y_1387_d(i), () -> this.u_1723_Y.get(i), k, p_lambda$processAllUpdates$7_3_ -> {
                        if (p_lambda$processAllUpdates$7_3_ >= this.u_1723_Y.defaultReturnValue()) {
                            this.u_1723_Y.remove(i);
                        } else {
                            this.u_1723_Y.put(i, p_lambda$processAllUpdates$7_3_);
                        }
                    });
                    this.n_1700_B(i, k, this.R_4764_Y(j), this.R_4764_Y(k));
                }
                this.v_4262_N.clear();
            }
        }

        private boolean R_4764_Y(int p_215505_1_) {
            return p_215505_1_ <= this.P_1922_E - 2;
        }
    }
}


