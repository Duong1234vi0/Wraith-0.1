/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.N_4263_v;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.a_3913_L;
import lightning.product.h_2739_B;
import lightning.product.ClientBootstrap;
import lightning.product.AttackAura;
import lightning.product.ModuleCategory;

public class NoFriendDamage
extends Module {
    public NoFriendDamage() {
        super("NoFriendDamage", ModuleCategory.n_1700_B);
    }

    @Y_1740_V
    public void n_1700_B(h_2739_B e) {
        AttackAura attackAura = (AttackAura)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(AttackAura.class);
        N_4263_v n_4263_v = e.J_1907_R();
        if (n_4263_v instanceof a_3913_L) {
            a_3913_L player = (a_3913_L)n_4263_v;
            if (ClientBootstrap.Y_601_j().v_4262_N().R_4764_Y(player.y_4642_Y().getName()) && (!attackAura.w_1484_f() || attackAura.v_4262_N != e.J_1907_R())) {
                e.n_1700_B(true);
            }
        }
    }
}


