/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 */
package net.minecraft.data;

import com.google.gson.JsonElement;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import lightning.product.g_2336_b;
import lightning.product.q_1613_l;
import lightning.product.Items;
import net.minecraft.data.A_4115_X;
import net.minecraft.data.G_624_v;
import net.minecraft.data.H_2857_Y;
import net.minecraft.data.Y_1740_V;

public class q_2307_F {
    private final BiConsumer<g_2336_b, Supplier<JsonElement>> n_1700_B;

    public q_2307_F(BiConsumer<g_2336_b, Supplier<JsonElement>> p_i232519_1_) {
        this.n_1700_B = p_i232519_1_;
    }

    private void n_1700_B(q_1613_l p_240076_1_, Y_1740_V p_240076_2_) {
        p_240076_2_.n_1700_B(A_4115_X.n_1700_B(p_240076_1_), H_2857_Y.J_1907_R(p_240076_1_), this.n_1700_B);
    }

    private void n_1700_B(q_1613_l p_240077_1_, String p_240077_2_, Y_1740_V p_240077_3_) {
        p_240077_3_.n_1700_B(A_4115_X.n_1700_B(p_240077_1_, p_240077_2_), H_2857_Y.t_148_a(H_2857_Y.n_1700_B(p_240077_1_, p_240077_2_)), this.n_1700_B);
    }

    private void n_1700_B(q_1613_l p_240075_1_, q_1613_l p_240075_2_, Y_1740_V p_240075_3_) {
        p_240075_3_.n_1700_B(A_4115_X.n_1700_B(p_240075_1_), H_2857_Y.J_1907_R(p_240075_2_), this.n_1700_B);
    }

