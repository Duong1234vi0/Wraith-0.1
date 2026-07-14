/*
 * Decompiled with CFR 0.152.
 */
package lightning.product;

import lightning.product.F_3698_k;
import lightning.product.I_1170_F;
import lightning.product.I_4817_s;
import lightning.product.N_4263_v;
import lightning.product.P_3504_Q;
import lightning.product.MinecraftAccess;
import lightning.product.e_2866_D;
import lightning.product.ClientBootstrap;
import lightning.product.r_4811_B;
import lightning.product.u_530_F;

public class a_1344_X {
    public static float n_1700_B = 0.0f;
    public static float J_1907_R = 0.0f;

    public static e_2866_D n_1700_B(e_2866_D vec, I_4817_s AABB) {
        return new e_2866_D(u_530_F.n_1700_B(vec.n_1700_B(), AABB.minX, AABB.maxX), u_530_F.n_1700_B(vec.J_1907_R(), AABB.minY, AABB.maxY), u_530_F.n_1700_B(vec.R_4764_Y(), AABB.minZ, AABB.maxZ));
    }

    public static e_2866_D n_1700_B(e_2866_D vec, N_4263_v entity) {
        return a_1344_X.n_1700_B(vec, entity.i_601_W());
    }

    public static e_2866_D n_1700_B(N_4263_v entity) {
        e_2866_D eyePosVec = MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(MinecraftAccess.c_3005_b.RealmsClientConfig());
        if (entity instanceof r_4811_B) {
            boolean shouldPredict;
            r_4811_B target = (r_4811_B)entity;
            F_3698_k elytraForward = ClientBootstrap.Y_601_j().J_1907_R().R_4764_Y();
            boolean bl = shouldPredict = elytraForward != null && elytraForward.h_1847_R() && MinecraftAccess.c_3005_b.Y_259_p.k_578_l() && target.k_578_l();
            if (shouldPredict) {
                double dz;
                double dy;
                double dx = target.O_3598_v() - target.r_715_M;
                double blockMoveSq = dx * dx + (dy = target.X_2960_b() - target.A_1038_p) * dy + (dz = target.l_2647_k() - target.i_1637_u) * dz;
                if (blockMoveSq < 1.0) {
                    e_2866_D legPos = new e_2866_D(target.O_3598_v(), target.X_2960_b(), target.l_2647_k());
                    return legPos.G_564_y(eyePosVec);
                }
                float forward = ((Float)F_3698_k.u_2550_I.J_1907_R()).floatValue();
                e_2866_D targetPos = target.s_4990_V().J_1907_R(0.0, target.v_165_F() / 2.0f, 0.0);
                e_2866_D velocity = target.I_4348_c();
                e_2866_D velDir = velocity.v_4262_N() > 1.0E-4 ? velocity.G_564_y() : e_2866_D.n_1700_B;
                e_2866_D lookVec = target.RealmsSettingsScreen();
                e_2866_D combinedDir = lookVec.P_1922_E(velDir).G_564_y();
                e_2866_D predictedPos = targetPos.P_1922_E(combinedDir.n_1700_B((double)forward));
                e_2866_D predictedLegPos = new e_2866_D(predictedPos.J_1907_R, predictedPos.R_4764_Y - (double)target.v_165_F() / 2.0, predictedPos.G_564_y);
                return predictedLegPos.G_564_y(eyePosVec);
            }
        }
        return a_1344_X.n_1700_B(eyePosVec, entity).G_564_y(eyePosVec);
    }

    public static e_2866_D J_1907_R(N_4263_v entity) {
        e_2866_D eye = MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(1.0f);
        e_2866_D raw = new e_2866_D(entity.s_4990_V().J_1907_R, u_530_F.G_564_y(u_530_F.n_1700_B(eye.u_1723_Y(entity.s_4990_V().J_1907_R(0.0, entity.X_1313_W(), 0.0)) / 3.0, 0.0, 1.0), entity.i_601_W().minY, u_530_F.n_1700_B(eye.R_4764_Y, entity.i_601_W().minY, entity.i_601_W().maxY)), entity.s_4990_V().G_564_y).G_564_y(eye);
        double distance = MinecraftAccess.c_3005_b.Y_259_p.t_148_a(entity);
        return raw.G_564_y().n_1700_B(distance);
    }

