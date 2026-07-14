/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.UUID;
import lightning.product.Y_408_h;
import lightning.product.MinecraftClient;
import lightning.product.u_273_N;
import lightning.product.x_282_a;

public class OverlayChatListener
implements u_273_N {
    private final MinecraftClient n_1700_B;

    public OverlayChatListener(MinecraftClient minecraftIn) {
        this.n_1700_B = minecraftIn;
    }

    @Override
    public void n_1700_B(Y_408_h chatTypeIn, x_282_a message, UUID sender) {
        this.n_1700_B.M_588_G.n_1700_B(message, false);
    }
}



