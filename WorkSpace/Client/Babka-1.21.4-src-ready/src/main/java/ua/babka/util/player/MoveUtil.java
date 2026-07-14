package ua.babka.util.player;

import java.util.Objects;
import net.minecraft.entity.Entity;
import net.minecraft.util.PlayerInput;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import ua.babka.util.IMinecraft;

public final class MoveUtil
implements IMinecraft {
    public static boolean hasPlayerMovement() {
        return MoveUtil.mc.player.input.movementForward != 0.0f || MoveUtil.mc.player.input.movementSideways != 0.0f;
    }

    public static float getMovementDirection() {
        if (MoveUtil.mc.player == null) {
            return 0.0f;
        }
        float forward = MoveUtil.mc.player.input.movementForward;
        float strafe = MoveUtil.mc.player.input.movementSideways;
        if (forward == 0.0f && strafe == 0.0f) {
            return MoveUtil.mc.player.getYaw();
        }
        float yaw = MoveUtil.mc.player.getYaw();
        if (forward != 0.0f) {
            if (strafe > 0.0f) {
                yaw += forward > 0.0f ? -45.0f : 45.0f;
            } else if (strafe < 0.0f) {
                yaw += forward > 0.0f ? 45.0f : -45.0f;
            }
            strafe = 0.0f;
            float f2 = forward = forward > 0.0f ? 1.0f : -1.0f;
        }
        if (strafe > 0.0f) {
            yaw -= 90.0f;
        } else if (strafe < 0.0f) {
            yaw += 90.0f;
        }
        return yaw;
    }

    public static double[] calculateDirection(double distance) {
        float forward = MoveUtil.mc.player.input.movementForward;
        float sideways = MoveUtil.mc.player.input.movementSideways;
        float yaw = MoveUtil.mc.gameRenderer.getCamera().getYaw();
        if (forward != 0.0f) {
            if (sideways > 0.0f) {
                yaw += forward > 0.0f ? -45.0f : 45.0f;
            } else if (sideways < 0.0f) {
                yaw += forward > 0.0f ? 45.0f : -45.0f;
            }
            sideways = 0.0f;
            forward = forward > 0.0f ? 1.0f : -1.0f;
        }
        double sinYaw = Math.sin(Math.toRadians(yaw + 90.0f));
        double cosYaw = Math.cos(Math.toRadians(yaw + 90.0f));
        double xMovement = (double)forward * distance * cosYaw + (double)sideways * distance * sinYaw;
        double zMovement = (double)forward * distance * sinYaw - (double)sideways * distance * cosYaw;
        return new double[]{xMovement, zMovement};
    }

    public static double getSpeedSqrt(Entity entity) {
        double dx = entity.getX() - entity.prevX;
        double dy = entity.getY() - entity.prevY;
        double dz = entity.getZ() - entity.prevZ;
        return Math.sqrt(dx * dx + dz * dz + dy * dy);
    }

    public static void setMotion(double velocity) {
        double[] direction = MoveUtil.calculateDirection(velocity);
        Objects.requireNonNull(MoveUtil.mc.player).setVelocity(direction[0], MoveUtil.mc.player.getVelocity().getY(), direction[1]);
    }

    public static void setMotion(double velocity, double y2) {
        double[] direction = MoveUtil.calculateDirection(velocity);
        Objects.requireNonNull(MoveUtil.mc.player).setVelocity(direction[0], y2, direction[1]);
    }

    public static double getDegreesRelativeToView(Vec3d positionRelativeToPlayer, float yaw) {
        float optimalYaw = (float)Math.atan2(-positionRelativeToPlayer.x, positionRelativeToPlayer.z);
        double currentYaw = Math.toRadians(MathHelper.wrapDegrees((float)yaw));
        return Math.toDegrees(MathHelper.wrapDegrees((double)((double)optimalYaw - currentYaw)));
    }

    public static PlayerInput getDirectionalInputForDegrees(PlayerInput input, double dgs, float deadAngle) {
        boolean forwards = input.comp_3159();
        boolean backwards = input.comp_3160();
        boolean left = input.comp_3161();
        boolean right = input.comp_3162();
        if (dgs >= (double)(-90.0f + deadAngle) && dgs <= (double)(90.0f - deadAngle)) {
            forwards = true;
        } else if (dgs < (double)(-90.0f - deadAngle) || dgs > (double)(90.0f + deadAngle)) {
            backwards = true;
        }
        if (dgs >= (double)(0.0f + deadAngle) && dgs <= (double)(180.0f - deadAngle)) {
            right = true;
        } else if (dgs >= (double)(-180.0f + deadAngle) && dgs <= (double)(0.0f - deadAngle)) {
            left = true;
        }
        return new PlayerInput(forwards, backwards, left, right, input.comp_3163(), input.sneak(), input.comp_3165());
    }

    public static double direction(float rotationYaw, float moveForward, float moveStrafing) {
        if (moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (moveForward < 0.0f) {
            forward = -0.5f;
        }
        if (moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (moveStrafing < 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }

    public static PlayerInput getDirectionalInputForDegrees(PlayerInput input, double dgs) {
        return MoveUtil.getDirectionalInputForDegrees(input, dgs, 20.0f);
    }

    private MoveUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

