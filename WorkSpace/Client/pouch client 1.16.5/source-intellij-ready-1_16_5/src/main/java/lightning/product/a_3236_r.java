/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;
import lightning.product.E_4668_a;
import lightning.product.WalkTarget;
import lightning.product.L_2225_p;
import lightning.product.N_4263_v;
import lightning.product.ProjectileWeaponItem;
import lightning.product.Z_148_A;
import lightning.product.Z_1993_T;
import lightning.product.Z_530_i;
import lightning.product.c_1514_x;
import lightning.product.SectionPos;
import lightning.product.e_2866_D;
import lightning.product.e_3591_l;
import lightning.product.n_1494_c;
import lightning.product.o_4722_d;
import lightning.product.q_1613_l;
import lightning.product.r_4811_B;
import lightning.product.t_5_h;
import lightning.product.MemoryModuleType;

public class a_3236_r {
    public static void n_1700_B(r_4811_B firstEntity, r_4811_B secondEntity, float speed) {
        a_3236_r.G_564_y(firstEntity, secondEntity);
        a_3236_r.J_1907_R(firstEntity, secondEntity, speed);
    }

    public static boolean n_1700_B(E_4668_a<?> brainIn, r_4811_B target) {
        return brainIn.R_4764_Y(MemoryModuleType.w_1484_f).filter(visible -> visible.contains(target)).isPresent();
    }

    public static boolean n_1700_B(E_4668_a<?> brains, MemoryModuleType<? extends r_4811_B> memorymodule, t_5_h<?> entityTypeIn) {
        return a_3236_r.n_1700_B(brains, memorymodule, (r_4811_B livingEntity) -> livingEntity.f_4016_n() == entityTypeIn);
    }

    private static boolean n_1700_B(E_4668_a<?> brain, MemoryModuleType<? extends r_4811_B> memoryType, Predicate<r_4811_B> livingPredicate) {
        return brain.R_4764_Y(memoryType).filter(livingPredicate).filter(r_4811_B::RealmsLongRunningMcoTaskScreen).filter(livingEntity -> a_3236_r.n_1700_B(brain, livingEntity)).isPresent();
    }

    private static void G_564_y(r_4811_B firstEntity, r_4811_B secondEntity) {
        a_3236_r.n_1700_B(firstEntity, secondEntity);
        a_3236_r.n_1700_B(secondEntity, firstEntity);
    }

