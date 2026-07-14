package ua.babka.combat;

import java.util.Objects;
import java.util.function.Predicate;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import ua.babka.core.Rotation;
import ua.babka.util.IMinecraft;

public final class RaytraceUtil
implements IMinecraft {
    public static BlockHitResult raycast(double range, Rotation angle, boolean includeFluids) {
        return RaytraceUtil.raycast(Objects.requireNonNull(RaytraceUtil.mc.player).getCameraPosVec(1.0f), range, angle, includeFluids);
    }

    public static BlockHitResult raycast(Vec3d vec, double range, Rotation angle, boolean includeFluids) {
        Entity entity = RaytraceUtil.mc.cameraEntity;
        if (entity == null) {
            return null;
        }
        Vec3d rotationVec = angle.toVector();
        Vec3d end = vec.add(rotationVec.x * range, rotationVec.y * range, rotationVec.z * range);
        ClientWorld world = RaytraceUtil.mc.world;
        if (world == null) {
            return null;
        }
        RaycastContext.FluidHandling fluidHandling = includeFluids ? RaycastContext.FluidHandling.ANY : RaycastContext.FluidHandling.NONE;
        RaycastContext context = new RaycastContext(vec, end, RaycastContext.ShapeType.OUTLINE, fluidHandling, entity);
        return world.raycast(context);
    }

    public static BlockHitResult raycast(Vec3d start, Vec3d end, RaycastContext.ShapeType shapeType) {
        return RaytraceUtil.raycast(start, end, shapeType, (Entity)RaytraceUtil.mc.player);
    }

    public static BlockHitResult raycast(Vec3d start, Vec3d end, RaycastContext.ShapeType shapeType, Entity entity) {
        return RaytraceUtil.mc.world.raycast(new RaycastContext(start, end, shapeType, RaycastContext.FluidHandling.NONE, entity));
    }

    public static EntityHitResult raytraceEntity(double range, Rotation angle, Predicate<Entity> filter) {
        Entity entity = RaytraceUtil.mc.cameraEntity;
        if (entity == null) {
            return null;
        }
        Vec3d cameraVec = entity.getCameraPosVec(1.0f);
        Vec3d rotationVec = angle.toVector();
        Vec3d vec3d3 = cameraVec.add(rotationVec.x * range, rotationVec.y * range, rotationVec.z * range);
        Box box = entity.getBoundingBox().stretch(rotationVec.multiply(range)).expand(1.0, 1.0, 1.0);
        return ProjectileUtil.raycast((Entity)entity, (Vec3d)cameraVec, (Vec3d)vec3d3, (Box)box, e2 -> !e2.isSpectator() && filter.test((Entity)e2), (double)(range * range));
    }

    public static boolean rayTrace(Vec3d clientVec, double range, Box box) {
        Vec3d cameraVec = Objects.requireNonNull(RaytraceUtil.mc.player).getEyePos();
        return box.contains(cameraVec) || box.raycast(cameraVec, cameraVec.add(clientVec.multiply(range))).isPresent();
    }

    private RaytraceUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

