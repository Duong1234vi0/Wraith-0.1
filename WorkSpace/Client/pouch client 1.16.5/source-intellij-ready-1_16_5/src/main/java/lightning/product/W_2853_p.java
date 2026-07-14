/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Multimap
 *  com.google.common.collect.Sets
 *  com.mojang.authlib.GameProfile
 *  com.mojang.brigadier.CommandDispatcher
 *  com.viaversion.viaversion.api.protocol.version.ProtocolVersion
 *  io.netty.buffer.Unpooled
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Nullable;
import lightning.product.A_1557_z;
import lightning.product.A_2226_Q;
import lightning.product.A_2629_w;
import lightning.product.A_4115_X;
import lightning.product.A_4313_D;
import lightning.product.A_4388_s;
import lightning.product.A_4605_O;
import lightning.product.B_1132_Q;
import lightning.product.ClientboundContainerSetContentPacket;
import lightning.product.B_3091_S;
import lightning.product.AttributeMap;
import lightning.product.B_3790_C;
import lightning.product.LargeFireball;
import lightning.product.C_1375_J;
import lightning.product.TagContainer;
import lightning.product.D_1056_N;
import lightning.product.RecipeCollection;
import lightning.product.D_38_f;
import lightning.product.D_4704_b;
import lightning.product.D_686_b;
import lightning.product.ClientboundSetCameraPacket;
import lightning.product.E_1407_D;
import lightning.product.ClientboundSetObjectivePacket;
import lightning.product.E_3520_U;
import lightning.product.ClientboundBlockBreakAckPacket;
import lightning.product.E_4925_L;
import lightning.product.F_1241_B;
import lightning.product.F_2904_S;
import lightning.product.F_3620_e;
import lightning.product.F_551_J;
import lightning.product.F_666_T;
import lightning.product.F_997_G;
import lightning.product.ServerboundResourcePackPacket;
import lightning.product.G_1455_B;
import lightning.product.G_2460_P;
import lightning.product.G_2722_I;
import lightning.product.G_3165_y;
import lightning.product.G_3474_H;
import lightning.product.G_4919_s;
import lightning.product.H_1748_a;
import lightning.product.H_2543_D;
import lightning.product.Arrow;
import lightning.product.ThrownExperienceBottle;
import lightning.product.ClientboundChangeDifficultyPacket;
import lightning.product.I_14_v;
import lightning.product.LeashFenceKnotEntity;
import lightning.product.I_438_q;
import lightning.product.I_4656_k;
import lightning.product.ClientboundLoginPacket;
import lightning.product.J_3992_v;
import lightning.product.ClientboundGameEventPacket;
import lightning.product.ClientboundAddEntityPacket;
import lightning.product.Snowball;
import lightning.product.ClientboundCustomSoundPacket;
import lightning.product.K_3710_b;
import lightning.product.K_4074_S;
import lightning.product.K_4719_o;
import lightning.product.L_2837_o;
import lightning.product.L_4122_s;
import lightning.product.M_1462_J;
import lightning.product.BoundingBox;
import lightning.product.ClientboundTeleportEntityPacket;
import lightning.product.M_3508_C;
import lightning.product.ClientboundRespawnPacket;
import lightning.product.N_1216_z;
import lightning.product.ClientboundCommandSuggestionsPacket;
import lightning.product.ClientboundLightUpdatePacket;
import lightning.product.N_3268_u;
import lightning.product.N_3369_p;
import lightning.product.N_4263_v;
import lightning.product.N_4422_X;
import lightning.product.ClientboundPlayerAbilitiesPacket;
import lightning.product.ClientboundSetTitlesPacket;
import lightning.product.O_2639_P;
import lightning.product.ClientboundUpdateAdvancementsPacket;
import lightning.product.ClientboundBlockUpdatePacket;
import lightning.product.O_3036_q;
import lightning.product.Painting;
import lightning.product.O_4030_c;
import lightning.product.ClientboundTagQueryPacket;
import lightning.product.MinecartSpawner;
import lightning.product.O_922_L;
import lightning.product.GuardianAttackSoundInstance;
import lightning.product.SearchRegistry;
import lightning.product.StatsUpdateListener;
import lightning.product.ClientboundAddPaintingPacket;
import lightning.product.ClientboundSectionBlocksUpdatePacket;
import lightning.product.ClientboundAddMobPacket;
import lightning.product.ClientboundOpenScreenPacket;
import lightning.product.ClientboundStopSoundPacket;
import lightning.product.Q_1187_u;
import lightning.product.R_137_s;
import lightning.product.R_1900_x;
import lightning.product.R_2450_T;
import lightning.product.ClientboundUpdateAttributesPacket;
import lightning.product.R_3940_n;
import lightning.product.R_831_p;
import lightning.product.S_1134_u;
import lightning.product.DataLayer;
import lightning.product.ClientboundSetDefaultSpawnPositionPacket;
import lightning.product.S_3601_T;
import lightning.product.WitherSkull;
import lightning.product.T_1088_H;
import lightning.product.T_1368_k;
import lightning.product.T_2915_h;
import lightning.product.SimpleSoundInstance;
import lightning.product.U_157_Y;
import lightning.product.U_1880_G;
import lightning.product.U_2534_D;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.U_532_X;
import lightning.product.V_3137_a;
import lightning.product.V_3163_W;
import lightning.product.V_3354_l;
import lightning.product.V_4217_p;
import lightning.product.SoundEvents;
import lightning.product.V_4423_d;
import lightning.product.ServerData;
import lightning.product.V_674_I;
import lightning.product.V_772_m;
import lightning.product.W_1158_a;
import lightning.product.W_1247_f;
import lightning.product.ClientboundPlayerLookAtPacket;
import lightning.product.ClientboundSetHealthPacket;
import lightning.product.ClientboundAddPlayerPacket;
import lightning.product.W_3491_f;
import lightning.product.Objective;
import lightning.product.W_4148_E;
import lightning.product.W_4464_I;
import lightning.product.GoalSelectorDebugRenderer;
import lightning.product.ClientboundSoundPacket;
import lightning.product.X_508_u;
import lightning.product.X_776_r;
import lightning.product.X_821_u;
import lightning.product.Y_1387_d;
import lightning.product.Y_158_B;
import lightning.product.SoundInstance;
import lightning.product.ClientboundSetEquipmentPacket;
import lightning.product.Z_1993_T;
import lightning.product.RecipeUpdateListener;
import lightning.product.BeeSoundInstance;
import lightning.product.Z_3903_F;
import lightning.product.Z_390_O;
import lightning.product.ClientboundRemoveMobEffectPacket;
import lightning.product.ClientboundSelectAdvancementsTabPacket;
import lightning.product.ClientboundSetExperiencePacket;
import lightning.product.ClientboundMerchantOffersPacket;
import lightning.product.Z_530_i;
import lightning.product.ClientboundBlockEventPacket;
import lightning.product.a_2900_S;
import lightning.product.a_3913_L;
import lightning.product.NeighborsUpdateRenderer;
import lightning.product.a_4411_f;
import lightning.product.a_4762_y;
import lightning.product.a_4764_N;
import lightning.product.ClientboundPlayerPositionPacket;
import lightning.product.b_1722_e;
import lightning.product.b_1913_J;
import lightning.product.MinecraftAccess;
import lightning.product.b_257_Y;
import lightning.product.b_2585_i;
import lightning.product.b_2971_b;
import lightning.product.b_4507_u;
import lightning.product.ClientAdvancements;
import lightning.product.c_1070_s;
import lightning.product.c_1108_W;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.c_1633_k;
import lightning.product.PlayerTeam;
import lightning.product.ClientSuggestionProvider;
import lightning.product.SectionPos;
import lightning.product.ClientboundSetPassengersPacket;
import lightning.product.SpectralArrow;
import lightning.product.d_338_B;
import lightning.product.MinecartChest;
import lightning.product.e_1174_E;
import lightning.product.e_2866_D;
import lightning.product.e_446_u;
import lightning.product.e_869_m;
import lightning.product.f_2392_k;
import lightning.product.StatsCounter;
import lightning.product.f_691_R;
import lightning.product.g_1462_f;
import lightning.product.g_2336_b;
import lightning.product.ClientboundCustomPayloadPacket;
import lightning.product.g_422_i;
import lightning.product.RealmsScreen;
import lightning.product.ClientboundLevelParticlesPacket;
import lightning.product.ClientboundExplodePacket;
import lightning.product.h_3270_j;
import lightning.product.h_384_L;
import lightning.product.ClientboundAwardStatsPacket;
import lightning.product.MinecartHopper;
import lightning.product.i_1140_L;
import lightning.product.i_2154_H;
import lightning.product.ClientboundSetPlayerTeamPacket;
import lightning.product.Recipe;
import lightning.product.ClientboundAddExperienceOrbPacket;
import lightning.product.SpawnerBlockEntity;
import lightning.product.BedBlockEntity;
import lightning.product.i_4895_l;
import lightning.product.MerchantOffers;
import lightning.product.j_2644_e;
import lightning.product.WorldGenAttemptRenderer;
import lightning.product.MinecartFurnace;
import lightning.product.j_4436_c;
import lightning.product.ClientboundChatPacket;
import lightning.product.k_2603_m;
import lightning.product.k_2610_C;
import lightning.product.k_4690_i;
import lightning.product.k_596_g;
import lightning.product.ClientboundRecipePacket;
import lightning.product.m_1551_m;
import lightning.product.m_1761_s;
import lightning.product.m_2105_M;
import lightning.product.m_3545_A;
import lightning.product.ClientboundSetEntityMotionPacket;
import lightning.product.ClientboundBlockEntityDataPacket;
import lightning.product.BeeFlyingSoundInstance;
import lightning.product.n_1494_c;
import lightning.product.ClientboundPlayerCombatPacket;
import lightning.product.n_2740_g;
import lightning.product.ClientboundRemoveEntitiesPacket;
import lightning.product.n_4563_y;
import lightning.product.n_4637_L;
import lightning.product.DebugQueryHandler;
import lightning.product.n_633_r;
import lightning.product.o_3050_h;
import lightning.product.MutableSearchTree;
import lightning.product.PositionImpl;
import lightning.product.o_98_P;
import lightning.product.p_198_K;
import lightning.product.p_4692_E;
import lightning.product.ClientGamePacketListener;
import lightning.product.PackSource;
import lightning.product.p_752_J;
import lightning.product.ClientboundSetTimePacket;
import lightning.product.JigsawBlockEntity;
import lightning.product.EnderDragonPart;
import lightning.product.q_3092_O;
import lightning.product.q_3131_N;
import lightning.product.q_3418_t;
import lightning.product.Items;
import lightning.product.ClientboundUpdateMobEffectPacket;
import lightning.product.r_1637_F;
import lightning.product.HorseInventoryScreen;
import lightning.product.r_1873_a;
import lightning.product.r_214_x;
import lightning.product.r_4097_j;
import lightning.product.r_4399_U;
import lightning.product.r_4811_B;
import lightning.product.r_4889_F;
import lightning.product.Minecart;
import lightning.product.ClientboundLevelEventPacket;
import lightning.product.s_4438_s;
import lightning.product.LightningBolt;
import lightning.product.t_1786_h;
import lightning.product.ParticleTypes;
import lightning.product.ClientboundSetDisplayObjectivePacket;
import lightning.product.Packet;
import lightning.product.t_3340_s;
import lightning.product.t_3906_J;
import lightning.product.t_4503_H;
import lightning.product.PrimedTnt;
import lightning.product.t_5_h;
import lightning.product.t_950_g;
import lightning.product.u_530_F;
import lightning.product.v_1937_d;
import lightning.product.MinecartSoundInstance;
import lightning.product.v_4727_z;
import lightning.product.v_4839_y;
import lightning.product.ClientboundSetScorePacket;
import lightning.product.w_2989_N;
import lightning.product.w_3005_z;
import lightning.product.DragonFireball;
import lightning.product.w_690_m;
import lightning.product.x_1688_C;
import lightning.product.ThrownEgg;
import lightning.product.x_2401_v;
import lightning.product.x_2680_y;
import lightning.product.x_282_a;
import lightning.product.x_3974_Q;
import lightning.product.CommandBlockEditScreen;
import lightning.product.BeeAggressiveSoundInstance;
import lightning.product.y_4319_k;
import lightning.product.y_4642_Y;
import lightning.product.y_740_d;
import lightning.product.z_2326_J;
import lightning.product.RecipeToast;
import lightning.product.ClientboundSoundEntityPacket;
import mods.baritone.Baritone;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.event.events.ChunkEvent;
import mods.baritone.api.api.java.baritone.api.event.events.type.EventState;
import mods.baritone.cache.CachedChunk;
import mods.viaversion.vialoadingbase.ViaLoadingBase;
import mods.voicechat.eventforge.ClientPlayerNetworkEvent;
import mods.voicechat.eventforge.ForgeNetworkEvents;
import mods.voicechat.intercompatibility.ClientCompatibilityManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class W_2853_p
implements ClientGamePacketListener {
    private static final Logger n_1700_B = LogManager.getLogger();
    private static final x_282_a J_1907_R = new F_2904_S("disconnect.lost");
    private final c_1633_k R_4764_Y;
    private final GameProfile G_564_y;
    private final k_2603_m P_1922_E;
    private MinecraftClient u_1723_Y;
    private k_4690_i v_4262_N;
    private k_4690_i.n_1700_B w_1484_f;
    private boolean t_148_a;
    private final Map<UUID, A_2226_Q> s_956_w = Maps.newHashMap();
    private final ClientAdvancements u_2550_I;
    private final ClientSuggestionProvider M_588_G;
    private TagContainer P_4830_p = TagContainer.n_1700_B;
    private final DebugQueryHandler h_1847_R = new DebugQueryHandler(this);
    private int Q_4569_t = 3;
    private final Random M_182_A = new Random();
    private CommandDispatcher<V_4217_p> t_1786_h = new CommandDispatcher();
    private final G_3474_H multiplayerClientSuggestionProvider = new G_3474_H();
    private final UUID w_1457_N = UUID.randomUUID();
    private Set<f_2392_k<b_4507_u>> Y_601_j;
    private r_4097_j Y_259_p = r_4097_j.J_1907_R();

    public W_2853_p(MinecraftClient mcIn, k_2603_m previousGuiScreen, c_1633_k networkManagerIn, GameProfile profileIn) {
        this.u_1723_Y = mcIn;
        this.P_1922_E = previousGuiScreen;
        this.R_4764_Y = networkManagerIn;
        this.G_564_y = profileIn;
        this.u_2550_I = new ClientAdvancements(mcIn);
        this.M_588_G = new ClientSuggestionProvider(this, mcIn);
    }

    public ClientSuggestionProvider n_1700_B() {
        return this.M_588_G;
    }

    public void J_1907_R() {
        this.v_4262_N = null;
    }

    public G_3474_H R_4764_Y() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Override
    public void n_1700_B(ClientboundLoginPacket packetIn) {
        k_4690_i.n_1700_B clientworld$clientworldinfo;
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.w_1457_N = new V_3163_W(this.u_1723_Y, this);
        if (!this.R_4764_Y.G_564_y()) {
            O_4030_c.n_1700_B();
        }
        ArrayList arraylist = Lists.newArrayList(packetIn.v_4262_N());
        Collections.shuffle(arraylist);
        this.Y_601_j = Sets.newLinkedHashSet((Iterable)arraylist);
        this.Y_259_p = packetIn.w_1484_f();
        f_2392_k<b_4507_u> registrykey = packetIn.s_956_w();
        Z_3903_F dimensiontype = packetIn.t_148_a();
        this.Q_4569_t = packetIn.u_2550_I();
        boolean flag = packetIn.h_1847_R();
        boolean flag1 = packetIn.Q_4569_t();
        this.w_1484_f = clientworld$clientworldinfo = new k_4690_i.n_1700_B(R_2450_T.R_4764_Y, packetIn.G_564_y(), flag1);
        this.v_4262_N = new k_4690_i(this, clientworld$clientworldinfo, registrykey, dimensiontype, this.Q_4569_t, this.u_1723_Y::PlayerInfo, this.u_1723_Y.u_1723_Y, flag, packetIn.R_4764_Y());
        this.u_1723_Y.n_1700_B(this.v_4262_N);
        if (this.u_1723_Y.Y_259_p == null) {
            this.u_1723_Y.Y_259_p = this.u_1723_Y.w_1457_N.createPlayer(this.v_4262_N, new StatsCounter(), new c_1070_s());
            this.u_1723_Y.Y_259_p.p_178_J = -180.0f;
            if (this.u_1723_Y.n_3318_d() != null) {
                this.u_1723_Y.n_3318_d().n_1700_B(this.u_1723_Y.Y_259_p.w_2705_t());
            }
        }
        this.u_1723_Y.u_2550_I.n_1700_B();
        this.u_1723_Y.Y_259_p.k_3961_g();
        A_4115_X.n_1700_B(new ClientPlayerNetworkEvent.LoggedInEvent(MinecraftAccess.c_3005_b.w_1457_N, MinecraftAccess.c_3005_b.Y_259_p, MinecraftAccess.c_3005_b.Q_2552_b));
        int i = packetIn.J_1907_R();
        this.v_4262_N.n_1700_B(i, this.u_1723_Y.Y_259_p);
        this.u_1723_Y.Y_259_p.G_564_y = new e_869_m(this.u_1723_Y.P_4830_p);
        this.u_1723_Y.w_1457_N.setPlayerCapabilities(this.u_1723_Y.Y_259_p);
        this.u_1723_Y.C_2741_M = this.u_1723_Y.Y_259_p;
        this.u_1723_Y.n_1700_B(new G_2460_P());
        this.u_1723_Y.Y_259_p.G_564_y(i);
        this.u_1723_Y.Y_259_p.Y_601_j(packetIn.M_588_G());
        this.u_1723_Y.Y_259_p.R_4764_Y(packetIn.P_4830_p());
        this.u_1723_Y.w_1457_N.setGameType(packetIn.P_1922_E());
        this.u_1723_Y.w_1457_N.func_241675_a_(packetIn.u_1723_Y());
        this.u_1723_Y.P_4830_p.R_4764_Y();
        this.R_4764_Y.n_1700_B(new O_3036_q(O_3036_q.n_1700_B, new b_2585_i(Unpooled.buffer()).n_1700_B(p_752_J.n_1700_B())));
        this.u_1723_Y.V_1225_t().J_1907_R();
        ClientCompatibilityManager.INSTANCE.onJoinWorld(() -> {});
    }

    @Override
    public void n_1700_B(ClientboundAddEntityPacket packetIn) {
        N_4263_v entity;
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        double d0 = packetIn.G_564_y();
        double d1 = packetIn.P_1922_E();
        double d2 = packetIn.u_1723_Y();
        t_5_h<?> entitytype = packetIn.M_588_G();
        if (entitytype == t_5_h.X_933_l) {
            entity = new MinecartChest(this.v_4262_N, d0, d1, d2);
        } else if (entitytype == t_5_h.H_1990_U) {
            entity = new MinecartFurnace(this.v_4262_N, d0, d1, d2);
        } else if (entitytype == t_5_h.g_2268_R) {
            entity = new r_1637_F(this.v_4262_N, d0, d1, d2);
        } else if (entitytype == t_5_h.c_4037_x) {
            entity = new MinecartSpawner(this.v_4262_N, d0, d1, d2);
        } else if (entitytype == t_5_h.N_2525_X) {
            entity = new MinecartHopper(this.v_4262_N, d0, d1, d2);
        } else if (entitytype == t_5_h.Z_976_R) {
            entity = new z_2326_J(this.v_4262_N, d0, d1, d2);
        } else if (entitytype == t_5_h.g_164_R) {
            entity = new Minecart(this.v_4262_N, d0, d1, d2);
        } else if (entitytype == t_5_h.RealmsClientOutdatedScreen) {
            N_4263_v entity1 = this.v_4262_N.J_1907_R(packetIn.P_4830_p());
            entity = entity1 instanceof a_3913_L ? new W_1247_f(this.v_4262_N, (a_3913_L)entity1, d0, d1, d2) : null;
        } else if (entitytype == t_5_h.R_4764_Y) {
            entity = new Arrow(this.v_4262_N, d0, d1, d2);
            N_4263_v entity2 = this.v_4262_N.J_1907_R(packetIn.P_4830_p());
            if (entity2 != null) {
                ((h_384_L)entity).J_1907_R(entity2);
            }
        } else if (entitytype == t_5_h.w_612_n) {
            entity = new SpectralArrow(this.v_4262_N, d0, d1, d2);
            N_4263_v entity3 = this.v_4262_N.J_1907_R(packetIn.P_4830_p());
            if (entity3 != null) {
                ((h_384_L)entity).J_1907_R(entity3);
            }
        } else if (entitytype == t_5_h.ValueObject) {
            entity = new E_4925_L(this.v_4262_N, d0, d1, d2);
            N_4263_v entity4 = this.v_4262_N.J_1907_R(packetIn.P_4830_p());
            if (entity4 != null) {
                ((h_384_L)entity).J_1907_R(entity4);
            }
        } else {
            entity = entitytype == t_5_h.dtoRealmsServerAddress ? new Snowball(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.e_2887_G ? new r_214_x(this.v_4262_N, d0, d1, d2, packetIn.v_4262_N(), packetIn.w_1484_f(), packetIn.t_148_a()) : (entitytype == t_5_h.G_624_v ? new y_740_d(this.v_4262_N, new c_1514_x(d0, d1, d2), b_257_Y.n_1700_B(packetIn.P_4830_p())) : (entitytype == t_5_h.q_4610_l ? new LeashFenceKnotEntity(this.v_4262_N, new c_1514_x(d0, d1, d2)) : (entitytype == t_5_h.RegionPingResult ? new w_2989_N(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.Z_875_P ? new R_137_s(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.H_2857_Y ? new J_3992_v(this.v_4262_N, d0, d1, d2, Z_1993_T.J_1907_R) : (entitytype == t_5_h.T_2506_i ? new LargeFireball(this.v_4262_N, d0, d1, d2, packetIn.v_4262_N(), packetIn.w_1484_f(), packetIn.t_148_a()) : (entitytype == t_5_h.M_182_A ? new DragonFireball(this.v_4262_N, d0, d1, d2, packetIn.v_4262_N(), packetIn.w_1484_f(), packetIn.t_148_a()) : (entitytype == t_5_h.U_1241_n ? new s_4438_s(this.v_4262_N, d0, d1, d2, packetIn.v_4262_N(), packetIn.w_1484_f(), packetIn.t_148_a()) : (entitytype == t_5_h.LongRunningTask ? new WitherSkull(this.v_4262_N, d0, d1, d2, packetIn.v_4262_N(), packetIn.w_1484_f(), packetIn.t_148_a()) : (entitytype == t_5_h.h_4320_q ? new L_2837_o(this.v_4262_N, d0, d1, d2, packetIn.v_4262_N(), packetIn.w_1484_f(), packetIn.t_148_a()) : (entitytype == t_5_h.RealmsWorldResetDto ? new ThrownEgg(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.k_2293_S ? new t_950_g(this.v_4262_N, d0, d1, d2, 0.0f, 0, null) : (entitytype == t_5_h.R_3908_n ? new F_666_T(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.H_1083_k ? new ThrownExperienceBottle(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.v_4262_N ? new g_1462_f(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.f_4016_n ? new PrimedTnt(this.v_4262_N, d0, d1, d2, null) : (entitytype == t_5_h.J_1907_R ? new D_686_b(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.w_1457_N ? new V_3354_l(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.d_2461_k ? new n_1494_c(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.c_3005_b ? new W_4464_I(this.v_4262_N, d0, d1, d2, T_2915_h.n_1700_B(packetIn.P_4830_p())) : (entitytype == t_5_h.n_1700_B ? new B_1132_Q(this.v_4262_N, d0, d1, d2) : (entitytype == t_5_h.z_4693_k ? new LightningBolt((t_5_h<? extends LightningBolt>)t_5_h.z_4693_k, (b_4507_u)this.v_4262_N) : null)))))))))))))))))))))));
        }
        if (entity != null) {
            int i = packetIn.J_1907_R();
            entity.n_1700_B(d0, d1, d2);
            entity.P_1922_E(d0, d1, d2);
            entity.f_4016_n = (float)(packetIn.s_956_w() * 360) / 256.0f;
            entity.p_178_J = (float)(packetIn.u_2550_I() * 360) / 256.0f;
            entity.G_564_y(i);
            entity.a_(packetIn.R_4764_Y());
            this.v_4262_N.n_1700_B(i, entity);
            if (entity instanceof y_4319_k) {
                this.u_1723_Y.Z_976_R().n_1700_B((SoundInstance)new MinecartSoundInstance((y_4319_k)entity));
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundAddExperienceOrbPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        double d0 = packetIn.R_4764_Y();
        double d1 = packetIn.G_564_y();
        double d2 = packetIn.P_1922_E();
        n_4637_L entity = new n_4637_L(this.v_4262_N, d0, d1, d2, packetIn.u_1723_Y());
        entity.n_1700_B(d0, d1, d2);
        entity.p_178_J = 0.0f;
        entity.f_4016_n = 0.0f;
        entity.G_564_y(packetIn.J_1907_R());
        this.v_4262_N.n_1700_B(packetIn.J_1907_R(), (N_4263_v)entity);
    }

    @Override
    public void n_1700_B(ClientboundAddPaintingPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        Painting paintingentity = new Painting(this.v_4262_N, packetIn.G_564_y(), packetIn.P_1922_E(), packetIn.u_1723_Y());
        paintingentity.G_564_y(packetIn.J_1907_R());
        paintingentity.a_(packetIn.R_4764_Y());
        this.v_4262_N.n_1700_B(packetIn.J_1907_R(), (N_4263_v)paintingentity);
    }

    @Override
    public void n_1700_B(ClientboundSetEntityMotionPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.J_1907_R());
        if (entity != null) {
            entity.s_956_w((double)packetIn.R_4764_Y() / 8000.0, (double)packetIn.G_564_y() / 8000.0, (double)packetIn.P_1922_E() / 8000.0);
        }
    }

    @Override
    public void n_1700_B(a_4762_y packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.R_4764_Y());
        if (entity != null && packetIn.J_1907_R() != null) {
            entity.D_60_a().n_1700_B(packetIn.J_1907_R());
        }
    }

    @Override
    public void n_1700_B(ClientboundAddPlayerPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        double d0 = packetIn.G_564_y();
        double d1 = packetIn.P_1922_E();
        double d2 = packetIn.u_1723_Y();
        float f = (float)(packetIn.v_4262_N() * 360) / 256.0f;
        float f1 = (float)(packetIn.w_1484_f() * 360) / 256.0f;
        int i = packetIn.J_1907_R();
        A_2226_Q playerInfo = this.n_1700_B(packetIn.R_4764_Y());
        if (playerInfo == null) {
            GameProfile tempProfile = new GameProfile(packetIn.R_4764_Y(), "Player" + i);
            Q_1187_u remoteclientplayerentity = new Q_1187_u(this.u_1723_Y.Y_601_j, tempProfile);
            remoteclientplayerentity.G_564_y(i);
            remoteclientplayerentity.u_1723_Y(d0, d1, d2);
            remoteclientplayerentity.n_1700_B(d0, d1, d2);
            remoteclientplayerentity.n_1700_B(d0, d1, d2, f, f1);
            this.v_4262_N.n_1700_B(i, remoteclientplayerentity);
            return;
        }
        Q_1187_u remoteclientplayerentity = new Q_1187_u(this.u_1723_Y.Y_601_j, playerInfo.n_1700_B());
        remoteclientplayerentity.G_564_y(i);
        remoteclientplayerentity.u_1723_Y(d0, d1, d2);
        remoteclientplayerentity.n_1700_B(d0, d1, d2);
        remoteclientplayerentity.n_1700_B(d0, d1, d2, f, f1);
        this.v_4262_N.n_1700_B(i, remoteclientplayerentity);
    }

    @Override
    public void n_1700_B(ClientboundTeleportEntityPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.J_1907_R());
        if (entity != null) {
            double d0 = packetIn.R_4764_Y();
            double d1 = packetIn.G_564_y();
            double d2 = packetIn.P_1922_E();
            entity.n_1700_B(d0, d1, d2);
            if (!entity.v_887_r()) {
                float f = (float)(packetIn.u_1723_Y() * 360) / 256.0f;
                float f1 = (float)(packetIn.v_4262_N() * 360) / 256.0f;
                entity.n_1700_B(d0, d1, d2, f, f1, 3, true);
                entity.u_1723_Y(packetIn.w_1484_f());
            }
        }
    }

    @Override
    public void n_1700_B(Z_390_O packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        if (W_3491_f.J_1907_R(packetIn.J_1907_R())) {
            this.u_1723_Y.Y_259_p.l_1268_F.G_564_y = packetIn.J_1907_R();
        }
    }

    @Override
    public void n_1700_B(N_4422_X packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = packetIn.n_1700_B(this.v_4262_N);
        if (entity != null && !entity.v_887_r()) {
            if (packetIn.P_1922_E()) {
                e_2866_D vector3d = packetIn.n_1700_B(entity.RealmsClientConfig());
                entity.a_(vector3d);
                float f = packetIn.G_564_y() ? (float)(packetIn.J_1907_R() * 360) / 256.0f : entity.p_178_J;
                float f1 = packetIn.G_564_y() ? (float)(packetIn.R_4764_Y() * 360) / 256.0f : entity.f_4016_n;
                entity.n_1700_B(vector3d.n_1700_B(), vector3d.J_1907_R(), vector3d.R_4764_Y(), f, f1, 3, false);
            } else if (packetIn.G_564_y()) {
                float f2 = (float)(packetIn.J_1907_R() * 360) / 256.0f;
                float f3 = (float)(packetIn.R_4764_Y() * 360) / 256.0f;
                entity.n_1700_B(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), f2, f3, 3, false);
            }
            entity.u_1723_Y(packetIn.u_1723_Y());
        }
    }

    @Override
    public void n_1700_B(X_776_r packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = packetIn.n_1700_B(this.v_4262_N);
        if (entity != null) {
            float f = (float)(packetIn.J_1907_R() * 360) / 256.0f;
            entity.n_1700_B(f, 3);
        }
    }

    @Override
    public void n_1700_B(ClientboundRemoveEntitiesPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        for (int i = 0; i < packetIn.J_1907_R().length; ++i) {
            int j = packetIn.J_1907_R()[i];
            this.v_4262_N.n_1700_B(j);
        }
    }

    @Override
    public void n_1700_B(ClientboundPlayerPositionPacket packetIn) {
        double d5;
        double d4;
        double d3;
        double d2;
        double d1;
        double d0;
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        V_772_m playerentity = this.u_1723_Y.Y_259_p;
        e_2866_D vector3d = playerentity.I_4348_c();
        boolean flag = packetIn.w_1484_f().contains((Object)ClientboundPlayerPositionPacket.n_1700_B.n_1700_B);
        boolean flag1 = packetIn.w_1484_f().contains((Object)ClientboundPlayerPositionPacket.n_1700_B.J_1907_R);
        boolean flag2 = packetIn.w_1484_f().contains((Object)ClientboundPlayerPositionPacket.n_1700_B.R_4764_Y);
        if (flag) {
            d0 = vector3d.n_1700_B();
            d1 = playerentity.O_3598_v() + packetIn.J_1907_R();
            playerentity.q_1982_R += packetIn.J_1907_R();
        } else {
            d0 = 0.0;
            playerentity.q_1982_R = d1 = packetIn.J_1907_R();
        }
        if (flag1) {
            d2 = vector3d.J_1907_R();
            d3 = playerentity.X_2960_b() + packetIn.R_4764_Y();
            playerentity.dtoRealmsServerAddress += packetIn.R_4764_Y();
        } else {
            d2 = 0.0;
            playerentity.dtoRealmsServerAddress = d3 = packetIn.R_4764_Y();
        }
        if (flag2) {
            d4 = vector3d.R_4764_Y();
            d5 = playerentity.l_2647_k() + packetIn.G_564_y();
            playerentity.w_612_n += packetIn.G_564_y();
        } else {
            d4 = 0.0;
            playerentity.w_612_n = d5 = packetIn.G_564_y();
        }
        if (playerentity.RealmsWorldResetDto > 0 && playerentity.l_3609_d() != null) {
            ((a_3913_L)playerentity).t_();
        }
        playerentity.Q_4569_t(d1, d3, d5);
        playerentity.r_715_M = d1;
        playerentity.A_1038_p = d3;
        playerentity.i_1637_u = d5;
        playerentity.h_1847_R(d0, d2, d4);
        float f = packetIn.P_1922_E();
        float f1 = packetIn.u_1723_Y();
        if (packetIn.w_1484_f().contains((Object)ClientboundPlayerPositionPacket.n_1700_B.P_1922_E)) {
            f1 += playerentity.f_4016_n;
        }
        if (packetIn.w_1484_f().contains((Object)ClientboundPlayerPositionPacket.n_1700_B.G_564_y)) {
            f += playerentity.p_178_J;
        }
        M_3508_C eventNoRotate = new M_3508_C(f, f1);
        A_4115_X.n_1700_B(eventNoRotate);
        if (eventNoRotate.n_1700_B()) {
            playerentity.J_1907_R(d1, d3, d5);
        } else {
            playerentity.n_1700_B(d1, d3, d5, eventNoRotate.J_1907_R(), eventNoRotate.R_4764_Y());
        }
        this.R_4764_Y.n_1700_B(new x_2401_v(packetIn.v_4262_N()));
        this.R_4764_Y.n_1700_B(new N_3268_u.J_1907_R(playerentity.O_3598_v(), playerentity.X_2960_b(), playerentity.l_2647_k(), playerentity.p_178_J, playerentity.f_4016_n, false));
        if (!this.t_148_a) {
            this.t_148_a = true;
            this.u_1723_Y.n_1700_B((k_2603_m)null);
        }
    }

    @Override
    public void n_1700_B(ClientboundSectionBlocksUpdatePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        int i = 0x13 | (packetIn.J_1907_R() ? 128 : 0);
        packetIn.n_1700_B((c_1514_x p_243492_2_, K_4074_S p_243492_3_) -> this.v_4262_N.n_1700_B((c_1514_x)p_243492_2_, (K_4074_S)p_243492_3_, i));
        if (!((Boolean)Baritone.settings().repackOnAnyBlockChange.value).booleanValue()) {
            return;
        }
        Y_1387_d[] chunkPos = new Y_1387_d[1];
        packetIn.n_1700_B((c_1514_x pos, K_4074_S state) -> {
            if (CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.contains((Object)state.J_1907_R())) {
                chunkPos[0] = new Y_1387_d((c_1514_x)pos);
            }
        });
        if (chunkPos[0] == null) {
            return;
        }
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
            V_772_m player = ibaritone.getPlayerContext().player();
            if (player == null || player.n_1700_B != this) continue;
            ibaritone.getGameEventHandler().onChunkEvent(new ChunkEvent(EventState.POST, ChunkEvent.Type.POPULATE_FULL, chunkPos[0].J_1907_R, chunkPos[0].R_4764_Y));
        }
    }

    @Override
    public void n_1700_B(U_157_Y packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        int i = packetIn.R_4764_Y();
        int j = packetIn.G_564_y();
        c_1108_W biomecontainer = packetIn.t_148_a() == null ? null : new c_1108_W(this.Y_259_p.J_1907_R(V_3137_a.PlayerInfo), packetIn.t_148_a());
        H_1748_a chunk = this.v_4262_N.v_4262_N().n_1700_B(i, j, biomecontainer, packetIn.J_1907_R(), packetIn.v_4262_N(), packetIn.P_1922_E(), packetIn.u_1723_Y());
        if (chunk != null && packetIn.u_1723_Y()) {
            this.v_4262_N.J_1907_R(chunk);
        }
        for (int k = 0; k < 16; ++k) {
            this.v_4262_N.J_1907_R(i, k, j);
        }
        for (U_2912_j compoundnbt : packetIn.w_1484_f()) {
            c_1514_x blockpos = new c_1514_x(compoundnbt.w_1484_f("x"), compoundnbt.w_1484_f("y"), compoundnbt.w_1484_f("z"));
            i_2154_H tileentity = this.v_4262_N.getTileEntity(blockpos);
            if (tileentity == null) continue;
            tileentity.n_1700_B(this.v_4262_N.getBlockState(blockpos), compoundnbt);
        }
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
            V_772_m player = ibaritone.getPlayerContext().player();
            if (player == null || player.n_1700_B != this) continue;
            ibaritone.getGameEventHandler().onChunkEvent(new ChunkEvent(EventState.POST, packetIn.u_1723_Y() ? ChunkEvent.Type.POPULATE_FULL : ChunkEvent.Type.POPULATE_PARTIAL, packetIn.R_4764_Y(), packetIn.G_564_y()));
        }
        if (!this.t_148_a && packetIn.u_1723_Y() && this.u_1723_Y.Y_259_p != null && this.u_1723_Y.Y_1740_V instanceof G_2460_P) {
            Y_1387_d playerChunk = new Y_1387_d(this.u_1723_Y.Y_259_p.b_2312_j());
            if (playerChunk.J_1907_R == i && playerChunk.R_4764_Y == j) {
                this.M_182_A();
            }
        }
    }

    @Override
    public void n_1700_B(W_4148_E packetIn) {
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
            V_772_m player = ibaritone.getPlayerContext().player();
            if (player == null || player.n_1700_B != this) continue;
            ibaritone.getGameEventHandler().onChunkEvent(new ChunkEvent(EventState.PRE, ChunkEvent.Type.UNLOAD, packetIn.J_1907_R(), packetIn.R_4764_Y()));
        }
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        int i = packetIn.J_1907_R();
        int j = packetIn.R_4764_Y();
        r_4399_U clientchunkprovider = this.v_4262_N.v_4262_N();
        clientchunkprovider.n_1700_B(i, j);
        R_1900_x worldlightmanager = clientchunkprovider.G_564_y();
        for (int k = 0; k < 16; ++k) {
            this.v_4262_N.J_1907_R(i, k, j);
            worldlightmanager.n_1700_B(SectionPos.n_1700_B(i, k, j), true);
        }
        worldlightmanager.n_1700_B(new Y_1387_d(i, j), false);
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
            V_772_m player = ibaritone.getPlayerContext().player();
            if (player == null || player.n_1700_B != this) continue;
            ibaritone.getGameEventHandler().onChunkEvent(new ChunkEvent(EventState.POST, ChunkEvent.Type.UNLOAD, packetIn.J_1907_R(), packetIn.R_4764_Y()));
        }
    }

    @Override
    public void n_1700_B(ClientboundBlockUpdatePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.v_4262_N.n_1700_B(packetIn.R_4764_Y(), packetIn.J_1907_R());
        if (!((Boolean)Baritone.settings().repackOnAnyBlockChange.value).booleanValue()) {
            return;
        }
        if (!CachedChunk.BLOCKS_TO_KEEP_TRACK_OF.contains((Object)packetIn.J_1907_R().J_1907_R())) {
            return;
        }
        for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
            V_772_m player = ibaritone.getPlayerContext().player();
            if (player == null || player.n_1700_B != this) continue;
            ibaritone.getGameEventHandler().onChunkEvent(new ChunkEvent(EventState.POST, ChunkEvent.Type.POPULATE_FULL, packetIn.R_4764_Y().getX() >> 4, packetIn.R_4764_Y().getZ() >> 4));
        }
    }

    @Override
    public void n_1700_B(w_690_m packetIn) {
        this.R_4764_Y.n_1700_B(packetIn.J_1907_R());
    }

    @Override
    public void onDisconnect(x_282_a reason) {
        this.u_1723_Y.Y_601_j();
        if (this.P_1922_E != null) {
            if (this.P_1922_E instanceof RealmsScreen) {
                this.u_1723_Y.n_1700_B(new n_633_r(this.P_1922_E, J_1907_R, reason));
            } else {
                this.u_1723_Y.n_1700_B(new a_4411_f(this.P_1922_E, J_1907_R, reason));
            }
        } else {
            this.u_1723_Y.n_1700_B(new a_4411_f(new q_3131_N(y_4642_Y.R_4764_Y() ? new k_596_g(true) : new O_922_L()), J_1907_R, reason));
        }
    }

    public void n_1700_B(Packet<?> packetIn) {
        this.R_4764_Y.n_1700_B(packetIn);
    }

    @Override
    public void n_1700_B(X_508_u packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.J_1907_R());
        r_4811_B livingentity = (r_4811_B)this.v_4262_N.J_1907_R(packetIn.R_4764_Y());
        if (livingentity == null) {
            livingentity = this.u_1723_Y.Y_259_p;
        }
        if (entity != null) {
            if (entity instanceof n_4637_L) {
                this.v_4262_N.n_1700_B(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), SoundEvents.d_4500_Q, D_38_f.w_1484_f, 0.1f, (this.M_182_A.nextFloat() - this.M_182_A.nextFloat()) * 0.35f + 0.9f, false);
            } else {
                this.v_4262_N.n_1700_B(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), SoundEvents.DeathCoords, D_38_f.w_1484_f, 0.2f, (this.M_182_A.nextFloat() - this.M_182_A.nextFloat()) * 1.4f + 2.0f, false);
            }
            this.u_1723_Y.v_4262_N.n_1700_B(new I_438_q(this.u_1723_Y.O_508_d(), this.u_1723_Y.j_1564_a(), this.v_4262_N, entity, (N_4263_v)livingentity));
            if (entity instanceof n_1494_c) {
                n_1494_c itementity = (n_1494_c)entity;
                Z_1993_T itemstack = itementity.P_1922_E();
                U_532_X event = new U_532_X(itementity, livingentity, itemstack.t_148_a(), packetIn.G_564_y());
                A_4115_X.n_1700_B(event);
                if (!event.n_1700_B()) {
                    itemstack.v_4262_N(packetIn.G_564_y());
                    if (itemstack.n_1700_B()) {
                        this.v_4262_N.n_1700_B(packetIn.J_1907_R());
                    }
                }
            } else {
                this.v_4262_N.n_1700_B(packetIn.J_1907_R());
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundChatPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.M_588_G.n_1700_B(packetIn.G_564_y(), packetIn.J_1907_R(), packetIn.P_1922_E());
    }

    @Override
    public void n_1700_B(q_3092_O packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.J_1907_R());
        if (entity != null) {
            if (packetIn.R_4764_Y() == 0) {
                r_4811_B livingentity = (r_4811_B)entity;
                livingentity.n_1700_B(x_1688_C.n_1700_B);
            } else if (packetIn.R_4764_Y() == 3) {
                r_4811_B livingentity1 = (r_4811_B)entity;
                livingentity1.n_1700_B(x_1688_C.J_1907_R);
            } else if (packetIn.R_4764_Y() == 1) {
                entity.D_4361_a();
            } else if (packetIn.R_4764_Y() == 2) {
                a_3913_L playerentity = (a_3913_L)entity;
                playerentity.n_1700_B(false, false);
            } else if (packetIn.R_4764_Y() == 4) {
                this.u_1723_Y.v_4262_N.n_1700_B(entity, ParticleTypes.v_4262_N);
            } else if (packetIn.R_4764_Y() == 5) {
                this.u_1723_Y.v_4262_N.n_1700_B(entity, ParticleTypes.multiplayerClientSuggestionProvider);
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundAddMobPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        double d0 = packetIn.P_1922_E();
        double d1 = packetIn.u_1723_Y();
        double d2 = packetIn.v_4262_N();
        float f = (float)(packetIn.u_2550_I() * 360) / 256.0f;
        float f1 = (float)(packetIn.M_588_G() * 360) / 256.0f;
        r_4811_B livingentity = (r_4811_B)t_5_h.n_1700_B(packetIn.G_564_y(), (b_4507_u)this.u_1723_Y.Y_601_j);
        if (livingentity != null) {
            livingentity.n_1700_B(d0, d1, d2);
            livingentity.C_1162_e = (float)(packetIn.P_4830_p() * 360) / 256.0f;
            livingentity.f_3449_S = (float)(packetIn.P_4830_p() * 360) / 256.0f;
            if (livingentity instanceof b_2971_b) {
                EnderDragonPart[] aenderdragonpartentity = ((b_2971_b)livingentity).Q_4569_t();
                for (int i = 0; i < aenderdragonpartentity.length; ++i) {
                    aenderdragonpartentity[i].G_564_y(i + packetIn.J_1907_R());
                }
            }
            livingentity.G_564_y(packetIn.J_1907_R());
            livingentity.a_(packetIn.R_4764_Y());
            livingentity.n_1700_B(d0, d1, d2, f, f1);
            livingentity.h_1847_R((float)packetIn.w_1484_f() / 8000.0f, (float)packetIn.t_148_a() / 8000.0f, (float)packetIn.s_956_w() / 8000.0f);
            this.v_4262_N.n_1700_B(packetIn.J_1907_R(), (N_4263_v)livingentity);
            if (livingentity instanceof b_1913_J) {
                boolean flag = ((b_1913_J)livingentity).B_();
                BeeSoundInstance beesound = flag ? new BeeAggressiveSoundInstance((b_1913_J)livingentity) : new BeeFlyingSoundInstance((b_1913_J)livingentity);
                this.u_1723_Y.Z_976_R().n_1700_B(beesound);
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundSetTimePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.Y_601_j.n_1700_B(packetIn.J_1907_R());
        this.u_1723_Y.Y_601_j.J_1907_R(packetIn.R_4764_Y());
    }

    @Override
    public void n_1700_B(ClientboundSetDefaultSpawnPositionPacket p_230488_1_) {
        v_1937_d.n_1700_B(p_230488_1_, this, this.u_1723_Y);
        this.u_1723_Y.Y_601_j.J_1907_R(p_230488_1_.J_1907_R(), p_230488_1_.R_4764_Y());
    }

    @Override
    public void n_1700_B(ClientboundSetPassengersPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.R_4764_Y());
        if (entity != null) {
            boolean flag = entity.Q_2552_b(this.u_1723_Y.Y_259_p);
            entity.C_3538_G();
            for (int i : packetIn.J_1907_R()) {
                N_4263_v entity1 = this.v_4262_N.J_1907_R(i);
                if (entity1 == null) continue;
                entity1.n_1700_B(entity, true);
                if (entity1 != this.u_1723_Y.Y_259_p || flag) continue;
                this.u_1723_Y.M_588_G.n_1700_B(new F_2904_S("mount.onboard", this.u_1723_Y.P_4830_p.p_178_J.u_2550_I()), false);
            }
        }
    }

    @Override
    public void n_1700_B(e_446_u packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.J_1907_R());
        if (entity instanceof Z_530_i) {
            ((Z_530_i)entity).multiplayerClientSuggestionProvider(packetIn.R_4764_Y());
        }
    }

    private static Z_1993_T n_1700_B(a_3913_L player) {
        for (x_1688_C hand : x_1688_C.values()) {
            Z_1993_T itemstack = player.R_4764_Y(hand);
            if (itemstack.J_1907_R() != Items.N_81_X) continue;
            return itemstack;
        }
        return new Z_1993_T(Items.N_81_X);
    }

    @Override
    public void n_1700_B(C_1375_J packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = packetIn.n_1700_B(this.v_4262_N);
        if (entity != null) {
            if (packetIn.J_1907_R() == 21) {
                this.u_1723_Y.Z_976_R().n_1700_B((SoundInstance)new GuardianAttackSoundInstance((G_1455_B)entity));
            } else if (packetIn.J_1907_R() == 35) {
                f_691_R pop = new f_691_R(entity);
                A_4115_X.n_1700_B(pop);
                int i = 40;
                this.u_1723_Y.v_4262_N.n_1700_B(entity, ParticleTypes.N_2525_X, 30);
                this.v_4262_N.n_1700_B(entity.O_3598_v(), entity.X_2960_b(), entity.l_2647_k(), SoundEvents.S_1431_H, entity.r_2478_U(), 1.0f, 1.0f, false);
                h_3270_j event = new h_3270_j(h_3270_j.n_1700_B.P_1922_E);
                A_4115_X.n_1700_B(event);
                if (entity == this.u_1723_Y.Y_259_p && !event.n_1700_B()) {
                    this.u_1723_Y.s_956_w.n_1700_B(W_2853_p.n_1700_B(this.u_1723_Y.Y_259_p));
                }
            } else {
                entity.n_1700_B(packetIn.J_1907_R());
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundSetHealthPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.Y_259_p.G_564_y(packetIn.J_1907_R());
        this.u_1723_Y.Y_259_p.P_2295_B().n_1700_B(packetIn.R_4764_Y());
        this.u_1723_Y.Y_259_p.P_2295_B().J_1907_R(packetIn.G_564_y());
    }

    @Override
    public void n_1700_B(ClientboundSetExperiencePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.Y_259_p.n_1700_B(packetIn.J_1907_R(), packetIn.R_4764_Y(), packetIn.G_564_y());
    }

    @Override
    public void n_1700_B(ClientboundRespawnPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        f_2392_k<b_4507_u> registrykey = packetIn.R_4764_Y();
        Z_3903_F dimensiontype = packetIn.J_1907_R();
        V_772_m clientplayerentity = this.u_1723_Y.Y_259_p;
        int i = clientplayerentity.j_276_v();
        this.t_148_a = false;
        if (registrykey != clientplayerentity.O_508_d.g_2268_R()) {
            k_4690_i.n_1700_B clientworld$clientworldinfo;
            i_4895_l scoreboard = this.v_4262_N.Q_4569_t();
            boolean flag = packetIn.v_4262_N();
            boolean flag1 = packetIn.w_1484_f();
            this.w_1484_f = clientworld$clientworldinfo = new k_4690_i.n_1700_B(this.w_1484_f.u_2550_I(), this.w_1484_f.n_1700_B(), flag1);
            this.v_4262_N = new k_4690_i(this, clientworld$clientworldinfo, registrykey, dimensiontype, this.Q_4569_t, this.u_1723_Y::PlayerInfo, this.u_1723_Y.u_1723_Y, flag, packetIn.G_564_y());
            this.v_4262_N.n_1700_B(scoreboard);
            this.u_1723_Y.n_1700_B(this.v_4262_N);
            this.u_1723_Y.n_1700_B(new G_2460_P());
        }
        this.v_4262_N.u_1723_Y();
        String s = clientplayerentity.h_1847_R();
        this.u_1723_Y.C_2741_M = null;
        V_772_m clientplayerentity1 = this.u_1723_Y.w_1457_N.func_239167_a_(this.v_4262_N, clientplayerentity.Q_4569_t(), clientplayerentity.M_182_A(), clientplayerentity.q_2307_F(), clientplayerentity.o_2341_D());
        clientplayerentity1.G_564_y(i);
        this.u_1723_Y.Y_259_p = clientplayerentity1;
        if (registrykey != clientplayerentity.O_508_d.g_2268_R()) {
            this.u_1723_Y.multiplayerClientSuggestionProvider().J_1907_R();
        }
        this.u_1723_Y.C_2741_M = clientplayerentity1;
        clientplayerentity1.D_60_a().n_1700_B(clientplayerentity.D_60_a().R_4764_Y());
        if (packetIn.t_148_a()) {
            clientplayerentity1.B_1146_q().n_1700_B(clientplayerentity.B_1146_q());
        }
        clientplayerentity1.k_3961_g();
        clientplayerentity1.J_1907_R(s);
        this.v_4262_N.n_1700_B(i, clientplayerentity1);
        clientplayerentity1.p_178_J = -180.0f;
        clientplayerentity1.G_564_y = new e_869_m(this.u_1723_Y.P_4830_p);
        this.u_1723_Y.w_1457_N.setPlayerCapabilities(clientplayerentity1);
        clientplayerentity1.Y_601_j(clientplayerentity.y_3417_N());
        clientplayerentity1.R_4764_Y(clientplayerentity.multiplayerClientSuggestionProvider());
        if (this.u_1723_Y.Y_1740_V instanceof E_1407_D) {
            this.u_1723_Y.n_1700_B((k_2603_m)null);
        }
        this.u_1723_Y.w_1457_N.setGameType(packetIn.P_1922_E());
        this.u_1723_Y.w_1457_N.func_241675_a_(packetIn.u_1723_Y());
    }

    @Override
    public void n_1700_B(ClientboundExplodePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        F_1241_B explosion = new F_1241_B(this.u_1723_Y.Y_601_j, null, packetIn.P_1922_E(), packetIn.u_1723_Y(), packetIn.v_4262_N(), packetIn.w_1484_f(), packetIn.t_148_a());
        explosion.n_1700_B(true);
        this.u_1723_Y.Y_259_p.v_4262_N(this.u_1723_Y.Y_259_p.I_4348_c().J_1907_R(packetIn.J_1907_R(), packetIn.R_4764_Y(), packetIn.G_564_y()));
    }

    @Override
    public void n_1700_B(t_3906_J packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.G_564_y());
        if (entity instanceof U_2534_D) {
            V_772_m clientplayerentity = this.u_1723_Y.Y_259_p;
            U_2534_D abstracthorseentity = (U_2534_D)entity;
            N_1216_z inventory = new N_1216_z(packetIn.R_4764_Y());
            R_3940_n horseinventorycontainer = new R_3940_n(packetIn.J_1907_R(), clientplayerentity.l_1268_F, inventory, abstracthorseentity);
            clientplayerentity.H_1873_g = horseinventorycontainer;
            this.u_1723_Y.n_1700_B(new HorseInventoryScreen(horseinventorycontainer, clientplayerentity.l_1268_F, abstracthorseentity));
        }
    }

    @Override
    public void n_1700_B(ClientboundOpenScreenPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        D_4704_b.n_1700_B(packetIn.R_4764_Y(), this.u_1723_Y, packetIn.J_1907_R(), packetIn.G_564_y());
    }

    @Override
    public void n_1700_B(a_4764_N packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        V_772_m playerentity = this.u_1723_Y.Y_259_p;
        Z_1993_T itemstack = packetIn.G_564_y();
        int i = packetIn.R_4764_Y();
        this.u_1723_Y.D_60_a().n_1700_B(itemstack);
        if (packetIn.J_1907_R() == -1) {
            if (!(this.u_1723_Y.Y_1740_V instanceof B_3091_S)) {
                playerentity.l_1268_F.v_4262_N(itemstack);
            }
        } else if (packetIn.J_1907_R() == -2) {
            playerentity.l_1268_F.J_1907_R(i, itemstack);
        } else {
            boolean flag = false;
            if (this.u_1723_Y.Y_1740_V instanceof B_3091_S) {
                B_3091_S creativescreen = (B_3091_S)this.u_1723_Y.Y_1740_V;
                boolean bl = flag = creativescreen.P_1922_E() != S_1134_u.h_1847_R.n_1700_B();
            }
            if (packetIn.J_1907_R() == 0 && i >= 0 && i < 45) {
                Z_1993_T itemstack1;
                if (!itemstack.n_1700_B() && ((itemstack1 = playerentity.o_1800_r.n_1700_B(i).n_1700_B()).n_1700_B() || itemstack1.t_4043_B() < itemstack.t_4043_B())) {
                    itemstack.G_564_y(5);
                }
                playerentity.o_1800_r.n_1700_B(i, itemstack);
            } else if (!(packetIn.J_1907_R() != playerentity.H_1873_g.u_1723_Y || packetIn.J_1907_R() == 0 && flag)) {
                playerentity.H_1873_g.n_1700_B(i, itemstack);
            }
        }
    }

    @Override
    public void n_1700_B(n_2740_g packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        a_2900_S container = null;
        V_772_m playerentity = this.u_1723_Y.Y_259_p;
        if (ViaLoadingBase.getInstance().getTargetVersion().newerThanOrEqualTo(ProtocolVersion.v1_17)) {
            this.n_1700_B(new V_674_I(packetIn.J_1907_R(), 0, false));
            return;
        }
        if (packetIn.J_1907_R() == 0) {
            container = playerentity.o_1800_r;
        } else if (packetIn.J_1907_R() == playerentity.H_1873_g.u_1723_Y) {
            container = playerentity.H_1873_g;
        }
        if (container != null && !packetIn.G_564_y()) {
            this.n_1700_B(new V_674_I(packetIn.J_1907_R(), packetIn.R_4764_Y(), true));
        }
    }

    @Override
    public void n_1700_B(ClientboundContainerSetContentPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        V_772_m playerentity = this.u_1723_Y.Y_259_p;
        if (packetIn.J_1907_R() == 0) {
            playerentity.o_1800_r.n_1700_B(packetIn.R_4764_Y());
        } else if (packetIn.J_1907_R() == playerentity.H_1873_g.u_1723_Y) {
            playerentity.H_1873_g.n_1700_B(packetIn.R_4764_Y());
        }
    }

    @Override
    public void n_1700_B(w_3005_z packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        i_2154_H tileentity = this.v_4262_N.getTileEntity(packetIn.J_1907_R());
        if (!(tileentity instanceof A_4313_D)) {
            tileentity = new A_4313_D();
            tileentity.J_1907_R(this.v_4262_N, packetIn.J_1907_R());
        }
        this.u_1723_Y.Y_259_p.n_1700_B((A_4313_D)tileentity);
    }

    @Override
    public void n_1700_B(ClientboundBlockEntityDataPacket packetIn) {
        boolean flag;
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        c_1514_x blockpos = packetIn.J_1907_R();
        i_2154_H tileentity = this.u_1723_Y.Y_601_j.getTileEntity(blockpos);
        int i = packetIn.R_4764_Y();
        boolean bl = flag = i == 2 && tileentity instanceof T_1368_k;
        if (i == 1 && tileentity instanceof SpawnerBlockEntity || flag || i == 3 && tileentity instanceof x_3974_Q || i == 4 && tileentity instanceof O_2639_P || i == 6 && tileentity instanceof r_4889_F || i == 7 && tileentity instanceof j_2644_e || i == 8 && tileentity instanceof A_4605_O || i == 9 && tileentity instanceof A_4313_D || i == 11 && tileentity instanceof BedBlockEntity || i == 5 && tileentity instanceof m_1551_m || i == 12 && tileentity instanceof JigsawBlockEntity || i == 13 && tileentity instanceof G_2722_I || i == 14 && tileentity instanceof F_997_G) {
            tileentity.n_1700_B(this.u_1723_Y.Y_601_j.getBlockState(blockpos), packetIn.G_564_y());
        }
        if (flag && this.u_1723_Y.Y_1740_V instanceof CommandBlockEditScreen) {
            ((CommandBlockEditScreen)this.u_1723_Y.Y_1740_V).P_1922_E();
        }
    }

    @Override
    public void n_1700_B(X_821_u packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        V_772_m playerentity = this.u_1723_Y.Y_259_p;
        if (playerentity.H_1873_g != null && playerentity.H_1873_g.u_1723_Y == packetIn.J_1907_R()) {
            playerentity.H_1873_g.n_1700_B(packetIn.R_4764_Y(), packetIn.G_564_y());
        }
    }

    @Override
    public void n_1700_B(ClientboundSetEquipmentPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.J_1907_R());
        if (entity != null) {
            packetIn.R_4764_Y().forEach(p_241664_1_ -> entity.n_1700_B((e_1174_E)((Object)((Object)p_241664_1_.getFirst())), (Z_1993_T)p_241664_1_.getSecond()));
        }
    }

    @Override
    public void n_1700_B(v_4727_z packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.Y_259_p.u_1723_Y();
    }

    @Override
    public void n_1700_B(ClientboundBlockEventPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.Y_601_j.n_1700_B(packetIn.J_1907_R(), packetIn.P_1922_E(), packetIn.R_4764_Y(), packetIn.G_564_y());
    }

    @Override
    public void n_1700_B(t_4503_H packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.Y_601_j.n_1700_B(packetIn.J_1907_R(), packetIn.R_4764_Y(), packetIn.G_564_y());
    }

    @Override
    public void n_1700_B(ClientboundGameEventPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        V_772_m playerentity = this.u_1723_Y.Y_259_p;
        ClientboundGameEventPacket.n_1700_B schangegamestatepacket$state = packetIn.J_1907_R();
        float f = packetIn.R_4764_Y();
        int i = u_530_F.G_564_y(f + 0.5f);
        if (schangegamestatepacket$state == ClientboundGameEventPacket.n_1700_B) {
            ((a_3913_L)playerentity).n_1700_B((x_282_a)new F_2904_S("block.minecraft.spawn.not_valid"), false);
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.J_1907_R) {
            this.v_4262_N.Y_259_p().n_1700_B(true);
            this.v_4262_N.t_148_a(0.0f);
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.R_4764_Y) {
            this.v_4262_N.Y_259_p().n_1700_B(false);
            this.v_4262_N.t_148_a(1.0f);
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.G_564_y) {
            this.u_1723_Y.w_1457_N.setGameType(I_14_v.n_1700_B(i));
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.P_1922_E) {
            if (i == 0) {
                this.u_1723_Y.Y_259_p.n_1700_B.n_1700_B(new H_2543_D(H_2543_D.n_1700_B.n_1700_B));
                this.u_1723_Y.n_1700_B(new G_2460_P());
            } else if (i == 1) {
                this.u_1723_Y.n_1700_B(new T_1088_H(true, () -> this.u_1723_Y.Y_259_p.n_1700_B.n_1700_B(new H_2543_D(H_2543_D.n_1700_B.n_1700_B))));
            }
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.u_1723_Y) {
            V_4423_d gamesettings = this.u_1723_Y.P_4830_p;
            if (f == 0.0f) {
                this.u_1723_Y.n_1700_B(new m_2105_M());
            } else if (f == 101.0f) {
                this.u_1723_Y.M_588_G.R_4764_Y().n_1700_B(new F_2904_S("demo.help.movement", gamesettings.O_508_d.u_2550_I(), gamesettings.r_715_M.u_2550_I(), gamesettings.A_1038_p.u_2550_I(), gamesettings.i_1637_u.u_2550_I()));
            } else if (f == 102.0f) {
                this.u_1723_Y.M_588_G.R_4764_Y().n_1700_B(new F_2904_S("demo.help.jump", gamesettings.Ping.u_2550_I()));
            } else if (f == 103.0f) {
                this.u_1723_Y.M_588_G.R_4764_Y().n_1700_B(new F_2904_S("demo.help.inventory", gamesettings.f_4016_n.u_2550_I()));
            } else if (f == 104.0f) {
                this.u_1723_Y.M_588_G.R_4764_Y().n_1700_B(new F_2904_S("demo.day.6", gamesettings.PlayerInfo.u_2550_I()));
            }
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.v_4262_N) {
            this.v_4262_N.n_1700_B(playerentity, playerentity.O_3598_v(), playerentity.X_2048_Y(), playerentity.l_2647_k(), SoundEvents.N_2525_X, D_38_f.w_1484_f, 0.18f, 0.45f);
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.w_1484_f) {
            this.v_4262_N.t_148_a(f);
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.t_148_a) {
            this.v_4262_N.v_4262_N(f);
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.s_956_w) {
            this.v_4262_N.n_1700_B(playerentity, playerentity.O_3598_v(), playerentity.X_2960_b(), playerentity.l_2647_k(), SoundEvents.W_3801_h, D_38_f.v_4262_N, 1.0f, 1.0f);
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.u_2550_I) {
            this.v_4262_N.n_1700_B(ParticleTypes.t_1786_h, playerentity.O_3598_v(), playerentity.X_2960_b(), playerentity.l_2647_k(), 0.0, 0.0, 0.0);
            if (i == 1) {
                this.v_4262_N.n_1700_B(playerentity, playerentity.O_3598_v(), playerentity.X_2960_b(), playerentity.l_2647_k(), SoundEvents.Q_2467_v, D_38_f.u_1723_Y, 1.0f, 1.0f);
            }
        } else if (schangegamestatepacket$state == ClientboundGameEventPacket.M_588_G) {
            this.u_1723_Y.Y_259_p.R_4764_Y(f == 0.0f);
        }
    }

    @Override
    public void n_1700_B(N_3369_p packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        t_3340_s mapitemrenderer = this.u_1723_Y.s_956_w.t_148_a();
        String s = G_3165_y.n_1700_B(packetIn.J_1907_R());
        F_3620_e mapdata = this.u_1723_Y.Y_601_j.n_1700_B(s);
        if (mapdata == null) {
            F_3620_e mapdata1;
            mapdata = new F_3620_e(s);
            if (mapitemrenderer.n_1700_B(s) != null && (mapdata1 = mapitemrenderer.n_1700_B(mapitemrenderer.n_1700_B(s))) != null) {
                mapdata = mapdata1;
            }
            this.u_1723_Y.Y_601_j.n_1700_B(mapdata);
        }
        packetIn.n_1700_B(mapdata);
        mapitemrenderer.n_1700_B(mapdata);
    }

    @Override
    public void n_1700_B(ClientboundLevelEventPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        if (packetIn.J_1907_R()) {
            this.u_1723_Y.Y_601_j.J_1907_R(packetIn.R_4764_Y(), packetIn.P_1922_E(), packetIn.G_564_y());
        } else {
            this.u_1723_Y.Y_601_j.R_4764_Y(packetIn.R_4764_Y(), packetIn.P_1922_E(), packetIn.G_564_y());
        }
    }

    @Override
    public void n_1700_B(ClientboundUpdateAdvancementsPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_2550_I.n_1700_B(packetIn);
    }

    @Override
    public void n_1700_B(ClientboundSelectAdvancementsTabPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        g_2336_b resourcelocation = packetIn.J_1907_R();
        if (resourcelocation == null) {
            this.u_2550_I.n_1700_B(null, false);
        } else {
            A_2629_w advancement = this.u_2550_I.n_1700_B().n_1700_B(resourcelocation);
            this.u_2550_I.n_1700_B(advancement, false);
        }
    }

    @Override
    public void n_1700_B(B_3790_C packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.t_1786_h = new CommandDispatcher(packetIn.J_1907_R());
    }

    @Override
    public void n_1700_B(ClientboundStopSoundPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.Z_976_R().n_1700_B(packetIn.J_1907_R(), packetIn.R_4764_Y());
    }

    @Override
    public void n_1700_B(ClientboundCommandSuggestionsPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.M_588_G.n_1700_B(packetIn.J_1907_R(), packetIn.R_4764_Y());
    }

    @Override
    public void n_1700_B(A_1557_z packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.multiplayerClientSuggestionProvider.n_1700_B(packetIn.J_1907_R());
        MutableSearchTree<RecipeCollection> imutablesearchtree = this.u_1723_Y.n_1700_B(SearchRegistry.R_4764_Y);
        imutablesearchtree.n_1700_B();
        c_1070_s clientrecipebook = this.u_1723_Y.Y_259_p.M_182_A();
        clientrecipebook.n_1700_B(this.multiplayerClientSuggestionProvider.J_1907_R());
        clientrecipebook.n_1700_B().forEach(imutablesearchtree::n_1700_B);
        imutablesearchtree.J_1907_R();
    }

    @Override
    public void n_1700_B(ClientboundPlayerLookAtPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        e_2866_D vector3d = packetIn.n_1700_B(this.v_4262_N);
        if (vector3d != null) {
            this.u_1723_Y.Y_259_p.n_1700_B(packetIn.J_1907_R(), vector3d);
        }
    }

    @Override
    public void n_1700_B(ClientboundTagQueryPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        if (!this.h_1847_R.n_1700_B(packetIn.J_1907_R(), packetIn.R_4764_Y())) {
            n_1700_B.debug("Got unhandled response to tag query {}", (Object)packetIn.J_1907_R());
        }
    }

    @Override
    public void n_1700_B(ClientboundAwardStatsPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        for (Map.Entry<o_98_P<?>, Integer> entry : packetIn.J_1907_R().entrySet()) {
            o_98_P<?> stat = entry.getKey();
            int i = entry.getValue();
            this.u_1723_Y.Y_259_p.Q_4569_t().n_1700_B(this.u_1723_Y.Y_259_p, stat, i);
        }
        if (this.u_1723_Y.Y_1740_V instanceof StatsUpdateListener) {
            ((StatsUpdateListener)((Object)this.u_1723_Y.Y_1740_V)).n_1700_B();
        }
    }

    @Override
    public void n_1700_B(ClientboundRecipePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        c_1070_s clientrecipebook = this.u_1723_Y.Y_259_p.M_182_A();
        clientrecipebook.n_1700_B(packetIn.G_564_y());
        ClientboundRecipePacket.n_1700_B srecipebookpacket$state = packetIn.P_1922_E();
        switch (srecipebookpacket$state) {
            case R_4764_Y: {
                for (g_2336_b resourcelocation3 : packetIn.J_1907_R()) {
                    this.multiplayerClientSuggestionProvider.n_1700_B(resourcelocation3).ifPresent(clientrecipebook::R_4764_Y);
                }
                break;
            }
            case n_1700_B: {
                for (g_2336_b resourcelocation1 : packetIn.J_1907_R()) {
                    this.multiplayerClientSuggestionProvider.n_1700_B(resourcelocation1).ifPresent(clientrecipebook::n_1700_B);
                }
                for (g_2336_b resourcelocation2 : packetIn.R_4764_Y()) {
                    this.multiplayerClientSuggestionProvider.n_1700_B(resourcelocation2).ifPresent(clientrecipebook::u_1723_Y);
                }
                break;
            }
            case J_1907_R: {
                for (g_2336_b resourcelocation : packetIn.J_1907_R()) {
                    this.multiplayerClientSuggestionProvider.n_1700_B(resourcelocation).ifPresent(p_217278_2_ -> {
                        clientrecipebook.n_1700_B((Recipe<?>)p_217278_2_);
                        clientrecipebook.u_1723_Y((Recipe<?>)p_217278_2_);
                        RecipeToast.n_1700_B(this.u_1723_Y.e_1992_r(), p_217278_2_);
                    });
                }
                break;
            }
        }
        clientrecipebook.n_1700_B().forEach(p_199527_1_ -> p_199527_1_.n_1700_B(clientrecipebook));
        if (this.u_1723_Y.Y_1740_V instanceof RecipeUpdateListener) {
            ((RecipeUpdateListener)((Object)this.u_1723_Y.Y_1740_V)).n_1700_B();
        }
    }

    @Override
    public void n_1700_B(ClientboundUpdateMobEffectPacket packetIn) {
        g_422_i effect;
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.R_4764_Y());
        if (entity instanceof r_4811_B && (effect = g_422_i.n_1700_B(packetIn.G_564_y())) != null) {
            k_2610_C effectinstance = new k_2610_C(effect, packetIn.u_1723_Y(), packetIn.P_1922_E(), packetIn.w_1484_f(), packetIn.v_4262_N(), packetIn.t_148_a());
            effectinstance.n_1700_B(packetIn.J_1907_R());
            ((r_4811_B)entity).R_4764_Y(effectinstance);
        }
    }

    @Override
    public void n_1700_B(I_4656_k packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        TagContainer itagcollectionsupplier = packetIn.J_1907_R();
        Multimap<g_2336_b, g_2336_b> multimap = O_4030_c.J_1907_R(itagcollectionsupplier);
        if (!multimap.isEmpty()) {
            this.R_4764_Y.n_1700_B(new F_2904_S("multiplayer.disconnect.missing_tags"));
        } else {
            this.P_4830_p = itagcollectionsupplier;
            if (!this.R_4764_Y.G_564_y()) {
                itagcollectionsupplier.P_1922_E();
            }
            this.u_1723_Y.n_1700_B(SearchRegistry.J_1907_R).J_1907_R();
        }
    }

    @Override
    public void n_1700_B(ClientboundPlayerCombatPacket packetIn) {
        N_4263_v entity;
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        if (packetIn.n_1700_B == ClientboundPlayerCombatPacket.n_1700_B.R_4764_Y && (entity = this.v_4262_N.J_1907_R(packetIn.J_1907_R)) == this.u_1723_Y.Y_259_p) {
            if (this.u_1723_Y.Y_259_p.multiplayerClientSuggestionProvider()) {
                for (IBaritone ibaritone : BaritoneAPI.getProvider().getAllBaritones()) {
                    V_772_m player = ibaritone.getPlayerContext().player();
                    if (player == null || player.n_1700_B != this) continue;
                    ibaritone.getGameEventHandler().onPlayerDeath();
                }
                this.u_1723_Y.n_1700_B(new E_1407_D(packetIn.P_1922_E, this.v_4262_N.Y_259_p().n_1700_B()));
            } else {
                this.u_1723_Y.Y_259_p.G_564_y();
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundChangeDifficultyPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.w_1484_f.n_1700_B(packetIn.R_4764_Y());
        this.w_1484_f.J_1907_R(packetIn.J_1907_R());
    }

    @Override
    public void n_1700_B(ClientboundSetCameraPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = packetIn.n_1700_B(this.v_4262_N);
        if (entity != null) {
            this.u_1723_Y.n_1700_B(entity);
        }
    }

    @Override
    public void n_1700_B(R_831_p packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        packetIn.n_1700_B(this.v_4262_N.H_2857_Y());
    }

    @Override
    public void n_1700_B(ClientboundSetTitlesPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        ClientboundSetTitlesPacket.n_1700_B stitlepacket$type = packetIn.J_1907_R();
        x_282_a itextcomponent = null;
        x_282_a itextcomponent1 = null;
        x_282_a itextcomponent2 = packetIn.R_4764_Y() != null ? packetIn.R_4764_Y() : U_2871_b.R_4764_Y;
        switch (stitlepacket$type) {
            case n_1700_B: {
                itextcomponent = itextcomponent2;
                break;
            }
            case J_1907_R: {
                itextcomponent1 = itextcomponent2;
                break;
            }
            case R_4764_Y: {
                this.u_1723_Y.M_588_G.n_1700_B(itextcomponent2, false);
                return;
            }
            case u_1723_Y: {
                this.u_1723_Y.M_588_G.n_1700_B((x_282_a)null, (x_282_a)null, -1, -1, -1);
                this.u_1723_Y.M_588_G.n_1700_B();
                return;
            }
        }
        this.u_1723_Y.M_588_G.n_1700_B(itextcomponent, itextcomponent1, packetIn.G_564_y(), packetIn.P_1922_E(), packetIn.u_1723_Y());
    }

    @Override
    public void n_1700_B(D_1056_N packetIn) {
        this.u_1723_Y.M_588_G.v_4262_N().J_1907_R(packetIn.J_1907_R().getString().isEmpty() ? null : packetIn.J_1907_R());
        this.u_1723_Y.M_588_G.v_4262_N().n_1700_B(packetIn.R_4764_Y().getString().isEmpty() ? null : packetIn.R_4764_Y());
    }

    @Override
    public void n_1700_B(ClientboundRemoveMobEffectPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = packetIn.n_1700_B(this.v_4262_N);
        if (entity instanceof r_4811_B) {
            ((r_4811_B)entity).n_1700_B(packetIn.J_1907_R());
        }
    }

    @Override
    public void n_1700_B(d_338_B packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        for (d_338_B.J_1907_R splayerlistitempacket$addplayerdata : packetIn.J_1907_R()) {
            if (packetIn.R_4764_Y() == d_338_B.n_1700_B.P_1922_E) {
                this.u_1723_Y.dtoRealmsServerAddress().u_1723_Y(splayerlistitempacket$addplayerdata.n_1700_B().getId());
                this.s_956_w.remove(splayerlistitempacket$addplayerdata.n_1700_B().getId());
                continue;
            }
            A_2226_Q networkplayerinfo = this.s_956_w.get(splayerlistitempacket$addplayerdata.n_1700_B().getId());
            if (packetIn.R_4764_Y() == d_338_B.n_1700_B.n_1700_B) {
                networkplayerinfo = new A_2226_Q(splayerlistitempacket$addplayerdata);
                this.s_956_w.put(networkplayerinfo.n_1700_B().getId(), networkplayerinfo);
                this.u_1723_Y.dtoRealmsServerAddress().n_1700_B(networkplayerinfo);
            }
            if (networkplayerinfo == null) continue;
            switch (packetIn.R_4764_Y()) {
                case n_1700_B: {
                    networkplayerinfo.n_1700_B(splayerlistitempacket$addplayerdata.R_4764_Y());
                    networkplayerinfo.n_1700_B(splayerlistitempacket$addplayerdata.J_1907_R());
                    networkplayerinfo.n_1700_B(splayerlistitempacket$addplayerdata.G_564_y());
                    break;
                }
                case J_1907_R: {
                    networkplayerinfo.n_1700_B(splayerlistitempacket$addplayerdata.R_4764_Y());
                    break;
                }
                case R_4764_Y: {
                    networkplayerinfo.n_1700_B(splayerlistitempacket$addplayerdata.J_1907_R());
                    break;
                }
                case G_564_y: {
                    networkplayerinfo.n_1700_B(splayerlistitempacket$addplayerdata.G_564_y());
                }
            }
        }
    }

    @Override
    public void n_1700_B(r_1873_a packetIn) {
        this.n_1700_B(new p_4692_E(packetIn.J_1907_R()));
    }

    @Override
    public void n_1700_B(ClientboundPlayerAbilitiesPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        V_772_m playerentity = this.u_1723_Y.Y_259_p;
        playerentity.C_415_h.J_1907_R = packetIn.R_4764_Y();
        playerentity.C_415_h.G_564_y = packetIn.P_1922_E();
        playerentity.C_415_h.n_1700_B = packetIn.J_1907_R();
        playerentity.C_415_h.R_4764_Y = packetIn.G_564_y();
        playerentity.C_415_h.n_1700_B(packetIn.u_1723_Y());
        playerentity.C_415_h.J_1907_R(packetIn.v_4262_N());
    }

    @Override
    public void n_1700_B(ClientboundSoundPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.Y_601_j.n_1700_B(this.u_1723_Y.Y_259_p, packetIn.G_564_y(), packetIn.P_1922_E(), packetIn.u_1723_Y(), packetIn.J_1907_R(), packetIn.R_4764_Y(), packetIn.v_4262_N(), packetIn.w_1484_f());
    }

    @Override
    public void n_1700_B(ClientboundSoundEntityPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.G_564_y());
        if (entity != null) {
            this.u_1723_Y.Y_601_j.n_1700_B((a_3913_L)this.u_1723_Y.Y_259_p, entity, packetIn.J_1907_R(), packetIn.R_4764_Y(), packetIn.P_1922_E(), packetIn.u_1723_Y());
        }
    }

    @Override
    public void n_1700_B(ClientboundCustomSoundPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.Z_976_R().n_1700_B(new SimpleSoundInstance(packetIn.J_1907_R(), packetIn.R_4764_Y(), packetIn.v_4262_N(), packetIn.w_1484_f(), false, 0, SoundInstance.n_1700_B.J_1907_R, packetIn.G_564_y(), packetIn.P_1922_E(), packetIn.u_1723_Y(), false));
    }

    @Override
    public void n_1700_B(E_3520_U packetIn) {
        String s = packetIn.J_1907_R();
        String s1 = packetIn.R_4764_Y();
        if (this.J_1907_R(s)) {
            if (s.startsWith("level://")) {
                try {
                    String s2 = URLDecoder.decode(s.substring("level://".length()), StandardCharsets.UTF_8.toString());
                    File file1 = new File(this.u_1723_Y.M_182_A, "saves");
                    File file2 = new File(file1, s2);
                    if (file2.isFile()) {
                        this.n_1700_B(ServerboundResourcePackPacket.n_1700_B.G_564_y);
                        CompletableFuture<Void> completablefuture = this.u_1723_Y.z_4693_k().n_1700_B(file2, PackSource.R_4764_Y);
                        this.n_1700_B(completablefuture);
                        return;
                    }
                }
                catch (UnsupportedEncodingException s2) {
                    // empty catch block
                }
                this.n_1700_B(ServerboundResourcePackPacket.n_1700_B.R_4764_Y);
            } else {
                ServerData serverdata = this.u_1723_Y.t_4043_B();
                if (serverdata != null && serverdata.J_1907_R() == ServerData.n_1700_B.n_1700_B) {
                    this.n_1700_B(ServerboundResourcePackPacket.n_1700_B.G_564_y);
                    this.n_1700_B(this.u_1723_Y.z_4693_k().n_1700_B(s, s1));
                } else if (serverdata != null && serverdata.J_1907_R() != ServerData.n_1700_B.R_4764_Y) {
                    this.n_1700_B(ServerboundResourcePackPacket.n_1700_B.J_1907_R);
                } else {
                    this.u_1723_Y.execute(() -> this.u_1723_Y.n_1700_B(new q_3418_t(p_217274_3_ -> {
                        this.u_1723_Y = MinecraftClient.A_4115_X();
                        ServerData serverdata1 = this.u_1723_Y.t_4043_B();
                        if (p_217274_3_) {
                            if (serverdata1 != null) {
                                serverdata1.n_1700_B(ServerData.n_1700_B.n_1700_B);
                            }
                            this.n_1700_B(ServerboundResourcePackPacket.n_1700_B.G_564_y);
                            this.n_1700_B(this.u_1723_Y.z_4693_k().n_1700_B(s, s1));
                        } else {
                            if (serverdata1 != null) {
                                serverdata1.n_1700_B(ServerData.n_1700_B.J_1907_R);
                            }
                            this.n_1700_B(ServerboundResourcePackPacket.n_1700_B.J_1907_R);
                        }
                        m_3545_A.R_4764_Y(serverdata1);
                        this.u_1723_Y.n_1700_B((k_2603_m)null);
                    }, new F_2904_S("multiplayer.texturePrompt.line1"), new F_2904_S("multiplayer.texturePrompt.line2"))));
                }
            }
        }
    }

    private boolean J_1907_R(String url) {
        try {
            URI uri = new URI(url);
            String s = uri.getScheme();
            boolean flag = "level".equals(s);
            if (!("http".equals(s) || "https".equals(s) || flag)) {
                throw new URISyntaxException(url, "Wrong protocol");
            }
            if (!flag || !url.contains("..") && url.endsWith("/resources.zip")) {
                return true;
            }
            throw new URISyntaxException(url, "Invalid levelstorage resourcepack path");
        }
        catch (URISyntaxException urisyntaxexception) {
            this.n_1700_B(ServerboundResourcePackPacket.n_1700_B.R_4764_Y);
            return false;
        }
    }

    private void n_1700_B(CompletableFuture<?> futureIn) {
        ((CompletableFuture)futureIn.thenRun(() -> this.n_1700_B(ServerboundResourcePackPacket.n_1700_B.n_1700_B))).exceptionally(p_217276_1_ -> {
            this.n_1700_B(ServerboundResourcePackPacket.n_1700_B.R_4764_Y);
            return null;
        });
    }

    private void n_1700_B(ServerboundResourcePackPacket.n_1700_B action) {
        this.R_4764_Y.n_1700_B(new ServerboundResourcePackPacket(action));
    }

    @Override
    public void n_1700_B(m_1761_s packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.M_588_G.t_148_a().n_1700_B(packetIn);
    }

    @Override
    public void n_1700_B(G_4919_s packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        if (packetIn.R_4764_Y() == 0) {
            this.u_1723_Y.Y_259_p.p_1458_L().J_1907_R(packetIn.J_1907_R());
        } else {
            this.u_1723_Y.Y_259_p.p_1458_L().n_1700_B(packetIn.J_1907_R(), packetIn.R_4764_Y());
        }
    }

    @Override
    public void n_1700_B(F_551_J packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.u_1723_Y.Y_259_p.d_3244_b();
        if (entity != this.u_1723_Y.Y_259_p && entity.v_887_r()) {
            entity.n_1700_B(packetIn.J_1907_R(), packetIn.R_4764_Y(), packetIn.G_564_y(), packetIn.P_1922_E(), packetIn.u_1723_Y());
            this.R_4764_Y.n_1700_B(new L_4122_s(entity));
        }
    }

    @Override
    public void n_1700_B(x_2680_y packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        Z_1993_T itemstack = this.u_1723_Y.Y_259_p.R_4764_Y(packetIn.J_1907_R());
        if (itemstack.J_1907_R() == Items.CryingObsidianBlock) {
            this.u_1723_Y.n_1700_B(new i_1140_L(new i_1140_L.R_4764_Y(itemstack)));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void n_1700_B(ClientboundCustomPayloadPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        g_2336_b resourcelocation = packetIn.J_1907_R();
        b_2585_i packetbuffer = null;
        ForgeNetworkEvents.onCustomPayloadClient(packetIn);
        try {
            packetbuffer = packetIn.R_4764_Y();
            if (ClientboundCustomPayloadPacket.n_1700_B.equals(resourcelocation)) {
                this.u_1723_Y.Y_259_p.J_1907_R(packetbuffer.P_1922_E(Short.MAX_VALUE));
            } else if (ClientboundCustomPayloadPacket.J_1907_R.equals(resourcelocation)) {
                int i = packetbuffer.readInt();
                float f = packetbuffer.readFloat();
                b_1722_e path = b_1722_e.n_1700_B(packetbuffer);
                this.u_1723_Y.u_2550_I.n_1700_B.n_1700_B(i, path, f);
            } else if (ClientboundCustomPayloadPacket.R_4764_Y.equals(resourcelocation)) {
                long l1 = packetbuffer.v_4262_N();
                c_1514_x blockpos9 = packetbuffer.R_4764_Y();
                ((NeighborsUpdateRenderer)this.u_1723_Y.u_2550_I.u_1723_Y).n_1700_B(l1, blockpos9);
            } else if (ClientboundCustomPayloadPacket.G_564_y.equals(resourcelocation)) {
                c_1514_x blockpos2 = packetbuffer.R_4764_Y();
                int k2 = packetbuffer.readInt();
                ArrayList list1 = Lists.newArrayList();
                ArrayList list = Lists.newArrayList();
                for (int j = 0; j < k2; ++j) {
                    list1.add(packetbuffer.R_4764_Y());
                    list.add(Float.valueOf(packetbuffer.readFloat()));
                }
                this.u_1723_Y.u_2550_I.v_4262_N.n_1700_B(blockpos2, list1, list);
            } else if (ClientboundCustomPayloadPacket.P_1922_E.equals(resourcelocation)) {
                Z_3903_F dimensiontype = this.Y_259_p.n_1700_B().n_1700_B(packetbuffer.P_4830_p());
                BoundingBox mutableboundingbox = new BoundingBox(packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt());
                int i4 = packetbuffer.readInt();
                ArrayList list2 = Lists.newArrayList();
                ArrayList list4 = Lists.newArrayList();
                for (int k = 0; k < i4; ++k) {
                    list2.add(new BoundingBox(packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt(), packetbuffer.readInt()));
                    list4.add(packetbuffer.readBoolean());
                }
                this.u_1723_Y.u_2550_I.w_1484_f.n_1700_B(mutableboundingbox, list2, list4, dimensiontype);
            } else if (ClientboundCustomPayloadPacket.u_1723_Y.equals(resourcelocation)) {
                ((WorldGenAttemptRenderer)this.u_1723_Y.u_2550_I.s_956_w).n_1700_B(packetbuffer.R_4764_Y(), packetbuffer.readFloat(), packetbuffer.readFloat(), packetbuffer.readFloat(), packetbuffer.readFloat(), packetbuffer.readFloat());
            } else if (ClientboundCustomPayloadPacket.s_956_w.equals(resourcelocation)) {
                int i2 = packetbuffer.readInt();
                for (int l2 = 0; l2 < i2; ++l2) {
                    this.u_1723_Y.u_2550_I.h_1847_R.n_1700_B(packetbuffer.G_564_y());
                }
                int i3 = packetbuffer.readInt();
                for (int j4 = 0; j4 < i3; ++j4) {
                    this.u_1723_Y.u_2550_I.h_1847_R.J_1907_R(packetbuffer.G_564_y());
                }
            } else if (ClientboundCustomPayloadPacket.w_1484_f.equals(resourcelocation)) {
                c_1514_x blockpos3 = packetbuffer.R_4764_Y();
                String s8 = packetbuffer.M_588_G();
                int k4 = packetbuffer.readInt();
                Y_158_B.J_1907_R pointofinterestdebugrenderer$poiinfo = new Y_158_B.J_1907_R(blockpos3, s8, k4);
                this.u_1723_Y.u_2550_I.P_4830_p.n_1700_B(pointofinterestdebugrenderer$poiinfo);
            } else if (ClientboundCustomPayloadPacket.t_148_a.equals(resourcelocation)) {
                c_1514_x blockpos4 = packetbuffer.R_4764_Y();
                this.u_1723_Y.u_2550_I.P_4830_p.n_1700_B(blockpos4);
            } else if (ClientboundCustomPayloadPacket.v_4262_N.equals(resourcelocation)) {
                c_1514_x blockpos5 = packetbuffer.R_4764_Y();
                int j3 = packetbuffer.readInt();
                this.u_1723_Y.u_2550_I.P_4830_p.n_1700_B(blockpos5, j3);
            } else if (ClientboundCustomPayloadPacket.u_2550_I.equals(resourcelocation)) {
                c_1514_x blockpos6 = packetbuffer.R_4764_Y();
                int k3 = packetbuffer.readInt();
                int l4 = packetbuffer.readInt();
                ArrayList list3 = Lists.newArrayList();
                for (int i6 = 0; i6 < l4; ++i6) {
                    int j6 = packetbuffer.readInt();
                    boolean flag = packetbuffer.readBoolean();
                    String s = packetbuffer.P_1922_E(255);
                    list3.add(new GoalSelectorDebugRenderer.n_1700_B(blockpos6, j6, s, flag));
                }
                this.u_1723_Y.u_2550_I.t_1786_h.n_1700_B(k3, list3);
            } else if (ClientboundCustomPayloadPacket.t_1786_h.equals(resourcelocation)) {
                int j2 = packetbuffer.readInt();
                ArrayList collection = Lists.newArrayList();
                for (int i5 = 0; i5 < j2; ++i5) {
                    collection.add(packetbuffer.R_4764_Y());
                }
                this.u_1723_Y.u_2550_I.M_182_A.n_1700_B(collection);
            } else if (ClientboundCustomPayloadPacket.M_588_G.equals(resourcelocation)) {
                double d0 = packetbuffer.readDouble();
                double d2 = packetbuffer.readDouble();
                double d4 = packetbuffer.readDouble();
                PositionImpl iposition = new PositionImpl(d0, d2, d4);
                UUID uuid = packetbuffer.w_1484_f();
                int l = packetbuffer.readInt();
                String s1 = packetbuffer.M_588_G();
                String s2 = packetbuffer.M_588_G();
                int i1 = packetbuffer.readInt();
                float f1 = packetbuffer.readFloat();
                float f2 = packetbuffer.readFloat();
                String s3 = packetbuffer.M_588_G();
                boolean flag1 = packetbuffer.readBoolean();
                b_1722_e path1 = flag1 ? b_1722_e.n_1700_B(packetbuffer) : null;
                boolean flag2 = packetbuffer.readBoolean();
                Y_158_B.n_1700_B pointofinterestdebugrenderer$braininfo = new Y_158_B.n_1700_B(uuid, l, s1, s2, i1, f1, f2, iposition, s3, path1, flag2);
                int j1 = packetbuffer.readInt();
                for (int k1 = 0; k1 < j1; ++k1) {
                    String s4 = packetbuffer.M_588_G();
                    pointofinterestdebugrenderer$braininfo.M_588_G.add(s4);
                }
                int i8 = packetbuffer.readInt();
                for (int j8 = 0; j8 < i8; ++j8) {
                    String s5 = packetbuffer.M_588_G();
                    pointofinterestdebugrenderer$braininfo.P_4830_p.add(s5);
                }
                int k8 = packetbuffer.readInt();
                for (int l8 = 0; l8 < k8; ++l8) {
                    String s6 = packetbuffer.M_588_G();
                    pointofinterestdebugrenderer$braininfo.h_1847_R.add(s6);
                }
                int i9 = packetbuffer.readInt();
                for (int j9 = 0; j9 < i9; ++j9) {
                    c_1514_x blockpos = packetbuffer.R_4764_Y();
                    pointofinterestdebugrenderer$braininfo.M_182_A.add(blockpos);
                }
                int k9 = packetbuffer.readInt();
                for (int l9 = 0; l9 < k9; ++l9) {
                    c_1514_x blockpos1 = packetbuffer.R_4764_Y();
                    pointofinterestdebugrenderer$braininfo.t_1786_h.add(blockpos1);
                }
                int i10 = packetbuffer.readInt();
                for (int j10 = 0; j10 < i10; ++j10) {
                    String s7 = packetbuffer.M_588_G();
                    pointofinterestdebugrenderer$braininfo.Q_4569_t.add(s7);
                }
                this.u_1723_Y.u_2550_I.P_4830_p.n_1700_B(pointofinterestdebugrenderer$braininfo);
            } else if (ClientboundCustomPayloadPacket.P_4830_p.equals(resourcelocation)) {
                double d1 = packetbuffer.readDouble();
                double d3 = packetbuffer.readDouble();
                double d5 = packetbuffer.readDouble();
                PositionImpl iposition1 = new PositionImpl(d1, d3, d5);
                UUID uuid1 = packetbuffer.w_1484_f();
                int k6 = packetbuffer.readInt();
                boolean flag4 = packetbuffer.readBoolean();
                c_1514_x blockpos10 = null;
                if (flag4) {
                    blockpos10 = packetbuffer.R_4764_Y();
                }
                boolean flag5 = packetbuffer.readBoolean();
                c_1514_x blockpos11 = null;
                if (flag5) {
                    blockpos11 = packetbuffer.R_4764_Y();
                }
                int l6 = packetbuffer.readInt();
                boolean flag6 = packetbuffer.readBoolean();
                b_1722_e path2 = null;
                if (flag6) {
                    path2 = b_1722_e.n_1700_B(packetbuffer);
                }
                S_3601_T.n_1700_B beedebugrenderer$bee = new S_3601_T.n_1700_B(uuid1, k6, iposition1, path2, blockpos10, blockpos11, l6);
                int i7 = packetbuffer.readInt();
                for (int j7 = 0; j7 < i7; ++j7) {
                    String s11 = packetbuffer.M_588_G();
                    beedebugrenderer$bee.w_1484_f.add(s11);
                }
                int k7 = packetbuffer.readInt();
                for (int l7 = 0; l7 < k7; ++l7) {
                    c_1514_x blockpos12 = packetbuffer.R_4764_Y();
                    beedebugrenderer$bee.t_148_a.add(blockpos12);
                }
                this.u_1723_Y.u_2550_I.Q_4569_t.n_1700_B(beedebugrenderer$bee);
            } else if (ClientboundCustomPayloadPacket.h_1847_R.equals(resourcelocation)) {
                c_1514_x blockpos7 = packetbuffer.R_4764_Y();
                String s9 = packetbuffer.M_588_G();
                int j5 = packetbuffer.readInt();
                int k5 = packetbuffer.readInt();
                boolean flag3 = packetbuffer.readBoolean();
                S_3601_T.J_1907_R beedebugrenderer$hive = new S_3601_T.J_1907_R(blockpos7, s9, j5, k5, flag3, this.v_4262_N.X_933_l());
                this.u_1723_Y.u_2550_I.Q_4569_t.n_1700_B(beedebugrenderer$hive);
            } else if (ClientboundCustomPayloadPacket.M_182_A.equals(resourcelocation)) {
                this.u_1723_Y.u_2550_I.multiplayerClientSuggestionProvider.n_1700_B();
            } else if (ClientboundCustomPayloadPacket.Q_4569_t.equals(resourcelocation)) {
                c_1514_x blockpos8 = packetbuffer.R_4764_Y();
                int l3 = packetbuffer.readInt();
                String s10 = packetbuffer.M_588_G();
                int l5 = packetbuffer.readInt();
                this.u_1723_Y.u_2550_I.multiplayerClientSuggestionProvider.n_1700_B(blockpos8, l3, s10, l5);
            }
        }
        finally {
            if (packetbuffer != null) {
                packetbuffer.release();
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundSetObjectivePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        i_4895_l scoreboard = this.v_4262_N.Q_4569_t();
        String s = packetIn.J_1907_R();
        if (packetIn.G_564_y() == 0) {
            scoreboard.n_1700_B(s, M_1462_J.J_1907_R, packetIn.R_4764_Y(), packetIn.P_1922_E());
        } else if (scoreboard.n_1700_B(s)) {
            Objective scoreobjective = scoreboard.R_4764_Y(s);
            if (packetIn.G_564_y() == 1) {
                scoreboard.J_1907_R(scoreobjective);
            } else if (packetIn.G_564_y() == 2) {
                scoreobjective.n_1700_B(packetIn.P_1922_E());
                scoreobjective.n_1700_B(packetIn.R_4764_Y());
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundSetScorePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        i_4895_l scoreboard = this.v_4262_N.Q_4569_t();
        String s = packetIn.R_4764_Y();
        switch (packetIn.P_1922_E()) {
            case n_1700_B: {
                Objective scoreobjective = scoreboard.J_1907_R(s);
                v_4839_y score = scoreboard.J_1907_R(packetIn.J_1907_R(), scoreobjective);
                score.J_1907_R(packetIn.G_564_y());
                break;
            }
            case J_1907_R: {
                scoreboard.R_4764_Y(packetIn.J_1907_R(), scoreboard.R_4764_Y(s));
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundSetDisplayObjectivePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        i_4895_l scoreboard = this.v_4262_N.Q_4569_t();
        String s = packetIn.R_4764_Y();
        Objective scoreobjective = s == null ? null : scoreboard.J_1907_R(s);
        scoreboard.n_1700_B(packetIn.J_1907_R(), scoreobjective);
    }

    @Override
    public void n_1700_B(ClientboundSetPlayerTeamPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        i_4895_l scoreboard = this.v_4262_N.Q_4569_t();
        PlayerTeam scoreplayerteam = packetIn.P_1922_E() == 0 ? scoreboard.u_1723_Y(packetIn.J_1907_R()) : scoreboard.P_1922_E(packetIn.J_1907_R());
        if (scoreplayerteam == null) {
            return;
        }
        if (packetIn.P_1922_E() == 0 || packetIn.P_1922_E() == 2) {
            o_3050_h.n_1700_B team$collisionrule;
            scoreplayerteam.n_1700_B(packetIn.R_4764_Y());
            scoreplayerteam.n_1700_B(packetIn.v_4262_N());
            scoreplayerteam.n_1700_B(packetIn.u_1723_Y());
            o_3050_h.J_1907_R team$visible = o_3050_h.J_1907_R.n_1700_B(packetIn.w_1484_f());
            if (team$visible != null) {
                scoreplayerteam.n_1700_B(team$visible);
            }
            if ((team$collisionrule = o_3050_h.n_1700_B.n_1700_B(packetIn.t_148_a())) != null) {
                scoreplayerteam.n_1700_B(team$collisionrule);
            }
            scoreplayerteam.J_1907_R(packetIn.s_956_w());
            scoreplayerteam.R_4764_Y(packetIn.u_2550_I());
        }
        if (packetIn.P_1922_E() == 0 || packetIn.P_1922_E() == 3) {
            for (String s : packetIn.G_564_y()) {
                scoreboard.n_1700_B(s, scoreplayerteam);
            }
        }
        if (packetIn.P_1922_E() == 4) {
            for (String s1 : packetIn.G_564_y()) {
                scoreboard.J_1907_R(s1, scoreplayerteam);
            }
        }
        if (packetIn.P_1922_E() == 1) {
            scoreboard.n_1700_B(scoreplayerteam);
        }
    }

    @Override
    public void n_1700_B(ClientboundLevelParticlesPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        if (packetIn.s_956_w() == 0) {
            double d0 = packetIn.t_148_a() * packetIn.u_1723_Y();
            double d2 = packetIn.t_148_a() * packetIn.v_4262_N();
            double d4 = packetIn.t_148_a() * packetIn.w_1484_f();
            try {
                this.v_4262_N.n_1700_B(packetIn.u_2550_I(), packetIn.J_1907_R(), packetIn.R_4764_Y(), packetIn.G_564_y(), packetIn.P_1922_E(), d0, d2, d4);
            }
            catch (Throwable throwable) {}
        } else {
            for (int i = 0; i < packetIn.s_956_w(); ++i) {
                double d1 = this.M_182_A.nextGaussian() * (double)packetIn.u_1723_Y();
                double d3 = this.M_182_A.nextGaussian() * (double)packetIn.v_4262_N();
                double d5 = this.M_182_A.nextGaussian() * (double)packetIn.w_1484_f();
                double d6 = this.M_182_A.nextGaussian() * (double)packetIn.t_148_a();
                double d7 = this.M_182_A.nextGaussian() * (double)packetIn.t_148_a();
                double d8 = this.M_182_A.nextGaussian() * (double)packetIn.t_148_a();
                try {
                    this.v_4262_N.n_1700_B(packetIn.u_2550_I(), packetIn.J_1907_R(), packetIn.R_4764_Y() + d1, packetIn.G_564_y() + d3, packetIn.P_1922_E() + d5, d6, d7, d8);
                    continue;
                }
                catch (Throwable throwable) {
                    return;
                }
            }
        }
    }

    @Override
    public void n_1700_B(ClientboundUpdateAttributesPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        N_4263_v entity = this.v_4262_N.J_1907_R(packetIn.J_1907_R());
        if (entity != null) {
            if (!(entity instanceof r_4811_B)) {
                throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + String.valueOf(entity) + ")");
            }
            AttributeMap attributemodifiermanager = ((r_4811_B)entity).B_1146_q();
            for (ClientboundUpdateAttributesPacket.n_1700_B sentitypropertiespacket$snapshot : packetIn.R_4764_Y()) {
                A_4388_s modifiableattributeinstance = attributemodifiermanager.n_1700_B(sentitypropertiespacket$snapshot.n_1700_B());
                if (modifiableattributeinstance == null) continue;
                modifiableattributeinstance.n_1700_B(sentitypropertiespacket$snapshot.J_1907_R());
                modifiableattributeinstance.P_1922_E();
                for (U_1880_G attributemodifier : sentitypropertiespacket$snapshot.R_4764_Y()) {
                    modifiableattributeinstance.J_1907_R(attributemodifier);
                }
            }
        }
    }

    @Override
    public void n_1700_B(W_1158_a packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        a_2900_S container = this.u_1723_Y.Y_259_p.H_1873_g;
        if (container.u_1723_Y == packetIn.R_4764_Y() && container.R_4764_Y(this.u_1723_Y.Y_259_p)) {
            this.multiplayerClientSuggestionProvider.n_1700_B(packetIn.J_1907_R()).ifPresent(p_241665_2_ -> {
                if (this.u_1723_Y.Y_1740_V instanceof RecipeUpdateListener) {
                    j_4436_c recipebookgui = ((RecipeUpdateListener)((Object)this.u_1723_Y.Y_1740_V)).J_1907_R();
                    recipebookgui.n_1700_B((Recipe<?>)p_241665_2_, container.P_1922_E);
                }
            });
        }
    }

    @Override
    public void n_1700_B(ClientboundLightUpdatePacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        int i = packetIn.J_1907_R();
        int j = packetIn.R_4764_Y();
        R_1900_x worldlightmanager = this.v_4262_N.v_4262_N().G_564_y();
        int k = packetIn.G_564_y();
        int l = packetIn.P_1922_E();
        Iterator<byte[]> iterator = packetIn.u_1723_Y().iterator();
        this.n_1700_B(i, j, worldlightmanager, K_4719_o.n_1700_B, k, l, iterator, packetIn.s_956_w());
        int i1 = packetIn.v_4262_N();
        int j1 = packetIn.w_1484_f();
        Iterator<byte[]> iterator1 = packetIn.t_148_a().iterator();
        this.n_1700_B(i, j, worldlightmanager, K_4719_o.J_1907_R, i1, j1, iterator1, packetIn.s_956_w());
    }

    @Override
    public void n_1700_B(ClientboundMerchantOffersPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        a_2900_S container = this.u_1723_Y.Y_259_p.H_1873_g;
        if (packetIn.J_1907_R() == container.u_1723_Y && container instanceof K_3710_b) {
            ((K_3710_b)container).n_1700_B(new MerchantOffers(packetIn.R_4764_Y().n_1700_B()));
            ((K_3710_b)container).P_1922_E(packetIn.P_1922_E());
            ((K_3710_b)container).u_1723_Y(packetIn.G_564_y());
            ((K_3710_b)container).n_1700_B(packetIn.u_1723_Y());
            ((K_3710_b)container).J_1907_R(packetIn.v_4262_N());
        }
    }

    @Override
    public void n_1700_B(n_4563_y packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.Q_4569_t = packetIn.J_1907_R();
        this.v_4262_N.v_4262_N().n_1700_B(packetIn.J_1907_R());
    }

    @Override
    public void n_1700_B(p_198_K packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.v_4262_N.v_4262_N().J_1907_R(packetIn.J_1907_R(), packetIn.R_4764_Y());
        this.M_182_A();
    }

    @Override
    public void n_1700_B(ClientboundBlockBreakAckPacket packetIn) {
        v_1937_d.n_1700_B(packetIn, this, this.u_1723_Y);
        this.u_1723_Y.w_1457_N.acknowledgePlayerDiggingReceived(this.v_4262_N, packetIn.R_4764_Y(), packetIn.J_1907_R(), packetIn.P_1922_E(), packetIn.G_564_y());
    }

    private void n_1700_B(int chunkX, int chunkZ, R_1900_x lightManager, K_4719_o type, int p_217284_5_, int p_217284_6_, Iterator<byte[]> p_217284_7_, boolean p_217284_8_) {
        for (int i = 0; i < 18; ++i) {
            boolean flag1;
            int j = -1 + i;
            boolean flag = (p_217284_5_ & 1 << i) != 0;
            boolean bl = flag1 = (p_217284_6_ & 1 << i) != 0;
            if (!flag && !flag1) continue;
            lightManager.n_1700_B(type, SectionPos.n_1700_B(chunkX, j, chunkZ), flag ? new DataLayer((byte[])p_217284_7_.next().clone()) : new DataLayer(), p_217284_8_);
            this.v_4262_N.J_1907_R(chunkX, j, chunkZ);
        }
    }

    @Override
    public c_1633_k getNetworkManager() {
        return this.R_4764_Y;
    }

    public void G_564_y() {
        MinecraftClient mc = MinecraftClient.A_4115_X();
        if (mc == null || !(mc.Y_1740_V instanceof G_2460_P)) {
            return;
        }
        this.t_148_a = true;
        mc.n_1700_B((k_2603_m)null);
    }

    private void M_182_A() {
        try {
            ViaLoadingBase base = ViaLoadingBase.getInstance();
            if (base == null || base.getTargetVersion().getVersion() == 754) {
                return;
            }
        }
        catch (Throwable ignored) {
            return;
        }
        this.G_564_y();
    }

    @Override
    public t_1786_h getBotNetwork() {
        return null;
    }

    public Collection<A_2226_Q> P_1922_E() {
        return this.s_956_w.values();
    }

    public Collection<UUID> u_1723_Y() {
        return this.s_956_w.keySet();
    }

    @Nullable
    public A_2226_Q n_1700_B(UUID uniqueId) {
        return this.s_956_w.get(uniqueId);
    }

    @Nullable
    public A_2226_Q n_1700_B(String name) {
        for (A_2226_Q networkplayerinfo : this.s_956_w.values()) {
            if (!networkplayerinfo.n_1700_B().getName().equals(name)) continue;
            return networkplayerinfo;
        }
        return null;
    }

    public GameProfile v_4262_N() {
        return this.G_564_y;
    }

    public ClientAdvancements w_1484_f() {
        return this.u_2550_I;
    }

    public CommandDispatcher<V_4217_p> t_148_a() {
        return this.t_1786_h;
    }

    public k_4690_i s_956_w() {
        return this.v_4262_N;
    }

    public TagContainer u_2550_I() {
        return this.P_4830_p;
    }

    public DebugQueryHandler M_588_G() {
        return this.h_1847_R;
    }

    public UUID P_4830_p() {
        return this.w_1457_N;
    }

    public Set<f_2392_k<b_4507_u>> h_1847_R() {
        return this.Y_601_j;
    }

    public r_4097_j Q_4569_t() {
        return this.Y_259_p;
    }

    public void J_1907_R(Packet<?> packetIn) {
        this.R_4764_Y.J_1907_R(packetIn);
    }
}



