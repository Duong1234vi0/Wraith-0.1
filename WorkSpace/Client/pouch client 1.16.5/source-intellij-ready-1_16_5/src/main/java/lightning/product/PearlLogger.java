/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.Projectile;
import lightning.product.D_4024_W;
import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.O_1309_Q;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.h_1015_G;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.AttackAura;
import lightning.product.v_1900_v;
import lightning.product.w_2989_N;
import lightning.product.ModuleCategory;

public class PearlLogger
extends Module {
    private final BooleanSetting reagirovatNaVasEnabled = new BooleanSetting("\u0420\u0435\u0430\u0433\u0438\u0440\u043e\u0432\u0430\u0442\u044c \u043d\u0430 \u0432\u0430\u0441", false);
    private final BooleanSetting avtoGpsDoPerlaEnabled = new BooleanSetting("\u0410\u0432\u0442\u043e GPS \u0434\u043e \u043f\u0451\u0440\u043b\u0430", false);
    private boolean t_148_a = true;
    private a_3913_L s_956_w = null;
    private double u_2550_I = 0.0;
    private double M_588_G = 0.0;
    private boolean P_4830_p = false;

    public PearlLogger() {
        super("PearlLogger", ModuleCategory.G_564_y);
        this.addSettings(this.reagirovatNaVasEnabled, this.avtoGpsDoPerlaEnabled);
    }

    @Override
    public void onEnable() {
        this.t_148_a = true;
        this.s_956_w = null;
        this.P_4830_p = false;
        this.u_2550_I = 0.0;
        this.M_588_G = 0.0;
        super.onEnable();
    }

    @Override
    public void onDisable() {
        if (this.P_4830_p && O_1309_Q.J_1907_R && Math.abs(O_1309_Q.R_4764_Y - this.u_2550_I) < 0.1 && Math.abs(O_1309_Q.G_564_y - this.M_588_G) < 0.1) {
            O_1309_Q.J_1907_R = false;
            O_1309_Q.R_4764_Y = 0.0;
            O_1309_Q.G_564_y = 0.0;
        }
        this.P_4830_p = false;
        super.onDisable();
    }

    @Y_1740_V
    public void n_1700_B(h_1015_G e) {
        AttackAura attackAura;
        if (PearlLogger.c_3005_b.Y_601_j == null || PearlLogger.c_3005_b.Y_259_p == null) {
            return;
        }
        Projectile pearl = null;
        for (N_4263_v entity : PearlLogger.c_3005_b.Y_601_j.J_1907_R()) {
            if (!(entity instanceof w_2989_N)) continue;
            pearl = (w_2989_N)entity;
            break;
        }
        if (pearl == null) {
            this.t_148_a = true;
            this.s_956_w = null;
            return;
        }
        a_3913_L thrower = null;
        N_4263_v owner = pearl.Y_601_j();
        if (owner instanceof a_3913_L) {
            thrower = (a_3913_L)owner;
        } else {
            for (a_3913_L a_3913_L2 : PearlLogger.c_3005_b.Y_601_j.multiplayerClientSuggestionProvider()) {
                if (thrower != null && !(a_3913_L2.G_564_y(pearl) < thrower.G_564_y(pearl))) continue;
                thrower = a_3913_L2;
            }
        }
        if (thrower == null) {
            return;
        }
        if (thrower == PearlLogger.c_3005_b.Y_259_p && !this.reagirovatNaVasEnabled.isEnabled().booleanValue()) {
            return;
        }
        if (!this.t_148_a && thrower == this.s_956_w) {
            return;
        }
        e_2866_D landingPos = this.n_1700_B((w_2989_N)pearl);
        String string = String.valueOf((Object)D_4024_W.Q_4569_t) + String.valueOf((int)thrower.O_3598_v()) + " " + (int)thrower.X_2960_b() + " " + (int)thrower.l_2647_k();
        String toCoords = String.valueOf((Object)D_4024_W.h_1847_R) + String.valueOf((int)landingPos.n_1700_B()) + " " + (int)landingPos.J_1907_R() + " " + (int)landingPos.R_4764_Y();
        boolean isFriend = ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(thrower.O_1309_Q().getString());
        String nameColored = String.valueOf((Object)(isFriend ? D_4024_W.u_2550_I : D_4024_W.P_4830_p)) + thrower.O_1309_Q().getString();
        String message = nameColored + String.valueOf((Object)D_4024_W.M_182_A) + " \u043a\u0438\u043d\u0443\u043b \u043f\u0435\u0440\u043b \u0438\u0437 " + string + String.valueOf((Object)D_4024_W.M_182_A) + " \u0432 " + toCoords;
        if (this.t_148_a) {
            v_1900_v.n_1700_B(message, new Object[0]);
            this.t_148_a = false;
            this.s_956_w = thrower;
            if (this.avtoGpsDoPerlaEnabled.isEnabled().booleanValue() && (attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B) != null && attackAura.w_1484_f() && attackAura.h_1847_R() != null && thrower == attackAura.h_1847_R()) {
                this.u_2550_I = landingPos.n_1700_B();
                this.M_588_G = landingPos.R_4764_Y();
                O_1309_Q.J_1907_R = true;
                O_1309_Q.R_4764_Y = this.u_2550_I;
                O_1309_Q.G_564_y = this.M_588_G;
                this.P_4830_p = true;
            }
        }
        if (this.P_4830_p && this.avtoGpsDoPerlaEnabled.isEnabled().booleanValue() && O_1309_Q.J_1907_R) {
            attackAura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
            if (attackAura != null && attackAura.h_1847_R() != null && this.s_956_w != null && attackAura.h_1847_R() != this.s_956_w) {
                O_1309_Q.J_1907_R = false;
                O_1309_Q.R_4764_Y = 0.0;
                O_1309_Q.G_564_y = 0.0;
                this.P_4830_p = false;
                return;
            }
            double playerX = PearlLogger.c_3005_b.Y_259_p.O_3598_v();
            double playerZ = PearlLogger.c_3005_b.Y_259_p.l_2647_k();
            double distance = Math.sqrt(Math.pow(this.u_2550_I - playerX, 2.0) + Math.pow(this.M_588_G - playerZ, 2.0));
            if (distance <= 5.0) {
                O_1309_Q.J_1907_R = false;
                O_1309_Q.R_4764_Y = 0.0;
                O_1309_Q.G_564_y = 0.0;
                this.P_4830_p = false;
            }
        }
    }

    private e_2866_D n_1700_B(w_2989_N pearl) {
        e_2866_D pearlPosition = pearl.s_4990_V();
        e_2866_D pearlMotion = pearl.I_4348_c();
        e_2866_D lastPosition = pearlPosition;
        for (int i = 0; i <= 300; ++i) {
            lastPosition = pearlPosition;
            pearlPosition = pearlPosition.P_1922_E(pearlMotion);
            e_2866_D motionUpdated = pearlMotion;
            if (pearl.RowButton() || PearlLogger.c_3005_b.Y_601_j.getBlockState(new c_1514_x(pearlPosition)).J_1907_R() == a_3742_W.c_3005_b) {
                float scale = pearl instanceof w_2989_N ? 0.8f : 0.6f;
                motionUpdated = motionUpdated.n_1700_B((double)scale);
            } else {
                motionUpdated = motionUpdated.n_1700_B((double)0.99f);
            }
            if (!pearl.u_744_e()) {
                motionUpdated = motionUpdated.n_1700_B(0.0, pearl instanceof w_2989_N ? 0.03 : 0.05, 0.0);
            }
            pearlMotion = motionUpdated;
            ClipContext rayTraceContext = new ClipContext(lastPosition, pearlPosition, ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, PearlLogger.c_3005_b.Y_259_p);
            BlockHitResult blockHitResult = PearlLogger.c_3005_b.Y_601_j.n_1700_B(rayTraceContext);
            if (blockHitResult.R_4764_Y() != HitResult.n_1700_B.J_1907_R && !(pearlPosition.R_4764_Y <= 0.0)) continue;
            return blockHitResult.P_1922_E();
        }
        return lastPosition;
    }
}



