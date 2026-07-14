/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils;

import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.N_4263_v;
import lightning.product.e_2866_D;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.api.api.java.baritone.api.utils.RotationUtils;

public final class RayTraceUtils {
    private RayTraceUtils() {
    }

    public static HitResult rayTraceTowards(N_4263_v entity, Rotation rotation, double blockReachDistance) {
        return RayTraceUtils.rayTraceTowards(entity, rotation, blockReachDistance, false);
    }

    public static HitResult rayTraceTowards(N_4263_v entity, Rotation rotation, double blockReachDistance, boolean wouldSneak) {
        e_2866_D start = wouldSneak ? RayTraceUtils.inferSneakingEyePosition(entity) : entity.u_2550_I(1.0f);
        e_2866_D direction = RotationUtils.calcVector3dFromRotation(rotation);
        e_2866_D end = start.J_1907_R(direction.J_1907_R * blockReachDistance, direction.R_4764_Y * blockReachDistance, direction.G_564_y * blockReachDistance);
        return entity.O_508_d.n_1700_B(new ClipContext(start, end, ClipContext.n_1700_B.J_1907_R, ClipContext.J_1907_R.n_1700_B, entity));
    }

    public static e_2866_D inferSneakingEyePosition(N_4263_v entity) {
        return new e_2866_D(entity.O_3598_v(), entity.X_2960_b() + IPlayerContext.eyeHeight(true), entity.l_2647_k());
    }
}


