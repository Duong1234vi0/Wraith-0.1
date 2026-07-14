/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.T_2915_h;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.h_1015_G;
import lightning.product.p_1183_T;
import lightning.product.ModeSetting;
import lightning.product.Items;
import lightning.product.u_1934_K;
import lightning.product.x_4991_F;
import lightning.product.ModuleCategory;

public class AutoTool
extends Module {
    private final ModeSetting rezhimMode = new ModeSetting("\u0420\u0435\u0436\u0438\u043c", "\u0421\u0430\u0439\u043b\u0435\u043d\u0442", "\u041e\u0431\u044b\u0447\u043d\u044b\u0439", "\u0421\u0430\u0439\u043b\u0435\u043d\u0442");
    private int w_1484_f = -1;
    private int t_148_a = -1;
    private int s_956_w = -1;

    public AutoTool() {
        super("AutoTool", ModuleCategory.G_564_y);
        this.addSettings(this.rezhimMode);
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        if (!this.Q_4569_t()) {
            return;
        }
        if (AutoTool.c_3005_b.Y_259_p == null || AutoTool.c_3005_b.Y_601_j == null || AutoTool.c_3005_b.Y_259_p.G_624_v()) {
            this.s_956_w = -1;
            return;
        }
        if (this.t_1786_h()) {
            int bestToolSlot = this.M_182_A();
            if (bestToolSlot != -1) {
                if (this.s_956_w == -1) {
                    this.s_956_w = AutoTool.c_3005_b.Y_259_p.l_1268_F.G_564_y;
                }
                AutoTool.c_3005_b.Y_259_p.l_1268_F.G_564_y = bestToolSlot;
            }
        } else if (this.s_956_w != -1) {
            AutoTool.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.s_956_w;
            this.s_956_w = -1;
        }
    }

    private boolean h_1847_R() {
        return this.rezhimMode.isMode("\u0421\u0430\u0439\u043b\u0435\u043d\u0442");
    }

    private boolean Q_4569_t() {
        return this.rezhimMode.isMode("\u041e\u0431\u044b\u0447\u043d\u044b\u0439") || this.rezhimMode.isMode("\u041b\u0435\u0433\u0438\u0442");
    }

    @Override
    public void onDisable() {
        if (AutoTool.c_3005_b.Y_259_p != null) {
            if (this.h_1847_R() && this.w_1484_f != -1) {
                AutoTool.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.w_1484_f;
                AutoTool.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.w_1484_f));
            }
            if (this.Q_4569_t() && this.s_956_w != -1) {
                AutoTool.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.s_956_w;
            }
        }
        this.w_1484_f = -1;
        this.t_148_a = -1;
        this.s_956_w = -1;
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(x_4991_F e) {
        if (!this.h_1847_R()) {
            return;
        }
        if (e.G_564_y() == x_4991_F.n_1700_B.n_1700_B) {
            int bestToolSlot;
            if (this.w_1484_f == -1) {
                this.w_1484_f = AutoTool.c_3005_b.Y_259_p.l_1268_F.G_564_y;
            }
            if ((bestToolSlot = this.J_1907_R(e)) != -1) {
                if (AutoTool.c_3005_b.Y_259_p.l_1268_F.G_564_y != bestToolSlot) {
                    AutoTool.c_3005_b.Y_259_p.l_1268_F.G_564_y = bestToolSlot;
                }
                if (this.t_148_a != bestToolSlot) {
                    AutoTool.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(bestToolSlot));
                    this.t_148_a = bestToolSlot;
                }
            }
        } else if (this.w_1484_f != -1) {
            AutoTool.c_3005_b.Y_259_p.l_1268_F.G_564_y = this.w_1484_f;
            AutoTool.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new p_1183_T(this.w_1484_f));
            this.w_1484_f = -1;
            this.t_148_a = -1;
        }
    }

    private int J_1907_R(x_4991_F e) {
        int bestToolSlot = -1;
        if (e.J_1907_R().J_1907_R() == a_3742_W.y_1700_S) {
            for (int i = 0; i < 9; ++i) {
                if (AutoTool.c_3005_b.Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != Items.LightPredicate) continue;
                bestToolSlot = i;
                break;
            }
        }
        if (bestToolSlot == -1) {
            bestToolSlot = u_1934_K.n_1700_B(e.J_1907_R());
        }
        return bestToolSlot;
    }

    private int M_182_A() {
        if (!(AutoTool.c_3005_b.Z_875_P instanceof BlockHitResult)) {
            return -1;
        }
        BlockHitResult ray = (BlockHitResult)AutoTool.c_3005_b.Z_875_P;
        T_2915_h block = AutoTool.c_3005_b.Y_601_j.getBlockState(ray.n_1700_B()).J_1907_R();
        if (block == a_3742_W.y_1700_S) {
            for (int slot = 0; slot < 9; ++slot) {
                if (AutoTool.c_3005_b.Y_259_p.l_1268_F.s_956_w(slot).J_1907_R() != Items.LightPredicate) continue;
                return slot;
            }
        }
        return u_1934_K.n_1700_B(AutoTool.c_3005_b.Y_601_j.getBlockState(ray.n_1700_B()));
    }

    private boolean t_1786_h() {
        return AutoTool.c_3005_b.Z_875_P != null && AutoTool.c_3005_b.P_4830_p.D_60_a.G_564_y();
    }
}



