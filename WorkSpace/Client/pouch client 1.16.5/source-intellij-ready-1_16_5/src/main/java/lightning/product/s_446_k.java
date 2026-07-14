/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.UUID;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.Y_408_h;
import lightning.product.Globals;
import lightning.product.MinecraftClient;
import lightning.product.u_273_N;
import lightning.product.x_282_a;

public class s_446_k
implements u_273_N {
    private final MinecraftClient n_1700_B;

    public s_446_k(MinecraftClient minecraft) {
        this.n_1700_B = minecraft;
    }

    @Override
    public void n_1700_B(Y_408_h chatTypeIn, x_282_a message, UUID sender) {
        Globals globals;
        if (chatTypeIn == Y_408_h.n_1700_B && sender != null && this.n_1700_B.Y_601_j != null && (globals = Globals.h_1847_R()) != null && globals.w_1484_f() && globals.n_1700_B(sender)) {
            if (message instanceof F_2904_S) {
                Object[] originalArgs;
                F_2904_S t = (F_2904_S)message;
                if ("chat.type.text".equals(t.w_1484_f()) && (originalArgs = t.s_956_w()).length > 0 && originalArgs[0] instanceof x_282_a) {
                    x_282_a nameComponent = (x_282_a)originalArgs[0];
                    MutableComponent newName = new U_2871_b(nameComponent.getString() + " pouch").n_1700_B(nameComponent.n_1700_B());
                    Object[] newArgs = new Object[originalArgs.length];
                    System.arraycopy(originalArgs, 0, newArgs, 0, originalArgs.length);
                    newArgs[0] = newName;
                    message = new F_2904_S(t.w_1484_f(), newArgs).n_1700_B(t.n_1700_B());
                }
            } else {
                message = message.P_1922_E().n_1700_B(" pouch");
            }
        }
        if (chatTypeIn != Y_408_h.n_1700_B) {
            this.n_1700_B.M_588_G.R_4764_Y().n_1700_B(message);
        } else {
            this.n_1700_B.M_588_G.R_4764_Y().J_1907_R(message);
        }
    }
}



