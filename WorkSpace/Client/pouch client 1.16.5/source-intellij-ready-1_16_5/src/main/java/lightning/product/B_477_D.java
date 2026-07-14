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
import lightning.product.StringTag;
import lightning.product.H_1468_N;
import lightning.product.MutableComponent;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.Stats;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3742_W;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.c_1514_x;
import lightning.product.g_3316_o;
import lightning.product.h_355_y;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.WritableBookItem;
import lightning.product.ComponentUtils;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;
import lightning.product.y_2498_m;

public class B_477_D
extends q_1613_l {
    public B_477_D(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    public static boolean n_1700_B(@Nullable U_2912_j nbt) {
        if (!WritableBookItem.n_1700_B(nbt)) {
            return false;
        }
        if (!nbt.R_4764_Y("title", 8)) {
            return false;
        }
        String s = nbt.M_588_G("title");
        return s.length() > 32 ? false : nbt.R_4764_Y("author", 8);
    }

    public static int G_564_y(Z_1993_T book) {
        return book.Q_4569_t().w_1484_f("generation");
    }

    public static int v_4262_N(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.Q_4569_t();
        return compoundnbt != null ? compoundnbt.G_564_y("pages", 8).size() : 0;
    }

    @Override
    public x_282_a w_1484_f(Z_1993_T stack) {
        U_2912_j compoundnbt;
        String s;
        if (stack.h_1847_R() && !H_1468_N.J_1907_R(s = (compoundnbt = stack.Q_4569_t()).M_588_G("title"))) {
            return new U_2871_b(s);
        }
        return super.w_1484_f(stack);
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        if (stack.h_1847_R()) {
            U_2912_j compoundnbt = stack.Q_4569_t();
            String s = compoundnbt.M_588_G("author");
            if (!H_1468_N.J_1907_R(s)) {
                tooltip.add(new F_2904_S("book.byAuthor", s).n_1700_B(D_4024_W.w_1484_f));
            }
            tooltip.add(new F_2904_S("book.generation." + compoundnbt.w_1484_f("generation")).n_1700_B(D_4024_W.w_1484_f));
        }
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        c_1514_x blockpos;
        b_4507_u world = context.getWorld();
        K_4074_S blockstate = world.getBlockState(blockpos = context.getPos());
        if (blockstate.n_1700_B(a_3742_W.F_489_x)) {
            return h_355_y.n_1700_B(world, blockpos, blockstate, context.getItem()) ? m_3054_I.n_1700_B(world.Y_259_p) : m_3054_I.R_4764_Y;
        }
        return m_3054_I.R_4764_Y;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        playerIn.n_1700_B(itemstack, handIn);
        playerIn.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
        return InteractionResultHolder.n_1700_B(itemstack, worldIn.v_4276_D());
    }

    public static boolean n_1700_B(Z_1993_T stack, @Nullable y_2498_m resolvingSource, @Nullable a_3913_L resolvingPlayer) {
        U_2912_j compoundnbt = stack.Q_4569_t();
        if (compoundnbt != null && !compoundnbt.t_1786_h("resolved")) {
            compoundnbt.n_1700_B("resolved", true);
            if (!B_477_D.n_1700_B(compoundnbt)) {
                return false;
            }
            q_2896_o listnbt = compoundnbt.G_564_y("pages", 8);
            for (int i = 0; i < listnbt.size(); ++i) {
                MutableComponent itextcomponent;
                String s = listnbt.t_148_a(i);
                try {
                    itextcomponent = x_282_a.n_1700_B.J_1907_R(s);
                    itextcomponent = ComponentUtils.n_1700_B(resolvingSource, itextcomponent, (N_4263_v)resolvingPlayer, 0);
                }
                catch (Exception exception) {
                    itextcomponent = new U_2871_b(s);
                }
                listnbt.G_564_y(i, StringTag.n_1700_B(x_282_a.n_1700_B.n_1700_B(itextcomponent)));
            }
            compoundnbt.n_1700_B("pages", listnbt);
            return true;
        }
        return false;
    }

    @Override
    public boolean P_1922_E(Z_1993_T stack) {
        return true;
    }
}


