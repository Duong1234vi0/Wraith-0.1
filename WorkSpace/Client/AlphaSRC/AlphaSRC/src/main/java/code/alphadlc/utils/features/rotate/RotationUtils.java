/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2350
 *  net.minecraft.class_238
 *  net.minecraft.class_239
 *  net.minecraft.class_241
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_3959
 *  net.minecraft.class_3959$class_242
 *  net.minecraft.class_3959$class_3960
 */
package code.alphadlc.utils.features.rotate;

import code.alphadlc.features.impl.combat.components.gcd.GCDUtil;
import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.features.rotate.Rotation;
import net.minecraft.class_1297;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_239;
import net.minecraft.class_241;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import org.jetbrains.annotations.NotNull;

public final class RotationUtils
implements QuickImports {
    public static class_239 rayTrace(double dst, float yaw, float pitch) {
        class_243 vec3d = RotationUtils.mc.field_1724.method_5836(1.0f);
        class_243 vec3d2 = RotationUtils.getRotationVector(pitch, yaw);
        class_243 vec3d3 = vec3d.method_1031(vec3d2.field_1352 * dst, vec3d2.field_1351 * dst, vec3d2.field_1350 * dst);
        return RotationUtils.mc.field_1687.method_17742(new class_3959(vec3d, vec3d3, class_3959.class_3960.field_17559, class_3959.class_242.field_1348, (class_1297)RotationUtils.mc.field_1724));
    }

    static class_243 getBestVector(class_1297 entity) {
        class_243 eyePos = RotationUtils.mc.field_1724.method_33571();
        class_238 box = entity.method_5829();
        double step = 0.1;
        class_243 bestVec = null;
        double closestDistance = Double.MAX_VALUE;
        for (double x = box.field_1323; x <= box.field_1320; x += step) {
            for (double y = box.field_1322; y <= box.field_1325; y += step) {
                for (double z = box.field_1321; z <= box.field_1324; z += step) {
                    class_243 sample = new class_243(x, y, z);
                    double dist = eyePos.method_1022(sample);
                    if (!(dist < closestDistance)) continue;
                    closestDistance = dist;
                    bestVec = sample;
                }
            }
        }
        return bestVec;
    }

    public static Rotation fromVec3d(class_243 vector) {
        return new Rotation((float)class_3532.method_15338((double)(Math.toDegrees(Math.atan2(vector.field_1350, vector.field_1352)) - 90.0)), (float)class_3532.method_15338((double)Math.toDegrees(-Math.atan2(vector.field_1351, Math.hypot(vector.field_1352, vector.field_1350)))));
    }

    @NotNull
    public static class_243 getRotationVector(float yaw, float pitch) {
        return new class_243((double)(class_3532.method_15374((float)(-pitch * ((float)Math.PI / 180))) * class_3532.method_15362((float)(yaw * ((float)Math.PI / 180)))), (double)(-class_3532.method_15374((float)(yaw * ((float)Math.PI / 180)))), (double)(class_3532.method_15362((float)(-pitch * ((float)Math.PI / 180))) * class_3532.method_15362((float)(yaw * ((float)Math.PI / 180)))));
    }

    public static class_241 getRotations(class_1297 entity) {
        return RotationUtils.getRotations(entity.method_23317(), entity.method_23318(), entity.method_23321());
    }

    public static class_241 getRotations(class_243 vec3d) {
        return RotationUtils.getRotations(vec3d.field_1352, vec3d.field_1351, vec3d.field_1350);
    }

    public static class_241 getRotations(double x, double y, double z) {
        double deltaX = x - RotationUtils.mc.field_1724.method_23317();
        double deltaY = y - RotationUtils.mc.field_1724.method_23320();
        double deltaZ = z - RotationUtils.mc.field_1724.method_23321();
        double distance = class_3532.method_15355((float)((float)(deltaX * deltaX + deltaZ * deltaZ)));
        float yaw = (float)(class_3532.method_15349((double)deltaZ, (double)deltaX) * 57.29577951308232 - 90.0);
        float pitch = (float)(-class_3532.method_15349((double)deltaY, (double)distance) * 57.29577951308232);
        return new class_241(yaw, pitch);
    }

    public static float[] getRotations(class_2350 direction) {
        float[] fArray;
        switch (direction) {
            default: {
                throw new MatchException(null, null);
            }
            case field_11033: {
                float[] fArray2 = new float[2];
                fArray2[0] = RotationUtils.mc.field_1724.method_36454();
                fArray = fArray2;
                fArray2[1] = 90.0f;
                break;
            }
            case field_11036: {
                float[] fArray3 = new float[2];
                fArray3[0] = RotationUtils.mc.field_1724.method_36454();
                fArray = fArray3;
                fArray3[1] = -90.0f;
                break;
            }
            case field_11043: {
                float[] fArray4 = new float[2];
                fArray4[0] = 180.0f;
                fArray = fArray4;
                fArray4[1] = RotationUtils.mc.field_1724.method_36455();
                break;
            }
            case field_11035: {
                float[] fArray5 = new float[2];
                fArray5[0] = 0.0f;
                fArray = fArray5;
                fArray5[1] = RotationUtils.mc.field_1724.method_36455();
                break;
            }
            case field_11039: {
                float[] fArray6 = new float[2];
                fArray6[0] = 90.0f;
                fArray = fArray6;
                fArray6[1] = RotationUtils.mc.field_1724.method_36455();
                break;
            }
            case field_11034: {
                float[] fArray7 = new float[2];
                fArray7[0] = -90.0f;
                fArray = fArray7;
                fArray7[1] = RotationUtils.mc.field_1724.method_36455();
            }
        }
        return fArray;
    }

    public static float[] correctRotation(float[] rotations) {
        rotations[0] = rotations[0] - rotations[0] % GCDUtil.getGCDValue();
        rotations[1] = rotations[1] - rotations[1] % GCDUtil.getGCDValue();
        return new float[]{rotations[0], rotations[1]};
    }

    public static float getFixRotate(float rot) {
        return RotationUtils.getDeltaMouse(rot) * GCDUtil.getGCDValue();
    }

    public static float getDeltaMouse(float delta) {
        return Math.round(delta / GCDUtil.getGCDValue());
    }

    private RotationUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
