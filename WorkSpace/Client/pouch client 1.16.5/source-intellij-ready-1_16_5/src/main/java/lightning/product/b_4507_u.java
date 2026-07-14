/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 *  org.apache.logging.log4j.util.Supplier
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.annotation.Nullable;
import lightning.product.ChunkSource;
import lightning.product.A_2352_Z;
import lightning.product.A_4115_X;
import lightning.product.TagContainer;
import lightning.product.ChunkStatus;
import lightning.product.D_38_f;
import lightning.product.F_1241_B;
import lightning.product.BlockGetter;
import lightning.product.F_3620_e;
import lightning.product.G_3474_H;
import lightning.product.H_1748_a;
import lightning.product.BiomeManager;
import lightning.product.I_4817_s;
import lightning.product.Fluids;
import lightning.product.K_4074_S;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.R_1900_x;
import lightning.product.BaseFireBlock;
import lightning.product.LevelData;
import lightning.product.ParticleOptions;
import lightning.product.T_2915_h;
import lightning.product.T_603_v;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.SoundEvent;
import lightning.product.X_1924_A;
import lightning.product.ProfilerFiller;
import lightning.product.X_2960_b;
import lightning.product.ExplosionDamageCalculator;
import lightning.product.Z_1993_T;
import lightning.product.Z_3903_F;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.ChunkAccess;
import lightning.product.f_2392_k;
import lightning.product.g_2336_b;
import lightning.product.WritableLevelData;
import lightning.product.i_2154_H;
import lightning.product.i_4895_l;
import lightning.product.k_594_Q;
import lightning.product.n_3236_c;
import lightning.product.ReportedException;
import lightning.product.CrashReportCategory;
import lightning.product.BlockEntityType;
import lightning.product.LevelAccessor;
import lightning.product.Packet;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.y_3683_b;
import lightning.product.z_2963_s;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class b_4507_u
implements AutoCloseable,
LevelAccessor {
    protected static final Logger G_564_y = LogManager.getLogger();
    public static final Codec<f_2392_k<b_4507_u>> P_1922_E = g_2336_b.n_1700_B.xmap(f_2392_k.J_1907_R(V_3137_a.z_1737_N), f_2392_k::n_1700_B);
    public static final f_2392_k<b_4507_u> u_1723_Y = f_2392_k.n_1700_B(V_3137_a.z_1737_N, new g_2336_b("overworld"));
    public static final f_2392_k<b_4507_u> v_4262_N = f_2392_k.n_1700_B(V_3137_a.z_1737_N, new g_2336_b("the_nether"));
    public static final f_2392_k<b_4507_u> w_1484_f = f_2392_k.n_1700_B(V_3137_a.z_1737_N, new g_2336_b("the_end"));
    private static final b_257_Y[] n_1700_B = b_257_Y.values();
    public final List<i_2154_H> t_148_a = Lists.newArrayList();
    public final List<i_2154_H> s_956_w = Lists.newArrayList();
    protected final List<i_2154_H> u_2550_I = Lists.newArrayList();
    protected final List<i_2154_H> M_588_G = Lists.newArrayList();
    private final Thread J_1907_R;
    private final boolean R_4764_Y;
    private int C_2741_M;
    protected int P_4830_p = new Random().nextInt();
    protected final int h_1847_R = 1013904223;
    protected float Q_4569_t;
    protected float M_182_A;
    protected float t_1786_h;
    protected float multiplayerClientSuggestionProvider;
    public final Random w_1457_N = new Random();
    private final Z_3903_F k_2293_S;
    protected final WritableLevelData Y_601_j;
    private final Supplier<ProfilerFiller> q_2307_F;
    public final boolean Y_259_p;
    protected boolean Q_2552_b;
    private final T_603_v Z_875_P;
    private final BiomeManager c_3005_b;
    private final f_2392_k<b_4507_u> H_2857_Y;
    private static MinecraftClient A_4115_X = MinecraftClient.A_4115_X();

    protected b_4507_u(WritableLevelData worldInfo, f_2392_k<b_4507_u> dimension, final Z_3903_F dimensionType, Supplier<ProfilerFiller> profiler, boolean isRemote, boolean isDebug, long seed) {
        this.q_2307_F = profiler;
        this.Y_601_j = worldInfo;
        this.k_2293_S = dimensionType;
        this.H_2857_Y = dimension;
        this.Y_259_p = isRemote;
        this.Z_875_P = dimensionType.u_1723_Y() != 1.0 ? new T_603_v(this){

            @Override
            public double n_1700_B() {
                return super.n_1700_B() / dimensionType.u_1723_Y();
            }

            @Override
            public double J_1907_R() {
                return super.J_1907_R() / dimensionType.u_1723_Y();
            }
        } : new T_603_v();
        this.J_1907_R = Thread.currentThread();
        this.c_3005_b = new BiomeManager(this, seed, dimensionType.P_4830_p());
        this.R_4764_Y = isDebug;
    }

    @Override
    public boolean v_4276_D() {
        return this.Y_259_p;
    }

    @Nullable
    public G_564_y T_2506_i() {
        return null;
    }

    public c_1514_x w_1457_N() {
        if (b_4507_u.A_4115_X.Y_601_j != null) {
            c_1514_x blockpos = new c_1514_x(this.Y_601_j.J_1907_R(), this.Y_601_j.R_4764_Y(), this.Y_601_j.G_564_y());
            if (!this.H_2857_Y().n_1700_B(blockpos)) {
                blockpos = this.n_1700_B(z_2963_s.n_1700_B.P_1922_E, new c_1514_x(this.H_2857_Y().n_1700_B(), 0.0, this.H_2857_Y().J_1907_R()));
            }
            return blockpos;
        }
        return null;
    }

    public static boolean P_4830_p(c_1514_x pos) {
        return !b_4507_u.Q_4569_t(pos) && b_4507_u.k_2293_S(pos);
    }

    public static boolean h_1847_R(c_1514_x pos) {
        return !b_4507_u.n_1700_B(pos.getY()) && b_4507_u.k_2293_S(pos);
    }

    private static boolean k_2293_S(c_1514_x pos) {
        return pos.getX() >= -30000000 && pos.getZ() >= -30000000 && pos.getX() < 30000000 && pos.getZ() < 30000000;
    }

    private static boolean n_1700_B(int y) {
        return y < -20000000 || y >= 20000000;
    }

    public static boolean Q_4569_t(c_1514_x pos) {
        return b_4507_u.G_564_y(pos.getY());
    }

    public static boolean G_564_y(int y) {
        return y < 0 || y >= 256;
    }

    public H_1748_a M_182_A(c_1514_x pos) {
        return this.u_1723_Y(pos.getX() >> 4, pos.getZ() >> 4);
    }

    public H_1748_a u_1723_Y(int chunkX, int chunkZ) {
        return (H_1748_a)this.n_1700_B(chunkX, chunkZ, ChunkStatus.P_4830_p);
    }

    @Override
    public ChunkAccess n_1700_B(int x, int z, ChunkStatus requiredStatus, boolean nonnull) {
        ChunkAccess ichunk = this.q_2307_F().J_1907_R(x, z, requiredStatus, nonnull);
        if (ichunk == null && nonnull) {
            throw new IllegalStateException("Should always be able to create a chunk!");
        }
        return ichunk;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, K_4074_S newState, int flags) {
        return this.n_1700_B(pos, newState, flags, 512);
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, K_4074_S state, int flags, int recursionLeft) {
        if (b_4507_u.Q_4569_t(pos)) {
            return false;
        }
        if (!this.Y_259_p && this.l_1233_K()) {
            return false;
        }
        H_1748_a chunk = this.M_182_A(pos);
        T_2915_h block = state.J_1907_R();
        K_4074_S blockstate = chunk.setBlockState(pos, state, (flags & 0x40) != 0);
        if (blockstate == null) {
            return false;
        }
        K_4074_S blockstate1 = this.getBlockState(pos);
        if ((flags & 0x80) == 0 && blockstate1 != blockstate && (blockstate1.J_1907_R((BlockGetter)this, pos) != blockstate.J_1907_R((BlockGetter)this, pos) || blockstate1.u_1723_Y() != blockstate.u_1723_Y() || blockstate1.P_1922_E() || blockstate.P_1922_E())) {
            this.D_4792_h().n_1700_B("queueCheckLight");
            this.q_2307_F().G_564_y().n_1700_B(pos);
            this.D_4792_h().R_4764_Y();
        }
        if (blockstate1 == state) {
            if (blockstate != blockstate1) {
                this.n_1700_B(pos, blockstate, blockstate1);
            }
            if ((flags & 2) != 0 && (!this.Y_259_p || (flags & 4) == 0) && (this.Y_259_p || chunk.getLocationType() != null && chunk.getLocationType().n_1700_B(y_3683_b.G_564_y.R_4764_Y))) {
                this.n_1700_B(pos, blockstate, state, flags);
            }
            if ((flags & 1) != 0) {
                this.n_1700_B(pos, blockstate.J_1907_R());
                if (!this.Y_259_p && state.s_956_w()) {
                    this.R_4764_Y(pos, block);
                }
            }
            if ((flags & 0x10) == 0 && recursionLeft > 0) {
                int i = flags & 0xFFFFFFDE;
                blockstate.J_1907_R((LevelAccessor)this, pos, i, recursionLeft - 1);
                state.n_1700_B((LevelAccessor)this, pos, i, recursionLeft - 1);
                state.J_1907_R((LevelAccessor)this, pos, i, recursionLeft - 1);
            }
            this.J_1907_R(pos, blockstate, blockstate1);
        }
        return true;
    }

    public void J_1907_R(c_1514_x pos, K_4074_S blockStateIn, K_4074_S newState) {
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, boolean isMoving) {
        FluidState fluidstate = this.getFluidState(pos);
        return this.n_1700_B(pos, fluidstate.v_4262_N(), 3 | (isMoving ? 64 : 0));
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, boolean dropBlock, @Nullable N_4263_v entity, int recursionLeft) {
        K_4074_S blockstate = this.getBlockState(pos);
        if (blockstate.v_4262_N()) {
            return false;
        }
        FluidState fluidstate = this.getFluidState(pos);
        if (!(blockstate.J_1907_R() instanceof BaseFireBlock)) {
            this.R_4764_Y(2001, pos, T_2915_h.s_956_w(blockstate));
        }
        if (dropBlock) {
            i_2154_H tileentity = blockstate.J_1907_R().G_564_y() ? this.getTileEntity(pos) : null;
            T_2915_h.n_1700_B(blockstate, this, pos, tileentity, entity, Z_1993_T.J_1907_R);
        }
        return this.n_1700_B(pos, fluidstate.v_4262_N(), 3, recursionLeft);
    }

    public boolean J_1907_R(c_1514_x pos, K_4074_S state) {
        return this.n_1700_B(pos, state, 3);
    }

    public abstract void n_1700_B(c_1514_x var1, K_4074_S var2, K_4074_S var3, int var4);

    public void n_1700_B(c_1514_x blockPosIn, K_4074_S oldState, K_4074_S newState) {
    }

    public void J_1907_R(c_1514_x pos, T_2915_h blockIn) {
        this.n_1700_B(pos.west(), blockIn, pos);
        this.n_1700_B(pos.east(), blockIn, pos);
        this.n_1700_B(pos.down(), blockIn, pos);
        this.n_1700_B(pos.up(), blockIn, pos);
        this.n_1700_B(pos.north(), blockIn, pos);
        this.n_1700_B(pos.south(), blockIn, pos);
    }

    public void n_1700_B(c_1514_x pos, T_2915_h blockType, b_257_Y skipSide) {
        if (skipSide != b_257_Y.P_1922_E) {
            this.n_1700_B(pos.west(), blockType, pos);
        }
        if (skipSide != b_257_Y.u_1723_Y) {
            this.n_1700_B(pos.east(), blockType, pos);
        }
        if (skipSide != b_257_Y.n_1700_B) {
            this.n_1700_B(pos.down(), blockType, pos);
        }
        if (skipSide != b_257_Y.J_1907_R) {
            this.n_1700_B(pos.up(), blockType, pos);
        }
        if (skipSide != b_257_Y.R_4764_Y) {
            this.n_1700_B(pos.north(), blockType, pos);
        }
        if (skipSide != b_257_Y.G_564_y) {
            this.n_1700_B(pos.south(), blockType, pos);
        }
    }

    public void n_1700_B(c_1514_x pos, T_2915_h blockIn, c_1514_x fromPos) {
        if (!this.Y_259_p) {
            K_4074_S blockstate = this.getBlockState(pos);
            try {
                blockstate.n_1700_B(this, pos, blockIn, fromPos, false);
            }
            catch (Throwable throwable) {
                n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Exception while updating neighbours");
                CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block being updated");
                crashreportcategory.n_1700_B("Source block type", () -> {
                    try {
                        return String.format("ID #%s (%s // %s)", V_3137_a.q_4610_l.J_1907_R(blockIn), blockIn.P_4830_p(), blockIn.getClass().getCanonicalName());
                    }
                    catch (Throwable throwable1) {
                        return "ID #" + String.valueOf(V_3137_a.q_4610_l.J_1907_R(blockIn));
                    }
                });
                CrashReportCategory.n_1700_B(crashreportcategory, pos, blockstate);
                throw new ReportedException(crashreport);
            }
        }
    }

    @Override
    public int n_1700_B(z_2963_s.n_1700_B heightmapType, int x, int z) {
        int i = x >= -30000000 && z >= -30000000 && x < 30000000 && z < 30000000 ? (this.R_4764_Y(x >> 4, z >> 4) ? this.u_1723_Y(x >> 4, z >> 4).getTopBlockY(heightmapType, x & 0xF, z & 0xF) + 1 : 0) : this.d_2461_k() + 1;
        return i;
    }

    @Override
    public R_1900_x getLightManager() {
        return this.q_2307_F().G_564_y();
    }

    @Override
    public K_4074_S getBlockState(c_1514_x pos) {
        if (b_4507_u.Q_4569_t(pos)) {
            return a_3742_W.z_2759_Q.multiplayerClientSuggestionProvider();
        }
        H_1748_a chunk = this.u_1723_Y(pos.getX() >> 4, pos.getZ() >> 4);
        return chunk.getBlockState(pos);
    }

    @Override
    public FluidState getFluidState(c_1514_x pos) {
        if (b_4507_u.Q_4569_t(pos)) {
            return Fluids.n_1700_B.w_1484_f();
        }
        H_1748_a chunk = this.M_182_A(pos);
        return chunk.getFluidState(pos);
    }

    public boolean q_4610_l() {
        return !this.G_624_v().h_1847_R() && this.C_2741_M < 4;
    }

    public boolean z_4693_k() {
        return !this.G_624_v().h_1847_R() && !this.q_4610_l();
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player, c_1514_x pos, SoundEvent soundIn, D_38_f category, float volume, float pitch) {
        this.n_1700_B(player, (double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, soundIn, category, volume, pitch);
    }

    public abstract void n_1700_B(@Nullable a_3913_L var1, double var2, double var4, double var6, SoundEvent var8, D_38_f var9, float var10, float var11);

    public abstract void n_1700_B(@Nullable a_3913_L var1, N_4263_v var2, SoundEvent var3, D_38_f var4, float var5, float var6);

    public void n_1700_B(double x, double y, double z, SoundEvent soundIn, D_38_f category, float volume, float pitch, boolean distanceDelay) {
    }

    @Override
    public void n_1700_B(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
    }

    public void n_1700_B(ParticleOptions particleData, boolean forceAlwaysRender, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
    }

    public void J_1907_R(ParticleOptions particleData, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
    }

    public void J_1907_R(ParticleOptions particleData, boolean ignoreRange, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
    }

    public float P_1922_E(float partialTicks) {
        float f = this.G_564_y(partialTicks);
        return f * ((float)Math.PI * 2);
    }

    public boolean n_1700_B(i_2154_H tile) {
        boolean flag;
        if (this.Q_2552_b) {
            org.apache.logging.log4j.util.Supplier[] supplierArray = new org.apache.logging.log4j.util.Supplier[2];
            supplierArray[0] = () -> V_3137_a.X_933_l.J_1907_R(tile.z_1737_N());
            supplierArray[1] = tile::x_607_J;
            G_564_y.error("Adding block entity while ticking: {} @ {}", supplierArray);
        }
        if ((flag = this.t_148_a.add(tile)) && tile instanceof X_1924_A) {
            this.s_956_w.add(tile);
        }
        if (this.Y_259_p) {
            c_1514_x blockpos = tile.x_607_J();
            K_4074_S blockstate = this.getBlockState(blockpos);
            this.n_1700_B(blockpos, blockstate, blockstate, 2);
        }
        return flag;
    }

    public void n_1700_B(Collection<i_2154_H> tileEntityCollection) {
        if (this.Q_2552_b) {
            this.u_2550_I.addAll(tileEntityCollection);
        } else {
            for (i_2154_H tileentity : tileEntityCollection) {
                this.n_1700_B(tileentity);
            }
        }
    }

    public void g_221_o() {
        ProfilerFiller iprofiler = this.D_4792_h();
        iprofiler.n_1700_B("blockEntities");
        if (!this.M_588_G.isEmpty()) {
            this.s_956_w.removeAll(this.M_588_G);
            this.t_148_a.removeAll(this.M_588_G);
            this.M_588_G.clear();
        }
        this.Q_2552_b = true;
        Iterator<i_2154_H> iterator = this.s_956_w.iterator();
        while (iterator.hasNext()) {
            i_2154_H tileentity = iterator.next();
            if (!tileentity.n_3318_d() && tileentity.t_4043_B()) {
                c_1514_x blockpos = tileentity.x_607_J();
                if (this.q_2307_F().n_1700_B(blockpos) && this.H_2857_Y().n_1700_B(blockpos)) {
                    try {
                        iprofiler.n_1700_B(() -> String.valueOf(BlockEntityType.n_1700_B(tileentity.z_1737_N())));
                        if (tileentity.z_1737_N().n_1700_B(this.getBlockState(blockpos).J_1907_R())) {
                            ((X_1924_A)((Object)tileentity)).P_1922_E();
                        } else {
                            tileentity.v_4276_D();
                        }
                        iprofiler.R_4764_Y();
                    }
                    catch (Throwable throwable) {
                        n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Ticking block entity");
                        CrashReportCategory crashreportcategory = crashreport.n_1700_B("Block entity being ticked");
                        tileentity.n_1700_B(crashreportcategory);
                        throw new ReportedException(crashreport);
                    }
                }
            }
            if (!tileentity.n_3318_d()) continue;
            iterator.remove();
            this.t_148_a.remove(tileentity);
            if (!this.M_588_G(tileentity.x_607_J())) continue;
            this.M_182_A(tileentity.x_607_J()).removeTileEntity(tileentity.x_607_J());
        }
        this.Q_2552_b = false;
        iprofiler.J_1907_R("pendingBlockEntities");
        if (!this.u_2550_I.isEmpty()) {
            for (int i = 0; i < this.u_2550_I.size(); ++i) {
                i_2154_H tileentity1 = this.u_2550_I.get(i);
                if (tileentity1.n_3318_d()) continue;
                if (!this.t_148_a.contains(tileentity1)) {
                    this.n_1700_B(tileentity1);
                }
                if (!this.M_588_G(tileentity1.x_607_J())) continue;
                H_1748_a chunk = this.M_182_A(tileentity1.x_607_J());
                K_4074_S blockstate = chunk.getBlockState(tileentity1.x_607_J());
                chunk.addTileEntity(tileentity1.x_607_J(), tileentity1);
                this.n_1700_B(tileentity1.x_607_J(), blockstate, blockstate, 3);
            }
            this.u_2550_I.clear();
        }
        iprofiler.R_4764_Y();
    }

    public void n_1700_B(Consumer<N_4263_v> consumerEntity, N_4263_v entityIn) {
        try {
            lightning.product.A_4115_X.n_1700_B(new X_2960_b());
            consumerEntity.accept(entityIn);
        }
        catch (Throwable throwable) {
            n_3236_c crashreport = n_3236_c.n_1700_B(throwable, "Ticking entity");
            CrashReportCategory crashreportcategory = crashreport.n_1700_B("Entity being ticked");
            entityIn.n_1700_B(crashreportcategory);
            throw new ReportedException(crashreport);
        }
    }

    public F_1241_B n_1700_B(@Nullable N_4263_v entityIn, double xIn, double yIn, double zIn, float explosionRadius, F_1241_B.n_1700_B modeIn) {
        return this.n_1700_B(entityIn, null, null, xIn, yIn, zIn, explosionRadius, false, modeIn);
    }

    public F_1241_B n_1700_B(@Nullable N_4263_v entityIn, double xIn, double yIn, double zIn, float explosionRadius, boolean causesFire, F_1241_B.n_1700_B modeIn) {
        return this.n_1700_B(entityIn, null, null, xIn, yIn, zIn, explosionRadius, causesFire, modeIn);
    }

    public F_1241_B n_1700_B(@Nullable N_4263_v exploder, @Nullable P_11_z damageSource, @Nullable ExplosionDamageCalculator context, double x, double y, double z, float size, boolean causesFire, F_1241_B.n_1700_B mode) {
        F_1241_B explosion = new F_1241_B(this, exploder, damageSource, context, x, y, z, size, causesFire, mode);
        explosion.n_1700_B();
        explosion.n_1700_B(true);
        return explosion;
    }

    public String e_2887_G() {
        return this.q_2307_F().J_1907_R();
    }

    @Override
    @Nullable
    public i_2154_H getTileEntity(c_1514_x pos) {
        if (b_4507_u.Q_4569_t(pos)) {
            return null;
        }
        if (!this.Y_259_p && Thread.currentThread() != this.J_1907_R) {
            return null;
        }
        i_2154_H tileentity = null;
        if (this.Q_2552_b) {
            tileentity = this.q_2307_F(pos);
        }
        if (tileentity == null) {
            tileentity = this.M_182_A(pos).getTileEntity(pos, H_1748_a.n_1700_B.n_1700_B);
        }
        if (tileentity == null) {
            tileentity = this.q_2307_F(pos);
        }
        return tileentity;
    }

    @Nullable
    private i_2154_H q_2307_F(c_1514_x pos) {
        for (int i = 0; i < this.u_2550_I.size(); ++i) {
            i_2154_H tileentity = this.u_2550_I.get(i);
            if (tileentity.n_3318_d() || !tileentity.x_607_J().equals(pos)) continue;
            return tileentity;
        }
        return null;
    }

    public void n_1700_B(c_1514_x pos, @Nullable i_2154_H tileEntityIn) {
        if (!b_4507_u.Q_4569_t(pos) && tileEntityIn != null && !tileEntityIn.n_3318_d()) {
            if (this.Q_2552_b) {
                tileEntityIn.J_1907_R(this, pos);
                Iterator<i_2154_H> iterator = this.u_2550_I.iterator();
                while (iterator.hasNext()) {
                    i_2154_H tileentity = iterator.next();
                    if (!tileentity.x_607_J().equals(pos)) continue;
                    tileentity.I_();
                    iterator.remove();
                }
                this.u_2550_I.add(tileEntityIn);
            } else {
                this.M_182_A(pos).addTileEntity(pos, tileEntityIn);
                this.n_1700_B(tileEntityIn);
            }
        }
    }

    public void t_1786_h(c_1514_x pos) {
        i_2154_H tileentity = this.getTileEntity(pos);
        if (tileentity != null && this.Q_2552_b) {
            tileentity.I_();
            this.u_2550_I.remove(tileentity);
        } else {
            if (tileentity != null) {
                this.u_2550_I.remove(tileentity);
                this.t_148_a.remove(tileentity);
                this.s_956_w.remove(tileentity);
            }
            this.M_182_A(pos).removeTileEntity(pos);
        }
    }

    public boolean multiplayerClientSuggestionProvider(c_1514_x pos) {
        return b_4507_u.Q_4569_t(pos) ? false : this.q_2307_F().P_1922_E(pos.getX() >> 4, pos.getZ() >> 4);
    }

    public boolean n_1700_B(c_1514_x pos, N_4263_v entity, b_257_Y direction) {
        if (b_4507_u.Q_4569_t(pos)) {
            return false;
        }
        ChunkAccess ichunk = this.n_1700_B(pos.getX() >> 4, pos.getZ() >> 4, ChunkStatus.P_4830_p, false);
        return ichunk == null ? false : ichunk.getBlockState(pos).n_1700_B((BlockGetter)this, pos, entity, direction);
    }

    public boolean n_1700_B(c_1514_x pos, N_4263_v entityIn) {
        return this.n_1700_B(pos, entityIn, b_257_Y.J_1907_R);
    }

    public void B_1668_F() {
        double d0 = 1.0 - (double)(this.w_1484_f(1.0f) * 5.0f) / 16.0;
        double d1 = 1.0 - (double)(this.u_1723_Y(1.0f) * 5.0f) / 16.0;
        double d2 = 0.5 + 2.0 * u_530_F.n_1700_B((double)u_530_F.J_1907_R(this.G_564_y(1.0f) * ((float)Math.PI * 2)), -0.25, 0.25);
        this.C_2741_M = (int)((1.0 - d2 * d0 * d1) * 11.0);
    }

    public void n_1700_B(boolean hostile, boolean peaceful) {
        this.q_2307_F().n_1700_B(hostile, peaceful);
    }

    protected void g_164_R() {
        if (this.Y_601_j.v_4262_N()) {
            this.M_182_A = 1.0f;
            if (this.Y_601_j.t_148_a()) {
                this.multiplayerClientSuggestionProvider = 1.0f;
            }
        }
    }

    @Override
    public void close() throws IOException {
        this.q_2307_F().close();
    }

    @Override
    @Nullable
    public BlockGetter G_564_y(int chunkX, int chunkZ) {
        return this.n_1700_B(chunkX, chunkZ, ChunkStatus.P_4830_p, false);
    }

    @Override
    public List<N_4263_v> J_1907_R(@Nullable N_4263_v entityIn, I_4817_s boundingBox, @Nullable Predicate<? super N_4263_v> predicate) {
        this.D_4792_h().R_4764_Y("getEntities");
        ArrayList list = Lists.newArrayList();
        int i = u_530_F.R_4764_Y((boundingBox.minX - 2.0) / 16.0);
        int j = u_530_F.R_4764_Y((boundingBox.maxX + 2.0) / 16.0);
        int k = u_530_F.R_4764_Y((boundingBox.minZ - 2.0) / 16.0);
        int l = u_530_F.R_4764_Y((boundingBox.maxZ + 2.0) / 16.0);
        ChunkSource abstractchunkprovider = this.q_2307_F();
        for (int i1 = i; i1 <= j; ++i1) {
            for (int j1 = k; j1 <= l; ++j1) {
                H_1748_a chunk = abstractchunkprovider.n_1700_B(i1, j1, false);
                if (chunk == null) continue;
                chunk.getEntitiesWithinAABBForEntity(entityIn, boundingBox, list, predicate);
            }
        }
        return list;
    }

    public <T extends N_4263_v> List<T> n_1700_B(@Nullable t_5_h<T> type, I_4817_s boundingBox, Predicate<? super T> predicate) {
        this.D_4792_h().R_4764_Y("getEntities");
        int i = u_530_F.R_4764_Y((boundingBox.minX - 2.0) / 16.0);
        int j = u_530_F.P_1922_E((boundingBox.maxX + 2.0) / 16.0);
        int k = u_530_F.R_4764_Y((boundingBox.minZ - 2.0) / 16.0);
        int l = u_530_F.P_1922_E((boundingBox.maxZ + 2.0) / 16.0);
        ArrayList list = Lists.newArrayList();
        for (int i1 = i; i1 < j; ++i1) {
            for (int j1 = k; j1 < l; ++j1) {
                H_1748_a chunk = this.q_2307_F().n_1700_B(i1, j1, false);
                if (chunk == null) continue;
                chunk.getEntitiesWithinAABBForList(type, boundingBox, list, predicate);
            }
        }
        return list;
    }

    @Override
    public <T extends N_4263_v> List<T> n_1700_B(Class<? extends T> clazz, I_4817_s aabb, @Nullable Predicate<? super T> filter) {
        this.D_4792_h().R_4764_Y("getEntities");
        int i = u_530_F.R_4764_Y((aabb.minX - 2.0) / 16.0);
        int j = u_530_F.P_1922_E((aabb.maxX + 2.0) / 16.0);
        int k = u_530_F.R_4764_Y((aabb.minZ - 2.0) / 16.0);
        int l = u_530_F.P_1922_E((aabb.maxZ + 2.0) / 16.0);
        ArrayList list = Lists.newArrayList();
        ChunkSource abstractchunkprovider = this.q_2307_F();
        for (int i1 = i; i1 < j; ++i1) {
            for (int j1 = k; j1 < l; ++j1) {
                H_1748_a chunk = abstractchunkprovider.n_1700_B(i1, j1, false);
                if (chunk == null) continue;
                chunk.getEntitiesOfTypeWithinAABB(clazz, aabb, list, filter);
            }
        }
        return list;
    }

    @Override
    public <T extends N_4263_v> List<T> J_1907_R(Class<? extends T> p_225316_1_, I_4817_s p_225316_2_, @Nullable Predicate<? super T> p_225316_3_) {
        this.D_4792_h().R_4764_Y("getLoadedEntities");
        int i = u_530_F.R_4764_Y((p_225316_2_.minX - 2.0) / 16.0);
        int j = u_530_F.P_1922_E((p_225316_2_.maxX + 2.0) / 16.0);
        int k = u_530_F.R_4764_Y((p_225316_2_.minZ - 2.0) / 16.0);
        int l = u_530_F.P_1922_E((p_225316_2_.maxZ + 2.0) / 16.0);
        ArrayList list = Lists.newArrayList();
        ChunkSource abstractchunkprovider = this.q_2307_F();
        for (int i1 = i; i1 < j; ++i1) {
            for (int j1 = k; j1 < l; ++j1) {
                H_1748_a chunk = abstractchunkprovider.R_4764_Y(i1, j1);
                if (chunk == null) continue;
                chunk.getEntitiesOfTypeWithinAABB(p_225316_1_, p_225316_2_, list, p_225316_3_);
            }
        }
        return list;
    }

    @Nullable
    public abstract N_4263_v J_1907_R(int var1);

    public void J_1907_R(c_1514_x pos, i_2154_H unusedTileEntity) {
        if (this.M_588_G(pos)) {
            this.M_182_A(pos).markDirty();
        }
    }

    @Override
    public int d_2461_k() {
        return 63;
    }

    public int w_1457_N(c_1514_x pos) {
        int i = 0;
        if ((i = Math.max(i, this.n_1700_B(pos.down(), b_257_Y.n_1700_B))) >= 15) {
            return i;
        }
        if ((i = Math.max(i, this.n_1700_B(pos.up(), b_257_Y.J_1907_R))) >= 15) {
            return i;
        }
        if ((i = Math.max(i, this.n_1700_B(pos.north(), b_257_Y.R_4764_Y))) >= 15) {
            return i;
        }
        if ((i = Math.max(i, this.n_1700_B(pos.south(), b_257_Y.G_564_y))) >= 15) {
            return i;
        }
        if ((i = Math.max(i, this.n_1700_B(pos.west(), b_257_Y.P_1922_E))) >= 15) {
            return i;
        }
        return (i = Math.max(i, this.n_1700_B(pos.east(), b_257_Y.u_1723_Y))) >= 15 ? i : i;
    }

    public boolean J_1907_R(c_1514_x pos, b_257_Y side) {
        return this.R_4764_Y(pos, side) > 0;
    }

    public int R_4764_Y(c_1514_x pos, b_257_Y facing) {
        K_4074_S blockstate = this.getBlockState(pos);
        int i = blockstate.J_1907_R((BlockGetter)this, pos, facing);
        return blockstate.v_4262_N(this, pos) ? Math.max(i, this.w_1457_N(pos)) : i;
    }

    public boolean Y_601_j(c_1514_x pos) {
        if (this.R_4764_Y(pos.down(), b_257_Y.n_1700_B) > 0) {
            return true;
        }
        if (this.R_4764_Y(pos.up(), b_257_Y.J_1907_R) > 0) {
            return true;
        }
        if (this.R_4764_Y(pos.north(), b_257_Y.R_4764_Y) > 0) {
            return true;
        }
        if (this.R_4764_Y(pos.south(), b_257_Y.G_564_y) > 0) {
            return true;
        }
        if (this.R_4764_Y(pos.west(), b_257_Y.P_1922_E) > 0) {
            return true;
        }
        return this.R_4764_Y(pos.east(), b_257_Y.u_1723_Y) > 0;
    }

    public int Y_259_p(c_1514_x pos) {
        int i = 0;
        for (b_257_Y direction : n_1700_B) {
            int j = this.R_4764_Y(pos.offset(direction), direction);
            if (j >= 15) {
                return 15;
            }
            if (j <= i) continue;
            i = j;
        }
        return i;
    }

    public void w_1484_f() {
    }

    public long X_933_l() {
        return this.Y_601_j.P_1922_E();
    }

    public long Z_976_R() {
        return this.Y_601_j.u_1723_Y();
    }

    public boolean n_1700_B(a_3913_L player, c_1514_x pos) {
        return true;
    }

    public void n_1700_B(N_4263_v entityIn, byte state) {
    }

    public void n_1700_B(c_1514_x pos, T_2915_h blockIn, int eventID, int eventParam) {
        this.getBlockState(pos).n_1700_B(this, pos, eventID, eventParam);
    }

    @Override
    public LevelData k_2293_S() {
        return this.Y_601_j;
    }

    public A_2352_Z H_1990_U() {
        return this.Y_601_j.s_956_w();
    }

    public float u_1723_Y(float delta) {
        return u_530_F.v_4262_N(delta, this.t_1786_h, this.multiplayerClientSuggestionProvider) * this.w_1484_f(delta);
    }

    public void v_4262_N(float strength) {
        this.t_1786_h = strength;
        this.multiplayerClientSuggestionProvider = strength;
    }

    public float w_1484_f(float delta) {
        return u_530_F.v_4262_N(delta, this.Q_4569_t, this.M_182_A);
    }

    public void t_148_a(float strength) {
        this.Q_4569_t = strength;
        this.M_182_A = strength;
    }

    public boolean N_2525_X() {
        if (this.G_624_v().J_1907_R() && !this.G_624_v().R_4764_Y()) {
            return (double)this.u_1723_Y(1.0f) > 0.9;
        }
        return false;
    }

    public boolean c_4037_x() {
        return (double)this.w_1484_f(1.0f) > 0.2;
    }

    public boolean Q_2552_b(c_1514_x position) {
        if (!this.c_4037_x()) {
            return false;
        }
        if (!this.canSeeSky(position)) {
            return false;
        }
        if (this.n_1700_B(z_2963_s.n_1700_B.P_1922_E, position).getY() > position.getY()) {
            return false;
        }
        k_594_Q biome = this.P_1922_E(position);
        return biome.R_4764_Y() == k_594_Q.P_1922_E.J_1907_R && biome.n_1700_B(position) >= 0.15f;
    }

    public boolean C_2741_M(c_1514_x pos) {
        k_594_Q biome = this.P_1922_E(pos);
        return biome.G_564_y();
    }

    @Nullable
    public abstract F_3620_e n_1700_B(String var1);

    public abstract void n_1700_B(F_3620_e var1);

    public abstract int h_1847_R();

    public void J_1907_R(int id, c_1514_x pos, int data) {
    }

    public CrashReportCategory n_1700_B(n_3236_c report) {
        CrashReportCategory crashreportcategory = report.n_1700_B("Affected level", 1);
        crashreportcategory.n_1700_B("All players", () -> this.multiplayerClientSuggestionProvider().size() + " total; " + String.valueOf(this.multiplayerClientSuggestionProvider()));
        crashreportcategory.n_1700_B("Chunk stats", this.q_2307_F()::J_1907_R);
        crashreportcategory.n_1700_B("Level dimension", () -> this.g_2268_R().n_1700_B().toString());
        try {
            this.Y_601_j.n_1700_B(crashreportcategory);
        }
        catch (Throwable throwable) {
            crashreportcategory.n_1700_B("Level Data Unobtainable", throwable);
        }
        return crashreportcategory;
    }

    public abstract void n_1700_B(int var1, c_1514_x var2, int var3);

    public void n_1700_B(double x, double y, double z, double motionX, double motionY, double motionZ, @Nullable U_2912_j compound) {
    }

    public abstract i_4895_l Q_4569_t();

    public void R_4764_Y(c_1514_x pos, T_2915_h blockIn) {
        for (b_257_Y direction : b_257_Y.R_4764_Y.n_1700_B) {
            c_1514_x blockpos = pos.offset(direction);
            if (!this.M_588_G(blockpos)) continue;
            K_4074_S blockstate = this.getBlockState(blockpos);
            if (blockstate.n_1700_B(a_3742_W.N_4006_T)) {
                blockstate.n_1700_B(this, blockpos, blockIn, pos, false);
                continue;
            }
            if (!blockstate.v_4262_N(this, blockpos) || !(blockstate = this.getBlockState(blockpos = blockpos.offset(direction))).n_1700_B(a_3742_W.N_4006_T)) continue;
            blockstate.n_1700_B(this, blockpos, blockIn, pos, false);
        }
    }

    @Override
    public DifficultyInstance J_1907_R(c_1514_x pos) {
        long i = 0L;
        float f = 0.0f;
        if (this.M_588_G(pos)) {
            f = this.Y_1740_V();
            i = this.M_182_A(pos).getInhabitedTime();
        }
        return new DifficultyInstance(this.x_607_J(), this.Z_976_R(), i, f);
    }

    @Override
    public int d_2427_y() {
        return this.C_2741_M;
    }

    public void R_4764_Y(int timeFlashIn) {
    }

    @Override
    public T_603_v H_2857_Y() {
        return this.Z_875_P;
    }

    public void n_1700_B(Packet<?> packetIn) {
        throw new UnsupportedOperationException("Can't send packets to server unless you're on the client.");
    }

    @Override
    public Z_3903_F G_624_v() {
        return this.k_2293_S;
    }

    public f_2392_k<b_4507_u> g_2268_R() {
        return this.H_2857_Y;
    }

    @Override
    public Random e_4240_b() {
        return this.w_1457_N;
    }

    @Override
    public boolean n_1700_B(c_1514_x pos, Predicate<K_4074_S> state) {
        return state.test(this.getBlockState(pos));
    }

    public abstract G_3474_H s_956_w();

    public abstract TagContainer M_182_A();

    public c_1514_x n_1700_B(int x, int y, int z, int yMask) {
        this.P_4830_p = this.P_4830_p * 3 + 1013904223;
        int i = this.P_4830_p >> 2;
        return new c_1514_x(x + (i & 0xF), y + (i >> 16 & yMask), z + (i >> 8 & 0xF));
    }

    public boolean T_3594_S() {
        return false;
    }

    public ProfilerFiller D_4792_h() {
        return this.q_2307_F.get();
    }

    public Supplier<ProfilerFiller> s_2632_s() {
        return this.q_2307_F;
    }

    @Override
    public BiomeManager z_1737_N() {
        return this.c_3005_b;
    }

    public final boolean l_1233_K() {
        return this.R_4764_Y;
    }

    @Override
    public /* synthetic */ ChunkAccess P_1922_E(int n, int n2) {
        return this.u_1723_Y(n, n2);
    }
}