    public void n_1700_B() {
        this.n_1700_B(Items.h_4152_b, G_624_v.ValueObject);
        this.n_1700_B(Items.E_738_L, G_624_v.ValueObject);
        this.n_1700_B(Items.p_1168_n, G_624_v.ValueObject);
        this.n_1700_B(Items.g_24_p, G_624_v.ValueObject);
        this.n_1700_B(Items.DirectionalBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.H_1883_T, G_624_v.F_1410_V);
        this.n_1700_B(Items.h_2396_v, G_624_v.ValueObject);
        this.n_1700_B(Items.s_3401_U, G_624_v.ValueObject);
        this.n_1700_B(Items.MyceliumBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.OreBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.K_4866_h, G_624_v.ValueObject);
        this.n_1700_B(Items.C_3528_u, G_624_v.ValueObject);
        this.n_1700_B(Items.A_2487_t, G_624_v.F_1410_V);
        this.n_1700_B(Items.I_4421_I, G_624_v.ValueObject);
        this.n_1700_B(Items.r_1970_q, G_624_v.ValueObject);
        this.n_1700_B(Items.K_4237_u, G_624_v.ValueObject);
        this.n_1700_B(Items.S_4088_D, G_624_v.ValueObject);
        this.n_1700_B(Items.m_3828_C, G_624_v.ValueObject);
        this.n_1700_B(Items.F_489_x, G_624_v.ValueObject);
        this.n_1700_B(Items.q_608_V, G_624_v.ValueObject);
        this.n_1700_B(Items.G_1539_D, G_624_v.ValueObject);
        this.n_1700_B(Items.EndRodBlock, G_624_v.S_4022_R);
        this.n_1700_B(Items.j_3599_p, G_624_v.S_4022_R);
        this.n_1700_B(Items.F_747_P, G_624_v.ValueObject);
        this.n_1700_B(Items.f_800_j, G_624_v.ValueObject);
        this.n_1700_B(Items.S_4325_V, G_624_v.ValueObject);
        this.n_1700_B(Items.R_2329_T, G_624_v.ValueObject);
        this.n_1700_B(Items.d_560_A, G_624_v.ValueObject);
        this.n_1700_B(Items.k_1366_K, G_624_v.ValueObject);
        this.n_1700_B(Items.w_2892_f, G_624_v.ValueObject);
        this.n_1700_B(Items.MagmaBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.i_4833_u, G_624_v.ValueObject);
        for (int i = 1; i < 64; ++i) {
            this.n_1700_B(Items.A_2629_w, String.format("_%02d", i), G_624_v.ValueObject);
        }
        this.n_1700_B(Items.T_797_O, G_624_v.ValueObject);
        this.n_1700_B(Items.W_3801_h, G_624_v.ValueObject);
        this.n_1700_B(Items.FaceAttachedHorizontalDirectionalBlock, G_624_v.ValueObject);
        for (int j = 0; j < 32; ++j) {
            if (j == 16) continue;
            this.n_1700_B(Items.X_1303_p, String.format("_%02d", j), G_624_v.ValueObject);
        }
        this.n_1700_B(Items.x_2711_Y, G_624_v.ValueObject);
        this.n_1700_B(Items.m_3052_r, G_624_v.ValueObject);
        this.n_1700_B(Items.U_3554_Q, G_624_v.ValueObject);
        this.n_1700_B(Items.o_869_X, G_624_v.ValueObject);
        this.n_1700_B(Items.l_3729_r, G_624_v.ValueObject);
        this.n_1700_B(Items.A_3138_X, G_624_v.ValueObject);
        this.n_1700_B(Items.T_4001_f, G_624_v.ValueObject);
        this.n_1700_B(Items.B_1335_M, G_624_v.ValueObject);
        this.n_1700_B(Items.InfestedBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.n_4560_z, G_624_v.ValueObject);
        this.n_1700_B(Items.M_1398_d, G_624_v.ValueObject);
        this.n_1700_B(Items.k_2273_q, G_624_v.ValueObject);
        this.n_1700_B(Items.M_2029_A, G_624_v.F_1410_V);
        this.n_1700_B(Items.V_4557_X, G_624_v.ValueObject);
        this.n_1700_B(Items.f_508_U, G_624_v.ValueObject);
        this.n_1700_B(Items.q_4361_M, G_624_v.ValueObject);
        this.n_1700_B(Items.q_3148_R, G_624_v.F_1410_V);
        this.n_1700_B(Items.HayBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.A_1603_w, G_624_v.ValueObject);
        this.n_1700_B(Items.C_1577_A, G_624_v.F_1410_V);
        this.n_1700_B(Items.s_1124_y, G_624_v.F_1410_V);
        this.n_1700_B(Items.N_2592_G, G_624_v.F_1410_V);
        this.n_1700_B(Items.O_3671_t, G_624_v.ValueObject);
        this.n_1700_B(Items.MinMaxBounds, G_624_v.ValueObject);
        this.n_1700_B(Items.s_4405_m, G_624_v.ValueObject);
        this.n_1700_B(Items.Y_2905_A, G_624_v.ValueObject);
        this.n_1700_B(Items.M_4472_P, G_624_v.ValueObject);
        this.n_1700_B(Items.V_1824_v, G_624_v.ValueObject);
        this.n_1700_B(Items.v_2746_S, G_624_v.ValueObject);
        this.n_1700_B(Items.LoomBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.s_3084_y, G_624_v.ValueObject);
        this.n_1700_B(Items.Y_3066_B, G_624_v.ValueObject);
        this.n_1700_B(Items.FenceBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.CraftingTableBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.W_1488_x, G_624_v.ValueObject);
        this.n_1700_B(Items.S_1165_y, G_624_v.ValueObject);
        this.n_1700_B(Items.Y_3462_U, G_624_v.ValueObject);
        this.n_1700_B(Items.y_4842_Z, G_624_v.ValueObject);
        this.n_1700_B(Items.b_3334_n, G_624_v.ValueObject);
        this.n_1700_B(Items.Y_3588_g, G_624_v.ValueObject);
        this.n_1700_B(Items.e_1503_j, G_624_v.ValueObject);
        this.n_1700_B(Items.PlayerHeadBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.AdvancementList, G_624_v.ValueObject);
        this.n_1700_B(Items.p_863_D, G_624_v.ValueObject);
        this.n_1700_B(Items.u_4724_w, G_624_v.F_1410_V);
        this.n_1700_B(Items.m_4644_u, G_624_v.ValueObject);
        this.n_1700_B(Items.DoublePlantBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.m_38_G, G_624_v.ValueObject);
        this.n_1700_B(Items.h_3066_J, G_624_v.ValueObject);
        this.n_1700_B(Items.H_1952_g, G_624_v.F_1410_V);
        this.n_1700_B(Items.GrindstoneBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.k_4946_A, G_624_v.ValueObject);
        this.n_1700_B(Items.i_1894_C, G_624_v.F_1410_V);
        this.n_1700_B(Items.W_2756_H, G_624_v.F_1410_V);
        this.n_1700_B(Items.n_2412_y, G_624_v.F_1410_V);
        this.n_1700_B(Items.ServerHandshakePacketListener, G_624_v.ValueObject);
        this.n_1700_B(Items.u_3578_p, G_624_v.ValueObject);
        this.n_1700_B(Items.D_4237_z, G_624_v.ValueObject);
        this.n_1700_B(Items.Z_2021_u, G_624_v.ValueObject);
        this.n_1700_B(Items.Easing, G_624_v.ValueObject);
        this.n_1700_B(Items.SaplingBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.StemGrownBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.StructureBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.s_3834_w, G_624_v.ValueObject);
        this.n_1700_B(Items.B_3068_A, G_624_v.ValueObject);
        this.n_1700_B(Items.E_390_U, G_624_v.F_1410_V);
        this.n_1700_B(Items.j_2302_z, G_624_v.ValueObject);
        this.n_1700_B(Items.k_2282_P, G_624_v.ValueObject);
        this.n_1700_B(Items.T_1170_t, G_624_v.ValueObject);
        this.n_1700_B(Items.Z_256_c, G_624_v.F_1410_V);
        this.n_1700_B(Items.GravelBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.D_1621_L, G_624_v.ValueObject);
        this.n_1700_B(Items.U_2474_c, G_624_v.ValueObject);
        this.n_1700_B(Items.f_1186_l, G_624_v.ValueObject);
        this.n_1700_B(Items.r_976_u, G_624_v.F_1410_V);
        this.n_1700_B(Items.Z_1243_X, G_624_v.F_1410_V);
        this.n_1700_B(Items.w_2152_d, G_624_v.F_1410_V);
        this.n_1700_B(Items.DeadBushBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.IronBarsBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.Z_4149_q, G_624_v.ValueObject);
        this.n_1700_B(Items.W_4813_f, G_624_v.ValueObject);
        this.n_1700_B(Items.u_1934_K, G_624_v.ValueObject);
        this.n_1700_B(Items.y_254_d, G_624_v.ValueObject);
        this.n_1700_B(Items.HoneyBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.RequirementsStrategy, G_624_v.ValueObject);
        this.n_1700_B(Items.d_3769_f, G_624_v.ValueObject);
        this.n_1700_B(Items.SimpleCriterionTrigger, G_624_v.ValueObject);
        this.n_1700_B(Items.CriterionTrigger, G_624_v.ValueObject);
        this.n_1700_B(Items.S_3844_E, G_624_v.ValueObject);
        this.n_1700_B(Items.S_1431_H, G_624_v.ValueObject);
        this.n_1700_B(Items.B_368_w, G_624_v.ValueObject);
        this.n_1700_B(Items.H_2506_c, G_624_v.ValueObject);
        this.n_1700_B(Items.u_925_K, G_624_v.ValueObject);
        this.n_1700_B(Items.SkullBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.MinecraftAccess, G_624_v.ValueObject);
        this.n_1700_B(Items.b_1707_w, G_624_v.ValueObject);
        this.n_1700_B(Items.v_448_E, G_624_v.ValueObject);
        this.n_1700_B(Items.PumpkinBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.L_2801_l, G_624_v.ValueObject);
        this.n_1700_B(Items.w_4059_h, G_624_v.ValueObject);
        this.n_1700_B(Items.U_2334_m, G_624_v.ValueObject);
        this.n_1700_B(Items.PoweredBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.u_782_h, G_624_v.ValueObject);
        this.n_1700_B(Items.RepeaterBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.RedstoneLampBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.z_3617_Q, G_624_v.ValueObject);
        this.n_1700_B(Items.Z_4734_t, G_624_v.ValueObject);
        this.n_1700_B(Items.RedstoneTorchBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.s_3698_N, G_624_v.ValueObject);
        this.n_1700_B(Items.HorizontalDirectionalBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.SandBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.O_922_L, G_624_v.F_1410_V);
        this.n_1700_B(Items.j_2129_E, G_624_v.ValueObject);
        this.n_1700_B(Items.O_1043_U, G_624_v.ValueObject);
        this.n_1700_B(Items.u_488_m, G_624_v.ValueObject);
        this.n_1700_B(Items.D_940_S, G_624_v.F_1410_V);
        this.n_1700_B(Items.q_4124_m, G_624_v.ValueObject);
        this.n_1700_B(Items.v_1900_v, G_624_v.ValueObject);
        this.n_1700_B(Items.K_1964_I, G_624_v.F_1410_V);
        this.n_1700_B(Items.m_396_H, G_624_v.ValueObject);
        this.n_1700_B(Items.D_563_q, G_624_v.F_1410_V);
        this.n_1700_B(Items.K_1200_E, G_624_v.F_1410_V);
        this.n_1700_B(Items.FletchingTableBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.FallingBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.m_1628_s, G_624_v.ValueObject);
        this.n_1700_B(Items.h_1723_G, G_624_v.ValueObject);
        this.n_1700_B(Items.q_3115_L, G_624_v.ValueObject);
        this.n_1700_B(Items.l_3370_o, G_624_v.ValueObject);
        this.n_1700_B(Items.RotatedPillarBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.PlayerWallHeadBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.T_2391_T, G_624_v.ValueObject);
        this.n_1700_B(Items.S_3458_C, G_624_v.ValueObject);
        this.n_1700_B(Items.MelonBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.j_1654_T, G_624_v.ValueObject);
        this.n_1700_B(Items.FrostedIceBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.v_4620_e, G_624_v.ValueObject);
        this.n_1700_B(Items.U_1258_d, G_624_v.ValueObject);
        this.n_1700_B(Items.F_2052_z, G_624_v.ValueObject);
        this.n_1700_B(Items.y_2012_u, G_624_v.ValueObject);
        this.n_1700_B(Items.z_936_s, G_624_v.ValueObject);
        this.n_1700_B(Items.FlowerBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.FungusBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.GlazedTerracottaBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.GrassBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.N_3347_G, G_624_v.ValueObject);
        this.n_1700_B(Items.P_1965_C, G_624_v.ValueObject);
        this.n_1700_B(Items.m_1964_F, G_624_v.ValueObject);
        this.n_1700_B(Items.Z_361_l, G_624_v.ValueObject);
        this.n_1700_B(Items.C_3304_p, G_624_v.ValueObject);
        this.n_1700_B(Items.j_1376_w, G_624_v.ValueObject);
        this.n_1700_B(Items.o_977_F, G_624_v.ValueObject);
        this.n_1700_B(Items.LightPredicate, G_624_v.ValueObject);
        this.n_1700_B(Items.NetherVines, G_624_v.ValueObject);
        this.n_1700_B(Items.PipeBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.Z_3822_q, G_624_v.ValueObject);
        this.n_1700_B(Items.i_770_g, G_624_v.ValueObject);
        this.n_1700_B(Items.g_2783_J, G_624_v.ValueObject);
        this.n_1700_B(Items.r_2687_x, G_624_v.ValueObject);
        this.n_1700_B(Items.ObserverBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.A_4514_U, G_624_v.F_1410_V);
        this.n_1700_B(Items.z_283_n, G_624_v.F_1410_V);
        this.n_1700_B(Items.a_1887_j, G_624_v.F_1410_V);
        this.n_1700_B(Items.Y_4293_u, G_624_v.F_1410_V);
        this.n_1700_B(Items.z_4066_l, G_624_v.F_1410_V);
        this.n_1700_B(Items.Y_3623_f, G_624_v.F_1410_V);
        this.n_1700_B(Items.o_3456_E, G_624_v.ValueObject);
        this.n_1700_B(Items.Q_2342_H, G_624_v.ValueObject);
        this.n_1700_B(Items.h_935_G, G_624_v.ValueObject);
        this.n_1700_B(Items.N_81_X, G_624_v.ValueObject);
        this.n_1700_B(Items.P_2605_j, G_624_v.ValueObject);
        this.n_1700_B(Items.J_1008_m, G_624_v.ValueObject);
        this.n_1700_B(Items.v_2826_q, G_624_v.ValueObject);
        this.n_1700_B(Items.S_315_z, G_624_v.ValueObject);
        this.n_1700_B(Items.W_2770_z, G_624_v.ValueObject);
        this.n_1700_B(Items.V_3441_j, G_624_v.ValueObject);
        this.n_1700_B(Items.ServerFunctionManager, G_624_v.ValueObject);
        this.n_1700_B(Items.a_2727_J, G_624_v.F_1410_V);
        this.n_1700_B(Items.D_1410_T, G_624_v.F_1410_V);
        this.n_1700_B(Items.N_1833_W, G_624_v.F_1410_V);
        this.n_1700_B(Items.B_707_U, G_624_v.F_1410_V);
        this.n_1700_B(Items.S_234_U, G_624_v.F_1410_V);
        this.n_1700_B(Items.CropBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.CryingObsidianBlock, G_624_v.ValueObject);
        this.n_1700_B(Items.S_4998_h, G_624_v.ValueObject);
        this.n_1700_B(Items.q_3398_T, Items.A_4514_U, G_624_v.F_1410_V);
        this.n_1700_B(Items.E_4612_l, Items.p_863_D, G_624_v.ValueObject);
    }
}



