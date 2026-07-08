/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1675
 *  net.minecraft.class_238
 *  net.minecraft.class_239
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 *  net.minecraft.class_3966
 */
package code.alphadlc.utils.features.aura.utils;

import code.alphadlc.features.impl.combat.AttackAura;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.aura.warp.Turns;
import net.minecraft.class_1297;
import net.minecraft.class_1675;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3966;
import org.jetbrains.annotations.NotNull;

public final class MathAngle
implements QuickImports {
    public static Turns fromVec2f(class_241 vector2f) {
        return new Turns(vector2f.field_1342, vector2f.field_1343);
    }

    public static float computeAngleDifference(float a, float b) {
        return class_3532.method_15393((float)(a - b));
    }

    public static Turns fromVec3d(class_243 vector) {
        return new Turns((float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(vector.field_1350, vector.field_1352)) - 90.0)), (float)class_3532.method_15338((double)Math.toDegrees(-Math.atan2(vector.field_1351, Math.hypot(vector.field_1352, vector.field_1350)))));
    }

    public static Turns calculateDelta(Turns start, Turns end) {
        float deltaYaw = class_3532.method_15393((float)(end.getYaw() - start.getYaw()));
        float deltaPitch = class_3532.method_15393((float)(end.getPitch() - start.getPitch()));
        return new Turns(deltaYaw, deltaPitch);
    }

    public static Turns calculateAngle(class_243 to) {
        if (MathAngle.mc.field_1724 == null) {
            return new Turns(0.0f, 0.0f);
        }
        return MathAngle.fromVec3d(to.method_1020(MathAngle.mc.field_1724.method_33571()));
    }

    public static Turns pitch(float pitch) {
        if (MathAngle.mc.field_1724 == null) {
            return new Turns(0.0f, pitch);
        }
        return new Turns(MathAngle.mc.field_1724.method_36454(), pitch);
    }

    public static Turns cameraAngle() {
        if (MathAngle.mc.field_1724 == null) {
            return new Turns(0.0f, 0.0f);
        }
        return new Turns(MathAngle.mc.field_1724.method_36454(), MathAngle.mc.field_1724.method_36455());
    }

    public static boolean rayTrace(float yaw, float pitch, float distance, float wallDistance, class_1297 entity) {
        double maxDistance;
        class_238 entityArea;
        class_243 rotationVector;
        class_243 endPoint;
        class_3966 ehr;
        if (MathAngle.mc.field_1724 == null || MathAngle.mc.field_1687 == null) {
            return false;
        }
        class_239 result = MathAngle.rayTrace(distance, yaw, pitch);
        class_243 startPoint = MathAngle.mc.field_1724.method_19538().method_1031(0.0, (double)MathAngle.mc.field_1724.method_18381(MathAngle.mc.field_1724.method_18376()), 0.0);
        double distancePow2 = Math.pow(distance, 2.0);
        AttackAura attackAuraModule1 = AttackAura.getInstance();
        if (result != null) {
            distancePow2 = startPoint.method_1025(result.method_17784());
        }
        if ((ehr = class_1675.method_18075((class_1297)MathAngle.mc.field_1724, (class_243)startPoint, (class_243)(endPoint = startPoint.method_1019(rotationVector = MathAngle.getRotationVector(pitch, yaw).method_1021((double)distance))), (class_238)(entityArea = MathAngle.mc.field_1724.method_5829().method_18804(rotationVector).method_1009(1.0, 1.0, 1.0)), e -> !e.method_7325() && e.method_5863() && e == entity, (double)(maxDistance = Math.max(distancePow2, Math.pow(wallDistance, 2.0))))) != null) {
            double targetHeight;
            double minY;
            double minHitY;
            boolean wallBehindEntity;
            boolean allowedWallDistance = startPoint.method_1025(ehr.method_17784()) <= Math.pow(wallDistance, 2.0);
            boolean wallMissing = result == null;
            boolean bl = wallBehindEntity = startPoint.method_1025(ehr.method_17784()) < distancePow2;
            if (startPoint.method_1025(ehr.method_17784()) <= Math.pow(distance, 2.0) && ehr.method_17784().field_1351 >= (minHitY = (minY = entity.method_23318()) + (targetHeight = (double)entity.method_17682()) * 0.3)) {
                return ehr.method_17782() == entity;
            }
        }
        return false;
    }

    public static float getGCDValue() {
        double sensitivity = (Double)MathAngle.mc.field_1690.method_42495().method_41753();
        double value = sensitivity * 0.6 + 0.2;
        double result = Math.pow(value, 3.0) * 0.8;
        return (float)result * 0.15f;
    }

    public static class_239 rayTrace(double dst, float yaw, float pitch) {
        if (MathAngle.mc.field_1724 == null || MathAngle.mc.field_1687 == null) {
            return null;
        }
        class_243 vec3d = MathAngle.mc.field_1724.method_5836(1.0f);
        class_243 vec3d2 = MathAngle.getRotationVector(pitch, yaw);
        class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * dst, vec3d2.field_1351 * dst, vec3d2.field_1350 * dst);
        return MathAngle.mc.field_1687.method_17742(new class_3959(vec3d, vec3d3, class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)MathAngle.mc.field_1724));
    }

    @NotNull
    public static class_243 getRotationVector(float yaw, float pitch) {
        return new class_243((double)(class_3532.method_15374((float)(-pitch * ((float)Math.PI / 180))) * class_3532.method_15362((float)(yaw * ((float)Math.PI / 180)))), (double)(-class_3532.method_15374((float)(yaw * ((float)Math.PI / 180)))), (double)(class_3532.method_15362((float)(-pitch * ((float)Math.PI / 180))) * class_3532.method_15362((float)(yaw * ((float)Math.PI / 180)))));
    }

    public static class_243 getClosestVec(class_1297 entity) {
        if (MathAngle.mc.field_1724 == null || entity == null) {
            return class_243.field_1353;
        }
        class_243 eyePosVec = MathAngle.mc.field_1724.method_33571();
        class_238 boundingBox = entity.method_5829();
        return new class_243(class_3532.method_15350((double)eyePosVec.field_1352, (double)boundingBox.field_1323, (double)boundingBox.field_1320), class_3532.method_15350((double)eyePosVec.field_1351, (double)boundingBox.field_1322, (double)boundingBox.field_1325), class_3532.method_15350((double)eyePosVec.field_1350, (double)boundingBox.field_1321, (double)boundingBox.field_1324));
    }

    public static double getDistanceEyePos(class_1297 target) {
        if (MathAngle.mc.field_1724 == null || target == null) {
            return 0.0;
        }
        class_243 closestHitboxPoint = MathAngle.getClosestVec(target);
        return MathAngle.mc.field_1724.method_33571().method_1022(closestHitboxPoint);
    }

    public static double getStrictDistance(class_1297 entity) {
        return MathAngle.getDistanceEyePos(entity);
    }

    private MathAngle() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
