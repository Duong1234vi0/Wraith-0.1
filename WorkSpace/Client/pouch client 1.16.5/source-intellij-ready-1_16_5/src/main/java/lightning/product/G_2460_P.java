/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.I_1084_e;
import lightning.product.MinecraftClient;
import lightning.product.g_221_o;
import lightning.product.k_2603_m;
import lightning.product.x_282_a;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;

public class G_2460_P
extends k_2603_m {
    private static final x_282_a n_1700_B = new F_2904_S("multiplayer.downloadingTerrain");
    private static final long J_1907_R = 2500000000L;
    private CustomLoadingScreen R_4764_Y = CustomLoadingScreens.getCustomLoadingScreen();
    private long G_564_y;
    private boolean P_1922_E;

    public G_2460_P() {
        super(I_1084_e.n_1700_B);
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @Override
    public void render(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.n_1700_B();
        if (this.R_4764_Y != null) {
            this.R_4764_Y.drawBackground(this.width, this.height);
        } else {
            this.renderDirtBackground(0);
        }
        G_2460_P.drawCenteredString(matrixStack, this.font, n_1700_B, this.width / 2, this.height / 2 - 50, 0xFFFFFF);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        this.n_1700_B();
    }

    private void n_1700_B() {
        if (this.P_1922_E) {
            return;
        }
        MinecraftClient mc = MinecraftClient.A_4115_X();
        if (mc.Y_601_j == null || mc.Y_259_p == null) {
            return;
        }
        long now = System.nanoTime();
        if (this.G_564_y == 0L) {
            this.G_564_y = now;
        }
        if (now - this.G_564_y < 2500000000L) {
            return;
        }
        mc.Y_259_p.n_1700_B.G_564_y();
        this.P_1922_E = true;
    }
}


