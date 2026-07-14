/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.Button;
import lightning.product.MinecraftClient;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;

public class LockIconButton
extends Button {
    private boolean n_1700_B;

    public LockIconButton(int x, int y, Button.n_1700_B p_i51133_3_) {
        super(x, y, 20, 20, new F_2904_S("narrator.button.difficulty_lock"), p_i51133_3_);
    }

    @Override
    protected MutableComponent getNarrationMessage() {
        return super.getNarrationMessage().n_1700_B(". ").n_1700_B(this.n_1700_B() ? new F_2904_S("narrator.button.difficulty_lock.locked") : new F_2904_S("narrator.button.difficulty_lock.unlocked"));
    }

    public boolean n_1700_B() {
        return this.n_1700_B;
    }

    public void n_1700_B(boolean lockedIn) {
        this.n_1700_B = lockedIn;
    }

    @Override
    public void renderButton(g_221_o matrixStack, int mouseX, int mouseY, float partialTicks) {
        MinecraftClient.A_4115_X().G_624_v().n_1700_B(Button.WIDGETS_LOCATION);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f, 1.0f);
        n_1700_B lockiconbutton$icon = !this.active ? (this.n_1700_B ? lightning.product.LockIconButton$n_1700_B.R_4764_Y : lightning.product.LockIconButton$n_1700_B.u_1723_Y) : (this.isHovered() ? (this.n_1700_B ? lightning.product.LockIconButton$n_1700_B.J_1907_R : lightning.product.LockIconButton$n_1700_B.P_1922_E) : (this.n_1700_B ? lightning.product.LockIconButton$n_1700_B.n_1700_B : lightning.product.LockIconButton$n_1700_B.G_564_y));
        this.blit(matrixStack, this.x, this.y, lockiconbutton$icon.n_1700_B(), lockiconbutton$icon.J_1907_R(), this.width, this.height);
    }

    static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(0, 146);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(0, 166);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(0, 186);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(20, 146);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B(20, 166);
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B(20, 186);
        private final int v_4262_N;
        private final int w_1484_f;
        private static final /* synthetic */ n_1700_B[] t_148_a;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_148_a.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(int xIn, int yIn) {
            this.v_4262_N = xIn;
            this.w_1484_f = yIn;
        }

        public int n_1700_B() {
            return this.v_4262_N;
        }

        public int J_1907_R() {
            return this.w_1484_f;
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            t_148_a = lightning.product.LockIconButton$n_1700_B.R_4764_Y();
        }
    }
}



