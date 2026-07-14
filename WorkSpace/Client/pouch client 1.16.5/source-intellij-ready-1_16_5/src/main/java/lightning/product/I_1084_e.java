/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.text2speech.Narrator
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package lightning.product;

import com.mojang.text2speech.Narrator;
import java.util.UUID;
import lightning.product.D_1624_i;
import lightning.product.F_2904_S;
import lightning.product.SharedConstants;
import lightning.product.SystemToast;
import lightning.product.U_2871_b;
import lightning.product.Y_408_h;
import lightning.product.MinecraftClient;
import lightning.product.e_3022_i;
import lightning.product.u_273_N;
import lightning.product.x_282_a;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class I_1084_e
implements u_273_N {
    public static final x_282_a n_1700_B = U_2871_b.R_4764_Y;
    private static final Logger R_4764_Y = LogManager.getLogger();
    public static final I_1084_e J_1907_R = new I_1084_e();
    private final Narrator G_564_y = Narrator.getNarrator();

    @Override
    public void n_1700_B(Y_408_h chatTypeIn, x_282_a message, UUID sender) {
        e_3022_i narratorstatus = I_1084_e.G_564_y();
        if (narratorstatus != e_3022_i.n_1700_B && this.G_564_y.active() && (narratorstatus == e_3022_i.J_1907_R || narratorstatus == e_3022_i.R_4764_Y && chatTypeIn == Y_408_h.n_1700_B || narratorstatus == e_3022_i.G_564_y && chatTypeIn == Y_408_h.J_1907_R)) {
            x_282_a itextcomponent = message instanceof F_2904_S && "chat.type.text".equals(((F_2904_S)message).w_1484_f()) ? new F_2904_S("chat.type.text.narrate", ((F_2904_S)message).s_956_w()) : message;
            this.n_1700_B(chatTypeIn.J_1907_R(), itextcomponent.getString());
        }
    }

    public void n_1700_B(String msg) {
        e_3022_i narratorstatus = I_1084_e.G_564_y();
        if (this.G_564_y.active() && narratorstatus != e_3022_i.n_1700_B && narratorstatus != e_3022_i.R_4764_Y && !msg.isEmpty()) {
            this.G_564_y.clear();
            this.n_1700_B(true, msg);
        }
    }

    private static e_3022_i G_564_y() {
        return MinecraftClient.A_4115_X().P_4830_p.W_3464_O;
    }

    private void n_1700_B(boolean interrupt, String msg) {
        if (SharedConstants.G_564_y) {
            R_4764_Y.debug("Narrating: {}", (Object)msg.replaceAll("\n", "\\\\n"));
        }
        this.G_564_y.say(msg, interrupt);
    }

    public void n_1700_B(e_3022_i status) {
        this.J_1907_R();
        this.G_564_y.say(new F_2904_S("options.narrator").n_1700_B(" : ").n_1700_B(status.J_1907_R()).getString(), true);
        D_1624_i toastgui = MinecraftClient.A_4115_X().e_1992_r();
        if (this.G_564_y.active()) {
            if (status == e_3022_i.n_1700_B) {
                SystemToast.J_1907_R(toastgui, SystemToast.n_1700_B.J_1907_R, new F_2904_S("narrator.toast.disabled"), null);
            } else {
                SystemToast.J_1907_R(toastgui, SystemToast.n_1700_B.J_1907_R, new F_2904_S("narrator.toast.enabled"), status.J_1907_R());
            }
        } else {
            SystemToast.J_1907_R(toastgui, SystemToast.n_1700_B.J_1907_R, new F_2904_S("narrator.toast.disabled"), new F_2904_S("options.narrator.notavailable"));
        }
    }

    public boolean n_1700_B() {
        return this.G_564_y.active();
    }

    public void J_1907_R() {
        if (I_1084_e.G_564_y() != e_3022_i.n_1700_B && this.G_564_y.active()) {
            this.G_564_y.clear();
        }
    }

    public void R_4764_Y() {
        this.G_564_y.destroy();
    }
}



