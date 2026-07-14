/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Lists
 *  com.mojang.datafixers.util.Either
 *  it.unimi.dsi.fastutil.ints.IntArrayList
 *  it.unimi.dsi.fastutil.ints.IntList
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Either;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import javax.annotation.Nullable;
import lightning.product.K_3381_i;
import lightning.product.T_3975_o;
import lightning.product.V_3137_a;
import lightning.product.b_2085_h;
import lightning.product.ChunkAccess;
import lightning.product.e_2754_J;
import lightning.product.e_3591_l;
import lightning.product.g_2336_b;
import lightning.product.j_3341_s;
import lightning.product.n_1254_X;
import lightning.product.LevelAccessor;
import lightning.product.y_3683_b;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;

public class ChunkStatus {
    private static final EnumSet<z_2963_s.n_1700_B> h_1847_R = EnumSet.of(z_2963_s.n_1700_B.R_4764_Y, z_2963_s.n_1700_B.n_1700_B);
    private static final EnumSet<z_2963_s.n_1700_B> Q_4569_t = EnumSet.of(z_2963_s.n_1700_B.G_564_y, z_2963_s.n_1700_B.J_1907_R, z_2963_s.n_1700_B.P_1922_E, z_2963_s.n_1700_B.u_1723_Y);
    private static final J_1907_R M_182_A = (status, world, templateManager, worldLightManager, loadingFunction, loadingChunk) -> {
        if (loadingChunk instanceof n_1254_X && !loadingChunk.getStatus().J_1907_R(status)) {
            ((n_1254_X)loadingChunk).n_1700_B(status);
        }
        return CompletableFuture.completedFuture(Either.left((Object)loadingChunk));
    };
    public static final ChunkStatus n_1700_B = ChunkStatus.n_1700_B("empty", (ChunkStatus)null, -1, h_1847_R, lightning.product.ChunkStatus$G_564_y.n_1700_B, (e_3591_l world, z_1753_f generator, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> {});
    public static final ChunkStatus J_1907_R = ChunkStatus.n_1700_B("structure_starts", n_1700_B, 0, h_1847_R, lightning.product.ChunkStatus$G_564_y.n_1700_B, (ChunkStatus status, e_3591_l world, z_1753_f generator, b_2085_h templateManager, e_2754_J worldLightManager, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>>> loadingFunction, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> {
        if (!loadingChunk.getStatus().J_1907_R(status)) {
            if (world.T_2506_i().c_132_F().e_4240_b().J_1907_R()) {
                generator.n_1700_B(world.t_1786_h(), world.R_4764_Y(), loadingChunk, templateManager, world.n_1700_B());
            }
            if (loadingChunk instanceof n_1254_X) {
                ((n_1254_X)loadingChunk).n_1700_B(status);
            }
        }
        return CompletableFuture.completedFuture(Either.left((Object)loadingChunk));
    });
    public static final ChunkStatus R_4764_Y = ChunkStatus.n_1700_B("structure_references", J_1907_R, 8, h_1847_R, lightning.product.ChunkStatus$G_564_y.n_1700_B, (e_3591_l world, z_1753_f generator, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> {
        K_3381_i worldgenregion = new K_3381_i(world, chunks);
        generator.n_1700_B(worldgenregion, world.R_4764_Y().n_1700_B(worldgenregion), loadingChunk);
    });
    public static final ChunkStatus G_564_y = ChunkStatus.n_1700_B("biomes", R_4764_Y, 0, h_1847_R, lightning.product.ChunkStatus$G_564_y.n_1700_B, (e_3591_l world, z_1753_f generator, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> generator.n_1700_B(world.t_1786_h().J_1907_R(V_3137_a.PlayerInfo), loadingChunk));
    public static final ChunkStatus P_1922_E = ChunkStatus.n_1700_B("noise", G_564_y, 8, h_1847_R, lightning.product.ChunkStatus$G_564_y.n_1700_B, (e_3591_l world, z_1753_f generator, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> {
        K_3381_i worldgenregion = new K_3381_i(world, chunks);
        generator.n_1700_B((LevelAccessor)worldgenregion, world.R_4764_Y().n_1700_B(worldgenregion), loadingChunk);
    });
    public static final ChunkStatus u_1723_Y = ChunkStatus.n_1700_B("surface", P_1922_E, 0, h_1847_R, lightning.product.ChunkStatus$G_564_y.n_1700_B, (e_3591_l world, z_1753_f generator, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> generator.n_1700_B(new K_3381_i(world, chunks), loadingChunk));
    public static final ChunkStatus v_4262_N = ChunkStatus.n_1700_B("carvers", u_1723_Y, 0, h_1847_R, lightning.product.ChunkStatus$G_564_y.n_1700_B, (e_3591_l world, z_1753_f generator, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> generator.n_1700_B(world.n_1700_B(), world.z_1737_N(), loadingChunk, T_3975_o.n_1700_B.n_1700_B));
    public static final ChunkStatus w_1484_f = ChunkStatus.n_1700_B("liquid_carvers", v_4262_N, 0, Q_4569_t, lightning.product.ChunkStatus$G_564_y.n_1700_B, (e_3591_l world, z_1753_f generator, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> generator.n_1700_B(world.n_1700_B(), world.z_1737_N(), loadingChunk, T_3975_o.n_1700_B.J_1907_R));
    public static final ChunkStatus t_148_a = ChunkStatus.n_1700_B("features", w_1484_f, 8, Q_4569_t, lightning.product.ChunkStatus$G_564_y.n_1700_B, (ChunkStatus status, e_3591_l world, z_1753_f generator, b_2085_h templateManager, e_2754_J worldLightManager, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>>> loadingFunction, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> {
        n_1254_X chunkprimer = (n_1254_X)loadingChunk;
        chunkprimer.n_1700_B(worldLightManager);
        if (!loadingChunk.getStatus().J_1907_R(status)) {
            z_2963_s.n_1700_B(loadingChunk, EnumSet.of(z_2963_s.n_1700_B.P_1922_E, z_2963_s.n_1700_B.u_1723_Y, z_2963_s.n_1700_B.G_564_y, z_2963_s.n_1700_B.J_1907_R));
            K_3381_i worldgenregion = new K_3381_i(world, chunks);
            generator.n_1700_B(worldgenregion, world.R_4764_Y().n_1700_B(worldgenregion));
            chunkprimer.n_1700_B(status);
        }
        return CompletableFuture.completedFuture(Either.left((Object)loadingChunk));
    });
    public static final ChunkStatus s_956_w = ChunkStatus.n_1700_B("light", t_148_a, 1, Q_4569_t, lightning.product.ChunkStatus$G_564_y.n_1700_B, (status, world, generator, templateManager, worldLightManager, loadingFunction, chunks, loadingChunk) -> ChunkStatus.n_1700_B(status, worldLightManager, loadingChunk), (status, world, templateManager, worldLightManager, loadingFunction, loadingChunk) -> ChunkStatus.n_1700_B(status, worldLightManager, loadingChunk));
    public static final ChunkStatus u_2550_I = ChunkStatus.n_1700_B("spawn", s_956_w, 0, Q_4569_t, lightning.product.ChunkStatus$G_564_y.n_1700_B, (e_3591_l world, z_1753_f generator, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> generator.n_1700_B(new K_3381_i(world, chunks)));
    public static final ChunkStatus M_588_G = ChunkStatus.n_1700_B("heightmaps", u_2550_I, 0, Q_4569_t, lightning.product.ChunkStatus$G_564_y.n_1700_B, (e_3591_l world, z_1753_f generator, List<ChunkAccess> chunks, ChunkAccess loadingChunk) -> {});
    public static final ChunkStatus P_4830_p = ChunkStatus.n_1700_B("full", M_588_G, 0, Q_4569_t, lightning.product.ChunkStatus$G_564_y.J_1907_R, (status, world, generator, templateManager, worldLightManager, loadingFunction, chunks, loadingChunk) -> (CompletableFuture)loadingFunction.apply(loadingChunk), (status, world, templateManager, worldLightManager, loadingFunction, loadingChunk) -> (CompletableFuture)loadingFunction.apply(loadingChunk));
    private static final List<ChunkStatus> t_1786_h = ImmutableList.of((Object)P_4830_p, (Object)t_148_a, (Object)w_1484_f, (Object)J_1907_R, (Object)J_1907_R, (Object)J_1907_R, (Object)J_1907_R, (Object)J_1907_R, (Object)J_1907_R, (Object)J_1907_R, (Object)J_1907_R);
    private static final IntList multiplayerClientSuggestionProvider = (IntList)j_3341_s.n_1700_B(new IntArrayList(ChunkStatus.n_1700_B().size()), (T statusRange) -> {
        int i = 0;
        for (int j = ChunkStatus.n_1700_B().size() - 1; j >= 0; --j) {
            while (i + 1 < t_1786_h.size() && j <= t_1786_h.get(i + 1).R_4764_Y()) {
                ++i;
            }
            statusRange.add(0, i);
        }
    });
    private final String w_1457_N;
    private final int Y_601_j;
    private final ChunkStatus Y_259_p;
    private final n_1700_B Q_2552_b;
    private final J_1907_R C_2741_M;
    private final int k_2293_S;
    private final G_564_y q_2307_F;
    private final EnumSet<z_2963_s.n_1700_B> Z_875_P;

    private static CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> n_1700_B(ChunkStatus status, e_2754_J lightManager, ChunkAccess chunk) {
        boolean flag = ChunkStatus.n_1700_B(status, chunk);
        if (!chunk.getStatus().J_1907_R(status)) {
            ((n_1254_X)chunk).n_1700_B(status);
        }
        return lightManager.n_1700_B(chunk, flag).thenApply(Either::left);
    }

    private static ChunkStatus n_1700_B(String key, @Nullable ChunkStatus parent, int taskRange, EnumSet<z_2963_s.n_1700_B> heightmaps, G_564_y type, R_4764_Y generationWorker) {
        return ChunkStatus.n_1700_B(key, parent, taskRange, heightmaps, type, (n_1700_B)generationWorker);
    }

    private static ChunkStatus n_1700_B(String key, @Nullable ChunkStatus parent, int taskRange, EnumSet<z_2963_s.n_1700_B> heightmaps, G_564_y type, n_1700_B generationWorker) {
        return ChunkStatus.n_1700_B(key, parent, taskRange, heightmaps, type, generationWorker, M_182_A);
    }

    private static ChunkStatus n_1700_B(String key, @Nullable ChunkStatus parent, int taskRange, EnumSet<z_2963_s.n_1700_B> heightmaps, G_564_y type, n_1700_B generationWorker, J_1907_R loadingWorker) {
        return V_3137_a.n_1700_B(V_3137_a.N_2525_X, key, new ChunkStatus(key, parent, taskRange, heightmaps, type, generationWorker, loadingWorker));
    }

    public static List<ChunkStatus> n_1700_B() {
        ChunkStatus chunkstatus;
        ArrayList list = Lists.newArrayList();
        for (chunkstatus = P_4830_p; chunkstatus.P_1922_E() != chunkstatus; chunkstatus = chunkstatus.P_1922_E()) {
            list.add(chunkstatus);
        }
        list.add(chunkstatus);
        Collections.reverse(list);
        return list;
    }

    private static boolean n_1700_B(ChunkStatus status, ChunkAccess chunk) {
        return chunk.getStatus().J_1907_R(status) && chunk.hasLight();
    }

    public static ChunkStatus n_1700_B(int id) {
        if (id >= t_1786_h.size()) {
            return n_1700_B;
        }
        return id < 0 ? P_4830_p : t_1786_h.get(id);
    }

    public static int J_1907_R() {
        return t_1786_h.size();
    }

    public static int n_1700_B(ChunkStatus status) {
        return multiplayerClientSuggestionProvider.getInt(status.R_4764_Y());
    }

    ChunkStatus(String nameIn, @Nullable ChunkStatus parentIn, int taskRangeIn, EnumSet<z_2963_s.n_1700_B> heightmapsIn, G_564_y typeIn, n_1700_B generationWorkerIn, J_1907_R loadingWorkerIn) {
        this.w_1457_N = nameIn;
        this.Y_259_p = parentIn == null ? this : parentIn;
        this.Q_2552_b = generationWorkerIn;
        this.C_2741_M = loadingWorkerIn;
        this.k_2293_S = taskRangeIn;
        this.q_2307_F = typeIn;
        this.Z_875_P = heightmapsIn;
        this.Y_601_j = parentIn == null ? 0 : parentIn.R_4764_Y() + 1;
    }

    public int R_4764_Y() {
        return this.Y_601_j;
    }

    public String G_564_y() {
        return this.w_1457_N;
    }

    public ChunkStatus P_1922_E() {
        return this.Y_259_p;
    }

    public CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> n_1700_B(e_3591_l worldIn, z_1753_f chunkGeneratorIn, b_2085_h templateManagerIn, e_2754_J lightManager, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>>> loadingFunction, List<ChunkAccess> chunks) {
        return this.Q_2552_b.doWork(this, worldIn, chunkGeneratorIn, templateManagerIn, lightManager, loadingFunction, chunks, chunks.get(chunks.size() / 2));
    }

    public CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> n_1700_B(e_3591_l worldIn, b_2085_h templateManagerIn, e_2754_J lightManager, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>>> loadingFunction, ChunkAccess loadingChunk) {
        return this.C_2741_M.doWork(this, worldIn, templateManagerIn, lightManager, loadingFunction, loadingChunk);
    }

    public int u_1723_Y() {
        return this.k_2293_S;
    }

    public G_564_y v_4262_N() {
        return this.q_2307_F;
    }

    public static ChunkStatus n_1700_B(String location) {
        return V_3137_a.N_2525_X.n_1700_B(g_2336_b.J_1907_R(location));
    }

    public EnumSet<z_2963_s.n_1700_B> w_1484_f() {
        return this.Z_875_P;
    }

    public boolean J_1907_R(ChunkStatus status) {
        return this.R_4764_Y() >= status.R_4764_Y();
    }

    public String toString() {
        return V_3137_a.N_2525_X.J_1907_R(this).toString();
    }

    public static final class G_564_y
    extends Enum<G_564_y> {
        public static final /* enum */ G_564_y n_1700_B = new G_564_y();
        public static final /* enum */ G_564_y J_1907_R = new G_564_y();
        private static final /* synthetic */ G_564_y[] R_4764_Y;

        public static G_564_y[] values() {
            return (G_564_y[])R_4764_Y.clone();
        }

        public static G_564_y valueOf(String name) {
            return Enum.valueOf(G_564_y.class, name);
        }

        private static /* synthetic */ G_564_y[] n_1700_B() {
            return new G_564_y[]{n_1700_B, J_1907_R};
        }

        static {
            R_4764_Y = lightning.product.ChunkStatus$G_564_y.n_1700_B();
        }
    }

    static interface n_1700_B {
        public CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> doWork(ChunkStatus var1, e_3591_l var2, z_1753_f var3, b_2085_h var4, e_2754_J var5, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>>> var6, List<ChunkAccess> var7, ChunkAccess var8);
    }

    static interface J_1907_R {
        public CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> doWork(ChunkStatus var1, e_3591_l var2, b_2085_h var3, e_2754_J var4, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>>> var5, ChunkAccess var6);
    }

    static interface R_4764_Y
    extends n_1700_B {
        @Override
        default public CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>> doWork(ChunkStatus p_doWork_1_, e_3591_l p_doWork_2_, z_1753_f p_doWork_3_, b_2085_h p_doWork_4_, e_2754_J p_doWork_5_, Function<ChunkAccess, CompletableFuture<Either<ChunkAccess, y_3683_b.n_1700_B>>> p_doWork_6_, List<ChunkAccess> p_doWork_7_, ChunkAccess p_doWork_8_) {
            if (!p_doWork_8_.getStatus().J_1907_R(p_doWork_1_)) {
                this.doWork(p_doWork_2_, p_doWork_3_, p_doWork_7_, p_doWork_8_);
                if (p_doWork_8_ instanceof n_1254_X) {
                    ((n_1254_X)p_doWork_8_).n_1700_B(p_doWork_1_);
                }
            }
            return CompletableFuture.completedFuture(Either.left((Object)p_doWork_8_));
        }

        public void doWork(e_3591_l var1, z_1753_f var2, List<ChunkAccess> var3, ChunkAccess var4);
    }
}


