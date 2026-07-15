/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.math.Axis
 *  org.joml.Quaternionfc
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.interfaces.Poses;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.parents.AbstractPose;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import org.joml.Quaternionfc;

public class RotationZ
extends AbstractPose
implements Poses {
    private float x;
    private float y;
    private float z;
    private float amount;

    public RotationZ(float amount, float x, float y, float z) {
        this.amount = amount;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void applyPose(PoseStack matrices) {
        matrices.rotateAround((Quaternionfc)Axis.ZP.rotationDegrees(this.amount), this.x, this.y, this.z);
    }
}
