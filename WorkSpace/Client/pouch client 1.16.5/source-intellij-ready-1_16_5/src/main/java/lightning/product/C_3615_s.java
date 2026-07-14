/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.datafixers.util.Either
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Either;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.ChunkSource;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.ChunkStatus;
import lightning.product.BlockGetter;
import lightning.product.H_1748_a;
import lightning.product.H_3272_P;
import lightning.product.K_4719_o;
import lightning.product.N_4263_v;
import lightning.product.R_1900_x;
import lightning.product.LevelData;
import lightning.product.ProfilerFiller;
import lightning.product.Y_1387_d;
import lightning.product.b_2085_h;
import lightning.product.b_2971_z;
import lightning.product.b_4507_u;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.ChunkAccess;
import lightning.product.e_2754_J;
import lightning.product.e_3591_l;
import lightning.product.TicketType;
import lightning.product.j_3341_s;
import lightning.product.ChunkProgressListener;
import lightning.product.l_4108_L;
import lightning.product.s_4380_l;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.u_743_i;
import lightning.product.y_1195_s;
import lightning.product.y_3683_b;
import lightning.product.z_1753_f;

public class C_3615_s
extends ChunkSource {
    private static final List<ChunkStatus> J_1907_R = ChunkStatus.n_1700_B();
    private final l_4108_L R_4764_Y;
    private final z_1753_f G_564_y;
    private final e_3591_l P_1922_E;
    private final Thread u_1723_Y;
    private final e_2754_J v_4262_N;
    private final n_1700_B w_1484_f;
    public final y_1195_s n_1700_B;
    private final s_4380_l t_148_a;
    private long s_956_w;
    private boolean u_2550_I = true;
    private boolean M_588_G = true;
    private final long[] P_4830_p = new long[4];
    private final ChunkStatus[] h_1847_R = new ChunkStatus[4];
    private final ChunkAccess[] Q_4569_t = new ChunkAccess[4];
    @Nullable
    private u_743_i.n_1700_B M_182_A;

    public C_3615_s(e_3591_l p_i232603_1_, b_2971_z.n_1700_B p_i232603_2_, DataFixer p_i232603_3_, b_2085_h p_i232603_4_, Executor p_i232603_5_, z_1753_f p_i232603_6_, int p_i232603_7_, boolean p_i232603_8_, ChunkProgressListener p_i232603_9_, Supplier<s_4380_l> p_i232603_10_) {
        this.P_1922_E = p_i232603_1_;
        this.w_1484_f = new n_1700_B(p_i232603_1_);
        this.G_564_y = p_i232603_6_;
        this.u_1723_Y = Thread.currentThread();
        File file1 = p_i232603_2_.n_1700_B(p_i232603_1_.g_2268_R());
        File file2 = new File(file1, "data");
        file2.mkdirs();
        this.t_148_a = new s_4380_l(file2, p_i232603_3_);
        this.n_1700_B = new y_1195_s(p_i232603_1_, p_i232603_2_, p_i232603_3_, p_i232603_4_, p_i232603_5_, this.w_1484_f, this, this.t_148_a(), p_i232603_9_, p_i232603_10_, p_i232603_7_, p_i232603_8_);
        this.v_4262_N = this.n_1700_B.J_1907_R();
        this.R_4764_Y = this.n_1700_B.u_1723_Y();
        this.h_1847_R();
    }

    public e_2754_J R_4764_Y() {
        return this.v_4262_N;
    }

    @Nullable
    private y_3683_b n_1700_B(long chunkPosIn) {
        return this.n_1700_B.J_1907_R(chunkPosIn);
    }

    public int P_1922_E() {
        return this.n_1700_B.G_564_y();
    }

    private void n_1700_B(long p_225315_1_, ChunkAccess p_225315_3_, ChunkStatus p_225315_4_) {
        for (int i = 3; i > 0; --i) {
            this.P_4830_p[i] = this.P_4830_p[i - 1];
            this.h_1847_R[i] = this.h_1847_R[i - 1];
            this.Q_4569_t[i] = this.Q_4569_t[i - 1];
        }
        this.P_4830_p[0] = p_225315_1_;
        this.h_1847_R[0] = p_225315_4_;
        this.Q_4569_t[0] = p_225315_3_;
    }

    @Override
    @Nullable
    public ChunkAccess J_1907_R(int chunkX, int chunkZ, ChunkStatus requiredStatus, boolean load) {
        if (Thread.currentThread() != this.u_1723_Y) {
            return CompletableFuture.supplyAsync(() -> this.J_1907_R(chunkX, chunkZ, requiredStatus, load), this.w_1484_f).join();
        }
        ProfilerFiller iprofiler = this.P_1922_E.D_4792_h();
        iprofiler.R_4764_Y("getChunk");
        long i = Y_1387_d.n_1700_B(chunkX, chunkZ);
        for (int j = 0; j < 4; ++j) {
            ChunkAccess ichunk;
            if (i != this.P_4830_p[j] || requiredStatus != this.h_1847_R[j] || (ichunk = this.Q_4569_t[j]) == null && load) continue;
            return ichunk;
        }
        iprofiler.R_4764_Y("getChunkCacheMiss");
        CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> completablefuture = this.R_4764_Y(chunkX, chunkZ, requiredStatus, load);
        this.w_1484_f.R_4764_Y(completablefuture::isDone);
        ChunkAccess ichunk1 = (ChunkAccess)completablefuture.join().map(p_222874_0_ -> p_222874_0_, p_222870_1_ -> {
            if (load) {
                throw j_3341_s.R_4764_Y(new IllegalStateException("Chunk not there when requested: " + String.valueOf(p_222870_1_)));
            }
            return null;
        });
        this.n_1700_B(i, ichunk1, requiredStatus);
        return ichunk1;
    }

    @Override
    @Nullable
    public H_1748_a R_4764_Y(int chunkX, int chunkZ) {
        if (Thread.currentThread() != this.u_1723_Y) {
            return null;
        }
        this.P_1922_E.D_4792_h().R_4764_Y("getChunkNow");
        long i = Y_1387_d.n_1700_B(chunkX, chunkZ);
        for (int j = 0; j < 4; ++j) {
            if (i != this.P_4830_p[j] || this.h_1847_R[j] != ChunkStatus.P_4830_p) continue;
            ChunkAccess ichunk = this.Q_4569_t[j];
            return ichunk instanceof H_1748_a ? (H_1748_a)ichunk : null;
        }
        y_3683_b chunkholder = this.n_1700_B(i);
        if (chunkholder == null) {
            return null;
        }
        Either<ChunkAccess, y_3683_b.n_1700_B> either = chunkholder.J_1907_R(ChunkStatus.P_4830_p).getNow((Either<ChunkAccess, y_3683_b.n_1700_B>)((Either)null));
        if (either == null) {
            return null;
        }
        ChunkAccess ichunk1 = either.left().orElse(null);
        if (ichunk1 != null) {
            this.n_1700_B(i, ichunk1, ChunkStatus.P_4830_p);
            if (ichunk1 instanceof H_1748_a) {
                return (H_1748_a)ichunk1;
            }
        }
        return null;
    }

    private void h_1847_R() {
        Arrays.fill(this.P_4830_p, Y_1387_d.n_1700_B);
        Arrays.fill(this.h_1847_R, null);
        Arrays.fill(this.Q_4569_t, null);
    }

    public CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> n_1700_B(int chunkX, int chunkZ, ChunkStatus requiredStatus, boolean load) {
        CompletionStage<Object> completablefuture;
        boolean flag;
        boolean bl = flag = Thread.currentThread() == this.u_1723_Y;
        if (flag) {
            completablefuture = this.R_4764_Y(chunkX, chunkZ, requiredStatus, load);
            this.w_1484_f.R_4764_Y(() -> completablefuture.isDone());
        } else {
            completablefuture = CompletableFuture.supplyAsync(() -> this.R_4764_Y(chunkX, chunkZ, requiredStatus, load), this.w_1484_f).thenCompose(p_217211_0_ -> p_217211_0_);
        }
        return completablefuture;
    }

    private CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> R_4764_Y(int chunkX, int chunkZ, ChunkStatus requiredStatus, boolean load) {
        Y_1387_d chunkpos = new Y_1387_d(chunkX, chunkZ);
        long i = chunkpos.n_1700_B();
        int j = 33 + ChunkStatus.n_1700_B(requiredStatus);
        y_3683_b chunkholder = this.n_1700_B(i);
        if (load) {
            this.R_4764_Y.n_1700_B(TicketType.w_1484_f, chunkpos, j, chunkpos);
            if (this.n_1700_B(chunkholder, j)) {
                ProfilerFiller iprofiler = this.P_1922_E.D_4792_h();
                iprofiler.n_1700_B("chunkLoad");
                this.Q_4569_t();
                chunkholder = this.n_1700_B(i);
                iprofiler.R_4764_Y();
                if (this.n_1700_B(chunkholder, j)) {
                    throw j_3341_s.R_4764_Y(new IllegalStateException("No chunk holder after ticket has been added"));
                }
            }
        }
        return this.n_1700_B(chunkholder, j) ? y_3683_b.J_1907_R : chunkholder.n_1700_B(requiredStatus, this.n_1700_B);
    }

    private boolean n_1700_B(@Nullable y_3683_b chunkHolderIn, int p_217224_2_) {
        return chunkHolderIn == null || chunkHolderIn.s_956_w() > p_217224_2_;
    }

    @Override
    public boolean P_1922_E(int x, int z) {
        int i;
        y_3683_b chunkholder = this.n_1700_B(new Y_1387_d(x, z).n_1700_B());
        return !this.n_1700_B(chunkholder, i = 33 + ChunkStatus.n_1700_B(ChunkStatus.P_4830_p));
    }

    @Override
    public BlockGetter G_564_y(int chunkX, int chunkZ) {
        long i = Y_1387_d.n_1700_B(chunkX, chunkZ);
        y_3683_b chunkholder = this.n_1700_B(i);
        if (chunkholder == null) {
            return null;
        }
        int j = J_1907_R.size() - 1;
        ChunkStatus chunkstatus;
        Optional optional;
        while (!(optional = chunkholder.n_1700_B(chunkstatus = J_1907_R.get(j)).getNow(y_3683_b.n_1700_B).left()).isPresent()) {
            if (chunkstatus == ChunkStatus.s_956_w.P_1922_E()) {
                return null;
            }
            --j;
        }
        return (BlockGetter)optional.get();
    }

    public b_4507_u u_1723_Y() {
        return this.P_1922_E;
    }

    public boolean v_4262_N() {
        return this.w_1484_f.l_();
    }

    private boolean Q_4569_t() {
        boolean flag = this.R_4764_Y.n_1700_B(this.n_1700_B);
        boolean flag1 = this.n_1700_B.R_4764_Y();
        if (!flag && !flag1) {
            return false;
        }
        this.h_1847_R();
        return true;
    }

    @Override
    public boolean n_1700_B(N_4263_v entityIn) {
        long i = Y_1387_d.n_1700_B(u_530_F.R_4764_Y(entityIn.O_3598_v()) >> 4, u_530_F.R_4764_Y(entityIn.l_2647_k()) >> 4);
        return this.n_1700_B(i, y_3683_b::J_1907_R);
    }

    @Override
    public boolean n_1700_B(Y_1387_d pos) {
        return this.n_1700_B(pos.n_1700_B(), y_3683_b::J_1907_R);
    }

    @Override
    public boolean n_1700_B(c_1514_x pos) {
        long i = Y_1387_d.n_1700_B(pos.getX() >> 4, pos.getZ() >> 4);
        return this.n_1700_B(i, y_3683_b::n_1700_B);
    }

    private boolean n_1700_B(long pos, Function<y_3683_b, CompletableFuture<Either<H_1748_a, y_3683_b.n_1700_B>>> p_222872_3_) {
        y_3683_b chunkholder = this.n_1700_B(pos);
        if (chunkholder == null) {
            return false;
        }
        Either<H_1748_a, y_3683_b.n_1700_B> either = p_222872_3_.apply(chunkholder).getNow(y_3683_b.R_4764_Y);
        return either.left().isPresent();
    }

    public void n_1700_B(boolean flush) {
        this.Q_4569_t();
        this.n_1700_B.n_1700_B(flush);
    }

    @Override
    public void close() throws IOException {
        this.n_1700_B(true);
        this.v_4262_N.close();
        this.n_1700_B.close();
    }

    public void n_1700_B(BooleanSupplier hasTimeLeft) {
        this.P_1922_E.D_4792_h().n_1700_B("purge");
        this.R_4764_Y.n_1700_B();
        this.Q_4569_t();
        this.P_1922_E.D_4792_h().J_1907_R("chunks");
        this.M_182_A();
        this.P_1922_E.D_4792_h().J_1907_R("unload");
        this.n_1700_B.n_1700_B(hasTimeLeft);
        this.P_1922_E.D_4792_h().R_4764_Y();
        this.h_1847_R();
    }

    private void M_182_A() {
        long i = this.P_1922_E.X_933_l();
        long j = i - this.s_956_w;
        this.s_956_w = i;
        LevelData iworldinfo = this.P_1922_E.k_2293_S();
        boolean flag = this.P_1922_E.l_1233_K();
        boolean flag1 = this.P_1922_E.H_1990_U().J_1907_R(A_2352_Z.G_564_y);
        if (!flag) {
            u_743_i.n_1700_B worldentityspawner$entitydensitymanager;
            this.P_1922_E.D_4792_h().n_1700_B("pollingChunks");
            int k = this.P_1922_E.H_1990_U().R_4764_Y(A_2352_Z.P_4830_p);
            boolean flag2 = iworldinfo.P_1922_E() % 400L == 0L;
            this.P_1922_E.D_4792_h().n_1700_B("naturalSpawnCount");
            int l = this.R_4764_Y.J_1907_R();
            this.M_182_A = worldentityspawner$entitydensitymanager = u_743_i.n_1700_B(l, this.P_1922_E.f_4016_n(), this::n_1700_B);
            this.P_1922_E.D_4792_h().R_4764_Y();
            ArrayList list = Lists.newArrayList(this.n_1700_B.v_4262_N());
            Collections.shuffle(list);
            list.forEach(p_241099_7_ -> {
                Optional optional = p_241099_7_.n_1700_B().getNow(y_3683_b.R_4764_Y).left();
                if (optional.isPresent()) {
                    this.P_1922_E.D_4792_h().n_1700_B("broadcast");
                    p_241099_7_.n_1700_B((H_1748_a)optional.get());
                    this.P_1922_E.D_4792_h().R_4764_Y();
                    Optional optional1 = p_241099_7_.J_1907_R().getNow(y_3683_b.R_4764_Y).left();
                    if (optional1.isPresent()) {
                        H_1748_a chunk = (H_1748_a)optional1.get();
                        Y_1387_d chunkpos = p_241099_7_.t_148_a();
                        if (!this.n_1700_B.P_1922_E(chunkpos)) {
                            chunk.setInhabitedTime(chunk.getInhabitedTime() + j);
                            if (flag1 && (this.u_2550_I || this.M_588_G) && this.P_1922_E.H_2857_Y().n_1700_B(chunk.getPos())) {
                                u_743_i.n_1700_B(this.P_1922_E, chunk, worldentityspawner$entitydensitymanager, this.M_588_G, this.u_2550_I, flag2);
                            }
                            this.P_1922_E.n_1700_B(chunk, k);
                        }
                    }
                }
            });
            this.P_1922_E.D_4792_h().n_1700_B("customSpawners");
            if (flag1) {
                this.P_1922_E.J_1907_R(this.u_2550_I, this.M_588_G);
            }
            this.P_1922_E.D_4792_h().R_4764_Y();
            this.P_1922_E.D_4792_h().R_4764_Y();
        }
        this.n_1700_B.w_1484_f();
    }

    private void n_1700_B(long p_241098_1_, Consumer<H_1748_a> p_241098_3_) {
        y_3683_b chunkholder = this.n_1700_B(p_241098_1_);
        if (chunkholder != null) {
            chunkholder.R_4764_Y().getNow(y_3683_b.R_4764_Y).left().ifPresent(p_241098_3_);
        }
    }

    @Override
    public String J_1907_R() {
        return "ServerChunkCache: " + this.s_956_w();
    }

    @VisibleForTesting
    public int w_1484_f() {
        return this.w_1484_f.RealmsLongRunningMcoTaskScreen();
    }

    public z_1753_f t_148_a() {
        return this.G_564_y;
    }

    public int s_956_w() {
        return this.n_1700_B.P_1922_E();
    }

    public void J_1907_R(c_1514_x pos) {
        int j;
        int i = pos.getX() >> 4;
        y_3683_b chunkholder = this.n_1700_B(Y_1387_d.n_1700_B(i, j = pos.getZ() >> 4));
        if (chunkholder != null) {
            chunkholder.n_1700_B(pos);
        }
    }

    @Override
    public void n_1700_B(K_4719_o type, SectionPos pos) {
        this.w_1484_f.execute(() -> {
            y_3683_b chunkholder = this.n_1700_B(pos.M_588_G().n_1700_B());
            if (chunkholder != null) {
                chunkholder.n_1700_B(type, pos.J_1907_R());
            }
        });
    }

    public <T> void n_1700_B(TicketType<T> type, Y_1387_d pos, int distance, T value) {
        this.R_4764_Y.R_4764_Y(type, pos, distance, value);
    }

    public <T> void J_1907_R(TicketType<T> type, Y_1387_d pos, int distance, T value) {
        this.R_4764_Y.G_564_y(type, pos, distance, value);
    }

    @Override
    public void n_1700_B(Y_1387_d pos, boolean add) {
        this.R_4764_Y.n_1700_B(pos, add);
    }

    public void n_1700_B(B_4088_l player) {
        this.n_1700_B.n_1700_B(player);
    }

    public void J_1907_R(N_4263_v entityIn) {
        this.n_1700_B.J_1907_R(entityIn);
    }

    public void R_4764_Y(N_4263_v entityIn) {
        this.n_1700_B.n_1700_B(entityIn);
    }

    public void n_1700_B(N_4263_v entityIn, Packet<?> packet) {
        this.n_1700_B.J_1907_R(entityIn, packet);
    }

    public void J_1907_R(N_4263_v entityIn, Packet<?> packet) {
        this.n_1700_B.n_1700_B(entityIn, packet);
    }

    public void n_1700_B(int viewDistance) {
        this.n_1700_B.n_1700_B(viewDistance);
    }

    @Override
    public void n_1700_B(boolean hostile, boolean peaceful) {
        this.u_2550_I = hostile;
        this.M_588_G = peaceful;
    }

    public String J_1907_R(Y_1387_d chunkPosIn) {
        return this.n_1700_B.J_1907_R(chunkPosIn);
    }

    public s_4380_l u_2550_I() {
        return this.t_148_a;
    }

    public b_4946_z M_588_G() {
        return this.n_1700_B.t_148_a();
    }

    @Nullable
    public u_743_i.n_1700_B P_4830_p() {
        return this.M_182_A;
    }

    @Override
    public /* synthetic */ R_1900_x G_564_y() {
        return this.R_4764_Y();
    }

    @Override
    public /* synthetic */ BlockGetter n_1700_B() {
        return this.u_1723_Y();
    }

    final class n_1700_B
    extends H_3272_P<Runnable> {
        private n_1700_B(b_4507_u worldIn) {
            super("Chunk source main thread executor for " + String.valueOf(worldIn.g_2268_R().n_1700_B()));
        }

        @Override
        protected Runnable n_1700_B(Runnable runnable) {
            return runnable;
        }

        @Override
        protected boolean J_1907_R(Runnable runnable) {
            return true;
        }

        @Override
        protected boolean j_() {
            return true;
        }

        @Override
        protected Thread l_1233_K() {
            return C_3615_s.this.u_1723_Y;
        }

        @Override
        protected void P_1922_E(Runnable taskIn) {
            C_3615_s.this.P_1922_E.D_4792_h().R_4764_Y("runTask");
            super.P_1922_E(taskIn);
        }

        @Override
        protected boolean l_() {
            if (C_3615_s.this.Q_4569_t()) {
                return true;
            }
            C_3615_s.this.v_4262_N.J_1907_R();
            return super.l_();
        }
    }
}


