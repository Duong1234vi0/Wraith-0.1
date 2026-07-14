/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.x_282_a;

public class CommonComponents {
    public static final x_282_a n_1700_B = new F_2904_S("options.on");
    public static final x_282_a J_1907_R = new F_2904_S("options.off");
    public static final x_282_a R_4764_Y = new F_2904_S("gui.done");
    public static final x_282_a G_564_y = new F_2904_S("gui.cancel");
    public static final x_282_a P_1922_E = new F_2904_S("gui.yes");
    public static final x_282_a u_1723_Y = new F_2904_S("gui.no");
    public static final x_282_a v_4262_N = new F_2904_S("gui.proceed");
    public static final x_282_a w_1484_f = new F_2904_S("gui.back");
    public static final x_282_a t_148_a = new F_2904_S("connect.failed");

    public static x_282_a n_1700_B(boolean isEnabled) {
        return isEnabled ? n_1700_B : J_1907_R;
    }

    public static MutableComponent n_1700_B(x_282_a message, boolean composed) {
        return new F_2904_S(composed ? "options.on.composed" : "options.off.composed", message);
    }
}


