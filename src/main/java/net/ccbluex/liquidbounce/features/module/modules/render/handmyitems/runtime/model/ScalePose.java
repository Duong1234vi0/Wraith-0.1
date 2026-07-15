/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.interfaces.Poses;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.parents.AbstractPose;
import com.mojang.blaze3d.vertex.PoseStack;

public class ScalePose
extends AbstractPose
implements Poses {
    float x;
    float y;
    float z;

    public ScalePose(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void applyPose(PoseStack matrices) {
        matrices.scale(this.x, this.y, this.z);
    }
}
