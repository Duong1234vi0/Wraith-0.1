/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import lightning.product.B_368_w;
import lightning.product.E_3705_H;
import lightning.product.LootItemCondition;
import lightning.product.R_2836_Y;
import lightning.product.S_2110_L;
import lightning.product.biomeBiomes;
import lightning.product.U_2912_j;
import lightning.product.W_2672_e;
import lightning.product.a_3742_W;
import lightning.product.b_1430_k;
import lightning.product.b_203_K;
import lightning.product.c_2687_J;
import lightning.product.d_1384_D;
import lightning.product.d_4035_P;
import lightning.product.e_2748_L;
import lightning.product.g_2336_b;
import lightning.product.i_4685_W;
import lightning.product.j_3341_s;
import lightning.product.n_2967_p;
import lightning.product.o_3393_s;
import lightning.product.o_4810_o;
import lightning.product.p_4985_U;
import lightning.product.q_1704_m;
import lightning.product.Items;
import lightning.product.FishingHookPredicate;
import lightning.product.u_1373_N;

public class FishingLoot
implements Consumer<BiConsumer<g_2336_b, p_4985_U.n_1700_B>> {
    public static final LootItemCondition.n_1700_B n_1700_B = d_1384_D.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(biomeBiomes.Q_2552_b));
    public static final LootItemCondition.n_1700_B J_1907_R = d_1384_D.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(biomeBiomes.C_2741_M));
    public static final LootItemCondition.n_1700_B R_4764_Y = d_1384_D.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(biomeBiomes.k_2293_S));
    public static final LootItemCondition.n_1700_B G_564_y = d_1384_D.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(biomeBiomes.V_1446_Y));
    public static final LootItemCondition.n_1700_B P_1922_E = d_1384_D.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(biomeBiomes.A_1038_p));
    public static final LootItemCondition.n_1700_B u_1723_Y = d_1384_D.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(biomeBiomes.i_1637_u));
    public static final LootItemCondition.n_1700_B v_4262_N = d_1384_D.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(biomeBiomes.PlayerInfo));

    public void n_1700_B(BiConsumer<g_2336_b, p_4985_U.n_1700_B> p_accept_1_) {
        p_accept_1_.accept(o_4810_o.r_715_M, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(S_2110_L.n_1700_B(1)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)c_2687_J.n_1700_B(o_4810_o.A_1038_p).n_1700_B(10)).J_1907_R(-2)).n_1700_B((u_1373_N.n_1700_B<?>)((u_1373_N.n_1700_B)((e_2748_L.n_1700_B)c_2687_J.n_1700_B(o_4810_o.i_1637_u).n_1700_B(5)).J_1907_R(2)).J_1907_R(W_2672_e.n_1700_B(q_1704_m.J_1907_R.n_1700_B, b_1430_k.J_1907_R.n_1700_B().n_1700_B(FishingHookPredicate.n_1700_B(true))))).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)c_2687_J.n_1700_B(o_4810_o.Ping).n_1700_B(85)).J_1907_R(-1))));
        p_accept_1_.accept(o_4810_o.Ping, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.ServerAdvancementManager).n_1700_B(60)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.C_3304_p).n_1700_B(25)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.J_1008_m).n_1700_B(2)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.U_1258_d).n_1700_B(13))));
        p_accept_1_.accept(o_4810_o.A_1038_p, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(a_3742_W.S_4035_N).n_1700_B(17)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.a_1344_X).n_1700_B(10)).J_1907_R(b_203_K.n_1700_B(o_3393_s.n_1700_B(0.0f, 0.9f)))).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.y_254_d).n_1700_B(10)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.DamageSourcePredicate).n_1700_B(10)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.j_2461_G).n_1700_B(10)).J_1907_R(i_4685_W.n_1700_B(j_3341_s.n_1700_B(new U_2912_j(), nbt -> nbt.n_1700_B("Potion", "minecraft:water"))))).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.Animation).n_1700_B(5)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.w_2223_C).n_1700_B(2)).J_1907_R(b_203_K.n_1700_B(o_3393_s.n_1700_B(0.0f, 0.9f)))).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.S_4088_D).n_1700_B(10)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.A_4514_U).n_1700_B(5)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.B_3068_A).n_1700_B(1)).J_1907_R(E_3705_H.n_1700_B(S_2110_L.n_1700_B(10)))).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(a_3742_W.d_2169_p).n_1700_B(10)).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.m_1964_F).n_1700_B(10)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(a_3742_W.t_1509_b).J_1907_R(n_1700_B.n_1700_B(J_1907_R).n_1700_B(R_4764_Y).n_1700_B(G_564_y).n_1700_B(P_1922_E).n_1700_B(u_1723_Y).n_1700_B(v_4262_N))).n_1700_B(10))));
        p_accept_1_.accept(o_4810_o.i_1637_u, p_4985_U.J_1907_R().n_1700_B(n_2967_p.n_1700_B().n_1700_B(R_2836_Y.n_1700_B(Items.HorizontalDirectionalBlock)).n_1700_B(R_2836_Y.n_1700_B(Items.Z_361_l)).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.R_1796_s).J_1907_R(b_203_K.n_1700_B(o_3393_s.n_1700_B(0.0f, 0.25f)))).J_1907_R(d_4035_P.n_1700_B(S_2110_L.n_1700_B(30)).v_4262_N())).n_1700_B((u_1373_N.n_1700_B<?>)((e_2748_L.n_1700_B)R_2836_Y.n_1700_B(Items.w_2223_C).J_1907_R(b_203_K.n_1700_B(o_3393_s.n_1700_B(0.0f, 0.25f)))).J_1907_R(d_4035_P.n_1700_B(S_2110_L.n_1700_B(30)).v_4262_N())).n_1700_B((u_1373_N.n_1700_B<?>)R_2836_Y.n_1700_B(Items.K_4237_u).J_1907_R(d_4035_P.n_1700_B(S_2110_L.n_1700_B(30)).v_4262_N())).n_1700_B(R_2836_Y.n_1700_B(Items.SandBlock))));
    }

    @Override
    public /* synthetic */ void accept(Object object) {
        this.n_1700_B((BiConsumer)object);
    }
}


