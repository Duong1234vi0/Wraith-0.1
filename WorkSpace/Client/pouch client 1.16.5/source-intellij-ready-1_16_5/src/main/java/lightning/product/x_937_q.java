/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Optional;
import javax.annotation.Nullable;
import lightning.product.F_2904_S;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.P_11_z;
import lightning.product.CombatEntry;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.BlockTags;
import lightning.product.r_4811_B;
import lightning.product.x_282_a;

public class x_937_q {
    private final List<CombatEntry> n_1700_B = Lists.newArrayList();
    private final r_4811_B J_1907_R;
    private int R_4764_Y;
    private int G_564_y;
    private int P_1922_E;
    private boolean u_1723_Y;
    private boolean v_4262_N;
    private String w_1484_f;

    public x_937_q(r_4811_B fighterIn) {
        this.J_1907_R = fighterIn;
    }

    public void n_1700_B() {
        this.w_1484_f();
        Optional<c_1514_x> optional = this.J_1907_R.Z_4720_K();
        if (optional.isPresent()) {
            K_4074_S blockstate = this.J_1907_R.O_508_d.getBlockState(optional.get());
            this.w_1484_f = !blockstate.n_1700_B(a_3742_W.L_3570_A) && !blockstate.n_1700_B(BlockTags.z_1737_N) ? (blockstate.n_1700_B(a_3742_W.U_4087_m) ? "vines" : (!blockstate.n_1700_B(a_3742_W.RequirementsStrategy) && !blockstate.n_1700_B(a_3742_W.S_4998_h) ? (!blockstate.n_1700_B(a_3742_W.SimpleCriterionTrigger) && !blockstate.n_1700_B(a_3742_W.T_2391_T) ? (blockstate.n_1700_B(a_3742_W.i_770_g) ? "scaffolding" : "other_climbable") : "twisting_vines") : "weeping_vines")) : "ladder";
        } else if (this.J_1907_R.RowButton()) {
            this.w_1484_f = "water";
        }
    }

    public void n_1700_B(P_11_z damageSrc, float healthIn, float damageAmount) {
        this.P_1922_E();
        this.n_1700_B();
        CombatEntry combatentry = new CombatEntry(damageSrc, this.J_1907_R.RealmsWorldResetDto, healthIn, damageAmount, this.w_1484_f, this.J_1907_R.U_1241_n);
        this.n_1700_B.add(combatentry);
        this.R_4764_Y = this.J_1907_R.RealmsWorldResetDto;
        this.v_4262_N = true;
        if (combatentry.R_4764_Y() && !this.u_1723_Y && this.J_1907_R.RealmsLongRunningMcoTaskScreen()) {
            this.u_1723_Y = true;
            this.P_1922_E = this.G_564_y = this.J_1907_R.RealmsWorldResetDto;
            this.J_1907_R.E_4256_w();
        }
    }

    public x_282_a J_1907_R() {
        x_282_a itextcomponent;
        if (this.n_1700_B.isEmpty()) {
            return new F_2904_S("death.attack.generic", this.J_1907_R.c_());
        }
        CombatEntry combatentry = this.v_4262_N();
        CombatEntry combatentry1 = this.n_1700_B.get(this.n_1700_B.size() - 1);
        x_282_a itextcomponent1 = combatentry1.P_1922_E();
        N_4263_v entity = combatentry1.n_1700_B().u_2550_I();
        if (combatentry != null && combatentry1.n_1700_B() == P_11_z.u_2550_I) {
            x_282_a itextcomponent2 = combatentry.P_1922_E();
            if (combatentry.n_1700_B() != P_11_z.u_2550_I && combatentry.n_1700_B() != P_11_z.P_4830_p) {
                if (!(itextcomponent2 == null || itextcomponent1 != null && itextcomponent2.equals(itextcomponent1))) {
                    Z_1993_T itemstack1;
                    N_4263_v entity1 = combatentry.n_1700_B().u_2550_I();
                    Z_1993_T z_1993_T = itemstack1 = entity1 instanceof r_4811_B ? ((r_4811_B)entity1).A_2714_y() : Z_1993_T.J_1907_R;
                    itextcomponent = !itemstack1.n_1700_B() && itemstack1.Y_601_j() ? new F_2904_S("death.fell.assist.item", this.J_1907_R.c_(), itextcomponent2, itemstack1.A_4115_X()) : new F_2904_S("death.fell.assist", this.J_1907_R.c_(), itextcomponent2);
                } else if (itextcomponent1 != null) {
                    Z_1993_T itemstack;
                    Z_1993_T z_1993_T = itemstack = entity instanceof r_4811_B ? ((r_4811_B)entity).A_2714_y() : Z_1993_T.J_1907_R;
                    itextcomponent = !itemstack.n_1700_B() && itemstack.Y_601_j() ? new F_2904_S("death.fell.finish.item", this.J_1907_R.c_(), itextcomponent1, itemstack.A_4115_X()) : new F_2904_S("death.fell.finish", this.J_1907_R.c_(), itextcomponent1);
                } else {
                    itextcomponent = new F_2904_S("death.fell.killer", this.J_1907_R.c_());
                }
            } else {
                itextcomponent = new F_2904_S("death.fell.accident." + this.n_1700_B(combatentry), this.J_1907_R.c_());
            }
        } else {
            itextcomponent = combatentry1.n_1700_B().n_1700_B(this.J_1907_R);
        }
        return itextcomponent;
    }

