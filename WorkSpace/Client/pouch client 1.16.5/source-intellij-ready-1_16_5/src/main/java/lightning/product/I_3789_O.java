/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableSet
 *  com.google.common.collect.Maps
 *  com.google.common.collect.Sets
 */
package lightning.product;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lightning.product.A_2178_U;
import lightning.product.D_3640_k;
import lightning.product.E_3705_H;
import lightning.product.H_1285_S;
import lightning.product.EmptyLootItem;
import lightning.product.DamageSourcePredicate;
import lightning.product.ItemTags;
import lightning.product.R_2836_Y;
import lightning.product.S_2110_L;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.W_2672_e;
import lightning.product.W_60_G;
import lightning.product.Z_749_F;
import lightning.product.a_3742_W;
import lightning.product.b_1430_k;
import lightning.product.c_2687_J;
import lightning.product.d_1107_Z;
import lightning.product.e_2748_L;
import lightning.product.g_2336_b;
import lightning.product.SmeltItemFunction;
import lightning.product.i_4685_W;
import lightning.product.j_3341_s;
import lightning.product.n_2967_p;
import lightning.product.n_430_n;
import lightning.product.n_4800_F;
import lightning.product.o_3393_s;
import lightning.product.o_4810_o;
import lightning.product.EntityTypeTags;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.q_1803_e;
import lightning.product.Items;
import lightning.product.t_5_h;
import lightning.product.u_1373_N;
import lightning.product.u_3844_p;

