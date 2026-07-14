/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import javax.annotation.Nullable;
import lightning.product.C_2701_A;
import lightning.product.D_1624_i;
import lightning.product.Toast;
import lightning.product.c_4037_x;
import lightning.product.g_221_o;
import lightning.product.u_530_F;
import lightning.product.x_282_a;

public class TutorialToast
implements Toast {
    private final n_1700_B R_4764_Y;
    private final x_282_a G_564_y;
    private final x_282_a P_1922_E;
    private Toast.n_1700_B u_1723_Y = Toast.n_1700_B.n_1700_B;
    private long v_4262_N;
    private float w_1484_f;
    private float t_148_a;
    private final boolean s_956_w;

    public TutorialToast(n_1700_B iconIn, x_282_a titleComponent, @Nullable x_282_a subtitleComponent, boolean drawProgressBar) {
        this.R_4764_Y = iconIn;
        this.G_564_y = titleComponent;
        this.P_1922_E = subtitleComponent;
        this.s_956_w = drawProgressBar;
    }

    @Override
    public Toast.n_1700_B func_230444_a_(g_221_o p_230444_1_, D_1624_i p_230444_2_, long p_230444_3_) {
        p_230444_2_.J_1907_R().G_624_v().n_1700_B(n_1700_B);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f);
        p_230444_2_.blit(p_230444_1_, 0, 0, 0, 96, this.J_1907_R(), this.R_4764_Y());
        this.R_4764_Y.n_1700_B(p_230444_1_, p_230444_2_, 6, 6);
        if (this.P_1922_E == null) {
            p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, this.G_564_y, 30.0f, 12.0f, -11534256);
        } else {
            p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, this.G_564_y, 30.0f, 7.0f, -11534256);
            p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, this.P_1922_E, 30.0f, 18.0f, -16777216);
        }
        if (this.s_956_w) {
            C_2701_A.fill(p_230444_1_, 3, 28, 157, 29, -1);
            float f = (float)u_530_F.J_1907_R((double)this.w_1484_f, (double)this.t_148_a, (double)((float)(p_230444_3_ - this.v_4262_N) / 100.0f));
            int i = this.t_148_a >= this.w_1484_f ? -16755456 : -11206656;
            C_2701_A.fill(p_230444_1_, 3, 28, (int)(3.0f + 154.0f * f), 29, i);
            this.w_1484_f = f;
            this.v_4262_N = p_230444_3_;
        }
        return this.u_1723_Y;
    }

    public void G_564_y() {
        this.u_1723_Y = Toast.n_1700_B.J_1907_R;
    }

    public void n_1700_B(float progress) {
        this.t_148_a = progress;
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(0, 0);
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(1, 0);
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(2, 0);
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(0, 1);
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B(1, 1);
        public static final /* enum */ n_1700_B u_1723_Y = new n_1700_B(2, 1);
        private final int v_4262_N;
        private final int w_1484_f;
        private static final /* synthetic */ n_1700_B[] t_148_a;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_148_a.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(int columnIn, int rowIn) {
            this.v_4262_N = columnIn;
            this.w_1484_f = rowIn;
        }

        public void n_1700_B(g_221_o p_238543_1_, C_2701_A p_238543_2_, int p_238543_3_, int p_238543_4_) {
            c_4037_x.Y_601_j();
            p_238543_2_.blit(p_238543_1_, p_238543_3_, p_238543_4_, 176 + this.v_4262_N * 20, this.w_1484_f * 20, 20, 20);
            c_4037_x.Y_601_j();
        }

        private static /* synthetic */ n_1700_B[] n_1700_B() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E, u_1723_Y};
        }

        static {
            t_148_a = lightning.product.TutorialToast$n_1700_B.n_1700_B();
        }
    }
}


