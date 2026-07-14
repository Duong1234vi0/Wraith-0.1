/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Sets
 *  com.mojang.datafixers.DataFixer
 *  com.mojang.serialization.Dynamic
 *  com.mojang.serialization.DynamicOps
 *  com.mojang.serialization.Lifecycle
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.Lifecycle;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import lightning.product.A_2352_Z;
import lightning.product.B_4315_y;
import lightning.product.StringTag;
import lightning.product.H_1033_y;
import lightning.product.I_14_v;
import lightning.product.SerializableUUID;
import lightning.product.SharedConstants;
import lightning.product.RegistryWriteOps;
import lightning.product.O_1568_Z;
import lightning.product.R_2450_T;
import lightning.product.T_603_v;
import lightning.product.U_2912_j;
import lightning.product.Tag;
import lightning.product.Z_1125_b;
import lightning.product.c_1514_x;
import lightning.product.ServerLevelData;
import lightning.product.DataPackConfig;
import lightning.product.j_3341_s;
import lightning.product.j_419_j;
import lightning.product.l_4118_l;
import lightning.product.WorldData;
import lightning.product.n_3832_I;
import lightning.product.o_1967_f;
import lightning.product.q_2896_o;
import lightning.product.CrashReportCategory;
import lightning.product.r_4097_j;
import net.minecraft.server.G_564_y;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class c_3102_J
implements ServerLevelData,
WorldData {
    private static final Logger n_1700_B = LogManager.getLogger();
    private B_4315_y J_1907_R;
    private final j_419_j R_4764_Y;
    private final Lifecycle G_564_y;
    private int P_1922_E;
    private int u_1723_Y;
    private int v_4262_N;
    private float w_1484_f;
    private long t_148_a;
    private long s_956_w;
    @Nullable
    private final DataFixer u_2550_I;
    private final int M_588_G;
    private boolean P_4830_p;
    @Nullable
    private U_2912_j h_1847_R;
    private final int Q_4569_t;
    private int M_182_A;
    private boolean t_1786_h;
    private int multiplayerClientSuggestionProvider;
    private boolean w_1457_N;
    private int Y_601_j;
    private boolean Y_259_p;
    private boolean Q_2552_b;
    private T_603_v.R_4764_Y C_2741_M;
    private U_2912_j k_2293_S;
    @Nullable
    private U_2912_j q_2307_F;
    private int Z_875_P;
    private int c_3005_b;
    @Nullable
    private UUID H_2857_Y;
    private final Set<String> A_4115_X;
    private boolean Y_1740_V;
    private final Z_1125_b<G_564_y> t_4043_B;

    private c_3102_J(@Nullable DataFixer dataFixer, int version, @Nullable U_2912_j loadedPlayerNBT, boolean wasModded, int spawnX, int spawnY, int spawnZ, float spawnAngle, long gameTime, long dayTime, int levelStorageVersion, int clearWeatherTime, int rainTime, boolean raining, int thunderTime, boolean thundering, boolean initialized, boolean difficultyLocked, T_603_v.R_4764_Y borderSerializer, int wanderingTraderSpawnDelay, int wanderingTraderSpawnChance, @Nullable UUID wanderingTraderID, LinkedHashSet<String> serverBrands, Z_1125_b<G_564_y> schedueledEvents, @Nullable U_2912_j customBossEventNBT, U_2912_j dragonFightNBT, B_4315_y worldSettings, j_419_j generatorSettings, Lifecycle lifecycle) {
        this.u_2550_I = dataFixer;
        this.Y_1740_V = wasModded;
        this.P_1922_E = spawnX;
        this.u_1723_Y = spawnY;
        this.v_4262_N = spawnZ;
        this.w_1484_f = spawnAngle;
        this.t_148_a = gameTime;
        this.s_956_w = dayTime;
        this.Q_4569_t = levelStorageVersion;
        this.M_182_A = clearWeatherTime;
        this.multiplayerClientSuggestionProvider = rainTime;
        this.t_1786_h = raining;
        this.Y_601_j = thunderTime;
        this.w_1457_N = thundering;
        this.Y_259_p = initialized;
        this.Q_2552_b = difficultyLocked;
        this.C_2741_M = borderSerializer;
        this.Z_875_P = wanderingTraderSpawnDelay;
        this.c_3005_b = wanderingTraderSpawnChance;
        this.H_2857_Y = wanderingTraderID;
        this.A_4115_X = serverBrands;
        this.h_1847_R = loadedPlayerNBT;
        this.M_588_G = version;
        this.t_4043_B = schedueledEvents;
        this.q_2307_F = customBossEventNBT;
        this.k_2293_S = dragonFightNBT;
        this.J_1907_R = worldSettings;
        this.R_4764_Y = generatorSettings;
        this.G_564_y = lifecycle;
    }

    public c_3102_J(B_4315_y worldSettings, j_419_j generatorSettings, Lifecycle lifecycle) {
        this(null, SharedConstants.n_1700_B().getWorldVersion(), null, false, 0, 0, 0, 0.0f, 0L, 0L, 19133, 0, 0, false, 0, false, false, false, T_603_v.J_1907_R, 0, 0, null, Sets.newLinkedHashSet(), new Z_1125_b<G_564_y>(O_1568_Z.n_1700_B), null, new U_2912_j(), worldSettings.w_1484_f(), generatorSettings, lifecycle);
    }

    public static c_3102_J n_1700_B(Dynamic<Tag> dynamic, DataFixer dataFixer, int version, @Nullable U_2912_j playerNBT, B_4315_y worldSettings, H_1033_y versionData, j_419_j generatorSettings, Lifecycle lifecycle) {
        long i = dynamic.get("Time").asLong(0L);
        U_2912_j compoundnbt = (U_2912_j)dynamic.get("DragonFight").result().map(Dynamic::getValue).orElseGet(() -> (Tag)dynamic.get("DimensionData").get("1").get("DragonFight").orElseEmptyMap().getValue());
        return new c_3102_J(dataFixer, version, playerNBT, dynamic.get("WasModded").asBoolean(false), dynamic.get("SpawnX").asInt(0), dynamic.get("SpawnY").asInt(0), dynamic.get("SpawnZ").asInt(0), dynamic.get("SpawnAngle").asFloat(0.0f), i, dynamic.get("DayTime").asLong(i), versionData.n_1700_B(), dynamic.get("clearWeatherTime").asInt(0), dynamic.get("rainTime").asInt(0), dynamic.get("raining").asBoolean(false), dynamic.get("thunderTime").asInt(0), dynamic.get("thundering").asBoolean(false), dynamic.get("initialized").asBoolean(true), dynamic.get("DifficultyLocked").asBoolean(false), T_603_v.R_4764_Y.n_1700_B(dynamic, T_603_v.J_1907_R), dynamic.get("WanderingTraderSpawnDelay").asInt(0), dynamic.get("WanderingTraderSpawnChance").asInt(0), dynamic.get("WanderingTraderId").read(SerializableUUID.n_1700_B).result().orElse(null), dynamic.get("ServerBrands").asStream().flatMap(nbt -> j_3341_s.n_1700_B(nbt.asString().result())).collect(Collectors.toCollection(Sets::newLinkedHashSet)), new Z_1125_b<G_564_y>(O_1568_Z.n_1700_B, dynamic.get("ScheduledEvents").asStream()), (U_2912_j)dynamic.get("CustomBossEvents").orElseEmptyMap().getValue(), compoundnbt, worldSettings, generatorSettings, lifecycle);
    }

    @Override
    public U_2912_j n_1700_B(r_4097_j registries, @Nullable U_2912_j hostPlayerNBT) {
        this.d_2427_y();
        if (hostPlayerNBT == null) {
            hostPlayerNBT = this.h_1847_R;
        }
        U_2912_j compoundnbt = new U_2912_j();
        this.n_1700_B(registries, compoundnbt, hostPlayerNBT);
        return compoundnbt;
    }

    private void n_1700_B(r_4097_j registry, U_2912_j nbt, @Nullable U_2912_j playerNBT) {
        q_2896_o listnbt = new q_2896_o();
        this.A_4115_X.stream().map(StringTag::n_1700_B).forEach(listnbt::add);
        nbt.n_1700_B("ServerBrands", listnbt);
        nbt.n_1700_B("WasModded", this.Y_1740_V);
        U_2912_j compoundnbt = new U_2912_j();
        compoundnbt.n_1700_B("Name", SharedConstants.n_1700_B().getName());
        compoundnbt.J_1907_R("Id", SharedConstants.n_1700_B().getWorldVersion());
        compoundnbt.n_1700_B("Snapshot", !SharedConstants.n_1700_B().isStable());
        nbt.n_1700_B("Version", compoundnbt);
        nbt.J_1907_R("DataVersion", SharedConstants.n_1700_B().getWorldVersion());
        RegistryWriteOps<Tag> worldgensettingsexport = RegistryWriteOps.n_1700_B(l_4118_l.n_1700_B, registry);
        j_419_j.n_1700_B.encodeStart(worldgensettingsexport, (Object)this.R_4764_Y).resultOrPartial(j_3341_s.n_1700_B("WorldGenSettings: ", arg_0 -> ((Logger)n_1700_B).error(arg_0))).ifPresent(worldSettingsNBT -> nbt.n_1700_B("WorldGenSettings", (Tag)worldSettingsNBT));
        nbt.J_1907_R("GameType", this.J_1907_R.J_1907_R().n_1700_B());
        nbt.J_1907_R("SpawnX", this.P_1922_E);
        nbt.J_1907_R("SpawnY", this.u_1723_Y);
        nbt.J_1907_R("SpawnZ", this.v_4262_N);
        nbt.n_1700_B("SpawnAngle", this.w_1484_f);
        nbt.n_1700_B("Time", this.t_148_a);
        nbt.n_1700_B("DayTime", this.s_956_w);
        nbt.n_1700_B("LastPlayed", j_3341_s.G_564_y());
        nbt.n_1700_B("LevelName", this.J_1907_R.n_1700_B());
        nbt.J_1907_R("version", 19133);
        nbt.J_1907_R("clearWeatherTime", this.M_182_A);
        nbt.J_1907_R("rainTime", this.multiplayerClientSuggestionProvider);
        nbt.n_1700_B("raining", this.t_1786_h);
        nbt.J_1907_R("thunderTime", this.Y_601_j);
        nbt.n_1700_B("thundering", this.w_1457_N);
        nbt.n_1700_B("hardcore", this.J_1907_R.R_4764_Y());
        nbt.n_1700_B("allowCommands", this.J_1907_R.P_1922_E());
        nbt.n_1700_B("initialized", this.Y_259_p);
        this.C_2741_M.n_1700_B(nbt);
        nbt.n_1700_B("Difficulty", (byte)this.J_1907_R.G_564_y().n_1700_B());
        nbt.n_1700_B("DifficultyLocked", this.Q_2552_b);
        nbt.n_1700_B("GameRules", this.J_1907_R.u_1723_Y().n_1700_B());
        nbt.n_1700_B("DragonFight", this.k_2293_S);
        if (playerNBT != null) {
            nbt.n_1700_B("Player", playerNBT);
        }
        DataPackConfig.J_1907_R.encodeStart((DynamicOps)l_4118_l.n_1700_B, (Object)this.J_1907_R.v_4262_N()).result().ifPresent(dataPacksNBT -> nbt.n_1700_B("DataPacks", (Tag)dataPacksNBT));
        if (this.q_2307_F != null) {
            nbt.n_1700_B("CustomBossEvents", this.q_2307_F);
        }
        nbt.n_1700_B("ScheduledEvents", this.t_4043_B.J_1907_R());
        nbt.J_1907_R("WanderingTraderSpawnDelay", this.Z_875_P);
        nbt.J_1907_R("WanderingTraderSpawnChance", this.c_3005_b);
        if (this.H_2857_Y != null) {
            nbt.n_1700_B("WanderingTraderId", this.H_2857_Y);
        }
    }

    @Override
    public int J_1907_R() {
        return this.P_1922_E;
    }

    @Override
    public int R_4764_Y() {
        return this.u_1723_Y;
    }

    @Override
    public int G_564_y() {
        return this.v_4262_N;
    }

    @Override
    public float w_1484_f() {
        return this.w_1484_f;
    }

    @Override
    public long P_1922_E() {
        return this.t_148_a;
    }

    @Override
    public long u_1723_Y() {
        return this.s_956_w;
    }

    private void d_2427_y() {
        if (!this.P_4830_p && this.h_1847_R != null) {
            if (this.M_588_G < SharedConstants.n_1700_B().getWorldVersion()) {
                if (this.u_2550_I == null) {
                    throw j_3341_s.R_4764_Y(new NullPointerException("Fixer Upper not set inside LevelData, and the player tag is not upgraded."));
                }
                this.h_1847_R = n_3832_I.n_1700_B(this.u_2550_I, o_1967_f.J_1907_R, this.h_1847_R, this.M_588_G);
            }
            this.P_4830_p = true;
        }
    }

    @Override
    public U_2912_j t_4043_B() {
        this.d_2427_y();
        return this.h_1847_R;
    }

    @Override
    public void n_1700_B(int x) {
        this.P_1922_E = x;
    }

    @Override
    public void J_1907_R(int y) {
        this.u_1723_Y = y;
    }

    @Override
    public void R_4764_Y(int z) {
        this.v_4262_N = z;
    }

    @Override
    public void n_1700_B(float angle) {
        this.w_1484_f = angle;
    }

    @Override
    public void n_1700_B(long time) {
        this.t_148_a = time;
    }

    @Override
    public void J_1907_R(long time) {
        this.s_956_w = time;
    }

    @Override
    public void n_1700_B(c_1514_x spawnPoint, float angle) {
        this.P_1922_E = spawnPoint.getX();
        this.u_1723_Y = spawnPoint.getY();
        this.v_4262_N = spawnPoint.getZ();
        this.w_1484_f = angle;
    }

    @Override
    public String P_4830_p() {
        return this.J_1907_R.n_1700_B();
    }

    @Override
    public int Y_1740_V() {
        return this.Q_4569_t;
    }

    @Override
    public int h_1847_R() {
        return this.M_182_A;
    }

    @Override
    public void G_564_y(int time) {
        this.M_182_A = time;
    }

    @Override
    public boolean t_148_a() {
        return this.w_1457_N;
    }

    @Override
    public void J_1907_R(boolean thunderingIn) {
        this.w_1457_N = thunderingIn;
    }

    @Override
    public int Q_4569_t() {
        return this.Y_601_j;
    }

    @Override
    public void P_1922_E(int time) {
        this.Y_601_j = time;
    }

    @Override
    public boolean v_4262_N() {
        return this.t_1786_h;
    }

    @Override
    public void n_1700_B(boolean isRaining) {
        this.t_1786_h = isRaining;
    }

    @Override
    public int M_182_A() {
        return this.multiplayerClientSuggestionProvider;
    }

    @Override
    public void u_1723_Y(int time) {
        this.multiplayerClientSuggestionProvider = time;
    }

    @Override
    public I_14_v t_1786_h() {
        return this.J_1907_R.J_1907_R();
    }

    @Override
    public void n_1700_B(I_14_v type) {
        this.J_1907_R = this.J_1907_R.n_1700_B(type);
    }

    @Override
    public boolean n_1700_B() {
        return this.J_1907_R.R_4764_Y();
    }

    @Override
    public boolean multiplayerClientSuggestionProvider() {
        return this.J_1907_R.P_1922_E();
    }

    @Override
    public boolean w_1457_N() {
        return this.Y_259_p;
    }

    @Override
    public void R_4764_Y(boolean initializedIn) {
        this.Y_259_p = initializedIn;
    }

    @Override
    public A_2352_Z s_956_w() {
        return this.J_1907_R.u_1723_Y();
    }

    @Override
    public T_603_v.R_4764_Y Y_601_j() {
        return this.C_2741_M;
    }

    @Override
    public void n_1700_B(T_603_v.R_4764_Y serializer) {
        this.C_2741_M = serializer;
    }

    @Override
    public R_2450_T u_2550_I() {
        return this.J_1907_R.G_564_y();
    }

    @Override
    public void n_1700_B(R_2450_T difficulty) {
        this.J_1907_R = this.J_1907_R.n_1700_B(difficulty);
    }

    @Override
    public boolean M_588_G() {
        return this.Q_2552_b;
    }

    @Override
    public void G_564_y(boolean locked) {
        this.Q_2552_b = locked;
    }

    @Override
    public Z_1125_b<G_564_y> Y_259_p() {
        return this.t_4043_B;
    }

    @Override
    public void n_1700_B(CrashReportCategory category) {
        ServerLevelData.super.n_1700_B(category);
        WorldData.super.n_1700_B(category);
    }

    @Override
    public j_419_j e_4240_b() {
        return this.R_4764_Y;
    }

    @Override
    public Lifecycle n_3318_d() {
        return this.G_564_y;
    }

    @Override
    public U_2912_j x_607_J() {
        return this.k_2293_S;
    }

    @Override
    public void J_1907_R(U_2912_j nbt) {
        this.k_2293_S = nbt;
    }

    @Override
    public DataPackConfig k_2293_S() {
        return this.J_1907_R.v_4262_N();
    }

    @Override
    public void n_1700_B(DataPackConfig codec) {
        this.J_1907_R = this.J_1907_R.n_1700_B(codec);
    }

    @Override
    @Nullable
    public U_2912_j c_3005_b() {
        return this.q_2307_F;
    }

    @Override
    public void n_1700_B(@Nullable U_2912_j nbt) {
        this.q_2307_F = nbt;
    }

    @Override
    public int Q_2552_b() {
        return this.Z_875_P;
    }

    @Override
    public void v_4262_N(int delay) {
        this.Z_875_P = delay;
    }

    @Override
    public int C_2741_M() {
        return this.c_3005_b;
    }

    @Override
    public void w_1484_f(int chance) {
        this.c_3005_b = chance;
    }

    @Override
    public void n_1700_B(UUID id) {
        this.H_2857_Y = id;
    }

    @Override
    public void n_1700_B(String name, boolean isModded) {
        this.A_4115_X.add(name);
        this.Y_1740_V |= isModded;
    }

    @Override
    public boolean q_2307_F() {
        return this.Y_1740_V;
    }

    @Override
    public Set<String> Z_875_P() {
        return ImmutableSet.copyOf(this.A_4115_X);
    }

    @Override
    public ServerLevelData H_2857_Y() {
        return this;
    }

    @Override
    public B_4315_y A_4115_X() {
        return this.J_1907_R.w_1484_f();
    }
}


