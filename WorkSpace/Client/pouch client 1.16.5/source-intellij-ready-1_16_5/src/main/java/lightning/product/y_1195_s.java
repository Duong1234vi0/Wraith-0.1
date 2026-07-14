/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Queues
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.datafixers.util.Either
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2ByteMap
 *  it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.longs.Long2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.longs.LongIterator
 *  it.unimi.dsi.fastutil.longs.LongOpenHashSet
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.mutable.MutableBoolean
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ByteMap;
import it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.LongIterator;
import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
import it.unimi.dsi.fastutil.longs.LongSet;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.function.IntFunction;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.DebugPackets;
import lightning.product.ChunkStatus;
import lightning.product.ChunkStorage;
import lightning.product.H_1748_a;
import lightning.product.H_3272_P;
import lightning.product.ClientboundLightUpdatePacket;
import lightning.product.N_404_o;
import lightning.product.N_4263_v;
import lightning.product.O_1400_s;
import lightning.product.U_157_Y;
import lightning.product.U_2912_j;
import lightning.product.U_3758_m;
import lightning.product.ProfilerFiller;
import lightning.product.ImposterProtoChunk;
import lightning.product.Y_1387_d;
import lightning.product.Z_530_i;
import lightning.product.a_3913_L;
import lightning.product.b_2085_h;
import lightning.product.b_2971_z;
import lightning.product.b_4507_u;
import lightning.product.b_4946_z;
import lightning.product.SectionPos;
import lightning.product.ClientboundSetPassengersPacket;
import lightning.product.StructureStart;
import lightning.product.ChunkAccess;
import lightning.product.e_1322_b;
import lightning.product.e_2754_J;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.e_446_u;
import lightning.product.f_2197_c;
import lightning.product.PlayerMap;
import lightning.product.TicketType;
import lightning.product.j_3341_s;
import lightning.product.ChunkProgressListener;
import lightning.product.l_4108_L;
import lightning.product.n_1254_X;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.p_198_K;
import lightning.product.EnderDragonPart;
import lightning.product.CrashReportCategory;
import lightning.product.r_3634_h;
import lightning.product.s_2187_o;
import lightning.product.s_4380_l;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.ProcessorHandle;
import lightning.product.u_530_F;
import lightning.product.LightChunkGetter;
import lightning.product.y_3683_b;
import lightning.product.z_1136_g;
import lightning.product.z_1753_f;
import net.optifine.reflect.Reflector;
import org.apache.commons.lang3.mutable.MutableBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class y_1195_s
extends ChunkStorage
implements y_3683_b.R_4764_Y {
    private static final Logger R_4764_Y = LogManager.getLogger();
    public static final int J_1907_R = 33 + ChunkStatus.J_1907_R();
    private final Long2ObjectLinkedOpenHashMap<y_3683_b> G_564_y = new Long2ObjectLinkedOpenHashMap();
    private volatile Long2ObjectLinkedOpenHashMap<y_3683_b> P_1922_E = this.G_564_y.clone();
    private final Long2ObjectLinkedOpenHashMap<y_3683_b> u_1723_Y = new Long2ObjectLinkedOpenHashMap();
    private final LongSet v_4262_N = new LongOpenHashSet();
    private final e_3591_l w_1484_f;
    private final e_2754_J t_148_a;
    private final H_3272_P<Runnable> s_956_w;
    private final z_1753_f u_2550_I;
    private final Supplier<s_4380_l> M_588_G;
    private final b_4946_z P_4830_p;
    private final LongSet h_1847_R = new LongOpenHashSet();
    private boolean Q_4569_t;
    private final f_2197_c M_182_A;
    private final ProcessorHandle<f_2197_c.n_1700_B<Runnable>> t_1786_h;
    private final ProcessorHandle<f_2197_c.n_1700_B<Runnable>> multiplayerClientSuggestionProvider;
    private final ChunkProgressListener w_1457_N;
    private final J_1907_R Y_601_j;
    private final AtomicInteger Y_259_p = new AtomicInteger();
    private final b_2085_h Q_2552_b;
    private final File C_2741_M;
    private final PlayerMap k_2293_S = new PlayerMap();
    private final Int2ObjectMap<n_1700_B> q_2307_F = new Int2ObjectOpenHashMap();
    private final Long2ByteMap Z_875_P = new Long2ByteOpenHashMap();
    private final Queue<Runnable> c_3005_b = Queues.newConcurrentLinkedQueue();
    private int H_2857_Y;

    public y_1195_s(e_3591_l p_i232602_1_, b_2971_z.n_1700_B p_i232602_2_, DataFixer p_i232602_3_, b_2085_h p_i232602_4_, Executor p_i232602_5_, H_3272_P<Runnable> p_i232602_6_, LightChunkGetter p_i232602_7_, z_1753_f p_i232602_8_, ChunkProgressListener p_i232602_9_, Supplier<s_4380_l> p_i232602_10_, int p_i232602_11_, boolean p_i232602_12_) {
        super(new File(p_i232602_2_.n_1700_B(p_i232602_1_.g_2268_R()), "region"), p_i232602_3_, p_i232602_12_);
        this.Q_2552_b = p_i232602_4_;
        this.C_2741_M = p_i232602_2_.n_1700_B(p_i232602_1_.g_2268_R());
        this.w_1484_f = p_i232602_1_;
        this.u_2550_I = p_i232602_8_;
        this.s_956_w = p_i232602_6_;
        U_3758_m<Runnable> delegatedtaskexecutor = U_3758_m.n_1700_B(p_i232602_5_, "worldgen");
        ProcessorHandle<Runnable> itaskexecutor = ProcessorHandle.n_1700_B("main", p_i232602_6_::w_1484_f);
        this.w_1457_N = p_i232602_9_;
        U_3758_m<Runnable> delegatedtaskexecutor1 = U_3758_m.n_1700_B(p_i232602_5_, "light");
        this.M_182_A = new f_2197_c((List<ProcessorHandle<?>>)ImmutableList.of(delegatedtaskexecutor, itaskexecutor, delegatedtaskexecutor1), p_i232602_5_, Integer.MAX_VALUE);
        this.t_1786_h = this.M_182_A.n_1700_B(delegatedtaskexecutor, false);
        this.multiplayerClientSuggestionProvider = this.M_182_A.n_1700_B(itaskexecutor, false);
        this.t_148_a = new e_2754_J(p_i232602_7_, this, this.w_1484_f.G_624_v().J_1907_R(), delegatedtaskexecutor1, this.M_182_A.n_1700_B(delegatedtaskexecutor1, false));
        this.Y_601_j = new J_1907_R(p_i232602_5_, p_i232602_6_);
        this.M_588_G = p_i232602_10_;
        this.P_4830_p = new b_4946_z(new File(this.C_2741_M, "poi"), p_i232602_3_, p_i232602_12_);
        this.n_1700_B(p_i232602_11_);
    }

    private static double n_1700_B(Y_1387_d chunkPosIn, N_4263_v entityIn) {
        double d0 = chunkPosIn.J_1907_R * 16 + 8;
        double d1 = chunkPosIn.R_4764_Y * 16 + 8;
        double d2 = d0 - entityIn.O_3598_v();
        double d3 = d1 - entityIn.l_2647_k();
        return d2 * d2 + d3 * d3;
    }

    private static int n_1700_B(Y_1387_d pos, B_4088_l player, boolean p_219215_2_) {
        int j;
        int i;
        if (p_219215_2_) {
            SectionPos sectionpos = player.c_4037_x();
            i = sectionpos.n_1700_B();
            j = sectionpos.R_4764_Y();
        } else {
            i = u_530_F.R_4764_Y(player.O_3598_v() / 16.0);
            j = u_530_F.R_4764_Y(player.l_2647_k() / 16.0);
        }
        return y_1195_s.n_1700_B(pos, i, j);
    }

    private static int n_1700_B(Y_1387_d chunkPosIn, int x, int y) {
        int i = chunkPosIn.J_1907_R - x;
        int j = chunkPosIn.R_4764_Y - y;
        return Math.max(Math.abs(i), Math.abs(j));
    }

    protected e_2754_J J_1907_R() {
        return this.t_148_a;
    }

    @Nullable
    protected y_3683_b n_1700_B(long chunkPosIn) {
        return (y_3683_b)this.G_564_y.get(chunkPosIn);
    }

    @Nullable
    protected y_3683_b J_1907_R(long chunkPosIn) {
        return (y_3683_b)this.P_1922_E.get(chunkPosIn);
    }

    protected IntSupplier R_4764_Y(long chunkPosIn) {
        return () -> {
            y_3683_b chunkholder = this.J_1907_R(chunkPosIn);
            return chunkholder == null ? z_1136_g.n_1700_B - 1 : Math.min(chunkholder.u_2550_I(), z_1136_g.n_1700_B - 1);
        };
    }

    public String J_1907_R(Y_1387_d pos) {
        y_3683_b chunkholder = this.J_1907_R(pos.n_1700_B());
        if (chunkholder == null) {
            return "null";
        }
        String s = chunkholder.s_956_w() + "\n";
        ChunkStatus chunkstatus = chunkholder.P_1922_E();
        ChunkAccess ichunk = chunkholder.u_1723_Y();
        if (chunkstatus != null) {
            s = s + "St: \u00a7" + chunkstatus.R_4764_Y() + String.valueOf(chunkstatus) + "\u00a7r\n";
        }
        if (ichunk != null) {
            s = s + "Ch: \u00a7" + ichunk.getStatus().R_4764_Y() + String.valueOf(ichunk.getStatus()) + "\u00a7r\n";
        }
        y_3683_b.G_564_y chunkholder$locationtype = chunkholder.w_1484_f();
        s = s + "\u00a7" + chunkholder$locationtype.ordinal() + String.valueOf((Object)chunkholder$locationtype);
        return s + "\u00a7r";
    }

    private CompletableFuture<Either<List<ChunkAccess>, y_3683_b.n_1700_B>> n_1700_B(Y_1387_d pos, final int p_219236_2_, IntFunction<ChunkStatus> p_219236_3_) {
        ArrayList list = Lists.newArrayList();
        final int i = pos.J_1907_R;
        final int j = pos.R_4764_Y;
        for (int k = -p_219236_2_; k <= p_219236_2_; ++k) {
            for (int l = -p_219236_2_; l <= p_219236_2_; ++l) {
                int i1 = Math.max(Math.abs(l), Math.abs(k));
                final Y_1387_d chunkpos = new Y_1387_d(i + l, j + k);
                long j1 = chunkpos.n_1700_B();
                y_3683_b chunkholder = this.n_1700_B(j1);
                if (chunkholder == null) {
                    return CompletableFuture.completedFuture(Either.right((Object)new y_3683_b.n_1700_B(){

                        public String toString() {
                            return "Unloaded " + chunkpos.toString();
                        }
                    }));
                }
                ChunkStatus chunkstatus = p_219236_3_.apply(i1);
                CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> completablefuture = chunkholder.n_1700_B(chunkstatus, this);
                list.add(completablefuture);
            }
        }
        CompletableFuture completablefuture1 = j_3341_s.J_1907_R(list);
        return completablefuture1.thenApply(p_lambda$func_219236_a$1_4_ -> {
            ArrayList list1 = Lists.newArrayList();
            int k1 = 0;
            for (final Either either : p_lambda$func_219236_a$1_4_) {
                Optional optional = either.left();
                if (!optional.isPresent()) {
                    final int l1 = k1;
                    return Either.right((Object)new y_3683_b.n_1700_B(){

                        public String toString() {
                            return "Unloaded " + String.valueOf(new Y_1387_d(i + l1 % (p_219236_2_ * 2 + 1), j + l1 / (p_219236_2_ * 2 + 1))) + " " + ((y_3683_b.n_1700_B)either.right().get()).toString();
                        }
                    });
                }
                list1.add((ChunkAccess)optional.get());
                ++k1;
            }
            return Either.left((Object)list1);
        });
    }

    public CompletableFuture<Either<H_1748_a, y_3683_b.n_1700_B>> R_4764_Y(Y_1387_d p_219188_1_) {
        return this.n_1700_B(p_219188_1_, 2, (int p_lambda$func_219188_b$2_0_) -> ChunkStatus.P_4830_p).thenApplyAsync(p_lambda$func_219188_b$4_0_ -> p_lambda$func_219188_b$4_0_.mapLeft(p_lambda$null$3_0_ -> (H_1748_a)p_lambda$null$3_0_.get(p_lambda$null$3_0_.size() / 2)), (Executor)this.s_956_w);
    }

    @Nullable
    private y_3683_b n_1700_B(long chunkPosIn, int newLevel, @Nullable y_3683_b holder, int oldLevel) {
        if (oldLevel > J_1907_R && newLevel > J_1907_R) {
            return holder;
        }
        if (holder != null) {
            holder.n_1700_B(newLevel);
        }
        if (holder != null) {
            if (newLevel > J_1907_R) {
                this.h_1847_R.add(chunkPosIn);
            } else {
                this.h_1847_R.remove(chunkPosIn);
            }
        }
        if (newLevel <= J_1907_R && holder == null) {
            holder = (y_3683_b)this.u_1723_Y.remove(chunkPosIn);
            if (holder != null) {
                holder.n_1700_B(newLevel);
            } else {
                holder = new y_3683_b(new Y_1387_d(chunkPosIn), newLevel, this.t_148_a, this.M_182_A, this);
            }
            this.G_564_y.put(chunkPosIn, (Object)holder);
            this.Q_4569_t = true;
        }
        return holder;
    }

    @Override
    public void close() throws IOException {
        try {
            this.M_182_A.close();
            this.P_4830_p.close();
        }
        finally {
            super.close();
        }
    }

    protected void n_1700_B(boolean flush) {
        if (flush) {
            List list = this.P_1922_E.values().stream().filter(y_3683_b::M_588_G).peek(y_3683_b::P_4830_p).collect(Collectors.toList());
            MutableBoolean mutableboolean = new MutableBoolean();
            do {
                mutableboolean.setFalse();
                list.stream().map(p_lambda$save$5_1_ -> {
                    CompletableFuture<ChunkAccess> completablefuture;
                    do {
                        completablefuture = p_lambda$save$5_1_.v_4262_N();
                        this.s_956_w.R_4764_Y(completablefuture::isDone);
                    } while (completablefuture != p_lambda$save$5_1_.v_4262_N());
                    return completablefuture.join();
                }).filter(p_lambda$save$6_0_ -> p_lambda$save$6_0_ instanceof ImposterProtoChunk || p_lambda$save$6_0_ instanceof H_1748_a).filter(this::n_1700_B).forEach(p_lambda$save$7_1_ -> mutableboolean.setTrue());
            } while (mutableboolean.isTrue());
            this.J_1907_R(() -> true);
            this.n_1700_B();
            R_4764_Y.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", (Object)this.C_2741_M.getName());
        } else {
            this.P_1922_E.values().stream().filter(y_3683_b::M_588_G).forEach(p_lambda$save$9_1_ -> {
                ChunkAccess ichunk = p_lambda$save$9_1_.v_4262_N().getNow(null);
                if (ichunk instanceof ImposterProtoChunk || ichunk instanceof H_1748_a) {
                    this.n_1700_B(ichunk);
                    p_lambda$save$9_1_.P_4830_p();
                }
            });
        }
    }

    protected void n_1700_B(BooleanSupplier hasMoreTime) {
        ProfilerFiller iprofiler = this.w_1484_f.D_4792_h();
        iprofiler.n_1700_B("poi");
        this.P_4830_p.n_1700_B(hasMoreTime);
        iprofiler.J_1907_R("chunk_unload");
        if (!this.w_1484_f.T_3594_S()) {
            this.J_1907_R(hasMoreTime);
        }
        iprofiler.R_4764_Y();
    }

    private void J_1907_R(BooleanSupplier hasMoreTime) {
        Runnable runnable;
        LongIterator longiterator = this.h_1847_R.iterator();
        int i = 0;
        while (longiterator.hasNext() && (hasMoreTime.getAsBoolean() || i < 200 || this.h_1847_R.size() > 2000)) {
            long j = longiterator.nextLong();
            y_3683_b chunkholder = (y_3683_b)this.G_564_y.remove(j);
            if (chunkholder != null) {
                this.u_1723_Y.put(j, (Object)chunkholder);
                this.Q_4569_t = true;
                ++i;
                this.n_1700_B(j, chunkholder);
            }
            longiterator.remove();
        }
        while ((hasMoreTime.getAsBoolean() || this.c_3005_b.size() > 2000) && (runnable = this.c_3005_b.poll()) != null) {
            runnable.run();
        }
    }

    private void n_1700_B(long chunkPosIn, y_3683_b chunkHolderIn) {
        CompletableFuture<ChunkAccess> completablefuture = chunkHolderIn.v_4262_N();
        ((CompletableFuture)completablefuture.thenAcceptAsync(p_lambda$scheduleSave$10_5_ -> {
            CompletableFuture<ChunkAccess> completablefuture1 = chunkHolderIn.v_4262_N();
            if (completablefuture1 != completablefuture) {
                this.n_1700_B(chunkPosIn, chunkHolderIn);
            } else if (this.u_1723_Y.remove(chunkPosIn, (Object)chunkHolderIn) && p_lambda$scheduleSave$10_5_ != null) {
                if (p_lambda$scheduleSave$10_5_ instanceof H_1748_a) {
                    ((H_1748_a)p_lambda$scheduleSave$10_5_).setLoaded(false);
                    if (Reflector.ChunkEvent_Unload_Constructor.exists()) {
                        Reflector.postForgeBusEvent(Reflector.ChunkEvent_Unload_Constructor, p_lambda$scheduleSave$10_5_);
                    }
                }
                this.n_1700_B((ChunkAccess)p_lambda$scheduleSave$10_5_);
                if (this.v_4262_N.remove(chunkPosIn) && p_lambda$scheduleSave$10_5_ instanceof H_1748_a) {
                    H_1748_a chunk = (H_1748_a)p_lambda$scheduleSave$10_5_;
                    this.w_1484_f.n_1700_B(chunk);
                }
                this.t_148_a.n_1700_B(p_lambda$scheduleSave$10_5_.getPos());
                this.t_148_a.J_1907_R();
                this.w_1457_N.n_1700_B(p_lambda$scheduleSave$10_5_.getPos(), null);
            }
        }, this.c_3005_b::add)).whenComplete((p_lambda$scheduleSave$11_1_, p_lambda$scheduleSave$11_2_) -> {
            if (p_lambda$scheduleSave$11_2_ != null) {
                R_4764_Y.error("Failed to save chunk " + String.valueOf(chunkHolderIn.t_148_a()), p_lambda$scheduleSave$11_2_);
            }
        });
    }

    protected boolean R_4764_Y() {
        if (!this.Q_4569_t) {
            return false;
        }
        this.P_1922_E = this.G_564_y.clone();
        this.Q_4569_t = false;
        return true;
    }

    public CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> n_1700_B(y_3683_b chunkHolder, ChunkStatus chunkStatus) {
        Y_1387_d chunkpos = chunkHolder.t_148_a();
        if (chunkStatus == ChunkStatus.n_1700_B) {
            return this.u_1723_Y(chunkpos);
        }
        CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> completablefuture = chunkHolder.n_1700_B(chunkStatus.P_1922_E(), this);
        return completablefuture.thenComposeAsync(p_lambda$func_219244_a$13_4_ -> {
            ChunkAccess ichunk;
            Optional optional = p_lambda$func_219244_a$13_4_.left();
            if (!optional.isPresent()) {
                return CompletableFuture.completedFuture(p_lambda$func_219244_a$13_4_);
            }
            if (chunkStatus == ChunkStatus.s_956_w) {
                this.Y_601_j.n_1700_B(TicketType.P_1922_E, chunkpos, 33 + ChunkStatus.n_1700_B(ChunkStatus.t_148_a), chunkpos);
            }
            if ((ichunk = (ChunkAccess)optional.get()).getStatus().J_1907_R(chunkStatus)) {
                CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> completablefuture1 = chunkStatus == ChunkStatus.s_956_w ? this.J_1907_R(chunkHolder, chunkStatus) : chunkStatus.n_1700_B(this.w_1484_f, this.Q_2552_b, this.t_148_a, p_lambda$null$12_2_ -> this.R_4764_Y(chunkHolder), ichunk);
                this.w_1457_N.n_1700_B(chunkpos, chunkStatus);
                return completablefuture1;
            }
            return this.J_1907_R(chunkHolder, chunkStatus);
        }, (Executor)this.s_956_w);
    }

    private CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> u_1723_Y(Y_1387_d chunkPos) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                this.w_1484_f.D_4792_h().R_4764_Y("chunkLoad");
                U_2912_j compoundnbt = this.t_148_a(chunkPos);
                if (compoundnbt != null) {
                    boolean flag;
                    boolean bl = flag = compoundnbt.R_4764_Y("Level", 10) && compoundnbt.M_182_A("Level").R_4764_Y("Status", 8);
                    if (flag) {
                        n_1254_X ichunk = s_2187_o.n_1700_B(this.w_1484_f, this.Q_2552_b, this.P_4830_p, chunkPos, compoundnbt);
                        ichunk.setLastSaveTime(this.w_1484_f.X_933_l());
                        this.n_1700_B(chunkPos, ichunk.getStatus().v_4262_N());
                        return Either.left((Object)ichunk);
                    }
                    R_4764_Y.error("Chunk file at {} is missing level data, skipping", (Object)chunkPos);
                }
            }
            catch (ReportedException reportedexception) {
                Throwable throwable = reportedexception.getCause();
                if (!(throwable instanceof IOException)) {
                    this.v_4262_N(chunkPos);
                    throw reportedexception;
                }
                R_4764_Y.error("Couldn't load chunk {}", (Object)chunkPos, (Object)throwable);
            }
            catch (Exception exception1) {
                R_4764_Y.error("Couldn't load chunk {}", (Object)chunkPos, (Object)exception1);
            }
            this.v_4262_N(chunkPos);
            return Either.left((Object)new n_1254_X(chunkPos, r_3634_h.n_1700_B));
        }, this.s_956_w);
    }

    private void v_4262_N(Y_1387_d p_241089_1_) {
        this.Z_875_P.put(p_241089_1_.n_1700_B(), (byte)-1);
    }

    private byte n_1700_B(Y_1387_d p_241088_1_, ChunkStatus.G_564_y p_241088_2_) {
        return this.Z_875_P.put(p_241088_1_.n_1700_B(), (byte)(p_241088_2_ == ChunkStatus.G_564_y.n_1700_B ? -1 : 1));
    }

    private CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> J_1907_R(y_3683_b chunkHolder, ChunkStatus chunkStatus) {
        Y_1387_d chunkpos = chunkHolder.t_148_a();
        CompletableFuture<Either<List<ChunkAccess>, y_3683_b.n_1700_B>> completablefuture = this.n_1700_B(chunkpos, chunkStatus.u_1723_Y(), (int p_lambda$chunkGenerate$15_2_) -> this.n_1700_B(chunkStatus, p_lambda$chunkGenerate$15_2_));
        this.w_1484_f.D_4792_h().R_4764_Y(() -> "chunkGenerate " + chunkStatus.G_564_y());
        return completablefuture.thenComposeAsync(p_lambda$chunkGenerate$20_4_ -> (CompletionStage)p_lambda$chunkGenerate$20_4_.map(p_lambda$null$18_4_ -> {
            try {
                CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> completablefuture1 = chunkStatus.n_1700_B(this.w_1484_f, this.u_2550_I, this.Q_2552_b, this.t_148_a, p_lambda$null$17_2_ -> this.R_4764_Y(chunkHolder), (List<ChunkAccess>)p_lambda$null$18_4_);
                this.w_1457_N.n_1700_B(chunkpos, chunkStatus);
                return completablefuture1;
            }
            catch (Exception exception1) {
                n_3236_c crashreport = n_3236_c.n_1700_B(exception1, "Exception generating new chunk");
                CrashReportCategory crashreportcategory = crashreport.n_1700_B("Chunk to be generated");
                crashreportcategory.n_1700_B("Location", String.format("%d,%d", chunkpos.J_1907_R, chunkpos.R_4764_Y));
                crashreportcategory.n_1700_B("Position hash", Y_1387_d.n_1700_B(chunkpos.J_1907_R, chunkpos.R_4764_Y));
                crashreportcategory.n_1700_B("Generator", this.u_2550_I);
                throw new ReportedException(crashreport);
            }
        }, p_lambda$null$19_2_ -> {
            this.G_564_y(chunkpos);
            return CompletableFuture.completedFuture(Either.right((Object)p_lambda$null$19_2_));
        }), p_lambda$chunkGenerate$21_2_ -> this.t_1786_h.n_1700_B(f_2197_c.n_1700_B(chunkHolder, p_lambda$chunkGenerate$21_2_)));
    }

    protected void G_564_y(Y_1387_d p_219209_1_) {
        this.s_956_w.w_1484_f(j_3341_s.n_1700_B(() -> this.Y_601_j.J_1907_R(TicketType.P_1922_E, p_219209_1_, 33 + ChunkStatus.n_1700_B(ChunkStatus.t_148_a), p_219209_1_), () -> "release light ticket " + String.valueOf(p_219209_1_)));
    }

    private ChunkStatus n_1700_B(ChunkStatus p_219205_1_, int p_219205_2_) {
        ChunkStatus chunkstatus = p_219205_2_ == 0 ? p_219205_1_.P_1922_E() : ChunkStatus.n_1700_B(ChunkStatus.n_1700_B(p_219205_1_) + p_219205_2_);
        return chunkstatus;
    }

    private CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> R_4764_Y(y_3683_b p_219200_1_) {
        CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> completablefuture = p_219200_1_.n_1700_B(ChunkStatus.P_4830_p.P_1922_E());
        return completablefuture.thenApplyAsync(p_lambda$func_219200_b$26_2_ -> {
            ChunkStatus chunkstatus = y_3683_b.J_1907_R(p_219200_1_.s_956_w());
            return !chunkstatus.J_1907_R(ChunkStatus.P_4830_p) ? y_3683_b.n_1700_B : p_lambda$func_219200_b$26_2_.mapLeft(p_lambda$null$25_2_ -> {
                H_1748_a chunk;
                Y_1387_d chunkpos = p_219200_1_.t_148_a();
                if (p_lambda$null$25_2_ instanceof ImposterProtoChunk) {
                    chunk = ((ImposterProtoChunk)p_lambda$null$25_2_).w_1484_f();
                } else {
                    chunk = new H_1748_a(this.w_1484_f, (n_1254_X)p_lambda$null$25_2_);
                    p_219200_1_.n_1700_B(new ImposterProtoChunk(chunk));
                }
                chunk.setLocationType(() -> y_3683_b.R_4764_Y(p_219200_1_.s_956_w()));
                chunk.postLoad();
                if (this.v_4262_N.add(chunkpos.n_1700_B())) {
                    chunk.setLoaded(true);
                    this.w_1484_f.n_1700_B(chunk.getTileEntityMap().values());
                    Iterable list = null;
                    e_1322_b<N_4263_v>[] classinheritancemultimap = chunk.getEntityLists();
                    int i = classinheritancemultimap.length;
                    for (int j = 0; j < i; ++j) {
                        for (N_4263_v entity : classinheritancemultimap[j]) {
                            if (entity instanceof a_3913_L || this.w_1484_f.w_1484_f(entity)) continue;
                            if (list == null) {
                                list = Lists.newArrayList((Object[])new N_4263_v[]{entity});
                                continue;
                            }
                            list.add(entity);
                        }
                    }
                    if (list != null) {
                        list.forEach(chunk::removeEntity);
                    }
                    if (Reflector.ChunkEvent_Load_Constructor.exists()) {
                        Reflector.postForgeBusEvent(Reflector.ChunkEvent_Load_Constructor, chunk);
                    }
                }
                return chunk;
            });
        }, p_lambda$func_219200_b$27_2_ -> this.multiplayerClientSuggestionProvider.n_1700_B(f_2197_c.n_1700_B(p_lambda$func_219200_b$27_2_, p_219200_1_.t_148_a().n_1700_B(), p_219200_1_::s_956_w)));
    }

    public CompletableFuture<Either<H_1748_a, y_3683_b.n_1700_B>> n_1700_B(y_3683_b p_219179_1_) {
        Y_1387_d chunkpos = p_219179_1_.t_148_a();
        CompletableFuture<Either<List<ChunkAccess>, y_3683_b.n_1700_B>> completablefuture = this.n_1700_B(chunkpos, 1, (int p_lambda$func_219179_a$28_0_) -> ChunkStatus.P_4830_p);
        CompletionStage completablefuture1 = completablefuture.thenApplyAsync(p_lambda$func_219179_a$30_0_ -> p_lambda$func_219179_a$30_0_.flatMap(p_lambda$null$29_0_ -> {
            H_1748_a chunk = (H_1748_a)p_lambda$null$29_0_.get(p_lambda$null$29_0_.size() / 2);
            chunk.postProcess();
            return Either.left((Object)chunk);
        }), p_lambda$func_219179_a$31_2_ -> this.multiplayerClientSuggestionProvider.n_1700_B(f_2197_c.n_1700_B(p_219179_1_, p_lambda$func_219179_a$31_2_)));
        ((CompletableFuture)completablefuture1).thenAcceptAsync(p_lambda$func_219179_a$34_2_ -> p_lambda$func_219179_a$34_2_.mapLeft(p_lambda$null$33_2_ -> {
            this.Y_259_p.getAndIncrement();
            Packet[] ipacket = new Packet[2];
            this.n_1700_B(chunkpos, false).forEach(p_lambda$null$32_3_ -> this.n_1700_B((B_4088_l)p_lambda$null$32_3_, ipacket, (H_1748_a)p_lambda$null$33_2_));
            return Either.left((Object)p_lambda$null$33_2_);
        }), p_lambda$func_219179_a$35_2_ -> this.multiplayerClientSuggestionProvider.n_1700_B(f_2197_c.n_1700_B(p_219179_1_, p_lambda$func_219179_a$35_2_)));
        return completablefuture1;
    }

    public CompletableFuture<Either<H_1748_a, y_3683_b.n_1700_B>> J_1907_R(y_3683_b p_222961_1_) {
        return p_222961_1_.n_1700_B(ChunkStatus.P_4830_p, this).thenApplyAsync(p_lambda$func_222961_b$37_0_ -> p_lambda$func_222961_b$37_0_.mapLeft(p_lambda$null$36_0_ -> {
            H_1748_a chunk = (H_1748_a)p_lambda$null$36_0_;
            chunk.rescheduleTicks();
            return chunk;
        }), p_lambda$func_222961_b$38_2_ -> this.multiplayerClientSuggestionProvider.n_1700_B(f_2197_c.n_1700_B(p_222961_1_, p_lambda$func_222961_b$38_2_)));
    }

    public int G_564_y() {
        return this.Y_259_p.get();
    }

    private boolean n_1700_B(ChunkAccess chunkIn) {
        this.P_4830_p.n_1700_B(chunkIn.getPos());
        if (!chunkIn.isModified()) {
            return false;
        }
        chunkIn.setLastSaveTime(this.w_1484_f.X_933_l());
        chunkIn.setModified(false);
        Y_1387_d chunkpos = chunkIn.getPos();
        try {
            ChunkStatus chunkstatus = chunkIn.getStatus();
            if (chunkstatus.v_4262_N() != ChunkStatus.G_564_y.J_1907_R) {
                if (this.w_1484_f(chunkpos)) {
                    return false;
                }
                if (chunkstatus == ChunkStatus.n_1700_B && chunkIn.getStructureStarts().values().stream().noneMatch(StructureStart::P_1922_E)) {
                    return false;
                }
            }
            this.w_1484_f.D_4792_h().R_4764_Y("chunkSave");
            U_2912_j compoundnbt = s_2187_o.n_1700_B(this.w_1484_f, chunkIn);
            if (Reflector.ChunkDataEvent_Save_Constructor.exists()) {
                b_4507_u world = (b_4507_u)Reflector.call(chunkIn, Reflector.ForgeIChunk_getWorldForge, new Object[0]);
                Reflector.postForgeBusEvent(Reflector.ChunkDataEvent_Save_Constructor, chunkIn, world != null ? world : this.w_1484_f, compoundnbt);
            }
            this.n_1700_B(chunkpos, compoundnbt);
            this.n_1700_B(chunkpos, chunkstatus.v_4262_N());
            return true;
        }
        catch (Exception exception1) {
            R_4764_Y.error("Failed to save chunk {},{}", (Object)chunkpos.J_1907_R, (Object)chunkpos.R_4764_Y, (Object)exception1);
            return false;
        }
    }

    private boolean w_1484_f(Y_1387_d p_241090_1_) {
        U_2912_j compoundnbt;
        byte b0 = this.Z_875_P.get(p_241090_1_.n_1700_B());
        if (b0 != 0) {
            return b0 == 1;
        }
        try {
            compoundnbt = this.t_148_a(p_241090_1_);
            if (compoundnbt == null) {
                this.v_4262_N(p_241090_1_);
                return false;
            }
        }
        catch (Exception exception) {
            R_4764_Y.error("Failed to read chunk {}", (Object)p_241090_1_, (Object)exception);
            this.v_4262_N(p_241090_1_);
            return false;
        }
        ChunkStatus.G_564_y chunkstatus$type = s_2187_o.n_1700_B(compoundnbt);
        return this.n_1700_B(p_241090_1_, chunkstatus$type) == 1;
    }

    protected void n_1700_B(int viewDistance) {
        int i = u_530_F.n_1700_B(viewDistance + 1, 3, 64);
        if (i != this.H_2857_Y) {
            int j = this.H_2857_Y;
            this.H_2857_Y = i;
            this.Y_601_j.n_1700_B(this.H_2857_Y);
            for (y_3683_b chunkholder : this.G_564_y.values()) {
                Y_1387_d chunkpos = chunkholder.t_148_a();
                Packet[] ipacket = new Packet[2];
                this.n_1700_B(chunkpos, false).forEach(p_lambda$setViewDistance$39_4_ -> {
                    int k = y_1195_s.n_1700_B(chunkpos, p_lambda$setViewDistance$39_4_, true);
                    boolean flag = k <= j;
                    boolean flag1 = k <= this.H_2857_Y;
                    this.n_1700_B((B_4088_l)p_lambda$setViewDistance$39_4_, chunkpos, ipacket, flag, flag1);
                });
            }
        }
    }

    protected void n_1700_B(B_4088_l player, Y_1387_d chunkPosIn, Packet<?>[] packetCache, boolean wasLoaded, boolean load) {
        if (player.O_508_d == this.w_1484_f) {
            y_3683_b chunkholder;
            if (Reflector.ForgeEventFactory_fireChunkWatch.exists()) {
                Reflector.ForgeEventFactory_fireChunkWatch.call(wasLoaded, load, player, chunkPosIn, this.w_1484_f);
            }
            if (load && !wasLoaded && (chunkholder = this.J_1907_R(chunkPosIn.n_1700_B())) != null) {
                H_1748_a chunk = chunkholder.G_564_y();
                if (chunk != null) {
                    this.n_1700_B(player, packetCache, chunk);
                }
                DebugPackets.n_1700_B(this.w_1484_f, chunkPosIn);
            }
            if (!load && wasLoaded) {
                player.n_1700_B(chunkPosIn);
            }
        }
    }

    public int P_1922_E() {
        return this.P_1922_E.size();
    }

    protected J_1907_R u_1723_Y() {
        return this.Y_601_j;
    }

    protected Iterable<y_3683_b> v_4262_N() {
        return Iterables.unmodifiableIterable((Iterable)this.P_1922_E.values());
    }

    void n_1700_B(Writer p_225406_1_) throws IOException {
        O_1400_s csvwriter = O_1400_s.n_1700_B().n_1700_B("x").n_1700_B("z").n_1700_B("level").n_1700_B("in_memory").n_1700_B("status").n_1700_B("full_status").n_1700_B("accessible_ready").n_1700_B("ticking_ready").n_1700_B("entity_ticking_ready").n_1700_B("ticket").n_1700_B("spawning").n_1700_B("entity_count").n_1700_B("block_entity_count").n_1700_B(p_225406_1_);
        for (Long2ObjectMap.Entry entry : this.P_1922_E.long2ObjectEntrySet()) {
            Y_1387_d chunkpos = new Y_1387_d(entry.getLongKey());
            y_3683_b chunkholder = (y_3683_b)entry.getValue();
            Optional<ChunkAccess> optional = Optional.ofNullable(chunkholder.u_1723_Y());
            Optional<Object> optional1 = optional.flatMap(p_lambda$func_225406_a$40_0_ -> p_lambda$func_225406_a$40_0_ instanceof H_1748_a ? Optional.of((H_1748_a)p_lambda$func_225406_a$40_0_) : Optional.empty());
            csvwriter.n_1700_B(new Object[]{chunkpos.J_1907_R, chunkpos.R_4764_Y, chunkholder.s_956_w(), optional.isPresent(), optional.map(ChunkAccess::getStatus).orElse(null), optional1.map(H_1748_a::getLocationType).orElse(null), y_1195_s.n_1700_B(chunkholder.R_4764_Y()), y_1195_s.n_1700_B(chunkholder.n_1700_B()), y_1195_s.n_1700_B(chunkholder.J_1907_R()), this.Y_601_j.R_4764_Y(entry.getLongKey()), !this.P_1922_E(chunkpos), optional1.map(p_lambda$func_225406_a$41_0_ -> Stream.of(p_lambda$func_225406_a$41_0_.getEntityLists()).mapToInt(e_1322_b::size).sum()).orElse(0), optional1.map(p_lambda$func_225406_a$42_0_ -> p_lambda$func_225406_a$42_0_.getTileEntityMap().size()).orElse(0)});
        }
    }

    private static String n_1700_B(CompletableFuture<Either<H_1748_a, y_3683_b.n_1700_B>> p_225402_0_) {
        try {
            Either<H_1748_a, y_3683_b.n_1700_B> either = p_225402_0_.getNow((Either<H_1748_a, y_3683_b.n_1700_B>)((Either)null));
            return either != null ? (String)either.map(p_lambda$func_225402_a$43_0_ -> "done", p_lambda$func_225402_a$44_0_ -> "unloaded") : "not completed";
        }
        catch (CompletionException completionexception) {
            return "failed " + completionexception.getCause().getMessage();
        }
        catch (CancellationException cancellationexception1) {
            return "cancelled";
        }
    }

    @Nullable
    private U_2912_j t_148_a(Y_1387_d pos) throws IOException {
        U_2912_j compoundnbt = this.n_1700_B(pos);
        return compoundnbt == null ? null : this.n_1700_B(this.w_1484_f.g_2268_R(), this.M_588_G, compoundnbt);
    }

    boolean P_1922_E(Y_1387_d chunkPosIn) {
        long i = chunkPosIn.n_1700_B();
        return !this.Y_601_j.G_564_y(i) ? true : this.k_2293_S.n_1700_B(i).noneMatch(p_lambda$isOutsideSpawningRadius$45_1_ -> !p_lambda$isOutsideSpawningRadius$45_1_.d_2461_k() && y_1195_s.n_1700_B(chunkPosIn, (N_4263_v)p_lambda$isOutsideSpawningRadius$45_1_) < 16384.0);
    }

    private boolean J_1907_R(B_4088_l player) {
        return player.d_2461_k() && !this.w_1484_f.H_1990_U().J_1907_R(A_2352_Z.M_182_A);
    }

    void n_1700_B(B_4088_l player, boolean track) {
        boolean flag = this.J_1907_R(player);
        boolean flag1 = this.k_2293_S.R_4764_Y(player);
        int i = u_530_F.R_4764_Y(player.O_3598_v()) >> 4;
        int j = u_530_F.R_4764_Y(player.l_2647_k()) >> 4;
        if (track) {
            this.k_2293_S.n_1700_B(Y_1387_d.n_1700_B(i, j), player, flag);
            this.R_4764_Y(player);
            if (!flag) {
                this.Y_601_j.n_1700_B(SectionPos.n_1700_B(player), player);
            }
        } else {
            SectionPos sectionpos = player.c_4037_x();
            this.k_2293_S.n_1700_B(sectionpos.M_588_G().n_1700_B(), player);
            if (!flag1) {
                this.Y_601_j.J_1907_R(sectionpos, player);
            }
        }
        for (int l = i - this.H_2857_Y; l <= i + this.H_2857_Y; ++l) {
            for (int k = j - this.H_2857_Y; k <= j + this.H_2857_Y; ++k) {
                Y_1387_d chunkpos = new Y_1387_d(l, k);
                this.n_1700_B(player, chunkpos, new Packet[2], !track, track);
            }
        }
    }

    private SectionPos R_4764_Y(B_4088_l serverPlayerEntity) {
        SectionPos sectionpos = SectionPos.n_1700_B(serverPlayerEntity);
        serverPlayerEntity.n_1700_B(sectionpos);
        serverPlayerEntity.n_1700_B.n_1700_B(new p_198_K(sectionpos.n_1700_B(), sectionpos.R_4764_Y()));
        return sectionpos;
    }

    public void n_1700_B(B_4088_l player) {
        boolean flag2;
        for (n_1700_B chunkmanager$entitytracker : this.q_2307_F.values()) {
            if (chunkmanager$entitytracker.R_4764_Y == player) {
                chunkmanager$entitytracker.n_1700_B(this.w_1484_f.multiplayerClientSuggestionProvider());
                continue;
            }
            chunkmanager$entitytracker.J_1907_R(player);
        }
        int l1 = u_530_F.R_4764_Y(player.O_3598_v()) >> 4;
        int i2 = u_530_F.R_4764_Y(player.l_2647_k()) >> 4;
        SectionPos sectionpos = player.c_4037_x();
        SectionPos sectionpos1 = SectionPos.n_1700_B(player);
        long i = sectionpos.M_588_G().n_1700_B();
        long j = sectionpos1.M_588_G().n_1700_B();
        boolean flag = this.k_2293_S.G_564_y(player);
        boolean flag1 = this.J_1907_R(player);
        boolean bl = flag2 = sectionpos.P_4830_p() != sectionpos1.P_4830_p();
        if (flag2 || flag != flag1) {
            this.R_4764_Y(player);
            if (!flag) {
                this.Y_601_j.J_1907_R(sectionpos, player);
            }
            if (!flag1) {
                this.Y_601_j.n_1700_B(sectionpos1, player);
            }
            if (!flag && flag1) {
                this.k_2293_S.n_1700_B(player);
            }
            if (flag && !flag1) {
                this.k_2293_S.J_1907_R(player);
            }
            if (i != j) {
                this.k_2293_S.n_1700_B(i, j, player);
            }
        }
        int k = sectionpos.n_1700_B();
        int l = sectionpos.R_4764_Y();
        if (Math.abs(k - l1) <= this.H_2857_Y * 2 && Math.abs(l - i2) <= this.H_2857_Y * 2) {
            int k2 = Math.min(l1, k) - this.H_2857_Y;
            int i3 = Math.min(i2, l) - this.H_2857_Y;
            int j3 = Math.max(l1, k) + this.H_2857_Y;
            int k3 = Math.max(i2, l) + this.H_2857_Y;
            for (int l3 = k2; l3 <= j3; ++l3) {
                for (int k1 = i3; k1 <= k3; ++k1) {
                    Y_1387_d chunkpos1 = new Y_1387_d(l3, k1);
                    boolean flag5 = y_1195_s.n_1700_B(chunkpos1, k, l) <= this.H_2857_Y;
                    boolean flag6 = y_1195_s.n_1700_B(chunkpos1, l1, i2) <= this.H_2857_Y;
                    this.n_1700_B(player, chunkpos1, new Packet[2], flag5, flag6);
                }
            }
        } else {
            for (int i1 = k - this.H_2857_Y; i1 <= k + this.H_2857_Y; ++i1) {
                for (int j1 = l - this.H_2857_Y; j1 <= l + this.H_2857_Y; ++j1) {
                    Y_1387_d chunkpos = new Y_1387_d(i1, j1);
                    boolean flag3 = true;
                    boolean flag4 = false;
                    this.n_1700_B(player, chunkpos, new Packet[2], true, false);
                }
            }
            for (int j2 = l1 - this.H_2857_Y; j2 <= l1 + this.H_2857_Y; ++j2) {
                for (int l2 = i2 - this.H_2857_Y; l2 <= i2 + this.H_2857_Y; ++l2) {
                    Y_1387_d chunkpos2 = new Y_1387_d(j2, l2);
                    boolean flag7 = false;
                    boolean flag8 = true;
                    this.n_1700_B(player, chunkpos2, new Packet[2], false, true);
                }
            }
        }
    }

    @Override
    public Stream<B_4088_l> n_1700_B(Y_1387_d pos, boolean boundaryOnly) {
        return this.k_2293_S.n_1700_B(pos.n_1700_B()).filter(p_lambda$getTrackingPlayers$46_3_ -> {
            int i = y_1195_s.n_1700_B(pos, p_lambda$getTrackingPlayers$46_3_, true);
            if (i > this.H_2857_Y) {
                return false;
            }
            return !boundaryOnly || i == this.H_2857_Y;
        });
    }

    protected void n_1700_B(N_4263_v entityIn) {
        boolean flag = entityIn instanceof EnderDragonPart;
        if (Reflector.PartEntity.exists()) {
            flag = Reflector.PartEntity.isInstance(entityIn);
        }
        if (!flag) {
            t_5_h<?> entitytype = entityIn.f_4016_n();
            int i = entitytype.M_588_G() * 16;
            int j = entitytype.P_4830_p();
            if (this.q_2307_F.containsKey(entityIn.j_276_v())) {
                throw j_3341_s.R_4764_Y(new IllegalStateException("Entity is already tracked!"));
            }
            n_1700_B chunkmanager$entitytracker = new n_1700_B(entityIn, i, j, entitytype.h_1847_R());
            this.q_2307_F.put(entityIn.j_276_v(), (Object)chunkmanager$entitytracker);
            chunkmanager$entitytracker.n_1700_B(this.w_1484_f.multiplayerClientSuggestionProvider());
            if (entityIn instanceof B_4088_l) {
                B_4088_l serverplayerentity = (B_4088_l)entityIn;
                this.n_1700_B(serverplayerentity, true);
                for (n_1700_B chunkmanager$entitytracker1 : this.q_2307_F.values()) {
                    if (chunkmanager$entitytracker1.R_4764_Y == serverplayerentity) continue;
                    chunkmanager$entitytracker1.J_1907_R(serverplayerentity);
                }
            }
        }
    }

    protected void J_1907_R(N_4263_v entity) {
        n_1700_B chunkmanager$entitytracker1;
        if (entity instanceof B_4088_l) {
            B_4088_l serverplayerentity = (B_4088_l)entity;
            this.n_1700_B(serverplayerentity, false);
            for (n_1700_B chunkmanager$entitytracker : this.q_2307_F.values()) {
                chunkmanager$entitytracker.n_1700_B(serverplayerentity);
            }
        }
        if ((chunkmanager$entitytracker1 = (n_1700_B)this.q_2307_F.remove(entity.j_276_v())) != null) {
            chunkmanager$entitytracker1.n_1700_B();
        }
    }

    protected void w_1484_f() {
        ArrayList list = Lists.newArrayList();
        List<B_4088_l> list1 = this.w_1484_f.multiplayerClientSuggestionProvider();
        for (n_1700_B chunkmanager$entitytracker : this.q_2307_F.values()) {
            SectionPos sectionpos = chunkmanager$entitytracker.P_1922_E;
            SectionPos sectionpos1 = SectionPos.n_1700_B(chunkmanager$entitytracker.R_4764_Y);
            if (!Objects.equals(sectionpos, sectionpos1)) {
                chunkmanager$entitytracker.n_1700_B(list1);
                N_4263_v entity = chunkmanager$entitytracker.R_4764_Y;
                if (entity instanceof B_4088_l) {
                    list.add((B_4088_l)entity);
                }
                chunkmanager$entitytracker.P_1922_E = sectionpos1;
            }
            chunkmanager$entitytracker.J_1907_R.n_1700_B();
        }
        if (!list.isEmpty()) {
            for (n_1700_B chunkmanager$entitytracker1 : this.q_2307_F.values()) {
                chunkmanager$entitytracker1.n_1700_B(list);
            }
        }
    }

    protected void n_1700_B(N_4263_v entity, Packet<?> p_219222_2_) {
        n_1700_B chunkmanager$entitytracker = (n_1700_B)this.q_2307_F.get(entity.j_276_v());
        if (chunkmanager$entitytracker != null) {
            chunkmanager$entitytracker.n_1700_B(p_219222_2_);
        }
    }

    protected void J_1907_R(N_4263_v entity, Packet<?> p_219225_2_) {
        n_1700_B chunkmanager$entitytracker = (n_1700_B)this.q_2307_F.get(entity.j_276_v());
        if (chunkmanager$entitytracker != null) {
            chunkmanager$entitytracker.J_1907_R(p_219225_2_);
        }
    }

    private void n_1700_B(B_4088_l player, Packet<?>[] packetCache, H_1748_a chunkIn) {
        if (packetCache[0] == null) {
            packetCache[0] = new U_157_Y(chunkIn, 65535);
            packetCache[1] = new ClientboundLightUpdatePacket(chunkIn.getPos(), this.t_148_a, true);
        }
        player.n_1700_B(chunkIn.getPos(), packetCache[0], packetCache[1]);
        DebugPackets.n_1700_B(this.w_1484_f, chunkIn.getPos());
        ArrayList list = Lists.newArrayList();
        ArrayList list1 = Lists.newArrayList();
        for (n_1700_B chunkmanager$entitytracker : this.q_2307_F.values()) {
            N_4263_v entity = chunkmanager$entitytracker.R_4764_Y;
            if (entity == player || entity.u_744_e != chunkIn.getPos().J_1907_R || entity.r_3651_U != chunkIn.getPos().R_4764_Y) continue;
            chunkmanager$entitytracker.J_1907_R(player);
            if (entity instanceof Z_530_i && ((Z_530_i)entity).y_2622_c() != null) {
                list.add(entity);
            }
            if (entity.o_3599_Z().isEmpty()) continue;
            list1.add(entity);
        }
        if (!list.isEmpty()) {
            for (N_4263_v entity1 : list) {
                player.n_1700_B.n_1700_B(new e_446_u(entity1, ((Z_530_i)entity1).y_2622_c()));
            }
        }
        if (!list1.isEmpty()) {
            for (N_4263_v entity2 : list1) {
                player.n_1700_B.n_1700_B(new ClientboundSetPassengersPacket(entity2));
            }
        }
    }

    protected b_4946_z t_148_a() {
        return this.P_4830_p;
    }

    public CompletableFuture<Void> n_1700_B(H_1748_a chunk) {
        return this.s_956_w.u_1723_Y(() -> chunk.saveScheduledTicks(this.w_1484_f));
    }

    class J_1907_R
    extends l_4108_L {
        protected J_1907_R(Executor p_i50469_2_, Executor p_i50469_3_) {
            super(p_i50469_2_, p_i50469_3_);
        }

        @Override
        protected boolean n_1700_B(long p_219371_1_) {
            return y_1195_s.this.h_1847_R.contains(p_219371_1_);
        }

        @Override
        @Nullable
        protected y_3683_b J_1907_R(long chunkPosIn) {
            return y_1195_s.this.n_1700_B(chunkPosIn);
        }

        @Override
        @Nullable
        protected y_3683_b n_1700_B(long chunkPosIn, int newLevel, @Nullable y_3683_b holder, int oldLevel) {
            return y_1195_s.this.n_1700_B(chunkPosIn, newLevel, holder, oldLevel);
        }
    }

    class n_1700_B {
        private final N_404_o J_1907_R;
        private final N_4263_v R_4764_Y;
        private final int G_564_y;
        private SectionPos P_1922_E;
        private final Set<B_4088_l> u_1723_Y = Sets.newHashSet();

        public n_1700_B(N_4263_v entity, int p_i50468_3_, int updateFrequency, boolean sendVelocityUpdates) {
            this.J_1907_R = new N_404_o(y_1195_s.this.w_1484_f, entity, updateFrequency, sendVelocityUpdates, this::n_1700_B);
            this.R_4764_Y = entity;
            this.G_564_y = p_i50468_3_;
            this.P_1922_E = SectionPos.n_1700_B(entity);
        }

        public boolean equals(Object p_equals_1_) {
            if (p_equals_1_ instanceof n_1700_B) {
                return ((n_1700_B)p_equals_1_).R_4764_Y.j_276_v() == this.R_4764_Y.j_276_v();
            }
            return false;
        }

        public int hashCode() {
            return this.R_4764_Y.j_276_v();
        }

        public void n_1700_B(Packet<?> p_219391_1_) {
            for (B_4088_l serverplayerentity : this.u_1723_Y) {
                serverplayerentity.n_1700_B.n_1700_B(p_219391_1_);
            }
        }

        public void J_1907_R(Packet<?> p_219392_1_) {
            this.n_1700_B(p_219392_1_);
            if (this.R_4764_Y instanceof B_4088_l) {
                ((B_4088_l)this.R_4764_Y).n_1700_B.n_1700_B(p_219392_1_);
            }
        }

        public void n_1700_B() {
            for (B_4088_l serverplayerentity : this.u_1723_Y) {
                this.J_1907_R.n_1700_B(serverplayerentity);
            }
        }

        public void n_1700_B(B_4088_l player) {
            if (this.u_1723_Y.remove(player)) {
                this.J_1907_R.n_1700_B(player);
            }
        }

        public void J_1907_R(B_4088_l player) {
            if (player != this.R_4764_Y) {
                boolean flag;
                e_2866_D vector3d = player.s_4990_V().G_564_y(this.J_1907_R.J_1907_R());
                int i = Math.min(this.J_1907_R(), (y_1195_s.this.H_2857_Y - 1) * 16);
                boolean bl = flag = vector3d.J_1907_R >= (double)(-i) && vector3d.J_1907_R <= (double)i && vector3d.G_564_y >= (double)(-i) && vector3d.G_564_y <= (double)i && this.R_4764_Y.n_1700_B(player);
                if (flag) {
                    Y_1387_d chunkpos;
                    y_3683_b chunkholder;
                    boolean flag1 = this.R_4764_Y.z_1333_t;
                    if (!flag1 && (chunkholder = y_1195_s.this.J_1907_R((chunkpos = new Y_1387_d(this.R_4764_Y.u_744_e, this.R_4764_Y.r_3651_U)).n_1700_B())) != null && chunkholder.G_564_y() != null) {
                        boolean bl2 = flag1 = y_1195_s.n_1700_B(chunkpos, player, false) <= y_1195_s.this.H_2857_Y;
                    }
                    if (flag1 && this.u_1723_Y.add(player)) {
                        this.J_1907_R.J_1907_R(player);
                    }
                } else if (this.u_1723_Y.remove(player)) {
                    this.J_1907_R.n_1700_B(player);
                }
            }
        }

        private int n_1700_B(int p_241091_1_) {
            return y_1195_s.this.w_1484_f.T_2506_i().J_1907_R(p_241091_1_);
        }

        private int J_1907_R() {
            Collection<N_4263_v> collection = this.R_4764_Y.X_290_I();
            int i = this.G_564_y;
            for (N_4263_v entity : collection) {
                int j = entity.f_4016_n().M_588_G() * 16;
                if (j <= i) continue;
                i = j;
            }
            return this.n_1700_B(i);
        }

        public void n_1700_B(List<B_4088_l> playersList) {
            for (B_4088_l serverplayerentity : playersList) {
                this.J_1907_R(serverplayerentity);
            }
        }
    }
}


