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
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.B_4088_l;
import lightning.product.Projectile;
import lightning.product.D_38_f;
import lightning.product.D_4024_W;
import lightning.product.F_1573_j;
import lightning.product.F_2904_S;
import lightning.product.J_3992_v;
import lightning.product.J_4485_t;
import lightning.product.K_4096_w;
import lightning.product.M_1336_P;
import lightning.product.M_4954_p;
import lightning.product.Stats;
import lightning.product.U_2871_b;
import lightning.product.U_2912_j;
import lightning.product.U_3554_Q;
import lightning.product.SoundEvents;
import lightning.product.SoundEvent;
import lightning.product.ProjectileWeaponItem;
import lightning.product.InteractionResultHolder;
import lightning.product.Z_1993_T;
import lightning.product.a_3913_L;
import lightning.product.b_4507_u;
import lightning.product.Enchantments;
import lightning.product.e_2866_D;
import lightning.product.g_3316_o;
import lightning.product.h_384_L;
import lightning.product.q_1613_l;
import lightning.product.q_2896_o;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.ArrowItem;
import lightning.product.w_3785_E;
import lightning.product.x_1688_C;
import lightning.product.x_282_a;

public class Z_1630_j
extends ProjectileWeaponItem
implements J_4485_t {
    private boolean R_4764_Y = false;
    private boolean G_564_y = false;

    public Z_1630_j(q_1613_l.n_1700_B propertiesIn) {
        super(propertiesIn);
    }

    @Override
    public Predicate<Z_1993_T> v_4262_N() {
        return J_1907_R;
    }

    @Override
    public Predicate<Z_1993_T> R_4764_Y() {
        return n_1700_B;
    }

    @Override
    public InteractionResultHolder<Z_1993_T> n_1700_B(b_4507_u worldIn, a_3913_L playerIn, x_1688_C handIn) {
        Z_1993_T itemstack = playerIn.R_4764_Y(handIn);
        if (Z_1630_j.G_564_y(itemstack)) {
            Z_1630_j.n_1700_B(worldIn, playerIn, handIn, itemstack, Z_1630_j.P_4830_p(itemstack), 1.0f);
            Z_1630_j.n_1700_B(itemstack, false);
            return InteractionResultHolder.J_1907_R(itemstack);
        }
        if (!playerIn.u_1723_Y(itemstack).n_1700_B()) {
            if (!Z_1630_j.G_564_y(itemstack)) {
                this.R_4764_Y = false;
                this.G_564_y = false;
                playerIn.J_1907_R(handIn);
            }
            return InteractionResultHolder.J_1907_R(itemstack);
        }
        return InteractionResultHolder.G_564_y(itemstack);
    }

    @Override
    public void n_1700_B(Z_1993_T stack, b_4507_u worldIn, r_4811_B entityLiving, int timeLeft) {
        int i = this.J_1907_R(stack) - timeLeft;
        float f = Z_1630_j.n_1700_B(i, stack);
        if (f >= 1.0f && !Z_1630_j.G_564_y(stack) && Z_1630_j.n_1700_B(entityLiving, stack)) {
            Z_1630_j.n_1700_B(stack, true);
            D_38_f soundcategory = entityLiving instanceof a_3913_L ? D_38_f.w_1484_f : D_38_f.u_1723_Y;
            worldIn.n_1700_B((a_3913_L)null, entityLiving.O_3598_v(), entityLiving.X_2960_b(), entityLiving.l_2647_k(), SoundEvents.x_4991_F, soundcategory, 1.0f, 1.0f / (w_1484_f.nextFloat() * 0.5f + 1.0f) + 0.2f);
        }
    }

    private static boolean n_1700_B(r_4811_B entityIn, Z_1993_T stack) {
        int i = K_4096_w.n_1700_B(Enchantments.n_3318_d, stack);
        int j = i == 0 ? 1 : 3;
        boolean flag = entityIn instanceof a_3913_L && ((a_3913_L)entityIn).C_415_h.G_564_y;
        Z_1993_T itemstack = entityIn.u_1723_Y(stack);
        Z_1993_T itemstack1 = itemstack.t_148_a();
        for (int k = 0; k < j; ++k) {
            if (k > 0) {
                itemstack = itemstack1.t_148_a();
            }
            if (itemstack.n_1700_B() && flag) {
                itemstack = new Z_1993_T(Items.g_24_p);
                itemstack1 = itemstack.t_148_a();
            }
            if (Z_1630_j.n_1700_B(entityIn, stack, itemstack, k > 0, flag)) continue;
            return false;
        }
        return true;
    }

    private static boolean n_1700_B(r_4811_B p_220023_0_, Z_1993_T stack, Z_1993_T p_220023_2_, boolean p_220023_3_, boolean p_220023_4_) {
        Z_1993_T itemstack;
        boolean flag;
        if (p_220023_2_.n_1700_B()) {
            return false;
        }
        boolean bl = flag = p_220023_4_ && p_220023_2_.J_1907_R() instanceof ArrowItem;
        if (!(flag || p_220023_4_ || p_220023_3_)) {
            itemstack = p_220023_2_.n_1700_B(1);
            if (p_220023_2_.n_1700_B() && p_220023_0_ instanceof a_3913_L) {
                ((a_3913_L)p_220023_0_).l_1268_F.u_1723_Y(p_220023_2_);
            }
        } else {
            itemstack = p_220023_2_.t_148_a();
        }
        Z_1630_j.J_1907_R(stack, itemstack);
        return true;
    }

    public static boolean G_564_y(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.Q_4569_t();
        return compoundnbt != null && compoundnbt.t_1786_h("Charged");
    }

    public static void n_1700_B(Z_1993_T stack, boolean chargedIn) {
        U_2912_j compoundnbt = stack.M_182_A();
        compoundnbt.n_1700_B("Charged", chargedIn);
    }

    private static void J_1907_R(Z_1993_T crossbow, Z_1993_T projectile) {
        U_2912_j compoundnbt = crossbow.M_182_A();
        q_2896_o listnbt = compoundnbt.R_4764_Y("ChargedProjectiles", 9) ? compoundnbt.G_564_y("ChargedProjectiles", 10) : new q_2896_o();
        U_2912_j compoundnbt1 = new U_2912_j();
        projectile.J_1907_R(compoundnbt1);
        listnbt.add(compoundnbt1);
        compoundnbt.n_1700_B("ChargedProjectiles", listnbt);
    }

    private static List<Z_1993_T> u_2550_I(Z_1993_T stack) {
        q_2896_o listnbt;
        ArrayList list = Lists.newArrayList();
        U_2912_j compoundnbt = stack.Q_4569_t();
        if (compoundnbt != null && compoundnbt.R_4764_Y("ChargedProjectiles", 9) && (listnbt = compoundnbt.G_564_y("ChargedProjectiles", 10)) != null) {
            for (int i = 0; i < listnbt.size(); ++i) {
                U_2912_j compoundnbt1 = listnbt.n_1700_B(i);
                list.add(Z_1993_T.n_1700_B(compoundnbt1));
            }
        }
        return list;
    }

    private static void M_588_G(Z_1993_T stack) {
        U_2912_j compoundnbt = stack.Q_4569_t();
        if (compoundnbt != null) {
            q_2896_o listnbt = compoundnbt.G_564_y("ChargedProjectiles", 9);
            listnbt.clear();
            compoundnbt.n_1700_B("ChargedProjectiles", listnbt);
        }
    }

    public static boolean n_1700_B(Z_1993_T stack, q_1613_l ammoItem) {
        return Z_1630_j.u_2550_I(stack).stream().anyMatch(p_220010_1_ -> p_220010_1_.J_1907_R() == ammoItem);
    }

    private static void n_1700_B(b_4507_u worldIn, r_4811_B shooter, x_1688_C handIn, Z_1993_T crossbow, Z_1993_T projectile, float soundPitch, boolean isCreativeMode, float velocity, float inaccuracy, float projectileAngle) {
        if (!worldIn.Y_259_p) {
            Projectile projectileentity;
            boolean flag;
            boolean bl = flag = projectile.J_1907_R() == Items.FenceBlock;
            if (flag) {
                projectileentity = new J_3992_v(worldIn, projectile, shooter, shooter.O_3598_v(), shooter.X_2048_Y() - (double)0.15f, shooter.l_2647_k(), true);
            } else {
                projectileentity = Z_1630_j.n_1700_B(worldIn, shooter, crossbow, projectile);
                if (isCreativeMode || projectileAngle != 0.0f) {
                    ((h_384_L)projectileentity).R_4764_Y = h_384_L.n_1700_B.R_4764_Y;
                }
            }
            if (shooter instanceof M_4954_p) {
                M_4954_p icrossbowuser = (M_4954_p)((Object)shooter);
                icrossbowuser.n_1700_B(icrossbowuser.t_148_a(), crossbow, projectileentity, projectileAngle);
            } else {
                e_2866_D vector3d1 = shooter.s_956_w(1.0f);
                w_3785_E quaternion = new w_3785_E(new M_1336_P(vector3d1), projectileAngle, true);
                e_2866_D vector3d = shooter.t_148_a(1.0f);
                M_1336_P vector3f = new M_1336_P(vector3d);
                vector3f.n_1700_B(quaternion);
                projectileentity.R_4764_Y(vector3f.n_1700_B(), vector3f.J_1907_R(), vector3f.R_4764_Y(), velocity, inaccuracy);
            }
            crossbow.n_1700_B(flag ? 3 : 1, shooter, (T p_220017_1_) -> p_220017_1_.G_564_y(handIn));
            worldIn.a_(projectileentity);
            worldIn.n_1700_B((a_3913_L)null, shooter.O_3598_v(), shooter.X_2960_b(), shooter.l_2647_k(), SoundEvents.H_1873_g, D_38_f.w_1484_f, 1.0f, soundPitch);
        }
    }

    private static h_384_L n_1700_B(b_4507_u worldIn, r_4811_B shooter, Z_1993_T crossbow, Z_1993_T ammo) {
        ArrowItem arrowitem = (ArrowItem)(ammo.J_1907_R() instanceof ArrowItem ? ammo.J_1907_R() : Items.g_24_p);
        h_384_L abstractarrowentity = arrowitem.n_1700_B(worldIn, ammo, shooter);
        if (shooter instanceof a_3913_L) {
            abstractarrowentity.n_1700_B(true);
        }
        abstractarrowentity.n_1700_B(SoundEvents.X_1313_W);
        abstractarrowentity.G_564_y(true);
        int i = K_4096_w.n_1700_B(Enchantments.z_1737_N, crossbow);
        if (i > 0) {
            abstractarrowentity.J_1907_R((byte)i);
        }
        return abstractarrowentity;
    }

    public static void n_1700_B(b_4507_u worldIn, r_4811_B shooter, x_1688_C handIn, Z_1993_T stack, float velocityIn, float inaccuracyIn) {
        List<Z_1993_T> list = Z_1630_j.u_2550_I(stack);
        float[] afloat = Z_1630_j.n_1700_B(shooter.M_3508_C());
        for (int i = 0; i < list.size(); ++i) {
            boolean flag;
            Z_1993_T itemstack = list.get(i);
            boolean bl = flag = shooter instanceof a_3913_L && ((a_3913_L)shooter).C_415_h.G_564_y;
            if (itemstack.n_1700_B()) continue;
            if (i == 0) {
                Z_1630_j.n_1700_B(worldIn, shooter, handIn, stack, itemstack, afloat[i], flag, velocityIn, inaccuracyIn, 0.0f);
                continue;
            }
            if (i == 1) {
                Z_1630_j.n_1700_B(worldIn, shooter, handIn, stack, itemstack, afloat[i], flag, velocityIn, inaccuracyIn, -10.0f);
                continue;
            }
            if (i != 2) continue;
            Z_1630_j.n_1700_B(worldIn, shooter, handIn, stack, itemstack, afloat[i], flag, velocityIn, inaccuracyIn, 10.0f);
        }
        Z_1630_j.n_1700_B(worldIn, shooter, stack);
    }

    private static float[] n_1700_B(Random rand) {
        boolean flag = rand.nextBoolean();
        return new float[]{1.0f, Z_1630_j.n_1700_B(flag), Z_1630_j.n_1700_B(!flag)};
    }

    private static float n_1700_B(boolean flagIn) {
        float f = flagIn ? 0.63f : 0.43f;
        return 1.0f / (w_1484_f.nextFloat() * 0.5f + 1.8f) + f;
    }

    private static void n_1700_B(b_4507_u worldIn, r_4811_B shooter, Z_1993_T stack) {
        if (shooter instanceof B_4088_l) {
            B_4088_l serverplayerentity = (B_4088_l)shooter;
            if (!worldIn.Y_259_p) {
                U_3554_Q.x_607_J.n_1700_B(serverplayerentity, stack);
            }
            serverplayerentity.n_1700_B(Stats.R_4764_Y.J_1907_R(stack.J_1907_R()));
        }
        Z_1630_j.M_588_G(stack);
    }

    @Override
    public void n_1700_B(b_4507_u worldIn, r_4811_B livingEntityIn, Z_1993_T stack, int count) {
        if (!worldIn.Y_259_p) {
            int i = K_4096_w.n_1700_B(Enchantments.d_2427_y, stack);
            SoundEvent soundevent = this.n_1700_B(i);
            SoundEvent soundevent1 = i == 0 ? SoundEvents.Z_759_W : null;
            float f = (float)(stack.u_2550_I() - count) / (float)Z_1630_j.v_4262_N(stack);
            if (f < 0.2f) {
                this.R_4764_Y = false;
                this.G_564_y = false;
            }
            if (f >= 0.2f && !this.R_4764_Y) {
                this.R_4764_Y = true;
                worldIn.n_1700_B((a_3913_L)null, livingEntityIn.O_3598_v(), livingEntityIn.X_2960_b(), livingEntityIn.l_2647_k(), soundevent, D_38_f.w_1484_f, 0.5f, 1.0f);
            }
            if (f >= 0.5f && soundevent1 != null && !this.G_564_y) {
                this.G_564_y = true;
                worldIn.n_1700_B((a_3913_L)null, livingEntityIn.O_3598_v(), livingEntityIn.X_2960_b(), livingEntityIn.l_2647_k(), soundevent1, D_38_f.w_1484_f, 0.5f, 1.0f);
            }
        }
    }

    @Override
    public int J_1907_R(Z_1993_T stack) {
        return Z_1630_j.v_4262_N(stack) + 3;
    }

    public static int v_4262_N(Z_1993_T stack) {
        int i = K_4096_w.n_1700_B(Enchantments.d_2427_y, stack);
        return i == 0 ? 25 : 25 - 5 * i;
    }

    @Override
    public F_1573_j R_4764_Y(Z_1993_T stack) {
        return F_1573_j.v_4262_N;
    }

    private SoundEvent n_1700_B(int enchantmentLevel) {
        switch (enchantmentLevel) {
            case 1: {
                return SoundEvents.l_1268_F;
            }
            case 2: {
                return SoundEvents.J_303_C;
            }
            case 3: {
                return SoundEvents.o_1800_r;
            }
        }
        return SoundEvents.f_1574_f;
    }

    private static float n_1700_B(int useTime, Z_1993_T stack) {
        float f = (float)useTime / (float)Z_1630_j.v_4262_N(stack);
        if (f > 1.0f) {
            f = 1.0f;
        }
        return f;
    }

    @Override
    public void n_1700_B(Z_1993_T stack, @Nullable b_4507_u worldIn, List<x_282_a> tooltip, g_3316_o flagIn) {
        List<Z_1993_T> list = Z_1630_j.u_2550_I(stack);
        if (Z_1630_j.G_564_y(stack) && !list.isEmpty()) {
            Z_1993_T itemstack = list.get(0);
            tooltip.add(new F_2904_S("item.minecraft.crossbow.projectile").n_1700_B(" ").n_1700_B(itemstack.A_4115_X()));
            if (flagIn.n_1700_B() && itemstack.J_1907_R() == Items.FenceBlock) {
                ArrayList list1 = Lists.newArrayList();
                Items.FenceBlock.n_1700_B(itemstack, worldIn, list1, flagIn);
                if (!list1.isEmpty()) {
                    for (int i = 0; i < list1.size(); ++i) {
                        list1.set(i, new U_2871_b("  ").n_1700_B((x_282_a)list1.get(i)).n_1700_B(D_4024_W.w_1484_f));
                    }
                    tooltip.addAll(list1);
                }
            }
        }
    }

    private static float P_4830_p(Z_1993_T p_220013_0_) {
        return p_220013_0_.J_1907_R() == Items.V_2454_J && Z_1630_j.n_1700_B(p_220013_0_, Items.FenceBlock) ? 1.6f : 3.15f;
    }

    @Override
    public int P_1922_E() {
        return 8;
    }
}


