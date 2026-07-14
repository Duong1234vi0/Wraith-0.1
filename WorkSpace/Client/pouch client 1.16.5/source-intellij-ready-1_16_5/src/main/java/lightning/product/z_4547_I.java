/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Queues
 *  com.google.common.collect.Sets
 *  com.google.common.primitives.Doubles
 *  it.unimi.dsi.fastutil.objects.ObjectArraySet
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.google.common.primitives.Doubles;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;
import lightning.product.A_4115_X;
import lightning.product.B_1647_r;
import lightning.product.ChunkStatus;
import lightning.product.D_3318_r;
import lightning.product.D_4883_k;
import lightning.product.E_688_b;
import lightning.product.BlockGetter;
import lightning.product.H_1748_a;
import lightning.product.I_4817_s;
import lightning.product.K_4074_S;
import lightning.product.O_2369_F;
import lightning.product.T_2915_h;
import lightning.product.U_3758_m;
import lightning.product.W_571_B;
import lightning.product.Y_1387_d;
import lightning.product.Y_3830_x;
import lightning.product.Z_2812_M;
import lightning.product.Z_4734_t;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.d_1620_j;
import lightning.product.e_2866_D;
import lightning.product.e_3977_C;
import lightning.product.f_2689_h;
import lightning.product.g_221_o;
import lightning.product.VisGraph;
import lightning.product.h_3572_K;
import lightning.product.i_2154_H;
import lightning.product.CactusBlock;
import lightning.product.j_3341_s;
import lightning.product.k_4690_i;
import lightning.product.l_1802_R;
import lightning.product.n_3236_c;
import lightning.product.o_2576_A;
import lightning.product.ChunkBufferBuilderPack;
import lightning.product.u_4256_q;
import lightning.product.z_883_p;
import net.minecraftforge.client.extensions.IForgeRenderChunk;
import net.minecraftforge.client.model.ModelDataManager;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.client.model.data.IModelData;
import net.optifine.BlockPosM;
import net.optifine.Config;
import net.optifine.CustomBlockLayers;
import net.optifine.override.ChunkCacheOF;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;
import net.optifine.render.AabbFrame;
import net.optifine.render.ChunkLayerMap;
import net.optifine.render.ChunkLayerSet;
import net.optifine.render.ICamera;
import net.optifine.render.RenderEnv;
import net.optifine.render.RenderTypes;
import net.optifine.shaders.SVertexBuilder;
import net.optifine.shaders.Shaders;
import net.optifine.util.ChunkUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class z_4547_I {
    private static final Logger R_4764_Y = LogManager.getLogger();
    private final PriorityQueue<n_1700_B.n_1700_B> G_564_y = Queues.newPriorityQueue();
    private final Queue<ChunkBufferBuilderPack> P_1922_E;
    private final Queue<Runnable> u_1723_Y = Queues.newConcurrentLinkedQueue();
    private volatile int v_4262_N;
    private volatile int w_1484_f;
    private final ChunkBufferBuilderPack t_148_a;
    private final U_3758_m<Runnable> s_956_w;
    private final Executor u_2550_I;
    private b_4507_u M_588_G;
    private final z_883_p P_4830_p;
    private e_2866_D h_1847_R = e_2866_D.n_1700_B;
    private int Q_4569_t;
    private List<ChunkBufferBuilderPack> M_182_A = new ArrayList<ChunkBufferBuilderPack>();
    public static final o_2576_A[] n_1700_B = o_2576_A.k_2293_S().toArray(new o_2576_A[0]);
    private static final boolean t_1786_h = Reflector.ForgeHooksClient.exists();
    private static final boolean multiplayerClientSuggestionProvider = Reflector.ForgeRenderTypeLookup_canRenderInLayerBs.exists();
    private static final boolean w_1457_N = Reflector.ForgeRenderTypeLookup_canRenderInLayerBs.exists();
    private static final boolean Y_601_j = Reflector.ForgeHooksClient_setRenderLayer.exists();
    public static int J_1907_R;

    public z_4547_I(b_4507_u worldIn, z_883_p worldRendererIn, Executor executorIn, boolean java64bit, ChunkBufferBuilderPack fixedBuilderIn) {
        this(worldIn, worldRendererIn, executorIn, java64bit, fixedBuilderIn, -1);
    }

    public z_4547_I(b_4507_u p_i242112_1_, z_883_p p_i242112_2_, Executor p_i242112_3_, boolean p_i242112_4_, ChunkBufferBuilderPack p_i242112_5_, int p_i242112_6_) {
        this.M_588_G = p_i242112_1_;
        this.P_4830_p = p_i242112_2_;
        int i = Math.max(1, (int)((double)Runtime.getRuntime().maxMemory() * 0.3) / (o_2576_A.k_2293_S().stream().mapToInt(o_2576_A::q_2307_F).sum() * 4) - 1);
        int j = Runtime.getRuntime().availableProcessors();
        int k = p_i242112_4_ ? j : Math.min(j, 4);
        int l = Math.max(1, Math.min(k, i));
        if (p_i242112_6_ > 0) {
            l = p_i242112_6_;
        }
        this.t_148_a = p_i242112_5_;
        ArrayList list = Lists.newArrayListWithExpectedSize((int)l);
        try {
            for (int i1 = 0; i1 < l; ++i1) {
                list.add(new ChunkBufferBuilderPack());
            }
        }
        catch (OutOfMemoryError outofmemoryerror1) {
            R_4764_Y.warn("Allocated only {}/{} buffers", (Object)list.size(), (Object)l);
            int j1 = Math.min(list.size() * 2 / 3, list.size() - 1);
            for (int k1 = 0; k1 < j1; ++k1) {
                list.remove(list.size() - 1);
            }
            System.gc();
        }
        this.P_1922_E = Queues.newConcurrentLinkedQueue((Iterable)list);
        this.Q_4569_t = this.w_1484_f = this.P_1922_E.size();
        this.u_2550_I = p_i242112_3_;
        this.s_956_w = U_3758_m.n_1700_B(p_i242112_3_, "Chunk Renderer");
        this.s_956_w.n_1700_B(this::t_148_a);
    }

    public void n_1700_B(b_4507_u worldIn) {
        this.M_588_G = worldIn;
    }

    private void t_148_a() {
        n_1700_B.n_1700_B chunkrenderdispatcher$chunkrender$chunkrendertask;
        if (!this.P_1922_E.isEmpty() && (chunkrenderdispatcher$chunkrender$chunkrendertask = this.G_564_y.poll()) != null) {
            ChunkBufferBuilderPack regionrendercachebuilder = this.P_1922_E.poll();
            if (regionrendercachebuilder == null) {
                this.G_564_y.add(chunkrenderdispatcher$chunkrender$chunkrendertask);
                return;
            }
            this.v_4262_N = this.G_564_y.size();
            this.w_1484_f = this.P_1922_E.size();
            ((CompletableFuture)CompletableFuture.runAsync(() -> {}, this.u_2550_I).thenCompose(p_lambda$runTask$1_2_ -> chunkrenderdispatcher$chunkrender$chunkrendertask.n_1700_B(regionrendercachebuilder))).whenComplete((p_lambda$runTask$3_2_, p_lambda$runTask$3_3_) -> {
                if (p_lambda$runTask$3_3_ != null) {
                    n_3236_c crashreport = n_3236_c.n_1700_B(p_lambda$runTask$3_3_, "Batching chunks");
                    MinecraftClient.A_4115_X().n_1700_B(MinecraftClient.A_4115_X().R_4764_Y(crashreport));
                } else {
                    this.s_956_w.n_1700_B(() -> {
                        if (p_lambda$runTask$3_2_ == lightning.product.z_4547_I$J_1907_R.n_1700_B) {
                            regionrendercachebuilder.n_1700_B();
                        } else {
                            regionrendercachebuilder.J_1907_R();
                        }
                        this.P_1922_E.add(regionrendercachebuilder);
                        this.w_1484_f = this.P_1922_E.size();
                        this.t_148_a();
                    });
                }
            });
        }
    }

    public String n_1700_B() {
        return String.format("pC: %03d, pU: %02d, aB: %02d", this.v_4262_N, this.u_1723_Y.size(), this.w_1484_f);
    }

    public void n_1700_B(e_2866_D posIn) {
        this.h_1847_R = posIn;
    }

    public e_2866_D J_1907_R() {
        return this.h_1847_R;
    }

    public boolean R_4764_Y() {
        Runnable runnable;
        boolean flag = false;
        while ((runnable = this.u_1723_Y.poll()) != null) {
            runnable.run();
            flag = true;
        }
        return flag;
    }

    public void n_1700_B(n_1700_B chunkRenderIn) {
        chunkRenderIn.u_2550_I();
    }

    public void G_564_y() {
        this.s_956_w();
    }

    public void n_1700_B(n_1700_B.n_1700_B renderTaskIn) {
        this.s_956_w.n_1700_B(() -> {
            this.G_564_y.offer(renderTaskIn);
            this.v_4262_N = this.G_564_y.size();
            this.t_148_a();
        });
    }

    public CompletableFuture<Void> n_1700_B(D_3318_r bufferBuilderIn, D_4883_k vertexBufferIn) {
        return CompletableFuture.runAsync(() -> {}, this.u_1723_Y::add).thenCompose(p_lambda$uploadChunkLayer$6_3_ -> this.J_1907_R(bufferBuilderIn, vertexBufferIn));
    }

    private CompletableFuture<Void> J_1907_R(D_3318_r bufferBuilderIn, D_4883_k vertexBufferIn) {
        return vertexBufferIn.J_1907_R(bufferBuilderIn);
    }

    private void s_956_w() {
        while (!this.G_564_y.isEmpty()) {
            n_1700_B.n_1700_B chunkrenderdispatcher$chunkrender$chunkrendertask = this.G_564_y.poll();
            if (chunkrenderdispatcher$chunkrender$chunkrendertask == null) continue;
            chunkrenderdispatcher$chunkrender$chunkrendertask.n_1700_B();
        }
        this.v_4262_N = 0;
    }

    public boolean P_1922_E() {
        return this.v_4262_N == 0 && this.u_1723_Y.isEmpty();
    }

    public void u_1723_Y() {
        this.s_956_w();
        this.s_956_w.close();
        this.P_1922_E.clear();
    }

    public void v_4262_N() {
        long i = System.currentTimeMillis();
        if (this.M_182_A.size() <= 0) {
            while (this.M_182_A.size() != this.Q_4569_t) {
                this.R_4764_Y();
                ChunkBufferBuilderPack regionrendercachebuilder = this.P_1922_E.poll();
                if (regionrendercachebuilder != null) {
                    this.M_182_A.add(regionrendercachebuilder);
                }
                if (System.currentTimeMillis() <= i + 1000L) continue;
                break;
            }
        }
    }

    public void w_1484_f() {
        this.P_1922_E.addAll(this.M_182_A);
        this.M_182_A.clear();
    }

    public boolean J_1907_R(n_1700_B p_updateChunkNow_1_) {
        this.n_1700_B(p_updateChunkNow_1_);
        return true;
    }

    public boolean R_4764_Y(n_1700_B p_updateChunkLater_1_) {
        if (this.P_1922_E.isEmpty()) {
            return false;
        }
        p_updateChunkLater_1_.n_1700_B(this);
        return true;
    }

    public boolean G_564_y(n_1700_B p_updateTransparencyLater_1_) {
        return this.P_1922_E.isEmpty() ? false : p_updateTransparencyLater_1_.n_1700_B(RenderTypes.TRANSLUCENT, this);
    }

    public class lightning.product.z_4547_I$n_1700_B
    implements IForgeRenderChunk {
        public final AtomicReference<lightning.product.z_4547_I$R_4764_Y> n_1700_B = new AtomicReference<lightning.product.z_4547_I$R_4764_Y>(lightning.product.z_4547_I$R_4764_Y.n_1700_B);
        @Nullable
        private J_1907_R v_4262_N;
        @Nullable
        private R_4764_Y w_1484_f;
        private final Set<i_2154_H> t_148_a = Sets.newHashSet();
        private final ChunkLayerMap<D_4883_k> s_956_w = new ChunkLayerMap<D_4883_k>(p_lambda$new$0_0_ -> new D_4883_k(E_688_b.w_1484_f));
        public I_4817_s J_1907_R;
        private int u_2550_I = -1;
        private boolean M_588_G = true;
        private final c_1514_x.n_1700_B P_4830_p = new c_1514_x.n_1700_B(-1, -1, -1);
        private final c_1514_x.n_1700_B[] h_1847_R = j_3341_s.n_1700_B(new c_1514_x.n_1700_B[6], p_lambda$new$1_0_ -> {
            for (int i = 0; i < ((c_1514_x.n_1700_B[])p_lambda$new$1_0_).length; ++i) {
                p_lambda$new$1_0_[i] = new c_1514_x.n_1700_B();
            }
        });
        private boolean Q_4569_t;
        private final boolean M_182_A = Config.isMipmaps();
        private final boolean t_1786_h = !Reflector.BetterFoliageClient.exists();
        private boolean multiplayerClientSuggestionProvider = false;
        private boolean w_1457_N = Config.isRenderRegions();
        public int R_4764_Y;
        public int G_564_y;
        private int Y_601_j;
        private int Y_259_p;
        private int Q_2552_b;
        private final lightning.product.z_4547_I$n_1700_B[] C_2741_M = new lightning.product.z_4547_I$n_1700_B[6];
        private boolean k_2293_S = false;
        private H_1748_a q_2307_F;
        private lightning.product.z_4547_I$n_1700_B[] Z_875_P = new lightning.product.z_4547_I$n_1700_B[b_257_Y.v_4262_N.length];
        private lightning.product.z_4547_I$n_1700_B[] c_3005_b = new lightning.product.z_4547_I$n_1700_B[b_257_Y.v_4262_N.length];
        private boolean H_2857_Y = false;
        private z_883_p.n_1700_B A_4115_X = new z_883_p.n_1700_B(this, null, 0);
        public AabbFrame P_1922_E;

        private boolean n_1700_B(c_1514_x blockPosIn) {
            return z_4547_I.this.M_588_G.n_1700_B(blockPosIn.getX() >> 4, blockPosIn.getZ() >> 4, ChunkStatus.P_4830_p, false) != null;
        }

        public boolean n_1700_B() {
            int i = 24;
            if (!(this.J_1907_R() > 576.0)) {
                return true;
            }
            return this.n_1700_B(this.h_1847_R[b_257_Y.P_1922_E.ordinal()]) && this.n_1700_B(this.h_1847_R[b_257_Y.R_4764_Y.ordinal()]) && this.n_1700_B(this.h_1847_R[b_257_Y.u_1723_Y.ordinal()]) && this.n_1700_B(this.h_1847_R[b_257_Y.G_564_y.ordinal()]);
        }

        public boolean n_1700_B(int frameIndexIn) {
            if (this.u_2550_I == frameIndexIn) {
                return false;
            }
            this.u_2550_I = frameIndexIn;
            return true;
        }

        public D_4883_k n_1700_B(o_2576_A renderTypeIn) {
            return this.s_956_w.get(renderTypeIn);
        }

        public void n_1700_B(int x, int y, int z) {
            if (x != this.P_4830_p.getX() || y != this.P_4830_p.getY() || z != this.P_4830_p.getZ()) {
                this.t_1786_h();
                this.P_4830_p.n_1700_B(x, y, z);
                if (this.w_1457_N) {
                    int i = 8;
                    this.R_4764_Y = x >> i << i;
                    this.G_564_y = z >> i << i;
                    this.Y_601_j = x - this.R_4764_Y;
                    this.Y_259_p = y;
                    this.Q_2552_b = z - this.G_564_y;
                }
                this.J_1907_R = new I_4817_s(x, y, z, x + 16, y + 16, z + 16);
                lightning.product.A_4115_X.n_1700_B(new Z_2812_M(this));
                for (b_257_Y direction : b_257_Y.v_4262_N) {
                    this.h_1847_R[direction.ordinal()].n_1700_B(this.P_4830_p).n_1700_B(direction, 16);
                }
                this.k_2293_S = false;
                this.H_2857_Y = false;
                for (int j = 0; j < this.Z_875_P.length; ++j) {
                    lightning.product.z_4547_I$n_1700_B chunkrenderdispatcher$chunkrender = this.Z_875_P[j];
                    if (chunkrenderdispatcher$chunkrender == null) continue;
                    chunkrenderdispatcher$chunkrender.H_2857_Y = false;
                }
                this.q_2307_F = null;
                this.P_1922_E = null;
            }
        }

        protected double J_1907_R() {
            h_3572_K activerenderinfo = MinecraftClient.A_4115_X().s_956_w.M_588_G();
            double d0 = this.J_1907_R.minX + 8.0 - activerenderinfo.J_1907_R().J_1907_R;
            double d1 = this.J_1907_R.minY + 8.0 - activerenderinfo.J_1907_R().R_4764_Y;
            double d2 = this.J_1907_R.minZ + 8.0 - activerenderinfo.J_1907_R().G_564_y;
            return d0 * d0 + d1 * d1 + d2 * d2;
        }

        private void n_1700_B(D_3318_r bufferBuilderIn) {
            bufferBuilderIn.n_1700_B(7, E_688_b.w_1484_f);
        }

        public lightning.product.z_4547_I$R_4764_Y R_4764_Y() {
            return this.n_1700_B.get();
        }

        private void t_1786_h() {
            this.t_148_a();
            this.n_1700_B.set(lightning.product.z_4547_I$R_4764_Y.n_1700_B);
            this.M_588_G = true;
        }

        public void G_564_y() {
            this.t_1786_h();
            this.s_956_w.values().forEach(D_4883_k::close);
        }

        public c_1514_x P_1922_E() {
            return this.P_4830_p;
        }

        public void n_1700_B(boolean immediate) {
            boolean flag = this.M_588_G;
            this.M_588_G = true;
            this.Q_4569_t = immediate | (flag && this.Q_4569_t);
            if (this.multiplayerClientSuggestionProvider()) {
                this.multiplayerClientSuggestionProvider = true;
            }
        }

        public void u_1723_Y() {
            this.M_588_G = false;
            this.Q_4569_t = false;
            this.multiplayerClientSuggestionProvider = false;
        }

        public boolean v_4262_N() {
            return this.M_588_G;
        }

        public boolean w_1484_f() {
            return this.M_588_G && this.Q_4569_t;
        }

        public c_1514_x n_1700_B(b_257_Y facing) {
            return this.h_1847_R[facing.ordinal()];
        }

        public boolean n_1700_B(o_2576_A renderTypeIn, z_4547_I renderDispatcherIn) {
            lightning.product.z_4547_I$R_4764_Y chunkrenderdispatcher$compiledchunk = this.R_4764_Y();
            if (this.w_1484_f != null) {
                this.w_1484_f.n_1700_B();
            }
            if (!chunkrenderdispatcher$compiledchunk.R_4764_Y.contains(renderTypeIn)) {
                return false;
            }
            this.w_1484_f = t_1786_h ? new R_4764_Y(new Y_1387_d(this.P_1922_E()), this.J_1907_R(), chunkrenderdispatcher$compiledchunk) : new R_4764_Y(this.J_1907_R(), chunkrenderdispatcher$compiledchunk);
            renderDispatcherIn.n_1700_B(this.w_1484_f);
            return true;
        }

        protected void t_148_a() {
            if (this.v_4262_N != null) {
                this.v_4262_N.n_1700_B();
                this.v_4262_N = null;
            }
            if (this.w_1484_f != null) {
                this.w_1484_f.n_1700_B();
                this.w_1484_f = null;
            }
        }

        public n_1700_B s_956_w() {
            this.t_148_a();
            c_1514_x blockpos = this.P_4830_p.toImmutable();
            boolean i = true;
            Y_3830_x chunkrendercache = null;
            this.v_4262_N = t_1786_h ? new J_1907_R(new Y_1387_d(this.P_1922_E()), this.J_1907_R(), chunkrendercache) : new J_1907_R(this.J_1907_R(), chunkrendercache);
            return this.v_4262_N;
        }

        public void n_1700_B(z_4547_I dispatcherIn) {
            n_1700_B chunkrenderdispatcher$chunkrender$chunkrendertask = this.s_956_w();
            dispatcherIn.n_1700_B(chunkrenderdispatcher$chunkrender$chunkrendertask);
        }

        private void n_1700_B(Set<i_2154_H> globalEntitiesIn) {
            HashSet set = Sets.newHashSet(globalEntitiesIn);
            HashSet set1 = Sets.newHashSet(this.t_148_a);
            set.removeAll(this.t_148_a);
            set1.removeAll(globalEntitiesIn);
            this.t_148_a.clear();
            this.t_148_a.addAll(globalEntitiesIn);
            z_4547_I.this.P_4830_p.n_1700_B(set1, set);
        }

        public void u_2550_I() {
            n_1700_B chunkrenderdispatcher$chunkrender$chunkrendertask = this.s_956_w();
            chunkrenderdispatcher$chunkrender$chunkrendertask.n_1700_B(z_4547_I.this.t_148_a);
        }

        private boolean multiplayerClientSuggestionProvider() {
            if (z_4547_I.this.M_588_G instanceof k_4690_i) {
                k_4690_i clientworld = (k_4690_i)z_4547_I.this.M_588_G;
                return clientworld.t_148_a();
            }
            return false;
        }

        public boolean M_588_G() {
            return this.multiplayerClientSuggestionProvider;
        }

        private o_2576_A[] n_1700_B(FluidState p_getFluidRenderLayers_1_, o_2576_A[] p_getFluidRenderLayers_2_) {
            if (w_1457_N) {
                return n_1700_B;
            }
            p_getFluidRenderLayers_2_[0] = d_1620_j.n_1700_B(p_getFluidRenderLayers_1_);
            return p_getFluidRenderLayers_2_;
        }

        private o_2576_A[] n_1700_B(K_4074_S p_getBlockRenderLayers_1_, o_2576_A[] p_getBlockRenderLayers_2_) {
            if (multiplayerClientSuggestionProvider) {
                return n_1700_B;
            }
            p_getBlockRenderLayers_2_[0] = d_1620_j.n_1700_B(p_getBlockRenderLayers_1_);
            return p_getBlockRenderLayers_2_;
        }

        private o_2576_A n_1700_B(BlockGetter p_fixBlockLayer_1_, K_4074_S p_fixBlockLayer_2_, c_1514_x p_fixBlockLayer_3_, o_2576_A p_fixBlockLayer_4_) {
            o_2576_A rendertype;
            if (CustomBlockLayers.isActive() && (rendertype = CustomBlockLayers.getRenderLayer(p_fixBlockLayer_1_, p_fixBlockLayer_2_, p_fixBlockLayer_3_)) != null) {
                return rendertype;
            }
            if (!this.t_1786_h) {
                return p_fixBlockLayer_4_;
            }
            if (this.M_182_A) {
                if (p_fixBlockLayer_4_ == RenderTypes.CUTOUT) {
                    T_2915_h block = p_fixBlockLayer_2_.J_1907_R();
                    if (block instanceof Z_4734_t) {
                        return p_fixBlockLayer_4_;
                    }
                    if (block instanceof CactusBlock) {
                        return p_fixBlockLayer_4_;
                    }
                    return RenderTypes.CUTOUT_MIPPED;
                }
            } else if (p_fixBlockLayer_4_ == RenderTypes.CUTOUT_MIPPED) {
                return RenderTypes.CUTOUT;
            }
            return p_fixBlockLayer_4_;
        }

        private void n_1700_B(ChunkBufferBuilderPack p_postRenderOverlays_1_, lightning.product.z_4547_I$R_4764_Y p_postRenderOverlays_2_) {
            this.n_1700_B(RenderTypes.CUTOUT, p_postRenderOverlays_1_, p_postRenderOverlays_2_);
            this.n_1700_B(RenderTypes.CUTOUT_MIPPED, p_postRenderOverlays_1_, p_postRenderOverlays_2_);
            this.n_1700_B(RenderTypes.TRANSLUCENT, p_postRenderOverlays_1_, p_postRenderOverlays_2_);
        }

        private void n_1700_B(o_2576_A p_postRenderOverlay_1_, ChunkBufferBuilderPack p_postRenderOverlay_2_, lightning.product.z_4547_I$R_4764_Y p_postRenderOverlay_3_) {
            D_3318_r bufferbuilder = p_postRenderOverlay_2_.n_1700_B(p_postRenderOverlay_1_);
            if (bufferbuilder.s_956_w()) {
                p_postRenderOverlay_3_.G_564_y(p_postRenderOverlay_1_);
                if (bufferbuilder.M_588_G() > 0) {
                    p_postRenderOverlay_3_.P_1922_E(p_postRenderOverlay_1_);
                }
            }
        }

        private ChunkCacheOF J_1907_R(c_1514_x p_makeChunkCacheOF_1_) {
            c_1514_x blockpos = p_makeChunkCacheOF_1_.add(-1, -1, -1);
            c_1514_x blockpos1 = p_makeChunkCacheOF_1_.add(16, 16, 16);
            Y_3830_x chunkrendercache = this.createRegionRenderCache(z_4547_I.this.M_588_G, blockpos, blockpos1, 1);
            return new ChunkCacheOF(chunkrendercache, blockpos, blockpos1, 1);
        }

        @Override
        public Y_3830_x createRegionRenderCache(b_4507_u p_createRegionRenderCache_1_, c_1514_x p_createRegionRenderCache_2_, c_1514_x p_createRegionRenderCache_3_, int p_createRegionRenderCache_4_) {
            return Y_3830_x.n_1700_B(p_createRegionRenderCache_1_, p_createRegionRenderCache_2_, p_createRegionRenderCache_3_, p_createRegionRenderCache_4_, false);
        }

        public lightning.product.z_4547_I$n_1700_B n_1700_B(B_1647_r p_getRenderChunkOffset16_1_, b_257_Y p_getRenderChunkOffset16_2_) {
            if (!this.k_2293_S) {
                for (int i = 0; i < b_257_Y.v_4262_N.length; ++i) {
                    b_257_Y direction = b_257_Y.v_4262_N[i];
                    c_1514_x blockpos = this.n_1700_B(direction);
                    this.C_2741_M[i] = p_getRenderChunkOffset16_1_.n_1700_B(blockpos);
                }
                this.k_2293_S = true;
            }
            return this.C_2741_M[p_getRenderChunkOffset16_2_.ordinal()];
        }

        public H_1748_a P_4830_p() {
            return this.R_4764_Y(this.P_4830_p);
        }

        private H_1748_a R_4764_Y(c_1514_x p_getChunk_1_) {
            H_1748_a chunk = this.q_2307_F;
            if (chunk != null && ChunkUtils.isLoaded(chunk)) {
                return chunk;
            }
            this.q_2307_F = chunk = z_4547_I.this.M_588_G.M_182_A(p_getChunk_1_);
            return chunk;
        }

        public boolean h_1847_R() {
            return this.G_564_y(this.P_4830_p);
        }

        private boolean G_564_y(c_1514_x p_isChunkRegionEmpty_1_) {
            int i = p_isChunkRegionEmpty_1_.getY();
            int j = i + 15;
            return this.R_4764_Y(p_isChunkRegionEmpty_1_).n_1700_B(i, j);
        }

        public void n_1700_B(b_257_Y p_setRenderChunkNeighbour_1_, lightning.product.z_4547_I$n_1700_B p_setRenderChunkNeighbour_2_) {
            this.Z_875_P[p_setRenderChunkNeighbour_1_.ordinal()] = p_setRenderChunkNeighbour_2_;
            this.c_3005_b[p_setRenderChunkNeighbour_1_.ordinal()] = p_setRenderChunkNeighbour_2_;
        }

        public lightning.product.z_4547_I$n_1700_B J_1907_R(b_257_Y p_getRenderChunkNeighbour_1_) {
            if (!this.H_2857_Y) {
                this.w_1457_N();
            }
            return this.c_3005_b[p_getRenderChunkNeighbour_1_.ordinal()];
        }

        public z_883_p.n_1700_B Q_4569_t() {
            return this.A_4115_X;
        }

        private void w_1457_N() {
            int i = this.P_1922_E().getX();
            int j = this.P_1922_E().getZ();
            int k = b_257_Y.R_4764_Y.ordinal();
            int l = b_257_Y.G_564_y.ordinal();
            int i1 = b_257_Y.P_1922_E.ordinal();
            int j1 = b_257_Y.u_1723_Y.ordinal();
            this.c_3005_b[k] = this.Z_875_P[k].P_1922_E().getZ() == j - 16 ? this.Z_875_P[k] : null;
            this.c_3005_b[l] = this.Z_875_P[l].P_1922_E().getZ() == j + 16 ? this.Z_875_P[l] : null;
            this.c_3005_b[i1] = this.Z_875_P[i1].P_1922_E().getX() == i - 16 ? this.Z_875_P[i1] : null;
            this.c_3005_b[j1] = this.Z_875_P[j1].P_1922_E().getX() == i + 16 ? this.Z_875_P[j1] : null;
            this.H_2857_Y = true;
        }

        public boolean n_1700_B(ICamera p_isBoundingBoxInFrustum_1_, int p_isBoundingBoxInFrustum_2_) {
            return this.M_182_A().isBoundingBoxInFrustumFully(p_isBoundingBoxInFrustum_1_, p_isBoundingBoxInFrustum_2_) ? true : p_isBoundingBoxInFrustum_1_.isBoundingBoxInFrustum(this.J_1907_R);
        }

        public AabbFrame M_182_A() {
            if (this.P_1922_E == null) {
                AabbFrame aabbframe;
                c_1514_x blockpos = this.P_1922_E();
                int i = blockpos.getX();
                int j = blockpos.getY();
                int k = blockpos.getZ();
                int l = 5;
                int i1 = i >> l << l;
                int j1 = j >> l << l;
                int k1 = k >> l << l;
                if ((i1 != i || j1 != j || k1 != k) && (aabbframe = z_4547_I.this.P_4830_p.n_1700_B(new c_1514_x(i1, j1, k1)).M_182_A()) != null && aabbframe.minX == (double)i1 && aabbframe.minY == (double)j1 && aabbframe.minZ == (double)k1) {
                    this.P_1922_E = aabbframe;
                }
                if (this.P_1922_E == null) {
                    int l1 = 1 << l;
                    this.P_1922_E = new AabbFrame(i1, j1, k1, i1 + l1, j1 + l1, k1 + l1);
                }
            }
            return this.P_1922_E;
        }

        public String toString() {
            return "pos: " + String.valueOf(this.P_1922_E()) + ", frameIndex: " + this.u_2550_I;
        }

        class R_4764_Y
        extends n_1700_B {
            private final lightning.product.z_4547_I$R_4764_Y P_1922_E;

            public R_4764_Y(double distanceSqIn, lightning.product.z_4547_I$R_4764_Y compiledChunkIn) {
                this(null, distanceSqIn, compiledChunkIn);
            }

            public R_4764_Y(Y_1387_d p_i242104_2_, double p_i242104_3_, lightning.product.z_4547_I$R_4764_Y p_i242104_5_) {
                super(n_1700_B.this, p_i242104_2_, p_i242104_3_);
                this.P_1922_E = p_i242104_5_;
            }

            @Override
            public CompletableFuture<lightning.product.z_4547_I$J_1907_R> n_1700_B(ChunkBufferBuilderPack builderIn) {
                if (this.J_1907_R.get()) {
                    return CompletableFuture.completedFuture(lightning.product.z_4547_I$J_1907_R.J_1907_R);
                }
                if (!n_1700_B.this.n_1700_B()) {
                    this.J_1907_R.set(true);
                    return CompletableFuture.completedFuture(lightning.product.z_4547_I$J_1907_R.J_1907_R);
                }
                if (this.J_1907_R.get()) {
                    return CompletableFuture.completedFuture(lightning.product.z_4547_I$J_1907_R.J_1907_R);
                }
                e_2866_D vector3d = z_4547_I.this.J_1907_R();
                float f = (float)vector3d.J_1907_R;
                float f1 = (float)vector3d.R_4764_Y;
                float f2 = (float)vector3d.G_564_y;
                D_3318_r.J_1907_R bufferbuilder$state = this.P_1922_E.v_4262_N;
                if (bufferbuilder$state != null && this.P_1922_E.J_1907_R.contains(o_2576_A.t_148_a())) {
                    D_3318_r bufferbuilder = builderIn.n_1700_B(o_2576_A.t_148_a());
                    bufferbuilder.n_1700_B(o_2576_A.t_148_a());
                    n_1700_B.this.n_1700_B(bufferbuilder);
                    bufferbuilder.n_1700_B(bufferbuilder$state);
                    bufferbuilder.n_1700_B((float)n_1700_B.this.Y_601_j + f - (float)n_1700_B.this.P_4830_p.getX(), (float)n_1700_B.this.Y_259_p + f1 - (float)n_1700_B.this.P_4830_p.getY(), (float)n_1700_B.this.Q_2552_b + f2 - (float)n_1700_B.this.P_4830_p.getZ());
                    this.P_1922_E.v_4262_N = bufferbuilder.P_1922_E();
                    bufferbuilder.u_1723_Y();
                    if (this.J_1907_R.get()) {
                        return CompletableFuture.completedFuture(lightning.product.z_4547_I$J_1907_R.J_1907_R);
                    }
                    CompletionStage completablefuture = z_4547_I.this.n_1700_B(builderIn.n_1700_B(o_2576_A.t_148_a()), n_1700_B.this.n_1700_B(o_2576_A.t_148_a())).thenApply(p_lambda$execute$0_0_ -> lightning.product.z_4547_I$J_1907_R.J_1907_R);
                    return ((CompletableFuture)completablefuture).handle((p_lambda$execute$1_1_, p_lambda$execute$1_2_) -> {
                        if (p_lambda$execute$1_2_ != null && !(p_lambda$execute$1_2_ instanceof CancellationException) && !(p_lambda$execute$1_2_ instanceof InterruptedException)) {
                            MinecraftClient.A_4115_X().n_1700_B(n_3236_c.n_1700_B(p_lambda$execute$1_2_, "Rendering chunk"));
                        }
                        return this.J_1907_R.get() ? lightning.product.z_4547_I$J_1907_R.J_1907_R : lightning.product.z_4547_I$J_1907_R.n_1700_B;
                    });
                }
                return CompletableFuture.completedFuture(lightning.product.z_4547_I$J_1907_R.J_1907_R);
            }

            @Override
            public void n_1700_B() {
                this.J_1907_R.set(true);
            }
        }

        abstract class n_1700_B
        implements Comparable<n_1700_B> {
            protected final double n_1700_B;
            protected final AtomicBoolean J_1907_R = new AtomicBoolean(false);
            protected Map<c_1514_x, IModelData> R_4764_Y;

            public n_1700_B(lightning.product.z_4547_I$n_1700_B this$1, double distanceSqIn) {
                this(this$1, null, distanceSqIn);
            }

            public n_1700_B(lightning.product.z_4547_I$n_1700_B this$1, Y_1387_d p_i242119_2_, double p_i242119_3_) {
                this.n_1700_B = p_i242119_3_;
                this.R_4764_Y = p_i242119_2_ == null ? Collections.emptyMap() : ModelDataManager.getModelData(MinecraftClient.A_4115_X().Y_601_j, p_i242119_2_);
            }

            public abstract CompletableFuture<lightning.product.z_4547_I$J_1907_R> n_1700_B(ChunkBufferBuilderPack var1);

            public abstract void n_1700_B();

            public int n_1700_B(n_1700_B p_compareTo_1_) {
                return Doubles.compare((double)this.n_1700_B, (double)p_compareTo_1_.n_1700_B);
            }

            public IModelData n_1700_B(c_1514_x p_getModelData_1_) {
                return this.R_4764_Y.getOrDefault(p_getModelData_1_, EmptyModelData.INSTANCE);
            }

            @Override
            public /* synthetic */ int compareTo(Object object) {
                return this.n_1700_B((n_1700_B)object);
            }
        }

        class J_1907_R
        extends n_1700_B {
            @Nullable
            protected Y_3830_x G_564_y;

            public J_1907_R(double distanceSqIn, Y_3830_x renderCacheIn) {
                this(null, distanceSqIn, renderCacheIn);
            }

            public J_1907_R(@Nullable Y_1387_d p_i242111_2_, double p_i242111_3_, Y_3830_x p_i242111_5_) {
                super(n_1700_B.this, p_i242111_2_, p_i242111_3_);
                this.G_564_y = p_i242111_5_;
            }

            @Override
            public CompletableFuture<lightning.product.z_4547_I$J_1907_R> n_1700_B(ChunkBufferBuilderPack builderIn) {
                if (this.J_1907_R.get()) {
                    return CompletableFuture.completedFuture(lightning.product.z_4547_I$J_1907_R.J_1907_R);
                }
                if (!n_1700_B.this.n_1700_B()) {
                    this.G_564_y = null;
                    n_1700_B.this.n_1700_B(false);
                    this.J_1907_R.set(true);
                    return CompletableFuture.completedFuture(lightning.product.z_4547_I$J_1907_R.J_1907_R);
                }
                if (this.J_1907_R.get()) {
                    return CompletableFuture.completedFuture(lightning.product.z_4547_I$J_1907_R.J_1907_R);
                }
                e_2866_D vector3d = z_4547_I.this.J_1907_R();
                float f = (float)vector3d.J_1907_R;
                float f1 = (float)vector3d.R_4764_Y;
                float f2 = (float)vector3d.G_564_y;
                lightning.product.z_4547_I$R_4764_Y chunkrenderdispatcher$compiledchunk = new lightning.product.z_4547_I$R_4764_Y();
                Set<i_2154_H> set = this.n_1700_B(f, f1, f2, chunkrenderdispatcher$compiledchunk, builderIn);
                n_1700_B.this.n_1700_B(set);
                if (this.J_1907_R.get()) {
                    return CompletableFuture.completedFuture(lightning.product.z_4547_I$J_1907_R.J_1907_R);
                }
                ArrayList list = Lists.newArrayList();
                chunkrenderdispatcher$compiledchunk.R_4764_Y.forEach(p_lambda$execute$0_3_ -> list.add(z_4547_I.this.n_1700_B(builderIn.n_1700_B((o_2576_A)p_lambda$execute$0_3_), n_1700_B.this.n_1700_B((o_2576_A)p_lambda$execute$0_3_))));
                return j_3341_s.J_1907_R(list).handle((p_lambda$execute$1_2_, p_lambda$execute$1_3_) -> {
                    if (p_lambda$execute$1_3_ != null && !(p_lambda$execute$1_3_ instanceof CancellationException) && !(p_lambda$execute$1_3_ instanceof InterruptedException)) {
                        MinecraftClient.A_4115_X().n_1700_B(n_3236_c.n_1700_B(p_lambda$execute$1_3_, "Rendering chunk"));
                    }
                    if (this.J_1907_R.get()) {
                        return lightning.product.z_4547_I$J_1907_R.J_1907_R;
                    }
                    n_1700_B.this.n_1700_B.set(chunkrenderdispatcher$compiledchunk);
                    return lightning.product.z_4547_I$J_1907_R.n_1700_B;
                });
            }

            private Set<i_2154_H> n_1700_B(float xIn, float yIn, float zIn, lightning.product.z_4547_I$R_4764_Y compiledChunkIn, ChunkBufferBuilderPack builderIn) {
                boolean i = true;
                c_1514_x blockpos = n_1700_B.this.P_4830_p.toImmutable();
                c_1514_x blockpos1 = blockpos.add(15, 15, 15);
                VisGraph visgraph = new VisGraph();
                HashSet set = Sets.newHashSet();
                this.G_564_y = null;
                g_221_o matrixstack = new g_221_o();
                if (!n_1700_B.this.G_564_y(blockpos)) {
                    ++z_4547_I.J_1907_R;
                    ChunkCacheOF chunkcacheof = n_1700_B.this.J_1907_R(blockpos);
                    chunkcacheof.renderStart();
                    o_2576_A[] arendertype = new o_2576_A[1];
                    boolean flag = Config.isShaders();
                    boolean flag1 = flag && Shaders.useMidBlockAttrib;
                    W_571_B.n_1700_B();
                    Random random = new Random();
                    e_3977_C blockrendererdispatcher = MinecraftClient.A_4115_X().z_1333_t();
                    for (BlockPosM blockposm : BlockPosM.getAllInBoxMutable(blockpos, blockpos1)) {
                        IModelData imodeldata;
                        i_2154_H tileentity;
                        K_4074_S blockstate = chunkcacheof.getBlockState(blockposm);
                        if (blockstate.v_4262_N()) continue;
                        T_2915_h block = blockstate.J_1907_R();
                        if (blockstate.t_148_a(chunkcacheof, blockposm)) {
                            visgraph.n_1700_B(blockposm);
                        }
                        if (ReflectorForge.blockHasTileEntity(blockstate) && (tileentity = chunkcacheof.getTileEntity(blockposm, H_1748_a.n_1700_B.R_4764_Y)) != null) {
                            this.n_1700_B(compiledChunkIn, set, tileentity);
                        }
                        FluidState fluidstate = blockstate.P_4830_p();
                        IModelData iModelData = imodeldata = z_4547_I.t_1786_h ? this.n_1700_B(blockposm) : null;
                        if (!fluidstate.R_4764_Y()) {
                            o_2576_A[] arendertype1 = n_1700_B.this.n_1700_B(fluidstate, arendertype);
                            for (int j = 0; j < arendertype1.length; ++j) {
                                o_2576_A rendertype = arendertype1[j];
                                if (z_4547_I.w_1457_N && !Reflector.callBoolean(Reflector.ForgeRenderTypeLookup_canRenderInLayerFs, fluidstate, rendertype)) continue;
                                if (z_4547_I.Y_601_j) {
                                    Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, rendertype);
                                }
                                D_3318_r bufferbuilder = builderIn.n_1700_B(rendertype);
                                bufferbuilder.n_1700_B(rendertype);
                                RenderEnv renderenv = bufferbuilder.n_1700_B(blockstate, blockposm);
                                renderenv.setRegionRenderCacheBuilder(builderIn);
                                chunkcacheof.setRenderEnv(renderenv);
                                if (compiledChunkIn.R_4764_Y.add(rendertype)) {
                                    n_1700_B.this.n_1700_B(bufferbuilder);
                                }
                                if (!blockrendererdispatcher.n_1700_B(blockposm, chunkcacheof, bufferbuilder, fluidstate)) continue;
                                compiledChunkIn.G_564_y = false;
                                compiledChunkIn.J_1907_R.add(rendertype);
                            }
                        }
                        if (blockstate.w_1484_f() != O_2369_F.n_1700_B) {
                            o_2576_A[] arendertype2 = n_1700_B.this.n_1700_B(blockstate, arendertype);
                            for (int k = 0; k < arendertype2.length; ++k) {
                                o_2576_A rendertype3 = arendertype2[k];
                                if (z_4547_I.multiplayerClientSuggestionProvider && !Reflector.callBoolean(Reflector.ForgeRenderTypeLookup_canRenderInLayerBs, blockstate, rendertype3)) continue;
                                if (z_4547_I.Y_601_j) {
                                    Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, rendertype3);
                                }
                                rendertype3 = n_1700_B.this.n_1700_B(chunkcacheof, blockstate, blockposm, rendertype3);
                                D_3318_r bufferbuilder3 = builderIn.n_1700_B(rendertype3);
                                bufferbuilder3.n_1700_B(rendertype3);
                                RenderEnv renderenv1 = bufferbuilder3.n_1700_B(blockstate, blockposm);
                                renderenv1.setRegionRenderCacheBuilder(builderIn);
                                chunkcacheof.setRenderEnv(renderenv1);
                                if (compiledChunkIn.R_4764_Y.add(rendertype3)) {
                                    n_1700_B.this.n_1700_B(bufferbuilder3);
                                }
                                matrixstack.n_1700_B();
                                matrixstack.n_1700_B((double)n_1700_B.this.Y_601_j + (double)(blockposm.getX() & 0xF), (double)n_1700_B.this.Y_259_p + (double)(blockposm.getY() & 0xF), (double)n_1700_B.this.Q_2552_b + (double)(blockposm.getZ() & 0xF));
                                if (flag1) {
                                    bufferbuilder3.setMidBlock(0.5f + (float)n_1700_B.this.Y_601_j + (float)(blockposm.getX() & 0xF), 0.5f + (float)n_1700_B.this.Y_259_p + (float)(blockposm.getY() & 0xF), 0.5f + (float)n_1700_B.this.Q_2552_b + (float)(blockposm.getZ() & 0xF));
                                }
                                if (blockrendererdispatcher.n_1700_B(blockstate, blockposm, chunkcacheof, matrixstack, bufferbuilder3, true, random, imodeldata)) {
                                    compiledChunkIn.G_564_y = false;
                                    compiledChunkIn.J_1907_R.add(rendertype3);
                                    if (renderenv1.isOverlaysRendered()) {
                                        n_1700_B.this.n_1700_B(builderIn, compiledChunkIn);
                                        renderenv1.setOverlaysRendered(false);
                                    }
                                }
                                matrixstack.J_1907_R();
                            }
                        }
                        if (!z_4547_I.Y_601_j) continue;
                        Reflector.callVoid(Reflector.ForgeHooksClient_setRenderLayer, new Object[]{null});
                    }
                    if (compiledChunkIn.J_1907_R.contains(o_2576_A.t_148_a())) {
                        D_3318_r bufferbuilder1 = builderIn.n_1700_B(o_2576_A.t_148_a());
                        bufferbuilder1.n_1700_B((float)n_1700_B.this.Y_601_j + xIn - (float)blockpos.getX(), (float)n_1700_B.this.Y_259_p + yIn - (float)blockpos.getY(), (float)n_1700_B.this.Q_2552_b + zIn - (float)blockpos.getZ());
                        compiledChunkIn.v_4262_N = bufferbuilder1.P_1922_E();
                    }
                    compiledChunkIn.R_4764_Y.stream().map(builderIn::n_1700_B).forEach(D_3318_r::u_1723_Y);
                    for (o_2576_A rendertype2 : z_4547_I.n_1700_B) {
                        compiledChunkIn.n_1700_B(rendertype2, (BitSet)null);
                    }
                    for (o_2576_A rendertype1 : compiledChunkIn.R_4764_Y) {
                        if (Config.isShaders()) {
                            SVertexBuilder.calcNormalChunkLayer(builderIn.n_1700_B(rendertype1));
                        }
                        D_3318_r bufferbuilder2 = builderIn.n_1700_B(rendertype1);
                        if (bufferbuilder2.u_2550_I == null || bufferbuilder2.u_2550_I.isEmpty()) continue;
                        compiledChunkIn.n_1700_B(rendertype1, (BitSet)bufferbuilder2.u_2550_I.clone());
                    }
                    chunkcacheof.renderFinish();
                    W_571_B.J_1907_R();
                }
                compiledChunkIn.u_1723_Y = visgraph.n_1700_B();
                return set;
            }

            private <E extends i_2154_H> void n_1700_B(lightning.product.z_4547_I$R_4764_Y compiledChunkIn, Set<i_2154_H> tileEntitiesIn, E tileEntityIn) {
                l_1802_R<E> tileentityrenderer = f_2689_h.J_1907_R.n_1700_B(tileEntityIn);
                if (tileentityrenderer != null) {
                    if (tileentityrenderer.n_1700_B(tileEntityIn)) {
                        tileEntitiesIn.add(tileEntityIn);
                    } else {
                        compiledChunkIn.P_1922_E.add(tileEntityIn);
                    }
                }
            }

            @Override
            public void n_1700_B() {
                this.G_564_y = null;
                if (this.J_1907_R.compareAndSet(false, true)) {
                    n_1700_B.this.n_1700_B(false);
                }
            }
        }
    }

    static final class J_1907_R
    extends Enum<J_1907_R> {
        public static final /* enum */ J_1907_R n_1700_B = new J_1907_R();
        public static final /* enum */ J_1907_R J_1907_R = new J_1907_R();
        private static final /* synthetic */ J_1907_R[] R_4764_Y;

        public static J_1907_R[] values() {
            return (J_1907_R[])R_4764_Y.clone();
        }

        public static J_1907_R valueOf(String name) {
            return Enum.valueOf(J_1907_R.class, name);
        }

        private static /* synthetic */ J_1907_R[] n_1700_B() {
            return new J_1907_R[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.z_4547_I$J_1907_R.n_1700_B();
        }
    }

    public static class R_4764_Y {
        public static final R_4764_Y n_1700_B = new R_4764_Y(){

            @Override
            public boolean n_1700_B(b_257_Y facing, b_257_Y facing2) {
                return false;
            }

            @Override
            public void n_1700_B(o_2576_A p_setAnimatedSprites_1_, BitSet p_setAnimatedSprites_2_) {
                throw new UnsupportedOperationException();
            }
        };
        private final ChunkLayerSet J_1907_R = new ChunkLayerSet();
        private final Set<o_2576_A> R_4764_Y = new ObjectArraySet();
        private boolean G_564_y = true;
        private final List<i_2154_H> P_1922_E = Lists.newArrayList();
        private u_4256_q u_1723_Y = new u_4256_q();
        @Nullable
        private D_3318_r.J_1907_R v_4262_N;
        private BitSet[] w_1484_f = new BitSet[o_2576_A.N_2525_X.length];

        public boolean n_1700_B() {
            return this.G_564_y;
        }

        public boolean n_1700_B(o_2576_A renderTypeIn) {
            return !this.J_1907_R.contains(renderTypeIn);
        }

        public List<i_2154_H> J_1907_R() {
            return this.P_1922_E;
        }

        public boolean n_1700_B(b_257_Y facing, b_257_Y facing2) {
            return this.u_1723_Y.n_1700_B(facing, facing2);
        }

        public BitSet J_1907_R(o_2576_A p_getAnimatedSprites_1_) {
            return this.w_1484_f[p_getAnimatedSprites_1_.G_564_y()];
        }

        public void n_1700_B(o_2576_A p_setAnimatedSprites_1_, BitSet p_setAnimatedSprites_2_) {
            this.w_1484_f[p_setAnimatedSprites_1_.G_564_y()] = p_setAnimatedSprites_2_;
        }

        public boolean R_4764_Y(o_2576_A p_isLayerStarted_1_) {
            return this.R_4764_Y.contains(p_isLayerStarted_1_);
        }

        public void G_564_y(o_2576_A p_setLayerStarted_1_) {
            this.R_4764_Y.add(p_setLayerStarted_1_);
        }

        public void P_1922_E(o_2576_A p_setLayerUsed_1_) {
            this.J_1907_R.add(p_setLayerUsed_1_);
        }
    }
}



