/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.D_1624_i;
import lightning.product.SimpleSoundInstance;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.g_221_o;
import lightning.product.g_2336_b;
import lightning.product.k_4218_M;

public interface Toast {
    public static final g_2336_b n_1700_B = new g_2336_b("textures/gui/toasts.png");
    public static final Object J_1907_R = new Object();

    public n_1700_B func_230444_a_(g_221_o var1, D_1624_i var2, long var3);

    default public Object n_1700_B() {
        return J_1907_R;
    }

    default public int J_1907_R() {
        return 160;
    }

    default public int R_4764_Y() {
        return 32;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(SoundEvents.o_3946_o);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(SoundEvents.BonemealableBlock);
        private final SoundEvent R_4764_Y;
        private static final /* synthetic */ n_1700_B[] G_564_y;

        public static n_1700_B[] values() {
            return (n_1700_B[])G_564_y.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(SoundEvent soundIn) {
            this.R_4764_Y = soundIn;
        }

        public void n_1700_B(k_4218_M handler) {
            handler.n_1700_B(SimpleSoundInstance.n_1700_B(this.R_4764_Y, 1.0f, 1.0f));
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R};
        }

        static {
            G_564_y = lightning.product.Toast$n_1700_B.n_1700_B();
        }
    }
}


