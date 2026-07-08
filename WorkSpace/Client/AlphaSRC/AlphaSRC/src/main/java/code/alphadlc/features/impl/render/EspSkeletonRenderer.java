/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 *  net.minecraft.class_310
 *  net.minecraft.class_3532
 *  net.minecraft.class_4587
 *  net.minecraft.class_7833
 *  org.joml.Vector3f
 */
package code.alphadlc.features.impl.render;

import code.alphadlc.utils.display.render.geometry.Render3D;
import code.alphadlc.utils.math.projection.Projection;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_7833;
import org.joml.Vector3f;

final class EspSkeletonRenderer {
    private static final int MAX_BONES_PER_PLAYER = 15;
    private static final Vector3f TRANSFORMED = new Vector3f();
    private static final List<class_243[]> BONE_BUFFER = new ArrayList<class_243[]>(64);

    private EspSkeletonRenderer() {
    }

    static void renderPlayers(class_4587 matrices, List<class_1657> players, float tickDelta, float lineWidth, int color) {
        if (players.isEmpty()) {
            return;
        }
        class_310 mc = class_310.method_1551();
        BONE_BUFFER.clear();
        for (class_1657 player : players) {
            if (player == null || player.method_5767() || player == mc.field_1724 && mc.field_1690.method_31044().method_31034() || !Projection.isWithinRenderDistance((class_1297)player) || !Projection.canSee(player.method_5829())) continue;
            EspSkeletonRenderer.collectBones(player, tickDelta, BONE_BUFFER);
        }
        if (BONE_BUFFER.isEmpty()) {
            return;
        }
        for (class_243[] bone : BONE_BUFFER) {
            Render3D.drawLine(bone[0], bone[1], color, lineWidth, false);
        }
    }

