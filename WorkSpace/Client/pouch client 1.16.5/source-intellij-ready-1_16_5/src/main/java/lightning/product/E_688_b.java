/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package lightning.product;

import com.google.common.collect.ImmutableList;
import lightning.product.A_1726_L;
import lightning.product.b_1213_w;
import net.optifine.Config;
import net.optifine.shaders.SVertexFormat;

public class E_688_b {
    public static final A_1726_L n_1700_B = new A_1726_L(0, A_1726_L.n_1700_B.n_1700_B, A_1726_L.J_1907_R.n_1700_B, 3);
    public static final A_1726_L J_1907_R = new A_1726_L(0, A_1726_L.n_1700_B.J_1907_R, A_1726_L.J_1907_R.R_4764_Y, 4);
    public static final A_1726_L R_4764_Y = new A_1726_L(0, A_1726_L.n_1700_B.n_1700_B, A_1726_L.J_1907_R.G_564_y, 2);
    public static final A_1726_L G_564_y = new A_1726_L(1, A_1726_L.n_1700_B.P_1922_E, A_1726_L.J_1907_R.G_564_y, 2);
    public static final A_1726_L P_1922_E = new A_1726_L(2, A_1726_L.n_1700_B.P_1922_E, A_1726_L.J_1907_R.G_564_y, 2);
    public static final A_1726_L u_1723_Y = new A_1726_L(0, A_1726_L.n_1700_B.R_4764_Y, A_1726_L.J_1907_R.J_1907_R, 3);
    public static final A_1726_L v_4262_N = new A_1726_L(0, A_1726_L.n_1700_B.R_4764_Y, A_1726_L.J_1907_R.P_1922_E, 1);
    public static final b_1213_w w_1484_f = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)J_1907_R).add((Object)R_4764_Y).add((Object)P_1922_E).add((Object)u_1723_Y).add((Object)v_4262_N).build());
    public static final b_1213_w t_148_a = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)J_1907_R).add((Object)R_4764_Y).add((Object)G_564_y).add((Object)P_1922_E).add((Object)u_1723_Y).add((Object)v_4262_N).build());
    public static final b_1213_w s_956_w = w_1484_f.M_588_G();
    public static final b_1213_w u_2550_I = SVertexFormat.makeExtendedFormatBlock(s_956_w);
    public static final int M_588_G = s_956_w.J_1907_R();
    public static final int P_4830_p = u_2550_I.J_1907_R();
    public static final b_1213_w h_1847_R = t_148_a.M_588_G();
    public static final b_1213_w Q_4569_t = SVertexFormat.makeExtendedFormatEntity(h_1847_R);
    public static final int M_182_A = h_1847_R.J_1907_R();
    public static final int t_1786_h = Q_4569_t.J_1907_R();
    @Deprecated
    public static final b_1213_w multiplayerClientSuggestionProvider = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)R_4764_Y).add((Object)J_1907_R).add((Object)P_1922_E).build());
    public static final b_1213_w w_1457_N = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).build());
    public static final b_1213_w Y_601_j = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)J_1907_R).build());
    public static final b_1213_w Y_259_p = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)J_1907_R).add((Object)P_1922_E).build());
    public static final b_1213_w Q_2552_b = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)R_4764_Y).build());
    public static final b_1213_w C_2741_M = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)J_1907_R).add((Object)R_4764_Y).build());
    @Deprecated
    public static final b_1213_w k_2293_S = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)R_4764_Y).add((Object)J_1907_R).build());
    public static final b_1213_w q_2307_F = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)J_1907_R).add((Object)R_4764_Y).add((Object)P_1922_E).build());
    @Deprecated
    public static final b_1213_w Z_875_P = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)R_4764_Y).add((Object)P_1922_E).add((Object)J_1907_R).build());
    @Deprecated
    public static final b_1213_w c_3005_b = new b_1213_w((ImmutableList<A_1726_L>)ImmutableList.builder().add((Object)n_1700_B).add((Object)R_4764_Y).add((Object)J_1907_R).add((Object)u_1723_Y).add((Object)v_4262_N).build());

    public static void n_1700_B() {
        if (Config.isShaders()) {
            w_1484_f.n_1700_B(u_2550_I);
            t_148_a.n_1700_B(Q_4569_t);
        } else {
            w_1484_f.n_1700_B(s_956_w);
            t_148_a.n_1700_B(h_1847_R);
        }
    }

    static {
        n_1700_B.n_1700_B("POSITION_3F");
        J_1907_R.n_1700_B("COLOR_4UB");
        R_4764_Y.n_1700_B("TEX_2F");
        G_564_y.n_1700_B("TEX_2S");
        P_1922_E.n_1700_B("TEX_2SB");
        u_1723_Y.n_1700_B("NORMAL_3B");
        v_4262_N.n_1700_B("PADDING_1B");
        w_1484_f.n_1700_B("BLOCK");
        t_148_a.n_1700_B("ENTITY");
        u_2550_I.n_1700_B("BLOCK_SHADERS");
        Q_4569_t.n_1700_B("ENTITY_SHADERS");
        multiplayerClientSuggestionProvider.n_1700_B("PARTICLE_POSITION_TEX_COLOR_LMAP");
        w_1457_N.n_1700_B("POSITION");
        Y_601_j.n_1700_B("POSITION_COLOR");
        Y_259_p.n_1700_B("POSITION_COLOR_LIGHTMAP");
        Q_2552_b.n_1700_B("POSITION_TEX");
        C_2741_M.n_1700_B("POSITION_COLOR_TEX");
        k_2293_S.n_1700_B("POSITION_TEX_COLOR");
        q_2307_F.n_1700_B("POSITION_COLOR_TEX_LIGHTMAP");
        Z_875_P.n_1700_B("POSITION_TEX_LIGHTMAP_COLOR");
        c_3005_b.n_1700_B("POSITION_TEX_COLOR_NORMAL");
    }
}


