/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.NumberSetting;
import lightning.product.V_772_m;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.m_3054_I;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.u_1934_K;
import lightning.product.u_925_K;
import lightning.product.x_1688_C;
import lightning.product.ModuleCategory;

public class Spider
extends Module {
    private final ModeSetting modMode = new ModeSetting("\u041c\u043e\u0434", "Vanilla", "Vanilla", "Jump", "GrimSlime", "WaterBucket", "Sphere");
    private final NumberSetting skorostSetting = new NumberSetting("\u0421\u043a\u043e\u0440\u043e\u0441\u0442\u044c", 0.2f, 0.1f, 0.5f, 0.05f, () -> this.modMode.isMode("Vanilla"));
    private static final int t_148_a = 15;
    private static final int s_956_w = 150;
    private long u_2550_I;
    private long M_588_G;

    public Spider() {
        super("Spider", ModuleCategory.J_1907_R);
        this.addSettings(this.modMode, this.skorostSetting);
    }

    @Override
    public void onDisable() {
        this.M_588_G = 0L;
        this.u_2550_I = 0L;
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G event) {
        if (Spider.c_3005_b.Y_259_p == null || Spider.c_3005_b.Y_601_j == null) {
            return;
        }
        if (!this.modMode.isMode("Sphere")) {
            this.M_588_G = 0L;
        }
        if (this.modMode.isMode("Vanilla") && Spider.c_3005_b.Y_259_p.D_60_a && u_925_K.n_1700_B()) {
            Spider.c_3005_b.Y_259_p.h_1847_R(Spider.c_3005_b.Y_259_p.I_4348_c().J_1907_R, ((Float)this.skorostSetting.getValue()).floatValue(), Spider.c_3005_b.Y_259_p.I_4348_c().G_564_y);
        }
        if (this.modMode.isMode("Jump") && Spider.c_3005_b.Y_259_p.D_60_a && u_925_K.n_1700_B() && (Spider.c_3005_b.Y_259_p.M_1641_O() || this.Q_4569_t())) {
            Spider.c_3005_b.Y_259_p.e_837_t();
        }
        if (this.modMode.isMode("GrimSlime")) {
            if (!this.M_182_A()) {
                return;
            }
            if (Spider.c_3005_b.Y_259_p.D_60_a) {
                Spider.c_3005_b.Y_259_p.h_1847_R(Spider.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.2, Spider.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            }
        }
        if (this.modMode.isMode("WaterBucket") && u_1934_K.n_1700_B(Items.W_2770_z) != -1) {
            if (Spider.c_3005_b.Y_259_p.A_2714_y().J_1907_R() != Items.W_2770_z) {
                Spider.c_3005_b.Y_259_p.l_1268_F.G_564_y = u_1934_K.n_1700_B(Items.W_2770_z);
            }
            if (Spider.c_3005_b.Y_259_p.D_60_a) {
                Spider.c_3005_b.w_1457_N.processRightClick(Spider.c_3005_b.Y_259_p, Spider.c_3005_b.Y_601_j, x_1688_C.n_1700_B);
                Spider.c_3005_b.Y_259_p.f_4016_n = 0.0f;
                Spider.c_3005_b.Y_259_p.h_1847_R(Spider.c_3005_b.Y_259_p.I_4348_c().J_1907_R, 0.35, Spider.c_3005_b.Y_259_p.I_4348_c().G_564_y);
            }
        }
        if (this.modMode.isMode("Sphere")) {
            this.h_1847_R();
        }
    }

    private void h_1847_R() {
        e_2866_D look;
        e_2866_D end;
        V_772_m player = Spider.c_3005_b.Y_259_p;
        if (player == null || Spider.c_3005_b.Y_601_j == null || Spider.c_3005_b.w_1457_N == null) {
            return;
        }
        long now = System.currentTimeMillis();
        if (this.M_588_G != 0L && now >= this.M_588_G) {
            this.M_588_G = 0L;
        }
        if (!player.D_60_a || !u_925_K.n_1700_B()) {
            return;
        }
        Z_1993_T off = player.S_4035_N();
        if (off.n_1700_B() || off.J_1907_R() != Items.C_3560_B) {
            return;
        }
        player.f_4016_n = 80.0f;
        if (now - this.u_2550_I < 15L) {
            return;
        }
        double reach = Spider.c_3005_b.w_1457_N.getBlockReachDistance();
        e_2866_D eyes = player.u_2550_I(1.0f);
        ClipContext ctx = new ClipContext(eyes, end = eyes.P_1922_E((look = player.t_148_a(1.0f)).n_1700_B(reach)), ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, player);
        BlockHitResult hit = Spider.c_3005_b.Y_601_j.n_1700_B(ctx);
        if (hit.R_4764_Y() != HitResult.n_1700_B.J_1907_R) {
            return;
        }
        m_3054_I placeResult = Spider.c_3005_b.w_1457_N.func_217292_a(player, Spider.c_3005_b.Y_601_j, x_1688_C.J_1907_R, hit);
        if (!placeResult.n_1700_B()) {
            return;
        }
        this.u_2550_I = now;
        player.n_1700_B(x_1688_C.J_1907_R);
        if (!Spider.c_3005_b.Y_601_j.u_1723_Y(player.b_2312_j().down())) {
            this.M_588_G = now + 150L;
        }
    }

    private boolean Q_4569_t() {
        return Spider.c_3005_b.Y_259_p.D_60_a && Spider.c_3005_b.Y_259_p.I_4348_c().R_4764_Y < 0.0;
    }

    private boolean M_182_A() {
        c_1514_x playerPos = new c_1514_x(Spider.c_3005_b.Y_259_p.s_4990_V());
        for (int x = -2; x <= 2; ++x) {
            for (int y = -2; y <= 2; ++y) {
                for (int z = -2; z <= 2; ++z) {
                    c_1514_x checkPos = playerPos.add(x, y, z);
                    if (Spider.c_3005_b.Y_601_j.getBlockState(checkPos).J_1907_R() != a_3742_W.g_4841_c) continue;
                    return true;
                }
            }
        }
        return false;
    }
}



