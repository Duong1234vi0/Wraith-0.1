/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.N_1091_Y;
import lightning.product.U_679_Y;
import lightning.product.MinecraftClient;
import lightning.product.q_570_v;
import lightning.product.w_4886_q;

public final class VirtualScreen
implements AutoCloseable {
    private final MinecraftClient n_1700_B;
    private final w_4886_q J_1907_R;

    public VirtualScreen(MinecraftClient mcIn) {
        this.n_1700_B = mcIn;
        this.J_1907_R = new w_4886_q(N_1091_Y::new);
    }

    public U_679_Y n_1700_B(q_570_v screenSizeIn, @Nullable String videoModeName, String titleIn) {
        return new U_679_Y(this.n_1700_B, this.J_1907_R, screenSizeIn, videoModeName, titleIn);
    }

    @Override
    public void close() {
        this.J_1907_R.n_1700_B();
    }
}



