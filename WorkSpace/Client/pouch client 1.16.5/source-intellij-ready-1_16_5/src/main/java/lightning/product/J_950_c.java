/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Consumer;
import lightning.product.A_2629_w;
import lightning.product.B_368_w;
import lightning.product.F_2904_S;
import lightning.product.StructureFeature;
import lightning.product.K_4866_h;
import lightning.product.DamageSourcePredicate;
import lightning.product.ItemTags;
import lightning.product.P_2068_y;
import lightning.product.U_4107_W;
import lightning.product.W_4813_f;
import lightning.product.a_3742_W;
import lightning.product.b_3334_n;
import lightning.product.b_4507_u;
import lightning.product.g_2336_b;
import lightning.product.m_3168_q;
import lightning.product.Items;
import lightning.product.r_1970_q;
import lightning.product.RequirementsStrategy;
import lightning.product.w_4866_k;
import lightning.product.x_282_a;
import lightning.product.z_936_s;

public class J_950_c
implements Consumer<Consumer<A_2629_w>> {
    public void n_1700_B(Consumer<A_2629_w> p_accept_1_) {
        A_2629_w advancement = A_2629_w.n_1700_B.n_1700_B().n_1700_B(a_3742_W.t_148_a, (x_282_a)new F_2904_S("advancements.story.root.title"), (x_282_a)new F_2904_S("advancements.story.root.description"), new g_2336_b("textures/gui/advancements/backgrounds/stone.png"), W_4813_f.n_1700_B, false, false, false).n_1700_B("crafting_table", P_2068_y.n_1700_B.n_1700_B(a_3742_W.O_2934_T)).n_1700_B(p_accept_1_, "story/root");
        A_2629_w advancement1 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement).n_1700_B(Items.N_1833_W, (x_282_a)new F_2904_S("advancements.story.mine_stone.title"), (x_282_a)new F_2904_S("advancements.story.mine_stone.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("get_stone", P_2068_y.n_1700_B.n_1700_B(w_4866_k.n_1700_B.n_1700_B().n_1700_B(ItemTags.D_4792_h).J_1907_R())).n_1700_B(p_accept_1_, "story/mine_stone");
        A_2629_w advancement2 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement1).n_1700_B(Items.Y_4293_u, (x_282_a)new F_2904_S("advancements.story.upgrade_tools.title"), (x_282_a)new F_2904_S("advancements.story.upgrade_tools.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("stone_pickaxe", P_2068_y.n_1700_B.n_1700_B(Items.Y_4293_u)).n_1700_B(p_accept_1_, "story/upgrade_tools");
        A_2629_w advancement3 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement2).n_1700_B(Items.D_1621_L, (x_282_a)new F_2904_S("advancements.story.smelt_iron.title"), (x_282_a)new F_2904_S("advancements.story.smelt_iron.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("iron", P_2068_y.n_1700_B.n_1700_B(Items.D_1621_L)).n_1700_B(p_accept_1_, "story/smelt_iron");
        A_2629_w advancement4 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement3).n_1700_B(Items.r_976_u, (x_282_a)new F_2904_S("advancements.story.iron_tools.title"), (x_282_a)new F_2904_S("advancements.story.iron_tools.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("iron_pickaxe", P_2068_y.n_1700_B.n_1700_B(Items.r_976_u)).n_1700_B(p_accept_1_, "story/iron_tools");
        A_2629_w advancement5 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement4).n_1700_B(Items.k_2273_q, (x_282_a)new F_2904_S("advancements.story.mine_diamond.title"), (x_282_a)new F_2904_S("advancements.story.mine_diamond.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("diamond", P_2068_y.n_1700_B.n_1700_B(Items.k_2273_q)).n_1700_B(p_accept_1_, "story/mine_diamond");
        A_2629_w advancement6 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement3).n_1700_B(Items.u_1934_K, (x_282_a)new F_2904_S("advancements.story.lava_bucket.title"), (x_282_a)new F_2904_S("advancements.story.lava_bucket.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("lava_bucket", P_2068_y.n_1700_B.n_1700_B(Items.u_1934_K)).n_1700_B(p_accept_1_, "story/lava_bucket");
        A_2629_w advancement7 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement3).n_1700_B(Items.k_2282_P, (x_282_a)new F_2904_S("advancements.story.obtain_armor.title"), (x_282_a)new F_2904_S("advancements.story.obtain_armor.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B("iron_helmet", P_2068_y.n_1700_B.n_1700_B(Items.T_1170_t)).n_1700_B("iron_chestplate", P_2068_y.n_1700_B.n_1700_B(Items.k_2282_P)).n_1700_B("iron_leggings", P_2068_y.n_1700_B.n_1700_B(Items.U_2474_c)).n_1700_B("iron_boots", P_2068_y.n_1700_B.n_1700_B(Items.j_2302_z)).n_1700_B(p_accept_1_, "story/obtain_armor");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement5).n_1700_B(Items.M_4472_P, (x_282_a)new F_2904_S("advancements.story.enchant_item.title"), (x_282_a)new F_2904_S("advancements.story.enchant_item.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("enchanted_item", m_3168_q.n_1700_B.J_1907_R()).n_1700_B(p_accept_1_, "story/enchant_item");
        A_2629_w advancement8 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement6).n_1700_B(a_3742_W.ClientBootstrap, (x_282_a)new F_2904_S("advancements.story.form_obsidian.title"), (x_282_a)new F_2904_S("advancements.story.form_obsidian.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("obsidian", P_2068_y.n_1700_B.n_1700_B(a_3742_W.ClientBootstrap)).n_1700_B(p_accept_1_, "story/form_obsidian");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement7).n_1700_B(Items.NoteBlock, (x_282_a)new F_2904_S("advancements.story.deflect_arrow.title"), (x_282_a)new F_2904_S("advancements.story.deflect_arrow.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("deflected_projectile", U_4107_W.n_1700_B.n_1700_B(r_1970_q.n_1700_B.n_1700_B().n_1700_B(DamageSourcePredicate.n_1700_B.n_1700_B().n_1700_B(true)).n_1700_B(true))).n_1700_B(p_accept_1_, "story/deflect_arrow");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement5).n_1700_B(Items.f_508_U, (x_282_a)new F_2904_S("advancements.story.shiny_gear.title"), (x_282_a)new F_2904_S("advancements.story.shiny_gear.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B(RequirementsStrategy.J_1907_R).n_1700_B("diamond_helmet", P_2068_y.n_1700_B.n_1700_B(Items.q_4361_M)).n_1700_B("diamond_chestplate", P_2068_y.n_1700_B.n_1700_B(Items.f_508_U)).n_1700_B("diamond_leggings", P_2068_y.n_1700_B.n_1700_B(Items.A_1603_w)).n_1700_B("diamond_boots", P_2068_y.n_1700_B.n_1700_B(Items.V_4557_X)).n_1700_B(p_accept_1_, "story/shiny_gear");
        A_2629_w advancement9 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement8).n_1700_B(Items.S_1165_y, (x_282_a)new F_2904_S("advancements.story.enter_the_nether.title"), (x_282_a)new F_2904_S("advancements.story.enter_the_nether.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("entered_nether", z_936_s.n_1700_B.n_1700_B(b_4507_u.v_4262_N)).n_1700_B(p_accept_1_, "story/enter_the_nether");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement9).n_1700_B(Items.p_863_D, (x_282_a)new F_2904_S("advancements.story.cure_zombie_villager.title"), (x_282_a)new F_2904_S("advancements.story.cure_zombie_villager.description"), (g_2336_b)null, W_4813_f.R_4764_Y, true, true, false).n_1700_B("cured_zombie", K_4866_h.n_1700_B.J_1907_R()).n_1700_B(p_accept_1_, "story/cure_zombie_villager");
        A_2629_w advancement10 = A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement9).n_1700_B(Items.V_1824_v, (x_282_a)new F_2904_S("advancements.story.follow_ender_eye.title"), (x_282_a)new F_2904_S("advancements.story.follow_ender_eye.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("in_stronghold", b_3334_n.n_1700_B.n_1700_B(B_368_w.n_1700_B(StructureFeature.u_2550_I))).n_1700_B(p_accept_1_, "story/follow_ender_eye");
        A_2629_w.n_1700_B.n_1700_B().n_1700_B(advancement10).n_1700_B(a_3742_W.e_1231_S, (x_282_a)new F_2904_S("advancements.story.enter_the_end.title"), (x_282_a)new F_2904_S("advancements.story.enter_the_end.description"), (g_2336_b)null, W_4813_f.n_1700_B, true, true, false).n_1700_B("entered_end", z_936_s.n_1700_B.n_1700_B(b_4507_u.w_1484_f)).n_1700_B(p_accept_1_, "story/enter_the_end");
    }

    @Override
    public /* synthetic */ void accept(Object object) {
        this.n_1700_B((Consumer)object);
    }
}



