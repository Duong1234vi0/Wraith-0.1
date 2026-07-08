/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_9779$class_9781
 *  org.spongepowered.asm.mixin.Final
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Mutable
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.Unique
 */
package code.alphadlc.mixins;

import code.alphadlc.utils.client.timer.IRenderTickCounterDynamic;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(value={class_9779.class_9781.class})
public class RenderTickCounterDynamicMixin
implements IRenderTickCounterDynamic {
    @Shadow
    @Final
    @Mutable
    private float field_51964;
    @Unique
    private float speedMultiplier = 1.0f;
    @Unique
    private final float baseTickTime = 50.0f;

    @Override
    public void setSpeedMultiplier(float speed) {
        this.speedMultiplier = Math.max(0.1f, Math.min(speed, 10.0f));
        this.field_51964 = 50.0f / this.speedMultiplier;
    }

    @Override
    public float getSpeedMultiplier() {
        return this.speedMultiplier;
    }
}
