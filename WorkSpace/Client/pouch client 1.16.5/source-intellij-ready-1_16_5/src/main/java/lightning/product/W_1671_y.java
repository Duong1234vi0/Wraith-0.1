/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.TutorialToast;
import lightning.product.I_14_v;
import lightning.product.HitResult;
import lightning.product.K_4074_S;
import lightning.product.Z_1993_T;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.k_4690_i;
import lightning.product.s_4082_G;
import lightning.product.t_4467_k;
import lightning.product.Input;
import lightning.product.TutorialStepInstance;
import lightning.product.x_282_a;

public class W_1671_y {
    private final MinecraftClient n_1700_B;
    @Nullable
    private TutorialStepInstance J_1907_R;
    private List<n_1700_B> R_4764_Y = Lists.newArrayList();

    public W_1671_y(MinecraftClient minecraft) {
        this.n_1700_B = minecraft;
    }

    public void n_1700_B(Input p_193293_1_) {
        if (this.J_1907_R != null) {
            this.J_1907_R.n_1700_B(p_193293_1_);
        }
    }

    public void n_1700_B(double velocityX, double velocityY) {
        if (this.J_1907_R != null) {
            this.J_1907_R.n_1700_B(velocityX, velocityY);
        }
    }

    public void n_1700_B(@Nullable k_4690_i worldIn, @Nullable HitResult result) {
        if (this.J_1907_R != null && result != null && worldIn != null) {
            this.J_1907_R.n_1700_B(worldIn, result);
        }
    }

    public void n_1700_B(k_4690_i worldIn, c_1514_x pos, K_4074_S state, float diggingStage) {
        if (this.J_1907_R != null) {
            this.J_1907_R.n_1700_B(worldIn, pos, state, diggingStage);
        }
    }

    public void n_1700_B() {
        if (this.J_1907_R != null) {
            this.J_1907_R.R_4764_Y();
        }
    }

    public void n_1700_B(Z_1993_T stack) {
        if (this.J_1907_R != null) {
            this.J_1907_R.n_1700_B(stack);
        }
    }

    public void J_1907_R() {
        if (this.J_1907_R != null) {
            this.J_1907_R.J_1907_R();
            this.J_1907_R = null;
        }
    }

    public void R_4764_Y() {
        if (this.J_1907_R != null) {
            this.J_1907_R();
        }
        this.J_1907_R = this.n_1700_B.P_4830_p.Y_1740_V.n_1700_B(this);
    }

    public void n_1700_B(TutorialToast p_244698_1_, int p_244698_2_) {
        this.R_4764_Y.add(new n_1700_B(p_244698_1_, p_244698_2_));
        this.n_1700_B.e_1992_r().n_1700_B(p_244698_1_);
    }

    public void n_1700_B(TutorialToast p_244697_1_) {
        this.R_4764_Y.removeIf(p_244699_1_ -> p_244699_1_.n_1700_B == p_244697_1_);
        p_244697_1_.G_564_y();
    }

    public void G_564_y() {
        this.R_4764_Y.removeIf(p_244700_0_ -> p_244700_0_.n_1700_B());
        if (this.J_1907_R != null) {
            if (this.n_1700_B.Y_601_j != null) {
                this.J_1907_R.n_1700_B();
            } else {
                this.J_1907_R();
            }
        } else if (this.n_1700_B.Y_601_j != null) {
            this.R_4764_Y();
        }
    }

    public void n_1700_B(t_4467_k step) {
        this.n_1700_B.P_4830_p.Y_1740_V = step;
        this.n_1700_B.P_4830_p.J_1907_R();
        if (this.J_1907_R != null) {
            this.J_1907_R.J_1907_R();
            this.J_1907_R = step.n_1700_B(this);
        }
    }

    public MinecraftClient P_1922_E() {
        return this.n_1700_B;
    }

    public I_14_v u_1723_Y() {
        return this.n_1700_B.w_1457_N == null ? I_14_v.n_1700_B : this.n_1700_B.w_1457_N.getCurrentGameType();
    }

    public static x_282_a n_1700_B(String keybind) {
        return new s_4082_G("key." + keybind).n_1700_B(D_4024_W.multiplayerClientSuggestionProvider);
    }

    static final class n_1700_B {
        private final TutorialToast n_1700_B;
        private final int J_1907_R;
        private int R_4764_Y;

        private n_1700_B(TutorialToast p_i242134_1_, int p_i242134_2_) {
            this.n_1700_B = p_i242134_1_;
            this.J_1907_R = p_i242134_2_;
        }

        private boolean n_1700_B() {
            this.n_1700_B.n_1700_B(Math.min((float)(++this.R_4764_Y) / (float)this.J_1907_R, 1.0f));
            if (this.R_4764_Y > this.J_1907_R) {
                this.n_1700_B.G_564_y();
                return true;
            }
            return false;
        }
    }
}



