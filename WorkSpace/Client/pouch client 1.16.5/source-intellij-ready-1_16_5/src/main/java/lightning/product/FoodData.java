/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.A_2352_Z;
import lightning.product.FoodProperties;
import lightning.product.P_11_z;
import lightning.product.R_2450_T;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.q_1613_l;

public class FoodData {
    private int n_1700_B = 20;
    private float J_1907_R = 5.0f;
    private float R_4764_Y;
    private int G_564_y;
    private int P_1922_E = 20;

    public void n_1700_B(int foodLevelIn, float foodSaturationModifier) {
        this.n_1700_B = Math.min(foodLevelIn + this.n_1700_B, 20);
        this.J_1907_R = Math.min(this.J_1907_R + (float)foodLevelIn * foodSaturationModifier * 2.0f, (float)this.n_1700_B);
    }

    public void n_1700_B(q_1613_l maybeFood, Z_1993_T stack) {
        if (maybeFood.Y_259_p()) {
            FoodProperties food = maybeFood.Q_2552_b();
            this.n_1700_B(food.n_1700_B(), food.J_1907_R());
        }
    }

    public void n_1700_B(a_3913_L player) {
        boolean flag;
        R_2450_T difficulty = player.O_508_d.x_607_J();
        this.P_1922_E = this.n_1700_B;
        if (this.R_4764_Y > 4.0f) {
            this.R_4764_Y -= 4.0f;
            if (this.J_1907_R > 0.0f) {
                this.J_1907_R = Math.max(this.J_1907_R - 1.0f, 0.0f);
            } else if (difficulty != R_2450_T.n_1700_B) {
                this.n_1700_B = Math.max(this.n_1700_B - 1, 0);
            }
        }
        if ((flag = player.O_508_d.H_1990_U().J_1907_R(A_2352_Z.t_148_a)) && this.J_1907_R > 0.0f && player.U_1697_c() && this.n_1700_B >= 20) {
            ++this.G_564_y;
            if (this.G_564_y >= 10) {
                float f = Math.min(this.J_1907_R, 6.0f);
                player.n_1700_B(f / 6.0f);
                this.n_1700_B(f);
                this.G_564_y = 0;
            }
        } else if (flag && this.n_1700_B >= 18 && player.U_1697_c()) {
            ++this.G_564_y;
            if (this.G_564_y >= 80) {
                player.n_1700_B(1.0f);
                this.n_1700_B(6.0f);
                this.G_564_y = 0;
            }
        } else if (this.n_1700_B <= 0) {
            ++this.G_564_y;
            if (this.G_564_y >= 80) {
                if (player.g_46_E() > 10.0f || difficulty == R_2450_T.G_564_y || player.g_46_E() > 1.0f && difficulty == R_2450_T.R_4764_Y) {
                    player.n_1700_B(P_11_z.t_148_a, 1.0f);
                }
                this.G_564_y = 0;
            }
        } else {
            this.G_564_y = 0;
        }
    }

    public void n_1700_B(U_2912_j compound) {
        if (compound.R_4764_Y("foodLevel", 99)) {
            this.n_1700_B = compound.w_1484_f("foodLevel");
            this.G_564_y = compound.w_1484_f("foodTickTimer");
            this.J_1907_R = compound.s_956_w("foodSaturationLevel");
            this.R_4764_Y = compound.s_956_w("foodExhaustionLevel");
        }
    }

    public void J_1907_R(U_2912_j compound) {
        compound.J_1907_R("foodLevel", this.n_1700_B);
        compound.J_1907_R("foodTickTimer", this.G_564_y);
        compound.n_1700_B("foodSaturationLevel", this.J_1907_R);
        compound.n_1700_B("foodExhaustionLevel", this.R_4764_Y);
    }

    public int n_1700_B() {
        return this.n_1700_B;
    }

    public boolean J_1907_R() {
        return this.n_1700_B < 20;
    }

    public void n_1700_B(float exhaustion) {
        this.R_4764_Y = Math.min(this.R_4764_Y + exhaustion, 40.0f);
    }

    public float R_4764_Y() {
        return this.J_1907_R;
    }

    public void n_1700_B(int foodLevelIn) {
        this.n_1700_B = foodLevelIn;
    }

    public void J_1907_R(float foodSaturationLevelIn) {
        this.J_1907_R = foodSaturationLevelIn;
    }
}