    public static boolean n_1700_B() {
        boolean cannotMoveUp;
        boolean blockAboveHead = a_1344_X.J_1907_R();
        boolean bl = cannotMoveUp = !a_1344_X.R_4764_Y();
        if (!(MinecraftAccess.c_3005_b.Y_259_p.RowButton() || MinecraftAccess.c_3005_b.Y_259_p.e_() || MinecraftAccess.c_3005_b.Y_259_p.C_415_h.J_1907_R)) {
            return MinecraftAccess.c_3005_b.Y_259_p.M_1641_O() && blockAboveHead && cannotMoveUp;
        }
        return false;
    }

    public static boolean J_1907_R() {
        double upHeight = !MinecraftAccess.c_3005_b.Y_259_p.M_1641_O() ? 1.5 : 2.5;
        I_4817_s collisionBox = a_1344_X.n_1700_B(upHeight);
        return MinecraftAccess.c_3005_b.Y_601_j.J_1907_R(MinecraftAccess.c_3005_b.Y_259_p, collisionBox).iterator().hasNext();
    }

    public static boolean R_4764_Y() {
        boolean isCrouchingAndJumping = MinecraftAccess.c_3005_b.Y_259_p.h_4320_q() == I_1170_F.u_1723_Y && MinecraftAccess.c_3005_b.P_4830_p.Ping.G_564_y();
        double upHeight = !isCrouchingAndJumping ? (double)MinecraftAccess.c_3005_b.Y_259_p.v_165_F() + 0.2 : (double)MinecraftAccess.c_3005_b.Y_259_p.v_165_F() + 0.05;
        I_4817_s upMoveBox = a_1344_X.n_1700_B(upHeight);
        return !MinecraftAccess.c_3005_b.Y_601_j.J_1907_R(MinecraftAccess.c_3005_b.Y_259_p, upMoveBox).iterator().hasNext();
    }

    private static I_4817_s n_1700_B(double heightOffset) {
        return new I_4817_s(MinecraftAccess.c_3005_b.Y_259_p.O_3598_v() - 0.3, MinecraftAccess.c_3005_b.Y_259_p.X_2960_b() + (double)MinecraftAccess.c_3005_b.Y_259_p.v_165_F(), MinecraftAccess.c_3005_b.Y_259_p.l_2647_k() - 0.3, MinecraftAccess.c_3005_b.Y_259_p.O_3598_v() + 0.3, MinecraftAccess.c_3005_b.Y_259_p.X_2960_b() + heightOffset, MinecraftAccess.c_3005_b.Y_259_p.l_2647_k() + 0.3);
    }

    public static e_2866_D n_1700_B(r_4811_B entity) {
        e_2866_D eye = MinecraftAccess.c_3005_b.Y_259_p.u_2550_I(MinecraftAccess.c_3005_b.RealmsClientConfig());
        e_2866_D targetPos = entity.i_601_W().getCenter();
        return targetPos.G_564_y(eye);
    }

    public static P_3504_Q n_1700_B(P_3504_Q currentRotation, e_2866_D targetVec) {
        float yaw = (float)Math.toDegrees(Math.atan2(targetVec.G_564_y, targetVec.J_1907_R)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(targetVec.R_4764_Y, Math.sqrt(targetVec.J_1907_R * targetVec.J_1907_R + targetVec.G_564_y * targetVec.G_564_y))));
        return new P_3504_Q(yaw, pitch);
    }

    public static float n_1700_B(float min, float max) {
        return min + (float)Math.random() * (max - min);
    }

    public static float n_1700_B(float speed, float current, float target) {
        return current + (target - current) * speed;
    }

    public static void G_564_y() {
        n_1700_B = 0.0f;
        J_1907_R = 0.0f;
    }
}



