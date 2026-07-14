/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.B_4756_G;
import lightning.product.MinecraftClient;
import lightning.product.u_530_F;

public class PanoramaRenderer {
    private final MinecraftClient n_1700_B;
    private final B_4756_G J_1907_R;
    private float R_4764_Y;

    public PanoramaRenderer(B_4756_G rendererIn) {
        this.J_1907_R = rendererIn;
        this.n_1700_B = MinecraftClient.A_4115_X();
    }

    public void n_1700_B(float deltaT, float alpha) {
        this.R_4764_Y += deltaT;
        this.J_1907_R.n_1700_B(this.n_1700_B, u_530_F.n_1700_B(this.R_4764_Y * 0.001f) * 5.0f + 25.0f, -this.R_4764_Y * 0.1f, alpha);
    }
}



