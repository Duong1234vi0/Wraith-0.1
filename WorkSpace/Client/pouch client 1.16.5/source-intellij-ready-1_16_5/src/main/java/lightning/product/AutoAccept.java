/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Arrays;
import lightning.product.Q_2753_H;
import lightning.product.Module;
import lightning.product.Y_1740_V;
import lightning.product.NameProtect;
import lightning.product.ClientboundChatPacket;
import lightning.product.n_473_l;
import lightning.product.ClientBootstrap;
import lightning.product.BooleanSetting;
import lightning.product.q_3115_L;
import lightning.product.Packet;
import lightning.product.ModuleCategory;

public class AutoAccept
extends Module {
    private static String[] v_4262_N = new String[]{"\u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f", "has requested teleport", "\u043f\u0440\u043e\u0441\u0438\u0442 \u043a \u0432\u0430\u043c \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442\u0438\u0440\u043e\u0432\u0430\u0442\u044c\u0441\u044f", "\u0437\u0430\u043f\u0440\u0430\u0448\u0438\u0432\u0430\u0435\u0442 \u0442\u0435\u043b\u0435\u043f\u043e\u0440\u0442 \u043a \u0432\u0430\u043c"};
    private final BooleanSetting prinimatZaprosyTolkoOtDruzeyEnabled = new BooleanSetting("\u041f\u0440\u0438\u043d\u0438\u043c\u0430\u0442\u044c \u0437\u0430\u043f\u0440\u043e\u0441\u044b \u0442\u043e\u043b\u044c\u043a\u043e \u043e\u0442 \u0434\u0440\u0443\u0437\u0435\u0439", true);

    public AutoAccept() {
        super("AutoAccept", ModuleCategory.P_1922_E);
        this.addSettings(this.prinimatZaprosyTolkoOtDruzeyEnabled);
    }

    @Y_1740_V
    public void n_1700_B(Q_2753_H event) {
        Packet<?> t_3138_Z2;
        if (AutoAccept.c_3005_b.Y_259_p == null || AutoAccept.c_3005_b.Y_601_j == null || q_3115_L.n_1700_B() || !((t_3138_Z2 = event.G_564_y()) instanceof ClientboundChatPacket)) {
            return;
        }
        ClientboundChatPacket chatPacket = (ClientboundChatPacket)t_3138_Z2;
        String message = chatPacket.J_1907_R().getString().toLowerCase();
        if (Arrays.stream(v_4262_N).anyMatch(message::contains)) {
            if (this.prinimatZaprosyTolkoOtDruzeyEnabled.isEnabled().booleanValue() && !this.R_4764_Y(message)) {
                return;
            }
            AutoAccept.c_3005_b.Y_259_p.n_1700_B("/tpaccept");
        }
    }

    private boolean R_4764_Y(String message) {
        NameProtect nameProtect = (NameProtect)ClientBootstrap.Y_601_j().J_1907_R().n_1700_B(NameProtect.class);
        if (nameProtect.w_1484_f() && NameProtect.v_4262_N.t_148_a().booleanValue() && message.contains("protected")) {
            return true;
        }
        for (n_473_l.n_1700_B friend : ClientBootstrap.Y_601_j().v_4262_N().P_4830_p()) {
            String friendName = friend.n_1700_B();
            if (!message.contains(friendName.toLowerCase())) continue;
            return true;
        }
        return false;
    }
}



