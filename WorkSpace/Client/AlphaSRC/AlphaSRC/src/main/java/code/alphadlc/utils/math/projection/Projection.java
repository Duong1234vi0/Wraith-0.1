/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_2338
 *  net.minecraft.class_2374
 *  net.minecraft.class_238
 *  net.minecraft.class_243
 *  net.minecraft.class_3532
 *  net.minecraft.class_4184
 *  net.minecraft.class_4604
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Vector3f
 *  org.joml.Vector4d
 *  org.joml.Vector4f
 */
package code.alphadlc.utils.math.projection;

import code.alphadlc.utils.display.interfaces.QuickImports;
import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.features.aura.utils.MathAngle;
import code.alphadlc.utils.features.aura.warp.Turns;
import code.alphadlc.utils.math.calc.Calculate;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_2374;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_4604;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4d;
import org.joml.Vector4f;

public final class Projection
implements QuickImports {
    private static final Vector4f TRANSFORMED = new Vector4f();
    private static final Vector3f PROJECTED = new Vector3f();
    private static final Matrix4f PROJ_MATRIX = new Matrix4f();
    private static final int[] VIEWPORT = new int[4];
    private static final double[] SCREEN_SCRATCH = new double[3];

    @NotNull
    public static class_243 worldSpaceToScreenSpace(class_243 pos) {
        return Projection.worldSpaceToScreenSpace(pos.field_1352, pos.field_1351, pos.field_1350);
    }

    @NotNull
    public static class_243 worldSpaceToScreenSpace(double x, double y, double z) {
        if (Projection.worldSpaceToScreenSpaceInto(x, y, z, SCREEN_SCRATCH)) {
            return new class_243(SCREEN_SCRATCH[0], SCREEN_SCRATCH[1], SCREEN_SCRATCH[2]);
        }
        return new class_243(0.0, 0.0, -1.0);
    }

    public static boolean worldSpaceToScreenSpaceInto(double x, double y, double z, double[] out) {
        TRANSFORMED.set((float)x, (float)y, (float)z, 1.0f).mul((Matrix4fc)Render3D.lastWorldSpaceMatrix.method_23761());
        PROJ_MATRIX.set((Matrix4fc)Render3D.lastProjMat);
        Projection.VIEWPORT[0] = 0;
        Projection.VIEWPORT[1] = 0;
        Projection.VIEWPORT[2] = mc.method_22683().method_4489();
        Projection.VIEWPORT[3] = mc.method_22683().method_4506();
        PROJ_MATRIX.project(TRANSFORMED.x(), TRANSFORMED.y(), TRANSFORMED.z(), VIEWPORT, PROJECTED);
        double depth = Projection.PROJECTED.z;
        if (depth <= 0.0) {
            return false;
        }
        double scale = mc.method_22683().method_4495();
        out[0] = (double)Projection.PROJECTED.x / scale;
        out[1] = (double)((float)mc.method_22683().method_4507() - Projection.PROJECTED.y) / scale;
        out[2] = depth;
        return true;
    }

    public static double getDistanceToGround() {
        for (double y = Projection.mc.field_1724.method_23318(); y > 0.0; y -= 0.1) {
            if (Projection.mc.field_1687.method_8320(Projection.mc.field_1724.method_24515().method_10087((int)(Projection.mc.field_1724.method_23318() - y + 1.0))).method_26215()) continue;
            return Projection.mc.field_1724.method_23318() - y;
        }
        return 256.0;
    }

    public static double getMaxRenderDistanceSq() {
        if (Projection.mc.field_1690 == null) {
            return 65536.0;
        }
        double max = (double)((Integer)Projection.mc.field_1690.method_42503().method_41753()).intValue() * 16.0;
        return max * max;
    }

    public static boolean isWithinRenderDistance(class_1297 ent) {
        if (ent == null || Projection.mc.field_1724 == null) {
            return false;
        }
        return ent.method_5858((class_1297)Projection.mc.field_1724) <= Projection.getMaxRenderDistanceSq();
    }

    public static Vector4d getVector4D(class_1297 ent) {
        Vector4d out = new Vector4d();
        return Projection.fillVector4D(ent, out) ? out : null;
    }

    public static boolean fillVector4D(class_1297 ent, Vector4d out) {
        if (ent == null || !Projection.isWithinRenderDistance(ent)) {
            return false;
        }
        class_243 pos = Calculate.interpolate(ent);
        class_238 bb = ent.method_5829();
        double dx = pos.field_1352 - ent.method_23317();
        double dy = pos.field_1351 - ent.method_23318();
        double dz = pos.field_1350 - ent.method_23321();
        double minX = bb.field_1323 + dx - 0.1;
        double minY = bb.field_1322 + dy - 0.1;
        double minZ = bb.field_1321 + dz - 0.1;
        double maxX = bb.field_1320 + dx + 0.1;
        double maxY = bb.field_1325 + dy + 0.1;
        double maxZ = bb.field_1324 + dz + 0.1;
        boolean visible = false;
        visible |= Projection.mergeCornerInto(out, visible, minX, minY, minZ);
        visible |= Projection.mergeCornerInto(out, visible, minX, maxY, minZ);
        visible |= Projection.mergeCornerInto(out, visible, maxX, minY, minZ);
        visible |= Projection.mergeCornerInto(out, visible, maxX, maxY, minZ);
        visible |= Projection.mergeCornerInto(out, visible, minX, minY, maxZ);
        visible |= Projection.mergeCornerInto(out, visible, minX, maxY, maxZ);
        visible |= Projection.mergeCornerInto(out, visible, maxX, minY, maxZ);
        return (visible |= Projection.mergeCornerInto(out, visible, maxX, maxY, maxZ)) && !Projection.cantSee(out);
    }

    private static boolean mergeCornerInto(Vector4d position, boolean hasCorner, double x, double y, double z) {
        if (!Projection.worldSpaceToScreenSpaceInto(x, y, z, SCREEN_SCRATCH)) {
            return hasCorner;
        }
        double screenX = SCREEN_SCRATCH[0];
        double screenY = SCREEN_SCRATCH[1];
        if (!hasCorner) {
            position.x = screenX;
            position.y = screenY;
            position.z = screenX;
            position.w = screenY;
            return true;
        }
        position.x = Math.min(screenX, position.x);
        position.y = Math.min(screenY, position.y);
        position.z = Math.max(screenX, position.z);
        position.w = Math.max(screenY, position.w);
        return true;
    }

    @Deprecated
    @NotNull
    public static class_243[] getVec3ds(class_1297 ent, class_243 pos) {
        class_238 axisAlignedBB2 = ent.method_5829();
        class_238 axisAlignedBB = new class_238(axisAlignedBB2.field_1323 - ent.method_23317() + pos.field_1352 - (double)0.1f, axisAlignedBB2.field_1322 - ent.method_23318() + pos.field_1351 - (double)0.1f, axisAlignedBB2.field_1321 - ent.method_23321() + pos.field_1350 - (double)0.1f, axisAlignedBB2.field_1320 - ent.method_23317() + pos.field_1352 + (double)0.1f, axisAlignedBB2.field_1325 - ent.method_23318() + pos.field_1351 + (double)0.1f, axisAlignedBB2.field_1324 - ent.method_23321() + pos.field_1350 + (double)0.1f);
        return new class_243[]{new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1322, axisAlignedBB.field_1321), new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1325, axisAlignedBB.field_1321), new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1322, axisAlignedBB.field_1321), new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1325, axisAlignedBB.field_1321), new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1322, axisAlignedBB.field_1324), new class_243(axisAlignedBB.field_1323, axisAlignedBB.field_1325, axisAlignedBB.field_1324), new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1322, axisAlignedBB.field_1324), new class_243(axisAlignedBB.field_1320, axisAlignedBB.field_1325, axisAlignedBB.field_1324)};
    }

    public static boolean isInFrontOfCamera(class_243 worldPos) {
        class_4184 camera = Projection.mc.method_1561().field_4686;
        if (camera == null) {
            return false;
        }
        class_243 delta = worldPos.method_1020(camera.method_19326());
        if (delta.method_1027() < 1.0E-6) {
            return false;
        }
        class_243 look = class_243.method_1030((float)camera.method_19329(), (float)camera.method_19330());
        return look.method_1026(delta) > 0.0;
    }

    public static boolean canSee(class_243 vec3d) {
        class_4184 camera = Projection.mc.method_1561().field_4686;
        Turns angle = MathAngle.fromVec3d(vec3d.method_1020(camera.method_19326()));
        return Math.abs(class_3532.method_15393((float)(angle.getYaw() - camera.method_19330()))) < 90.0f && Math.abs(class_3532.method_15393((float)(angle.getPitch() - camera.method_19329()))) < 60.0f || Projection.canSee(new class_238(class_2338.method_49638((class_2374)vec3d)));
    }

    public static boolean canSee(class_238 box) {
        class_4604 frustum = Projection.mc.field_1769.field_27740;
        if (frustum == null) {
            return true;
        }
        return box != null && frustum.method_23093(box);
    }

    public static boolean cantSee(Vector4d vec) {
        return vec == null || vec.x < 0.0 && vec.z < 1.0 || vec.y < 0.0 && vec.w < 1.0;
    }

    public static double centerX(Vector4d vec) {
        return vec.x + (vec.z - vec.x) / 2.0;
    }

    private Projection() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
