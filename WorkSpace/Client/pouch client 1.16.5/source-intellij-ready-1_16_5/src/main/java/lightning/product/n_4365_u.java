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
import java.util.function.Function;
import java.util.stream.Stream;
import lightning.product.A_1604_A;
import lightning.product.A_2178_U;
import lightning.product.A_3895_D;
import lightning.product.B_368_w;
import lightning.product.D_3746_J;
import lightning.product.D_4237_z;
import lightning.product.E_2006_R;
import lightning.product.E_3705_H;
import lightning.product.E_4700_p;
import lightning.product.CropBlock;
import lightning.product.H_3357_D;
import lightning.product.H_4584_y;
import lightning.product.J_22_h;
import lightning.product.J_2868_p;
import lightning.product.J_4436_F;
import lightning.product.O_215_U;
import lightning.product.LootItemCondition;
import lightning.product.R_2836_Y;
import lightning.product.S_1431_H;
import lightning.product.S_2110_L;
import lightning.product.T_2915_h;
import lightning.product.T_328_T;
import lightning.product.V_3137_a;
import lightning.product.V_883_W;
import lightning.product.W_2672_e;
import lightning.product.ApplyExplosionDecay;
import lightning.product.BeetrootBlock;
import lightning.product.Y_3462_U;
import lightning.product.Z_3128_E;
import lightning.product.a_3742_W;
import lightning.product.a_648_i;
import lightning.product.c_1514_x;
import lightning.product.d_1292_N;
import lightning.product.d_1384_D;
import lightning.product.d_150_Y;
import lightning.product.Enchantments;
import lightning.product.e_2748_L;
import lightning.product.SweetBerryBushBlock;
import lightning.product.g_1926_q;
import lightning.product.g_2336_b;
import lightning.product.g_3212_H;
import lightning.product.h_2829_o;
import lightning.product.h_935_G;
import lightning.product.RandomIntGenerator;
import lightning.product.k_4738_s;
import lightning.product.m_4962_f;
import lightning.product.n_1769_f;
import lightning.product.n_2967_p;
import lightning.product.n_430_n;
import lightning.product.o_3393_s;
import lightning.product.o_4810_o;
import lightning.product.NetherWartBlock;
import lightning.product.p_1840_B;
import lightning.product.p_4985_U;
import lightning.product.DoublePlantBlock;
import lightning.product.q_1613_l;
import lightning.product.q_1704_m;
import lightning.product.q_1803_e;
import lightning.product.q_3398_T;
import lightning.product.Items;
import lightning.product.r_2687_x;
import lightning.product.MinMaxBounds;
import lightning.product.u_1373_N;
import lightning.product.CocoaBlock;
import lightning.product.v_1577_d;
import lightning.product.v_2678_c;
import lightning.product.v_3760_Q;
import lightning.product.w_2512_g;
import lightning.product.w_4866_k;
import lightning.product.SnowLayerBlock;
import lightning.product.y_3008_A;
import lightning.product.y_3142_C;