    public static void n_1700_B(r_4811_B entityIn, r_4811_B targetIn) {
        entityIn.y_1945_D().n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d(targetIn, true));
    }

    private static void J_1907_R(r_4811_B firstEntity, r_4811_B secondEntity, float speed) {
        int i = 2;
        a_3236_r.n_1700_B(firstEntity, secondEntity, speed, 2);
        a_3236_r.n_1700_B(secondEntity, firstEntity, speed, 2);
    }

    public static void n_1700_B(r_4811_B livingEntity, N_4263_v target, float speed, int distance) {
        WalkTarget walktarget = new WalkTarget(new o_4722_d(target, false), speed, distance);
        livingEntity.y_1945_D().n_1700_B(MemoryModuleType.h_1847_R, new o_4722_d(target, true));
        livingEntity.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, walktarget);
    }

    public static void n_1700_B(r_4811_B livingEntity, c_1514_x pos, float speed, int distance) {
        WalkTarget walktarget = new WalkTarget(new Z_148_A(pos), speed, distance);
        livingEntity.y_1945_D().n_1700_B(MemoryModuleType.h_1847_R, new Z_148_A(pos));
        livingEntity.y_1945_D().n_1700_B(MemoryModuleType.P_4830_p, walktarget);
    }

    public static void n_1700_B(r_4811_B livingEntity, Z_1993_T stack, e_2866_D offset) {
        double d0 = livingEntity.X_2048_Y() - (double)0.3f;
        n_1494_c itementity = new n_1494_c(livingEntity.O_508_d, livingEntity.O_3598_v(), d0, livingEntity.l_2647_k(), stack);
        float f = 0.3f;
        e_2866_D vector3d = offset.G_564_y(livingEntity.s_4990_V());
        vector3d = vector3d.G_564_y().n_1700_B((double)0.3f);
        itementity.v_4262_N(vector3d);
        itementity.t_148_a();
        livingEntity.O_508_d.a_(itementity);
    }

    public static SectionPos n_1700_B(e_3591_l serverWorldIn, SectionPos sectionPosIn, int radius) {
        int i = serverWorldIn.J_1907_R(sectionPosIn);
        return SectionPos.n_1700_B(sectionPosIn, radius).filter(sectionPos -> serverWorldIn.J_1907_R((SectionPos)sectionPos) < i).min(Comparator.comparingInt(serverWorldIn::J_1907_R)).orElse(sectionPosIn);
    }

    public static boolean n_1700_B(Z_530_i mob, r_4811_B target, int cooldown) {
        q_1613_l item = mob.A_2714_y().J_1907_R();
        if (item instanceof ProjectileWeaponItem && mob.n_1700_B((ProjectileWeaponItem)item)) {
            int i = ((ProjectileWeaponItem)item).P_1922_E() - cooldown;
            return mob.n_1700_B((N_4263_v)target, (double)i);
        }
        return a_3236_r.J_1907_R(mob, target);
    }

    public static boolean J_1907_R(r_4811_B livingEntity, r_4811_B target) {
        double d1;
        double d0 = livingEntity.v_4262_N(target.O_3598_v(), target.X_2960_b(), target.l_2647_k());
        return d0 <= (d1 = (double)(livingEntity.C_415_h() * 2.0f * livingEntity.C_415_h() * 2.0f + target.C_415_h()));
    }

    public static boolean n_1700_B(r_4811_B livingEntity, r_4811_B target, double distance) {
        Optional<r_4811_B> optional = livingEntity.y_1945_D().R_4764_Y(MemoryModuleType.Q_4569_t);
        if (!optional.isPresent()) {
            return false;
        }
        double d0 = livingEntity.u_1723_Y(optional.get().s_4990_V());
        double d1 = livingEntity.u_1723_Y(target.s_4990_V());
        return d1 > d0 + distance * distance;
    }

    public static boolean R_4764_Y(r_4811_B livingEntity, r_4811_B target) {
        E_4668_a<List<r_4811_B>> brain = livingEntity.y_1945_D();
        return !brain.n_1700_B(MemoryModuleType.w_1484_f) ? false : brain.R_4764_Y(MemoryModuleType.w_1484_f).get().contains(target);
    }

    public static r_4811_B n_1700_B(r_4811_B centerEntity, Optional<r_4811_B> optionalEntity, r_4811_B livingEntity) {
        return !optionalEntity.isPresent() ? livingEntity : a_3236_r.n_1700_B(centerEntity, optionalEntity.get(), livingEntity);
    }

    public static r_4811_B n_1700_B(r_4811_B centerEntity, r_4811_B livingEntity1, r_4811_B livingEntity2) {
        e_2866_D vector3d = livingEntity1.s_4990_V();
        e_2866_D vector3d1 = livingEntity2.s_4990_V();
        return centerEntity.u_1723_Y(vector3d) < centerEntity.u_1723_Y(vector3d1) ? livingEntity1 : livingEntity2;
    }

    public static Optional<r_4811_B> n_1700_B(r_4811_B livingEntity, MemoryModuleType<UUID> targetMemory) {
        Optional<UUID> optional = livingEntity.y_1945_D().R_4764_Y(targetMemory);
        return optional.map(uuid -> (r_4811_B)((e_3591_l)livingEntity.O_508_d).J_1907_R((UUID)uuid));
    }

    public static Stream<L_2225_p> n_1700_B(L_2225_p villager, Predicate<L_2225_p> villagerPredicate) {
        return villager.y_1945_D().R_4764_Y(MemoryModuleType.v_4262_N).map(mobs -> mobs.stream().filter(livingEntity -> livingEntity instanceof L_2225_p && livingEntity != villager).map(livingEntity -> (L_2225_p)livingEntity).filter(r_4811_B::RealmsLongRunningMcoTaskScreen).filter(villagerPredicate)).orElseGet(Stream::empty);
    }
}


