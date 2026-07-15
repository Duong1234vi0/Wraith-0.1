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

public class PoseX
extends AbstractPose
implements Poses {
    float amount;

    public PoseX(float amount) {
        this.amount = amount;
    }

    @Override
    public void applyPose(PoseStack matrices) {
        matrices.translate(this.amount, 0.0f, 0.0f);
    }
}