    private static void collectBones(class_1657 player, float tickDelta, List<class_243[]> out) {
        float rArmRot;
        float lArmRot;
        double x = class_3532.method_16436((double)tickDelta, (double)player.field_6014, (double)player.method_23317());
        double y = class_3532.method_16436((double)tickDelta, (double)player.field_6036, (double)player.method_23318());
        double z = class_3532.method_16436((double)tickDelta, (double)player.field_5969, (double)player.method_23321());
        float bodyYaw = class_3532.method_17821((float)tickDelta, (float)player.field_6220, (float)player.field_6283);
        float headYaw = class_3532.method_17821((float)tickDelta, (float)player.field_6259, (float)player.field_6241);
        float pitch = class_3532.method_16439((float)tickDelta, (float)player.field_6004, (float)player.method_36455());
        float swing = player.field_42108.method_48572(tickDelta);
        float swingAmt = Math.min(player.field_42108.method_48570(tickDelta), 1.0f) * 0.5f;
        float handSwing = player.method_6055(tickDelta);
        float height = player.method_17682();
        boolean elytra = player.method_6128();
        boolean sneak = player.method_5715();
        class_4587 stack = new class_4587();
        stack.method_22904(x, y, z);
        if (sneak && !elytra) {
            stack.method_22904(0.0, 0.125, 0.0);
        }
        stack.method_22907(class_7833.field_40716.rotationDegrees(-bodyYaw));
        float bodyPitch = 0.0f;
        if (elytra) {
            bodyPitch = 1.57f + pitch / 57.2958f;
        } else if (sneak) {
            bodyPitch = 0.5f;
        }
        if (elytra || sneak) {
            stack.method_22907(class_7833.field_40714.rotationDegrees(bodyPitch * 57.2958f));
        }
        if (sneak && !elytra) {
            stack.method_22904(0.0, -0.13, 0.0);
        }
        stack.method_22903();
        stack.method_22904(0.0, (double)height * 0.75, 0.0);
        class_243 neck = EspSkeletonRenderer.getPos(stack);
        stack.method_22903();
        stack.method_22907(class_7833.field_40716.rotationDegrees(bodyYaw - headYaw));
        if (!elytra) {
            stack.method_22907(class_7833.field_40714.rotationDegrees(pitch));
        }
        stack.method_22904(0.0, (double)height * 0.15, 0.0);
        class_243 head = EspSkeletonRenderer.getPos(stack);
        stack.method_22909();
        stack.method_22903();
        stack.method_22904(0.25, 0.0, 0.0);
        class_243 lShoulder = EspSkeletonRenderer.getPos(stack);
        float f = lArmRot = elytra ? -0.2f : class_3532.method_15362((float)(swing * 0.6662f + (float)Math.PI)) * 0.8f * swingAmt;
        if (elytra) {
            stack.method_22907(class_7833.field_40718.rotationDegrees(-5.0f));
        }
        stack.method_22907(class_7833.field_40714.rotationDegrees(lArmRot * 57.2958f));
        stack.method_22904(0.0, -0.25, 0.0);
        class_243 lElbow = EspSkeletonRenderer.getPos(stack);
        stack.method_22907(class_7833.field_40714.rotationDegrees(Math.max(0.0f, lArmRot * 15.0f)));
        stack.method_22904(0.0, -0.25, 0.0);
        class_243 lHand = EspSkeletonRenderer.getPos(stack);
        stack.method_22909();
        stack.method_22903();
        stack.method_22904(-0.25, 0.0, 0.0);
        class_243 rShoulder = EspSkeletonRenderer.getPos(stack);
        float f2 = rArmRot = elytra ? -0.2f : class_3532.method_15362((float)(swing * 0.6662f)) * 0.8f * swingAmt;
        if (elytra) {
            stack.method_22907(class_7833.field_40718.rotationDegrees(5.0f));
        }
        if (handSwing > 0.0f && !elytra) {
            float swingProgress = 1.0f - handSwing;
            swingProgress *= swingProgress;
            float swingRot = class_3532.method_15374((float)(swingProgress * (float)Math.PI));
            float headYawDiff = headYaw - bodyYaw;
            float yawFactor = class_3532.method_15363((float)(headYawDiff / 75.0f), (float)-1.0f, (float)1.0f);
            stack.method_22907(class_7833.field_40716.rotationDegrees(swingRot * 15.0f * yawFactor));
            rArmRot += -swingRot * 0.8f;
        }
        stack.method_22907(class_7833.field_40714.rotationDegrees(rArmRot * 57.2958f));
        stack.method_22904(0.0, -0.25, 0.0);
        class_243 rElbow = EspSkeletonRenderer.getPos(stack);
        stack.method_22907(class_7833.field_40714.rotationDegrees(Math.max(0.0f, rArmRot * 15.0f)));
        stack.method_22904(0.0, -0.25, 0.0);
        class_243 rHand = EspSkeletonRenderer.getPos(stack);
        stack.method_22909();
        stack.method_22909();
        stack.method_22903();
        stack.method_22904(0.0, (double)height * 0.5, 0.0);
        class_243 waist = EspSkeletonRenderer.getPos(stack);
        stack.method_22909();
        stack.method_22903();
        stack.method_22904(0.0, (double)height * 0.3, 0.0);
        class_243 pelvis = EspSkeletonRenderer.getPos(stack);
        stack.method_22903();
        stack.method_22904(0.125, 0.0, 0.0);
        class_243 lHip = EspSkeletonRenderer.getPos(stack);
        float lLegRot = elytra ? 0.1f : class_3532.method_15362((float)(swing * 0.6662f)) * 0.5f * swingAmt;
        stack.method_22907(class_7833.field_40714.rotationDegrees(lLegRot * 57.2958f));
        stack.method_22904(0.0, -0.25, 0.0);
        class_243 lKnee = EspSkeletonRenderer.getPos(stack);
        stack.method_22907(class_7833.field_40714.rotationDegrees(Math.abs(lLegRot) * 15.0f));
        stack.method_22904(0.0, -0.25, 0.0);
        class_243 lFoot = EspSkeletonRenderer.getPos(stack);
        stack.method_22909();
        stack.method_22903();
        stack.method_22904(-0.125, 0.0, 0.0);
        class_243 rHip = EspSkeletonRenderer.getPos(stack);
        float rLegRot = elytra ? 0.1f : class_3532.method_15362((float)(swing * 0.6662f + (float)Math.PI)) * 0.5f * swingAmt;
        stack.method_22907(class_7833.field_40714.rotationDegrees(rLegRot * 57.2958f));
        stack.method_22904(0.0, -0.25, 0.0);
        class_243 rKnee = EspSkeletonRenderer.getPos(stack);
        stack.method_22907(class_7833.field_40714.rotationDegrees(Math.abs(rLegRot) * 15.0f));
        stack.method_22904(0.0, -0.25, 0.0);
        class_243 rFoot = EspSkeletonRenderer.getPos(stack);
        stack.method_22909();
        stack.method_22909();
        EspSkeletonRenderer.addBone(out, neck, head);
        EspSkeletonRenderer.addBone(out, neck, waist);
        EspSkeletonRenderer.addBone(out, waist, pelvis);
        EspSkeletonRenderer.addBone(out, neck, lShoulder);
        EspSkeletonRenderer.addBone(out, neck, rShoulder);
        EspSkeletonRenderer.addBone(out, lShoulder, lElbow);
        EspSkeletonRenderer.addBone(out, lElbow, lHand);
        EspSkeletonRenderer.addBone(out, rShoulder, rElbow);
        EspSkeletonRenderer.addBone(out, rElbow, rHand);
        EspSkeletonRenderer.addBone(out, pelvis, lHip);
        EspSkeletonRenderer.addBone(out, pelvis, rHip);
        EspSkeletonRenderer.addBone(out, lHip, lKnee);
        EspSkeletonRenderer.addBone(out, lKnee, lFoot);
        EspSkeletonRenderer.addBone(out, rHip, rKnee);
        EspSkeletonRenderer.addBone(out, rKnee, rFoot);
    }

    private static void addBone(List<class_243[]> out, class_243 start, class_243 end) {
        if (out.size() >= 960) {
            return;
        }
        out.add(new class_243[]{start, end});
    }

    private static class_243 getPos(class_4587 stack) {
        stack.method_23760().method_23761().transformPosition(0.0f, 0.0f, 0.0f, TRANSFORMED);
        return new class_243((double)EspSkeletonRenderer.TRANSFORMED.x, (double)EspSkeletonRenderer.TRANSFORMED.y, (double)EspSkeletonRenderer.TRANSFORMED.z);
    }
}
