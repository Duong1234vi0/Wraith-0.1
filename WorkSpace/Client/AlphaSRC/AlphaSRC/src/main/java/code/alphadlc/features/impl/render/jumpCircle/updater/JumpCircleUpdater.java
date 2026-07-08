/*
 * Decompiled with CFR 0.152.
 */
package code.alphadlc.features.impl.render.jumpCircle.updater;

import code.alphadlc.common.animation.Direction;
import code.alphadlc.features.impl.render.jumpCircle.JumpCircleUtility;
import code.alphadlc.features.impl.render.jumpCircle.data.JumpCircleLayer;
import code.alphadlc.features.impl.render.jumpCircle.data.JumpCircleStorage;
import code.alphadlc.features.impl.render.jumpCircle.receiver.JumpCircleModule;

public record JumpCircleUpdater(JumpCircleModule module) {
    public void update() {
        JumpCircleStorage storage = this.module.getStorage();
        for (JumpCircleLayer jumpCircleLayer : storage.getJumpCircleLayers()) {
            if (!JumpCircleUtility.isExpired(jumpCircleLayer.startTime(), this.module.getLifeTime().getValue())) continue;
            jumpCircleLayer.animation().setDirection(Direction.BACKWARDS);
        }
        storage.getJumpCircleLayers().removeIf(layer -> layer.animation().isFinished(Direction.BACKWARDS));
    }
}
