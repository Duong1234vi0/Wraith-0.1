/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.ImmutableMap
 *  com.google.common.collect.Maps
 *  com.mojang.serialization.Codec
 *  javax.annotation.Nullable
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import lightning.product.B_222_E;
import lightning.product.B_4810_D;
import lightning.product.ProbabilityFeatureConfiguration;
import lightning.product.ChunkStatus;
import lightning.product.E_3771_B;
import lightning.product.G_1547_e;
import lightning.product.MineshaftConfiguration;
import lightning.product.I_892_V;
import lightning.product.J_3017_d;
import lightning.product.BoundingBox;
import lightning.product.BastionFeature;
import lightning.product.ShipwreckConfiguration;
import lightning.product.T_1316_M;
import lightning.product.T_3975_o;
import lightning.product.U_1084_f;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.V_3545_G;
import lightning.product.OceanRuinConfiguration;
import lightning.product.V_4739_Y;
import lightning.product.MobSpawnSettings;
import lightning.product.Y_1387_d;
import lightning.product.PillagerOutpostFeature;
import lightning.product.b_2085_h;
import lightning.product.c_1514_x;
import lightning.product.StructurePieceType;
import lightning.product.SectionPos;
import lightning.product.StructureStart;
import lightning.product.d_2489_R;
import lightning.product.ChunkAccess;
import lightning.product.WorldgenRandom;
import lightning.product.e_4586_L;
import lightning.product.g_2336_b;
import lightning.product.h_2920_Q;
import lightning.product.RuinedPortalConfiguration;
import lightning.product.j_4336_h;
import lightning.product.j_4900_U;
import lightning.product.ConfiguredStructureFeature;
import lightning.product.k_594_Q;
import lightning.product.BiomeSource;
import lightning.product.o_2105_O;
import lightning.product.StrongholdFeature;
import lightning.product.SwamplandHutFeature;
import lightning.product.q_2896_o;
import lightning.product.WoodlandMansionFeature;
import lightning.product.r_4097_j;
import lightning.product.JigsawConfiguration;
import lightning.product.FeatureConfiguration;
import lightning.product.v_4827_Y;
import lightning.product.NetherFortressFeature;
import lightning.product.z_1753_f;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class StructureFeature<C extends FeatureConfiguration> {
    public static final BiMap<String, StructureFeature<?>> n_1700_B = HashBiMap.create();
    private static final Map<StructureFeature<?>, T_3975_o.J_1907_R> Y_259_p = Maps.newHashMap();
    private static final Logger Q_2552_b = LogManager.getLogger();
    public static final StructureFeature<JigsawConfiguration> J_1907_R = StructureFeature.n_1700_B("Pillager_Outpost", new PillagerOutpostFeature(JigsawConfiguration.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<MineshaftConfiguration> R_4764_Y = StructureFeature.n_1700_B("Mineshaft", new j_4336_h(MineshaftConfiguration.n_1700_B), T_3975_o.J_1907_R.G_564_y);
    public static final StructureFeature<o_2105_O> G_564_y = StructureFeature.n_1700_B("Mansion", new WoodlandMansionFeature(o_2105_O.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<o_2105_O> P_1922_E = StructureFeature.n_1700_B("Jungle_Pyramid", new B_222_E(o_2105_O.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<o_2105_O> u_1723_Y = StructureFeature.n_1700_B("Desert_Pyramid", new v_4827_Y(o_2105_O.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<o_2105_O> v_4262_N = StructureFeature.n_1700_B("Igloo", new j_4900_U(o_2105_O.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<RuinedPortalConfiguration> w_1484_f = StructureFeature.n_1700_B("Ruined_Portal", new I_892_V(RuinedPortalConfiguration.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<ShipwreckConfiguration> t_148_a = StructureFeature.n_1700_B("Shipwreck", new h_2920_Q(ShipwreckConfiguration.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final SwamplandHutFeature s_956_w = StructureFeature.n_1700_B("Swamp_Hut", new SwamplandHutFeature(o_2105_O.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<o_2105_O> u_2550_I = StructureFeature.n_1700_B("Stronghold", new StrongholdFeature(o_2105_O.n_1700_B), T_3975_o.J_1907_R.u_1723_Y);
    public static final StructureFeature<o_2105_O> M_588_G = StructureFeature.n_1700_B("Monument", new U_1084_f(o_2105_O.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<OceanRuinConfiguration> P_4830_p = StructureFeature.n_1700_B("Ocean_Ruin", new d_2489_R(OceanRuinConfiguration.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<o_2105_O> h_1847_R = StructureFeature.n_1700_B("Fortress", new NetherFortressFeature(o_2105_O.n_1700_B), T_3975_o.J_1907_R.w_1484_f);
    public static final StructureFeature<o_2105_O> Q_4569_t = StructureFeature.n_1700_B("EndCity", new V_3545_G(o_2105_O.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<ProbabilityFeatureConfiguration> M_182_A = StructureFeature.n_1700_B("Buried_Treasure", new e_4586_L(ProbabilityFeatureConfiguration.n_1700_B), T_3975_o.J_1907_R.G_564_y);
    public static final StructureFeature<JigsawConfiguration> t_1786_h = StructureFeature.n_1700_B("Village", new G_1547_e(JigsawConfiguration.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final StructureFeature<o_2105_O> multiplayerClientSuggestionProvider = StructureFeature.n_1700_B("Nether_Fossil", new B_4810_D(o_2105_O.n_1700_B), T_3975_o.J_1907_R.w_1484_f);
    public static final StructureFeature<JigsawConfiguration> w_1457_N = StructureFeature.n_1700_B("Bastion_Remnant", new BastionFeature(JigsawConfiguration.n_1700_B), T_3975_o.J_1907_R.P_1922_E);
    public static final List<StructureFeature<?>> Y_601_j = ImmutableList.of(J_1907_R, t_1786_h, multiplayerClientSuggestionProvider);
    private static final g_2336_b C_2741_M = new g_2336_b("jigsaw");
    private static final Map<g_2336_b, g_2336_b> k_2293_S = ImmutableMap.builder().put((Object)new g_2336_b("nvi"), (Object)C_2741_M).put((Object)new g_2336_b("pcp"), (Object)C_2741_M).put((Object)new g_2336_b("bastionremnant"), (Object)C_2741_M).put((Object)new g_2336_b("runtime"), (Object)C_2741_M).build();
    private final Codec<ConfiguredStructureFeature<C, StructureFeature<C>>> q_2307_F;

    private static <F extends StructureFeature<?>> F n_1700_B(String p_236394_0_, F p_236394_1_, T_3975_o.J_1907_R p_236394_2_) {
        n_1700_B.put((Object)p_236394_0_.toLowerCase(Locale.ROOT), p_236394_1_);
        Y_259_p.put(p_236394_1_, p_236394_2_);
        return (F)V_3137_a.n_1700_B(V_3137_a.M_1641_O, p_236394_0_.toLowerCase(Locale.ROOT), p_236394_1_);
    }

    public StructureFeature(Codec<C> p_i231997_1_) {
        this.q_2307_F = p_i231997_1_.fieldOf("config").xmap(p_236395_1_ -> new ConfiguredStructureFeature<FeatureConfiguration, StructureFeature>(this, (FeatureConfiguration)p_236395_1_), p_236390_0_ -> p_236390_0_.P_1922_E).codec();
    }

    public T_3975_o.J_1907_R G_564_y() {
        return Y_259_p.get(this);
    }

    public static void P_1922_E() {
    }

    @Nullable
    public static StructureStart<?> n_1700_B(b_2085_h p_236393_0_, U_2912_j p_236393_1_, long p_236393_2_) {
        String s = p_236393_1_.M_588_G("id");
        if ("INVALID".equals(s)) {
            return StructureStart.n_1700_B;
        }
        StructureFeature<?> structure = V_3137_a.M_1641_O.n_1700_B(new g_2336_b(s.toLowerCase(Locale.ROOT)));
        if (structure == null) {
            Q_2552_b.error("Unknown feature id: {}", (Object)s);
            return null;
        }
        int i = p_236393_1_.w_1484_f("ChunkX");
        int j = p_236393_1_.w_1484_f("ChunkZ");
        int k = p_236393_1_.w_1484_f("references");
        BoundingBox mutableboundingbox = p_236393_1_.P_1922_E("BB") ? new BoundingBox(p_236393_1_.h_1847_R("BB")) : BoundingBox.n_1700_B();
        q_2896_o listnbt = p_236393_1_.G_564_y("Children", 10);
        try {
            StructureStart<?> structurestart = structure.n_1700_B(i, j, mutableboundingbox, k, p_236393_2_);
            for (int l = 0; l < listnbt.size(); ++l) {
                U_2912_j compoundnbt = listnbt.n_1700_B(l);
                String s1 = compoundnbt.M_588_G("id").toLowerCase(Locale.ROOT);
                g_2336_b resourcelocation = new g_2336_b(s1);
                g_2336_b resourcelocation1 = k_2293_S.getOrDefault(resourcelocation, resourcelocation);
                StructurePieceType istructurepiecetype = V_3137_a.RealmsWorldResetDto.n_1700_B(resourcelocation1);
                if (istructurepiecetype == null) {
                    Q_2552_b.error("Unknown structure piece id: {}", (Object)resourcelocation1);
                    continue;
                }
                try {
                    E_3771_B structurepiece = istructurepiecetype.load(p_236393_0_, compoundnbt);
                    structurestart.G_564_y().add(structurepiece);
                    continue;
                }
                catch (Exception exception) {
                    Q_2552_b.error("Exception loading structure piece with id {}", (Object)resourcelocation1, (Object)exception);
                }
            }
            return structurestart;
        }
        catch (Exception exception1) {
            Q_2552_b.error("Failed Start with id {}", (Object)s, (Object)exception1);
            return null;
        }
    }

    public Codec<ConfiguredStructureFeature<C, StructureFeature<C>>> u_1723_Y() {
        return this.q_2307_F;
    }

    public ConfiguredStructureFeature<C, ? extends StructureFeature<C>> n_1700_B(C p_236391_1_) {
        return new ConfiguredStructureFeature<C, StructureFeature>(this, p_236391_1_);
    }

    @Nullable
    public c_1514_x n_1700_B(T_1316_M p_236388_1_, J_3017_d p_236388_2_, c_1514_x p_236388_3_, int p_236388_4_, boolean p_236388_5_, long p_236388_6_, V_4739_Y p_236388_8_) {
        int i = p_236388_8_.n_1700_B();
        int j = p_236388_3_.getX() >> 4;
        int k = p_236388_3_.getZ() >> 4;
        WorldgenRandom sharedseedrandom = new WorldgenRandom();
        block0: for (int l = 0; l <= p_236388_4_; ++l) {
            for (int i1 = -l; i1 <= l; ++i1) {
                boolean flag = i1 == -l || i1 == l;
                for (int j1 = -l; j1 <= l; ++j1) {
                    boolean flag1;
                    boolean bl = flag1 = j1 == -l || j1 == l;
                    if (!flag && !flag1) continue;
                    int k1 = j + i * i1;
                    int l1 = k + i * j1;
                    Y_1387_d chunkpos = this.n_1700_B(p_236388_8_, p_236388_6_, sharedseedrandom, k1, l1);
                    ChunkAccess ichunk = p_236388_1_.n_1700_B(chunkpos.J_1907_R, chunkpos.R_4764_Y, ChunkStatus.J_1907_R);
                    StructureStart<?> structurestart = p_236388_2_.n_1700_B(SectionPos.n_1700_B(ichunk.getPos(), 0), this, ichunk);
                    if (structurestart != null && structurestart.P_1922_E()) {
                        if (p_236388_5_ && structurestart.w_1484_f()) {
                            structurestart.t_148_a();
                            return structurestart.n_1700_B();
                        }
                        if (!p_236388_5_) {
                            return structurestart.n_1700_B();
                        }
                    }
                    if (l == 0) break;
                }
                if (l == 0) continue block0;
            }
        }
        return null;
    }

    protected boolean J_1907_R() {
        return true;
    }

    public final Y_1387_d n_1700_B(V_4739_Y p_236392_1_, long p_236392_2_, WorldgenRandom p_236392_4_, int p_236392_5_, int p_236392_6_) {
        int j1;
        int i1;
        int i = p_236392_1_.n_1700_B();
        int j = p_236392_1_.J_1907_R();
        int k = Math.floorDiv(p_236392_5_, i);
        int l = Math.floorDiv(p_236392_6_, i);
        p_236392_4_.n_1700_B(p_236392_2_, k, l, p_236392_1_.R_4764_Y());
        if (this.J_1907_R()) {
            i1 = p_236392_4_.nextInt(i - j);
            j1 = p_236392_4_.nextInt(i - j);
        } else {
            i1 = (p_236392_4_.nextInt(i - j) + p_236392_4_.nextInt(i - j)) / 2;
            j1 = (p_236392_4_.nextInt(i - j) + p_236392_4_.nextInt(i - j)) / 2;
        }
        return new Y_1387_d(k * i + i1, l * i + j1);
    }

    protected boolean n_1700_B(z_1753_f p_230363_1_, BiomeSource p_230363_2_, long p_230363_3_, WorldgenRandom p_230363_5_, int p_230363_6_, int p_230363_7_, k_594_Q p_230363_8_, Y_1387_d p_230363_9_, C p_230363_10_) {
        return true;
    }

    private StructureStart<C> n_1700_B(int p_236387_1_, int p_236387_2_, BoundingBox p_236387_3_, int p_236387_4_, long p_236387_5_) {
        return this.n_1700_B().create(this, p_236387_1_, p_236387_2_, p_236387_3_, p_236387_4_, p_236387_5_);
    }

    public StructureStart<?> n_1700_B(r_4097_j p_242785_1_, z_1753_f p_242785_2_, BiomeSource p_242785_3_, b_2085_h p_242785_4_, long p_242785_5_, Y_1387_d p_242785_7_, k_594_Q p_242785_8_, int p_242785_9_, WorldgenRandom p_242785_10_, V_4739_Y p_242785_11_, C p_242785_12_) {
        Y_1387_d chunkpos = this.n_1700_B(p_242785_11_, p_242785_5_, p_242785_10_, p_242785_7_.J_1907_R, p_242785_7_.R_4764_Y);
        if (p_242785_7_.J_1907_R == chunkpos.J_1907_R && p_242785_7_.R_4764_Y == chunkpos.R_4764_Y && this.n_1700_B(p_242785_2_, p_242785_3_, p_242785_5_, p_242785_10_, p_242785_7_.J_1907_R, p_242785_7_.R_4764_Y, p_242785_8_, chunkpos, p_242785_12_)) {
            StructureStart<C> structurestart = this.n_1700_B(p_242785_7_.J_1907_R, p_242785_7_.R_4764_Y, BoundingBox.n_1700_B(), p_242785_9_, p_242785_5_);
            structurestart.n_1700_B(p_242785_1_, p_242785_2_, p_242785_4_, p_242785_7_.J_1907_R, p_242785_7_.R_4764_Y, p_242785_8_, p_242785_12_);
            if (structurestart.P_1922_E()) {
                return structurestart;
            }
        }
        return StructureStart.n_1700_B;
    }

    public abstract n_1700_B<C> n_1700_B();

    public String v_4262_N() {
        return (String)n_1700_B.inverse().get((Object)this);
    }

    public List<MobSpawnSettings.R_4764_Y> R_4764_Y() {
        return ImmutableList.of();
    }

    public List<MobSpawnSettings.R_4764_Y> w_1484_f() {
        return ImmutableList.of();
    }

    public static interface n_1700_B<C extends FeatureConfiguration> {
        public StructureStart<C> create(StructureFeature<C> var1, int var2, int var3, BoundingBox var4, int var5, long var6);
    }
}


