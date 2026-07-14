/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Consumer;
import lightning.product.A_1604_A;
import lightning.product.A_2629_w;
import lightning.product.B_368_w;
import lightning.product.C_3528_u;
import lightning.product.D_4237_z;
import lightning.product.E_4068_x;
import lightning.product.F_2904_S;
import lightning.product.J_1008_m;
import lightning.product.K_550_M;
import lightning.product.P_2068_y;
import lightning.product.T_2391_T;
import lightning.product.V_3137_a;
import lightning.product.W_4813_f;
import lightning.product.Z_2021_u;
import lightning.product.a_3742_W;
import lightning.product.b_1430_k;
import lightning.product.b_4067_I;
import lightning.product.Enchantments;
import lightning.product.d_3769_f;
import lightning.product.g_1096_r;
import lightning.product.g_2336_b;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.RequirementsStrategy;
import lightning.product.MinMaxBounds;
import lightning.product.t_5_h;
import lightning.product.w_2892_f;
import lightning.product.w_4866_k;
import lightning.product.x_282_a;

public class t_1512_m
implements Consumer<Consumer<A_2629_w>> {
    private static final t_5_h<?>[] n_1700_B = new t_5_h[]{t_5_h.n_3318_d, t_5_h.Q_4569_t, t_5_h.T_3594_S, t_5_h.k_3961_g, t_5_h.M_588_G, t_5_h.D_4792_h, t_5_h.A_1038_p, t_5_h.s_956_w, t_5_h.j_2266_I, t_5_h.s_2632_s, t_5_h.UploadStatus, t_5_h.g_221_o, t_5_h.w_1484_f, t_5_h.z_1333_t, t_5_h.A_4115_X, t_5_h.P_1922_E, t_5_h.e_4240_b, t_5_h.RealmsWorldOptions};
    private static final q_1613_l[] J_1907_R = new q_1613_l[]{Items.ServerAdvancementManager, Items.J_1008_m, Items.U_1258_d, Items.C_3304_p};
    private static final q_1613_l[] R_4764_Y = new q_1613_l[]{Items.W_3801_h, Items.v_2826_q, Items.F_2052_z, Items.j_1376_w};
    private static final q_1613_l[] G_564_y = new q_1613_l[]{Items.E_738_L, Items.MinecraftAccess, Items.m_3828_C, Items.j_1654_T, Items.l_3729_r, Items.p_863_D, Items.E_4612_l, Items.ServerAdvancementManager, Items.C_3304_p, Items.J_1008_m, Items.U_1258_d, Items.U_3554_Q, Items.T_4001_f, Items.B_1335_M, Items.B_368_w, Items.h_2396_v, Items.x_2711_Y, Items.w_2892_f, Items.m_3052_r, Items.m_1964_F, Items.r_2687_x, Items.BaseCoralWallFanBlock, Items.l_683_e, Items.DirectionalBlock, Items.S_3458_C, Items.DoublePlantBlock, Items.y_2012_u, Items.FungusBlock, Items.A_3138_X, Items.N_3347_G, Items.s_3698_N, Items.o_869_X, Items.MagmaBlock, Items.s_3401_U, Items.MyceliumBlock, Items.MinMaxBounds, Items.Q_2342_H, Items.D_265_n, Items.StructureBlock};

    public void n_1700_B(Consumer<A_2629_w> p_accept_1_) {
        A_2629_w advancement = A_2629_w.n_1700_B.n_1700_B().n_1700_B(a_3742_W.M_4609_z, (x_282_a)new F_2904_S("advancements.husbandry.root.title"), (x_282_a)new F_2904_S("advancements.husbandry.root.description"), new g_2336_b("textures/gui/advancements/backgrounds/husbandry.png"), W_4813_f.n_1700_B, false, false, false).n_1700_B("consumed_item", Z_2021_u.n_1700_B.J_1907_R()).n_1700_B(p_accept_1_, "husbandry/root");
        A_2629_w advancement1 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.V_3441_j, (x_282_a)new F_2904_S("advancements.husbandry.plant_seed.title"), (x_282_a)new F_2904_S("advancements.husbandry.plant_seed.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B("wheat", w_2892_f.n_1700_B.n_1700_B(a_3742_W.l_4088_R)).n_1700_B("pumpkin_stem", w_2892_f.n_1700_B.n_1700_B(a_3742_W.L_1733_J)).n_1700_B("melon_stem", w_2892_f.n_1700_B.n_1700_B(a_3742_W.n_4539_g)).n_1700_B("beetroots", w_2892_f.n_1700_B.n_1700_B(a_3742_W.FreeCam)).n_1700_B("nether_wart", w_2892_f.n_1700_B.n_1700_B(a_3742_W.W_3729_Q)).n_1700_B(p_accept_1_, "husbandry/plant_seed");
        A_2629_w advancement2 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.V_3441_j, (x_282_a)new F_2904_S("advancements.husbandry.breed_an_animal.title"), (x_282_a)new F_2904_S("advancements.husbandry.breed_an_animal.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B("bred", d_3769_f.n_1700_B.J_1907_R()).n_1700_B(p_accept_1_, "husbandry/breed_an_animal");
        this.n_1700_B(A_2629_w.n_1700_B.n_1700_B()).n_1700_B(advancement1).n_1700_B(Items.E_738_L, (x_282_a)new F_2904_S("advancements.husbandry.balanced_diet.title"), (x_282_a)new F_2904_S("advancements.husbandry.balanced_diet.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(100)).n_1700_B(p_accept_1_, "husbandry/balanced_diet");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement1).n_1700_B(Items.D_940_S, (x_282_a)new F_2904_S("advancements.husbandry.netherite_hoe.title"), (x_282_a)new F_2904_S("advancements.husbandry.netherite_hoe.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(100)).n_1700_B("netherite_hoe", P_2068_y.n_1700_B.n_1700_B(Items.D_940_S)).n_1700_B(p_accept_1_, "husbandry/obtain_netherite_hoe");
        A_2629_w advancement3 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.c_1788_D, (x_282_a)new F_2904_S("advancements.husbandry.tame_an_animal.title"), (x_282_a)new F_2904_S("advancements.husbandry.tame_an_animal.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("tamed_animal", C_3528_u.n_1700_B.J_1907_R()).n_1700_B(p_accept_1_, "husbandry/tame_an_animal");
        this.J_1907_R(A_2629_w.n_1700_B.n_1700_B()).n_1700_B(advancement2).n_1700_B(Items.DoublePlantBlock, (x_282_a)new F_2904_S("advancements.husbandry.breed_all_animals.title"), (x_282_a)new F_2904_S("advancements.husbandry.breed_all_animals.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(100)).n_1700_B(p_accept_1_, "husbandry/bred_all_animals");
        A_2629_w advancement4 = this.G_564_y(A_2629_w.n_1700_B.n_1700_B()).n_1700_B(advancement).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B(Items.w_2223_C, (x_282_a)new F_2904_S("advancements.husbandry.fishy_business.title"), (x_282_a)new F_2904_S("advancements.husbandry.fishy_business.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(p_accept_1_, "husbandry/fishy_business");
        this.R_4764_Y(A_2629_w.n_1700_B.n_1700_B()).n_1700_B(advancement4).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B(Items.F_2052_z, (x_282_a)new F_2904_S("advancements.husbandry.tactical_fishing.title"), (x_282_a)new F_2904_S("advancements.husbandry.tactical_fishing.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(p_accept_1_, "husbandry/tactical_fishing");
        this.P_1922_E(A_2629_w.n_1700_B.n_1700_B()).n_1700_B(advancement3).n_1700_B(Items.ServerAdvancementManager, (x_282_a)new F_2904_S("advancements.husbandry.complete_catalogue.title"), (x_282_a)new F_2904_S("advancements.husbandry.complete_catalogue.description"), (g_2336_b)null, W_4813_f.J_1907_R, true, true, false).n_1700_B(J_1008_m.n_1700_B.n_1700_B(50)).n_1700_B(p_accept_1_, "husbandry/complete_catalogue");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B("safely_harvest_honey", g_1096_r.n_1700_B.n_1700_B(B_368_w.n_1700_B.n_1700_B().n_1700_B(D_4237_z.n_1700_B.n_1700_B().n_1700_B(BlockTags.Ping).J_1907_R()).n_1700_B(true), w_4866_k.n_1700_B.n_1700_B().n_1700_B(Items.Y_3588_g))).n_1700_B(Items.StructureBlock, (x_282_a)new F_2904_S("advancements.husbandry.safely_harvest_honey.title"), (x_282_a)new F_2904_S("advancements.husbandry.safely_harvest_honey.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(p_accept_1_, "husbandry/safely_harvest_honey");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B("silk_touch_nest", T_2391_T.n_1700_B.n_1700_B(a_3742_W.w_4866_k, w_4866_k.n_1700_B.n_1700_B().n_1700_B(new A_1604_A(Enchantments.Y_259_p, MinMaxBounds.G_564_y.J_1907_R(1))), MinMaxBounds.G_564_y.n_1700_B(3))).n_1700_B(a_3742_W.w_4866_k, (x_282_a)new F_2904_S("advancements.husbandry.silk_touch_nest.title"), (x_282_a)new F_2904_S("advancements.husbandry.silk_touch_nest.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(p_accept_1_, "husbandry/silk_touch_nest");
    }

    private A_2629_w.n_1700_B n_1700_B(A_2629_w.n_1700_B builder) {
        for (q_1613_l item : G_564_y) {
            builder.n_1700_B(V_3137_a.e_2887_G.J_1907_R(item).J_1907_R(), Z_2021_u.n_1700_B.n_1700_B(item));
        }
        return builder;
    }

    private A_2629_w.n_1700_B J_1907_R(A_2629_w.n_1700_B builder) {
        for (t_5_h<?> entitytype : n_1700_B) {
            builder.n_1700_B(t_5_h.n_1700_B(entitytype).toString(), d_3769_f.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(entitytype)));
        }
        builder.n_1700_B(t_5_h.n_1700_B(t_5_h.l_4537_E).toString(), d_3769_f.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.l_4537_E).J_1907_R(), b_1430_k.J_1907_R.n_1700_B().n_1700_B(t_5_h.l_4537_E).J_1907_R(), b_1430_k.n_1700_B));
        return builder;
    }

    private A_2629_w.n_1700_B R_4764_Y(A_2629_w.n_1700_B builder) {
        for (q_1613_l item : R_4764_Y) {
            builder.n_1700_B(V_3137_a.e_2887_G.J_1907_R(item).J_1907_R(), b_4067_I.n_1700_B.n_1700_B(w_4866_k.n_1700_B.n_1700_B().n_1700_B(item).J_1907_R()));
        }
        return builder;
    }

    private A_2629_w.n_1700_B G_564_y(A_2629_w.n_1700_B builder) {
        for (q_1613_l item : J_1907_R) {
            builder.n_1700_B(V_3137_a.e_2887_G.J_1907_R(item).J_1907_R(), E_4068_x.n_1700_B.n_1700_B(w_4866_k.n_1700_B, b_1430_k.n_1700_B, w_4866_k.n_1700_B.n_1700_B().n_1700_B(item).J_1907_R()));
        }
        return builder;
    }

    private A_2629_w.n_1700_B P_1922_E(A_2629_w.n_1700_B builder) {
        K_550_M.h_1847_R.forEach((id, texture) -> builder.n_1700_B(texture.J_1907_R(), C_3528_u.n_1700_B.n_1700_B(b_1430_k.J_1907_R.n_1700_B().n_1700_B((g_2336_b)texture).J_1907_R())));
        return builder;
    }

    @Override
    public /* synthetic */ void accept(Object object) {
        this.n_1700_B((Consumer)object);
    }
}



