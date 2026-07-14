/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_2109_T;
import lightning.product.C_2005_i;
import lightning.product.ProbabilityFeatureConfiguration;
import lightning.product.StructureFeature;
import lightning.product.MineshaftConfiguration;
import lightning.product.I_892_V;
import lightning.product.Q_25_b;
import lightning.product.ShipwreckConfiguration;
import lightning.product.V_1872_B;
import lightning.product.BuiltinRegistries;
import lightning.product.OceanRuinConfiguration;
import lightning.product.V_4775_U;
import lightning.product.Z_131_s;
import lightning.product.d_2489_R;
import lightning.product.RuinedPortalConfiguration;
import lightning.product.j_4336_h;
import lightning.product.ConfiguredStructureFeature;
import lightning.product.o_2105_O;
import lightning.product.BastionPieces;
import lightning.product.JigsawConfiguration;
import lightning.product.FeatureConfiguration;

public class StructureFeatures {
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> n_1700_B = StructureFeatures.n_1700_B("pillager_outpost", StructureFeature.J_1907_R.n_1700_B(new JigsawConfiguration(() -> V_4775_U.n_1700_B, 7)));
    public static final ConfiguredStructureFeature<MineshaftConfiguration, ? extends StructureFeature<MineshaftConfiguration>> J_1907_R = StructureFeatures.n_1700_B("mineshaft", StructureFeature.R_4764_Y.n_1700_B(new MineshaftConfiguration(0.004f, j_4336_h.J_1907_R.n_1700_B)));
    public static final ConfiguredStructureFeature<MineshaftConfiguration, ? extends StructureFeature<MineshaftConfiguration>> R_4764_Y = StructureFeatures.n_1700_B("mineshaft_mesa", StructureFeature.R_4764_Y.n_1700_B(new MineshaftConfiguration(0.004f, j_4336_h.J_1907_R.J_1907_R)));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> G_564_y = StructureFeatures.n_1700_B("mansion", StructureFeature.G_564_y.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> P_1922_E = StructureFeatures.n_1700_B("jungle_pyramid", StructureFeature.P_1922_E.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> u_1723_Y = StructureFeatures.n_1700_B("desert_pyramid", StructureFeature.u_1723_Y.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> v_4262_N = StructureFeatures.n_1700_B("igloo", StructureFeature.v_4262_N.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<ShipwreckConfiguration, ? extends StructureFeature<ShipwreckConfiguration>> w_1484_f = StructureFeatures.n_1700_B("shipwreck", StructureFeature.t_148_a.n_1700_B(new ShipwreckConfiguration(false)));
    public static final ConfiguredStructureFeature<ShipwreckConfiguration, ? extends StructureFeature<ShipwreckConfiguration>> t_148_a = StructureFeatures.n_1700_B("shipwreck_beached", StructureFeature.t_148_a.n_1700_B(new ShipwreckConfiguration(true)));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> s_956_w = StructureFeatures.n_1700_B("swamp_hut", StructureFeature.s_956_w.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> u_2550_I = StructureFeatures.n_1700_B("stronghold", StructureFeature.u_2550_I.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> M_588_G = StructureFeatures.n_1700_B("monument", StructureFeature.M_588_G.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<OceanRuinConfiguration, ? extends StructureFeature<OceanRuinConfiguration>> P_4830_p = StructureFeatures.n_1700_B("ocean_ruin_cold", StructureFeature.P_4830_p.n_1700_B(new OceanRuinConfiguration(d_2489_R.J_1907_R.J_1907_R, 0.3f, 0.9f)));
    public static final ConfiguredStructureFeature<OceanRuinConfiguration, ? extends StructureFeature<OceanRuinConfiguration>> h_1847_R = StructureFeatures.n_1700_B("ocean_ruin_warm", StructureFeature.P_4830_p.n_1700_B(new OceanRuinConfiguration(d_2489_R.J_1907_R.n_1700_B, 0.3f, 0.9f)));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> Q_4569_t = StructureFeatures.n_1700_B("fortress", StructureFeature.h_1847_R.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> M_182_A = StructureFeatures.n_1700_B("nether_fossil", StructureFeature.multiplayerClientSuggestionProvider.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<o_2105_O, ? extends StructureFeature<o_2105_O>> t_1786_h = StructureFeatures.n_1700_B("end_city", StructureFeature.Q_4569_t.n_1700_B(o_2105_O.J_1907_R));
    public static final ConfiguredStructureFeature<ProbabilityFeatureConfiguration, ? extends StructureFeature<ProbabilityFeatureConfiguration>> multiplayerClientSuggestionProvider = StructureFeatures.n_1700_B("buried_treasure", StructureFeature.M_182_A.n_1700_B(new ProbabilityFeatureConfiguration(0.01f)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> w_1457_N = StructureFeatures.n_1700_B("bastion_remnant", StructureFeature.w_1457_N.n_1700_B(new JigsawConfiguration(() -> BastionPieces.n_1700_B, 6)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> Y_601_j = StructureFeatures.n_1700_B("village_plains", StructureFeature.t_1786_h.n_1700_B(new JigsawConfiguration(() -> V_1872_B.n_1700_B, 6)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> Y_259_p = StructureFeatures.n_1700_B("village_desert", StructureFeature.t_1786_h.n_1700_B(new JigsawConfiguration(() -> B_2109_T.n_1700_B, 6)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> Q_2552_b = StructureFeatures.n_1700_B("village_savanna", StructureFeature.t_1786_h.n_1700_B(new JigsawConfiguration(() -> C_2005_i.n_1700_B, 6)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> C_2741_M = StructureFeatures.n_1700_B("village_snowy", StructureFeature.t_1786_h.n_1700_B(new JigsawConfiguration(() -> Q_25_b.n_1700_B, 6)));
    public static final ConfiguredStructureFeature<JigsawConfiguration, ? extends StructureFeature<JigsawConfiguration>> k_2293_S = StructureFeatures.n_1700_B("village_taiga", StructureFeature.t_1786_h.n_1700_B(new JigsawConfiguration(() -> Z_131_s.n_1700_B, 6)));
    public static final ConfiguredStructureFeature<RuinedPortalConfiguration, ? extends StructureFeature<RuinedPortalConfiguration>> q_2307_F = StructureFeatures.n_1700_B("ruined_portal", StructureFeature.w_1484_f.n_1700_B(new RuinedPortalConfiguration(I_892_V.n_1700_B.n_1700_B)));
    public static final ConfiguredStructureFeature<RuinedPortalConfiguration, ? extends StructureFeature<RuinedPortalConfiguration>> Z_875_P = StructureFeatures.n_1700_B("ruined_portal_desert", StructureFeature.w_1484_f.n_1700_B(new RuinedPortalConfiguration(I_892_V.n_1700_B.J_1907_R)));
    public static final ConfiguredStructureFeature<RuinedPortalConfiguration, ? extends StructureFeature<RuinedPortalConfiguration>> c_3005_b = StructureFeatures.n_1700_B("ruined_portal_jungle", StructureFeature.w_1484_f.n_1700_B(new RuinedPortalConfiguration(I_892_V.n_1700_B.R_4764_Y)));
    public static final ConfiguredStructureFeature<RuinedPortalConfiguration, ? extends StructureFeature<RuinedPortalConfiguration>> H_2857_Y = StructureFeatures.n_1700_B("ruined_portal_swamp", StructureFeature.w_1484_f.n_1700_B(new RuinedPortalConfiguration(I_892_V.n_1700_B.G_564_y)));
    public static final ConfiguredStructureFeature<RuinedPortalConfiguration, ? extends StructureFeature<RuinedPortalConfiguration>> A_4115_X = StructureFeatures.n_1700_B("ruined_portal_mountain", StructureFeature.w_1484_f.n_1700_B(new RuinedPortalConfiguration(I_892_V.n_1700_B.P_1922_E)));
    public static final ConfiguredStructureFeature<RuinedPortalConfiguration, ? extends StructureFeature<RuinedPortalConfiguration>> Y_1740_V = StructureFeatures.n_1700_B("ruined_portal_ocean", StructureFeature.w_1484_f.n_1700_B(new RuinedPortalConfiguration(I_892_V.n_1700_B.u_1723_Y)));
    public static final ConfiguredStructureFeature<RuinedPortalConfiguration, ? extends StructureFeature<RuinedPortalConfiguration>> t_4043_B = StructureFeatures.n_1700_B("ruined_portal_nether", StructureFeature.w_1484_f.n_1700_B(new RuinedPortalConfiguration(I_892_V.n_1700_B.v_4262_N)));

    private static <FC extends FeatureConfiguration, F extends StructureFeature<FC>> ConfiguredStructureFeature<FC, F> n_1700_B(String p_244162_0_, ConfiguredStructureFeature<FC, F> p_244162_1_) {
        return BuiltinRegistries.n_1700_B(BuiltinRegistries.u_1723_Y, p_244162_0_, p_244162_1_);
    }
}


