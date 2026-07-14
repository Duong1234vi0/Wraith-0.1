/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

public class MaterialColor {
    public static final MaterialColor[] n_1700_B = new MaterialColor[64];
    public static final MaterialColor J_1907_R = new MaterialColor(0, 0);
    public static final MaterialColor R_4764_Y = new MaterialColor(1, 8368696);
    public static final MaterialColor G_564_y = new MaterialColor(2, 16247203);
    public static final MaterialColor P_1922_E = new MaterialColor(3, 0xC7C7C7);
    public static final MaterialColor u_1723_Y = new MaterialColor(4, 0xFF0000);
    public static final MaterialColor v_4262_N = new MaterialColor(5, 0xA0A0FF);
    public static final MaterialColor w_1484_f = new MaterialColor(6, 0xA7A7A7);
    public static final MaterialColor t_148_a = new MaterialColor(7, 31744);
    public static final MaterialColor s_956_w = new MaterialColor(8, 0xFFFFFF);
    public static final MaterialColor u_2550_I = new MaterialColor(9, 10791096);
    public static final MaterialColor M_588_G = new MaterialColor(10, 9923917);
    public static final MaterialColor P_4830_p = new MaterialColor(11, 0x707070);
    public static final MaterialColor h_1847_R = new MaterialColor(12, 0x4040FF);
    public static final MaterialColor Q_4569_t = new MaterialColor(13, 9402184);
    public static final MaterialColor M_182_A = new MaterialColor(14, 0xFFFCF5);
    public static final MaterialColor t_1786_h = new MaterialColor(15, 14188339);
    public static final MaterialColor multiplayerClientSuggestionProvider = new MaterialColor(16, 11685080);
    public static final MaterialColor w_1457_N = new MaterialColor(17, 6724056);
    public static final MaterialColor Y_601_j = new MaterialColor(18, 0xE5E533);
    public static final MaterialColor Y_259_p = new MaterialColor(19, 8375321);
    public static final MaterialColor Q_2552_b = new MaterialColor(20, 15892389);
    public static final MaterialColor C_2741_M = new MaterialColor(21, 0x4C4C4C);
    public static final MaterialColor k_2293_S = new MaterialColor(22, 0x999999);
    public static final MaterialColor q_2307_F = new MaterialColor(23, 5013401);
    public static final MaterialColor Z_875_P = new MaterialColor(24, 8339378);
    public static final MaterialColor c_3005_b = new MaterialColor(25, 3361970);
    public static final MaterialColor H_2857_Y = new MaterialColor(26, 6704179);
    public static final MaterialColor A_4115_X = new MaterialColor(27, 6717235);
    public static final MaterialColor Y_1740_V = new MaterialColor(28, 0x993333);
    public static final MaterialColor t_4043_B = new MaterialColor(29, 0x191919);
    public static final MaterialColor x_607_J = new MaterialColor(30, 16445005);
    public static final MaterialColor e_4240_b = new MaterialColor(31, 6085589);
    public static final MaterialColor n_3318_d = new MaterialColor(32, 4882687);
    public static final MaterialColor d_2427_y = new MaterialColor(33, 55610);
    public static final MaterialColor z_1737_N = new MaterialColor(34, 8476209);
    public static final MaterialColor v_4276_D = new MaterialColor(35, 0x700200);
    public static final MaterialColor d_2461_k = new MaterialColor(36, 13742497);
    public static final MaterialColor G_624_v = new MaterialColor(37, 10441252);
    public static final MaterialColor T_2506_i = new MaterialColor(38, 9787244);
    public static final MaterialColor q_4610_l = new MaterialColor(39, 7367818);
    public static final MaterialColor z_4693_k = new MaterialColor(40, 12223780);
    public static final MaterialColor g_221_o = new MaterialColor(41, 6780213);
    public static final MaterialColor e_2887_G = new MaterialColor(42, 10505550);
    public static final MaterialColor B_1668_F = new MaterialColor(43, 0x392923);
    public static final MaterialColor g_164_R = new MaterialColor(44, 8874850);
    public static final MaterialColor X_933_l = new MaterialColor(45, 0x575C5C);
    public static final MaterialColor Z_976_R = new MaterialColor(46, 8014168);
    public static final MaterialColor H_1990_U = new MaterialColor(47, 4996700);
    public static final MaterialColor N_2525_X = new MaterialColor(48, 4993571);
    public static final MaterialColor c_4037_x = new MaterialColor(49, 5001770);
    public static final MaterialColor g_2268_R = new MaterialColor(50, 9321518);
    public static final MaterialColor T_3594_S = new MaterialColor(51, 2430480);
    public static final MaterialColor D_4792_h = new MaterialColor(52, 12398641);
    public static final MaterialColor s_2632_s = new MaterialColor(53, 9715553);
    public static final MaterialColor l_1233_K = new MaterialColor(54, 6035741);
    public static final MaterialColor z_1333_t = new MaterialColor(55, 1474182);
    public static final MaterialColor O_508_d = new MaterialColor(56, 3837580);
    public static final MaterialColor r_715_M = new MaterialColor(57, 5647422);
    public static final MaterialColor A_1038_p = new MaterialColor(58, 1356933);
    public int i_1637_u;
    public final int Ping;

    private MaterialColor(int index, int color) {
        if (index < 0 || index > 63) {
            throw new IndexOutOfBoundsException("Map colour ID must be between 0 and 63 (inclusive)");
        }
        this.Ping = index;
        this.i_1637_u = color;
        MaterialColor.n_1700_B[index] = this;
    }

    public int n_1700_B(int index) {
        int i = 220;
        if (index == 3) {
            i = 135;
        }
        if (index == 2) {
            i = 255;
        }
        if (index == 1) {
            i = 220;
        }
        if (index == 0) {
            i = 180;
        }
        int j = (this.i_1637_u >> 16 & 0xFF) * i / 255;
        int k = (this.i_1637_u >> 8 & 0xFF) * i / 255;
        int l = (this.i_1637_u & 0xFF) * i / 255;
        return 0xFF000000 | l << 16 | k << 8 | j;
    }
}


