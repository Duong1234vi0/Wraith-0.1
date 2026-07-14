/*
 * Decompiled with CFR 0.152.
 */
package mods.baritone.api.api.java.baritone.api.utils;

import java.util.Optional;
import lightning.product.BlockHitResult;
import lightning.product.HitResult;
import lightning.product.K_4074_S;
import lightning.product.N_4263_v;
import lightning.product.BaseFireBlock;
import lightning.product.V_772_m;
import lightning.product.b_257_Y;
import lightning.product.c_1514_x;
import lightning.product.e_2866_D;
import lightning.product.s_1395_c;
import lightning.product.u_530_F;
import lightning.product.x_268_Y;
import mods.baritone.api.api.java.baritone.api.BaritoneAPI;
import mods.baritone.api.api.java.baritone.api.IBaritone;
import mods.baritone.api.api.java.baritone.api.utils.IPlayerContext;
import mods.baritone.api.api.java.baritone.api.utils.RayTraceUtils;
import mods.baritone.api.api.java.baritone.api.utils.Rotation;
import mods.baritone.api.api.java.baritone.api.utils.VecUtils;

public final class RotationUtils {
    public static final double DEG_TO_RAD = Math.PI / 180;
    public static final double RAD_TO_DEG = 57.29577951308232;
    private static final e_2866_D[] BLOCK_SIDE_MULTIPLIERS = new e_2866_D[]{new e_2866_D(0.5, 0.0, 0.5), new e_2866_D(0.5, 1.0, 0.5), new e_2866_D(0.5, 0.5, 0.0), new e_2866_D(0.5, 0.5, 1.0), new e_2866_D(0.0, 0.5, 0.5), new e_2866_D(1.0, 0.5, 0.5)};

    private RotationUtils() {
    }

    public static Rotation calcRotationFromCoords(c_1514_x orig, c_1514_x dest) {
        return RotationUtils.calcRotationFromVec3d(new e_2866_D(orig.getX(), orig.getY(), orig.getZ()), new e_2866_D(dest.getX(), dest.getY(), dest.getZ()));
    }

    public static Rotation wrapAnglesToRelative(Rotation current, Rotation target) {
        if (current.yawIsReallyClose(target)) {
            return new Rotation(current.getYaw(), target.getPitch());
        }
        return target.subtract(current).normalize().add(current);
    }

    public static Rotation calcRotationFromVec3d(e_2866_D orig, e_2866_D dest, Rotation current) {
        return RotationUtils.wrapAnglesToRelative(current, RotationUtils.calcRotationFromVec3d(orig, dest));
    }

    private static Rotation calcRotationFromVec3d(e_2866_D orig, e_2866_D dest) {
        double[] delta = new double[]{orig.J_1907_R - dest.J_1907_R, orig.R_4764_Y - dest.R_4764_Y, orig.G_564_y - dest.G_564_y};
        double yaw = u_530_F.G_564_y(delta[0], -delta[2]);
        double dist = Math.sqrt(delta[0] * delta[0] + delta[2] * delta[2]);
        double pitch = u_530_F.G_564_y(delta[1], dist);
        return new Rotation((float)(yaw * 57.29577951308232), (float)(pitch * 57.29577951308232));
    }

    public static e_2866_D calcVector3dFromRotation(Rotation rotation) {
        float f = u_530_F.J_1907_R(-rotation.getYaw() * ((float)Math.PI / 180) - (float)Math.PI);
        float f1 = u_530_F.n_1700_B(-rotation.getYaw() * ((float)Math.PI / 180) - (float)Math.PI);
        float f2 = -u_530_F.J_1907_R(-rotation.getPitch() * ((float)Math.PI / 180));
        float f3 = u_530_F.n_1700_B(-rotation.getPitch() * ((float)Math.PI / 180));
        return new e_2866_D(f1 * f2, f3, f * f2);
    }

    public static Optional<Rotation> reachable(IPlayerContext ctx, c_1514_x pos) {
        return RotationUtils.reachable(ctx, pos, false);
    }

    public static Optional<Rotation> reachable(IPlayerContext ctx, c_1514_x pos, boolean wouldSneak) {
        return RotationUtils.reachable(ctx, pos, ctx.playerController().getBlockReachDistance(), wouldSneak);
    }

    public static Optional<Rotation> reachable(IPlayerContext ctx, c_1514_x pos, double blockReachDistance) {
        return RotationUtils.reachable(ctx, pos, blockReachDistance, false);
    }