    @Nullable
    public r_4811_B R_4764_Y() {
        r_4811_B livingentity = null;
        a_3913_L playerentity = null;
        float f = 0.0f;
        float f1 = 0.0f;
        for (CombatEntry combatentry : this.n_1700_B) {
            if (combatentry.n_1700_B().u_2550_I() instanceof a_3913_L && (playerentity == null || combatentry.J_1907_R() > f1)) {
                f1 = combatentry.J_1907_R();
                playerentity = (a_3913_L)combatentry.n_1700_B().u_2550_I();
            }
            if (!(combatentry.n_1700_B().u_2550_I() instanceof r_4811_B) || livingentity != null && !(combatentry.J_1907_R() > f)) continue;
            f = combatentry.J_1907_R();
            livingentity = (r_4811_B)combatentry.n_1700_B().u_2550_I();
        }
        return playerentity != null && f1 >= f / 3.0f ? playerentity : livingentity;
    }

    @Nullable
    private CombatEntry v_4262_N() {
        CombatEntry combatentry = null;
        CombatEntry combatentry1 = null;
        float f = 0.0f;
        float f1 = 0.0f;
        for (int i = 0; i < this.n_1700_B.size(); ++i) {
            CombatEntry combatentry3;
            CombatEntry combatentry2 = this.n_1700_B.get(i);
            CombatEntry q_4534_n = combatentry3 = i > 0 ? this.n_1700_B.get(i - 1) : null;
            if ((combatentry2.n_1700_B() == P_11_z.u_2550_I || combatentry2.n_1700_B() == P_11_z.P_4830_p) && combatentry2.u_1723_Y() > 0.0f && (combatentry == null || combatentry2.u_1723_Y() > f1)) {
                combatentry = i > 0 ? combatentry3 : combatentry2;
                f1 = combatentry2.u_1723_Y();
            }
            if (combatentry2.G_564_y() == null || combatentry1 != null && !(combatentry2.J_1907_R() > f)) continue;
            combatentry1 = combatentry2;
            f = combatentry2.J_1907_R();
        }
        if (f1 > 5.0f && combatentry != null) {
            return combatentry;
        }
        return f > 5.0f && combatentry1 != null ? combatentry1 : null;
    }

    private String n_1700_B(CombatEntry entry) {
        return entry.G_564_y() == null ? "generic" : entry.G_564_y();
    }

    public int G_564_y() {
        return this.u_1723_Y ? this.J_1907_R.RealmsWorldResetDto - this.G_564_y : this.P_1922_E - this.G_564_y;
    }

    private void w_1484_f() {
        this.w_1484_f = null;
    }

    public void P_1922_E() {
        int i;
        int n = i = this.u_1723_Y ? 300 : 100;
        if (this.v_4262_N && (!this.J_1907_R.RealmsLongRunningMcoTaskScreen() || this.J_1907_R.RealmsWorldResetDto - this.R_4764_Y > i)) {
            boolean flag = this.u_1723_Y;
            this.v_4262_N = false;
            this.u_1723_Y = false;
            this.P_1922_E = this.J_1907_R.RealmsWorldResetDto;
            if (flag) {
                this.J_1907_R.V_1665_T();
            }
            this.n_1700_B.clear();
        }
    }

    public r_4811_B u_1723_Y() {
        return this.J_1907_R;
    }
}


