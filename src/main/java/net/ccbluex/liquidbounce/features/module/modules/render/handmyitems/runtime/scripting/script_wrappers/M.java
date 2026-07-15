/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  net.minecraft.util.Mth
 *  com.mojang.math.Axis
 *  org.joml.Matrix4f
 *  org.joml.Matrix4fc
 *  org.joml.Quaternionfc
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.scripting.script_wrappers;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.util.Mth;
import com.mojang.math.Axis;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Quaternionfc;

public class M {
    public double PI = 3.1415927410125732;

    @Safe
    public void scale(PoseStack matrices, double x, double y, double z) {
        matrices.scale((float)x, (float)y, (float)z);
    }

    @Safe
    public void push(PoseStack matrices) {
        matrices.pushPose();
    }

    @Safe
    public void pop(PoseStack matrices) {
        matrices.popPose();
    }

    @Safe
    public void moveX(PoseStack matrices, double amount) {
        matrices.translate(amount, 0.0, 0.0);
    }

    @Safe
    public void moveY(PoseStack matrices, double amount) {
        matrices.translate(0.0, amount, 0.0);
    }

    @Safe
    public void moveZ(PoseStack matrices, double amount) {
        matrices.translate(0.0, 0.0, amount);
    }

    @Safe
    public void translate(PoseStack matrices, double x, double y, double z) {
        matrices.translate(x, y, z);
    }

    @Safe
    public void rotateX(PoseStack matrices, double amount) {
        matrices.mulPose((Quaternionfc)Axis.XP.rotationDegrees((float)amount));
    }

    @Safe
    public void rotateY(PoseStack matrices, double amount) {
        matrices.mulPose((Quaternionfc)Axis.YP.rotationDegrees((float)amount));
    }

    @Safe
    public void rotateZ(PoseStack matrices, double amount) {
        matrices.mulPose((Quaternionfc)Axis.ZP.rotationDegrees((float)amount));
    }

    @Safe
    public void rotateX(PoseStack matrices, double amount, double x, double y, double z) {
        matrices.rotateAround((Quaternionfc)Axis.XP.rotationDegrees((float)amount), (float)x, (float)y, (float)z);
    }

    @Safe
    public void rotateY(PoseStack matrices, double amount, double x, double y, double z) {
        matrices.rotateAround((Quaternionfc)Axis.YP.rotationDegrees((float)amount), (float)x, (float)y, (float)z);
    }

    @Safe
    public void rotateZ(PoseStack matrices, double amount, double x, double y, double z) {
        matrices.rotateAround((Quaternionfc)Axis.ZP.rotationDegrees((float)amount), (float)x, (float)y, (float)z);
    }

    @Safe
    public double sin(double a) {
        return Math.sin(a);
    }

    @Safe
    public double cos(double a) {
        return Math.cos(a);
    }

    @Safe
    public double clamp(double a, double min, double max) {
        return Math.clamp(a, min, max);
    }

    @Safe
    public double floor(double a) {
        return Math.floor(a);
    }

    @Safe
    public double abs(double a) {
        return Math.abs(a);
    }

    @Safe
    public double lerp(double a, double start, double end) {
        return Mth.lerp((double)a, (double)start, (double)end);
    }

    @Safe
    public double pow(double a, double b) {
        return Math.pow(a, b);
    }

    @Safe
    public double ceil(double a) {
        return Math.ceil(a);
    }

    @Safe
    public double round(double a) {
        return Math.round(a);
    }

    @Safe
    public void shear(PoseStack matrices, double shearX, double shearY, double shearZ) {
        Matrix4f shearMatrix = new Matrix4f(1.0f, (float)shearX, (float)shearX, 0.0f, (float)shearY, 1.0f, (float)shearY, 0.0f, (float)shearZ, (float)shearZ, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        matrices.last().pose().mul((Matrix4fc)shearMatrix);
    }
}
