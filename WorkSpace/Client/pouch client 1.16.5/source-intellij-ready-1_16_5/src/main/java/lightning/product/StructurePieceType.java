/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Locale;
import lightning.product.C_1437_B;
import lightning.product.E_3771_B;
import lightning.product.WoodlandMansionPieces;
import lightning.product.PoolElementStructurePiece;
import lightning.product.N_2400_q;
import lightning.product.JunglePyramidPiece;
import lightning.product.U_2912_j;
import lightning.product.V_3137_a;
import lightning.product.NetherFossilPieces;
import lightning.product.b_2085_h;
import lightning.product.e_1123_d;
import lightning.product.f_1884_F;
import lightning.product.DesertPyramidPiece;
import lightning.product.g_4102_b;
import lightning.product.h_4966_V;
import lightning.product.i_3880_A;
import lightning.product.o_2945_w;
import lightning.product.o_3297_d;
import lightning.product.OceanRuinPieces;
import lightning.product.r_4360_R;

public interface StructurePieceType {
    public static final StructurePieceType n_1700_B = StructurePieceType.n_1700_B(e_1123_d.n_1700_B::new, "MSCorridor");
    public static final StructurePieceType J_1907_R = StructurePieceType.n_1700_B(e_1123_d.J_1907_R::new, "MSCrossing");
    public static final StructurePieceType R_4764_Y = StructurePieceType.n_1700_B(e_1123_d.G_564_y::new, "MSRoom");
    public static final StructurePieceType G_564_y = StructurePieceType.n_1700_B(e_1123_d.P_1922_E::new, "MSStairs");
    public static final StructurePieceType P_1922_E = StructurePieceType.n_1700_B(i_3880_A.w_1484_f::new, "NeBCr");
    public static final StructurePieceType u_1723_Y = StructurePieceType.n_1700_B(i_3880_A.t_148_a::new, "NeBEF");
    public static final StructurePieceType v_4262_N = StructurePieceType.n_1700_B(i_3880_A.M_182_A::new, "NeBS");
    public static final StructurePieceType w_1484_f = StructurePieceType.n_1700_B(i_3880_A.R_4764_Y::new, "NeCCS");
    public static final StructurePieceType t_148_a = StructurePieceType.n_1700_B(i_3880_A.G_564_y::new, "NeCTB");
    public static final StructurePieceType s_956_w = StructurePieceType.n_1700_B(i_3880_A.s_956_w::new, "NeCE");
    public static final StructurePieceType u_2550_I = StructurePieceType.n_1700_B(i_3880_A.v_4262_N::new, "NeSCSC");
    public static final StructurePieceType M_588_G = StructurePieceType.n_1700_B(i_3880_A.n_1700_B::new, "NeSCLT");
    public static final StructurePieceType P_4830_p = StructurePieceType.n_1700_B(i_3880_A.P_1922_E::new, "NeSC");
    public static final StructurePieceType h_1847_R = StructurePieceType.n_1700_B(i_3880_A.J_1907_R::new, "NeSCRT");
    public static final StructurePieceType Q_4569_t = StructurePieceType.n_1700_B(i_3880_A.u_2550_I::new, "NeCSR");
    public static final StructurePieceType M_182_A = StructurePieceType.n_1700_B(i_3880_A.t_1786_h::new, "NeMT");
    public static final StructurePieceType t_1786_h = StructurePieceType.n_1700_B(i_3880_A.u_1723_Y::new, "NeRC");
    public static final StructurePieceType multiplayerClientSuggestionProvider = StructurePieceType.n_1700_B(i_3880_A.h_1847_R::new, "NeSR");
    public static final StructurePieceType w_1457_N = StructurePieceType.n_1700_B(i_3880_A.Q_4569_t::new, "NeStart");
    public static final StructurePieceType Y_601_j = StructurePieceType.n_1700_B(g_4102_b.n_1700_B::new, "SHCC");
    public static final StructurePieceType Y_259_p = StructurePieceType.n_1700_B(g_4102_b.J_1907_R::new, "SHFC");
    public static final StructurePieceType Q_2552_b = StructurePieceType.n_1700_B(g_4102_b.R_4764_Y::new, "SH5C");
    public static final StructurePieceType C_2741_M = StructurePieceType.n_1700_B(g_4102_b.G_564_y::new, "SHLT");
    public static final StructurePieceType k_2293_S = StructurePieceType.n_1700_B(g_4102_b.P_1922_E::new, "SHLi");
    public static final StructurePieceType q_2307_F = StructurePieceType.n_1700_B(g_4102_b.v_4262_N::new, "SHPR");
    public static final StructurePieceType Z_875_P = StructurePieceType.n_1700_B(g_4102_b.w_1484_f::new, "SHPH");
    public static final StructurePieceType c_3005_b = StructurePieceType.n_1700_B(g_4102_b.t_148_a::new, "SHRT");
    public static final StructurePieceType H_2857_Y = StructurePieceType.n_1700_B(g_4102_b.s_956_w::new, "SHRC");
    public static final StructurePieceType A_4115_X = StructurePieceType.n_1700_B(g_4102_b.u_2550_I::new, "SHSD");
    public static final StructurePieceType Y_1740_V = StructurePieceType.n_1700_B(g_4102_b.M_588_G::new, "SHStart");
    public static final StructurePieceType t_4043_B = StructurePieceType.n_1700_B(g_4102_b.Q_4569_t::new, "SHS");
    public static final StructurePieceType x_607_J = StructurePieceType.n_1700_B(g_4102_b.P_4830_p::new, "SHSSD");
    public static final StructurePieceType e_4240_b = StructurePieceType.n_1700_B(JunglePyramidPiece::new, "TeJP");
    public static final StructurePieceType n_3318_d = StructurePieceType.n_1700_B(OceanRuinPieces.n_1700_B::new, "ORP");
    public static final StructurePieceType d_2427_y = StructurePieceType.n_1700_B(f_1884_F.n_1700_B::new, "Iglu");
    public static final StructurePieceType z_1737_N = StructurePieceType.n_1700_B(h_4966_V::new, "RUPO");
    public static final StructurePieceType v_4276_D = StructurePieceType.n_1700_B(o_3297_d::new, "TeSH");
    public static final StructurePieceType d_2461_k = StructurePieceType.n_1700_B(DesertPyramidPiece::new, "TeDP");
    public static final StructurePieceType G_624_v = StructurePieceType.n_1700_B(o_2945_w.s_956_w::new, "OMB");
    public static final StructurePieceType T_2506_i = StructurePieceType.n_1700_B(o_2945_w.u_2550_I::new, "OMCR");
    public static final StructurePieceType q_4610_l = StructurePieceType.n_1700_B(o_2945_w.n_1700_B::new, "OMDXR");
    public static final StructurePieceType z_4693_k = StructurePieceType.n_1700_B(o_2945_w.J_1907_R::new, "OMDXYR");
    public static final StructurePieceType g_221_o = StructurePieceType.n_1700_B(o_2945_w.R_4764_Y::new, "OMDYR");
    public static final StructurePieceType e_2887_G = StructurePieceType.n_1700_B(o_2945_w.G_564_y::new, "OMDYZR");
    public static final StructurePieceType B_1668_F = StructurePieceType.n_1700_B(o_2945_w.P_1922_E::new, "OMDZR");
    public static final StructurePieceType g_164_R = StructurePieceType.n_1700_B(o_2945_w.u_1723_Y::new, "OMEntry");
    public static final StructurePieceType X_933_l = StructurePieceType.n_1700_B(o_2945_w.M_588_G::new, "OMPenthouse");
    public static final StructurePieceType Z_976_R = StructurePieceType.n_1700_B(o_2945_w.Q_4569_t::new, "OMSimple");
    public static final StructurePieceType H_1990_U = StructurePieceType.n_1700_B(o_2945_w.M_182_A::new, "OMSimpleT");
    public static final StructurePieceType N_2525_X = StructurePieceType.n_1700_B(o_2945_w.t_1786_h::new, "OMWR");
    public static final StructurePieceType c_4037_x = StructurePieceType.n_1700_B(C_1437_B.n_1700_B::new, "ECP");
    public static final StructurePieceType g_2268_R = StructurePieceType.n_1700_B(WoodlandMansionPieces.R_4764_Y::new, "WMP");
    public static final StructurePieceType T_3594_S = StructurePieceType.n_1700_B(r_4360_R.n_1700_B::new, "BTP");
    public static final StructurePieceType D_4792_h = StructurePieceType.n_1700_B(N_2400_q.n_1700_B::new, "Shipwreck");
    public static final StructurePieceType s_2632_s = StructurePieceType.n_1700_B(NetherFossilPieces.n_1700_B::new, "NeFos");
    public static final StructurePieceType l_1233_K = StructurePieceType.n_1700_B(PoolElementStructurePiece::new, "jigsaw");

    public E_3771_B load(b_2085_h var1, U_2912_j var2);

    public static StructurePieceType n_1700_B(StructurePieceType type, String key) {
        return V_3137_a.n_1700_B(V_3137_a.RealmsWorldResetDto, key.toLowerCase(Locale.ROOT), type);
    }
}