    public static Optional<Rotation> reachable(IPlayerContext ctx, c_1514_x pos, double blockReachDistance, boolean wouldSneak) {
        Optional<Rotation> possibleRotation;
        if (((Boolean)BaritoneAPI.getSettings().remainWithExistingLookDirection.value).booleanValue() && ctx.isLookingAt(pos)) {
            Rotation hypothetical = ctx.playerRotations().add(new Rotation(0.0f, 1.0E-4f));
            if (wouldSneak) {
                HitResult result = RayTraceUtils.rayTraceTowards(ctx.player(), hypothetical, blockReachDistance, true);
                if (result != null && result.R_4764_Y() == HitResult.n_1700_B.J_1907_R && ((BlockHitResult)result).n_1700_B().equals(pos)) {
                    return Optional.of(hypothetical);
                }
            } else {
                return Optional.of(hypothetical);
            }
        }
        if ((possibleRotation = RotationUtils.reachableCenter(ctx, pos, blockReachDistance, wouldSneak)).isPresent()) {
            return possibleRotation;
        }
        K_4074_S state = ctx.world().getBlockState(pos);
        s_1395_c shape = state.s_956_w(ctx.world(), pos);
        if (shape.J_1907_R()) {
            shape = x_268_Y.J_1907_R();
        }
        for (e_2866_D sideOffset : BLOCK_SIDE_MULTIPLIERS) {
            double xDiff = shape.J_1907_R(b_257_Y.n_1700_B.n_1700_B) * sideOffset.J_1907_R + shape.R_4764_Y(b_257_Y.n_1700_B.n_1700_B) * (1.0 - sideOffset.J_1907_R);
            double yDiff = shape.J_1907_R(b_257_Y.n_1700_B.J_1907_R) * sideOffset.R_4764_Y + shape.R_4764_Y(b_257_Y.n_1700_B.J_1907_R) * (1.0 - sideOffset.R_4764_Y);
            double zDiff = shape.J_1907_R(b_257_Y.n_1700_B.R_4764_Y) * sideOffset.G_564_y + shape.R_4764_Y(b_257_Y.n_1700_B.R_4764_Y) * (1.0 - sideOffset.G_564_y);
            possibleRotation = RotationUtils.reachableOffset(ctx, pos, new e_2866_D(pos.getX(), pos.getY(), pos.getZ()).J_1907_R(xDiff, yDiff, zDiff), blockReachDistance, wouldSneak);
            if (!possibleRotation.isPresent()) continue;
            return possibleRotation;
        }
        return Optional.empty();
    }

    public static Optional<Rotation> reachableOffset(IPlayerContext ctx, c_1514_x pos, e_2866_D offsetPos, double blockReachDistance, boolean wouldSneak) {
        e_2866_D eyes = wouldSneak ? RayTraceUtils.inferSneakingEyePosition(ctx.player()) : ctx.player().u_2550_I(1.0f);
        Rotation rotation = RotationUtils.calcRotationFromVec3d(eyes, offsetPos, ctx.playerRotations());
        Rotation actualRotation = BaritoneAPI.getProvider().getBaritoneForPlayer(ctx.player()).getLookBehavior().getAimProcessor().peekRotation(rotation);
        HitResult result = RayTraceUtils.rayTraceTowards(ctx.player(), actualRotation, blockReachDistance, wouldSneak);
        if (result != null && result.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            if (((BlockHitResult)result).n_1700_B().equals(pos)) {
                return Optional.of(rotation);
            }
            if (ctx.world().getBlockState(pos).J_1907_R() instanceof BaseFireBlock && ((BlockHitResult)result).n_1700_B().equals(pos.down())) {
                return Optional.of(rotation);
            }
        }
        return Optional.empty();
    }

    public static Optional<Rotation> reachableCenter(IPlayerContext ctx, c_1514_x pos, double blockReachDistance, boolean wouldSneak) {
        return RotationUtils.reachableOffset(ctx, pos, VecUtils.calculateBlockCenter(ctx.world(), pos), blockReachDistance, wouldSneak);
    }

    @Deprecated
    public static Optional<Rotation> reachable(V_772_m entity, c_1514_x pos, double blockReachDistance) {
        return RotationUtils.reachable(entity, pos, blockReachDistance, false);
    }

    @Deprecated
    public static Optional<Rotation> reachable(V_772_m entity, c_1514_x pos, double blockReachDistance, boolean wouldSneak) {
        IBaritone baritone = BaritoneAPI.getProvider().getBaritoneForPlayer(entity);
        IPlayerContext ctx = baritone.getPlayerContext();
        return RotationUtils.reachable(ctx, pos, blockReachDistance, wouldSneak);
    }

    @Deprecated
    public static Optional<Rotation> reachableOffset(N_4263_v entity, c_1514_x pos, e_2866_D offsetPos, double blockReachDistance, boolean wouldSneak) {
        e_2866_D eyes = wouldSneak ? RayTraceUtils.inferSneakingEyePosition(entity) : entity.u_2550_I(1.0f);
        Rotation rotation = RotationUtils.calcRotationFromVec3d(eyes, offsetPos, new Rotation(entity.p_178_J, entity.f_4016_n));
        HitResult result = RayTraceUtils.rayTraceTowards(entity, rotation, blockReachDistance, wouldSneak);
        if (result != null && result.R_4764_Y() == HitResult.n_1700_B.J_1907_R) {
            if (((BlockHitResult)result).n_1700_B().equals(pos)) {
                return Optional.of(rotation);
            }
            if (entity.O_508_d.getBlockState(pos).J_1907_R() instanceof BaseFireBlock && ((BlockHitResult)result).n_1700_B().equals(pos.down())) {
                return Optional.of(rotation);
            }
        }
        return Optional.empty();
    }

    @Deprecated
    public static Optional<Rotation> reachableCenter(N_4263_v entity, c_1514_x pos, double blockReachDistance, boolean wouldSneak) {
        return RotationUtils.reachableOffset(entity, pos, VecUtils.calculateBlockCenter(entity.O_508_d, pos), blockReachDistance, wouldSneak);
    }
}


