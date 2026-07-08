/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.class_1297
 *  net.minecraft.class_1937
 *  net.minecraft.class_2248
 *  net.minecraft.class_2338
 *  net.minecraft.class_4970$class_4971
 *  org.spongepowered.asm.mixin.Mixin
 *  org.spongepowered.asm.mixin.Shadow
 *  org.spongepowered.asm.mixin.injection.At
 *  org.spongepowered.asm.mixin.injection.Inject
 *  org.spongepowered.asm.mixin.injection.callback.CallbackInfo
 */
package code.alphadlc.mixins;

import code.alphadlc.events.player.PlayerCollisionEvent;
import code.alphadlc.utils.client.managers.event.SafeEvents;
import code.alphadlc.utils.display.interfaces.QuickImports;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2248;
import net.minecraft.class_2338;
import net.minecraft.class_4970;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={class_4970.class_4971.class})
public abstract class AbstractBlockStateMixin
implements QuickImports {
    @Shadow
    public abstract class_2248 method_26204();

    @Inject(method={"onEntityCollision"}, at={@At(value="HEAD")}, cancellable=true)
    public void onEntityCollision(class_1937 world, class_2338 pos, class_1297 entity, CallbackInfo ci) {
        if (entity != AbstractBlockStateMixin.mc.field_1724) {
            return;
        }
        PlayerCollisionEvent event = SafeEvents.dispatch(() -> new PlayerCollisionEvent(this.method_26204()));
        if (event != null && event.isCancelled()) {
            ci.cancel();
        }
    }
}
