/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.function.Predicate;
import lightning.product.D_38_f;
import lightning.product.F_1573_j;
import lightning.product.J_4485_t;
import lightning.product.K_4096_w;
import lightning.product.Stats;
import lightning.product.SoundEvents;
import lightning.product.ProjectileWeaponItem;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.Enchantments;
import lightning.product.h_384_L;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.ArrowItem;
import lightning.product.x_1688_C;

public class BowItem
extends ProjectileWeaponItem
implements J_4485_t {
    public BowItem(q_1613_l.n_1700_B builder) {
        super(builder);
    }

    @Override
    public void n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving, int timeLeft) {
        if (entityLiving instanceof a_3913_L) {
            a_3913_L playerentity = (a_3913_L)entityLiving;
            boolean flag = playerentity.C_415_h.G_564_y || K_4096_w.n_1700_B(Enchantments.c_3005_b, stack) > 0;
            Z_1993_T itemstack = playerentity.u_1723_Y(stack);
            if (!itemstack.n_1700_B() || flag) {
                int i;
                float f;
                if (itemstack.n_1700_B()) {
                    itemstack = new Z_1993_T(Items.g_24_p);
                }
                if (!((double)(f = BowItem.n_1700_B(i = this.J_1907_R(stack) - timeLeft)) < 0.1)) {
                    boolean flag1;
                    boolean bl = flag1 = flag && itemstack.J_1907_R() == Items.g_24_p;
                    if (!worldIn.Y_259_p) {
                        int k;
                        int j;
                        ArrowItem arrowitem = (ArrowItem)(itemstack.J_1907_R() instanceof ArrowItem ? itemstack.J_1907_R() : Items.g_24_p);
                        h_384_L abstractarrowentity = arrowitem.n_1700_B(worldIn, itemstack, (r_4811_B)playerentity);
                        abstractarrowentity.n_1700_B(playerentity, playerentity.f_4016_n, playerentity.p_178_J, 0.0f, f * 3.0f, 1.0f);
                        if (f == 1.0f) {
                            abstractarrowentity.n_1700_B(true);
                        }
                        if ((j = K_4096_w.n_1700_B(Enchantments.k_2293_S, stack)) > 0) {
                            abstractarrowentity.w_1484_f(abstractarrowentity.t_148_a() + (double)j * 0.5 + 0.5);
                        }
                        if ((k = K_4096_w.n_1700_B(Enchantments.q_2307_F, stack)) > 0) {
                            abstractarrowentity.n_1700_B(k);
                        }
                        if (K_4096_w.n_1700_B(Enchantments.Z_875_P, stack) > 0) {
                            abstractarrowentity.P_1922_E(100);
                        }
                        stack.n_1700_B(1, playerentity, (T p_220009_1_) -> p_220009_1_.G_564_y(playerentity.Q_2552_b()));
                        if (flag1 || playerentity.C_415_h.G_564_y && (itemstack.J_1907_R() == Items.g_2783_J || itemstack.J_1907_R() == Items.NetherWartBlock)) {
                            abstractarrowentity.R_4764_Y = h_384_L.n_1700_B.R_4764_Y;
                        }
                        worldIn.a_(abstractarrowentity);
                    }
                    worldIn.n_1700_B((a_3913_L)null, playerentity.O_3598_v(), playerentity.X_2960_b(), playerentity.l_2647_k(), SoundEvents.c_4037_x, D_38_f.w_1484_f, 1.0f, 1.0f / (w_1484_f.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
                    if (!flag1 && !playerentity.C_415_h.G_564_y) {
                        itemstack.v_4262_N(1);
                        if (itemstack.n_1700_B()) {
                            playerentity.l_1268_F.u_1723_Y(itemstack);
                        }
                    }
                    playerentity.n_1700_B(Stats.R_4764_Y.J_1907_R(this));
                }
            }
        }
    }

    public static float n_1700_B(int charge) {
        float f = (float)charge / 20.0f;
        if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    @Override
    public int J_1907_R(Z_1993_T stack) {
        return 72000;
    }

    @Override
    public F_1573_j R_4764_Y(Z_1993_T stack) {
        return F_1573_j.P_1922_E;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        boolean flag;
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        boolean bl = flag = !playerIn.u_1723_Y(itemstack).n_1700_B();
        if (!playerIn.C_415_h.G_564_y && !flag) {
            return InteractionResultHolder.G_564_y(itemstack);
        }
        playerIn.J_1907_R(handIn);
        return InteractionResultHolder.J_1907_R(itemstack);
    }

    @Override
    public Predicate<Z_1993_T> R_4764_Y() {
        return n_1700_B;
    }

    @Override
    public int P_1922_E() {
        return 15;
    }
}


