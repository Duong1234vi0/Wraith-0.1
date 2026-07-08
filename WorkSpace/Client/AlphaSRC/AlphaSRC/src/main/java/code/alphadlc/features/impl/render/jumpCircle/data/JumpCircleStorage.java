/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1657
 *  net.minecraft.class_243
 */
package code.alphadlc.features.impl.render.jumpCircle.data;

import code.alphadlc.common.animation.implement.OutBack;
import code.alphadlc.features.impl.render.jumpCircle.data.JumpCircleLayer;
import code.alphadlc.features.impl.render.jumpCircle.receiver.JumpCircleModule;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.class_1657;
import net.minecraft.class_243;

public class JumpCircleStorage {
    private final List<JumpCircleLayer> jumpCircleLayers = new CopyOnWriteArrayList<JumpCircleLayer>();
    private final JumpCircleModule module;

    public JumpCircleStorage(JumpCircleModule module) {
        this.module = module;
    }

    public void add(class_243 vec3d) {
        int lifeTime = (int)this.module.getLifeTime().getValue();
        this.jumpCircleLayers.add(new JumpCircleLayer(vec3d, new OutBack().setMs(lifeTime).setValue(1.0)));
    }

    public void add(class_1657 player) {
        this.add(player.method_19538().method_1031(0.0, (double)0.01f, 0.0));
    }

    public boolean isPresent() {
        return !this.jumpCircleLayers.isEmpty();
    }

    public List<JumpCircleLayer> getJumpCircleLayers() {
        return this.jumpCircleLayers;
    }

    public JumpCircleModule getModule() {
        return this.module;
    }
}
