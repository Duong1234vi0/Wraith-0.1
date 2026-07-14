/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.A_4390_i;
import lightning.product.B_3217_H;
import lightning.product.D_38_f;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.N_4263_v;
import lightning.product.U_2912_j;
import lightning.product.AbstractFish;
import lightning.product.SoundEvents;
import lightning.product.Z_1993_T;
import lightning.product.a_3160_D;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.e_3591_l;
import lightning.product.g_3316_o;
import lightning.product.q_1613_l;
import lightning.product.Fluid;
import lightning.product.LevelAccessor;
import lightning.product.t_5_h;
import lightning.product.x_282_a;

public class S_3045_l
extends B_3217_H {
    private final t_5_h<?> n_1700_B;

    public S_3045_l(t_5_h<?> fishTypeIn, Fluid p_i49022_2_, q_1613_l.n_1700_B builder) {
        super(p_i49022_2_, builder);
        this.n_1700_B = fishTypeIn;
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, Z_1993_T p_203792_2_, c_1514_x pos) {
        if (worldIn instanceof e_3591_l) {
            this.n_1700_B((e_3591_l)worldIn, p_203792_2_, pos);
        }
    }

    @Override
    protected void n_1700_B(@Nullable a_3913_L player, LevelAccessor worldIn, c_1514_x pos) {
        worldIn.n_1700_B(player, pos, SoundEvents.RealmsLongConfirmationScreen, D_38_f.v_4262_N, 1.0f, 1.0f);
    }

    private void n_1700_B(e_3591_l worldIn, Z_1993_T p_205357_2_, c_1514_x pos) {
        N_4263_v entity = this.n_1700_B.n_1700_B(worldIn, p_205357_2_, null, pos, a_3160_D.M_588_G, true, false);
        if (entity != null) {
            ((AbstractFish)entity).w_1457_N(true);
        }
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        U_2912_j compoundnbt;
        if (this.n_1700_B == t_5_h.S_4022_R && (compoundnbt = stack.Q_4569_t()) != null && compoundnbt.R_4764_Y("BucketVariantTag", 3)) {
            int i = compoundnbt.w_1484_f("BucketVariantTag");
            D_4024_W[] atextformatting = new D_4024_W[]{D_4024_W.Y_259_p, D_4024_W.w_1484_f};
            String s = "color.minecraft." + String.valueOf(A_4390_i.w_1457_N(i));
            String s1 = "color.minecraft." + String.valueOf(A_4390_i.Y_601_j(i));
            for (int j = 0; j < A_4390_i.n_1700_B.length; ++j) {
                if (i != A_4390_i.n_1700_B[j]) continue;
                tooltip.add(new F_2904_S(A_4390_i.J_1907_R(j)).n_1700_B(atextformatting));
                return;
            }
            tooltip.add(new F_2904_S(A_4390_i.Y_259_p(i)).n_1700_B(atextformatting));
            F_2904_S iformattabletextcomponent = new F_2904_S(s);
            if (!s.equals(s1)) {
                iformattabletextcomponent.n_1700_B(", ").n_1700_B(new F_2904_S(s1));
            }
            iformattabletextcomponent.n_1700_B(atextformatting);
            tooltip.add(iformattabletextcomponent);
        }
    }
}


