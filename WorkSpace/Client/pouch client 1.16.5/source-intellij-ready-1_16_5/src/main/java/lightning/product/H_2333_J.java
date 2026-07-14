/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package lightning.product;

import java.util.Optional;
import java.util.function.Predicate;
import javax.annotation.Nullable;
import lightning.product.ClipContext;
import lightning.product.Arrow;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.Z_1993_T;
import lightning.product.b_4507_u;
import lightning.product.e_2866_D;
import lightning.product.h_384_L;
import lightning.product.q_1613_l;
import lightning.product.Items;
import lightning.product.r_4811_B;
import lightning.product.ArrowItem;
import lightning.product.u_530_F;
import lightning.product.EntityHitResult;
import lightning.product.x_1688_C;

public final class H_2333_J {
    public static HitResult n_1700_B(N_4263_v p_234618_0_, Predicate<N_4263_v> p_234618_1_) {
        EntityHitResult raytraceresult1;
        e_2866_D vector3d2;
        e_2866_D vector3d = p_234618_0_.I_4348_c();
        b_4507_u world = p_234618_0_.O_508_d;
        e_2866_D vector3d1 = p_234618_0_.s_4990_V();
        HitResult raytraceresult = world.n_1700_B(new ClipContext(vector3d1, vector3d2 = vector3d1.P_1922_E(vector3d), ClipContext.n_1700_B.n_1700_B, ClipContext.J_1907_R.n_1700_B, p_234618_0_));
        if (((HitResult)raytraceresult).R_4764_Y() != HitResult.n_1700_B.n_1700_B) {
            vector3d2 = raytraceresult.P_1922_E();
        }
        if ((raytraceresult1 = H_2333_J.n_1700_B(world, p_234618_0_, vector3d1, vector3d2, p_234618_0_.i_601_W().expand(p_234618_0_.I_4348_c()).grow(1.0), p_234618_1_)) != null) {
            raytraceresult = raytraceresult1;
        }
        return raytraceresult;
    }

    @Nullable
    public static EntityHitResult n_1700_B(N_4263_v shooter, e_2866_D startVec, e_2866_D endVec, I_4817_s boundingBox, Predicate<N_4263_v> filter, double distance) {
        b_4507_u world = shooter.O_508_d;
        double d0 = distance;
        N_4263_v entity = null;
        e_2866_D vector3d = null;
        for (N_4263_v entity1 : world.J_1907_R(shooter, boundingBox, filter)) {
            e_2866_D vector3d1;
            double d1;
            I_4817_s axisalignedbb = entity1.i_601_W().grow(entity1.G_424_k());
            Optional<e_2866_D> optional = axisalignedbb.rayTrace(startVec, endVec);
            if (axisalignedbb.contains(startVec)) {
                if (!(d0 >= 0.0)) continue;
                entity = entity1;
                vector3d = optional.orElse(startVec);
                d0 = 0.0;
                continue;
            }
            if (!optional.isPresent() || !((d1 = startVec.v_4262_N(vector3d1 = optional.get())) < d0) && d0 != 0.0) continue;
            if (entity1.d_3244_b() == shooter.d_3244_b()) {
                if (d0 != 0.0) continue;
                entity = entity1;
                vector3d = vector3d1;
                continue;
            }
            entity = entity1;
            vector3d = vector3d1;
            d0 = d1;
        }
        return entity == null ? null : new EntityHitResult(entity, vector3d);
    }

    @Nullable
    public static EntityHitResult n_1700_B(b_4507_u worldIn, N_4263_v projectile, e_2866_D startVec, e_2866_D endVec, I_4817_s boundingBox, Predicate<N_4263_v> filter) {
        double d0 = Double.MAX_VALUE;
        N_4263_v entity = null;
        for (N_4263_v entity1 : worldIn.J_1907_R(projectile, boundingBox, filter)) {
            double d1;
            I_4817_s axisalignedbb = entity1.i_601_W().grow(0.3f);
            Optional<e_2866_D> optional = axisalignedbb.rayTrace(startVec, endVec);
            if (!optional.isPresent() || !((d1 = startVec.v_4262_N(optional.get())) < d0)) continue;
            entity = entity1;
            d0 = d1;
        }
        return entity == null ? null : new EntityHitResult(entity);
    }

    public static final void n_1700_B(N_4263_v projectile, float rotationSpeed) {
        e_2866_D vector3d = projectile.I_4348_c();
        if (vector3d.v_4262_N() != 0.0) {
            float f = u_530_F.n_1700_B(N_4263_v.R_4764_Y(vector3d));
            projectile.p_178_J = (float)(u_530_F.G_564_y(vector3d.G_564_y, vector3d.J_1907_R) * 57.2957763671875) + 90.0f;
            projectile.f_4016_n = (float)(u_530_F.G_564_y((double)f, vector3d.R_4764_Y) * 57.2957763671875) - 90.0f;
            while (projectile.f_4016_n - projectile.UploadStatus < -180.0f) {
                projectile.UploadStatus -= 360.0f;
            }
            while (projectile.f_4016_n - projectile.UploadStatus >= 180.0f) {
                projectile.UploadStatus += 360.0f;
            }
            while (projectile.p_178_J - projectile.j_276_v < -180.0f) {
                projectile.j_276_v -= 360.0f;
            }
            while (projectile.p_178_J - projectile.j_276_v >= 180.0f) {
                projectile.j_276_v += 360.0f;
            }
            projectile.f_4016_n = u_530_F.v_4262_N(rotationSpeed, projectile.UploadStatus, projectile.f_4016_n);
            projectile.p_178_J = u_530_F.v_4262_N(rotationSpeed, projectile.j_276_v, projectile.p_178_J);
        }
    }

    public static x_1688_C n_1700_B(r_4811_B living, q_1613_l itemIn) {
        return living.A_2714_y().J_1907_R() == itemIn ? x_1688_C.n_1700_B : x_1688_C.J_1907_R;
    }

    public static h_384_L n_1700_B(r_4811_B shooter, Z_1993_T arrowStack, float distanceFactor) {
        ArrowItem arrowitem = (ArrowItem)(arrowStack.J_1907_R() instanceof ArrowItem ? arrowStack.J_1907_R() : Items.g_24_p);
        h_384_L abstractarrowentity = arrowitem.n_1700_B(shooter.O_508_d, arrowStack, shooter);
        abstractarrowentity.n_1700_B(shooter, distanceFactor);
        if (arrowStack.J_1907_R() == Items.NetherWartBlock && abstractarrowentity instanceof Arrow) {
            ((Arrow)abstractarrowentity).J_1907_R(arrowStack);
        }
        return abstractarrowentity;
    }
}


