/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.concurrent.ThreadLocalRandom;
import lightning.product.ServerboundInteractPacket;
import lightning.product.N_3268_u;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.Q_2753_H;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.d_2169_p;
import lightning.product.e_2866_D;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.ModeSetting;
import lightning.product.AttackAura;
import lightning.product.r_4811_B;
import lightning.product.Packet;
import lightning.product.u_530_F;
import lightning.product.ModuleCategory;

public class KBDisplacement
extends Module {
    private static final float v_4262_N = 0.15f;
    private static final float w_1484_f = 0.15f;
    private static final float t_148_a = 0.08f;
    private final ModeSetting napravlenieMode = new ModeSetting("\u041d\u0430\u043f\u0440\u0430\u0432\u043b\u0435\u043d\u0438\u0435", "\u0412\u043f\u0440\u0430\u0432\u043e", "\u041a\u043e \u043c\u043d\u0435", "\u0412\u043f\u0440\u0430\u0432\u043e", "\u0412\u043b\u0435\u0432\u043e", "\u0423\u043c\u043d\u044b\u0439");
    private final BooleanSetting tolkoSKillauroyEnabled = new BooleanSetting("\u0422\u043e\u043b\u044c\u043a\u043e \u0441 \u043a\u0438\u043b\u043b\u0430\u0443\u0440\u043e\u0439", true);

    public KBDisplacement() {
        super("KBDisplacement", ModuleCategory.n_1700_B);
        this.addSettings(this.napravlenieMode, this.tolkoSKillauroyEnabled);
    }

    private float n_1700_B(N_4263_v attackTarget) {
        String mode = (String)this.napravlenieMode.getValue();
        if ("\u0423\u043c\u043d\u044b\u0439".equals(mode)) {
            return this.J_1907_R(attackTarget);
        }
        return switch (mode) {
            case "\u041a\u043e \u043c\u043d\u0435" -> 180.0f;
            case "\u0412\u043f\u0440\u0430\u0432\u043e" -> 90.0f;
            case "\u0412\u043b\u0435\u0432\u043e" -> -90.0f;
            default -> 0.0f;
        };
    }

    private float J_1907_R(N_4263_v attackTarget) {
        if (attackTarget == null || !(attackTarget instanceof r_4811_B)) {
            return this.h_1847_R();
        }
        r_4811_B target = (r_4811_B)attackTarget;
        e_2866_D toPlayer = KBDisplacement.c_3005_b.Y_259_p.s_4990_V().G_564_y(target.s_4990_V());
        double dist = toPlayer.u_1723_Y();
        if (dist < 0.01) {
            return this.h_1847_R();
        }
        toPlayer = toPlayer.G_564_y();
        e_2866_D vel = target.I_4348_c();
        double velLen = vel.u_1723_Y();
        if (velLen < (double)0.08f) {
            return this.h_1847_R();
        }
        double dot = vel.J_1907_R * toPlayer.J_1907_R + vel.R_4764_Y * toPlayer.R_4764_Y + vel.G_564_y * toPlayer.G_564_y;
        if (dot < (double)-0.15f) {
            return 180.0f;
        }
        if (dot > (double)0.15f) {
            return ThreadLocalRandom.current().nextBoolean() ? 90.0f : -90.0f;
        }
        return this.h_1847_R();
    }

    private float h_1847_R() {
        float[] options = new float[]{180.0f, 90.0f, -90.0f};
        return options[ThreadLocalRandom.current().nextInt(options.length)];
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H e) {
        ServerboundInteractPacket packet;
        if (!e.R_4764_Y() || KBDisplacement.c_3005_b.Y_259_p == null || KBDisplacement.c_3005_b.Y_601_j == null) {
            return;
        }
        Packet<?> t_3138_Z2 = e.G_564_y();
        if (t_3138_Z2 instanceof ServerboundInteractPacket && (packet = (ServerboundInteractPacket)t_3138_Z2).J_1907_R() == ServerboundInteractPacket.n_1700_B.J_1907_R) {
            AttackAura aura = ClientBootstrap.Y_601_j().J_1907_R().n_1700_B;
            P_3504_Q rot = this.n_1700_B(aura);
            if (rot == null) {
                return;
            }
            N_4263_v target = packet.n_1700_B(KBDisplacement.c_3005_b.Y_601_j);
            float offsetYaw = u_530_F.v_4262_N(rot.t_148_a + this.n_1700_B(target));
            KBDisplacement.c_3005_b.Y_259_p.n_1700_B.n_1700_B(new N_3268_u.J_1907_R(KBDisplacement.c_3005_b.Y_259_p.O_3598_v(), KBDisplacement.c_3005_b.Y_259_p.X_2960_b(), KBDisplacement.c_3005_b.Y_259_p.l_2647_k(), offsetYaw, rot.s_956_w, KBDisplacement.c_3005_b.Y_259_p.M_1641_O()));
        }
    }

    private P_3504_Q n_1700_B(AttackAura aura) {
        if (this.tolkoSKillauroyEnabled.isEnabled().booleanValue()) {
            if (aura == null || !aura.w_1484_f()) {
                return null;
            }
            P_3504_Q lastrot = aura.t_1786_h();
            if (lastrot != null) {
                return lastrot;
            }
            if (d_2169_p.n_1700_B()) {
                return new P_3504_Q(d_2169_p.J_1907_R(), d_2169_p.R_4764_Y());
            }
            return null;
        }
        if (aura != null && aura.w_1484_f()) {
            P_3504_Q lastrot = aura.t_1786_h();
            if (lastrot != null) {
                return lastrot;
            }
            if (d_2169_p.n_1700_B()) {
                return new P_3504_Q(d_2169_p.J_1907_R(), d_2169_p.R_4764_Y());
            }
        }
        return new P_3504_Q(KBDisplacement.c_3005_b.Y_259_p.p_178_J, KBDisplacement.c_3005_b.Y_259_p.f_4016_n);
    }
}



