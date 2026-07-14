/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  javax.annotation.Nullable
 */
package lightning.product;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.annotation.Nullable;
import lightning.product.D_4024_W;
import lightning.product.F_2904_S;
import lightning.product.J_3992_v;
import lightning.product.T_3136_m;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.UseOnContext;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_257_Y;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.g_3316_o;
import lightning.product.m_3054_I;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;

public class FireworkRocketItem
extends q_1613_l {
    public FireworkRocketItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public m_3054_I n_1700_B(UseOnContext context) {
        b_4507_u world = context.getWorld();
        if (!world.Y_259_p) {
            Z_1993_T itemstack = context.getItem();
            e_2866_D vector3d = context.getHitVec();
            b_257_Y direction = context.getFace();
            J_3992_v fireworkrocketentity = new J_3992_v(world, context.getPlayer(), vector3d.J_1907_R + (double)direction.t_148_a() * 0.15, vector3d.R_4764_Y + (double)direction.s_956_w() * 0.15, vector3d.G_564_y + (double)direction.u_2550_I() * 0.15, itemstack);
            world.a_(fireworkrocketentity);
            itemstack.v_4262_N(1);
        }
        return m_3054_I.n_1700_B(world.Y_259_p);
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        if (playerIn.k_578_l()) {
            Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
            if (!worldIn.Y_259_p) {
                worldIn.a_(new J_3992_v(worldIn, itemstack, playerIn));
                if (!playerIn.C_415_h.G_564_y) {
                    itemstack.v_4262_N(1);
                }
            }
            return InteractionResultHolder.n_1700_B(playerIn.R_4764_Y(handIn), worldIn.v_4276_D());
        }
        return InteractionResultHolder.R_4764_Y(playerIn.R_4764_Y(handIn));
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        U_2912_j compoundnbt = stack.J_1907_R("Fireworks");
        if (compoundnbt != null) {
            q_2896_o listnbt;
            if (compoundnbt.R_4764_Y("Flight", 99)) {
                tooltip.add(new F_2904_S("item.minecraft.firework_rocket.flight").n_1700_B(" ").n_1700_B(String.valueOf(compoundnbt.u_1723_Y("Flight"))).n_1700_B(D_4024_W.w_1484_f));
            }
            if (!(listnbt = compoundnbt.G_564_y("Explosions", 10)).isEmpty()) {
                for (int i = 0; i < listnbt.size(); ++i) {
                    U_2912_j compoundnbt1 = listnbt.n_1700_B(i);
                    ArrayList list = Lists.newArrayList();
                    T_3136_m.n_1700_B(compoundnbt1, list);
                    if (list.isEmpty()) continue;
                    for (int j = 1; j < list.size(); ++j) {
                        list.set(j, new U_2871_b("  ").n_1700_B((x_282_a)list.get(j)).n_1700_B(D_4024_W.w_1484_f));
                    }
                    tooltip.addAll(list);
                }
            }
        }
    }

    public static final class n_1700_B
    extends Enum<n_1700_B> {
        public static final /* enum */ n_1700_B n_1700_B = new n_1700_B(0, "small_ball");
        public static final /* enum */ n_1700_B J_1907_R = new n_1700_B(1, "large_ball");
        public static final /* enum */ n_1700_B R_4764_Y = new n_1700_B(2, "star");
        public static final /* enum */ n_1700_B G_564_y = new n_1700_B(3, "creeper");
        public static final /* enum */ n_1700_B P_1922_E = new n_1700_B(4, "burst");
        private static final n_1700_B[] u_1723_Y;
        private final int v_4262_N;
        private final String w_1484_f;
        private static final /* synthetic */ n_1700_B[] t_148_a;

        public static n_1700_B[] values() {
            return (n_1700_B[])t_148_a.clone();
        }

        public static n_1700_B valueOf(String name) {
            return Enum.valueOf(n_1700_B.class, name);
        }

        private n_1700_B(int indexIn, String nameIn) {
            this.v_4262_N = indexIn;
            this.w_1484_f = nameIn;
        }

        public int n_1700_B() {
            return this.v_4262_N;
        }

        public String J_1907_R() {
            return this.w_1484_f;
        }

        public static n_1700_B n_1700_B(int indexIn) {
            return indexIn >= 0 && indexIn < u_1723_Y.length ? u_1723_Y[indexIn] : n_1700_B;
        }

        private static /* synthetic */ n_1700_B[] R_4764_Y() {
            return new n_1700_B[]{n_1700_B, J_1907_R, R_4764_Y, G_564_y, P_1922_E};
        }

        static {
            t_148_a = lightning.product.FireworkRocketItem$n_1700_B.R_4764_Y();
            u_1723_Y = (n_1700_B[])Arrays.stream(lightning.product.FireworkRocketItem$n_1700_B.values()).sorted(Comparator.comparingInt(p_199796_0_ -> p_199796_0_.v_4262_N)).toArray(n_1700_B[]::new);
        }
    }
}


