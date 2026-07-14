/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.annotations.VisibleForTesting
 *  com.google.common.collect.Iterables
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Queues
 *  com.google.common.collect.Sets
 *  it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap
 *  it.unimi.dsi.fastutil.ints.Int2ObjectMap$Entry
 *  it.unimi.dsi.fastutil.longs.LongSet
 *  it.unimi.dsi.fastutil.longs.LongSets
 *  it.unimi.dsi.fastutil.objects.Object2IntMap$Entry
 *  it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap
 *  it.unimi.dsi.fastutil.objects.ObjectIterator
 *  it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet
 *  javax.annotation.Nonnull
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.longs.LongSet;
import it.unimi.dsi.fastutil.longs.LongSets;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import lightning.product.ChunkSource;
import lightning.product.A_2352_Z;
import lightning.product.B_4088_l;
import lightning.product.PathNavigation;
import lightning.product.DebugPackets;
import lightning.product.C_1375_J;
import lightning.product.TagContainer;
import lightning.product.C_3615_s;
import lightning.product.C_990_G;
import lightning.product.TickList;
import lightning.product.ChunkStatus;
import lightning.product.D_38_f;
import lightning.product.D_4381_C;
import lightning.product.D_4533_B;
import lightning.product.CustomSpawner;
import lightning.product.F_1241_B;
import lightning.product.F_2904_S;
import lightning.product.F_3620_e;
import lightning.product.StructureFeature;
import lightning.product.G_3474_H;
import lightning.product.H_1748_a;
import lightning.product.ReputationEventType;
import lightning.product.I_4817_s;
import lightning.product.Fluids;
import lightning.product.J_3017_d;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.K_4074_S;
import lightning.product.BoundingBox;
import lightning.product.Animal;
import lightning.product.DifficultyInstance;
import lightning.product.N_4263_v;
import lightning.product.O_1400_s;
import lightning.product.P_11_z;
import lightning.product.P_2976_u;
import lightning.product.P_3550_Z;
import lightning.product.Q_2410_O;
import lightning.product.WorldGenLevel;
import lightning.product.ClientboundSetDefaultSpawnPositionPacket;
import lightning.product.ParticleOptions;
import lightning.product.T_2915_h;
import lightning.product.U_2866_z;
import lightning.product.V_3137_a;
import lightning.product.SoundEvent;
import lightning.product.X_1446_C;
import lightning.product.ProfilerFiller;
import lightning.product.ClientboundSoundPacket;
import lightning.product.ExplosionDamageCalculator;
import lightning.product.Y_1387_d;
import lightning.product.Y_3104_y;
import lightning.product.Z_3903_F;
import lightning.product.Z_530_i;
import lightning.product.ClientboundBlockEventPacket;
import lightning.product.Z_749_F;
import lightning.product.ForcedChunksSavedData;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_2085_h;
import lightning.product.b_257_Y;
import lightning.product.b_2971_b;
import lightning.product.b_2971_z;
import lightning.product.b_3129_s;
import lightning.product.b_4507_u;
import lightning.product.b_4946_z;
import lightning.product.c_1514_x;
import lightning.product.FluidState;
import lightning.product.SectionPos;
import lightning.product.ServerLevelData;
import lightning.product.StructureStart;
import lightning.product.ChunkAccess;
import lightning.product.e_1322_b;
import lightning.product.e_2866_D;
import lightning.product.f_2392_k;
import lightning.product.WaterAnimal;
import lightning.product.g_2336_b;
import lightning.product.g_457_d;
import lightning.product.ClientboundLevelParticlesPacket;
import lightning.product.ClientboundExplodePacket;
import lightning.product.i_2154_H;
import lightning.product.ServerScoreboard;
import lightning.product.i_4895_l;
import lightning.product.ProgressListener;
import lightning.product.TicketType;
import lightning.product.j_3341_s;
import lightning.product.ChunkProgressListener;
import lightning.product.k_594_Q;
import lightning.product.n_3236_c;
import lightning.product.q_2232_A;
import lightning.product.EnderDragonPart;
import lightning.product.r_1780_L;
import lightning.product.r_4097_j;
import lightning.product.r_4811_B;
import lightning.product.s_1395_c;
import lightning.product.ClientboundLevelEventPacket;
import lightning.product.s_4380_l;
import lightning.product.Fluid;
import lightning.product.LightningBolt;
import lightning.product.Packet;
import lightning.product.t_4503_H;
import lightning.product.t_5_h;
import lightning.product.u_530_F;
import lightning.product.u_743_i;
import lightning.product.MapIndex;
import lightning.product.x_268_Y;
import lightning.product.x_282_a;
import lightning.product.y_1195_s;
import lightning.product.BooleanOp;
import lightning.product.z_1753_f;
import lightning.product.z_2963_s;
import lightning.product.ClientboundSoundEntityPacket;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class e_3591_l
extends b_4507_u
implements WorldGenLevel {
    public static final c_1514_x n_1700_B = new c_1514_x(100, 50, 0);
    private static final Logger k_2293_S = LogManager.getLogger();
    private final Int2ObjectMap<N_4263_v> q_2307_F = new Int2ObjectLinkedOpenHashMap();
    private final Map<UUID, N_4263_v> Z_875_P = Maps.newHashMap();
    private final Queue<N_4263_v> c_3005_b = Queues.newArrayDeque();
    private final List<B_4088_l> H_2857_Y = Lists.newArrayList();
    private final C_3615_s A_4115_X;
    boolean J_1907_R;
    private final G_564_y Y_1740_V;
    private final ServerLevelData t_4043_B;
    public boolean R_4764_Y;
    private boolean x_607_J;
    private int e_4240_b;
    private final g_457_d n_3318_d;
    private final Y_3104_y<T_2915_h> d_2427_y = new Y_3104_y<T_2915_h>(this, p_205341_0_ -> p_205341_0_ == null || p_205341_0_.multiplayerClientSuggestionProvider().v_4262_N(), V_3137_a.q_4610_l::J_1907_R, this::J_1907_R);
    private final Y_3104_y<Fluid> z_1737_N = new Y_3104_y<Fluid>(this, p_205774_0_ -> p_205774_0_ == null || p_205774_0_ == Fluids.n_1700_B, V_3137_a.G_624_v::J_1907_R, this::n_1700_B);
    private final Set<PathNavigation> v_4276_D = Sets.newHashSet();
    protected final U_2866_z C_2741_M;
    private final ObjectLinkedOpenHashSet<P_2976_u> d_2461_k = new ObjectLinkedOpenHashSet();
    private boolean G_624_v;
    private final List<CustomSpawner> T_2506_i;
    @Nullable
    private final C_990_G q_4610_l;
    private final J_3017_d z_4693_k;
    private final boolean g_221_o;

    public e_3591_l(G_564_y p_i241885_1_, Executor p_i241885_2_, b_2971_z.n_1700_B p_i241885_3_, ServerLevelData p_i241885_4_, f_2392_k<b_4507_u> p_i241885_5_, Z_3903_F p_i241885_6_, ChunkProgressListener p_i241885_7_, z_1753_f p_i241885_8_, boolean p_i241885_9_, long p_i241885_10_, List<CustomSpawner> p_i241885_12_, boolean p_i241885_13_) {
        super(p_i241885_4_, p_i241885_5_, p_i241885_6_, p_i241885_1_::LongRunningTask, false, p_i241885_9_, p_i241885_10_);
        this.g_221_o = p_i241885_13_;
        this.Y_1740_V = p_i241885_1_;
        this.T_2506_i = p_i241885_12_;
        this.t_4043_B = p_i241885_4_;
        this.A_4115_X = new C_3615_s(this, p_i241885_3_, p_i241885_1_.M_1641_O(), p_i241885_1_.M_2677_i(), p_i241885_2_, p_i241885_8_, p_i241885_1_.p_178_J().t_1786_h(), p_i241885_1_.RealmsScreenWithCallback(), p_i241885_7_, () -> p_i241885_1_.x_607_J().r_715_M());
        this.n_3318_d = new g_457_d(this);
        this.B_1668_F();
        this.g_164_R();
        this.H_2857_Y().n_1700_B(p_i241885_1_.dtoRealmsServerAddress());
        this.C_2741_M = this.r_715_M().n_1700_B(() -> new U_2866_z(this), U_2866_z.n_1700_B(this.G_624_v()));
        if (!p_i241885_1_.T_2506_i()) {
            p_i241885_4_.n_1700_B(p_i241885_1_.Q_4569_t());
        }
        this.z_4693_k = new J_3017_d(this, p_i241885_1_.c_132_F().e_4240_b());
        this.q_4610_l = this.G_624_v().M_588_G() ? new C_990_G(this, p_i241885_1_.c_132_F().e_4240_b().n_1700_B(), p_i241885_1_.c_132_F().x_607_J()) : null;
    }

    public void n_1700_B(int p_241113_1_, int p_241113_2_, boolean p_241113_3_, boolean p_241113_4_) {
        this.t_4043_B.G_564_y(p_241113_1_);
        this.t_4043_B.u_1723_Y(p_241113_2_);
        this.t_4043_B.P_1922_E(p_241113_2_);
        this.t_4043_B.n_1700_B(p_241113_3_);
        this.t_4043_B.J_1907_R(p_241113_4_);
    }

    @Override
    public k_594_Q R_4764_Y(int x, int y, int z) {
        return this.Y_259_p().t_148_a().G_564_y().G_564_y(x, y, z);
    }

    public J_3017_d R_4764_Y() {
        return this.z_4693_k;
    }

    public void n_1700_B(BooleanSupplier hasTimeLeft) {
        boolean flag3;
        ProfilerFiller iprofiler = this.D_4792_h();
        this.G_624_v = true;
        iprofiler.n_1700_B("world border");
        this.H_2857_Y().w_1457_N();
        iprofiler.J_1907_R("weather");
        boolean flag = this.c_4037_x();
        if (this.G_624_v().J_1907_R()) {
            if (this.H_1990_U().J_1907_R(A_2352_Z.Y_601_j)) {
                int i = this.t_4043_B.h_1847_R();
                int j = this.t_4043_B.Q_4569_t();
                int k = this.t_4043_B.M_182_A();
                boolean flag1 = this.Y_601_j.t_148_a();
                boolean flag2 = this.Y_601_j.v_4262_N();
                if (i > 0) {
                    --i;
                    j = flag1 ? 0 : 1;
                    k = flag2 ? 0 : 1;
                    flag1 = false;
                    flag2 = false;
                } else {
                    if (j > 0) {
                        if (--j == 0) {
                            flag1 = !flag1;
                        }
                    } else {
                        j = flag1 ? this.w_1457_N.nextInt(12000) + 3600 : this.w_1457_N.nextInt(168000) + 12000;
                    }
                    if (k > 0) {
                        if (--k == 0) {
                            flag2 = !flag2;
                        }
                    } else {
                        k = flag2 ? this.w_1457_N.nextInt(12000) + 12000 : this.w_1457_N.nextInt(168000) + 12000;
                    }
                }
                this.t_4043_B.P_1922_E(j);
                this.t_4043_B.u_1723_Y(k);
                this.t_4043_B.G_564_y(i);
                this.t_4043_B.J_1907_R(flag1);
                this.t_4043_B.n_1700_B(flag2);
            }
            this.t_1786_h = this.multiplayerClientSuggestionProvider;
            this.multiplayerClientSuggestionProvider = this.Y_601_j.t_148_a() ? (float)((double)this.multiplayerClientSuggestionProvider + 0.01) : (float)((double)this.multiplayerClientSuggestionProvider - 0.01);
            this.multiplayerClientSuggestionProvider = u_530_F.n_1700_B(this.multiplayerClientSuggestionProvider, 0.0f, 1.0f);
            this.Q_4569_t = this.M_182_A;
            this.M_182_A = this.Y_601_j.v_4262_N() ? (float)((double)this.M_182_A + 0.01) : (float)((double)this.M_182_A - 0.01);
            this.M_182_A = u_530_F.n_1700_B(this.M_182_A, 0.0f, 1.0f);
        }
        if (this.Q_4569_t != this.M_182_A) {
            this.Y_1740_V.p_178_J().n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.w_1484_f, this.M_182_A), this.g_2268_R());
        }
        if (this.t_1786_h != this.multiplayerClientSuggestionProvider) {
            this.Y_1740_V.p_178_J().n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.t_148_a, this.multiplayerClientSuggestionProvider), this.g_2268_R());
        }
        if (flag != this.c_4037_x()) {
            if (flag) {
                this.Y_1740_V.p_178_J().n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.R_4764_Y, 0.0f));
            } else {
                this.Y_1740_V.p_178_J().n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.J_1907_R, 0.0f));
            }
            this.Y_1740_V.p_178_J().n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.w_1484_f, this.M_182_A));
            this.Y_1740_V.p_178_J().n_1700_B(new ClientboundGameEventPacket(ClientboundGameEventPacket.t_148_a, this.multiplayerClientSuggestionProvider));
        }
        if (this.x_607_J && this.H_2857_Y.stream().noneMatch(p_241132_0_ -> !p_241132_0_.d_2461_k() && !p_241132_0_.h_1640_b())) {
            this.x_607_J = false;
            if (this.H_1990_U().J_1907_R(A_2352_Z.s_956_w)) {
                long l = this.Y_601_j.u_1723_Y() + 24000L;
                this.n_1700_B(l - l % 24000L);
            }
            this.D_60_a();
            if (this.H_1990_U().J_1907_R(A_2352_Z.Y_601_j)) {
                this.k_3961_g();
            }
        }
        this.B_1668_F();
        this.G_564_y();
        iprofiler.J_1907_R("chunkSource");
        this.Y_259_p().n_1700_B(hasTimeLeft);
        iprofiler.J_1907_R("tickPending");
        if (!this.l_1233_K()) {
            this.d_2427_y.n_1700_B();
            this.z_1737_N.n_1700_B();
        }
        iprofiler.J_1907_R("raid");
        this.C_2741_M.n_1700_B();
        iprofiler.J_1907_R("blockEvents");
        this.h_4320_q();
        this.G_624_v = false;
        iprofiler.J_1907_R("entities");
        boolean bl = flag3 = !this.H_2857_Y.isEmpty() || !this.Ping().isEmpty();
        if (flag3) {
            this.t_148_a();
        }
        if (flag3 || this.e_4240_b++ < 300) {
            if (this.q_4610_l != null) {
                this.q_4610_l.J_1907_R();
            }
            this.J_1907_R = true;
            ObjectIterator objectiterator = this.q_2307_F.int2ObjectEntrySet().iterator();
            while (true) {
                if (!objectiterator.hasNext()) {
                    N_4263_v entity;
                    this.J_1907_R = false;
                    while ((entity = this.c_3005_b.poll()) != null) {
                        this.h_1847_R(entity);
                    }
                    this.g_221_o();
                    break;
                }
                Int2ObjectMap.Entry entry = (Int2ObjectMap.Entry)objectiterator.next();
                N_4263_v entity1 = (N_4263_v)entry.getValue();
                N_4263_v entity2 = entity1.l_3609_d();
                if (!this.Y_1740_V.N_2525_X() && (entity1 instanceof Animal || entity1 instanceof WaterAnimal)) {
                    entity1.Ops();
                }
                if (!this.Y_1740_V.c_4037_x() && entity1 instanceof r_1780_L) {
                    entity1.Ops();
                }
                iprofiler.n_1700_B("checkDespawn");
                if (!entity1.t_4219_U) {
                    entity1.a_178_J();
                }
                iprofiler.R_4764_Y();
                if (entity2 != null) {
                    if (!entity2.t_4219_U && entity2.Y_601_j(entity1)) continue;
                    entity1.A_3959_N();
                }
                iprofiler.n_1700_B("tick");
                if (!entity1.t_4219_U && !(entity1 instanceof EnderDragonPart)) {
                    this.n_1700_B(this::J_1907_R, entity1);
                }
                iprofiler.R_4764_Y();
                iprofiler.n_1700_B("remove");
                if (entity1.t_4219_U) {
                    this.Q_4569_t(entity1);
                    objectiterator.remove();
                    this.s_956_w(entity1);
                }
                iprofiler.R_4764_Y();
            }
        }
        iprofiler.R_4764_Y();
    }

    protected void G_564_y() {
        if (this.g_221_o) {
            long i = this.Y_601_j.P_1922_E() + 1L;
            this.t_4043_B.n_1700_B(i);
            this.t_4043_B.Y_259_p().n_1700_B(this.Y_1740_V, i);
            if (this.Y_601_j.s_956_w().J_1907_R(A_2352_Z.s_956_w)) {
                this.n_1700_B(this.Y_601_j.u_1723_Y() + 1L);
            }
        }
    }

    public void n_1700_B(long p_241114_1_) {
        this.t_4043_B.J_1907_R(p_241114_1_);
    }

    public void J_1907_R(boolean p_241123_1_, boolean p_241123_2_) {
        for (CustomSpawner ispecialspawner : this.T_2506_i) {
            ispecialspawner.n_1700_B(this, p_241123_1_, p_241123_2_);
        }
    }

    private void D_60_a() {
        this.H_2857_Y.stream().filter(r_4811_B::z_2372_L).collect(Collectors.toList()).forEach(p_241131_0_ -> p_241131_0_.n_1700_B(false, false));
    }

    public void n_1700_B(H_1748_a chunkIn, int randomTickSpeed) {
        c_1514_x blockpos;
        Y_1387_d chunkpos = chunkIn.getPos();
        boolean flag = this.c_4037_x();
        int i = chunkpos.J_1907_R();
        int j = chunkpos.R_4764_Y();
        ProfilerFiller iprofiler = this.D_4792_h();
        iprofiler.n_1700_B("thunder");
        if (flag && this.N_2525_X() && this.w_1457_N.nextInt(100000) == 0 && this.Q_2552_b(blockpos = this.k_2293_S(this.n_1700_B(i, 0, j, 15)))) {
            boolean flag1;
            DifficultyInstance difficultyinstance = this.J_1907_R(blockpos);
            boolean bl = flag1 = this.H_1990_U().J_1907_R(A_2352_Z.G_564_y) && this.w_1457_N.nextDouble() < (double)difficultyinstance.J_1907_R() * 0.01;
            if (flag1) {
                D_4381_C skeletonhorseentity = t_5_h.PlayerInfo.n_1700_B(this);
                skeletonhorseentity.w_1457_N(true);
                skeletonhorseentity.b_(0);
                skeletonhorseentity.J_1907_R(blockpos.getX(), blockpos.getY(), blockpos.getZ());
                this.a_(skeletonhorseentity);
            }
            LightningBolt lightningboltentity = t_5_h.z_4693_k.n_1700_B(this);
            lightningboltentity.P_1922_E(e_2866_D.R_4764_Y(blockpos));
            lightningboltentity.n_1700_B(flag1);
            this.a_(lightningboltentity);
        }
        iprofiler.J_1907_R("iceandsnow");
        if (this.w_1457_N.nextInt(16) == 0) {
            c_1514_x blockpos2 = this.n_1700_B(z_2963_s.n_1700_B.P_1922_E, this.n_1700_B(i, 0, j, 15));
            c_1514_x blockpos3 = blockpos2.down();
            k_594_Q biome = this.P_1922_E(blockpos2);
            if (biome.n_1700_B(this, blockpos3)) {
                this.J_1907_R(blockpos3, a_3742_W.O_1795_e.multiplayerClientSuggestionProvider());
            }
            if (flag && biome.J_1907_R(this, blockpos2)) {
                this.J_1907_R(blockpos2, a_3742_W.X_290_I.multiplayerClientSuggestionProvider());
            }
            if (flag && this.P_1922_E(blockpos3).R_4764_Y() == k_594_Q.P_1922_E.J_1907_R) {
                this.getBlockState(blockpos3).J_1907_R().R_4764_Y(this, blockpos3);
            }
        }
        iprofiler.J_1907_R("tickBlocks");
        if (randomTickSpeed > 0) {
            for (P_3550_Z chunksection : chunkIn.getSections()) {
                if (chunksection == H_1748_a.EMPTY_SECTION || !chunksection.G_564_y()) continue;
                int k = chunksection.v_4262_N();
                for (int l = 0; l < randomTickSpeed; ++l) {
                    FluidState fluidstate;
                    c_1514_x blockpos1 = this.n_1700_B(i, k, j, 15);
                    iprofiler.n_1700_B("randomTick");
                    K_4074_S blockstate = chunksection.n_1700_B(blockpos1.getX() - i, blockpos1.getY() - k, blockpos1.getZ() - j);
                    if (blockstate.h_1847_R()) {
                        blockstate.J_1907_R(this, blockpos1, this.w_1457_N);
                    }
                    if ((fluidstate = blockstate.P_4830_p()).u_1723_Y()) {
                        fluidstate.J_1907_R(this, blockpos1, this.w_1457_N);
                    }
                    iprofiler.R_4764_Y();
                }
            }
        }
        iprofiler.R_4764_Y();
    }

    protected c_1514_x k_2293_S(c_1514_x pos) {
        c_1514_x blockpos = this.n_1700_B(z_2963_s.n_1700_B.P_1922_E, pos);
        I_4817_s axisalignedbb = new I_4817_s(blockpos, new c_1514_x(blockpos.getX(), this.c_3005_b(), blockpos.getZ())).grow(3.0);
        List<r_4811_B> list = this.n_1700_B(r_4811_B.class, axisalignedbb, (? super T p_241115_1_) -> p_241115_1_ != null && p_241115_1_.RealmsLongRunningMcoTaskScreen() && this.canSeeSky(p_241115_1_.b_2312_j()));
        if (!list.isEmpty()) {
            return list.get(this.w_1457_N.nextInt(list.size())).b_2312_j();
        }
        if (blockpos.getY() == -1) {
            blockpos = blockpos.up(2);
        }
        return blockpos;
    }

    public boolean P_1922_E() {
        return this.G_624_v;
    }

    public void u_1723_Y() {
        this.x_607_J = false;
        if (!this.H_2857_Y.isEmpty()) {
            int i = 0;
            int j = 0;
            for (B_4088_l serverplayerentity : this.H_2857_Y) {
                if (serverplayerentity.d_2461_k()) {
                    ++i;
                    continue;
                }
                if (!serverplayerentity.z_2372_L()) continue;
                ++j;
            }
            this.x_607_J = j > 0 && j >= this.H_2857_Y.size() - i;
        }
    }

    public ServerScoreboard v_4262_N() {
        return this.Y_1740_V.S_4022_R();
    }

    private void k_3961_g() {
        this.t_4043_B.u_1723_Y(0);
        this.t_4043_B.n_1700_B(false);
        this.t_4043_B.P_1922_E(0);
        this.t_4043_B.J_1907_R(false);
    }

    public void t_148_a() {
        this.e_4240_b = 0;
    }

    private void n_1700_B(Q_2410_O<Fluid> fluidTickEntry) {
        FluidState fluidstate = this.getFluidState(fluidTickEntry.n_1700_B);
        if (fluidstate.n_1700_B() == fluidTickEntry.J_1907_R()) {
            fluidstate.n_1700_B(this, fluidTickEntry.n_1700_B);
        }
    }

    private void J_1907_R(Q_2410_O<T_2915_h> blockTickEntry) {
        K_4074_S blockstate = this.getBlockState(blockTickEntry.n_1700_B);
        if (blockstate.n_1700_B(blockTickEntry.J_1907_R())) {
            blockstate.n_1700_B(this, blockTickEntry.n_1700_B, this.w_1457_N);
        }
    }

    public void J_1907_R(N_4263_v entityIn) {
        if (!(entityIn instanceof a_3913_L) && !this.Y_259_p().n_1700_B(entityIn)) {
            this.R_4764_Y(entityIn);
        } else {
            entityIn.u_1723_Y(entityIn.O_3598_v(), entityIn.X_2960_b(), entityIn.l_2647_k());
            entityIn.j_276_v = entityIn.p_178_J;
            entityIn.UploadStatus = entityIn.f_4016_n;
            if (entityIn.y_1700_S) {
                ++entityIn.RealmsWorldResetDto;
                ProfilerFiller iprofiler = this.D_4792_h();
                iprofiler.n_1700_B(() -> V_3137_a.g_221_o.J_1907_R(entityIn.f_4016_n()).toString());
                iprofiler.R_4764_Y("tickNonPassenger");
                entityIn.v_();
                iprofiler.R_4764_Y();
            }
            this.R_4764_Y(entityIn);
            if (entityIn.y_1700_S) {
                for (N_4263_v entity : entityIn.o_3599_Z()) {
                    this.n_1700_B(entityIn, entity);
                }
            }
        }
    }

    public void n_1700_B(N_4263_v ridingEntity, N_4263_v passengerEntity) {
        if (!passengerEntity.t_4219_U && passengerEntity.l_3609_d() == ridingEntity) {
            if (passengerEntity instanceof a_3913_L || this.Y_259_p().n_1700_B(passengerEntity)) {
                passengerEntity.u_1723_Y(passengerEntity.O_3598_v(), passengerEntity.X_2960_b(), passengerEntity.l_2647_k());
                passengerEntity.j_276_v = passengerEntity.p_178_J;
                passengerEntity.UploadStatus = passengerEntity.f_4016_n;
                if (passengerEntity.y_1700_S) {
                    ++passengerEntity.RealmsWorldResetDto;
                    ProfilerFiller iprofiler = this.D_4792_h();
                    iprofiler.n_1700_B(() -> V_3137_a.g_221_o.J_1907_R(passengerEntity.f_4016_n()).toString());
                    iprofiler.R_4764_Y("tickPassenger");
                    passengerEntity.x_607_J();
                    iprofiler.R_4764_Y();
                }
                this.R_4764_Y(passengerEntity);
                if (passengerEntity.y_1700_S) {
                    for (N_4263_v entity : passengerEntity.o_3599_Z()) {
                        this.n_1700_B(passengerEntity, entity);
                    }
                }
            }
        } else {
            passengerEntity.A_3959_N();
        }
    }

    public void R_4764_Y(N_4263_v entityIn) {
        if (entityIn.H_1873_g()) {
            this.D_4792_h().n_1700_B("chunkCheck");
            int i = u_530_F.R_4764_Y(entityIn.O_3598_v() / 16.0);
            int j = u_530_F.R_4764_Y(entityIn.X_2960_b() / 16.0);
            int k = u_530_F.R_4764_Y(entityIn.l_2647_k() / 16.0);
            if (!entityIn.y_1700_S || entityIn.u_744_e != i || entityIn.RetryCallException != j || entityIn.r_3651_U != k) {
                if (entityIn.y_1700_S && this.R_4764_Y(entityIn.u_744_e, entityIn.r_3651_U)) {
                    this.u_1723_Y(entityIn.u_744_e, entityIn.r_3651_U).removeEntityAtIndex(entityIn, entityIn.RetryCallException);
                }
                if (!entityIn.o_1800_r() && !this.R_4764_Y(i, k)) {
                    if (entityIn.y_1700_S) {
                        k_2293_S.warn("Entity {} left loaded chunk area", (Object)entityIn);
                    }
                    entityIn.y_1700_S = false;
                } else {
                    this.u_1723_Y(i, k).addEntity(entityIn);
                }
            }
            this.D_4792_h().R_4764_Y();
        }
    }

    @Override
    public boolean n_1700_B(a_3913_L player, c_1514_x pos) {
        return !this.Y_1740_V.n_1700_B(this, pos, player) && this.H_2857_Y().n_1700_B(pos);
    }

    public void n_1700_B(@Nullable ProgressListener progress, boolean flush, boolean skipSave) {
        C_3615_s serverchunkprovider = this.Y_259_p();
        if (!skipSave) {
            if (progress != null) {
                progress.n_1700_B(new F_2904_S("menu.savingLevel"));
            }
            this.Ops();
            if (progress != null) {
                progress.R_4764_Y(new F_2904_S("menu.savingChunks"));
            }
            serverchunkprovider.n_1700_B(flush);
        }
    }

    private void Ops() {
        if (this.q_4610_l != null) {
            this.Y_1740_V.c_132_F().J_1907_R(this.q_4610_l.n_1700_B());
        }
        this.Y_259_p().u_2550_I().n_1700_B();
    }

    public List<N_4263_v> n_1700_B(@Nullable t_5_h<?> entityTypeIn, Predicate<? super N_4263_v> predicateIn) {
        ArrayList list = Lists.newArrayList();
        C_3615_s serverchunkprovider = this.Y_259_p();
        for (N_4263_v entity : this.q_2307_F.values()) {
            if (entityTypeIn != null && entity.f_4016_n() != entityTypeIn || !serverchunkprovider.P_1922_E(u_530_F.R_4764_Y(entity.O_3598_v()) >> 4, u_530_F.R_4764_Y(entity.l_2647_k()) >> 4) || !predicateIn.test(entity)) continue;
            list.add(entity);
        }
        return list;
    }

    public List<b_2971_b> P_4830_p() {
        ArrayList list = Lists.newArrayList();
        for (N_4263_v entity : this.q_2307_F.values()) {
            if (!(entity instanceof b_2971_b) || !entity.RealmsLongRunningMcoTaskScreen()) continue;
            list.add((b_2971_b)entity);
        }
        return list;
    }

    public List<B_4088_l> n_1700_B(Predicate<? super B_4088_l> predicateIn) {
        ArrayList list = Lists.newArrayList();
        for (B_4088_l serverplayerentity : this.H_2857_Y) {
            if (!predicateIn.test(serverplayerentity)) continue;
            list.add(serverplayerentity);
        }
        return list;
    }

    @Nullable
    public B_4088_l Y_601_j() {
        List<B_4088_l> list = this.n_1700_B(r_4811_B::RealmsLongRunningMcoTaskScreen);
        return list.isEmpty() ? null : list.get(this.w_1457_N.nextInt(list.size()));
    }

    @Override
    public boolean a_(N_4263_v entityIn) {
        return this.M_588_G(entityIn);
    }

    public boolean G_564_y(N_4263_v entityIn) {
        return this.M_588_G(entityIn);
    }

    public void v_4262_N(N_4263_v entityIn) {
        boolean flag = entityIn.z_1333_t;
        entityIn.z_1333_t = true;
        this.G_564_y(entityIn);
        entityIn.z_1333_t = flag;
        this.R_4764_Y(entityIn);
    }

    public void n_1700_B(B_4088_l playerIn) {
        this.u_1723_Y(playerIn);
        this.R_4764_Y((N_4263_v)playerIn);
    }

    public void J_1907_R(B_4088_l playerIn) {
        this.u_1723_Y(playerIn);
        this.R_4764_Y((N_4263_v)playerIn);
    }

    public void R_4764_Y(B_4088_l player) {
        this.u_1723_Y(player);
    }

    public void G_564_y(B_4088_l player) {
        this.u_1723_Y(player);
    }

    private void u_1723_Y(B_4088_l player) {
        N_4263_v entity = this.Z_875_P.get(player.w_2705_t());
        if (entity != null) {
            k_2293_S.warn("Force-added player with duplicate UUID {}", (Object)player.w_2705_t().toString());
            entity.Ping();
            this.P_1922_E((B_4088_l)entity);
        }
        this.H_2857_Y.add(player);
        this.u_1723_Y();
        ChunkAccess ichunk = this.n_1700_B(u_530_F.R_4764_Y(player.O_3598_v() / 16.0), u_530_F.R_4764_Y(player.l_2647_k() / 16.0), ChunkStatus.P_4830_p, true);
        if (ichunk instanceof H_1748_a) {
            ichunk.addEntity(player);
        }
        this.h_1847_R(player);
    }

    private boolean M_588_G(N_4263_v entityIn) {
        if (entityIn.t_4219_U) {
            k_2293_S.warn("Tried to add entity {} but it was marked as removed already", (Object)t_5_h.n_1700_B(entityIn.f_4016_n()));
            return false;
        }
        if (this.P_4830_p(entityIn)) {
            return false;
        }
        ChunkAccess ichunk = this.n_1700_B(u_530_F.R_4764_Y(entityIn.O_3598_v() / 16.0), u_530_F.R_4764_Y(entityIn.l_2647_k() / 16.0), ChunkStatus.P_4830_p, entityIn.z_1333_t);
        if (!(ichunk instanceof H_1748_a)) {
            return false;
        }
        ichunk.addEntity(entityIn);
        this.h_1847_R(entityIn);
        return true;
    }

    public boolean w_1484_f(N_4263_v entityIn) {
        if (this.P_4830_p(entityIn)) {
            return false;
        }
        this.h_1847_R(entityIn);
        return true;
    }

    private boolean P_4830_p(N_4263_v entityIn) {
        UUID uuid = entityIn.w_2705_t();
        N_4263_v entity = this.R_4764_Y(uuid);
        if (entity == null) {
            return false;
        }
        k_2293_S.warn("Trying to add entity with duplicated UUID {}. Existing {}#{}, new: {}#{}", (Object)uuid, (Object)t_5_h.n_1700_B(entity.f_4016_n()), (Object)entity.j_276_v(), (Object)t_5_h.n_1700_B(entityIn.f_4016_n()), (Object)entityIn.j_276_v());
        return true;
    }

    @Nullable
    private N_4263_v R_4764_Y(UUID p_242105_1_) {
        N_4263_v entity = this.Z_875_P.get(p_242105_1_);
        if (entity != null) {
            return entity;
        }
        if (this.J_1907_R) {
            for (N_4263_v entity1 : this.c_3005_b) {
                if (!entity1.w_2705_t().equals(p_242105_1_)) continue;
                return entity1;
            }
        }
        return null;
    }

    public boolean t_148_a(N_4263_v p_242106_1_) {
        if (p_242106_1_.O_1795_e().anyMatch(this::P_4830_p)) {
            return false;
        }
        this.n_1700_B(p_242106_1_);
        return true;
    }

    public void n_1700_B(H_1748_a chunkIn) {
        this.M_588_G.addAll(chunkIn.getTileEntityMap().values());
        e_1322_b<N_4263_v>[] aclassinheritancemultimap = chunkIn.getEntityLists();
        int i = aclassinheritancemultimap.length;
        for (int j = 0; j < i; ++j) {
            for (N_4263_v entity : aclassinheritancemultimap[j]) {
                if (entity instanceof B_4088_l) continue;
                if (this.J_1907_R) {
                    throw j_3341_s.R_4764_Y(new IllegalStateException("Removing entity while ticking!"));
                }
                this.q_2307_F.remove(entity.j_276_v());
                this.s_956_w(entity);
            }
        }
    }

    public void s_956_w(N_4263_v entityIn) {
        if (entityIn instanceof b_2971_b) {
            for (EnderDragonPart enderdragonpartentity : ((b_2971_b)entityIn).Q_4569_t()) {
                enderdragonpartentity.Ops();
            }
        }
        this.Z_875_P.remove(entityIn.w_2705_t());
        this.Y_259_p().J_1907_R(entityIn);
        if (entityIn instanceof B_4088_l) {
            B_4088_l serverplayerentity = (B_4088_l)entityIn;
            this.H_2857_Y.remove(serverplayerentity);
        }
        this.v_4262_N().n_1700_B(entityIn);
        if (entityIn instanceof Z_530_i) {
            this.v_4276_D.remove(((Z_530_i)entityIn).e_4240_b());
        }
    }

    private void h_1847_R(N_4263_v entityIn) {
        if (this.J_1907_R) {
            this.c_3005_b.add(entityIn);
        } else {
            this.q_2307_F.put(entityIn.j_276_v(), (Object)entityIn);
            if (entityIn instanceof b_2971_b) {
                for (EnderDragonPart enderdragonpartentity : ((b_2971_b)entityIn).Q_4569_t()) {
                    this.q_2307_F.put(enderdragonpartentity.j_276_v(), (Object)enderdragonpartentity);
                }
            }
            this.Z_875_P.put(entityIn.w_2705_t(), entityIn);
            this.Y_259_p().R_4764_Y(entityIn);
            if (entityIn instanceof Z_530_i) {
                this.v_4276_D.add(((Z_530_i)entityIn).e_4240_b());
            }
        }
    }

    public void u_2550_I(N_4263_v entityIn) {
        if (this.J_1907_R) {
            throw j_3341_s.R_4764_Y(new IllegalStateException("Removing entity while ticking!"));
        }
        this.Q_4569_t(entityIn);
        this.q_2307_F.remove(entityIn.j_276_v());
        this.s_956_w(entityIn);
    }

    private void Q_4569_t(N_4263_v entityIn) {
        ChunkAccess ichunk = this.n_1700_B(entityIn.u_744_e, entityIn.r_3651_U, ChunkStatus.P_4830_p, false);
        if (ichunk instanceof H_1748_a) {
            ((H_1748_a)ichunk).removeEntity(entityIn);
        }
    }

    public void P_1922_E(B_4088_l player) {
        player.Ops();
        this.u_2550_I(player);
        this.u_1723_Y();
    }

    @Override
    public void n_1700_B(int breakerId, c_1514_x pos, int progress) {
        for (B_4088_l serverplayerentity : this.Y_1740_V.p_178_J().w_1457_N()) {
            double d2;
            double d1;
            double d0;
            if (serverplayerentity == null || serverplayerentity.O_508_d != this || serverplayerentity.j_276_v() == breakerId || !((d0 = (double)pos.getX() - serverplayerentity.O_3598_v()) * d0 + (d1 = (double)pos.getY() - serverplayerentity.X_2960_b()) * d1 + (d2 = (double)pos.getZ() - serverplayerentity.l_2647_k()) * d2 < 1024.0)) continue;
            serverplayerentity.n_1700_B.n_1700_B(new t_4503_H(breakerId, pos, progress));
        }
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player, double x, double y, double z, SoundEvent soundIn, D_38_f category, float volume, float pitch) {
        this.Y_1740_V.p_178_J().n_1700_B(player, x, y, z, volume > 1.0f ? (double)(16.0f * volume) : 16.0, this.g_2268_R(), new ClientboundSoundPacket(soundIn, category, x, y, z, volume, pitch));
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L playerIn, N_4263_v entityIn, SoundEvent eventIn, D_38_f categoryIn, float volume, float pitch) {
        this.Y_1740_V.p_178_J().n_1700_B(playerIn, entityIn.O_3598_v(), entityIn.X_2960_b(), entityIn.l_2647_k(), volume > 1.0f ? (double)(16.0f * volume) : 16.0, this.g_2268_R(), new ClientboundSoundEntityPacket(eventIn, categoryIn, entityIn, volume, pitch));
    }

    @Override
    public void J_1907_R(int id, c_1514_x pos, int data) {
        this.Y_1740_V.p_178_J().n_1700_B(new ClientboundLevelEventPacket(id, pos, data, true));
    }

    @Override
    public void n_1700_B(@Nullable a_3913_L player, int type, c_1514_x pos, int data) {
        this.Y_1740_V.p_178_J().n_1700_B(player, pos.getX(), pos.getY(), pos.getZ(), 64.0, this.g_2268_R(), new ClientboundLevelEventPacket(type, pos, data, false));
    }

    @Override
    public void n_1700_B(c_1514_x pos, K_4074_S oldState, K_4074_S newState, int flags) {
        this.Y_259_p().J_1907_R(pos);
        s_1395_c voxelshape = oldState.u_2550_I(this, pos);
        s_1395_c voxelshape1 = newState.u_2550_I(this, pos);
        if (x_268_Y.R_4764_Y(voxelshape, voxelshape1, BooleanOp.v_4262_N)) {
            for (PathNavigation pathnavigator : this.v_4276_D) {
                if (pathnavigator.w_1484_f()) continue;
                pathnavigator.J_1907_R(pos);
            }
        }
    }

    @Override
    public void n_1700_B(N_4263_v entityIn, byte state) {
        this.Y_259_p().n_1700_B(entityIn, new C_1375_J(entityIn, state));
    }

    public C_3615_s Y_259_p() {
        return this.A_4115_X;
    }

    @Override
    public F_1241_B n_1700_B(@Nullable N_4263_v exploder, @Nullable P_11_z damageSource, @Nullable ExplosionDamageCalculator context, double x, double y, double z, float size, boolean causesFire, F_1241_B.n_1700_B mode) {
        F_1241_B explosion = new F_1241_B(this, exploder, damageSource, context, x, y, z, size, causesFire, mode);
        explosion.n_1700_B();
        explosion.n_1700_B(false);
        if (mode == F_1241_B.n_1700_B.n_1700_B) {
            explosion.P_1922_E();
        }
        for (B_4088_l serverplayerentity : this.H_2857_Y) {
            if (!(serverplayerentity.v_4262_N(x, y, z) < 4096.0)) continue;
            serverplayerentity.n_1700_B.n_1700_B(new ClientboundExplodePacket(x, y, z, size, explosion.u_1723_Y(), explosion.R_4764_Y().get(serverplayerentity)));
        }
        return explosion;
    }

    @Override
    public void n_1700_B(c_1514_x pos, T_2915_h blockIn, int eventID, int eventParam) {
        this.d_2461_k.add((Object)new P_2976_u(pos, blockIn, eventID, eventParam));
    }

    private void h_4320_q() {
        while (!this.d_2461_k.isEmpty()) {
            P_2976_u blockeventdata = (P_2976_u)this.d_2461_k.removeFirst();
            if (!this.n_1700_B(blockeventdata)) continue;
            this.Y_1740_V.p_178_J().n_1700_B(null, blockeventdata.n_1700_B().getX(), blockeventdata.n_1700_B().getY(), blockeventdata.n_1700_B().getZ(), 64.0, this.g_2268_R(), new ClientboundBlockEventPacket(blockeventdata.n_1700_B(), blockeventdata.J_1907_R(), blockeventdata.R_4764_Y(), blockeventdata.G_564_y()));
        }
    }

    private boolean n_1700_B(P_2976_u event) {
        K_4074_S blockstate = this.getBlockState(event.n_1700_B());
        return blockstate.n_1700_B(event.J_1907_R()) ? blockstate.n_1700_B((b_4507_u)this, event.n_1700_B(), event.R_4764_Y(), event.G_564_y()) : false;
    }

    public Y_3104_y<T_2915_h> Q_2552_b() {
        return this.d_2427_y;
    }

    public Y_3104_y<Fluid> C_2741_M() {
        return this.z_1737_N;
    }

    @Override
    @Nonnull
    public G_564_y T_2506_i() {
        return this.Y_1740_V;
    }

    public g_457_d z_1333_t() {
        return this.n_3318_d;
    }

    public b_2085_h O_508_d() {
        return this.Y_1740_V.M_2677_i();
    }

    public <T extends ParticleOptions> int n_1700_B(T type, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed) {
        ClientboundLevelParticlesPacket sspawnparticlepacket = new ClientboundLevelParticlesPacket(type, false, posX, posY, posZ, (float)xOffset, (float)yOffset, (float)zOffset, (float)speed, particleCount);
        int i = 0;
        for (int j = 0; j < this.H_2857_Y.size(); ++j) {
            B_4088_l serverplayerentity = this.H_2857_Y.get(j);
            if (!this.n_1700_B(serverplayerentity, false, posX, posY, posZ, sspawnparticlepacket)) continue;
            ++i;
        }
        return i;
    }

    public <T extends ParticleOptions> boolean n_1700_B(B_4088_l player, T type, boolean longDistance, double posX, double posY, double posZ, int particleCount, double xOffset, double yOffset, double zOffset, double speed) {
        ClientboundLevelParticlesPacket ipacket = new ClientboundLevelParticlesPacket(type, longDistance, posX, posY, posZ, (float)xOffset, (float)yOffset, (float)zOffset, (float)speed, particleCount);
        return this.n_1700_B(player, longDistance, posX, posY, posZ, ipacket);
    }

    private boolean n_1700_B(B_4088_l player, boolean longDistance, double posX, double posY, double posZ, Packet<?> packet) {
        if (player.c_3005_b() != this) {
            return false;
        }
        c_1514_x blockpos = player.b_2312_j();
        if (blockpos.withinDistance(new e_2866_D(posX, posY, posZ), longDistance ? 512.0 : 32.0)) {
            player.n_1700_B.n_1700_B(packet);
            return true;
        }
        return false;
    }

    @Override
    @Nullable
    public N_4263_v J_1907_R(int id) {
        return (N_4263_v)this.q_2307_F.get(id);
    }

    @Nullable
    public N_4263_v J_1907_R(UUID uniqueId) {
        return this.Z_875_P.get(uniqueId);
    }

    @Nullable
    public c_1514_x n_1700_B(StructureFeature<?> p_241117_1_, c_1514_x p_241117_2_, int p_241117_3_, boolean p_241117_4_) {
        return !this.Y_1740_V.c_132_F().e_4240_b().J_1907_R() ? null : this.Y_259_p().t_148_a().n_1700_B(this, p_241117_1_, p_241117_2_, p_241117_3_, p_241117_4_);
    }

    @Nullable
    public c_1514_x n_1700_B(k_594_Q p_241116_1_, c_1514_x p_241116_2_, int p_241116_3_, int p_241116_4_) {
        return this.Y_259_p().t_148_a().G_564_y().n_1700_B(p_241116_2_.getX(), p_241116_2_.getY(), p_241116_2_.getZ(), p_241116_3_, p_241116_4_, (k_594_Q p_242102_1_) -> p_242102_1_ == p_241116_1_, this.w_1457_N, true);
    }

    @Override
    public G_3474_H s_956_w() {
        return this.Y_1740_V.ValueObject();
    }

    @Override
    public TagContainer M_182_A() {
        return this.Y_1740_V.F_1410_V();
    }

    @Override
    public boolean T_3594_S() {
        return this.R_4764_Y;
    }

    @Override
    public r_4097_j t_1786_h() {
        return this.Y_1740_V.g_4106_L();
    }

    public s_4380_l r_715_M() {
        return this.Y_259_p().u_2550_I();
    }

    @Override
    @Nullable
    public F_3620_e n_1700_B(String mapName) {
        return this.T_2506_i().x_607_J().r_715_M().J_1907_R(() -> new F_3620_e(mapName), mapName);
    }

    @Override
    public void n_1700_B(F_3620_e mapDataIn) {
        this.T_2506_i().x_607_J().r_715_M().n_1700_B(mapDataIn);
    }

    @Override
    public int h_1847_R() {
        return this.T_2506_i().x_607_J().r_715_M().n_1700_B(MapIndex::new, "idcounts").n_1700_B();
    }

    public void n_1700_B(c_1514_x p_241124_1_, float p_241124_2_) {
        Y_1387_d chunkpos = new Y_1387_d(new c_1514_x(this.Y_601_j.J_1907_R(), 0, this.Y_601_j.G_564_y()));
        this.Y_601_j.n_1700_B(p_241124_1_, p_241124_2_);
        this.Y_259_p().J_1907_R(TicketType.n_1700_B, chunkpos, 11, X_1446_C.n_1700_B);
        this.Y_259_p().n_1700_B(TicketType.n_1700_B, new Y_1387_d(p_241124_1_), 11, X_1446_C.n_1700_B);
        this.T_2506_i().p_178_J().n_1700_B(new ClientboundSetDefaultSpawnPositionPacket(p_241124_1_, p_241124_2_));
    }

    public c_1514_x A_1038_p() {
        c_1514_x blockpos = new c_1514_x(this.Y_601_j.J_1907_R(), this.Y_601_j.R_4764_Y(), this.Y_601_j.G_564_y());
        if (!this.H_2857_Y().n_1700_B(blockpos)) {
            blockpos = this.n_1700_B(z_2963_s.n_1700_B.P_1922_E, new c_1514_x(this.H_2857_Y().n_1700_B(), 0.0, this.H_2857_Y().J_1907_R()));
        }
        return blockpos;
    }

    public float i_1637_u() {
        return this.Y_601_j.w_1484_f();
    }

    public LongSet Ping() {
        ForcedChunksSavedData forcedchunkssavedata = this.r_715_M().J_1907_R(ForcedChunksSavedData::new, "chunks");
        return forcedchunkssavedata != null ? LongSets.unmodifiable((LongSet)forcedchunkssavedata.n_1700_B()) : LongSets.EMPTY_SET;
    }

    public boolean n_1700_B(int chunkX, int chunkZ, boolean add) {
        boolean flag;
        ForcedChunksSavedData forcedchunkssavedata = this.r_715_M().n_1700_B(ForcedChunksSavedData::new, "chunks");
        Y_1387_d chunkpos = new Y_1387_d(chunkX, chunkZ);
        long i = chunkpos.n_1700_B();
        if (add) {
            flag = forcedchunkssavedata.n_1700_B().add(i);
            if (flag) {
                this.u_1723_Y(chunkX, chunkZ);
            }
        } else {
            flag = forcedchunkssavedata.n_1700_B().remove(i);
        }
        forcedchunkssavedata.n_1700_B(flag);
        if (flag) {
            this.Y_259_p().n_1700_B(chunkpos, add);
        }
        return flag;
    }

    public List<B_4088_l> multiplayerClientSuggestionProvider() {
        return this.H_2857_Y;
    }

    @Override
    public void J_1907_R(c_1514_x pos, K_4074_S blockStateIn, K_4074_S newState) {
        Optional<q_2232_A> optional1;
        Optional<q_2232_A> optional = q_2232_A.n_1700_B(blockStateIn);
        if (!Objects.equals(optional, optional1 = q_2232_A.n_1700_B(newState))) {
            c_1514_x blockpos = pos.toImmutable();
            optional.ifPresent(p_241130_2_ -> this.T_2506_i().execute(() -> {
                this.p_178_J().n_1700_B(blockpos);
                DebugPackets.J_1907_R(this, blockpos);
            }));
            optional1.ifPresent(p_217476_2_ -> this.T_2506_i().execute(() -> {
                this.p_178_J().n_1700_B(blockpos, (q_2232_A)p_217476_2_);
                DebugPackets.n_1700_B(this, blockpos);
            }));
        }
    }

    public b_4946_z p_178_J() {
        return this.Y_259_p().M_588_G();
    }

    public boolean q_2307_F(c_1514_x pos) {
        return this.R_4764_Y(pos, 1);
    }

    public boolean n_1700_B(SectionPos pos) {
        return this.q_2307_F(pos.u_2550_I());
    }

    public boolean R_4764_Y(c_1514_x p_241119_1_, int p_241119_2_) {
        if (p_241119_2_ > 6) {
            return false;
        }
        return this.J_1907_R(SectionPos.n_1700_B(p_241119_1_)) <= p_241119_2_;
    }

    public int J_1907_R(SectionPos pos) {
        return this.p_178_J().n_1700_B(pos);
    }

    public U_2866_z RealmsClientConfig() {
        return this.C_2741_M;
    }

    @Nullable
    public b_3129_s Z_875_P(c_1514_x pos) {
        return this.C_2741_M.n_1700_B(pos, 9216);
    }

    public boolean c_3005_b(c_1514_x pos) {
        return this.Z_875_P(pos) != null;
    }

    public void n_1700_B(ReputationEventType type, N_4263_v target, D_4533_B host) {
        host.n_1700_B(type, target);
    }

    public void n_1700_B(Path pathIn) throws IOException {
        y_1195_s chunkmanager = this.Y_259_p().n_1700_B;
        try (BufferedWriter writer = Files.newBufferedWriter(pathIn.resolve("stats.txt"), new OpenOption[0]);){
            writer.write(String.format("spawning_chunks: %d\n", chunkmanager.u_1723_Y().J_1907_R()));
            u_743_i.n_1700_B worldentityspawner$entitydensitymanager = this.Y_259_p().P_4830_p();
            if (worldentityspawner$entitydensitymanager != null) {
                for (Object2IntMap.Entry entry : worldentityspawner$entitydensitymanager.J_1907_R().object2IntEntrySet()) {
                    writer.write(String.format("spawn_count.%s: %d\n", ((Z_749_F)entry.getKey()).J_1907_R(), entry.getIntValue()));
                }
            }
            writer.write(String.format("entities: %d\n", this.q_2307_F.size()));
            writer.write(String.format("block_entities: %d\n", this.t_148_a.size()));
            writer.write(String.format("block_ticks: %d\n", this.Q_2552_b().J_1907_R()));
            writer.write(String.format("fluid_ticks: %d\n", this.C_2741_M().J_1907_R()));
            writer.write("distance_manager: " + chunkmanager.u_1723_Y().R_4764_Y() + "\n");
            writer.write(String.format("pending_tasks: %d\n", this.Y_259_p().w_1484_f()));
        }
        n_3236_c crashreport = new n_3236_c("Level dump", new Exception("dummy"));
        this.n_1700_B(crashreport);
        try (BufferedWriter writer1 = Files.newBufferedWriter(pathIn.resolve("example_crash.txt"), new OpenOption[0]);){
            writer1.write(crashreport.G_564_y());
        }
        Path path = pathIn.resolve("chunks.csv");
        try (BufferedWriter writer2 = Files.newBufferedWriter(path, new OpenOption[0]);){
            chunkmanager.n_1700_B(writer2);
        }
        Path path1 = pathIn.resolve("entities.csv");
        try (BufferedWriter writer3 = Files.newBufferedWriter(path1, new OpenOption[0]);){
            e_3591_l.n_1700_B(writer3, (Iterable<N_4263_v>)this.q_2307_F.values());
        }
        Path path2 = pathIn.resolve("block_entities.csv");
        try (BufferedWriter writer4 = Files.newBufferedWriter(path2, new OpenOption[0]);){
            this.n_1700_B(writer4);
        }
    }

    private static void n_1700_B(Writer writerIn, Iterable<N_4263_v> entities) throws IOException {
        O_1400_s csvwriter = O_1400_s.n_1700_B().n_1700_B("x").n_1700_B("y").n_1700_B("z").n_1700_B("uuid").n_1700_B("type").n_1700_B("alive").n_1700_B("display_name").n_1700_B("custom_name").n_1700_B(writerIn);
        for (N_4263_v entity : entities) {
            x_282_a itextcomponent = entity.k_2302_P();
            x_282_a itextcomponent1 = entity.c_();
            csvwriter.n_1700_B(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), entity.w_2705_t(), V_3137_a.g_221_o.J_1907_R(entity.f_4016_n()), entity.RealmsLongRunningMcoTaskScreen(), itextcomponent1.getString(), itextcomponent != null ? itextcomponent.getString() : null);
        }
    }

    private void n_1700_B(Writer writerIn) throws IOException {
        O_1400_s csvwriter = O_1400_s.n_1700_B().n_1700_B("x").n_1700_B("y").n_1700_B("z").n_1700_B("type").n_1700_B(writerIn);
        for (i_2154_H tileentity : this.t_148_a) {
            c_1514_x blockpos = tileentity.x_607_J();
            csvwriter.n_1700_B(blockpos.getX(), blockpos.getY(), blockpos.getZ(), V_3137_a.X_933_l.J_1907_R(tileentity.z_1737_N()));
        }
    }

    @VisibleForTesting
    public void n_1700_B(BoundingBox boundingBox) {
        this.d_2461_k.removeIf(p_241118_1_ -> boundingBox.J_1907_R(p_241118_1_.n_1700_B()));
    }

    @Override
    public void n_1700_B(c_1514_x p_230547_1_, T_2915_h p_230547_2_) {
        if (!this.l_1233_K()) {
            this.J_1907_R(p_230547_1_, p_230547_2_);
        }
    }

    @Override
    public float func_230487_a_(b_257_Y p_230487_1_, boolean p_230487_2_) {
        return 1.0f;
    }

    public Iterable<N_4263_v> f_4016_n() {
        return Iterables.unmodifiableIterable((Iterable)this.q_2307_F.values());
    }

    public String toString() {
        return "ServerLevel[" + this.t_4043_B.P_4830_p() + "]";
    }

    public boolean j_276_v() {
        return this.Y_1740_V.c_132_F().e_4240_b().w_1484_f();
    }

    @Override
    public long n_1700_B() {
        return this.Y_1740_V.c_132_F().e_4240_b().n_1700_B();
    }

    @Nullable
    public C_990_G UploadStatus() {
        return this.q_4610_l;
    }

    @Override
    public Stream<? extends StructureStart<?>> n_1700_B(SectionPos p_241827_1_, StructureFeature<?> p_241827_2_) {
        return this.R_4764_Y().n_1700_B(p_241827_1_, p_241827_2_);
    }

    @Override
    public e_3591_l J_1907_R() {
        return this;
    }

    @VisibleForTesting
    public String e_1992_r() {
        return String.format("players: %s, entities: %d [%s], block_entities: %d [%s], block_ticks: %d, fluid_ticks: %d, chunk_source: %s", this.H_2857_Y.size(), this.q_2307_F.size(), e_3591_l.n_1700_B(this.q_2307_F.values(), (T p_244527_0_) -> V_3137_a.g_221_o.J_1907_R(p_244527_0_.f_4016_n())), this.s_956_w.size(), e_3591_l.n_1700_B(this.s_956_w, (T p_244526_0_) -> V_3137_a.X_933_l.J_1907_R(p_244526_0_.z_1737_N())), this.Q_2552_b().J_1907_R(), this.C_2741_M().J_1907_R(), this.e_2887_G());
    }

    private static <T> String n_1700_B(Collection<T> p_244524_0_, Function<T, g_2336_b> p_244524_1_) {
        try {
            Object2IntOpenHashMap object2intopenhashmap = new Object2IntOpenHashMap();
            for (T t : p_244524_0_) {
                g_2336_b resourcelocation = p_244524_1_.apply(t);
                object2intopenhashmap.addTo((Object)resourcelocation, 1);
            }
            return object2intopenhashmap.object2IntEntrySet().stream().sorted(Comparator.comparing(Object2IntMap.Entry::getIntValue).reversed()).limit(5L).map(p_244523_0_ -> String.valueOf(p_244523_0_.getKey()) + ":" + p_244523_0_.getIntValue()).collect(Collectors.joining(","));
        }
        catch (Exception exception) {
            return "";
        }
    }

    public static void n_1700_B(e_3591_l p_241121_0_) {
        c_1514_x blockpos = n_1700_B;
        int i = blockpos.getX();
        int j = blockpos.getY() - 2;
        int k = blockpos.getZ();
        c_1514_x.getAllInBoxMutable(i - 2, j + 1, k - 2, i + 2, j + 3, k + 2).forEach(p_244430_1_ -> p_241121_0_.J_1907_R((c_1514_x)p_244430_1_, a_3742_W.n_1700_B.multiplayerClientSuggestionProvider()));
        c_1514_x.getAllInBoxMutable(i - 2, j, k - 2, i + 2, j, k + 2).forEach(p_241122_1_ -> p_241121_0_.J_1907_R((c_1514_x)p_241122_1_, a_3742_W.ClientBootstrap.multiplayerClientSuggestionProvider()));
    }

    @Override
    public /* synthetic */ i_4895_l Q_4569_t() {
        return this.v_4262_N();
    }

    @Override
    public /* synthetic */ ChunkSource q_2307_F() {
        return this.Y_259_p();
    }

    public /* synthetic */ TickList M_588_G() {
        return this.C_2741_M();
    }

    public /* synthetic */ TickList u_2550_I() {
        return this.Q_2552_b();
    }
}



