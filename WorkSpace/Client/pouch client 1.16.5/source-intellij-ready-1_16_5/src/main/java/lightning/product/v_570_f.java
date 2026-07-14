/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.BlockHitResult;
import lightning.product.ClipContext;
import lightning.product.HitResult;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.BlockTags;

public class v_570_f {
    public static BlockHitResult n_1700_B(double distance, float yaw, float pitch) {
        e_2866_D eyeVec = MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(MinecraftAccess.c_3005_b.RealmsClientConfig());
        e_2866_D lookVec = MinecraftAccess.c_3005_b.Y_259_p.G_564_y(pitch, yaw);
        e_2866_D endVec = eyeVec.P_1922_E(lookVec.n_1700_B(distance));
        ClipContext context = new ClipContext(eyeVec, endVec, ClipContext.n_1700_B.J_1907_R, ClipContext.J_1907_R.n_1700_B, MinecraftAccess.c_3005_b.Y_259_p);
        return MinecraftAccess.c_3005_b.Y_601_j.n_1700_B(context);
    }

    public static boolean n_1700_B(float yaw, float pitch, double distance, N_4263_v entity, boolean raytraceblock) {
        return v_570_f.n_1700_B(yaw, pitch, distance, entity, raytraceblock, false);
    }

    public static boolean n_1700_B(float yaw, float pitch, double distance, N_4263_v entity, boolean raytraceblock, boolean allowDoorHits) {
        e_2866_D hitPoint;
        ClipContext context;
        BlockHitResult blockTrace;
        boolean intersects;
        e_2866_D eyeVec = MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(MinecraftAccess.c_3005_b.RealmsClientConfig());
        e_2866_D lookVec = MinecraftAccess.c_3005_b.Y_259_p.G_564_y(pitch, yaw);
        e_2866_D endVec = eyeVec.P_1922_E(lookVec.n_1700_B(distance));
        I_4817_s aabb = entity.i_601_W();
        boolean bl = intersects = aabb.contains(eyeVec) || aabb.rayTrace(eyeVec, endVec).isPresent();
        if (!intersects) {
            return false;
        }
        if (raytraceblock && (blockTrace = MinecraftAccess.c_3005_b.Y_601_j.n_1700_B(context = new ClipContext(eyeVec, hitPoint = aabb.contains(eyeVec) ? eyeVec : aabb.rayTrace(eyeVec, endVec).get(), ClipContext.n_1700_B.J_1907_R, ClipContext.J_1907_R.n_1700_B, MinecraftAccess.c_3005_b.Y_259_p))).R_4764_Y() != HitResult.n_1700_B.n_1700_B) {
            return allowDoorHits && blockTrace.n_1700_B() != null && MinecraftAccess.c_3005_b.Y_601_j.getBlockState(blockTrace.n_1700_B()).n_1700_B(BlockTags.M_182_A);
        }
        return true;
    }
}



