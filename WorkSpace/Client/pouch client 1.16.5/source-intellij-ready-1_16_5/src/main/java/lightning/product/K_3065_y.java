/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Random;
import lightning.product.Container;
import lightning.product.N_4263_v;
import lightning.product.NonNullList;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.MinecraftClient;
import lightning.product.c_1514_x;
import lightning.product.n_1494_c;
import lightning.product.q_1613_l;
import lightning.product.t_5_h;

public class K_3065_y {
    private static final Random n_1700_B = new Random();

    public static void n_1700_B(b_4507_u worldIn, c_1514_x pos, Container inventory) {
        K_3065_y.n_1700_B(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), inventory);
    }

    public static void n_1700_B(b_4507_u worldIn, N_4263_v entityAt, Container inventory) {
        K_3065_y.n_1700_B(worldIn, entityAt.O_3598_v(), entityAt.X_2960_b(), entityAt.l_2647_k(), inventory);
    }

    private static void n_1700_B(b_4507_u worldIn, double x, double y, double z, Container inventory) {
        for (int i = 0; i < inventory.Y_259_p(); ++i) {
            K_3065_y.n_1700_B(worldIn, x, y, z, inventory.s_956_w(i));
        }
    }

    public static void n_1700_B(b_4507_u p_219961_0_, c_1514_x p_219961_1_, NonNullList<Z_1993_T> p_219961_2_) {
        p_219961_2_.forEach(p_219962_2_ -> K_3065_y.n_1700_B(p_219961_0_, (double)p_219961_1_.getX(), (double)p_219961_1_.getY(), (double)p_219961_1_.getZ(), p_219962_2_));
    }

    public static void n_1700_B(b_4507_u worldIn, double x, double y, double z, Z_1993_T stack) {
        double d0 = t_5_h.d_2461_k.t_148_a();
        double d1 = 1.0 - d0;
        double d2 = d0 / 2.0;
        double d3 = Math.floor(x) + n_1700_B.nextDouble() * d1 + d2;
        double d4 = Math.floor(y) + n_1700_B.nextDouble() * d1;
        double d5 = Math.floor(z) + n_1700_B.nextDouble() * d1 + d2;
        while (!stack.n_1700_B()) {
            n_1494_c itementity = new n_1494_c(worldIn, d3, d4, d5, stack.n_1700_B(n_1700_B.nextInt(21) + 10));
            float f = 0.05f;
            itementity.h_1847_R(n_1700_B.nextGaussian() * (double)0.05f, n_1700_B.nextGaussian() * (double)0.05f + (double)0.2f, n_1700_B.nextGaussian() * (double)0.05f);
            worldIn.a_(itementity);
        }
    }

    public static int n_1700_B(q_1613_l item) {
        for (int i = 0; i < 45; ++i) {
            if (MinecraftClient.A_4115_X().Y_259_p.l_1268_F.s_956_w(i).J_1907_R() != item) continue;
            return i;
        }
        return -1;
    }
}



