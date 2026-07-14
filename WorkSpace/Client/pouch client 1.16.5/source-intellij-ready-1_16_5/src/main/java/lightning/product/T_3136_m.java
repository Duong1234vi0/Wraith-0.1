/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.MutableComponent;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.e_933_M;
import lightning.product.g_3316_o;
import lightning.product.q_1613_l;
import lightning.product.FireworkRocketItem;
import lightning.product.x_282_a;

public class T_3136_m
extends q_1613_l {
    public T_3136_m(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        U_2912_j compoundnbt = stack.J_1907_R("Explosion");
        if (compoundnbt != null) {
            T_3136_m.n_1700_B(compoundnbt, tooltip);
        }
    }

    public static void n_1700_B(U_2912_j compound, List<x_282_a> tooltip) {
        int[] aint1;
        FireworkRocketItem.n_1700_B fireworkrocketitem$shape = FireworkRocketItem.n_1700_B.n_1700_B(compound.u_1723_Y("Type"));
        tooltip.add(new F_2904_S("item.minecraft.firework_star.shape." + fireworkrocketitem$shape.J_1907_R()).n_1700_B(D_4024_W.w_1484_f));
        int[] aint = compound.h_1847_R("Colors");
        if (aint.length > 0) {
            tooltip.add(T_3136_m.n_1700_B(new U_2871_b("").n_1700_B(D_4024_W.w_1484_f), aint));
        }
        if ((aint1 = compound.h_1847_R("FadeColors")).length > 0) {
            tooltip.add(T_3136_m.n_1700_B(new F_2904_S("item.minecraft.firework_star.fade_to").n_1700_B(" ").n_1700_B(D_4024_W.w_1484_f), aint1));
        }
        if (compound.t_1786_h("Trail")) {
            tooltip.add(new F_2904_S("item.minecraft.firework_star.trail").n_1700_B(D_4024_W.w_1484_f));
        }
        if (compound.t_1786_h("Flicker")) {
            tooltip.add(new F_2904_S("item.minecraft.firework_star.flicker").n_1700_B(D_4024_W.w_1484_f));
        }
    }

    private static x_282_a n_1700_B(MutableComponent p_200298_0_, int[] p_200298_1_) {
        for (int i = 0; i < p_200298_1_.length; ++i) {
            if (i > 0) {
                p_200298_0_.n_1700_B(", ");
            }
            p_200298_0_.n_1700_B(T_3136_m.n_1700_B(p_200298_1_[i]));
        }
        return p_200298_0_;
    }

    private static x_282_a n_1700_B(int p_200297_0_) {
        e_933_M dyecolor = e_933_M.J_1907_R(p_200297_0_);
        return dyecolor == null ? new F_2904_S("item.minecraft.firework_star.custom_color") : new F_2904_S("item.minecraft.firework_star." + dyecolor.R_4764_Y());
    }
}


