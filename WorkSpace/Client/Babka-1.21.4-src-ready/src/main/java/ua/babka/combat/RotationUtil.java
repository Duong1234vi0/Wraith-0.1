package ua.babka.combat;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import ua.babka.core.Rotation;
import ua.babka.util.IMinecraft;

public final class RotationUtil
implements IMinecraft {
    public static Vec2f sample(Vec3d fromVec, Vec3d toVec) {
        double TO_DEGREES = 57.29577951308232;
        Vec3d diff = toVec.subtract(fromVec);
        double distance = Math.hypot(diff.x, diff.z);
        float yaw = (float)(MathHelper.atan2((double)diff.z, (double)diff.x) * 57.29577951308232) - 90.0f;
        float pitch = (float)(-(MathHelper.atan2((double)diff.y, (double)distance) * 57.29577951308232));
        return new Vec2f(yaw, pitch);
    }

    public static Vec2f sample(Entity entity) {
        return RotationUtil.sample(entity.getPos().add(0.0, (double)entity.getEyeHeight(entity.getPose()), 0.0));
    }

    public static Vec2f sample(Vec3d toVec) {
        return RotationUtil.sample(RotationUtil.mc.player.getPos().add(0.0, (double)RotationUtil.mc.player.getEyeHeight(RotationUtil.mc.player.getPose()), 0.0), toVec);
    }

    public static float getAngleDifference(float dir, float yaw) {
        float f2 = Math.abs(yaw - dir) % 360.0f;
        return f2 > 180.0f ? 360.0f - f2 : f2;
    }

    public static Vec3d getEyesPos(Entity entity) {
        return entity.getPos().add(0.0, (double)entity.getEyeHeight(entity.getPose()), 0.0);
    }

    public static float[] calculateAngle(Vec3d to) {
        return RotationUtil.calculateAngle(RotationUtil.getEyesPos((Entity)RotationUtil.mc.player), to);
    }

    public static float[] calculateAngle(Vec3d from, Vec3d to) {
        double difX = to.x - from.x;
        double difY = (to.y - from.y) * -1.0;
        double difZ = to.z - from.z;
        double dist = MathHelper.sqrt((float)((float)(difX * difX + difZ * difZ)));
        float yD = (float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0));
        float pD = (float)MathHelper.clamp((double)MathHelper.wrapDegrees((double)Math.toDegrees(Math.atan2(difY, dist))), (double)-90.0, (double)90.0);
        return new float[]{yD, pD};
    }

    public static Vector3f getDirectionVector(float yaw, float pitch) {
        float yawRadians = (float)Math.toRadians(yaw);
        float pitchRadians = (float)Math.toRadians(pitch);
        float x2 = -MathHelper.cos((float)pitchRadians) * MathHelper.sin((float)yawRadians);
        float y2 = -MathHelper.sin((float)pitchRadians);
        float z2 = MathHelper.cos((float)pitchRadians) * MathHelper.cos((float)yawRadians);
        return new Vector3f(x2, y2, z2);
    }

    public static float calculateFov(float cameraYaw, float cameraPitch, float targetYaw, float targetPitch) {
        Vector3f cameraDirection = RotationUtil.getDirectionVector(cameraYaw, cameraPitch);
        Vector3f targetDirection = RotationUtil.getDirectionVector(targetYaw, targetPitch);
        float dotProduct = cameraDirection.dot((Vector3fc)targetDirection);
        dotProduct = MathHelper.clamp((float)dotProduct, (float)-1.0f, (float)1.0f);
        float angleRadians = (float)Math.acos(dotProduct);
        return (float)Math.toDegrees(angleRadians);
    }

    public static Rotation fromVec3d(Vec3d vector) {
        return new Rotation((float)MathHelper.wrapDegrees((double)(Math.toDegrees(Math.atan2(vector.z, vector.x)) - 90.0)), (float)MathHelper.wrapDegrees((double)Math.toDegrees(-Math.atan2(vector.y, Math.hypot(vector.x, vector.z)))));
    }

    private RotationUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