public class n_4365_u
implements Consumer<BiConsumer<g_2336_b, p_4985_U.n_1700_B>> {
    private static final LootItemCondition.n_1700_B n_1700_B = y_3142_C.n_1700_B(w_4866_k.n_1700_B.n_1700_B().n_1700_B(new A_1604_A(Enchantments.Y_259_p, MinMaxBounds.G_564_y.J_1907_R(1))));
    private static final LootItemCondition.n_1700_B J_1907_R = n_1700_B.n_1700_B();
    private static final LootItemCondition.n_1700_B R_4764_Y = y_3142_C.n_1700_B(w_4866_k.n_1700_B.n_1700_B().n_1700_B(Items.LightPredicate));
    private static final LootItemCondition.n_1700_B G_564_y = R_4764_Y.n_1700_B(n_1700_B);
    private static final LootItemCondition.n_1700_B P_1922_E = G_564_y.n_1700_B();
    private static final Set<q_1613_l> u_1723_Y = (Set)Stream.of(a_3742_W.F_391_H, a_3742_W.k_578_l, a_3742_W.V_3441_j, a_3742_W.D_3612_q, a_3742_W.ModuleCategory, a_3742_W.Setting, a_3742_W.Module, a_3742_W.BooleanSetting, a_3742_W.H_1491_c, a_3742_W.k_1052_R, a_3742_W.EntityESP, a_3742_W.Crosshair, a_3742_W.CrystalESP, a_3742_W.ChatBubbles, a_3742_W.BlockOverlay, a_3742_W.DistantAlpha, a_3742_W.ArmorDurability, a_3742_W.Chams, a_3742_W.AspectRatio, a_3742_W.AnomalyESP, a_3742_W.x_555_z, a_3742_W.BlockESP, a_3742_W.Cosmetics, a_3742_W.Emotions, a_3742_W.Ambience, a_3742_W.Arrows).map(q_1803_e::u_1723_Y).collect(ImmutableSet.toImmutableSet());
    private static final float[] v_4262_N = new float[]{0.05f, 0.0625f, 0.083333336f, 0.1f};
    private static final float[] w_1484_f = new float[]{0.025f, 0.027777778f, 0.03125f, 0.041666668f, 0.1f};
    private final Map<g_2336_b, p_4985_U.n_1700_B> t_148_a = Maps.newHashMap();

    private static <T> T n_1700_B(q_1803_e item, Z_3128_E<T> function) {
        return !u_1723_Y.contains(item.u_1723_Y()) ? function.n_1700_B(ApplyExplosionDecay.R_4764_Y()) : function.G_564_y();
    }

    private static <T> T n_1700_B(q_1803_e item, A_3895_D<T> condition) {
        return !u_1723_Y.contains(item.u_1723_Y()) ? condition.n_1700_B(g_1926_q.R_4764_Y()) : condition.G_564_y();
    }

    private static p_4985_U.n_1700_B n_1700_B(q_1803_e item) {
        return p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B(item, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(item))));
    }

    private static p_4985_U.n_1700_B n_1700_B(T_2915_h block, LootItemCondition.n_1700_B conditionBuilder, u_1373_N.n_1700_B<?> p_218494_2_) {
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(block).J_1907_R(conditionBuilder)).n_1700_B(p_218494_2_)));
    }

    private static p_4985_U.n_1700_B n_1700_B(T_2915_h block, u_1373_N.n_1700_B<?> builder) {
        return n_4365_u.n_1700_B(block, n_1700_B, builder);
    }

    private static p_4985_U.n_1700_B J_1907_R(T_2915_h block, u_1373_N.n_1700_B<?> noShearAlternativeEntry) {
        return n_4365_u.n_1700_B(block, R_4764_Y, noShearAlternativeEntry);
    }

    private static p_4985_U.n_1700_B R_4764_Y(T_2915_h block, u_1373_N.n_1700_B<?> alternativeLootEntry) {
        return n_4365_u.n_1700_B(block, G_564_y, alternativeLootEntry);
    }

    private static p_4985_U.n_1700_B J_1907_R(T_2915_h block, q_1803_e noSilkTouch) {
        return n_4365_u.n_1700_B(block, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)block, R_2836_Y.n_1700_B(noSilkTouch)));
    }

    private static p_4985_U.n_1700_B n_1700_B(q_1803_e item, RandomIntGenerator range) {
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B)n_4365_u.n_1700_B(item, R_2836_Y.n_1700_B(item).J_1907_R(E_3705_H.n_1700_B(range)))));
    }

    private static p_4985_U.n_1700_B n_1700_B(T_2915_h block, q_1803_e item, RandomIntGenerator range) {
        return n_4365_u.n_1700_B(block, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)block, R_2836_Y.n_1700_B(item).J_1907_R(E_3705_H.n_1700_B(range))));
    }

    private static p_4985_U.n_1700_B J_1907_R(q_1803_e item) {
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().J_1907_R(n_1700_B).n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(item)));
    }

    private static p_4985_U.n_1700_B R_4764_Y(q_1803_e flower) {
        return p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)a_3742_W.r_4790_y, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(a_3742_W.r_4790_y)))).n_1700_B(n_4365_u.n_1700_B(flower, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(R_2836_Y.n_1700_B(flower))));
    }

    private static p_4985_U.n_1700_B P_1922_E(T_2915_h slab) {
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)slab, R_2836_Y.n_1700_B(slab).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(S_2110_L.n_1700_B(2)).J_1907_R(w_2512_g.n_1700_B(slab).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(y_3008_A.P_4830_p, n_1769_f.R_4764_Y)))))));
    }

    private static <T extends Comparable<T> & E_4700_p> p_4985_U.n_1700_B n_1700_B(T_2915_h block, v_3760_Q<T> property, T value) {
        return p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)block, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(block).J_1907_R(w_2512_g.n_1700_B(block).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(property, value))))));
    }

    private static p_4985_U.n_1700_B u_1723_Y(T_2915_h block) {
        return p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)block, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(block).J_1907_R(d_150_Y.n_1700_B(d_150_Y.J_1907_R.G_564_y)))));
    }

    private static p_4985_U.n_1700_B v_4262_N(T_2915_h shulker) {
        return p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)shulker, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(shulker).J_1907_R(d_150_Y.n_1700_B(d_150_Y.J_1907_R.G_564_y))).J_1907_R(p_1840_B.n_1700_B(p_1840_B.P_1922_E.G_564_y).n_1700_B("Lock", "BlockEntityTag.Lock").n_1700_B("LootTable", "BlockEntityTag.LootTable").n_1700_B("LootTableSeed", "BlockEntityTag.LootTableSeed"))).J_1907_R(J_4436_F.R_4764_Y().n_1700_B(v_2678_c.n_1700_B(Y_3462_U.h_1847_R))))));
    }

    private static p_4985_U.n_1700_B w_1484_f(T_2915_h banner) {
        return p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)banner, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(banner).J_1907_R(d_150_Y.n_1700_B(d_150_Y.J_1907_R.G_564_y))).J_1907_R(p_1840_B.n_1700_B(p_1840_B.P_1922_E.G_564_y).n_1700_B("Patterns", "BlockEntityTag.Patterns")))));
    }

    private static p_4985_U.n_1700_B t_148_a(T_2915_h nest) {
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().J_1907_R(n_1700_B).n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(nest).J_1907_R(p_1840_B.n_1700_B(p_1840_B.P_1922_E.G_564_y).n_1700_B("Bees", "BlockEntityTag.Bees"))).J_1907_R(T_328_T.n_1700_B(nest).n_1700_B(v_1577_d.h_1847_R))));
    }

    private static p_4985_U.n_1700_B s_956_w(T_2915_h hive) {
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B(((u_1373_N.n_1700_B)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(hive).J_1907_R(n_1700_B)).J_1907_R(p_1840_B.n_1700_B(p_1840_B.P_1922_E.G_564_y).n_1700_B("Bees", "BlockEntityTag.Bees"))).J_1907_R(T_328_T.n_1700_B(hive).n_1700_B(v_1577_d.h_1847_R))).n_1700_B(R_2836_Y.n_1700_B(hive))));
    }

    private static p_4985_U.n_1700_B n_1700_B(T_2915_h block, q_1613_l item) {
        return n_4365_u.n_1700_B(block, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)block, R_2836_Y.n_1700_B(item).J_1907_R(m_4962_f.n_1700_B(Enchantments.C_2741_M))));
    }

    private static p_4985_U.n_1700_B R_4764_Y(T_2915_h block, q_1803_e item) {
        return n_4365_u.n_1700_B(block, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)block, ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(item).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(-6.0f, 2.0f)))).J_1907_R(O_215_U.n_1700_B(H_3357_D.n_1700_B(0)))));
    }

    private static p_4985_U.n_1700_B u_2550_I(T_2915_h block) {
        return n_4365_u.J_1907_R(block, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)block, ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.G_4691_Q).J_1907_R(n_430_n.n_1700_B(0.125f))).J_1907_R(m_4962_f.n_1700_B(Enchantments.C_2741_M, 2))));
    }

    private static p_4985_U.n_1700_B J_1907_R(T_2915_h stemFruit, q_1613_l item) {
        return p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)stemFruit, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(item).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(J_22_h.n_1700_B(3, 0.06666667f)).J_1907_R(w_2512_g.n_1700_B(stemFruit).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(D_3746_J.P_4830_p, false))))).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(J_22_h.n_1700_B(3, 0.13333334f)).J_1907_R(w_2512_g.n_1700_B(stemFruit).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(D_3746_J.P_4830_p, true))))).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(J_22_h.n_1700_B(3, 0.2f)).J_1907_R(w_2512_g.n_1700_B(stemFruit).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(D_3746_J.P_4830_p, 2))))).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(J_22_h.n_1700_B(3, 0.26666668f)).J_1907_R(w_2512_g.n_1700_B(stemFruit).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(D_3746_J.P_4830_p, 3))))).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(J_22_h.n_1700_B(3, 0.33333334f)).J_1907_R(w_2512_g.n_1700_B(stemFruit).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(D_3746_J.P_4830_p, 4))))).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(J_22_h.n_1700_B(3, 0.4f)).J_1907_R(w_2512_g.n_1700_B(stemFruit).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(D_3746_J.P_4830_p, 5))))).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(J_22_h.n_1700_B(3, 0.46666667f)).J_1907_R(w_2512_g.n_1700_B(stemFruit).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(D_3746_J.P_4830_p, 6))))).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(J_22_h.n_1700_B(3, 0.53333336f)).J_1907_R(w_2512_g.n_1700_B(stemFruit).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(D_3746_J.P_4830_p, 7)))))));
    }

    private static p_4985_U.n_1700_B R_4764_Y(T_2915_h stem, q_1613_l stemSeed) {
        return p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)stem, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(stemSeed).J_1907_R(E_3705_H.n_1700_B(J_22_h.n_1700_B(3, 0.53333336f))))));
    }

    private static p_4985_U.n_1700_B G_564_y(q_1803_e item) {
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).J_1907_R(R_4764_Y).n_1700_B(R_2836_Y.n_1700_B(item)));
    }

    private static p_4985_U.n_1700_B n_1700_B(T_2915_h block, T_2915_h sapling, float ... chances) {
        return n_4365_u.R_4764_Y(block, ((e_2748_L.n_1700_B)n_4365_u.n_1700_B((q_1803_e)block, R_2836_Y.n_1700_B(sapling))).J_1907_R(E_2006_R.n_1700_B(Enchantments.C_2741_M, chances))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).J_1907_R(P_1922_E).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)n_4365_u.n_1700_B((q_1803_e)block, R_2836_Y.n_1700_B(Items.A_4514_U).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(1.0f, 2.0f))))).J_1907_R(E_2006_R.n_1700_B(Enchantments.C_2741_M, 0.02f, 0.022222223f, 0.025f, 0.033333335f, 0.1f))));
    }

    private static p_4985_U.n_1700_B J_1907_R(T_2915_h block, T_2915_h sapling, float ... chances) {
        return n_4365_u.n_1700_B(block, sapling, chances).n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).J_1907_R(P_1922_E).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)n_4365_u.n_1700_B((q_1803_e)block, R_2836_Y.n_1700_B(Items.E_738_L))).J_1907_R(E_2006_R.n_1700_B(Enchantments.C_2741_M, 0.005f, 0.0055555557f, 0.00625f, 0.008333334f, 0.025f))));
    }

    private static p_4985_U.n_1700_B n_1700_B(T_2915_h block, q_1613_l itemConditional, q_1613_l withBonus, LootItemCondition.n_1700_B conditionBuilder) {
        return n_4365_u.n_1700_B((q_1803_e)block, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(itemConditional).J_1907_R(conditionBuilder)).n_1700_B(R_2836_Y.n_1700_B(withBonus)))).n_1700_B(n_2967_p.n_1700_B().J_1907_R(conditionBuilder).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(withBonus).J_1907_R(m_4962_f.n_1700_B(Enchantments.C_2741_M, 0.5714286f, 3)))));
    }

    private static p_4985_U.n_1700_B M_588_G(T_2915_h sheared) {
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().J_1907_R(R_4764_Y).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(sheared).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(2)))));
    }

    private static p_4985_U.n_1700_B J_1907_R(T_2915_h block, T_2915_h sheared) {
        d_1292_N.n_1700_B builder = ((e_2748_L.n_1700_B)((u_1373_N.n_1700_B)R_2836_Y.n_1700_B(sheared).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(2)))).J_1907_R(R_4764_Y)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)n_4365_u.n_1700_B((q_1803_e)block, R_2836_Y.n_1700_B(Items.G_4691_Q))).J_1907_R(n_430_n.n_1700_B(0.125f)));
        return p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(builder).J_1907_R(w_2512_g.n_1700_B(block).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(DoublePlantBlock.P_4830_p, g_3212_H.J_1907_R))).J_1907_R(d_1384_D.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(D_4237_z.n_1700_B.n_1700_B().n_1700_B(block).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(DoublePlantBlock.P_4830_p, g_3212_H.n_1700_B).J_1907_R()).J_1907_R()), new c_1514_x(0, 1, 0)))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(builder).J_1907_R(w_2512_g.n_1700_B(block).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(DoublePlantBlock.P_4830_p, g_3212_H.n_1700_B))).J_1907_R(d_1384_D.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(D_4237_z.n_1700_B.n_1700_B().n_1700_B(block).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(DoublePlantBlock.P_4830_p, g_3212_H.J_1907_R).J_1907_R()).J_1907_R()), new c_1514_x(0, -1, 0))));
    }

    public static p_4985_U.n_1700_B n_1700_B() {
        return p_4985_U.J_1907_R();
    }

    public void n_1700_B(BiConsumer<g_2336_b, p_4985_U.n_1700_B> p_accept_1_) {
        this.G_564_y(a_3742_W.R_4764_Y);
        this.G_564_y(a_3742_W.G_564_y);
        this.G_564_y(a_3742_W.P_1922_E);
        this.G_564_y(a_3742_W.u_1723_Y);
        this.G_564_y(a_3742_W.v_4262_N);
        this.G_564_y(a_3742_W.w_1484_f);
        this.G_564_y(a_3742_W.s_956_w);
        this.G_564_y(a_3742_W.u_2550_I);
        this.G_564_y(a_3742_W.P_4830_p);
        this.G_564_y(a_3742_W.h_1847_R);
        this.G_564_y(a_3742_W.Q_4569_t);
        this.G_564_y(a_3742_W.M_182_A);
        this.G_564_y(a_3742_W.t_1786_h);
        this.G_564_y(a_3742_W.multiplayerClientSuggestionProvider);
        this.G_564_y(a_3742_W.w_1457_N);
        this.G_564_y(a_3742_W.Y_601_j);
        this.G_564_y(a_3742_W.Y_259_p);
        this.G_564_y(a_3742_W.Q_2552_b);
        this.G_564_y(a_3742_W.C_2741_M);
        this.G_564_y(a_3742_W.k_2293_S);
        this.G_564_y(a_3742_W.q_2307_F);
        this.G_564_y(a_3742_W.A_4115_X);
        this.G_564_y(a_3742_W.Y_1740_V);
        this.G_564_y(a_3742_W.x_607_J);
        this.G_564_y(a_3742_W.e_4240_b);
        this.G_564_y(a_3742_W.z_1737_N);
        this.G_564_y(a_3742_W.v_4276_D);
        this.G_564_y(a_3742_W.d_2461_k);
        this.G_564_y(a_3742_W.G_624_v);
        this.G_564_y(a_3742_W.T_2506_i);
        this.G_564_y(a_3742_W.q_4610_l);
        this.G_564_y(a_3742_W.z_4693_k);
        this.G_564_y(a_3742_W.g_221_o);
        this.G_564_y(a_3742_W.e_2887_G);
        this.G_564_y(a_3742_W.B_1668_F);
        this.G_564_y(a_3742_W.g_164_R);
        this.G_564_y(a_3742_W.X_933_l);
        this.G_564_y(a_3742_W.w_2223_C);
        this.G_564_y(a_3742_W.B_3068_A);
        this.G_564_y(a_3742_W.Z_976_R);
        this.G_564_y(a_3742_W.H_1990_U);
        this.G_564_y(a_3742_W.N_2525_X);
        this.G_564_y(a_3742_W.c_4037_x);
        this.G_564_y(a_3742_W.g_2268_R);
        this.G_564_y(a_3742_W.T_3594_S);
        this.G_564_y(a_3742_W.D_4792_h);
        this.G_564_y(a_3742_W.s_2632_s);
        this.G_564_y(a_3742_W.l_1233_K);
        this.G_564_y(a_3742_W.z_1333_t);
        this.G_564_y(a_3742_W.O_508_d);
        this.G_564_y(a_3742_W.r_715_M);
        this.G_564_y(a_3742_W.W_4813_f);
        this.G_564_y(a_3742_W.AdvancementList);
        this.G_564_y(a_3742_W.j_276_v);
        this.G_564_y(a_3742_W.UploadStatus);
        this.G_564_y(a_3742_W.k_3961_g);
        this.G_564_y(a_3742_W.h_4320_q);
        this.G_564_y(a_3742_W.t_4219_U);
        this.G_564_y(a_3742_W.V_1446_Y);
        this.G_564_y(a_3742_W.PlayerInfo);
        this.G_564_y(a_3742_W.l_4537_E);
        this.G_564_y(a_3742_W.F_2624_D);
        this.G_564_y(a_3742_W.RealmsDefaultUncaughtExceptionHandler);
        this.G_564_y(a_3742_W.j_2266_I);
        this.G_564_y(a_3742_W.R_3077_Z);
        this.G_564_y(a_3742_W.RealmsScreenWithCallback);
        this.G_564_y(a_3742_W.M_2677_i);
        this.G_564_y(a_3742_W.c_132_F);
        this.G_564_y(a_3742_W.g_4106_L);
        this.G_564_y(a_3742_W.RealmsClientOutdatedScreen);
        this.G_564_y(a_3742_W.W_3464_O);
        this.G_564_y(a_3742_W.RealmsConfirmScreen);
        this.G_564_y(a_3742_W.RealmsCreateRealmScreen);
        this.G_564_y(a_3742_W.C_290_v);
        this.G_564_y(a_3742_W.w_728_N);
        this.G_564_y(a_3742_W.J_4256_G);
        this.G_564_y(a_3742_W.RealmsLongConfirmationScreen);
        this.G_564_y(a_3742_W.RealmsLongRunningMcoTaskScreen);
        this.G_564_y(a_3742_W.i_2993_w);
        this.G_564_y(a_3742_W.RealmsParentalConsentScreen);
        this.G_564_y(a_3742_W.s_1671_u);
        this.G_564_y(a_3742_W.RealmsResetNormalWorldScreen);
        this.G_564_y(a_3742_W.C_3538_G);
        this.G_564_y(a_3742_W.A_3959_N);
        this.G_564_y(a_3742_W.G_424_k);
        this.G_564_y(a_3742_W.RealmsSettingsScreen);
        this.G_564_y(a_3742_W.f_1043_S);
        this.G_564_y(a_3742_W.F_4247_a);
        this.G_564_y(a_3742_W.J_739_q);
        this.G_564_y(a_3742_W.C_1162_e);
        this.G_564_y(a_3742_W.D_4361_a);
        this.G_564_y(a_3742_W.f_3449_S);
        this.G_564_y(a_3742_W.u_55_V);
        this.G_564_y(a_3742_W.JsonUtils);
        this.G_564_y(a_3742_W.RealmsPersistence);
        this.G_564_y(a_3742_W.y_2772_m);
        this.G_564_y(a_3742_W.H_1883_T);
        this.G_564_y(a_3742_W.d_4007_L);
        this.G_564_y(a_3742_W.U_1341_G);
        this.G_564_y(a_3742_W.ClientBootstrap);
        this.G_564_y(a_3742_W.MinMaxBounds);
        this.G_564_y(a_3742_W.o_2341_D);
        this.G_564_y(a_3742_W.F_3572_x);
        this.G_564_y(a_3742_W.P_5000_x);
        this.G_564_y(a_3742_W.O_1309_Q);
        this.G_564_y(a_3742_W.O_2934_T);
        this.G_564_y(a_3742_W.X_4895_T);
        this.G_564_y(a_3742_W.L_103_L);
        this.G_564_y(a_3742_W.n_3197_X);
        this.G_564_y(a_3742_W.P_2947_S);
        this.G_564_y(a_3742_W.O_4761_U);
        this.G_564_y(a_3742_W.w_2705_t);
        this.G_564_y(a_3742_W.L_3570_A);
        this.G_564_y(a_3742_W.Y_776_s);
        this.G_564_y(a_3742_W.S_3139_t);
        this.G_564_y(a_3742_W.x_92_N);
        this.G_564_y(a_3742_W.i_601_W);
        this.G_564_y(a_3742_W.X_1313_W);
        this.G_564_y(a_3742_W.x_4991_F);
        this.G_564_y(a_3742_W.Z_759_W);
        this.G_564_y(a_3742_W.f_1574_f);
        this.G_564_y(a_3742_W.l_1268_F);
        this.G_564_y(a_3742_W.J_303_C);
        this.G_564_y(a_3742_W.H_1873_g);
        this.G_564_y(a_3742_W.o_3599_Z);
        this.G_564_y(a_3742_W.d_3244_b);
        this.G_564_y(a_3742_W.l_3609_d);
        this.G_564_y(a_3742_W.r_2478_U);
        this.G_564_y(a_3742_W.h_2848_I);
        this.G_564_y(a_3742_W.A_3244_K);
        this.G_564_y(a_3742_W.i_3196_G);
        this.G_564_y(a_3742_W.C_415_h);
        this.G_564_y(a_3742_W.v_165_F);
        this.G_564_y(a_3742_W.s_4990_V);
        this.G_564_y(a_3742_W.b_2312_j);
        this.G_564_y(a_3742_W.I_4348_c);
        this.G_564_y(a_3742_W.X_2048_Y);
        this.G_564_y(a_3742_W.l_2647_k);
        this.G_564_y(a_3742_W.T_437_o);
        this.G_564_y(a_3742_W.q_817_e);
        this.G_564_y(a_3742_W.r_260_T);
        this.G_564_y(a_3742_W.Q_2753_H);
        this.G_564_y(a_3742_W.Y_2080_q);
        this.G_564_y(a_3742_W.g_4560_H);
        this.G_564_y(a_3742_W.z_2025_Z);
        this.G_564_y(a_3742_W.f_691_R);
        this.G_564_y(a_3742_W.I_4481_g);
        this.G_564_y(a_3742_W.g_1734_y);
        this.G_564_y(a_3742_W.I_3457_f);
        this.G_564_y(a_3742_W.Z_4720_K);
        this.G_564_y(a_3742_W.k_3129_Y);
        this.G_564_y(a_3742_W.B_1146_q);
        this.G_564_y(a_3742_W.F_2860_q);
        this.G_564_y(a_3742_W.S_4035_N);
        this.G_564_y(a_3742_W.h_1015_G);
        this.G_564_y(a_3742_W.d_4500_Q);
        this.G_564_y(a_3742_W.O_2761_o);
        this.G_564_y(a_3742_W.m_1621_v);
        this.G_564_y(a_3742_W.e_1231_S);
        this.G_564_y(a_3742_W.B_3040_x);
        this.G_564_y(a_3742_W.E_4256_w);
        this.G_564_y(a_3742_W.d_2169_p);
        this.G_564_y(a_3742_W.B_2580_P);
        this.G_564_y(a_3742_W.U_144_f);
        this.G_564_y(a_3742_W.g_1031_K);
        this.G_564_y(a_3742_W.g_134_G);
        this.G_564_y(a_3742_W.h_3859_C);
        this.G_564_y(a_3742_W.F_1446_q);
        this.G_564_y(a_3742_W.r_4790_y);
        this.G_564_y(a_3742_W.V_537_k);
        this.G_564_y(a_3742_W.c_2086_l);
        this.G_564_y(a_3742_W.o_4117_e);
        this.G_564_y(a_3742_W.U_3758_B);
        this.G_564_y(a_3742_W.y_3417_N);
        this.G_564_y(a_3742_W.A_1306_N);
        this.G_564_y(a_3742_W.D_3612_q);
        this.G_564_y(a_3742_W.ModuleCategory);
        this.G_564_y(a_3742_W.Module);
        this.G_564_y(a_3742_W.BooleanSetting);
        this.G_564_y(a_3742_W.H_1491_c);
        this.G_564_y(a_3742_W.c_1608_O);
        this.G_564_y(a_3742_W.ModeSetting);
        this.G_564_y(a_3742_W.MultiBooleanSetting);
        this.G_564_y(a_3742_W.O_3016_i);
        this.G_564_y(a_3742_W.b_2037_V);
        this.G_564_y(a_3742_W.N_4006_T);
        this.G_564_y(a_3742_W.k_1608_N);
        this.G_564_y(a_3742_W.s_3815_K);
        this.G_564_y(a_3742_W.P_3676_m);
        this.G_564_y(a_3742_W.Q_4222_k);
        this.G_564_y(a_3742_W.f_887_Z);
        this.G_564_y(a_3742_W.R_3213_X);
        this.G_564_y(a_3742_W.H_1475_K);
        this.G_564_y(a_3742_W.I_2209_R);
        this.G_564_y(a_3742_W.h_3858_e);
        this.G_564_y(a_3742_W.l_4397_i);
        this.G_564_y(a_3742_W.t_4433_T);
        this.G_564_y(a_3742_W.AimAssist);
        this.G_564_y(a_3742_W.AntiBot);
        this.G_564_y(a_3742_W.AntiSurround);
        this.G_564_y(a_3742_W.s_4447_V);
        this.G_564_y(a_3742_W.AttackAura);
        this.G_564_y(a_3742_W.AutoAnchor);
        this.G_564_y(a_3742_W.AutoCrystal);
        this.G_564_y(a_3742_W.AutoExplosion);
        this.G_564_y(a_3742_W.AutoSwap);
        this.G_564_y(a_3742_W.AutoTotem);
        this.G_564_y(a_3742_W.AutoTrap);
        this.G_564_y(a_3742_W.s_4054_j);
        this.G_564_y(a_3742_W.I_4683_a);
        this.G_564_y(a_3742_W.n_2689_l);
        this.G_564_y(a_3742_W.g_4841_c);
        this.G_564_y(a_3742_W.q_2475_j);
        this.G_564_y(a_3742_W.z_2311_U);
        this.G_564_y(a_3742_W.Q_1082_O);
        this.G_564_y(a_3742_W.G_3540_E);
        this.G_564_y(a_3742_W.e_4654_Y);
        this.G_564_y(a_3742_W.b_967_P);
        this.G_564_y(a_3742_W.P_459_I);
        this.G_564_y(a_3742_W.M_4609_z);
        this.G_564_y(a_3742_W.AuctionHelper);
        this.G_564_y(a_3742_W.AutoAccept);
        this.G_564_y(a_3742_W.AutoContract);
        this.G_564_y(a_3742_W.AutoDuel);
        this.G_564_y(a_3742_W.BedrockProxy);
        this.G_564_y(a_3742_W.BetterMinecraft);
        this.G_564_y(a_3742_W.BotAutoCollector);
        this.G_564_y(a_3742_W.Bots);
        this.G_564_y(a_3742_W.ClickFriend);
        this.G_564_y(a_3742_W.ClientSpoof);
        this.G_564_y(a_3742_W.DeathCoords);
        this.G_564_y(a_3742_W.DiscordRPC);
        this.G_564_y(a_3742_W.EcSaver);
        this.G_564_y(a_3742_W.ElytraHelper);
        this.G_564_y(a_3742_W.FlagDetector);
        this.G_564_y(a_3742_W.Globals);
        this.G_564_y(a_3742_W.InventoryPlus);
        this.G_564_y(a_3742_W.ItemHelper);
        this.G_564_y(a_3742_W.BlockFly);
        this.G_564_y(a_3742_W.BoatNoClip);
        this.G_564_y(a_3742_W.ElytraResolver);
        this.G_564_y(a_3742_W.ElytraJump);
        this.G_564_y(a_3742_W.SuperFirework);
        this.G_564_y(a_3742_W.Timer);
        this.G_564_y(a_3742_W.WaterSpeed);
        this.G_564_y(a_3742_W.AntiAFK);
        this.G_564_y(a_3742_W.AutoArmor);
        this.G_564_y(a_3742_W.AutoBuy);
        this.G_564_y(a_3742_W.AutoDupe);
        this.G_564_y(a_3742_W.AutoEat);
        this.G_564_y(a_3742_W.AutoFarm);
        this.G_564_y(a_3742_W.AutoFish);
        this.G_564_y(a_3742_W.AutoJoiner);
        this.G_564_y(a_3742_W.AutoLeave);
        this.G_564_y(a_3742_W.AutoLes);
        this.G_564_y(a_3742_W.AutoPilot);
        this.G_564_y(a_3742_W.BaritoneSettings);
        this.G_564_y(a_3742_W.ClickPearl);
        this.G_564_y(a_3742_W.CrystalOptimizer);
        this.G_564_y(a_3742_W.FastBreak);
        this.G_564_y(a_3742_W.FastPlace);
        this.G_564_y(a_3742_W.LevitationControl);
        this.G_564_y(a_3742_W.LockSlot);
        this.G_564_y(a_3742_W.NoInteract);
        this.G_564_y(a_3742_W.Nuker);
        this.G_564_y(a_3742_W.RegionExploit);
        this.G_564_y(a_3742_W.Y_2805_J);
        this.G_564_y(a_3742_W.ExtendedTab);
        this.G_564_y(a_3742_W.FireworkESP);
        this.G_564_y(a_3742_W.FullBright);
        this.G_564_y(a_3742_W.Glint);
        this.G_564_y(a_3742_W.f_2247_K);
        this.G_564_y(a_3742_W.HitEffect);
        this.G_564_y(a_3742_W.Interface);
        this.G_564_y(a_3742_W.ItemPhysics);
        this.G_564_y(a_3742_W.ItemRadius);
        this.G_564_y(a_3742_W.JumpCircle);
        this.G_564_y(a_3742_W.KillEffect);
        this.G_564_y(a_3742_W.LogoutSpots);
        this.G_564_y(a_3742_W.w_2099_r);
        this.G_564_y(a_3742_W.R_4688_l);
        this.G_564_y(a_3742_W.ObjectInfo);
        this.G_564_y(a_3742_W.Particles);
        this.G_564_y(a_3742_W.Particular);
        this.G_564_y(a_3742_W.Prediction);
        this.G_564_y(a_3742_W.Removals);
        this.G_564_y(a_3742_W.SantaHat);
        this.G_564_y(a_3742_W.SeeInvisibles);
        this.G_564_y(a_3742_W.ShulkerPreview);
        this.G_564_y(a_3742_W.Skeleton);
        this.G_564_y(a_3742_W.t_1595_x);
        this.G_564_y(a_3742_W.Tags);
        this.G_564_y(a_3742_W.ThirdPerson);
        this.G_564_y(a_3742_W.TotemPop);
        this.G_564_y(a_3742_W.Tracers);
        this.G_564_y(a_3742_W.Trails);
        this.G_564_y(a_3742_W.Trajectory);
        this.G_564_y(a_3742_W.ViewModel);
        this.G_564_y(a_3742_W.WorldParticles);
        this.G_564_y(a_3742_W.CavityFinder);
        this.G_564_y(a_3742_W.e_87_p);
        this.G_564_y(a_3742_W.K_2336_H);
        this.G_564_y(a_3742_W.n_421_x);
        this.G_564_y(a_3742_W.p_1976_q);
        this.G_564_y(a_3742_W.A_4252_m);
        this.G_564_y(a_3742_W.a_794_m);
        this.G_564_y(a_3742_W.E_170_p);
        this.G_564_y(a_3742_W.m_229_F);
        this.G_564_y(a_3742_W.f_4340_D);
        this.G_564_y(a_3742_W.A_2204_Z);
        this.G_564_y(a_3742_W.R_4912_F);
        this.G_564_y(a_3742_W.S_315_z);
        this.G_564_y(a_3742_W.o_977_F);
        this.G_564_y(a_3742_W.S_1165_y);
        this.G_564_y(a_3742_W.E_738_L);
        this.G_564_y(a_3742_W.R_1796_s);
        this.G_564_y(a_3742_W.T_797_O);
        this.G_564_y(a_3742_W.k_2273_q);
        this.G_564_y(a_3742_W.D_1621_L);
        this.G_564_y(a_3742_W.ServerHandshakePacketListener);
        this.G_564_y(a_3742_W.q_4124_m);
        this.G_564_y(a_3742_W.m_396_H);
        this.G_564_y(a_3742_W.V_3441_j);
        this.G_564_y(a_3742_W.F_391_H);
        this.G_564_y(a_3742_W.t_1509_b);
        this.G_564_y(a_3742_W.f_800_j);
        this.G_564_y(a_3742_W.R_2329_T);
        this.G_564_y(a_3742_W.F_747_P);
        this.G_564_y(a_3742_W.T_1170_t);
        this.G_564_y(a_3742_W.k_2282_P);
        this.G_564_y(a_3742_W.U_2474_c);
        this.G_564_y(a_3742_W.j_2302_z);
        this.G_564_y(a_3742_W.q_4361_M);
        this.G_564_y(a_3742_W.f_508_U);
        this.G_564_y(a_3742_W.A_1603_w);
        this.G_564_y(a_3742_W.V_4557_X);
        this.G_564_y(a_3742_W.h_3066_J);
        this.G_564_y(a_3742_W.m_38_G);
        this.G_564_y(a_3742_W.k_4946_A);
        this.G_564_y(a_3742_W.Y_2143_L);
        this.G_564_y(a_3742_W.U_567_E);
        this.G_564_y(a_3742_W.i_1479_B);
        this.G_564_y(a_3742_W.t_4562_T);
        this.G_564_y(a_3742_W.H_3529_d);
        this.G_564_y(a_3742_W.U_3005_m);
        this.G_564_y(a_3742_W.G_1539_D);
        this.G_564_y(a_3742_W.W_2770_z);
        this.G_564_y(a_3742_W.u_1934_K);
        this.G_564_y(a_3742_W.u_925_K);
        this.G_564_y(a_3742_W.Z_361_l);
        this.G_564_y(a_3742_W.v_570_f);
        this.G_564_y(a_3742_W.m_1628_s);
        this.G_564_y(a_3742_W.i_770_g);
        this.G_564_y(a_3742_W.B_1335_M);
        this.G_564_y(a_3742_W.K_4518_s);
        this.G_564_y(a_3742_W.WrappedMinMaxBounds);
        this.G_564_y(a_3742_W.m_3052_r);
        this.G_564_y(a_3742_W.X_1303_p);
        this.G_564_y(a_3742_W.A_2629_w);
        this.G_564_y(a_3742_W.C_3304_p);
        this.G_564_y(a_3742_W.J_1008_m);
        this.G_564_y(a_3742_W.T_4001_f);
        this.G_564_y(a_3742_W.M_712_N);
        this.G_564_y(a_3742_W.h_1723_G);
        this.G_564_y(a_3742_W.CriterionTrigger);
        this.G_564_y(a_3742_W.d_3769_f);
        this.G_564_y(a_3742_W.n_4560_z);
        this.G_564_y(a_3742_W.Z_2021_u);
        this.G_564_y(a_3742_W.K_4866_h);
        this.G_564_y(a_3742_W.DamageSourcePredicate);
        this.G_564_y(a_3742_W.I_3736_z);
        this.G_564_y(a_3742_W.A_1604_A);
        this.G_564_y(a_3742_W.k_200_a);
        this.G_564_y(a_3742_W.n_3115_n);
        this.G_564_y(a_3742_W.q_608_V);
        this.G_564_y(a_3742_W.P_1965_C);
        this.G_564_y(a_3742_W.r_1970_q);
        this.G_564_y(a_3742_W.o_3456_E);
        this.G_564_y(a_3742_W.m_3168_q);
        this.G_564_y(a_3742_W.V_3982_O);
        this.G_564_y(a_3742_W.b_1430_k);
        this.G_564_y(a_3742_W.LightPredicate);
        this.G_564_y(a_3742_W.B_368_w);
        this.G_564_y(a_3742_W.m_1964_F);
        this.G_564_y(a_3742_W.g_1096_r);
        this.G_564_y(a_3742_W.Y_3066_B);
        this.G_564_y(a_3742_W.v_2746_S);
        this.G_564_y(a_3742_W.A_2487_t);
        this.G_564_y(a_3742_W.C_3528_u);
        this.G_564_y(a_3742_W.Y_3588_g);
        this.G_564_y(a_3742_W.j_2461_G);
        this.G_564_y(a_3742_W.u_3578_p);
        this.G_564_y(a_3742_W.Q_1036_Q);
        this.G_564_y(a_3742_W.V_1824_v);
        this.G_564_y(a_3742_W.e_1503_j);
        this.G_564_y(a_3742_W.z_1100_b);
        this.G_564_y(a_3742_W.e_2973_e);
        this.G_564_y(a_3742_W.b_1557_h);
        this.G_564_y(a_3742_W.AbstractBannerBlock);
        this.G_564_y(a_3742_W.r_4879_Z);
        this.G_564_y(a_3742_W.U_1258_d);
        this.G_564_y(a_3742_W.D_4237_z);
        this.n_1700_B(a_3742_W.Z_735_d, (q_1803_e)a_3742_W.s_956_w);
        this.n_1700_B(a_3742_W.I_3637_j, Items.Animation);
        this.n_1700_B(a_3742_W.InvManager, (q_1803_e)a_3742_W.s_956_w);
        this.n_1700_B(a_3742_W.g_24_p, (q_1803_e)a_3742_W.R_1796_s);
        this.n_1700_B(a_3742_W.m_3828_C, (q_1803_e)a_3742_W.t_1509_b);
        this.n_1700_B(a_3742_W.J_1907_R, (T_2915_h stone) -> n_4365_u.J_1907_R(stone, (q_1803_e)a_3742_W.P_4830_p));
        this.n_1700_B(a_3742_W.t_148_a, (T_2915_h grass) -> n_4365_u.J_1907_R(grass, (q_1803_e)a_3742_W.s_956_w));
        this.n_1700_B(a_3742_W.M_588_G, (T_2915_h podzol) -> n_4365_u.J_1907_R(podzol, (q_1803_e)a_3742_W.s_956_w));
        this.n_1700_B(a_3742_W.A_2714_y, (T_2915_h mycelium) -> n_4365_u.J_1907_R(mycelium, (q_1803_e)a_3742_W.s_956_w));
        this.n_1700_B(a_3742_W.S_234_U, (T_2915_h tubeCoral) -> n_4365_u.J_1907_R(tubeCoral, (q_1803_e)a_3742_W.k_2273_q));
        this.n_1700_B(a_3742_W.B_707_U, (T_2915_h brainCoral) -> n_4365_u.J_1907_R(brainCoral, (q_1803_e)a_3742_W.D_1621_L));
        this.n_1700_B(a_3742_W.N_1833_W, (T_2915_h bubbleCoral) -> n_4365_u.J_1907_R(bubbleCoral, (q_1803_e)a_3742_W.ServerHandshakePacketListener));
        this.n_1700_B(a_3742_W.a_2727_J, (T_2915_h fireCoral) -> n_4365_u.J_1907_R(fireCoral, (q_1803_e)a_3742_W.q_4124_m));
        this.n_1700_B(a_3742_W.D_1410_T, (T_2915_h hornCoral) -> n_4365_u.J_1907_R(hornCoral, (q_1803_e)a_3742_W.m_396_H));
        this.n_1700_B(a_3742_W.ServerFunctionManager, (T_2915_h crimson) -> n_4365_u.J_1907_R(crimson, (q_1803_e)a_3742_W.i_3196_G));
        this.n_1700_B(a_3742_W.ServerAdvancementManager, (T_2915_h warped) -> n_4365_u.J_1907_R(warped, (q_1803_e)a_3742_W.i_3196_G));
        this.n_1700_B(a_3742_W.UploadTokenCache, (T_2915_h bookshelf) -> n_4365_u.n_1700_B(bookshelf, Items.K_4237_u, S_2110_L.n_1700_B(3)));
        this.n_1700_B(a_3742_W.v_887_r, (T_2915_h clay) -> n_4365_u.n_1700_B(clay, Items.i_4833_u, S_2110_L.n_1700_B(4)));
        this.n_1700_B(a_3742_W.k_2348_i, (T_2915_h enderChest) -> n_4365_u.n_1700_B(enderChest, (q_1803_e)a_3742_W.ClientBootstrap, S_2110_L.n_1700_B(8)));
        this.n_1700_B(a_3742_W.l_697_B, (T_2915_h snow) -> n_4365_u.n_1700_B(snow, Items.i_770_g, S_2110_L.n_1700_B(4)));
        this.n_1700_B(a_3742_W.ChestStealer, n_4365_u.n_1700_B((q_1803_e)Items.MagmaBlock, o_3393_s.n_1700_B(0.0f, 1.0f)));
        this.J_1907_R(a_3742_W.x_2635_q);
        this.J_1907_R(a_3742_W.f_2403_E);
        this.J_1907_R(a_3742_W.c_776_E);
        this.J_1907_R(a_3742_W.z_2372_L);
        this.J_1907_R(a_3742_W.t_2932_z);
        this.J_1907_R(a_3742_W.m_3147_m);
        this.J_1907_R(a_3742_W.I_1790_n);
        this.J_1907_R(a_3742_W.C_332_W);
        this.J_1907_R(a_3742_W.L_3537_K);
        this.J_1907_R(a_3742_W.z_3000_g);
        this.J_1907_R(a_3742_W.n_4915_F);
        this.J_1907_R(a_3742_W.y_2622_c);
        this.J_1907_R(a_3742_W.n_473_l);
        this.J_1907_R(a_3742_W.r_4414_L);
        this.J_1907_R(a_3742_W.P_2272_O);
        this.J_1907_R(a_3742_W.S_2828_i);
        this.J_1907_R(a_3742_W.y_4642_Y);
        this.J_1907_R(a_3742_W.h_1640_b);
        this.J_1907_R(a_3742_W.V_1176_p);
        this.J_1907_R(a_3742_W.y_2447_C);
        this.J_1907_R(a_3742_W.J_3635_s);
        this.J_1907_R(a_3742_W.o_82_k);
        this.J_1907_R(a_3742_W.h_973_D);
        this.J_1907_R(a_3742_W.f_2787_O);
        this.J_1907_R(a_3742_W.r_2090_h);
        this.J_1907_R(a_3742_W.y_2836_h);
        this.J_1907_R(a_3742_W.h_2396_v);
        this.J_1907_R(a_3742_W.x_2711_Y);
        this.J_1907_R(a_3742_W.w_2892_f);
        this.n_1700_B(a_3742_W.GuiMove, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.Flight, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.NoWeb, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.NoSlow, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.HighJump, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.u_1980_X, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.c_892_d, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.Speed, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.ElytraMotion, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.NoPush, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.t_4864_b, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.l_1757_S, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.Strafe, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.Spider, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.Sprint, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.NoFall, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.Step, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.NoJumpDelay, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.F_3698_k, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.Phase, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.Jesus, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.MoveHelper, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.m_4644_u, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.u_488_m, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.O_1043_U, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.v_1900_v, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.j_2129_E, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.W_1488_x, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.j_1654_T, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.l_3729_r, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.q_3115_L, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.p_863_D, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.E_4612_l, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.v_143_j, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.U_3443_A, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.z_936_s, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.I_4421_I, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.b_3334_n, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.r_2687_x, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.TickTrigger, n_4365_u::P_1922_E);
        this.n_1700_B(a_3742_W.AutoTool, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.AutoRespawn, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.AutoTrade, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.h_2739_B, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.AutoSoup, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.F_518_D, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.AutoPotion, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.U_4107_W, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.D_3640_k, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.S_4022_R, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.H_1083_k, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.R_3908_n, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.RealmsWorldResetDto, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.M_1641_O, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.ValueObject, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.dtoRealmsServerAddress, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.RealmsWorldOptions, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.RealmsServerPing, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.q_1982_R, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.RegionPingResult, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.U_1241_n, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.j_1564_a, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.F_1410_V, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.V_1225_t, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.w_612_n, (T_2915_h bed) -> n_4365_u.n_1700_B(bed, J_2868_p.P_4830_p, h_2829_o.n_1700_B));
        this.n_1700_B(a_3742_W.X_812_G, (T_2915_h lilac) -> n_4365_u.n_1700_B(lilac, DoublePlantBlock.P_4830_p, g_3212_H.J_1907_R));
        this.n_1700_B(a_3742_W.V_983_n, (T_2915_h sunflower) -> n_4365_u.n_1700_B(sunflower, DoublePlantBlock.P_4830_p, g_3212_H.J_1907_R));
        this.n_1700_B(a_3742_W.OpenWalls, (T_2915_h peony) -> n_4365_u.n_1700_B(peony, DoublePlantBlock.P_4830_p, g_3212_H.J_1907_R));
        this.n_1700_B(a_3742_W.NameProtect, (T_2915_h roseBush) -> n_4365_u.n_1700_B(roseBush, DoublePlantBlock.P_4830_p, g_3212_H.J_1907_R));
        this.n_1700_B(a_3742_W.TextRenderingUtils, p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)a_3742_W.TextRenderingUtils, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(a_3742_W.TextRenderingUtils).J_1907_R(w_2512_g.n_1700_B(a_3742_W.TextRenderingUtils).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(V_883_W.P_4830_p, false)))))));
        this.n_1700_B(a_3742_W.U_3823_u, (T_2915_h cocoa) -> p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)cocoa, R_2836_Y.n_1700_B(Items.M_712_N).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(S_2110_L.n_1700_B(3)).J_1907_R(w_2512_g.n_1700_B(cocoa).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(CocoaBlock.P_4830_p, 2))))))));
        this.n_1700_B(a_3742_W.Easing, (T_2915_h pickle) -> p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)a_3742_W.Easing, ((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(pickle).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(S_2110_L.n_1700_B(2)).J_1907_R(w_2512_g.n_1700_B(pickle).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(H_4584_y.P_4830_p, 2))))).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(S_2110_L.n_1700_B(3)).J_1907_R(w_2512_g.n_1700_B(pickle).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(H_4584_y.P_4830_p, 3))))).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(S_2110_L.n_1700_B(4)).J_1907_R(w_2512_g.n_1700_B(pickle).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(H_4584_y.P_4830_p, 4))))))));
        this.n_1700_B(a_3742_W.P_2068_y, (T_2915_h composter) -> p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B((u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)composter, R_2836_Y.n_1700_B(Items.y_3008_A)))).n_1700_B(n_2967_p.n_1700_B().n_1700_B(R_2836_Y.n_1700_B(Items.r_1970_q)).J_1907_R(w_2512_g.n_1700_B(composter).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(a_648_i.P_4830_p, 8)))));
        this.n_1700_B(a_3742_W.k_578_l, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.e_837_t, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.L_1362_X, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.Ops, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.c_1732_c, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.E_453_w, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.P_925_e, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.p_3749_n, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.NumberSetting, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.H_2506_c, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.F_2052_z, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.y_254_d, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.j_1376_w, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.W_3801_h, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.v_2826_q, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.F_489_x, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.i_4833_u, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.f_4705_f, n_4365_u::u_1723_Y);
        this.n_1700_B(a_3742_W.l_3370_o, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.K_4237_u, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.Z_3822_q, n_4365_u::n_1700_B);
        this.n_1700_B(a_3742_W.k_1052_R, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.EntityESP, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.Crosshair, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.CrystalESP, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.ChatBubbles, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.BlockOverlay, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.DistantAlpha, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.ArmorDurability, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.Chams, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.AspectRatio, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.AnomalyESP, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.x_555_z, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.BlockESP, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.Cosmetics, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.Emotions, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.Ambience, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.Arrows, n_4365_u::v_4262_N);
        this.n_1700_B(a_3742_W.n_3932_q, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.VoiceChat, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.a_2587_Z, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.TrashTalk, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.TapeMouse, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.D_3097_e, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.ScoreboardHealth, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.ToggleSounds, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.AhHelper, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.SRPSpoof, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.RussianRoulette, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.TPLoot, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.UseTracker, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.q_3386_W, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.t_2598_a, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.Spammer, n_4365_u::w_1484_f);
        this.n_1700_B(a_3742_W.Setting, (T_2915_h playerHead) -> p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)playerHead, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(playerHead).J_1907_R(p_1840_B.n_1700_B(p_1840_B.P_1922_E.G_564_y).n_1700_B("SkullOwner", "SkullOwner"))))));
        this.n_1700_B(a_3742_W.w_4866_k, n_4365_u::t_148_a);
        this.n_1700_B(a_3742_W.t_4057_p, n_4365_u::s_956_w);
        this.n_1700_B(a_3742_W.Ping, (T_2915_h leaves) -> n_4365_u.n_1700_B(leaves, a_3742_W.Q_2552_b, v_4262_N));
        this.n_1700_B(a_3742_W.RealmsClientConfig, (T_2915_h leaves) -> n_4365_u.n_1700_B(leaves, a_3742_W.k_2293_S, v_4262_N));
        this.n_1700_B(a_3742_W.p_178_J, (T_2915_h leaves) -> n_4365_u.n_1700_B(leaves, a_3742_W.C_2741_M, w_1484_f));
        this.n_1700_B(a_3742_W.i_1637_u, (T_2915_h leaves) -> n_4365_u.n_1700_B(leaves, a_3742_W.Y_259_p, v_4262_N));
        this.n_1700_B(a_3742_W.A_1038_p, (T_2915_h leaves) -> n_4365_u.J_1907_R(leaves, a_3742_W.Y_601_j, v_4262_N));
        this.n_1700_B(a_3742_W.f_4016_n, (T_2915_h leaves) -> n_4365_u.J_1907_R(leaves, a_3742_W.q_2307_F, v_4262_N));
        w_2512_g.n_1700_B ilootcondition$ibuilder = w_2512_g.n_1700_B(a_3742_W.FreeCam).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(BeetrootBlock.P_4830_p, 3));
        this.n_1700_B(a_3742_W.FreeCam, n_4365_u.n_1700_B(a_3742_W.FreeCam, Items.s_3401_U, Items.MushroomBlock, ilootcondition$ibuilder));
        w_2512_g.n_1700_B ilootcondition$ibuilder1 = w_2512_g.n_1700_B(a_3742_W.l_4088_R).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(CropBlock.h_1847_R, 7));
        this.n_1700_B(a_3742_W.l_4088_R, n_4365_u.n_1700_B(a_3742_W.l_4088_R, Items.V_3441_j, Items.G_4691_Q, ilootcondition$ibuilder1));
        w_2512_g.n_1700_B ilootcondition$ibuilder2 = w_2512_g.n_1700_B(a_3742_W.P_2295_B).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(k_4738_s.h_1847_R, 7));
        this.n_1700_B(a_3742_W.P_2295_B, n_4365_u.n_1700_B((q_1803_e)a_3742_W.P_2295_B, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(R_2836_Y.n_1700_B(Items.BaseCoralWallFanBlock))).n_1700_B(n_2967_p.n_1700_B().J_1907_R(ilootcondition$ibuilder2).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.BaseCoralWallFanBlock).J_1907_R(m_4962_f.n_1700_B(Enchantments.C_2741_M, 0.5714286f, 3))))));
        w_2512_g.n_1700_B ilootcondition$ibuilder3 = w_2512_g.n_1700_B(a_3742_W.U_1697_c).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(q_3398_T.h_1847_R, 7));
        this.n_1700_B(a_3742_W.U_1697_c, n_4365_u.n_1700_B((q_1803_e)a_3742_W.U_1697_c, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(R_2836_Y.n_1700_B(Items.l_683_e))).n_1700_B(n_2967_p.n_1700_B().J_1907_R(ilootcondition$ibuilder3).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.l_683_e).J_1907_R(m_4962_f.n_1700_B(Enchantments.C_2741_M, 0.5714286f, 3)))).n_1700_B(n_2967_p.n_1700_B().J_1907_R(ilootcondition$ibuilder3).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.S_3458_C).J_1907_R(n_430_n.n_1700_B(0.02f))))));
        this.n_1700_B(a_3742_W.s_4405_m, (T_2915_h sweetBerry) -> n_4365_u.n_1700_B((q_1803_e)sweetBerry, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().J_1907_R(w_2512_g.n_1700_B(a_3742_W.s_4405_m).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SweetBerryBushBlock.P_4830_p, 3))).n_1700_B(R_2836_Y.n_1700_B(Items.D_265_n)).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(2.0f, 3.0f))).J_1907_R(m_4962_f.J_1907_R(Enchantments.C_2741_M))).n_1700_B(n_2967_p.n_1700_B().J_1907_R(w_2512_g.n_1700_B(a_3742_W.s_4405_m).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SweetBerryBushBlock.P_4830_p, 2))).n_1700_B(R_2836_Y.n_1700_B(Items.D_265_n)).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(1.0f, 2.0f))).J_1907_R(m_4962_f.J_1907_R(Enchantments.C_2741_M)))));
        this.n_1700_B(a_3742_W.b_2625_m, (T_2915_h brownMushroom) -> n_4365_u.R_4764_Y(brownMushroom, a_3742_W.JsonUtils));
        this.n_1700_B(a_3742_W.q_3401_q, (T_2915_h redMushroom) -> n_4365_u.R_4764_Y(redMushroom, a_3742_W.RealmsPersistence));
        this.n_1700_B(a_3742_W.n_3318_d, (T_2915_h coal) -> n_4365_u.n_1700_B(coal, Items.T_797_O));
        this.n_1700_B(a_3742_W.V_1665_T, (T_2915_h emerald) -> n_4365_u.n_1700_B(emerald, Items.Y_2905_A));
        this.n_1700_B(a_3742_W.N_2266_w, (T_2915_h netherQuartz) -> n_4365_u.n_1700_B(netherQuartz, Items.FlowerBlock));
        this.n_1700_B(a_3742_W.L_4248_u, (T_2915_h diamond) -> n_4365_u.n_1700_B(diamond, Items.k_2273_q));
        this.n_1700_B(a_3742_W.d_2427_y, (T_2915_h netherGold) -> n_4365_u.n_1700_B(netherGold, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)netherGold, ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.u_3578_p).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(2.0f, 6.0f)))).J_1907_R(m_4962_f.n_1700_B(Enchantments.C_2741_M)))));
        this.n_1700_B(a_3742_W.D_60_a, (T_2915_h lapis) -> n_4365_u.n_1700_B(lapis, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)lapis, ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.W_4813_f).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(4.0f, 9.0f)))).J_1907_R(m_4962_f.n_1700_B(Enchantments.C_2741_M)))));
        this.n_1700_B(a_3742_W.y_1700_S, (T_2915_h cobweb) -> n_4365_u.R_4764_Y(cobweb, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)cobweb, R_2836_Y.n_1700_B(Items.Animation))));
        this.n_1700_B(a_3742_W.r_3651_U, (T_2915_h deadBush) -> n_4365_u.J_1907_R(deadBush, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)deadBush, R_2836_Y.n_1700_B(Items.A_4514_U).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(0.0f, 2.0f))))));
        this.n_1700_B(a_3742_W.U_3554_Q, n_4365_u::G_564_y);
        this.n_1700_B(a_3742_W.RowButton, n_4365_u::G_564_y);
        this.n_1700_B(a_3742_W.U_4087_m, n_4365_u::G_564_y);
        this.n_1700_B(a_3742_W.LongRunningTask, n_4365_u.M_588_G(a_3742_W.RowButton));
        this.n_1700_B(a_3742_W.PotionTracker, (T_2915_h fern) -> n_4365_u.J_1907_R(fern, a_3742_W.RetryCallException));
        this.n_1700_B(a_3742_W.Party, (T_2915_h tallgrass) -> n_4365_u.J_1907_R(tallgrass, a_3742_W.u_744_e));
        this.n_1700_B(a_3742_W.n_4539_g, (T_2915_h stem) -> n_4365_u.J_1907_R(stem, Items.y_2836_h));
        this.n_1700_B(a_3742_W.J_2061_p, (T_2915_h stem) -> n_4365_u.R_4764_Y(stem, Items.y_2836_h));
        this.n_1700_B(a_3742_W.L_1733_J, (T_2915_h stem) -> n_4365_u.J_1907_R(stem, Items.WrappedMinMaxBounds));
        this.n_1700_B(a_3742_W.i_789_Q, (T_2915_h stem) -> n_4365_u.R_4764_Y(stem, Items.WrappedMinMaxBounds));
        this.n_1700_B(a_3742_W.ChorusExploit, (T_2915_h chorusFlower) -> p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)n_4365_u.n_1700_B((q_1803_e)chorusFlower, R_2836_Y.n_1700_B(chorusFlower))).J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B)))));
        this.n_1700_B(a_3742_W.RetryCallException, n_4365_u::u_2550_I);
        this.n_1700_B(a_3742_W.u_744_e, n_4365_u::u_2550_I);
        this.n_1700_B(a_3742_W.X_2960_b, (T_2915_h glowstone) -> n_4365_u.n_1700_B(glowstone, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)glowstone, ((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.AdvancementList).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(2.0f, 4.0f)))).J_1907_R(m_4962_f.J_1907_R(Enchantments.C_2741_M))).J_1907_R(O_215_U.n_1700_B(H_3357_D.n_1700_B(1, 4))))));
        this.n_1700_B(a_3742_W.E_3343_g, (T_2915_h melon) -> n_4365_u.n_1700_B(melon, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)melon, ((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.B_368_w).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(3.0f, 7.0f)))).J_1907_R(m_4962_f.J_1907_R(Enchantments.C_2741_M))).J_1907_R(O_215_U.n_1700_B(H_3357_D.J_1907_R(9))))));
        this.n_1700_B(a_3742_W.o_1800_r, (T_2915_h redstoneOre) -> n_4365_u.n_1700_B(redstoneOre, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)redstoneOre, ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.v_570_f).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(4.0f, 5.0f)))).J_1907_R(m_4962_f.J_1907_R(Enchantments.C_2741_M)))));
        this.n_1700_B(a_3742_W.T_33_Q, (T_2915_h seaLantern) -> n_4365_u.n_1700_B(seaLantern, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)seaLantern, ((e_2748_L.n_1700_B)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.FrostedIceBlock).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(2.0f, 3.0f)))).J_1907_R(m_4962_f.J_1907_R(Enchantments.C_2741_M))).J_1907_R(O_215_U.n_1700_B(H_3357_D.n_1700_B(1, 5))))));
        this.n_1700_B(a_3742_W.W_3729_Q, (T_2915_h netherWart) -> p_4985_U.J_1907_R().n_1700_B(n_4365_u.n_1700_B((q_1803_e)netherWart, n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.g_1096_r).J_1907_R((A_2178_U.n_1700_B)E_3705_H.n_1700_B(o_3393_s.n_1700_B(2.0f, 4.0f)).J_1907_R(w_2512_g.n_1700_B(netherWart).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(NetherWartBlock.P_4830_p, 3))))).J_1907_R((A_2178_U.n_1700_B)m_4962_f.J_1907_R(Enchantments.C_2741_M).J_1907_R(w_2512_g.n_1700_B(netherWart).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(NetherWartBlock.P_4830_p, 3))))))));
        this.n_1700_B(a_3742_W.X_290_I, (T_2915_h snow) -> p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B)).n_1700_B(d_1292_N.n_1700_B(new u_1373_N.n_1700_B[]{d_1292_N.n_1700_B(new u_1373_N.n_1700_B[]{R_2836_Y.n_1700_B(Items.i_770_g).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, true))), ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.i_770_g).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 2)))).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(2))), ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.i_770_g).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 3)))).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(3))), ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.i_770_g).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 4)))).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(4))), ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.i_770_g).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 5)))).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(5))), ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.i_770_g).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 6)))).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(6))), ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.i_770_g).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 7)))).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(7))), R_2836_Y.n_1700_B(Items.i_770_g).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(8)))}).J_1907_R(J_1907_R), d_1292_N.n_1700_B(new u_1373_N.n_1700_B[]{R_2836_Y.n_1700_B(a_3742_W.X_290_I).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, true))), ((u_1373_N.n_1700_B)R_2836_Y.n_1700_B(a_3742_W.X_290_I).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(2)))).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 2))), ((u_1373_N.n_1700_B)R_2836_Y.n_1700_B(a_3742_W.X_290_I).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(3)))).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 3))), ((u_1373_N.n_1700_B)R_2836_Y.n_1700_B(a_3742_W.X_290_I).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(4)))).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 4))), ((u_1373_N.n_1700_B)R_2836_Y.n_1700_B(a_3742_W.X_290_I).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(5)))).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 5))), ((u_1373_N.n_1700_B)R_2836_Y.n_1700_B(a_3742_W.X_290_I).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(6)))).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 6))), ((u_1373_N.n_1700_B)R_2836_Y.n_1700_B(a_3742_W.X_290_I).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(7)))).J_1907_R(w_2512_g.n_1700_B(snow).n_1700_B(r_2687_x.n_1700_B.n_1700_B().n_1700_B(SnowLayerBlock.P_4830_p, 7))), R_2836_Y.n_1700_B(a_3742_W.l_697_B)})}))));
        this.n_1700_B(a_3742_W.t_4043_B, (T_2915_h gravel) -> n_4365_u.n_1700_B(gravel, n_4365_u.n_1700_B((q_1803_e)gravel, ((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.W_1488_x).J_1907_R(E_2006_R.n_1700_B(Enchantments.C_2741_M, 0.1f, 0.14285715f, 0.25f, 1.0f))).n_1700_B(R_2836_Y.n_1700_B(gravel)))));
        this.n_1700_B(a_3742_W.k_1366_K, (T_2915_h unlit) -> n_4365_u.n_1700_B(unlit, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)unlit, R_2836_Y.n_1700_B(Items.d_560_A).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(2))))));
        this.n_1700_B(a_3742_W.S_3844_E, (T_2915_h gildedBlackstone) -> n_4365_u.n_1700_B(gildedBlackstone, n_4365_u.n_1700_B((q_1803_e)gildedBlackstone, ((e_2748_L.n_1700_B)((u_1373_N.n_1700_B)R_2836_Y.n_1700_B(Items.u_3578_p).J_1907_R(E_3705_H.n_1700_B(o_3393_s.n_1700_B(2.0f, 5.0f)))).J_1907_R(E_2006_R.n_1700_B(Enchantments.C_2741_M, 0.1f, 0.14285715f, 0.25f, 1.0f))).n_1700_B(R_2836_Y.n_1700_B(gildedBlackstone)))));
        this.n_1700_B(a_3742_W.y_4842_Z, (T_2915_h unlit) -> n_4365_u.n_1700_B(unlit, (u_1373_N.n_1700_B)n_4365_u.n_1700_B((q_1803_e)unlit, R_2836_Y.n_1700_B(Items.h_3270_j).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(1))))));
        this.R_4764_Y(a_3742_W.e_1992_r);
        this.R_4764_Y(a_3742_W.y_1945_D);
        this.R_4764_Y(a_3742_W.U_532_X);
        this.R_4764_Y(a_3742_W.P_4639_N);
        this.R_4764_Y(a_3742_W.i_4434_b);
        this.R_4764_Y(a_3742_W.P_328_a);
        this.R_4764_Y(a_3742_W.l_4627_h);
        this.R_4764_Y(a_3742_W.K_3372_t);
        this.R_4764_Y(a_3742_W.Q_2467_v);
        this.R_4764_Y(a_3742_W.m_2262_U);
        this.R_4764_Y(a_3742_W.S_4258_d);
        this.R_4764_Y(a_3742_W.m_891_U);
        this.R_4764_Y(a_3742_W.T_2971_J);
        this.R_4764_Y(a_3742_W.Q_3581_n);
        this.R_4764_Y(a_3742_W.I_685_r);
        this.R_4764_Y(a_3742_W.h_3270_j);
        this.R_4764_Y(a_3742_W.M_3508_C);
        this.R_4764_Y(a_3742_W.q_839_y);
        this.R_4764_Y(a_3742_W.HoleFill);
        this.R_4764_Y(a_3742_W.KBDisplacement);
        this.R_4764_Y(a_3742_W.NoEntityTrace);
        this.R_4764_Y(a_3742_W.NoFriendDamage);
        this.R_4764_Y(a_3742_W.NoServerDesync);
        this.R_4764_Y(a_3742_W.r_4217_P);
        this.R_4764_Y(a_3742_W.Velocity);
        this.R_4764_Y(a_3742_W.PacketCriticals);
        this.R_4764_Y(a_3742_W.Surround);
        this.R_4764_Y(a_3742_W.TargetPearl);
        this.R_4764_Y(a_3742_W.TargetStrafe);
        this.R_4764_Y(a_3742_W.TriggerBot);
        this.R_4764_Y(a_3742_W.r_4601_j);
        this.R_4764_Y(a_3742_W.O_726_g);
        this.R_4764_Y(a_3742_W.E_2115_e);
        this.R_4764_Y(a_3742_W.W_1707_M);
        this.R_4764_Y(a_3742_W.O_1795_e);
        this.R_4764_Y(a_3742_W.ServerHelper);
        this.R_4764_Y(a_3742_W.G_4691_Q);
        this.R_4764_Y(a_3742_W.d_560_A);
        this.R_4764_Y(a_3742_W.u_796_y);
        this.R_4764_Y(a_3742_W.Y_3623_f);
        this.R_4764_Y(a_3742_W.z_4066_l);
        this.R_4764_Y(a_3742_W.Y_4293_u);
        this.R_4764_Y(a_3742_W.z_283_n);
        this.R_4764_Y(a_3742_W.a_1887_j);
        this.R_4764_Y(a_3742_W.n_2412_y);
        this.R_4764_Y(a_3742_W.W_2756_H);
        this.R_4764_Y(a_3742_W.i_1894_C);
        this.R_4764_Y(a_3742_W.u_4724_w);
        this.R_4764_Y(a_3742_W.H_1952_g);
        this.R_4764_Y(a_3742_W.w_2152_d);
        this.R_4764_Y(a_3742_W.Z_1243_X);
        this.R_4764_Y(a_3742_W.r_976_u);
        this.R_4764_Y(a_3742_W.E_390_U);
        this.R_4764_Y(a_3742_W.Z_256_c);
        this.R_4764_Y(a_3742_W.N_2592_G);
        this.R_4764_Y(a_3742_W.s_1124_y);
        this.R_4764_Y(a_3742_W.C_1577_A);
        this.R_4764_Y(a_3742_W.M_2029_A);
        this.R_4764_Y(a_3742_W.q_3148_R);
        this.n_1700_B(a_3742_W.b_3528_u, a_3742_W.J_1907_R);
        this.n_1700_B(a_3742_W.I_4477_R, a_3742_W.P_4830_p);
        this.n_1700_B(a_3742_W.g_46_E, a_3742_W.f_691_R);
        this.n_1700_B(a_3742_W.Z_2812_M, a_3742_W.I_4481_g);
        this.n_1700_B(a_3742_W.J_4125_o, a_3742_W.g_1734_y);
        this.n_1700_B(a_3742_W.A_229_v, a_3742_W.I_3457_f);
        this.R_4764_Y(a_3742_W.RequirementsStrategy, a_3742_W.S_4998_h);
        this.R_4764_Y(a_3742_W.SimpleCriterionTrigger, a_3742_W.T_2391_T);
        this.n_1700_B(a_3742_W.a_178_J, n_4365_u.n_1700_B());
        this.n_1700_B(a_3742_W.LeaveTracker, n_4365_u.n_1700_B());
        this.n_1700_B(a_3742_W.j_306_t, n_4365_u.n_1700_B());
        this.n_1700_B(a_3742_W.x_612_B, n_4365_u.n_1700_B());
        this.n_1700_B(a_3742_W.t_1446_I, n_4365_u.n_1700_B());
        this.n_1700_B(a_3742_W.M_766_z, n_4365_u.n_1700_B());
        HashSet set = Sets.newHashSet();
        for (T_2915_h block : V_3137_a.q_4610_l) {
            g_2336_b resourcelocation = block.P_1922_E();
            if (resourcelocation == o_4810_o.n_1700_B || !set.add(resourcelocation)) continue;
            p_4985_U.n_1700_B loottable$builder = this.t_148_a.remove(resourcelocation);
            if (loottable$builder == null) {
                throw new IllegalStateException(String.format("Missing loottable '%s' for '%s'", resourcelocation, V_3137_a.q_4610_l.J_1907_R(block)));
            }
            p_accept_1_.accept(resourcelocation, loottable$builder);
        }
        if (!this.t_148_a.isEmpty()) {
            throw new IllegalStateException("Created block loot tables for non-blocks: " + String.valueOf(this.t_148_a.keySet()));
        }
    }

    private void R_4764_Y(T_2915_h vines, T_2915_h plant) {
        p_4985_U.n_1700_B loottable$builder = n_4365_u.R_4764_Y(vines, R_2836_Y.n_1700_B(vines).J_1907_R(E_2006_R.n_1700_B(Enchantments.C_2741_M, 0.33f, 0.55f, 0.77f, 1.0f)));
        this.n_1700_B(vines, loottable$builder);
        this.n_1700_B(plant, loottable$builder);
    }

    public static p_4985_U.n_1700_B n_1700_B(T_2915_h door) {
        return n_4365_u.n_1700_B(door, S_1431_H.t_1786_h, g_3212_H.J_1907_R);
    }

    public void J_1907_R(T_2915_h flowerPot) {
        this.n_1700_B(flowerPot, (T_2915_h pot) -> n_4365_u.R_4764_Y(((h_935_G)pot).J_1907_R()));
    }

    public void n_1700_B(T_2915_h blockIn, T_2915_h silkTouchDrop) {
        this.n_1700_B(blockIn, n_4365_u.J_1907_R(silkTouchDrop));
    }

    public void n_1700_B(T_2915_h blockIn, q_1803_e drop) {
        this.n_1700_B(blockIn, n_4365_u.n_1700_B(drop));
    }

    public void R_4764_Y(T_2915_h blockIn) {
        this.n_1700_B(blockIn, blockIn);
    }

    public void G_564_y(T_2915_h block) {
        this.n_1700_B(block, (q_1803_e)block);
    }

    private void n_1700_B(T_2915_h blockIn, Function<T_2915_h, p_4985_U.n_1700_B> factory) {
        this.n_1700_B(blockIn, factory.apply(blockIn));
    }

    private void n_1700_B(T_2915_h blockIn, p_4985_U.n_1700_B table) {
        this.t_148_a.put(blockIn.P_1922_E(), table);
    }

    @Override
    public /* synthetic */ void accept(Object object) {
        this.n_1700_B((BiConsumer)object);
    }
}



