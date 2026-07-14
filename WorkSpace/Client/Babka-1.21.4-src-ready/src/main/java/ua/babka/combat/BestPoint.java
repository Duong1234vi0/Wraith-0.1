package ua.babka.combat;

import net.minecraft.entity.Entity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import ua.babka.combat.RaytraceUtil;
import ua.babka.combat.RotationUtil;
import ua.babka.core.Rotation;
import ua.babka.util.IMinecraft;
import ua.babka.util.math.MathUtil;

public final class BestPoint
implements IMinecraft {
    private static Vec3d rotationPoint = Vec3d.ZERO;
    private static Vec3d rotationMotion = Vec3d.ZERO;

    public static Vec3d getRotationPoint() {
        return rotationPoint;
    }

    public static Vec3d getNearestPoint(Entity entity) {
        Box box = entity.getBoundingBox();
        double step = 0.1;
        Vec3d bestVec = null;
        double closestDistance = Double.MAX_VALUE;
        for (double x2 = box.minX; x2 <= box.maxX; x2 += step) {
            for (double y2 = box.minY; y2 <= box.maxY; y2 += step) {
                for (double z2 = box.minZ; z2 <= box.maxZ; z2 += step) {
                    Vec3d sample = new Vec3d(x2, y2, z2);
                    double dist = BestPoint.mc.player.getEyePos().distanceTo(sample);
                    if (!(dist < closestDistance)) continue;
                    closestDistance = dist;
                    bestVec = sample;
                }
            }
        }
        return bestVec;
    }

    public static Vec3d getPoint(Entity target) {
        Box box = target.getBoundingBox();
        double width = box.maxX - box.minX;
        double height = box.maxY - box.minY;
        double depth = box.maxZ - box.minZ;
        double baseX = box.minX + width / 2.0;
        double baseY = box.minY + height * 0.7;
        double baseZ = box.minZ + depth / 2.0;
        double time = (double)System.currentTimeMillis() / 50.0;
        int id = target.getId();
        double offsetX = Math.sin(time + (double)id) * (width * 0.45);
        double offsetY = Math.cos(time * 0.8 + (double)id) * (height * 0.1);
        double offsetZ = Math.cos(time * 1.2 + (double)id) * (depth * 0.45);
        return new Vec3d(baseX + offsetX, baseY + offsetY, baseZ + offsetZ);
    }

    public static Vec3d getPoint2(Entity target) {
        Box box = target.getBoundingBox();
        double width = box.maxX - box.minX;
        double height = box.maxY - box.minY;
        double depth = box.maxZ - box.minZ;
        double baseX = box.minX + width / 2.0;
        double baseY = box.minY + height * 0.65;
        double baseZ = box.minZ + depth / 2.0;
        double time = (double)System.currentTimeMillis() / 65.0;
        int id = target.getId();
        double offsetX = Math.sin(time + (double)id) * (width * 0.7);
        double offsetY = Math.cos(time * 0.8 + (double)id) * (height * 0.4);
        double offsetZ = Math.cos(time * 1.2 + (double)id) * (depth * 0.7);
        return new Vec3d(baseX + offsetX, baseY + offsetY, baseZ + offsetZ);
    }

    public static Vec3d getNearestVisiblePoint(Entity target, Vec3d preferredPoint, double range) {
        if (preferredPoint == null || BestPoint.mc.player == null || BestPoint.mc.world == null) {
            return preferredPoint;
        }
        if (BestPoint.isPointVisible(target, preferredPoint, range)) {
            return preferredPoint;
        }
        Box box = target.getBoundingBox();
        double step = 0.12;
        Vec3d bestPoint = null;
        double bestDistance = Double.MAX_VALUE;
        for (double x2 = box.minX; x2 <= box.maxX; x2 += step) {
            for (double y2 = box.minY; y2 <= box.maxY; y2 += step) {
                for (double z2 = box.minZ; z2 <= box.maxZ; z2 += step) {
                    double distanceToCurrent;
                    Vec3d sample = new Vec3d(x2, y2, z2);
                    if (!BestPoint.isPointVisible(target, sample, range) || !((distanceToCurrent = sample.squaredDistanceTo(preferredPoint)) < bestDistance)) continue;
                    bestDistance = distanceToCurrent;
                    bestPoint = sample;
                }
            }
        }
        return bestPoint != null ? bestPoint : preferredPoint;
    }

    private static boolean isPointVisible(Entity target, Vec3d point, double range) {
        Vec3d eyePos = BestPoint.mc.player.getEyePos();
        double distance = eyePos.distanceTo(point);
        if (distance > range) {
            return false;
        }
        Vec3d direction = point.subtract(eyePos).normalize();
        if (!RaytraceUtil.rayTrace(direction, distance + 0.2, target.getBoundingBox())) {
            return false;
        }
        BlockHitResult blockHit = RaytraceUtil.raycast(eyePos, point, RaycastContext.ShapeType.COLLIDER, (Entity)BestPoint.mc.player);
        return blockHit.getType() == HitResult.Type.MISS || eyePos.squaredDistanceTo(blockHit.getPos()) >= eyePos.squaredDistanceTo(point) - 1.0E-4;
    }

    public static Vec3d getMultipoint(Entity target, double distance) {
        float minMotionXZ = 0.005f;
        float maxMotionXZ = 0.015f;
        float minMotionY = 0.0015f;
        float maxMotionY = 0.015f;
        double lenghtX = target.getBoundingBox().getLengthX();
        double lenghtY = target.getBoundingBox().getLengthY();
        double lenghtZ = target.getBoundingBox().getLengthZ();
        if (rotationMotion.equals((Object)Vec3d.ZERO)) {
            rotationMotion = new Vec3d((double)MathUtil.random(-0.02f, 0.02f), (double)MathUtil.random(-0.02f, 0.02f), (double)MathUtil.random(-0.02f, 0.02f));
        }
        if (rotationPoint.equals((Object)Vec3d.ZERO)) {
            rotationPoint = new Vec3d(0.0, lenghtY * 0.5, 0.0);
        }
        rotationPoint = rotationPoint.add(rotationMotion);
        double safeX = (lenghtX - 0.1) / 2.0;
        double safeZ = (lenghtZ - 0.1) / 2.0;
        if (BestPoint.rotationPoint.x >= safeX) {
            rotationMotion = new Vec3d((double)(-MathUtil.random(minMotionXZ, maxMotionXZ)), rotationMotion.getY(), rotationMotion.getZ());
        } else if (BestPoint.rotationPoint.x <= -safeX) {
            rotationMotion = new Vec3d((double)MathUtil.random(minMotionXZ, maxMotionXZ), rotationMotion.getY(), rotationMotion.getZ());
        }
        if (BestPoint.rotationPoint.y >= lenghtY * 0.75) {
            rotationMotion = new Vec3d(rotationMotion.getX(), (double)(-MathUtil.random(minMotionY, maxMotionY)), rotationMotion.getZ());
        } else if (BestPoint.rotationPoint.y <= lenghtY * 0.3) {
            rotationMotion = new Vec3d(rotationMotion.getX(), (double)MathUtil.random(minMotionY, maxMotionY), rotationMotion.getZ());
        }
        if (BestPoint.rotationPoint.z >= safeZ) {
            rotationMotion = new Vec3d(rotationMotion.getX(), rotationMotion.getY(), (double)(-MathUtil.random(minMotionXZ, maxMotionXZ)));
        } else if (BestPoint.rotationPoint.z <= -safeZ) {
            rotationMotion = new Vec3d(rotationMotion.getX(), rotationMotion.getY(), (double)MathUtil.random(minMotionXZ, maxMotionXZ));
        }
        rotationPoint.add((double)MathUtil.random(-0.05f, 0.05f), 0.0, (double)MathUtil.random(-0.05f, 0.05f));
        if (!RaytraceUtil.rayTrace(BestPoint.mc.player.getRotationVector(), distance, target.getBoundingBox())) {
            float halfBox = (float)(lenghtX / 2.0) * 0.8f;
            for (float x1 = -halfBox; x1 <= halfBox; x1 += 0.1f) {
                for (float z1 = -halfBox; z1 <= halfBox; z1 += 0.1f) {
                    float y1 = (float)(lenghtY * 0.9);
                    while ((double)y1 >= lenghtY * 0.3) {
                        Vec3d v1 = new Vec3d(target.getX() + (double)x1, target.getY() + (double)y1, target.getZ() + (double)z1);
                        Rotation rotation = RotationUtil.fromVec3d(v1);
                        if (RaytraceUtil.rayTrace(rotation.toVector(), distance, target.getBoundingBox())) {
                            rotationPoint = new Vec3d((double)x1, (double)y1, (double)z1);
                            return target.getPos().add(rotationPoint);
                        }
                        y1 -= 0.1f;
                    }
                }
            }
        }
        return target.getPos().add(rotationPoint);
    }

    private BestPoint() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

