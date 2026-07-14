/*
 * Decompiled with CFR 0.152.
 */
package net.minecraft.data;

import java.nio.file.Path;
import java.util.function.Function;
import lightning.product.ItemTags;
import lightning.product.T_2915_h;
import lightning.product.V_3137_a;
import lightning.product.g_2336_b;
import lightning.product.BlockTags;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_109_r;
import net.minecraft.data.Q_4569_t;
import net.minecraft.data.u_2550_I;
import net.minecraft.data.z_4693_k;

public class Z_875_P
extends z_4693_k<q_1613_l> {
    private final Function<r_109_r.J_1907_R<T_2915_h>, r_109_r.n_1700_B> G_564_y = blockTagProvider::J_1907_R;

    public Z_875_P(Q_4569_t dataGenerator, u_2550_I blockTagProvider) {
        super(dataGenerator, V_3137_a.e_2887_G);
    }

    @Override
    protected void J_1907_R() {
        this.n_1700_B(BlockTags.J_1907_R, ItemTags.J_1907_R);
        this.n_1700_B(BlockTags.R_4764_Y, ItemTags.R_4764_Y);
        this.n_1700_B(BlockTags.G_564_y, ItemTags.G_564_y);
        this.n_1700_B(BlockTags.P_1922_E, ItemTags.P_1922_E);
        this.n_1700_B(BlockTags.u_1723_Y, ItemTags.u_1723_Y);
        this.n_1700_B(BlockTags.v_4262_N, ItemTags.v_4262_N);
        this.n_1700_B(BlockTags.w_1484_f, ItemTags.w_1484_f);
        this.n_1700_B(BlockTags.t_148_a, ItemTags.t_148_a);
        this.n_1700_B(BlockTags.s_956_w, ItemTags.s_956_w);
        this.n_1700_B(BlockTags.u_2550_I, ItemTags.u_2550_I);
        this.n_1700_B(BlockTags.P_4830_p, ItemTags.M_588_G);
        this.n_1700_B(BlockTags.M_182_A, ItemTags.h_1847_R);
        this.n_1700_B(BlockTags.t_1786_h, ItemTags.Q_4569_t);
        this.n_1700_B(BlockTags.Y_259_p, ItemTags.w_1457_N);
        this.n_1700_B(BlockTags.Y_601_j, ItemTags.multiplayerClientSuggestionProvider);
        this.n_1700_B(BlockTags.Q_2552_b, ItemTags.Y_601_j);
        this.n_1700_B(BlockTags.C_2741_M, ItemTags.Y_259_p);
        this.n_1700_B(BlockTags.q_2307_F, ItemTags.C_2741_M);
        this.n_1700_B(BlockTags.k_2293_S, ItemTags.Q_2552_b);
        this.n_1700_B(BlockTags.Z_875_P, ItemTags.k_2293_S);
        this.n_1700_B(BlockTags.c_3005_b, ItemTags.q_2307_F);
        this.n_1700_B(BlockTags.multiplayerClientSuggestionProvider, ItemTags.M_182_A);
        this.n_1700_B(BlockTags.w_1457_N, ItemTags.t_1786_h);
        this.n_1700_B(BlockTags.A_4115_X, ItemTags.c_3005_b);
        this.n_1700_B(BlockTags.t_4043_B, ItemTags.A_4115_X);
        this.n_1700_B(BlockTags.x_607_J, ItemTags.Y_1740_V);
        this.n_1700_B(BlockTags.Y_1740_V, ItemTags.H_2857_Y);
        this.n_1700_B(BlockTags.e_4240_b, ItemTags.t_4043_B);
        this.n_1700_B(BlockTags.n_3318_d, ItemTags.x_607_J);
        this.n_1700_B(BlockTags.d_2427_y, ItemTags.e_4240_b);
        this.n_1700_B(BlockTags.Q_4569_t, ItemTags.P_4830_p);
        this.n_1700_B(BlockTags.z_1737_N, ItemTags.n_3318_d);
        this.n_1700_B(BlockTags.v_4276_D, ItemTags.d_2427_y);
        this.n_1700_B(BlockTags.d_2461_k, ItemTags.z_1737_N);
        this.n_1700_B(BlockTags.G_624_v, ItemTags.v_4276_D);
        this.n_1700_B(BlockTags.T_2506_i, ItemTags.d_2461_k);
        this.n_1700_B(BlockTags.q_4610_l, ItemTags.G_624_v);
        this.n_1700_B(BlockTags.g_221_o, ItemTags.z_4693_k);
        this.n_1700_B(BlockTags.PlayerInfo, ItemTags.e_2887_G);
        this.n_1700_B(ItemTags.Z_875_P).n_1700_B((q_1613_l[])new q_1613_l[]{Items.o_3946_o, Items.BonemealableBlock, Items.LiquidBlockContainer, Items.k_2789_z, Items.SimpleWaterloggedBlock, Items.IceBlock, Items.JigsawBlock, Items.JukeboxBlock, Items.KelpPlantBlock, Items.V_1395_p, Items.C_1985_D, Items.R_2215_C, Items.LeavesBlock, Items.h_355_y, Items.K_3256_W, Items.WaterlilyBlock});
        this.n_1700_B(ItemTags.B_1668_F).n_1700_B((q_1613_l[])new q_1613_l[]{Items.m_1628_s, Items.ObserverBlock, Items.OreBlock, Items.IronBarsBlock, Items.h_4152_b, Items.M_1398_d});
        this.n_1700_B(ItemTags.g_164_R).n_1700_B((q_1613_l[])new q_1613_l[]{Items.ServerAdvancementManager, Items.U_3554_Q, Items.C_3304_p, Items.T_4001_f, Items.U_1258_d, Items.J_1008_m});
        this.n_1700_B(BlockTags.l_1233_K, ItemTags.X_933_l);
        this.n_1700_B(ItemTags.H_1990_U).n_1700_B((q_1613_l[])new q_1613_l[]{Items.v_448_E, Items.L_2801_l, Items.PumpkinBlock, Items.w_4059_h, Items.U_2334_m, Items.PoweredBlock, Items.u_782_h, Items.RedstoneLampBlock, Items.z_3617_Q, Items.RedstoneTorchBlock, Items.b_1707_w, Items.Z_4734_t});
        this.n_1700_B(ItemTags.Z_976_R).n_1700_B(ItemTags.H_1990_U).n_1700_B(Items.RepeaterBlock);
        this.n_1700_B(ItemTags.N_2525_X).n_1700_B((q_1613_l[])new q_1613_l[]{Items.T_797_O, Items.d_560_A});
        this.n_1700_B(ItemTags.c_4037_x).n_1700_B((q_1613_l[])new q_1613_l[]{Items.g_24_p, Items.NetherWartBlock, Items.g_2783_J});
        this.n_1700_B(ItemTags.g_2268_R).n_1700_B((q_1613_l[])new q_1613_l[]{Items.CryingObsidianBlock, Items.CropBlock});
        this.n_1700_B(ItemTags.T_3594_S).n_1700_B((q_1613_l[])new q_1613_l[]{Items.q_4124_m, Items.Y_2905_A, Items.k_2273_q, Items.ServerHandshakePacketListener, Items.D_1621_L});
        this.n_1700_B(ItemTags.T_2506_i).n_1700_B(Items.r_260_T).n_1700_B(Items.F_4312_i).n_1700_B(Items.StandingSignBlock);
        this.n_1700_B(ItemTags.q_4610_l).n_1700_B(ItemTags.z_4693_k).n_1700_B((q_1613_l[])new q_1613_l[]{Items.d_4007_L, Items.TrappedChestBlock, Items.Module, Items.ServerHandshakePacketListener, Items.SpongeBlock, Items.A_2629_w, Items.DoublePlantBlock, Items.e_1503_j, Items.p_863_D, Items.E_4612_l, Items.h_3066_J, Items.m_38_G, Items.k_4946_A, Items.m_4644_u, Items.GrindstoneBlock, Items.n_2412_y, Items.i_1894_C, Items.W_2756_H, Items.u_4724_w, Items.H_1952_g});
        this.n_1700_B(ItemTags.g_221_o).n_1700_B((q_1613_l[])new q_1613_l[]{Items.B_1668_F, Items.T_3594_S, Items.D_60_a, Items.i_1637_u, Items.e_2887_G, Items.g_2268_R, Items.e_1992_r, Items.A_1038_p, Items.Q_2552_b, Items.C_2741_M, Items.t_1446_I, Items.j_306_t, Items.b_2312_j, Items.I_4348_c, Items.m_2262_U, Items.S_4258_d, Items.b_3528_u, Items.I_4477_R, Items.W_3729_Q, Items.E_453_w, Items.f_2403_E, Items.c_776_E, Items.U_3758_B, Items.y_3417_N, Items.a_794_m, Items.E_170_p, Items.H_3529_d, Items.U_3005_m});
        this.n_1700_B(ItemTags.D_4792_h).n_1700_B((q_1613_l[])new q_1613_l[]{Items.Q_4569_t, Items.TargetBlock});
        this.n_1700_B(ItemTags.s_2632_s).n_1700_B((q_1613_l[])new q_1613_l[]{Items.Q_4569_t, Items.TargetBlock});
    }

    protected void n_1700_B(r_109_r.J_1907_R<T_2915_h> blockTag, r_109_r.J_1907_R<q_1613_l> itemTag) {
        r_109_r.n_1700_B itag$builder = this.J_1907_R(itemTag);
        r_109_r.n_1700_B itag$builder1 = this.G_564_y.apply(blockTag);
        itag$builder1.J_1907_R().forEach(itag$builder::n_1700_B);
    }

    @Override
    protected Path n_1700_B(g_2336_b id) {
        return this.J_1907_R.J_1907_R().resolve("data/" + id.R_4764_Y() + "/tags/items/" + id.J_1907_R() + ".json");
    }

    @Override
    public String n_1700_B() {
        return "Item Tags";
    }
}



