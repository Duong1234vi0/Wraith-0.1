/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_243
 */
package code.alphadlc.features.impl.render.jumpCircle.data;

import code.alphadlc.common.animation.Animation;
import net.minecraft.class_243;

public record JumpCircleLayer(class_243 vec3d, long startTime, Animation animation) {
    public JumpCircleLayer(class_243 vec3d, Animation animation) {
        this(vec3d, System.currentTimeMillis(), animation);
    }
}