public class I_3789_O
implements Consumer<BiConsumer<g_2336_b, p_4985_U.n_1700_B>> {
    private static final b_1430_k.J_1907_R n_1700_B = b_1430_k.J_1907_R.n_1700_B().n_1700_B(D_3640_k.n_1700_B.n_1700_B().n_1700_B(true).J_1907_R());
    private static final Set<t_5_h<?>> J_1907_R = ImmutableSet.of(t_5_h.g_4106_L, t_5_h.J_1907_R, t_5_h.v_4276_D, t_5_h.q_1982_R, t_5_h.RealmsDefaultUncaughtExceptionHandler);
    private final Map<g_2336_b, p_4985_U.n_1700_B> R_4764_Y = Maps.newHashMap();

    private static p_4985_U.n_1700_B n_1700_B(q_1803_e wool) {
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(wool))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(c_2687_J.n_1700_B(t_5_h.k_3961_g.w_1484_f())));
    }

    public void n_1700_B(BiConsumer<g_2336_b, p_4985_U.n_1700_B> p_accept_1_) {
        this.n_1700_B(t_5_h.J_1907_R, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.G_564_y, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.P_1922_E, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.u_1723_Y, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.A_2487_t).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(u_3844_p.R_4764_Y())));
        this.n_1700_B(t_5_h.w_1484_f, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.Animation).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f))))));
        this.n_1700_B(t_5_h.t_148_a, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Animation).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.r_2687_x).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(-1.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(u_3844_p.R_4764_Y())));
        this.n_1700_B(t_5_h.s_956_w, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.H_274_C).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.w_2892_f).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.u_2550_I, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.ServerAdvancementManager).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.r_1970_q)).J_1907_R(n_430_n.n_1700_B(0.05f))));
        this.n_1700_B(t_5_h.M_588_G, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.y_254_d).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.h_2396_v).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(1.0f, 3.0f)))).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.P_4830_p, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Easing).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(W_60_G.n_1700_B(ItemTags.H_1990_U)).J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.J_1907_R, b_1430_k.J_1907_R.n_1700_B().n_1700_B(EntityTypeTags.J_1907_R)))));
        this.n_1700_B(t_5_h.h_1847_R, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.ServerAdvancementManager).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B))))));
        this.n_1700_B(t_5_h.Q_4569_t, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.y_254_d).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.t_1786_h, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.m_1964_F).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.ServerHandshakePacketListener)).J_1907_R(u_3844_p.R_4764_Y()).J_1907_R(n_4800_F.n_1700_B(0.05f, 0.01f))));
        this.n_1700_B(t_5_h.multiplayerClientSuggestionProvider, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.v_4620_e).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.ServerAdvancementManager).n_1700_B(3)).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B)))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.FrostedIceBlock).n_1700_B(2)).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).n_1700_B(EmptyLootItem.J_1907_R())).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(a_3742_W.UploadStatus)).J_1907_R(u_3844_p.R_4764_Y())).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(c_2687_J.n_1700_B(o_4810_o.Ping)).J_1907_R(u_3844_p.R_4764_Y()).J_1907_R(n_4800_F.n_1700_B(0.025f, 0.01f))));
        this.n_1700_B(t_5_h.Y_601_j, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.Y_259_p, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.v_2746_S).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.Q_2552_b, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.C_2741_M, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.N_81_X))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Y_2905_A).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(u_3844_p.R_4764_Y())));
        this.n_1700_B(t_5_h.A_4115_X, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.Y_1740_V, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.b_3334_n).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Easing).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.t_4043_B, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.x_607_J, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.v_4620_e).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.ServerAdvancementManager).n_1700_B(2)).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B)))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.FrostedIceBlock).n_1700_B(2)).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).n_1700_B(EmptyLootItem.J_1907_R())).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(c_2687_J.n_1700_B(o_4810_o.Ping)).J_1907_R(u_3844_p.R_4764_Y()).J_1907_R(n_4800_F.n_1700_B(0.025f, 0.01f))));
        this.n_1700_B(t_5_h.n_3318_d, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.y_254_d).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.d_2427_y, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.m_1964_F).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.D_1621_L)).n_1700_B(R_2836_Y.n_1700_B(Items.BaseCoralWallFanBlock)).n_1700_B(R_2836_Y.n_1700_B(Items.l_683_e)).J_1907_R(u_3844_p.R_4764_Y()).J_1907_R(n_4800_F.n_1700_B(0.025f, 0.01f))));
        this.n_1700_B(t_5_h.e_1992_r, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.Z_361_l).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(1))))));
        this.n_1700_B(t_5_h.z_1737_N, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.v_4276_D, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(a_3742_W.RealmsResetNormalWorldScreen).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.D_1621_L).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(3.0f, 5.0f))))));
        this.n_1700_B(t_5_h.g_221_o, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.y_254_d).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.B_1668_F, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.S_3844_E).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(-2.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.T_3594_S, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.y_254_d).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.D_4792_h, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.y_254_d).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.h_2396_v).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(1.0f, 3.0f)))).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.s_2632_s, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.z_1333_t, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(a_3742_W.t_1509_b).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(1))))));
        this.n_1700_B(t_5_h.O_508_d, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.H_274_C).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(1.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.r_715_M, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.RotatedPillarBlock).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(u_3844_p.R_4764_Y())));
        this.n_1700_B(t_5_h.A_1038_p, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.j_1654_T).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(1.0f, 3.0f)))).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.p_178_J, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.g_4106_L, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.RealmsClientConfig, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.ServerAdvancementManager).n_1700_B(3)).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.C_3304_p).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.j_276_v, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.U_1258_d).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(1))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.r_1970_q)).J_1907_R(n_430_n.n_1700_B(0.05f))));
        this.n_1700_B(t_5_h.UploadStatus, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.GrassBlock).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.FungusBlock).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.GlazedTerracottaBlock)).J_1907_R(u_3844_p.R_4764_Y()).J_1907_R(n_4800_F.n_1700_B(0.1f, 0.03f))));
        this.n_1700_B(t_5_h.D_60_a, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.C_3304_p).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.r_1970_q)).J_1907_R(n_430_n.n_1700_B(0.05f))));
        this.n_1700_B(t_5_h.k_3961_g, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.s_3698_N).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(1.0f, 2.0f)))).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(o_4810_o.O_508_d, I_3789_O.n_1700_B(a_3742_W.RealmsParentalConsentScreen));
        this.n_1700_B(o_4810_o.D_4792_h, I_3789_O.n_1700_B(a_3742_W.J_4256_G));
        this.n_1700_B(o_4810_o.s_2632_s, I_3789_O.n_1700_B(a_3742_W.RealmsLongConfirmationScreen));
        this.n_1700_B(o_4810_o.g_2268_R, I_3789_O.n_1700_B(a_3742_W.C_290_v));
        this.n_1700_B(o_4810_o.N_2525_X, I_3789_O.n_1700_B(a_3742_W.RealmsConfirmScreen));
        this.n_1700_B(o_4810_o.l_1233_K, I_3789_O.n_1700_B(a_3742_W.RealmsLongRunningMcoTaskScreen));
        this.n_1700_B(o_4810_o.g_164_R, I_3789_O.n_1700_B(a_3742_W.c_132_F));
        this.n_1700_B(o_4810_o.c_4037_x, I_3789_O.n_1700_B(a_3742_W.RealmsCreateRealmScreen));
        this.n_1700_B(o_4810_o.Z_976_R, I_3789_O.n_1700_B(a_3742_W.RealmsClientOutdatedScreen));
        this.n_1700_B(o_4810_o.B_1668_F, I_3789_O.n_1700_B(a_3742_W.M_2677_i));
        this.n_1700_B(o_4810_o.e_2887_G, I_3789_O.n_1700_B(a_3742_W.RealmsScreenWithCallback));
        this.n_1700_B(o_4810_o.H_1990_U, I_3789_O.n_1700_B(a_3742_W.W_3464_O));
        this.n_1700_B(o_4810_o.T_3594_S, I_3789_O.n_1700_B(a_3742_W.w_728_N));
        this.n_1700_B(o_4810_o.z_1333_t, I_3789_O.n_1700_B(a_3742_W.i_2993_w));
        this.n_1700_B(o_4810_o.g_221_o, I_3789_O.n_1700_B(a_3742_W.R_3077_Z));
        this.n_1700_B(o_4810_o.X_933_l, I_3789_O.n_1700_B(a_3742_W.g_4106_L));
        this.n_1700_B(t_5_h.Ops, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.NetherVines)).J_1907_R(n_4800_F.n_1700_B(0.5f, 0.0625f))));
        this.n_1700_B(t_5_h.t_4219_U, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.V_1446_Y, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.g_24_p).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.DamageSourcePredicate).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.PlayerInfo, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.DamageSourcePredicate).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.V_1225_t, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Z_3822_q).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.q_1982_R, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.i_770_g).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 15.0f))))));
        this.n_1700_B(t_5_h.RealmsServerPing, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Animation).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.r_2687_x).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(-1.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(u_3844_p.R_4764_Y())));
        this.n_1700_B(t_5_h.j_1564_a, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.B_3068_A).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(1.0f, 3.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.M_1641_O, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.g_24_p).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.DamageSourcePredicate).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.NetherWartBlock).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)).n_1700_B(1))).J_1907_R(i_4685_W.n_1700_B(j_3341_s.n_1700_B(new U_2912_j(), nbt -> nbt.n_1700_B("Potion", "minecraft:slowness"))))).J_1907_R(u_3844_p.R_4764_Y())));
        this.n_1700_B(t_5_h.RealmsWorldOptions, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Animation).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(2.0f, 5.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.F_1410_V, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.y_254_d).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.S_4022_R, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.J_1008_m).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(1))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.r_1970_q)).J_1907_R(n_430_n.n_1700_B(0.05f))));
        this.n_1700_B(t_5_h.l_4537_E, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(a_3742_W.RowButton).n_1700_B(3)).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.S_4088_D)).J_1907_R(H_1285_S.n_1700_B(DamageSourcePredicate.n_1700_B.n_1700_B().J_1907_R(true)))));
        this.n_1700_B(t_5_h.F_2624_D, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.RealmsDefaultUncaughtExceptionHandler, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.u_744_e, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.y_1700_S, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Y_2905_A).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(u_3844_p.R_4764_Y())));
        this.n_1700_B(t_5_h.RetryCallException, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(o_3393_s.n_1700_B(1.0f, 3.0f)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.AdvancementList).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.o_3456_E).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.v_570_f).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.r_2687_x).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Y_3588_g).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.Easing).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.A_4514_U).n_1700_B(2)).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.r_3651_U, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.RowButton, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.T_797_O).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(-1.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.DamageSourcePredicate).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(a_3742_W.ModuleCategory)).J_1907_R(u_3844_p.R_4764_Y()).J_1907_R(n_4800_F.n_1700_B(0.025f, 0.01f))));
        this.n_1700_B(t_5_h.j_2266_I, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.S_980_j, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.m_1964_F).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(1.0f, 3.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.R_3077_Z, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.m_1964_F).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.D_1621_L)).n_1700_B(R_2836_Y.n_1700_B(Items.BaseCoralWallFanBlock)).n_1700_B(R_2836_Y.n_1700_B(Items.l_683_e)).J_1907_R(u_3844_p.R_4764_Y()).J_1907_R(n_4800_F.n_1700_B(0.025f, 0.01f))));
        this.n_1700_B(t_5_h.RealmsScreenWithCallback, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.m_1964_F).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.c_132_F, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.m_1964_F).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.u_3578_p).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.ServerHandshakePacketListener)).J_1907_R(u_3844_p.R_4764_Y()).J_1907_R(n_4800_F.n_1700_B(0.025f, 0.01f))));
        this.n_1700_B(t_5_h.e_4240_b, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.j_1654_T).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(2.0f, 4.0f)))).J_1907_R((A_2178_U.n_1700_B)SmeltItemFunction.R_4764_Y().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, n_1700_B)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.y_254_d).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))));
        this.n_1700_B(t_5_h.i_1637_u, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.Ping, p_4985_U.J_1907_R());
        this.n_1700_B(t_5_h.M_2677_i, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.m_1964_F).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f)))).J_1907_R(d_1107_Z.n_1700_B(o_3393_s.n_1700_B(0.0f, 1.0f))))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(Items.D_1621_L)).n_1700_B(R_2836_Y.n_1700_B(Items.BaseCoralWallFanBlock)).n_1700_B(R_2836_Y.n_1700_B(Items.l_683_e)).J_1907_R(u_3844_p.R_4764_Y()).J_1907_R(n_4800_F.n_1700_B(0.025f, 0.01f))));
        HashSet set = Sets.newHashSet();
        for (t_5_h t_5_h2 : V_3137_a.g_221_o) {
            g_2336_b resourcelocation = t_5_h2.w_1484_f();
            if (!J_1907_R.contains(t_5_h2) && t_5_h2.P_1922_E() == Z_749_F.u_1723_Y) {
                if (resourcelocation == o_4810_o.n_1700_B || this.R_4764_Y.remove(resourcelocation) == null) continue;
                throw new IllegalStateException(String.format("Weird loottable '%s' for '%s', not a LivingEntity so should not have loot", resourcelocation, V_3137_a.g_221_o.J_1907_R(t_5_h2)));
            }
            if (resourcelocation == o_4810_o.n_1700_B || !set.add(resourcelocation)) continue;
            p_4985_U.n_1700_B loottable$builder = this.R_4764_Y.remove(resourcelocation);
            if (loottable$builder == null) {
                throw new IllegalStateException(String.format("Missing loottable '%s' for '%s'", resourcelocation, V_3137_a.g_221_o.J_1907_R(t_5_h2)));
            }
            p_accept_1_.accept(resourcelocation, loottable$builder);
        }
        this.R_4764_Y.forEach(p_accept_1_::accept);
    }

    private void n_1700_B(t_5_h<?> type, p_4985_U.n_1700_B table) {
        this.n_1700_B(type.w_1484_f(), table);
    }

    private void n_1700_B(g_2336_b id, p_4985_U.n_1700_B table) {
        this.R_4764_Y.put(id, table);
    }

    @Override
    public /* synthetic */ void accept(Object object) {
        this.n_1700_B((BiConsumer)object);
    }
}



