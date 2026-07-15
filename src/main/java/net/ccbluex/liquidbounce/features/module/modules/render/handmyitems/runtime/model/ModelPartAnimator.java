/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 */
package net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model;

import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.annotation.Safe;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.PoseX;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.PoseY;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.PoseZ;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.RotationX;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.RotationY;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.RotationZ;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.ScalePose;
import net.ccbluex.liquidbounce.features.module.modules.render.handmyitems.runtime.model.parents.AbstractPose;
import java.util.ArrayList;
import com.mojang.blaze3d.vertex.PoseStack;

public class ModelPartAnimator {
    private final ArrayList<AbstractPose> poseStack = new ArrayList();

    private void addPose(int index, AbstractPose pose) {
        pose.index = index;
        this.poseStack.add(pose);
    }

    @Safe
    public void moveX(int indexStart, int indexEnd, float amount) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new PoseX(amount));
        }
    }

    @Safe
    public void moveY(int indexStart, int indexEnd, float amount) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new PoseY(amount));
        }
    }

    @Safe
    public void moveZ(int indexStart, int indexEnd, float amount) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new PoseZ(amount));
        }
    }

    @Safe
    public void rotateX(int indexStart, int indexEnd, float amount) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new RotationX(amount, 0.0f, 0.0f, 0.0f));
        }
    }

    @Safe
    public void rotateY(int indexStart, int indexEnd, float amount) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new RotationY(amount, 0.0f, 0.0f, 0.0f));
        }
    }

    @Safe
    public void rotateZ(int indexStart, int indexEnd, float amount) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new RotationZ(amount, 0.0f, 0.0f, 0.0f));
        }
    }

    @Safe
    public void rotateX(int indexStart, int indexEnd, float amount, float x, float y, float z) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new RotationX(amount, x, y, z));
        }
    }

    @Safe
    public void rotateY(int indexStart, int indexEnd, float amount, float x, float y, float z) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new RotationY(amount, x, y, z));
        }
    }

    @Safe
    public void rotateZ(int indexStart, int indexEnd, float amount, float x, float y, float z) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new RotationZ(amount, x, y, z));
        }
    }

    @Safe
    public void scale(int indexStart, int indexEnd, float x, float y, float z) {
        for (int i = indexStart; i <= indexEnd; ++i) {
            this.addPose(i, new ScalePose(x, y, z));
        }
    }

    public void applyPoses(int index, PoseStack matrixStack) {
        this.poseStack.reversed().forEach(pose -> {
            if (pose.index == index) {
                pose.applyPose(matrixStack);
            }
        });
    }

    public void clear() {
        this.poseStack.clear();
    }
}
