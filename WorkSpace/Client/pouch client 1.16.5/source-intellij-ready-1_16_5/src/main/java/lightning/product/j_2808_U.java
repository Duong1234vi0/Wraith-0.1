/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.List;
import lightning.product.A_2629_w;
import lightning.product.D_1624_i;
import lightning.product.M_712_N;
import lightning.product.Toast;
import lightning.product.SimpleSoundInstance;
import lightning.product.SoundEvents;
import lightning.product.W_4813_f;
import lightning.product.c_4037_x;
import lightning.product.FormattedCharSequence;
import lightning.product.g_221_o;
import lightning.product.u_530_F;

public class j_2808_U
implements Toast {
    private final A_2629_w R_4764_Y;
    private boolean G_564_y;

    public j_2808_U(A_2629_w advancementIn) {
        this.R_4764_Y = advancementIn;
    }

    @Override
    public Toast.n_1700_B func_230444_a_(g_221_o p_230444_1_, D_1624_i p_230444_2_, long p_230444_3_) {
        p_230444_2_.J_1907_R().G_624_v().n_1700_B(n_1700_B);
        c_4037_x.G_564_y(1.0f, 1.0f, 1.0f);
        M_712_N displayinfo = this.R_4764_Y.R_4764_Y();
        p_230444_2_.blit(p_230444_1_, 0, 0, 0, 0, this.J_1907_R(), this.R_4764_Y());
        if (displayinfo != null) {
            int i;
            List<FormattedCharSequence> list = p_230444_2_.J_1907_R().t_148_a.J_1907_R(displayinfo.n_1700_B(), 125);
            int n = i = displayinfo.P_1922_E() == W_4813_f.J_1907_R ? 0xFF88FF : 0xFFFF00;
            if (list.size() == 1) {
                p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, displayinfo.P_1922_E().G_564_y(), 30.0f, 7.0f, i | 0xFF000000);
                p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, list.get(0), 30.0f, 18.0f, -1);
            } else {
                int j = 1500;
                float f = 300.0f;
                if (p_230444_3_ < 1500L) {
                    int k = u_530_F.G_564_y(u_530_F.n_1700_B((float)(1500L - p_230444_3_) / 300.0f, 0.0f, 1.0f) * 255.0f) << 24 | 0x4000000;
                    p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, displayinfo.P_1922_E().G_564_y(), 30.0f, 11.0f, i | k);
                } else {
                    int i1 = u_530_F.G_564_y(u_530_F.n_1700_B((float)(p_230444_3_ - 1500L) / 300.0f, 0.0f, 1.0f) * 252.0f) << 24 | 0x4000000;
                    int l = this.R_4764_Y() / 2 - list.size() * 9 / 2;
                    for (FormattedCharSequence ireorderingprocessor : list) {
                        p_230444_2_.J_1907_R().t_148_a.J_1907_R(p_230444_1_, ireorderingprocessor, 30.0f, (float)l, 0xFFFFFF | i1);
                        l += 9;
                    }
                }
            }
            if (!this.G_564_y && p_230444_3_ > 0L) {
                this.G_564_y = true;
                if (displayinfo.P_1922_E() == W_4813_f.J_1907_R) {
                    p_230444_2_.J_1907_R().Z_976_R().n_1700_B(SimpleSoundInstance.n_1700_B(SoundEvents.o_869_X, 1.0f, 1.0f));
                }
            }
            p_230444_2_.J_1907_R().r_715_M().R_4764_Y(displayinfo.R_4764_Y(), 8, 8);
            return p_230444_3_ >= 5000L ? Toast.n_1700_B.J_1907_R : Toast.n_1700_B.n_1700_B;
        }
        return Toast.n_1700_B.J_1907_R;
    }
}


